/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.application.internal.operations;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
//TODO this is temporary until we have jee 5 model support ready
public class AddComponentToEnterpriseApplicationDataModelProvider extends org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider {

	public AddComponentToEnterpriseApplicationDataModelProvider() {
		super();
	}

	public IDataModelOperation getDefaultOperation() {
		return new AddComponentToEnterpriseApplicationOp(model);
	}

}
