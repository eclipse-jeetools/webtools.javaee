/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.ui.internal.wizard;

import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleImportPage;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorComponentImportPage extends J2EEModuleImportPage {

	public ConnectorComponentImportPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(JCAUIMessages.JCA_IMPORT_MAIN_PG_TITLE);
		setDescription(JCAUIMessages.JCA_IMPORT_MAIN_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_IMPORT_WIZARD_BANNER));
	}

	@Override
	protected String getFileImportLabel() {
		return JCAUIMessages.JCA_FILE_LBL;
	}

	@Override
	protected String[] getFilterExpression() {
		return new String[]{"*.rar"}; //$NON-NLS-1$
	}

	@Override
	protected String getFileNamesStoreID() {
		return "RAR";//$NON-NLS-1$
	}

	@Override
	protected String getProjectImportLabel() {
		return JCAUIMessages.JCA_MODULE_LBL;
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		return super.createTopLevelComposite(parent);
	}

	@Override
	protected String getInfopopID() {
		return IJ2EEUIContextIds.IMPORT_RAR_WIZARD_PAGE;
	}
	
	@Override
	protected String getModuleFacetID(){
		return IJ2EEFacetConstants.JCA;
	}	
}
