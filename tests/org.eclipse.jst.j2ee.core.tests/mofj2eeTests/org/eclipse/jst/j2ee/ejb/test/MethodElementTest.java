package org.eclipse.jst.j2ee.ejb.test;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.java.impl.JavaRefFactoryImpl;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodElementKind;
import org.eclipse.jst.j2ee.ejb.Session;

public class MethodElementTest extends EjbEMFTestBase {
	private final int SESSION =0;
	private final int ENTITY = 1;
	private final int CONTAINER_MANAGED_ENTITY =2;
	
	

	/**
	 * @param name
	 */
	public MethodElementTest(String name) {
		super(name);
		
	}

	private MethodElement getInstance() {
		return EjbPackage.eINSTANCE.getEjbFactory().createMethodElement();
	}
	
    public void test_MethodElement() {
        MethodElement tmpMethodElement = getInstance();
        assertNotNull(tmpMethodElement);
    }

    public void test_addMethodParams() {
        MethodElement objMethodElement = getInstance();
        String param1 = "param1";
        String param2= "param2";
        boolean retValue = false;
        objMethodElement.addMethodParams(param1);
        objMethodElement.addMethodParams(param2);
        List list = objMethodElement.getMethodParams();
        retValue = list.contains(param1);
        assertEquals(retValue,true);
    }

    public void test_applyZeroParams() {
    	MethodElement objMethodElement = getInstance();
        String param1 = "param1";
        String param2= "param2";
        boolean retValue = false;
        objMethodElement.addMethodParams(param1);
        objMethodElement.addMethodParams(param2);
        List list = objMethodElement.getMethodParams();
        assertEquals(list.size()==2, true) ;
        objMethodElement.applyZeroParams();
        list = objMethodElement.getMethodParams();
        assertEquals(list.size()==0, true) ;
        
    }

    public void test_equalSignature() {

        MethodElement objMethodElement1 = getInstance();
        MethodElement objMethodElement2 = getInstance();
        MethodElement objMethodElement3 = getInstance();
        
        objMethodElement1.setName("name");
        objMethodElement2.setName("name");
        objMethodElement3.setName("name");
        
        String param1 = "param1";
        String param2= "param2";
        String param3 = "param3";
        String param4= "param4";
        
        objMethodElement1.addMethodParams(param1);
        objMethodElement1.addMethodParams(param2);
        
        objMethodElement2.addMethodParams(param1);
        objMethodElement2.addMethodParams(param2);
        
        objMethodElement3.addMethodParams(param1);
        objMethodElement3.addMethodParams(param2);
        
        objMethodElement1.setType(MethodElementKind.LOCAL_LITERAL);
        objMethodElement2.setType(MethodElementKind.LOCAL_LITERAL);
        objMethodElement3.setType(MethodElementKind.LOCAL_LITERAL);
        
        boolean retValue = false;
        retValue = objMethodElement1.equalSignature(objMethodElement2);
        assertEquals(retValue,true);
        retValue = objMethodElement2.equalSignature(objMethodElement3);
        assertEquals(retValue,true);
        
        objMethodElement3.setName("nameDiff");
        retValue = objMethodElement2.equalSignature(objMethodElement3);
        assertEquals(retValue,false);
        
        objMethodElement2.addMethodParams(param3);
        retValue = objMethodElement1.equalSignature(objMethodElement2);
        assertEquals(retValue,false);
        
        objMethodElement3.setName("name");
        retValue = objMethodElement1.equalSignature(objMethodElement3);
        assertEquals(retValue,true);
        
        objMethodElement3.setType(MethodElementKind.HOME_LITERAL);
        retValue = objMethodElement1.equalSignature(objMethodElement3);
        assertEquals(retValue,false);
    }

    public void test_getMethodParams() {

    	MethodElement objMethodElement = getInstance();
        String param1 = "param1";
        String param2= "param2";
        boolean retValue = false;
        objMethodElement.addMethodParams(param1);
        objMethodElement.addMethodParams(param2);
        List list = objMethodElement.getMethodParams();
        assertEquals(list.size()==2, true) ;
        assertEquals(list.contains(param1),true);
        assertEquals(list.contains(param2),true);
    }

    

