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

import org.eclipse.jst.ejb.ui.EJBModuleCreationWizard;
import org.eclipse.jst.ejb.ui.EJBModuleExportWizard;
import org.eclipse.jst.ejb.ui.EJBModuleImportWizard;
import org.eclipse.jst.j2ee.jca.ui.ConnectorModuleCreationWizard;
import org.eclipse.jst.j2ee.jca.ui.ConnectorModuleExportWizard;
import org.eclipse.jst.j2ee.jca.ui.ConnectorModuleImportWizard;
import org.eclipse.jst.j2ee.ui.AppClientModuleCreationWizard;
import org.eclipse.jst.j2ee.ui.AppClientModuleExportWizard;
import org.eclipse.jst.j2ee.ui.AppClientModuleImportWizard;
import org.eclipse.jst.j2ee.ui.EnterpriseApplicationCreationWizard;
import org.eclipse.jst.j2ee.ui.EnterpriseApplicationExportWizard;
import org.eclipse.jst.j2ee.ui.EnterpriseApplicationImportWizard;
import org.eclipse.jst.j2ee.ui.J2EEUtilityJarImportWizard;
import org.eclipse.jst.servlet.ui.WebModuleCreationWizard;
import org.eclipse.jst.servlet.ui.WebModuleExportWizard;
import org.eclipse.jst.servlet.ui.WebModuleImportWizard;

/**
 * <p>
 * The following class is experimental until fully documented.
 * </p>
 * <p>
 * The Creation IDs are used for activity filtering and as such are declared on the 
 * individual wizards. All other IDs must be maintained to stay in sync with the
 * values found in the plugin.xml files of the respective module UI plugins.  
 */
public interface IJ2EEWizardConstants {
	 
	String NEW_EAR_PROJECT_WIZARD_ID = EnterpriseApplicationCreationWizard.WIZARD_ID;

	String NEW_APPCLIENT_PROJECT_WIZARD_ID = AppClientModuleCreationWizard.WIZARD_ID;

	String NEW_JCA_PROJECT_WIZARD_ID = ConnectorModuleCreationWizard.WIZARD_ID;

	String NEW_EJB_PROJECT_WIZARD_ID = EJBModuleCreationWizard.WIZARD_ID;

	String NEW_WEB_PROJECT_WIZARD_ID = WebModuleCreationWizard.WIZARD_ID;

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
