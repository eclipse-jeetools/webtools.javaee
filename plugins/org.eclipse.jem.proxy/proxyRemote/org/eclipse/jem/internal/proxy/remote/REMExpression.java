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
 *  $RCSfile: REMExpression.java,v $
 *  $Revision: 1.6 $  $Date: 2004/10/28 21:24:57 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import java.io.IOException;
import java.util.logging.Level;

import org.eclipse.jem.internal.proxy.common.CommandException;
import org.eclipse.jem.internal.proxy.common.remote.*;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.initParser.tree.IInternalExpressionConstants;
 
/**
 * The Remote proxy version of Expression.
 * 
 * @since 1.0.0
 */
public class REMExpression extends Expression {

	protected IREMExpressionConnection connection;
	
	protected Commands.ValueObject workerValue = new Commands.ValueObject();	// A worker object so that we don't need to keep creating one and throwing it away.
	
	/**
	 * @param registry
	 * 
	 * @since 1.0.0
	 */
	public REMExpression(REMProxyFactoryRegistry registry) {
		super(registry);
		connection = (IREMExpressionConnection) registry.getFreeConnection();
		try {
			connection.startExpressionProcessing(this.hashCode());
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			throwIllegalStateException(IO_EXCEPTION_MSG);
		}
	}
	
	/**
	 * General IOException occurred msg.
	 */
	protected static final String IO_EXCEPTION_MSG = ProxyRemoteMessages.getString("REMExpression.IOExceptionSeeLog_INFO_"); //$NON-NLS-1$
	
	protected static final String COMMAND_EXCEPTION_MSG = ProxyRemoteMessages.getString("REMExpression.CommandExceptionSeeLog_INFO_"); //$NON-NLS-1$
	
	/**
	 * Throw an an illegal state exception if some general error, in particular an I/O or Command Exception
	 * occurred so that callers know there is something wrong.
	 * 
	 * @param msg
	 * @throws IllegalStateException
	 * 
	 * @since 1.0.0
	 */
	protected void throwIllegalStateException(String msg) throws IllegalStateException {
		throw new IllegalStateException(msg);
	}
	
