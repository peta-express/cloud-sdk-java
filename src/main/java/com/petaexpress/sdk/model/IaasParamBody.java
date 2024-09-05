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

package com.petaexpress.sdk.model;

import com.petaexpress.sdk.annotation.ParamAnnotation;

/** Created by karooli on 16/9/23. */
public class IaasParamBody extends RequestInputModel {

    private String zone;

    private String action;

    @ParamAnnotation(paramType = "query", paramName = "zone")
    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @ParamAnnotation(paramType = "query", paramName = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
