/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;

public interface IValidationRuleList {
	public static final Object EJB11_EJBJAR = new Integer(1); // EJB 1.1 projects
	public static final Object EJB11_EJBEXT = new Integer(2); // EJB 1.1 projects
	public static final Object EJB20_EJBJAR = new Integer(3); // EJB 2.0 projects
	public static final Object EJB20_EJBEXT = new Integer(4); // EJB 2.0 projects
	
	public static final Object EJB11_ENTERPRISEBEAN = new Integer(5); // EJB 1.1 or EJB 2.0 projects
	public static final Object EJB20_ENTERPRISEBEAN = new Integer(6); // EJB 2.0 projects
	
	public static final Object EJB11_SESSION_BEANCLASS = new Integer(7); // EJB 1.1 or EJB 2.0 projects
	public static final Object EJB11_SESSION_REMOTE = new Integer(8); // EJB 1.1 or EJB 2.0 projects
	public static final Object EJB11_SESSION_HOME = new Integer(9); // EJB 1.1 or EJB 2.0 projects
	
	public static final Object EJB11_CMP_BEANCLASS = new Integer(10); // EJB 1.1 or EJB 2.0 projects
	public static final Object EJB11_CMP_REMOTE = new Integer(11); // EJB 1.1 or EJB 2.0 projects
	public static final Object EJB11_CMP_HOME = new Integer(12); // EJB 1.1 or EJB 2.0 projects
	public static final Object EJB11_CMP_KEYCLASS = new Integer(13); // EJB 1.1 or EJB 2.0 projects

	public static final Object EJB11_BMP_BEANCLASS = new Integer(14); // EJB 1.1 or EJB 2.0 projects
	public static final Object EJB11_BMP_REMOTE = new Integer(15); // EJB 1.1 or EJB 2.0 projects
	public static final Object EJB11_BMP_HOME = new Integer(16); // EJB 1.1 or EJB 2.0 projects
	public static final Object EJB11_BMP_KEYCLASS = new Integer(17); // EJB 1.1 or EJB 2.0 projects

	public static final Object EJB20_STATELESS_SESSION_BEANCLASS = new Integer(18); // EJB 2.0 projects
	public static final Object EJB20_STATELESS_SESSION_REMOTE = new Integer(19); // EJB 2.0 projects
	public static final Object EJB20_STATELESS_SESSION_HOME = new Integer(20); // EJB 2.0 projects
	public static final Object EJB20_STATELESS_SESSION_LOCAL = new Integer(21); // EJB 2.0 projects
	public static final Object EJB20_STATELESS_SESSION_LOCALHOME = new Integer(22); // EJB 2.0 projects
	
	public static final Object EJB20_STATEFUL_SESSION_BEANCLASS = new Integer(23); // EJB 2.0 projects
	public static final Object EJB20_STATEFUL_SESSION_REMOTE = new Integer(24); // EJB 2.0 projects
	public static final Object EJB20_STATEFUL_SESSION_HOME = new Integer(25); // EJB 2.0 projects
	public static final Object EJB20_STATEFUL_SESSION_LOCAL = new Integer(26); // EJB 2.0 projects
	public static final Object EJB20_STATEFUL_SESSION_LOCALHOME = new Integer(27); // EJB 2.0 projects
	
	public static final Object EJB20_CMP_BEANCLASS = new Integer(28); // EJB 2.0 projects
	public static final Object EJB20_CMP_REMOTE = new Integer(29); // EJB 2.0 projects
	public static final Object EJB20_CMP_HOME = new Integer(30); // EJB 2.0 projects
	public static final Object EJB20_CMP_LOCAL = new Integer(31); // EJB 2.0 projects
	public static final Object EJB20_CMP_LOCALHOME = new Integer(32); // EJB 2.0 projects
	public static final Object EJB20_CMP_KEYCLASS = new Integer(33); // EJB 2.0 projects

	public static final Object EJB20_BMP_BEANCLASS = new Integer(34); // EJB 2.0 projects
	public static final Object EJB20_BMP_REMOTE = new Integer(35); // EJB 2.0 projects
	public static final Object EJB20_BMP_HOME = new Integer(36); // EJB 2.0 projects
	public static final Object EJB20_BMP_LOCAL = new Integer(37); // EJB 2.0 projects
	public static final Object EJB20_BMP_LOCALHOME = new Integer(38); // EJB 2.0 projects
	public static final Object EJB20_BMP_KEYCLASS = new Integer(39); // EJB 2.0 projects

	public static final Object EJB20_MESSAGE_BEANCLASS = new Integer(40); // EJB 2.0 projects
}
