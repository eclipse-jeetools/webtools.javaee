/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.editmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.webservice.plugin.WebServicePlugin;
import org.eclipse.wst.common.internal.emf.resource.ReferencedResource;


public abstract class CompositeEditModel extends EditModel implements CommandStackListener {

	protected int fReferenceCount;

	protected Hashtable fEditModels;
	protected Hashtable fResources;
	protected Hashtable fRootObjects;
	protected List fDescriptors;

	protected EditModelEvent dirtyModelEvent;
	private List fListeners;
	protected boolean isNotifing = false;

	public int getReferenceCount() {
		return fReferenceCount;
	}

	public boolean access() {
		fReferenceCount++;
		if (fReferenceCount == 1) {
			//get all the registered edit models
			IConfigurationElement[] elements = getExtensions();

			//get a command stack
			createCommandStack();

			for (int i = 0; i < elements.length; i++) {
				try {
					Object editModelObject = elements[i].createExecutableExtension("class"); //$NON-NLS-1$
					if (editModelObject instanceof EditModel) {
						//Setup the edit model and add it to the Hashtable
						EditModel editModel = (EditModel) editModelObject;
						String descriptorName = elements[i].getAttribute("descriptorName"); //$NON-NLS-1$
						editModel.setResourceSet(fResourceSet);
						editModel.setProject(fProject);
						editModel.setInputFile(fInputFile);
						editModel.setCommandStack(fCommandStack);
						editModel.setParent(this);
						fEditModels.put(descriptorName, editModel);
						//Get the resource from the editmodel and add it to the resource Hashtable
						Resource res = editModel.getModelResource(descriptorName);
						if (res == null) {
							//Exit access()
							return false;
						}
						//The webservices.xml and webservicesclient.xml do not load as
						// ReferencedResources
						//so, for the time being, we only do proper accessing on the xmi resources
						// we load.
						if (res instanceof ReferencedResource)
							((ReferencedResource) res).accessForWrite();
						//todo: process the resource upon load (i.e. accessForWrite(), etc)
						fResources.put(descriptorName, res);
						//Get the root object from the resource and add it to the Hashtable
						EObject rootObject = editModel.getRootModelObject(descriptorName);
						fRootObjects.put(descriptorName, rootObject);
						fDescriptors.add(descriptorName);

					}
				} catch (CoreException e) {
					return false;
				}
			}
		}
		return true;
	}

	public abstract IConfigurationElement[] getExtensions();

	public void release() {
		fReferenceCount--;
		if (fReferenceCount == 0) {
			//release resources and dispose the edit model.
			Resource resource;
			int listsize = fDescriptors.size();
			for (int i = 0; i < listsize; i++) {
				String descriptorName = (String) fDescriptors.get(i);
				//release the resource
				resource = getModelResource(descriptorName);

				//The webservices.xml and webservicesclient.xml do not load as ReferencedResources
				//so, for the time being, we only do proper accessing on the xmi resources we load.
				if (resource instanceof ReferencedResource)
					((ReferencedResource) resource).releaseFromWrite();
				else {
					//this is a really bad but until we load ReferencedResources for
					// webservices.xml and
					//webservicesclient.xml we need to explicitly unload
					//resource.unload();
				}

				//remove stored resources, root objects and edit models from Hashtables
				fResources.remove(descriptorName);
				fRootObjects.remove(descriptorName);
				fEditModels.remove(descriptorName);
			}

			fDescriptors = null;
			fCommandStack = null;
			dirtyModelEvent = null;
			dispose();
		}
	}

	protected abstract void dispose();

	/** *************Getting resources and objects ******************* */
	public EditModel getEditModel(String descriptorName) {
		EditModel em = (EditModel) fEditModels.get(descriptorName);
		return em;
	}

	public Resource getModelResource(String descriptorName) {
		Resource res = (Resource) fResources.get(descriptorName);
		return res;
	}

	public EObject getRootModelObject(String descriptorName) {
		EObject res = (EObject) fRootObjects.get(descriptorName);
		return res;
	}

	public abstract EObject getRootModelObject();

	public abstract Resource getRootModelResource();


