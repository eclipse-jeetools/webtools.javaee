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
 *  $RCSfile: BeaninfoPackage.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/13 16:17:00 $ 
 */

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.jem.java.JavaRefPackage;
/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoFactory
 * @generated
 */

public interface BeaninfoPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "beaninfo"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/jem/internal/beaninfo/beaninfo.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jem.internal.beaninfo.beaninfo"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BeaninfoPackage eINSTANCE = org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl.init();

	
	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl <em>Feature Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl#getFeatureDecorator()
	 * @generated
	 */
	int FEATURE_DECORATOR = 0;
	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__EANNOTATIONS = EcorePackage.EANNOTATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__SOURCE = EcorePackage.EANNOTATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Details</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__DETAILS = EcorePackage.EANNOTATION__DETAILS;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__EMODEL_ELEMENT = EcorePackage.EANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__CONTENTS = EcorePackage.EANNOTATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__REFERENCES = EcorePackage.EANNOTATION__REFERENCES;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__DISPLAY_NAME = EcorePackage.EANNOTATION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__SHORT_DESCRIPTION = EcorePackage.EANNOTATION_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__CATEGORY = EcorePackage.EANNOTATION_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Expert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__EXPERT = EcorePackage.EANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__HIDDEN = EcorePackage.EANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Preferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__PREFERRED = EcorePackage.EANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Merge Introspection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__MERGE_INTROSPECTION = EcorePackage.EANNOTATION_FEATURE_COUNT + 6;
	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl <em>Event Set Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl#getEventSetDecorator()
	 * @generated
	 */
	int EVENT_SET_DECORATOR = 3;
	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.beaninfo.impl.MethodProxyImpl <em>Method Proxy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.beaninfo.impl.MethodProxyImpl
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl#getMethodProxy()
	 * @generated
	 */
	int METHOD_PROXY = 8;
	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl <em>Property Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl#getPropertyDecorator()
	 * @generated
	 */
	int PROPERTY_DECORATOR = 6;
	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.beaninfo.impl.IndexedPropertyDecoratorImpl <em>Indexed Property Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.beaninfo.impl.IndexedPropertyDecoratorImpl
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl#getIndexedPropertyDecorator()
	 * @generated
	 */
	int INDEXED_PROPERTY_DECORATOR = 7;
	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.beaninfo.impl.BeanDecoratorImpl <em>Bean Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeanDecoratorImpl
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl#getBeanDecorator()
	 * @generated
	 */
	int BEAN_DECORATOR = 2;
	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.beaninfo.impl.MethodDecoratorImpl <em>Method Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.beaninfo.impl.MethodDecoratorImpl
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl#getMethodDecorator()
	 * @generated
	 */
	int METHOD_DECORATOR = 4;
	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.beaninfo.impl.ParameterDecoratorImpl <em>Parameter Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.beaninfo.impl.ParameterDecoratorImpl
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl#getParameterDecorator()
	 * @generated
	 */
	int PARAMETER_DECORATOR = 5;
	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.beaninfo.impl.FeatureAttributeValueImpl <em>Feature Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.beaninfo.impl.FeatureAttributeValueImpl
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl#getFeatureAttributeValue()
	 * @generated
	 */
	int FEATURE_ATTRIBUTE_VALUE = 1;
	/**
	 * The feature id for the '<em><b>Attributes Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT = EcorePackage.EANNOTATION_FEATURE_COUNT + 7;
	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR__ATTRIBUTES = EcorePackage.EANNOTATION_FEATURE_COUNT + 8;
	/**
	 * The number of structural features of the the '<em>Feature Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DECORATOR_FEATURE_COUNT = EcorePackage.EANNOTATION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ATTRIBUTE_VALUE__NAME = 0;
	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ATTRIBUTE_VALUE__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Value Proxy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ATTRIBUTE_VALUE__VALUE_PROXY = 2;
	/**
	 * The number of structural features of the the '<em>Feature Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ATTRIBUTE_VALUE_FEATURE_COUNT = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__EANNOTATIONS = FEATURE_DECORATOR__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__SOURCE = FEATURE_DECORATOR__SOURCE;

	/**
	 * The feature id for the '<em><b>Details</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__DETAILS = FEATURE_DECORATOR__DETAILS;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__EMODEL_ELEMENT = FEATURE_DECORATOR__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__CONTENTS = FEATURE_DECORATOR__CONTENTS;

	/**
	 * The feature id for the '<em><b>References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__REFERENCES = FEATURE_DECORATOR__REFERENCES;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__DISPLAY_NAME = FEATURE_DECORATOR__DISPLAY_NAME;
	/**
	 * The feature id for the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__SHORT_DESCRIPTION = FEATURE_DECORATOR__SHORT_DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__CATEGORY = FEATURE_DECORATOR__CATEGORY;
	/**
	 * The feature id for the '<em><b>Expert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__EXPERT = FEATURE_DECORATOR__EXPERT;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__HIDDEN = FEATURE_DECORATOR__HIDDEN;

	/**
	 * The feature id for the '<em><b>Preferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__PREFERRED = FEATURE_DECORATOR__PREFERRED;

	/**
	 * The feature id for the '<em><b>Merge Introspection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__MERGE_INTROSPECTION = FEATURE_DECORATOR__MERGE_INTROSPECTION;
	/**
	 * The feature id for the '<em><b>Attributes Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__ATTRIBUTES_EXPLICIT = FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT;
	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__ATTRIBUTES = FEATURE_DECORATOR__ATTRIBUTES;
	/**
	 * The feature id for the '<em><b>Merge Super Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__MERGE_SUPER_PROPERTIES = FEATURE_DECORATOR_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Merge Super Behaviors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__MERGE_SUPER_BEHAVIORS = FEATURE_DECORATOR_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Merge Super Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__MERGE_SUPER_EVENTS = FEATURE_DECORATOR_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Introspect Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__INTROSPECT_PROPERTIES = FEATURE_DECORATOR_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Introspect Behaviors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__INTROSPECT_BEHAVIORS = FEATURE_DECORATOR_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Introspect Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__INTROSPECT_EVENTS = FEATURE_DECORATOR_FEATURE_COUNT + 5;
	/**
	 * The feature id for the '<em><b>Do Beaninfo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__DO_BEANINFO = FEATURE_DECORATOR_FEATURE_COUNT + 6;
	/**
	 * The feature id for the '<em><b>Customizer Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR__CUSTOMIZER_CLASS = FEATURE_DECORATOR_FEATURE_COUNT + 7;
	/**
	 * The number of structural features of the the '<em>Bean Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_DECORATOR_FEATURE_COUNT = FEATURE_DECORATOR_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__EANNOTATIONS = FEATURE_DECORATOR__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__SOURCE = FEATURE_DECORATOR__SOURCE;

	/**
	 * The feature id for the '<em><b>Details</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__DETAILS = FEATURE_DECORATOR__DETAILS;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__EMODEL_ELEMENT = FEATURE_DECORATOR__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__CONTENTS = FEATURE_DECORATOR__CONTENTS;

	/**
	 * The feature id for the '<em><b>References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__REFERENCES = FEATURE_DECORATOR__REFERENCES;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__DISPLAY_NAME = FEATURE_DECORATOR__DISPLAY_NAME;
	/**
	 * The feature id for the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__SHORT_DESCRIPTION = FEATURE_DECORATOR__SHORT_DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__CATEGORY = FEATURE_DECORATOR__CATEGORY;
	/**
	 * The feature id for the '<em><b>Expert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__EXPERT = FEATURE_DECORATOR__EXPERT;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__HIDDEN = FEATURE_DECORATOR__HIDDEN;

	/**
	 * The feature id for the '<em><b>Preferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__PREFERRED = FEATURE_DECORATOR__PREFERRED;

	/**
	 * The feature id for the '<em><b>Merge Introspection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__MERGE_INTROSPECTION = FEATURE_DECORATOR__MERGE_INTROSPECTION;
	/**
	 * The feature id for the '<em><b>Attributes Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__ATTRIBUTES_EXPLICIT = FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT;
	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__ATTRIBUTES = FEATURE_DECORATOR__ATTRIBUTES;
	/**
	 * The feature id for the '<em><b>In Default Event Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET = FEATURE_DECORATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unicast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__UNICAST = FEATURE_DECORATOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.beaninfo.impl.BeanEventImpl <em>Bean Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeanEventImpl
	 * @see org.eclipse.jem.internal.beaninfo.impl.BeaninfoPackageImpl#getBeanEvent()
	 * @generated
	 */
	int BEAN_EVENT = 9;
	/**
	 * The feature id for the '<em><b>Listener Methods Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT = FEATURE_DECORATOR_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Add Listener Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__ADD_LISTENER_METHOD = FEATURE_DECORATOR_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Listener Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__LISTENER_METHODS = FEATURE_DECORATOR_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Listener Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__LISTENER_TYPE = FEATURE_DECORATOR_FEATURE_COUNT + 5;
	/**
	 * The feature id for the '<em><b>Remove Listener Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR__REMOVE_LISTENER_METHOD = FEATURE_DECORATOR_FEATURE_COUNT + 6;
	/**
	 * The number of structural features of the the '<em>Event Set Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SET_DECORATOR_FEATURE_COUNT = FEATURE_DECORATOR_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__EANNOTATIONS = FEATURE_DECORATOR__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__SOURCE = FEATURE_DECORATOR__SOURCE;

	/**
	 * The feature id for the '<em><b>Details</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__DETAILS = FEATURE_DECORATOR__DETAILS;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__EMODEL_ELEMENT = FEATURE_DECORATOR__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__CONTENTS = FEATURE_DECORATOR__CONTENTS;

	/**
	 * The feature id for the '<em><b>References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__REFERENCES = FEATURE_DECORATOR__REFERENCES;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__DISPLAY_NAME = FEATURE_DECORATOR__DISPLAY_NAME;
	/**
	 * The feature id for the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__SHORT_DESCRIPTION = FEATURE_DECORATOR__SHORT_DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__CATEGORY = FEATURE_DECORATOR__CATEGORY;
	/**
	 * The feature id for the '<em><b>Expert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__EXPERT = FEATURE_DECORATOR__EXPERT;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__HIDDEN = FEATURE_DECORATOR__HIDDEN;

	/**
	 * The feature id for the '<em><b>Preferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__PREFERRED = FEATURE_DECORATOR__PREFERRED;

	/**
	 * The feature id for the '<em><b>Merge Introspection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__MERGE_INTROSPECTION = FEATURE_DECORATOR__MERGE_INTROSPECTION;
	/**
	 * The feature id for the '<em><b>Attributes Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__ATTRIBUTES_EXPLICIT = FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT;
	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__ATTRIBUTES = FEATURE_DECORATOR__ATTRIBUTES;
	/**
	 * The feature id for the '<em><b>Parms Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__PARMS_EXPLICIT = FEATURE_DECORATOR_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Parameter Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR__PARAMETER_DESCRIPTORS = FEATURE_DECORATOR_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the the '<em>Method Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECORATOR_FEATURE_COUNT = FEATURE_DECORATOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__EANNOTATIONS = FEATURE_DECORATOR__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__SOURCE = FEATURE_DECORATOR__SOURCE;

	/**
	 * The feature id for the '<em><b>Details</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__DETAILS = FEATURE_DECORATOR__DETAILS;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__EMODEL_ELEMENT = FEATURE_DECORATOR__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__CONTENTS = FEATURE_DECORATOR__CONTENTS;

	/**
	 * The feature id for the '<em><b>References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__REFERENCES = FEATURE_DECORATOR__REFERENCES;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__DISPLAY_NAME = FEATURE_DECORATOR__DISPLAY_NAME;
	/**
	 * The feature id for the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__SHORT_DESCRIPTION = FEATURE_DECORATOR__SHORT_DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__CATEGORY = FEATURE_DECORATOR__CATEGORY;
	/**
	 * The feature id for the '<em><b>Expert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__EXPERT = FEATURE_DECORATOR__EXPERT;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__HIDDEN = FEATURE_DECORATOR__HIDDEN;

	/**
	 * The feature id for the '<em><b>Preferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__PREFERRED = FEATURE_DECORATOR__PREFERRED;

	/**
	 * The feature id for the '<em><b>Merge Introspection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__MERGE_INTROSPECTION = FEATURE_DECORATOR__MERGE_INTROSPECTION;
	/**
	 * The feature id for the '<em><b>Attributes Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__ATTRIBUTES_EXPLICIT = FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT;
	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__ATTRIBUTES = FEATURE_DECORATOR__ATTRIBUTES;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__NAME = FEATURE_DECORATOR_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR__PARAMETER = FEATURE_DECORATOR_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the the '<em>Parameter Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DECORATOR_FEATURE_COUNT = FEATURE_DECORATOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__EANNOTATIONS = FEATURE_DECORATOR__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__SOURCE = FEATURE_DECORATOR__SOURCE;

	/**
	 * The feature id for the '<em><b>Details</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__DETAILS = FEATURE_DECORATOR__DETAILS;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__EMODEL_ELEMENT = FEATURE_DECORATOR__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__CONTENTS = FEATURE_DECORATOR__CONTENTS;

	/**
	 * The feature id for the '<em><b>References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__REFERENCES = FEATURE_DECORATOR__REFERENCES;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__DISPLAY_NAME = FEATURE_DECORATOR__DISPLAY_NAME;
	/**
	 * The feature id for the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__SHORT_DESCRIPTION = FEATURE_DECORATOR__SHORT_DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__CATEGORY = FEATURE_DECORATOR__CATEGORY;
	/**
	 * The feature id for the '<em><b>Expert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__EXPERT = FEATURE_DECORATOR__EXPERT;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__HIDDEN = FEATURE_DECORATOR__HIDDEN;

	/**
	 * The feature id for the '<em><b>Preferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__PREFERRED = FEATURE_DECORATOR__PREFERRED;

	/**
	 * The feature id for the '<em><b>Merge Introspection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__MERGE_INTROSPECTION = FEATURE_DECORATOR__MERGE_INTROSPECTION;
	/**
	 * The feature id for the '<em><b>Attributes Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT = FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT;
	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__ATTRIBUTES = FEATURE_DECORATOR__ATTRIBUTES;
	/**
	 * The feature id for the '<em><b>Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__BOUND = FEATURE_DECORATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Constrained</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__CONSTRAINED = FEATURE_DECORATOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Design Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__DESIGN_TIME = FEATURE_DECORATOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Always Incompatible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE = FEATURE_DECORATOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Filter Flags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__FILTER_FLAGS = FEATURE_DECORATOR_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Property Editor Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS = FEATURE_DECORATOR_FEATURE_COUNT + 5;
	/**
	 * The feature id for the '<em><b>Read Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__READ_METHOD = FEATURE_DECORATOR_FEATURE_COUNT + 6;
	/**
	 * The feature id for the '<em><b>Write Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR__WRITE_METHOD = FEATURE_DECORATOR_FEATURE_COUNT + 7;
	/**
	 * The number of structural features of the the '<em>Property Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_DECORATOR_FEATURE_COUNT = FEATURE_DECORATOR_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__EANNOTATIONS = PROPERTY_DECORATOR__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__SOURCE = PROPERTY_DECORATOR__SOURCE;

	/**
	 * The feature id for the '<em><b>Details</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__DETAILS = PROPERTY_DECORATOR__DETAILS;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__EMODEL_ELEMENT = PROPERTY_DECORATOR__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__CONTENTS = PROPERTY_DECORATOR__CONTENTS;

	/**
	 * The feature id for the '<em><b>References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__REFERENCES = PROPERTY_DECORATOR__REFERENCES;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__DISPLAY_NAME = PROPERTY_DECORATOR__DISPLAY_NAME;
	/**
	 * The feature id for the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__SHORT_DESCRIPTION = PROPERTY_DECORATOR__SHORT_DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__CATEGORY = PROPERTY_DECORATOR__CATEGORY;
	/**
	 * The feature id for the '<em><b>Expert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__EXPERT = PROPERTY_DECORATOR__EXPERT;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__HIDDEN = PROPERTY_DECORATOR__HIDDEN;

	/**
	 * The feature id for the '<em><b>Preferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__PREFERRED = PROPERTY_DECORATOR__PREFERRED;

	/**
	 * The feature id for the '<em><b>Merge Introspection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__MERGE_INTROSPECTION = PROPERTY_DECORATOR__MERGE_INTROSPECTION;
	/**
	 * The feature id for the '<em><b>Attributes Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT = PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT;
	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__ATTRIBUTES = PROPERTY_DECORATOR__ATTRIBUTES;
	/**
	 * The feature id for the '<em><b>Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__BOUND = PROPERTY_DECORATOR__BOUND;

	/**
	 * The feature id for the '<em><b>Constrained</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__CONSTRAINED = PROPERTY_DECORATOR__CONSTRAINED;

	/**
	 * The feature id for the '<em><b>Design Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__DESIGN_TIME = PROPERTY_DECORATOR__DESIGN_TIME;

	/**
	 * The feature id for the '<em><b>Always Incompatible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE = PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE;

	/**
	 * The feature id for the '<em><b>Filter Flags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__FILTER_FLAGS = PROPERTY_DECORATOR__FILTER_FLAGS;
	/**
	 * The feature id for the '<em><b>Property Editor Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS = PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS;
	/**
	 * The feature id for the '<em><b>Read Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__READ_METHOD = PROPERTY_DECORATOR__READ_METHOD;
	/**
	 * The feature id for the '<em><b>Write Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__WRITE_METHOD = PROPERTY_DECORATOR__WRITE_METHOD;
	/**
	 * The feature id for the '<em><b>Indexed Read Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD = PROPERTY_DECORATOR_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Indexed Write Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD = PROPERTY_DECORATOR_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the the '<em>Indexed Property Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_PROPERTY_DECORATOR_FEATURE_COUNT = PROPERTY_DECORATOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__EANNOTATIONS = EcorePackage.EOPERATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__NAME = EcorePackage.EOPERATION__NAME;
	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__ORDERED = EcorePackage.EOPERATION__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__UNIQUE = EcorePackage.EOPERATION__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__LOWER_BOUND = EcorePackage.EOPERATION__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__UPPER_BOUND = EcorePackage.EOPERATION__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__MANY = EcorePackage.EOPERATION__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__REQUIRED = EcorePackage.EOPERATION__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__ETYPE = EcorePackage.EOPERATION__ETYPE;

	/**
	 * The feature id for the '<em><b>EContaining Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__ECONTAINING_CLASS = EcorePackage.EOPERATION__ECONTAINING_CLASS;

	/**
	 * The feature id for the '<em><b>EParameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__EPARAMETERS = EcorePackage.EOPERATION__EPARAMETERS;

	/**
	 * The feature id for the '<em><b>EExceptions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__EEXCEPTIONS = EcorePackage.EOPERATION__EEXCEPTIONS;

	/**
	 * The feature id for the '<em><b>Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY__METHOD = EcorePackage.EOPERATION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>Method Proxy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PROXY_FEATURE_COUNT = EcorePackage.EOPERATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__EANNOTATIONS = JavaRefPackage.JAVA_EVENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__NAME = JavaRefPackage.JAVA_EVENT__NAME;
	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__ORDERED = JavaRefPackage.JAVA_EVENT__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__UNIQUE = JavaRefPackage.JAVA_EVENT__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__LOWER_BOUND = JavaRefPackage.JAVA_EVENT__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__UPPER_BOUND = JavaRefPackage.JAVA_EVENT__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__MANY = JavaRefPackage.JAVA_EVENT__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__REQUIRED = JavaRefPackage.JAVA_EVENT__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__ETYPE = JavaRefPackage.JAVA_EVENT__ETYPE;

	/**
	 * The feature id for the '<em><b>Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__CHANGEABLE = JavaRefPackage.JAVA_EVENT__CHANGEABLE;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__VOLATILE = JavaRefPackage.JAVA_EVENT__VOLATILE;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__TRANSIENT = JavaRefPackage.JAVA_EVENT__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Default Value Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__DEFAULT_VALUE_LITERAL = JavaRefPackage.JAVA_EVENT__DEFAULT_VALUE_LITERAL;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__DEFAULT_VALUE = JavaRefPackage.JAVA_EVENT__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Unsettable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__UNSETTABLE = JavaRefPackage.JAVA_EVENT__UNSETTABLE;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__DERIVED = JavaRefPackage.JAVA_EVENT__DERIVED;

	/**
	 * The feature id for the '<em><b>EContaining Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT__ECONTAINING_CLASS = JavaRefPackage.JAVA_EVENT__ECONTAINING_CLASS;

	/**
	 * The number of structural features of the the '<em>Bean Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAN_EVENT_FEATURE_COUNT = JavaRefPackage.JAVA_EVENT_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator <em>Feature Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Decorator</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator
	 * @generated
	 */
	EClass getFeatureDecorator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator#getDisplayName()
	 * @see #getFeatureDecorator()
	 * @generated
	 */
	EAttribute getFeatureDecorator_DisplayName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getShortDescription <em>Short Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Short Description</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator#getShortDescription()
	 * @see #getFeatureDecorator()
	 * @generated
	 */
	EAttribute getFeatureDecorator_ShortDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator#getCategory()
	 * @see #getFeatureDecorator()
	 * @generated
	 */
	EAttribute getFeatureDecorator_Category();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isExpert <em>Expert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expert</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator#isExpert()
	 * @see #getFeatureDecorator()
	 * @generated
	 */
	EAttribute getFeatureDecorator_Expert();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator#isHidden()
	 * @see #getFeatureDecorator()
	 * @generated
	 */
	EAttribute getFeatureDecorator_Hidden();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isPreferred <em>Preferred</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator#isPreferred()
	 * @see #getFeatureDecorator()
	 * @generated
	 */
	EAttribute getFeatureDecorator_Preferred();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isMergeIntrospection <em>Merge Introspection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Merge Introspection</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator#isMergeIntrospection()
	 * @see #getFeatureDecorator()
	 * @generated
	 */
	EAttribute getFeatureDecorator_MergeIntrospection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator#getAttributes()
	 * @see #getFeatureDecorator()
	 * @generated
	 */
	EReference getFeatureDecorator_Attributes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator <em>Event Set Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Set Decorator</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.EventSetDecorator
	 * @generated
	 */
	EClass getEventSetDecorator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isInDefaultEventSet <em>In Default Event Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>In Default Event Set</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.EventSetDecorator#isInDefaultEventSet()
	 * @see #getEventSetDecorator()
	 * @generated
	 */
	EAttribute getEventSetDecorator_InDefaultEventSet();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isUnicast <em>Unicast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unicast</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.EventSetDecorator#isUnicast()
	 * @see #getEventSetDecorator()
	 * @generated
	 */
	EAttribute getEventSetDecorator_Unicast();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getAddListenerMethod <em>Add Listener Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Add Listener Method</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.EventSetDecorator#getAddListenerMethod()
	 * @see #getEventSetDecorator()
	 * @generated
	 */
	EReference getEventSetDecorator_AddListenerMethod();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getListenerMethods <em>Listener Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Listener Methods</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.EventSetDecorator#getListenerMethods()
	 * @see #getEventSetDecorator()
	 * @generated
	 */
	EReference getEventSetDecorator_ListenerMethods();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getListenerType <em>Listener Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Listener Type</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.EventSetDecorator#getListenerType()
	 * @see #getEventSetDecorator()
	 * @generated
	 */
	EReference getEventSetDecorator_ListenerType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getRemoveListenerMethod <em>Remove Listener Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Remove Listener Method</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.EventSetDecorator#getRemoveListenerMethod()
	 * @see #getEventSetDecorator()
	 * @generated
	 */
	EReference getEventSetDecorator_RemoveListenerMethod();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.beaninfo.MethodProxy <em>Method Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Proxy</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.MethodProxy
	 * @generated
	 */
	EClass getMethodProxy();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.MethodProxy#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Method</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.MethodProxy#getMethod()
	 * @see #getMethodProxy()
	 * @generated
	 */
	EReference getMethodProxy_Method();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator <em>Property Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Decorator</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator
	 * @generated
	 */
	EClass getPropertyDecorator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isBound <em>Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bound</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator#isBound()
	 * @see #getPropertyDecorator()
	 * @generated
	 */
	EAttribute getPropertyDecorator_Bound();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isConstrained <em>Constrained</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constrained</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator#isConstrained()
	 * @see #getPropertyDecorator()
	 * @generated
	 */
	EAttribute getPropertyDecorator_Constrained();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isDesignTime <em>Design Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Design Time</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator#isDesignTime()
	 * @see #getPropertyDecorator()
	 * @generated
	 */
	EAttribute getPropertyDecorator_DesignTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isAlwaysIncompatible <em>Always Incompatible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Always Incompatible</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator#isAlwaysIncompatible()
	 * @see #getPropertyDecorator()
	 * @generated
	 */
	EAttribute getPropertyDecorator_AlwaysIncompatible();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getFilterFlags <em>Filter Flags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Filter Flags</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator#getFilterFlags()
	 * @see #getPropertyDecorator()
	 * @generated
	 */
	EAttribute getPropertyDecorator_FilterFlags();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getPropertyEditorClass <em>Property Editor Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Property Editor Class</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator#getPropertyEditorClass()
	 * @see #getPropertyDecorator()
	 * @generated
	 */
	EReference getPropertyDecorator_PropertyEditorClass();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getReadMethod <em>Read Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Read Method</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator#getReadMethod()
	 * @see #getPropertyDecorator()
	 * @generated
	 */
	EReference getPropertyDecorator_ReadMethod();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getWriteMethod <em>Write Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Write Method</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator#getWriteMethod()
	 * @see #getPropertyDecorator()
	 * @generated
	 */
	EReference getPropertyDecorator_WriteMethod();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator <em>Indexed Property Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Indexed Property Decorator</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator
	 * @generated
	 */
	EClass getIndexedPropertyDecorator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedReadMethod <em>Indexed Read Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Indexed Read Method</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedReadMethod()
	 * @see #getIndexedPropertyDecorator()
	 * @generated
	 */
	EReference getIndexedPropertyDecorator_IndexedReadMethod();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedWriteMethod <em>Indexed Write Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Indexed Write Method</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedWriteMethod()
	 * @see #getIndexedPropertyDecorator()
	 * @generated
	 */
	EReference getIndexedPropertyDecorator_IndexedWriteMethod();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator <em>Bean Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bean Decorator</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.BeanDecorator
	 * @generated
	 */
	EClass getBeanDecorator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperProperties <em>Merge Super Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Merge Super Properties</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperProperties()
	 * @see #getBeanDecorator()
	 * @generated
	 */
	EAttribute getBeanDecorator_MergeSuperProperties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperBehaviors <em>Merge Super Behaviors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Merge Super Behaviors</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperBehaviors()
	 * @see #getBeanDecorator()
	 * @generated
	 */
	EAttribute getBeanDecorator_MergeSuperBehaviors();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperEvents <em>Merge Super Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Merge Super Events</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperEvents()
	 * @see #getBeanDecorator()
	 * @generated
	 */
	EAttribute getBeanDecorator_MergeSuperEvents();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectProperties <em>Introspect Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Introspect Properties</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectProperties()
	 * @see #getBeanDecorator()
	 * @generated
	 */
	EAttribute getBeanDecorator_IntrospectProperties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectBehaviors <em>Introspect Behaviors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Introspect Behaviors</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectBehaviors()
	 * @see #getBeanDecorator()
	 * @generated
	 */
	EAttribute getBeanDecorator_IntrospectBehaviors();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectEvents <em>Introspect Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Introspect Events</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectEvents()
	 * @see #getBeanDecorator()
	 * @generated
	 */
	EAttribute getBeanDecorator_IntrospectEvents();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#getCustomizerClass <em>Customizer Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Customizer Class</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.BeanDecorator#getCustomizerClass()
	 * @see #getBeanDecorator()
	 * @generated
	 */
	EReference getBeanDecorator_CustomizerClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.beaninfo.MethodDecorator <em>Method Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Decorator</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.MethodDecorator
	 * @generated
	 */
	EClass getMethodDecorator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.MethodDecorator#isParmsExplicit <em>Parms Explicit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parms Explicit</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.MethodDecorator#isParmsExplicit()
	 * @see #getMethodDecorator()
	 * @generated
	 */
	EAttribute getMethodDecorator_ParmsExplicit();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.beaninfo.MethodDecorator#getParameterDescriptors <em>Parameter Descriptors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter Descriptors</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.MethodDecorator#getParameterDescriptors()
	 * @see #getMethodDecorator()
	 * @generated
	 */
	EReference getMethodDecorator_ParameterDescriptors();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.beaninfo.ParameterDecorator <em>Parameter Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Decorator</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.ParameterDecorator
	 * @generated
	 */
	EClass getParameterDecorator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.ParameterDecorator#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.ParameterDecorator#getName()
	 * @see #getParameterDecorator()
	 * @generated
	 */
	EAttribute getParameterDecorator_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue <em>Feature Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Attribute Value</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureAttributeValue
	 * @generated
	 */
	EClass getFeatureAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getName()
	 * @see #getFeatureAttributeValue()
	 * @generated
	 */
	EAttribute getFeatureAttributeValue_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValue()
	 * @see #getFeatureAttributeValue()
	 * @generated
	 */
	EReference getFeatureAttributeValue_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BeaninfoFactory getBeaninfoFactory();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isDoBeaninfo <em>Do Beaninfo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Do Beaninfo</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.BeanDecorator#isDoBeaninfo()
	 * @see #getBeanDecorator()
	 * @generated
	 */
	EAttribute getBeanDecorator_DoBeaninfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValueProxy <em>Value Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value Proxy</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValueProxy()
	 * @see #getFeatureAttributeValue()
	 * @generated
	 */
	EAttribute getFeatureAttributeValue_ValueProxy();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.beaninfo.ParameterDecorator#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.ParameterDecorator#getParameter()
	 * @see #getParameterDecorator()
	 * @generated
	 */
	EReference getParameterDecorator_Parameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isAttributesExplicit <em>Attributes Explicit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attributes Explicit</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator#isAttributesExplicit()
	 * @see #getFeatureDecorator()
	 * @generated
	 */
	EAttribute getFeatureDecorator_AttributesExplicit();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.beaninfo.BeanEvent <em>Bean Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bean Event</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.BeanEvent
	 * @generated
	 */
	EClass getBeanEvent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isListenerMethodsExplicit <em>Listener Methods Explicit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Listener Methods Explicit</em>'.
	 * @see org.eclipse.jem.internal.beaninfo.EventSetDecorator#isListenerMethodsExplicit()
	 * @see #getEventSetDecorator()
	 * @generated
	 */
	EAttribute getEventSetDecorator_ListenerMethodsExplicit();

}
