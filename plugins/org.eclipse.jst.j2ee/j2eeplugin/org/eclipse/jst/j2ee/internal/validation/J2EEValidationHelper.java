/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.validation;


import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.validation.internal.operations.AWorkbenchHelper;


public abstract class J2EEValidationHelper extends AWorkbenchHelper {
	/**
	 * Constructor for J2EEValidationHelper.
	 */
	public J2EEValidationHelper() {
		super();
	}

	/*
	 * @see IWorkbenchHelper#getFile(Object)
	 */
	public IResource getResource(Object object) {
		Resource resource = null;
		if (object != null && object instanceof EObject)
			resource = ((EObject) object).eResource();
		if (resource != null)
			return WorkbenchResourceHelper.getFile(resource);
		return null;
	}

	/**
	 * Given a resource, return its non-eclipse-specific location. If this resource, or type of
	 * resource, isn't handled by this helper, return null.
	 */
	protected String getFileName(IFile file, IContainer[] containers) {
		if ((file == null) || (containers == null)) {
			return null;
		}

		for (int c = 0; c < containers.length; c++) {
			IPath resourcePath = getContainerRelativePath(file, containers[c]);
			if (resourcePath != null) {
				// file has been found
				return resourcePath.toString();
			}
		}

		return null;
	}

	/**
	 * Given a file name, return its corresponding IFile, if one exists. This method checks if the
	 * IContainer passed in contains a file with the given file name. If the container does not have
	 * the IFile, null is returned.
	 */
	protected IFile getFile(String fileName, IContainer[] containers) {
		IFile clazzFile = null;
		if ((fileName == null) || (containers == null)) {
			return null;
		}

		for (int c = 0; c < containers.length; c++) {
			IContainer container = containers[c];
			IPath filePath = container.getProjectRelativePath();
			clazzFile = getProject().getFile(filePath.append(fileName));

			if (clazzFile.exists()) {
				return clazzFile;
			}
		}

		return null;
	}

	protected IFile getXmlFile(String xmlFileName, J2EENature nature) {
		if (nature == null) {
			return null;
		}
		return nature.getMetaFolder().getFile(xmlFileName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.validation.internal.operations.IWorkbenchHelper#getTargetObjectName(java.lang.Object)
	 */
	public String getTargetObjectName(Object object) {
		if (object != null)
			return object.toString();
		return null;
	}

}