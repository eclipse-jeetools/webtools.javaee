package org.eclipse.jst.j2ee.ejb.test;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;

public class EJBRelationTest extends TestCase {

	private EjbFactory EJBFACTORY = EjbPackage.eINSTANCE.getEjbFactory();
	private EJBRelation getInstance() {
		return EjbFactory.eINSTANCE.createEJBRelation();
	}
	
    public void test_EJBRelation() {

        EJBRelation tmpEJBRelation = getInstance();
        assertNotNull(tmpEJBRelation);
    }

    public void test_containsRelationshipRole() {
    	EJBRelationshipRole role1 = EJBFACTORY.createEJBRelationshipRole();
    	EJBRelationshipRole role2 = EJBFACTORY.createEJBRelationshipRole();
        EJBRelation objEJBRelation = getInstance();
        boolean retValue = false;
        retValue = objEJBRelation.containsRelationshipRole(role1);
        assertEquals(false,retValue);
        List roleList = objEJBRelation.getRelationshipRoles();
        roleList.add(role1);
        roleList.add(role2);
        retValue = objEJBRelation.containsRelationshipRole(role1);
        assertEquals(true,retValue);
    }


    public void test_getFirstRole() {
    	EJBRelationshipRole role1 = EJBFACTORY.createEJBRelationshipRole();
    	EJBRelationshipRole role2 = EJBFACTORY.createEJBRelationshipRole();
        EJBRelation objEJBRelation = getInstance();
        List roleList = objEJBRelation.getRelationshipRoles();
        roleList.add(role1);
        roleList.add(role2);
        EJBRelationshipRole retValue = null;
        retValue = objEJBRelation.getFirstRole();
        assertEquals(role1,retValue);
    }

    public void test_getOppositeRole() {
    	
    	EJBRelationshipRole role1 = EJBFACTORY.createEJBRelationshipRole();
    	EJBRelationshipRole role2 = EJBFACTORY.createEJBRelationshipRole();
        EJBRelation objEJBRelation = getInstance();
        EJBRelationshipRole oppRole = objEJBRelation.getOppositeRole(role1);
        assertNull(oppRole);
        
        List roleList = objEJBRelation.getRelationshipRoles();
        roleList.add(role1);
        roleList.add(role2);
        
        oppRole = objEJBRelation.getOppositeRole(role1);
        assertEquals(role2,oppRole);
        
        oppRole = objEJBRelation.getOppositeRole(role2);
        assertEquals(role1,oppRole);
    }

  
    public void test_getSecondRole() {

    	EJBRelationshipRole role1 = EJBFACTORY.createEJBRelationshipRole();
    	EJBRelationshipRole role2 = EJBFACTORY.createEJBRelationshipRole();
        EJBRelation objEJBRelation = getInstance();
        List roleList = objEJBRelation.getRelationshipRoles();
        roleList.add(role1);
        roleList.add(role2);
        EJBRelationshipRole retValue = null;
        retValue = objEJBRelation.getSecondRole();
        assertEquals(role2,retValue);
    }

    public void test_setFoward() {

    	EJBRelationshipRole role1 = EJBFACTORY.createEJBRelationshipRole();
    	EJBRelationshipRole role2 = EJBFACTORY.createEJBRelationshipRole();
        EJBRelation objEJBRelation = getInstance();
        List roleList = objEJBRelation.getRelationshipRoles();
        roleList.add(role1);
        roleList.add(role2);
        
        objEJBRelation.setFoward(role2);
        EJBRelationshipRole retValue = null;
        retValue = objEJBRelation.getFirstRole();
        assertEquals(role2,retValue);
    }

  /*  public void test_getDescription() {

        EJBRelation objEJBRelation = getInstance();
        String retValue = "";
        retValue = objEJBRelation.getDescription();
    }

    public void test_setDescription() {

        EJBRelation objEJBRelation = getInstance();
        String newDescription = "";
        objEJBRelation.setDescription(newDescription);
    }*/

  /* public void test_getName() {

        EJBRelation objEJBRelation = getInstance();
        String retValue = "";
        retValue = objEJBRelation.getName();
    }

    public void test_setName() {

        EJBRelation objEJBRelation = getInstance();
        String newName = "";
        objEJBRelation.setName(newName);
    }*/

   /* public void test_getRelationshipList() {

        EJBRelation objEJBRelation = getInstance();
        Relationships retValue = null;
        retValue = objEJBRelation.getRelationshipList();
    }

    public void test_setRelationshipList() {

        EJBRelation objEJBRelation = getInstance();
        Relationships newRelationshipList = null;
        objEJBRelation.setRelationshipList(newRelationshipList);
    }

 
    public void test_getDescriptions() {

        EJBRelation objEJBRelation = getInstance();
        EList retValue = null;
        retValue = objEJBRelation.getDescriptions();
    }*/
}
