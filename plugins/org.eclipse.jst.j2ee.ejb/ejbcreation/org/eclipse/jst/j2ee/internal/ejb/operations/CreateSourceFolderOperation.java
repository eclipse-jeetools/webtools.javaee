/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.operations;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.ejb.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.framework.operation.WTPOperation;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class CreateSourceFolderOperation extends WTPOperation {
	private String folderName;
	private EJBNatureRuntime ejbNature;

	public CreateSourceFolderOperation(String newFolderName, EJBNatureRuntime anEJBNature) {
		setFolderName(newFolderName);
		setEjbNature(anEJBNature);
	}

	/**
	 * Gets the folderName.
	 * 
	 * @return Returns a String
	 */
	protected String getFolderName() {
		return folderName;
	}

	/**
	 * Sets the folderName.
	 * 
	 * @param newFolderName
	 *            The folderName to set
	 */
	protected void setFolderName(String newFolderName) {
		folderName = newFolderName;
	}

	/**
	 * Gets the ejbNature.
	 * 
	 * @return Returns a EJBNatureRuntime
	 */
	protected EJBNatureRuntime getEjbNature() {
		return ejbNature;
	}

	/**
	 * Sets the ejbNature.
	 * 
	 * @param ejbNature
	 *            The ejbNature to set
	 */
	protected void setEjbNature(EJBNatureRuntime ejbNature) {
		this.ejbNature = ejbNature;
	}

	/*
	 * @see HeadlessJ2EEOperation#execute(IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		IFolder folder = createFolder();
		if (folder != null && folder.exists())
			addSourceClasspathEntry(folder, monitor);
	}

	/**
	 * Actually create a new folder in the Java project for the EJB Nature based on the
	 * <code>folderName</code>.
	 * 
	 * @return IFolder
	 */
	protected IFolder createFolder() {
		try {
			return getEjbNature().createFolder(folderName);
		} catch (CoreException e) {
			Logger.getLogger(EjbPlugin.PLUGIN_ID).logError(e);
		}
		return null;
	}

	/**
	 * Add <code>aFolder</code> as a source folder to the Java project's classpath.
	 * 
	 * @param aFolder
	 *            IFolder
	 * @param monitor
	 *            IProgressMonitor
	 */
	protected void addSourceClasspathEntry(IFolder aFolder, IProgressMonitor monitor) throws JavaModelException {
		IJavaProject javaProject = ProjectUtilities.getJavaProject(getEjbNature().getProject());
		IPath path = aFolder.getFullPath();
		IPackageFragmentRoot root = javaProject.findPackageFragmentRoot(path);
		if (root == null) {
			monitor.subTask(EJBCreationResourceHandler.getString("Creating_source_folder__1", new Object[]{getFolderName()})); //$NON-NLS-1$
			IClasspathEntry[] entries = javaProject.getRawClasspath();
			int nEntries = entries.length;
			IClasspathEntry[] newEntries = new IClasspathEntry[nEntries + 1];
			for (int i = 0; i < nEntries; i++) {
				newEntries[i] = entries[i];
			}
			newEntries[nEntries] = JavaCore.newSourceEntry(path);
			javaProject.setRawClasspath(newEntries, null);
		}
	}
}