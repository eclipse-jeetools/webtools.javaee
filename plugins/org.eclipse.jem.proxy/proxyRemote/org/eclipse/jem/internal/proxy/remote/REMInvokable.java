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
 *  $RCSfile: REMInvokable.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.common.CommandException;
import org.eclipse.jem.internal.proxy.common.remote.*;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IInvokable;
 

/**
 * Remote Invokable. This invokes on the remote vm, but it doesn't use a method proxy. Instead
 * it sends the information to get the method to the remote vm along with the parms. That way
 * it will do the lookup on the remote vm and we don't have the overhead of two calls, one to
 * get the methodproxy and the other to actually do the invoke. This should typically be used
 * only for infrequent calls so that cache of the method proxy is not needed.
 * 
 * @since 1.0.0
 */
class REMInvokable implements IInvokable {

	protected final IREMBeanTypeProxy beanType;	// Beantype to get method from.
	protected final String methodName;	// Method name
	protected final IBeanTypeProxy[] methodArgTypes;	// Argument types (or null if no arguments).
	protected final REMProxyFactoryRegistry factory;	// Factory for this invokable
	
	
	REMInvokable(IREMBeanTypeProxy beanType, String methodName, IBeanTypeProxy[] methodArgTypes) {
		this.beanType = beanType;
		this.methodName = methodName;
		this.methodArgTypes = methodArgTypes;
		this.factory = (REMProxyFactoryRegistry) beanType.getProxyFactoryRegistry();
	}
	
	public IBeanProxy invoke(IBeanProxy subject) throws ThrowableProxy {
		return invoke(subject, (IBeanProxy[]) null);
	}

	public IBeanProxy invoke(IBeanProxy subject, IBeanProxy[] parms) throws ThrowableProxy {
		return invokeWithParms(subject, parms);
	}

