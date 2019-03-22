/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
	public final static String INIT_PARAM_TITLE = WEBUIMessages.INIT_PARAM_TITLE;
	public final static String INIT_PARAM_LABEL = WEBUIMessages.INIT_PARAM_LABEL;
	public final static String URL_MAPPINGS_TITLE = WEBUIMessages.URL_MAPPINGS_TITLE;
	public final static String URL_MAPPINGS_LABEL = WEBUIMessages.URL_MAPPINGS_LABEL;
	public final static String ASYNC_SUPPORT_LABEL = WEBUIMessages.ASYNC_SUPPORT_LABEL;
	public final static String CHOOSE_SERVLET_CLASS = WEBUIMessages.CHOOSE_SERVLET_CLASS;
	
	// NewServletWizard
	public final static String NEW_SERVLET_WIZARD_WINDOW_TITLE = WEBUIMessages.NEW_SERVLET_WIZARD_WINDOW_TITLE;
	public final static String NEW_SERVLET_WIZARD_PAGE_TITLE = WEBUIMessages.NEW_SERVLET_WIZARD_PAGE_TITLE;
	public final static String NEW_SERVLET_WIZARD_PAGE_DESC = WEBUIMessages.NEW_SERVLET_WIZARD_PAGE_DESC;
	public final static String SERVLET_PACKAGE_LABEL = WEBUIMessages.SERVLET_PACKAGE_LABEL;
	public final static String SERVLET_NAME_LABEL = WEBUIMessages.SERVLET_NAME_LABEL;
	
    // AddFilterWizard
    public final static String ADD_FILTER_WIZARD_WINDOW_TITLE = WEBUIMessages.ADD_FILTER_WIZARD_WINDOW_TITLE;
    public final static String ADD_FILTER_WIZARD_PAGE_TITLE = WEBUIMessages.ADD_FILTER_WIZARD_PAGE_TITLE;
    public final static String ADD_FILTER_WIZARD_PAGE_DESC = WEBUIMessages.ADD_FILTER_WIZARD_PAGE_DESC;

    public final static String USE_EXISTING_FILTER_CLASS = WEBUIMessages.USE_EXISTING_FILTER_CLASS;
    public final static String CHOOSE_FILTER_CLASS = WEBUIMessages.CHOOSE_FILTER_CLASS;
    public final static String SERVLET_MAPPINGS_LABEL = WEBUIMessages.SERVLET_MAPPINGS_LABEL;
    public final static String FILTER_MAPPINGS_LABEL = WEBUIMessages.FILTER_MAPPINGS_LABEL;
    public final static String URL_SERVLET_LABEL = WEBUIMessages.URL_SERVLET_LABEL;
    public final static String DISPATCHERS_LABEL = WEBUIMessages.DISPATCHERS_LABEL;
    
    public static String REQUEST = WEBUIMessages.DISPATCHER_REQUEST;
    public static String FORWARD = WEBUIMessages.DISPATCHER_FORWARD; 
    public static String INCLUDE = WEBUIMessages.DISPATCHER_INCLUDE; 
    public static String ERROR = WEBUIMessages.DISPATCHER_ERROR; 
    
    // NewFilterWizard
    public final static String NEW_FILTER_WIZARD_WINDOW_TITLE = WEBUIMessages.NEW_FILTER_WIZARD_WINDOW_TITLE;
    
    // NewFilterWizard
    public final static String NEW_LISTENER_WIZARD_WINDOW_TITLE = WEBUIMessages.NEW_LISTENER_WIZARD_WINDOW_TITLE;

	// AddListenerWizard
	public final static String ADD_LISTENER_WIZARD_WINDOW_TITLE = WEBUIMessages.ADD_LISTENER_WIZARD_WINDOW_TITLE;
	public final static String ADD_LISTENER_WIZARD_PAGE_TITLE = WEBUIMessages.ADD_LISTENER_WIZARD_PAGE_TITLE;
	public final static String ADD_LISTENER_WIZARD_PAGE_DESC = WEBUIMessages.ADD_LISTENER_WIZARD_PAGE_DESC;
	
	public final static String CHOOSE_LISTENER_CLASS = WEBUIMessages.CHOOSE_LISTENER_CLASS;	
    public final static String USE_EXISTING_LISTENER_CLASS = WEBUIMessages.USE_EXISTING_LISTENER_CLASS;
	public final static String ADD_LISTENER_WIZARD_SERVLET_CONTEXT_EVENTS = WEBUIMessages.ADD_LISTENER_WIZARD_SERVLET_CONTEXT_EVENTS;
	public final static String ADD_LISTENER_WIZARD_HTTP_SESSION_EVENTS = WEBUIMessages.ADD_LISTENER_WIZARD_HTTP_SESSION_EVENTS;
	public final static String ADD_LISTENER_WIZARD_SERVLET_REQUEST_EVENTS = WEBUIMessages.ADD_LISTENER_WIZARD_SERVLET_REQUEST_EVENTS;
	public final static String ADD_LISTENER_WIZARD_LIFECYCLE = WEBUIMessages.ADD_LISTENER_WIZARD_LIFECYCLE;
	public final static String ADD_LISTENER_WIZARD_LIFECYCLE_L = WEBUIMessages.ADD_LISTENER_WIZARD_LIFECYCLE_L;
	public final static String ADD_LISTENER_WIZARD_LIFECYCLE_I = WEBUIMessages.ADD_LISTENER_WIZARD_LIFECYCLE_I;
	public final static String ADD_LISTENER_WIZARD_LIFECYCLE_Y = WEBUIMessages.ADD_LISTENER_WIZARD_LIFECYCLE_Y;
	public final static String ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES = WEBUIMessages.ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES;
	public final static String ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES_C = WEBUIMessages.ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES_C;
	public final static String ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES_H = WEBUIMessages.ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES_H;
	public final static String ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES_G = WEBUIMessages.ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES_G;
	public final static String ADD_LISTENER_WIZARD_SESSION_MIGRATION = WEBUIMessages.ADD_LISTENER_WIZARD_SESSION_MIGRATION;
	public final static String ADD_LISTENER_WIZARD_OBJECT_BINDING = WEBUIMessages.ADD_LISTENER_WIZARD_OBJECT_BINDING;
	public final static String ADD_LISTENER_WIZARD_OBJECT_SESSION_ID = WEBUIMessages.ADD_LISTENER_WIZARD_OBJECT_SESSION_ID;
	public final static String ADD_LISTENER_WIZARD_ASYNC_EVENTS = WEBUIMessages.ADD_LISTENER_WIZARD_ASYNC_EVENTS;
	// common
	public final static String ADD_BUTTON_LABEL = WEBUIMessages.ADD_BUTTON_LABEL;
	public final static String ADD_BUTTON_LABEL_A = WEBUIMessages.ADD_BUTTON_LABEL_A;
	public final static String ADD_BUTTON_LABEL_D = WEBUIMessages.ADD_BUTTON_LABEL_D;
	public final static String EDIT_BUTTON_LABEL = WEBUIMessages.EDIT_BUTTON_LABEL;
	public final static String EDIT_BUTTON_LABEL_E = WEBUIMessages.EDIT_BUTTON_LABEL_E;
	public final static String EDIT_BUTTON_LABEL_T = WEBUIMessages.EDIT_BUTTON_LABEL_T;
	public final static String REMOVE_BUTTON_LABEL = WEBUIMessages.REMOVE_BUTTON_LABEL;
	public final static String REMOVE_BUTTON_LABEL_R = WEBUIMessages.REMOVE_BUTTON_LABEL_R;
	public final static String REMOVE_BUTTON_LABEL_V = WEBUIMessages.REMOVE_BUTTON_LABEL_V;
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

	public final static String NAME_TITLE = WEBUIMessages.NAME_TITLE;
	public final static String NAME_LABEL = WEBUIMessages.NAME_LABEL;
	public final static String DESCRIPTION_TITLE = WEBUIMessages.DESCRIPTION_TITLE;
	public final static String DESCRIPTION_LABEL = WEBUIMessages.DESCRIPTION_LABEL;
	public final static String VALUE_TITLE = WEBUIMessages.VALUE_TITLE;
	public final static String VALUE_LABEL = WEBUIMessages.VALUE_LABEL;
	public final static String EMPTY_STRING = ""; //$NON-NLS-1$
	public final static String BROWSE_BUTTON_LABEL = WEBUIMessages.BROWSE_BUTTON_LABEL;
	public static final String REMOVE_BUTTON = WEBUIMessages.REMOVE_BUTTON;
	public final static String SELECT_ALL_BUTTON = WEBUIMessages.SELECT_ALL_BUTTON;
	public final static String CLEAR_BUTTON = WEBUIMessages.CLEAR_BUTTON;
	
    
	public final static String NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC = WEBUIMessages.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC;
	public final static String NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC = WEBUIMessages.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC;
	
	public final static String ImportWARAction_UI_ = WEBUIMessages.ImportWARAction_UI_;
	public final static String ExportWARAction_UI_ = WEBUIMessages.ExportWARAction_UI_;
	
	public final static String NO_WEB_PROJECTS = WEBUIMessages.NO_WEB_PROJECTS;
	
	public static final String SERVLETS = WEBUIMessages.SERVLETS;
	
	public static final String CONVERT_TO_WEB_TITLE = WEBUIMessages.ConvertToWebTitle;
	public static final String SELECT_WEB_VERSION = WEBUIMessages.SelectWebVersion;
}
