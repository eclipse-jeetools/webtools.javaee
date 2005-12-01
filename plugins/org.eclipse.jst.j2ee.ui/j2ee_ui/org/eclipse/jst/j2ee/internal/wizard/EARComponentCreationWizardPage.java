/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EARComponentCreationWizardPage extends J2EEComponentCreationWizardPage {

//	private static final int SIZING_TEXT_FIELD_WIDTH = 250;
	
	/**
	 * @param model
	 * @param pageName
	 */
	public EARComponentCreationWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_WIZ_BANNER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEModuleCreationPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected void addToAdvancedComposite(Composite advanced) {
		super.addToAdvancedComposite(advanced);
	}

	//TODO: utility to handle additions
	protected String[] getValidationPropertyNames() {
        return new String[] {IComponentCreationDataModelProperties.PROJECT_NAME, COMPONENT_NAME, COMPONENT_VERSION, IJavaComponentCreationDataModelProperties.RUNTIME_TARGET_ID };
    }

	public void dispose() {
		super.dispose();
	}
}