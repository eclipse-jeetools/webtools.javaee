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
package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.jst.ejb.ui.internal.wizard.EJBComponentCreationWizard;
import org.eclipse.jst.ejb.ui.internal.wizard.EJBModuleExportWizard;
import org.eclipse.jst.ejb.ui.internal.wizard.EJBModuleImportWizard;
import org.eclipse.jst.j2ee.internal.wizard.AppClientComponentCreationWizard;
import org.eclipse.jst.j2ee.internal.wizard.AppClientModuleExportWizard;
import org.eclipse.jst.j2ee.internal.wizard.AppClientModuleImportWizard;
import org.eclipse.jst.j2ee.internal.wizard.EARComponentCreationWizard;
import org.eclipse.jst.j2ee.internal.wizard.EnterpriseApplicationExportWizard;
import org.eclipse.jst.j2ee.internal.wizard.EnterpriseApplicationImportWizard;
import org.eclipse.jst.j2ee.internal.wizard.J2EEUtilityJarImportWizard;
import org.eclipse.jst.j2ee.jca.ui.internal.wizard.ConnectorComponentCreationWizard;
import org.eclipse.jst.j2ee.jca.ui.internal.wizard.ConnectorModuleExportWizard;
import org.eclipse.jst.j2ee.jca.ui.internal.wizard.ConnectorModuleImportWizard;
import org.eclipse.jst.servlet.ui.internal.wizard.WebComponentCreationWizard;
import org.eclipse.jst.servlet.ui.internal.wizard.WebModuleExportWizard;
import org.eclipse.jst.servlet.ui.internal.wizard.WebModuleImportWizard;

/**
 * <p>
 * The following class is experimental until fully documented.
 * </p>
 * <p>
 * The Creation IDs are used for activity filtering and as such are declared on the individual
 * wizards. All other IDs must be maintained to stay in sync with the values found in the plugin.xml
 * files of the respective module UI plugins.
 */
public interface IJ2EEWizardConstants {

	String NEW_EAR_PROJECT_WIZARD_ID = EARComponentCreationWizard.WIZARD_ID;

	String NEW_APPCLIENT_PROJECT_WIZARD_ID = AppClientComponentCreationWizard.WIZARD_ID;

	String NEW_JCA_PROJECT_WIZARD_ID = ConnectorComponentCreationWizard.WIZARD_ID;

	String NEW_EJB_PROJECT_WIZARD_ID = EJBComponentCreationWizard.WIZARD_ID;

	String NEW_WEB_PROJECT_WIZARD_ID = WebComponentCreationWizard.WIZARD_ID;

	String NEW_ENTERPRISE_BEAN_WIZARD_ID = "org.eclipse.jst.j2ee.ejb.ui.util.createEJBWizard"; //$NON-NLS-1$

	String IMPORT_EAR_WIZARD_ID = EnterpriseApplicationImportWizard.class.getName();

	String IMPORT_APPCLIENT_WIZARD_ID = AppClientModuleImportWizard.class.getName();

	String IMPORT_CONNECTOR_WIZARD_ID = ConnectorModuleImportWizard.class.getName();

	String IMPORT_ENTERPRISE_BEAN_WIZARD_ID = EJBModuleImportWizard.class.getName();

	String IMPORT_WEB_MODULE_WIZARD_ID = WebModuleImportWizard.class.getName();

	String IMPORT_UTILITY_JAR_WIZARD_ID = J2EEUtilityJarImportWizard.class.getName();

	String EXPORT_EAR_WIZARD_ID = EnterpriseApplicationExportWizard.class.getName();

	String EXPORT_APPCLIENT_WIZARD_ID = AppClientModuleExportWizard.class.getName();

	String EXPORT_CONNECTOR_WIZARD_ID = ConnectorModuleExportWizard.class.getName();

	String EXPORT_ENTERPRISE_BEAN_WIZARD_ID = EJBModuleExportWizard.class.getName();

	String EXPORT_WEB_MODULE_WIZARD_ID = WebModuleExportWizard.class.getName();
}