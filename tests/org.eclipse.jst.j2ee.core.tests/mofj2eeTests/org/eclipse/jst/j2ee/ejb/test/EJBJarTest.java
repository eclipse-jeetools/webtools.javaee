package org.eclipse.jst.j2ee.ejb.test;

import java.util.List;

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
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.jst.j2ee.ejb.Session;

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
        JavaClass aJavaClass = null;
        EnterpriseBean retValue = null;
        retValue = objEJBJar.getEnterpriseBeanWithReference(aJavaClass);
    }

    public void test_getSessionBeans() {

        EJBJar objEJBJar = getInstance();
        List retValue = null;
        retValue = objEJBJar.getSessionBeans();
    }


    public void test_renameSecurityRole() {

        EJBJar objEJBJar = getInstance();
        java.lang.String existingRoleName = "";
        java.lang.String newRoleName = "";
        objEJBJar.renameSecurityRole(existingRoleName, newRoleName);
    }

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

    public void test_getVersionID() throws IllegalStateException {

        EJBJar objEJBJar = getInstance();
        int retValue = 0;
        retValue = objEJBJar.getVersionID();
    }

    public void test_getJ2EEVersionID() throws IllegalStateException {

        EJBJar objEJBJar = getInstance();
        int retValue = 0;
        retValue = objEJBJar.getJ2EEVersionID();
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
        String aRelationName = "";
        EJBRelation retValue = null;
        retValue = objEJBJar.getEJBRelation(aRelationName);
    }

    public void test_getEJBRelationsForSource() {

        EJBJar objEJBJar = getInstance();
        ContainerManagedEntity cmp = null;
        List retValue = null;
        retValue = objEJBJar.getEJBRelationsForSource(cmp);
    }

    public void test_getEJBRelationshipRolesForType() {

        EJBJar objEJBJar = getInstance();
        ContainerManagedEntity cmp = null;
        List retValue = null;
        retValue = objEJBJar.getEJBRelationshipRolesForType(cmp);
    }

}
