package org.eclipse.jst.j2ee.webservice.wscommon.test;

import junit.framework.TestCase;

import org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType;
import org.eclipse.jst.j2ee.webservice.wscommon.DisplayNameType;
import org.eclipse.jst.j2ee.webservice.wscommon.InitParam;
import org.eclipse.jst.j2ee.webservice.wscommon.PortName;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPHeader;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPRole;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;

public class WscommonFactoryTest extends TestCase {

	private WscommonFactory getInstance() {
		return WscommonPackage.eINSTANCE.getWscommonFactory();
	}
    public void test_createInitParam() {

        WscommonFactory objWscommonFactory = getInstance();
        InitParam retValue = null;
        retValue = objWscommonFactory.createInitParam();
        assertNotNull(retValue);
    }

    public void test_createSOAPHeader() {

        WscommonFactory objWscommonFactory = getInstance();
        SOAPHeader retValue = null;
        retValue = objWscommonFactory.createSOAPHeader();
        assertNotNull(retValue);
    }

    public void test_createSOAPRole() {

        WscommonFactory objWscommonFactory = getInstance();
        SOAPRole retValue = null;
        retValue = objWscommonFactory.createSOAPRole();
        assertNotNull(retValue);
    }

    public void test_createPortName() {

        WscommonFactory objWscommonFactory = getInstance();
        PortName retValue = null;
        retValue = objWscommonFactory.createPortName();
        assertNotNull(retValue);
    }

    public void test_createDescriptionType() {

        WscommonFactory objWscommonFactory = getInstance();
        DescriptionType retValue = null;
        retValue = objWscommonFactory.createDescriptionType();
        assertNotNull(retValue);
    }

    public void test_createDisplayNameType() {

        WscommonFactory objWscommonFactory = getInstance();
        DisplayNameType retValue = null;
        retValue = objWscommonFactory.createDisplayNameType();
        assertNotNull(retValue);
    }

    public void test_getWscommonPackage() {

        WscommonFactory objWscommonFactory = getInstance();
        WscommonPackage retValue = null;
        retValue = objWscommonFactory.getWscommonPackage();
        
        assertNotNull(retValue);
    }

}
