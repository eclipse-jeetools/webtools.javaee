package org.eclipse.jst.j2ee.ejb.test;

import java.util.List;

import junit.framework.TestSuite;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.QueryMethod;

public class QueryMethodTest extends EjbEMFTestBase {

	/**
	 * @param name
	 */
	public QueryMethodTest(String name) {
		super(name);
	}
	
   
  /*  public void test_getQuery() {

        QueryMethod objQueryMethod = EjbFactory.eINSTANCE.createQueryMethod();
        Query retValue = null;
        retValue = objQueryMethod.getQuery();
    }

    public void test_setQuery() {

        QueryMethod objQueryMethod = EjbFactory.eINSTANCE.createQueryMethod();
        Query newQuery = null;
        objQueryMethod.setQuery(newQuery);
    } */
    

    
    public void test_getClientTypeJavaClasses() throws Exception {
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
		assertNotNull(entityBean);
		QueryMethod objQueryMethod =getEjbFactory().createQueryMethod();
		objQueryMethod.setName(QueryMethod.SELECT_PREFIX+"Test");
		objQueryMethod.setEnterpriseBean(entityBean);
		JavaClass[] retValue = objQueryMethod.getClientTypeJavaClasses();
		assertNotNull(retValue);
		assertEquals(retValue.length==1,true );
		assertEquals(retValue[0],entityBean.getEjbClass());
		
		objQueryMethod.setName(QueryMethod.FIND_PREFIX+"Test");
		
		if (entityBean.hasLocalClient() && !entityBean.hasRemoteClient()) {
			retValue = objQueryMethod.getClientTypeJavaClasses();
			assertNotNull(retValue);
			assertEquals(retValue.length==1,true );
			assertEquals(retValue[0],entityBean.getLocalHomeInterface());
		}	
		if (entityBean.hasRemoteClient() && !entityBean.hasLocalClient()) {
			retValue = objQueryMethod.getClientTypeJavaClasses();
			assertNotNull(retValue);
			assertEquals(retValue.length==1,true );
			assertEquals(retValue[0],entityBean.getHomeInterface());
		}
		if (entityBean.hasRemoteClient() && entityBean.hasLocalClient()) {
			retValue = objQueryMethod.getClientTypeJavaClasses();
			assertNotNull(retValue);
			assertEquals(retValue.length==2,true );
			assertEquals(retValue[0],entityBean.getLocalHomeInterface());
			assertEquals(retValue[1],entityBean.getHomeInterface());
		}  
	}
    
    public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new QueryMethodTest("test_getClientTypeJavaClasses"));
		return suite;
	}		
		
	
	

}
