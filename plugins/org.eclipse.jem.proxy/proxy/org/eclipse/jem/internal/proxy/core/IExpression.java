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
 *  $RCSfile: IExpression.java,v $
 *  $Revision: 1.1 $  $Date: 2004/02/03 23:18:36 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import org.eclipse.jem.internal.proxy.initParser.tree.IExpressionConstants;
 
/**
 * This is an expression. It will be evaluated on the other side. The difference between an
 * expression and using IMethodProxy's, IConstructorProxy's, etc. is the granularity. The proxies
 * are one round-trip to the other side for each access or execution. The expression builds them up
 * and will execute them all at once on the other side. Another difference is that the reflection
 * will be done on the other side too. For instance when invoking a method, the method name is
 * passed into the expression as a string then reflected and then invoked, while with method
 * proxies, the string is used to find the proxy on the other side, and then a later round-trip
 * will be used to invoke it.
 * <p>
 * Also an expression is a one-time use object. It can't be reused a second time. A new one must be
 * built up again.
 * <p>
 * We are not using separate instances of expressions, and types of expressions, because we don't
 * want to build up many objects (in a form of an expression tree) that will then be thrown away
 * (one for each expression in the nested list of expressions). We just build the command list as
 * we build the expression.
 * <p>
 * To use, you call the IStandardBeanProxyFactory's createExpression method. An IExpression is
 * returned. From there you will start creating the contents of the expression. Any then you will
 * finally either getExpressionValue() to get the final value of the expression, or use
 * invokeExpression() to just execute the expression(s). If you use getExpressionValue(), there can
 * only be one root expression. If you use invokeExpression there can be more than one root
 * expression, and they will all be executed.
 * <p>
 * Since sequence is so important, it will be tested and if anything is done out of order an
 * IllegalStateException will be thrown.
 * <p>
 * Also, it is up to the implementation, but it may start executing ahead of completion, and so you
 * may get a ThrowableProxy from any of the calls during build up.
 * <p>
 * Each time an expression is created, one argument passed in will be <code>forExpression</code> flag.
 * This is a set of constants used as a clue for what expression this expression is being created.
 * This is for a sanity check on the state. For example, when creating the array expression for an
 * array access, the ARRAYACCESS_ARRAY flag is passed in. This way if the current expression on the
 * stack is not for an array access waiting for the array expression, an IllegalStateException will be thrown.
 * Without this flag, it would be easy to accidently create the wrong expression at the wrong time.
 * Once such an error occurs, this IExpression will no longer be valid. IllegalStateException will be thrown
 * for any type of access.
 * 
 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanProxyFactory#createExpression()
 * @see java.lang.IllegalStateException
 * @since 1.0.0
 */
public interface IExpression extends IExpressionConstants {
	
