/*******************************************************************************
 * Copyright (c) 2003, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEJBComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBComponentImportOperation;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * This dataModel is used for to import EJB Modules (from EJB Jar files) into the workspace.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @plannedfor WTP 1.0
 */
public final class EJBComponentImportDataModelProvider extends J2EEComponentImportDataModelProvider implements IEJBComponentImportDataModelProperties {

	@Override
	protected int getType() {
		return XMLResource.EJB_TYPE;
	}

	@Override
	protected void handleUnknownType(JavaEEQuickPeek jqp) {
		jqp.setType(J2EEVersionConstants.EJB_TYPE);
		jqp.setVersion(J2EEVersionConstants.EJB_3_2_ID);
		jqp.setJavaEEVersion(J2EEVersionConstants.JEE_7_0_ID);
	}
	
	@Override
	public IDataModelOperation getDefaultOperation() {
		return new EJBComponentImportOperation(model);
	}

	@Override
	protected IDataModel createJ2EEComponentCreationDataModel() {
		IDataModel ejbCreationDM = DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
		
		FacetDataModelMap map = (FacetDataModelMap) ejbCreationDM.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel ejbFacetDataModel = map.getFacetDataModel( J2EEProjectUtilities.EJB );
		ejbFacetDataModel.setBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT, false);
		return ejbCreationDM;
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
	protected void refreshInterpretedSpecVersion(){
		IDataModel moduleDM = model.getNestedModel(NESTED_MODEL_J2EE_COMPONENT_CREATION);
		FacetDataModelMap map = (FacetDataModelMap) moduleDM.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel ejbFacetDataModel = map.getFacetDataModel( J2EEProjectUtilities.EJB );
		int version = getInterpretedSpecVersion(getArchiveWrapper()).getVersion();
		String versionText = J2EEVersionUtil.getEJBTextVersion( version );
		ejbFacetDataModel.setStringProperty(IFacetDataModelProperties.FACET_VERSION_STR, versionText);
		updateWorkingCopyFacetVersion(moduleDM, ejbFacetDataModel);
		updateJavaFacetVersion();
	}

	@Override
	public void init() {
		super.init();
		IDataModel componentCreationDM = model.getNestedModel(NESTED_MODEL_J2EE_COMPONENT_CREATION);
		FacetDataModelMap map = (FacetDataModelMap) componentCreationDM.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel ejbFacet = map.getFacetDataModel( IJ2EEFacetConstants.EJB );	
		ejbFacet.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, false);
	}
	
	@Override
	protected ArchiveOptions getArchiveOptions(IPath archivePath) throws ArchiveOpenFailureException {
		ArchiveOptions archiveOptions = super.getArchiveOptions(archivePath);
		archiveOptions.setOption(JavaEEArchiveUtilities.DISCRIMINATE_MAIN_CLASS, Boolean.FALSE);
		return archiveOptions;
	}
}