    public void test_getSignature() {

    	MethodElement objMethodElement = getInstance();
    	String name = "test";
        objMethodElement.setName(name);
        
        String param1 = "param1";
        String param2= "param2";
        
        objMethodElement.addMethodParams(param1);
        objMethodElement.addMethodParams(param2);
        
        String signature = name + "(" + param1 + "," + param2 + ")";
        String retValue = objMethodElement.getSignature();
        assertEquals(signature,retValue);
        
      
    }
    
    
    
   
    public void test_getTypeJavaClass() throws Exception {
    	init();
    	EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
    	List enterpriseBeans = (List)DD.getEJBJar().getEnterpriseBeans();
    	EnterpriseBean eb = (EnterpriseBean)enterpriseBeans.get(0);
        MethodElement objMethodElement = getInstance();
        objMethodElement.setEnterpriseBean(eb);
        JavaClass retValue = null;
        if (eb.hasRemoteClient()) {
        	objMethodElement.setType(MethodElementKind.HOME_LITERAL);
            retValue = objMethodElement.getTypeJavaClass();
            assertEquals(retValue,eb.getHomeInterface());
            objMethodElement.setType(MethodElementKind.REMOTE_LITERAL);
            retValue = objMethodElement.getTypeJavaClass();
            assertEquals(retValue,eb.getRemoteInterface());
            
        } 
        if (eb.hasLocalClient()){
        	objMethodElement.setType(MethodElementKind.LOCAL_HOME_LITERAL);
            retValue = objMethodElement.getTypeJavaClass();
            assertEquals(retValue,eb.getLocalHomeInterface());
            objMethodElement.setType(MethodElementKind.LOCAL_LITERAL);
            retValue = objMethodElement.getTypeJavaClass();
            assertEquals(retValue,eb.getLocalInterface());
            
        } 
        if (eb.isMessageDriven()) {
        	objMethodElement.setType(MethodElementKind.UNSPECIFIED_LITERAL);
        	retValue = objMethodElement.getTypeJavaClass();
            assertEquals(retValue,eb.getEjbClass());
        }
  
    }
    
   

   /* public void test_getMethods() {
    
    }

    public void test_getMostSpecific() {

        MethodElement objMethodElement = getInstance();
        MethodElement aMethodElement = null;
        JavaClass aClass = null;
        MethodElement retValue = null;
        retValue = objMethodElement.getMostSpecific(aMethodElement, aClass);
    }*/
    
    public void test_hasMethodParams() {
        MethodElement objMethodElement = getInstance();
        boolean retValue = false;
        retValue = objMethodElement.hasMethodParams();
        assertEquals(retValue,false);
        String param1 = "param1";
        objMethodElement.addMethodParams(param1);
        retValue = objMethodElement.hasMethodParams();
        assertEquals(retValue,true);
        String param2 = "param2";
        objMethodElement.addMethodParams(param2);
        retValue = objMethodElement.hasMethodParams();
        assertEquals(retValue,true);
    }

    public void test_initializeFromSignature() {
        MethodElement objMethodElement = getInstance();
        String signature = "setTwoParamMethod(java.lang.String,java.util.List)";
        objMethodElement.initializeFromSignature(signature);
        List list = objMethodElement.getMethodParams();
        assertEquals(list.size()==2, true) ;
        String name = "setTwoParamMethod";
        assertEquals(list.contains("java.lang.String"),true);
        assertEquals(list.contains("java.util.List"),true);
        assertEquals(objMethodElement.getName(), "setTwoParamMethod");
        
    }

    public void test_isDefault() {
        MethodElement objMethodElement = getInstance();
        objMethodElement.setName("test");
        boolean retValue = false;
        retValue = objMethodElement.isDefault();
        assertEquals(retValue,false);
        objMethodElement.setName(JavaClass.DEFAULT_METHOD_NAME);
        retValue = objMethodElement.isDefault();
        assertEquals(retValue,true);
        
    }

