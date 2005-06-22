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
 *  $RCSfile: IExpression.java,v $
 *  $Revision: 1.5 $  $Date: 2005/06/22 21:05:18 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import org.eclipse.jem.internal.proxy.initParser.tree.*;
import org.eclipse.jem.internal.proxy.initParser.tree.IExpressionConstants;
import org.eclipse.jem.internal.proxy.initParser.tree.NoExpressionValueException;
 
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
 * returned. From there you will start creating the contents of the expression. And then you will
 * finally either getExpressionValue() to get the final value of the expression, or use
 * invokeExpression() to just execute the expression(s). If you use getExpressionValue(), there can
 * only be one root expression. If you use invokeExpression there can be more than one root
 * expression, and they will all be executed.
 * <p>
 * Since sequence is so important, it will be tested and if anything is done out of order an
 * IllegalStateException will be thrown.
 * <p>
 * Each time an expression is created, one argument passed in will be <code>forExpression</code> flag.
 * This is a set of constants used as a clue for what expression this expression is being created.
 * This is for a sanity check on the state. For example, when creating the array expression for an
 * array access, the ARRAYACCESS_ARRAY flag is passed in. This way if the current expression on the
 * stack is not for an array access waiting for the array expression, an IllegalStateException will be thrown.
 * Without this flag, it would be easy to accidently create the wrong expression at the wrong time.
 * Once such an error occurs, this IExpression will no longer be valid. IllegalStateException will be thrown
 * for any type of access.
 * <p>
 * It is guarenteed that the entire stack of commands will be sent without stopping except for IllegalStateException
 * due to out of order expressions.
 * <p>
 * <b>Note:</b> This interface is not meant to be neither instantiated nor implemented by customers.
 * It is the interface into the expression processing. It is to be instantiated through the createExpression request.
 * The {@link org.eclipse.jem.internal.proxy.core.Expression} class is available as API. It is not meant to
 * be instantiated by customers, but customers can take the IExpression and cast to Expression for some advanced
 * API.
 *  
 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanProxyFactory#createExpression()
 * @see java.lang.IllegalStateException
 * @since 1.0.0
 */
public interface IExpression extends IExpressionConstants {
	
	/**
	 * Return the registry this expression is working against.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public ProxyFactoryRegistry getRegistry();
	
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
	 * Return whether the expression is valid. It would be invalid if any of the create... calls had thrown an
	 * exception or if the expression has already been evaluated (invoked or getExpressionValue()).
	 * 
	 * @return <code>true</code> if expression is valid, <code>false</code> if there had been some error, or if the expression has already been evaluated.
	 * 
	 * @since 1.1.0
	 */
	public boolean isValid();
	
	/**
	 * Close the expression. This needs to be called if for some reason {@link IExpression#invokeExpression()} or {@link IExpression#getExpressionValue()}
	 * were not called. It is not an error to always call this after these calls. It will not have any problems with the expression already
	 * being closed. invoke and getvalue automatically call close for you. If you don't call close in case of an error, the resources
	 * for the expression will not be freed up. 
	 * 
	 * 
	 * @since 1.1.0
	 */
	public void close();
	
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
	 * <pre>
	 *  <code>ARRAYACCESS_ARRAY</code>
	 *  <code>ARRAYACCESS_INDEX</code> an indexCount times. 
	 * </pre>
	 * <p>
	 * So the array access must be followed by 1+indexCount expressions.
	 * 
	 * @param forExpression This is for what expression this expression is being created.
	 * @param indexCount The number of index expressions that will be created.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createArrayAccess(ForExpression forExpression, int indexCount) throws IllegalStateException;
	
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
	 * <pre>
	 *  <code>ARRAYCREATION_DIMENSION</code> a dimensionExpressionCount times, 
	 *  or an <code>createArrayInitializer()</code> if dimension count is 0.
	 * </pre>
	 *  
	 * @param forExpression
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format. It must also include the correct number of  <code>[]</code> at the end.
	 * @param dimensionExpressionCount
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createArrayCreation(ForExpression forExpression, String type, int dimensionExpressionCount) throws IllegalStateException;

