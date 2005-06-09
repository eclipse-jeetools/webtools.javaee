/*******************************************************************************
 * Copyright (c) 2002, 2003,2004 Eteration Bilisim A.S.
 * Naci Dai and others.
 * 
 * Parts developed under contract ref:FT/R&D/MAPS/AMS/2004-09-09/AL are 
 * Copyright France Telecom, 2004.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eteration Bilisim A.S. - initial API and implementation
 *     Naci Dai
 * For more information on eteration, please see
 * <http://www.eteration.com/>.
 ***************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.ant.internal.ui.launchConfigurations.AntJRETab;
import org.eclipse.ant.internal.ui.launchConfigurations.IAntLaunchConfigurationConstants;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletAnnotationPlugin;
import org.eclipse.ui.externaltools.internal.model.IExternalToolConstants;

public class AntLauncherUtility {

	private static final int MAX_TARGET_APPEND_LENGTH = 30;

	private URL url;

	private IPath output;

	private Properties properties;

	private Map templates;

	private boolean useLauncher = false;

	public AntLauncherUtility(URL templateUrl, IPath outputFolder,
			Properties properties, Map templates) {
		this.url = templateUrl;
		this.output = outputFolder;
		this.properties = properties;
		this.templates = templates;
	}

	public String getBuildFile() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(url
				.openStream()));
		StringBuffer buildFile = new StringBuffer(512);
		int c;
		do {
			c = reader.read();
			if (c != -1)
				buildFile.append((char) c);
		} while (c != -1);
		String build = buildFile.toString();
		Iterator keys = templates.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			build = build.replaceAll(key, (String) templates.get(key));
		}
		return build;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.server.core.model.IPublisher#publish(org.eclipse.wtp.server.core.resources.IModuleResource[],
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public IStatus[] launch(String target, IProgressMonitor monitor)
			throws Exception {
		String build = this.getBuildFile();

		if (build == null || build.length() < 1)
			return new IStatus[] { new Status(IStatus.ERROR,
					XDocletAnnotationPlugin.PLUGINID, 0, "AntBuildFileDoesNotExist",
					null) };

		IPath file = XDocletAnnotationPlugin.getDefault().getStateLocation().append(
				"tempAnt.xml");
		try {
			createNewFile(file.toFile(), build.getBytes());
		} catch (IOException e) {
		}

		runAnt(file, new String[] { target }, this.properties, monitor);

		// file.toFile().delete();
		return new IStatus[] { new Status(IStatus.OK,
				XDocletAnnotationPlugin.PLUGINID, 0, "Finished", null) };
	}

	private void runAnt(IPath buildFile, String[] targets,
			Properties properties, final IProgressMonitor monitor)
			throws Exception {
		IPath propfile = XDocletAnnotationPlugin.getDefault().getStateLocation()
				.append("build.properties");
		File buildProp = propfile.toFile();
		if (buildProp.exists())
			buildProp.delete();
		FileOutputStream fileOutputStream = new FileOutputStream(propfile
				.toFile());
		properties.store(fileOutputStream,
				"WTP BUILD FILE PROPERTIES- UPDATED:" + (new Date()));
		fileOutputStream.close();

		if (useLauncher)
			runUsingLauncher(buildFile, targets, monitor);
		else {
			AntRunner antRunner = new AntRunner();

			antRunner.setBuildFileLocation(buildFile.toString());
			antRunner.setExecutionTargets(targets);
			antRunner.run(monitor);
		}

	}

	/**
	 * @param buildFile
	 * @param targets
	 * @param monitor
	 * @throws CoreException
	 */
	private void runUsingLauncher(IPath buildFile, String[] targets,
			final IProgressMonitor monitor) throws CoreException {
		ILaunchConfiguration configuration = createDefaultLaunchConfiguration(
				buildFile, null, targets[0]);
		DebugUITools.buildAndLaunch(configuration, "run", monitor);
	}

	public ILaunchConfiguration createDefaultLaunchConfiguration(
			IPath filePath, IProject project, String targetAttribute)
			throws CoreException {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType type = manager
				.getLaunchConfigurationType(IAntLaunchConfigurationConstants.ID_ANT_LAUNCH_CONFIGURATION_TYPE);

		String projectName = project != null ? project.getName() : null;
		String name = getNewLaunchConfigurationName(filePath, projectName,
				targetAttribute);
		ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null,
				name);
		workingCopy.setAttribute(IExternalToolConstants.ATTR_LOCATION, filePath
				.toString());
		workingCopy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_CLASSPATH_PROVIDER,
				"org.eclipse.ant.ui.AntClasspathProvider"); //$NON-NLS-1$
		workingCopy.setAttribute(
				IAntLaunchConfigurationConstants.ATTR_ANT_TARGETS,
				targetAttribute);

		// set default for common settings
		CommonTab tab = new CommonTab();
		tab.setDefaults(workingCopy);
		tab.dispose();

		AntJRETab jreTab = new AntJRETab();
		jreTab.setDefaults(workingCopy);
		jreTab.dispose();
		workingCopy.setAttribute(IDebugUIConstants.ATTR_LAUNCH_IN_BACKGROUND,
				false);

		return workingCopy.doSave();
	}

	/**
	 * Returns a unique name for a copy of the given launch configuration with
	 * the given targets. The name seed is the same as the name for a new launch
	 * configuration with " [targetList]" appended to the end.
	 * 
	 * @param config
	 * @param targetAttribute
	 * @return
	 */
	public String getNewLaunchConfigurationName(IPath filePath,
			String projectName, String targetAttribute) {
		StringBuffer buffer = new StringBuffer();
		if (projectName != null) {
			buffer.append(projectName);
			buffer.append(' ');
			buffer.append(filePath.lastSegment());
		} else {
			buffer.append(filePath.toOSString());
		}

		if (targetAttribute != null) {
			buffer.append(" ["); //$NON-NLS-1$
			if (targetAttribute.length() > MAX_TARGET_APPEND_LENGTH + 3) {
				// The target attribute can potentially be a long,
				// comma-separated list
				// of target. Make sure the generated name isn't extremely long.
				buffer.append(targetAttribute.substring(0,
						MAX_TARGET_APPEND_LENGTH));
				buffer.append("..."); //$NON-NLS-1$
			} else {
				buffer.append(targetAttribute);
			}
			buffer.append(']');
		}

		String name = DebugPlugin.getDefault().getLaunchManager()
				.generateUniqueLaunchConfigurationNameFrom(buffer.toString());
		return name;
	}

	public boolean createNewFile(File f, byte[] content) throws IOException {
		if (f != null) {
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(f);
				fos.write(content);
			} finally {
				if (fos != null) {
					fos.close();
				}
			}
			return true;
		}
		return false;
	}

	public void setUseLauncher(boolean useLauncher) {
		this.useLauncher = useLauncher;
	}
}
