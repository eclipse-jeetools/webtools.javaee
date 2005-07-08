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
 *  $Revision: 1.5 $  $Date: 2005/07/08 17:51:47 $ 
 */

import java.net.Socket;
import java.util.*;

import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent;

/**
 * This registry will handle callbacks.
 * It is package protected because no one
 * should access it outside.
 */
class REMCallbackRegistry implements ICallbackRegistry {
	final REMProxyFactoryRegistry fFactory;
	final String fNamePostfix;
	List fThreads = Collections.synchronizedList(new LinkedList());	// List of active callback threads.	
	
	Map fIdToCallback = new HashMap(5);	// ID to Callback map.
	Map fCallbackProxyToId = new HashMap(5);	// Callback to ID map. This will also hold onto the callback proxies so that they don't get GC'd while the callback is registered.
		
	IREMMethodProxy fInitializeCallback;
	IBeanProxy fRemoteServer;
	
	boolean registryOpen = true;
	
	public REMCallbackRegistry(String name, REMProxyFactoryRegistry aFactory) {
	
		fFactory = aFactory;
		fNamePostfix = name;
		
		// Now register common proxies.
		IREMBeanTypeProxy callbackType = new REMInterfaceBeanTypeProxy(fFactory, new Integer(Commands.ICALLBACK_CLASS), "org.eclipse.jem.internal.proxy.vm.remote.ICallback"); //$NON-NLS-1$
	
		fInitializeCallback = (IREMMethodProxy) ((REMMethodProxyFactory) fFactory.getMethodProxyFactory()).methodType.newBeanProxy(new Integer(Commands.INITIALIZECALLBACK_METHOD_ID));
		
		fRemoteServer = aFactory.getBeanProxyFactory().getIVMServerProxy();	// For us, the IVMServer on the remote vm ALSO implements IVMCallbackServer.

		((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).registerBeanTypeProxy(callbackType, true);
		((REMStandardBeanProxyFactory) fFactory.getBeanProxyFactory()).registerProxy(fInitializeCallback);
		
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
		fCallbackProxyToId.clear();
		fInitializeCallback = null;
		fRemoteServer = null;
			
	}		
	
	public ICallback getRegisteredCallback(int id) {
		synchronized(fIdToCallback)	{	
			return (ICallback) fIdToCallback.get(new Integer(id));
		}
	}
		
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.ICallbackRegistry#registerCallback(org.eclipse.jem.internal.proxy.core.IBeanProxy, org.eclipse.jem.internal.proxy.core.ICallback)
	 */
	public void registerCallback(IBeanProxy callbackProxy, ICallback cb) {
		synchronized(fIdToCallback) {
			fIdToCallback.put(((IREMBeanProxy) callbackProxy).getID(), cb);
			fCallbackProxyToId.put(callbackProxy, ((IREMBeanProxy) callbackProxy).getID());
			fInitializeCallback.invokeCatchThrowableExceptions(callbackProxy, new IBeanProxy[] {fRemoteServer, fFactory.getBeanProxyFactory().createBeanProxyWith(((IREMBeanProxy) callbackProxy).getID().intValue())});
		}
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.ICallbackRegistry#registerCallback(org.eclipse.jem.internal.proxy.core.IProxy, org.eclipse.jem.internal.proxy.core.ICallback, org.eclipse.jem.internal.proxy.core.IExpression)
	 */
	public void registerCallback(IProxy callbackProxy, final ICallback cb, IExpression expression) {
		final Integer id;
		if (callbackProxy.isBeanProxy()) {
			id = ((IREMBeanProxy) callbackProxy).getID();
			synchronized(fIdToCallback) {
				fIdToCallback.put(id, cb);
				fCallbackProxyToId.put(callbackProxy, id);
			}
		} else {
			id = new Integer(callbackProxy.hashCode());
			synchronized (fIdToCallback) {
				fIdToCallback.put(id, cb);	// This is so that it is registered in case callback is invoked from remote vm during expression processing.
			}
			((ExpressionProxy) callbackProxy).addProxyListener(new ExpressionProxy.ProxyListener() {
				public void proxyResolved(ProxyEvent event) {
					synchronized(fIdToCallback) {
						fCallbackProxyToId.put(event.getProxy(), id);
					}
				}
				
				public void proxyNotResolved(ProxyEvent event) {
					// Failed, so remove registration completely.
					synchronized (fIdToCallback) {
						fIdToCallback.remove(id);
					}
				}
				
				public void proxyVoid(ProxyEvent event) {
					// Failed, so remove registration completely.
					synchronized (fIdToCallback) {
						fIdToCallback.remove(id);
					}
				}
				
			});
		}
		expression.createSimpleMethodInvoke(fInitializeCallback, callbackProxy, new IProxy[] {fRemoteServer, fFactory.getBeanProxyFactory().createBeanProxyWith(id.intValue())}, false);
	}
	
	/**
	 * The public interface for deregistering callbacks.
	 */
	public void deregisterCallback(IBeanProxy callbackProxy) {
		synchronized(fIdToCallback)	{
			Integer id = (Integer) fCallbackProxyToId.remove(callbackProxy);
			fIdToCallback.remove(id);
		}
	}
	

}