	/**
	 * Create an Array Creation (e.g. <code>new int[3]</code> or <code>new int[3][]</code> or <code>new int[] {3, 4}</code>).
	 * If <code>dimensionExpressionCount</code> is zero, then there must be an initializer. This is because
	 * if there are any dimension expressions, then initializers are invalid and visa-versa.
	 * <p>
	 * The dimensionExpressionCount is for how many dimensions have an expression in them. For instance,
	 * <ol>
	 * <li><code>new int[3]</code> will have a dimensionExpressionCount of 1. While
	 * <li><code>new int[3][]</code> will also have count of 1. And finally
	 * <li><code>new int []</code> will have a count of 0.
	 * </ol>
	 * The expressions that follow if dimensionExpressionCount is not zero must evaluate to be compatible to an int type (i.e. byte, char, short, or int).
	 * Each expression will be used to create an array for that dimension of that size. For example <code>new int[3][]</code> will have an
	 * dimension expression that evaluates to "3", and so it will create an array int[3][].
	 * <p>
	 * This must be followed by create expressions for:
	 * 	dimensionExpressionCount times an: <code>ARRAYCREATION_DIMENSION</code>
	 *  or an createArrayInitializer if dimension count is 0.
	 *  
	 * @param forExpression
	 * @param type This is the type. This must be the actual type with the correct total number of dimensions (e.g. "java.lang.Object[][]").
	 * @param dimensionExpressionCount number of int valued expressions that follow that give the size for each dimension.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createArrayCreation(ForExpression forExpression, IProxyBeanType type, int dimensionExpressionCount) throws IllegalStateException;
	
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
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createArrayInitializer(int expressionCount) throws IllegalStateException;
		
	/**
	 * Create a cast expression (e.g. <code>(short)10</code> or <code>(java.lang.String) "asd"</code>)
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	<code>CAST_EXPRESSION</code> 
	 *  
	 * @param forExpression
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createCastExpression(ForExpression forExpression, String type) throws IllegalStateException;

	/**
	 * Create a cast expression (e.g. <code>(short)10</code> or <code>(java.lang.String) "asd"</code>)
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	<code>CAST_EXPRESSION</code> 
	 *  
	 * @param forExpression
	 * @param type This is the type.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createCastExpression(ForExpression forExpression, IProxyBeanType type) throws IllegalStateException;
		
	/**
	 * Create a new class instance expression (e.g. <code>new java.lang.Integer(5)</code>)
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	argumentCount times an: <code>CLASSINSTANCECREATION_ARGUMENT</code>
	 * 
	 * @param forExpression
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format.
	 * @param argumentCount
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createClassInstanceCreation(ForExpression forExpression, String type, int argumentCount) throws IllegalStateException;

	/**
	 * Create a new class instance expression (e.g. <code>new java.lang.Integer(5)</code>)
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	argumentCount times an: <code>CLASSINSTANCECREATION_ARGUMENT</code>
	 * 
	 * @param forExpression
	 * @param type This is the type.
	 * @param argumentCount
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createClassInstanceCreation(ForExpression forExpression, IProxyBeanType type, int argumentCount) throws IllegalStateException;
	
	/**
	 * Create a conditional expression (e.g. <code>x != 3 ? 4 : 5</code>)
	 * <p>
	 * This must be followed by createExpressions for:
	 * 	<code>CONDITIONAL_CONDITION</code>
	 * 	<code>CONDITIONAL_TRUE</code>
	 * 	<code>CONDITIONAL_FALSE</code>	
	 * 
	 * @param forExpression
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createConditionalExpression(ForExpression forExpression) throws IllegalStateException;
	
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
	 * @param hasReceiver Has a receiver flag. Currently this must always be true. This is because can't tell what class to look into for the field without a receiver.
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * 
	 * @see IExpression#createTypeReceiver(String)
	 * @since 1.0.0
	 */
	public void createFieldAccess(ForExpression forExpression, String fieldName, boolean hasReceiver) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Create a field access (e.g. <code>java.awt.Color.red</code>)
	 * <p>
	 * Note: At this time we require a receiver for non-static access. In the future it may be possible to not have one, but
	 * for that we need a <code>this</code> object to know who the receiver implicitly is.
	 * The receiver may be a "type receiver" if it is a type, e.g. <code>java.awt.Color</code>.
	 * <p>
	 * This must be followed by createExpressions for:
	 * <pre>
	 * 	<code>FIELD_RECEIVER</code> if hasReceiver is <code>true</code>
	 * </pre>
	 * 
	 * @param forExpression
	 * @param fieldProxy The field proxy for the field.
	 * @param hasReceiver Has a receiver flag.
	 * @throws IllegalStateException
	 * 
	 * @see IExpression#createTypeReceiver(String)
	 * @since 1.0.0
	 */
	public void createFieldAccess(ForExpression forExpression, IProxyField fieldProxy, boolean hasReceiver) throws IllegalStateException;

