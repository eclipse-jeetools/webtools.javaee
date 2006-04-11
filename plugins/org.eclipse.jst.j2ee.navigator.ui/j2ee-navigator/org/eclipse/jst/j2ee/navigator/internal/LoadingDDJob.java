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
package org.eclipse.jst.j2ee.navigator.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.AbstractTreeViewer;

public class LoadingDDJob extends Job {

	private static final Class IPROJECT_CLASS = IProject.class;

	private EMFRootObjectProvider rootObjectProvider;
	private LoadingDDNode placeHolder;
	private AbstractTreeViewer viewer;
	private IAdaptable parent;

	public LoadingDDJob(AbstractTreeViewer viewer, LoadingDDNode placeHolder, IAdaptable parent, EMFRootObjectProvider rootObjectProvider) {
		super(placeHolder.getText());
		this.viewer = viewer;
		this.placeHolder = placeHolder;
		this.parent = parent;
		this.rootObjectProvider = rootObjectProvider;
		setRule(new NonConflictingRule());
	}

	protected IStatus run(IProgressMonitor monitor) { 

		LoadingDDUIJob updateUIJob = new LoadingDDUIJob(viewer, placeHolder);
		updateUIJob.schedule();
		
		List children = new ArrayList();
		IProject project =  (IProject)  parent.getAdapter(IPROJECT_CLASS);
		Object[] rootObjects = (rootObjectProvider != null) ? rootObjectProvider.getModels(project) : null;
		if (rootObjects != null) {
			for (int x=0; x< rootObjects.length ; ++x) {
				children.add(rootObjects[x]);
			}
			
		}		
		updateUIJob.setComplete(true);
		new ClearPlaceHolderJob(viewer, placeHolder, parent, children.toArray()).schedule();
		return Status.OK_STATUS;
	}

}
