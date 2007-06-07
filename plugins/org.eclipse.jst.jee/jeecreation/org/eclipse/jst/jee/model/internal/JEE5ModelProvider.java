/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.model.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jem.util.emf.workbench.ProjectResourceSet;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

public class JEE5ModelProvider implements IModelProvider{

	protected XMLResourceImpl writableResource;
	protected IProject proj;
	protected IPath defaultResourcePath;

	public JEE5ModelProvider() {
		super();
	}

	protected ProjectResourceSet getResourceSet(IProject proj2) {
		return (ProjectResourceSet)WorkbenchResourceHelperBase.getResourceSet(proj);
	}

	public XMLResourceImpl getWritableResource() {
		return writableResource;
	}

	public void setWritableResource(XMLResourceImpl writableResource) {
		this.writableResource = writableResource;
	}

	protected XMLResourceImpl getModelResource(IPath modelPath) {
		if (writableResource != null)
			return writableResource;
		if (modelPath == null)
			modelPath = getDefaultResourcePath();
		ProjectResourceSet resSet = getResourceSet(proj);
		IVirtualFolder container = ComponentCore.createComponent(proj).getRootFolder();
		String modelPathURI = modelPath.toString();
		URI uri = URI.createURI(container.getProjectRelativePath().toString() + Path.SEPARATOR + modelPathURI);
		XMLResourceImpl res = null;
		try {
			if (proj.getFile(uri.toString()).exists())
				res = (XMLResourceImpl) resSet.getResource(uri,true);
		} catch (WrappedException ex) {
			if (ex.getCause() instanceof FileNotFoundException)
				return null;
			else throw ex;
		}
		return res;
	}

	public IPath getDefaultResourcePath() {
		return defaultResourcePath;
	}

	public void setDefaultResourcePath(IPath defaultResourcePath) {
		this.defaultResourcePath = defaultResourcePath;
	}

	public Object getModelObject() {
		return getModelObject(getDefaultResourcePath());
	}

	public Object getModelObject(IPath modelPath) {
		// TODO Auto-generated method stub
		return null;
	}


	public IStatus validateEdit(IPath modelPath, Object context) {
		if (modelPath == null)
			modelPath = getDefaultResourcePath();
		IWorkspace work = ResourcesPlugin.getWorkspace();
		IFile file = WorkbenchResourceHelper.getFile(getModelResource(modelPath));
		if (file != null) {
			IFile[] files = { file };
			if (context == null)
				context = IWorkspace.VALIDATE_PROMPT;
			return work.validateEdit(files, context);
		} else
			return Status.OK_STATUS;
	}

	public void modify(Runnable runnable, IPath modelPath) {
		//About to modify and save this model
		try {
			XMLResourceImpl res = getModelResource(modelPath);
			if (res != null)
				setWritableResource(res);
			runnable.run();
			try {
				if (res != null)
					res.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			setWritableResource(null);
		}
		
	}

}