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
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.security.Principal;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WootheeMethodArgumentResolverTest {

    @Test
    public void testSupportWootheeData() throws Exception {
        MethodParameter parameter = new MethodParameter(FooController.class.getMethod("testData", Woothee.class), 0);
        WootheeMethodArgumentResolver resolver = new WootheeMethodArgumentResolver();
        assertThat(resolver.supportsParameter(parameter), is(true));
    }

    @Test
    public void testSupportString() throws Exception {
        MethodParameter parameter = new MethodParameter(FooController.class.getMethod("testString", String.class), 0);
        WootheeMethodArgumentResolver resolver = new WootheeMethodArgumentResolver();
        assertThat(resolver.supportsParameter(parameter), is(false));
    }

    @Test
    public void testResolveWootheeData() throws Exception {
        MethodParameter parameter = new MethodParameter(FooController.class.getMethod("testData", Woothee.class), 0);
        WootheeMethodArgumentResolver resolver = new WootheeMethodArgumentResolver();
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36";
        Object result = resolver.resolveArgument(parameter, null, new MockWebRequest(userAgent), null);
        assertThat(result, instanceOf(Woothee.class));
        Woothee data = (Woothee) result;
        assertThat(data.getName(), is("Chrome"));
        assertThat(data.getCategory(), is("pc"));
        assertThat(data.getOs(), is("Windows 7"));
        assertThat(data.getVersion(), is("38.0.2125.111"));
        assertThat(data.getVendor(), is("Google"));
        assertThat(data.getOsVersion(), is("NT 6.1"));
        assertThat(data.getUserAgent(), is(userAgent));
    }
}

@RestController
class FooController {
    @RequestMapping("data")
    public Woothee testData(Woothee data) {
        return data;
    }

    @RequestMapping("String")
    public String testString(String data) {
        return data;
    }
}

class MockWebRequest implements NativeWebRequest {
    String userAgent;

    public MockWebRequest(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public Object getNativeRequest() {
        return null;
    }

    @Override
    public Object getNativeResponse() {
        return null;
    }

    @Override
    public <T> T getNativeRequest(Class<T> requiredType) {
        return null;
    }

    @Override
    public <T> T getNativeResponse(Class<T> requiredType) {
        return null;
    }

    @Override
    public String getHeader(String headerName) {
        return userAgent;
    }

    @Override
    public String[] getHeaderValues(String headerName) {
        return new String[0];
    }

    @Override
    public Iterator<String> getHeaderNames() {
        return null;
    }

    @Override
    public String getParameter(String paramName) {
        return null;
    }

    @Override
    public String[] getParameterValues(String paramName) {
        return new String[0];
    }

    @Override
    public Iterator<String> getParameterNames() {
        return null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return null;
    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public String getContextPath() {
        return null;
    }

    @Override
    public String getRemoteUser() {
        return null;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public boolean checkNotModified(long lastModifiedTimestamp) {
        return false;
    }

    @Override
    public boolean checkNotModified(String etag) {
        return false;
    }

    @Override
    public String getDescription(boolean includeClientInfo) {
        return null;
    }

    @Override
    public Object getAttribute(String name, int scope) {
        return null;
    }

    @Override
    public void setAttribute(String name, Object value, int scope) {

    }

    @Override
    public void removeAttribute(String name, int scope) {

    }

    @Override
    public String[] getAttributeNames(int scope) {
        return new String[0];
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback, int scope) {

    }

    @Override
    public Object resolveReference(String key) {
        return null;
    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public Object getSessionMutex() {
        return null;
    }
}
