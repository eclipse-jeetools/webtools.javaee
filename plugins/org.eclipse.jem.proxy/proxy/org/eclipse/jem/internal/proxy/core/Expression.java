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
 *  $RCSfile: Expression.java,v $
 *  $Revision: 1.3 $  $Date: 2004/06/02 19:58:49 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.eclipse.jem.internal.proxy.initParser.tree.*;
 
/**
 * This is an internal implementation of Expression. It encapsulates much of the processing required
 * into a common form that will be turned into simple push/pop/evaluate type of interaction with the
 * actual other side. 
 * <p>
 * It will maintain a stack of the expressions. As the expressions come in they will be stacked if not
 * able to be executed immediately. The expressions come to this class in an  outside to inside order,
 * but they need to be processed in an inside-out order instead. 
 * <p>
 * Subclasses will be used for the different types of proxy interfaces. The abstract methods will
 * then be the simple interface. 
 * <p>
 * It is not meant to override the actual create expression methods because the processing the stack
 * is very sensitive and must execute in the proper sequence. So the create methods are final for this.
 * <p>
 * This class is not thread-safe.
 * 
 * @since 1.0.0
 */
public abstract class Expression implements IExpression {

	/*
	 * We have stack here, but rather than create a class that does the
	 * stack protocol, will simply have some private methods to do
	 * the same thing for the stack. (Note: Can't use java.util.Stack
	 * because that is a synchronized class, and don't want the overhead). 
	 * 
	 * The purpose of the stack is to stack up expressions that have not yet
	 * been evaluated. 
	 * 
	 * Each expression type will control the content of what it pushes and pops.
	 * The expression type will be the last thing it pushes so that on popping
	 * we know what kind of expression is now completed and ready for evaluation.
	 */
	private ArrayList controlStack = new ArrayList(30);
	
	protected final ProxyFactoryRegistry registry;
	protected final IStandardBeanProxyFactory beanProxyFactory;
	
	/**
	 * Push an object onto the control stack.
	 * 
	 * @param o
	 * 
	 * @since 1.0.0
	 */
	protected final void push(Object o) {
		controlStack.add(o);
	}
	
	/**
	 * Pop an object off of the control stack
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final Object pop() {
		return controlStack.remove(controlStack.size()-1);
	}
	
	/**
	 * Peek at an object from the control stack. <code>fromTop</code> is how far from the top of the stack to look.
	 * If it one, then it is the top entry, two is the next one down. Zero is an invalid value for the parameter.
	 * @param fromTop How far from the top to peek. <code>1</code> is the top, not zero.
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final Object peek(int fromTop) {
		// 1 means the top, 2 is the next one down.
		return controlStack.get(controlStack.size()-fromTop);
	}
	
	/*
	 * Expression type constants.
	 */
	
	/*
	 * ARRAY ACCESS expression.
	 * The expression stack will have:
	 * 	IExpression.ARRAYACCESS_ARRAY
	 * 	IExpression.ARRAYACCESS_INDEX (for 1 to n times depending on index count)
	 * 	PROCESS_EXPRESSION
	 * 
	 * The value stack will have
	 * 	ARRAYACCESS
	 * 	Integer(index count) 
	 */
	private static final Integer ARRAYACCESS = new Integer(IInternalExpressionConstants.ARRAY_ACCESS_EXPRESSION);
	private static final Integer ARRAYACCESS_INDEX_1 = new Integer(1);	// Use in normal case of one index count. Saves object creation.
	
	/*
	 * ARRAY CREATION expression.
	 * The expression stack will have:
	 * 	ARRAYCREATION_INITIALIZER - if hasInitializer
	 *  IExpression.ARRAYCREATION_DIMENSION (for 0 to n times depending on dimension count)
	 *  PROCESS_EXPRESSION
	 * 
	 * The value stack will have
	 *  ARRAYCREATION
	 *  type (either a string representing the type, or an IBeanProxyType representing the type).
	 *  Integer(dimension count) (if zero then there is an initializer) 
	 * 
	 * 
	 * Note: Array Initializer works with this in that it will peek into the value stack two entries down
	 * to find the type of array it should be creating.
	 */
	private static final Integer ARRAYCREATION = new Integer(IInternalExpressionConstants.ARRAY_CREATION_EXPRESSION);
	private static final Integer ARRAY_CREATION_DIMENSION_1 = new Integer(1);	// Use in normal case of one dimension. Save object creation.
	private static final Integer ARRAY_CREATION_DIMENSION_0 = new Integer(0);	// Use in normal case of initializer. Save object creation.
	private static final int ARRAY_INITIALIZER = -1;	// Local because only needed her for expression.
	
	/*
	 * ARRAY INITIALIZER expression
	 * The expression stack will have:
	 * 	IExpression.ARRAYINITIALIZER_EXPRESSION (for n times depending on number of expressions count)
	 * 	PROCESS_EXPRESSION
	 * 
	 * The value stack will have
	 * 	ARRAYINITIALIZER
	 *  type (either a string representing the type, or an IBeanProxyType representing the type).
	 * 		I.e. if array being created is int[][], the value pushed here will be int[]. This is because when created
	 * 		the array will wind up with int[expressioncount][] in the end.
	 * 	Integer (expression count)
	 * 
	 * Note: Imbedded Array Initializers works with this in that it will peek into the value stack two entries down
	 * to find the type of array it should be creating.
	 */
	private static final Integer ARRAYINITIALIZER = new Integer(IInternalExpressionConstants.ARRAY_INITIALIZER_EXPRESSION);
	private static final Integer ARRAYINITIALIZER_COUNT_0 = new Integer(0);	// Use in normal case of empty array. Save object creation.
	private static final Integer ARRAYINITIALIZER_COUNT_1 = new Integer(1);	// Use in normal case of one element array. Save object creation.
	private static final Integer ARRAYINITIALIZER_COUNT_2 = new Integer(2);	// Use in normal case of two element array. Save object creation.	
	
