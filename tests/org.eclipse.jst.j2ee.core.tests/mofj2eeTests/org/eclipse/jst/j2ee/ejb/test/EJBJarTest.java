package org.eclipse.jst.j2ee.ejb.test;

import java.util.List;

import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;

public class EJBJarTest extends  EjbEMFTestBase {

	private static final CommonFactory COMMONFACTORY = CommonPackage.eINSTANCE.getCommonFactory();
	public EJBJarTest(String name) {
		super(name);
	}
	private EJBJar getInstance() {
		return getEjbFactory().createEJBJar();
	}
    public void test_EJBJar() {
        EJBJar tmpEJBJar = getInstance();
        assertNotNull(tmpEJBJar);
    }
    
   
    

    public void test_containsContainerManagedBeans() {

        EJBJar objEJBJar = getInstance();
        Session session = getEjbFactory().createSession();
        objEJBJar.getEnterpriseBeans().add(session);
        
        boolean retValue = false;
        retValue = objEJBJar.containsContainerManagedBeans();
        assertEquals(retValue,false);
        
        ContainerManagedEntity entity = getEjbFactory().createContainerManagedEntity();
        objEJBJar.getEnterpriseBeans().add(entity);
        
        retValue = objEJBJar.containsContainerManagedBeans();
        assertEquals(retValue,true);
    }

    public void test_containsSecurityRole() {

        EJBJar objEJBJar = getInstance();
        AssemblyDescriptor descriptor = getEjbFactory().createAssemblyDescriptor();
        objEJBJar.setAssemblyDescriptor(descriptor);
        
        String securityRoleName = "testRole";
        boolean retValue = false;
        retValue = objEJBJar.containsSecurityRole(securityRoleName);
        assertEquals(retValue, false);
        
        
        SecurityRole securityRole= CommonPackage.eINSTANCE.getCommonFactory().createSecurityRole();
		securityRole.setRoleName(securityRoleName);
		descriptor.getSecurityRoles().add(securityRole);
		retValue = objEJBJar.containsSecurityRole(securityRoleName) ;
		assertEquals(retValue,true);
    }
    
    

    public void test_getBeanManagedBeans() {
        EJBJar objEJBJar = getInstance();
        Session session = getEjbFactory().createSession();
        objEJBJar.getEnterpriseBeans().add(session);
        
        List retValue = null;
        retValue = objEJBJar.getBeanManagedBeans();
        assertEquals(retValue.size()==0,true);
        
        Entity entity = getEjbFactory().createEntity();
        objEJBJar.getEnterpriseBeans().add(entity);
        
        retValue = objEJBJar.getBeanManagedBeans();
        assertEquals(retValue.size()==1,true);
        assertEquals(retValue.get(0),entity);
    }

    public void test_getContainerManagedBeans() {
    	
        EJBJar objEJBJar = getInstance();
        Session session = getEjbFactory().createSession();
        objEJBJar.getEnterpriseBeans().add(session);
        
        List retValue = null;
        retValue = objEJBJar.getContainerManagedBeans();
        assertEquals(retValue.size()==0,true);
        
        ContainerManagedEntity entity = getEjbFactory().createContainerManagedEntity();
        objEJBJar.getEnterpriseBeans().add(entity);
        
        retValue = objEJBJar.getContainerManagedBeans();
        assertEquals(retValue.size()==1,true);
        assertEquals(retValue.get(0),entity);
    }
    
   
    

    public void test_getEJB11ContainerManagedBeans() {

    	  EJBJar objEJBJar = getInstance();
          Session session = getEjbFactory().createSession();
          objEJBJar.getEnterpriseBeans().add(session);
          
          List retValue = null;
          retValue = objEJBJar.getEJB11ContainerManagedBeans();
          assertEquals(retValue.size()==0,true);
          
          ContainerManagedEntity entity1 = getEjbFactory().createContainerManagedEntity();
          objEJBJar.getEnterpriseBeans().add(entity1);
          entity1.setVersion(ContainerManagedEntity.VERSION_1_X);
          
          ContainerManagedEntity entity2 = getEjbFactory().createContainerManagedEntity();
          objEJBJar.getEnterpriseBeans().add(entity2);
          entity2.setVersion(ContainerManagedEntity.VERSION_2_X);
          
          
          retValue = objEJBJar.getEJB11ContainerManagedBeans();
          assertEquals(retValue.size()==1,true);
          assertEquals(retValue.get(0),entity1);
    }
       
