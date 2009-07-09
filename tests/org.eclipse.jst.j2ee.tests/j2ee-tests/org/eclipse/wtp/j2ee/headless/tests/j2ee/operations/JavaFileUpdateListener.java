/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

public class JavaFileUpdateListener implements IResourceChangeListener, IResourceDeltaVisitor {

	private static JavaFileUpdateListener instance = null;
	
	public static JavaFileUpdateListener getInstance(){
		if(instance == null){
			instance = new JavaFileUpdateListener();
		}
		return instance;
	}
	
	private List <IFile> filesList = null;
	
	public void setFiles(List<IFile> filesList){
		this.filesList = filesList;
	}
	
	public boolean areFilesCreated() {
		return filesList == null || filesList.size() == 0;
	}
	
	public void resourceChanged(IResourceChangeEvent event) {
		switch (event.getType()){
			case IResourceChangeEvent.POST_CHANGE:
				try {
					event.getDelta().accept(this);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			break;
		}
	}

	public boolean visit(IResourceDelta delta) throws CoreException {
		if(filesList == null){
			return false;
		}
		IResource resource = delta.getResource();
		switch (resource.getType()) {
			case IResource.ROOT:
				return true;
			case IResource.PROJECT:
				for(int i=0;i<filesList.size(); i++){
					if(filesList.get(i).getProject().equals(resource)){
						return true;
					}
				}
				return false;
			case IResource.FOLDER: 
				for(int i=0;i<filesList.size(); i++){
					IContainer container = filesList.get(i).getParent();
					while(container != null && container.getType() == IResource.FOLDER){
						if(resource.equals(container)){
							return true;
						}
						container = container.getParent();
					}
				}
				return false;
			case IResource.FILE:
				if(filesList.contains(resource)){
					filesList.remove(resource);
					if(filesList.isEmpty()){
						filesList = null;
					}
				}
				return false;
		}	
		
		return false;
	}
}
