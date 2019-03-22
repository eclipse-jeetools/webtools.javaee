/*******************************************************************************
 * Copyright (c) 2009 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.internal.adapter;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;

public abstract class JavaEEArtifactAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IJavaElement.class) {
			IJavaProject javaProject = getJavaProject(adaptableObject);
			String className = getFullyQualifiedClassName(adaptableObject);
			return getJavaElement(javaProject, className);
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] { IJavaElement.class };
	}

	protected abstract String getFullyQualifiedClassName(Object adaptableObject);
	
	private IJavaProject getJavaProject(Object adaptableObject) {
		IResource resource = getResource(adaptableObject);
		if (resource != null) {
			IProject project = resource.getProject();
			return JavaCore.create(project);
		}
		return null;
	}
	
	private IResource getResource(Object adaptableObject) {
		if (adaptableObject instanceof IAdaptable) {
			return ((IAdaptable) adaptableObject).getAdapter(IResource.class);
		} 
		return Platform.getAdapterManager().getAdapter(adaptableObject, IResource.class);
	}
	
	private IJavaElement getJavaElement(IJavaProject javaProject, String className) {
		if (className != null && javaProject != null && javaProject.exists()) {
			try {
				return javaProject.findType(className);
			} catch (JavaModelException e) {
				J2EECorePlugin.logError(e);
			}
		}
		return null;
	}

}
