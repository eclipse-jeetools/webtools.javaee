/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.editmodel;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

import com.ibm.wtp.emf.workbench.EMFWorkbenchContextBase;
import com.ibm.wtp.emf.workbench.IEMFContextContributor;
import com.ibm.wtp.emf.workbench.ProjectResourceSet;

public abstract class AbstractEditModelOwner implements IEMFContextContributor {
	protected IFile fInputFile;
	protected IProject fProject;
	protected EMFWorkbenchContext fEmfNature;
	protected CompositeEditModel fEditModel;
	protected Adapter fResourceSetListener;

	public IProject getProject() {
		return fProject;
	}

	public IFile getInputFile() {
		return fInputFile;
	}

	public EditModel getEditModel() {
		return fEditModel;
	}

	public ResourceSet getResourceSet() {
		return getEmfNature().getResourceSet();
	}

	protected EMFWorkbenchContext getEmfNature() {
		if (fEmfNature == null) {
			try {
				createEmfNature();
			} catch (CoreException ex) {
				// Log anything...?
			}
		}
		return fEmfNature;
	}

	protected void createEmfNature() throws CoreException {
		WorkbenchResourceHelper.createEMFContext(getProject(), this);
	}

	public void primaryContributeToContext(EMFWorkbenchContextBase aNature) {
		if (fEmfNature == aNature)
			return;
		fEmfNature = (EMFWorkbenchContext) aNature;
		ProjectResourceSet set = aNature.getResourceSet();
		set.setResourceFactoryRegistry(J2EEResourceFactoryRegistry.INSTANCE);
		startListeningToResourceSet();
	}

	public void secondaryContributeToContext(EMFWorkbenchContextBase aNature) {

	}


	protected void startListeningToResourceSet() {
		ResourceSet set = getResourceSet();
		if (set != null)
			set.eAdapters().add(getResourceSetListener());
	}

	/**
	 * Notify all editModels of the change.
	 */
	public void addedResource(Resource addedResource) {
		//todo
	}

	/**
	 * Notify all editModels of the change.
	 */
	public void removedResource(Resource removedResource) {

		if (fEditModel != null) {
			EditModelEvent event = new EditModelEvent(EditModelEvent.REMOVED_RESOURCE, null);
			event.addResource(removedResource);
			fEditModel.resourceChanged(event);
		}

	}

	/**
	 * Notify all editModels of the change.
	 */
	public void removedResources(List removedResources) {
		//todo
	}


	protected class ResourceSetListener extends AdapterImpl {
		/*
		 * @see Adapter#notifyChanged(new ENotificationImpl((InternalEObject)Notifier,
		 *      int,(EStructuralFeature) EObject, Object, Object, int))
		 */
		public void notifyChanged(Notification notification) {
			switch (notification.getEventType()) {
				case Notification.ADD :
					addedResource((Resource) notification.getNewValue());
					break;
				case Notification.REMOVE :
					removedResource((Resource) notification.getOldValue());
					break;
				case Notification.REMOVE_MANY :
					removedResources((List) notification.getOldValue());
					break;
			}
		}
	}

	protected Adapter getResourceSetListener() {
		if (fResourceSetListener == null)
			fResourceSetListener = new ResourceSetListener();
		return fResourceSetListener;
	}

	public abstract EditModel createEditModel();

}