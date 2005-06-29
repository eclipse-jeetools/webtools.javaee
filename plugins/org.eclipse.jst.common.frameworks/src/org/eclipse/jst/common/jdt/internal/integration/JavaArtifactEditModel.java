package org.eclipse.jst.common.jdt.internal.integration;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.internal.emf.resource.CompatibilityXMIResource;
import org.eclipse.wst.common.internal.emf.resource.ReferencedResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;

public class JavaArtifactEditModel extends ArtifactEditModel implements WorkingCopyProvider {

	private WorkingCopyManager workingCopyManager = null;

	/**
	 * @param anEditModelId
	 * @param aContext
	 * @param toMakeReadOnly
	 * @param toAccessUnknownResourcesAsReadOnly
	 * @param aModuleURI
	 */
	public JavaArtifactEditModel(String anEditModelId, EMFWorkbenchContext aContext, boolean toMakeReadOnly, boolean toAccessUnknownResourcesAsReadOnly, URI aModuleURI) {
		super(anEditModelId, aContext, toMakeReadOnly,
				toAccessUnknownResourcesAsReadOnly, aModuleURI);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param anEditModelId
	 * @param aContext
	 * @param toMakeReadOnly
	 * @param aModuleURI
	 */
	public JavaArtifactEditModel(String anEditModelId, EMFWorkbenchContext aContext, boolean toMakeReadOnly, URI aModuleURI) {
		super(anEditModelId, aContext, toMakeReadOnly, aModuleURI);
		// TODO Auto-generated constructor stub
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
	public org.eclipse.jdt.core.ICompilationUnit getWorkingCopy(ICompilationUnit cu, boolean forNewCU) throws CoreException {
		return getWorkingCopyManager().getWorkingCopy(cu, forNewCU);
	}

	/**
	 * Save the new compilation units only.
	 */
	protected void handleSaveIfNecessaryDidNotSave(IProgressMonitor monitor) {
		getWorkingCopyManager().saveOnlyNewCompilationUnits(monitor);
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

	/**
	 * This will force all of the referenced Resources to be saved.
	 */
	public void primSave(IProgressMonitor monitor) {
		saveCompilationUnits(monitor);
		if (monitor == null || !monitor.isCanceled())
			super.primSave(monitor);
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
				release((ReferencedResource) aResource);
			if (!isDisposing())
				resetWorkingCopyManager();
			}
		}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel#resourceIsLoadedChanged(org.eclipse.emf.ecore.resource.Resource,
	 *      boolean, boolean)
	 */
	protected void resourceIsLoadedChanged(Resource aResource, boolean oldValue, boolean newValue) {
		if (!isReverting && !disposing && !isReadOnly() && oldValue && !newValue && aResource instanceof TranslatorResource)
			resetWorkingCopyManager();
		super.resourceIsLoadedChanged(aResource, oldValue, newValue);
	}

	protected void reverted(ReferencedResource revertedResource) {
		if (getWorkingCopyManager() != null)
			getWorkingCopyManager().revert();
		revertAllResources();
	}

	/**
	 * This will save all of the referenced CompilationUnits to be saved.
	 */
	public void saveCompilationUnits(IProgressMonitor monitor) {
		getWorkingCopyManager().saveCompilationUnits(monitor);
	}

	public WorkingCopyManager getWorkingCopyManager() {
		if (workingCopyManager == null)
			workingCopyManager = WorkingCopyManagerFactory.newRegisteredInstance();
		return workingCopyManager;
	}

	/**
	 * Reset the working copy manager because the ejb-jar.xml was removed without disposing.
	 */
	protected void resetWorkingCopyManager() {
		if (workingCopyManager != null) {
			workingCopyManager.dispose();
			workingCopyManager = null;
		}
	}
	

}
