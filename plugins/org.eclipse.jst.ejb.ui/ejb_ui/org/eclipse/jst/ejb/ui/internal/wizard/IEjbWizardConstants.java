/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;


public interface IEjbWizardConstants {

	public final static String NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC = EJBUIMessages.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC;
	public final static String NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC = EJBUIMessages.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC;
	public final static String ADD_SESSION_BEAN_WIZARD_PAGE_DESC = EJBUIMessages.ADD_SESSION_BEAN_WIZARD_PAGE_DESC;
	
	
	// New Enterprise Bean Wizard
	public final static String NO_EJB_PROJECTS = EJBUIMessages.NO_EJB_PROJECTS;
	public final static String ADD_BEANS_WIZARD_PAGE_TITLE = EJBUIMessages.ADD_BEANS_WIZARD_PAGE_TITLE;
	public static final String STATE_TYPE_LABEL = EJBUIMessages.STATE_TYPE_LABEL;
	public static final String CREATE_BUSSINES_INTERFACE = EJBUIMessages.CREATEBUSSINESINTERFACE;
	public static final String REMOTE_BUSSINES_INTERFACE = EJBUIMessages.REMOTE_BUSSINES_INTERFACE;
	public static final String LOCAL_BUSSINES_INTERFACE = EJBUIMessages.LOCAL_BUSSINES_INTERFACE;
	public static final String EJB_NAME = EJBUIMessages.EJB_NAME;
	
	public final static class TRANSACTIONTYPE {
	    public final static String CONTAINER = EJBUIMessages.CONTAINER;
	    public final static String BEAN = EJBUIMessages.BEAN;
	    public final static String[] LABELS = { CONTAINER, BEAN };
	  }
	public final static class BEAN_TYPE{
		public final static String STATELESS = EJBUIMessages.STATELESS;
		public final static String STATEFUL = EJBUIMessages.STATEFUL;
		public final static String[] LABELS = {STATELESS, STATEFUL};
	}
}
