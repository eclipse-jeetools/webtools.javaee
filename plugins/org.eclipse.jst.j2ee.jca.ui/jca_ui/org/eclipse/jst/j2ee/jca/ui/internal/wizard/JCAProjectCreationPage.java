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
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.jca.ui.internal.wizard;

import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleCreationPage;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JCAProjectCreationPage extends J2EEModuleCreationPage {

	/**
	 * @param model
	 * @param pageName
	 */
	public JCAProjectCreationPage(ConnectorModuleCreationDataModel model, String pageName) {
		super(model, pageName);
		setTitle(JCAUIMessages.getResourceString(JCAUIMessages.JCA_PROJECT_MAIN_PG_TITLE));
		setDescription(JCAUIMessages.getResourceString(JCAUIMessages.JCA_PROJECT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_PROJECT_WIZARD_BANNER));
	}

	protected String getVersionLabel() {
		return JCAUIMessages.getResourceString(JCAUIMessages.JCA_VERSION_LBL);
	}

	/**
	 *  
	 */
	protected String getInfopopID() {
		return IJ2EEUIContextIds.NEW_CONNECTOR_WIZARD_P1;
	}
}