	/*
	 * CAST expression.
	 * The expression stack will have:
	 * 	IExpression.CAST_EXPRESSION
	 * 	PROCESS_EXPRESSION
	 * 
	 * The value stack will have:
	 * 	CAST
	 * 	type (either a string representing the type, or an IBeanProxyType representing the type).
	 */
	private static final Integer CAST = new Integer(IInternalExpressionConstants.CAST_EXPRESSION);

	/*
	 * CLASS INSTANCE CREATION expression.
	 * The expression stack will have:
	 *  IExpression.CLASSINSTANCECREATION_ARGUMENT (for 0 to n times depending on argument count)
	 *  PROCESS_EXPRESSION
	 * 
	 * The value stack will have
	 *  CLASSINSTANCECREATION
	 *  type (either a string representing the type, or an IBeanProxyType representing the type).
	 *  Integer(argument count) 
	 * 
	 * 
	 * Note: Array Initializer works with this in that it will peek into the value stack two entries down
	 * to find the type of array it should be creating.
	 */
	private static final Integer CLASSINSTANCECREATION = new Integer(IInternalExpressionConstants.CLASS_INSTANCE_CREATION_EXPRESSION);
	private static final Integer CLASS_INSTANCE_CREATION_ARGUMENTS_1 = new Integer(1);	// Use in normal case of one argument. Save object creation.
	private static final Integer CLASS_INSTANCE_CREATION_ARGUMENTS_0 = new Integer(0);	// Use in normal case of no arguments (default ctor). Save object creation.

	/*
	 * CONDITIONAL expression.
	 * Since this can cause skipping of expressions (e.g. if condition is false, then the true condition should not be evaluated),
	 * we need to have a process expression and process call to the other side for each expression so that it can
	 * determine if it should be ignored or not.
	 * 
	 * The expression stack will have:
	 * 	IExpression.CONDITIONAL_CONDITION
	 * 	PROCESS_EXPRESSION
	 * 	IExpression.CONDITIONAL_TRUE
	 * 	PROCESS_EXPRESSION
	 * 	IExpression.CONDITIONAL_FALSE
	 * 	PROCESS_EXPRESSION
	 * 
	 * The value stack will have:
	 * 	CONDITIONAL
	 * 	CONDITIONAL_CONDITION
	 * 	CONDITIONAL
	 * 	CONDITIONAL_TRUE
	 * 	CONDITIONAL
	 * 	CONDITIONAL_FALSE
	 * 
	 */
	private static final Integer CONDITIONAL = new Integer(IInternalExpressionConstants.CONDITIONAL_EXPRESSION);
	private static final Integer CONDITIONAL_TEST = new Integer(IExpressionConstants.CONDITIONAL_CONDITION);
	private static final Integer CONDITIONAL_TRUEEXP = new Integer(IExpressionConstants.CONDITIONAL_TRUE);
	private static final Integer CONDITIONAL_FALSEXP = new Integer(IExpressionConstants.CONDITIONAL_FALSE);
	
	/*
	 * PREFIX expression.
	 * The expression stack will have:
	 * 	IExpression.PREFIX_OPERAND
	 * 	PROCESS_EXPRESSION
	 * 
	 * The value stack will have:
	 * 	PREFIX
	 * 	operator (using Integer prefix operator constants defined here) 
	 */
	private static final Integer PREFIX = new Integer(IInternalExpressionConstants.PREFIX_EXPRESSION);
	private static final Integer[] PREFIX_OPERATORS;
	static {
		PREFIX_OPERATORS = new Integer[IExpressionConstants.PRE_MAX+1];
		PREFIX_OPERATORS[IExpressionConstants.PRE_PLUS] = new Integer(IExpressionConstants.PRE_PLUS);
		PREFIX_OPERATORS[IExpressionConstants.PRE_MINUS] = new Integer(IExpressionConstants.PRE_MINUS);
		PREFIX_OPERATORS[IExpressionConstants.PRE_COMPLEMENT] = new Integer(IExpressionConstants.PRE_COMPLEMENT);
		PREFIX_OPERATORS[IExpressionConstants.PRE_NOT] = new Integer(IExpressionConstants.PRE_NOT);
	}
	
