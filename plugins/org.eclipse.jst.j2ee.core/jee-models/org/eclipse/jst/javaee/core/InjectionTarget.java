/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Injection Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         An injection target specifies a class and a name within
 *         that class into which a resource should be injected.
 *         
 *         The injection target class specifies the fully qualified
 *         class name that is the target of the injection.  The
 *         Java EE specifications describe which classes can be an
 *         injection target.
 *         
 *         The injection target name specifies the target within
 *         the specified class.  The target is first looked for as a
 *         JavaBeans property name.  If not found, the target is
 *         looked for as a field name.
 *         
 *         The specified resource will be injected into the target
 *         during initialization of the class by either calling the
 *         set method for the target property or by setting a value
 *         into the named field.
 *         
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.InjectionTarget#getInjectionTargetClass <em>Injection Target Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.InjectionTarget#getInjectionTargetName <em>Injection Target Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getInjectionTarget()
 * @extends JavaEEObject
 * @generated
 */
public interface InjectionTarget extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Injection Target Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Injection Target Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Injection Target Class</em>' attribute.
	 * @see #setInjectionTargetClass(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getInjectionTarget_InjectionTargetClass()
	 * @generated
	 */
	String getInjectionTargetClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.InjectionTarget#getInjectionTargetClass <em>Injection Target Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Injection Target Class</em>' attribute.
	 * @see #getInjectionTargetClass()
	 * @generated
	 */
	void setInjectionTargetClass(String value);

	/**
	 * Returns the value of the '<em><b>Injection Target Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Injection Target Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Injection Target Name</em>' attribute.
	 * @see #setInjectionTargetName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getInjectionTarget_InjectionTargetName()
	 * @generated
	 */
	String getInjectionTargetName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.InjectionTarget#getInjectionTargetName <em>Injection Target Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Injection Target Name</em>' attribute.
	 * @see #getInjectionTargetName()
	 * @generated
	 */
	void setInjectionTargetName(String value);

} // InjectionTarget