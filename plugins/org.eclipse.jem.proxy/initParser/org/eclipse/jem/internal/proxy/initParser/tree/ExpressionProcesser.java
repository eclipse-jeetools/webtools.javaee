/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl~v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ExpressionProcesser.java,v $
 *  $Revision: 1.4 $  $Date: 2004/06/04 23:26:02 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;

import java.lang.reflect.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.ArrayList;

import org.eclipse.jem.internal.proxy.initParser.EvaluationException;
import org.eclipse.jem.internal.proxy.initParser.MethodHelper;
import org.eclipse.jem.internal.proxy.initParser.tree.IExpressionConstants.NoExpressionValueException;
 
/**
 * Expression processing. This does the actual expression processing with the live objects.
 * It is meant to be subclassed only to provide additional expression types. All of the
 * current expressions cannot be overridden. This is because the stack is very sensitive to 
 * call order.
 * 
 * @since 1.0.0
 */
public class ExpressionProcesser {

	/**
	 * The expression result stack and the expression result type stack.
	 * The type stack is used to be expected type of the corresponding
	 * expression result. This is needed for converting to primitives
	 * and for finding correct method call from the argument types. In
	 * this case, it is not the true value, but the value expected, e.g.
	 * <code>Object getObject()</code> returns something of type Object.
	 * This needs to be maintained so that if it goes into another method
	 * we don't accidently return a more specific method instead of the
	 * one that takes Object as an argument. 
	 * 
	 * expressionStack has result of the expression.
	 * expressionTypeStack has the computed type of the expression i.e.
	 * the type that the expression returns, not the type of the value.
	 * These can be different because the expression (e.g. method) may
	 * return an Object, but the expression value will be some specific
	 * subclass. So the expressionTypeStack would have a <code>java.lang.Object.class</code>
	 * on it in that case.
	 * Note: if the expressionStack has a <code>null</code> on it, then the type stack
	 * may either have a specific type in it, or it may be <code>MethodHelper.NULL_TYPE</code>. It
	 * would be this if it was explicitly pushed in and not as the
	 * result of a computation. If the result of a computation, it would have the
	 * true value.
	 * Note: if the expressionStack has a <code>Void.type</code> on it, then that
	 * means the previous expression had no result. This is an error if trying to
	 * use the expression in another expression.
	 * 
	 * @see org.eclipse.jem.internal.proxy.initParser.MethodHelper#NULL_TYPE
	 */
	private ArrayList expressionStack = new ArrayList(10);
	private ArrayList expressionTypeStack = new ArrayList(10);
	
	/**
	 * Push the expression value and its expected type.
	 * @param o
	 * @param type
	 * 
	 * @since 1.0.0
	 */
	protected final void pushExpressionValue(Object o, Class type) {
		expressionStack.add(o);
		expressionTypeStack.add(type);
	}
	
	/**
	 * Pop just the expression value. It is imperitive that the expression type
	 * is popped immediately following. Separated the methods so that we
	 * don't need to create an array to return two values.
	 * 
	 * @return The value.
	 * @throws ThrowableProxy
	 * 
	 * @since 1.0.0
	 */
	protected final Object popExpression() throws NoExpressionValueException {
		try {
			return expressionStack.remove(expressionStack.size()-1);
		} catch (IndexOutOfBoundsException e) {
			throw new NoExpressionValueException();
		}
	}

	/**
	 * Get the expression at <code>fromTop</code> down from the top. This is
	 * need for when multi-operators happen and they are stored in reverse of 
	 * what is needed. They would normally be stored left to right, with the
	 * rightmost one on top. But they need to be processed left to right, so
	 * to get the left most one requires digging down in the stack.
	 * <p>
	 * When done, <code>popExpressions(int count)</code> must be called to
	 * clean them out since they were processed.
	 *  
	 * @param fromTop <code>1</code> is the top one, <code>2</code> is the next one down.
	 * @return The entry from the top that was requested.
	 * @throws NoExpressionValueException
	 * 
	 * @see IDEExpression#popExpressions(int)
	 * @since 1.0.0
	 */
	protected final Object getExpression(int fromTop) throws NoExpressionValueException {
		try {
			return expressionStack.get(expressionStack.size()-fromTop);
		} catch (IndexOutOfBoundsException e) {
			throw new NoExpressionValueException();
		}
	}
	
	/**
	 * Remove the top <code>count</code> items.
	 * 
	 * @param count
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected final void popExpressions(int count) throws NoExpressionValueException {
		try {
			int remove = expressionStack.size()-1;
			while (count-- > 0)
				expressionStack.remove(remove--);
		} catch (IndexOutOfBoundsException e) {
			throw new NoExpressionValueException();
		}
	}
	
	/**
	 * Pop just the expression type. It is imperitive that the expression type
	 * is popped immediately following popExpression. Separated the methods so that we
	 * don't need to create an array to return two values.
	 * <p>
	 * If the allowVoid is false and type is void, then a NoExpressionValueException will be thrown.
	 * This is for the case where the expression was trying to be used in a different
	 * expression. This will be set to void only on expressions that return no value (only
	 * method's do this for now).
	 * 
	 * @param allowVoid Allow void types if <code>true</code>
	 * @return The type.
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * @since 1.0.0
	 */
	protected final Class popExpressionType(boolean allowVoid) throws NoExpressionValueException {
		try {
			Class result = (Class) expressionTypeStack.remove(expressionTypeStack.size()-1);
			if (!allowVoid && result == Void.TYPE)
				throw new NoExpressionValueException();
			return result;
				
		} catch (IndexOutOfBoundsException e) {
			throw new NoExpressionValueException();
		}
	}

	/**
	 * Get the expression type at <code>fromTop</code> down from the top. This is
	 * need for when multi-operators happen and they are stored in reverse of 
	 * what is needed. They would normally be stored left to right, with the
	 * rightmost one on top. But they need to be processed left to right, so
	 * to get the left most one requires digging down in the stack.
	 * <p>
	 * When done, <code>popExpressionTypes(int count)</code> must be called to
	 * clean them out since they were processed.

	 * @param fromTop <code>1</code> is the top one, <code>2</code> is the next one down.
	 * @param allowVoid Allow void types if <code>true</code>
	 * @return The type from the top that was requested.
	 * @throws ThrowableProxy
	 * @throws NoExpressionValueException
	 * 
	 * @see IDEExpression#popExpressionTypes(int)
	 * @since 1.0.0
	 */
	protected final Class getExpressionType(int fromTop, boolean allowVoid) throws NoExpressionValueException {
		try {
			Class result = (Class) expressionTypeStack.get(expressionTypeStack.size()-fromTop);
			if (!allowVoid && result == Void.TYPE)
				throw new NoExpressionValueException();
			return result;
		} catch (IndexOutOfBoundsException e) {
			throw new NoExpressionValueException();
		}
	}
	