	/**
	 * Return the registry as a REMProxyFactoryRegistry
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final REMProxyFactoryRegistry getREMRegistry() {
		return (REMProxyFactoryRegistry) registry;
	}
	
	/**
	 * Return the bean proxy factory as a REMStandardBeanProxyFactory.
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected final REMStandardBeanProxyFactory getREMBeanProxyFactory() {
		return (REMStandardBeanProxyFactory) beanProxyFactory;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushToProxy(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	protected void pushToProxy(IBeanProxy proxy) throws ThrowableProxy {
		try {
			// Format of push proxy command is:
			//	PushExpressionCommand(push to proxy) followed by:
			//		ValueObject containing the rendered proxy.
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.PUSH_TO_PROXY_EXPRESSION);
			if (proxy == null)
				workerValue.set();
			else
				((IREMBeanProxy) proxy).renderBean(workerValue);
			connection.pushValueObject(workerValue);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			if (!e.isRecoverable()) {
				connection.close();
				throwIllegalStateException(COMMAND_EXCEPTION_MSG);
			}			
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#closeProxy()
	 */
	protected void closeProxy() {
		try {
			try {
				connection.stopExpressionProcessing(this.hashCode());
			} catch (IOException e) {
				connection.close();
				ProxyPlugin.getPlugin().getLogger().log(e, Level.INFO);
				// Not throwing an illegal state here because we don't care, other than logging and not 
				// returning the connection to the registry that there was an error on close.
			}
		} finally {
			getREMRegistry().returnConnection(connection);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pullProxyValue()
	 */
	protected IBeanProxy pullProxyValue() throws ThrowableProxy, NoExpressionValueException {
		try {
			connection.pullValue(this.hashCode(), workerValue);
			return getREMBeanProxyFactory().getBeanProxy(workerValue);
		} catch (CommandErrorException e) {
			if (e.getErrorCode() == ExpressionCommands.ExpressionNoExpressionValueException) {
				throw new NoExpressionValueException((String) e.getErrorObject());
			}
			try {
				getREMBeanProxyFactory().processErrorReturn(e);
			} catch (CommandException e1) {
				ProxyPlugin.getPlugin().getLogger().log(e);
				if (!e.isRecoverable()) {
					connection.close();
					throwIllegalStateException(COMMAND_EXCEPTION_MSG);
				}			
			}
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(e);
			if (!e.isRecoverable()) {
				connection.close();
				throwIllegalStateException(COMMAND_EXCEPTION_MSG);
			}			
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushCastToProxy(java.lang.Object)
	 */
	protected void pushCastToProxy(Object type) throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push cast to proxy command is:
			//	PushExpressionCommand(push cast to proxy) followed by:
			//		ValueObject containing the rendered bean type proxy or the String representing the name of class.
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.CAST_EXPRESSION);
			if (type instanceof String)
				workerValue.set((String) type);
			else
				((IREMBeanProxy) type).renderBean(workerValue);
			connection.pushValueObject(workerValue);
		} catch (IOException e) {
			connection.close();			
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			if (!e.isRecoverable()) {
				connection.close();
				throwIllegalStateException(COMMAND_EXCEPTION_MSG);
			}			
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushInstanceofToProxy(java.lang.Object)
	 */
	protected void pushInstanceofToProxy(Object type) throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push instanceof to proxy command is:
			//	PushExpressionCommand(push instanceof to proxy) followed by:
			//		ValueObject containing the rendered bean type proxy or the String representing the name of class.
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.INSTANCEOF_EXPRESSION);
			if (type instanceof String)
				workerValue.set((String) type);
			else
				((IREMBeanProxy) type).renderBean(workerValue);
			connection.pushValueObject(workerValue);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			if (!e.isRecoverable()) {
				connection.close();
				throwIllegalStateException(COMMAND_EXCEPTION_MSG);
			}			
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushInfixToProxy(int, int)
	 */
	protected void pushInfixToProxy(int operator, int operandType) throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push infix to proxy command is:
			//	PushExpressionCommand(push infix to proxy) followed by:
			//		byte: operator
			//		byte: operandType
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.INFIX_EXPRESSION);
			connection.pushByte((byte) operator);
			connection.pushByte((byte) operandType);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		}		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushPrefixToProxy(int)
	 */
	protected void pushPrefixToProxy(int operator) throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push prefix to proxy command is:
			//	PushExpressionCommand(push prefix to proxy) followed by:
			//		byte: operator
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.PREFIX_EXPRESSION);
			connection.pushByte((byte) operator);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushTypeLiteralToProxy(java.lang.String)
	 */
	protected void pushTypeLiteralToProxy(String type) throws ThrowableProxy {
		try {
			// Format of push type literal to proxy command is:
			//	PushExpressionCommand(push typeliteral to proxy) followed by:
			//		ValueObject containing the String representing the name of class.
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.TYPELITERAL_EXPRESSION);
			workerValue.set((String) type);
			connection.pushValueObject(workerValue);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			if (!e.isRecoverable()) {
				connection.close();
				throwIllegalStateException(COMMAND_EXCEPTION_MSG);
			}			
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushArrayAccessToProxy(int)
	 */
	protected void pushArrayAccessToProxy(int indexCount) throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push array access to proxy command is:
			//	PushExpressionCommand(push array acces to proxy) followed by:
			//		int: indexCount
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.ARRAY_ACCESS_EXPRESSION);
			connection.pushInt(indexCount);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushArrayCreationToProxy(java.lang.Object, int)
	 */
	protected void pushArrayCreationToProxy(Object type, int dimensionCount) throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push array creation to proxy command is:
			//	PushExpressionCommand(push array creation to proxy) followed by:
			//		ValueObject containing the rendered bean type proxy or the String representing the name of class.
			//		int: dimension count
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.ARRAY_CREATION_EXPRESSION);
			if (type instanceof String)
				workerValue.set((String) type);
			else
				((IREMBeanProxy) type).renderBean(workerValue);
			connection.pushValueObject(workerValue);
			connection.pushInt(dimensionCount);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			if (!e.isRecoverable()) {
				connection.close();
				throwIllegalStateException(COMMAND_EXCEPTION_MSG);
			}			
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushArrayInitializerToProxy(java.lang.Object, int)
	 */
	protected void pushArrayInitializerToProxy(Object type, int expressionCount) throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push array initializer to proxy command is:
			//	PushExpressionCommand(push array initializer to proxy) followed by:
			//		ValueObject containing the rendered bean type proxy or the String representing the name of class.
			//		int: expression count
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.ARRAY_INITIALIZER_EXPRESSION);
			if (type instanceof String)
				workerValue.set((String) type);
			else
				((IREMBeanProxy) type).renderBean(workerValue);
			connection.pushValueObject(workerValue);
			connection.pushInt(expressionCount);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			if (!e.isRecoverable()) {
				connection.close();
				throwIllegalStateException(COMMAND_EXCEPTION_MSG);
			}			
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushClassInstanceCreationToProxy(java.lang.Object, int)
	 */
	protected void pushClassInstanceCreationToProxy(Object type, int argumentCount) throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push class instance creation to proxy command is:
			//	PushExpressionCommand(push class instance creation to proxy) followed by:
			//		ValueObject containing the rendered bean type proxy or the String representing the name of class.
			//		int: argument count
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.CLASS_INSTANCE_CREATION_EXPRESSION);
			if (type instanceof String)
				workerValue.set((String) type);
			else
				((IREMBeanProxy) type).renderBean(workerValue);
			connection.pushValueObject(workerValue);
			connection.pushInt(argumentCount);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			if (!e.isRecoverable()) {
				connection.close();
				throwIllegalStateException(COMMAND_EXCEPTION_MSG);
			}			
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushTypeReceiverToProxy(java.lang.Object)
	 */
	protected void pushTypeReceiverToProxy(Object type) throws ThrowableProxy {
		try {
			// Format of push type receiver to proxy command is:
			//	PushExpressionCommand(push type receiver to proxy) followed by:
			//		ValueObject containing the rendered bean type proxy or the String representing the name of class.
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.TYPERECEIVER_EXPRESSION);
			if (type instanceof String)
				workerValue.set((String) type);
			else
				((IREMBeanProxy) type).renderBean(workerValue);
			connection.pushValueObject(workerValue);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			if (!e.isRecoverable()) {
				connection.close();
				throwIllegalStateException(COMMAND_EXCEPTION_MSG);
			}			
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushFieldAccessToProxy(java.lang.String, boolean)
	 */
	protected void pushFieldAccessToProxy(String fieldName, boolean hasReceiver) throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push field access to proxy command is:
			//	PushExpressionCommand(push field access to proxy) followed by:
			//		String: fieldName
			//		boolean: hasReceiver
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.FIELD_ACCESS_EXPRESSION);
			connection.pushString(fieldName);
			connection.pushBoolean(hasReceiver);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushMethodInvocationToProxy(java.lang.String, boolean, int)
	 */
	protected void pushMethodInvocationToProxy(String methodName, boolean hasReceiver, int argCount)
		throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push method invocation to proxy command is:
			//	PushExpressionCommand(push method invocation to proxy) followed by:
			//		String: methodName
			//		boolean: hasReceiver
			//		int: argCount
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.METHOD_EXPRESSION);
			connection.pushString(methodName);
			connection.pushBoolean(hasReceiver);
			connection.pushInt(argCount);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushConditionalToProxy(int)
	 */
	protected void pushConditionalToProxy(int expressionType) throws ThrowableProxy, NoExpressionValueException {
		try {
			// Format of push conditional to proxy command is:
			//	PushExpressionCommand(push conditional to proxy) followed by:
			//		byte: expression type
			connection.pushExpressionCommand(this.hashCode(), (byte)IInternalExpressionConstants.CONDITIONAL_EXPRESSION);
			connection.pushByte((byte) expressionType);
		} catch (IOException e) {
			connection.close();
			ProxyPlugin.getPlugin().getLogger().log(e);
			markInvalid(e.getLocalizedMessage());
			throwIllegalStateException(IO_EXCEPTION_MSG);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.Expression#pushInvoke()
	 */
	protected void pushInvoke() throws ThrowableProxy, NoExpressionValueException {
		try {
			connection.sync(this.hashCode(), workerValue);
			getREMBeanProxyFactory().getBeanProxy(workerValue);	// This processes the return. It will be either true or an error. If true we don't care and if error the catch will handle it.
		} catch (CommandErrorException e) {
			if (e.getErrorCode() == ExpressionCommands.ExpressionNoExpressionValueException) {
				throw new NoExpressionValueException((String) e.getErrorObject());
			}
			try {
				getREMBeanProxyFactory().processErrorReturn(e);
			} catch (CommandException e1) {
				ProxyPlugin.getPlugin().getLogger().log(e);
				if (!e.isRecoverable()) {
					connection.close();
					throwIllegalStateException(COMMAND_EXCEPTION_MSG);
				}			
			}
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(e);
			if (!e.isRecoverable()) {
				connection.close();
				throwIllegalStateException(COMMAND_EXCEPTION_MSG);
			}			
		}
	}

}
