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

package com.petaexpress.sdk.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.petaexpress.sdk.config.EnvContext;
import com.petaexpress.sdk.constants.QCConstant;
import com.petaexpress.sdk.model.OutputModel;
import com.petaexpress.sdk.request.ResponseCallBack;
import com.petaexpress.sdk.request.ResourceRequestFactory;
import com.petaexpress.sdk.exception.QCException;
import com.petaexpress.sdk.model.IaasParamBody;
import com.petaexpress.sdk.service.Types.*;
import com.petaexpress.sdk.utils.QCStringUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataService {

    private EnvContext envContext;

    private String zone;

    public UserDataService(EnvContext envContext) {
        this.envContext = envContext;
    }

    public UserDataService(EnvContext envContext, String zone) {
        this.envContext = envContext;
        this.zone = zone;
    }
        
    /**
      * @param input Request parameters and headers in the class
      * @return UploadUserDataAttachmentOutput Response body and headers in the class
      * @throws QCException IOException or network error
      *
      * <a href=https://docs.petaexpress.com/api/userdata/upload_userdata_attachment.html>Documentation URL</a>
      */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public UploadUserDataAttachmentOutput uploadUserDataAttachment(UploadUserDataAttachmentInput input) throws QCException {
        if (input == null) {
            input = new UploadUserDataAttachmentInput();
        }
        Map context = new HashMap();
        context.put(QCConstant.ENV_CONTEXT_KEY, this.envContext);
        context.put("OperationName", "UploadUserDataAttachment");
        context.put("APIName", "UploadUserDataAttachment");
        context.put("ServiceName", "UploadUserDataAttachment");
        context.put("RequestMethod", "POST");
        context.put("RequestURI", "/iaas/UploadUserDataAttachment");

        input.setAction("UploadUserDataAttachment");
        if (QCStringUtil.isEmpty(this.zone)) {
            input.setZone(this.envContext.getZone());
        } else {
            input.setZone(this.zone);
        }

        Object backModel = ResourceRequestFactory.getResourceRequest().sendApiRequest(context, input,UploadUserDataAttachmentOutput.class);
        if(backModel != null){
            return (UploadUserDataAttachmentOutput)backModel;
        }
        return null;
    }

     /**
       * @param input Request parameters and headers in the class
       * @param callback Response calls back when success
       * @throws QCException IOException or network error
       *
       * <a href=https://docs.petaexpress.com/api/userdata/upload_userdata_attachment.html>Documentation URL</a>
       */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void uploadUserDataAttachment(UploadUserDataAttachmentInput input, ResponseCallBack<UploadUserDataAttachmentOutput> callback) throws QCException {
        if (input == null) {
            input = new UploadUserDataAttachmentInput();
        }
        Map context = new HashMap();
        context.put(QCConstant.ENV_CONTEXT_KEY, this.envContext);
        context.put("OperationName", "UploadUserDataAttachment");
        context.put("APIName", "UploadUserDataAttachment");
        context.put("ServiceName", "UploadUserDataAttachment");
        context.put("RequestMethod", "POST");
        context.put("RequestURI", "/iaas/UploadUserDataAttachment");

        input.setAction("UploadUserDataAttachment");
        if (QCStringUtil.isEmpty(this.zone)) {
            input.setZone(this.envContext.getZone());
        } else {
            input.setZone(this.zone);
        }

        if(callback == null) {
            throw new QCException("callback can't be null");
        }

        ResourceRequestFactory.getResourceRequest().sendApiRequestAsync(context,input,callback);
    }

    public static class UploadUserDataAttachmentInput extends IaasParamBody {
        // Required
        private String attachmentContent;

        @JsonProperty(value = "attachment_content")
        public void setAttachmentContent(String attachmentContent) {
            this.attachmentContent = attachmentContent;
        }

        @JsonProperty(value = "attachment_content")
        public String getAttachmentContent() {
            return this.attachmentContent;
        }

        private String attachmentName;

        @JsonProperty(value = "attachment_name")
        public void setAttachmentName(String attachmentName) {
            this.attachmentName = attachmentName;
        }

        @JsonProperty(value = "attachment_name")
        public String getAttachmentName() {
            return this.attachmentName;
        }

        public String validateParam() throws QCException {
            if (QCStringUtil.isEmpty(this.getAttachmentContent())) {
                throw new QCException("AttachmentContent is required");
            }
            return null;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UploadUserDataAttachmentOutput extends OutputModel {
        private String action;

        @JsonProperty(value = "action")
        public void setAction(String action) {
            this.action = action;
        }

        @JsonProperty(value = "action")
        public String getAction() {
            return this.action;
        }

        private String attachmentID;

        @JsonProperty(value = "attachment_id")
        public void setAttachmentID(String attachmentID) {
            this.attachmentID = attachmentID;
        }

        @JsonProperty(value = "attachment_id")
        public String getAttachmentID() {
            return this.attachmentID;
        }

        private Integer retCode;

        @JsonProperty(value = "ret_code")
        public void setRetCode(Integer retCode) {
            this.retCode = retCode;
        }

        @JsonProperty(value = "ret_code")
        public Integer getRetCode() {
            return this.retCode;
        }

    }}



















