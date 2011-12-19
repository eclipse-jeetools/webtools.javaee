/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
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

import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEModuleImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

/**
 * This dataModel is a common super class used to import J2EE Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @plannedfor WTP 1.0
 */
public abstract class J2EEComponentImportDataModelProvider extends J2EEArtifactImportDataModelProvider implements IJ2EEModuleImportDataModelProperties {


	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		return propertyNames;
	}

	protected int getModuleSpecVersion() {
		ArchiveWrapper wrapper = getArchiveWrapper();
		return wrapper.getJavaEEQuickPeek().getVersion();
	}

	/**
	 * Updates the Java Facet Version so it is compliant with the Java EE Module version 
	 */
	protected void updateJavaFacetVersion() {
		IProjectFacetVersion javaFacetVersion = null;
		IRuntime runtime = (IRuntime)getProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME);
		if(runtime != null){
			if(runtime.supports(JavaFacet.VERSION_1_7)){
				javaFacetVersion = JavaFacet.VERSION_1_7;
			} else if(runtime.supports(JavaFacet.VERSION_1_6)){
				javaFacetVersion = JavaFacet.VERSION_1_6;
			} else if(runtime.supports(JavaFacet.VERSION_1_5)){
				javaFacetVersion = JavaFacet.VERSION_1_5;
			} else {
				javaFacetVersion = JavaFacet.VERSION_1_4;
			}
		} else {
			JavaEEQuickPeek jqp = getInterpretedSpecVersion(getArchiveWrapper());
			int javaEEVersion = jqp.getJavaEEVersion();
			switch (javaEEVersion){
			case J2EEVersionConstants.J2EE_1_2_ID:
			case J2EEVersionConstants.J2EE_1_3_ID:
			case J2EEVersionConstants.J2EE_1_4_ID:
				javaFacetVersion = JavaFacet.VERSION_1_4;
				break;
			case J2EEVersionConstants.JEE_5_0_ID:
				javaFacetVersion = JavaFacet.VERSION_1_5;
				break;
			case J2EEVersionConstants.JEE_6_0_ID:
				javaFacetVersion = JavaFacet.VERSION_1_6;
				break;
			}
		}
		if(javaFacetVersion != null){
			IDataModel moduleDM = model.getNestedModel(NESTED_MODEL_J2EE_COMPONENT_CREATION);
			FacetDataModelMap map = (FacetDataModelMap) moduleDM.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			IDataModel javaFacetDataModel = map.getFacetDataModel( J2EEProjectUtilities.JAVA );
			javaFacetDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION, javaFacetVersion);
			updateWorkingCopyFacetVersion(moduleDM, javaFacetDataModel);
		}
	}
}