	/**
	 * Create an if/else stmt. Since this is a statment, there is no ForExpression. ROOT_EXPRESSION must be the next expression type.
	 * <p<
	 * This must be followed by createExpressions for:
	 * <pre>
	 *   <code>IF_CONDITION</code> The if test, must return boolean.
	 *   <code>IF_TRUE</code> The if condition is true clause. The clause may be an expression, or a block.
	 *   <code>IF_ELSE</code>  if hasElseClause is <code>true</code>. The else clause. The clause may be an expression, or a block.
	 * </pre>
	 * 
	 * @param hasElseClause
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public void createIfElse(boolean hasElseClause) throws IllegalStateException;
	
	/**
	 * Create an infix expression (e.g. <code>3 + 4</code> or <code>3 + 4 + 5</code>).
	 * <p>
	 * If there are more than 2 operands (all with the same operator) then for convienence all of
	 * the expression can be done in one expression than requiring several, one for each operator.
	 * If they are different operators, then different expressions will be required.
	 * <p>
	 * <pre>
	 * This must be followed by createExpressions for:
	 * 	<code>INFIX_LEFT</code>
	 * 	<code>INFIX_RIGHT</code>
	 * 	extendedOperandCount times an: <code>INFIX_EXTENDED</code>
	 * </pre>
	 * 
	 * @param forExpression
	 * @param operator The operator.
	 * @param extendedOperandCount The number of extended operands. May be zero.
	 * @throws IllegalStateException
	 * 
	 * @see org.eclipse.jem.internal.proxy.common.IExpressionConstants#IN_AND
	 * @since 1.0.0
	 */
	public void createInfixExpression(ForExpression forExpression, InfixOperator operator, int extendedOperandCount) throws IllegalStateException;
	
	/**
	 * Create an instanceof expression (e.g. <code>x instanceof java.lang.String</code>
	 * <p>
	 * This must be followed by createExpression for:
	 * 	<code>INSTANCEOF_VALUE</code>
	 * @param forExpression
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createInstanceofExpression(ForExpression forExpression, String type) throws IllegalStateException;
	
	/**
	 * Create an instanceof expression (e.g. <code>x instanceof java.lang.String</code>
	 * <p>
	 * This must be followed by createExpression for:
	 * 	<code>INSTANCEOF_VALUE</code>
	 * @param forExpression
	 * @param type This is the type.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createInstanceofExpression(ForExpression forExpression, IProxyBeanType type) throws IllegalStateException;
	
	/**
	 * Create a method invocation expression (e.g. <code>java.lang.String.valueOf(10)</code>).
	 * When using a string the method invoked will be the one most compatible with the arguments sent. 
	 * This allows overloading a method to occur when coming in from a parse tree, since the parse tree
	 * doesn't know the exact method to use.
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
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 * 
	 * @see IExpression#createTypeReceiver(String)
	 * @since 1.0.0
	 */
	public void createMethodInvocation(ForExpression forExpression, String name, boolean hasReceiver, int argumentCount) throws IllegalStateException, IllegalArgumentException;

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
	 * @param methodProxy The proxy of the method
	 * @param hasReceiver Has a receiver flag. Currently this must always be true.
	 * @param argumentCount Count of number of arguments. May be zero.
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 * 
	 * @see IExpression#createTypeReceiver(String)
	 * @since 1.0.0
	 */
	public void createMethodInvocation(ForExpression forExpression, IProxyMethod methodProxy, boolean hasReceiver, int argumentCount) throws IllegalStateException, IllegalArgumentException;

