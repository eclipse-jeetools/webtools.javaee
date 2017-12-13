package org.eclipse.jst.j2ee.ejb.test;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;

public class CMPAttributeTest extends TestCase {

	private EjbFactory EJBFACTORY = EjbPackage.eINSTANCE.getEjbFactory();
	
	public CMPAttributeTest(String name) {
		super(name);
	}
	
	private CMPAttribute getInstance() {
		return EjbPackage.eINSTANCE.getEjbFactory().createCMPAttribute();
	
	}  
   /* public void test_getField() {

        CMPAttribute objCMPAttribute = getInstance();
        Field retValue = null;
        retValue = objCMPAttribute.getField();
    } */

    public void test_getGetterName() {
        CMPAttribute objCMPAttribute = getInstance();
        String name = "attrName" ;
        String getterName = "get" + name.substring(0, 1).toUpperCase() + (name.length() > 1 ? name.substring(1) : "");
        objCMPAttribute.setName(name);
        String retValue = objCMPAttribute.getGetterName();
        assertEquals(getterName,retValue);
    }

    public void test_getOriginatingType() {
        CMPAttribute objCMPAttribute = getInstance();
        JavaHelpers retValue = null;
        retValue = objCMPAttribute.getOriginatingType();
        assertNull(retValue);
        JavaHelpers newValue = JavaRefFactory.eINSTANCE.createClassRef("java.lang.String");
        objCMPAttribute.setOriginatingType(newValue);
        retValue = objCMPAttribute.getOriginatingType();
        assertEquals(retValue,newValue);
    }

    public void test_getSetterName() {
    	  CMPAttribute objCMPAttribute = getInstance();
          String name = "attrName" ;
          String setterName = "set" + name.substring(0, 1).toUpperCase() + (name.length() > 1 ? name.substring(1) : "");
          objCMPAttribute.setName(name);
          String retValue = objCMPAttribute.getSetterName();
          assertEquals(setterName,retValue);
    }

    public void test_getType() throws Exception {
		CMPAttribute objCMPAttribute = getInstance();
		String name = "attrName" ;
        objCMPAttribute.setName(name);
       JavaHelpers newValue = JavaRefFactory.eINSTANCE.createClassRef("java.lang.String");
        objCMPAttribute.setEType(newValue);
        JavaHelpers retValue = null;
        retValue = objCMPAttribute.getType();
        assertEquals(newValue,retValue);
        
		
    }

  


    public void test_isCMRField() {
    	CMPAttribute objCMPAttribute = getInstance();
    	boolean retValue = objCMPAttribute.isCMRField();
        assertEquals(retValue,false);
        objCMPAttribute = EJBFACTORY.createCMRField();
        retValue = objCMPAttribute.isCMRField();
        assertEquals(retValue,true);
        
    }

    public void test_isKey() {
    	EJBJar jar = EJBFACTORY.createEJBJar();
    	ContainerManagedEntity entity = EJBFACTORY.createContainerManagedEntity();
    	entity.setName("Foo");
    	jar.getEnterpriseBeans().add(entity);
        CMPAttribute objCMPAttribute = getInstance();
        objCMPAttribute.setName("fooAttr");
        entity.getPersistentAttributes().add(objCMPAttribute);
        JavaHelpers newValue = JavaRefFactory.eINSTANCE.createClassRef("java.lang.String");
        objCMPAttribute.setEType(newValue);
        ((ContainerManagedEntity)jar.getEnterpriseBeanNamed(entity.getName())).getCMPAttribute().add(objCMPAttribute);
        CMPAttribute retAttribute = (CMPAttribute)entity.getCMPAttribute().get(0);
        boolean retValue = false;
        retValue = retAttribute.isKey();
        assertEquals(retValue,false);
        List list = entity.getKeyAttributes();
        list.add(objCMPAttribute);
        retValue = retAttribute.isKey();
        assertEquals(retValue,true);
    }

    public void test_isPrimKeyField() {
    	ContainerManagedEntity entity = EJBFACTORY.createContainerManagedEntity();
        CMPAttribute objCMPAttribute = getInstance();
        List list = entity.getCMPAttribute();
        entity.getPersistentAttributes().add(objCMPAttribute);
        list.add(objCMPAttribute);
        boolean retValue = false;
        retValue = objCMPAttribute.isPrimKeyField();
        assertEquals(retValue,false);
        entity.setPrimKeyField(objCMPAttribute);
        retValue = false;
        retValue = objCMPAttribute.isPrimKeyField();
        assertEquals(retValue,true);
    }

    public void test_setOriginatingType() {

    	CMPAttribute objCMPAttribute = getInstance();
        JavaHelpers retValue = null;
        retValue = objCMPAttribute.getOriginatingType();
        assertNull(retValue);
        JavaHelpers newValue = JavaRefFactory.eINSTANCE.createClassRef("java.lang.String");
        objCMPAttribute.setOriginatingType(newValue);
        retValue = objCMPAttribute.getOriginatingType();
        assertEquals(retValue,newValue);
    }

   /* public void test_getDescription() {

        CMPAttribute objCMPAttribute = getInstance();
        String retValue = "";
        retValue = objCMPAttribute.getDescription();
    }

    public void test_setDescription() {

        CMPAttribute objCMPAttribute = getInstance();
        String newDescription = "";
        objCMPAttribute.setDescription(newDescription);
    }*/

  
    
    public void test_isDerived() {

    	 CMPAttribute objCMPAttribute = getInstance();
         boolean aBoolean = false;
         objCMPAttribute.setDerived(aBoolean);
         boolean retVal = objCMPAttribute.isDerived();
         assertEquals(retVal,aBoolean);
         aBoolean = true;
         objCMPAttribute.setDerived(aBoolean);
         retVal = objCMPAttribute.isDerived();
         assertEquals(retVal,aBoolean);
    }

    public void test_setDerived() {

        CMPAttribute objCMPAttribute = getInstance();
        boolean aBoolean = false;
        objCMPAttribute.setDerived(aBoolean);
        boolean retVal = objCMPAttribute.isDerived();
        assertEquals(retVal,aBoolean);
        aBoolean = true;
        objCMPAttribute.setDerived(aBoolean);
        retVal = objCMPAttribute.isDerived();
        assertEquals(retVal,aBoolean);
    }

    public static Test suite() {
		return new TestSuite(CMPAttributeTest.class);
	}

   
}
