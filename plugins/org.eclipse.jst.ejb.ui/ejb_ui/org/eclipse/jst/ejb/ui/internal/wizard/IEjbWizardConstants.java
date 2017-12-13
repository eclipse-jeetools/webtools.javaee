/*******************************************************************************
 * Copyright (c) 2007, 2014 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 * Ludovic Champenois ludo@java.net
 * IBM - Async and Non-persistent support
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;

public interface IEjbWizardConstants {

	public final static String NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC = EJBUIMessages.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC;
	public final static String NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC = EJBUIMessages.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC;
	public final static String ADD_SESSION_BEAN_WIZARD_PAGE_DESC = EJBUIMessages.ADD_SESSION_BEAN_WIZARD_PAGE_DESC;
	
	
	// New Enterprise Bean Wizard
	public final static String ADD_SESSION_BEAN_WIZARD_PAGE_TITLE = EJBUIMessages.ADD_SESSION_BEAN_WIZARD_PAGE_TITLE;
	public static final String STATE_TYPE_LABEL = EJBUIMessages.STATE_TYPE_LABEL;
	public static final String CREATE_BUSINESS_INTERFACE = EJBUIMessages.CREATE_BUSINESS_INTERFACE;
	public static final String REMOTE_BUSINESS_INTERFACE = EJBUIMessages.REMOTE_BUSINESS_INTERFACE;
	public static final String REMOTE_BUSINESS_INTERFACE_LABEL = EJBUIMessages.REMOTE_BUSINESS_INTERFACE_LABEL;
	public static final String LOCAL_BUSINESS_INTERFACE = EJBUIMessages.LOCAL_BUSINESS_INTERFACE;
	public static final String LOCAL_BUSINESS_INTERFACE_LABEL = EJBUIMessages.LOCAL_BUSINESS_INTERFACE_LABEL;
	public static final String NO_INTERFACE = EJBUIMessages.NO_INTERFACE;
	public static final String ASYNC = EJBUIMessages.ASYNC;
	public static final String NON_PERSISTENT = EJBUIMessages.NON_PERSISTENT;
	
	public static final String EJB_NAME = EJBUIMessages.EJB_NAME;
	
	public final static String TRANSACTION_TYPE_CONTAINER = EJBUIMessages.TRANSACTION_TYPE_CONTAINER;
	public final static String TRANSACTION_TYPE_BEAN = EJBUIMessages.TRANSACTION_TYPE_BEAN;
	
	public final static String STATE_TYPE_STATELESS = EJBUIMessages.STATE_TYPE_STATELESS;
	public final static String STATE_TYPE_STATEFUL = EJBUIMessages.STATE_TYPE_STATEFUL;
	
	//New Message-Driven Bean wizard
	public final static String ADD_MESSAGE_DRIVEN_BEANS_WIZARD_PAGE_TITLE = EJBUIMessages.ADD_MESSAGE_DRIVEN_BEAN_WIZARD_PAGE_TITLE;
	public final static String DESTINATION_LABEL = EJBUIMessages.DESTINATION_LABEL;
	public final static String DESTINATION_TYPE_QUEUE = EJBUIMessages.DESTINATION_TYPE_QUEUE;
	public final static String DESTINATION_TYPE_TOPIC = EJBUIMessages.DESTINATION_TYPE_TOPIC;
	public static final String JMS = EJBUIMessages.JMS;
	public static final String DESTINATION_NAME_LABEL = EJBUIMessages.DESTINATION_NAME_LABEL;
	public static final String ADD_MESSAGE_DRIVEN_BEAN_WIZARD_PAGE_DESC = EJBUIMessages.ADD_MESSAGE_DRIVEN_BEAN_WIZARD_PAGE_DESC;
	public static final String MESSAGE_LISTENER_INTERFACE_HYPERLINK = EJBUIMessages.MESSAGE_LISTENER_INTERFACE_HYPERLINK;
	public static final String MESSAGE_LISTENER_INTERFACE_HYPERLINK_TOOLTIP = EJBUIMessages.MESSAGE_LISTENER_INTERFACE_HYPERLINK_TOOLTIP;
	public static final String CLICK_TO_SELECT = EJBUIMessages.CLICK_TO_SELECT;
	
    public static final  String timerWizardTitle = EJBUIMessages.timerWizardTitle;
    public static final  String timerWizardDescription = EJBUIMessages.timerWizardDescription;
    public static final  String timerScheduleLabel = EJBUIMessages.timerScheduleLabel;
	
}
