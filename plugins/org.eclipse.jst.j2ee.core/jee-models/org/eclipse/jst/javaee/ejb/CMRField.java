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
package org.eclipse.jst.javaee.ejb;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CMR Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The cmr-fieldType describes the Bean Provider's view of
 *         a relationship. It consists of an optional description, and
 *         the name and the class type of a field in the source of a
 *         role of a relationship. The cmr-field-name element
 *         corresponds to the name used for the get and set accessor
 *         methods for the relationship. The cmr-field-type element is
 *         used only for collection-valued cmr-fields. It specifies the
 *         type of the collection that is used.
 *         
 *         Support for entity beans is optional as of EJB 3.2.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.CMRField#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.CMRField#getCmrFieldName <em>Cmr Field Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.CMRField#getCmrFieldType <em>Cmr Field Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.CMRField#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getCMRField()
 * @extends JavaEEObject
 * @generated
 */
public interface CMRField extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getCMRField_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Cmr Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The cmr-field-name element specifies the name of a
	 *             logical relationship field in the entity bean
	 *             class. The name of the cmr-field must begin with a
	 *             lowercase letter. This field is accessed by methods
	 *             whose names consist of the name of the field
	 *             specified by cmr-field-name in which the first
	 *             letter is uppercased, prefixed by "get" or "set".
	 *             
	 *             Support for entity beans is optional as of EJB 3.2.
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cmr Field Name</em>' attribute.
	 * @see #setCmrFieldName(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getCMRField_CmrFieldName()
	 * @generated
	 */
	String getCmrFieldName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.CMRField#getCmrFieldName <em>Cmr Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cmr Field Name</em>' attribute.
	 * @see #getCmrFieldName()
	 * @generated
	 */
	void setCmrFieldName(String value);

	/**
	 * Returns the value of the '<em><b>Cmr Field Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.ejb.CMRFieldType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cmr Field Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cmr Field Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.CMRFieldType
	 * @see #isSetCmrFieldType()
	 * @see #unsetCmrFieldType()
	 * @see #setCmrFieldType(CMRFieldType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getCMRField_CmrFieldType()
	 * @generated
	 */
	CMRFieldType getCmrFieldType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.CMRField#getCmrFieldType <em>Cmr Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cmr Field Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.CMRFieldType
	 * @see #isSetCmrFieldType()
	 * @see #unsetCmrFieldType()
	 * @see #getCmrFieldType()
	 * @generated
	 */
	void setCmrFieldType(CMRFieldType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.CMRField#getCmrFieldType <em>Cmr Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCmrFieldType()
	 * @see #getCmrFieldType()
	 * @see #setCmrFieldType(CMRFieldType)
	 * @generated
	 */
	void unsetCmrFieldType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.CMRField#getCmrFieldType <em>Cmr Field Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cmr Field Type</em>' attribute is set.
	 * @see #unsetCmrFieldType()
	 * @see #getCmrFieldType()
	 * @see #setCmrFieldType(CMRFieldType)
	 * @generated
	 */
	boolean isSetCmrFieldType();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getCMRField_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.CMRField#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // CMRField