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
 *  $RCSfile: ExpressionImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.Expression;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class ExpressionImpl extends EObjectImpl implements Expression {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getExpression();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.Expression#accept(org.eclipse.jem.internal.instantiation.ParseVisitor)
	 */
	public final void accept(ParseVisitor visitor) {
		if (visitor == null) {
			throw new IllegalArgumentException();
		}
		// begin with the generic pre-visit
		visitor.preVisit(this);
		// dynamic dispatch to internal method for type-specific visit/endVisit
		accept0(visitor);
		// end with the generic post-visit
		visitor.postVisit(this);
	}

	/**
	 * Accepts the given visitor on a type-specific visit of the current node.
	 * This method must be implemented in all concrete Expression node types.
	 * <p>
	 * General template for implementation on each concrete ASTNode class:
	 * <pre>
	 * <code>
	 * boolean visitChildren = visitor.visit(this);
	 * if (visitChildren) {
	 *    // visit children in normal left to right reading order
	 *    acceptChild(visitor, getProperty1());
	 *    acceptChildren(visitor, rawListProperty);
	 *    acceptChild(visitor, getProperty2());
	 * }
	 * visitor.endVisit(this);
	 * </code>
	 * </pre>
	 * Note that the caller (<code>accept</code>) take cares of invoking
	 * <code>visitor.preVisit(this)</code> and <code>visitor.postVisit(this)</code>.
	 * </p>
	 * 
	 * @param visitor the visitor object
	 */
	protected abstract void accept0(ParseVisitor visitor);

	/**
	 * Accepts the given visitor on a visit of the current node.
	 * <p>
	 * This method should be used by the concrete implementations of
	 * <code>accept0</code> to traverse optional properties. Equivalent
	 * to <code>child.accept(visitor)</code> if <code>child</code>
	 * is not <code>null</code>.
	 * </p>
	 * 
	 * @param visitor the visitor object
	 * @param child the child Expression node to dispatch too, or <code>null</code>
	 *    if none
	 */
	protected final void acceptChild(ParseVisitor visitor, Expression child) {
		if (child == null) {
			return;
		}
		child.accept(visitor);
	}
	
	/**
	 * Accepts the given visitor on a visit of the given live list of
	 * child nodes. 
	 * @param visitor the visitor object
	 * @param children the child Expression nodes to dispatch to, or <code>null</code> if none.
	 */
	protected final void acceptChildren(ParseVisitor visitor, List children) {
		if (children != null) {
			int size = children.size();
			for (int i = 0; i < size; i++) {
				acceptChild(visitor, (Expression) children.get(i));
			}
		} 
	}	

} //ExpressionImpl
