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
 *  $RCSfile: ParseTreeAllocationInstantiationVisitor.java,v $
 *  $Revision: 1.6 $  $Date: 2005/06/16 17:46:17 $ 
 */
package org.eclipse.jem.internal.instantiation.base;

import java.util.List;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.initParser.tree.*;
 
/**
 * This is the standard parse visitor for instantiating a bean proxy from a java parse tree allocation.
 * It can be reused, but is not thread-safe.
 * 
 * @since 1.0.0
 */
public class ParseTreeAllocationInstantiationVisitor extends ParseVisitor {
		
	/**
	 * The expression that is being created and evaluated.
	 */
	private IExpression expression;
	
	/*
	 * The next expression type that should be used. It is used when one expression is sending the
	 * visitation to the next expression. It will set this to what that expression should be using. This
	 * is necessary because the next expression doesn't know what it should be.
	 */
	private ForExpression nextExpression = ForExpression.ROOTEXPRESSION;
	
	/**
	 * An exception occurred during processing. It is a RuntimeException because
	 * it can be thrown at any time. It wrappers another exception. That exception
	 * can be retrieved from the cause of the ProcessingException.
	 * 
	 * @see Throwable#getCause()
	 * @since 1.0.0
	 */
	public static class ProcessingException extends RuntimeException {
		
		/**
		 * Comment for <code>serialVersionUID</code>
		 * 
		 * @since 1.1.0
		 */
		private static final long serialVersionUID = 1268624222490406643L;

		/**
		 * @param cause
		 * 
		 * @since 1.0.0
		 */
		public ProcessingException(Throwable cause) {
			super(cause);
		}
	}
	
	static final InfixOperator[] INFIXTOPROXY;
	static {
		INFIXTOPROXY = new InfixOperator[PTInfixOperator.VALUES.size()];
		INFIXTOPROXY[PTInfixOperator.AND] = InfixOperator.IN_AND;
		INFIXTOPROXY[PTInfixOperator.CONDITIONAL_AND] = InfixOperator.IN_CONDITIONAL_AND;
		INFIXTOPROXY[PTInfixOperator.CONDITIONAL_OR] = InfixOperator.IN_CONDITIONAL_OR;
		INFIXTOPROXY[PTInfixOperator.DIVIDE] = InfixOperator.IN_DIVIDE;
		INFIXTOPROXY[PTInfixOperator.EQUALS] = InfixOperator.IN_EQUALS;
		INFIXTOPROXY[PTInfixOperator.GREATER] = InfixOperator.IN_GREATER;
		INFIXTOPROXY[PTInfixOperator.GREATER_EQUALS] = InfixOperator.IN_GREATER_EQUALS;
		INFIXTOPROXY[PTInfixOperator.LEFT_SHIFT] = InfixOperator.IN_LEFT_SHIFT;
		INFIXTOPROXY[PTInfixOperator.LESS] = InfixOperator.IN_LESS;
		INFIXTOPROXY[PTInfixOperator.LESS_EQUALS] = InfixOperator.IN_LESS_EQUALS;
		INFIXTOPROXY[PTInfixOperator.MINUS] = InfixOperator.IN_MINUS;
		INFIXTOPROXY[PTInfixOperator.NOT_EQUALS] = InfixOperator.IN_NOT_EQUALS;
		INFIXTOPROXY[PTInfixOperator.OR] = InfixOperator.IN_OR;
		INFIXTOPROXY[PTInfixOperator.PLUS] = InfixOperator.IN_PLUS;
		INFIXTOPROXY[PTInfixOperator.REMAINDER] = InfixOperator.IN_REMAINDER;
		INFIXTOPROXY[PTInfixOperator.RIGHT_SHIFT_SIGNED] = InfixOperator.IN_RIGHT_SHIFT_SIGNED;
		INFIXTOPROXY[PTInfixOperator.RIGHT_SHIFT_UNSIGNED] = InfixOperator.IN_RIGHT_SHIFT_UNSIGNED;
		INFIXTOPROXY[PTInfixOperator.TIMES] = InfixOperator.IN_TIMES;
		INFIXTOPROXY[PTInfixOperator.XOR] = InfixOperator.IN_XOR;
	}
	
