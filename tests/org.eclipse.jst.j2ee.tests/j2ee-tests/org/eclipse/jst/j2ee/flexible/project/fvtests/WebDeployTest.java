/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.flexible.project.fvtests;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

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
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.IModuleFolder;
import org.eclipse.wst.server.core.model.IModuleResource;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public class WebDeployTest extends TestCase {

	private static String[] projectNames = new String[]{"TestDeploy","TestDeployUtil","TestDeployWeb","TestWebLibProject","TestExternalJar"}; //$NON-NLS-1$;
	private static Path zipFilePath = new Path("/TestData/WebDeployTests/WebDeployTest.zip"); //$NON-NLS-1$
	
	public static Test suite() {
		return new TestSuite(WebDeployTest.class);
	}
	public void testMembersDeployment() {
		ProjectUnzipUtil util = new ProjectUnzipUtil(getLocalPath(), projectNames);
		util.createProjects();
		
		//hari: we're getting the web project here
		IProject project = ProjectUtilities.getProject(projectNames[2]);
		IVirtualComponent component = ComponentCore.createComponent(project);
		J2EEFlexProjDeployable deployable = new J2EEFlexProjDeployable(project, component);
		try {
			IModuleResource[] members = deployable.members();
			assertTrue(members.length==2);
			
			for (int i=0; i<members.length; i++) {
				String name = members[i].getName();
				if (name.equals("META-INF")) {
					IModuleResource manifest = ((IModuleFolder)members[i]).members()[0];
					assertTrue(manifest.getModuleRelativePath().toString().equals("META-INF"));
					assertTrue(manifest.getName().equals("MANIFEST.MF"));
					
				} else if (name.equals("WEB-INF")) {
					IModuleResource[] webInf = ((IModuleFolder)members[i]).members();
					assertTrue(webInf.length==3);
					for (int j=0; j<webInf.length; j++) {
						IModuleResource webResource = webInf[j];
						assertTrue(webResource.getModuleRelativePath().toString().equals("WEB-INF"));
						if (webResource.getName().equals("lib")){
							IModuleResource[] webresMembers = ((IModuleFolder)webResource).members();
							{
								//finds only smokeEJB.jar here...
								assertTrue(webresMembers.length==0);
//								//Check that the names match the 3 names we expect
//								for (int k = 0; k < webresMembers.length; k++){
//									String localName = webresMembers[k].getName();
//									assertTrue	(localName.equals("SmokeEJB.jar") || localName.equals("AutoWorldEJB512.jar"));
//								}
							}
						}
					}
				} 
			}
			
			// get child modules on the deployable should return the utility modules which are not 
			// on the manifest but are web lib projects
			IModule[] childModules = deployable.getChildModules();
			assertEquals(3, childModules.length);
			List childURIs = Arrays.asList(new String[] {
					"WEB-INF/lib/SmokeEJB.jar",
					"WEB-INF/lib/TestWebLibProject.jar",
					"WEB-INF/lib/AutoWorldEJB512.jar",
			});
			
			for (int l = 0; l < childModules.length; l++){
				String uri = deployable.getURI(childModules[l]);
				if( !childURIs.contains(uri)) 
					fail("URI " + uri + " is not an expected child module uri result");
			}
			
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