    public void test_isEquivalent() {

        MethodElement objMethodElement1 = getInstance();
        MethodElement objMethodElement2 = getInstance();
        
        Session session1 = getEjbFactory().createSession();
        Session session2 = getEjbFactory().createSession();
        objMethodElement1.setEnterpriseBean(session1);
        objMethodElement2.setEnterpriseBean(session1);
        String signature1 = "setTwoParamMethod(java.lang.String,java.util.List)";
        String signature2 = "setTwoParamMethod2(java.lang.String,java.util.List)";
        objMethodElement1.initializeFromSignature(signature1);
        objMethodElement2.initializeFromSignature(signature1);
        boolean retValue = false;
        retValue = objMethodElement1.isEquivalent(objMethodElement2);
        assertEquals(retValue,true);
        objMethodElement2.initializeFromSignature(signature2);
        retValue = objMethodElement1.isEquivalent(objMethodElement2);
        assertEquals(retValue,false);
        
        objMethodElement2.initializeFromSignature(signature1);
        objMethodElement2.setEnterpriseBean(session2);
        retValue = objMethodElement1.isEquivalent(objMethodElement2);
        assertEquals(retValue,false);
    }

    public void test_isHome() {

        MethodElement objMethodElement = getInstance();
        objMethodElement.setType(MethodElementKind.HOME_LITERAL);
        boolean retValue = false;
        retValue = objMethodElement.isHome();
        assertEquals(retValue,true);
        objMethodElement.setType(MethodElementKind.LOCAL_HOME_LITERAL);
        retValue = objMethodElement.isHome();
        assertEquals(retValue,false);
        
    }

    public void test_isRemote() {
    	 MethodElement objMethodElement = getInstance();
         objMethodElement.setType(MethodElementKind.REMOTE_LITERAL);
         boolean retValue = false;
         retValue = objMethodElement.isRemote();
         assertEquals(retValue,true);
         objMethodElement.setType(MethodElementKind.LOCAL_HOME_LITERAL);
         retValue = objMethodElement.isRemote();
         assertEquals(retValue,false);
    }

    public void test_isUnspecified() {
    	 MethodElement objMethodElement = getInstance();
         objMethodElement.setType(MethodElementKind.UNSPECIFIED_LITERAL);
         boolean retValue = false;
         retValue = objMethodElement.isUnspecified();
         assertEquals(retValue,true);
         objMethodElement.setType(MethodElementKind.LOCAL_HOME_LITERAL);
         retValue = objMethodElement.isUnspecified();
         assertEquals(retValue,false);
        
    }

    public void test_isLocalHome() {

    	 MethodElement objMethodElement = getInstance();
         objMethodElement.setType(MethodElementKind.LOCAL_HOME_LITERAL );
         boolean retValue = false;
         retValue = objMethodElement.isLocalHome();
         assertEquals(retValue,true);
         objMethodElement.setType(MethodElementKind.UNSPECIFIED_LITERAL);
         retValue = objMethodElement.isLocalHome();
         assertEquals(retValue,false);
    }

    public void test_isLocal() {
    	 MethodElement objMethodElement = getInstance();
         objMethodElement.setType(MethodElementKind.LOCAL_LITERAL );
         boolean retValue = false;
         retValue = objMethodElement.isLocal();
         assertEquals(retValue,true);
         objMethodElement.setType(MethodElementKind.UNSPECIFIED_LITERAL);
         retValue = objMethodElement.isLocal();
         assertEquals(retValue,false);
    }

    public void test_isValid() {
        MethodElement objMethodElement = getInstance();
        boolean retValue = false;
        retValue = objMethodElement.isValid();
    }

  
    public void test_nameAndParamsEquals() {

        MethodElement objMethodElement = getInstance();
        Method aMethod =  JavaRefFactoryImpl.getActiveFactory().createMethod();
        String name = "setTwoParamMethod";
        String param1 = "java.lang.String";
        String param2 = "java.util.List";
        
        JavaParameter javaParameter1 = JavaRefFactory.eINSTANCE.createJavaParameter();
        JavaHelpers type1 = JavaRefFactory.eINSTANCE.createClassRef("java.lang.String");
        javaParameter1.setEType(type1);
        
        JavaParameter javaParameter2 = JavaRefFactory.eINSTANCE.createJavaParameter();
        JavaHelpers type2 = JavaRefFactory.eINSTANCE.createClassRef("java.util.List");
        javaParameter2.setEType(type2);
        
        aMethod.setName(name);
        aMethod.getParameters().add(javaParameter1);
        aMethod.getParameters().add(javaParameter2);
        
        objMethodElement.setName(name);
        objMethodElement.addMethodParams(param1);
        objMethodElement.addMethodParams(param2);
       
        boolean retValue = false;
        retValue = objMethodElement.nameAndParamsEquals(aMethod);
        assertEquals(retValue,true);
        
        String name2 = "setTwoParamMethod2";
        objMethodElement.setName(name2);
        retValue = objMethodElement.nameAndParamsEquals(aMethod);
        assertEquals(retValue,false);
        
        objMethodElement.setName(name);
        String param3 = "java.util.List";
        objMethodElement.addMethodParams(param3);
        retValue = objMethodElement.nameAndParamsEquals(aMethod);
        assertEquals(retValue,false);
        
    }