    public void test_getEJB20ContainerManagedBeans() {

  	  EJBJar objEJBJar = getInstance();
      Session session = getEjbFactory().createSession();
      objEJBJar.getEnterpriseBeans().add(session);
      
      List retValue = null;
      retValue = objEJBJar.getEJB20ContainerManagedBeans();
      assertEquals(retValue.size()==0,true);
      
      ContainerManagedEntity entity1 = getEjbFactory().createContainerManagedEntity();
      objEJBJar.getEnterpriseBeans().add(entity1);
      entity1.setVersion(ContainerManagedEntity.VERSION_1_X);
      
      ContainerManagedEntity entity2 = getEjbFactory().createContainerManagedEntity();
      objEJBJar.getEnterpriseBeans().add(entity2);
      entity2.setVersion(ContainerManagedEntity.VERSION_2_X);
      
      
      retValue = objEJBJar.getEJB20ContainerManagedBeans();
      assertEquals(retValue.size()==1,true);
      assertEquals(retValue.get(0),entity2);
    }

    
    public void test_getMessageDrivenBeans() {
        EJBJar objEJBJar = getInstance();
        Session session = getEjbFactory().createSession();
        objEJBJar.getEnterpriseBeans().add(session);
        
        List retValue = null;
        retValue = objEJBJar.getMessageDrivenBeans();
        assertEquals(retValue.size()==0,true);
        
        MessageDriven messageDriven = getEjbFactory().createMessageDriven();
        objEJBJar.getEnterpriseBeans().add(messageDriven);
        
        retValue = objEJBJar.getMessageDrivenBeans();
        assertEquals(retValue.size()==1,true);
        assertEquals(retValue.get(0),messageDriven);
    }

    public void test_getEjbRelations() {

        EJBJar objEJBJar = getInstance();
        Relationships relationships = getEjbFactory().createRelationships();
        objEJBJar.setRelationshipList(relationships);
        relationships.setEjbJar(objEJBJar);
        EList ejbRelations = relationships.getEjbRelations();
        List retValue = null;
        retValue = objEJBJar.getEjbRelations();
        
        assertEquals(ejbRelations,retValue);
    }
    

   

    public void test_getEnterpiseBeanFromRef() {
    	
    	EJBJar objEJBJar = getInstance();
        AssemblyDescriptor descriptor = getEjbFactory().createAssemblyDescriptor();
        objEJBJar.setAssemblyDescriptor(descriptor);
         
    	EjbRef ejbRef = COMMONFACTORY.createEjbRef();
    	Session session1 = getEjbFactory().createSession();
    	String name1 = "Name1";
    	session1.setName(name1);
    	
    	Session session2 = getEjbFactory().createSession();
    	String name2 = "Name2";
    	session2.setName(name2);
    	
    	ejbRef.setLink(name2);
    	ejbRef.setName("EjbRef_" + name2);
    	
    	objEJBJar.getEnterpriseBeans().add(session1);
    	objEJBJar.getEnterpriseBeans().add(session2);
    	
        EnterpriseBean retValue = objEJBJar.getEnterpiseBeanFromRef(ejbRef);
        assertEquals(session2,retValue);
    }

    public void test_getEnterpriseBeanNamed() {

    	EJBJar objEJBJar = getInstance();
        AssemblyDescriptor descriptor = getEjbFactory().createAssemblyDescriptor();
        objEJBJar.setAssemblyDescriptor(descriptor);
         
    	Session session1 = getEjbFactory().createSession();
    	String name1 = "Name1";
    	session1.setName(name1);
    	
    	Session session2 = getEjbFactory().createSession();
    	String name2 = "Name2";
    	session2.setName(name2);
    	
        	
    	objEJBJar.getEnterpriseBeans().add(session1);
    	objEJBJar.getEnterpriseBeans().add(session2);
    	
        EnterpriseBean retValue = objEJBJar.getEnterpriseBeanNamed(name2);
        assertEquals(session2,retValue);
    }

