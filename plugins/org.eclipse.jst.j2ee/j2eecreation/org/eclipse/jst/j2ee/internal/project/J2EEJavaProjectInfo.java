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
package org.eclipse.jst.j2ee.internal.project;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.internal.plugin.JavaProjectInfo;
import org.eclipse.wst.server.core.IRuntime;

/**
 * This class stores the info required for creating a new J2EE project; not all info will apply to
 * all kinds of projects
 */
public class J2EEJavaProjectInfo extends JavaProjectInfo {
	protected static final String SRCROOT_VAR = "JRE_SRCROOT"; //$NON-NLS-1$
	protected IProject project;
	protected String jdkRTJarPath;
	protected String projectName;
	protected IPath projectLocation;
	protected String javaOutputPath;
	protected IClasspathEntry[] classpathEntries;
	protected boolean shouldInitializeDefaultClasspath = true;
	protected String natureId;
	protected IRuntime serverTarget;
	protected int moduleVersion;

	/**
	 * EJBProjectInfo constructor comment.
	 */
	public J2EEJavaProjectInfo() {
		super();
	}

	/**
	 * EJBProjectInfo constructor comment.
	 */
	public J2EEJavaProjectInfo(IProject project) {
		super();
		setProject(project);
	}

	public IClasspathEntry[] calculateServerClasspathEntries() throws JavaModelException {
		IJavaProject javaProject = getJavaProject();
		if (javaProject == null)
			return null;
		if (getProject().exists()) {
			//We don't need to remove a server target anyomre as it need to be there
			//ServerTargetManager.removeServerTarget(getProject(),null);
			IClasspathEntry[] ces = javaProject.getRawClasspath();
			if (ces.length > 0)
				addToClasspathEntries(ces);
		}
		addServerJdkRuntimeToClasspathEntries();
		addServerJarsToClasspathEntries();
		return classpathEntries;
	}

	/**
	 * Sets up the default classpath for this project
	 */
	protected void addDefaultToClasspathEntries() {
		addToClasspathEntries(computeDefaultJavaClasspath());
	}

	/**
	 * Set the corresponding jsp and servlet levels. Creation date: (11/09/00 10:05:24 AM)
	 */
	public void setJ2EEVersion(int newVersion) {
	}


	/**
	 * add the source folder to classpath dir (IClasspathEntry.CPE_SOURCE)
	 */
	public void addJavaSourceToClasspathEntries() {
		IPath sourceClassPath = new Path(getFullSourcePath());
		addToClasspathEntries(new IClasspathEntry[]{JavaCore.newSourceEntry(sourceClassPath)});
	}

	public boolean addJdkJarToClasspathEntries(String jdkJarFullPathName) {

		IJavaProject javaProject = getJavaProject();
		if (javaProject == null)
			return false;

		IClasspathEntry[] entry = new IClasspathEntry[1];
		Path path = new Path(jdkJarFullPathName);
		if (!path.toFile().exists()) {
			org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(J2EECreationResourceHandler.getString("JdkJarFileDoesNotExist_UI", new Object[]{jdkJarFullPathName})); //$NON-NLS-1$
			return false;
		}

		entry[0] = JavaCore.newLibraryEntry(path, null, null);
		addToClasspathEntries(entry);

		return true;
	}

	/**
	 * add rt.jar form the server.jdk plugin
	 */
	public void addServerJdkRuntimeToClasspathEntries() {
		addToClasspathEntries(getServerJDKClasspathEntries());
	}

	public IClasspathEntry[] getServerJDKClasspathEntries() {
		List list = new ArrayList(4);
		//TODO This class should be deleted.
		//		if (isJ2EE13()
		//			|| !org.eclipse.jst.j2ee.internal.internal.plugin.J2EEPlugin.hasDevelopmentRole()) {
		//			list.add(JavaCore.newVariableEntry(new
		// Path(IEJBNatureConstants.SERVERJDK_50_PLUGINDIR_VARIABLE + "/jre/lib/rt.jar"),
		// //$NON-NLS-1$
		//			new Path(IEJBNatureConstants.SERVERJDK_50_PLUGINDIR_VARIABLE + "/src.jar"), //$NON-NLS-1$
		//			new Path(IEJBNatureConstants.SERVERJDK_SRCROOT_VARIABLE))); //$NON-NLS-1$
		//		} else {
		//			list.add(JavaCore.newVariableEntry(new
		// Path(IEJBNatureConstants.SERVERJDK_PLUGINDIR_VARIABLE + "/jre/lib/rt.jar"), //$NON-NLS-1$
		//			new Path(IEJBNatureConstants.SERVERJDK_PLUGINDIR_VARIABLE + "/src.jar"), //$NON-NLS-1$
		//			new Path(IEJBNatureConstants.SERVERJDK_SRCROOT_VARIABLE))); //$NON-NLS-1$
		//		}
		return (IClasspathEntry[]) list.toArray(new IClasspathEntry[list.size()]);
	}

