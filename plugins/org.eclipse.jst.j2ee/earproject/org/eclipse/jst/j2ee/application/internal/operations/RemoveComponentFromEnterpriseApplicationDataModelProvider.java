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
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class RemoveComponentFromEnterpriseApplicationDataModelProvider extends RemoveReferenceComponentsDataModelProvider implements IAddComponentToEnterpriseApplicationDataModelProperties {

	public RemoveComponentFromEnterpriseApplicationDataModelProvider() {
		super();
	}
	
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(TARGET_COMPONENTS_TO_URI_MAP);
		return propertyNames;
	}

	public IDataModelOperation getDefaultOperation() {
		return new RemoveComponentFromEnterpriseApplicationOperation(model);
	}
	
	public Object getDefaultProperty(String propertyName) {
		if (TARGET_COMPONENTS_TO_URI_MAP.equals(propertyName)) {
			final Map map = new HashMap();
			final List components = (List) getProperty(TARGET_COMPONENT_LIST);
			for (int i = 0; i < components.size(); i++) {
				final IVirtualComponent component = (IVirtualComponent) components.get(i);
				map.put(component, AddComponentToEnterpriseApplicationDataModelProvider.getComponentURI(component));
			}
			return map;
		}
		return super.getDefaultProperty(propertyName);
	}
}
