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
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;

public interface IWebWizardConstants {
	
	// AddServletWizard
	public final static String ADD_SERVLET_WIZARD_WINDOW_TITLE = WEBUIMessages.getResourceString("ADD_SERVLET_WIZARD_WINDOW_TITLE"); //$NON-NLS-1$
	public final static String ADD_SERVLET_WIZARD_PAGE_TITLE = WEBUIMessages.getResourceString("ADD_SERVLET_WIZARD_PAGE_TITLE"); //$NON-NLS-1$
	public final static String ADD_SERVLET_WIZARD_PAGE_DESC = WEBUIMessages.getResourceString("ADD_SERVLET_WIZARD_PAGE_DESC"); //$NON-NLS-1$

	public final static String DEFAULT_PACKAGE = WEBUIMessages.getResourceString("DEFAULT_PACKAGE"); //$NON-NLS-1$
	public final static String SELECT_CLASS_TITLE = WEBUIMessages.getResourceString("SELECT_CLASS_TITLE"); //$NON-NLS-1$
	public final static String MAP_CLASS_NAME_TO_CLASS_ERROR_MSG = WEBUIMessages.getResourceString("MAP_CLASS_NAME_TO_CLASS_ERROR_MSG"); //$NON-NLS-1$
	public final static String EMPTY_LIST_MSG = WEBUIMessages.getResourceString("EMPTY_LIST_MSG"); //$NON-NLS-1$
	public final static String ADD_LABEL = WEBUIMessages.getResourceString("ADD_LABEL"); //$NON-NLS-1$
	public final static String USE_EXISTING_SERVLET_CLASS = WEBUIMessages.getResourceString("USE_EXISTING_SERVLET_CLASS"); //$NON-NLS-1$
	public final static String INIT_PARAM_LABEL = WEBUIMessages.getResourceString("INIT_PARAM_LABEL"); //$NON-NLS-1$
	public final static String URL_MAPPINGS_LABEL = WEBUIMessages.getResourceString("URL_MAPPINGS_LABEL"); //$NON-NLS-1$
	public final static String CHOOSE_SERVLET_CLASS = WEBUIMessages.getResourceString("CHOOSE_SERVLET_CLASS"); //$NON-NLS-1$
	