	/**
	 * Create a prefix expression (e.g. <code>!flag</code> or <code>-(3+4)</code>).
	 * If you are just trying to create a signed numeric literal, just use the createPrimitiveLiteral passing in a
	 * negative value. You don't need to use prefix expression for that.
	 * <p>
	 * <pre>
	 * This must be followed by createExpressions for:
	 * 	<code>PREFIX_OPERAND</code>
	 * </pre>
	 * @param forExpression
	 * @param operator The operator. The values come from IExpressionConstants, the prefix constants.
	 * @throws IllegalStateException
	 * 
	 * @see org.eclipse.jem.internal.proxy.common.IExpressionConstants#PRE_PLUS
	 * @since 1.0.0
	 */
	public void createPrefixExpression(ForExpression forExpression, PrefixOperator operator) throws IllegalStateException;
	
	/**
	 * Create a reference to <code>null</code>.
	 * 
	 * @param forExpression
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createNull(ForExpression forExpression) throws IllegalStateException;
	
	/**
	 * Create a type literal (e.g. <code>java.lang.String.class</code>). This is used when the type is being used as value itself, not
	 * as a receiver for a field or method or constructor or instanceof. Like as an argument to a method.
	 * <p>
	 * Note: If you want a type literal to be an IProxyBeanType, just use {@link IExpression#createProxyExpression(ForExpression, IProxy)} and pass in the
	 * IProxyBeanType.
	 * 
	 * @param forExpression
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createTypeLiteral(ForExpression forExpression, String type) throws IllegalStateException;
	
	/**
	 * Create a type receiver. This is where a type is used as the receiver of a field access or a method invocation.
	 * (e.g. <code>java.lang.String.valueOf(10)</code>). For this the "java.lang.String" IBeanTypeProxy is the type receiver.
	 * <p>
	 * This is unusual in that there is no forExpression. It isn't needed because these are only valid
	 * in certain situations (method or field receiver) and if used anywhere else it is an error.
	 * 
	 * @param type This is the type. It must be fully-qualified and if an inner class, it must have the "$" format.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createTypeReceiver(String type) throws IllegalStateException;

	/**
	 * Create a type receiver. This is where a type is used as the receiver of a field access or a method invocation.
	 * (e.g. <code>java.lang.String.valueOf(10)</code>). For this the "java.lang.String" IProxyBeanType is the type receiver.
	 * <p>
	 * This is unusual in that there is no forExpression. It isn't needed because these are only valid
	 * in certain situations (method or field receiver) and if used anywhere else it is an error.
	 * 
	 * @param type This is the type proxy.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createTypeReceiver(IProxyBeanType type) throws IllegalStateException;
	
	/**
	 * Create a boolean primitive literal (e.g. <code>true</code>).
	 * 
	 * @param forExpression
	 * @param value The boolean value for the literal.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(ForExpression forExpression, boolean value) throws IllegalStateException;
	
	/**
	 * Create a character literal (e.g. <code>'a'</code> or <code>'\n'</code>)
	 * 
	 * @param forExpression
	 * @param value The character value for this literal.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(ForExpression forExpression, char value) throws IllegalStateException;

	/**
	 * Create a byte literal (e.g. <code>(byte)10</code>)
	 * 
	 * @param forExpression
	 * @param value The byte value for this literal.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(ForExpression forExpression, byte value) throws IllegalStateException;

	/**
	 * Create a double literal (e.g. <code>10d</code>)
	 * 
	 * @param forExpression
	 * @param value The double value for this literal.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(ForExpression forExpression, double value) throws IllegalStateException;

	/**
	 * Create a float literal (e.g. <code>10f</code>)
	 * 
	 * @param forExpression
	 * @param value The float value for this literal.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(ForExpression forExpression, float value) throws IllegalStateException;
	
	/**
	 * Create a int literal (e.g. <code>100000</code>)
	 * 
	 * @param forExpression
	 * @param value The int value for this literal.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(ForExpression forExpression, int value) throws IllegalStateException;
	
	/**
	 * Create a long literal (e.g. <code>10l</code>)
	 * 
	 * @param forExpression
	 * @param value The long value for this literal.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(ForExpression forExpression, long value) throws IllegalStateException;
	
	/**
	 * Create a short literal (e.g. <code>(short)10</code>)
	 * 
	 * @param forExpression
	 * @param value The short value for this literal.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createPrimitiveLiteral(ForExpression forExpression, short value) throws IllegalStateException;
	
	/**
	 * Create a string literal (e.g. <code>"asdf"</code>). The value is the actual string, with escapes already
	 * translated into the true character values.
	 *  
	 * @param forExpression
	 * @param value The string value for this literal.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createStringLiteral(ForExpression forExpression, String value) throws IllegalStateException;
	
	/**
	 * Create a subexpression.
	 * <p>
	 * A subexpression allows, at any time, to fork off the expression stack and do some other sets of expressions.
	 * When the cooresponding {@link #createSubexpressionEnd()} is called, all of the expression results on the
	 * stack that accumulated during the subexpression evaluation will be thrown away and the stack will be
	 * what it was at the start of subexpression. Any ExpressionProxies that were resolved during the evaluation 
	 * will not be thrown away and will still be valid.
	 * <p>
	 * This is useful if in the middle of an expression (such as method invocation and an argument is needed) to
	 * go off and get the necessary value. This will allow expressions that require ROOTEXPRESSION state like a 
	 * try/catch. If you know the expression doesn't need this, then it is more efficient to not use subexpression.
	 * <p>
	 * For example:
	 * <pre><code>
	 *   new XYZ(
	 *     {(subexpression)
	 *      try {
	 *         x = 3*y.get();
	 *      } catch (Exception e) {
	 *         x =4;
	 *      }
	 *     (end subexpression)}
	 *     x);
	 * </code></pre>
	 * 
	 * In the above example, we needed to calculate "x" as the argument for XYZ, but it was too complicated and
	 * could throw exceptions. So we used a subexpression instead. 
	 * <p>
	 * Of course the following would of been the better way to do it without subexpressions. But sometimes
	 * your code is in a position that you don't know you need to do this until it is too late.
	 *  <pre><code>
	 *   try {
	 *      x = 3*y.get();
	 *   } catch (Exception e) {
	 *      x =4;
	 *   }
	 *   new XYZ(x);
	 * </code></pre>
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public void createSubexpression() throws IllegalStateException;
	
	public void createSubexpressionEnd() throws IllegalStateException;
	
	/**
	 * Create an expression that has an existing bean proxy as its value.
	 * 
	 * @param forExpression This is for what expression this expression is being created.
	 * @param proxy The proxy that should be used as a value, either a bean proxy or an expression proxy.
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	public void createProxyExpression(ForExpression forExpression, IProxy proxy) throws IllegalStateException;
		
	/**
	 * Create an assignment expression between a VariableReference and an expression. The left operand must be a variable reference (e.g. FieldAccess or
	 * ArrayAccess). The right operand may be any expression that results in a value.
	 * <p>
	 * <pre>
	 * This must be followed by createExpressions for:
	 *  <code>ASSIGNMENT_LEFT</code>
	 * 	<code>ASSIGNMENT_RIGHT</code>
	 * </pre>
	 * @param forExpression
	 * @throws ThrowableProxy
	 * @throws IllegalStateException
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.1.0
	 */
	public void createAssignmentExpression(ForExpression forExpression) throws IllegalStateException;
	