    public void test_getEnterpriseBeansWithReference() {

    	EJBJar objEJBJar = getInstance();
        AssemblyDescriptor descriptor = getEjbFactory().createAssemblyDescriptor();
        objEJBJar.setAssemblyDescriptor(descriptor);
         
    	Session session1 = getEjbFactory().createSession();
    	String name1 = "Name1";
    	session1.setName(name1);
    	
    	Session session2 = getEjbFactory().createSession();
    	String name2 = "Name2";
    	session2.setName(name2);
    	
        	
    	objEJBJar.getEnterpriseBeans().add(session1);
    	objEJBJar.getEnterpriseBeans().add(session2);
    	
    	JavaClass javaClass1 = JavaRefFactory.eINSTANCE.createClassRef("java.lang.String") ;
    	JavaClass javaClass2 = JavaRefFactory.eINSTANCE.createClassRef("java.util.List") ;
    	
    	session1.setEjbClass(javaClass1);
    	session2.setEjbClass(javaClass2);
    	
         java.util.List retValue = null;
         retValue = objEJBJar.getEnterpriseBeansWithReference(javaClass1);
         assertEquals(retValue.size()==1, true);
         assertEquals(retValue.contains(session1), true);
         
         session2.setHomeInterface(javaClass1);
         retValue = objEJBJar.getEnterpriseBeansWithReference(javaClass1);
         assertEquals(retValue.size()==2, true);
         assertEquals(retValue.contains(session1), true);
         assertEquals(retValue.contains(session2), true);
         
    }
    
  

    public void test_getEnterpriseBeanWithReference() {
    	
    	EJBJar objEJBJar = getInstance();
        AssemblyDescriptor descriptor = getEjbFactory().createAssemblyDescriptor();
        objEJBJar.setAssemblyDescriptor(descriptor);
         
    	Session session1 = getEjbFactory().createSession();
    	String name1 = "Name1";
    	session1.setName(name1);
    	
    	Session session2 = getEjbFactory().createSession();
    	String name2 = "Name2";
    	session2.setName(name2);
    	
        	
    	objEJBJar.getEnterpriseBeans().add(session1);
    	objEJBJar.getEnterpriseBeans().add(session2);
    	
    	JavaClass javaClass1 = JavaRefFactory.eINSTANCE.createClassRef("java.lang.String") ;
    	
    	session1.setEjbClass(javaClass1);
    	session2.setEjbClass(javaClass1);
    	
    	EnterpriseBean retValue = null;
        retValue = objEJBJar.getEnterpriseBeanWithReference(javaClass1);
        assertEquals(retValue, session1);
        
    }

    public void test_getSessionBeans() {     
        EJBJar objEJBJar = getInstance();
        MessageDriven messageDriven = getEjbFactory().createMessageDriven();
        objEJBJar.getEnterpriseBeans().add(messageDriven);
        
        
        List retValue = null;
        retValue = objEJBJar.getSessionBeans();
        assertEquals(retValue.size()==0,true);
        
        Session session = getEjbFactory().createSession();
        objEJBJar.getEnterpriseBeans().add(session);
        
        
        retValue = objEJBJar.getSessionBeans();
        assertEquals(retValue.size()==1,true);
        assertEquals(retValue.contains(session), true);
        
    }


   /* public void test_renameSecurityRole() {
        EJBJar objEJBJar = getInstance();
        java.lang.String existingRoleName = "";
        java.lang.String newRoleName = "";
        objEJBJar.renameSecurityRole(existingRoleName, newRoleName);
    } */

  /* public void test_getEjbClientJar() {

        EJBJar objEJBJar = getInstance();
        String retValue = "";
        retValue = objEJBJar.getEjbClientJar();
    }

    public void test_setEjbClientJar() {

        EJBJar objEJBJar = getInstance();
        String newEjbClientJar = "";
        objEJBJar.setEjbClientJar(newEjbClientJar);
    }*/

  /*  public void test_getVersion() {

        EJBJar objEJBJar = getInstance();
        String retValue = "";
        retValue = objEJBJar.getVersion();
    }*/
    
   
    