	/**
	 * Remove the top <code>count</code> items.
	 * 
	 * @param count
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	protected final void popExpressionTypes(int count) throws NoExpressionValueException {
		try {
			int remove = expressionTypeStack.size()-1;
			while (count-- > 0)
				expressionTypeStack.remove(remove--);
		} catch (IndexOutOfBoundsException e) {
			throw new NoExpressionValueException();
		}
	}
	
	/**
	 * Flag indicating expression should be ignored and not processed.
	 * This happens because of few cases, like conditional and, that
	 * if one returns false, the rest of the expressions in that conditional and
	 * expression should be ignored and not processed.
	 * <p>
	 * It is an int so that those expressions that can initiate an ignore can 
	 * know if it is thiers or not. Only when it decrements to zero will ignore
	 * be over. Those expressions that can start an ignore must increment the
	 * ignore counter if the ignore counter is on, but ignore the expression,
	 * and decrement it when they are complete.
	 * <p>
	 * All of the pushTo...Proxy methods must test this for this to work correctly.
	 */
	protected int ignoreExpression = 0;	
	
	/**
	 * Create the IDEExpression
	 * @param registry
	 * 
	 * @since 1.0.0
	 */
	public ExpressionProcesser() {
	}
	
	/**
	 * Close the exception processing
	 * 
	 * @since 1.0.0
	 */
	public final void close() {
		expressionStack.clear();
		expressionTypeStack.clear();
	}

	/**
	 * Pull the value. The value will be placed into the array passed in.
	 * It will be stored as value[0] = value value[1] = valuetype(Class).
	 * 
	 * @param value The value array to store the value and type into.
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public final void pullValue(Object[] value) throws NoExpressionValueException {
		value[0] = popExpression();
		value[1] = popExpressionType(false);
		close();
	}
	
	/**
	 * Push the expression (just a value) onto the stack.
	 * 
	 * @param o
	 * @param t
	 * 
	 * @since 1.0.0
	 */
	public final void pushExpression(Object o, Class t) {
		if (ignoreExpression>0)
			return;
		pushExpressionValue(o, t);
	}
	
	/**
	 * Push a cast onto stack. The type passed in is either a String (with classname to cast to) or the
	 * type to cast to.
	 * @param type To cast to. If <code>String</code> then convert to type (using something like <code>Class.forName()</code>) or it is a Class
	 * @throws NoExpressionValueException
	 * @throws ClassCastException
	 * 
	 * @since 1.0.0
	 */
	public final void pushCast(Class type) throws NoExpressionValueException, ClassCastException {
		if (ignoreExpression>0)
			return;
		
		Object exp = popExpression();
		Class exptype = popExpressionType(false);
		
		pushExpressionValue(castBean(type, exp, exptype), type);
	}
	
	/**
	 * Cast a bean into the return type. If the return type is not primitive, then
	 * the bean is left alone, however it is checked to be an instance of
	 * the return type. If the return type is primitive, then the
	 * correct primitive wrapper is created from the bean (bean must be a number or character or boolean primitve so
	 * that cast will work).
	 * <p>
	 * However if can't be cast for primitive or if not an instance of the
	 * returntype for objects, a ClassCastException will be raised.
	 * 
	 * @param returnType
	 * @param bean
	 * @param beanType The type that bean is supposed to be (e.g. even though it is a Number, it actually represents a primitive).
	 * @return The cast bean (either to the appropriate primitive wrapper type or bean)
	 * 
	 * @throws ClassCastException
	 * @since 1.0.0
	 */
	protected final Object castBean(Class returnType, Object bean, Class beanType) throws ClassCastException {
		// Cast uses true value and true class of bean, not expected type (i.e. not beanType).
		if (bean == null)
			if (!returnType.isPrimitive())
				return bean;	// bean is null, and return type is not primitive, so this is a valid cast.
			else 
				throwClassCast(returnType, bean);
		else if (returnType.equals(bean.getClass()))
			return bean;	// They are already the same.
		else if (!returnType.isPrimitive()) {
			if (!beanType.isPrimitive() && returnType.isInstance(bean))
				return bean;
			else
				throwClassCast(returnType, bean);	// Either bean type was wrappering primitive or not instanceof returntype.
		} else {
			if (!beanType.isPrimitive())
				throwClassCast(returnType, bean);	// bean type was not wrappering a primitive. Can't cast to primitive.
			// It is return type of primitive. Now convert to correct primitive.
			if (returnType == Boolean.TYPE)
				if (bean instanceof Boolean)
					return bean;
				else
					throwClassCast(returnType, bean);
			else {
				if (bean instanceof Number) {
					if (returnType == Integer.TYPE)
						if (bean instanceof Integer)
							return bean;
						else
							return new Integer(((Number) bean).intValue());
					else if (returnType == Byte.TYPE)
						if (bean instanceof Byte)
							return bean;
						else
							return new Byte(((Number) bean).byteValue());
					else if (returnType == Character.TYPE)
						if (bean instanceof Character)
							return bean;
						else
							return new Character((char) ((Number) bean).intValue());
					else if (returnType == Double.TYPE)
						if (bean instanceof Double)
							return bean;
						else
							return new Double(((Number) bean).doubleValue());
					else if (returnType == Float.TYPE)
						if (bean instanceof Float)
							return bean;
						else
							return new Float(((Number) bean).floatValue());
					else if (returnType == Long.TYPE)
						if (bean instanceof Long)
							return bean;
						else
							return new Long(((Number) bean).longValue());
					else if (returnType == Short.TYPE)
						if (bean instanceof Short)
							return bean;
						else
							return new Short(((Number) bean).shortValue());	
					else
						throwClassCast(returnType, bean);
				} else if (bean instanceof Character) {
					if (returnType == Character.TYPE)
						return bean;
					else if (returnType == Integer.TYPE)
						return new Integer(((Character) bean).charValue());
					else if (returnType == Byte.TYPE)
						return new Byte((byte) ((Character) bean).charValue());
					else if (returnType == Double.TYPE)
						return new Double((double) ((Character) bean).charValue());
					else if (returnType == Float.TYPE)
						return new Float((float) ((Character) bean).charValue());
					else if (returnType == Long.TYPE)
						return new Long((long) ((Character) bean).charValue());
					else if (returnType == Short.TYPE)
						return new Short((short) ((Character) bean).charValue());	
					else
						throwClassCast(returnType, bean);
				} else
					throwClassCast(returnType, bean);
			}
			
		}
		return null;	// It should never get here;
	}
	
	private void throwClassCast(Class returnType, Object bean) throws ClassCastException {
		throw new ClassCastException(MessageFormat.format(InitparserTreeMessages.getString("ExpressionProcesser.CannotCastXToY_EXC_"), new Object[] {bean != null ? bean.getClass().getName() : null, returnType.getName()})); //$NON-NLS-1$
	}

