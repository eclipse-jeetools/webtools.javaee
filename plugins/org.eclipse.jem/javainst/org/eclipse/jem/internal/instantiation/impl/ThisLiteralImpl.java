package org.eclipse.jem.internal.instantiation.impl;
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
 *  $RCSfile: ThisLiteralImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */
import org.eclipse.emf.ecore.EClass;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;
import org.eclipse.jem.internal.instantiation.ThisLiteral;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>This Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ThisLiteralImpl extends ExpressionImpl implements ThisLiteral {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ThisLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getThisLiteral();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "this";
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.impl.ExpressionImpl#accept0(org.eclipse.jem.internal.instantiation.ParseVisitor)
	 */
	protected void accept0(ParseVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}	

} //ThisLiteralImpl
