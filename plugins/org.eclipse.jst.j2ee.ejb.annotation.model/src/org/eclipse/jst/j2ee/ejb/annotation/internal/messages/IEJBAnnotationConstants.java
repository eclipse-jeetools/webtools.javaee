/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotation.internal.messages;



public interface IEJBAnnotationConstants {
	
	public static final String ERR_EJB_CLASS_NAME_USED = EJBAnnotationMessages.getResourceString("ERR_EJB_CLASS_NAME_USED"); //$NON-NLS-1$
	public static final String ERR_EJB_NAME_EMPTY = EJBAnnotationMessages.getResourceString("ERR_EJB_NAME_EMPTY"); //$NON-NLS-1$
	public static final String ERR_EJB_NAME_ENDS_WITH_BEAN = EJBAnnotationMessages.getResourceString("ERR_EJB_NAME_ENDS_WITH_BEAN"); //$NON-NLS-1$
	public static final String ERR_DISPLAY_NAME_EMPTY = EJBAnnotationMessages.getResourceString("ERR_DISPLAY_NAME_EMPTY"); //$NON-NLS-1$
	public static final String ERR_EJB_DISPLAY_NAME_USED = EJBAnnotationMessages.getResourceString("ERR_EJB_DISPLAY_NAME_USED"); //$NON-NLS-1$
	public static final String ERR_STATELESS_EMPTY = EJBAnnotationMessages.getResourceString("ERR_STATELESS_EMPTY"); //$NON-NLS-1$
	public static final String ERR_STATELESS_VALUE = EJBAnnotationMessages.getResourceString("ERR_STATELESS_VALUE"); //$NON-NLS-1$
	public static final String ERR_JNDI_NAME_EMPTY = EJBAnnotationMessages.getResourceString("ERR_JNDI_NAME_EMPTY"); //$NON-NLS-1$
	public static final String ERR_JNDI_NAME_VALUE = EJBAnnotationMessages.getResourceString("ERR_JNDI_NAME_VALUE"); //$NON-NLS-1$
	public static final String ERR_TRANSACTION_EMPTY = EJBAnnotationMessages.getResourceString("ERR_TRANSACTION_EMPTY"); //$NON-NLS-1$
	public static final String ERR_TRANSACTION_VALUE = EJBAnnotationMessages.getResourceString("ERR_TRANSACTION_VALUE"); //$NON-NLS-1$
	public static final String ERR_DESTINATIONTYPE_EMPTY = EJBAnnotationMessages.getResourceString("ERR_DESTINATIONTYPE_EMPTY"); //$NON-NLS-1$
	public static final String ERR_DESTINATIONTYPE_VALUE = EJBAnnotationMessages.getResourceString("ERR_DESTINATIONTYPE_VALUE"); //$NON-NLS-1$

	// AddEjbWizard
	public final static String ADD_SESSION_EJB_WIZARD_WINDOW_TITLE = EJBAnnotationMessages.getResourceString("ADD_SESSION_EJB_WIZARD_WINDOW_TITLE"); //$NON-NLS-1$
	public final static String ADD_SESSION_EJB_WIZARD_PAGE_TITLE = EJBAnnotationMessages.getResourceString("ADD_SESSION_EJB_WIZARD_PAGE_TITLE"); //$NON-NLS-1$
	public static final String ADD_SESSION_EJB_WIZARD_PAGE_DESC = EJBAnnotationMessages.getResourceString("ADD_SESSION_EJB_WIZARD_PAGE_DESC"); //$NON-NLS-1$
	
	public final static String ADD_MESSAGE_EJB_WIZARD_WINDOW_TITLE = EJBAnnotationMessages.getResourceString("ADD_MESSAGE_EJB_WIZARD_WINDOW_TITLE"); //$NON-NLS-1$
	public final static String ADD_MESSAGE_EJB_WIZARD_PAGE_TITLE = EJBAnnotationMessages.getResourceString("ADD_MESSAGE_EJB_WIZARD_PAGE_TITLE"); //$NON-NLS-1$
	public static final String ADD_MESSAGE_EJB_WIZARD_PAGE_DESC = EJBAnnotationMessages.getResourceString("ADD_MESSAGE_EJB_WIZARD_PAGE_DESC"); //$NON-NLS-1$

