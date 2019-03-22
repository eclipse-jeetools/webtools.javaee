/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jst.j2ee.internal.navigator.ui.Messages;
import org.eclipse.ui.progress.UIJob;

public class ClearPlaceHolderJob extends UIJob {

	private AbstractTreeViewer viewer;
	private LoadingDDNode placeHolder;
	private Object[] children;
	private Object parent;

	public ClearPlaceHolderJob(AbstractTreeViewer viewer, LoadingDDNode placeHolder, Object parent, Object[] children) {
		super(Messages.ClearPlaceHolderJob_Removing_place_holder_);
		this.viewer = viewer;
		this.placeHolder = placeHolder; 
		this.parent = parent;
		this.children = children;
		setRule(new NonConflictingRule());
	}
	
	@Override
	public IStatus runInUIThread(IProgressMonitor monitor) {

		try {
			if( !viewer.getControl().isDisposed() ){
				viewer.getControl().setRedraw(false);
				viewer.add(parent, children);
				viewer.remove(placeHolder);
			}
		} finally {
			if( !viewer.getControl().isDisposed() ){
				viewer.getControl().setRedraw(true);
			}
		}
		return Status.OK_STATUS;
	}

}
