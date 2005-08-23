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
package org.eclipse.jst.j2ee.taglib.internal;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;
/**
 * The validator element provides information on an optional validator that can be used to validate the conformance of a JSP page to using this tag library.

 */
public interface Validator extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The ValidatorClass reference
	 * defines the TagLibraryValidator class that can be
	 * used to validate the conformance of a JSP page to using this tag library.
	 */
	JavaClass getValidatorClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the ValidatorClass reference
	 */
	void setValidatorClass(JavaClass value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of InitParams references
	 */
	EList getInitParams();

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TaglibPackage#getValidator_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}














