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
package org.eclipse.jst.j2ee.internal.taglib.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.internal.taglib.impl.TaglibFactoryImpl;
import org.eclipse.jst.j2ee.taglib.ExtensibleType;
import org.eclipse.jst.j2ee.taglib.Function;
import org.eclipse.jst.j2ee.taglib.JSPTag;
import org.eclipse.jst.j2ee.taglib.JSPTagAttribute;
import org.eclipse.jst.j2ee.taglib.JSPVariable;
import org.eclipse.jst.j2ee.taglib.TagFile;
import org.eclipse.jst.j2ee.taglib.TagLib;
import org.eclipse.jst.j2ee.taglib.TaglibFactory;
import org.eclipse.jst.j2ee.taglib.TaglibPackage;
import org.eclipse.jst.j2ee.taglib.TldExtension;
import org.eclipse.jst.j2ee.taglib.Validator;

public class TaglibSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TaglibPackage modelPackage;

	protected static TaglibFactory factory;

	public TaglibSwitch() {
		factory = (TaglibFactory)TaglibFactoryImpl.getPackage().getEFactoryInstance();
		modelPackage = TaglibFactoryImpl.getPackage();
	}
	public Object doSwitch(EObject theEObject) {
		     EClass theEClass = theEObject.eClass();
      if (theEClass.eContainer() != modelPackage) return defaultCase(theEObject);
     
		switch (theEClass.getClassifierID()) {
			case TaglibPackage.TAG_LIB:
			    {
				TagLib castedObject = (TagLib)theEObject;
				Object result = caseTagLib(castedObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			    }
			case TaglibPackage.VALIDATOR:
			    {
				Validator castedObject = (Validator)theEObject;
				Object result = caseValidator(castedObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			    }
			case TaglibPackage.JSP_TAG:
			    {
				JSPTag castedObject = (JSPTag)theEObject;
				Object result = caseJSPTag(castedObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			    }
			case TaglibPackage.JSP_TAG_ATTRIBUTE:
			    {
				JSPTagAttribute castedObject = (JSPTagAttribute)theEObject;
				Object result = caseJSPTagAttribute(castedObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			    }
			case TaglibPackage.JSP_VARIABLE:
			    {
				JSPVariable castedObject = (JSPVariable)theEObject;
				Object result = caseJSPVariable(castedObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			    }
			default: return defaultCase(theEObject);
		}
	} // doSwitch

	public Object caseTagLib(TagLib object) {
		return null;
	}

	public Object caseValidator(Validator object) {
		return null;
	}

	public Object caseJSPTag(JSPTag object) {
		return null;
	}

	public Object caseJSPTagAttribute(JSPTagAttribute object) {
		return null;
	}

	public Object caseJSPVariable(JSPVariable object) {
		return null;
	}

	public Object defaultCase(EObject object) {
		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object doSwitchGen(EObject theEObject) {
		EClass theEClass = theEObject.eClass();
		if (theEClass.eContainer() == modelPackage) {
			switch (theEClass.getClassifierID()) {
				case TaglibPackage.TAG_LIB: {
					TagLib tagLib = (TagLib)theEObject;
					Object result = caseTagLib(tagLib);
					if (result == null) result = caseCompatibilityDescriptionGroup(tagLib);
					if (result == null) result = caseDescriptionGroup(tagLib);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case TaglibPackage.JSP_TAG: {
					JSPTag jspTag = (JSPTag)theEObject;
					Object result = caseJSPTag(jspTag);
					if (result == null) result = caseDescriptionGroup(jspTag);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case TaglibPackage.JSP_TAG_ATTRIBUTE: {
					JSPTagAttribute jspTagAttribute = (JSPTagAttribute)theEObject;
					Object result = caseJSPTagAttribute(jspTagAttribute);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case TaglibPackage.VALIDATOR: {
					Validator validator = (Validator)theEObject;
					Object result = caseValidator(validator);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case TaglibPackage.JSP_VARIABLE: {
					JSPVariable jspVariable = (JSPVariable)theEObject;
					Object result = caseJSPVariable(jspVariable);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case TaglibPackage.FUNCTION: {
					Function function = (Function)theEObject;
					Object result = caseFunction(function);
					if (result == null) result = caseDescriptionGroup(function);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case TaglibPackage.TAG_FILE: {
					TagFile tagFile = (TagFile)theEObject;
					Object result = caseTagFile(tagFile);
					if (result == null) result = caseDescriptionGroup(tagFile);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case TaglibPackage.TLD_EXTENSION: {
					TldExtension tldExtension = (TldExtension)theEObject;
					Object result = caseTldExtension(tldExtension);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				default: return defaultCase(theEObject);
			}
		}
		return defaultCase(theEObject);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseTagLibGen(TagLib object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseValidatorGen(Validator object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseJSPTagGen(JSPTag object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseJSPTagAttributeGen(JSPTagAttribute object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseJSPVariableGen(JSPVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFunction(Function object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Tag File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Tag File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTagFile(TagFile object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Tld Extension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Tld Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTldExtension(TldExtension object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Extensible Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Extensible Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExtensibleType(ExtensibleType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Description Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDescriptionGroup(DescriptionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Compatibility Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Compatibility Description Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCompatibilityDescriptionGroup(CompatibilityDescriptionGroup object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object defaultCaseGen(EObject object) {
		return null;
	}

} //TaglibSwitch