	/**
	 * Return the primitive type that the wrapper bean represents (i.e. Boolean instance returns Boolean.TYPE) 
	 * @param bean
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final Class getPrimitiveType(Object bean) {
		if (bean instanceof Boolean)
			return Boolean.TYPE;
		else if (bean instanceof Integer)
			return Integer.TYPE;
		else if (bean instanceof Byte)
			return Byte.TYPE;
		else if (bean instanceof Character)
			return Character.TYPE;
		else if (bean instanceof Double)
			return Double.TYPE;
		else if (bean instanceof Float)
			return Float.TYPE;
		else if (bean instanceof Long)
			return Long.TYPE;
		else if (bean instanceof Short)
			return Short.TYPE;
		else
			throw new IllegalArgumentException(bean != null ? bean.getClass().getName() : "null"); //$NON-NLS-1$
	}
	

	/**
	 * Push the instanceof expression.  The type passed in is either a String (with classname to test against) or the
	 * type to test against.
	 * @param type To test against.
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public final void pushInstanceof(Class type) throws NoExpressionValueException {
		if (ignoreExpression>0)
			return;
		
		Object exp = popExpression();
		Class exptype = popExpressionType(false);
		pushExpressionValue(isInstance(type, exp, exptype) ? Boolean.TRUE : Boolean.FALSE, Boolean.TYPE);
	}
	
	/**
	 * Test if instance of. It will make sure that primitive to non-primitive is not permitted.
	 * This is a true instance of, which means null IS NOT AN instance of any type. This is
	 * different then assignable from, in that case null can be assigned to any class type.
	 * 
	 * @param type
	 * @param bean
	 * @param beanType
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final boolean isInstance(Class type, Object bean, Class beanType) {
		if (type.isPrimitive())
			return beanType.isPrimitive() && type == beanType;	// Can't use isInstance because for a primitive type isInstance returns false.
		else 
			return type.isInstance(bean);
	}

	private static final String[] PRE_OPER_TO_STRING;
	static {
		PRE_OPER_TO_STRING = new String[IExpressionConstants.PRE_MAX+1];
		PRE_OPER_TO_STRING[IExpressionConstants.PRE_PLUS] = "+"; //$NON-NLS-1$
		PRE_OPER_TO_STRING[IExpressionConstants.PRE_MINUS] = "-"; //$NON-NLS-1$
		PRE_OPER_TO_STRING[IExpressionConstants.PRE_COMPLEMENT] = "~"; //$NON-NLS-1$
		PRE_OPER_TO_STRING[IExpressionConstants.PRE_NOT] = "!"; //$NON-NLS-1$
	}
	
	/**
	 * Push prefix expression.
	 * @param operator The operator from IExpressionConstants
	 * @throws NoExpressionValueException
	 * 
	 * @see IExpressionConstants#PRE_MINUS
	 * @since 1.0.0
	 */
	public final void pushPrefix(int operator) throws NoExpressionValueException {
		if (ignoreExpression>0)
			return;
		
		if (operator == IExpressionConstants.PRE_PLUS)
			return;	// Do nothing. "+" doesn't affect the result of the current top expression.
		
		Object exp = popExpression();
		Class exptype = popExpressionType(false);
		if (!exptype.isPrimitive())
			throwInvalidPrefix(operator, exp);
		
		int primTypeEnum = getEnumForPrimitive(exptype);
		switch (operator) {
			case IExpressionConstants.PRE_MINUS:
				switch (primTypeEnum) {
					case BOOLEAN:
						throwInvalidPrefix(operator, exp);						
					case BYTE:
						exp = new Integer(-((Number) exp).byteValue());
						break;
					case CHAR:
						exp = new Integer(-((Character) exp).charValue());
						break;
					case DOUBLE:
						exp = new Double(-((Number) exp).doubleValue());
						break;
					case FLOAT:
						exp = new Float(-((Number) exp).floatValue());
						break;
					case INT:
						exp = new Integer(-((Number) exp).intValue());
						break;
					case LONG:
						exp = new Long(-((Number) exp).longValue());
						break;
					case SHORT:
						exp = new Integer(-((Number) exp).shortValue());
						break;
				}
				exptype = getPrimitiveType(exp);	// It can actually change the type.				
				break;
				
			case IExpressionConstants.PRE_COMPLEMENT:
				switch (primTypeEnum) {
					case BOOLEAN:
					case DOUBLE:
					case FLOAT:
						throwInvalidPrefix(operator, exp);						
					case BYTE:
						exp = new Integer(~((Number) exp).byteValue());
						break;
					case CHAR:
						exp = new Integer(~((Character) exp).charValue());
						break;
					case INT:
						exp = new Integer(~((Number) exp).intValue());
						break;
					case LONG:
						exp = new Long(~((Number) exp).longValue());
						break;
					case SHORT:
						exp = new Integer(~((Number) exp).shortValue());
						break;
				}
				exptype = getPrimitiveType(exp);	// It can actually change the type.
				break;
			case IExpressionConstants.PRE_NOT:
				switch (primTypeEnum) {
					case BOOLEAN:
						exp = !((Boolean) exp).booleanValue() ? Boolean.TRUE : Boolean.FALSE;
						break;
					case BYTE:
					case CHAR:
					case DOUBLE:
					case FLOAT:
					case INT:
					case LONG:
					case SHORT:
						throwInvalidPrefix(operator, exp);						
				}				
				break;
			}
		
		pushExpressionValue(exp, exptype);	// Push the result back on the stack.
	}

	/**
	 * The primitive enums. 
	 * NOTE: Their order must not changed. They are in order of permitted widening.
	 * 
	 */
	protected static final int
		BOOLEAN = 0,
		BYTE = 1,
		SHORT = 2,	
		CHAR = 3,
		INT = 4,
		LONG = 5,
		FLOAT = 6,
		DOUBLE = 7;
		
		
	
	/**
	 * Get the enum constant for the type of primitive passed in.
	 * @param primitiveType
	 * @return
	 * 
	 * @see ExpressionProcesser#BOOLEAN
	 * @since 1.0.0
	 */
	protected final int getEnumForPrimitive(Class primitiveType) {
		if (primitiveType == Boolean.TYPE)
			return BOOLEAN;
		else if (primitiveType == Integer.TYPE)
			return INT;
		else if (primitiveType == Byte.TYPE)
			return BYTE;
		else if (primitiveType == Character.TYPE)
			return CHAR;
		else if (primitiveType == Double.TYPE)
			return DOUBLE;
		else if (primitiveType == Float.TYPE)
			return FLOAT;
		else if (primitiveType == Long.TYPE)
			return LONG;
		else if (primitiveType == Short.TYPE)
			return SHORT;
		else
			throw new IllegalArgumentException();
	}
	
	private void throwInvalidPrefix(int operator, Object exp) throws IllegalArgumentException {
		throw new IllegalArgumentException(MessageFormat.format(InitparserTreeMessages.getString("ExpressionProcesser.InvalidOperandOfPrefixOperator_EXC_"), new Object[] {exp != null ? exp.toString() : null, PRE_OPER_TO_STRING[operator]})); //$NON-NLS-1$
	}

