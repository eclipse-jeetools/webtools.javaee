/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class RemoveComponentFromEnterpriseApplicationDataModelProvider extends RemoveReferenceComponentsDataModelProvider implements IAddComponentToEnterpriseApplicationDataModelProperties {

	public RemoveComponentFromEnterpriseApplicationDataModelProvider() {
		super();
	}

	@Override
	public IDataModelOperation getDefaultOperation() {
		return new RemoveComponentFromEnterpriseApplicationOperation(model);
	}
}
