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
package org.eclipse.jst.j2ee.common;



/**
 * The security-identity element specifies whether the caller's security identity is to be used for the execution of the methods of the enterprise bean or whether a specific run-as identity is to be used. It    contains an optional description and a specification of the security identity to be used.

 */
public interface RunAsSpecifiedIdentity extends SecurityIdentity{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Identity reference
	 * Optional specifies the run-as identity to be used for the execution of the
	 * methods of an enterprise bean. It contains an optional description, and the
	 * name of a security role.
	 */
	Identity getIdentity();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Identity reference
	 */
	void setIdentity(Identity value);

}