	/**
	 * A helper method to convert the parse tree's infix operator to the Proxy infix operator.
	 * 
	 * @param operator
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public static InfixOperator convertPTInfixOperatorToProxyInfixOperator(PTInfixOperator operator) {
		return INFIXTOPROXY[operator.getValue()];
	}

	static final PrefixOperator[] PREFIXTOPROXY;
	static {
		PREFIXTOPROXY = new PrefixOperator[PTPrefixOperator.VALUES.size()];
		PREFIXTOPROXY[PTPrefixOperator.COMPLEMENT] = PrefixOperator.PRE_COMPLEMENT;
		PREFIXTOPROXY[PTPrefixOperator.MINUS] = PrefixOperator.PRE_MINUS;
		PREFIXTOPROXY[PTPrefixOperator.NOT] = PrefixOperator.PRE_NOT;
		PREFIXTOPROXY[PTPrefixOperator.PLUS] = PrefixOperator.PRE_PLUS;
	}
	
	/**
	 * A helper method to convert the parse tree's prefix operator to the Proxy prefix operator.
	 * 
	 * @param operator
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public static PrefixOperator convertPTPrefixOperatorToProxyPrefixOperator(PTPrefixOperator operator) {
		return PREFIXTOPROXY[operator.getValue()];
	}
	
	/**
	 * Create the visitor with the given registry.
	 * 
	 * @param registry
	 * 
	 * @since 1.0.0
	 */
	public ParseTreeAllocationInstantiationVisitor() {
	}
	
	/**
	 * Get the current registry.
	 * 
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final ProxyFactoryRegistry getRegistry() {
		return expression.getRegistry();
	}
	
	/**
	 * Get the current expression.
	 * 
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final IExpression getExpression() {
		return expression;
	}	

	/**
	 * Get the beanproxy for the given expression and registry. It will evaluate immediately.
	 * 
	 * @param expression
	 * @param registry
	 * @return
	 * @throws IllegalStateException
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * @throws ProcessingException
	 * 
	 * @since 1.0.0
	 */
	public IBeanProxy getBeanProxy(PTExpression expression, ProxyFactoryRegistry registry) throws IllegalStateException, IllegalArgumentException, ThrowableProxy, NoExpressionValueException, ProcessingException {
		this.expression = registry.getBeanProxyFactory().createExpression();
		setNextExpression(ForExpression.ROOTEXPRESSION);
		try {
			expression.accept(this);
		} catch (ProcessingException e) {
			// Handle the most common that make sense to be know distinctly and throw them instead of ProcessingException.
			Throwable t = e.getCause();
			if (t instanceof NoExpressionValueException)
				throw (NoExpressionValueException) t;
			else if (t instanceof IllegalStateException)
				throw (IllegalStateException) t;
			else
				throw e;
		}
		
		return getExpression().getExpressionValue();
	}
	
	/**
	 * Using the given expression processor allocate the proxy. It will not evaluate immediately, but just push onto the expression.
	 * @param expression
	 * @param expressionProcessor
	 * @return the ExpressionProxy for the allocation.
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 * @throws ProcessingException
	 * 
	 * @since 1.1.0
	 */
	public ExpressionProxy getProxy(PTExpression expression, IExpression expressionProcessor) throws IllegalStateException, IllegalArgumentException, ProcessingException {
		this.expression = expressionProcessor;
		try {
			ExpressionProxy proxy = expressionProcessor.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			setNextExpression(ForExpression.ASSIGNMENT_RIGHT);
			expression.accept(this);
			return proxy;
		} catch (ProcessingException e) {
			// Handle the most common that make sense to be know distinctly and throw them instead of ProcessingException.
			Throwable t = e.getCause();
			if (t instanceof IllegalStateException)
				throw (IllegalStateException) t;
			else
				throw e;
		}
	}

	
	/**
	 * Set the next expression type. (i.e. the <code>forExpression</code> field of most of the create expression methods.
	 * 
	 * @param nextExpression
	 * 
	 * @see IExpression#createInfixExpression(int, int, int)
	 * @since 1.0.0
	 */
	protected final void setNextExpression(ForExpression nextExpression) {
		this.nextExpression = nextExpression;
	}