	/**
	 * Create an assignment expression (e.g. x = 3+4) where x will be assigned to the ExpressionProxy. 
	 * It may be used later on as a value in {@link IExpression#createExpressionProxyExpression(int, ExpressionProxy)}.
	 * Or if callbacks were added, the callbacks would be called to return the true IBeanProxy value of the expression proxy when the complete
	 * IExpression has been evaluated. The value of the assignment expression (e.g. x) will be passed on into the next expression.
	 * <p>
	 * <pre>
	 * This must be followed by createExpressions for:
	 * 	<code>ASSIGNMENT_RIGHT</code>
	 * </pre>
	 * <p>
	 * <b>Note:</b> It is guarenteed as part of the contract that expression proxies will be notified through the listeners of the final state in the
	 * order the expression proxies were created. 
	 * @param forExpression This is for what expression this expression is being created.
	 * @return a proxy to the expression value. 
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public ExpressionProxy createProxyAssignmentExpression(ForExpression forExpression) throws IllegalStateException;
	
	/**
	 * Create a reassignment expression. This is like the original proxy assignment except that instead of returning a new proxy, it
	 * reassigns the new value to the existing proxy. This cannot be used on IBeanTypeExpressionProxy's. That is because
	 * they are controlled by the registry and severe errors would occur if they were reassigned.
	 * <p>
	 * <pre>
	 * This must be followed by createExpressions for:
	 * 	<code>ASSIGNMENT_RIGHT</code>
	 * </pre>
	 * <p>
	 * <b>Note:</b> Since we are not creating a new proxy, the notification on the callbacks will be in the original order of proxies. This
	 * does not change the notification position of this proxy.
	 * 
	 * @param forExpression
	 * @param proxy
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException if the expression proxy is for a BeanType instead of just a standard expression proxy.
	 * 
	 * @since 1.1.0
	 */
	public void createProxyReassignmentExpression(ForExpression forExpression, ExpressionProxy proxy) throws IllegalStateException, IllegalArgumentException;
	
