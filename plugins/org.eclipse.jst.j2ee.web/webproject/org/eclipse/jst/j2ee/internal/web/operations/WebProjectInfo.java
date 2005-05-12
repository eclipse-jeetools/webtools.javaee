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
package org.eclipse.jst.j2ee.internal.web.operations;



import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;


/**
 * The WebProjectInfo is used to set up the Java project info like the J2EE specification level,java
 * class path,project nature project location, associated EAR project location, for a new Web
 * project creation
 * 
 * @deprecated
 * Use
 * <p>
 * 		WebArtifactEdit
 * </p>
 */

public class WebProjectInfo extends org.eclipse.jst.j2ee.internal.project.J2EEJavaProjectInfo implements IWebProjectWizardInfo {

	private String fContextRoot = null;

	public boolean fJ2EEWebProject = true;

	public final static String J2EE_VERSION_1_2 = "J2EE_1_2"; //$NON-NLS-1$
	public final static String J2EE_VERSION_1_3 = "J2EE_1_3"; //$NON-NLS-1$
	public final static String J2EE_VERSION_1_4 = "J2EE_1_4"; //$NON-NLS-1$
	

	public static final String PROPERTY_EAR_PROJECT_NAME = "EAR name"; //$NON-NLS-1$
	public static final String PROPERTY_J2EE_VERSION = "J2EE level"; //$NON-NLS-1$
	public static final String PROPERTY_PROJECT_NAME = "Project name"; //$NON-NLS-1$
	public static final String PROPERTY_SERVER_TARGET = "Server Target"; //$NON-NLS-1$

	protected int fJSPLevel;
	protected int fServletLevel;
	protected IProject wtWebProject;
	protected String wtProjectName;
	protected IPath wtProjectLocation;
	protected String wtEarProjectName;
	protected String fWebContentName;
	protected String fJavaSourceName;
	protected IProject wtEarProject;
	protected IPath wtEarProjectLocation;
	protected boolean wtExampleProject = false;
	protected boolean synch = false;
	protected Vector wtFeatureIds = new Vector();

	protected PropertyChangeSupport listeners;

	/**
	 * Additional property Hashtable
	 */
	protected Hashtable wtPropertyTable = new Hashtable(10, 20);

	public WebProjectInfo() {
		super();
	}

	/**
	 * NOTE: Notification for all properties is not implemented yet.
	 * 
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String,
	 *      java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		if (listeners == null) {
			listeners = new PropertyChangeSupport(this);
		}
		listeners.addPropertyChangeListener(property, listener);
	}

	/**
	 * NOTE: Notification for all properties is not implemented yet.
	 * 
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String,
	 *      java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
	}

	public void addServerJarsToClasspathEntries() {
		// The jars to be added are different based on the level of J2EE supported
		// by the project.
		addToClasspathEntries(getWASClasspathEntries());
	}

	public IClasspathEntry[] getWASClasspathEntries() {
		List list = new ArrayList(4);
		//TODO This class needs to be deleted.
		//		if (!org.eclipse.jst.j2ee.internal.internal.plugin.J2EEPlugin.hasDevelopmentRole()) {
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/j2ee.jar"), null, null)); //$NON-NLS-1$
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/servletevent.jar"), null, null)); //$NON-NLS-1$
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/ivjejb35.jar"), null, null)); //$NON-NLS-1$
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/runtime.jar"), null, null)); //$NON-NLS-1$
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/pagelist.jar"), null, null)); //$NON-NLS-1$ GMR
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/webcontainer.jar"), null, null)); //$NON-NLS-1$ GMR
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/xalan.jar"), null, null)); //$NON-NLS-1$ GMR
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/als.jar"), null, null)); //$NON-NLS-1$ GMR
		//		} else if (isJ2EE13()) {
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/j2ee.jar"), null, null)); //$NON-NLS-1$
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/servletevent.jar"), null, null)); //$NON-NLS-1$
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/ivjejb35.jar"), null, null)); //$NON-NLS-1$
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE
		// + "/lib/runtime.jar"), null, null)); //$NON-NLS-1$
		//		} else {
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_PLUGINDIR_VARIABLE +
		// "/lib/j2ee.jar"), null, null)); //$NON-NLS-1$
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_PLUGINDIR_VARIABLE +
		// "/lib/webcontainer.jar"), null, null)); //$NON-NLS-1$
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_PLUGINDIR_VARIABLE +
		// "/lib/ivjejb35.jar"), null, null)); //$NON-NLS-1$
		//			list.add(JavaCore.newVariableEntry(new Path(IEJBNatureConstants.WAS_PLUGINDIR_VARIABLE +
		// "/lib/websphere.jar"), null, null)); //$NON-NLS-1$
		//		}

		return (IClasspathEntry[]) list.toArray(new IClasspathEntry[list.size()]);
	}

	/**
	 * Return the standard classpath for ejb project.
	 */
	protected IClasspathEntry[] computeDefaultJavaClasspath() {

		super.computeDefaultJavaClasspath();
		IJavaProject javaProject = getJavaProject();
		if (javaProject == null)
			return null;

		addWASJarsToClasspathEntries();
		return classpathEntries;
	}

