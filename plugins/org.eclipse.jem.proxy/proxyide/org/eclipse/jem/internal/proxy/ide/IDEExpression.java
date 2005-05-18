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
 * $RCSfile: IDEExpression.java,v $ $Revision: 1.7 $ $Date: 2005/05/18 18:41:20 $
 */
package org.eclipse.jem.internal.proxy.ide;

import java.util.*;

import org.eclipse.core.runtime.Platform;

import org.eclipse.jem.internal.proxy.common.MethodHelper;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent;
import org.eclipse.jem.internal.proxy.initParser.tree.*;

/**
 * IDE expression processing.
 * 
 * @since 1.0.0
 */
public class IDEExpression extends Expression {

	private final IDEStandardBeanTypeProxyFactory beantypefactory;
	protected final ExpressionProcesser eproc;
	{
		boolean useTracing = "true".equalsIgnoreCase(Platform.getDebugOption(ProxyPlugin.getPlugin().getBundle().getSymbolicName() + ProxyLaunchSupport.EXPRESSION_TRACING));
		long threshold = Long.getLong(Platform.getDebugOption(ProxyPlugin.getPlugin().getBundle().getSymbolicName() + ProxyLaunchSupport.EXPRESSION_TRACEING_TIMER_THRESHOLD), -1L).longValue();
		eproc = new ExpressionProcesser(useTracing, threshold);
	}
	

