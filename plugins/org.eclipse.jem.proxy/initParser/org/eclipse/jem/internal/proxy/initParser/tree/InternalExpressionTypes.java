package org.eclipse.jem.internal.proxy.initParser.tree;

/**
 * These constants are for communicating between the IDE and the proxy side for expression evaluation. It is not meant to be used by customers.
 * 
 * @since 1.0.0
 */
public class InternalExpressionTypes extends AbstractEnum {

	/**
	 * ARRAY ACCESS Expression
	 */
	public final static int ARRAY_ACCESS_EXPRESSION_VALUE = 1;

	public final static InternalExpressionTypes ARRAY_ACCESS_EXPRESSION = new InternalExpressionTypes(ARRAY_ACCESS_EXPRESSION_VALUE,
			"Array Access Expression"); //$NON-NLS-1$

	/**
	 * ARRAY CREATION Expression
	 */
	public final static int ARRAY_CREATION_EXPRESSION_VALUE = 2;

	public final static InternalExpressionTypes ARRAY_CREATION_EXPRESSION = new InternalExpressionTypes(ARRAY_CREATION_EXPRESSION_VALUE,
			"Array Creation Expression"); //$NON-NLS-1$

	/**
	 * ARRAY INITIALIZER expression
	 */
	public final static int ARRAY_INITIALIZER_EXPRESSION_VALUE = 3;

	public final static InternalExpressionTypes ARRAY_INITIALIZER_EXPRESSION = new InternalExpressionTypes(ARRAY_INITIALIZER_EXPRESSION_VALUE,
			"Array Initializer Expression"); //$NON-NLS-1$

	/**
	 * CAST Expression.
	 */
	public final static int CAST_EXPRESSION_VALUE = 4;

	public final static InternalExpressionTypes CAST_EXPRESSION = new InternalExpressionTypes(CAST_EXPRESSION_VALUE, "Cast Expression"); //$NON-NLS-1$

	/**
	 * CONDITIONAL expression
	 */
	public final static int CONDITIONAL_EXPRESSION_VALUE = 5;

	public final static InternalExpressionTypes CONDITIONAL_EXPRESSION = new InternalExpressionTypes(CONDITIONAL_EXPRESSION_VALUE,
			"Conditional Expression"); //$NON-NLS-1$

	/**
	 * CLASS INSTANCE CREATION expression
	 */
	public final static int CLASS_INSTANCE_CREATION_EXPRESSION_VALUE = 6;

	public final static InternalExpressionTypes CLASS_INSTANCE_CREATION_EXPRESSION = new InternalExpressionTypes(
			CLASS_INSTANCE_CREATION_EXPRESSION_VALUE, "Class Instance Creation Expression"); //$NON-NLS-1$

	/**
	 * FIELD ACCESS expression.
	 */
	public final static int FIELD_ACCESS_EXPRESSION_VALUE = 7;

	public final static InternalExpressionTypes FIELD_ACCESS_EXPRESSION = new InternalExpressionTypes(FIELD_ACCESS_EXPRESSION_VALUE,
			"Field Access Expression"); //$NON-NLS-1$

	/**
	 * INSTANCEOF Expression.
	 */
	public final static int INSTANCEOF_EXPRESSION_VALUE = 8;

	public final static InternalExpressionTypes INSTANCEOF_EXPRESSION = new InternalExpressionTypes(INSTANCEOF_EXPRESSION_VALUE,
			"Instanceof Expression"); //$NON-NLS-1$

	/**
	 * Infix expression
	 */
	public final static int INFIX_EXPRESSION_VALUE = 9;

	public final static InternalExpressionTypes INFIX_EXPRESSION = new InternalExpressionTypes(INFIX_EXPRESSION_VALUE, "Infix Expression"); //$NON-NLS-1$

	/**
	 * Method expression.
	 */
	public final static int METHOD_EXPRESSION_VALUE = 10;

	public final static InternalExpressionTypes METHOD_EXPRESSION = new InternalExpressionTypes(METHOD_EXPRESSION_VALUE, "Method Invoke Expression"); //$NON-NLS-1$

	/**
	 * Prefix expression
	 */
	public final static int PREFIX_EXPRESSION_VALUE = 11;

