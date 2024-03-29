/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;

// 
public class CompressedNodeAdapterFactory implements IAdapterFactory {
	
	private static final Class IJAVA_PROJECT_CLASS = IJavaProject.class;
	private static final Class IJAVA_ELEMENT_CLASS = IJavaElement.class;
	
	private static final Class[] ADAPTER_LIST = new Class[] { IJAVA_PROJECT_CLASS };

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if(adaptableObject instanceof CompressedJavaProject) {
			if(adapterType == IJAVA_PROJECT_CLASS) {
				return ((CompressedJavaProject)adaptableObject).getProject();
			} else if (adapterType == IJAVA_ELEMENT_CLASS) {
				return ((CompressedJavaProject)adaptableObject).getJavaElement();
			}
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() { 
		return ADAPTER_LIST;
	}

}
