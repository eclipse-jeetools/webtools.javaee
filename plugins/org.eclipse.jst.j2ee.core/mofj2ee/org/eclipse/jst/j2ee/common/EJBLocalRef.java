/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common;




/**
 * The ejb-local-ref element is used for the declaration of a reference to another enterprise bean's local home. The declaration consists of an optional description; the EJB reference name used in the code of the referencing enterprise bean; the expected type of the referenced enterprise bean; the expected local home and local interfaces of the referenced enterprise bean; and an optional ejb-link information. The optional ejb-link element is used to specify the referenced enterprise bean.
 * Used in: entity, session, message-driven
 * @since 1.0 */
public interface EJBLocalRef extends EjbRef{

	/**
	 * @return The value of the Local attribute
	 * The Local element contains the fully-qualified name of the enterprise bean's local interface.
	 */
	public String getLocal();
	/**
	 * @return The value of the localHome attribute
	 * The local home element contains the fully-qualified name of the enterprise bean's local home interface.
	 * Example: <home>com.aardvark.payroll.PayrollHome<//home>
	 */
	public String getLocalHome();
	/**
	 * @param value The new value of the local attribute
	 */
	public void setLocal(String value);
	/**
	 * @param value The new value of the localHome attribute
	 */
	public void setLocalHome(String value);
}