	private static final String[] IN_OPER_TO_STRING;
	static {
		IN_OPER_TO_STRING = new String[IExpressionConstants.IN_MAX+1];
		IN_OPER_TO_STRING[IExpressionConstants.IN_AND] = "&"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_CONDITIONAL_AND] = "&&"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_CONDITIONAL_OR] = "||"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_DIVIDE] = "/"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_EQUALS] = "=="; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_GREATER] = ">"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_GREATER_EQUALS] = ">="; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_LEFT_SHIFT] = "<<"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_LESS] = "<"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_LESS_EQUALS] = "<="; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_MINUS] = "-"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_NOT_EQUALS] = "!="; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_OR] = "|"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_PLUS] = "+"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_REMAINDER] = "%"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_RIGHT_SHIFT_SIGNED] = ">>"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_RIGHT_SHIFT_UNSIGNED] = ">>>"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_TIMES] = "*"; //$NON-NLS-1$
		IN_OPER_TO_STRING[IExpressionConstants.IN_XOR] = "^"; //$NON-NLS-1$
	}
		
	
	/**
	 * Push the infix expression onto the stack.
	 * @param operator
	 * @param operandType The operator type from IExpressionConstants.IN_*
	 * @throws NoExpressionValueException
	 * 
	 * @see IExpressionConstants#IN_AND
	 * @since 1.0.0
	 */
	public final void pushInfix(int operator, int operandType) throws NoExpressionValueException {
		boolean wasIgnoring = ignoreExpression>0;
		if (wasIgnoring)
			if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
				ignoreExpression++;	// Increment it so that entire expression is ignored because we already are in an ignore.
			else if (operandType == IInternalExpressionConstants.INFIX_LAST_OPERAND)
				ignoreExpression--;	// Decrement it because we have reached the end.
		if (ignoreExpression>0)
			return;	// We are still ignoring.
		
		if (wasIgnoring && operandType == IInternalExpressionConstants.INFIX_LAST_OPERAND)
			return;	// We've received the last operand but we were ignoring, but the value of the entire expression is still the top stack value.
		
		Object right = null;
		Class rightType = null;
		if (operandType != IInternalExpressionConstants.INFIX_LEFT_OPERAND) {
			// We are not the left operand, so the stack has the right on the top, followed by the left.
			right = popExpression();
			rightType = popExpressionType(false);
		} 
		
		Object value = popExpression();
		Class valueType = popExpressionType(false);

		switch (operator) {
			case IExpressionConstants.IN_AND:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidBitType(valueType, IExpressionConstants.IN_AND);
				testValidBitType(rightType, IExpressionConstants.IN_AND);
				if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) & getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it is int. (even two shorts together produce an int).
					value = new Integer(getInt(value) & getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			case IExpressionConstants.IN_CONDITIONAL_AND:
				// This is tricky.
				// First if this is left type, then just continue.
				// Else if this other or last, then need to make it the new value.
				if (operandType != IInternalExpressionConstants.INFIX_LEFT_OPERAND) {
					value = right;
					valueType = rightType;
				}
					
				//If the value is now false, we need to ignore the rest.
				if (valueType != Boolean.TYPE)
					throwInvalidInfix(operator, value);
				if (!((Boolean) value).booleanValue() && operandType != IInternalExpressionConstants.INFIX_LAST_OPERAND)
					++ignoreExpression;	// Start ignoring since current value is now false.
				break;
			case IExpressionConstants.IN_CONDITIONAL_OR:
				// This is tricky.
				// First if this is left type, then just continue.
				// Else if this other or last, then need to make it the new value.
				if (operandType != IInternalExpressionConstants.INFIX_LEFT_OPERAND) {
					value = right;
					valueType = rightType;
				}
				
				//If the value is now true, we need to ignore the rest.
				if (valueType != Boolean.TYPE)
					throwInvalidInfix(operator, value);
				if (((Boolean) value).booleanValue() && operandType != IInternalExpressionConstants.INFIX_LAST_OPERAND)
					++ignoreExpression;	// Start ignoring since current value is now true.
				break;
			case IExpressionConstants.IN_DIVIDE:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidArithmeticType(valueType, IExpressionConstants.IN_DIVIDE);
				testValidArithmeticType(rightType, IExpressionConstants.IN_DIVIDE);
				if (valueType == Double.TYPE || rightType == Double.TYPE) {
					// If either side is double, the result will be double.
					value = new Double(getDouble(value) / getDouble(right));
					valueType = Double.TYPE;
				} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
					// If either side is float, the result will be float.
					value = new Float(getFloat(value) / getFloat(right));
					valueType = Float.TYPE;
				} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) / getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it will result in an int, even if both sides are short.
					value = new Integer(getInt(value) / getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			case IExpressionConstants.IN_EQUALS:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				// We should never get extended operator for this, but we'll ignore the possibility.
				if (valueType.isPrimitive() && rightType.isPrimitive()) {
					// Primitives require more testing than just ==. boolean primitives 
					if (valueType == Boolean.TYPE || rightType == Boolean.TYPE) {
						// If either side is a boolean, then the other side needs to be boolean for it to even try to be true.
						if (valueType != Boolean.TYPE || valueType != Boolean.TYPE)
							value = Boolean.FALSE;
						else
							value = (((Boolean) value).booleanValue() == ((Boolean) right).booleanValue()) ? Boolean.TRUE : Boolean.FALSE;
					} else {
						// Now do number tests since not boolean primitive, only numbers are left
						if (valueType == Double.TYPE || rightType == Double.TYPE) {
							// If either side is double, compare as double.
							value = (getDouble(value) == getDouble(right)) ? Boolean.TRUE : Boolean.FALSE;
						} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
							// If either side is float, compare as float.
							value = (getFloat(value) == getFloat(right)) ? Boolean.TRUE : Boolean.FALSE;
						} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
							// If either side is long, the compare as long.
							value = (getLong(value) == getLong(right)) ? Boolean.TRUE : Boolean.FALSE;
						} else {
							// Else it will compare as int, even if both sides are short.
							value = (getInt(value) == getInt(right)) ? Boolean.TRUE : Boolean.FALSE;
						}
					}
				} else if (valueType.isPrimitive() || rightType.isPrimitive())
					value = Boolean.FALSE;	// Can't be true if one side prim and the other isn't
				else {
					// Just do object ==
					value = (value == right) ? Boolean.TRUE : Boolean.FALSE;
				}
				valueType = Boolean.TYPE;	// We know result will be a boolean.
				break;
			case IExpressionConstants.IN_GREATER:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidArithmeticType(valueType, IExpressionConstants.IN_GREATER);
				testValidArithmeticType(rightType, IExpressionConstants.IN_GREATER);
				if (valueType == Double.TYPE || rightType == Double.TYPE) {
					// If either side is double, compare will be double.
					value = (getDouble(value) > getDouble(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
					// If either side is float, compare will be float.
					value = (getFloat(value) > getFloat(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, compare will be long.
					value = (getLong(value) > getLong(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else {
					// Else compare will be int, even if both sides are short.
					value = (getInt(value) > getInt(right)) ? Boolean.TRUE : Boolean.FALSE;
				}
				valueType = Boolean.TYPE;	// We know result will be a boolean.
				break;
			case IExpressionConstants.IN_GREATER_EQUALS:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidArithmeticType(valueType, IExpressionConstants.IN_GREATER_EQUALS);
				testValidArithmeticType(rightType, IExpressionConstants.IN_GREATER_EQUALS);
				if (valueType == Double.TYPE || rightType == Double.TYPE) {
					// If either side is double, compare will be double.
					value = (getDouble(value) >= getDouble(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
					// If either side is float, compare will be float.
					value = (getFloat(value) >= getFloat(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, compare will be long.
					value = (getLong(value) >= getLong(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else {
					// Else compare will be int, even if both sides are short.
					value = (getInt(value) >= getInt(right)) ? Boolean.TRUE : Boolean.FALSE;
				}
				valueType = Boolean.TYPE;	// We know result will be a boolean.
				break;
			case IExpressionConstants.IN_LEFT_SHIFT:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidBitType(valueType, IExpressionConstants.IN_LEFT_SHIFT);
				testValidBitType(rightType, IExpressionConstants.IN_LEFT_SHIFT);
				if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) << getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it is int. (even two shorts together produce an int).
					value = new Integer(getInt(value) << getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			case IExpressionConstants.IN_LESS:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidArithmeticType(valueType, IExpressionConstants.IN_LESS);
				testValidArithmeticType(rightType, IExpressionConstants.IN_LESS);
				if (valueType == Double.TYPE || rightType == Double.TYPE) {
					// If either side is double, compare will be double.
					value = (getDouble(value) < getDouble(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
					// If either side is float, compare will be float.
					value = (getFloat(value) < getFloat(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, compare will be long.
					value = (getLong(value) < getLong(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else {
					// Else compare will be int, even if both sides are short.
					value = (getInt(value) < getInt(right)) ? Boolean.TRUE : Boolean.FALSE;
				}
				valueType = Boolean.TYPE;	// We know result will be a boolean.
				break;
			case IExpressionConstants.IN_LESS_EQUALS:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidArithmeticType(valueType, IExpressionConstants.IN_LESS_EQUALS);
				testValidArithmeticType(rightType, IExpressionConstants.IN_LESS_EQUALS);
				if (valueType == Double.TYPE || rightType == Double.TYPE) {
					// If either side is double, compare will be double.
					value = (getDouble(value) <= getDouble(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
					// If either side is float, compare will be float.
					value = (getFloat(value) <= getFloat(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, compare will be long.
					value = (getLong(value) <= getLong(right)) ? Boolean.TRUE : Boolean.FALSE;
				} else {
					// Else compare will be int, even if both sides are short.
					value = (getInt(value) <= getInt(right)) ? Boolean.TRUE : Boolean.FALSE;
				}
				valueType = Boolean.TYPE;	// We know result will be a boolean.
				break;
			case IExpressionConstants.IN_MINUS:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidArithmeticType(valueType, IExpressionConstants.IN_MINUS);
				testValidArithmeticType(rightType, IExpressionConstants.IN_MINUS);
				if (valueType == Double.TYPE || rightType == Double.TYPE) {
					// If either side is double, the result will be double.
					value = new Double(getDouble(value) - getDouble(right));
					valueType = Double.TYPE;
				} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
					// If either side is float, the result will be float.
					value = new Float(getFloat(value) - getFloat(right));
					valueType = Float.TYPE;
				} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) - getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it will result in an int, even if both sides are short.
					value = new Integer(getInt(value) - getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			case IExpressionConstants.IN_NOT_EQUALS:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				// We should never get extended operator for this, but we'll ignore the possibility.
				if (valueType.isPrimitive() && rightType.isPrimitive()) {
					// Primitives require more testing than just ==. boolean primitives 
					if (valueType == Boolean.TYPE || rightType == Boolean.TYPE) {
						// If either side is a boolean, then the other side needs to be boolean for it to even try to be true.
						if (valueType != Boolean.TYPE || valueType != Boolean.TYPE)
							value = Boolean.TRUE;
						else
							value = (((Boolean) value).booleanValue() != ((Boolean) right).booleanValue()) ? Boolean.TRUE : Boolean.FALSE;
					} else {
						// Now do number tests since not boolean primitive, only numbers are left
						if (valueType == Double.TYPE || rightType == Double.TYPE) {
							// If either side is double, compare as double.
							value = (getDouble(value) != getDouble(right)) ? Boolean.TRUE : Boolean.FALSE;
						} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
							// If either side is float, compare as float.
							value = (getFloat(value) != getFloat(right)) ? Boolean.TRUE : Boolean.FALSE;
						} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
							// If either side is long, the compare as long.
							value = (getLong(value) != getLong(right)) ? Boolean.TRUE : Boolean.FALSE;
						} else {
							// Else it will compare as int, even if both sides are short.
							value = (getInt(value) != getInt(right)) ? Boolean.TRUE : Boolean.FALSE;
						}
					}
				} else if (valueType.isPrimitive() || rightType.isPrimitive())
					value = Boolean.TRUE;	// Must be true if one side prim and the other isn't
				else {
					// Just do object !=
					value = (value != right) ? Boolean.TRUE : Boolean.FALSE;
				}
				valueType = Boolean.TYPE;	// We know result will be a boolean.
				break;
			case IExpressionConstants.IN_OR:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidBitType(valueType, IExpressionConstants.IN_OR);
				testValidBitType(rightType, IExpressionConstants.IN_OR);
				if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) | getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it is int. (even two shorts together produce an int).
					value = new Integer(getInt(value) | getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			case IExpressionConstants.IN_PLUS:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND) {
					if (valueType == String.class) {
						// Special. left argument is a string, so we want to store a string buffer instead
						// since we know we will be appending to it. 
						value = new StringBuffer((String) value);
					}	
					break;	// Do nothing with first operand
				}
				
				testValidPlusType(valueType, rightType);
				if (valueType == String.class || rightType == String.class) {
					// Special we have a string on one side. Need to do it as strings instead.
					// We are going to be tricky in that we will store a StringBuffer on the stack (if not last operand)
					// but call it a string.
					StringBuffer sb = null;
					if (valueType == String.class) {
						sb = (StringBuffer) value;	// We know that if the value (left) is string type, we've already converted it to buffer.
					} else {
						// The right is the one that introduces the string, so we change the value over to a string buffer.
						sb = new StringBuffer(((String) right).length()+16);	// We can't put the value in yet, need to get left into it.
						appendToBuffer(sb, value, valueType);	// Put the left value in now
						value = sb;
						valueType = String.class;	// Make it a string class
					}
					appendToBuffer(sb, right, rightType);
					// Now if we are the last operand, we should get rid of the buffer and put a true string back in.
					if (operandType == IInternalExpressionConstants.INFIX_LAST_OPERAND)
						value = sb.toString();
				} else if (valueType == Double.TYPE || rightType == Double.TYPE) {
					// If either side is double, the result will be double.
					value = new Double(getDouble(value) + getDouble(right));
					valueType = Double.TYPE;
				} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
					// If either side is float, the result will be float.
					value = new Float(getFloat(value) + getFloat(right));
					valueType = Float.TYPE;
				} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) + getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it will result in an int, even if both sides are short.
					value = new Integer(getInt(value) + getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			case IExpressionConstants.IN_REMAINDER:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidArithmeticType(valueType, IExpressionConstants.IN_REMAINDER);
				testValidArithmeticType(rightType, IExpressionConstants.IN_REMAINDER);
				if (valueType == Double.TYPE || rightType == Double.TYPE) {
					// If either side is double, the result will be double.
					value = new Double(getDouble(value) % getDouble(right));
					valueType = Double.TYPE;
				} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
					// If either side is float, the result will be float.
					value = new Float(getFloat(value) % getFloat(right));
					valueType = Float.TYPE;
				} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) % getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it will result in an int, even if both sides are short.
					value = new Integer(getInt(value) % getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			case IExpressionConstants.IN_RIGHT_SHIFT_SIGNED:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidBitType(valueType, IExpressionConstants.IN_RIGHT_SHIFT_SIGNED);
				testValidBitType(rightType, IExpressionConstants.IN_RIGHT_SHIFT_SIGNED);
				if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) >> getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it is int. (even two shorts together produce an int).
					value = new Integer(getInt(value) >> getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			case IExpressionConstants.IN_RIGHT_SHIFT_UNSIGNED:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidBitType(valueType, IExpressionConstants.IN_RIGHT_SHIFT_UNSIGNED);
				testValidBitType(rightType, IExpressionConstants.IN_RIGHT_SHIFT_UNSIGNED);
				if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) >>> getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it is int. (even two shorts together produce an int).
					value = new Integer(getInt(value) >>> getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			case IExpressionConstants.IN_TIMES:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidArithmeticType(valueType, IExpressionConstants.IN_TIMES);
				testValidArithmeticType(rightType, IExpressionConstants.IN_TIMES);
				if (valueType == Double.TYPE || rightType == Double.TYPE) {
					// If either side is double, the result will be double.
					value = new Double(getDouble(value) * getDouble(right));
					valueType = Double.TYPE;
				} else if (valueType == Float.TYPE || rightType == Float.TYPE) {
					// If either side is float, the result will be float.
					value = new Float(getFloat(value) * getFloat(right));
					valueType = Float.TYPE;
				} else if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) * getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it will result in an int, even if both sides are short.
					value = new Integer(getInt(value) * getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			case IExpressionConstants.IN_XOR:
				if (operandType == IInternalExpressionConstants.INFIX_LEFT_OPERAND)
					break;	// Do nothing with first operand
				
				testValidBitType(valueType, IExpressionConstants.IN_XOR);
				testValidBitType(rightType, IExpressionConstants.IN_XOR);
				if (valueType == Long.TYPE || rightType == Long.TYPE) {
					// If either side is long, the result will be long.
					value = new Long(getLong(value) ^ getLong(right));
					valueType = Long.TYPE;
				} else {
					// Else it is int. (even two shorts together produce an int).
					value = new Integer(getInt(value) ^ getInt(right));
					valueType = Integer.TYPE;
				}
				break;
			} 
		
		pushExpressionValue(value, valueType);	// Push the result back on the stack.
	}

	/**
	 * Get int value of the primitive wrapper bean passed in (must be either a <code>Number/code> or <code>Character</code>.
	 * Anything else will cause a class cast error.
	 * 
	 * @param bean
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final int getInt(Object bean) {
		return (bean instanceof Number) ? ((Number) bean).intValue() : ((Character) bean).charValue();
	}
	
	/**
	 * Get float value of the primitive wrapper bean passed in (must be either a <code>Number/code> or <code>Character</code>.
	 * Anything else will cause a class cast error.
	 * 
	 * @param bean
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final float getFloat(Object bean) {
		return (bean instanceof Number) ? ((Number) bean).floatValue() : ((Character) bean).charValue();
	}

	/**
	 * Get double value of the primitive wrapper bean passed in (must be either a <code>Number/code> or <code>Character</code>.
	 * Anything else will cause a class cast error.
	 * 
	 * @param bean
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final double getDouble(Object bean) {
		return (bean instanceof Number) ? ((Number) bean).doubleValue() : ((Character) bean).charValue();
	}
	
	/**
	 * Get long value of the primitive wrapper bean passed in (must be either a <code>Number/code> or <code>Character</code>.
	 * Anything else will cause a class cast error.
	 * 
	 * @param bean
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final long getLong(Object bean) {
		return (bean instanceof Number) ? ((Number) bean).longValue() : ((Character) bean).charValue();
	}
	
	private void throwInvalidInfix(int operator, Object value) throws IllegalArgumentException {
		throw new IllegalArgumentException(MessageFormat.format(InitparserTreeMessages.getString("ExpressionProcesser.InvalidOperandOfOperator_EXC_"), new Object[] {value != null ? value.toString() : null, IN_OPER_TO_STRING[operator]})); //$NON-NLS-1$
	}
	
	private void testValidBitType(Class type, int operator) {
		if (!type.isPrimitive() || type == Boolean.TYPE || type == Double.TYPE|| type == Float.TYPE)
			throwInvalidInfix(operator, type);
	}
	
	private void testValidArithmeticType(Class type, int operator) {
		if (!type.isPrimitive() || type == Boolean.TYPE)
			throwInvalidInfix(operator, type);
	}

	private void testValidPlusType(Class left, Class right) {
		// Plus is special in that string objects are also valid.
		if (left == String.class || right == String.class)
			return;	// As long as one side is string. Anything is valid.
		// If neither is string, then standard arithmetic test.
		testValidArithmeticType(left, IExpressionConstants.IN_PLUS);
		testValidArithmeticType(right, IExpressionConstants.IN_PLUS);
	}
	
	private void appendToBuffer(StringBuffer sb, Object value, Class valueType) {
		if (value == null)
			sb.append((Object)null);
		else if (valueType == String.class)
			sb.append((String) value);
		else if (valueType.isPrimitive()) {
			switch (getEnumForPrimitive(valueType)) {
				case BOOLEAN:
					sb.append(((Boolean) value).booleanValue());
					break;
				case BYTE:
					sb.append(((Number) value).byteValue());
					break;
				case CHAR:
					sb.append(((Character) value).charValue());
					break;
				case DOUBLE:
					sb.append(((Number) value).doubleValue());
					break;
				case FLOAT:
					sb.append(((Number) value).floatValue());
					break;
				case INT:
					sb.append(((Number) value).intValue());
					break;
				case LONG:
					sb.append(((Number) value).longValue());
					break;
				case SHORT:
					sb.append(((Number) value).shortValue());
					break;		
			}
		} else {
			// Just an object.
			sb.append(value);
		}
	}
	
	/**
	 * Push the array access expression.
	 * 
	 * @param indexCount Number of dimensions being accessed
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public final void pushArrayAccess(int indexCount) throws NoExpressionValueException {
		if (ignoreExpression>0)
			return;
		
		// We need to pop off the args. The topmost will be the rightmost index, and the bottom most will be the array itself.
		int[] arguments = new int[indexCount];
		// Fill the arg array in reverse order.
		for(int i=indexCount-1; i >= 0; i--) {
			Object index = popExpression();
			Class indexType = popExpressionType(false);
			if (indexType.isPrimitive() && (indexType == Integer.TYPE || indexType == Short.TYPE || indexType == Character.TYPE || indexType == Byte.TYPE)) {
				arguments[i] = getInt(index);
			} else
				throwClassCast(Integer.TYPE, index);
		}
		
		Object array = popExpression();
		Class arrayType = popExpressionType(false);
		if (arrayType.isArray()) {
			// First figure out how many dimensions are available. Stop when we hit indexcount because we won't be going further.
			int dimcount = 0;
			Class[] componentTypes = new Class[indexCount];	// 
			Class componentType = arrayType;
			while (dimcount < indexCount && componentType.isArray()) {
				componentTypes[dimcount++] = componentType = componentType.getComponentType();
			}
			
			if (dimcount < indexCount)
				throw new IllegalArgumentException(MessageFormat.format(InitparserTreeMessages.getString("ExpressionProcesser.XIsGreaterThanNumberOfDimensionsInArray_EXC_"), new Object[] {new Integer(indexCount), new Integer(dimcount)})); //$NON-NLS-1$
			
			// Now start accessing one index at a time.
			Object value = array;	// Final value, start with full array. 
			for(int i=0; i<indexCount; i++) {
				value = Array.get(value, arguments[i]);
			}
			pushExpressionValue(value, componentTypes[indexCount-1]);
		}  else
			throw new IllegalArgumentException(MessageFormat.format(InitparserTreeMessages.getString("ExpressionProcesser.NotAnArray_EXC_"), new Object[] {arrayType})); //$NON-NLS-1$
	}
	
	/**
	 * Push the array creation request.
	 * 
	 * @param arrayType The type of the array
	 * @param dimensionCount The number of dimensions being initialized. Zero if using an initializer.
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public final void pushArrayCreation(Class arrayType, int dimensionCount) throws NoExpressionValueException {
		if (ignoreExpression>0)
			return;
		
		if (dimensionCount == 0) {
			// The top value is the array itself, from the array initializer.
			// So we do nothing.
		} else {
			// Strip off dimensionCounts from the array type, e.g.
			// ArrayType is int[][][]
			// Dimensioncount is 2
			// Then we need to strip two componenttypes off of the array type
			// wind up with int[]
			// This is necessary because Array.new will add those dimensions back
			// on through the dimension count.
			Class componentType = arrayType;
			for(int i=0; i < dimensionCount && componentType != null; i++)
				componentType = componentType.getComponentType();
			if (componentType == null)
				throw new IllegalArgumentException(MessageFormat.format(InitparserTreeMessages.getString("ExpressionProcesser.ArraytypeHasFewerDimensionsThanRequested_EXC_"), new Object[] {arrayType, new Integer(dimensionCount)})); //$NON-NLS-1$
			
			// We need to pull in the dimension initializers. They are stacked in reverse order.
			int[] dimInit = new int[dimensionCount];
			for(int i=dimensionCount-1; i >= 0; i--) {
				Object index = popExpression();
				Class dimType = popExpressionType(false);
				if (dimType.isPrimitive() && (dimType == Integer.TYPE || dimType == Short.TYPE || dimType == Character.TYPE || dimType == Byte.TYPE)) {
					dimInit[i] = getInt(index);
				} else
					throwClassCast(Integer.TYPE, index);
			}
			
			// Finally create the array.
			Object array = Array.newInstance(componentType, dimInit);
			pushExpressionValue(array, arrayType);
		}
	}

	/**
	 * Push the array initializer request.
	 * 
	 * @param arrayType The type of the array to create, minus one dimension.
	 * @param expressionCount
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public final void pushArrayInitializer(Class arrayType, int expressionCount) throws NoExpressionValueException {
		if (ignoreExpression>0)
			return;

		Object[] dimValues = null;
		if (expressionCount > 0) {
			// We need to pull in the initializers. They are stacked in reverse order.
			dimValues = new Object[expressionCount];
			for (int i = expressionCount - 1; i >= 0; i--) {
				Object dimValue = dimValues[i] = popExpression();
				Class dimType = popExpressionType(false);
				if (arrayType.isPrimitive()) {
					if (dimValue == null || !dimType.isPrimitive())
						throwClassCast(arrayType, dimType);
					// A little trickier. Can assign short to an int, but can't assign long to an int. Widening is permitted.
					if (arrayType != dimType) {
						int compEnum = getEnumForPrimitive(arrayType);
						int dimEnum = getEnumForPrimitive(dimType);
						if (compEnum == BOOLEAN || dimEnum == BOOLEAN)
							throwClassCast(arrayType, dimType);
						int dimValueAsInt = getInt(dimValue);
						switch (compEnum) {
							case BYTE :
								// Can accept byte, short, char, or int as long as value is <= byte max. Can't accept long, double, float at all.
								// Note: This isn't actually true. The max/min test is only valid if the value is a literal, not an expression,
								// however, at this point in time we no longer know this. So we will simply allow it.
								if (dimEnum > INT || dimValueAsInt > Byte.MAX_VALUE || dimValueAsInt < Byte.MIN_VALUE)
									throwClassCast(arrayType, dimType);
								// But need to be changed to appropriate type for the array.set to work.									
								dimValues[i] = new Byte((byte)dimValueAsInt);
								break;
							case SHORT :								
								// Can accept byte, short, char, or int as long as value is <= byte max. Can't accept long, double, float at all.
								// Note: This isn't actually true. The max/min test is only valid if the value is a literal, not an expression,
								// however, at this point in time we no longer know this. So we will simply allow it.
								if (dimEnum > INT || dimValueAsInt > Short.MAX_VALUE || dimValueAsInt < Short.MIN_VALUE)
									throwClassCast(arrayType, dimType);
								// But need to be changed to appropriate type for the array.set to work.									
								dimValues[i] = new Short((short)dimValueAsInt);
								break;
							case CHAR :
								// Can accept byte, short, char, or int as long as value is <= byte max. Can't accept long, double, float at all.
								// Note: This isn't actually true. The max/min test is only valid if the value is a literal, not an expression,
								// however, at this point in time we no longer know this. So we will simply allow it.
								if (dimEnum > INT || dimValueAsInt > Character.MAX_VALUE || dimValueAsInt < Character.MIN_VALUE)
									throwClassCast(arrayType, dimType);
								// But need to be changed to appropriate type for the array.set to work.									
								dimValues[i] = new Character((char)dimValueAsInt);
								break;
							case INT :
								// Can accept byte, short, char, or int. Can't accept long, double, float at all.
								if (dimEnum > INT)
									throwClassCast(arrayType, dimType);
								// But need to be changed to appropriate type for the array.set to work.									
								dimValues[i] = new Integer(dimValueAsInt);
								break;
							case LONG :
								// Can accept byte, short, char, int, or long. Can't accept double, float at all.
								if (dimEnum > LONG)
									throwClassCast(arrayType, dimType);
								// But need to be changed to appropriate type for the array.set to work.									
								dimValues[i] = new Long(getLong(dimValue));
								break;
							case FLOAT :
								// Can accept byte, short, char, int, long, or float. Can't accept double at all.
								if (dimEnum > FLOAT)
									throwClassCast(arrayType, dimType);
								// But need to be changed to appropriate type for the array.set to work.									
								dimValues[i] = new Float(getFloat(dimValue));
								break;
							case DOUBLE :
								// But need to be changed to appropriate type for the array.set to work.									
								dimValues[i] = new Double(getDouble(dimValue));
								break;

						}
					}
					// Compatible, so ok.
				} else if (dimType != MethodHelper.NULL_TYPE && !arrayType.isAssignableFrom(dimType)) {
					// If it is NULL_TYPE, then this is a pushed null. This is always assignable to a non-primitive.
					// So we don't enter here in that case. However, a null that was returned from some expression
					// won't have a NULL_TYPE, it will instead have the expected return type. That must be used
					// in the assignment instead. That is because in java it uses the expected type to determine
					// compatibility, not the actual type.
					throwClassCast(arrayType, dimType);
				}
			}
			
		}
		
		// Now we finally create the array.
		Object array = Array.newInstance(arrayType, new int[] {expressionCount});
		for (int i = 0; i < expressionCount; i++) {
			Array.set(array, i, dimValues[i]);
		}
		
		pushExpressionValue(array, array.getClass());	// Adjust to true array type, not the incoming type (which is one dimension too small).
	}

	/**
	 * Push the class instance creation request.
	 * 
	 * @param type The type to create an instance of
	 * @param argumentCount The number of arguments (which are stored on the stack).	 * @throws NoExpressionValueException
	 * @throws EvaluationException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * 
	 * @since 1.0.0
	 */
	public final void pushClassInstanceCreation(Class type, int argumentCount) throws NoExpressionValueException, EvaluationException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		if (ignoreExpression>0)
			return;
					
		// We need to pull in the arguments. They are stacked in reverse order.
		Object value = null;	// The new instance.
		if (argumentCount > 0) {
			Object[]  args = new Object[argumentCount];
			Class[] argTypes = new Class[argumentCount];
			for (int i = argumentCount - 1; i >= 0; i--) {
				args[i] = popExpression();
				argTypes[i] = popExpressionType(false);
			}
			
			// Now we need to find the appropriate constructor.
			Constructor ctor = MethodHelper.findCompatibleConstructor(type, argTypes);
			value = ctor.newInstance(args);
		} else {
			// No args, just do default ctor.
			value = type.newInstance();
		}
		
		pushExpressionValue(value, type);
	}

	/**
	 * Push the field access expression.
	 * @param fieldName
	 * @param hasReceiver
	 * @throws NoExpressionValueException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * 
	 * @since 1.0.0
	 */
	public final void pushFieldAccess(String fieldName, boolean hasReceiver) throws NoExpressionValueException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		if (ignoreExpression>0)
			return;
	
		// Get the receiver off of the stack.
		Object receiver = popExpression();
		Class receiverType = popExpressionType(false);
		
		// Find the field.
		Field field = receiverType.getField(fieldName);
		// Access the field.
		Object value = field.get(receiver);
		Class valueType = field.getType();
		pushExpressionValue(value, valueType);
	}

	/**
	 * Push the method invocation expression.
	 * @param methodName
	 * @param hasReceiver
	 * @param argCount
	 * @throws NoExpressionValueException
	 * @throws EvaluationException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * 
	 * @since 1.0.0
	 */
	public final void pushMethodInvocation(String methodName, boolean hasReceiver, int argCount) throws NoExpressionValueException, EvaluationException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (ignoreExpression>0)
			return;
		
		// We need to pull in the arguments. They are stacked in reverse order.
		Object[]  args = new Object[argCount];
		Class[] argTypes = new Class[argCount];
		for (int i = argCount - 1; i >= 0; i--) {
			args[i] = popExpression();
			argTypes[i] = popExpressionType(false);
		}
		
		// Now get receiver
		Object receiver = popExpression();
		Class receiverType = popExpressionType(false);
		
		// Now we need to find the appropriate method.
		Method method = MethodHelper.findCompatibleMethod(receiverType, methodName, argTypes);
		Object value = method.invoke(receiver, args);
		
		pushExpressionValue(value, method.getReturnType());
	}

	/**
	 * @param expressionType
	 * 
	 * @since 1.0.0
	 */
	public final void pushConditional(int expressionType) throws NoExpressionValueException {
		boolean wasIgnoring = ignoreExpression>0;
		if (wasIgnoring) {
			if (expressionType == IExpressionConstants.CONDITIONAL_CONDITION)
				ignoreExpression+=2;	// Increment it twice so that entire expression is ignored because we already are in an ignore.
			else
				ignoreExpression--;	// Decrement it because we have ignored one of the expressions.
			if (ignoreExpression>0)
				return;	// We are still ignoring.
		}
		
		switch (expressionType) {
			case IExpressionConstants.CONDITIONAL_CONDITION:
				Object condition = popExpression();
				Class type = popExpressionType(false);
				if (type != Boolean.TYPE)
					throwClassCast(Boolean.TYPE, condition);
				if (((Boolean) condition).booleanValue()) {
					// Condition was true.
					// Do nothing. Let true condition be processed.
				} else {
					// Condition was false.
					++ignoreExpression;	// Tell the true condition it should be ignored.
				}
				// We don't put anything back on the stack because the condition test is not ever returned.
				// The appropriate true or false condition will be left on the stack.
				break;
			case IExpressionConstants.CONDITIONAL_TRUE:
				if (!wasIgnoring) {
					// true was processed, so now tell false to not process.
					// The true condition and type will be left on top of the stack.
					++ignoreExpression;	
				}
				break;
			case IExpressionConstants.CONDITIONAL_FALSE:
				// There's nothing to do, if it was ignored due to true, then true is on the stack.
				// If it wasn't ignored, then false expression is on the stack, which is what it should be.
				break;
		}
	}
}
