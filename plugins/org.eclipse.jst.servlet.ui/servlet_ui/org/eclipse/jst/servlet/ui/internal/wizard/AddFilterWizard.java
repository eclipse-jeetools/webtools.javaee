/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.ADD_FILTER_WIZARD_PAGE_TITLE;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.ADD_FILTER_WIZARD_WINDOW_TITLE;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.NewFilterClassDataModelProvider;
import org.eclipse.jst.servlet.ui.IWebUIContextIds;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/**
 * New servlet filter wizard
 */
public class AddFilterWizard extends NewWebArtifactWizard {
	
    public AddFilterWizard() {
        this(null);
    }

	public AddFilterWizard(IDataModel model) {
		super(model);
	}

	@Override
	protected String getTitle() {
		return ADD_FILTER_WIZARD_WINDOW_TITLE;
	}

	@Override
	protected ImageDescriptor getImage() {
		return getImageFromJ2EEPlugin("newfilter_wiz"); //$NON-NLS-1$
	}
    
    @Override
	public void doAddPages() {
		NewFilterClassWizardPage page1 = new NewFilterClassWizardPage(
		        getDataModel(),
		        PAGE_ONE,
		        NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				ADD_FILTER_WIZARD_PAGE_TITLE, 
				J2EEProjectUtilities.DYNAMIC_WEB);
		page1.setInfopopID(IWebUIContextIds.WEBEDITOR_FILTER_PAGE_ADD_FILTER_WIZARD_1);
		addPage(page1);

		AddFilterWizardPage page2 = new AddFilterWizardPage(getDataModel(), PAGE_TWO);
		page2.setInfopopID(IWebUIContextIds.WEBEDITOR_FILTER_PAGE_ADD_FILTER_WIZARD_2);
		addPage(page2);
        
        NewFilterClassOptionsWizardPage page3 = new NewFilterClassOptionsWizardPage(
                getDataModel(), 
                PAGE_THREE,
                NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC,
                ADD_FILTER_WIZARD_PAGE_TITLE);
        page3.setInfopopID(IWebUIContextIds.WEBEDITOR_FILTER_PAGE_ADD_FILTER_WIZARD_3);
        addPage(page3);
	}
    
	@Override
	protected IDataModelProvider getDefaultProvider() {
		return new NewFilterClassDataModelProvider();
	}

    @Override
    protected void postPerformFinish() throws InvocationTargetException {
    	//open new filter class in java editor
        openJavaClass();
    }
	
}
