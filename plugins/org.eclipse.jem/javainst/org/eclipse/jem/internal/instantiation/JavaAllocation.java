package org.eclipse.jem.internal.instantiation;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaAllocation.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/12 21:44:21 $ 
 */
 
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Allocation</b></em>'.
 * <p>
 * This class is the abstract base class of the allocation class. It is the value of the "allocation" property on a Java Object so that the actual allocation can be controlled. For example, there could be one for just init string, or one for serialized.
 * </p>
 * @since 1.0.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.JavaAllocation#getAllocString <em>Alloc String</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getJavaAllocation()
 * @model abstract="true"
 * @generated
 */
public interface JavaAllocation extends EObject{
	/**
	 * Returns the value of the '<em><b>Alloc String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * This is the allocation string in readable format. It is usually a comment string to make viewing the XMI easier. It is typically not used otherwise. A specific subclass may use it, and that subclass will indicate that it does.
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alloc String</em>' attribute.
	 * @see #setAllocString(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getJavaAllocation_AllocString()
	 * @model volatile="true"
	 * @generated
	 */
	String getAllocString();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.JavaAllocation#getAllocString <em>Alloc String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * The set is actually ignored. allocString is a derived property. The value comes from subclasses. Setting here
	 * does nothing. The subclasses control the string.
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alloc String</em>' attribute.
	 * @see #getAllocString()
	 * @generated
	 */
	void setAllocString(String value);

} // JavaAllocation
