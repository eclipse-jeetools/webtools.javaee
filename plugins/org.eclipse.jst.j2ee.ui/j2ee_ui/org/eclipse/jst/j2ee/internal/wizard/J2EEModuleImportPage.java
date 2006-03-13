/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Dec 4, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.ui.project.facet.EarSelectionPanel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class J2EEModuleImportPage extends J2EEImportPage {
	/**
	 * @param model
	 * @param pageName
	 */
	
	protected EarSelectionPanel earPanel;
	
	public J2EEModuleImportPage(IDataModel model, String pageName) {
		super(model, pageName);
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, getInfopopID());
		createServerEarAndStandaloneGroup(composite);
		createAnnotationsStandaloneGroup(composite);
		return composite;
	}

	/**
	 * @param composite
	 */
	protected void createAnnotationsStandaloneGroup(Composite composite) {
	}

	protected abstract String getModuleFacetID();	
	/**
	 * @param composite
	 */
	
	private void createServerEarAndStandaloneGroup(Composite composite) {
		IDataModel creationDM = getDataModel().getNestedModel(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION);
		FacetDataModelMap map = (FacetDataModelMap) creationDM.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel facetModel = (IDataModel) map.get(getModuleFacetID());
		
		//new ServerEarAndStandaloneGroup(composite, facetModel, synchHelper);
		
		earPanel = new EarSelectionPanel(facetModel, composite);
	}


	protected String[] getValidationPropertyNames() {
		return new String[]{IJ2EEComponentImportDataModelProperties.FILE_NAME,
					IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME,
					IFacetProjectCreationDataModelProperties.FACET_RUNTIME,
					IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME,
					IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR};
	}

	public void dispose() {
		super.dispose();
		if (earPanel != null)
			earPanel.dispose();
	}
	
}