    public void test_getVersionID() throws Exception {

    	init();
    	EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
        DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		setVersion(VERSION_1_4);
		setModuleType(EJB);
		populateRoot(DD.getRootObject());
        int retValue = 0;
        retValue = DD.getEJBJar().getVersionID();
        assertEquals(retValue,J2EEVersionConstants.EJB_2_1_ID);
        
        DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
		setVersion(VERSION_1_3);
		retValue = DD.getEJBJar().getVersionID();
	    assertEquals(retValue,J2EEVersionConstants.EJB_2_0_ID);
	    
	    DD.setVersionID(J2EEVersionConstants.J2EE_1_2_ID);
		setVersion(VERSION_1_2);
		retValue = DD.getEJBJar().getVersionID();
	    assertEquals(retValue,J2EEVersionConstants.EJB_1_1_ID);	    
    }

    public void test_getJ2EEVersionID() throws Exception {

    	init();
    	EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
        DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		setVersion(VERSION_1_4);
		setModuleType(EJB);
		populateRoot(DD.getRootObject());
        int retValue = 0;
        retValue = DD.getEJBJar().getJ2EEVersionID();
        assertEquals(retValue,J2EEVersionConstants.J2EE_1_4_ID);
        
        DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
		setVersion(VERSION_1_3);
		retValue = DD.getEJBJar().getJ2EEVersionID();
	    assertEquals(retValue,J2EEVersionConstants.J2EE_1_3_ID);
	    
	    DD.setVersionID(J2EEVersionConstants.J2EE_1_2_ID);
		setVersion(VERSION_1_2);
		retValue = DD.getEJBJar().getJ2EEVersionID();
	    assertEquals(retValue,J2EEVersionConstants.J2EE_1_2_ID);
    }

  /*  public void test_setVersion() {

        EJBJar objEJBJar = getInstance();
        String newVersion = "";
        objEJBJar.setVersion(newVersion);
    }*/

  /*  public void test_getAssemblyDescriptor() {

        EJBJar objEJBJar = getInstance();
        AssemblyDescriptor retValue = null;
        retValue = objEJBJar.getAssemblyDescriptor();
    }

 

    public void test_setAssemblyDescriptor() {

        EJBJar objEJBJar = getInstance();
        AssemblyDescriptor newAssemblyDescriptor = null;
        objEJBJar.setAssemblyDescriptor(newAssemblyDescriptor);
    }*/

   /* public void test_getEnterpriseBeans() {

        EJBJar objEJBJar = getInstance();
        EList retValue = null;
        retValue = objEJBJar.getEnterpriseBeans();
    }*/

   /* public void test_getRelationshipList() {

        EJBJar objEJBJar = getInstance();
        Relationships retValue = null;
        retValue = objEJBJar.getRelationshipList();
    }

 

    public void test_setRelationshipList() {

        EJBJar objEJBJar = getInstance();
        Relationships newRelationshipList = null;
        objEJBJar.setRelationshipList(newRelationshipList);
    }
*/
   

    public void test_getEJBRelation() {
    	 EJBJar objEJBJar = getInstance();
         Relationships relationships = getEjbFactory().createRelationships();
         objEJBJar.setRelationshipList(relationships);
         relationships.setEjbJar(objEJBJar);
       
         EJBRelation relation = getEjbFactory().createEJBRelation();
         String aRelationName = "Relation1";
         relation.setName(aRelationName);
         
         relationships.getEjbRelations().add(relation);
         EJBRelation retValue = null;
         retValue = objEJBJar.getEJBRelation(aRelationName);
         assertEquals(retValue, relation);
    }
    
   

