package org.eclipse.jem.internal.instantiation;
/*******************************************************************************
 * Copyright (c)  2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: Expression.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getExpression()
 * @model abstract="true"
 * @generated
 */
public interface Expression extends EObject {
	/**
	 * Accepts the given visitor on a visit of the current node.
	 * 
	 * @param visitor the visitor object
	 * @exception IllegalArgumentException if the visitor is null
	 */
	public void accept(ParseVisitor visitor);
} // Expression
