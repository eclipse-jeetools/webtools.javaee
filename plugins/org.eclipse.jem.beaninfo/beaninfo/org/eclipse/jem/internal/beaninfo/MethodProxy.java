/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo;
/*
 *  $RCSfile: MethodProxy.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/04 23:11:53 $ 
 */


import org.eclipse.emf.ecore.EOperation;

import org.eclipse.jem.java.Method;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Proxy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This is just a wrapper of a java Method. It allows access to the method but doesn't duplicate the interface for it.
 * <p>
 * MethodProxies will be in the eBehaviors setting for any methods that are in the JavaClass methods setting so that they are not duplicated.
 * <p>
 * MethodProxies would also have MethodDecorators.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.MethodProxy#getMethod <em>Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getMethodProxy()
 * @model 
 * @generated
 */


public interface MethodProxy extends EOperation{
	/**
	 * Returns the value of the '<em><b>Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' reference.
	 * @see #setMethod(Method)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getMethodProxy_Method()
	 * @model required="true"
	 * @generated
	 */
	Method getMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.MethodProxy#getMethod <em>Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' reference.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(Method value);

}
