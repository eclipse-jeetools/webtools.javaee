/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;


import java.io.IOException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.etools.common.test.apitools.ProjectUnzipUtil;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;



public class Test0_7Workspace {

	
	public static IPath workspaceLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation();
	public static String ARTIFACT_EDIT_FVT_RESOURCE_PATH_NAME = "/ArtifactEditFunctionTestResource.txt";
	public static IPath ARTIFACT_EDIT_FVT_RESOURCE_PATH = workspaceLocation.append(ARTIFACT_EDIT_FVT_RESOURCE_PATH_NAME);
		
	private static Path zipFilePath = new Path("/TestData/GenralArtifactTest/TEST0_7.zip");
	private static String[] projectNames = new String[]{".JETEmitters", "MyEar", "MyEarClient", "MyEarConnector", "MyEarEJB","TestWeb","TestWebEAR"};
	private boolean isValidWorkspace;
	



	public static final String META_INF = "META-INF"; //$NON-NLS-1$
	public static final String WEB_INF = "WEB-INF"; //$NON-NLS-1$

	public static IProject getTargetProject(String projectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}

	public static boolean createProjects() {
		IPath localZipPath = getLocalPath();
		ProjectUnzipUtil util = new ProjectUnzipUtil(localZipPath, projectNames);
		return util.createProjects();
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

	public static boolean init() {
		// revisit
		IProject project = getTargetProject("MyEar");
		if (project.exists())
			return true;
		else
			return createProjects();

	}

	public boolean isValidWorkspace() {
		return isValidWorkspace;
	}
}
