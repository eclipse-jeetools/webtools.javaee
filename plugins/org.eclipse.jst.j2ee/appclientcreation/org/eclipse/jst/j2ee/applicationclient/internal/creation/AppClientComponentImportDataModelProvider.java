/*******************************************************************************
 * Copyright (c) 2003, 2017 IBM Corporation and others.
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
import org.eclipse.jst.j2ee.datamodel.properties.IAppClientComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.AppClientComponentImportOperation;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
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

	@Override
	protected int getType() {
		return XMLResource.APP_CLIENT_TYPE;
	}

	@Override
	protected void handleUnknownType(JavaEEQuickPeek jqp) {
		jqp.setType(J2EEVersionConstants.APPLICATION_CLIENT_TYPE);
		jqp.setVersion(J2EEVersionConstants.JEE_8_0_ID);
		jqp.setJavaEEVersion(J2EEVersionConstants.JEE_8_0_ID);
	}

	
	@Override
	public IDataModelOperation getDefaultOperation() {
		return new AppClientComponentImportOperation(model);
	}

	@Override
	protected IDataModel createJ2EEComponentCreationDataModel() {
		IDataModel appClientDataModel = DataModelFactory.createDataModel(new AppClientFacetProjectCreationDataModelProvider());
		FacetDataModelMap map = (FacetDataModelMap)appClientDataModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		//need to ensure no default Main class is created during import
		map.getFacetDataModel(J2EEProjectUtilities.APPLICATION_CLIENT).setBooleanProperty(IAppClientFacetInstallDataModelProperties.CREATE_DEFAULT_MAIN_CLASS, false);
		return appClientDataModel;
	}

	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean set = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(ARCHIVE_WRAPPER)) {
			if (getArchiveWrapper() != null) {
				refreshInterpretedSpecVersion();
				model.notifyPropertyChange(PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
			}
		}
		return set;
	}

	@Override
	protected void refreshInterpretedSpecVersion() {
		IDataModel moduleDM = model.getNestedModel(NESTED_MODEL_J2EE_COMPONENT_CREATION);
		FacetDataModelMap map = (FacetDataModelMap) moduleDM.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel appClientFacetDataModel = map.getFacetDataModel( J2EEProjectUtilities.APPLICATION_CLIENT );
		int version = getInterpretedSpecVersion(getArchiveWrapper()).getVersion();
		String versionText = J2EEVersionUtil.getJ2EETextVersion( version );
		appClientFacetDataModel.setStringProperty(IFacetDataModelProperties.FACET_VERSION_STR, versionText);
		updateWorkingCopyFacetVersion(moduleDM, appClientFacetDataModel);
		updateJavaFacetVersion();
	}
	
	@Override
	public void init() {
		super.init();
		IDataModel componentCreationDM = model.getNestedModel(NESTED_MODEL_J2EE_COMPONENT_CREATION);
		FacetDataModelMap map = (FacetDataModelMap) componentCreationDM.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel appClientFacet = map.getFacetDataModel( IJ2EEFacetConstants.APPLICATION_CLIENT );	
		appClientFacet.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, false);
	}
}
