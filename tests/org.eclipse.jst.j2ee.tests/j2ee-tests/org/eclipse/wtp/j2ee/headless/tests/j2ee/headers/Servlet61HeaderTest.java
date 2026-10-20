/*******************************************************************************
 * Copyright (c) 2026, Oracle and/or its affiliates.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.wtp.j2ee.headless.tests.j2ee.headers;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import junit.framework.TestCase;

/** Regression coverage for Servlet 6.1 descriptor detection and WAR version conversion. */
public class Servlet61HeaderTest extends TestCase {
    public void testWebDescriptors() {
        assertDescriptor("web-app", "", true);
        assertDescriptor("web-app", "", false);
        assertDescriptor("web-app", "j:", true);
        assertDescriptor("web-app", "j:", false);
    }

    public void testFragmentDescriptors() {
        assertDescriptor("web-fragment", "", true);
        assertDescriptor("web-fragment", "", false);
        assertDescriptor("web-fragment", "j:", true);
        assertDescriptor("web-fragment", "j:", false);
    }

    private void assertDescriptor(String root, String prefix, boolean schema) {
        String ns = "https://jakarta.ee/xml/ns/jakartaee";
        String xml = "<" + prefix + root + " xmlns" + (prefix.isEmpty() ? "" : ":j")
                + "=\"" + ns + "\" version=\"6.1\""
                + (schema ? " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\""
                        + ns + " " + ns + "/" + root + "_6_1.xsd\"" : "") + "/>";
        JavaEEQuickPeek peek = new JavaEEQuickPeek(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
        assertEquals(xml, 61, peek.getVersion());
        assertEquals(xml, 110, peek.getJavaEEVersion());
        assertEquals(xml, root.equals("web-app") ? JavaEEQuickPeek.WEB_TYPE : JavaEEQuickPeek.WEBFRAGMENT_TYPE, peek.getType());
    }

    public void testVersionRoundTrips() {
        assertEquals("6.1", J2EEVersionUtil.getServletTextVersion(61));
        assertEquals("6.1", J2EEVersionUtil.convertVersionIntToString(61));
        assertEquals(61, J2EEVersionUtil.convertVersionStringToInt("6.1"));
        assertEquals(110, J2EEVersionUtil.convertWebVersionStringToJ2EEVersionID("6.1"));
        assertEquals(110, J2EEVersionUtil.convertWebFragmentVersionStringToJ2EEVersionID("6.1"));
        assertEquals(61, J2EEVersionUtil.convertJ2EEVersionIDToWebVersionID(110));
        assertEquals("11.0", J2EEVersionUtil.getJ2EETextVersion(110));
        assertEquals(110, J2EEVersionUtil.convertVersionStringToInt("11.0"));
        for (String version : new String[] {"3.1", "4.0", "5.0", "6.0"}) {
            int number = Integer.parseInt(version.replace(".", ""));
            assertEquals(version, J2EEVersionUtil.getServletTextVersion(number));
            assertEquals(version, J2EEVersionUtil.convertVersionIntToString(number));
        }
    }
}