	public final static String ADD_ENTITY_EJB_WIZARD_WINDOW_TITLE = EJBAnnotationMessages.getResourceString("ADD_ENTITY_EJB_WIZARD_WINDOW_TITLE"); //$NON-NLS-1$
	public final static String ADD_ENTITY_EJB_WIZARD_PAGE_TITLE = EJBAnnotationMessages.getResourceString("ADD_ENTITY_EJB_WIZARD_PAGE_TITLE"); //$NON-NLS-1$
	public static final String ADD_ENTITY_EJB_WIZARD_PAGE_DESC = EJBAnnotationMessages.getResourceString("ADD_ENTITY_EJB_WIZARD_PAGE_DESC"); //$NON-NLS-1$

	public final static String DEFAULT_PACKAGE = EJBAnnotationMessages.getResourceString("DEFAULT_PACKAGE"); //$NON-NLS-1$
	public final static String SELECT_CLASS_TITLE = EJBAnnotationMessages.getResourceString("SELECT_CLASS_TITLE"); //$NON-NLS-1$
	
	
	// common
	
	public final static String EJB_NAME_LABEL = EJBAnnotationMessages.getResourceString("EJB_NAME_LABEL"); //$NON-NLS-1$
	public final static String DESCRIPTION_LABEL = EJBAnnotationMessages.getResourceString("DESCRIPTION_LABEL"); //$NON-NLS-1$
	public static final String NAME_LABEL = EJBAnnotationMessages.getResourceString("NAME_LABEL"); //$NON-NLS-1$;
	public static final String JNDI_NAME_LABEL = EJBAnnotationMessages.getResourceString("JNDI_NAME_LABEL"); //$NON-NLS-1$;
	public static final String STATELESS_LABEL =  EJBAnnotationMessages.getResourceString("STATELESS_LABEL"); //$NON-NLS-1$;;
	public static final String TRANSACTION_LABEL = EJBAnnotationMessages.getResourceString("TRANSACTION_LABEL"); //$NON-NLS-1$;;;
	public static final String DESTINATION_LABEL = EJBAnnotationMessages.getResourceString("DESTINATION_LABEL"); //$NON-NLS-1$;;;
	public static final String DESTINATION_NAME_LABEL = EJBAnnotationMessages.getResourceString("DESTINATION_NAME_LABEL"); //$NON-NLS-1$;;;
	public static final String DISPLAY_NAME_LABEL = EJBAnnotationMessages.getResourceString("DISPLAY_NAME_LABEL"); //$NON-NLS-1$;

	
	public final static String ADD_BUTTON_LABEL = EJBAnnotationMessages.getResourceString("ADD_BUTTON_LABEL"); //$NON-NLS-1$
	public final static String FOLDER_LABEL = EJBAnnotationMessages.getResourceString("FOLDER_LABEL"); //$NON-NLS-1$
	public final static String JAVA_PACKAGE_LABEL = EJBAnnotationMessages.getResourceString("JAVA_PACKAGE_LABEL"); //$NON-NLS-1$
	public final static String CLASS_NAME_LABEL = EJBAnnotationMessages.getResourceString("CLASS_NAME_LABEL"); //$NON-NLS-1$
	public final static String SUPERCLASS_LABEL = EJBAnnotationMessages.getResourceString("SUPERCLASS_LABEL"); //$NON-NLS-1$
	public final static String CONTAINER_SELECTION_DIALOG_TITLE = EJBAnnotationMessages.getResourceString("CONTAINER_SELECTION_DIALOG_TITLE"); //$NON-NLS-1$
	public final static String CONTAINER_SELECTION_DIALOG_DESC = EJBAnnotationMessages.getResourceString("CONTAINER_SELECTION_DIALOG_DESC"); //$NON-NLS-1$
	public final static String CONTAINER_SELECTION_DIALOG_VALIDATOR_MESG = EJBAnnotationMessages.getResourceString("CONTAINER_SELECTION_DIALOG_VALIDATOR_MESG"); //$NON-NLS-1$
	public final static String PACKAGE_SELECTION_DIALOG_TITLE = EJBAnnotationMessages.getResourceString("PACKAGE_SELECTION_DIALOG_TITLE"); //$NON-NLS-1$
	public final static String PACKAGE_SELECTION_DIALOG_DESC = EJBAnnotationMessages.getResourceString("PACKAGE_SELECTION_DIALOG_DESC"); //$NON-NLS-1$
	public final static String PACKAGE_SELECTION_DIALOG_MSG_NONE = EJBAnnotationMessages.getResourceString("PACKAGE_SELECTION_DIALOG_MSG_NONE"); //$NON-NLS-1$
	public final static String SUPERCLASS_SELECTION_DIALOG_TITLE = EJBAnnotationMessages.getResourceString("SUPERCLASS_SELECTION_DIALOG_TITLE"); //$NON-NLS-1$
	public final static String SUPERCLASS_SELECTION_DIALOG_DESC = EJBAnnotationMessages.getResourceString("SUPERCLASS_SELECTION_DIALOG_DESC"); //$NON-NLS-1$
	public final static String INTERFACE_SELECTION_DIALOG_TITLE = EJBAnnotationMessages.getResourceString("INTERFACE_SELECTION_DIALOG_TITLE"); //$NON-NLS-1$
	public final static String INTERFACE_SELECTION_DIALOG_DESC = EJBAnnotationMessages.getResourceString("INTERFACE_SELECTION_DIALOG_DESC"); //$NON-NLS-1$
	public final static String INTERFACE_SELECTION_DIALOG_MSG_NONE = EJBAnnotationMessages.getResourceString("INTERFACE_SELECTION_DIALOG_MSG_NONE"); //$NON-NLS-1$
	public final static String JAVA_CLASS_MODIFIERS_LABEL = EJBAnnotationMessages.getResourceString("JAVA_CLASS_MODIFIERS_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_INTERFACES_LABEL = EJBAnnotationMessages.getResourceString("JAVA_CLASS_INTERFACES_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_METHOD_STUBS_LABEL = EJBAnnotationMessages.getResourceString("JAVA_CLASS_METHOD_STUBS_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_PUBLIC_CHECKBOX_LABEL = EJBAnnotationMessages.getResourceString("JAVA_CLASS_PUBLIC_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_ABSTRACT_CHECKBOX_LABEL = EJBAnnotationMessages.getResourceString("JAVA_CLASS_ABSTRACT_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_FINAL_CHECKBOX_LABEL = EJBAnnotationMessages.getResourceString("JAVA_CLASS_FINAL_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL = EJBAnnotationMessages.getResourceString("JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_MAIN_CHECKBOX_LABEL = EJBAnnotationMessages.getResourceString("JAVA_CLASS_MAIN_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_INHERIT_CHECKBOX_LABEL = EJBAnnotationMessages.getResourceString("JAVA_CLASS_INHERIT_CHECKBOX_LABEL"); //$NON-NLS-1$
	public final static String JAVA_CLASS_BROWER_DIALOG_TITLE = EJBAnnotationMessages.getResourceString("JAVA_CLASS_BROWER_DIALOG_TITLE"); //$NON-NLS-1$
	public final static String JAVA_CLASS_BROWER_DIALOG_MESSAGE = EJBAnnotationMessages.getResourceString("JAVA_CLASS_BROWER_DIALOG_MESSAGE"); //$NON-NLS-1$
	
	public final static String VALUE_LABEL = EJBAnnotationMessages.getResourceString("VALUE_LABEL"); //$NON-NLS-1$
	public final static String EMPTY_STRING = ""; //$NON-NLS-1$
	public final static String BROWSE_BUTTON_LABEL = EJBAnnotationMessages.getResourceString("BROWSE_BUTTON_LABEL"); //$NON-NLS-1$
	public static final String REMOVE_BUTTON = EJBAnnotationMessages.getResourceString("REMOVE_BUTTON"); //$NON-NLS-1$
    
	public final static String NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC = EJBAnnotationMessages.getResourceString("NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC"); //$NON-NLS-1$
	public final static String NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC = EJBAnnotationMessages.getResourceString("NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC"); //$NON-NLS-1$
	
	public final static String NO_EJB_PROJECTS = EJBAnnotationMessages.getResourceString("NO_EJB_PROJECTS"); //$NON-NLS-1$
}