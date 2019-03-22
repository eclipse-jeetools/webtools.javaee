/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
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
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.progress.UIJob;

public class LoadingDDUIJob extends UIJob {

	private static final long DELAY = 200;
	  
	private LoadingDDNode placeHolder;
	private StructuredViewer viewer; 
	 
	public LoadingDDUIJob(StructuredViewer viewer, LoadingDDNode placeHolder) {
		super(placeHolder.getText()); 
		this.viewer = viewer;
		this.placeHolder = placeHolder;
		setSystem(true);
		setRule(new NonConflictingRule());
	}

	@Override
	public IStatus runInUIThread(IProgressMonitor monitor) {  

		if(!placeHolder.isDisposed()) {
			viewer.refresh(placeHolder, true);			
			schedule(DELAY);		
		}
		return Status.OK_STATUS;
		
	}

}