	/**
	 * A simple method invocation. This is a helper to make it easier for a simple method invoke. It uses only the 
	 * method proxy (not a string to look it up), it uses a IBeanProxy receiver (not a complicated expression), and the
	 * arguments are a mixture of IBeanProxies and ExpressionProxies. Also it can be called only when the next
	 * expression must be a RootExpression.
	 * 
	 * @param method methodproxy of the method
	 * @param receiver the receiver proxy or <code>null</code> if a static method
	 * @param arguments array of arguments, where each element can only be either <code>null</code> for a null argument, <code>IProxy</code>. The array can be <code>null</code> if no arguments.
	 * @param wantResult <code>true</code> if you want an ExpressionProxy back, otherwise it will return <code>null</code>. For performance reasons, only use <code>true</code> if you really need the expression proxy.
	 * @return expression proxy if "wantResult" was true, else <code>null</code>.
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public ExpressionProxy createSimpleMethodInvoke(IProxyMethod method, IProxy receiver, IProxy[] arguments, boolean wantResult) throws IllegalStateException;

	/**
	 * A simple field access. This is a helper to make it easier for a simple field access. It uses only the 
	 * field proxy (not a string to look it up), and the bean proxy that is the receiver. Also it can be called only when the next
	 * expression must be a RootExpression. It doesn't allow complicated field access, such as <code>fielda.fieldb.fieldc</code>.
	 * Since this is a field access, it will always return an ExpressionProxy. It doesn't make sense to have a simple field access
	 * that doesn't return one.
	 * 
	 * @param field field proxy of the field.
	 * @param receiver the receiver proxy. It may be <code>null</code> for static fields.
	 * @return expression proxy to the result of the access.
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public ExpressionProxy createSimpleFieldAccess(IProxyField field, IProxy receiver) throws IllegalStateException;

	/**
	 * A simple field set. This is a helper to make it easier for a simple field access. It uses only the 
	 * field proxy (not a string to look it up), and the bean proxy that is the receiver. Also it can be called only when the next
	 * expression must be a RootExpression. It doesn't allow complicated field access setting, such as <code>fielda.fieldb.fieldc = 3</code>.
	 * 
	 * @param field field proxy of the field.
	 * @param receiver the receiver proxy. It may be <code>null</code> if this is a static field.
	 * @param value the value proxy to set it to or <code>null</code> if set to null value.
	 * @param wantResult <code>true</code> if you want an ExpressionProxy back, otherwise it will return <code>null</code>. For performance reasons, only use <code>true</code> if you really need the expression proxy.
	 * @return expression proxy if "wantResult" was true, else <code>null</code>.
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public ExpressionProxy createSimpleFieldSet(IProxyField field, IProxy receiver, IProxy value, boolean wantResult) throws IllegalStateException;

	/**
	 * Begin a block. No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * <p>
	 * Eventually {@link Expression#createBlockEnd()} must be called. You should use this pattern:
	 * <pre><code>
	 * exp.createBeginBlock();
	 * try {
	 *   exp.create something else.
	 *   ...
	 * } finally {
	 *   exp.createEndBlock();
	 * }
	 * </code></pre>
	 * 
	 * @return blocknumber for the block just opened. Can be used in {@link Expression#createBlockBreak(int)}.
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public int createBlockBegin() throws IllegalStateException;
	
	/**
	 * Does a break for the specified block number. No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * @param blockNumber
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public void createBlockBreak(int blockNumber) throws IllegalStateException;

	/**
	 * End a block. It will always end the inner most block that currently is on the stack. No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * @throws IllegalStateException
	 * 
	 * @see Expression#createBlockBegin()
	 * @since 1.1.0
	 */
	public void createBlockEnd() throws IllegalStateException;
	
