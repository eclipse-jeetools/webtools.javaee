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
 * Created on Nov 7, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.jst.j2ee.internal.war.wizard;

import org.eclipse.core.internal.registry.ConfigurationElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.war.ui.util.WEBUIMessages;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebProjectCreationOperation;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleProjectCreationWizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WEBProjectWizard extends J2EEModuleProjectCreationWizard implements IExecutableExtension, INewWizard {
	private IConfigurationElement configData;

	public WEBProjectWizard(WebProjectCreationDataModel dataModel) {
		super(dataModel);
	}

	public WEBProjectWizard() {
		super();
	}

	public String getWizardID() {
		return "org.eclipse.jst.j2ee.internal.webProjectWizard"; //$NON-NLS-1$
	}

	public String getLocalID() {
		return "org.eclipse.jst.j2ee.internal.webProjectWizard"; //$NON-NLS-1$
	}

	protected void doAddPages() {
		WEBProjectCreationPage page = new WEBProjectCreationPage(getWEBProjectCreationDataModel(), MAIN_PG);
		page.setInfopopID("org.eclipse.jst.j2ee.internal.ui.webw1000"); //$NON-NLS-1$
		addPage(page);
		super.doAddPages();
	}

	protected WTPOperationDataModel createDefaultModel() {
		WebProjectCreationDataModel aModel = new WebProjectCreationDataModel();
		aModel.setBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR, true);
		return aModel;
	}

	protected WTPOperation createBaseOperation() {
		return new WebProjectCreationOperation(getWEBProjectCreationDataModel());
	}

	private WebProjectCreationDataModel getWEBProjectCreationDataModel() {
		return (WebProjectCreationDataModel) model;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(WEBUIMessages.getResourceString(WEBUIMessages.WEB_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_PROJECT_WIZARD_BANNER));
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		configData = config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#postPerformFinish()
	 */
	protected void postPerformFinish() {
		String finalPerspective = model.getStringProperty(J2EEProjectCreationDataModel.FINAL_PERSPECTIVE);
		if (finalPerspective == null || finalPerspective.equals("")) { //$NON-NLS-1$
			BasicNewProjectResourceWizard.updatePerspective(configData);
		} else {
			IConfigurationElement newConfig = new ConfigurationElement() {
				public String getAttribute(String aName) {
					if (aName.equals("finalPerspective")) //$NON-NLS-1$
						return model.getStringProperty(J2EEProjectCreationDataModel.FINAL_PERSPECTIVE);
					return super.getAttribute(aName);
				}
			};
			BasicNewProjectResourceWizard.updatePerspective(newConfig);
		}
		BasicNewResourceWizard.selectAndReveal(((J2EEProjectCreationDataModel) model).getTargetProject(), J2EEUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow());
	}

}