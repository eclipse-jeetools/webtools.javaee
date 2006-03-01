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
package org.eclipse.jst.j2ee.refactor.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.internal.operations.RemoveComponentFromEnterpriseApplicationDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class RemoveDeletedComponentFromEARDataModelProvider extends RemoveComponentFromEnterpriseApplicationDataModelProvider {

	private final ProjectRefactorMetadata _metadata;
	public RemoveDeletedComponentFromEARDataModelProvider(final ProjectRefactorMetadata metadata) {
		super();
		_metadata = metadata;
	}

	public Object getDefaultProperty(String propertyName) {
		if (TARGET_COMPONENTS_TO_URI_MAP.equals(propertyName)) {
			Map map = new HashMap();
			List components = (List) getProperty(TARGET_COMPONENT_LIST);
			for (int i = 0; i < components.size(); i++) {
				IVirtualComponent component = (IVirtualComponent) components.get(i);
				IProject project = component.getProject();
				String name = component.getName();
				if (_metadata.isWeb()) {
					name += ".war"; //$NON-NLS-1$			
				} else if (_metadata.isEJB()) {
					name += ".jar"; //$NON-NLS-1$			
				} else if (_metadata.isAppClient()) {
					name += ".jar"; //$NON-NLS-1$			
				} else if (_metadata.isConnector()) {
					name += ".rar"; //$NON-NLS-1$			
				}
				map.put(component, name);
			}
			return map;
		}
		return super.getDefaultProperty(propertyName);
	}
}
