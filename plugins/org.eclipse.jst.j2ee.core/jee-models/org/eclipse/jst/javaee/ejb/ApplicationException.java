/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application Exception</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The application-exceptionType declares an application
 *         exception. The declaration consists of:
 *         
 *         - the exception class. When the container receives
 *         an exception of this type, it is required to 
 *         forward this exception as an applcation exception 
 *         to the client regardless of whether it is a checked
 *         or unchecked exception.
 *         - an optional rollback element. If this element is 
 *         set to true, the container must rollback the current 
 *         transaction before forwarding the exception to the
 *         client.  If not specified, it defaults to false.
 *         - an optional inherited element. If this element is 
 *         set to true, subclasses of the exception class type
 *         are also automatically considered application 
 *         exceptions (unless overriden at a lower level).
 *         If set to false, only the exception class type is
 *         considered an application-exception, not its
 *         exception subclasses. If not specified, this
 *         value defaults to true.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ApplicationException#getExceptionClass <em>Exception Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ApplicationException#isRollback <em>Rollback</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ApplicationException#isInherited <em>Inherited</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ApplicationException#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getApplicationException()
 * @extends JavaEEObject
 * @generated
 */
public interface ApplicationException extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Exception Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Class</em>' attribute.
	 * @see #setExceptionClass(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getApplicationException_ExceptionClass()
	 * @generated
	 */
	String getExceptionClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ApplicationException#getExceptionClass <em>Exception Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception Class</em>' attribute.
	 * @see #getExceptionClass()
	 * @generated
	 */
	void setExceptionClass(String value);

	/**
	 * Returns the value of the '<em><b>Rollback</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rollback</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rollback</em>' attribute.
	 * @see #isSetRollback()
	 * @see #unsetRollback()
	 * @see #setRollback(boolean)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getApplicationException_Rollback()
	 * @generated
	 */
	boolean isRollback();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ApplicationException#isRollback <em>Rollback</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rollback</em>' attribute.
	 * @see #isSetRollback()
	 * @see #unsetRollback()
	 * @see #isRollback()
	 * @generated
	 */
	void setRollback(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.ApplicationException#isRollback <em>Rollback</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRollback()
	 * @see #isRollback()
	 * @see #setRollback(boolean)
	 * @generated
	 */
	void unsetRollback();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.ApplicationException#isRollback <em>Rollback</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Rollback</em>' attribute is set.
	 * @see #unsetRollback()
	 * @see #isRollback()
	 * @see #setRollback(boolean)
	 * @generated
	 */
	boolean isSetRollback();

	/**
	 * Returns the value of the '<em><b>Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, EJB 3.1
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inherited</em>' attribute.
	 * @see #isSetInherited()
	 * @see #unsetInherited()
	 * @see #setInherited(boolean)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getApplicationException_Inherited()
	 * @generated
	 */
	boolean isInherited();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ApplicationException#isInherited <em>Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inherited</em>' attribute.
	 * @see #isSetInherited()
	 * @see #unsetInherited()
	 * @see #isInherited()
	 * @generated
	 */
	void setInherited(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.ApplicationException#isInherited <em>Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetInherited()
	 * @see #isInherited()
	 * @see #setInherited(boolean)
	 * @generated
	 */
	void unsetInherited();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.ApplicationException#isInherited <em>Inherited</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Inherited</em>' attribute is set.
	 * @see #unsetInherited()
	 * @see #isInherited()
	 * @see #setInherited(boolean)
	 * @generated
	 */
	boolean isSetInherited();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getApplicationException_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ApplicationException#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ApplicationException