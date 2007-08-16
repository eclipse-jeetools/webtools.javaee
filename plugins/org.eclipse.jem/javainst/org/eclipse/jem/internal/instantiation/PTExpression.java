/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation;
/*
 *  $RCSfile: PTExpression.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:20:24 $ 
 */
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTExpression()
 * @model abstract="true"
 * @generated
 */
public interface PTExpression extends EObject{
	/**
	 * Accepts the given visitor on a visit of the current node.
	 * 
	 * @param visitor the visitor object
	 * @exception IllegalArgumentException if the visitor is null
	 */
	public void accept(ParseVisitor visitor);
} // Expression
