/**
 * 
 */
package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
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
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.validation.internal.operations.ValidationBuilder;

/**
 * @author itewk
 *
 */
public abstract class GeneralEMFTest extends BaseTestCase {
	private RendererFactory testingFactory;
	private RendererFactory defaultFactory;
	public static final String VALIDATOR_JOB_FAMILY = "validators";
	
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
		// Wait for all validation jobs to end before ending test....
		waitOnJobs();
		super.tearDown();
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
	public static void waitOnJobs() throws InterruptedException {
		IProject[] projects = ProjectUtility.getAllProjects();
		for (int i = 0; i < projects.length; i++) {
			IProject project = projects[i];
			Job.getJobManager().join(project.getName() + VALIDATOR_JOB_FAMILY,null);
		}
		Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD,null);
		Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD,null);
		Job.getJobManager().join(ValidationBuilder.FAMILY_VALIDATION_JOB,null);
	}
}