	/**
	 * The internal method that allows mixture of constants (e.g. String) with bean proxies.
	 * A parm value can be a REMMethodProxy.ParmArray. This means this parm is an array that
	 * needs to have a ValueRetriever created for it. This is used if the array has ID's in
	 * it and is not an existing array on the server or all constant values.
	 *
	 * NOTE: It is assumed that all IBeanTypeProxies of the parms have already been retrieved.
	 *       It should still work, but it could be asking for them in the middle of the request
	 *       if they are not first gotton.
	 *
	 */
	public IBeanProxy invokeWithParms(IBeanProxy subject, final Object[] parms) throws ThrowableProxy {
		IREMConnection connect = factory.getFreeConnection();
		REMStandardBeanProxyFactory proxyFactory = (REMStandardBeanProxyFactory) factory.getBeanProxyFactory();
		proxyFactory.startTransaction(); // This is definately a transaction, so start it.
		try {
			// First need to send the method info.
			Commands.ValueObject classValue = new Commands.ValueObject();
			beanType.renderBean(classValue);
			
			Commands.ValueObject parmTypesValue = new Commands.ValueObject();
			if (methodArgTypes != null) {
				class Retriever implements Commands.ValueRetrieve {
					int index = 0;
					Object[] array;
					Commands.ValueObject worker = new Commands.ValueObject();

					public Retriever(Object[] anArray) {
						array = anArray;
					}

					public Commands.ValueObject nextValue() {
						if (index < array.length) {
							Object parm = array[index++];
							((IREMBeanTypeProxy) parm).renderBean(worker);
							return worker;
						} else
							return null;
					}
				};

				parmTypesValue.setArrayIDS(new Retriever(methodArgTypes), methodArgTypes.length, Commands.CLASS_CLASS); // Create Class[].
			}
			
			
			// Now we start building the actual invocation.
			
			Commands.ValueObject subjectValue = new Commands.ValueObject();
			if (subject != null)
				 ((IREMBeanProxy) subject).renderBean(subjectValue);

			Commands.ValueObject parmsValue = new Commands.ValueObject();

			if (parms != null) {
				// Have a local definition of the retriever so that the retriever can create
				// another one of itself if necessary.
				final IStandardBeanTypeProxyFactory typeFactory = factory.getBeanTypeProxyFactory();

				class Retriever implements Commands.ValueRetrieve {
					int index = 0;
					Object[] array;
					Commands.ValueObject worker = new Commands.ValueObject();

					public Retriever(Object[] anArray) {
						array = anArray;
					}

					public Commands.ValueObject nextValue() {
						if (index < array.length) {
							Object parm = array[index++];
							if (parm != null)
								if (parm instanceof IREMBeanProxy)
									 ((IREMBeanProxy) parm).renderBean(worker);
								else if (parm instanceof TransmitableArray) {
									// It is another array, create a new retriever.
									worker.setArrayIDS(
										new Retriever(((TransmitableArray) parm).array),
										((TransmitableArray) parm).array.length,
										((TransmitableArray) parm).componentTypeID);
								} else {
									// It's an object. Need to get bean type so that we can send it.
									IREMBeanProxy type = (IREMBeanProxy) typeFactory.getBeanTypeProxy(parm.getClass().getName());
									if (type == null)
										throw new IllegalArgumentException();
									int classID = type.getID().intValue();
									worker.setAsObject(parm, classID);
								}
							else
								worker.set();
							return worker;
						} else
							return null;
					}
				};

				parmsValue.setArrayIDS(new Retriever(parms), parms.length, Commands.OBJECT_CLASS); // Create Object[].
			}

			Commands.ValueObject returnValue = new Commands.ValueObject();
			try {
				invoke(connect, proxyFactory, classValue, parmTypesValue, subjectValue, parmsValue, returnValue);
				return proxyFactory.getBeanProxy(returnValue);
			} catch (CommandException e) {
				if (!e.isRecoverable()) {
					// Close the connection and try again.
					factory.closeConnection(connect);
					connect = null;
					connect = factory.getFreeConnection();
					try {
						invoke(connect, proxyFactory, classValue, parmTypesValue, subjectValue, parmsValue, returnValue);
						return proxyFactory.getBeanProxy(returnValue);
					} catch (CommandException eAgain) {
						// Failed again. Just close and print trace.
						factory.closeConnection(connect);
						connect = null;
						ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", eAgain)); //$NON-NLS-1$
						return null;
					}
				} else {
					// A recoverable error, print trace and return
					ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
					return null;
				}
			}
		} finally {
			proxyFactory.stopTransaction();
			if (connect != null)
				factory.returnConnection(connect);
		}
	}

private void invoke(
		IREMConnection connect,
		REMStandardBeanProxyFactory proxyFactory,
		Commands.ValueObject classTypeValue,
		Commands.ValueObject parmTypesValue,
		Commands.ValueObject subjectValue,
		Commands.ValueObject parmsValue,
		Commands.ValueObject returnValue)
		throws ThrowableProxy, CommandException {
		try {
			connect.invokeMethod(classTypeValue, methodName, parmTypesValue, subjectValue, parmsValue, returnValue);
		} catch (CommandErrorException e) {
			proxyFactory.processErrorReturn(e);
		}
	}

	public IBeanProxy invoke(IBeanProxy subject, IBeanProxy parm) throws ThrowableProxy {
		return invoke(subject, new IBeanProxy[] { parm });
	}

	public IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject) {
		try {
			return invoke(subject);
		} catch (ThrowableProxy e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
			factory.releaseProxy(e); // Since it's no longer needed, get rid of now instead of GC time.
			return null;
		}
	}

	public IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject, IBeanProxy[] parms) {
		try {
			return invoke(subject, parms);
		} catch (ThrowableProxy e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
			factory.releaseProxy(e); // Since it's no longer needed, get rid of now instead of GC time.
			return null;
		}
	}

	public IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject, IBeanProxy parm) {
		try {
			return invoke(subject, parm);
		} catch (ThrowableProxy e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
			factory.releaseProxy(e); // Since it's no longer needed, get rid of now instead of GC time.
			return null;
		}
	}

}
