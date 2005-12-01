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
package org.eclipse.jst.j2ee.taglib.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.jst.j2ee.taglib.internal.BodyContentType;
import org.eclipse.jst.j2ee.taglib.internal.Function;
import org.eclipse.jst.j2ee.taglib.internal.JSPScriptingVariableScope;
import org.eclipse.jst.j2ee.taglib.internal.JSPTag;
import org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute;
import org.eclipse.jst.j2ee.taglib.internal.JSPVariable;
import org.eclipse.jst.j2ee.taglib.internal.TagFile;
import org.eclipse.jst.j2ee.taglib.internal.TagLib;
import org.eclipse.jst.j2ee.taglib.internal.TaglibFactory;
import org.eclipse.jst.j2ee.taglib.internal.TaglibPackage;
import org.eclipse.jst.j2ee.taglib.internal.TldExtension;
import org.eclipse.jst.j2ee.taglib.internal.Validator;

/**
 * @generated
 */
public class TaglibFactoryImpl extends EFactoryImpl implements TaglibFactory{
 
	public TaglibFactoryImpl() {
		super(); 		
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TaglibPackage.TAG_LIB: return createTagLib();
			case TaglibPackage.JSP_TAG: return createJSPTag();
			case TaglibPackage.JSP_TAG_ATTRIBUTE: return createJSPTagAttribute();
			case TaglibPackage.VALIDATOR: return createValidator();
			case TaglibPackage.JSP_VARIABLE: return createJSPVariable();
			case TaglibPackage.FUNCTION: return createFunction();
			case TaglibPackage.TAG_FILE: return createTagFile();
			case TaglibPackage.TLD_EXTENSION: return createTldExtension();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TaglibPackage.BODY_CONTENT_TYPE: {
				BodyContentType result = BodyContentType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case TaglibPackage.JSP_SCRIPTING_VARIABLE_SCOPE: {
				JSPScriptingVariableScope result = JSPScriptingVariableScope.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TaglibPackage.BODY_CONTENT_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case TaglibPackage.JSP_SCRIPTING_VARIABLE_SCOPE:
				return instanceValue == null ? null : instanceValue.toString();
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

public static TaglibFactory getActiveFactory() {
	return (TaglibFactory) getPackage().getEFactoryInstance();
}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public TagLib createTagLib() {
		TagLibImpl tagLib = new TagLibImpl();
		return tagLib;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Validator createValidator() {
		ValidatorImpl validator = new ValidatorImpl();
		return validator;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JSPTag createJSPTag() {
		JSPTagImpl jspTag = new JSPTagImpl();
		return jspTag;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JSPTagAttribute createJSPTagAttribute() {
		JSPTagAttributeImpl jspTagAttribute = new JSPTagAttributeImpl();
		return jspTagAttribute;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JSPVariable createJSPVariable() {
		JSPVariableImpl jspVariable = new JSPVariableImpl();
		return jspVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function createFunction() {
		FunctionImpl function = new FunctionImpl();
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagFile createTagFile() {
		TagFileImpl tagFile = new TagFileImpl();
		return tagFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TldExtension createTldExtension() {
		TldExtensionImpl tldExtension = new TldExtensionImpl();
		return tldExtension;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public TaglibPackage getTaglibPackage() {
		return (TaglibPackage)getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static TaglibPackage getPackage() {
		return TaglibPackage.eINSTANCE;
	}

}







