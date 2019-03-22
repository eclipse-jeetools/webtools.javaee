/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.refactor.operations;

import java.util.Map;

import org.eclipse.jst.j2ee.application.internal.operations.RemoveComponentFromEnterpriseApplicationOperation;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class RemoveDeletedComponentFromEAROperation extends RemoveComponentFromEnterpriseApplicationOperation {

	public RemoveDeletedComponentFromEAROperation(IDataModel model) {
		super(model);
	}
		
	@Override
	protected String getModuleURI(final IEARModelProvider earModel, final IVirtualComponent targetComponent) {
		String uri = earModel.getModuleURI(targetComponent);
		if (uri == null) {
			Map compToURI = (Map) model.getProperty(RemoveDeletedComponentFromEARDataModelProvider.TARGET_COMPONENTS_TO_URI_MAP); 
			uri = (String) compToURI.get(targetComponent);
		}
		return uri;
	}
}
