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
package org.eclipse.jst.j2ee.internal.ejb.project;


import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.operations.DefaultModuleProjectCreationOperation;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesResource;
import org.eclipse.wst.common.internal.emf.resource.CompatibilityXMIResource;
import org.eclipse.wst.common.internal.emf.resource.ReferencedResource;
import org.eclipse.wst.common.internal.emf.utilities.Revisit;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.jdt.internal.integration.WorkingCopyProvider;


/**
 * Insert the type's description here. Creation date: (4/11/2001 5:05:30 PM)
 * 
 * @author: Administrator
 */
public class EJBEditModel extends AbstractEJBEditModel implements WorkingCopyProvider {

	private boolean batchMode = false;

	public EJBEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly) {
		super(editModelID, context, readOnly);
	}

	public EJBEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly, boolean accessUnkownResourcesAsReadOnly) {
		super(editModelID, context, readOnly, accessUnkownResourcesAsReadOnly);
	}

	/**
	 * This will delete
	 * 
	 * @cu from the workbench and fix the internal references for this working copy manager.
	 */
	public void delete(org.eclipse.jdt.core.ICompilationUnit cu, org.eclipse.core.runtime.IProgressMonitor monitor) {
		getWorkingCopyManager().delete(cu, monitor);
	}

	/**
	 * This method should only be called by the J2EENature.
	 */
	public void dispose() {
		super.dispose();
		resetWorkingCopyManager();
	}

	public Set getAffectedFiles() {
		java.util.Set affected = super.getAffectedFiles();
		if (getWorkingCopyManager() != null)
			affected.addAll(getWorkingCopyManager().getAffectedFiles());

		return affected;
	}

	/**
	 * Returns the working copy remembered for the compilation unit encoded in the given editor
	 * input. Does not connect the edit model to the working copy.
	 * 
	 * @param input
	 *            ICompilationUnit
	 * @return the working copy of the compilation unit, or <code>null</code> if the input does
	 *         not encode an editor input, or if there is no remembered working copy for this
	 *         compilation unit
	 */
	public org.eclipse.jdt.core.ICompilationUnit getExistingWorkingCopy(org.eclipse.jdt.core.ICompilationUnit cu) throws org.eclipse.core.runtime.CoreException {
		return getWorkingCopyManager().getExistingWorkingCopy(cu);
	}

	/**
	 * Returns the working copy remembered for the compilation unit.
	 * 
	 * @param input
	 *            ICompilationUnit
	 * @return the working copy of the compilation unit, or <code>null</code> if there is no
	 *         remembered working copy for this compilation unit
	 */
	public org.eclipse.jdt.core.ICompilationUnit getWorkingCopy(org.eclipse.jdt.core.ICompilationUnit cu, boolean forNewCU) throws org.eclipse.core.runtime.CoreException {
		return getWorkingCopyManager().getWorkingCopy(cu, forNewCU);
	}

	/**
	 * Save the new compilation units only.
	 */
	protected void handleSaveIfNecessaryDidNotSave(IProgressMonitor monitor) {
		getWorkingCopyManager().saveOnlyNewCompilationUnits(monitor);
	}

	/**
	 * Insert the method's description here. Creation date: (4/11/2001 4:14:26 PM)
	 * 
	 * @return java.util.Set
	 */
	public void processResource(Resource aResource) {
		if (aResource != null && !getResources().contains(aResource)) {
			if (aResource instanceof ReferencedResource) {
				access((ReferencedResource) aResource);
				if (!isReadOnly() && aResource instanceof EJBResource) //only need to hold extra
					// write count
					access((ReferencedResource) aResource);
				Revisit.revisit();
				//We need a better way to pass this through the save options instead.
				//We also need to make this dynamic based on the project target
				((ReferencedResource) aResource).setFormat(CompatibilityXMIResource.FORMAT_MOF5);
			} else if (!isReadOnly())
				aResource.setTrackingModification(true);
			addResource(aResource);
		}
	}

	/**
	 * Release each of the referenced resources.
	 */
	protected void release(Resource aResource) {

		removeResource(aResource);
		if (aResource != null) {
			boolean isRefRes = aResource instanceof ReferencedResource;
			if (isRefRes)
				super.release((ReferencedResource) aResource);
			if (!isReadOnly() && aResource instanceof EJBResource) {
				if (isRefRes)
					((ReferencedResource) aResource).releaseFromWrite();
				if (!isDisposing())
					resetWorkingCopyManager();
			}
		}

	}

	/**
	 * This will force all of the referenced Resources to be saved.
	 */
	public void primSave(IProgressMonitor monitor) {
		saveCompilationUnits(monitor);
		if (monitor == null || !monitor.isCanceled())
			super.primSave(monitor);
	}

	/**
	 * This will save all of the referenced CompilationUnits to be saved.
	 */
	public void saveCompilationUnits(IProgressMonitor monitor) {
		getWorkingCopyManager().saveCompilationUnits(monitor);
	}

	public void saveIfNecessary(Object accessorKey) {
		//if in batch mode (Bottom-up)...do not save
		if (!getBatchMode())
			super.saveIfNecessary(accessorKey);
	}

	/**
	 * Gets the batchMode.
	 * 
	 * @return Returns a boolean
	 */
	public boolean getBatchMode() {
		return batchMode;
	}

	/**
	 * Sets the batchMode.
	 * 
	 * @param batchMode
	 *            The batchMode to set
	 */
	public void setBatchMode(boolean isBatch) {
		batchMode = isBatch;
		EJBResource res = null;
		res = getEjbXmiResource();
		if (res != null)
			res.setBatchMode(isBatch);
	}

	public XMLResource getDeploymentDescriptorResource() {
		return getEjbXmiResource();
	}

	protected void reverted(ReferencedResource revertedResource) {
		if (getWorkingCopyManager() != null)
			getWorkingCopyManager().revert();
		revertAllResources();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel#isDirty()
	 */
	public boolean isDirty() {
		boolean dirtyBool = super.isDirty();
		if (!dirtyBool && getWorkingCopyManager() != null)
			dirtyBool = getWorkingCopyManager().hasWorkingCopies();
		return dirtyBool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel#resourceIsLoadedChanged(org.eclipse.emf.ecore.resource.Resource,
	 *      boolean, boolean)
	 */
	protected void resourceIsLoadedChanged(Resource aResource, boolean oldValue, boolean newValue) {
		if (!isReverting && !disposing && !isReadOnly() && oldValue && !newValue && aResource instanceof EJBResource)
			resetWorkingCopyManager();
		super.resourceIsLoadedChanged(aResource, oldValue, newValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel#getRootObject()
	 */
	public Object getRootObject() {
		return getEJBJar();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel#get13WebServicesClientResource()
	 */
	public WebServicesResource get13WebServicesClientResource() {
		return (WebServicesResource) getResource(J2EEConstants.WEB_SERVICES_CLIENT_META_INF_DD_URI_OBJ);
	}

	public String getDevelopmentAcivityID() {
		return DefaultModuleProjectCreationOperation.ENTERPRISE_JAVA;
	}
}