	// NewServletWizard
	public final static String NEW_SERVLET_WIZARD_WINDOW_TITLE = WEBUIMessages.getResourceString("NEW_SERVLET_WIZARD_WINDOW_TITLE"); //$NON-NLS-1$
	public final static String NEW_SERVLET_WIZARD_PAGE_TITLE = WEBUIMessages.getResourceString("NEW_SERVLET_WIZARD_PAGE_TITLE"); //$NON-NLS-1$
	public final static String NEW_SERVLET_WIZARD_PAGE_DESC = WEBUIMessages.getResourceString("NEW_SERVLET_WIZARD_PAGE_DESC"); //$NON-NLS-1$
	public final static String SERVLET_PACKAGE_LABEL = WEBUIMessages.getResourceString("SERVLET_PACKAGE_LABEL"); //$NON-NLS-1$
	public final static String SERVLET_NAME_LABEL = WEBUIMessages.getResourceString("SERVLET_NAME_LABEL"); //$NON-NLS-1$

	
	// common
	public final static String ADD_BUTTON_LABEL = WEBUIMessages.getResourceString("ADD_BUTTON_LABEL"); //$NON-NLS-1$
	public final static String REMOVE_BUTTON_LABEL = WEBUIMessages.getResourceString("REMOVE_BUTTON_LABEL"); //$NON-NLS-1$
	public final static String URL_PATTERN_TITLE = WEBUIMessages.getResourceString("URL_PATTERN_TITLE"); //$NON-NLS-1$
	public final static String URL_PATTERN_LABEL = WEBUIMessages.getResourceString("URL_PATTERN_LABEL"); //$NON-NLS-1$
	public final static String FOLDER_LABEL = WEBUIMessages.getResourceString("FOLDER_LABEL"); //$NON-NLS-1$
	public final static String JAVA_PACKAGE_LABEL = WEBUIMessages.getResourceString("JAVA_PACKAGE_LABEL"); //$NON-NLS-1$
	public final static String CLASS_NAME_LABEL = WEBUIMessages.getResourceString("CLASS_NAME_LABEL"); //$NON-NLS-1$
	public final static String SUPERCLASS_LABEL = WEBUIMessages.getResourceString("SUPERCLASS_LABEL"); //$NON-NLS-1$
	public final static String CONTAINER_SELECTION_DIALOG_TITLE = WEBUIMessages.getResourceString("CONTAINER_SELECTION_DIALOG_TITLE"); //$NON-NLS-1$
	public final static String CONTAINER_SELECTION_DIALOG_DESC = WEBUIMessages.getResourceString("CONTAINER_SELECTION_DIALOG_DESC"); //$NON-NLS-1$
	public final static String CONTAINER_SELECTION_DIALOG_VALIDATOR_MESG = WEBUIMessages.getResourceString("CONTAINER_SELECTION_DIALOG_VALIDATOR_MESG"); //$NON-NLS-1$
	public final static String PACKAGE_SELECTION_DIALOG_TITLE = WEBUIMessages.getResourceString("PACKAGE_SELECTION_DIALOG_TITLE"); //$NON-NLS-1$
	public final static String PACKAGE_SELECTION_DIALOG_DESC = WEBUIMessages.getResourceString("PACKAGE_SELECTION_DIALOG_DESC"); //$NON-NLS-1$
	public final static String PACKAGE_SELECTION_DIALOG_MSG_NONE = WEBUIMessages.getResourceString("PACKAGE_SELECTION_DIALOG_MSG_NONE"); //$NON-NLS-1$
	public final static String SUPERCLASS_SELECTION_DIALOG_TITLE = WEBUIMessages.getResourceString("SUPERCLASS_SELECTION_DIALOG_TITLE"); //$NON-NLS-1$
	public final static String SUPERCLASS_SELECTION_DIALOG_DESC = WEBUIMessages.getResourceString("SUPERCLASS_SELECTION_DIALOG_DESC"); //$NON-NLS-1$
	public final static String INTERFACE_SELECTION_DIALOG_TITLE = WEBUIMessages.getResourceString("INTERFACE_SELECTION_DIALOG_TITLE"); //$NON-NLS-1$
	public final static String INTERFACE_SELECTION_DIALOG_DESC = WEBUIMessages.getResourceString("INTERFACE_SELECTION_DIALOG_DESC"); //$NON-NLS-1$
	public final static String INTERFACE_SELECTION_DIALOG_MSG_NONE = WEBUIMessages.getResourceString("INTERFACE_SELECTION_DIALOG_MSG_NONE"); //$NON-NLS-1$
	public final static String JAVA_CLASS_MODIFIERS_LABEL = WEBUIMessages.getResourceString("JAVA_CLASS_MODIFIERS_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_INTERFACES_LABEL = WEBUIMessages.getResourceString("JAVA_CLASS_INTERFACES_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_METHOD_STUBS_LABEL = WEBUIMessages.getResourceString("JAVA_CLASS_METHOD_STUBS_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_PUBLIC_CHECKBOX_LABEL = WEBUIMessages.getResourceString("JAVA_CLASS_PUBLIC_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_ABSTRACT_CHECKBOX_LABEL = WEBUIMessages.getResourceString("JAVA_CLASS_ABSTRACT_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_FINAL_CHECKBOX_LABEL = WEBUIMessages.getResourceString("JAVA_CLASS_FINAL_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL = WEBUIMessages.getResourceString("JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_MAIN_CHECKBOX_LABEL = WEBUIMessages.getResourceString("JAVA_CLASS_MAIN_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_INHERIT_CHECKBOX_LABEL = WEBUIMessages.getResourceString("JAVA_CLASS_INHERIT_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_BROWER_DIALOG_TITLE = WEBUIMessages.getResourceString("JAVA_CLASS_BROWER_DIALOG_TITLE"); //$NON-NLS-1$
	public final static String JAVA_CLASS_BROWER_DIALOG_MESSAGE = WEBUIMessages.getResourceString("JAVA_CLASS_BROWER_DIALOG_MESSAGE"); //$NON-NLS-1$
	
	public final static String NAME_LABEL = WEBUIMessages.getResourceString("NAME_LABEL"); //$NON-NLS-1$
	public final static String DESCRIPTION_LABEL = WEBUIMessages.getResourceString("DESCRIPTION_LABEL"); //$NON-NLS-1$
	public final static String VALUE_LABEL = WEBUIMessages.getResourceString("VALUE_LABEL"); //$NON-NLS-1$
	public final static String EMPTY_STRING = ""; //$NON-NLS-1$
	public final static String BROWSE_BUTTON_LABEL = WEBUIMessages.getResourceString("BROWSE_BUTTON_LABEL"); //$NON-NLS-1$
	public static final String REMOVE_BUTTON = WEBUIMessages.getResourceString("REMOVE_BUTTON"); //$NON-NLS-1$
    
	public final static String NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC = WEBUIMessages.getResourceString("NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC"); //$NON-NLS-1$
	public final static String NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC = WEBUIMessages.getResourceString("NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC"); //$NON-NLS-1$
	
	public final static String ImportWARAction_UI_ = WEBUIMessages.getResourceString("ImportWARAction_UI_"); //$NON-NLS-1$
	public final static String ExportWARAction_UI_ = WEBUIMessages.getResourceString("ExportWARAction_UI_"); //$NON-NLS-1$
	
	public final static String NO_WEB_PROJECTS = WEBUIMessages.getResourceString("NO_WEB_PROJECTS"); //$NON-NLS-1$
	
	public static final String SERVLETS = WEBUIMessages.getResourceString("SERVLETS"); //$NON-NLS-1$
}