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
package org.eclipse.jem.internal.beaninfo.impl;
/*
 *  $RCSfile: BeaninfoFactoryImpl.java,v $
 *  $Revision: 1.4 $  $Date: 2004/08/27 15:33:31 $ 
 */

import java.util.Map;

import org.eclipse.jem.internal.beaninfo.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */


public class BeaninfoFactoryImpl extends EFactoryImpl implements BeaninfoFactory{

	/**
	 * Creates and instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */	
	public BeaninfoFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BeaninfoPackage.FEATURE_DECORATOR: return createFeatureDecorator();
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE: return createFeatureAttributeValue();
			case BeaninfoPackage.BEAN_DECORATOR: return createBeanDecorator();
			case BeaninfoPackage.EVENT_SET_DECORATOR: return createEventSetDecorator();
			case BeaninfoPackage.METHOD_DECORATOR: return createMethodDecorator();
			case BeaninfoPackage.PARAMETER_DECORATOR: return createParameterDecorator();
			case BeaninfoPackage.PROPERTY_DECORATOR: return createPropertyDecorator();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR: return createIndexedPropertyDecorator();
			case BeaninfoPackage.METHOD_PROXY: return createMethodProxy();
			case BeaninfoPackage.BEAN_EVENT: return createBeanEvent();
			case BeaninfoPackage.FEATURE_ATTRIBUTE_MAP_ENTRY: return (EObject)createFeatureAttributeMapEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureDecorator createFeatureDecorator() {
		FeatureDecoratorImpl featureDecorator = new FeatureDecoratorImpl();
		return featureDecorator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventSetDecorator createEventSetDecorator() {
		EventSetDecoratorImpl eventSetDecorator = new EventSetDecoratorImpl();
		return eventSetDecorator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodProxy createMethodProxy() {
		MethodProxyImpl methodProxy = new MethodProxyImpl();
		return methodProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyDecorator createPropertyDecorator() {
		PropertyDecoratorImpl propertyDecorator = new PropertyDecoratorImpl();
		return propertyDecorator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IndexedPropertyDecorator createIndexedPropertyDecorator() {
		IndexedPropertyDecoratorImpl indexedPropertyDecorator = new IndexedPropertyDecoratorImpl();
		return indexedPropertyDecorator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BeanDecorator createBeanDecorator() {
		BeanDecoratorImpl beanDecorator = new BeanDecoratorImpl();
		return beanDecorator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDecorator createMethodDecorator() {
		MethodDecoratorImpl methodDecorator = new MethodDecoratorImpl();
		return methodDecorator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterDecorator createParameterDecorator() {
		ParameterDecoratorImpl parameterDecorator = new ParameterDecoratorImpl();
		return parameterDecorator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureAttributeValue createFeatureAttributeValue() {
		FeatureAttributeValueImpl featureAttributeValue = new FeatureAttributeValueImpl();
		return featureAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BeaninfoPackage getBeaninfoPackage() {
		return (BeaninfoPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static BeaninfoPackage getPackage() {
		return BeaninfoPackage.eINSTANCE;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BeanEvent createBeanEvent() {
		BeanEventImpl beanEvent = new BeanEventImpl();
		return beanEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry createFeatureAttributeMapEntry() {
		FeatureAttributeMapEntryImpl featureAttributeMapEntry = new FeatureAttributeMapEntryImpl();
		return featureAttributeMapEntry;
	}

}
