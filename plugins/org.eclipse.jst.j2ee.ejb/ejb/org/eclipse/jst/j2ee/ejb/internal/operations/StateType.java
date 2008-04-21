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

/**
 * Enumerates the session state type values of a session bean. 
 */
public enum StateType {

	/**
	 * Represents the <code>Stateless</code> session state type. 
	 */
	STATELESS,
	
	/**
	 * Represents the <code>Stateful</code> session state type.
	 */
	STATEFUL;

}
