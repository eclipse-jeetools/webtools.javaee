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
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.commonarchivecore.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.wst.common.framework.operation.IOperationHandler;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author schacher
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public abstract class AbstractEJBClientJAROperation extends EditModelOperation {

	protected static final String SERVICE_LOCATOR_JAR_NAME = "serviceLocatorMgr.jar"; //$NON-NLS-1$

	protected IProject ejbProject;
	protected IProject clientProject;
	protected IWorkspace workspace;
	protected Object validateEditContext;
	protected EARNatureRuntime[] earNatures;
	protected EJBNatureRuntime ejbNature;
	protected IOperationHandler operationHandler;
	protected IProgressMonitor monitor;
	protected IProgressMonitor moveResourceMonitor;

	public AbstractEJBClientJAROperation(EJBClientProjectDataModel dataModel, IOperationHandler opHandler) {
		super(dataModel);
		ejbProject = getEJBProject(dataModel.getStringProperty(EJBClientProjectDataModel.PROJECT_NAME));
		operationHandler = opHandler;
		validateEditContext = opHandler.getContext();
	}

	public AbstractEJBClientJAROperation(EJBClientProjectDataModel dataModel) {
		super(dataModel);
		ejbProject = getEJBProject(dataModel.getStringProperty(EJBClientProjectDataModel.PROJECT_NAME));
		operationHandler = null;
	}


	protected void initialize() {
		workspace = ejbProject.getWorkspace();
		ejbNature = (EJBNatureRuntime) EJBNatureRuntime.getRegisteredRuntime(ejbProject);
		earNatures = ejbNature.getReferencingEARProjects();
	}

	protected boolean verifyFilesInSync() throws CoreException {
		if (verifyFilesInSync(ejbProject))
			return verifyFilesInSync(clientProject);
		return false;
	}

	protected boolean verifyFilesInSync(IProject project) throws CoreException {
		if (!project.exists())
			return true;
		if (!project.isSynchronized(IResource.DEPTH_INFINITE)) {
			String message = format(ClientJARCreationConstants.FILES_OUT_OF_SYNC, new String[]{project.getName()});
			if (operationHandler == null || operationHandler.canContinue(message))
				ejbProject.refreshLocal(IResource.DEPTH_INFINITE, createSubProgressMonitor(1));
			else
				return false;
		}
		return true;
	}

	/**
	 * @param clientProjectName
	 */
	protected IProject getEJBProject(String aProjectName) {
		return getWorkspace().getRoot().getProject(aProjectName);
	}

	protected String format(String aPattern, String[] args) {
		return MessageFormat.format(aPattern, args);
	}

	protected IProgressMonitor createSubProgressMonitor(int ticks) {
		return new SubProgressMonitor(monitor, ticks);
	}

	protected abstract void addAdditionalFilesForValidateEdit(List roFiles) throws CoreException;

	protected List normalize(String[] mfEntries, EARNatureRuntime nature, boolean filterServiceLocator) {
		String jarURI = nature.getJARUri(ejbProject);
		if (jarURI == null)
			return Collections.EMPTY_LIST;

		List result = new ArrayList(mfEntries.length);
		for (int i = 0; i < mfEntries.length; i++) {
			String norm = ArchiveUtil.deriveEARRelativeURI(mfEntries[i], jarURI);
			if (norm == null)
				norm = mfEntries[i];
			//We know this is an implementation dependency so we don't want to move it
			if (!SERVICE_LOCATOR_JAR_NAME.equals(norm) || !filterServiceLocator)
				result.add(norm);
		}
		return result;
	}

	protected void copyOutgoingClasspathEntries(IProject source, IProject target, boolean filterServiceLocator) throws JavaModelException {
		IJavaProject sourceJProject = ProjectUtilities.getJavaProject(source);
		IJavaProject targetJProject = ProjectUtilities.getJavaProject(target);
		IClasspathEntry[] sourceCp = sourceJProject.getRawClasspath();
		List targetCp = new ArrayList(Arrays.asList(targetJProject.getRawClasspath()));
		for (int i = 0; i < sourceCp.length; i++) {
			IClasspathEntry entry = sourceCp[i];
			if (!isContainedInProject(entry, source) && !contains(targetCp, entry) && (!isServiceLocator(entry) || !filterServiceLocator))
				targetCp.add(entry);
		}
		IClasspathEntry[] newCp = (IClasspathEntry[]) targetCp.toArray(new IClasspathEntry[targetCp.size()]);
		targetJProject.setRawClasspath(newCp, null);
	}

	/**
	 * @param entry
	 * @return
	 */
	private boolean isServiceLocator(IClasspathEntry entry) {
		IPath path = entry.getPath();
		return path != null && SERVICE_LOCATOR_JAR_NAME.equals(path.lastSegment());
	}

	protected boolean isContainedInProject(IClasspathEntry entry, IProject project) {
		return !entry.getPath().isEmpty() && project.getName().equals(entry.getPath().segment(0));
	}

	protected boolean contains(List cp, IClasspathEntry entry) {
		for (int i = 0; i < cp.size(); i++) {
			IClasspathEntry elmt = (IClasspathEntry) cp.get(i);
			if (elmt.getEntryKind() == entry.getEntryKind() && elmt.getPath().equals(entry.getPath()))
				return true;
		}
		return false;
	}

	protected void mkdirs(IPath newPath, IWorkspaceRoot root) throws CoreException {
		if (newPath.segmentCount() <= 2)
			return;
		IPath parentPath = newPath.removeLastSegments(1);
		IFolder folder = root.getFolder(parentPath);
		if (!folder.exists()) {
			mkdirs(parentPath, root);
			folder.create(true, true, null);
		}
	}

	/**
	 * @throws CoreException
	 */
	protected void cleanProject(IProject project) throws CoreException {
		if (project != null) {
			project.build(IncrementalProjectBuilder.CLEAN_BUILD, new SubProgressMonitor(monitor, 1));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation#doDispose(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void doDispose(IProgressMonitor new_monitor) {
		try {
			cleanProjects();
		} catch (CoreException ex) {
			Logger.getLogger().logError(ex);
		}
		super.doDispose(new_monitor);
	}

	protected void cleanProjects() throws CoreException {
		cleanProject(ejbProject);
	}



}