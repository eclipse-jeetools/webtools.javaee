/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: NaiveExpressionFlattener.java,v $
 *  $Revision: 1.4 $  $Date: 2004/05/10 18:12:54 $ 
 */
package org.eclipse.jem.internal.instantiation.impl;

import java.util.List;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.PTArrayAccess;
import org.eclipse.jem.internal.instantiation.ParseVisitor;
 
/**
 * This naively flattens the ParseTree. It just works with what's there.
 * 
 * @since 1.0.0
 */
public class NaiveExpressionFlattener extends ParseVisitor {
	
	private StringBuffer buffer = new StringBuffer(100);
	
	protected final StringBuffer getStringBuffer() {
		return buffer;
	}
	
	/**
	 * Return the string result of the flattening.
	 * @return The flattened string.
	 * 
	 * @since 1.0.0
	 */
	public String getResult() {
		return buffer.toString();
	}
	
	/**
	 * Reset the result so that flattener can be used again.
	 * 
	 * @since 1.0.0
	 */
	public void reset() {
		buffer.setLength(0);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTArrayAccess)
	 */
	public boolean visit(PTArrayAccess node) {
		node.getArray().accept(this);
		if (!node.getIndexes().isEmpty()) {
			List indexes = node.getIndexes();
			for (int i = 0; i < indexes.size(); i++) {
				buffer.append('[');
				((PTExpression) indexes.get(i)).accept(this);
				buffer.append(']');
			}
		}
		return false;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTArrayCreation)
	 */
	public boolean visit(PTArrayCreation node) {
		String type = node.getType();
		buffer.append("new ");
		int ob = type.indexOf('[');
		buffer.append(type.substring(0, ob));
		int realdims = 0;
		while (ob != -1) {
			realdims++;
			ob = type.indexOf('[',ob+1);
		}
		List dims = node.getDimensions();
		for (int i = 0; i < dims.size(); i++) {
			buffer.append('[');
			((PTExpression) dims.get(i)).accept(this);
			buffer.append(']');
		}
		for (int i=dims.size(); i < realdims; i++) {
			buffer.append("[]");
		}
		
		if (node.getInitializer() != null) {
			buffer.append(' ');
			((PTExpression) node.getInitializer()).accept(this);
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTArrayInitializer)
	 */
	public boolean visit(PTArrayInitializer node) {
		buffer.append('{');
		List exp = node.getExpressions();
		for (int i = 0; i < exp.size(); i++) {
			if (i != 0)
				buffer.append(", ");
			((PTExpression) exp.get(i)).accept(this);
		}
		buffer.append('}');
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTBooleanLiteral)
	 */
	public boolean visit(PTBooleanLiteral node) {
		buffer.append(node.isBooleanValue() ? "true" : "false");
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTCastExpression)
	 */
	public boolean visit(PTCastExpression node) {
		buffer.append('(');
		buffer.append(node.getType());
		buffer.append(") ");
		node.getExpression().accept(this);
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTCharacterLiteral)
	 */
	public boolean visit(PTCharacterLiteral node) {
		buffer.append(node.getEscapedValue());
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTClassInstanceCreation)
	 */
	public boolean visit(PTClassInstanceCreation node) {
		buffer.append("new ");
		buffer.append(node.getType());
		buffer.append('(');
		List args = node.getArguments();
		for (int i = 0; i < args.size(); i++) {
			if (i != 0)
				buffer.append(", ");
			((PTExpression) args.get(i)).accept(this);
		}
		buffer.append(')');
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTConditionalExpression)
	 */
	public boolean visit(PTConditionalExpression node) {
		node.getCondition().accept(this);
		buffer.append(" ? ");
		node.getTrue().accept(this);
		buffer.append(" : ");
		node.getFalse().accept(this);
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTFieldAccess)
	 */
	public boolean visit(PTFieldAccess node) {
		node.getReceiver().accept(this);
		buffer.append('.');
		buffer.append(node.getField());
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTInfixExpression)
	 */
	public boolean visit(PTInfixExpression node) {
		node.getLeftOperand().accept(this);
		buffer.append(' ');
		String oper = node.getOperator().getOperator();
		buffer.append(oper);
		buffer.append(' ');
		node.getRightOperand().accept(this);
		List ext = node.getExtendedOperands();
		for (int i = 0; i < ext.size(); i++) {
			buffer.append(' ');
			buffer.append(oper);
			buffer.append(' ');
			((PTExpression) ext.get(i)).accept(this);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTInstanceof)
	 */
	public boolean visit(PTInstanceof node) {
		node.getOperand().accept(this);
		buffer.append(" instanceof ");
		buffer.append(node.getType());
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTInvalidExpression)
	 */
	public boolean visit(PTInvalidExpression node) {
		buffer.append(" invalid: \"");
		buffer.append(node.getMessage());
		buffer.append("\"");
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTMethodInvocation)
	 */
	public boolean visit(PTMethodInvocation node) {
		if (node.getReceiver() != null) {
			node.getReceiver().accept(this);
			buffer.append('.');
		}
		
		buffer.append(node.getName());
		buffer.append('(');
		List args = node.getArguments();
		for (int i = 0; i < args.size(); i++) {
			if (i != 0)
				buffer.append(", ");
			((PTExpression) args.get(i)).accept(this);
		}
		buffer.append(')');
		return false;	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTName)
	 */
	public boolean visit(PTName node) {
		buffer.append(node.getName());
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTNullLiteral)
	 */
	public boolean visit(PTNullLiteral node) {
		buffer.append("null");
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTNumberLiteral)
	 */
	public boolean visit(PTNumberLiteral node) {
		buffer.append(node.getToken());
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTParenthesizedExpression)
	 */
	public boolean visit(PTParenthesizedExpression node) {
		buffer.append('(');
		node.getExpression().accept(this);
		buffer.append(')');
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTPrefixExpression)
	 */
	public boolean visit(PTPrefixExpression node) {
		buffer.append(node.getOperator().getOperator());
		node.getExpression().accept(this);
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTStringLiteral)
	 */
	public boolean visit(PTStringLiteral node) {
		buffer.append(node.getEscapedValue());
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTThisLiteral)
	 */
	public boolean visit(PTThisLiteral node) {
		buffer.append("this");
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTTypeLiteral)
	 */
	public boolean visit(PTTypeLiteral node) {
		buffer.append(node.getType());
		buffer.append(".class");
		return false;
	}

}
