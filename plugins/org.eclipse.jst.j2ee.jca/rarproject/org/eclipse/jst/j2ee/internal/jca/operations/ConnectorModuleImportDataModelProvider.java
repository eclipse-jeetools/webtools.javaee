/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.operations;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleImportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * This dataModel is used for to import Connector Modules (from RAR files) into the workspace.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class ConnectorModuleImportDataModelProvider extends J2EEModuleImportDataModelProvider {

	public void init() {
		super.init();
		setIntProperty(IConnectorComponentCreationDataModelProperties.COMPONENT_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
	}

	protected int getType() {
		return XMLResource.RAR_TYPE;
	}

	protected Archive openArchive(String uri) throws OpenFailureException {
		Archive archive = CommonarchiveFactory.eINSTANCE.openRARFile(getArchiveOptions(), uri);
		return archive;
	}

	protected IDataModel createJ2EEComponentCreationDataModel() {
		return DataModelFactory.createDataModel(new ConnectorComponentCreationDataModelProvider());
	}

	public IDataModelOperation getDefaultOperation() {
		return new ConnectorModuleImportOperationNew(model);
	}

}