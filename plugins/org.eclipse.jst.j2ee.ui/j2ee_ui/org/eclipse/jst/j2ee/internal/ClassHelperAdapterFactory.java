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
package org.eclipse.jst.j2ee.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jst.j2ee.internal.ejb.provider.J2EEJavaClassProviderHelper;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;

public class ClassHelperAdapterFactory implements IAdapterFactory {
	
	private static final Class IFILE_CLASS = IFile.class;
	private static final Class IRESOURCE_CLASS = IResource.class;
	
	private static final Class[] ADAPTER_TYPES = new Class[] {
		IFILE_CLASS, IRESOURCE_CLASS
	};

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if(IRESOURCE_CLASS == adapterType || IFILE_CLASS == adapterType) {
			if(adaptableObject instanceof J2EEJavaClassProviderHelper) {
				J2EEJavaClassProviderHelper provider = (J2EEJavaClassProviderHelper) adaptableObject;
				try {
					IResource resource = (IResource) provider.getAdapter(IRESOURCE_CLASS);
					return resource;
				} catch (RuntimeException e) {
					J2EEUIPlugin.logError(0, e.getMessage(), e);
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return ADAPTER_TYPES;
	}

}