	/*
	 * INFIX expression.
	 * Since two types of infix operators (conditional and AND or) can cause skipping of expressions (e.g. once
	 * conditional and sees a false expression, the rest of the expressions are skipped and should not be evaluated),
	 * we need to have a process expression and process call to the other side for each expression so that it can
	 * determine if it should be ignored or not.
	 * 
	 * The expression stack will have:
	 * 	IExpression.INFIX_LEFT
	 * 	PROCESS_EXPRESSION
	 * 	IExpression.INFIX_RIGHT
	 *  PROCESS_EXPRESSION
	 * 		(for 0 to n times depending upon extended count)
	 * 		IExpression.INFIX_EXTENDED 
	 * 		PROCESS_EXPRESSION
	 * 
	 * The value stack will have:
	 * 	INFIX
	 * 	operator (using Integer infix operator constants defined here)
	 *  IN_LEFT
	 * 		(for (extendedCount) times) This will cover the right one and all but last extended
	 * 		INFIX
	 * 		operator (using Integer infix operator constants defined here)
	 *  	IN_OTHER
	 * INFIX
	 * 	operator (using Integer infix operator constants defined here)
	 *  IN_LAST (this is covers either the right one if no extended, or the last extended)
	 */
	private static final Integer INFIX = new Integer(IInternalExpressionConstants.INFIX_EXPRESSION);
	private static final Integer IN_LEFT = new Integer(IInternalExpressionConstants.INFIX_LEFT_OPERAND);
	private static final Integer IN_OTHER= new Integer(IInternalExpressionConstants.INFIX_OTHER_OPERAND);
	private static final Integer IN_LAST = new Integer(IInternalExpressionConstants.INFIX_LAST_OPERAND);
	private static final Integer[] INFIX_OPERATORS;
	static {
		INFIX_OPERATORS = new Integer[IExpressionConstants.IN_MAX+1];
		INFIX_OPERATORS[IExpressionConstants.IN_AND] = new Integer(IExpressionConstants.IN_AND);
		INFIX_OPERATORS[IExpressionConstants.IN_CONDITIONAL_AND] = new Integer(IExpressionConstants.IN_CONDITIONAL_AND);
		INFIX_OPERATORS[IExpressionConstants.IN_CONDITIONAL_OR] = new Integer(IExpressionConstants.IN_CONDITIONAL_OR);
		INFIX_OPERATORS[IExpressionConstants.IN_DIVIDE] = new Integer(IExpressionConstants.IN_DIVIDE);
		INFIX_OPERATORS[IExpressionConstants.IN_EQUALS] = new Integer(IExpressionConstants.IN_EQUALS);
		INFIX_OPERATORS[IExpressionConstants.IN_GREATER] = new Integer(IExpressionConstants.IN_GREATER);
		INFIX_OPERATORS[IExpressionConstants.IN_GREATER_EQUALS] = new Integer(IExpressionConstants.IN_GREATER_EQUALS);
		INFIX_OPERATORS[IExpressionConstants.IN_LEFT_SHIFT] = new Integer(IExpressionConstants.IN_LEFT_SHIFT);
		INFIX_OPERATORS[IExpressionConstants.IN_LESS] = new Integer(IExpressionConstants.IN_LESS);
		INFIX_OPERATORS[IExpressionConstants.IN_LESS_EQUALS] = new Integer(IExpressionConstants.IN_LESS_EQUALS);
		INFIX_OPERATORS[IExpressionConstants.IN_MINUS] = new Integer(IExpressionConstants.IN_MINUS);
		INFIX_OPERATORS[IExpressionConstants.IN_NOT_EQUALS] = new Integer(IExpressionConstants.IN_NOT_EQUALS);
		INFIX_OPERATORS[IExpressionConstants.IN_OR] = new Integer(IExpressionConstants.IN_OR);
		INFIX_OPERATORS[IExpressionConstants.IN_PLUS] = new Integer(IExpressionConstants.IN_PLUS);
		INFIX_OPERATORS[IExpressionConstants.IN_REMAINDER] = new Integer(IExpressionConstants.IN_REMAINDER);
		INFIX_OPERATORS[IExpressionConstants.IN_RIGHT_SHIFT_SIGNED] = new Integer(IExpressionConstants.IN_RIGHT_SHIFT_SIGNED);
		INFIX_OPERATORS[IExpressionConstants.IN_RIGHT_SHIFT_UNSIGNED] = new Integer(IExpressionConstants.IN_RIGHT_SHIFT_UNSIGNED);
		INFIX_OPERATORS[IExpressionConstants.IN_TIMES] = new Integer(IExpressionConstants.IN_TIMES);
		INFIX_OPERATORS[IExpressionConstants.IN_XOR] = new Integer(IExpressionConstants.IN_XOR);
	}	
	
	/*
	 * INSTANCEOF expression.
	 * The expression stack will have:
	 * 	IExpression.INSTANCEOF_EXPRESSION
	 * 	PROCESS_EXPRESSION
	 * 
	 * The value stack will have:
	 * 	INSTANCEOF
	 * 	type (either a string representing the type, or an IBeanProxyType representing the type).
	 */
	private static final Integer INSTANCEOF = new Integer(IInternalExpressionConstants.INSTANCEOF_EXPRESSION);

	/*
	 * Field access expression.
	 * The expression stack will have:
	 * 	IExpression.FIELD_RECEIVER (if hasReceiver is true)
	 * 	PROCESS_EXPRESSION
	 * 
	 * The value stack will have:
	 * 	FIELDACCESS
	 * 	name (the name of the field)
	 *  Boolean (true if has receiver)
	 */
	private static final Integer FIELDACCESS = new Integer(IInternalExpressionConstants.FIELD_ACCESS_EXPRESSION);

	/*
	 * Method invocation expression.
	 * The expression stack will have:
	 * 	IExpression.METHOD_RECEIVER (if hasReceiver is true)
	 * 	IExpression.METHOD_ARGUMENT (0 to n times for how many arguments).
	 * 	PROCESS_EXPRESSION
	 * 
	 * The value stack will have:
	 * 	METHODINVOCATION
	 * 	name (the name of the method)
	 *  Boolean (true if has receiver)
	 *  argCount (the number of arguments).
	 */
	private static final Integer METHODINVOCATION = new Integer(IInternalExpressionConstants.METHOD_EXPRESSION);
	private static final Integer METHOD_ARGUMENTS_1 = new Integer(1);	// Use in normal case of one argument. Save object creation.
	private static final Integer METHOD_ARGUMENTS_0 = new Integer(0);	// Use in normal case of no arguments. Save object creation.
	
	
	/*
	 * Next valid for expression stack. This is kept as a stack also.
	 * As the expressions come in, the appropriate order (in reverse)
	 * of expression types will be pushed, and then popped as they 
	 * come in.
	 * 
	 * Since we can't have an array list of ints, will simulate the
	 * stack here.
	 */
	private int[] nextForExpressionStack = new int[30];
	private int nextForExpressionStackPos = -1;	// Position of top entry in stack.
	private static final int INVALID = -2;	// Mark that this expression is now invalid. (Goes into nextForExpressionStackSize)
	private String invalidMsg = null;	// Msg for being invalid if default msg not sufficient.
	
