package org.eclipse.jem.internal.beaninfo;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeaninfoFactory.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/04 23:11:53 $ 
 */


import org.eclipse.emf.ecore.EFactory;
/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage
 * @generated
 */


public interface BeaninfoFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BeaninfoFactory eINSTANCE = new org.eclipse.jem.internal.beaninfo.impl.BeaninfoFactoryImpl();

	/**
	 * Returns a new object of class '<em>Feature Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature Decorator</em>'.
	 * @generated
	 */
	FeatureDecorator createFeatureDecorator();

	/**
	 * Returns a new object of class '<em>Event Set Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event Set Decorator</em>'.
	 * @generated
	 */
	EventSetDecorator createEventSetDecorator();

	/**
	 * Returns a new object of class '<em>Method Proxy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Proxy</em>'.
	 * @generated
	 */
	MethodProxy createMethodProxy();

	/**
	 * Returns a new object of class '<em>Property Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property Decorator</em>'.
	 * @generated
	 */
	PropertyDecorator createPropertyDecorator();

	/**
	 * Returns a new object of class '<em>Indexed Property Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Indexed Property Decorator</em>'.
	 * @generated
	 */
	IndexedPropertyDecorator createIndexedPropertyDecorator();

	/**
	 * Returns a new object of class '<em>Bean Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bean Decorator</em>'.
	 * @generated
	 */
	BeanDecorator createBeanDecorator();

	/**
	 * Returns a new object of class '<em>Method Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Decorator</em>'.
	 * @generated
	 */
	MethodDecorator createMethodDecorator();

	/**
	 * Returns a new object of class '<em>Parameter Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter Decorator</em>'.
	 * @generated
	 */
	ParameterDecorator createParameterDecorator();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BeaninfoPackage getBeaninfoPackage();

	/**
	 * Returns a new object of class '<em>Bean Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bean Event</em>'.
	 * @generated
	 */
	BeanEvent createBeanEvent();

}