	public final static InternalExpressionTypes PREFIX_EXPRESSION = new InternalExpressionTypes(PREFIX_EXPRESSION_VALUE, "Prefix Expression"); //$NON-NLS-1$

	/**
	 * Push to proxy expression.
	 */
	public final static int PUSH_TO_PROXY_EXPRESSION_VALUE = 12;

	public final static InternalExpressionTypes PUSH_TO_PROXY_EXPRESSION = new InternalExpressionTypes(PUSH_TO_PROXY_EXPRESSION_VALUE,
			"Push to Proxy Expression"); //$NON-NLS-1$

	/**
	 * Push BeanType expression proxy expression.
	 */
	public final static int PUSH_BEANTYPE_EXPRESSIONPROXY_EXPRESSION_VALUE = 13;
	
	public final static InternalExpressionTypes PUSH_BEANTYPE_EXPRESSIONPROXY_EXPRESSION = new InternalExpressionTypes(PUSH_BEANTYPE_EXPRESSIONPROXY_EXPRESSION_VALUE,
			"Push BeanType ExpressionProxy Expression"); //$NON-NLS-1$

	
	/**
	 * Type receiver expression.
	 */
	public final static int TYPERECEIVER_EXPRESSION_VALUE = 14;

	public final static InternalExpressionTypes TYPERECEIVER_EXPRESSION = new InternalExpressionTypes(TYPERECEIVER_EXPRESSION_VALUE,
			"Type Receiver Expression"); //$NON-NLS-1$

	/**
	 * Assignment to an ExpressionProxy expression.
	 */
	public final static int ASSIGNMENT_PROXY_EXPRESSION_VALUE = 15;

	public final static InternalExpressionTypes ASSIGNMENT_PROXY_EXPRESSION = new InternalExpressionTypes(ASSIGNMENT_PROXY_EXPRESSION_VALUE,
			"Assignment to Proxy Expression"); //$NON-NLS-1$

	/**
	 * Push expression proxy value expression.
	 */
	public final static int PUSH_TO_EXPRESSION_PROXY_EXPRESSION_VALUE = 16;

	public final static InternalExpressionTypes PUSH_TO_EXPRESSION_PROXY_EXPRESSION = new InternalExpressionTypes(
			PUSH_TO_EXPRESSION_PROXY_EXPRESSION_VALUE, "Push to Expression Proxy Expression"); //$NON-NLS-1$

	/**
	 * Assignment expression.
	 */
	public final static int ASSIGNMENT_EXPRESSION_VALUE = 17;

	public final static InternalExpressionTypes ASSIGNMENT_EXPRESSION = new InternalExpressionTypes(ASSIGNMENT_EXPRESSION_VALUE,
			"Assignment Expression"); //$NON-NLS-1$

	/**
	 * Block End expression.
	 */
	public final static int BLOCK_BEGIN_EXPRESSION_VALUE = 18;
	
	public final static InternalExpressionTypes BLOCK_BEGIN_EXPRESSION = new InternalExpressionTypes(BLOCK_BEGIN_EXPRESSION_VALUE,
			"Begin block Expression"); //$NON-NLS-1$

	/**
	 * Block End expression.
	 */
	public final static int BLOCK_BREAK_EXPRESSION_VALUE = 19;
	
	public final static InternalExpressionTypes BLOCK_BREAK_EXPRESSION = new InternalExpressionTypes(BLOCK_BREAK_EXPRESSION_VALUE,
			"Break block Expression"); //$NON-NLS-1$

	/**
	 * Block End expression.
	 */
	public final static int BLOCK_END_EXPRESSION_VALUE = 20;
	
	public final static InternalExpressionTypes BLOCK_END_EXPRESSION = new InternalExpressionTypes(BLOCK_END_EXPRESSION_VALUE,
			"End block Expression"); //$NON-NLS-1$

	/**
	 * Try Begin expression.
	 */
	public final static int TRY_BEGIN_EXPRESSION_VALUE = 21;
	
	public final static InternalExpressionTypes TRY_BEGIN_EXPRESSION = new InternalExpressionTypes(TRY_BEGIN_EXPRESSION_VALUE,
			"Begin try Expression"); //$NON-NLS-1$

	/**
	 * Try Catch expression.
	 */
	public final static int TRY_CATCH_EXPRESSION_VALUE = 22;
	
