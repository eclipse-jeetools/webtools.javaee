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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.progress.UIJob;

public class LoadingDDUIJob extends UIJob {
	
	private static final String[] NO_PROPERTIES = new String[0];

	private static final long DELAY = 200;
	  
	private LoadingDDNode placeHolder;
	private StructuredViewer viewer;

	private boolean complete; 
	 
	public LoadingDDUIJob(StructuredViewer viewer, LoadingDDNode placeHolder) {
		super(placeHolder.getText()); 
		this.viewer = viewer;
		this.placeHolder = placeHolder;
		setSystem(true);
		//setRule(NonConflictingRule.INSTANCE);
	}

	public IStatus runInUIThread(IProgressMonitor monitor) { 
		
		if(!complete) {
			viewer.refresh(placeHolder, true);			
			schedule(DELAY);		
		}
		return Status.OK_STATUS;
		
	}

	public void setComplete(boolean newComplete) {
		complete = newComplete;
		
	}

}
