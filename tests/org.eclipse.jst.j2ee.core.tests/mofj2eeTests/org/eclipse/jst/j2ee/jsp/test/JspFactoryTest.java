package org.eclipse.jst.j2ee.jsp.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.internal.jsp.JspPackage;
import org.eclipse.jst.j2ee.jca.test.JcaFactoryTest;
import org.eclipse.jst.j2ee.jsp.JSPConfig;
import org.eclipse.jst.j2ee.jsp.JSPPropertyGroup;
import org.eclipse.jst.j2ee.jsp.JspFactory;
import org.eclipse.jst.j2ee.jsp.TagLibRefType;

public class JspFactoryTest extends TestCase {

	public static Test suite() {
		return new TestSuite(JcaFactoryTest.class);
	}
	
	private JspFactory getInstance() {
		return JspPackage.eINSTANCE.getJspFactory();
	}
    public void test_createJSPConfig() {

        JspFactory objJspFactory = getInstance();
        JSPConfig retValue = null;
        retValue = objJspFactory.createJSPConfig();
        assertNotNull(retValue);
    }

    public void test_createJSPPropertyGroup() {

        JspFactory objJspFactory = getInstance();
        JSPPropertyGroup retValue = null;
        retValue = objJspFactory.createJSPPropertyGroup();
        assertNotNull(retValue);
    }

    public void test_createTagLibRefType() {

        JspFactory objJspFactory = getInstance();
        TagLibRefType retValue = null;
        retValue = objJspFactory.createTagLibRefType();
        assertNotNull(retValue);
    }

    public void test_getJspPackage() {

        JspFactory objJspFactory = getInstance();
        JspPackage retValue = null;
        retValue = objJspFactory.getJspPackage();
        // TODO getWsddPackage method always return null
        assertNotNull(retValue);
    }

}
