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
 *  $RCSfile: ParseTreeAllocationInstantiationVisitor.java,v $
 *  $Revision: 1.3 $  $Date: 2004/06/04 23:25:40 $ 
 */
package org.eclipse.jem.internal.instantiation.base;

import java.util.List;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.ParseVisitor;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.initParser.tree.IExpressionConstants;
import org.eclipse.jem.internal.proxy.initParser.tree.IExpressionConstants.NoExpressionValueException;
 
/**
 * This is the standard parse visitor for instantiating a bean proxy from a java parse tree allocation.
 * It can be reused, but is not thread-safe.
 * 
 * @since 1.0.0
 */
public class ParseTreeAllocationInstantiationVisitor extends ParseVisitor {
	
	/**
	 * Registry for processing the expressions against.
	 */
	private ProxyFactoryRegistry registry;
	
	/**
	 * The expression that is being created and evaluated.
	 */
	private IExpression expression;
	
	/*
	 * The next expression type that should be used. It is used when one expression is sending the
	 * visitation to the next expression. It will set this to what that expression should be using. This
	 * is necessary because the next expression doesn't know what it should be.
	 */
	private int nextExpression = IExpression.ROOTEXPRESSION;
	
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
		 * @param cause
		 * 
		 * @since 1.0.0
		 */
		public ProcessingException(Throwable cause) {
			super(cause);
		}
	}
	
	static final int[] INFIXTOPROXY;
	static {
		INFIXTOPROXY = new int[PTInfixOperator.VALUES.size()];
		INFIXTOPROXY[PTInfixOperator.AND] = IExpressionConstants.IN_AND;
		INFIXTOPROXY[PTInfixOperator.CONDITIONAL_AND] = IExpressionConstants.IN_CONDITIONAL_AND;
		INFIXTOPROXY[PTInfixOperator.CONDITIONAL_OR] = IExpressionConstants.IN_CONDITIONAL_OR;
		INFIXTOPROXY[PTInfixOperator.DIVIDE] = IExpressionConstants.IN_DIVIDE;
		INFIXTOPROXY[PTInfixOperator.EQUALS] = IExpressionConstants.IN_EQUALS;
		INFIXTOPROXY[PTInfixOperator.GREATER] = IExpressionConstants.IN_GREATER;
		INFIXTOPROXY[PTInfixOperator.GREATER_EQUALS] = IExpressionConstants.IN_GREATER_EQUALS;
		INFIXTOPROXY[PTInfixOperator.LEFT_SHIFT] = IExpressionConstants.IN_LEFT_SHIFT;
		INFIXTOPROXY[PTInfixOperator.LESS] = IExpressionConstants.IN_LESS;
		INFIXTOPROXY[PTInfixOperator.LESS_EQUALS] = IExpressionConstants.IN_LESS_EQUALS;
		INFIXTOPROXY[PTInfixOperator.MINUS] = IExpressionConstants.IN_MINUS;
		INFIXTOPROXY[PTInfixOperator.NOT_EQUALS] = IExpressionConstants.IN_NOT_EQUALS;
		INFIXTOPROXY[PTInfixOperator.OR] = IExpressionConstants.IN_OR;
		INFIXTOPROXY[PTInfixOperator.PLUS] = IExpressionConstants.IN_PLUS;
		INFIXTOPROXY[PTInfixOperator.REMAINDER] = IExpressionConstants.IN_REMAINDER;
		INFIXTOPROXY[PTInfixOperator.RIGHT_SHIFT_SIGNED] = IExpressionConstants.IN_RIGHT_SHIFT_SIGNED;
		INFIXTOPROXY[PTInfixOperator.RIGHT_SHIFT_UNSIGNED] = IExpressionConstants.IN_RIGHT_SHIFT_UNSIGNED;
		INFIXTOPROXY[PTInfixOperator.TIMES] = IExpressionConstants.IN_TIMES;
		INFIXTOPROXY[PTInfixOperator.XOR] = IExpressionConstants.IN_XOR;
	}
	
	/**
	 * A helper method to convert the parse tree's infix operator to the Proxy infix operator.
	 * 
	 * @param operator
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public static int convertPTInfixOperatorToProxyInfixOperator(PTInfixOperator operator) {
		return INFIXTOPROXY[operator.getValue()];
	}

	static final int[] PREFIXTOPROXY;
	static {
		PREFIXTOPROXY = new int[PTPrefixOperator.VALUES.size()];
		PREFIXTOPROXY[PTPrefixOperator.COMPLEMENT] = IExpressionConstants.PRE_COMPLEMENT;
		PREFIXTOPROXY[PTPrefixOperator.MINUS] = IExpressionConstants.PRE_MINUS;
		PREFIXTOPROXY[PTPrefixOperator.NOT] = IExpressionConstants.PRE_NOT;
		PREFIXTOPROXY[PTPrefixOperator.PLUS] = IExpressionConstants.PRE_PLUS;
	}
	
	/**
	 * A helper method to convert the parse tree's prefix operator to the Proxy prefix operator.
	 * 
	 * @param operator
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public static int convertPTPrefixOperatorToProxyPrefixOperator(PTPrefixOperator operator) {
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
		return registry;
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
	 * Get the beanproxy for the given expression and registry.
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
		this.registry = registry;
		this.expression = registry.getBeanProxyFactory().createExpression();
		setNextExpression(IExpression.ROOTEXPRESSION);
		try {
			expression.accept(this);
		} catch (ProcessingException e) {
			// Handle the most common that make sense to be know distinctly and throw them instead of ProcessingException.
			Throwable t = e.getCause();
			if (t instanceof ThrowableProxy)
				throw (ThrowableProxy) t;
			else if (t instanceof NoExpressionValueException)
				throw (NoExpressionValueException) t;
			else
				throw e;
		}
		
		return getExpression().getExpressionValue();
	}
	
	/**
	 * Set the next expression type. (i.e. the <code>forExpression</code> field of most of the create expression methods.
	 * 
	 * @param nextExpression
	 * 
	 * @see IExpression#createInfixExpression(int, int, int)
	 * @since 1.0.0
	 */
	protected final void setNextExpression(int nextExpression) {
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
	protected final int getNextExpression() {
		return nextExpression;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTArrayAccess)
	 */
	public boolean visit(PTArrayAccess node) {
		try {
			getExpression().createArrayAccess(getNextExpression(), node.getIndexes().size());
			setNextExpression(IExpression.ARRAYACCESS_ARRAY);
			node.getArray().accept(this);
			List idx = node.getIndexes();
			int s = idx.size();
			for (int i = 0; i < s; i++) {
				setNextExpression(IExpression.ARRAYACCESS_INDEX);
				((PTExpression) idx.get(i)).accept(this);
			}
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTArrayCreation)
	 */
	public boolean visit(PTArrayCreation node) {
		try {
			getExpression().createArrayCreation(getNextExpression(), node.getType(), node.getDimensions().size());
			if (node.getDimensions().isEmpty()) {
				node.getInitializer().accept(this);	// Array initializer doesn't require a next expression.
			} else {
				List dims = node.getDimensions();
				int s = dims.size();
				for (int i = 0; i < s; i++) {
					setNextExpression(IExpression.ARRAYCREATION_DIMENSION);
					((PTExpression) dims.get(i)).accept(this);
				}
			}
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTArrayInitializer)
	 */
	public boolean visit(PTArrayInitializer node) {
		try {
			getExpression().createArrayInitializer(node.getExpressions().size());
			List exps = node.getExpressions();
			int s = exps.size();
			for (int i = 0; i < s; i++) {
				setNextExpression(IExpression.ARRAYINITIALIZER_EXPRESSION);
				((PTExpression) exps.get(i)).accept(this);
			}
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTBooleanLiteral)
	 */
	public boolean visit(PTBooleanLiteral node) {
		try {
			getExpression().createPrimitiveLiteral(getNextExpression(), node.isBooleanValue());
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTCastExpression)
	 */
	public boolean visit(PTCastExpression node) {
		try {
			getExpression().createCastExpression(getNextExpression(), node.getType());
			setNextExpression(IExpression.CAST_EXPRESSION);
			node.getExpression().accept(this);
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTCharacterLiteral)
	 */
	public boolean visit(PTCharacterLiteral node) {
		try {
			getExpression().createPrimitiveLiteral(getNextExpression(), node.getCharValue());
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTClassInstanceCreation)
	 */
	public boolean visit(PTClassInstanceCreation node) {
		try {
			getExpression().createClassInstanceCreation(getNextExpression(), node.getType(), node.getArguments().size());
			List args = node.getArguments();
			int s = args.size();
			for (int i = 0; i < s; i++) {
				setNextExpression(IExpression.CLASSINSTANCECREATION_ARGUMENT);
				((PTExpression) args.get(i)).accept(this);
			}
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTConditionalExpression)
	 */
	public boolean visit(PTConditionalExpression node) {
		try {
			getExpression().createConditionalExpression(getNextExpression());
			setNextExpression(IExpression.CONDITIONAL_CONDITION);
			node.getCondition().accept(this);
			setNextExpression(IExpression.CONDITIONAL_TRUE);
			node.getTrue().accept(this);
			setNextExpression(IExpression.CONDITIONAL_FALSE);
			node.getFalse().accept(this);			
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTFieldAccess)
	 */
	public boolean visit(PTFieldAccess node) {
		try {
			getExpression().createFieldAccess(getNextExpression(), node.getField(), node.getReceiver() != null);
			if (node.getReceiver() != null) {
				setNextExpression(IExpression.FIELD_RECEIVER);
				node.getReceiver().accept(this);
			}
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTInfixExpression)
	 */
	public boolean visit(PTInfixExpression node) {
		try {
			getExpression().createInfixExpression(getNextExpression(), convertPTInfixOperatorToProxyInfixOperator(node.getOperator()), node.getExtendedOperands().size());
			setNextExpression(IExpression.INFIX_LEFT);
			node.getLeftOperand().accept(this);
			setNextExpression(IExpression.INFIX_RIGHT);
			node.getRightOperand().accept(this);
			List extended = node.getExtendedOperands();
			int s = extended.size();
			for (int i = 0; i < s; i++) {
				setNextExpression(IExpression.INFIX_EXTENDED);
				((PTExpression) extended.get(i)).accept(this);
			}
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTInstanceof)
	 */
	public boolean visit(PTInstanceof node) {
		try {
			getExpression().createInstanceofExpression(getNextExpression(), node.getType());
			setNextExpression(IExpression.INSTANCEOF_VALUE);
			node.getOperand().accept(this);
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
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
		try {
			getExpression().createMethodInvocation(getNextExpression(), node.getName(), node.getReceiver() != null, node.getArguments().size());
			if (node.getReceiver() != null) {
				setNextExpression(IExpression.METHOD_RECEIVER);
				node.getReceiver().accept(this);
			}
			List args = node.getArguments();
			int s = args.size();
			for (int i = 0; i < s; i++) {
				setNextExpression(IExpression.METHOD_ARGUMENT);
				((PTExpression) args.get(i)).accept(this);
			}			
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTName)
	 */
	public boolean visit(PTName node) {
		try {
			// This is special in the PTName can only be used as a type receiver at this time.
			getExpression().createTypeReceiver(node.getName());
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTNullLiteral)
	 */
	public boolean visit(PTNullLiteral node) {
		try {
			// This is special in the PTName can only be used as a type receiver at this time.
			getExpression().createNull(getNextExpression());
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTNumberLiteral)
	 */
	public boolean visit(PTNumberLiteral node) {
		try {
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
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
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
		try {
			getExpression().createPrefixExpression(getNextExpression(), convertPTPrefixOperatorToProxyPrefixOperator(node.getOperator()));
			setNextExpression(IExpression.PREFIX_OPERAND);
			node.getExpression().accept(this);
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.ParseVisitor#visit(org.eclipse.jem.internal.instantiation.PTStringLiteral)
	 */
	public boolean visit(PTStringLiteral node) {
		try {
			getExpression().createProxyExpression(getNextExpression(), getRegistry().getBeanProxyFactory().createBeanProxyWith(node.getLiteralValue()));
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
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
		try {
			getExpression().createTypeLiteral(getNextExpression(), node.getType());
		} catch (ThrowableProxy e) {
			throw new ProcessingException(e);
		} catch (NoExpressionValueException e) {
			throw new ProcessingException(e);
		}
		return false;
	}

}
