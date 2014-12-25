/*
*Copyright (C) 2014 Toshiaki Maki <makingx@gmail.com>
*
*Licensed under the Apache License, Version 2.0 (the "License");
*you may not use this file except in compliance with the License.
*You may obtain a copy of the License at
*
*        http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing, software
*distributed under the License is distributed on an "AS IS" BASIS,
*WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*See the License for the specific language governing permissions and
*limitations under the License.
*/
package am.ik.woothee;

import java.io.Serializable;

/**
 * The class stores information from {@link is.tagomor.woothee.Classifier#parse(java.lang.String)} and User-Agent.
 */
public class Woothee implements Serializable {
    private final String category;
    private final String name;
    private final String version;
    private final String os;
    private final String vendor;
    private final String osVersion;
    private final String userAgent;

    public Woothee(String category, String name, String version, String os, String vendor, String osVersion, String userAgent) {
        this.category = category;
        this.name = name;
        this.version = version;
        this.os = os;
        this.vendor = vendor;
        this.osVersion = osVersion;
        this.userAgent = userAgent;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getOs() {
        return os;
    }

    public String getVendor() {
        return vendor;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WootheeData{");
        sb.append("category='").append(category).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", os='").append(os).append('\'');
        sb.append(", vendor=").append(vendor);
        sb.append(", osVersion=").append(osVersion);
        sb.append('}');
        return sb.toString();
    }
}
