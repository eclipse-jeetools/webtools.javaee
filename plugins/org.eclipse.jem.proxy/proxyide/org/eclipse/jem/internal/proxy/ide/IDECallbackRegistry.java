package org.eclipse.jem.internal.proxy.ide;
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
 *  $RCSfile: IDECallbackRegistry.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.core.*;

public class IDECallbackRegistry implements ICallbackRegistry {
	
	IDEVMServer fVMServer;
	int fNextCallbackID;
	IDEProxyFactoryRegistry fProxyFactoryRegistry;
	Map fCallbackIDToCallback = new HashMap(25);
	Map fBeanProxyToCallbackID = new HashMap(25);
	Map fCallbackIDToStream = new HashMap(25);
	
IDECallbackRegistry(IDEProxyFactoryRegistry aRegistry){
	fProxyFactoryRegistry = aRegistry;
	fVMServer = new IDEVMServer(this);
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

OutputStream requestStream(final int aCallbackID, final int aMsgID){
	final PipedOutputStream result = new PipedOutputStream();
	PipedInputStream tempStream = null;
	try {
		tempStream = new PipedInputStream(result);	
	} catch ( IOException exc ) {
		ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", exc));
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
						ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", exc));
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
	if ( parms == null ) {
		callbackResult = ideCallback.calledBack(aMsgID,(IBeanProxy)null);
	} else if ( parms.length == 1 ) {
		callbackResult = ideCallback.calledBack(aMsgID,(IBeanProxy)parms[0]);
	} else {
		callbackResult = ideCallback.calledBack(aMsgID,parms);
	}
	return callbackResult;
}

public void deregisterCallback(IBeanProxy aBeanProxy){
	// Remove the callback from both maps.  The actual unregistering of the callback
	// on the target VM is done separately by the object that added the event handler on the target VM
	Integer callbackID = (Integer) fBeanProxyToCallbackID.get(aBeanProxy);
	fBeanProxyToCallbackID.remove(aBeanProxy);
	fCallbackIDToCallback.remove(callbackID);
}
}