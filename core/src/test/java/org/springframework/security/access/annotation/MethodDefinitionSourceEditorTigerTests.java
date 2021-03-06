/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.access.annotation;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.annotation.test.Entity;
import org.springframework.security.access.annotation.test.PersonServiceImpl;
import org.springframework.security.access.annotation.test.Service;
import org.springframework.security.access.intercept.method.MockMethodInvocation;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSourceEditor;


/**
 * Extra tests to demonstrate generics behaviour with <code>MapBasedMethodDefinitionSource</code>.
 *
 * @author Ben Alex
 */
@SuppressWarnings("deprecation")
public class MethodDefinitionSourceEditorTigerTests {
    private MockMethodInvocation makeUpper;
    private MockMethodInvocation makeLower;

    @Before
    public void createMethodInvocations() throws Exception {
        makeUpper = new MockMethodInvocation(new PersonServiceImpl(), Service.class,"makeUpperCase", Entity.class);
        makeLower = new MockMethodInvocation(new PersonServiceImpl(), Service.class,"makeLowerCase", Entity.class);
    }

    @Test
    public void testConcreteClassInvocations() throws Exception {
        MethodSecurityMetadataSourceEditor editor = new MethodSecurityMetadataSourceEditor();
        editor.setAsText(
                "org.springframework.security.access.annotation.test.Service.makeLower*=ROLE_FROM_INTERFACE\r\n" +
                "org.springframework.security.access.annotation.test.Service.makeUpper*=ROLE_FROM_INTERFACE\r\n" +
                "org.springframework.security.access.annotation.test.ServiceImpl.makeUpper*=ROLE_FROM_IMPLEMENTATION");

        MapBasedMethodSecurityMetadataSource map = (MapBasedMethodSecurityMetadataSource) editor.getValue();
        assertEquals(3, map.getMethodMapSize());

        Collection<ConfigAttribute> returnedMakeLower = map.getAttributes(makeLower);
        List<ConfigAttribute> expectedMakeLower = SecurityConfig.createList("ROLE_FROM_INTERFACE");
        assertEquals(expectedMakeLower, returnedMakeLower);

        Collection<ConfigAttribute> returnedMakeUpper = map.getAttributes(makeUpper);
        List<ConfigAttribute> expectedMakeUpper = SecurityConfig.createList(new String[]{"ROLE_FROM_IMPLEMENTATION"});
        assertEquals(expectedMakeUpper, returnedMakeUpper);
    }

    @Test
    public void testBridgeMethodResolution() throws Exception {
        MethodSecurityMetadataSourceEditor editor = new MethodSecurityMetadataSourceEditor();
        editor.setAsText(
                "org.springframework.security.access.annotation.test.PersonService.makeUpper*=ROLE_FROM_INTERFACE\r\n" +
                "org.springframework.security.access.annotation.test.ServiceImpl.makeUpper*=ROLE_FROM_ABSTRACT\r\n" +
                "org.springframework.security.access.annotation.test.PersonServiceImpl.makeUpper*=ROLE_FROM_PSI");

        MapBasedMethodSecurityMetadataSource map = (MapBasedMethodSecurityMetadataSource) editor.getValue();
        assertEquals(3, map.getMethodMapSize());

        Collection<ConfigAttribute> returnedMakeUpper = map.getAttributes(makeUpper);
        List<ConfigAttribute> expectedMakeUpper = SecurityConfig.createList("ROLE_FROM_PSI");
        assertEquals(expectedMakeUpper, returnedMakeUpper);
    }

}
