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
 * $RCSfile: IDEExpression.java,v $ $Revision: 1.1 $ $Date: 2004/02/03 23:18:36 $
 */
package org.eclipse.jem.internal.proxy.ide;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.initParser.EvaluationException;
import org.eclipse.jem.internal.proxy.initParser.MethodHelper;
import org.eclipse.jem.internal.proxy.initParser.tree.ExpressionProcesser;

/**
 * IDE expression processing.
 * 
 * @since 1.0.0
 */
public class IDEExpression extends Expression {

	private final IDEStandardBeanTypeProxyFactory beantypefactory;
	protected final ExpressionProcesser eproc = new ExpressionProcesser();

	/**
	 * Create the IDEExpression
	 * 
	 * @param registry
	 * 
	 * @since 1.0.0
	 */
	public IDEExpression(ProxyFactoryRegistry registry) {
		super(registry);
		beantypefactory = (IDEStandardBeanTypeProxyFactory) registry.getBeanTypeProxyFactory();
	}

	protected final IDEProxyFactoryRegistry getIDERegistry() {
		return (IDEProxyFactoryRegistry) registry;
	}

	protected final IDEStandardBeanTypeProxyFactory getIDEBeanTypeFactory() {
		return beantypefactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushToProxy(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	protected void pushToProxy(IBeanProxy proxy) throws ThrowableProxy {
		if (proxy == null)
			eproc.pushExpression(null, MethodHelper.NULL_TYPE);
		else
			eproc.pushExpression(((IDEBeanProxy) proxy).getBean(), ((IDEBeanTypeProxy) proxy.getTypeProxy()).getTypeClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.Expression#closeProxy()
	 */
	protected void closeProxy() {
		eproc.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pullProxyValue()
	 */
	protected IBeanProxy pullProxyValue() throws NoExpressionValueException {
		Object result[] = new Object[2];
		eproc.pullValue(result);
		return getIDERegistry().getBeanProxy((Class) result[1], result[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushCastToProxy(java.lang.Object)
	 */
	protected void pushCastToProxy(Object type) throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushCast(getBeanTypeProxy(type).getTypeClass());
		} catch (ClassCastException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushInstanceofToProxy(java.lang.Object)
	 */
	protected void pushInstanceofToProxy(Object type) throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushInstanceof(getBeanTypeProxy(type).getTypeClass());
		} catch (RuntimeException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushTypeLiteralToProxy(java.lang.String)
	 */
	protected void pushTypeLiteralToProxy(String type) throws ThrowableProxy {
		eproc.pushExpression(getBeanTypeProxy(type).getTypeClass(), Class.class);
	}
	
	/**
	 * Get the BeanType proxy and test if valid. Throw ThrowableProxy if not valid.
	 * 
	 * @param type Must be either String or an IDEBeanTypeProxy. If String, it will look it up.
	 * @return 
	 * @throws ThrowableProxy
	 * 
	 * @since 1.0.0
	 */
	protected IDEBeanTypeProxy getBeanTypeProxy(Object type) throws ThrowableProxy {
		IDEBeanTypeProxy typeProxy = null;
		if (type instanceof String)		
			typeProxy = (IDEBeanTypeProxy) registry.getBeanTypeProxyFactory().getBeanTypeProxy((String) type);
		else
			typeProxy = (IDEBeanTypeProxy) type;
		if (!typeProxy.isValid()) {
			throw new IDEThrowableProxy(
					new Exception(typeProxy.getInitializationError()),
					getIDEBeanTypeFactory().getBeanTypeProxy(Exception.class));
		} else
			return typeProxy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushPrefixToProxy(int)
	 */
	protected void pushPrefixToProxy(int operator) throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushPrefix(operator);
		} catch (RuntimeException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushInfixToProxy(int, int)
	 */
	protected void pushInfixToProxy(int operator, int operandType) throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushInfix(operator, operandType);
		} catch (RuntimeException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushArrayAccessToProxy(int)
	 */
	protected void pushArrayAccessToProxy(int indexCount) throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushArrayAccess(indexCount);
		} catch (RuntimeException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushArrayCreationToProxy(java.lang.Object, int)
	 */
	protected void pushArrayCreationToProxy(Object type, int dimensionCount) throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushArrayCreation(getBeanTypeProxy(type).getTypeClass(), dimensionCount);
		} catch (RuntimeException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushArrayInitializerToProxy(java.lang.Object, int)
	 */
	protected void pushArrayInitializerToProxy(Object type, int expressionCount) throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushArrayInitializer(getBeanTypeProxy(type).getTypeClass(), expressionCount);
		} catch (RuntimeException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushClassInstanceCreationToProxy(java.lang.Object, int)
	 */
	protected void pushClassInstanceCreationToProxy(Object type, int argumentCount) throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushClassInstanceCreation(getBeanTypeProxy(type).getTypeClass(), argumentCount);
		} catch (InvocationTargetException e) {
			throw new IDEThrowableProxy(e.getTargetException(), getIDEBeanTypeFactory().getBeanTypeProxy(e.getTargetException().getClass()));
		} catch (NoExpressionValueException e) {
			throw e;
		} catch (ThrowableProxy e) {
			throw e;
		} catch (EvaluationException e) {
			throw new IDEThrowableProxy(e.getOriginalException(), getIDEBeanTypeFactory().getBeanTypeProxy(e.getOriginalException().getClass()));
		} catch (Exception e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pustTypeReceiverToProxy(java.lang.Object)
	 */
	protected void pushTypeReceiverToProxy(Object type) throws ThrowableProxy {
		Class c = getBeanTypeProxy(type).getTypeClass();
		// The expressionType is used for receivers in field/method invocation to find the field/method. So we want the type to be type we have.
		// Also then the receiver to invoke against is the expression value, which is the type.
		eproc.pushExpression(c, c);	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushFieldAccessToProxy(java.lang.String, boolean)
	 */
	protected void pushFieldAccessToProxy(String fieldName, boolean hasReceiver) throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushFieldAccess(fieldName, hasReceiver);
		} catch (RuntimeException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		} catch (NoExpressionValueException e) {
			throw e;
		} catch (NoSuchFieldException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		} catch (IllegalAccessException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		}		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushMethodInvocationToProxy(java.lang.String, boolean, int)
	 */
	protected void pushMethodInvocationToProxy(String methodName, boolean hasReceiver, int argCount)
		throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushMethodInvocation(methodName, hasReceiver, argCount);
		} catch (RuntimeException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		} catch (NoExpressionValueException e) {
			throw e;
		} catch (EvaluationException e) {
			throw new IDEThrowableProxy(e.getOriginalException(), getIDEBeanTypeFactory().getBeanTypeProxy(e.getOriginalException().getClass()));
		} catch (IllegalAccessException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		} catch (InvocationTargetException e) {
			throw new IDEThrowableProxy(e.getTargetException(), getIDEBeanTypeFactory().getBeanTypeProxy(e.getTargetException().getClass()));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushConditionalToProxy(int)
	 */
	protected void pushConditionalToProxy(int expressionType) throws ThrowableProxy, NoExpressionValueException {
		try {
			eproc.pushConditional(expressionType);
		} catch (RuntimeException e) {
			throw new IDEThrowableProxy(e, getIDEBeanTypeFactory().getBeanTypeProxy(e.getClass()));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushInvoke()
	 */
	protected void pushInvoke() {
		// In the IDE case do nothing. Nothing is pending.
	}

}