	/**
	 * Return the context root.
	 * 
	 * @deprecated Use getContextRoot()
	 * @return java.lang.String
	 */
	public String getDefaultContextRoot() {
		return getContextRoot();
	}

	public String getContextRoot() {
		if (fContextRoot != null)
			return fContextRoot;
		return wtProjectName;
	}

	/**
	 * Subclasses should override as necessary
	 */
	protected String getDefaultJavaOutputPath() {

		StringBuffer buf = new StringBuffer(getWebContentName());
		buf.append(IPath.SEPARATOR);
		buf.append(IWebNatureConstants.INFO_DIRECTORY);
		buf.append(IPath.SEPARATOR);
		buf.append(IWebNatureConstants.CLASSES_DIRECTORY);
		return buf.toString();
	}

	protected String getDefaultSourcePath() {
		return getJavaSourceName();
	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDefaultUri() {
		return wtProjectName.replace(' ', '_') + ".war";//$NON-NLS-1$
	}

	public String[] getFeatureIds() {
		return (String[]) wtFeatureIds.toArray(new String[wtFeatureIds.size()]);
	}

	/**
	 * Insert the method's description here. Creation date: (10/31/2001 3:10:01 PM)
	 * 
	 * @return boolean
	 */
	public int getWebProjectType() {
		if (fJ2EEWebProject)
			return IWebNatureConstants.J2EE_WEB_PROJECT;

		return 0;
	}

	public boolean isJ2EEWebProject() {
		return fJ2EEWebProject;
	}

	public boolean isJSP11() {
		return fJSPLevel == J2EEVersionConstants.JSP_1_1_ID;
	}

	public boolean isServlet22() {
		return fServletLevel == J2EEVersionConstants.SERVLET_2_2;
	}

	/**
	 * @deprecated - Use getModuleVersion() with J2EEVersionConstants
	 */
	/**
	 * Set the corresponding jsp and servlet levels. Creation date: (11/09/00 10:05:24 AM)
	 */
	public void setJ2EEVersion(String newLevel) {
		if (newLevel.equals(J2EE_VERSION_1_2)) {
			fJSPLevel = J2EEVersionConstants.JSP_1_1_ID;
			fServletLevel = J2EEVersionConstants.SERVLET_2_2;
		} else if (newLevel.equals(J2EE_VERSION_1_3)) {
			fJSPLevel = J2EEVersionConstants.JSP_1_2_ID;
			fServletLevel = J2EEVersionConstants.SERVLET_2_3;
		} else {
			fJSPLevel = J2EEVersionConstants.JSP_2_0_ID;
			fServletLevel = J2EEVersionConstants.SERVLET_2_4;
		}
	}

	public boolean isJ2EE13() {
		return (isServlet22() && !isJSP11());
	}

	/**
	 * Is this project being created for an example. Creation date: (10/31/2001 3:10:01 PM)
	 * 
	 * @return boolean
	 */
	public boolean isWebExample() {
		return wtExampleProject;
	}

	public void setContextRoot(java.lang.String contextRoot) {
		fContextRoot = contextRoot;
	}

	public void addFeatureId(java.lang.String featureId) {
		wtFeatureIds.add(featureId);
	}

	public void removeFeatureId(java.lang.String featureId) {
		wtFeatureIds.remove(featureId);

	}

	public void setFeatureIds(java.lang.String[] featureIds) {
		wtFeatureIds = new Vector();
		for (int i = 0; i < featureIds.length; i++)
			wtFeatureIds.add(featureIds[i]);
	}


	/**
	 * Insert the method's description here. Creation date: (10/31/2001 3:10:01 PM)
	 * 
	 * @param newFIsStaticWebProject
	 *            boolean
	 */
	public void setWebProjectType(boolean j2eeWebProject) {
		fJ2EEWebProject = j2eeWebProject;
	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public int getJSPLevel() {
		return fJSPLevel;

	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public int getServletLevel() {
		return fServletLevel;
	}

	/**
	 * This method is required as a separate method so that there are no inconsistencies when firing
	 * notifications caused due to change in either the JSP level or the Servlet level (through the
	 * setJSPLevel or setServletLevel methods), but not both.
	 */
	protected void updateJ2EELevel(String newLevel) {
		if (newLevel.equals(J2EE_VERSION_1_2)) {
			fJSPLevel = J2EEVersionConstants.JSP_1_1_ID;
			fServletLevel = J2EEVersionConstants.SERVLET_2_2;
		} else if (newLevel.equals(J2EE_VERSION_1_3)) {
			fJSPLevel = J2EEVersionConstants.JSP_1_2_ID;
			fServletLevel = J2EEVersionConstants.SERVLET_2_3;
		} else {
			fJSPLevel = J2EEVersionConstants.JSP_2_0_ID;
			fServletLevel = J2EEVersionConstants.SERVLET_2_4;
		}

		String oldValue = isJ2EE13() ? J2EE_VERSION_1_2 : J2EE_VERSION_1_3;
		firePropertyChange(PROPERTY_J2EE_VERSION, oldValue, newLevel);
	}

	/**
	 * @see java.beans.PropertyChangeSupport#firePropertyChange(java.lang.String, java.lang.Object,
	 *      java.lang.Object)
	 */
	protected void firePropertyChange(String property, Object oldValue, Object newValue) {
		if (listeners != null) {
			listeners.firePropertyChange(property, oldValue, newValue);
		}
	}

	public String getJ2EELevel() {
		String level = J2EE_VERSION_1_3;
		if (!isJ2EE13()) {
			level = J2EE_VERSION_1_2;
		}
		return level;
	}

	/**
	 * The J2EE level and the individual Servlet and JSP levels are always in sync now.
	 * 
	 * Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public void setJSPLevel(int newLevel) {
		fJSPLevel = newLevel;

	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public void setServletLevel(int newLevel) {
		fServletLevel = newLevel;
	}

	/**
	 * Return an Object for the assocated properties or null
	 */
	public Object getProperty(String propertyName) {

		return wtPropertyTable.get(propertyName);
	}

	/**
	 * Set an Object for the assocated properties or null
	 */
	public void setProperty(String propertyName, Object value) {
		wtPropertyTable.put(propertyName, value);
		return;
	}

	/**
	 * Return the project being created; checks the workspace for an existing project
	 */
	public IProject getProject() {
		if (wtWebProject == null && getProjectName() != null) {
			IProject aProject = getWorkspace().getRoot().getProject(getProjectName());
			if (aProject.exists())
				wtWebProject = aProject;
		}
		return wtWebProject;
	}

	/**
	 * Return the location of the project in the file system.
	 * 
	 * @return org.eclipse.core.runtime.IPath
	 */
	public IPath getProjectLocation() {
		return wtProjectLocation;
	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getProjectName() {
		if (wtProjectName == null)
			if (wtWebProject != null)
				wtProjectName = wtWebProject.getName();
		return wtProjectName;
	}

	public IPath getProjectPath() {
		return new Path(getProjectName());
	}

	/**
	 * Return the project being created; checks the workspace for an existing project
	 */
	public IProject getEARProject() {
		wtEarProject = null;
		if (!((getEARProjectName().trim()).length() == 0)) {
			IProject aProject = getWorkspace().getRoot().getProject(getEARProjectName());
			if (aProject.exists())
				wtEarProject = aProject;
		}

		return wtEarProject;
	}

	/**
	 * Return the location of the project in the file system.
	 * 
	 * @return org.eclipse.core.runtime.IPath
	 */
	public IPath getEARProjectLocation() {
		return wtEarProjectLocation;
	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getEARProjectName() {
		return wtEarProjectName;
	}

	public IPath getEARProjectPath() {
		return new Path(getEARProjectName());
	}

	public void setProject(IProject aProject) {
		fJSPLevel = getJSPVersion();
		fServletLevel = getServletVersion();
		wtWebProject = aProject;
		super.setProject(aProject);
	}
	
	protected int getJSPVersion() {
		WebArtifactEdit webEdit = null;
		try {
			//TODO migrate to flex projects
			//webEdit = (WebArtifactEdit) StructureEdit.getFirstArtifactEditForRead(project);
			if (webEdit != null)
				return webEdit.getJSPVersion();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return 0;
	}
	
	protected int getServletVersion() {
		WebArtifactEdit webEdit = null;
		try {
			//TODO migrate to flex projects
			//webEdit = (WebArtifactEdit) StructureEdit.getFirstArtifactEditForRead(project);
			if (webEdit != null)
				return webEdit.getServletVersion();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return 0;
	}

	/**
	 * Set the location in the file system that the project is to be created.
	 * 
	 * @param newProjectLocation
	 *            IPath
	 */
	public void setProjectLocation(IPath newProjectLocation) {
		wtProjectLocation = newProjectLocation;
	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @param newProjectName
	 *            java.lang.String
	 */
	public void setProjectName(java.lang.String newProjectName) {
		if ((wtProjectName == null && newProjectName != null) || (wtProjectName != null && !wtProjectName.equals(newProjectName))) {
			setClasspathEntries(null);
		}
		wtProjectName = newProjectName;
	}

	public void setEARProject(IProject aProject) {
		wtEarProject = aProject;
	}

	/**
	 * Set the location in the file system that the project is to be created.
	 * 
	 * @param newProjectLocation
	 *            IPath
	 */
	public void setEARProjectLocation(IPath newProjectLocation) {
		wtEarProjectLocation = newProjectLocation;
	}

	/**
	 * Insert the method's description here. Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @param newProjectName
	 *            java.lang.String
	 */
	public void setEARProjectName(java.lang.String newProjectName) {
		if ((newProjectName != null && !newProjectName.equals(wtEarProjectName)) || (newProjectName == null && wtEarProjectName != null)) {
			//do nothing for now
		}
		wtEarProjectName = newProjectName;
	}


	/**
	 * Insert the method's description here. Creation date: (10/31/2001 3:10:01 PM)
	 * 
	 * @param newFIsStaticWebProject
	 *            boolean
	 */
	public void setWebExample(boolean exampleProject) {
		wtExampleProject = exampleProject;
	}

	/**
	 * @see J2EEJavaProjectInfo#getJavaProject()
	 */
	public IJavaProject getJavaProject() {
		return super.getJavaProject();
	}


	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.web.operations.IWebProjectWizardInfo#getWebContentName()
	 */
	public String getWebContentName() {
		if (fWebContentName == null)
			fWebContentName = isJ2EEWebProject() ? J2EEPlugin.getDefault().getJ2EEPreferences().getJ2EEWebContentFolderName() : J2EEPlugin.getDefault().getJ2EEPreferences().getStaticWebContentFolderName();
		return fWebContentName;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.web.operations.IWebProjectWizardInfo#setWebContentName(String)
	 */
	public void setWebContentName(String name) {
		fWebContentName = name;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.web.operations.IWebProjectWizardInfo#getJavaSourceName()
	 */
	public String getJavaSourceName() {
		if (fJavaSourceName == null)
			fJavaSourceName = J2EEPlugin.getDefault().getJ2EEPreferences().getJavaSourceFolderName();
		return fJavaSourceName;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.web.operations.IWebProjectWizardInfo#setJavaSourceName(String)
	 */
	public void setJavaSourceName(String name) {
		fJavaSourceName = name;
	}

	/**
	 * In addition to setting the server target, this method also sets the server target type.
	 * WebProjectInfo clients don't need to worry about setting the target type.
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EEJavaProjectInfo#setServerTarget(com.ibm.etools.server.target.IServerTarget)
	 */
	/*
	 * public void setServerTarget(IServerTarget target) { if( (target != null &&
	 * !target.equals(getServerTarget())) || (target == null && getServerTarget() != null) ){
	 * IServerTarget oldTarget = getServerTarget(); super.setServerTarget(target);
	 * 
	 * ITargetType targetType = null; if( getServerTarget() != null ){ Iterator iterator =
	 * getServerTarget().getTargets().iterator(); while (iterator.hasNext()) { ITargetType type =
	 * (ITargetType) iterator.next(); if (IServerTargetConstants.WEB_TYPE.equals(type.getId())){
	 * targetType = type; break; } } } setServerTargetType(targetType);
	 * 
	 * firePropertyChange(PROPERTY_SERVER_TARGET, oldTarget, target); } }
	 */
	public void setSynchronizeWLPs(boolean synch) {
		this.synch = synch;
	}

	public boolean getSynchronizeWLPs() {
		return synch;
	}

	protected void addWASJarsToClasspathEntries() {
		// The jars to be added are different based on the level of J2EE supported
		// by the project.
		addToClasspathEntries(getWASClasspathEntries());
	}

}