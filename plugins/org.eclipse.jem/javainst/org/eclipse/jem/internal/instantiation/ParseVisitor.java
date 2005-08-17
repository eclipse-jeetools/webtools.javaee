/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ParseVisitor.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/17 18:36:32 $ 
 */
package org.eclipse.jem.internal.instantiation;
 
/**
 * A visitor for abstract parse expression trees.
 * <p>
 * For each different concrete Expression node type <it>T</it> there are
 * a pair of methods:
 * <ul>
 * <li><code>public boolean visit(<it>T</it> node)</code> - Visits
 * the given node to perform some arbitrary operation. If <code>true</code>
 * is returned, the given node's child nodes will be visited next; however,
 * if <code>false</code> is returned, the given node's child nodes will 
 * not be visited. The default implementation provided by this class does
 * nothing and returns <code>true</code>. Subclasses may reimplement
 * this method as needed.</li>
 * <li><code>public void endVisit(<it>T</it> node)</code> - Visits
 * the given node to perform some arbitrary operation. When used in the
 * conventional way, this method is called after all of the given node's
 * children have been visited (or immediately, if <code>visit</code> returned
 * <code>false</code>). The default implementation provided by this class does
 * nothing. Subclasses may reimplement this method as needed.</li>
 * </ul>
 * </p>
 * In addition, there are a pair of methods for visiting AST nodes in the 
 * abstract, regardless of node type:
 * <ul>
 * <li><code>public void preVisit(ASTNode node)</code> - Visits
 * the given node to perform some arbitrary operation. 
 * This method is invoked prior to the appropriate type-specific
 * <code>visit</code> method.
 * The default implementation of this method does nothing.
 * Subclasses may reimplement this method as needed.</li>
 * <li><code>public void postVisit(ASTNode node)</code> - Visits
 * the given node to perform some arbitrary operation. 
 * This method is invoked after the appropriate type-specific
 * <code>endVisit</code> method.
 * The default implementation of this method does nothing.
 * Subclasses may reimplement this method as needed.</li>
 * </ul>
 * <p>
 * For nodes with list-valued properties, the child nodes within the list
 * are visited in order. For nodes with multiple properties, the child nodes
 * are visited in the order that most closely corresponds to the lexical
 * reading order of the source program. For instance, for a type declaration
 * node, the child ordering is: name, superclass, superinterfaces, and 
 * body declarations.
 * </p>
 * <p>
 * While it is possible to modify the tree in the visitor, care is required to
 * ensure that the consequences are as expected and desirable.
 * During the course of an ordinary visit starting at a given node, every node
 * in the subtree is visited exactly twice, first with <code>visit</code> and
 * then with <code>endVisit</code>. During a traversal of a stationary tree, 
 * each node is either behind (after <code>endVisit</code>), ahead (before 
 * <code>visit</code>), or in progress (between <code>visit</code> and
 * the matching <code>endVisit</code>). Changes to the "behind" region of the
 * tree are of no consequence to the visit in progress. Changes to the "ahead"
 * region will be taken in stride. Changes to the "in progress" portion are
 * the more interesting cases. With a node, the various properties are arranged
 * in a linear list, with a cursor that separates the properties that have
 * been visited from the ones that are still to be visited (the cursor
 * is between the elements, rather than on an element). The cursor moves from
 * the head to the tail of this list, advancing to the next position just
 * <it>before</it> <code>visit</code> if called for that child. After the child
 * subtree has been completely visited, the visit moves on the child 
 * immediately after the cursor. Removing a child while it is being visited
 * does not alter the course of the visit. But any children added at positions
 * after the cursor are considered in the "ahead" portion and will be visited.
 * </p>
 * <p>
 * Cases to watch out for:
 * <ul>
 * <li>Moving a child node further down the list. This could result in the
 * child subtree being visited multiple times; these visits are sequential.</li>
 * <li>Moving a child node up into an ancestor. If the new home for
 * the node is in the "ahead" portion, the subtree will be visited 
 * a second time; again, these visits are sequential.</li>
 * <li>Moving a node down into a child. If the new home for
 * the node is in the "ahead" portion, the subtree will be visited 
 * a second time; in this case, the visits will be nested. In some cases,
 * this can lead to a stack overflow or out of memory condition.</li>
 * </ul>
 * </p>
 *
 * Note: This is based upon the visit concept in org.eclipse.jdt.core.dom.ASTVisitor. 
 *
 * @see Expression#accept
 *  
 * @since 1.0.0
 */
public class ParseVisitor {
	
