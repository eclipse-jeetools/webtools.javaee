/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;

public interface IAddComponentToEnterpriseApplicationDataModelProperties extends ICreateReferenceComponentsDataModelProperties {

	/**
	 * Returns a Map which maps Components in the TARGET_COMPONENT_LIST list to the name Strings
	 * that should be used when adding them to the ear.
	 */
	public static final String TARGET_COMPONENTS_TO_URI_MAP = "IAddComponentToEnterpriseApplicationDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP";

}
