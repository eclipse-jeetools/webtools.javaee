package org.eclipse.jst.j2ee.ejb.test;

import junit.framework.TestCase;

import org.eclipse.jem.java.Method;
import org.eclipse.jem.java.impl.MethodImpl;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.ActivationConfigProperty;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBMethodCategory;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.ExcludeList;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.MessageDrivenDestination;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.ejb.Query;
import org.eclipse.jst.j2ee.ejb.QueryMethod;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.ejb.Session;

public class EjbFactoryTest extends TestCase {

	private EjbFactory getInstance() {
		return EjbPackage.eINSTANCE.getEjbFactory();
	}
	
    public void test_EjbFactory() {

        EjbFactory tmpEjbFactory = getInstance();
    }

    public void test_createMethodElement() {

        EjbFactory objEjbFactory = getInstance();
        String aSignature = "";
        MethodElement retValue = null;
        retValue = objEjbFactory.createMethodElement(aSignature);
        assertNotNull(retValue);
    }

    public void test_createMethodElementFrom() {

        EjbFactory objEjbFactory = getInstance();
        Method method = null;
        MethodElement retValue = null;
        retValue = objEjbFactory.createMethodElementFrom(method);
        assertNotNull(retValue);
    }

    public void test_createMethodElementFrom_2() {

        EjbFactory objEjbFactory = getInstance();
        java.lang.reflect.Method method = null;
        MethodElement retValue = null;
        retValue = objEjbFactory.createMethodElementFrom(method);
        assertNotNull(retValue);
    }

  
    public void test_createMethodPermission() {

        EjbFactory objEjbFactory = getInstance();
        MethodPermission retValue = null;
        retValue = objEjbFactory.createMethodPermission();
        assertNotNull(retValue);
    }

    public void test_createAssemblyDescriptor() {

        EjbFactory objEjbFactory = getInstance();
        AssemblyDescriptor retValue = null;
        retValue = objEjbFactory.createAssemblyDescriptor();
        assertNotNull(retValue);
    }

    public void test_createMethodTransaction() {

        EjbFactory objEjbFactory = getInstance();
        MethodTransaction retValue = null;
        retValue = objEjbFactory.createMethodTransaction();
        assertNotNull(retValue);
    }

    public void test_createEntity() {

        EjbFactory objEjbFactory = getInstance();
        Entity retValue = null;
        retValue = objEjbFactory.createEntity();
        assertNotNull(retValue);
    }

    

    public void test_createContainerManagedEntity() {

        EjbFactory objEjbFactory = getInstance();
        ContainerManagedEntity retValue = null;
        retValue = objEjbFactory.createContainerManagedEntity();
        assertNotNull(retValue);
    }

    public void test_createCMPAttribute() {

        EjbFactory objEjbFactory = getInstance();
        CMPAttribute retValue = null;
        retValue = objEjbFactory.createCMPAttribute();
        assertNotNull(retValue);
    }

    public void test_createCMRField() {

        EjbFactory objEjbFactory = getInstance();
        CMRField retValue = null;
        retValue = objEjbFactory.createCMRField();
        assertNotNull(retValue);
    }

    public void test_createEJBRelationshipRole() {

        EjbFactory objEjbFactory = getInstance();
        EJBRelationshipRole retValue = null;
        retValue = objEjbFactory.createEJBRelationshipRole();
        assertNotNull(retValue);
    }

   
    public void test_createEJBRelation() {

        EjbFactory objEjbFactory = getInstance();
        EJBRelation retValue = null;
        retValue = objEjbFactory.createEJBRelation();
        assertNotNull(retValue);
    }


    public void test_createRelationships() {

        EjbFactory objEjbFactory = getInstance();
        Relationships retValue = null;
        retValue = objEjbFactory.createRelationships();
        assertNotNull(retValue);
    }

    public void test_createEJBJar() {

        EjbFactory objEjbFactory = getInstance();
        EJBJar retValue = null;
        retValue = objEjbFactory.createEJBJar();
        assertNotNull(retValue);
    }

    public void test_createRoleSource() {

        EjbFactory objEjbFactory = getInstance();
        RoleSource retValue = null;
        retValue = objEjbFactory.createRoleSource();
        assertNotNull(retValue);
    }

    public void test_createQuery() {

        EjbFactory objEjbFactory = getInstance();
        Query retValue = null;
        retValue = objEjbFactory.createQuery();
        assertNotNull(retValue);
    }

    public void test_createQueryMethod() {

        EjbFactory objEjbFactory = getInstance();
        QueryMethod retValue = null;
        retValue = objEjbFactory.createQueryMethod();
        assertNotNull(retValue);
    }

    public void test_createActivationConfigProperty() {

        EjbFactory objEjbFactory = getInstance();
        ActivationConfigProperty retValue = null;
        retValue = objEjbFactory.createActivationConfigProperty();
        assertNotNull(retValue);
    }

    public void test_createActivationConfig() {

        EjbFactory objEjbFactory = getInstance();
        ActivationConfig retValue = null;
        retValue = objEjbFactory.createActivationConfig();
        assertNotNull(retValue);
    }

    public void test_createMethodElement_2() {

        EjbFactory objEjbFactory = getInstance();
        MethodElement retValue = null;
        retValue = objEjbFactory.createMethodElement();
        assertNotNull(retValue);
    }

    public void test_createExcludeList() {

        EjbFactory objEjbFactory = getInstance();
        ExcludeList retValue = null;
        retValue = objEjbFactory.createExcludeList();
        assertNotNull(retValue);
    }

    public void test_createSession() {

        EjbFactory objEjbFactory = getInstance();
        Session retValue = null;
        retValue = objEjbFactory.createSession();
        assertNotNull(retValue);
    }

    public void test_createMessageDriven() {

        EjbFactory objEjbFactory = getInstance();
        MessageDriven retValue = null;
        retValue = objEjbFactory.createMessageDriven();
        assertNotNull(retValue);
    }

    public void test_createMessageDrivenDestination() {

        EjbFactory objEjbFactory = getInstance();
        MessageDrivenDestination retValue = null;
        retValue = objEjbFactory.createMessageDrivenDestination();
        assertNotNull(retValue);
    }

    public void test_createEJBMethodCategory() {

        EjbFactory objEjbFactory = getInstance();
        EJBMethodCategory retValue = null;
        retValue = objEjbFactory.createEJBMethodCategory();
        assertNotNull(retValue);
    }

    public void test_getEjbPackage() {

        EjbFactory objEjbFactory = getInstance();
        EjbPackage retValue = null;
        retValue = objEjbFactory.getEjbPackage();
        assertNotNull(retValue);
    }
}
