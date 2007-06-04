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
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.Set;

import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEModuleImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * This dataModel is a common super class used to import J2EE Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @plannedfor WTP 1.0
 */
public abstract class J2EEComponentImportDataModelProvider extends J2EEArtifactImportDataModelProvider implements IJ2EEModuleImportDataModelProperties {


	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EXTENDED_IMPORT_FACTORY);
		return propertyNames;
	}

	protected int getModuleSpecVersion() {
		ArchiveWrapper wrapper = getArchiveWrapper();
		return wrapper.getJavaEEQuickPeek().getVersion();
	}

	/**
	 * @deprecated use #IARCHIVE_WRAPPER
	 */
	protected ModuleFile getModuleFile() {
		return (ModuleFile) getArchiveFile();
	}
	
	/**
	 * Updates the Java Facet Version so it is compliant with the Java EE Module version 
	 */
	protected void updateJavaFacetVersion() {
		ArchiveWrapper wrapper = getArchiveWrapper();
		int javaEEVersion = wrapper.getJavaEEQuickPeek().getJavaEEVersion();
		IProjectFacetVersion javaFacetVersion = null;
		switch (javaEEVersion){
		case J2EEVersionConstants.J2EE_1_2_ID:
		case J2EEVersionConstants.J2EE_1_3_ID:
			javaFacetVersion = JavaFacetUtils.JAVA_13;
			break;
		case J2EEVersionConstants.J2EE_1_4_ID:
			javaFacetVersion = JavaFacetUtils.JAVA_14;
			break;
		case J2EEVersionConstants.JEE_5_0_ID:
			javaFacetVersion = JavaFacetUtils.JAVA_50;
			break;
		}
		if(javaFacetVersion != null){
			IDataModel moduleDM = model.getNestedModel(NESTED_MODEL_J2EE_COMPONENT_CREATION);
			FacetDataModelMap map = (FacetDataModelMap) moduleDM.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			IDataModel javaFacetDataModel = map.getFacetDataModel( J2EEProjectUtilities.JAVA );
			javaFacetDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION, javaFacetVersion);
		}
	}
}
