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
package org.eclipse.jem.java;
/*
 *  $RCSfile: JavaRefPackage.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:17 $ 
 */
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;


/**
 * @lastgen interface JavaRefPackage extends EPackage {}
 */
public interface JavaRefPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "java";

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS = 0;
	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EANNOTATIONS = EcorePackage.ECLASS__EANNOTATIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__NAME = EcorePackage.ECLASS__NAME;
	/**
	 * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__INSTANCE_CLASS_NAME = EcorePackage.ECLASS__INSTANCE_CLASS_NAME;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__INSTANCE_CLASS = EcorePackage.ECLASS__INSTANCE_CLASS;
	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__DEFAULT_VALUE = EcorePackage.ECLASS__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>EPackage</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EPACKAGE = EcorePackage.ECLASS__EPACKAGE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__ABSTRACT = EcorePackage.ECLASS__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__INTERFACE = EcorePackage.ECLASS__INTERFACE;

	/**
	 * The feature id for the '<em><b>ESuper Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__ESUPER_TYPES = EcorePackage.ECLASS__ESUPER_TYPES;

	/**
	 * The feature id for the '<em><b>EOperations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EOPERATIONS = EcorePackage.ECLASS__EOPERATIONS;

	/**
	 * The feature id for the '<em><b>EAll Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EALL_ATTRIBUTES = EcorePackage.ECLASS__EALL_ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>EAll References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EALL_REFERENCES = EcorePackage.ECLASS__EALL_REFERENCES;

	/**
	 * The feature id for the '<em><b>EReferences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EREFERENCES = EcorePackage.ECLASS__EREFERENCES;

	/**
	 * The feature id for the '<em><b>EAttributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EATTRIBUTES = EcorePackage.ECLASS__EATTRIBUTES;

	/**
	 * The feature id for the '<em><b>EAll Containments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EALL_CONTAINMENTS = EcorePackage.ECLASS__EALL_CONTAINMENTS;

	/**
	 * The feature id for the '<em><b>EAll Operations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EALL_OPERATIONS = EcorePackage.ECLASS__EALL_OPERATIONS;

	/**
	 * The feature id for the '<em><b>EAll Structural Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EALL_STRUCTURAL_FEATURES = EcorePackage.ECLASS__EALL_STRUCTURAL_FEATURES;

	/**
	 * The feature id for the '<em><b>EAll Super Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EALL_SUPER_TYPES = EcorePackage.ECLASS__EALL_SUPER_TYPES;

	/**
	 * The feature id for the '<em><b>EID Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int JAVA_CLASS__EID_ATTRIBUTE = EcorePackage.ECLASS__EID_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>EStructural Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__ESTRUCTURAL_FEATURES = EcorePackage.ECLASS__ESTRUCTURAL_FEATURES;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__KIND = EcorePackage.ECLASS_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__PUBLIC = EcorePackage.ECLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__FINAL = EcorePackage.ECLASS_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__IMPLEMENTS_INTERFACES = EcorePackage.ECLASS_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__CLASS_IMPORT = EcorePackage.ECLASS_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__PACKAGE_IMPORTS = EcorePackage.ECLASS_FEATURE_COUNT + 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__FIELDS = EcorePackage.ECLASS_FEATURE_COUNT + 6;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__METHODS = EcorePackage.ECLASS_FEATURE_COUNT + 7;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__INITIALIZERS = EcorePackage.ECLASS_FEATURE_COUNT + 8;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__DECLARED_CLASSES = EcorePackage.ECLASS_FEATURE_COUNT + 9;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__DECLARING_CLASS = EcorePackage.ECLASS_FEATURE_COUNT + 10;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__JAVA_PACKAGE = EcorePackage.ECLASS_FEATURE_COUNT + 11;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__EVENTS = EcorePackage.ECLASS_FEATURE_COUNT + 12;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_CLASS__ALL_EVENTS = EcorePackage.ECLASS_FEATURE_COUNT + 13;
	/**
	 * The number of structural features of the the '<em>Java Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS_FEATURE_COUNT = EcorePackage.ECLASS_FEATURE_COUNT + 14;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FIELD = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_PARAMETER = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE = 10;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_DATA_TYPE = 9;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_EVENT = 11;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_PACKAGE = 8;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int BLOCK = 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int COMMENT = 6;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int STATEMENT = 7;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int INITIALIZER = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int INITIALIZER__IS_STATIC = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int INITIALIZER__JAVA_CLASS = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int INITIALIZER__SOURCE = 2;

	/**
	 * The number of structural features of the the '<em>Initializer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZER_FEATURE_COUNT = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER__EANNOTATIONS = EcorePackage.EPARAMETER__EANNOTATIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_PARAMETER__NAME = EcorePackage.EPARAMETER__NAME;
	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER__ORDERED = EcorePackage.EPARAMETER__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER__UNIQUE = EcorePackage.EPARAMETER__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER__LOWER_BOUND = EcorePackage.EPARAMETER__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER__UPPER_BOUND = EcorePackage.EPARAMETER__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER__MANY = EcorePackage.EPARAMETER__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER__REQUIRED = EcorePackage.EPARAMETER__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER__ETYPE = EcorePackage.EPARAMETER__ETYPE;

	/**
	 * The feature id for the '<em><b>EOperation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER__EOPERATION = EcorePackage.EPARAMETER__EOPERATION;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER__FINAL = EcorePackage.EPARAMETER_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_PARAMETER__PARAMETER_KIND = EcorePackage.EPARAMETER_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the the '<em>Java Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PARAMETER_FEATURE_COUNT = EcorePackage.EPARAMETER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__EANNOTATIONS = EcorePackage.EOPERATION__EANNOTATIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD__NAME = EcorePackage.EOPERATION__NAME;
	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__ORDERED = EcorePackage.EOPERATION__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__UNIQUE = EcorePackage.EOPERATION__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__LOWER_BOUND = EcorePackage.EOPERATION__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__UPPER_BOUND = EcorePackage.EOPERATION__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__MANY = EcorePackage.EOPERATION__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__REQUIRED = EcorePackage.EOPERATION__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__ETYPE = EcorePackage.EOPERATION__ETYPE;

	/**
	 * The feature id for the '<em><b>EContaining Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__ECONTAINING_CLASS = EcorePackage.EOPERATION__ECONTAINING_CLASS;

	/**
	 * The feature id for the '<em><b>EParameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__EPARAMETERS = EcorePackage.EOPERATION__EPARAMETERS;

	/**
	 * The feature id for the '<em><b>EExceptions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__EEXCEPTIONS = EcorePackage.EOPERATION__EEXCEPTIONS;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__ABSTRACT = EcorePackage.EOPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__NATIVE = EcorePackage.EOPERATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Synchronized</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__SYNCHRONIZED = EcorePackage.EOPERATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__FINAL = EcorePackage.EOPERATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__CONSTRUCTOR = EcorePackage.EOPERATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__STATIC = EcorePackage.EOPERATION_FEATURE_COUNT + 5;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD__JAVA_VISIBILITY = EcorePackage.EOPERATION_FEATURE_COUNT + 6;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD__PARAMETERS = EcorePackage.EOPERATION_FEATURE_COUNT + 7;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD__JAVA_EXCEPTIONS = EcorePackage.EOPERATION_FEATURE_COUNT + 8;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD__JAVA_CLASS = EcorePackage.EOPERATION_FEATURE_COUNT + 9;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD__SOURCE = EcorePackage.EOPERATION_FEATURE_COUNT + 10;
	/**
	 * The number of structural features of the the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_FEATURE_COUNT = EcorePackage.EOPERATION_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__EANNOTATIONS = EcorePackage.ETYPED_ELEMENT__EANNOTATIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FIELD__NAME = EcorePackage.ETYPED_ELEMENT__NAME;
	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__ORDERED = EcorePackage.ETYPED_ELEMENT__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__UNIQUE = EcorePackage.ETYPED_ELEMENT__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__LOWER_BOUND = EcorePackage.ETYPED_ELEMENT__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__UPPER_BOUND = EcorePackage.ETYPED_ELEMENT__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__MANY = EcorePackage.ETYPED_ELEMENT__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__REQUIRED = EcorePackage.ETYPED_ELEMENT__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__ETYPE = EcorePackage.ETYPED_ELEMENT__ETYPE;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__FINAL = EcorePackage.ETYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__STATIC = EcorePackage.ETYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FIELD__JAVA_VISIBILITY = EcorePackage.ETYPED_ELEMENT_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__TRANSIENT = EcorePackage.ETYPED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__VOLATILE = EcorePackage.ETYPED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FIELD__JAVA_CLASS = EcorePackage.ETYPED_ELEMENT_FEATURE_COUNT + 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FIELD__INITIALIZER = EcorePackage.ETYPED_ELEMENT_FEATURE_COUNT + 6;
	/**
	 * The number of structural features of the the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_FEATURE_COUNT = EcorePackage.ETYPED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int BLOCK__SOURCE = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int BLOCK__NAME = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int BLOCK__CONTENTS = 2;

	/**
	 * The number of structural features of the the '<em>Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FEATURE_COUNT = 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int COMMENT__SOURCE = BLOCK__SOURCE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int COMMENT__NAME = BLOCK__NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int COMMENT__CONTENTS = BLOCK__CONTENTS;

	/**
	 * The number of structural features of the the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = BLOCK_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int STATEMENT__SOURCE = BLOCK__SOURCE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int STATEMENT__NAME = BLOCK__NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int STATEMENT__CONTENTS = BLOCK__CONTENTS;

	/**
	 * The number of structural features of the the '<em>Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_FEATURE_COUNT = BLOCK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PACKAGE__EANNOTATIONS = EcorePackage.EPACKAGE__EANNOTATIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_PACKAGE__NAME = EcorePackage.EPACKAGE__NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_PACKAGE__NS_URI = EcorePackage.EPACKAGE__NS_URI;
	/**
	 * The feature id for the '<em><b>Ns Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PACKAGE__NS_PREFIX = EcorePackage.EPACKAGE__NS_PREFIX;

	/**
	 * The feature id for the '<em><b>EFactory Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PACKAGE__EFACTORY_INSTANCE = EcorePackage.EPACKAGE__EFACTORY_INSTANCE;

	/**
	 * The feature id for the '<em><b>EClassifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PACKAGE__ECLASSIFIERS = EcorePackage.EPACKAGE__ECLASSIFIERS;

	/**
	 * The feature id for the '<em><b>ESubpackages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PACKAGE__ESUBPACKAGES = EcorePackage.EPACKAGE__ESUBPACKAGES;

	/**
	 * The feature id for the '<em><b>ESuper Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PACKAGE__ESUPER_PACKAGE = EcorePackage.EPACKAGE__ESUPER_PACKAGE;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_PACKAGE__JAVA_CLASSES = EcorePackage.EPACKAGE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>Java Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_PACKAGE_FEATURE_COUNT = EcorePackage.EPACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EANNOTATIONS = EcorePackage.ECLASS__EANNOTATIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_DATA_TYPE__NAME = EcorePackage.ECLASS__NAME;
	/**
	 * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__INSTANCE_CLASS_NAME = EcorePackage.ECLASS__INSTANCE_CLASS_NAME;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_DATA_TYPE__INSTANCE_CLASS = EcorePackage.ECLASS__INSTANCE_CLASS;
	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__DEFAULT_VALUE = EcorePackage.ECLASS__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>EPackage</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EPACKAGE = EcorePackage.ECLASS__EPACKAGE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__ABSTRACT = EcorePackage.ECLASS__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__INTERFACE = EcorePackage.ECLASS__INTERFACE;

	/**
	 * The feature id for the '<em><b>ESuper Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__ESUPER_TYPES = EcorePackage.ECLASS__ESUPER_TYPES;

	/**
	 * The feature id for the '<em><b>EOperations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EOPERATIONS = EcorePackage.ECLASS__EOPERATIONS;

	/**
	 * The feature id for the '<em><b>EAll Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EALL_ATTRIBUTES = EcorePackage.ECLASS__EALL_ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>EAll References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EALL_REFERENCES = EcorePackage.ECLASS__EALL_REFERENCES;

	/**
	 * The feature id for the '<em><b>EReferences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EREFERENCES = EcorePackage.ECLASS__EREFERENCES;

	/**
	 * The feature id for the '<em><b>EAttributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EATTRIBUTES = EcorePackage.ECLASS__EATTRIBUTES;

	/**
	 * The feature id for the '<em><b>EAll Containments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EALL_CONTAINMENTS = EcorePackage.ECLASS__EALL_CONTAINMENTS;

	/**
	 * The feature id for the '<em><b>EAll Operations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EALL_OPERATIONS = EcorePackage.ECLASS__EALL_OPERATIONS;

	/**
	 * The feature id for the '<em><b>EAll Structural Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EALL_STRUCTURAL_FEATURES = EcorePackage.ECLASS__EALL_STRUCTURAL_FEATURES;

	/**
	 * The feature id for the '<em><b>EAll Super Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EALL_SUPER_TYPES = EcorePackage.ECLASS__EALL_SUPER_TYPES;

	/**
	 * The feature id for the '<em><b>EID Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__EID_ATTRIBUTE = EcorePackage.ECLASS__EID_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>EStructural Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE__ESTRUCTURAL_FEATURES = EcorePackage.ECLASS__ESTRUCTURAL_FEATURES;

	/**
	 * The number of structural features of the the '<em>Java Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_DATA_TYPE_FEATURE_COUNT = EcorePackage.ECLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EANNOTATIONS = JAVA_CLASS__EANNOTATIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__NAME = JAVA_CLASS__NAME;
	/**
	 * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__INSTANCE_CLASS_NAME = JAVA_CLASS__INSTANCE_CLASS_NAME;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__INSTANCE_CLASS = JAVA_CLASS__INSTANCE_CLASS;
	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__DEFAULT_VALUE = JAVA_CLASS__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>EPackage</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EPACKAGE = JAVA_CLASS__EPACKAGE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__ABSTRACT = JAVA_CLASS__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__INTERFACE = JAVA_CLASS__INTERFACE;

	/**
	 * The feature id for the '<em><b>ESuper Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__ESUPER_TYPES = JAVA_CLASS__ESUPER_TYPES;

	/**
	 * The feature id for the '<em><b>EOperations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EOPERATIONS = JAVA_CLASS__EOPERATIONS;

	/**
	 * The feature id for the '<em><b>EAll Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EALL_ATTRIBUTES = JAVA_CLASS__EALL_ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>EAll References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EALL_REFERENCES = JAVA_CLASS__EALL_REFERENCES;

	/**
	 * The feature id for the '<em><b>EReferences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EREFERENCES = JAVA_CLASS__EREFERENCES;

	/**
	 * The feature id for the '<em><b>EAttributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EATTRIBUTES = JAVA_CLASS__EATTRIBUTES;

	/**
	 * The feature id for the '<em><b>EAll Containments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EALL_CONTAINMENTS = JAVA_CLASS__EALL_CONTAINMENTS;

	/**
	 * The feature id for the '<em><b>EAll Operations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EALL_OPERATIONS = JAVA_CLASS__EALL_OPERATIONS;

	/**
	 * The feature id for the '<em><b>EAll Structural Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EALL_STRUCTURAL_FEATURES = JAVA_CLASS__EALL_STRUCTURAL_FEATURES;

	/**
	 * The feature id for the '<em><b>EAll Super Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__EALL_SUPER_TYPES = JAVA_CLASS__EALL_SUPER_TYPES;

	/**
	 * The feature id for the '<em><b>EID Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int ARRAY_TYPE__EID_ATTRIBUTE = JAVA_CLASS__EID_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>EStructural Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__ESTRUCTURAL_FEATURES = JAVA_CLASS__ESTRUCTURAL_FEATURES;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__KIND = JAVA_CLASS__KIND;
	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__PUBLIC = JAVA_CLASS__PUBLIC;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__FINAL = JAVA_CLASS__FINAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__IMPLEMENTS_INTERFACES = JAVA_CLASS__IMPLEMENTS_INTERFACES;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__CLASS_IMPORT = JAVA_CLASS__CLASS_IMPORT;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__PACKAGE_IMPORTS = JAVA_CLASS__PACKAGE_IMPORTS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__FIELDS = JAVA_CLASS__FIELDS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__METHODS = JAVA_CLASS__METHODS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__INITIALIZERS = JAVA_CLASS__INITIALIZERS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__DECLARED_CLASSES = JAVA_CLASS__DECLARED_CLASSES;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__DECLARING_CLASS = JAVA_CLASS__DECLARING_CLASS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__JAVA_PACKAGE = JAVA_CLASS__JAVA_PACKAGE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__EVENTS = JAVA_CLASS__EVENTS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__ALL_EVENTS = JAVA_CLASS__ALL_EVENTS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__ARRAY_DIMENSIONS = JAVA_CLASS_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARRAY_TYPE__COMPONENT_TYPE = JAVA_CLASS_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the the '<em>Array Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_FEATURE_COUNT = JAVA_CLASS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__EANNOTATIONS = EcorePackage.ESTRUCTURAL_FEATURE__EANNOTATIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_EVENT__NAME = EcorePackage.ESTRUCTURAL_FEATURE__NAME;
	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__ORDERED = EcorePackage.ESTRUCTURAL_FEATURE__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__UNIQUE = EcorePackage.ESTRUCTURAL_FEATURE__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__LOWER_BOUND = EcorePackage.ESTRUCTURAL_FEATURE__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__UPPER_BOUND = EcorePackage.ESTRUCTURAL_FEATURE__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__MANY = EcorePackage.ESTRUCTURAL_FEATURE__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__REQUIRED = EcorePackage.ESTRUCTURAL_FEATURE__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__ETYPE = EcorePackage.ESTRUCTURAL_FEATURE__ETYPE;

	/**
	 * The feature id for the '<em><b>Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__CHANGEABLE = EcorePackage.ESTRUCTURAL_FEATURE__CHANGEABLE;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__VOLATILE = EcorePackage.ESTRUCTURAL_FEATURE__VOLATILE;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__TRANSIENT = EcorePackage.ESTRUCTURAL_FEATURE__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Default Value Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__DEFAULT_VALUE_LITERAL = EcorePackage.ESTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__DEFAULT_VALUE = EcorePackage.ESTRUCTURAL_FEATURE__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Unsettable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__UNSETTABLE = EcorePackage.ESTRUCTURAL_FEATURE__UNSETTABLE;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__DERIVED = EcorePackage.ESTRUCTURAL_FEATURE__DERIVED;

	/**
	 * The feature id for the '<em><b>EContaining Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT__ECONTAINING_CLASS = EcorePackage.ESTRUCTURAL_FEATURE__ECONTAINING_CLASS;

	/**
	 * The number of structural features of the the '<em>Java Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_EVENT_FEATURE_COUNT = EcorePackage.ESTRUCTURAL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_VISIBILITY_KIND = 13;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JAVA_PARAMETER_KIND = 14;
	/**
	 * The meta object id for the '<em>JType Java Helpers</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.java.JavaHelpers
	 * @see org.eclipse.jem.java.impl.JavaRefPackageImpl#getJTypeJavaHelpers()
	 * @generated
	 */
	int JTYPE_JAVA_HELPERS = 15;

	/**
	 * The meta object id for the '<em>JType List</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see org.eclipse.jem.java.impl.JavaRefPackageImpl#getJTypeList()
	 * @generated
	 */
	int JTYPE_LIST = 16;


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TYPE_KIND = 12;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	String eNS_URI = "java.xmi";
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jem.internal.java";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JavaRefPackage eINSTANCE = org.eclipse.jem.java.impl.JavaRefPackageImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaClass object
	 */
	EClass getJavaClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getJavaClass_Kind();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.JavaClass#isPublic <em>Public</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Public</em>'.
	 * @see org.eclipse.jem.java.JavaClass#isPublic()
	 * @see #getJavaClass()
	 * @generated
	 */
	EAttribute getJavaClass_Public();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.JavaClass#isFinal <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final</em>'.
	 * @see org.eclipse.jem.java.JavaClass#isFinal()
	 * @see #getJavaClass()
	 * @generated
	 */
	EAttribute getJavaClass_Final();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_ImplementsInterfaces();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_ClassImport();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_PackageImports();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_Fields();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_Methods();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_Initializers();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_DeclaringClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_DeclaredClasses();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_JavaPackage();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_Events();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaClass_AllEvents();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Field object
	 */
	EClass getField();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.Field#isFinal <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final</em>'.
	 * @see org.eclipse.jem.java.Field#isFinal()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Final();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.Field#isStatic <em>Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Static</em>'.
	 * @see org.eclipse.jem.java.Field#isStatic()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Static();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getField_JavaVisibility();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.Field#isTransient <em>Transient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transient</em>'.
	 * @see org.eclipse.jem.java.Field#isTransient()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Transient();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.Field#isVolatile <em>Volatile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Volatile</em>'.
	 * @see org.eclipse.jem.java.Field#isVolatile()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Volatile();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getField_JavaClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getField_Initializer();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Method object
	 */
	EClass getMethod();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.Method#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see org.eclipse.jem.java.Method#isAbstract()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.Method#isNative <em>Native</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Native</em>'.
	 * @see org.eclipse.jem.java.Method#isNative()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Native();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.Method#isSynchronized <em>Synchronized</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synchronized</em>'.
	 * @see org.eclipse.jem.java.Method#isSynchronized()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Synchronized();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.Method#isFinal <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final</em>'.
	 * @see org.eclipse.jem.java.Method#isFinal()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Final();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.Method#isConstructor <em>Constructor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constructor</em>'.
	 * @see org.eclipse.jem.java.Method#isConstructor()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Constructor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.Method#isStatic <em>Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Static</em>'.
	 * @see org.eclipse.jem.java.Method#isStatic()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Static();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMethod_JavaVisibility();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMethod_Parameters();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMethod_JavaExceptions();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMethod_JavaClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMethod_Source();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaParameter object
	 */
	EClass getJavaParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.java.JavaParameter#isFinal <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final</em>'.
	 * @see org.eclipse.jem.java.JavaParameter#isFinal()
	 * @see #getJavaParameter()
	 * @generated
	 */
	EAttribute getJavaParameter_Final();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getJavaParameter_ParameterKind();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ArrayType object
	 */
	EClass getArrayType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getArrayType_ArrayDimensions();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getArrayType_ComponentType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaDataType object
	 */
	EClass getJavaDataType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaEvent object
	 */
	EClass getJavaEvent();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaPackage object
	 */
	EClass getJavaPackage();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJavaPackage_JavaClasses();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Block object
	 */
	EClass getBlock();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getBlock_Source();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getBlock_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getBlock_Contents();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Comment object
	 */
	EClass getComment();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Statement object
	 */
	EClass getStatement();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Initializer object
	 */
	EClass getInitializer();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getInitializer_IsStatic();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getInitializer_JavaClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getInitializer_Source();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaVisibilityKind object
	 */
	EEnum getJavaVisibilityKind();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaParameterKind object
	 */
	EEnum getJavaParameterKind();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return TypeKind object
	 */
	EEnum getTypeKind();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JTypeList object
	 */
	EDataType getJTypeList();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JTypeJavaHelpers object
	 */
	EDataType getJTypeJavaHelpers();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	JavaRefFactory getJavaRefFactory();

} //JavaRefPackage






