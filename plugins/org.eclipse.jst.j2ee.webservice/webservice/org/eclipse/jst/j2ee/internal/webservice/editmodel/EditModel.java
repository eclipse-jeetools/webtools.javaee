/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.editmodel;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;


public abstract class EditModel {
	protected Resource fResource;
	protected ResourceSet fResourceSet;
	protected IProject fProject;
	protected IFile fInputFile;
	protected BasicCommandStack fCommandStack;
	protected CompositeEditModel parent_;


	public void setResourceSet(ResourceSet rs) {
		fResourceSet = rs;
	}

	public IProject getProject() {
		return fProject;
	}

	public void setProject(IProject p) {
		fProject = p;
	}

	public void setInputFile(IFile f) {
		fInputFile = f;
	}

	public BasicCommandStack getCommandStack() {
		return fCommandStack;
	}

	public void setCommandStack(BasicCommandStack stack) {
		fCommandStack = stack;
	}

	public void setParent(CompositeEditModel compEditModel) {
		parent_ = compEditModel;
	}

	public CompositeEditModel getParent() {
		return parent_;
	}

	public abstract Resource getModelResource(String descriptorName);

	public abstract EObject getRootModelObject(String descriptorName);

	public abstract EObject getRootModelObject();

	public abstract Resource getRootModelResource();

}