package org.eclipse.jst.j2ee.ejb.test;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.internal.impl.JavaRefFactoryImpl;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;

public class ContainerManagedEntityTest extends TestCase {
	
	private static final EjbFactory EJBFACTORY = EjbPackage.eINSTANCE.getEjbFactory();

	public static Test suite() {
		return new TestSuite(ContainerManagedEntityTest.class);
	}
	
	private ContainerManagedEntity getInstance() {
		return EJBFACTORY.createContainerManagedEntity();
	}
    public void test_addKeyAttributeName() {
    	ContainerManagedEntity objContainerManagedEntity = getInstance();
        CMPAttribute cmpAttribute = EJBFACTORY.createCMPAttribute();
        List persistentAttributes = objContainerManagedEntity.getPersistentAttributes();
        persistentAttributes.add(cmpAttribute);
        String attrName = "attr1" ;
        cmpAttribute.setName(attrName);
        objContainerManagedEntity.addKeyAttributeName(attrName);
        List keys = objContainerManagedEntity.getKeyAttributes();
        boolean retValue = keys.contains(cmpAttribute);
        assertEquals(retValue,true);
   }

   
    public void test_addPersistentAttribute() {
        ContainerManagedEntity objContainerManagedEntity = getInstance();
        Field aField = JavaRefFactoryImpl.getActiveFactory().createField();
        String name ="attr1" ;
        aField.setName(name);
        CMPAttribute retValue = null;
        retValue = objContainerManagedEntity.addPersistentAttribute(aField);
        assertNotNull(retValue);
        retValue = objContainerManagedEntity.getPersistentAttribute(name);
        assertNotNull(retValue);
    }

    public void test_addPersistentAttributeName() {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        String name ="attr1" ;
        CMPAttribute retValue = null;
        retValue = objContainerManagedEntity.addPersistentAttributeName(name);
        assertNotNull(retValue);
        retValue = objContainerManagedEntity.getPersistentAttribute(name);
        assertNotNull(retValue);
    }

   
    public void test_getKeyAttribute() {
        ContainerManagedEntity objContainerManagedEntity = getInstance();
        CMPAttribute cmpAttribute = EJBFACTORY.createCMPAttribute();
        List persistentAttributes = objContainerManagedEntity.getPersistentAttributes();
        persistentAttributes.add(cmpAttribute);
        String attrName = "attr1" ;
        cmpAttribute.setName(attrName);
        objContainerManagedEntity.addKeyAttributeName(attrName);
        CMPAttribute retValue = objContainerManagedEntity.getKeyAttribute(attrName);
       
        assertEquals(retValue,cmpAttribute);
        
    }

  
    public void test_getPrimaryKeyAttribute() {

	  ContainerManagedEntity objContainerManagedEntity = getInstance();
      CMPAttribute cmpAttribute = EJBFACTORY.createCMPAttribute();
      List persistentAttributes = objContainerManagedEntity.getPersistentAttributes();
      persistentAttributes.add(cmpAttribute);
      String attrName = "attr1" ;
      cmpAttribute.setName(attrName);
      CMPAttribute retValue = objContainerManagedEntity.getPrimaryKeyAttribute();
      assertNotSame(retValue,cmpAttribute);
      objContainerManagedEntity.setPrimKeyField(cmpAttribute);
      retValue = objContainerManagedEntity.getPrimaryKeyAttribute();
      assertEquals(retValue,cmpAttribute);
    }

    public void test_isContainerManagedEntity() {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        boolean retValue = false;
        retValue = objContainerManagedEntity.isContainerManagedEntity();
        assertEquals(retValue,true);
    }

    public void test_isKeyAttribute() {

    	ContainerManagedEntity objContainerManagedEntity = getInstance();
        CMPAttribute cmpAttribute = EJBFACTORY.createCMPAttribute();
        List persistentAttributes = objContainerManagedEntity.getPersistentAttributes();
        persistentAttributes.add(cmpAttribute);
        String attrName = "attr1" ;
        cmpAttribute.setName(attrName);
        boolean retValue = objContainerManagedEntity.isKeyAttribute(cmpAttribute);
        assertEquals(retValue,false);
        objContainerManagedEntity.addKeyAttributeName(attrName);
        retValue = objContainerManagedEntity.isKeyAttribute(cmpAttribute);
        assertEquals(retValue,true);
        
    }
    
