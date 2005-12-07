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
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.emf.ecore.EObject;
/**
 *  <!-- begin-user-doc -->
 * @deprecated since 4/25/2003 use list on {@link org.eclipse.jst.j2ee.internal.webapplication.WebResourceCollection} 
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webapplication.URLPatternType#getUrlPattern <em>Url Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webapplication.URLPatternType#getResCollection <em>Res Collection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getURLPatternType()
 * @model
 * @generated
 * @since 1.0
 */
public interface URLPatternType extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the UrlPattern attribute
	 */
	String getUrlPattern();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the UrlPattern attribute
	 */
	void setUrlPattern(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The ResCollection reference
	 */
	WebResourceCollection getResCollection();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ResCollection reference
	 */
	void setResCollection(WebResourceCollection value);

}














