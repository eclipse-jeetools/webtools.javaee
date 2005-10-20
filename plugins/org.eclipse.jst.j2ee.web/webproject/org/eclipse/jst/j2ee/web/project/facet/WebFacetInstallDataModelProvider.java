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

import java.util.Set;

import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class WebFacetInstallDataModelProvider extends FacetInstallDataModelProvider implements IWebFacetInstallDataModelProperties {

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(EAR_PROJECT_NAME);
		names.add(CONTEXT_ROOT);
		names.add(CONTENT_DIR);
		names.add(CREATE_WEB_INF_SRC);
		return names;
	}
	
	public Object getDefaultProperty(String propertyName) {
		if(propertyName.equals(CONTENT_DIR)){
			return "web";
		} else if(propertyName.equals(CREATE_WEB_INF_SRC)){
			return Boolean.FALSE;
		} else if(propertyName.equals(CONTEXT_ROOT)){
			return getProperty(FACET_PROJECT_NAME);
		} else if(propertyName.equals(FACET_ID)){
			return J2EEProjectUtilities.DYNAMIC_WEB;
		}
		return super.getDefaultProperty(propertyName);
	}
	
	public IDataModelOperation getDefaultOperation() {
		return new WebFacetInstallOperation(model);
	}
	
}
