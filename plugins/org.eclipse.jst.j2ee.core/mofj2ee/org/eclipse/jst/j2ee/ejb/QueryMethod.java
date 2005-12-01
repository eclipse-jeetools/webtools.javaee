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
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.jem.java.JavaClass;
/**
 * @generated
 * @since 1.0 */
public interface QueryMethod extends MethodElement{
	public static final String SELECT_PREFIX = "ejbSelect"; //$NON-NLS-1$
	public static final String FIND_PREFIX = "find"; //$NON-NLS-1$
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Query reference
	 */
	Query getQuery();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Query reference
	 */
	void setQuery(Query value);

	/**
	 * Return an array of JavaClasses of all the interfaces or bean class that this method is presented
	 * to a client.  If it is an ejbSelect... on the bean class will be returned and if
	 * it if a find both home interfaces will be returned if they exist.
	 * 
	 * May return null.
	 */
	JavaClass[] getClientTypeJavaClasses() ;
}





