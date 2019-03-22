/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation;
/*


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
 * <!-- begin-model-doc -->
 * This class is the abstract base class of the allocation class. It is the value of the "allocation" property on a Java Object so that the actual allocation can be controlled. For example, there could be one for just init string, or one for serialized.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getJavaAllocation()
 * @model abstract="true"
 * @generated
 */
public interface JavaAllocation extends EObject{
	
	/**
	 * Answer whether the allocation is a parse tree allocation.
	 * @return <code>true</code> if parse tree allocation
	 * 
	 * @since 1.2.0
	 */
	public boolean isParseTree();
	
	/**
	 * Answer whether the allocation is an implicit allocation.
	 * @return <code>true</code> if implicit allocation
	 * 
	 * @since 1.2.0
	 */
	public boolean isImplicit();
} // JavaAllocation
