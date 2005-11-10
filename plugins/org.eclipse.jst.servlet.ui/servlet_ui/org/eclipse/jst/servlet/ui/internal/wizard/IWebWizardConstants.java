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
	public final static String ADD_SERVLET_WIZARD_WINDOW_TITLE = WEBUIMessages.ADD_SERVLET_WIZARD_WINDOW_TITLE;
	public final static String ADD_SERVLET_WIZARD_PAGE_TITLE = WEBUIMessages.ADD_SERVLET_WIZARD_PAGE_TITLE;
	public final static String ADD_SERVLET_WIZARD_PAGE_DESC = WEBUIMessages.ADD_SERVLET_WIZARD_PAGE_DESC;

	public final static String DEFAULT_PACKAGE = WEBUIMessages.DEFAULT_PACKAGE;
	public final static String SELECT_CLASS_TITLE = WEBUIMessages.SELECT_CLASS_TITLE;
	public final static String MAP_CLASS_NAME_TO_CLASS_ERROR_MSG = WEBUIMessages.MAP_CLASS_NAME_TO_CLASS_ERROR_MSG;
	public final static String EMPTY_LIST_MSG = WEBUIMessages.EMPTY_LIST_MSG;
	public final static String ADD_LABEL = WEBUIMessages.ADD_LABEL;
	public final static String USE_EXISTING_SERVLET_CLASS = WEBUIMessages.USE_EXISTING_SERVLET_CLASS;
	public final static String INIT_PARAM_LABEL = WEBUIMessages.INIT_PARAM_LABEL;
	public final static String URL_MAPPINGS_LABEL = WEBUIMessages.URL_MAPPINGS_LABEL;
	public final static String CHOOSE_SERVLET_CLASS = WEBUIMessages.CHOOSE_SERVLET_CLASS;
	
	// NewServletWizard
	public final static String NEW_SERVLET_WIZARD_WINDOW_TITLE = WEBUIMessages.NEW_SERVLET_WIZARD_WINDOW_TITLE;
	public final static String NEW_SERVLET_WIZARD_PAGE_TITLE = WEBUIMessages.NEW_SERVLET_WIZARD_PAGE_TITLE;
	public final static String NEW_SERVLET_WIZARD_PAGE_DESC = WEBUIMessages.NEW_SERVLET_WIZARD_PAGE_DESC;
	public final static String SERVLET_PACKAGE_LABEL = WEBUIMessages.SERVLET_PACKAGE_LABEL;
	public final static String SERVLET_NAME_LABEL = WEBUIMessages.SERVLET_NAME_LABEL;

	
	// common
	public final static String ADD_BUTTON_LABEL = WEBUIMessages.ADD_BUTTON_LABEL;
	public final static String REMOVE_BUTTON_LABEL = WEBUIMessages.REMOVE_BUTTON_LABEL;
	public final static String URL_PATTERN_TITLE = WEBUIMessages.URL_PATTERN_TITLE;
	public final static String URL_PATTERN_LABEL = WEBUIMessages.URL_PATTERN_LABEL;
	public final static String FOLDER_LABEL = WEBUIMessages.FOLDER_LABEL;
	public final static String JAVA_PACKAGE_LABEL = WEBUIMessages.JAVA_PACKAGE_LABEL;
	public final static String CLASS_NAME_LABEL = WEBUIMessages.CLASS_NAME_LABEL;
	public final static String SUPERCLASS_LABEL = WEBUIMessages.SUPERCLASS_LABEL;
	public final static String CONTAINER_SELECTION_DIALOG_TITLE = WEBUIMessages.CONTAINER_SELECTION_DIALOG_TITLE;
	public final static String CONTAINER_SELECTION_DIALOG_DESC = WEBUIMessages.CONTAINER_SELECTION_DIALOG_DESC;
	public final static String CONTAINER_SELECTION_DIALOG_VALIDATOR_MESG = WEBUIMessages.CONTAINER_SELECTION_DIALOG_VALIDATOR_MESG;
	public final static String PACKAGE_SELECTION_DIALOG_TITLE = WEBUIMessages.PACKAGE_SELECTION_DIALOG_TITLE;
	public final static String PACKAGE_SELECTION_DIALOG_DESC = WEBUIMessages.PACKAGE_SELECTION_DIALOG_DESC;
	public final static String PACKAGE_SELECTION_DIALOG_MSG_NONE = WEBUIMessages.PACKAGE_SELECTION_DIALOG_MSG_NONE;
	public final static String SUPERCLASS_SELECTION_DIALOG_TITLE = WEBUIMessages.SUPERCLASS_SELECTION_DIALOG_TITLE;
	public final static String SUPERCLASS_SELECTION_DIALOG_DESC = WEBUIMessages.SUPERCLASS_SELECTION_DIALOG_DESC;
	public final static String INTERFACE_SELECTION_DIALOG_TITLE = WEBUIMessages.INTERFACE_SELECTION_DIALOG_TITLE;
	public final static String INTERFACE_SELECTION_DIALOG_DESC = WEBUIMessages.INTERFACE_SELECTION_DIALOG_DESC;
	public final static String INTERFACE_SELECTION_DIALOG_MSG_NONE = WEBUIMessages.INTERFACE_SELECTION_DIALOG_MSG_NONE;
	public final static String JAVA_CLASS_MODIFIERS_LABEL = WEBUIMessages.JAVA_CLASS_MODIFIERS_LABEL;
	public final static String JAVA_CLASS_INTERFACES_LABEL = WEBUIMessages.JAVA_CLASS_INTERFACES_LABEL;
	public final static String JAVA_CLASS_METHOD_STUBS_LABEL = WEBUIMessages.JAVA_CLASS_METHOD_STUBS_LABEL;
	public final static String JAVA_CLASS_PUBLIC_CHECKBOX_LABEL = WEBUIMessages.JAVA_CLASS_PUBLIC_CHECKBOX_LABEL;
	public final static String JAVA_CLASS_ABSTRACT_CHECKBOX_LABEL = WEBUIMessages.JAVA_CLASS_ABSTRACT_CHECKBOX_LABEL;
	public final static String JAVA_CLASS_FINAL_CHECKBOX_LABEL = WEBUIMessages.JAVA_CLASS_FINAL_CHECKBOX_LABEL;
	public final static String JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL = WEBUIMessages.JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL;
	public final static String JAVA_CLASS_MAIN_CHECKBOX_LABEL = WEBUIMessages.JAVA_CLASS_MAIN_CHECKBOX_LABEL;
	public final static String JAVA_CLASS_INHERIT_CHECKBOX_LABEL = WEBUIMessages.JAVA_CLASS_INHERIT_CHECKBOX_LABEL;
	public final static String JAVA_CLASS_BROWER_DIALOG_TITLE = WEBUIMessages.JAVA_CLASS_BROWER_DIALOG_TITLE;
	public final static String JAVA_CLASS_BROWER_DIALOG_MESSAGE = WEBUIMessages.JAVA_CLASS_BROWER_DIALOG_MESSAGE;
	
	public final static String NAME_LABEL = WEBUIMessages.NAME_LABEL;
	public final static String DESCRIPTION_LABEL = WEBUIMessages.DESCRIPTION_LABEL;
	public final static String VALUE_LABEL = WEBUIMessages.VALUE_LABEL;
	public final static String EMPTY_STRING = ""; //$NON-NLS-1$
	public final static String BROWSE_BUTTON_LABEL = WEBUIMessages.BROWSE_BUTTON_LABEL;
	public static final String REMOVE_BUTTON = WEBUIMessages.REMOVE_BUTTON;
    
	public final static String NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC = WEBUIMessages.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC;
	public final static String NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC = WEBUIMessages.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC;
	
	public final static String ImportWARAction_UI_ = WEBUIMessages.ImportWARAction_UI_;
	public final static String ExportWARAction_UI_ = WEBUIMessages.ExportWARAction_UI_;
	
	public final static String NO_WEB_PROJECTS = WEBUIMessages.NO_WEB_PROJECTS;
	
	public static final String SERVLETS = WEBUIMessages.SERVLETS;
}