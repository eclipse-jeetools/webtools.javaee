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
 *  $RCSfile: BeaninfoPackageImpl.java,v $
 *  $Revision: 1.6 $  $Date: 2005/02/08 21:54:02 $ 
 */

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;

import org.eclipse.jem.internal.beaninfo.BeanDecorator;
import org.eclipse.jem.internal.beaninfo.BeanEvent;
import org.eclipse.jem.internal.beaninfo.BeaninfoFactory;
import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.EventSetDecorator;
import org.eclipse.jem.internal.beaninfo.FeatureDecorator;
import org.eclipse.jem.internal.beaninfo.ImplicitItem;
import org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator;
import org.eclipse.jem.internal.beaninfo.MethodDecorator;
import org.eclipse.jem.internal.beaninfo.MethodProxy;
import org.eclipse.jem.internal.beaninfo.ParameterDecorator;
import org.eclipse.jem.internal.beaninfo.PropertyDecorator;
import org.eclipse.jem.internal.beaninfo.common.FeatureAttributeValue;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.impl.JavaRefPackageImpl;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */

public class BeaninfoPackageImpl extends EPackageImpl implements BeaninfoPackage{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass beanDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventSetDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass indexedPropertyDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodProxyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass beanEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureAttributeMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum implicitItemEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType featureAttributeValueEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BeaninfoPackageImpl() {
		super(eNS_URI, BeaninfoFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BeaninfoPackage init() {
		if (isInited) return (BeaninfoPackage)EPackage.Registry.INSTANCE.getEPackage(BeaninfoPackage.eNS_URI);

		// Obtain or create and register package
		BeaninfoPackageImpl theBeaninfoPackage = (BeaninfoPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof BeaninfoPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new BeaninfoPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();
		JavaRefPackageImpl.init();

		// Create package meta-data objects
		theBeaninfoPackage.createPackageContents();

		// Initialize created meta-data
		theBeaninfoPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBeaninfoPackage.freeze();

		return theBeaninfoPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFeatureDecorator() {
		return featureDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureDecorator_DisplayName() {
		return (EAttribute)featureDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureDecorator_ShortDescription() {
		return (EAttribute)featureDecoratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureDecorator_Category() {
		return (EAttribute)featureDecoratorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureDecorator_Expert() {
		return (EAttribute)featureDecoratorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureDecorator_Hidden() {
		return (EAttribute)featureDecoratorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureDecorator_Preferred() {
		return (EAttribute)featureDecoratorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureDecorator_MergeIntrospection() {
		return (EAttribute)featureDecoratorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureDecorator_AttributesExplicitEmpty() {
		return (EAttribute)featureDecoratorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureDecorator_ImplicitlySetBits() {
		return (EAttribute)featureDecoratorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureDecorator_ImplicitDecoratorFlag() {
		return (EAttribute)featureDecoratorEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFeatureDecorator_Attributes() {
		return (EReference)featureDecoratorEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventSetDecorator() {
		return eventSetDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventSetDecorator_InDefaultEventSet() {
		return (EAttribute)eventSetDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventSetDecorator_Unicast() {
		return (EAttribute)eventSetDecoratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventSetDecorator_ListenerMethodsExplicitEmpty() {
		return (EAttribute)eventSetDecoratorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventSetDecorator_AddListenerMethod() {
		return (EReference)eventSetDecoratorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventSetDecorator_ListenerMethods() {
		return (EReference)eventSetDecoratorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventSetDecorator_ListenerType() {
		return (EReference)eventSetDecoratorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventSetDecorator_RemoveListenerMethod() {
		return (EReference)eventSetDecoratorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventSetDecorator_EventAdapterClass() {
		return (EReference)eventSetDecoratorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventSetDecorator_SerListMthd() {
		return (EReference)eventSetDecoratorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodProxy() {
		return methodProxyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodProxy_Method() {
		return (EReference)methodProxyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyDecorator() {
		return propertyDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyDecorator_Bound() {
		return (EAttribute)propertyDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyDecorator_Constrained() {
		return (EAttribute)propertyDecoratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyDecorator_DesignTime() {
		return (EAttribute)propertyDecoratorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyDecorator_AlwaysIncompatible() {
		return (EAttribute)propertyDecoratorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyDecorator_FilterFlags() {
		return (EAttribute)propertyDecoratorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyDecorator_FieldReadOnly() {
		return (EAttribute)propertyDecoratorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyDecorator_PropertyEditorClass() {
		return (EReference)propertyDecoratorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyDecorator_ReadMethod() {
		return (EReference)propertyDecoratorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyDecorator_WriteMethod() {
		return (EReference)propertyDecoratorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyDecorator_Field() {
		return (EReference)propertyDecoratorEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIndexedPropertyDecorator() {
		return indexedPropertyDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIndexedPropertyDecorator_IndexedReadMethod() {
		return (EReference)indexedPropertyDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIndexedPropertyDecorator_IndexedWriteMethod() {
		return (EReference)indexedPropertyDecoratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBeanDecorator() {
		return beanDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeanDecorator_MergeSuperProperties() {
		return (EAttribute)beanDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeanDecorator_MergeSuperMethods() {
		return (EAttribute)beanDecoratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeanDecorator_MergeSuperEvents() {
		return (EAttribute)beanDecoratorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeanDecorator_IntrospectProperties() {
		return (EAttribute)beanDecoratorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeanDecorator_IntrospectMethods() {
		return (EAttribute)beanDecoratorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeanDecorator_IntrospectEvents() {
		return (EAttribute)beanDecoratorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBeanDecorator_CustomizerClass() {
		return (EReference)beanDecoratorEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodDecorator() {
		return methodDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodDecorator_ParmsExplicitEmpty() {
		return (EAttribute)methodDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDecorator_ParameterDescriptors() {
		return (EReference)methodDecoratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDecorator_SerParmDesc() {
		return (EReference)methodDecoratorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterDecorator() {
		return parameterDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameterDecorator_Name() {
		return (EAttribute)parameterDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getFeatureAttributeValue() {
		return featureAttributeValueEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BeaninfoFactory getBeaninfoFactory() {
		return (BeaninfoFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		featureDecoratorEClass = createEClass(FEATURE_DECORATOR);
		createEAttribute(featureDecoratorEClass, FEATURE_DECORATOR__DISPLAY_NAME);
		createEAttribute(featureDecoratorEClass, FEATURE_DECORATOR__SHORT_DESCRIPTION);
		createEAttribute(featureDecoratorEClass, FEATURE_DECORATOR__CATEGORY);
		createEAttribute(featureDecoratorEClass, FEATURE_DECORATOR__EXPERT);
		createEAttribute(featureDecoratorEClass, FEATURE_DECORATOR__HIDDEN);
		createEAttribute(featureDecoratorEClass, FEATURE_DECORATOR__PREFERRED);
		createEAttribute(featureDecoratorEClass, FEATURE_DECORATOR__MERGE_INTROSPECTION);
		createEAttribute(featureDecoratorEClass, FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY);
		createEAttribute(featureDecoratorEClass, FEATURE_DECORATOR__IMPLICITLY_SET_BITS);
		createEAttribute(featureDecoratorEClass, FEATURE_DECORATOR__IMPLICIT_DECORATOR_FLAG);
		createEReference(featureDecoratorEClass, FEATURE_DECORATOR__ATTRIBUTES);

		beanDecoratorEClass = createEClass(BEAN_DECORATOR);
		createEAttribute(beanDecoratorEClass, BEAN_DECORATOR__MERGE_SUPER_PROPERTIES);
		createEAttribute(beanDecoratorEClass, BEAN_DECORATOR__MERGE_SUPER_METHODS);
		createEAttribute(beanDecoratorEClass, BEAN_DECORATOR__MERGE_SUPER_EVENTS);
		createEAttribute(beanDecoratorEClass, BEAN_DECORATOR__INTROSPECT_PROPERTIES);
		createEAttribute(beanDecoratorEClass, BEAN_DECORATOR__INTROSPECT_METHODS);
		createEAttribute(beanDecoratorEClass, BEAN_DECORATOR__INTROSPECT_EVENTS);
		createEAttribute(beanDecoratorEClass, BEAN_DECORATOR__DO_BEANINFO);
		createEAttribute(beanDecoratorEClass, BEAN_DECORATOR__NOT_INHERITED_PROPERTY_NAMES);
		createEAttribute(beanDecoratorEClass, BEAN_DECORATOR__NOT_INHERITED_METHOD_NAMES);
		createEAttribute(beanDecoratorEClass, BEAN_DECORATOR__NOT_INHERITED_EVENT_NAMES);
		createEReference(beanDecoratorEClass, BEAN_DECORATOR__CUSTOMIZER_CLASS);

		eventSetDecoratorEClass = createEClass(EVENT_SET_DECORATOR);
		createEAttribute(eventSetDecoratorEClass, EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET);
		createEAttribute(eventSetDecoratorEClass, EVENT_SET_DECORATOR__UNICAST);
		createEAttribute(eventSetDecoratorEClass, EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT_EMPTY);
		createEReference(eventSetDecoratorEClass, EVENT_SET_DECORATOR__ADD_LISTENER_METHOD);
		createEReference(eventSetDecoratorEClass, EVENT_SET_DECORATOR__LISTENER_METHODS);
		createEReference(eventSetDecoratorEClass, EVENT_SET_DECORATOR__LISTENER_TYPE);
		createEReference(eventSetDecoratorEClass, EVENT_SET_DECORATOR__REMOVE_LISTENER_METHOD);
		createEReference(eventSetDecoratorEClass, EVENT_SET_DECORATOR__EVENT_ADAPTER_CLASS);
		createEReference(eventSetDecoratorEClass, EVENT_SET_DECORATOR__SER_LIST_MTHD);

		methodDecoratorEClass = createEClass(METHOD_DECORATOR);
		createEAttribute(methodDecoratorEClass, METHOD_DECORATOR__PARMS_EXPLICIT_EMPTY);
		createEReference(methodDecoratorEClass, METHOD_DECORATOR__PARAMETER_DESCRIPTORS);
		createEReference(methodDecoratorEClass, METHOD_DECORATOR__SER_PARM_DESC);

		parameterDecoratorEClass = createEClass(PARAMETER_DECORATOR);
		createEAttribute(parameterDecoratorEClass, PARAMETER_DECORATOR__NAME);
		createEReference(parameterDecoratorEClass, PARAMETER_DECORATOR__PARAMETER);

		propertyDecoratorEClass = createEClass(PROPERTY_DECORATOR);
		createEAttribute(propertyDecoratorEClass, PROPERTY_DECORATOR__BOUND);
		createEAttribute(propertyDecoratorEClass, PROPERTY_DECORATOR__CONSTRAINED);
		createEAttribute(propertyDecoratorEClass, PROPERTY_DECORATOR__DESIGN_TIME);
		createEAttribute(propertyDecoratorEClass, PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE);
		createEAttribute(propertyDecoratorEClass, PROPERTY_DECORATOR__FILTER_FLAGS);
		createEAttribute(propertyDecoratorEClass, PROPERTY_DECORATOR__FIELD_READ_ONLY);
		createEReference(propertyDecoratorEClass, PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS);
		createEReference(propertyDecoratorEClass, PROPERTY_DECORATOR__READ_METHOD);
		createEReference(propertyDecoratorEClass, PROPERTY_DECORATOR__WRITE_METHOD);
		createEReference(propertyDecoratorEClass, PROPERTY_DECORATOR__FIELD);

		indexedPropertyDecoratorEClass = createEClass(INDEXED_PROPERTY_DECORATOR);
		createEReference(indexedPropertyDecoratorEClass, INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD);
		createEReference(indexedPropertyDecoratorEClass, INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD);

		methodProxyEClass = createEClass(METHOD_PROXY);
		createEReference(methodProxyEClass, METHOD_PROXY__METHOD);

		beanEventEClass = createEClass(BEAN_EVENT);

		featureAttributeMapEntryEClass = createEClass(FEATURE_ATTRIBUTE_MAP_ENTRY);
		createEAttribute(featureAttributeMapEntryEClass, FEATURE_ATTRIBUTE_MAP_ENTRY__KEY);
		createEAttribute(featureAttributeMapEntryEClass, FEATURE_ATTRIBUTE_MAP_ENTRY__VALUE);

		// Create enums
		implicitItemEEnum = createEEnum(IMPLICIT_ITEM);

		// Create data types
		featureAttributeValueEDataType = createEDataType(FEATURE_ATTRIBUTE_VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackageImpl theEcorePackage = (EcorePackageImpl)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		JavaRefPackageImpl theJavaRefPackage = (JavaRefPackageImpl)EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI);

		// Add supertypes to classes
		featureDecoratorEClass.getESuperTypes().add(theEcorePackage.getEAnnotation());
		beanDecoratorEClass.getESuperTypes().add(this.getFeatureDecorator());
		eventSetDecoratorEClass.getESuperTypes().add(this.getFeatureDecorator());
		methodDecoratorEClass.getESuperTypes().add(this.getFeatureDecorator());
		parameterDecoratorEClass.getESuperTypes().add(this.getFeatureDecorator());
		propertyDecoratorEClass.getESuperTypes().add(this.getFeatureDecorator());
		indexedPropertyDecoratorEClass.getESuperTypes().add(this.getPropertyDecorator());
		methodProxyEClass.getESuperTypes().add(theEcorePackage.getEOperation());
		beanEventEClass.getESuperTypes().add(theJavaRefPackage.getJavaEvent());

		// Initialize classes and features; add operations and parameters
		initEClass(featureDecoratorEClass, FeatureDecorator.class, "FeatureDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFeatureDecorator_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureDecorator_ShortDescription(), ecorePackage.getEString(), "shortDescription", null, 0, 1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureDecorator_Category(), ecorePackage.getEString(), "category", null, 0, 1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureDecorator_Expert(), ecorePackage.getEBoolean(), "expert", null, 0, 1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureDecorator_Hidden(), ecorePackage.getEBoolean(), "hidden", null, 0, 1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureDecorator_Preferred(), ecorePackage.getEBoolean(), "preferred", null, 0, 1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureDecorator_MergeIntrospection(), ecorePackage.getEBoolean(), "mergeIntrospection", "true", 0, 1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureDecorator_AttributesExplicitEmpty(), ecorePackage.getEBoolean(), "attributesExplicitEmpty", null, 0, 1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureDecorator_ImplicitlySetBits(), ecorePackage.getELong(), "implicitlySetBits", null, 0, 1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureDecorator_ImplicitDecoratorFlag(), this.getImplicitItem(), "implicitDecoratorFlag", null, 0, 1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeatureDecorator_Attributes(), this.getFeatureAttributeMapEntry(), null, "attributes", null, 0, -1, FeatureDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(featureDecoratorEClass, ecorePackage.getEString(), "getName");

		initEClass(beanDecoratorEClass, BeanDecorator.class, "BeanDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBeanDecorator_MergeSuperProperties(), ecorePackage.getEBoolean(), "mergeSuperProperties", "true", 0, 1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeanDecorator_MergeSuperMethods(), ecorePackage.getEBoolean(), "mergeSuperMethods", "true", 0, 1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeanDecorator_MergeSuperEvents(), ecorePackage.getEBoolean(), "mergeSuperEvents", "true", 0, 1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeanDecorator_IntrospectProperties(), ecorePackage.getEBoolean(), "introspectProperties", "true", 0, 1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeanDecorator_IntrospectMethods(), ecorePackage.getEBoolean(), "introspectMethods", "true", 0, 1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeanDecorator_IntrospectEvents(), ecorePackage.getEBoolean(), "introspectEvents", "true", 0, 1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeanDecorator_DoBeaninfo(), ecorePackage.getEBoolean(), "doBeaninfo", "true", 0, 1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeanDecorator_NotInheritedPropertyNames(), ecorePackage.getEString(), "notInheritedPropertyNames", null, 0, -1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeanDecorator_NotInheritedMethodNames(), ecorePackage.getEString(), "notInheritedMethodNames", null, 0, -1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeanDecorator_NotInheritedEventNames(), ecorePackage.getEString(), "notInheritedEventNames", null, 0, -1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBeanDecorator_CustomizerClass(), theJavaRefPackage.getJavaClass(), null, "customizerClass", null, 0, 1, BeanDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventSetDecoratorEClass, EventSetDecorator.class, "EventSetDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventSetDecorator_InDefaultEventSet(), ecorePackage.getEBoolean(), "inDefaultEventSet", null, 0, 1, EventSetDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventSetDecorator_Unicast(), ecorePackage.getEBoolean(), "unicast", null, 0, 1, EventSetDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventSetDecorator_ListenerMethodsExplicitEmpty(), ecorePackage.getEBoolean(), "listenerMethodsExplicitEmpty", null, 0, 1, EventSetDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventSetDecorator_AddListenerMethod(), theJavaRefPackage.getMethod(), null, "addListenerMethod", null, 1, 1, EventSetDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventSetDecorator_ListenerMethods(), this.getMethodProxy(), null, "listenerMethods", null, 1, -1, EventSetDecorator.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEventSetDecorator_ListenerType(), theJavaRefPackage.getJavaClass(), null, "listenerType", null, 1, 1, EventSetDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventSetDecorator_RemoveListenerMethod(), theJavaRefPackage.getMethod(), null, "removeListenerMethod", null, 1, 1, EventSetDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventSetDecorator_EventAdapterClass(), theJavaRefPackage.getJavaClass(), null, "eventAdapterClass", null, 0, 1, EventSetDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventSetDecorator_SerListMthd(), this.getMethodProxy(), null, "serListMthd", null, 1, -1, EventSetDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodDecoratorEClass, MethodDecorator.class, "MethodDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodDecorator_ParmsExplicitEmpty(), ecorePackage.getEBoolean(), "parmsExplicitEmpty", null, 0, 1, MethodDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethodDecorator_ParameterDescriptors(), this.getParameterDecorator(), null, "parameterDescriptors", null, 0, -1, MethodDecorator.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMethodDecorator_SerParmDesc(), this.getParameterDecorator(), null, "serParmDesc", null, 0, -1, MethodDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterDecoratorEClass, ParameterDecorator.class, "ParameterDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameterDecorator_Name(), ecorePackage.getEString(), "name", null, 0, 1, ParameterDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParameterDecorator_Parameter(), theJavaRefPackage.getJavaParameter(), null, "parameter", null, 0, 1, ParameterDecorator.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyDecoratorEClass, PropertyDecorator.class, "PropertyDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPropertyDecorator_Bound(), ecorePackage.getEBoolean(), "bound", null, 0, 1, PropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyDecorator_Constrained(), ecorePackage.getEBoolean(), "constrained", null, 0, 1, PropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyDecorator_DesignTime(), ecorePackage.getEBoolean(), "designTime", null, 0, 1, PropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyDecorator_AlwaysIncompatible(), ecorePackage.getEBoolean(), "alwaysIncompatible", null, 0, 1, PropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyDecorator_FilterFlags(), ecorePackage.getEString(), "filterFlags", null, 0, -1, PropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyDecorator_FieldReadOnly(), ecorePackage.getEBoolean(), "fieldReadOnly", null, 0, 1, PropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyDecorator_PropertyEditorClass(), theJavaRefPackage.getJavaClass(), null, "propertyEditorClass", null, 0, 1, PropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyDecorator_ReadMethod(), theJavaRefPackage.getMethod(), null, "readMethod", null, 0, 1, PropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyDecorator_WriteMethod(), theJavaRefPackage.getMethod(), null, "writeMethod", null, 0, 1, PropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyDecorator_Field(), theJavaRefPackage.getField(), null, "field", null, 0, 1, PropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(propertyDecoratorEClass, theEcorePackage.getEClassifier(), "getPropertyType");

		initEClass(indexedPropertyDecoratorEClass, IndexedPropertyDecorator.class, "IndexedPropertyDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIndexedPropertyDecorator_IndexedReadMethod(), theJavaRefPackage.getMethod(), null, "indexedReadMethod", null, 0, 1, IndexedPropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIndexedPropertyDecorator_IndexedWriteMethod(), theJavaRefPackage.getMethod(), null, "indexedWriteMethod", null, 0, 1, IndexedPropertyDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodProxyEClass, MethodProxy.class, "MethodProxy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMethodProxy_Method(), theJavaRefPackage.getMethod(), null, "method", null, 1, 1, MethodProxy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(beanEventEClass, BeanEvent.class, "BeanEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(featureAttributeMapEntryEClass, Map.Entry.class, "FeatureAttributeMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFeatureAttributeMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureAttributeMapEntry_Value(), this.getFeatureAttributeValue(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(implicitItemEEnum, ImplicitItem.class, "ImplicitItem");
		addEEnumLiteral(implicitItemEEnum, ImplicitItem.NOT_IMPLICIT_LITERAL);
		addEEnumLiteral(implicitItemEEnum, ImplicitItem.IMPLICIT_DECORATOR_LITERAL);
		addEEnumLiteral(implicitItemEEnum, ImplicitItem.IMPLICIT_DECORATOR_AND_FEATURE_LITERAL);

		// Initialize data types
		initEDataType(featureAttributeValueEDataType, FeatureAttributeValue.class, "FeatureAttributeValue", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeanDecorator_DoBeaninfo() {
		return (EAttribute)beanDecoratorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeanDecorator_NotInheritedPropertyNames() {
		return (EAttribute)beanDecoratorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeanDecorator_NotInheritedMethodNames() {
		return (EAttribute)beanDecoratorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeanDecorator_NotInheritedEventNames() {
		return (EAttribute)beanDecoratorEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterDecorator_Parameter() {
		return (EReference)parameterDecoratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBeanEvent() {
		return beanEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFeatureAttributeMapEntry() {
		return featureAttributeMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureAttributeMapEntry_Key() {
		return (EAttribute)featureAttributeMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureAttributeMapEntry_Value() {
		return (EAttribute)featureAttributeMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getImplicitItem() {
		return implicitItemEEnum;
	}

}
