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
 *  $RCSfile: ThisLiteral.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>This Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents "this", e.g. this.getX() will be a MethodInvocation with the receiver being a ThisLiteral. We can't handle the format XYZ.this because that is for inner classes and we don't support that right now.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getThisLiteral()
 * @model 
 * @generated
 */
public interface ThisLiteral extends Expression {
} // ThisLiteral
