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
package org.eclipse.jst.j2ee.web.project.facet;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class JavaFacetInstallDataModelProvider extends FacetInstallDataModelProvider {

	public JavaFacetInstallDataModelProvider() {
		super();
	}

	public IDataModelOperation getDefaultOperation() {
		return new JavaFacetInstalOperation(model);
	}

	public Object getDefaultProperty(String propertyName) {
		if(FACET_ID.equals(propertyName)){
			return "java";
		}
		return super.getDefaultProperty(propertyName);
	}
	
}