	/**
	 * Create a try/catch statement. No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * There must be at least one catch or finally clause before try end or this is invalid.
	 * <p>
	 * This should be executed in the following way:
	 * <pre><code>
	 * exp.createTry();
	 * try {
	 *   ... create other exceptions ...
	 *   ... create catch/finally clauses as needed.
	 * } finally {
	 *   if (exp.isValid())
	 *     exp.createTryEnd();
	 * }
	 * </code></pre>
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public void createTry() throws IllegalStateException;
	

	/**
	 * Create a catch clause for the inner most try statement. No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * <p>
	 * Using this you can get just the exception as a proxy and/or execute some expressions as part of the catch clause.
	 * <p>
	 * This can be followed by RootExpressions, or another catch, or a finally, or a try end.
	 *  
	 * @param exceptionType the type of the exception to catch on.
	 * @param wantExceptionReturned <code>true</code> if you want an expression proxy for the exception.
	 * @return the ExpressionProxy for the exception if <code>wantExceptionReturned</code> is <code>true</code>, <code>null</code> otherwise.
	 * 
	 * @throws IllegalStateException
	 * @since 1.1.0
	 */
	public ExpressionProxy createTryCatchClause(IProxyBeanType exceptionType, boolean wantExceptionReturned) throws IllegalStateException;;

