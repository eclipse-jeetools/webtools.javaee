/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 25, 2004
 */
package org.eclipse.jst.common.internal.annotations.controller;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

/**
 * Annotations Controller interface used for communicating with emitters and determining available
 * tag sets
 */
public interface AnnotationsController {

	/**
	 * Determines if a tag handler is installed for the specified tag
	 * 
	 * @param tagset
	 *            the name of a tagset (e.g. "ejb")
	 * @return true only if the given tagset is available and enabled
	 */
	public boolean isTagHandlerInstalled(String tagset);

	/**
	 * Disables annotation processing for the specified resource
	 * 
	 * @param modelObject
	 *            The Annotated EMF Object
	 * @param tagset
	 *            The name of the annotations tagset to disable on the object
	 * @return an IStatus representing success or failure
	 */
	public IStatus disableAnnotations(EObject modelObject, String tagset);

	/**
	 * Returns the associated annotated file if the specified model object was generated via
	 * annotations from a parent resource and is enabled
	 * 
	 * @param modelObject
	 *            The Annotated EMF Object
	 * @return the annotated source file associated with the given modelObject
	 */
	public IFile getEnabledAnnotationFile(EObject modelObject);

	/**
	 * Process the annotations on the given resource during creation
	 * 
	 * @return all files touched by the annotations processing
	 * @throws CoreException
	 *             if a problem occurs while processing
	 */
	public IFile[] process(IResource res) throws CoreException;

	/**
	 * Process the annotations on the given resource array
	 * 
	 * @return all files touched by the annotations processing
	 * @throws CoreException
	 *             if a problem occurs while processing
	 */
	public IFile[] process(IResource[] res) throws CoreException;

	/**
	 * Provides the annotation processor an opportunity to initialize
	 */
	public void initialize(IProject project);

	/**
	 * Provides the annotation processor an opportunity to dispose and cleanup
	 */
	public void dispose();
}