/*
 * Created on Sep 5, 2003
 *
 */
package org.eclipse.jst.j2ee.archive.emftests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.wst.common.internal.emf.resource.EMF2SAXRendererFactory;

/**
 * @author Administrator
 */
public class AllSAXTests extends TestSuite{

//	private RendererFactory defaultRendererFactory;
//	
//	AllTests tests = new AllTests(); // force the class to load if this test is loaded 
//  
//	public AllSAXTests(String name) {
//		super(name);
//	} 
//
//	public AllSAXTests(String name, RendererFactory rf) {
//		super(name);
//		this.defaultRendererFactory = rf;
//	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("All SAX Tests for com.ibm.etools.archive.emftest");
		//$JUnit-BEGIN$
		suite.addTest(AppClientEMFEditTest.suite(EMF2SAXRendererFactory.INSTANCE));
		suite.addTest(AppClientEMFTest.suite(EMF2SAXRendererFactory.INSTANCE));
		suite.addTest(EarEMFEditTest.suite(EMF2SAXRendererFactory.INSTANCE));
		suite.addTest(EarEMFTest.suite(EMF2SAXRendererFactory.INSTANCE)); //
		suite.addTest(EjbEMFEditTest.suite(EMF2SAXRendererFactory.INSTANCE));
		suite.addTest(EjbEMFTest.suite(EMF2SAXRendererFactory.INSTANCE));
		suite.addTest(RarEMFEditTest.suite(EMF2SAXRendererFactory.INSTANCE));
		suite.addTest(RarEMFTest.suite(EMF2SAXRendererFactory.INSTANCE));
		suite.addTest(WarEMFEditTest.suite(EMF2SAXRendererFactory.INSTANCE));
		suite.addTest(WarEMFTest.suite(EMF2SAXRendererFactory.INSTANCE));
		suite.addTest(WebServicesEMFTest.suite(EMF2SAXRendererFactory.INSTANCE));
		//$JUnit-END$
		return suite;
	}
	
	public static void main(java.lang.String[] args) {
		 junit.textui.TestRunner.main(new String[] { AllSAXTests.class.getName() });
	}	
	
//	public void testSwitchRenderer() {
//		RendererFactory.setDefaultRendererFactory(EMF2SAXRendererFactory.INSTANCE); 
//	}
//	
//	public void testSwitchRendererBack() {
//		RendererFactory.setDefaultRendererFactory(defaultRendererFactory);
//	}
}
