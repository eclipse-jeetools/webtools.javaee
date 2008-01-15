/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

public interface INewMessageDrivenBeanClassDataModelProperties extends INewEnterpriseBeanClassDataModelProperties {

	/**
	 * Optional, boolean property used to specify whether to generate a
	 * JMS interface. The default is false.
	 */
	public static final String JMS = "INewMessageDrivenBeanClassDataModelProperties.JMS"; //$NON-NLS-1$
	
	/**
	 * Required, <code>DestinationType</code> enumeration property that
	 * determines the destination type of the message-driven bean. The default
	 * is <code>DestinationType.QUEUE</code>.
	 * 
	 * @see DestinationType
	 */
	public static final String DESTINATION_TYPE = "INewMessageDrivenBeanClassDataModelProperties.DESTINATION_TYPE"; //$NON-NLS-1$
	
}