	/** *********Command Stack related methods*************************** */
	public BasicCommandStack createCommandStack() {
		if (fCommandStack == null) {
			fCommandStack = new BasicCommandStack();
			fCommandStack.addCommandStackListener(this);
		}
		return fCommandStack;
	}

	public void commandStackChanged(EventObject event) {
		if (dirtyModelEvent == null)
			dirtyModelEvent = new EditModelEvent(EditModelEvent.DIRTY, this);
		if (hasListeners())
			notifyListeners(dirtyModelEvent);
	}

	protected List getListeners() {
		if (fListeners == null)
			fListeners = new ArrayList();
		return fListeners;
	}

	/**
	 * Add
	 * 
	 * @aListener to the list of listeners.
	 */
	public void addListener(EditModelListener aListener) {
		if (aListener != null && !getListeners().contains(aListener))
			getListeners().add(aListener);
	}

	/**
	 * Returns true if there are any listeners
	 */
	public boolean hasListeners() {
		return !getListeners().isEmpty();
	}

	/**
	 * Notify listeners of
	 * 
	 * @anEvent.
	 */
	protected void notifyListeners(EditModelEvent anEvent) {
		if (fListeners == null)
			return;
		synchronized (this) {
			isNotifing = true;
		}
		try {
			List list = getListeners();
			for (int i = 0; i < list.size(); i++)
				((EditModelListener) list.get(i)).editModelChanged(anEvent);
		} finally {
			synchronized (this) {
				isNotifing = false;
			}
		}
	}

	/** *********Save related methods*************************** */
	public final void save(IProgressMonitor monitor) {
		//getSaveHandler().access();
		try {
			IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
				public void run(IProgressMonitor aMonitor) {
					primSave(aMonitor);
				}
			};
			runSaveOperation(runnable, monitor);
		} catch (CoreException ex) {
			//Pop up a dialog indicating that problems occurred during the save
			MessageDialog.openError(null, WebServicePlugin.getMessage("%TITLE_SAVE_ERROR"), WebServicePlugin.getMessage("%MSG_SAVE_ERROR")); //$NON-NLS-1$ //$NON-NLS-2$
		}

	}

	private void runSaveOperation(IWorkspaceRunnable runnable, IProgressMonitor monitor) throws CoreException {
		ResourcesPlugin.getWorkspace().run(runnable, monitor);
	}


	/**
	 * This will force all of the referenced Resources to be saved.
	 */
	public void primSave(IProgressMonitor monitor) {
		Resource resource;
		int listsize = fDescriptors.size();
		for (int i = 0; i < listsize; i++) {
			String descriptorName = (String) fDescriptors.get(i);
			resource = getModelResource(descriptorName);
			if (resource.isModified()) {
				try {
					resource.save(Collections.EMPTY_MAP);
					resource.setModified(false);
				} catch (Exception e) {
				}
			}
		}

		getCommandStack().saveIsDone();
		if (hasListeners()) {
			EditModelEvent event = new EditModelEvent(EditModelEvent.SAVE, this);
			notifyListeners(event);
		}
	}

	public boolean isDirty() {
		Resource resource;
		int listsize = fDescriptors.size();
		for (int i = 0; i < listsize; i++) {
			String descriptorName = (String) fDescriptors.get(i);
			resource = getModelResource(descriptorName);
			if (resource.isModified()) {
				return true;
			}
		}
		return false;
	}


	public void resourceChanged(EditModelEvent anEvent) {
		if (hasListeners()) {
			anEvent.setEditModel(this);
			notifyListeners(anEvent);
		}
	}


	protected void resourceIsLoadedChanged(Resource aResource, boolean oldValue, boolean newValue) {
		//Do nothing for now
	}


	protected class ResourceAdapter extends AdapterImpl {
		public void notifyChanged(Notification notification) {
			if (notification.getEventType() == Notification.SET && notification.getFeatureID(null) == Resource.RESOURCE__IS_LOADED) {
				resourceIsLoadedChanged((Resource) notification.getNotifier(), notification.getOldBooleanValue(), notification.getNewBooleanValue());
			}
		}
	}

}