	/**
	 * Exception to throw to just stop visiting a parse visit. It doesn't mean error, it
	 * just means stop visiting. ParseVisitors should throw this when they want to stop
	 * visiting further without an explicit error.
	 * <p>
	 * If they want to indicate an error, parse visitors can throw any other RuntimeException. 
	 * Those will also stop visiting, but the exception will be passed on out of the 
	 * {@link PTExpression#accept(ParseVisitor)}. StopVisiting will not be passed on
	 * but will be caught inside accept.
	 * 
	 * @since 1.1.0.1
	 */
	public static class StopVisiting extends RuntimeException {

		/**
		 * Comment for <code>serialVersionUID</code>
		 * 
		 * @since 1.1.0.1
		 */
		private static final long serialVersionUID = -5246860806568355520L;
		
	}
	/**
	 * Visits the given parse node prior to the type-specific visit. (before <code>visit</code>).
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 * 
	 * @param node
	 *            the node to visit
	 */
	public void preVisit(PTExpression node) {
		// default implementation: do nothing
	}

	/**
	 * Visits the given parse node following the type-specific visit (after <code>endVisit</code>).
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 * 
	 * @param node
	 *            the node to visit
	 */
	public void postVisit(PTExpression node) {
		// default implementation: do nothing
	}

	public boolean visit(PTArrayAccess node) {
		return true;
	}
	public boolean visit(PTArrayCreation node) {
		return true;
	}
	public boolean visit(PTArrayInitializer node) {
		return true;
	}
	public boolean visit(PTBooleanLiteral node) {
		return true;
	}
	public boolean visit(PTCastExpression node) {
		return true;
	}
	public boolean visit(PTCharacterLiteral node) {
		return true;
	}
	public boolean visit(PTClassInstanceCreation node) {
		return true;
	}
	public boolean visit(PTConditionalExpression node) {
		return true;
	}
	public boolean visit(PTFieldAccess node) {
		return true;
	}
	public boolean visit(PTInfixExpression node) {
		return true;
	}
	public boolean visit(PTInstanceof node) {
		return true;
	}
	public boolean visit(PTInvalidExpression node) {
		return true;
	}
	public boolean visit(PTMethodInvocation node) {
		return true;
	}
	public boolean visit(PTName node) {
		return true;
	}
	public boolean visit(PTNullLiteral node) {
		return true;
	}
	public boolean visit(PTNumberLiteral node) {
		return true;
	}
	public boolean visit(PTParenthesizedExpression node) {
		return true;
	}
	public boolean visit(PTPrefixExpression node) {
		return true;
	}
	public boolean visit(PTStringLiteral node) {
		return true;
	}
	public boolean visit(PTThisLiteral node) {
		return true;
	}
	public boolean visit(PTTypeLiteral node) {
		return true;
	}
	public boolean visit(PTInstanceReference node) {
		return true;
	}

	public void endVisit(PTArrayAccess node) {
		// default implementation: do nothing
	}
	public void endVisit(PTArrayCreation node) {
		// default implementation: do nothing
	}
	public void endVisit(PTArrayInitializer node) {
		// default implementation: do nothing
	}
	public void endVisit(PTBooleanLiteral node) {
		// default implementation: do nothing
	}
	public void endVisit(PTCastExpression node) {
		// default implementation: do nothing
	}
	public void endVisit(PTCharacterLiteral node) {
		// default implementation: do nothing
	}
	public void endVisit(PTClassInstanceCreation node) {
		// default implementation: do nothing
	}
	public void endVisit(PTConditionalExpression node) {
		// default implementation: do nothing
	}
	public void endVisit(PTFieldAccess node) {
		// default implementation: do nothing
	}
	public void endVisit(PTInfixExpression node) {
		// default implementation: do nothing
	}
	public void endVisit(PTInstanceof node) {
		// default implementation: do nothing
	}
	public void endVisit(PTInvalidExpression node) {
		// default implementation: do nothing
	}
	public void endVisit(PTMethodInvocation node) {
		// default implementation: do nothing
	}
	public void endVisit(PTName node) {
		// default implementation: do nothing
	}
	public void endVisit(PTNullLiteral node) {
		// default implementation: do nothing
	}
	public void endVisit(PTNumberLiteral node) {
		// default implementation: do nothing
	}
	public void endVisit(PTParenthesizedExpression node) {
		// default implementation: do nothing
	}
	public void endVisit(PTPrefixExpression node) {
		// default implementation: do nothing
	}
	public void endVisit(PTStringLiteral node) {
		// default implementation: do nothing
	}
	public void endVisit(PTThisLiteral node) {
		// default implementation: do nothing
	}
	public void endVisit(PTTypeLiteral node) {
		// default implementation: do nothing
	}
	public void endVisit(PTInstanceReference node) {
		// default implementation: do nothing
	}
	
}