	private static final int PROCESS_EXPRESSION = Integer.MIN_VALUE;	// This is pushed onto the next expression stack, and went it is popped, then the expression is complete
	
	/**
	 * Check the for expression, and if legal, set to the next valid for expression type,
	 * if it can.
	 * 
	 * @param forExpression
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	protected final void checkForExpression(int forExpression) throws IllegalStateException {
		if (nextForExpressionStackPos != INVALID) {
			if (nextForExpressionStackPos == -1)
				if (forExpression == IExpressionConstants.ROOTEXPRESSION)
					return;	// valid. We are at the root (i.e. nothing is waiting).
				else
					;	// invalid. drop through
			else if (nextForExpressionStack[nextForExpressionStackPos--] == forExpression)
				return;	// Valid, the top expression matched.
		} else {
			String expMsg = invalidMsg != null ? MessageFormat.format(ProxyMessages.getString("Expression.InInvalidStateDueTo"), new Object[] {invalidMsg}) : ProxyMessages.getString("Expression.InInvalidState"); //$NON-NLS-1$ //$NON-NLS-2$
			throw new IllegalStateException(expMsg);
		}
		
		// If we got here, then invalid.
		nextForExpressionStackPos = INVALID;
		throw new IllegalStateException(ProxyMessages.getString("Expression.TypeSentInInvalidOrder")); //$NON-NLS-1$
	}
	
	/**
	 * Peek into the for expression stack to see if the top entry is the passed in value. It will
	 * not pop the stack nor throw any exceptions.
	 * 
	 * @param forExpression The top expression flag will be compared against this value.
	 * @return <code>true</code> if the top expression equals the parameter passed in.
	 * 
	 * @since 1.0.0
	 */
	protected final boolean peekForExpression(int forExpression) {
		if (nextForExpressionStackPos != INVALID) {
			if (nextForExpressionStackPos == -1)
				if (forExpression == IExpressionConstants.ROOTEXPRESSION)
					return true;	// valid. We are at the root (i.e. nothing is waiting).
				else
					;	// invalid. drop through
			else if (nextForExpressionStack[nextForExpressionStackPos] == forExpression)
				return true;	// Valid, the top expression matched.
		} 
		return false;
	}	
	
	/**
	 * Mark this expression as now invalid.
	 */
	protected final void markInvalid() {
		nextForExpressionStackPos = INVALID;
		controlStack.clear();
		closeProxy();
	}
	
	/**
	 * Mark this expression as now invalid, but supply a message to go with it.
	 * 
	 * @param msg
	 * 
	 * @since 1.0.0
	 */
	protected final void markInvalid(String msg) {
		invalidMsg = msg;
		markInvalid();
	}
	
	/*
	 * Check if the pending expression is ready for evaluation.
	 * It is complete if the next entry on the stack is a PROCESS_EXPRESSION
	 */
	private boolean expressionReady() {
		if (nextForExpressionStackPos >= 0 && nextForExpressionStack[nextForExpressionStackPos] == PROCESS_EXPRESSION) {
			checkForExpression(PROCESS_EXPRESSION);	// pop it
			return true;
		} else
			return false;
	}
	
	/*
	 * Push the next expression type.
	 */
	private void pushForExpression(int nextExpression) {
		if (++nextForExpressionStackPos >= nextForExpressionStack.length) {
			// Increase stack size.
			int[] newStack = new int[nextForExpressionStackPos*2];	// So room to grow without excessive allocations.
			System.arraycopy(nextForExpressionStack, 0, newStack, 0, nextForExpressionStack.length);
			nextForExpressionStack = newStack;
		}
		nextForExpressionStack[nextForExpressionStackPos] = nextExpression;
	}
	
	/*
	 * Check if expression is complete, and if it is, process it.
	 */
	private void processExpression() throws ThrowableProxy, NoExpressionValueException {
		while (expressionReady()) {
			try {
				// We've received all of the expressions for the expression, so process it.
				int expType = ((Integer) pop()).intValue();
				switch (expType) {
					case IInternalExpressionConstants.CAST_EXPRESSION:
						pushCastToProxy(pop());
						break;
					case IInternalExpressionConstants.INSTANCEOF_EXPRESSION:
						pushInstanceofToProxy(pop());
						break;
					case IInternalExpressionConstants.PREFIX_EXPRESSION:
						pushPrefixToProxy(((Integer)pop()).intValue());
						break;
					case IInternalExpressionConstants.INFIX_EXPRESSION:
						pushInfixToProxy(((Integer) pop()).intValue(), ((Integer) pop()).intValue());
						break;
					case IInternalExpressionConstants.ARRAY_ACCESS_EXPRESSION:
						pushArrayAccessToProxy(((Integer) pop()).intValue());
						break;
					case IInternalExpressionConstants.ARRAY_CREATION_EXPRESSION:
						pushArrayCreationToProxy(pop(), ((Integer) pop()).intValue());
						break;
					case IInternalExpressionConstants.ARRAY_INITIALIZER_EXPRESSION:
						pushArrayInitializerToProxy(pop(), ((Integer) pop()).intValue());
						break;
					case IInternalExpressionConstants.CLASS_INSTANCE_CREATION_EXPRESSION:
						pushClassInstanceCreationToProxy(pop(), ((Integer) pop()).intValue());
						break;
					case IInternalExpressionConstants.FIELD_ACCESS_EXPRESSION:
						pushFieldAccessToProxy((String) pop(), ((Boolean) pop()).booleanValue());
						break;
					case IInternalExpressionConstants.METHOD_EXPRESSION:
						pushMethodInvocationToProxy((String) pop(), ((Boolean) pop()).booleanValue(), ((Integer) pop()).intValue());
						break;
					case IInternalExpressionConstants.CONDITIONAL_EXPRESSION:
						pushConditionalToProxy(((Integer) pop()).intValue());
						break;
					default:
						internalProcessUnknownExpressionType(expType);
				}
			} catch (ThrowableProxy e) {
				markInvalid();
				throw e;
			} catch (NoExpressionValueException e) {
				markInvalid();
				throw e;
			} catch (RuntimeException e) {
				markInvalid();
				throw e;
			}
		}
	}
	
