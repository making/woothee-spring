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
package am.ik.woothee.spring;

import am.ik.woothee.Woothee;
import is.tagomor.woothee.Classifier;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

/**
 * Resolves {@link am.ik.woothee.Woothee} method arguments.
 */
public class WootheeMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        return Woothee.class.isAssignableFrom(paramType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String userAgent = webRequest.getHeader("User-Agent");
        Map<String, String> result = Classifier.parse(userAgent);
        String category = result.get("category");
        String name = result.get("name");
        String version = result.get("version");
        String os = result.get("os");
        String vendor = result.get("vendor");
        String osVersion = result.get("os_version");
        return new Woothee(category, name, version, os, vendor, osVersion, userAgent);
    }
}
