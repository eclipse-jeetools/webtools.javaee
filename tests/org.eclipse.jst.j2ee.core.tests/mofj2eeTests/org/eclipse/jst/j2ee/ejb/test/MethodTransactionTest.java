package org.eclipse.jst.j2ee.ejb.test;

import java.util.List;

import junit.framework.TestSuite;

import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.archive.emftests.EjbEMFTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.ejb.TransactionAttributeType;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;

public class MethodTransactionTest extends EjbEMFTestBase {

    /**
	 * @param name
	 */
	public MethodTransactionTest(String name) {
		super(name);
	}

	public void test_MethodTransaction() {
        MethodTransaction tmpMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        assertNotNull(tmpMethodTransaction);
    }   
    
    
    public void test_getMethodElements() throws Exception{
    	init();
    	EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		AssemblyDescriptor assemblyDescriptor = DD.getEJBJar().getAssemblyDescriptor();
		assertNotNull(assemblyDescriptor);
		List enterpriseBeans = (List)DD.getEJBJar().getEnterpriseBeans();
		EnterpriseBean eb1 = (EnterpriseBean)enterpriseBeans.get(0);
		// create a method transaction
		MethodTransaction methodTransaction = getEjbFactory().createMethodTransaction();
		List methodTransactions = assemblyDescriptor.getMethodTransactions();
		methodTransactions.add(methodTransaction);
		// create a method element
		MethodElement methodElement = getEjbFactory().createMethodElement();
		methodElement.setEnterpriseBean(eb1);
		methodTransaction.getMethodElements().add(methodElement);
		// get the method element for the enterprise bean
		List methodElements = methodTransaction.getMethodElements(eb1);
		assertEquals(methodElements.contains(methodElement), true);
	}
    
    /**
     *  
     * [issue : SA - getMostSpecificMethodElement() - does it need to be an API as it calls
     *  to the static member of MethodElementImpl ]
     *
     */

    public void test_getMostSpecificMethodElement() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        Method aMethod = null;
        MethodElement retValue = null;
        retValue = objMethodTransaction
                .getMostSpecificMethodElement(aMethod);
    }

    public void test_isEquivalent() {

        MethodTransaction objMethodTransaction1 = getEjbFactory().createMethodTransaction();
        MethodTransaction objMethodTransaction2 = getEjbFactory().createMethodTransaction();

        objMethodTransaction1.setTransactionAttribute(TransactionAttributeType.SUPPORTS_LITERAL);
        objMethodTransaction2.setTransactionAttribute(TransactionAttributeType.SUPPORTS_LITERAL);
     
        boolean retValue = objMethodTransaction1.isEquivalent(objMethodTransaction2);
        assertEquals(retValue,true);
        objMethodTransaction2.setTransactionAttribute(TransactionAttributeType.REQUIRED_LITERAL);
        
        retValue = objMethodTransaction1.isEquivalent(objMethodTransaction2);
        assertEquals(retValue,false);
    }

  /*  public void test_getTransactionAttribute() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        TransactionAttributeType retValue = null;
        retValue = objMethodTransaction.getTransactionAttribute();
    }

    public void test_setTransactionAttribute() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        TransactionAttributeType newTransactionAttribute = null;
        objMethodTransaction
                .setTransactionAttribute(newTransactionAttribute);
    }

    public void test_unsetTransactionAttribute() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        objMethodTransaction.unsetTransactionAttribute();
    } 

    public void test_isSetTransactionAttribute() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        boolean retValue = false;
        retValue = objMethodTransaction.isSetTransactionAttribute();
    }
*/
 /*   public void test_getDescription() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        String retValue = "";
        retValue = objMethodTransaction.getDescription();
    }

    public void test_setDescription() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        String newDescription = "";
        objMethodTransaction.setDescription(newDescription);
    }*/

 /*   public void test_getAssemblyDescriptor() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        AssemblyDescriptor retValue = null;
        retValue = objMethodTransaction.getAssemblyDescriptor();
    }

    public void test_setAssemblyDescriptor() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        AssemblyDescriptor newAssemblyDescriptor = null;
        objMethodTransaction.setAssemblyDescriptor(newAssemblyDescriptor);
    } */

  /*  public void test_getMethodElements_2() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        EList retValue = null;
        retValue = objMethodTransaction.getMethodElements();
    }

    public void test_getDescriptions() {

        MethodTransaction objMethodTransaction = EjbFactory.eINSTANCE.createMethodTransaction();
        EList retValue = null;
        retValue = objMethodTransaction.getDescriptions();
    } */
    
    public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new MethodTransactionTest("test_isEquivalent"));
		suite.addTest(new MethodTransactionTest("test_getMethodElements"));
		return suite;
	}
   

}
