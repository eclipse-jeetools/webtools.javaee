/*
 * Created on Sep 5, 2003
 *
 */
package org.eclipse.jst.j2ee.archive.emftests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRendererFactory;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;

/**
 * @author Administrator
 */
public class AllDOMTests extends TestCase{
	
	private RendererFactory defaultRendererFactory;

    public AllDOMTests(String name) {
		super(name);
	}
	
	public AllDOMTests(String name, RendererFactory rf) {
		super(name);
		this.defaultRendererFactory = rf;
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.ibm.etools.archive.emftest");
		//$JUnit-BEGIN$
		suite.addTest(new AllDOMTests("testSwitchRenderer", RendererFactory.getDefaultRendererFactory()));
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
		suite.addTest(new AllDOMTests("testSwitchRendererBack", RendererFactory.getDefaultRendererFactory()));
 
		//$JUnit-END$
		return suite;
	}
	
	public static void main(java.lang.String[] args) {
		 junit.textui.TestRunner.main(new String[] { AllDOMTests.class.getName() });
	}	
	
	public void testSwitchRenderer() {
		RendererFactory.setDefaultRendererFactory(EMF2DOMRendererFactory.INSTANCE);
	
	}

	public void testSwitchRendererBack() {
		RendererFactory.setDefaultRendererFactory(defaultRendererFactory);
	}

}
