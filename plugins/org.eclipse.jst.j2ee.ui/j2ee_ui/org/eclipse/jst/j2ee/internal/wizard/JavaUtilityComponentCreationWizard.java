/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizard;

/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JavaUtilityComponentCreationWizard extends DataModelWizard implements INewWizard, IExecutableExtension{

//	private IConfigurationElement configurationElement;
	
	public JavaUtilityComponentCreationWizard() {
		super();
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.JAVAUTIL_COMPONENT_WIZ_TITLE));
		//setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_PROJECT_WIZARD_BANNER));
	}

	public void doAddPages() {
		addPage(new JavaUtilityComponentCreationWizardPage(getDataModel(), JavaUtilityComponentCreationWizardPage.PAGE_NAME));
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.ui.DataModelWizard#getDefaultProvider()
	 */
	protected IDataModelProvider getDefaultProvider() {
		return new JavaComponentCreationDataModelProvider();
	}
	
	public final void setInitializationData(IConfigurationElement aConfigurationElement, String aPropertyName, Object theData) throws CoreException {
//		configurationElement = aConfigurationElement;
//		doSetInitializeData(aConfigurationElement, aPropertyName, theData);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}
	public boolean canFinish() {
		return super.canFinish();
	}
}