	private void processExpressionError() throws ThrowableProxy, NoExpressionValueException {
		if (!eproc.noErrors())
			if (eproc.isNoExpressionValue())
				throw (NoExpressionValueException) eproc.getErrorThrowable();
			else {
				Throwable t = eproc.getErrorThrowable();
				if (t instanceof ThrowableProxy)
					throw (ThrowableProxy) t;
				else
					throw new IDEThrowableProxy(eproc.getErrorThrowable(), beantypefactory.getBeanTypeProxy(t.getClass()));
			}
	}
	
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
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushToProxy(org.eclipse.jem.internal.proxy.core.IProxy)
	 */
	protected void pushToProxy(IProxy proxy) {
		if (proxy == null)
			eproc.pushExpression(null, MethodHelper.NULL_TYPE);
		else if (proxy.isBeanProxy())
			eproc.pushExpression(((IDEBeanProxy) proxy).getBean(), ((IDEBeanTypeProxy) ((IBeanProxy) proxy).getTypeProxy()).getTypeClass());
		else
			eproc.pushExpressionProxy(((ExpressionProxy) proxy).getProxyID());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.Expression#closeProxy()
	 */
	protected void closeProxy() {
		methodExpressionProxies = fieldExpressionProxies = null;
		eproc.close();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pullProxyValue(int, java.util.List)
	 */
	protected IBeanProxy pullProxyValue(int proxycount, List expressionProxies) throws ThrowableProxy, NoExpressionValueException {
		processExtensionProxies(proxycount, expressionProxies);
		processExpressionError();
		Object result[] = new Object[2];
		eproc.pullValue(result);
		IBeanProxy resultProxy = getIDERegistry().getBeanProxy((Class) result[1], result[0]);
		return resultProxy;
	}
	
	private void processExtensionProxies(int proxycount, List expressionProxies) {
		if (proxycount > 0) {
			int len = expressionProxies.size();
			Object[] proxyResolution = new Object[2];
			for (int i = 0; i < len; i++) {
				ExpressionProxy ep = (ExpressionProxy) expressionProxies.get(i);
				if (ep != null) {
					try {
						eproc.pullExpressionProxyValue(ep.getProxyID(), proxyResolution);
						if (proxyResolution[1] != Void.TYPE)
							fireProxyResolved(ep, getIDERegistry().getBeanProxy((Class) proxyResolution[1], proxyResolution[0]));
						else
							fireProxyVoid(ep);
					} catch (NoExpressionValueException e) {
						fireProxyNotResolved(ep);
					}
				}
			}
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushCastToProxy(org.eclipse.jem.internal.proxy.core.IProxyBeanType)
	 */
	protected void pushCastToProxy(IProxyBeanType type) {
		try {
			eproc.pushCast(getIDEBeanTypeProxy(type).getTypeClass());
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushInstanceofToProxy(org.eclipse.jem.internal.proxy.core.IProxyBeanType)
	 */
	protected void pushInstanceofToProxy(IProxyBeanType type) {
		try {
			eproc.pushInstanceof(getIDEBeanTypeProxy(type).getTypeClass());
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		}
	}
	
	/**
	 * Get the BeanType proxy and test if valid. Throw ThrowableProxy if not valid.
	 * 
	 * @param type 
	 * @return 
	 * @throws ThrowableProxy
	 * 
	 * @since 1.0.0
	 */
	protected IDEBeanTypeProxy getIDEBeanTypeProxy(IProxyBeanType type) throws ThrowableProxy {
		IDEBeanTypeProxy typeProxy;
		if (type.isExpressionProxy()) {
			// It should already be resolved at this point.
			typeProxy = ((IDEBeanTypeExpressionProxy) type).getBeanTypeProxy();
		} else
			typeProxy = (IDEBeanTypeProxy) type;
		if (!typeProxy.isValid()) {
			throw new IDEThrowableProxy(
					new Exception(typeProxy.getInitializationError()),
					getIDEBeanTypeFactory().getBeanTypeProxy(Exception.class));
		} else
			return typeProxy;
	}

	
	/**
	 * Get the BeanType proxy and test if valid. Throw ThrowableProxy if not valid.
	 * @param type
	 * @return
	 * @throws ThrowableProxy
	 * 
	 * @since 1.1.0
	 */
	protected IDEBeanTypeProxy getIDEBeanTypeProxy(String type) throws ThrowableProxy {
		return (IDEBeanTypeProxy) registry.getBeanTypeProxyFactory().getBeanTypeProxy(type);
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushPrefixToProxy(org.eclipse.jem.internal.proxy.initParser.tree.PrefixOperator)
	 */
	protected void pushPrefixToProxy(PrefixOperator operator) {
		eproc.pushPrefix(operator);
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushInfixToProxy(org.eclipse.jem.internal.proxy.initParser.tree.InfixOperator, org.eclipse.jem.internal.proxy.initParser.tree.InternalInfixOperandType)
	 */
	protected void pushInfixToProxy(InfixOperator operator, InternalInfixOperandType operandType) {
		eproc.pushInfix(operator, operandType);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushArrayAccessToProxy(int)
	 */
	protected void pushArrayAccessToProxy(int indexCount) {
		eproc.pushArrayAccess(indexCount);
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushArrayCreationToProxy(org.eclipse.jem.internal.proxy.core.IProxyBeanType, int)
	 */
	protected void pushArrayCreationToProxy(IProxyBeanType type, int dimensionCount) {
		try {
			eproc.pushArrayCreation(getIDEBeanTypeProxy(type).getTypeClass(), dimensionCount);
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		}
	}

	protected void pushArrayInitializerToProxy(IProxyBeanType type, int stripCount, int expressionCount) {
		try {
			eproc.pushArrayInitializer(getIDEBeanTypeProxy(type).getTypeClass(), stripCount, expressionCount);
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushClassInstanceCreationToProxy(org.eclipse.jem.internal.proxy.core.IProxyBeanType, int)
	 */
	protected void pushClassInstanceCreationToProxy(IProxyBeanType type, int argumentCount) {
		try {
			eproc.pushClassInstanceCreation(getIDEBeanTypeProxy(type).getTypeClass(), argumentCount);
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushTypeReceiverToProxy(org.eclipse.jem.internal.proxy.core.IProxyBeanType)
	 */
	protected void pushTypeReceiverToProxy(IProxyBeanType type) {
		try {
			Class c = getIDEBeanTypeProxy(type).getTypeClass();
			eproc.pushExpression(c, c);	// When as a receiver, the type is the same as the receiver. 
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		} catch (RuntimeException e) {
			eproc.processException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushFieldAccessToProxy(java.lang.Object, boolean)
	 */
	protected void pushFieldAccessToProxy(Object field, boolean hasReceiver) {
		boolean isString = field instanceof String;
		try {
			eproc.pushFieldAccess(isString ? field : getIDEFieldProxy((IProxyField) field).getBean(), isString, hasReceiver);
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		}		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushMethodInvocationToProxy(java.lang.Object, boolean, int)
	 */
	protected void pushMethodInvocationToProxy(Object method, boolean hasReceiver, int argCount) {
		boolean isString = method instanceof String;
		try {
			eproc.pushMethodInvocation(isString ? method : getIDEMethodProxy((IProxyMethod) method).getBean(), isString, hasReceiver, argCount);
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushConditionalToProxy(org.eclipse.jem.internal.proxy.initParser.tree.InternalConditionalOperandType)
	 */
	protected void pushConditionalToProxy(InternalConditionalOperandType expressionType) {
		eproc.pushConditional(expressionType);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushInvoke()
	 */
	protected void pushInvoke(int proxycount, List expressionProxies) throws ThrowableProxy, NoExpressionValueException {
		// In the IDE case do nothing. Nothing is pending. But still need to handle proxy resolution.
		processExtensionProxies(proxycount, expressionProxies);
		processExpressionError(); 
	}
	
	/**
	 * This is used as both an ExpressionProxy (i.e. IDE side) and the Expressions expression proxy result on the other side.
	 * This makes it easier to just use same instance on both sides.
	 * 
	 * @since 1.1.0
	 */
	protected static class IDEExpressionProxy extends ExpressionProxy implements InternalExpressionProxy {
		
		protected IDEExpressionProxy(int proxyid, int proxyType, Expression expression) {
			super(proxyid, proxyType, expression);
		}
		
		private Object value;
		private Class type;
		private boolean set;
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy#dispose()
		 */
		protected void dispose() {
			super.dispose();
			value = null;
			type = null;
			set = false;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.initParser.tree.InternalExpressionProxy#getType()
		 */
		public Class getType() {
			return type;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.initParser.tree.InternalExpressionProxy#getValue()
		 */
		public Object getValue() {
			return value;
		}
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.initParser.tree.InternalExpressionProxy#setProxy(java.lang.Object, java.lang.Class)
		 */
		public void setProxy(Object value, Class type) {
			this.value = value;
			this.type = type;
			set = true;
		}
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.initParser.tree.InternalExpressionProxy#isSet()
		 */
		public boolean isSet() {
			return set;
		}
	}
	
	/**
	 * The Expression proxy for IDE BeanTypes.
	 * 
	 * @since 1.1.0
	 */
	protected static class IDEBeanTypeExpressionProxy extends IDEExpressionProxy implements IBeanTypeExpressionProxy {
		
		private String typeName;
		private IDEBeanTypeProxy resolvedProxy;

		/**
		 * @param proxyid
		 * 
		 * @since 1.1.0
		 */
		public IDEBeanTypeExpressionProxy(int proxyid, Expression expression) {
			super(proxyid, BEANTYPE_EXPRESSION_PROXY, expression);
		}
		
		/*
		 *  (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeExpressionProxy#setTypeName(java.lang.String)
		 */
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		
		/*
		 *  (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.IProxyBeanType#getTypeName()
		 */
		public String getTypeName() {
			return typeName;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy#toString()
		 */
		public String toString() {
			return super.toString()+" - "+getTypeName();
		}

		/**
		 * Called by IDEExpression to resolve the beantype.
		 * @param beantypeProxy
		 * 
		 * @since 1.1.0
		 */
		void setProxy(IDEBeanTypeProxy beantypeProxy) {
			this.resolvedProxy = beantypeProxy;
			setProxy(resolvedProxy.getTypeClass(), Class.class);
		}
		
		/**
		 * Called by IDEExpression to get the resolved beantype proxy.
		 * @return
		 * 
		 * @since 1.1.0
		 */
		IDEBeanTypeProxy getBeanTypeProxy() {
			return resolvedProxy;
		}

		/*
		 *  (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.IProxyBeanType#getMethodProxy(org.eclipse.jem.internal.proxy.core.IExpression, java.lang.String, org.eclipse.jem.internal.proxy.core.IProxyBeanType[])
		 */
		public IProxyMethod getMethodProxy(IExpression expression, String methodName, IProxyBeanType[] parameterTypes) {
			IProxyMethod method = ((IDEExpression) expression).getMethodExpressionProxy(this, methodName, parameterTypes);
			if (method == null) {
				// Need to go to the expression and create it.
				method = ((Expression) expression).createMethodExpressionProxy(this, methodName, parameterTypes);
			}
			return method;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.IProxyBeanType#getMethodProxy(org.eclipse.jem.internal.proxy.core.IExpression, java.lang.String, java.lang.String[])
		 */
		public IProxyMethod getMethodProxy(IExpression expression, String methodName, String[] parameterTypes) {
			return ((IDEMethodProxyFactory) expression.getRegistry().getMethodProxyFactory()).getMethodProxy(expression, this, methodName, parameterTypes);
		}
		
		public IProxyMethod getMethodProxy(IExpression expression, String methodName) {
			return getMethodProxy(expression, methodName, (IProxyBeanType[]) null);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.IProxyBeanType#getFieldProxy(org.eclipse.jem.internal.proxy.core.IExpression, java.lang.String)
		 */
		public IProxyField getFieldProxy(IExpression expression, String fieldName) {
			IProxyField field = ((IDEExpression) expression).getFieldExpressionProxy(this, fieldName);
			if (field == null) {
				// Need to go to the expression and create it.
				field = ((Expression) expression).createFieldExpressionProxy(this, fieldName);
			}
			return field;
		}
	}
	
	/**
	 * The Expression proxy for IDE BeanTypes.
	 * 
	 * @since 1.1.0
	 */
	protected static class IDEMethodExpressionProxy extends IDEExpressionProxy implements IProxyMethod {
		
		private String methodName;
		private IDEMethodProxy resolvedProxy;
		private ThrowableProxy errorThrowable;

		/**
		 * @param proxyid
		 * 
		 * @since 1.1.0
		 */
		public IDEMethodExpressionProxy(int proxyid, Expression expression) {
			super(proxyid, METHOD_EXPRESSION_PROXY, expression);
		}
		
		/**
		 * Set by IDEExpression with the method name.
		 * @param methodName
		 * 
		 * @since 1.1.0
		 */
		void setMethodName(String methodName) {
			this.methodName = methodName;
		}
				
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy#toString()
		 */
		public String toString() {
			return super.toString()+" - "+methodName;
		}

		/**
		 * Called by IDEExpression to resolve the beantype.
		 * @param methodProxy
		 * 
		 * @since 1.1.0
		 */
		void setProxy(IDEMethodProxy methodProxy) {
			this.resolvedProxy = methodProxy;
			setProxy(resolvedProxy.getBean(), Class.class);
		}
		
		/**
		 * Called by IDEExpression to say there was error in creating the proxy.
		 * @param errorThrowable
		 * 
		 * @since 1.1.0
		 */
		void setThrowable(ThrowableProxy errorThrowable) {
			this.errorThrowable = errorThrowable;
		}
		
		/**
		 * Called by IDEExpression to get the resolved method proxy.
		 * @return
		 * @throws ThrowableProxy
		 * 
		 * @since 1.1.0
		 */
		IDEMethodProxy getMethodProxy() throws ThrowableProxy {
			if (errorThrowable != null)
				throw errorThrowable;
			return resolvedProxy;
		}
	}
	
	protected static class IDEFieldExpressionProxy extends IDEExpressionProxy implements IProxyField {
		
		private String fieldName;
		private IDEFieldProxy resolvedProxy;
		private ThrowableProxy errorThrowable;

		/**
		 * @param proxyid
		 * 
		 * @since 1.1.0
		 */
		public IDEFieldExpressionProxy(int proxyid, Expression expression) {
			super(proxyid, FIELD_EXPRESSION_PROXY, expression);
		}
		
		/**
		 * Set by IDEExpression with the method name.
		 * @param fieldName
		 * 
		 * @since 1.1.0
		 */
		void setField(String fieldName) {
			this.fieldName = fieldName;
		}
				
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy#toString()
		 */
		public String toString() {
			return super.toString()+" - "+fieldName;
		}

		/**
		 * Called by IDEExpression to resolve the beantype.
		 * @param fieldProxy
		 * 
		 * @since 1.1.0
		 */
		void setProxy(IDEFieldProxy fieldProxy) {
			this.resolvedProxy = fieldProxy;
			setProxy(resolvedProxy.getBean(), Class.class);
		}
		
		/**
		 * Called by IDEExpression to say there was error in creating the proxy.
		 * @param errorThrowable
		 * 
		 * @since 1.1.0
		 */
		void setThrowable(ThrowableProxy errorThrowable) {
			this.errorThrowable = errorThrowable;
		}
		
		/**
		 * Called by IDEExpression to get the resolved field proxy.
		 * @return
		 * @throws ThrowableProxy
		 * 
		 * @since 1.1.0
		 */
		IDEFieldProxy getFieldProxy() throws ThrowableProxy {
			if (errorThrowable != null)
				throw errorThrowable;
			return resolvedProxy;
		}
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#createExpressionProxy(int, int)
	 */
	protected ExpressionProxy createExpressionProxy(int proxyType, int proxyID) {
		switch (proxyType) {
			case NORMAL_EXPRESSION_PROXY:
			default:
				return new IDEExpressionProxy(proxyID, NORMAL_EXPRESSION_PROXY, this);
			
			case BEANTYPE_EXPRESSION_PROXY:
				return new IDEBeanTypeExpressionProxy(proxyID, this);
				
			case METHOD_EXPRESSION_PROXY:
				return new IDEMethodExpressionProxy(proxyID, this);
				
			case FIELD_EXPRESSION_PROXY:
				return new IDEFieldExpressionProxy(proxyID, this);
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushAssignmentToProxy(org.eclipse.jem.internal.proxy.core.ExpressionProxy)
	 */
	protected void pushAssignmentToProxy(ExpressionProxy proxy) {
		eproc.pushAssignment((InternalExpressionProxy) proxy);
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushAssignmentToProxy()
	 */
	protected void pushAssignmentToProxy() {
		eproc.pushAssignment();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushBlockBeginToProxy(int)
	 */
	protected void pushBlockBeginToProxy(int blockNumber) {
		eproc.pushBlockBegin(blockNumber);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushBlockEndToProxy(int)
	 */
	protected void pushBlockEndToProxy(int blockNumber) {
		eproc.pushBlockEnd(blockNumber);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushBlockBreakToProxy(int)
	 */
	protected void pushBlockBreakToProxy(int blockNumber) {
		eproc.pushBlockBreak(blockNumber);
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushTryBeginToProxy(int)
	 */
	protected void pushTryBeginToProxy(int tryNumber) {
		eproc.pushTryBegin(tryNumber);
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushTryCatchClauseToProxy(int, org.eclipse.jem.internal.proxy.core.IProxyBeanType, org.eclipse.jem.internal.proxy.core.ExpressionProxy)
	 */
	protected void pushTryCatchClauseToProxy(int tryNumber, IProxyBeanType exceptionType, ExpressionProxy ep) {
		try {
			eproc.pushTryCatchClause(tryNumber, getIDEBeanTypeProxy(exceptionType).getTypeClass(), (InternalExpressionProxy) ep);
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushTryFinallyClauseToProxy(int)
	 */
	protected void pushTryFinallyClauseToProxy(int tryNumber) {
		eproc.pushTryFinallyClause(tryNumber);
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushTryEndToProxy(int)
	 */
	protected void pushTryEndToProxy(int tryNumber) {
		eproc.pushTryEnd(tryNumber);
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushThrowToProxy()
	 */
	protected void pushThrowToProxy() {
		eproc.pushThrowException();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushRethrowToProxy(int)
	 */
	protected void pushRethrowToProxy(int tryNumber) {
		eproc.pushTryRethrow(tryNumber);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushBeanTypeToProxy(org.eclipse.jem.internal.proxy.core.IBeanTypeExpressionProxy)
	 */
	protected void pushBeanTypeToProxy(IBeanTypeExpressionProxy proxy) {
		try {
			IDEBeanTypeExpressionProxy ep = (IDEBeanTypeExpressionProxy) proxy;
			IDEBeanTypeProxy typeProxy = getIDEBeanTypeProxy(proxy.getTypeName());
			ep.setProxy(typeProxy);
			eproc.allocateExpressionProxy(ep);
			if (!typeProxy.isValid()) {
				throw new IDEThrowableProxy(
						new Exception(typeProxy.getInitializationError()),
						getIDEBeanTypeFactory().getBeanTypeProxy(Exception.class));
			}
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		}
	}
	
	/**
	 * Get the map of IProxyBeanTypes for a beantype name. Meant to be used only in conjunction with IDEStandardBeanTypeFactory.
	 * It is here so the IDDEStandardBeanTypeFactory can store pending proxies per expression.
	 * 
	 * @param beanType
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IProxyBeanType getBeanType(String beanTypeName) {
		if (beanTypeCache == null)
			beanTypeCache = new HashMap();
		return (IProxyBeanType) beanTypeCache.get(beanTypeName);
	}
	
	/**
	 * Add the beantype expression proxy to the map of bean type expression proxies. Used in conjunction with IDEStandardBeanTypeFactory.
	 * It is here so the IDEStandardBeanTypeFactory can store pending proxies per expression.
	 * @param beanTypeName
	 * @param beantype
	 * 
	 * @since 1.1.0
	 */
	public void addBeanType(String beanTypeName, IProxyBeanType beantype) {
		beanTypeCache.put(beanTypeName, beantype);
	}
	
	/**
	 * Remove the beantype expression proxy from the map. This is called because there was a rollback due to an endmark.
	 * @param beanTypeName
	 * 
	 * @since 1.1.0
	 */
	public void removeBeanType(String beanTypeName) {
		beanTypeCache.remove(beanTypeName);
	}

	/**
	 * Keeping a local map of Method Expression Proxies so that we don't keep recreating them for each request from within this expression.
	 * The map will be:  declaringTypeName->(Map) methodName or IDEMethodKey -> method expression proxy. 
	 * @see IDEExpression#pushMethodToProxy(ExpressionProxy, IProxyBeanType, String, IProxyBeanType[]) for the actual implementation. 
	 */
	protected Map methodExpressionProxies;
	
	/**
	 * Keeping a local map of Field Expression Proxies so that we don't keep recreating them for each request from within this expression.
	 * The map will be:  declaringTypeName->(Map) fieldname -> field expression proxy. 
	 * @see IDEExpression#pushFieldToProxy(ExpressionProxy, IProxyBeanType, String) 
	 */
	protected Map fieldExpressionProxies;
	
	/**
	 * Keeping a local map of BeanType expression proxies so that we don't keep recreating them for each request from within this expression.
	 * The map will be: typename->beanTypeExpressionProxy
	 */
	protected Map beanTypeCache;	// Use to cache pending BeanTypes. Used in conjunction with IDEStandardBeanTypeFactory.

	/*
	 * Used as the key to the methodCache when there are parms.
	 * It allows the parms to be either IProxyBeanType without the
	 * overhead of creating complicated strings.
	 * 
	 * It will compare method name and each individual parm name without fluffing
	 * up a string and building it up.
	 * 
	 * For no parm methods, just the name of the method as a string will be the key.
	 * 
	 * @since 1.1.0
	 */
	private static class MethodKey {
		public String methodName;
		public IProxyBeanType[] parmTypes;
		public MethodKey(String methodName, IProxyBeanType[] parmTypes) {
			this.methodName = methodName;
			this.parmTypes = parmTypes;
		}
				
				
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			try {
				return ((MethodKey) obj).compareParms(parmTypes);
			} catch (ClassCastException e) {
				return false;
			}
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.remote.REMProxyConstants.MethodKey#hashCode()
		 */
		public int hashCode() {
			int h = methodName.hashCode();;
			for (int i = 0; i < parmTypes.length; i++) {
				h += parmTypes[i].getTypeName().hashCode();
			}
			return h;
		}
				
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.remote.REMProxyConstants.MethodKey#compareParms(java.lang.String[])
		 */
		protected boolean compareParms(IProxyBeanType[] parms) {
			if (parms.length != parmTypes.length)
				return false;
			for (int i = 0; i < parms.length; i++) {
				if (!parmTypes[i].getTypeName().equals(parms[i].getTypeName()))
					return false;
			}
			return true;
		}		
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushMethodToProxy(org.eclipse.jem.internal.proxy.core.ExpressionProxy, org.eclipse.jem.internal.proxy.core.IProxyBeanType, java.lang.String, org.eclipse.jem.internal.proxy.core.IProxyBeanType[])
	 */
	protected void pushMethodToProxy(ExpressionProxy proxy, IProxyBeanType declaringType, String methodName, IProxyBeanType[] parameterTypes) {
		try {
			final Map methods = getMethods(declaringType);
			final Object key = getMethodKey(methodName, parameterTypes); 
			methods.put(key, proxy);
			proxy.addProxyListener(new ExpressionProxy.ProxyAdapter() {
			
				public void proxyNotResolved(ProxyEvent event) {
					methods.remove(key);	// Back it out. tis could happen due to endmark rollback.
				}
			
			});
			
			IDEMethodExpressionProxy ep = (IDEMethodExpressionProxy) proxy;
			ep.setMethodName(methodName);
			// We resolve immediately. Any expression proxies should also be resolved at this point too.
			Class declaringClass = getIDEBeanTypeProxy(declaringType).getTypeClass();
			Class[] parameterClasses;
			if (parameterTypes == null || parameterTypes.length == 0)
				parameterClasses = null;
			else {
				parameterClasses = new Class[parameterTypes.length];
				for (int i = 0; i < parameterClasses.length; i++) {
					parameterClasses[i] = getIDEBeanTypeProxy(parameterTypes[i]).getTypeClass();
				}
			}
			IDEMethodProxy methodProxy = ((IDEMethodProxyFactory) registry.getMethodProxyFactory()).getMethodProxy(declaringClass, methodName, parameterClasses);
			if (methodProxy == null) {
				String parms = "";
				if (parameterTypes != null || parameterTypes.length > 0) {
					StringBuffer st = new StringBuffer(100);
					for (int i = 0; i < parameterClasses.length; i++) {
						if (i > 0)
							st.append(',');
						st.append(parameterTypes[i].getTypeName());
					}
					parms = st.toString();
				}
				throw new IDEThrowableProxy(new NoSuchMethodException("No method: "+declaringType+'.'+methodName+"("+parms+')'),
					getIDEBeanTypeFactory().getBeanTypeProxy(NoSuchMethodException.class));
			}
			
			ep.setProxy(methodProxy);
			eproc.allocateExpressionProxy(ep);
		} catch (ThrowableProxy e) {
			((IDEMethodExpressionProxy) proxy).setThrowable(e);	// So we don't recreate throwable all of the time.
			eproc.processException(e);
		}		
	}
	
	private Map getMethods(IProxyBeanType classtype) {
		if (methodExpressionProxies == null)
			methodExpressionProxies = new HashMap();
		Map methods = (Map) methodExpressionProxies.get(classtype.getTypeName());
		if (methods == null)
			methodExpressionProxies.put(classtype.getTypeName(), methods = new HashMap());
		return methods;
	}
	
	private Object getMethodKey(String methodName, IProxyBeanType[] parameterTypes) {
		if (parameterTypes == null || parameterTypes.length == 0)
			return methodName;
		else
			return new MethodKey(methodName, parameterTypes);
	}
	
	private Map getFields(IProxyBeanType classtype) {
		if (fieldExpressionProxies == null)
			fieldExpressionProxies = new HashMap();
		Map fields = (Map) fieldExpressionProxies.get(classtype.getTypeName());
		if (fields == null)
			fieldExpressionProxies.put(classtype.getTypeName(), fields = new HashMap());
		return fields;
	}
	
	/**
	 * This is used by IDEBeanTypes and IDEBeanTypeExpressionProxy to access any already created Method Expression Proxies.
	 * 
	 * @param declaringType
	 * @param methodName
	 * @param parameterTypes
	 * @return IProxyMethod or <code>null</code> if not yet created.
	 * 
	 * @since 1.1.0
	 */
	IProxyMethod getMethodExpressionProxy(IProxyBeanType declaringType, String methodName, IProxyBeanType[] parameterTypes) {
		Map methods = getMethods(declaringType);
		Object key = getMethodKey(methodName, parameterTypes); 
		return (IProxyMethod) methods.get(key);
	}
	
	/**
	 * This is used by IDEBeanTypes and IDEBeanTypeExpressionProxy to access any already created Field Expression Proxies.
	 * @param declaringType
	 * @param fieldName
	 * @return
	 * 
	 * @since 1.1.0
	 */
	IProxyField getFieldExpressionProxy(IProxyBeanType declaringType, String fieldName) {
		Map fields = getFields(declaringType);
		return (IProxyField) fields.get(fieldName);
	}
	
	/**
	 * Get the IDEMethodProxy out of the already resolved Expression Proxy or IDEMethodProxy itself.
	 * @param method
	 * @return
	 * @throws ThrowableProxy
	 * 
	 * @since 1.1.0
	 */
	protected IDEMethodProxy getIDEMethodProxy(IProxyMethod method) throws ThrowableProxy {
		IDEMethodProxy methodProxy;
		if (method.isExpressionProxy()) {
			// It should already be resolved at this point.
			methodProxy = ((IDEMethodExpressionProxy) method).getMethodProxy();
		} else
			methodProxy = (IDEMethodProxy) method;
		return methodProxy;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushFieldToProxy(org.eclipse.jem.internal.proxy.core.ExpressionProxy, org.eclipse.jem.internal.proxy.core.IProxyBeanType, java.lang.String)
	 */
	protected void pushFieldToProxy(ExpressionProxy proxy, IProxyBeanType declaringType, final String fieldName) {

		try {
			final Map fields = getFields(declaringType);
			fields.put(fieldName, proxy);
			proxy.addProxyListener(new ExpressionProxy.ProxyAdapter(){
				public void proxyNotResolved(ExpressionProxy.ProxyEvent event) {
					fields.remove(fieldName);	// this can happen due to an endmark. It could be one of the ones that are rolled back.
				}
			});
			
			
			IDEFieldExpressionProxy ep = (IDEFieldExpressionProxy) proxy;
			// We resolve immediately. Any expression proxies should also be resolved at this point too.
			IDEFieldProxy fieldProxy = (IDEFieldProxy) getIDEBeanTypeProxy(declaringType).getFieldProxy(fieldName);
			if (fieldProxy == null) {
			throw new IDEThrowableProxy(new NoSuchFieldException("No field: "+declaringType+'.'+fieldName),
					getIDEBeanTypeFactory().getBeanTypeProxy(NoSuchFieldException.class));
			}
			
			ep.setProxy(fieldProxy);
			eproc.allocateExpressionProxy(ep);
		} catch (ThrowableProxy e) {
			((IDEFieldExpressionProxy) proxy).setThrowable(e);	// So we don't recreate throwable all of the time.
			eproc.processException(e);
		}		

	}
	
	/**
	 * Get the IDEFieldProxy out of the already resolved Expression Proxy or IDEFieldProxy itself.
	 * @param field
	 * @return
	 * @throws ThrowableProxy
	 * 
	 * @since 1.1.0
	 */
	protected IDEFieldProxy getIDEFieldProxy(IProxyField field) throws ThrowableProxy {
		IDEFieldProxy fieldProxy;
		if (field.isExpressionProxy()) {
			// It should already be resolved at this point.
			fieldProxy = ((IDEFieldExpressionProxy) field).getFieldProxy();
		} else
			fieldProxy = (IDEFieldProxy) field;
		return fieldProxy;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushIfTestToProxy()
	 */
	protected void pushIfTestToProxy() {
		eproc.pushIfElse();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushIfElseToProxy(org.eclipse.jem.internal.proxy.initParser.tree.InternalIfElseOperandType)
	 */
	protected void pushIfElseToProxy(InternalIfElseOperandType clauseType) {
		eproc.pushIfElse(clauseType);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushNewInstanceToProxy(java.lang.String, org.eclipse.jem.internal.proxy.core.IProxyBeanType)
	 */
	protected void pushNewInstanceToProxy(String initializationString, IProxyBeanType resultType) {
		try {
			eproc.pushNewInstanceFromString(initializationString, getIDEBeanTypeProxy(resultType).getTypeClass(), getIDERegistry().fClassLoader);
		} catch (ThrowableProxy e) {
			eproc.processException(e);
		}
	}

	protected void pushMarkToProxy(int markID) {
		eproc.pushMark(markID);
	}

	protected void pushEndmarkToProxy(int markID, boolean restore) {
		eproc.pushEndmark(markID, restore);
	}

	protected void pushBeginTransferThreadToProxy() {
		// For IDE it doesn't matter. Just go ahead and continue processing.
	}

	protected void pushTransferThreadToProxy() {
		// For IDE it doesn't matter. Just go ahead and continue processing.
	}
}
