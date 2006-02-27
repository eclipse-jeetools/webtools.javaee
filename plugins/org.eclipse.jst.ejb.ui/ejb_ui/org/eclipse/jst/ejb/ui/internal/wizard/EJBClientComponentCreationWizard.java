/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbClientJarCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizard;

public class EJBClientComponentCreationWizard extends DataModelWizard {
	private static final String PAGE_ONE = "pageOne"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public EJBClientComponentCreationWizard(IDataModel model) {
		super(model);
		setWindowTitle(EJBUIMessages.EJB_Client_Title);
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.CLIENT_BANNER));
	}

	/**
	 *  
	 */
	public EJBClientComponentCreationWizard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		addPage(new EJBClientComponentCreationWizardPage(getDataModel(), PAGE_ONE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#runForked()
	 */
	protected boolean runForked() {
		return false;
	}

    protected IDataModelProvider getDefaultProvider() {
        return new EjbClientJarCreationDataModelProvider();
    }

}