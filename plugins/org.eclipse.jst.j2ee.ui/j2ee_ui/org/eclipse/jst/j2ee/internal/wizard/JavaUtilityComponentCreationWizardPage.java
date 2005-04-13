/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.web.internal.WSTWebPlugin;



/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JavaUtilityComponentCreationWizardPage extends DataModelWizardPage {
	public static final String PAGE_NAME = "JavaUtilityComponentCreationWizardPage";
	protected NewModuleGroupEx projectNameGroup;	
	
	/**
	 * @param model
	 * @param pageName
	 */
	protected JavaUtilityComponentCreationWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.JAVAUTILITY_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.JAVAUTILITY_MAIN_PG_DESC));
		ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin(WSTWebPlugin.PLUGIN_ID, "icons/full/wizban/newwprj_wiz.gif"); //$NON-NLS-1$
		setImageDescriptor(desc);
		setPageComplete(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.ui.DataModelWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{IComponentCreationDataModelProperties.COMPONENT_NAME};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.ui.DataModelWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		top.setLayout(new GridLayout());
		top.setData(new GridData(GridData.FILL_BOTH));
		Composite composite = new Composite(top, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
		
		createProjectNameGroup(composite);
		Composite detail = new Composite(top, SWT.NONE);
		detail.setLayout(new GridLayout());
		detail.setData(new GridData(GridData.FILL_BOTH));


		return top;
	}
	
	protected void createProjectNameGroup(Composite parent) {
		projectNameGroup = new NewModuleGroupEx(parent, SWT.NULL, model);
	}

}