	/**
	 * Adds entries to the class path for this project
	 */
	public void addToClasspathEntries(IClasspathEntry[] entries) {

		java.util.List list = new ArrayList(10);
		// add the existing ones if any
		if (classpathEntries != null)
			list.addAll(Arrays.asList(classpathEntries));

		// add the new ones
		list.addAll(Arrays.asList(entries));

		// convert
		classpathEntries = new IClasspathEntry[list.size()];
		classpathEntries = (IClasspathEntry[]) list.toArray(classpathEntries);

	}

	public boolean addVariableJarToClasspathEntries(String fullPath) {

		IJavaProject javaProject = getJavaProject();
		if (javaProject == null)
			return false;

		IClasspathEntry[] entry = new IClasspathEntry[1];
		entry[0] = JavaCore.newVariableEntry(new Path(fullPath), null, null);
		addToClasspathEntries(entry);
		return true;
	}

	public boolean addVariableJarToClasspathEntriesWithAttachments(String fullPath, String srcPath, String srcRoot) {

		IJavaProject javaProject = getJavaProject();
		if (javaProject == null)
			return false;

		IClasspathEntry[] entry = new IClasspathEntry[1];
		entry[0] = JavaCore.newVariableEntry(new Path(fullPath), new Path(srcPath), new Path(srcRoot));
		addToClasspathEntries(entry);
		return true;
	}

	/**
	 * Return the default classpath for projects of this kind; subclasses should override for
	 * setting up new projects
	 */
	protected IClasspathEntry[] computeDefaultJavaClasspath() {
		IJavaProject javaProject = getJavaProject();
		if (javaProject == null)
			return null;
		addJavaSourceToClasspathEntries();
		if (serverTarget == null) {
			addServerJdkRuntimeToClasspathEntries();
			addServerJarsToClasspathEntries();
		}
		return classpathEntries;
	}

	/**
	 * Sublcasses have to overide this method to set the server jars
	 */
	public void addServerJarsToClasspathEntries() {

	}

	/**
	 * Creates a project handle with a specified path. The project resource should <b>not </b> be
	 * created concretely here;
	 */
	public IProject createProjectHandle(IPath projectPath) {
		return getWorkspace().getRoot().getProject(projectPath.segment(0));
	}

