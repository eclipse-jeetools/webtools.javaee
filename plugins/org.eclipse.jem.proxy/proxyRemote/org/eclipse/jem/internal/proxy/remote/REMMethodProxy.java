/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMMethodProxy.java,v $
 *  $Revision: 1.12 $  $Date: 2005/08/24 20:39:07 $ 
 */

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.common.CommandException;
import org.eclipse.jem.internal.proxy.common.remote.*;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * Remote VM implementation of the MethodProxy
 */

final class REMMethodProxy extends REMAccessibleObjectProxy implements IREMMethodProxy {

	private IBeanTypeProxy fDeclaringType;
	private String fMethodName;
	private IBeanTypeProxy[] fParameterTypes;
	private IBeanTypeProxy fReturnType;

	REMMethodProxy(REMProxyFactoryRegistry aRegistry, Integer anID) {
		super(aRegistry, anID);
	}

	public IBeanTypeProxy getClassType() {
		if (fDeclaringType == null)
			fDeclaringType =
				(IBeanTypeProxy) REMStandardBeanProxyConstants
					.getConstants(fFactory)
					.getMethodDeclaringClassMessage()
					.invokeCatchThrowableExceptions(
					this);
		return fDeclaringType;
	}

	public String getName() {
		if (fMethodName == null) {
			IStringBeanProxy proxy =
				(IStringBeanProxy) REMStandardBeanProxyConstants
					.getConstants(fFactory)
					.getMethodMethodNameMessage()
					.invokeCatchThrowableExceptions(
					this);
			if (proxy != null)
				fMethodName = proxy.stringValue();
			else
				fMethodName = ""; //$NON-NLS-1$
		}
		return fMethodName;
	}

	public synchronized IBeanTypeProxy[] getParameterTypes() {
		if (fParameterTypes == null) {
			IArrayBeanProxy parmTypes = (IArrayBeanProxy) REMStandardBeanProxyConstants.getConstants(fFactory)
					.getMethodParameterTypesMessage().invokeCatchThrowableExceptions(this);
			if (parmTypes == null)
				fParameterTypes = new IBeanTypeProxy[0]; // There was some error, only way null is returned
			else {
				int len = parmTypes.getLength();
				fParameterTypes = new IBeanTypeProxy[len];
				for (int i = 0; i < len; i++)
					try {
						fParameterTypes[i] = (IBeanTypeProxy) parmTypes.get(i);
					} catch (ThrowableProxy e) {
					}
				fFactory.releaseProxy(parmTypes); // Don't need the array on the server anymore.
			}
		}

		return fParameterTypes;
	}

	public IBeanTypeProxy getReturnType() {
		if (fReturnType == null)
			fReturnType =
				(IBeanTypeProxy) REMStandardBeanProxyConstants
					.getConstants(fFactory)
					.getMethodReturnTypeMessage()
					.invokeCatchThrowableExceptions(
					this);
		return fReturnType;
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
	 * NOTE: This is in IREMMethodProxy only so that other REM proxy implementations can access it.
	 */

	public IBeanProxy invokeWithParms(IBeanProxy subject, final Object[] parms) throws ThrowableProxy {
		IREMConnection connect = fFactory.getFreeConnection();
		REMStandardBeanProxyFactory proxyFactory = (REMStandardBeanProxyFactory) fFactory.getBeanProxyFactory();
		proxyFactory.startTransaction(); // This is definately a transaction, so start it.
		try {
			Commands.ValueObject subjectValue = new Commands.ValueObject();
			if (subject != null)
				 ((IREMBeanProxy) subject).renderBean(subjectValue);

			Commands.ValueObject parmsValue = new Commands.ValueObject();

			class Retriever implements Commands.ValueRetrieve {
				int index = 0;
				Object[] array;
				Commands.ValueObject worker = new Commands.ValueObject();
				IStandardBeanTypeProxyFactory typeFactory = fFactory.getBeanTypeProxyFactory();

				public Retriever(Object[] anArray) {
					array = anArray;
				}
				
				public void reset() {
					index = 0;
				}

				public Commands.ValueObject nextValue() {
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
				}
			};
			
			Retriever retriever = null;

			if (parms != null) {
				// Have a local definition of the retriever so that the retriever can create
				// another one of itself if necessary.
				parmsValue.setArrayIDS(retriever = new Retriever(parms), parms.length, Commands.OBJECT_CLASS); // Create Object[].
			}

			Commands.ValueObject returnValue = new Commands.ValueObject();
			try {
				invoke(connect, proxyFactory, subjectValue, parmsValue, returnValue);
				return proxyFactory.getBeanProxy(returnValue);
			} catch (CommandException e) {
				if (!e.isRecoverable()) {
					// Close the connection and try again.
					fFactory.closeConnection(connect);
					connect = null;
					connect = fFactory.getFreeConnection();
					try {
						if (retriever != null)
							retriever.reset();
						invoke(connect, proxyFactory, subjectValue, parmsValue, returnValue);
						return proxyFactory.getBeanProxy(returnValue);
					} catch (CommandException eAgain) {
						// Failed again. Just close and print trace.
						fFactory.closeConnection(connect);
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
				fFactory.returnConnection(connect);
		}
	}

private void invoke(
		IREMConnection connect,
		REMStandardBeanProxyFactory proxyFactory,
		Commands.ValueObject subjectValue,
		Commands.ValueObject parmsValue,
		Commands.ValueObject returnValue)
		throws ThrowableProxy, CommandException {
		try {
			connect.invokeMethod(getID().intValue(), subjectValue, parmsValue, returnValue);
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
			fFactory.releaseProxy(e); // Since it's no longer needed, get rid of now instead of GC time.
			return null;
		}
	}

	public IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject, IBeanProxy[] parms) {
		try {
			return invoke(subject, parms);
		} catch (ThrowableProxy e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
			fFactory.releaseProxy(e); // Since it's no longer needed, get rid of now instead of GC time.
			return null;
		}
	}

	public IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject, IBeanProxy parm) {
		try {
			return invoke(subject, parm);
		} catch (ThrowableProxy e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
			fFactory.releaseProxy(e); // Since it's no longer needed, get rid of now instead of GC time.
			return null;
		}
	}

	/**
	 * The type proxy is constant proxy out of the method factory.
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((REMMethodProxyFactory) fFactory.getMethodProxyFactory()).methodType;
	}

	/**
	 * The bean is being released, clear out the fields so they can be GC'd if necessary.
	 * Usually only big objects and proxy fields need to be cleared.
	 */
	public void release() {
		fDeclaringType = null;
		fParameterTypes = null;
		fReturnType = null;
		super.release();
	}
}
