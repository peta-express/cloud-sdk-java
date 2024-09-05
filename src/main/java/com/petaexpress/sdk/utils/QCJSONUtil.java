// +-------------------------------------------------------------------------
// | Copyright (C) 2016 RAKSmart, Inc.
// +-------------------------------------------------------------------------
// | Licensed under the Apache License, Version 2.0 (the "License");
// | you may not use this work except in compliance with the License.
// | You may obtain a copy of the License in the LICENSE file, or at:
// |
// | http://www.apache.org/licenses/LICENSE-2.0
// |
// | Unless required by applicable law or agreed to in writing, software
// | distributed under the License is distributed on an "AS IS" BASIS,
// | WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// | See the License for the specific language governing permissions and
// | limitations under the License.
// +-------------------------------------------------------------------------

package com.petaexpress.sdk.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petaexpress.sdk.config.EnvContext;
import com.petaexpress.sdk.constants.QCConstant;
import com.petaexpress.sdk.exception.QCException;
import com.petaexpress.sdk.model.RequestInputModel;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

import static com.petaexpress.sdk.constants.QCConstant.ENCODING_UTF8;
import static com.petaexpress.sdk.utils.QCSignatureUtil.computeIaasSignature;
import static com.petaexpress.sdk.utils.QCSignatureUtil.formatIso8601Date;
import static com.petaexpress.sdk.utils.QCStringUtil.percentEncode;

public class QCJSONUtil {

    private static final List<Class> PRIMITIVE_TYPES = new ArrayList<>(Arrays.asList(Boolean.class, String.class, Integer.class, Double.class, Long.class, Float.class, null, BigDecimal.class));

    public static String parseRequestParams(RequestInputModel inputModel, EnvContext envContext, String httpMethod) throws QCException {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> parameters = oMapper.convertValue(inputModel, Map.class);

        parameters.put("version", "1");
        parameters.put("signature_version", "1");
        parameters.put("signature_method", "HmacSHA256");
        parameters.put("access_key_id", envContext.getAccessKey());
        parameters.put("api_lang", envContext.getApiLang());
        parameters.put("time_stamp", formatIso8601Date(new Date()));
        if (envContext.getUri().equals("iam")) {
            parameters.put("token", envContext.getToken());
        }

        String uri = null;
        StringBuilder sbUri = new StringBuilder();

        // sort keys
        String[] keys = parameters.keySet().toArray(new String[] {});
        Arrays.sort(keys);

        try {
            for (String key : keys) {
                Object value = parameters.get(key);
                if (value == null) {
                    continue;
                }
                String encodedKey, encodedVal;
                if (PRIMITIVE_TYPES.contains(value.getClass())){
                    String valueStr = String.valueOf(value);
                    encodedKey = percentEncode(key, ENCODING_UTF8);
                    encodedVal = percentEncode(valueStr, QCConstant.ENCODING_UTF8);
                    sbUri.append(encodedKey).append("=").append(encodedVal).append("&");
                } else if (value instanceof List) {
                    int count = ((List) value).size();
                    List<String> listKeys = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        encodedKey = percentEncode(String.format("%s.%d", key, i+1), ENCODING_UTF8);
                        listKeys.add(encodedKey);
                    }
                    Collections.sort(listKeys);
                    for (int i = 0; i < count; i++) {
                        encodedKey = listKeys.get(i);
                        Object item = ((List) value).get(i);

                        if (item instanceof String) {
                            String itemStr = (String) item;
                            encodedVal = percentEncode(itemStr, ENCODING_UTF8);
                            sbUri.append(encodedKey).append("=").append(encodedVal).append("&");
                        } else if (item instanceof Map) {
                            // specified handler for GetMonitors post structure
                            for (Object itemKey : ((Map) item).keySet()) {
                                String deepEncodedKey = String.format("%s.%s", encodedKey, percentEncode((String) itemKey, ENCODING_UTF8));
                                Object deepItem = ((Map) item).get(itemKey);
                                String deepItemStr;
                                if (deepItem instanceof String) {
                                    deepItemStr = (String) deepItem;
                                } else {
                                    deepItemStr = oMapper.writeValueAsString(deepItem);
                                }
                                encodedVal = percentEncode(deepItemStr, ENCODING_UTF8);
                                sbUri.append(deepEncodedKey).append("=").append(encodedVal).append("&");
                            }
                        } else {
                            throw new QCException(String.format("Failed to parse input model: %s!", oMapper.writeValueAsString(inputModel)));
                        }
                    }
                } else {
                    throw new QCException(String.format("Failed to parse input model: %s!", oMapper.writeValueAsString(inputModel)));
                }
            }
            String tmp = sbUri.toString();
            String uriNotSigned = tmp.substring(0, tmp.length()-1);
            String signature = computeIaasSignature(httpMethod, envContext.getUri(), uriNotSigned, envContext.getAccessSecret());
            uri = String.format("%s&signature=%s", uriNotSigned, signature);
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return uri;
    }
}