    public void test_removePersistentAttribute_2() {
    	ContainerManagedEntity objContainerManagedEntity = getInstance();
        CMPAttribute cmpAttribute = EJBFACTORY.createCMPAttribute();
        List persistentAttributes = objContainerManagedEntity.getPersistentAttributes();
        persistentAttributes.add(cmpAttribute);
        String attrName = "attr1" ;
        cmpAttribute.setName(attrName);
        CMPAttribute retValue = objContainerManagedEntity.getPersistentAttribute(attrName);
        assertNotNull(retValue);
        retValue = objContainerManagedEntity.removePersistentAttribute(attrName);
        assertNotNull(retValue);
        retValue = objContainerManagedEntity.getPersistentAttribute(attrName);
        assertNull(retValue);
    }
    
    
    public void test_removePersistentAttribute() {

    	 ContainerManagedEntity objContainerManagedEntity = getInstance();
         Field aField = JavaRefFactoryImpl.getActiveFactory().createField();
         String name ="attr1" ;
         aField.setName(name);
         CMPAttribute retValue = null;
         retValue = objContainerManagedEntity.addPersistentAttribute(aField);
         assertNotNull(retValue);
         
         retValue = objContainerManagedEntity.getPersistentAttribute(name);
         assertNotNull(retValue);
         
         retValue = objContainerManagedEntity.removePersistentAttribute(aField);
         assertNotNull(retValue);
         
         retValue = objContainerManagedEntity.getPersistentAttribute(name);
         assertNull(retValue);
    }
    
    public void test_setPrimKeyField() {

  	  ContainerManagedEntity objContainerManagedEntity = getInstance();
        CMPAttribute cmpAttribute = EJBFACTORY.createCMPAttribute();
        List persistentAttributes = objContainerManagedEntity.getPersistentAttributes();
        persistentAttributes.add(cmpAttribute);
        String attrName = "attr1" ;
        cmpAttribute.setName(attrName);
        CMPAttribute retValue = objContainerManagedEntity.getPrimKeyField();
        assertNotSame(retValue,cmpAttribute);
        objContainerManagedEntity.setPrimKeyField(cmpAttribute);
        retValue = objContainerManagedEntity.getPrimKeyField();
        assertEquals(retValue,cmpAttribute);
  }

   
    public void test_isVersion1_X() {
        ContainerManagedEntity objContainerManagedEntity = getInstance();
        objContainerManagedEntity.setVersion(ContainerManagedEntity.VERSION_1_X);
        boolean retValue = false;
        retValue = objContainerManagedEntity.isVersion1_X();
        assertEquals(retValue,true);
        objContainerManagedEntity.setVersion(ContainerManagedEntity.VERSION_2_X);
        retValue = objContainerManagedEntity.isVersion1_X();
        assertEquals(retValue,false);
    }

    public void test_isVersion2_X() {

    	 ContainerManagedEntity objContainerManagedEntity = getInstance();
         objContainerManagedEntity.setVersion(ContainerManagedEntity.VERSION_2_X);
         boolean retValue = false;
         retValue = objContainerManagedEntity.isVersion2_X();
         assertEquals(retValue,true);
         objContainerManagedEntity.setVersion(ContainerManagedEntity.VERSION_1_X);
         retValue = objContainerManagedEntity.isVersion2_X();
         assertEquals(retValue,false);
    }
    
    public void test_listPersistentFieldNames() {

    	ContainerManagedEntity objContainerManagedEntity = getInstance();
        CMPAttribute cmpAttribute1 = EJBFACTORY.createCMPAttribute();
        String attrName1 = "attr1" ;
        cmpAttribute1.setName(attrName1);
        
        CMPAttribute cmpAttribute2 = EJBFACTORY.createCMPAttribute();
        String attrName2 = "attr2" ;
        cmpAttribute2.setName(attrName2);
        
        List persistentAttributes = objContainerManagedEntity.getPersistentAttributes();
        persistentAttributes.add(cmpAttribute1);
        persistentAttributes.add(cmpAttribute2);
        
        String[] retValue = null;
        retValue = objContainerManagedEntity.listPersistentFieldNames();
        assertNotNull(retValue);
        assertEquals(retValue.length == 2, true);
        assertEquals((retValue[0]==attrName1 || retValue[1]==attrName1) , true);
        assertEquals((retValue[0]==attrName2 || retValue[1]==attrName2) , true);
         
    }

    public void test_isUnknownPrimaryKey() {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        objContainerManagedEntity.setPrimaryKeyName("java.lang.String");
        
        boolean retValue = false;
        retValue = objContainerManagedEntity.isUnknownPrimaryKey();
        assertEquals(retValue,false);
        
        objContainerManagedEntity.setPrimaryKeyName("java.lang.Object");
        retValue = objContainerManagedEntity.isUnknownPrimaryKey();
        assertEquals(retValue,true);
        
        
    }
  
