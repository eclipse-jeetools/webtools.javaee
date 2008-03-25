/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.TRANSACTION_TYPE;

import org.eclipse.jst.j2ee.internal.common.operations.CreateJavaEEArtifactTemplateModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateEnterpriseBeanTemplateModel extends
		CreateJavaEEArtifactTemplateModel {
	
	protected static final String ATT_MAPPED_NAME = "mappedName"; //$NON-NLS-1$
	protected static final String ATT_NAME = "name"; //$NON-NLS-1$
	
	protected static final String QUOTATION_STRING = "\"";
	
	public CreateEnterpriseBeanTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}
    
    public boolean isContainerType() {
		int transactionType = dataModel.getIntProperty(TRANSACTION_TYPE);
		return transactionType == NewSessionBeanClassDataModelProvider.TRANSACTION_TYPE_CONTAINER_INDEX;
	}
}
