package org.eclipse.jst.j2ee.ejb.test;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.MultiplicityKind;
import org.eclipse.jst.j2ee.ejb.RoleSource;

public class EJBRelationshipRoleTest extends TestCase {

	private EjbFactory EJBFACTORY = EjbPackage.eINSTANCE.getEjbFactory();
	EJBRelationshipRole role1 = null, role2 = null;
	ContainerManagedEntity entity = null;
	EJBRelation ejbRelation = null ;
	
	private EJBRelationshipRole getInstance() {
		return EJBFACTORY.createEJBRelationshipRole();
	}
	
    public void test_EJBRelationshipRole() {

        EJBRelationshipRole tmpEJBRelationshipRole = getInstance();
        assertNotNull(tmpEJBRelationshipRole);
    }

   public void test_getLogicalName() {

       /* EJBRelationshipRole objEJBRelationshipRole = getInstance();
        String retValue = "";
        retValue = objEJBRelationshipRole.getLogicalName();*/
    }

    public void test_getName() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        String name = "role1";
        objEJBRelationshipRole.setName(name);
        String retValue = "";
        retValue = objEJBRelationshipRole.getName();
        assertEquals(name,retValue);
    }

    public void test_getOpposite() {
    	init();
        EJBRelationshipRole retValue = null;
        retValue = role1.getOpposite();
        assertEquals(retValue,role2);
        retValue = role2.getOpposite();
        assertEquals(retValue,role1);
    }

   public void test_getSourceEntity() {
	    init();
	    ContainerManagedEntity retValue = role1.getSourceEntity();
	    assertEquals(retValue,entity);
    }

    public void test_isForward() {
       	init();
	    boolean retValue = role2.isForward();
	    assertEquals(retValue,false);
	    role2.setFoward();
	    retValue = role2.isForward();
	    assertEquals(retValue,true);
	    
    }

    public void test_isKey() {
    	init();
	    boolean retValue = role2.isKey();
	    assertEquals(retValue,false);
	    role2.setKey(true);
	    retValue = role2.isKey();
	    assertEquals(retValue,true);
    }

    public void test_setKey() {

    	init();
	    boolean retValue = role2.isKey();
	    assertEquals(retValue,false);
	    role2.setKey(true);
	    retValue = role2.isKey();
	    assertEquals(retValue,true);
    }
    
    private void init() {
    	role1 = EJBFACTORY.createEJBRelationshipRole();
		role2 = EJBFACTORY.createEJBRelationshipRole();
	    ejbRelation = EJBFACTORY.createEJBRelation();
	    entity = EJBFACTORY.createContainerManagedEntity();
	    RoleSource roleSource = EJBFACTORY.createRoleSource();
	    roleSource.setEntityBean(entity);
	    roleSource.setRole(role1);
	    role1.setSource(roleSource);
	    List roleList = ejbRelation.getRelationshipRoles();
	    roleList.add(role1);
	    roleList.add(role2);
    }

    public void test_isMany() {
    	init();
	    boolean retValue = false;
	    retValue = role1.isMany();
	    assertEquals(retValue,false) ;
	    role2.setMultiplicity(MultiplicityKind.MANY_LITERAL);
	    retValue = role1.isMany();
	    assertEquals(retValue,true) ;
    }

    public void test_isNavigable() {
    	init();
    	boolean retValue = role1.isNavigable();
    	assertEquals(retValue,false);
    	CMRField cmrField = EJBFACTORY.createCMRField();
    	role1.setCmrField(cmrField);
    	retValue = role1.isNavigable();
    	assertEquals(retValue,true);
    }

  
    public void test_setFoward() {
    	init();
	    boolean retValue = role2.isForward();
	    assertEquals(retValue,false);
	    role2.setFoward();
	    retValue = role2.isForward();
	    assertEquals(retValue,true);
    }

    public void test_setName() {

    	 EJBRelationshipRole objEJBRelationshipRole = getInstance();
         String name = "role1";
         objEJBRelationshipRole.setName(name);
         String retValue = "";
         retValue = objEJBRelationshipRole.getName();
         assertEquals(name,retValue);
    }

  /* public void test_getDescription() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        String retValue = "";
        retValue = objEJBRelationshipRole.getDescription();
    }

    public void test_setDescription() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        String newDescription = "";
        objEJBRelationshipRole.setDescription(newDescription);
    }*/

   /* public void test_getRoleName() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        String retValue = "";
        retValue = objEJBRelationshipRole.getRoleName();
    }

    public void test_setRoleName() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        String newRoleName = "";
        objEJBRelationshipRole.setRoleName(newRoleName);
    }*/

  /*  public void test_getMultiplicity() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        MultiplicityKind retValue = null;
        retValue = objEJBRelationshipRole.getMultiplicity();
    }

    public void test_setMultiplicity() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        MultiplicityKind newMultiplicity = null;
        objEJBRelationshipRole.setMultiplicity(newMultiplicity);
    }*/

   /* public void test_unsetMultiplicity() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        objEJBRelationshipRole.unsetMultiplicity();
    }

    public void test_isSetMultiplicity() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        boolean retValue = false;
        retValue = objEJBRelationshipRole.isSetMultiplicity();
    }*/

   /* public void test_isCascadeDelete() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        boolean retValue = false;
        retValue = objEJBRelationshipRole.isCascadeDelete();
    }

    public void test_setCascadeDelete() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        boolean newCascadeDelete = false;
        objEJBRelationshipRole.setCascadeDelete(newCascadeDelete);
    }*/

 /*   public void test_unsetCascadeDelete() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        objEJBRelationshipRole.unsetCascadeDelete();
    }

    public void test_isSetCascadeDelete() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        boolean retValue = false;
        retValue = objEJBRelationshipRole.isSetCascadeDelete();
    }*/

    /* public void test_getRelationship() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        EJBRelation retValue = null;
        retValue = objEJBRelationshipRole.getRelationship();
    }

    public void test_setRelationship() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        EJBRelation newContainer = null;
        objEJBRelationshipRole.setRelationship(newContainer);
    }*/

   /*public void test_getSource() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        RoleSource retValue = null;
        retValue = objEJBRelationshipRole.getSource();
    }

    
    public void test_setSource() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        RoleSource newSource = null;
        objEJBRelationshipRole.setSource(newSource);
    }*/

   /* public void test_getCmrField() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        CMRField retValue = null;
        retValue = objEJBRelationshipRole.getCmrField();
    }

   
    public void test_setCmrField() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        CMRField newCmrField = null;
        objEJBRelationshipRole.setCmrField(newCmrField);
    }*/

   /* public void test_getDescriptions() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        EList retValue = null;
        retValue = objEJBRelationshipRole.getDescriptions();
    }*/

   
    public void test_setRequired() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        Boolean isRequired = Boolean.FALSE;
        objEJBRelationshipRole.setRequired(isRequired);
        assertEquals(false,objEJBRelationshipRole.isRequired());
        isRequired = Boolean.TRUE;
        objEJBRelationshipRole.setRequired(isRequired);
        assertEquals(true,objEJBRelationshipRole.isRequired());
    }
    
    public void test_reconcileAttributes() {

        EJBRelationshipRole objEJBRelationshipRole = getInstance();
        objEJBRelationshipRole.reconcileAttributes();
    }


   
}
