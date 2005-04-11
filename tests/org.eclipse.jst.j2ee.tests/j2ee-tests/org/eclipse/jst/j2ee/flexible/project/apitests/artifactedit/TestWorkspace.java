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
import org.eclipse.emf.common.util.URI;
import org.eclipse.etools.common.test.apitools.ProjectUnzipUtil;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;



public class TestWorkspace {

	public static final String EAR_PROJECT_NAME = "EARArtifactEditTest"; //$NON-NLS-1$
	public static final String EAR_MODULE_NAME = "EARArtifactModule";//$NON-NLS-1$
	public static final String EAR_PROJECT_VERSION = "1.4"; //$NON-NLS-1$
	public static final String EAR_DD_RESOURCE_URI = "platform:/resource/EARArtifactModule/EARArtifactModule/META-INF/application.xml"; //$NON-NLS-1$
	public static final String EAR_DD_XMI_RESOURCE_URI = "platform:/resource//EARArtifactEditTest/EARArtifactModule/ejbModule/META-INF/ejb-jar.xmi";//$NON-NLS-1$
	public static final URI EAR_MODULE_URI = URI.createURI("module:/resource/EARArtifactEditTest/EARArtifactModule");

	public static final String APP_CLIENT_PROJECT_NAME = "ApplicationClientArtifactEditTest"; //$NON-NLS-1$
	public static final String APP_CLIENT_MODULE_NAME = "ApplicationClientModule"; //$NON-NLS-1$
	public static final String APP_CLIENT_PROJECT_VERSION = "1.4";//$NON-NLS-1$
	public static final String APP_CLIENT_DD_RESOURCE_URI = "platform:/resource/ApplicationClientModule/ApplicationClientModule/appClientModule/META-INF/application-client.xml";//$NON-NLS-1$
	public static final String APP_CLIENT_DD_XMI_RESOURCE_URI = "platform:/resource/ApplicationClientModule/ApplicationClientModule/appClientModule/META-INF/application-client.xmi";//$NON-NLS-1$
	public static final URI APP_CLIENT_MODULE_URI = URI.createURI("module:/resource/ApplicationClientArtifactEditTest/ApplicationClientModule");//$NON-NLS-1$

	public static final String EJB_PROJECT_NAME = "EJBArtifactEditTest"; //$NON-NLS-1$
	public static final String EJB_MODULE_NAME = "EJBArtifactEditModule";//$NON-NLS-1$
	public static final String EJB_PROJECT_VERSION = "2.1"; //$NON-NLS-1$
	public static final String EJB_DD_RESOURCE_URI = "platform:/resource/EARArtifactEditTest/EARArtifactModule/META-INF/application.xml";//$NON-NLS-1$
	public static final String EJB_DD_XMI_RESOURCE_URI = "platform:/resource/EARArtifactEditTest/EARArtifactModule/META-INF/application.xmi";//$NON-NLS-1$
	public static final URI EJB_MODULE_URI = URI.createURI("module:/resource/EARArtifactEditTest/EARArtifactModule");

	public static final String WEB_PROJECT_NAME = "WebArtifactEditTest"; //$NON-NLS-1$
	public static final String WEB_MODULE_NAME = "WebArtifactEditModule"; //$NON-NLS-1$
	public static final String WEB_PROJECT_VERSION = "2.4";//$NON-NLS-1$
	public static final String WEB_DD_RESOURCE_URI = "platform:/resource/WebArtifactEditModule/WebArtifactEditModule/WebContent/WEB-INF/web.xml";//$NON-NLS-1$
	public static final String WEB_DD_XMI_RESOURCE_URI = "platform:/resource/WebArtifactEditModule/WebArtifactEditModule/WebContent/WEB-INF/web.xmi";//$NON-NLS-1$
	public static final URI WEB_MODULE_URI = URI.createURI("module:/resource/WebArtifactEditTest/WebArtifactEditModule");
	public static final String WEB_SERVER_CONTEXT_ROOT = "WebContent";

	public static final String JCA_PROJECT_NAME = "JCAArtifactEditTest"; //$NON-NLS-1$
	public static final String JCA_MODULE_NAME = "JCAArtifactEditTestModule";//$NON-NLS-1$
	public static final String JCA_PROJECT_VERSION = "1.5"; //$NON-NLS-1$
	public static final String JCA_DD_RESOURCE_URI = "platform:/resource/JCAArtifactEditTestModule/JCAArtifactEditTestModule/connectorModule/META-INF/ra.xml";//$NON-NLS-1$                                           
	public static final URI JCA_MODULE_URI = URI.createURI("module:/resource/JCAArtifactEditTest/JCAArtifactEditModule");
   
	public static final String FVT_DESCRIPTION = "FVT Description Test";
	public static final String FVT_DISPLAY_NAME = "FVT DisplayName Test";
	public static final String FVT_LARGE_ICON = "fvtLargeIcon.gif";
	public static IPath workspaceLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation();
	public static String ARTIFACT_EDIT_FVT_RESOURCE_PATH_NAME = "/ArtifactEditFunctionTestResource.txt";
	public static IPath ARTIFACT_EDIT_FVT_RESOURCE_PATH = workspaceLocation.append(ARTIFACT_EDIT_FVT_RESOURCE_PATH_NAME);
		
	private static Path zipFilePath = new Path("/TestData/GenralArtifactTest/J2EEArtifactEditTestProjects.zip");
	private static String[] projectNames = new String[]{EAR_PROJECT_NAME, APP_CLIENT_PROJECT_NAME, EJB_PROJECT_NAME, WEB_PROJECT_NAME, JCA_PROJECT_NAME};
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
		IProject project = getTargetProject(APP_CLIENT_PROJECT_NAME);
		if (project.exists())
			return true;
		else
			return createProjects();

	}

	public boolean isValidWorkspace() {
		return isValidWorkspace;
	}
}
