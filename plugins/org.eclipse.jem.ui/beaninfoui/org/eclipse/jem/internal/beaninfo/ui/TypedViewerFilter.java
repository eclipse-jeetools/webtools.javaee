/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: TypedViewerFilter.java,v $
 *  $Revision: 1.1 $  $Date: 2005/10/03 23:06:42 $ 
 */
package org.eclipse.jem.internal.beaninfo.ui;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
 

public class TypedViewerFilter extends ViewerFilter {
	private Class[] acceptedClasses;
	private Object[] rejectedFragments;
	
	public TypedViewerFilter(Class[] acceptedClasses, Object[] rejectedFragments) {
		this.acceptedClasses = acceptedClasses;
		this.rejectedFragments = rejectedFragments;
	}

	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (rejectedFragments != null) {
			for (int i= 0; i < rejectedFragments.length; i++) {
				if (element.equals(rejectedFragments[i])) {
					return false;
				}
			}
		}
		for (int i= 0; i < acceptedClasses.length; i++) {
			if (acceptedClasses[i].isInstance(element)) {
				return true;
			}
		}
		return false;
	}
}
