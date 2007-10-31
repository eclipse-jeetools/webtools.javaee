/**
 * 
 */
package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashSet;

import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.tests.BaseTestCase;

/**
 * @author itewk
 *
 */
public abstract class GeneralEMFTest extends BaseTestCase {
	private RendererFactory testingFactory;
	private RendererFactory defaultFactory;
	
	public GeneralEMFTest(String name) {
		super(name);
		
		defaultFactory = RendererFactory.getDefaultRendererFactory();
		testingFactory = RendererFactory.getDefaultRendererFactory();
	}
	
	public GeneralEMFTest(String name, RendererFactory factory) {
		super(name);
		
		defaultFactory = RendererFactory.getDefaultRendererFactory();
		testingFactory = factory;
	}
	
	protected void setUp() throws Exception {
		//set the default factory to the factory needed for this test run
		RendererFactory.setDefaultRendererFactory(testingFactory);
		
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		//set the default factory back to the orginal default
		RendererFactory.setDefaultRendererFactory(defaultFactory);
		
		super.setUp();
	}
	
	protected CommonarchiveFactory getArchiveFactory() {
        return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
    }

	protected EjbFactory getEjbFactory() {
        return EjbPackage.eINSTANCE.getEjbFactory();
    }

	protected ApplicationFactory getApplicationFactory() {
        return ApplicationPackage.eINSTANCE.getApplicationFactory();
    }

	protected WebapplicationFactory getWebAppFactory() {
        return WebapplicationPackage.eINSTANCE.getWebapplicationFactory();
    }
    
	protected HashSet ignorableAttributes(){
		HashSet set = new HashSet();
		set.add("id");
		return set;
	}
}