	/**
	 * Create a catch clause for the inner most try statement. No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * <p>
	 * Using this you can get just the exception as a proxy and/or execute some expressions as part of the catch clause.
	 * <p>
	 * This can be followed by RootExpressions, or another catch, or a finally, or a try end.
	 *  
	 * @param exceptionType the type of the exception to catch on.
	 * @param wantExceptionReturned <code>true</code> if you want an expression proxy for the exception.
	 * @return the ExpressionProxy for the exception if <code>wantExceptionReturned</code> is <code>true</code>, <code>null</code> otherwise.
	 * 
	 * @throws IllegalStateException
	 * @since 1.1.0
	 */
	public ExpressionProxy createTryCatchClause(String exceptionType, boolean wantExceptionReturned) throws IllegalStateException;;

	/**
	 * Create a finally clause for the inner most try statement. No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * There must be no more catch clauses for the try statement after this finally clause.
	 * 
	 * @throws IllegalStateException
	 * @since 1.1.0
	 */
	public void createTryFinallyClause() throws IllegalStateException;;
	
	/**
	 * Create the end of the inner most try statement. No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * There must be no more catch/finally clauses after this except if a new try statement is started.
	 * 
	 * @throws IllegalStateException
	 * @since 1.1.0
	 */
	public void createTryEnd() throws IllegalStateException;;
	
	/**
	 * Create a throw. No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * The next expression is the exception to be thrown.
	 * <p>
	 * This must be followed by createExpression for:
	 * 	<code>THROW_OPERAND</code>
	 *  
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public void createThrow() throws IllegalStateException;
	
	/**
	 * Create a rethrow. This must be within a catch clause or there is an error.
	 * <p> 
	 * This is a shortcut for:
	 * <pre><code>
	 * try {
	 *   .. do something ..
	 * } catch (AnException e) {
	 *   .. do something ..
	 *   throw e;
	 * }
	 * </code></pre>
	 * @throws IllegalStateException
	 * 
	 * @since 1.1.0
	 */
	public void createRethrow() throws IllegalStateException;

	
	/**
	 * Mark the expression stack so that if there are IllegalStateExceptions errors that would make the
	 * expression invalid, you can restore back to this mark point and the expression will now be valid
	 * again and at the state it was when mark was created. All marks must be ended, and at the same nesting
	 * level.
	 * <p>
	 * No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * <p>
	 * It must be used in conjunction with endMark. You must use <code>mark;try/finally{endMark;}</code> because
	 * the mark/endMark must match up. 
	 * <pre><code>
	 * int mark = expression.mark();
	 * try {
	 *   expression.create ...
	 * } catch (IllegalStateException e) {
	 *   process the error.
	 * } finally {
	 *   expression.endMark(mark); // After this, the expression will be valid again.
	 * }
	 * </code></pre>
	 * <p>
	 * However, the following code would be invalid nesting, and will throw an IllegalStateException on the createTryEnd. This is because
	 * we tried to end the Try statement within the mark. This is invalid because if we let it go through it would
	 * of popped the stack and when we got to the endMark the stack would of been popped past it and it could not
	 * be restored to the same state as it was at the time of the mark. The try would of already been ended.
	 * <pre><code>
	 * expression.createTry();
	 * int mark = expression.mark();
	 * try {
	 *   expression.create ...
	 *   expression.createTryEnd();
	 * } catch (IllegalStateException e) {
	 *   process the error. The expression is now invalid.
	 * } finally {
	 *   expression.endMark(mark); // After this, the expression will be valid again, if it had gone invalid.
	 * }
	 * </code></pre>
	 * <p>
	 * If not at RootExpression at time of mark() request, an IllegalStateException will be thrown.
	 * @return mark number, this number will be used in the cooresponding endMark.
	 * @throws IllegalStateException
	 * 
	 * @see IExpression#endMark(int)
	 * @since 1.1.0
	 */
	public int mark() throws IllegalStateException;
	
	/**
	 * The end mark for a mark. 
	 * <p>
	 * No need for a forExpression because it must currently be at ROOTEXPRESSION.
	 * @param markNumber
	 * @throws IllegalStateException
	 * 
	 * @see IExpression#mark()
	 * @since 1.1.0
	 */
	public void endMark(int markNumber) throws IllegalStateException;
}
