package org.eclipse.jst.j2ee.ejb.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;

public class CMRFieldTest extends TestCase {
	protected EjbFactory EJBFACTORY = EjbPackage.eINSTANCE.getEjbFactory();

	private CMRField getInstance() {
		return EJBFACTORY.createCMRField();
	}
	
   public void test_getCollectionTypeName() {
	   	CMRField objCMRField = getInstance();
	    String typeName = "testName";
	    objCMRField.setCollectionTypeName(typeName);
	    String retValue = "";
	    retValue = objCMRField.getCollectionTypeName();
	    assertEquals(typeName,retValue);
    }

 
    public void test_setCollectionTypeName() {

        CMRField objCMRField = getInstance();
        String typeName = "testName";
        objCMRField.setCollectionTypeName(typeName);
        String retValue = "";
        retValue = objCMRField.getCollectionTypeName();
        assertEquals(typeName,retValue);
    }

    public void test_getRole() {
    	 CMRField objCMRField = getInstance();
         EJBRelationshipRole newRole = EJBFACTORY.createEJBRelationshipRole();
         newRole.setName("Role1");
         objCMRField.setRole(newRole);
         EJBRelationshipRole retValue = objCMRField.getRole();
         assertEquals(newRole,retValue);
    }

    public void test_setRole() {
        CMRField objCMRField = getInstance();
        EJBRelationshipRole newRole = EJBFACTORY.createEJBRelationshipRole();
        newRole.setName("Role1");
        objCMRField.setRole(newRole);
        EJBRelationshipRole retValue = objCMRField.getRole();
        assertEquals(newRole,retValue);
        
    }

    public void test_getCollectionType() {

    	 CMRField objCMRField = getInstance();
         JavaClass newCollectionType = JavaRefFactory.eINSTANCE.createJavaClass();
         objCMRField.setCollectionType(newCollectionType);
         JavaClass retVal = objCMRField.getCollectionType();
         assertEquals(newCollectionType,retVal);
    }

   
    public void test_setCollectionType() {

        CMRField objCMRField = getInstance();
        JavaClass newCollectionType = JavaRefFactory.eINSTANCE.createJavaClass();
        objCMRField.setCollectionType(newCollectionType);
        JavaClass retVal = objCMRField.getCollectionType();
        assertEquals(newCollectionType,retVal);
    }
    
    public static Test suite() {
		return new TestSuite(CMRFieldTest.class);
	}


   
    

}
