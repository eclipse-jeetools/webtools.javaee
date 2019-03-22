/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal;


import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.j2ee.common.internal.util.CommonUtil;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


public class J2EEViewerSorter extends ViewerSorter {

	/**
	 * Constructor for J2EEViewerSorter.
	 */
	public J2EEViewerSorter() {
		super();
	}

	private static final String WST = "org.eclipse.wst"; //$NON-NLS-1$
	private static final String JST = "org.eclipse.jst"; //$NON-NLS-1$

	/**
	 * @see ViewerSorter#compare(Viewer, Object, Object)
	 */
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		// Check to see if each object is a base WST or JST core contribution
		boolean e1_isCore = isCore(e1);
		boolean e2_isCore = isCore(e2);
		// If both elements are from the same category, do default comparison
		if (e1_isCore == e2_isCore)
			return super.compare(viewer, e1, e2);
		// If e1 is core, but not e2, e1 is less than e2
		else if (e1_isCore)
			return -1;
		// If e1 is not core, but e2 is, e1 is greater than e2
		else
			return 1;
	}
	
	/**
	 * Any contribution from the base JST or WST packages is considered core, everything else is
	 * an extension
	 * @param element
	 * @return boolean is Contribution Core?
	 */
	private boolean isCore(Object element) {
		if (element != null) {
			Class clazz = element.getClass();
			if (clazz != null) {
				Package pack = clazz.getPackage();
				if (pack != null) {
					String packageName = pack.getName();
					return packageName!=null && (packageName.startsWith(WST) || packageName.startsWith(JST));
				}
			}
		}
		return false;
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