	/**
	 * Get the next expression type. (i.e. the <code>forExpression</code> field of most of the create expression methods.
	 * 
	 * @return
	 * 
	 * @see IExpression#createInfixExpression(int, int, int)
	 * @since 1.0.0
	 */
	protected final ForExpression getNextExpression() {
		return nextExpression;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTArrayAccess)
	 */
	public boolean visit(PTArrayAccess node) {
		getExpression().createArrayAccess(getNextExpression(), node.getIndexes().size());
		setNextExpression(ForExpression.ARRAYACCESS_ARRAY);
		node.getArray().accept(this);
		List idx = node.getIndexes();
		int s = idx.size();
		for (int i = 0; i < s; i++) {
			setNextExpression(ForExpression.ARRAYACCESS_INDEX);
			((PTExpression) idx.get(i)).accept(this);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTArrayCreation)
	 */
	public boolean visit(PTArrayCreation node) {
		getExpression().createArrayCreation(getNextExpression(), node.getType(), node.getDimensions().size());
		if (node.getDimensions().isEmpty()) {
			node.getInitializer().accept(this);	// Array initializer doesn't require a next expression.
		} else {
			List dims = node.getDimensions();
			int s = dims.size();
			for (int i = 0; i < s; i++) {
				setNextExpression(ForExpression.ARRAYCREATION_DIMENSION);
				((PTExpression) dims.get(i)).accept(this);
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTArrayInitializer)
	 */
	public boolean visit(PTArrayInitializer node) {
		getExpression().createArrayInitializer(node.getExpressions().size());
		List exps = node.getExpressions();
		int s = exps.size();
		for (int i = 0; i < s; i++) {
			setNextExpression(ForExpression.ARRAYINITIALIZER_EXPRESSION);
			((PTExpression) exps.get(i)).accept(this);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTBooleanLiteral)
	 */
	public boolean visit(PTBooleanLiteral node) {
		getExpression().createPrimitiveLiteral(getNextExpression(), node.isBooleanValue());
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTCastExpression)
	 */
	public boolean visit(PTCastExpression node) {
		getExpression().createCastExpression(getNextExpression(), node.getType());
		setNextExpression(ForExpression.CAST_EXPRESSION);
		node.getExpression().accept(this);
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTCharacterLiteral)
	 */
	public boolean visit(PTCharacterLiteral node) {
		getExpression().createPrimitiveLiteral(getNextExpression(), node.getCharValue());
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTClassInstanceCreation)
	 */
	public boolean visit(PTClassInstanceCreation node) {
		getExpression().createClassInstanceCreation(getNextExpression(), node.getType(), node.getArguments().size());
		List args = node.getArguments();
		int s = args.size();
		for (int i = 0; i < s; i++) {
			setNextExpression(ForExpression.CLASSINSTANCECREATION_ARGUMENT);
			((PTExpression) args.get(i)).accept(this);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTConditionalExpression)
	 */
	public boolean visit(PTConditionalExpression node) {
		getExpression().createConditionalExpression(getNextExpression());
		setNextExpression(ForExpression.CONDITIONAL_CONDITION);
		node.getCondition().accept(this);
		setNextExpression(ForExpression.CONDITIONAL_TRUE);
		node.getTrue().accept(this);
		setNextExpression(ForExpression.CONDITIONAL_FALSE);
		node.getFalse().accept(this);
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTFieldAccess)
	 */
	public boolean visit(PTFieldAccess node) {
		getExpression().createFieldAccess(getNextExpression(), node.getField(), node.getReceiver() != null);
		if (node.getReceiver() != null) {
			setNextExpression(ForExpression.FIELD_RECEIVER);
			node.getReceiver().accept(this);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTInfixExpression)
	 */
	public boolean visit(PTInfixExpression node) {
		getExpression().createInfixExpression(getNextExpression(), convertPTInfixOperatorToProxyInfixOperator(node.getOperator()), node.getExtendedOperands().size());
		setNextExpression(ForExpression.INFIX_LEFT);
		node.getLeftOperand().accept(this);
		setNextExpression(ForExpression.INFIX_RIGHT);
		node.getRightOperand().accept(this);
		List extended = node.getExtendedOperands();
		int s = extended.size();
		for (int i = 0; i < s; i++) {
			setNextExpression(ForExpression.INFIX_EXTENDED);
			((PTExpression) extended.get(i)).accept(this);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTInstanceof)
	 */
	public boolean visit(PTInstanceof node) {
		getExpression().createInstanceofExpression(getNextExpression(), node.getType());
		setNextExpression(ForExpression.INSTANCEOF_VALUE);
		node.getOperand().accept(this);
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTInvalidExpression)
	 */
	public boolean visit(PTInvalidExpression node) {
		throw new IllegalArgumentException(node.getMessage());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTMethodInvocation)
	 */
	public boolean visit(PTMethodInvocation node) {
		getExpression().createMethodInvocation(getNextExpression(), node.getName(), node.getReceiver() != null, node.getArguments().size());
		if (node.getReceiver() != null) {
			setNextExpression(ForExpression.METHOD_RECEIVER);
			node.getReceiver().accept(this);
		}
		List args = node.getArguments();
		int s = args.size();
		for (int i = 0; i < s; i++) {
			setNextExpression(ForExpression.METHOD_ARGUMENT);
			((PTExpression) args.get(i)).accept(this);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTName)
	 */
	public boolean visit(PTName node) {
		// This is special in the PTName can only be used as a type receiver at this time.
		getExpression().createTypeReceiver(node.getName());
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTNullLiteral)
	 */
	public boolean visit(PTNullLiteral node) {
		// This is special in the PTName can only be used as a type receiver at this time.
		getExpression().createNull(getNextExpression());
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTNumberLiteral)
	 */
	public boolean visit(PTNumberLiteral node) {
		// It is assumed the tokens are trimmed.
		String lit = node.getToken();
		char lastChar = lit.charAt(lit.length()-1);
		if (lastChar == 'l' || lastChar == 'L' ) {
			// It is definitely a long.
			// Using decode so that things like 0x3 will be parsed. parseLong won't recognize those.
			getExpression().createPrimitiveLiteral(getNextExpression(), Long.decode(lit.substring(0, lit.length()-1)).longValue());
		} else if (lastChar == 'F' || lastChar == 'f') {
			// It is definitely a float.
			getExpression().createPrimitiveLiteral(getNextExpression(), Float.parseFloat(lit.substring(0, lit.length()-1)));
		} else if (lastChar == 'D' || lastChar == 'd')  {
			// It is definitely a double.
			getExpression().createPrimitiveLiteral(getNextExpression(), Double.parseDouble(lit.substring(0, lit.length()-1)));
		} else if (lit.indexOf('.') != -1 || lit.indexOf('e') != -1 || lit.indexOf('E') != -1) {
				// It is definitely a double. (has a period or an exponent, but does not have an 'f' on the end is always a double).
				getExpression().createPrimitiveLiteral(getNextExpression(), Double.parseDouble(lit.substring(0, lit.length())));
		} else {
			// Using decode so that things like 0x3 will be parsed. parseInt won't recognize those.
			getExpression().createPrimitiveLiteral(getNextExpression(), Integer.decode(lit).intValue());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTParenthesizedExpression)
	 */
	public boolean visit(PTParenthesizedExpression node) {
		node.getExpression().accept(this);	// For instantiation purposes, the parenthesis can be ignored.
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTPrefixExpression)
	 */
	public boolean visit(PTPrefixExpression node) {
		getExpression().createPrefixExpression(getNextExpression(), convertPTPrefixOperatorToProxyPrefixOperator(node.getOperator()));
		setNextExpression(ForExpression.PREFIX_OPERAND);
		node.getExpression().accept(this);
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTStringLiteral)
	 */
	public boolean visit(PTStringLiteral node) {
		getExpression().createProxyExpression(getNextExpression(), getRegistry().getBeanProxyFactory().createBeanProxyWith(node.getLiteralValue()));
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTThisLiteral)
	 */
	public boolean visit(PTThisLiteral node) {
		throw new IllegalArgumentException(InstantiationBaseMessages.getString("ParseTreeAllocationInstantiationVisitor.CurrentlyThisNotSupported_EXC_")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTTypeLiteral)
	 */
	public boolean visit(PTTypeLiteral node) {
		getExpression().createTypeLiteral(getNextExpression(), node.getType());
		return false;
	}

}