	/**
	 * Invoke the expression(s). If there is more than one root expression, it will invoke them
	 * in the order created. If the expression stack is not complete, then <code>IllegalStateException</code>
	 * will be thrown.
	 * 
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void invokeExpression() throws ThrowableProxy, NoExpressionValueException, IllegalStateException;
	
	/**
	 * Invoke the root expression and return the value of the expression. If the expression stack
	 * is not complete, or if there is more than one root expression, then <code>IllegalStateException</code>
	 * will be thrown.
	 * 
	 * @return The value of the root expression. 
	 * 
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public IBeanProxy getExpressionValue() throws ThrowableProxy, NoExpressionValueException, IllegalStateException;
			
	/**
	 * Create an Array Access (e.g. x[3]).
	 * This must be followed by create expressions for:
	 * 	<code>ARRAYACCESS_ARRAY</code>
	 *  indexCount times an: <code>ARRAYACCESS_INDEX</code>
	 * <p>
	 * So the array access must be followed by 1+indexCount expressions.
	 * 
	 * @param forExpression This is for what expression this expression is being created.
	 * @param indexCount The number of index expressions that will be created.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createArrayAccess(int forExpression, int indexCount) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create an Array Creation (e.g. <code>new int[3]</code> or <code>new int[3][]</code> or <code>new int[] {3, 4}</code>).
	 * If <code>dimensionExpressionCount</code> is zero, then there must be an initializer. This is because
	 * if there are any dimension expressions, then initializers are invalid and visa-versa.
	 * <p>
	 * The dimensionExpressionCount is for how many dimensions have an expression in them. For instance,
	 * <code>new int[3]</code> will have a dimensionExpressionCount of 1. While
	 * <code>new int[3][]</code> will also have count of 1. And finally
	 * <code>new int []</code> will have a count of 0.
	 * <p>
	 * This must be followed by create expressions for:
	 * 	dimensionExpressionCount times an: <code>ARRAYCREATION_DIMENSION</code>
	 *  or an createArrayInitializer if dimension count is 0.
	 *  
	 * @param forExpression
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format. It must also include the correct number of  <code>[]</code> at the end.
	 * @param dimensionExpressionCount
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * @throws IllegalArgumentException
	 * 
	 * @since 1.0.0
	 */
	public void createArrayCreation(int forExpression, String type, int dimensionExpressionCount) throws ThrowableProxy, IllegalStateException, IllegalArgumentException, NoExpressionValueException;

