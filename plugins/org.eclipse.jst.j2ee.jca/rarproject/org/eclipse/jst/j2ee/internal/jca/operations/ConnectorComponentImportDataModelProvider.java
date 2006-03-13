/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.operations;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.jca.project.facet.ConnectorFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * This dataModel is used for to import Connector Modules (from RAR files) into the workspace.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @plannedfor WTP 1.0
 */
public final class ConnectorComponentImportDataModelProvider extends J2EEComponentImportDataModelProvider {

	public void init() {
		super.init();
		//setIntProperty(IConnectorComponentCreationDataModelProperties.COMPONENT_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
	}

	protected int getType() {
		return XMLResource.RAR_TYPE;
	}

	protected Archive openArchive(String uri) throws OpenFailureException {
		Archive archive = CommonarchiveFactory.eINSTANCE.openRARFile(getArchiveOptions(), uri);
		return archive;
	}

	protected IDataModel createJ2EEComponentCreationDataModel() {
		return DataModelFactory.createDataModel(new ConnectorFacetProjectCreationDataModelProvider());		
	}

	public IDataModelOperation getDefaultOperation() {
		return new ConnectorComponentImportOperation(model);
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean set = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(FILE)) {
			IDataModel moduleDM = model.getNestedModel(NESTED_MODEL_J2EE_COMPONENT_CREATION);
			if (getModuleFile() != null) {
				FacetDataModelMap map = (FacetDataModelMap) moduleDM.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
				IDataModel jcaFacetDataModel = map.getFacetDataModel( J2EEProjectUtilities.JCA );

				int version = getModuleSpecVersion();
				String versionText = J2EEVersionUtil.getJCATextVersion( version );
				jcaFacetDataModel.setStringProperty(IFacetDataModelProperties.FACET_VERSION_STR, versionText);
				model.notifyPropertyChange(PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
			}
		}
		return set;
	}	
}