	public IClasspathEntry[] getClasspathEntries() {
		if (classpathEntries == null)
			initializeClasspathEntries();
		return classpathEntries;
	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDefaultContextRoot() {
		return null;
	}

	/**
	 * Subclasses should override as necessary
	 */
	protected String getDefaultJavaOutputPath() {
		return DEFAULT_JAVA_OUTPUT_PATH;
	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDefaultUri() {
		return projectName.replace(' ', '_') + ".jar"; //$NON-NLS-1$
	}

	/**
	 * Get the java output folder for the receiver, in the form of /project/ <output folder>
	 * 
	 * @return java.lang.String
	 */
	public String getFullJavaOutputPath() {
		return Path.ROOT.append(getProjectPath()).append(getJavaOutputPath()).toString();
	}

	/**
	 * Get the module path folder for the receiver in the form of /project/modulepath
	 * 
	 * @return java.lang.String
	 */
	protected String getFullSourcePath() {
		return Path.ROOT.append(getProjectPath()).append(getSourcePath()).toString();
	}

	/**
	 * Returns the project relative path of the java build destination
	 */
	public java.lang.String getJavaOutputPath() {
		if (javaOutputPath == null)
			javaOutputPath = getDefaultJavaOutputPath();
		return javaOutputPath;
	}

	/**
	 * @param i
	 */
	public void setModuleVersion(int version) {
		moduleVersion = version;
	}

	public IJavaProject getJavaProject() {
		// needed to get an IJavaProject to create classpaths from.
		IProject aProject = getProject();
		if (aProject == null)
			aProject = getWorkspace().getRoot().getProject(getProjectName());

		return JavaCore.create(aProject);
	}

	/**
	 * Return the absolute path of the default jdk rt.jar to use for this project
	 */
	public java.lang.String getJdkRTJarPath() {
		return jdkRTJarPath;
	}

	/**
	 * Return the project being created; checks the workspace for an existing project
	 */
	public IProject getProject() {
		if (project == null) {
			IProject aProject = getWorkspace().getRoot().getProject(getProjectName());
			if (aProject.exists())
				project = aProject;
		}
		return project;
	}

	/**
	 * Return the location of the project in the file system.
	 * 
	 * @return org.eclipse.core.runtime.IPath
	 */
	public IPath getProjectLocation() {
		return projectLocation;
	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getProjectName() {
		if (projectName == null && project != null)
			projectName = project.getName();
		return projectName;
	}

	public IPath getProjectPath() {
		return new Path(getProjectName());
	}

	public IWorkspace getWorkspace() {
		return org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.getWorkspace();
	}

	/**
	 * Lazy initialization - useGetClasspathEntries
	 */
	protected void initializeClasspathEntries() {
		if (shouldInitializeDefaultClasspath())
			computeDefaultJavaClasspath();
		else
			classpathEntries = new IClasspathEntry[0];
	}

	/**
	 * Answer false by default
	 * 
	 * @deprecated - Use getModuleVersion() with J2EEVersionConstants
	 */
	protected boolean isJ2EE13() {
		return false;
	}

	public IProject primGetProject() {
		return project;
	}

	public void removeClasspathEntry(IClasspathEntry entry) {
		if (entry == null)
			return;

		List list = new ArrayList(Arrays.asList(getClasspathEntries()));
		list.remove(entry);
		classpathEntries = (IClasspathEntry[]) list.toArray(new IClasspathEntry[list.size()]);
	}

	/**
	 * Insert the method's description here. Creation date: (11/10/00 10:09:58 AM)
	 * 
	 * @param newClassPathEntries
	 *            org.eclipse.jdt.core.api.IClasspathEntry
	 */
	public void setClasspathEntries(IClasspathEntry[] newClasspathEntries) {
		classpathEntries = newClasspathEntries;
	}

	public void setJavaOutputPath(String path) {
		javaOutputPath = path;
	}

	public void setJdkRTJarPath(String path) {
		jdkRTJarPath = path;
	}

	public void setProject(IProject aProject) {
		project = aProject;
	}

	/**
	 * Set the location in the file system that the project is to be created.
	 * 
	 * @param newProjectLocation
	 *            IPath
	 */
	public void setProjectLocation(IPath newProjectLocation) {
		projectLocation = newProjectLocation;
	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @param newProjectName
	 *            java.lang.String
	 */
	public void setProjectName(java.lang.String newProjectName) {
		if (projectName != newProjectName)
			setClasspathEntries(null);
		projectName = newProjectName;
	}

	public void setShouldInitializeDefaultClasspath(boolean value) {
		shouldInitializeDefaultClasspath = value;
	}

	public boolean shouldInitializeDefaultClasspath() {
		return shouldInitializeDefaultClasspath;
	}

	/**
	 * Gets the natureId.
	 * 
	 * @return Returns a String
	 */
	public String getNatureId() {
		if (natureId == null)
			return getDefaultNatureId();
		return natureId;
	}

	/**
	 * returns the correct nature id string based on the J2EE spec level being used
	 */
	public String getDefaultNatureId() {
		return null;
	}

	/**
	 * Sets the natureId.
	 * 
	 * @param natureId
	 *            The natureId to set
	 */
	public void setNatureId(String natureId) {
		this.natureId = natureId;
	}

	/**
	 * Get the correct WAS classpath variable based on the J2EE version.
	 */
	protected String getWASPluginVariable() {
		//TODO This class needs to be deleted.
		//	if (isJ2EE13()
		//		|| !org.eclipse.jst.j2ee.internal.internal.plugin.J2EEPlugin.hasDevelopmentRole())
		//		return IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE;
		//	else
		//		return IEJBNatureConstants.WAS_PLUGINDIR_VARIABLE;
		return ""; //$NON-NLS-1$
	}

	public int getModuleVersion() {
		return moduleVersion;
	}

	/**
	 * @return
	 */
	public IRuntime getServerTarget() {
		return serverTarget;
	}

	/**
	 * @param target
	 */
	public void setServerTarget(IRuntime target) {
		serverTarget = target;
	}
}