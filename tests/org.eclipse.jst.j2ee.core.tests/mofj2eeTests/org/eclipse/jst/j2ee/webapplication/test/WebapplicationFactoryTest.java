package org.eclipse.jst.j2ee.webapplication.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.AuthConstraint;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.ErrorCodeErrorPage;
import org.eclipse.jst.j2ee.webapplication.ErrorPage;
import org.eclipse.jst.j2ee.webapplication.ExceptionTypeErrorPage;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.FormLoginConfig;
import org.eclipse.jst.j2ee.webapplication.HTTPMethodType;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.JSPType;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList;
import org.eclipse.jst.j2ee.webapplication.LoginConfig;
import org.eclipse.jst.j2ee.webapplication.MimeMapping;
import org.eclipse.jst.j2ee.webapplication.RoleNameType;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.SessionConfig;
import org.eclipse.jst.j2ee.webapplication.TagLibRef;
import org.eclipse.jst.j2ee.webapplication.URLPatternType;
import org.eclipse.jst.j2ee.webapplication.UserDataConstraint;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;

public class WebapplicationFactoryTest extends TestCase {

	public static Test suite() {
		return new TestSuite(WebapplicationFactoryTest.class);
	}
	
	private WebapplicationFactory getInstance() {
		return WebapplicationPackage.eINSTANCE.getWebapplicationFactory();
	}
	
    public void test_createWebApp() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        WebApp retValue = null;
        retValue = objWebapplicationFactory.createWebApp();
        assertNotNull(retValue);
    }

    public void test_createContextParam() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        ContextParam retValue = null;
        retValue = objWebapplicationFactory.createContextParam();
        assertNotNull(retValue);
    }

    public void test_createErrorPage() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        ErrorPage retValue = null;
        retValue = objWebapplicationFactory.createErrorPage();
        assertNotNull(retValue);
    }

    public void test_createTagLibRef() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        TagLibRef retValue = null;
        retValue = objWebapplicationFactory.createTagLibRef();
        assertNotNull(retValue);
    }

    public void test_createExceptionTypeErrorPage() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        ExceptionTypeErrorPage retValue = null;
        retValue = objWebapplicationFactory.createExceptionTypeErrorPage();
        assertNotNull(retValue);
    }

    public void test_createErrorCodeErrorPage() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        ErrorCodeErrorPage retValue = null;
        retValue = objWebapplicationFactory.createErrorCodeErrorPage();
        assertNotNull(retValue);
    }

    public void test_createWelcomeFileList() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        WelcomeFileList retValue = null;
        retValue = objWebapplicationFactory.createWelcomeFileList();
        assertNotNull(retValue);
    }

    public void test_createWelcomeFile() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        WelcomeFile retValue = null;
        retValue = objWebapplicationFactory.createWelcomeFile();
        assertNotNull(retValue);
    }

    public void test_createSecurityConstraint() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        SecurityConstraint retValue = null;
        retValue = objWebapplicationFactory.createSecurityConstraint();
        assertNotNull(retValue);
    }

    public void test_createWebResourceCollection() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        WebResourceCollection retValue = null;
        retValue = objWebapplicationFactory.createWebResourceCollection();
        assertNotNull(retValue);
    }

    public void test_createURLPatternType() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        URLPatternType retValue = null;
        retValue = objWebapplicationFactory.createURLPatternType();
        assertNotNull(retValue);
    }

    public void test_createAuthConstraint() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        AuthConstraint retValue = null;
        retValue = objWebapplicationFactory.createAuthConstraint();
        assertNotNull(retValue);
    }

    public void test_createUserDataConstraint() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        UserDataConstraint retValue = null;
        retValue = objWebapplicationFactory.createUserDataConstraint();
        assertNotNull(retValue);
    }

    public void test_createLoginConfig() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        LoginConfig retValue = null;
        retValue = objWebapplicationFactory.createLoginConfig();
        assertNotNull(retValue);
    }

    public void test_createFormLoginConfig() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        FormLoginConfig retValue = null;
        retValue = objWebapplicationFactory.createFormLoginConfig();
        assertNotNull(retValue);
    }

    public void test_createMimeMapping() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        MimeMapping retValue = null;
        retValue = objWebapplicationFactory.createMimeMapping();
        assertNotNull(retValue);
    }

    public void test_createSessionConfig() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        SessionConfig retValue = null;
        retValue = objWebapplicationFactory.createSessionConfig();
        assertNotNull(retValue);
    }

    public void test_createServletMapping() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        ServletMapping retValue = null;
        retValue = objWebapplicationFactory.createServletMapping();
        assertNotNull(retValue);
    }

    public void test_createServlet() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        Servlet retValue = null;
        retValue = objWebapplicationFactory.createServlet();
        assertNotNull(retValue);
    }

    public void test_createServletType() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        ServletType retValue = null;
        retValue = objWebapplicationFactory.createServletType();
        assertNotNull(retValue);
    }

    public void test_createJSPType() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        JSPType retValue = null;
        retValue = objWebapplicationFactory.createJSPType();
        assertNotNull(retValue);
    }

    public void test_createInitParam() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        InitParam retValue = null;
        retValue = objWebapplicationFactory.createInitParam();
        assertNotNull(retValue);
    }

    public void test_createFilter() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        Filter retValue = null;
        retValue = objWebapplicationFactory.createFilter();
        assertNotNull(retValue);
    }

    public void test_createFilterMapping() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        FilterMapping retValue = null;
        retValue = objWebapplicationFactory.createFilterMapping();
        assertNotNull(retValue);
    }

    public void test_createLocalEncodingMappingList() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        LocalEncodingMappingList retValue = null;
        retValue = objWebapplicationFactory
                .createLocalEncodingMappingList();
        assertNotNull(retValue);
    }

    public void test_createLocalEncodingMapping() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        LocalEncodingMapping retValue = null;
        retValue = objWebapplicationFactory.createLocalEncodingMapping();
        assertNotNull(retValue);
    }

    public void test_createHTTPMethodType() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        HTTPMethodType retValue = null;
        retValue = objWebapplicationFactory.createHTTPMethodType();
        assertNotNull(retValue);
    }

    public void test_createRoleNameType() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        RoleNameType retValue = null;
        retValue = objWebapplicationFactory.createRoleNameType();
        assertNotNull(retValue);
    }

    public void test_getWebapplicationPackage() {

        WebapplicationFactory objWebapplicationFactory = getInstance();
        WebapplicationPackage retValue = null;
        retValue = objWebapplicationFactory.getWebapplicationPackage();
        assertNotNull(retValue);
    }

}
