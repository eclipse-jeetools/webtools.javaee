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
package org.eclipse.jst.j2ee.application.internal.operations;

/**
 * The IAnnotationsDataModel is data model interface used by other data models in
 * the WTP wizard and operation framework.
 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel
 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperation
 * 
 * This provides the USE_ANNOTATIONS property which is used to determine whether or
 * not artifacts should be created using annotations or not.
 * 
 * Subclasses may extend this interface to add their own properties.
 * 
 * The use of this interface is EXPERIMENTAL and subject to substantial changes.
 * 
 */
public interface IAnnotationsDataModel {

	/**
	 * Optional, type Boolean to determine whether an artifact should be created with annotated tags.
	 * The default is set on the particular data model who implements this interface.
	 */
	public static final String USE_ANNOTATIONS = "IAnnotationsDataModel.useAnnotations"; //$NON-NLS-1$

}