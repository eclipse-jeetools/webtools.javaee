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
package org.eclipse.jem.internal.proxy.ide;
/*
 *  $RCSfile: IDECallbackRegistry.java,v $
 *  $Revision: 1.10 $  $Date: 2005/08/24 20:39:06 $ 
 */

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent;

public class IDECallbackRegistry implements ICallbackRegistry {
	
	IDEVMServer fVMServer;
	int fNextCallbackID;
	IDEProxyFactoryRegistry fProxyFactoryRegistry;
	Map fCallbackIDToCallback = new HashMap(25);
	Map fBeanProxyToCallbackID = new HashMap(25);
	Map fCallbackIDToStream = new HashMap(25);
	private IProxyMethod initializeCallbackMethodProxy;
	private IBeanProxy vmServerProxy;
	
IDECallbackRegistry(IDEProxyFactoryRegistry aRegistry){
	fProxyFactoryRegistry = aRegistry;
	fVMServer = new IDEVMServer(this);
	
	vmServerProxy = aRegistry.getBeanProxy(fVMServer.getClass(), fVMServer);
	
	try {
		Method initializeCallbackMethod = org.eclipse.jem.internal.proxy.common.ICallback.class.getMethod("initializeCallback", new Class[] {org.eclipse.jem.internal.proxy.common.IVMCallbackServer.class, Integer.TYPE}); //$NON-NLS-1$
		initializeCallbackMethodProxy = (IProxyMethod) aRegistry.getBeanProxy(Method.class, initializeCallbackMethod);
	} catch (SecurityException e) {
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	}
	
}


/**
 * Add a callback.  aBeanProxy is running on the target VM and ICallback runs on our VM
 * aBeanProxy will implement ICallback on the target side
 */	
public void registerCallback(IBeanProxy aBeanProxy, ICallback aCallback){

	// Get a handle to the remote VM callback	
	org.eclipse.jem.internal.proxy.common.ICallback vmCallback = (org.eclipse.jem.internal.proxy.common.ICallback) ((IIDEBeanProxy)aBeanProxy).getBean(); 
	fNextCallbackID = fNextCallbackID + 1;
	int callbackID = fNextCallbackID;
	vmCallback.initializeCallback(
		fVMServer,
		callbackID);
	Integer callbackIntegerID = new Integer(callbackID);
	fCallbackIDToCallback.put(callbackIntegerID,aCallback);
	fBeanProxyToCallbackID.put(aBeanProxy,callbackIntegerID);

}


/* (non-Javadoc)
 * @see org.eclipse.jem.internal.proxy.core.ICallbackRegistry#registerCallback(org.eclipse.jem.internal.proxy.core.IProxy, org.eclipse.jem.internal.proxy.core.ICallback, org.eclipse.jem.internal.proxy.core.IExpression)
 */
public void registerCallback(IProxy callbackProxy, final ICallback cb, IExpression expression) {
	final Integer callbackID = new Integer(++fNextCallbackID);
	fCallbackIDToCallback.put(callbackID, cb);
	if (callbackProxy.isBeanProxy()) {
		fBeanProxyToCallbackID.put(callbackProxy, callbackID);		
	} else {
		((ExpressionProxy) callbackProxy).addProxyListener(new ExpressionProxy.ProxyAdapter() {
			public void proxyResolved(ProxyEvent event) {
				fBeanProxyToCallbackID.put(event.getProxy(), callbackID);		
			}
		});
	}
	expression.createSimpleMethodInvoke(initializeCallbackMethodProxy, callbackProxy, new IProxy[] {vmServerProxy, fProxyFactoryRegistry.getBeanProxyFactory().createBeanProxyWith(callbackID.intValue())}, false);
	
}

OutputStream requestStream(final int aCallbackID, final int aMsgID){
	final PipedOutputStream result = new PipedOutputStream();
	PipedInputStream tempStream = null;
	try {
		tempStream = new PipedInputStream(result);	
	} catch ( IOException exc ) {
		ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", exc)); //$NON-NLS-1$
		return null;
	}
	final PipedInputStream inputStream = tempStream;
	Thread streamProcessor = new Thread(){
		public void run(){
			ICallback ideCallback = (ICallback)fCallbackIDToCallback.get(new Integer(aCallbackID));
			if (ideCallback != null) {
				try {
					ideCallback.calledBackStream(aMsgID, inputStream);
				} finally {
					try {
						inputStream.close();	// Make sure it's closed.
					} catch ( IOException  exc ){
						ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", exc)); //$NON-NLS-1$
					}
				}			
			}
		}
	};
	streamProcessor.start();
	return result;	
}

Object vmCallback(int aCallbackID ,int aMsgID, Object[] parms){
	// Find the bean proxy that registered with the ID
	ICallback ideCallback = (ICallback) fCallbackIDToCallback.get(new Integer(aCallbackID));
	Object callbackResult = null;
	if ( parms.length == 1 ) {
		callbackResult = ideCallback.calledBack(aMsgID,(IBeanProxy)parms[0]);
	} else {
		callbackResult = ideCallback.calledBack(aMsgID,parms);
	}
	return callbackResult;
}

Object vmCallback(int aCallbackID ,int aMsgID, Object parm){
	// Find the bean proxy that registered with the ID
	ICallback ideCallback = (ICallback) fCallbackIDToCallback.get(new Integer(aCallbackID));
	return ideCallback.calledBack(aMsgID,parm);
}

public void deregisterCallback(IBeanProxy aBeanProxy){
	// Remove the callback from both maps.  The actual unregistering of the callback
	// on the target VM is done separately by the object that added the event handler on the target VM
	Integer callbackID = (Integer) fBeanProxyToCallbackID.remove(aBeanProxy);
	fCallbackIDToCallback.remove(callbackID);
}
}
