package org.eclipse.jst.j2ee.webservice.wsdd.test;

import junit.framework.TestCase;

import org.eclipse.jst.j2ee.webservice.wsdd.BeanLink;
import org.eclipse.jst.j2ee.webservice.wsdd.EJBLink;
import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.ServiceImplBean;
import org.eclipse.jst.j2ee.webservice.wsdd.ServletLink;
import org.eclipse.jst.j2ee.webservice.wsdd.WSDLPort;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;

public class WsddFactoryTest extends TestCase {

	private WsddFactory getInstance() {
		return WsddPackage.eINSTANCE.getWsddFactory();
	}
    public void test_createWebServices() {

        WsddFactory objWsddFactory = getInstance();
        WebServices retValue = null;
        retValue = objWsddFactory.createWebServices();
        assertNotNull(retValue);
    }

    public void test_createWebServiceDescription() {

        WsddFactory objWsddFactory = getInstance();
        WebServiceDescription retValue = null;
        retValue = objWsddFactory.createWebServiceDescription();
        assertNotNull(retValue);
    }

    public void test_createPortComponent() {

        WsddFactory objWsddFactory = getInstance();
        PortComponent retValue = null;
        retValue = objWsddFactory.createPortComponent();
        assertNotNull(retValue);
    }

    public void test_createWSDLPort() {

        WsddFactory objWsddFactory = getInstance();
        WSDLPort retValue = null;
        retValue = objWsddFactory.createWSDLPort();
        assertNotNull(retValue);
    }

    public void test_createServiceImplBean() {

        WsddFactory objWsddFactory = getInstance();
        ServiceImplBean retValue = null;
        retValue = objWsddFactory.createServiceImplBean();
        assertNotNull(retValue);
    }

    public void test_createServletLink() {

        WsddFactory objWsddFactory = getInstance();
        ServletLink retValue = null;
        retValue = objWsddFactory.createServletLink();
        assertNotNull(retValue);
    }

    public void test_createEJBLink() {

        WsddFactory objWsddFactory = getInstance();
        EJBLink retValue = null;
        retValue = objWsddFactory.createEJBLink();
        assertNotNull(retValue);
    }

    public void test_createHandler() {

        WsddFactory objWsddFactory = getInstance();
        Handler retValue = null;
        retValue = objWsddFactory.createHandler();
        assertNotNull(retValue);
    }

    public void test_createBeanLink() {

        WsddFactory objWsddFactory = getInstance();
        BeanLink retValue = null;
        retValue = objWsddFactory.createBeanLink();
        assertNotNull(retValue);
    }

    public void test_getWsddPackage() {

        WsddFactory objWsddFactory = getInstance();
        WsddPackage retValue = null;
        retValue = objWsddFactory.getWsddPackage();
        // TODO getWsddPackage method always return null
        assertNotNull(retValue);
    }

}
