package org.eclipse.jst.j2ee.flexible.project.fvtests;

import java.io.IOException;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.etools.common.test.apitools.ProjectUnzipUtil;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.server.core.internal.ModuleFolder;
import org.eclipse.wst.server.core.model.IModuleResource;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public class J2EEFlexProjDeployablePerfTest extends TestCase {

	private static String[] projectNames = new String[]{"war"}; //$NON-NLS-1$;
	private static Path zipFilePath = new Path("/TestData/GenralArtifactTest/perfWAR.zip"); //$NON-NLS-1$
	
	public static Test suite() {
		return new TestSuite(J2EEFlexProjDeployablePerfTest.class);
	}
	public void testMembersPerformance() {
		ProjectUnzipUtil util = new ProjectUnzipUtil(getLocalPath(), projectNames);
		util.createProjects();
		IProject project = ProjectUtilities.getProject(projectNames[0]);
		IVirtualComponent component = ComponentCore.createComponent(project);
		J2EEFlexProjDeployable deployable = new J2EEFlexProjDeployable(project, component);
		try {
			IModuleResource[] members = deployable.members();
			assertTrue(members.length==3);
			int verified = 0;
			for (int i=0; i<members.length; i++) {
				String name = members[i].getName();
				if (name.equals("META-INF")) {
					IModuleResource manifest = ((ModuleFolder)members[i]).members()[0];
					assertTrue(manifest.getModuleRelativePath().toString().equals("META-INF"));
					assertTrue(manifest.getName().equals("MANIFEST.MF"));
					verified++;
				} else if (name.equals("WEB-INF")) {
					IModuleResource[] webInf = ((ModuleFolder)members[i]).members();
					assertTrue(webInf.length==2);
					for (int j=0; j<webInf.length; j++) {
						IModuleResource webResource = webInf[j];
						assertTrue(webResource.getModuleRelativePath().toString().equals("WEB-INF"));
						assertTrue(webResource.getName().equals("web.xml") || webResource.getName().equals("classes"));
					}
					verified++;
				} else if (name.equals("pkg0")) {
					assertTrue(((ModuleFolder)members[i]).members().length>0);
					verified++;
				}
			}
			assertTrue(verified==3);
		} catch (CoreException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	private static IPath getLocalPath() {
		URL url = HeadlessTestsPlugin.getDefault().find(zipFilePath);
		try {
			url = Platform.asLocalURL(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Path(url.getPath());
	}
}