    // TODO
   /* public void test_makePrimKeyFieldProxy() {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        objContainerManagedEntity.makePrimKeyFieldProxy();
    }

    public void test_getAllAvailableQueryMethods() {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        List retValue = null;
        retValue = objContainerManagedEntity.getAllAvailableQueryMethods();
    }

    public void test_getAvailableSelectQueryMethods() {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        List retValue = null;
        retValue = objContainerManagedEntity
                .getAvailableSelectQueryMethods();
    }

    public void test_getAvailableFindQueryMethods() {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        List retValue = null;
        retValue = objContainerManagedEntity.getAvailableFindQueryMethods();
    } */

    public void test_getCMPVersionID() throws IllegalStateException {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        objContainerManagedEntity.setVersion(ContainerManagedEntity.VERSION_1_X);
        int retValue = 0;
        retValue = objContainerManagedEntity.getCMPVersionID();
        assertEquals(J2EEVersionConstants.EJB_1_1_ID, retValue);
        objContainerManagedEntity.setVersion(ContainerManagedEntity.VERSION_2_X);
        
        retValue = objContainerManagedEntity.getCMPVersionID();
        assertEquals(J2EEVersionConstants.EJB_2_0_ID, retValue);
        
    }
  
   

  /*  public void test_getCMRFields() {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        List retValue = null;
        retValue = objContainerManagedEntity.getCMRFields();
    }

   
    public void test_getRoles() {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        List retValue = null;
        retValue = objContainerManagedEntity.getRoles();
    }

    public void test_getRole() {
        ContainerManagedEntity objContainerManagedEntity = getInstance();
        EJBRelationshipRole role = EJBFACTORY.createEJBRelationshipRole();
        String roleName = "role1";
        role.setName(roleName);
        CommonRelationshipRole retValue = null;
        retValue = objContainerManagedEntity.getRole(roleName);
        assertNull(retValue);
        objContainerManagedEntity.getRoles().add(role);
        retValue = objContainerManagedEntity.getRole(roleName);
        assertNotNull(retValue);
    }
    public void test_getFilteredFeatures() {

        ContainerManagedEntity objContainerManagedEntity = getInstance();
        EJBExtensionFilter aFilter = null;
        List retValue = null;
        retValue = objContainerManagedEntity.getFilteredFeatures(aFilter);
    } */

    /* public void test_getPersistentAttribute() {

    ContainerManagedEntity objContainerManagedEntity = getInstance();
    String aName = "";
    CMPAttribute retValue = null;
    retValue = objContainerManagedEntity.getPersistentAttribute(aName);
}*/

    
    /* public void test_getVersion() {

    ContainerManagedEntity objContainerManagedEntity = getInstance();
    String retValue = "";
    retValue = objContainerManagedEntity.getVersion();
}

public void test_setVersion() {

    ContainerManagedEntity objContainerManagedEntity = getInstance();
    String newVersion = "";
    objContainerManagedEntity.setVersion(newVersion);
}*/

/* public void test_getAbstractSchemaName() {

    ContainerManagedEntity objContainerManagedEntity = getInstance();
    String retValue = "";
    retValue = objContainerManagedEntity.getAbstractSchemaName();
}

public void test_setAbstractSchemaName() {

    ContainerManagedEntity objContainerManagedEntity = getInstance();
    String newAbstractSchemaName = "";
    objContainerManagedEntity
            .setAbstractSchemaName(newAbstractSchemaName);
}*/

/*  public void test_getPersistentAttributes() {

    ContainerManagedEntity objContainerManagedEntity = getInstance();
    EList retValue = null;
    retValue = objContainerManagedEntity.getPersistentAttributes();
}*/

/*public void test_getKeyAttributes() {

    ContainerManagedEntity objContainerManagedEntity = getInstance();
    EList retValue = null;
    retValue = objContainerManagedEntity.getKeyAttributes();
}*/

/* public void test_getQueries() {

    ContainerManagedEntity objContainerManagedEntity = getInstance();
    EList retValue = null;
    retValue = objContainerManagedEntity.getQueries();
}*/

/*  public void test_getCMPAttribute() {

    ContainerManagedEntity objContainerManagedEntity = getInstance();
    EList retValue = null;
    retValue = objContainerManagedEntity.getCMPAttribute();
}*/

/*  public void test_getPrimKeyField() {

    ContainerManagedEntity objContainerManagedEntity = getInstance();
    CMPAttribute retValue = null;
    retValue = objContainerManagedEntity.getPrimKeyField();
}*/

}
