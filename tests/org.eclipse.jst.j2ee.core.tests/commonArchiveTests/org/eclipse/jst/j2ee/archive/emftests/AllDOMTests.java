/*
 * Created on Sep 5, 2003
 *
 */
package org.eclipse.jst.j2ee.archive.emftests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRendererFactory;

/**
 * @author Administrator
 */
public class AllDOMTests extends TestSuite {
	
//	private RendererFactory defaultRendererFactory;

//    public AllDOMTests(String name) {
//		super(name);
//	}
	
//	public AllDOMTests(String name, RendererFactory rf) {
//		super(name);
//		this.defaultRendererFactory = rf;
//	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("All DOM Tests for com.ibm.etools.archive.emftest");
		//$JUnit-BEGIN$
		suite.addTest(AppClientEMFEditTest.suite(EMF2DOMRendererFactory.INSTANCE));
		suite.addTest(AppClientEMFTest.suite(EMF2DOMRendererFactory.INSTANCE));
		suite.addTest(EarEMFEditTest.suite(EMF2DOMRendererFactory.INSTANCE));
		suite.addTest(EarEMFTest.suite(EMF2DOMRendererFactory.INSTANCE)); //
		suite.addTest(EjbEMFEditTest.suite(EMF2DOMRendererFactory.INSTANCE));
		suite.addTest(EjbEMFTest.suite(EMF2DOMRendererFactory.INSTANCE));
		suite.addTest(RarEMFEditTest.suite(EMF2DOMRendererFactory.INSTANCE));
		suite.addTest(RarEMFTest.suite(EMF2DOMRendererFactory.INSTANCE));
		suite.addTest(WarEMFEditTest.suite(EMF2DOMRendererFactory.INSTANCE));
		suite.addTest(WarEMFTest.suite(EMF2DOMRendererFactory.INSTANCE));
		suite.addTest(WebServicesEMFTest.suite(EMF2DOMRendererFactory.INSTANCE));
		//$JUnit-END$
		return suite;
	}
	
	public static void main(java.lang.String[] args) {
		 junit.textui.TestRunner.main(new String[] { AllDOMTests.class.getName() });
	}	
	
//	public void testSwitchRenderer() {
//		RendererFactory.setDefaultRendererFactory(EMF2DOMRendererFactory.INSTANCE);
//	
//	}
//
//	public void testSwitchRendererBack() {
//		RendererFactory.setDefaultRendererFactory(defaultRendererFactory);
//	}

}