    public void test_removeMethodParams() {

    	 MethodElement objMethodElement = getInstance();
         String param1 = "param1";
         String param2= "param2";
         boolean retValue = false;
         objMethodElement.addMethodParams(param1);
         objMethodElement.addMethodParams(param2);
         List list = objMethodElement.getMethodParams();
         retValue = list.contains(param1);
         assertEquals(retValue,true);
         objMethodElement.removeMethodParams(param1);
         list = objMethodElement.getMethodParams();
         retValue = list.contains(param1);
         assertEquals(retValue,false);
    }

 /*   public void test_represents() {

        MethodElement objMethodElement = getInstance();
        Method aMethod = null;
        boolean retValue = false;
        retValue = objMethodElement.represents(aMethod);
    }

    public void test_setIdToReadableString() {

        MethodElement objMethodElement = getInstance();
        objMethodElement.setIdToReadableString();
    }

    public void test_uniquelyIdentifies() {

        MethodElement objMethodElement = getInstance();
        Method aMethod = null;
        boolean retValue = false;
        retValue = objMethodElement.uniquelyIdentifies(aMethod);
    } */

   /* public void test_getName() {

        MethodElement objMethodElement = getInstance();
        String retValue = "";
        retValue = objMethodElement.getName();
    }*/

   /* public void test_setName() {

        MethodElement objMethodElement = getInstance();
        String newName = "";
        objMethodElement.setName(newName);
    }*/

   /* public void test_getParms() {

        MethodElement objMethodElement = getInstance();
        String retValue = "";
        retValue = objMethodElement.getParms();
    }

    public void test_setParms() {

        MethodElement objMethodElement = getInstance();
        String newParms = "";
        objMethodElement.setParms(newParms);
    }*/

  /*  public void test_getType() {

        MethodElement objMethodElement = getInstance();
        MethodElementKind retValue = null;
        retValue = objMethodElement.getType();
    }

    public void test_setType() {

        MethodElement objMethodElement = getInstance();
        MethodElementKind newType = null;
        objMethodElement.setType(newType);
    }

    public void test_unsetType() {

        MethodElement objMethodElement = getInstance();
        objMethodElement.unsetType();
    }

    public void test_isSetType() {

        MethodElement objMethodElement = getInstance();
        boolean retValue = false;
        retValue = objMethodElement.isSetType();
    }*/

  /*  public void test_getDescription() {

        MethodElement objMethodElement = getInstance();
        String retValue = "";
        retValue = objMethodElement.getDescription();
    }

    public void test_setDescription() {

        MethodElement objMethodElement = getInstance();
        String newDescription = "";
        objMethodElement.setDescription(newDescription);
    }*/

   /* public void test_getEnterpriseBean() {

        MethodElement objMethodElement = getInstance();
        EnterpriseBean retValue = null;
        retValue = objMethodElement.getEnterpriseBean();
    }

  
    public void test_setEnterpriseBean() {

        MethodElement objMethodElement = getInstance();
        EnterpriseBean newEnterpriseBean = null;
        objMethodElement.setEnterpriseBean(newEnterpriseBean);
    }*/

   /* public void test_getDescriptions() {

        MethodElement objMethodElement = getInstance();
        EList retValue = null;
        retValue = objMethodElement.getDescriptions();
    }*/
    
    public static Test suite() {
		return new TestSuite(MethodElementTest.class);
	}

}
