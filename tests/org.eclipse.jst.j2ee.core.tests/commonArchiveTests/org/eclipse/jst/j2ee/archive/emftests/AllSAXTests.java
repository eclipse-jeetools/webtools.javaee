/*
 * Created on Sep 5, 2003
 *
 */
package org.eclipse.jst.j2ee.archive.emftests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.wst.common.internal.emf.resource.EMF2SAXRendererFactory;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;

/**
 * @author Administrator
 */
public class AllSAXTests extends TestCase{

	private RendererFactory defaultRendererFactory;
	
	AllTests tests = new AllTests(); // force the class to load if this test is loaded 
  
	public AllSAXTests(String name) {
		super(name);
	} 

	public AllSAXTests(String name, RendererFactory rf) {
		super(name);
		this.defaultRendererFactory = rf;
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.ibm.etools.archive.emftest");
		//$JUnit-BEGIN$
		suite.addTest(new AllSAXTests("testSwitchRenderer", RendererFactory.getDefaultRendererFactory()));
		suite.addTest(AppClientEMFEditTest.suite());
		suite.addTest(AppClientEMFTest.suite());
		suite.addTest(EarEMFEditTest.suite());
		suite.addTest(EarEMFTest.suite()); //
		suite.addTest(EjbEMFEditTest.suite());
		suite.addTest(EjbEMFTest.suite());
		suite.addTest(RarEMFEditTest.suite());
		suite.addTest(RarEMFTest.suite());
		suite.addTest(WarEMFEditTest.suite());
		suite.addTest(WarEMFTest.suite());
		suite.addTest(WebServicesEMFTest.suite());
		suite.addTest(new AllSAXTests("testSwitchRendererBack", RendererFactory.getDefaultRendererFactory()));

		//$JUnit-END$
		return suite;
	}
	
	public static void main(java.lang.String[] args) {
		 junit.textui.TestRunner.main(new String[] { AllSAXTests.class.getName() });
	}	
	
	public void testSwitchRenderer() {
		RendererFactory.setDefaultRendererFactory(EMF2SAXRendererFactory.INSTANCE); 
	}
	
	public void testSwitchRendererBack() {
		RendererFactory.setDefaultRendererFactory(defaultRendererFactory);
	}
}
