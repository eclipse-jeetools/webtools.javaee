/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
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
 *  $RCSfile: REMCallbackRegistry.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:56:10 $ 
 */

import java.net.Socket;
import java.util.*;

import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.core.*;

/**
 * This registry will handle callbacks.
 * It is package protected because no one
 * should access it outside.
 */
class REMCallbackRegistry implements ICallbackRegistry {
	final REMProxyFactoryRegistry fFactory;
	final String fNamePostfix;
	List fThreads = Collections.synchronizedList(new LinkedList());	// List of active callback threads.	
	
	HashMap fIdToCallback = new HashMap(5);	// ID to Callback map.
	HashSet fRegisteredCallbackProxies = new HashSet(5);	// Hold onto registered proxies so they aren't released as long as call back is registered.
	
	IREMMethodProxy fInitializeCallback;
	IREMBeanProxy fRemoteServer;
	
	boolean registryOpen = true;
	
	public REMCallbackRegistry(String name, REMProxyFactoryRegistry aFactory) {
	
		fFactory = aFactory;
		fNamePostfix = name;
		
		// Now register common proxies.
		REMStandardBeanTypeProxyFactory typeFactory = (REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory();
		IREMBeanTypeProxy vmserverType = new REMInterfaceBeanTypeProxy(fFactory, new Integer(Commands.IVMSERVER_CLASS), "org.eclipse.jem.internal.proxy.vm.remote.IVMServer"); //$NON-NLS-1$
		IREMBeanTypeProxy callbackType = new REMInterfaceBeanTypeProxy(fFactory, new Integer(Commands.ICALLBACK_CLASS), "org.eclipse.jem.internal.proxy.vm.remote.ICallback"); //$NON-NLS-1$
		IREMBeanTypeProxy serverType = typeFactory.objectClass.newBeanTypeForClass(new Integer(Commands.REMOTEVMSERVER_CLASS), "org.eclipse.jem.internal.proxy.vm.remote.RemoteVMServerThread", false); //$NON-NLS-1$
	
		fInitializeCallback = (IREMMethodProxy) ((REMMethodProxyFactory) fFactory.getMethodProxyFactory()).methodType.newBeanProxy(new Integer(Commands.INITIALIZECALLBACK_METHOD_ID));
		
		fRemoteServer = serverType.newBeanProxy(new Integer(Commands.REMOTESERVER_ID));

		((REMStandardBeanProxyFactory) fFactory.getBeanProxyFactory()).registerProxy(vmserverType);
		((REMStandardBeanProxyFactory) fFactory.getBeanProxyFactory()).registerProxy(callbackType);
		((REMStandardBeanProxyFactory) fFactory.getBeanProxyFactory()).registerProxy(fInitializeCallback);
		((REMStandardBeanProxyFactory) fFactory.getBeanProxyFactory()).registerProxy(fRemoteServer);		
		
	}
	
	
	public boolean createCallback(Socket incoming) {
		if (registryOpen) {
			Thread st = new REMCallbackThread(incoming, this, "Callback Thread-"+fNamePostfix, fFactory, fFactory.fNoTimeouts); //$NON-NLS-1$
			fThreads.add(st);
			st.start();
			return true;
		} else
			return false;
	}
	
	/**
	 * Use this to request a shutdown. If the server is already shutdown, this will return false.
	 */
	public boolean requestShutdown() {		
		if (registryOpen)
			shutdown();
		else
			return false;
		return true;
	}
	
	/**
	 * Remove a thread from the list.
	 */
	public void removeCallbackThread(REMCallbackThread thread) {
		fThreads.remove(thread);
	}
	
	private void shutdown() {
		
		// Go through each thread and ask it to close. Make a copy of the list so that we
		// won't get into deadlocks.
		REMCallbackThread[] threadsArray = (REMCallbackThread[]) fThreads.toArray(new REMCallbackThread[fThreads.size()]);
		for (int i=0; i<threadsArray.length; i++) {
			// This is a harsh way to shut a connection down, but there's no
			// other way I know of to interrupt the read on a socket.
			threadsArray[i].close();
		}
			
		// Now that they've been told to close, wait on each one to finish.
		for (int i=0; i<threadsArray.length; i++)
			try {
				threadsArray[i].join(10000);	// Wait ten seconds, if longer, just go on to next one.
			} catch (InterruptedException e) {
			}
			
		fThreads.clear();
		fIdToCallback.clear();
		fRegisteredCallbackProxies.clear();
		fInitializeCallback = null;
		fRemoteServer = null;
			
	}		
	
	
	public ICallback getRegisteredCallback(int id) {
		synchronized(fIdToCallback)	{	
			return (ICallback) fIdToCallback.get(new Integer(id));
		}
	}
		
	/**
	 * The public interface for registering callbacks
	 */
	public void registerCallback(IBeanProxy callbackProxy, ICallback cb) {
		synchronized(fIdToCallback) {
			fIdToCallback.put(((IREMBeanProxy) callbackProxy).getID(), cb);
			fRegisteredCallbackProxies.add(callbackProxy);
			fInitializeCallback.invokeCatchThrowableExceptions(callbackProxy, new IBeanProxy[] {fRemoteServer, fFactory.getBeanProxyFactory().createBeanProxyWith(((IREMBeanProxy) callbackProxy).getID().intValue())});
		}
	}
	
	/**
	 * The public interface for deregistering callbacks.
	 */
	public void deregisterCallback(IBeanProxy callbackProxy) {
		synchronized(fIdToCallback)	{
			fIdToCallback.remove(((IREMBeanProxy) callbackProxy).getID());
			fRegisteredCallbackProxies.remove(callbackProxy);	// Release it.
		}
	}
	

}