	public final static InternalExpressionTypes TRY_CATCH_EXPRESSION = new InternalExpressionTypes(TRY_CATCH_EXPRESSION_VALUE,
			"Try catch Expression"); //$NON-NLS-1$
	
	/**
	 * Try Finally expression.
	 */
	public final static int TRY_FINALLY_EXPRESSION_VALUE = 23;
	
	public final static InternalExpressionTypes TRY_FINALLY_EXPRESSION = new InternalExpressionTypes(TRY_FINALLY_EXPRESSION_VALUE,
			"Try finally Expression"); //$NON-NLS-1$

	/**
	 * Try End expression.
	 */
	public final static int TRY_END_EXPRESSION_VALUE = 24;
	
	public final static InternalExpressionTypes TRY_END_EXPRESSION = new InternalExpressionTypes(TRY_END_EXPRESSION_VALUE,
			"End try Expression"); //$NON-NLS-1$
	
	/**
	 * Throw expression.
	 */
	public final static int THROW_EXPRESSION_VALUE = 25;
	
	public final static InternalExpressionTypes THROW_EXPRESSION = new InternalExpressionTypes(THROW_EXPRESSION_VALUE,
			"Throw Expression"); //$NON-NLS-1$

	/**
	 * Rethrow expression.
	 */
	public final static int RETHROW_EXPRESSION_VALUE = 26;
	
	public final static InternalExpressionTypes RETHROW_EXPRESSION = new InternalExpressionTypes(RETHROW_EXPRESSION_VALUE,
			"Rethrow Expression"); //$NON-NLS-1$

	/**
	 * Push Method Lookup expression proxy expression.
	 */
	public final static int PUSH_METHOD_EXPRESSIONPROXY_EXPRESSION_VALUE = 27;
	
	public final static InternalExpressionTypes PUSH_METHOD_EXPRESSIONPROXY_EXPRESSION = new InternalExpressionTypes(PUSH_METHOD_EXPRESSIONPROXY_EXPRESSION_VALUE,
			"Push Method ExpressionProxy Expression"); //$NON-NLS-1$

	/**
	 * Push Field Lookup expression proxy expression.
	 */
	public final static int PUSH_FIELD_EXPRESSIONPROXY_EXPRESSION_VALUE = 28;
	
	public final static InternalExpressionTypes PUSH_FIELD_EXPRESSIONPROXY_EXPRESSION = new InternalExpressionTypes(PUSH_FIELD_EXPRESSIONPROXY_EXPRESSION_VALUE,
			"Push Field ExpressionProxy Expression"); //$NON-NLS-1$

	/**
	 * Push If/else (the clauses) expression proxy expression.
	 */
	public final static int IF_ELSE_EXPRESSION_VALUE = 29;
	
	public final static InternalExpressionTypes IF_ELSE_EXPRESSION = new InternalExpressionTypes(IF_ELSE_EXPRESSION_VALUE,
			"Push If/else Expression"); //$NON-NLS-1$

	/**
	 * Push If/else condition test expression proxy expression.
	 */
	public final static int IF_TEST_EXPRESSION_VALUE = 30;
	
	public final static InternalExpressionTypes IF_TEST_EXPRESSION = new InternalExpressionTypes(IF_TEST_EXPRESSION_VALUE,
			"Push If Test Expression"); //$NON-NLS-1$


	/**
	 * Push new instance from init string proxy expression.
	 */
	public final static int NEW_INSTANCE_VALUE = 31;
	
	public final static InternalExpressionTypes NEW_INSTANCE = new InternalExpressionTypes(NEW_INSTANCE_VALUE,
			"Push New Instance Expression"); //$NON-NLS-1$


	/**
	 * Push mark proxy expression.
	 */
	public final static int MARK_VALUE = 32;
	
	public final static InternalExpressionTypes MARK = new InternalExpressionTypes(MARK_VALUE,
			"Push Mark Expression"); //$NON-NLS-1$

	/**
	 * Push end mark proxy expression.
	 */
	public final static int ENDMARK_VALUE = 33;
	
	public final static InternalExpressionTypes ENDMARK = new InternalExpressionTypes(ENDMARK_VALUE,
			"Push End Mark Expression"); //$NON-NLS-1$

	private InternalExpressionTypes(int value, String name) {
		super(value, name);
	}
}