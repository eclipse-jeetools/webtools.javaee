package org.eclipse.jem.internal.proxy.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: REMConstructorProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2004/02/20 00:44:05 $ 
 */

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.common.remote.TransmitableArray;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * Proxy for a constructor.
 */

final class REMConstructorProxy extends REMAccessibleObjectProxy implements IConstructorProxy {

	REMConstructorProxy(REMProxyFactoryRegistry aRegistry, Integer anID) {
		super(aRegistry, anID);
	}

	/**
	 * Using this constructor (assuming it takes no arguments) create a new instance.
	 * Exception will be raised if this constructor takes arguments.
	 */
	public IBeanProxy newInstance() throws ThrowableProxy {
		return newInstance(null);
	}

	/**
	 * The type proxy is constant proxy out of the method factory.
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((REMMethodProxyFactory) fFactory.getMethodProxyFactory()).ctorType;
	}

	/**
	 * Using this constructor (with the arguments) create a new instance.
	 * Exception will be raised if arguments don't match the constructor.
	 */
	public IBeanProxy newInstance(IBeanProxy[] creationArguments) throws ThrowableProxy {
		IREMMethodProxy newInstanceMethod =
			(IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getConstructorNewInstance();

		// Constructor.newInstance takes one parm (Object[]) where [0] is an Object[] of the arguments to the constructor.
		Object[] parms = null;
		if (creationArguments != null)
			parms = new Object[] { new TransmitableArray(Commands.OBJECT_CLASS, creationArguments)};

		return newInstanceMethod.invokeWithParms(this, parms);
	}
	public IBeanProxy newInstanceCatchThrowableExceptions() {
		try {
			return newInstance();
		} catch (ThrowableProxy e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e)); //$NON-NLS-1$
			fFactory.releaseProxy(e); // Since it's no longer needed, get rid of now instead of GC time.
			return null;
		}
	}
	public IBeanProxy newInstanceCatchThrowableExceptions(IBeanProxy[] args) {
		try {
			return newInstance(args);
		} catch (ThrowableProxy e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e)); //$NON-NLS-1$
			fFactory.releaseProxy(e); // Since it's no longer needed, get rid of now instead of GC time.
			return null;
		}
	}
}