	/**
	 * Create an Array Creation (e.g. <code>new int[3]</code> or <code>new int[3][]</code> or <code>new int[] {3, 4}</code>).
	 * If <code>dimensionExpressionCount</code> is zero, then there must be an initializer. This is because
	 * if there are any dimension expressions, then initializers are invalid and visa-versa.
	 * <p>
	 * The dimensionExpressionCount is for how many dimensions have an expression in them. For instance,
	 * <code>new int[3]</code> will have a dimensionExpressionCount of 1. While
	 * <code>new int[3][]</code> will also have count of 1. And finally
	 * <code>new int []</code> will have a count of 0.
	 * <p>
	 * This must be followed by create expressions for:
	 * 	dimensionExpressionCount times an: <code>ARRAYCREATION_DIMENSION</code>
	 *  or an createArrayInitializer if dimension count is 0.
	 *  
	 * @param forExpression
	 * @param type This is the type.
	 * @param dimensionExpressionCount
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createArrayCreation(int forExpression, IBeanTypeProxy type, int dimensionExpressionCount) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create an array initializer. (e.g. <code>{2,3}</code>).
	 * This one is unusual in that there is no forExpression. That is because array initializers are only valid in
	 * certain places. And so if called when not expected, this is an IllegalStateException.
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	expressionCount times an: <code>ARRAYINITIALIZER_EXPRESSION</code>
	 * 		except if the expression is another array initializer. That is valid and doesn't have a forExpression,
	 * 		but it does count as one of the expressionCounts.
	 * 
	 * @param expressionCount Number of expressions, may be 0.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createArrayInitializer(int expressionCount) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
		
	/**
	 * Create a cast expression (e.g. <code>(short)10</code> or <code>(java.lang.String) "asd"</code>)
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	<code>CAST_EXPRESSION</code> 
	 *  
	 * @param forExpression
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createCastExpression(int forExpression, String type) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;

	/**
	 * Create a cast expression (e.g. <code>(short)10</code> or <code>(java.lang.String) "asd"</code>)
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	<code>CAST_EXPRESSION</code> 
	 *  
	 * @param forExpression
	 * @param type This is the type.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createCastExpression(int forExpression, IBeanTypeProxy type) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
		
	/**
	 * Create a new class instance expression (e.g. <code>new java.lang.Integer(5)</code>)
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	argumentCount times an: <code>CLASSINSTANCECREATION_ARGUMENT</code>
	 * 
	 * @param forExpression
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format.
	 * @param argumentCount
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createClassInstanceCreation(int forExpression, String type, int argumentCount) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;

	/**
	 * Create a new class instance expression (e.g. <code>new java.lang.Integer(5)</code>)
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	argumentCount times an: <code>CLASSINSTANCECREATION_ARGUMENT</code>
	 * 
	 * @param forExpression
	 * @param type This is the type.
	 * @param argumentCount
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createClassInstanceCreation(int forExpression, IBeanTypeProxy type, int argumentCount) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a conditional expression (e.g. <code>x != 3 ? 4 : 5</code>)
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	<code>CONDITIONAL_CONDITION</code>
	 * 	<code>CONDITIONAL_TRUE</code>
	 * 	<code>CONDITIONAL_FALSE</code>	
	 * 
	 * @param forExpression
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createConditionalExpression(int forExpression) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a field access (e.g. <code>java.awt.Color.red</code>)
	 * <p>
	 * Note: At this time we require a receiver. In the future it may be possible to not have one, but
	 * for that we need a <code>this</code> object to know who the receiver implicitly is.
	 * The receiver may be a "type receiver" if it is a type, e.g. <code>java.awt.Color</code>.
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	<code>FIELD_RECEIVER</code> if hasReceiver is <code>true</code>
	 * 
	 * @param forExpression
	 * @param fieldName The name of the field.
	 * @param hasReceiver Has a receiver flag. Currently this must always be true.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @see IExpression#createTypeReceiver(String)
	 * @since 1.0.0
	 */
	public void createFieldAccess(int forExpression, String fieldName, boolean hasReceiver) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create an infix expression (e.g. <code>3 + 4</code> or <code>3 + 4 + 5</code>).
	 * <p>
	 * If there are more than 2 operands (all with the same operator) then for convienence all of
	 * the expression can be done in one expression than requiring several, one for each operator.
	 * If they are different operators, then different expressions will be required.
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	<code>INFIX_LEFT</code>
	 * 	<code>INFIX_RIGHT</code>
	 * 	extendedOperandCount times an: <code>INFIX_EXTENDED</code>
	 * 
	 * @param forExpression
	 * @param operator The operator. The values come from IExpressionConstants, the infix constants.
	 * @param extendedOperandCount The number of extended operands. May be zero.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @see org.eclipse.jem.internal.proxy.common.IExpressionConstants#IN_AND
	 * @since 1.0.0
	 */
	public void createInfixExpression(int forExpression, int operator, int extendedOperandCount) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create an instanceof expression (e.g. <code>x instanceof java.lang.String</code>
	 * <p>
	 * This must be followed by createExpression for:
	 * 	<code>INSTANCEOF_VALUE</code>
	 * @param forExpression
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createInstanceofExpression(int forExpression, String type) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create an instanceof expression (e.g. <code>x instanceof java.lang.String</code>
	 * <p>
	 * This must be followed by createExpression for:
	 * 	<code>INSTANCEOF_VALUE</code>
	 * @param forExpression
	 * @param type This is the type.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createInstanceofExpression(int forExpression, IBeanTypeProxy type) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a method invocation expression (e.g. <code>java.lang.String.valueOf(10)</code>)
	 * <p>
	 * Note: At this time we require a receiver. In the future it may be possible to not have one, but
	 * for that we need a <code>this</code> object to know who the receiver implicitly is.
	 * The receiver may be a "type receiver" if it is a type, e.g. <code>java.awt.Color</code>.
	 * <p>
	 * This must be followed by createExpression for:
	 * 	<code>METHOD_RECEIVER</code>
	 * 	argumentCounts times expressions for: <code>METHOD_ARGUMENT</code>
	 * 
	 * @param forExpression
	 * @param name The name of the method
	 * @param hasReceiver Has a receiver flag. Currently this must always be true.
	 * @param argumentCount Count of number of arguments. May be zero.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @see IExpression#createTypeReceiver(String)
	 * @since 1.0.0
	 */
	public void createMethodInvocation(int forExpression, String name, boolean hasReceiver, int argumentCount) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a prefix expression (e.g. <code>!flag</code> or <code>-(3+4)</code>).
	 * If you are just trying to create a signed numeric literal, just use the createPrimitiveLiteral passing in a
	 * negative value. You don't need to use prefix expression for that.
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	<code>PREFIX_OPERAND</code>
	 * @param forExpression
	 * @param operator The operator. The values come from IExpressionConstants, the prefix constants.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @see org.eclipse.jem.internal.proxy.common.IExpressionConstants#PRE_PLUS
	 * @since 1.0.0
	 */
	public void createPrefixExpression(int forExpression, int operator) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a reference to <code>null</code>.
	 * 
	 * @param forExpression
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createNull(int forExpression) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a type literal (e.g. <code>java.lang.String.class</code>).\
	 * <p>
	 * Note: If you want a type literal to a IBeanTypeProxy, just use createProxyExpression and pass in the
	 * IBeanTypeProxy.
	 * 
	 * @param forExpression
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createTypeLiteral(int forExpression, String type) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a type receiver. This is where a type is used as the receiver of a field access or a method invocation.
	 * (e.g. <code>java.lang.String.valueOf(10)</code>). 
	 * <p>
	 * This is unusual in that there is no forExpression. It isn't needed because these are only valid
	 * in certain situations (method or field receiver) and if used anywhere else it is an error.
	 * 
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createTypeReceiver(String type) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;

	/**
	 * Create a type receiver. This is where a type is used as the receiver of a field access or a method invocation.
	 * (e.g. <code>java.lang.String.valueOf(10)</code>). 
	 * <p>
	 * This is unusual in that there is no forExpression. It isn't needed because these are only valid
	 * in certain situations (method or field receiver) and if used anywhere else it is an error.
	 * 
	 * @param type This is the type proxy.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createTypeReceiver(IBeanTypeProxy type) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a boolean primitive literal (e.g. <code>true</code>).
	 * 
	 * @param forExpression
	 * @param value The boolean value for the literal.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(int forExpression, boolean value) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a character literal (e.g. <code>'a'</code> or <code>'\n'</code>)
	 * 
	 * @param forExpression
	 * @param value The character value for this literal.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(int forExpression, char value) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;

	/**
	 * Create a byte literal (e.g. <code>(byte)10</code>)
	 * 
	 * @param forExpression
	 * @param value The byte value for this literal.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(int forExpression, byte value) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;

	/**
	 * Create a double literal (e.g. <code>10d</code>)
	 * 
	 * @param forExpression
	 * @param value The double value for this literal.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(int forExpression, double value) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;

	/**
	 * Create a float literal (e.g. <code>10f</code>)
	 * 
	 * @param forExpression
	 * @param value The float value for this literal.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(int forExpression, float value) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a int literal (e.g. <code>100000</code>)
	 * 
	 * @param forExpression
	 * @param value The int value for this literal.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(int forExpression, int value) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a long literal (e.g. <code>10l</code>)
	 * 
	 * @param forExpression
	 * @param value The long value for this literal.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(int forExpression, long value) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a short literal (e.g. <code>(short)10</code>)
	 * 
	 * @param forExpression
	 * @param value The short value for this literal.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(int forExpression, short value) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create a string literal (e.g. <code>"asdf"</code>). The value is the actual string, with escapes already
	 * translated into the true character values.
	 *  
	 * @param forExpression
	 * @param value The string value for this literal.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createStringLiteral(int forExpression, String value) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
	
	/**
	 * Create an expression that has an existing bean proxy as its value.
	 * 
	 * @param forExpression This is for what expression this expression is being created.
	 * @param proxy The proxy that should be used as a value.
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public void createProxyExpression(int forExpression, IBeanProxy proxy) throws ThrowableProxy, IllegalStateException, NoExpressionValueException;
}
