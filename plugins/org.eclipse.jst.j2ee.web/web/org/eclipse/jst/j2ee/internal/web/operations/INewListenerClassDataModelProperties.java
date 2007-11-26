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
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;

public interface INewListenerClassDataModelProperties extends INewJavaClassDataModelProperties, IAnnotationsDataModel {
	
	/**
	 * Optional, boolean property used to specify whether or not to generate a new java class.
	 * The default is false.
	 */
	public static final String USE_EXISTING_CLASS = "NewListenerClassDataModel.USE_EXISTING_CLASS"; //$NON-NLS-1$
	
}
