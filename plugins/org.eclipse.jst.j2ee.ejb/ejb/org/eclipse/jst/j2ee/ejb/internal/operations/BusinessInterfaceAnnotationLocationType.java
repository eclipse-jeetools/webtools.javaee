/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.ejb.internal.operations;

public enum BusinessInterfaceAnnotationLocationType {
	/**
	 * The business interface annotations <code>(javax.ejb.Local and javax.ejb.Remote) </code> should be added to the 
	 * bean class of the EJB being created. 
	 */
	BEAN_CLASS_ONLY,
	
	/**
	 * The business interface annotations <code>(javax.ejb.Local and javax.ejb.Remote) </code> should be added to the 
	 * business interfaces of the EJB being created. 
	 */
	INTERFACE_ONLY,
	
	/**
	 * The business interface annotations <code>(javax.ejb.Local and javax.ejb.Remote) </code> should be added to both the 
	 * business interfaces and the bean class of the EJB being created. 
	 */
	BEAN_CLASS_AND_INTERFACE,
}
