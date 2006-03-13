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
package org.eclipse.jst.j2ee.applicationclient.internal.creation;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.datamodel.properties.IAppClientComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.AppClientComponentImportOperation;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;


public final class AppClientComponentImportDataModelProvider extends J2EEComponentImportDataModelProvider implements IAppClientComponentImportDataModelProperties {

	public AppClientComponentImportDataModelProvider() {
		super();
	}

	protected int getType() {
		return XMLResource.APP_CLIENT_TYPE;
	}

	protected Archive openArchive(String uri) throws OpenFailureException {
		return CommonarchiveFactory.eINSTANCE.openApplicationClientFile(getArchiveOptions(), uri);
	}

	public IDataModelOperation getDefaultOperation() {
		return new AppClientComponentImportOperation(model);
	}

	protected IDataModel createJ2EEComponentCreationDataModel() {
		return DataModelFactory.createDataModel(new AppClientFacetProjectCreationDataModelProvider());
		
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean set = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(FILE)) {
			IDataModel moduleDM = model.getNestedModel(NESTED_MODEL_J2EE_COMPONENT_CREATION);
			if (getModuleFile() != null) {
				FacetDataModelMap map = (FacetDataModelMap) moduleDM.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
				IDataModel appClientFacetDataModel = map.getFacetDataModel( J2EEProjectUtilities.APPLICATION_CLIENT );

				int version = getModuleSpecVersion();
				String versionText = J2EEVersionUtil.getJ2EETextVersion( version );
				appClientFacetDataModel.setStringProperty(IFacetDataModelProperties.FACET_VERSION_STR, versionText);
				model.notifyPropertyChange(PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
			}
		}
		return set;
	}	
}
