/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.plugin;


import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.common.util.CommonUtil;

public class J2EEViewerSorter extends ViewerSorter {

	/**
	 * Constructor for J2EEViewerSorter.
	 */
	public J2EEViewerSorter() {
		super();
	}



	/**
	 * @see ViewerSorter#compare(Viewer, Object, Object)
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (shouldSort(e1, e2))
			return super.compare(viewer, e1, e2);
		return 0;
	}

	protected boolean isEnterpriseBean(Object o) {
		return o instanceof EnterpriseBean;
	}

	protected boolean isDeploymentDescriptorRoot(Object o) {
		return CommonUtil.isDeploymentDescriptorRoot(o);
	}

	protected boolean shouldSort(Object e1, Object e2) {
		return (isDeploymentDescriptorRoot(e1) && isDeploymentDescriptorRoot(e2)) || (isEnterpriseBean(e1) && isEnterpriseBean(e2)) || ((e1 instanceof IFile) && (e2 instanceof IFile));
	}


}