    public void test_getEJBRelationsForSource() {
    	
    	EJBJar objEJBJar = getInstance();
        Relationships relationships = getEjbFactory().createRelationships();
        objEJBJar.setRelationshipList(relationships);
        relationships.setEjbJar(objEJBJar);
      
        EJBRelation relation = getEjbFactory().createEJBRelation();
        String aRelationName = "Relation1";
        relation.setName(aRelationName);
        
        EJBRelationshipRole role1 = getEjbFactory().createEJBRelationshipRole();
    	EJBRelationshipRole role2 = getEjbFactory().createEJBRelationshipRole();
       
    	List roleList = relation.getRelationshipRoles();
        roleList.add(role1);
        roleList.add(role2);
        
        ContainerManagedEntity entity = getEjbFactory().createContainerManagedEntity();
        ContainerManagedEntity targetEntity = getEjbFactory().createContainerManagedEntity();
        
	    RoleSource roleSource = getEjbFactory().createRoleSource();
	    roleSource.setEntityBean(entity);
	    roleSource.setRole(role1);
	    role1.setSource(roleSource);
	    
	    RoleSource targetRoleSource = getEjbFactory().createRoleSource();
	    targetRoleSource.setEntityBean(targetEntity);
	    targetRoleSource.setRole(role2);
	    role2.setSource(targetRoleSource);
        
        List retValue = null;
        retValue = objEJBJar.getEJBRelationsForSource(entity);
        
        assertNotNull(retValue);
        assertEquals(retValue.size(),1);
        assertEquals(retValue.contains(relation),true);
        
    }

    public void test_getEJBRelationshipRolesForType() {

    	EJBJar objEJBJar = getInstance();
        Relationships relationships = getEjbFactory().createRelationships();
        objEJBJar.setRelationshipList(relationships);
        relationships.setEjbJar(objEJBJar);
      
        EJBRelation relation = getEjbFactory().createEJBRelation();
        String aRelationName = "Relation1";
        relation.setName(aRelationName);
        
        EJBRelationshipRole role1 = getEjbFactory().createEJBRelationshipRole();
    	EJBRelationshipRole role2 = getEjbFactory().createEJBRelationshipRole();
       
    	List roleList = relation.getRelationshipRoles();
        roleList.add(role1);
        roleList.add(role2);
        
        ContainerManagedEntity entity = getEjbFactory().createContainerManagedEntity();
        ContainerManagedEntity targetEntity = getEjbFactory().createContainerManagedEntity();
        
	    RoleSource roleSource = getEjbFactory().createRoleSource();
	    roleSource.setEntityBean(entity);
	    roleSource.setRole(role1);
	    role1.setSource(roleSource);
	    
	    RoleSource targetRoleSource = getEjbFactory().createRoleSource();
	    targetRoleSource.setEntityBean(targetEntity);
	    targetRoleSource.setRole(role2);
	    role2.setSource(targetRoleSource);
        
        List retValue = null;
        retValue = objEJBJar.getEJBRelationshipRolesForType(targetEntity);
        
        assertNotNull(retValue);
        assertEquals(retValue.size(),1);
        assertEquals(retValue.contains(relation),true);
    }
    
    public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new EJBJarTest("test_EJBJar"));
		suite.addTest(new EJBJarTest("test_containsContainerManagedBeans"));
		suite.addTest(new EJBJarTest("test_containsSecurityRole"));
		suite.addTest(new EJBJarTest("test_getBeanManagedBeans"));
		suite.addTest(new EJBJarTest("test_getContainerManagedBeans"));
		
		suite.addTest(new EJBJarTest("test_getEJB11ContainerManagedBeans"));
		suite.addTest(new EJBJarTest("test_getEJB20ContainerManagedBeans"));
		suite.addTest(new EJBJarTest("test_getMessageDrivenBeans"));
		suite.addTest(new EJBJarTest("test_getEjbRelations"));
		suite.addTest(new EJBJarTest("test_getEnterpiseBeanFromRef"));
		
		suite.addTest(new EJBJarTest("test_getEnterpriseBeanNamed"));
		suite.addTest(new EJBJarTest("test_getEnterpriseBeansWithReference"));
		suite.addTest(new EJBJarTest("test_getEnterpriseBeanWithReference"));
		suite.addTest(new EJBJarTest("test_getSessionBeans"));
		suite.addTest(new EJBJarTest("test_getVersionID"));
		
		suite.addTest(new EJBJarTest("test_getJ2EEVersionID"));
		suite.addTest(new EJBJarTest("test_getEJBRelation"));
		suite.addTest(new EJBJarTest("test_getEJBRelationsForSource"));
		suite.addTest(new EJBJarTest("test_getEJBRelationshipRolesForType"));
		
		return suite;
	}

}