	private void internalProcessUnknownExpressionType(int expressionType) throws IllegalArgumentException {
		if (!processUnknownExpressionType(expressionType))
			throw new IllegalArgumentException();
	}

	/**
	 * An unknown expression type was found in the processing of expression stack. Subclasses can override
	 * to process new types of expressions. 
	 * <p>
	 * Overrides must return <code>true</code> if they processed the expression type. If they return <code>false</code>
	 * it means they didn't understand it either and we should do default processing for unknow type.
	 * @param expressionType
	 * @return <code>true</code> if type was processed, <code>false</code> if not known by subclass either.
	 * 
	 * @since 1.0.0
	 */
	protected boolean processUnknownExpressionType(int expressionType) {
		return false;
	}

	/**
	 * Create the expression.
	 * 
	 * @param registry
	 * 
	 * @since 1.0.0
	 */
	protected Expression(ProxyFactoryRegistry registry) {
		this.registry = registry;
		this.beanProxyFactory = this.registry.getBeanProxyFactory();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#invokeExpression()
	 */
	public final void invokeExpression() throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		try {
			checkForExpression(IExpressionConstants.ROOTEXPRESSION); // We are at the root.
			pushInvoke();
		} finally {
			markInvalid(); // Mark invalid so any new calls after this will fail.
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#getExpressionValue()
	 */
	public final IBeanProxy getExpressionValue() throws ThrowableProxy, NoExpressionValueException, IllegalStateException {
		try {
			checkForExpression(IExpressionConstants.ROOTEXPRESSION); // We are at the root.
			return pullProxyValue(); // Get the top value.
		} finally {
			markInvalid();	// Mark invalid so any new calls after this will fail.
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createArrayAccess(int, int)
	 */
	public final void createArrayAccess(int forExpression, int indexCount) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushForExpression(PROCESS_EXPRESSION);
			int i = indexCount;
			while (i-- > 0)
				pushForExpression(IExpressionConstants.ARRAYACCESS_INDEX);
			pushForExpression(IExpressionConstants.ARRAYACCESS_ARRAY);

			push(indexCount == 1 ? ARRAYACCESS_INDEX_1 : new Integer(indexCount));
			push(ARRAYACCESS);
			processExpression(); // See if previous expression is ready for processing.
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createArrayCreation(int, java.lang.String, int)
	 */
	public final void createArrayCreation(int forExpression, String type, int dimensionExpressionCount)
		throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushArrayCreation(type, dimensionExpressionCount);	// Push this onto the local stack to wait for completion.
			processExpression();	// See if previous expression is ready for processing.
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}		
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createArrayCreation(int, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy, int)
	 */
	public final void createArrayCreation(int forExpression, IBeanTypeProxy type, int dimensionExpressionCount)
		throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushArrayCreation(type, dimensionExpressionCount);	// Push this onto the local stack to wait for completion.
			processExpression();	// See if previous expression is ready for processing.
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}		
	}

	private void pushArrayCreation(Object type, int dimensionExpressionCount) {
		switch (dimensionExpressionCount) {
			case 0:
				push(ARRAY_CREATION_DIMENSION_0);
				break;
			case 1:
				push(ARRAY_CREATION_DIMENSION_1);
				break;
			default:
				push(new Integer(dimensionExpressionCount));
				break;
		}
		push(type);
		push(ARRAYCREATION);
		
		pushForExpression(PROCESS_EXPRESSION);
		if (dimensionExpressionCount == 0)
			pushForExpression(ARRAY_INITIALIZER);
		else {
			while(dimensionExpressionCount-- >0)
				pushForExpression(IExpressionConstants.ARRAYCREATION_DIMENSION);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createArrayInitializer(int)
	 */
	public final void createArrayInitializer(int expressionCount) throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		try {
			// This is special, we could be waiting for an array initializer or an array initializer expression.
			// We will peek to see what it is and handle it.
			if (peekForExpression(ARRAY_INITIALIZER))
				checkForExpression(ARRAY_INITIALIZER);
			else
				checkForExpression(IExpressionConstants.ARRAYINITIALIZER_EXPRESSION);
			
			Object arrayType = peek(2);	// Get the type from the value stack. Do before I mess up the stack.
			
			switch (expressionCount) {
				case 0:
					push(ARRAYINITIALIZER_COUNT_0);
					break;
				case 1:
					push(ARRAYINITIALIZER_COUNT_1);
					break;
				case 2:
					push(ARRAYINITIALIZER_COUNT_2);
					break;
				default:
					push(new Integer(expressionCount));
					break;
			}
			
			if (arrayType instanceof String) {
				// Need to remove the end set of "[]" to reduce by one dimension.
				String at = (String) arrayType;
				int i = at.lastIndexOf("[]"); //$NON-NLS-1$
				if (i == -1)
					throw new IllegalArgumentException(MessageFormat.format(ProxyMessages.getString("Expression.ArrayTypeNotAnArray"), new Object[] {arrayType})); //$NON-NLS-1$
				arrayType = at.substring(0, i);
			} else if (arrayType instanceof IArrayBeanTypeProxy) {
				arrayType = ((IArrayBeanTypeProxy) arrayType).getComponentType();
			} else
				throw new IllegalArgumentException(MessageFormat.format(ProxyMessages.getString("Expression.ArrayTypeNotAnArray"), new Object[] {arrayType})); //$NON-NLS-1$
			push(arrayType);
			push(ARRAYINITIALIZER);
			
			pushForExpression(PROCESS_EXPRESSION);
			while(expressionCount-->0)
				pushForExpression(IExpressionConstants.ARRAYINITIALIZER_EXPRESSION);
			
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}		
		
	}
 
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createCastExpression(int, java.lang.String)
	 * A cast expression has one nested expression.
	 */
	public final void createCastExpression(int forExpression, String type) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		pushCast(forExpression, type); // Push this onto the local stack to wait for completion.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createCastExpression(int, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy)
	 */
	public final void createCastExpression(int forExpression, IBeanTypeProxy type) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		pushCast(forExpression, type); // Push this onto the local stack to wait for completion.
	}
	
	/*
	 * Push for a cast.
	 */
	private void pushCast(int forExpression, Object type) throws ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			push(type);
			push(CAST);
			pushForExpression(PROCESS_EXPRESSION);	
			pushForExpression(IExpressionConstants.CAST_EXPRESSION);	// The next expression must be for the cast expression.
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}		
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createClassInstanceCreation(int, java.lang.String, int)
	 */
	public final void createClassInstanceCreation(int forExpression, String type, int argumentCount)
		throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		pushClassInstanceCreation(forExpression, type, argumentCount);	// Push this onto the local stack to wait for completion.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createClassInstanceCreation(int, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy, int)
	 */
	public final void createClassInstanceCreation(int forExpression, IBeanTypeProxy type, int argumentCount)
		throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		pushClassInstanceCreation(forExpression, type, argumentCount);	// Push this onto the local stack to wait for completion.
	}

	/*
	 * Push for a class instance creation
	 */
	private void pushClassInstanceCreation(int forExpression, Object type, int argumentCount) throws ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			switch (argumentCount) {
				case 0:
					push(CLASS_INSTANCE_CREATION_ARGUMENTS_0);
					break;
				case 1:
					push(CLASS_INSTANCE_CREATION_ARGUMENTS_1);
					break;
				default:
					push(new Integer(argumentCount));
					break;
			}
			push(type);
			push(CLASSINSTANCECREATION);
			
			pushForExpression(PROCESS_EXPRESSION);
			while(argumentCount-- >0)
					pushForExpression(IExpressionConstants.CLASSINSTANCECREATION_ARGUMENT);
			processExpression();	// See if previous expression is ready for processing.			
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}			
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createConditionalExpression(int)
	 */
	public final void createConditionalExpression(int forExpression) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushForExpression(PROCESS_EXPRESSION);
			pushForExpression(IExpressionConstants.CONDITIONAL_FALSE);
			pushForExpression(PROCESS_EXPRESSION);
			pushForExpression(IExpressionConstants.CONDITIONAL_TRUE);
			pushForExpression(PROCESS_EXPRESSION);
			pushForExpression(IExpressionConstants.CONDITIONAL_CONDITION);
			
			push(CONDITIONAL_FALSEXP);
			push(CONDITIONAL);
			push(CONDITIONAL_TRUEEXP);
			push(CONDITIONAL);
			push(CONDITIONAL_TEST);
			push(CONDITIONAL);
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createFieldAccess(int, java.lang.String, boolean)
	 */
	public final void createFieldAccess(int forExpression, String fieldName, boolean hasReceiver) throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			if (!hasReceiver)
				throw new IllegalArgumentException(ProxyMessages.getString("Expression.CannotHandleNoReceiveOnFieldAccess")); //$NON-NLS-1$
			
			push(hasReceiver ? Boolean.TRUE : Boolean.FALSE);	// We have a receiver
			push(fieldName);
			push(FIELDACCESS);
			
			pushForExpression(PROCESS_EXPRESSION);
			if (hasReceiver)
				pushForExpression(IExpressionConstants.FIELD_RECEIVER);
			processExpression();	// See if previous expression is ready for processing.
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createInfixExpression(int, int, int)
	 */
	public final void createInfixExpression(int forExpression, int operator, int extendedOperandCount) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			Integer inoperator = INFIX_OPERATORS[operator];
			push(IN_LAST);
			push(inoperator);
			push(INFIX);
			int i = extendedOperandCount;
			while(i-->0) {
				push(IN_OTHER);
				push(inoperator);
				push(INFIX);
			}
			push(IN_LEFT);
			push(inoperator);
			push(INFIX);
		
			i = extendedOperandCount;
			while(i-->0) {
				pushForExpression(PROCESS_EXPRESSION);			
				pushForExpression(IExpressionConstants.INFIX_EXTENDED);
			}
			pushForExpression(PROCESS_EXPRESSION);
			pushForExpression(IExpressionConstants.INFIX_RIGHT);
			pushForExpression(PROCESS_EXPRESSION);
			pushForExpression(IExpressionConstants.INFIX_LEFT);
			processExpression();	// See if previous expression is ready for processing.
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createInstanceofExpression(int, java.lang.String)
	 */
	public final void createInstanceofExpression(int forExpression, String type) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		pushInstanceof(forExpression, type);	// Push this onto the local stack to wait for completion.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createInstanceofExpression(int, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy)
	 */
	public final void createInstanceofExpression(int forExpression, IBeanTypeProxy type) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		pushInstanceof(forExpression, type);	// Push this onto the local stack to wait for completion.
	}
	
	/*
	 * Push for a cast.
	 */
	private void pushInstanceof(int forExpression, Object type) throws ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			push(type);
			push(INSTANCEOF);
			pushForExpression(PROCESS_EXPRESSION);	
			pushForExpression(IExpressionConstants.INSTANCEOF_VALUE);	// The next expression must be for the instance of expression.
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}			
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createMethodInvocation(int, java.lang.String, boolean, int)
	 */
	public final void createMethodInvocation(int forExpression, String name, boolean hasReceiver, int argumentCount)
		throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			if (!hasReceiver)
				throw new IllegalArgumentException(MessageFormat.format(ProxyMessages.getString("Expression.MethodsNeedReceiver"), new Object[] {name})); //$NON-NLS-1$

			switch (argumentCount) {
				case 0 :
					push(METHOD_ARGUMENTS_0);
					break;
				case 1 :
					push(METHOD_ARGUMENTS_1);
					break;
				default :
					push(new Integer(argumentCount));
					break;
			}
			push(hasReceiver ? Boolean.TRUE : Boolean.FALSE);
			push(name);
			push(METHODINVOCATION);

			pushForExpression(PROCESS_EXPRESSION);
			while (argumentCount-- > 0)
				pushForExpression(IExpressionConstants.METHOD_ARGUMENT);
			if (hasReceiver)
				pushForExpression(IExpressionConstants.METHOD_RECEIVER);
			processExpression(); // See if previous expression is ready for processing.
		} catch (ThrowableProxy e) {
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createPrefixExpression(int, int)
	 */
	public final void createPrefixExpression(int forExpression, int operator) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			push(PREFIX_OPERATORS[operator]);
			push(PREFIX);
			
			pushForExpression(PROCESS_EXPRESSION);
			pushForExpression(IExpressionConstants.PREFIX_OPERAND);
			processExpression();	// See if previous expression is ready for processing.
		} catch (ThrowableProxy e) {
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createNull(int)
	 */
	public final void createNull(int forExpression) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(null);
			processExpression();	// See if previous expression is ready for processing.
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createTypeLiteral(int, java.lang.String)
	 */
	public final void createTypeLiteral(int forExpression, String type) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushTypeLiteralToProxy(type);
			processExpression();	// See if previous expression is ready for processing.
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createTypeReceiver(java.lang.String)
	 */
	public final void createTypeReceiver(String type) throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		pushTypeReceiver(type);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createTypeReceiver(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy)
	 */
	public final void createTypeReceiver(IBeanTypeProxy type) throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		pushTypeReceiver(type);
	}

	/*
	 * Push for a type receiver.
	 * @param type
	 * 
	 * @since 1.0.0
	 */
	private void pushTypeReceiver(Object type) throws ThrowableProxy, NoExpressionValueException {
		try {
			// This is special because type receivers are only valid as the receiver for a field access or a method access.
			// Since each has a different forExpression we need to test for one or the other. It doesn't make any difference
			// which one it is, but it must be one or the other.
			if (peekForExpression(FIELD_RECEIVER))
				checkForExpression(FIELD_RECEIVER);
			else
				checkForExpression(METHOD_RECEIVER);
			
			pushTypeReceiverToProxy(type);
			processExpression();	// See if previous expression is ready for processing.
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}			
	}
	
	/*
	 * For all of the primitive types we will be creating a IBeanProxy for them. That is because that
	 * would be the expected result of the expression, and no need to get the other side involved.
	 */


	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createPrimitiveLiteral(int, boolean)
	 */
	public final void createPrimitiveLiteral(int forExpression, boolean value) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(beanProxyFactory.createBeanProxyWith(value));
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createPrimitiveLiteral(int, char)
	 */
	public final void createPrimitiveLiteral(int forExpression, char value) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(beanProxyFactory.createBeanProxyWith(value));
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createPrimitiveLiteral(int, byte)
	 */
	public final void createPrimitiveLiteral(int forExpression, byte value) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(beanProxyFactory.createBeanProxyWith(value));
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createPrimitiveLiteral(int, double)
	 */
	public final void createPrimitiveLiteral(int forExpression, double value) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(beanProxyFactory.createBeanProxyWith(value));
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createPrimitiveLiteral(int, float)
	 */
	public final void createPrimitiveLiteral(int forExpression, float value) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(beanProxyFactory.createBeanProxyWith(value));
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createPrimitiveLiteral(int, int)
	 */
	public final void createPrimitiveLiteral(int forExpression, int value) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(beanProxyFactory.createBeanProxyWith(value));
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createPrimitiveLiteral(int, long)
	 */
	public final void createPrimitiveLiteral(int forExpression, long value) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(beanProxyFactory.createBeanProxyWith(value));
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createPrimitiveLiteral(int, short)
	 */
	public final void createPrimitiveLiteral(int forExpression, short value) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(beanProxyFactory.createBeanProxyWith(value));
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createStringLiteral(int, java.lang.String)
	 */
	public final void createStringLiteral(int forExpression, String value) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(beanProxyFactory.createBeanProxyWith(value));
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IExpression#createProxyExpression(int, org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public final void createProxyExpression(int forExpression, IBeanProxy proxy) throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		try {
			checkForExpression(forExpression);
			pushToProxy(proxy);
			processExpression();
		} catch (ThrowableProxy e) {
			markInvalid();
			throw e;
		} catch (NoExpressionValueException e) {
			markInvalid();
			throw e;
		} catch (RuntimeException e) {
			markInvalid();
			throw e;
		}
	}
	

	/**
	 * Push this proxy to the other side. It will simply take the proxy and push it onto
	 * its evaluation stack. It will be treated as the result of an expression. It's just 
	 * that the expression was evaluatable on this side (since it is already a proxy).
	 * 
	 * @param proxy
	 * @throws ThrowableProxy
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushToProxy(IBeanProxy proxy) throws ThrowableProxy;
	
	/**
	 * Tell the other side we are complete. This is only called for invoked expressions,
	 * i.e. no return value.
	 * 
	 * @throws ThrowableProxy
	 * 
	 * @since 1.0.0
	 */
	protected abstract void closeProxy();
	
	/**
	 * Do invoke. This should simply make sure everything is done and throw any pending errors.
	 * 
	 * @throws ThrowableProxy
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushInvoke() throws ThrowableProxy, NoExpressionValueException;
	
	/**
	 * Pull the top expression value from the evaluation stack. It will also under
	 * the covers call closeProxy. 
	 * 
	 * @return The top level evaluation stack value.
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected abstract IBeanProxy pullProxyValue() throws ThrowableProxy, NoExpressionValueException;
	
	/**
	 * Push to proxy the cast expression. The expression to use will be on the top of its evaluation stack.
	 * The result of the cast expression will be placed onto the evaluation stack.
	 *  
	 * @param type Cast type. It may be either <code>String</code> (the value is the type to be searched for) or <code>IBeanTypeProxy</code> (it is of that type that the proxy is wrappering).
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushCastToProxy(Object type) throws ThrowableProxy, NoExpressionValueException;

	/**
	 * Push to proxy the instanceof expression. The expression to use will be on the top of its evaluation stack.
	 * The result of the instanceof expression will be placed onto the evaluation stack.
	 *  
	 * @param type Instanceof type. It may be either <code>String</code> (the value is the type to be searched for) or <code>IBeanTypeProxy</code> (it is of that type that the proxy is wrappering).
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushInstanceofToProxy(Object type) throws ThrowableProxy, NoExpressionValueException;
	
	/**
	 * Push to proxy the infix operation. This is called on the completion of each operand of the expression.
	 * So it will be called a minimum of two times.
	 * 
	 * @param operator The operator, the values are from IExpressionConstants infix operators.
	 * @param operandType The operand type. left, other, or last. The values are from the IInternalExpressionConstants infix operations.
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @see IExpressionConstants#IN_AND
	 * @see IInternalExpressionConstants#INFIX_LAST_OPERAND
	 * @since 1.0.0
	 */
	protected abstract void pushInfixToProxy(int operator, int operandType) throws ThrowableProxy, NoExpressionValueException;
	
	/**
	 * Push to proxy the prefix expression. The expression to use will be on top of its evaluation stack.
	 * The result of the prefix operation will be placed onto the evaluation stack.
	 * 
	 * @param operator The operator, the values are from IExpressionConstants prefix operators.
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @see IExpressionConstants#PRE_MINUS
	 * @since 1.0.0
	 */
	protected abstract void pushPrefixToProxy(int operator) throws ThrowableProxy, NoExpressionValueException;	
	
	/**
	 * Push to proxy the type literal string. The result of the type literal string will be placed onto the evaluation
	 * stack.
	 *  
	 * @param type 
	 * @throws ThrowableProxy
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushTypeLiteralToProxy(String type) throws ThrowableProxy;
	
	/**
	 * Push to proxy the array access. The result will be placed onto the evaluation stack.
	 * 
	 * @param indexCount
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushArrayAccessToProxy(int indexCount) throws ThrowableProxy, NoExpressionValueException;
	
	/**
	 * Push to proxy the array creation. The result will be placed onto the evaluation stack.
	 * @param type The array type. (must be an array type) It may be either <code>String</code> (the value is the type to be searched for) or <code>IBeanTypeProxy</code> (it is of that type that the proxy is wrappering).
	 * @param dimensionCount
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushArrayCreationToProxy(Object type, int dimensionCount) throws ThrowableProxy, NoExpressionValueException;
	
	/**
	 * Push to proxy the array initializer. The resulting array will be placed onto the evaluation stack.
	 * @param type The array type minus one dimension. (must be an array type) It may be either <code>String</code> (the value is the type to be searched for) or <code>IBeanTypeProxy</code> (it is of that type that the proxy is wrappering).
	 * @param expressionCount
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushArrayInitializerToProxy(Object type, int expressionCount) throws ThrowableProxy, NoExpressionValueException;
	
	/**
	 * Push to proxy the class instance creation. The resulting class instance will be placed onto the evaluation stack.
	 * 
	 * @param type Class type. It may be either <code>String</code> (the value is the type to be searched for) or <code>IBeanTypeProxy</code> (it is of that type that the proxy is wrappering).
	 * @param argumentCount The number of arguments.
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushClassInstanceCreationToProxy(Object type, int argumentCount) throws ThrowableProxy, NoExpressionValueException;
	
	/**
	 * Push to proxy the type receiver. The resulting class will be placed onto the evaluation stack, along with it also
	 * being the expression type.
	 * @param type Class type. It may be either <code>String</code> (the value is the type to be searched for) or <code>IBeanTypeProxy</code> (it is of that type that the proxy is wrappering).
	 * @throws ThrowableProxy
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushTypeReceiverToProxy(Object type) throws ThrowableProxy;

	/**
	 * Push to proxy the field access. The result value will be placed onto the evaluation stack.
	 * @param fieldName The name of the field.
	 * @param hasReceiver Has receiver flag.
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushFieldAccessToProxy(String fieldName, boolean hasReceiver) throws ThrowableProxy, NoExpressionValueException;
	
	/**
	 * Push to proxy the method invocation. The result value will be placed onto the evaluation stack.
	 * 
	 * @param methodName
	 * @param hasReceiver
	 * @param argCount
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected abstract void pushMethodInvocationToProxy(String methodName, boolean hasReceiver, int argCount) throws ThrowableProxy, NoExpressionValueException;
	
	/**
	 * Push to proxy the conditional expression. This will be called on each part of expression. The expression type
	 * will be the current part (e.g. test, true, false).
	 * 
	 * @param expressionType The expression type, one of IExpressionConstants.CONDITIONAL_* constants.
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @see IExpressionConstants#CONDITIONAL_CONDITION
	 * @since 1.0.0
	 */
	protected abstract void pushConditionalToProxy(int expressionType) throws ThrowableProxy, NoExpressionValueException;
}
