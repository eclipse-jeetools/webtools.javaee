/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.web.datamodel.properties;

import org.eclipse.jst.j2ee.internal.web.operations.ConvertStaticWebToDyamnicDataModelProvider;

public interface IConvertStaticWebToDyamnicDataModelProperties {

	/**
	 * This field should not be used.  It is not part of the API and may be modified in the future.
	 */
	public static Class _provider_class = ConvertStaticWebToDyamnicDataModelProvider.class;

	public static final String PROJECT = "IConvertStaticWebToDyamnicProperties.PROJECT"; //$NON-NLS-1$
	public static final String CONVERT_TO_WEB_VERSION = "IConvertStaticWebToDyamnicProperties.CONVERT_TO_WEB_VERSION"; //$NON-NLS-1$
	
}
