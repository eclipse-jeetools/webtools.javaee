/***********************************************************************
 * Copyright (c) 2011 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jst.j2ee.navigator.internal.ClearPlaceHolderJob;

public class ClearJeePlaceHolderJob extends ClearPlaceHolderJob {

	private final AbstractTreeViewer viewer2;
	private final AbstractGroupProvider node;

	public ClearJeePlaceHolderJob(AbstractTreeViewer viewer,
			LoadingGroupProvider provider, Object parent, Object[] children) {
		super(viewer, provider.getPlaceHolder(), parent, children);
		viewer2 = viewer;
		this.node = provider;
	}

	@Override
	public IStatus runInUIThread(IProgressMonitor monitor) {
		IStatus status = super.runInUIThread(monitor);
		if(node != null){
			viewer2.remove(node);	
		}
		return status;
	}
	
	

}
