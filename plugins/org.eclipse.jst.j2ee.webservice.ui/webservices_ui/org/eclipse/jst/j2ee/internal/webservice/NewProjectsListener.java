/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.internal.webservice.helper.WebServicesManager;
import org.eclipse.jst.j2ee.internal.webservice.plugin.WebServiceUIPlugin;
import org.eclipse.jst.j2ee.internal.webservice.startup.WebserviceListener;

public class NewProjectsListener implements IResourceChangeListener, IResourceDeltaVisitor  {

	
	private WebServiceViewerSynchronization synchronization;
	private boolean listening = false;
	private boolean synchronizing = false;
	private boolean isDisposed = false;

	public NewProjectsListener(WebServiceViewerSynchronization sync) {
		synchronization = sync;
		if(!synchronization.webServiceProjectsExist(new NullProgressMonitor())) {
			//			 none exist
			WebServiceViewerSynchronization.setAreThereWebServices(false);
		}
		startListening();
	} 
	
	public void dispose() {
		if(listening)
			stopListening();
		if(synchronizing)
			synchronization.stop();
		if(WebserviceListener.getInstance() != null
				&& !WebserviceListener.getInstance().isListening()){
			WebserviceListener.getInstance().startListening();
		}
		isDisposed = true;
	}
	
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		try {
			event.getDelta().accept(this);
		} catch (CoreException e) {
			WebServiceUIPlugin.logError(0, e.getMessage(), e);
		} 
	} 

	@Override
	public boolean visit(IResourceDelta delta) throws CoreException { 
		
		IResource resource = delta.getResource();
		switch (resource.getType()) {
			case IResource.ROOT :
				return true;
			case IResource.PROJECT: 
				if(listening &&  (delta.getKind() == IResourceDelta.ADDED || delta.getKind() == IResourceDelta.CHANGED)){
					if(WebServiceViewerSynchronization.isInteresting(resource.getProject()) && !synchronization.hasNavigatorGroupBeenAdded()) {
						return true;
					}
				}
				break;
			case IResource.FOLDER :
				if(listening && (delta.getKind() == IResourceDelta.ADDED || delta.getKind() == IResourceDelta.CHANGED)) {
					return true;
				}
				break;
			case IResource.FILE :
				if(listening && delta.getKind() == IResourceDelta.ADDED) {
					if(WebServicesManager.isFileInteresting((IFile)resource)){
						stopListening();
						if(!synchronizing){
							synchronizing = true;
							synchronization.start();
						}
						// set it to true, add the node if not already added
						WebServiceViewerSynchronization.setAreThereWebServices(true);
						if(!synchronization.hasNavigatorGroupBeenAdded()){
							synchronization.addWebServiceNode();
							synchronization.indexJobScheduled = false;
						}
					}
				}				
		}
		
		return false;
	}

	void startListening() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
		listening = true;
	}

	void stopListening() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		listening = false;
	}

	boolean isListening() {
		return listening;
	}

	boolean isDisposed() {
		return isDisposed;
	}
}
