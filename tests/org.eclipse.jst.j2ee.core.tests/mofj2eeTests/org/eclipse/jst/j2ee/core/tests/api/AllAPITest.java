/*
 * Created on Mar 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.core.tests.api;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.test.ApplicationFactoryTest;
import org.eclipse.jst.j2ee.application.test.ApplicationTest;
import org.eclipse.jst.j2ee.client.test.ApplicationClientTest;
import org.eclipse.jst.j2ee.client.test.ClientFactoryTest;
import org.eclipse.jst.j2ee.common.test.CommonFactoryTest;
import org.eclipse.jst.j2ee.common.test.EjbRefTest;
import org.eclipse.jst.j2ee.common.test.ListenerTest;
import org.eclipse.jst.j2ee.common.test.QNameTest;
import org.eclipse.jst.j2ee.ejb.test.AssemblyDescriptorTest;
import org.eclipse.jst.j2ee.ejb.test.CMPAttributeTest;
import org.eclipse.jst.j2ee.ejb.test.CMRFieldTest;
import org.eclipse.jst.j2ee.ejb.test.ContainerManagedEntityTest;
import org.eclipse.jst.j2ee.ejb.test.EJBRelationTest;
import org.eclipse.jst.j2ee.ejb.test.EJBRelationshipRoleTest;
import org.eclipse.jst.j2ee.ejb.test.EjbFactoryTest;
import org.eclipse.jst.j2ee.ejb.test.EntityTest;
import org.eclipse.jst.j2ee.ejb.test.MethodElementTest;
import org.eclipse.jst.j2ee.ejb.test.MethodPermissionTest;
import org.eclipse.jst.j2ee.ejb.test.QueryMethodTest;
import org.eclipse.jst.j2ee.ejb.test.QueryTest;
import org.eclipse.jst.j2ee.ejb.test.SessionTest;
import org.eclipse.jst.j2ee.jca.test.ConnectorTest;
import org.eclipse.jst.j2ee.jca.test.JcaFactoryTest;
import org.eclipse.jst.j2ee.jsp.test.JspFactoryTest;
import org.eclipse.jst.j2ee.webapplication.test.WebapplicationFactoryTest;
import org.eclipse.jst.j2ee.webservice.wscommon.test.WscommonFactoryTest;
import org.eclipse.jst.j2ee.webservice.wsdd.test.WsddFactoryTest;



/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AllAPITest  extends TestSuite {

	 public static Test suite(){
        return new AllAPITest();
    }
    
    public AllAPITest(){
        super("J2EE Core API Tests");
        addTest(ApplicationFactoryTest.suite());
        addTest(ApplicationTest.suite());
        addTest(ApplicationClientTest.suite());
        addTest(ClientFactoryTest.suite());
        addTest(CommonFactoryTest.suite());
        addTest(EjbRefTest.suite());
        addTest(ListenerTest.suite());
        addTest(QNameTest.suite());
        addTest(AssemblyDescriptorTest.suite());
        addTest(CMPAttributeTest.suite());
        addTest(CMRFieldTest.suite());
        addTest(ContainerManagedEntityTest.suite());
        addTest(EjbFactoryTest.suite());
        addTest(EJBRelationshipRoleTest.suite());
        addTest(EJBRelationTest.suite());
        addTest(EntityTest.suite());
        addTest(MethodElementTest.suite());
        addTest(MethodPermissionTest.suite());
        addTest(QueryMethodTest.suite());
        addTest(QueryTest.suite());
        addTest(SessionTest.suite());
        addTest(ConnectorTest.suite());
        addTest(JcaFactoryTest.suite());
        addTest(JspFactoryTest.suite());
        addTest(WebapplicationFactoryTest.suite());
        addTest(WscommonFactoryTest.suite());
        addTest(WsddFactoryTest.suite());
        
    }
	
}
