package org.eclipse.jst.j2ee.flexible.project.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit.Test0_7Workspace;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;

public class Migrate07EJBTest extends TestCase {
	
	private IProject ejbProject,webProject;
	public Migrate07EJBTest() {
		super();

		if (Test0_7Workspace.init()) {
			ejbProject = Test0_7Workspace.getTargetProject("MyEarEJB");
			webProject = Test0_7Workspace.getTargetProject("TestWeb");
		} else {
			fail();

		}
	}

	private void pass() {
		assertTrue(true);
	}
	public String getFacetFromProject(IProject aProject) {
		return J2EEProjectUtilities.getJ2EEProjectType(aProject);
	}

	public void testMigrate() {
		EJBArtifactEdit ejbedit = null;
		WebArtifactEdit webEdit = null;
		
		
			//Run full build to start migration
			try {
				ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, null);
			} catch (CoreException e) {
			}
			
			ISchedulingRule rule= ResourcesPlugin.getWorkspace().getRuleFactory().buildRule();
			IJobManager manager= Platform.getJobManager();
			try {
				manager.beginRule(rule, null);

				try {
				ejbedit = EJBArtifactEdit.getEJBArtifactEditForRead(ejbProject);
				EJBJar ejb = ejbedit.getEJBJar();
				assertTrue(ejb != null);
				webEdit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
				WebApp web = webEdit.getWebApp();
				assertTrue(web != null);
				} finally {
					if (ejbedit != null) {
						ejbedit.dispose();
					}
					if (webEdit != null) {
						webEdit.dispose();
					}
				}

			} finally {
				manager.endRule(rule);
			}
	}
	

}
