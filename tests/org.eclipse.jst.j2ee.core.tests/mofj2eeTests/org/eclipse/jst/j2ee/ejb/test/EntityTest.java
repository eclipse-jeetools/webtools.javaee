package org.eclipse.jst.j2ee.ejb.test;

import java.util.List;

import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.archive.emftests.EjbEMFTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;

public class EntityTest extends EjbEMFTestBase{

	/**
	 * @param name
	 */
	public EntityTest(String name) {
		super(name);
	}
	
	private Entity getInstance() {
		return EjbFactory.eINSTANCE.createEntity();
	}
    public void test_Entity() {
        Entity tmpEntity = getInstance();
        assertNotNull(tmpEntity);
    }
    
    /* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

    public void test_getPrimaryKeyName() throws Exception{
    	init();
    	EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
    	
    	AssemblyDescriptor assemblyDescriptor = DD.getEJBJar().getAssemblyDescriptor();
		List enterpriseBeans = (List)DD.getEJBJar().getEnterpriseBeans();
		ContainerManagedEntity entityBean = null;
		EnterpriseBean bean = null;
		for (int x=0; x< enterpriseBeans.size(); ++x) {
			bean = (EnterpriseBean) enterpriseBeans.get(x);
			if (bean.isEntity() && bean.isContainerManagedEntity()) {
				entityBean = (ContainerManagedEntity) bean;
				break;
			}
		}
		if (entityBean != null) {
			String name = entityBean.getPrimaryKeyName();
			String primKeyName = entityBean.getPrimaryKey().getQualifiedName();
			assertEquals(name,primKeyName);
			
		}
    }

    
   /* public void test_isContainerManagedEntity() {
        Entity objEntity = getInstance();
        boolean retValue = false;
        retValue = objEntity.isContainerManagedEntity();
    }*/

    public void test_setPrimaryKeyName() throws Exception {
    	init();
    	EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
    	
    	AssemblyDescriptor assemblyDescriptor = DD.getEJBJar().getAssemblyDescriptor();
		List enterpriseBeans = (List)DD.getEJBJar().getEnterpriseBeans();
		ContainerManagedEntity entityBean = null;
		EnterpriseBean bean = null;
		for (int x=0; x< enterpriseBeans.size(); ++x) {
			bean = (EnterpriseBean) enterpriseBeans.get(x);
			if (bean.isEntity() && bean.isContainerManagedEntity()) {
				entityBean = (ContainerManagedEntity) bean;
				break;
			}
		}
		if (entityBean != null) {
			String name = entityBean.getPrimaryKeyName();
			String newName = "NewName" ;
			entityBean.setPrimaryKeyName(newName);
			assertEquals(newName,entityBean.getPrimaryKeyName());
		}    
    }

  /* public void test_isReentrant() {

        Entity objEntity = getInstance();
        boolean retValue = false;
        retValue = objEntity.isReentrant();
    }

    public void test_setReentrant() {

        Entity objEntity = getInstance();
        boolean newReentrant = false;
        objEntity.setReentrant(newReentrant);
    }*/

  /*  public void test_unsetReentrant() {

        Entity objEntity = getInstance();
        objEntity.unsetReentrant();
    } */

   /* public void test_isSetReentrant() {

        Entity objEntity = getInstance();
        boolean retValue = false;
        retValue = objEntity.isSetReentrant();
    } */

  /*  public void test_getPrimaryKey() {

        Entity objEntity = getInstance();
        JavaClass retValue = null;
        retValue = objEntity.getPrimaryKey();
    }

    public void test_setPrimaryKey() {

        Entity objEntity = getInstance();
        JavaClass newPrimaryKey = null;
        objEntity.setPrimaryKey(newPrimaryKey);
    } */
    
    public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new EntityTest("test_setPrimaryKeyName"));
		suite.addTest(new EntityTest("test_getPrimaryKeyName"));
		return suite;
	}



}
