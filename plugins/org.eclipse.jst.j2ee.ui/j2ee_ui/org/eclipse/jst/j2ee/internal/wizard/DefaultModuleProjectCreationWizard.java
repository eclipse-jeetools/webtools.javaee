/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 13, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.application.operations.DefaultModuleProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.DefaultModuleProjectCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;
import org.eclipse.wst.internal.common.frameworks.ui.WTPWizard;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class DefaultModuleProjectCreationWizard extends WTPWizard {
	private static final String SELECTION_PG = "selection"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public DefaultModuleProjectCreationWizard(DefaultModuleProjectCreationDataModel model) {
		super(model);
		initialize();
	}

	/**
	 *  
	 */
	public DefaultModuleProjectCreationWizard() {
		super();
		initialize();
	}

	/**
	 *  
	 */
	private void initialize() {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_MOD_WIZ_TITLE));
		String iconPath = "icons/full/"; //$NON-NLS-1$
		try {
			URL installURL = IDEWorkbenchPlugin.getDefault().getDescriptor().getInstallURL();
			URL url = new URL(installURL, iconPath + "wizban/new_wiz.gif"); //$NON-NLS-1$
			ImageDescriptor desc = ImageDescriptor.createFromURL(url);
			setDefaultPageImageDescriptor(desc);
		} catch (MalformedURLException e) {
			// Should not happen. Ignore.
		}
		setNeedsProgressMonitor(true);
		setForcePreviousAndNextButtons(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		return new DefaultModuleProjectCreationDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createOperation() {
		if (model.getBooleanProperty(DefaultModuleProjectCreationDataModel.ENABLED))
			return new DefaultModuleProjectCreationOperation((DefaultModuleProjectCreationDataModel) model);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		addPage(new NewModuleProjectSelectionPage((DefaultModuleProjectCreationDataModel) model, SELECTION_PG));
	}

	public boolean canFinish() {
		if (!super.canFinish()) {
			return false;
		}
		return model.getBooleanProperty(DefaultModuleProjectCreationDataModel.ENABLED);
	}
}