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
 *  $RCSfile: REMCallbackServerThread.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.ICallback;
import org.eclipse.jem.internal.proxy.core.ICallbackRegistry;
import org.eclipse.jem.internal.proxy.common.remote.Commands;

/**
 * This thread will handle callbacks.
 * It is package protected because no one
 * should access it outside.
 */
class REMCallbackServerThread extends Thread implements ICallbackRegistry {
	final REMProxyFactoryRegistry fFactory;
	final String fNamePostfix;
	ServerSocket fServer;
	List fThreads = Collections.synchronizedList(new LinkedList());	// List of active callback threads.	
	
	HashMap fIdToCallback = new HashMap(5);	// ID to Callback map.
	HashSet fRegisteredCallbackProxies = new HashSet(5);	// Hold onto registered proxies so they aren't released as long as call back is registered.
	
	IREMMethodProxy fInitializeCallback;
	IREMBeanProxy fRemoteServer;
	
	Object fStarted = null;	// Started semaphore
	
	// Kludge: Bug in Linux 1.3.xxx of JVM. Closing a socket while the socket is being read/accept will not interrupt the
	// wait. Need to timeout to the socket read/accept before the socket close will be noticed. This has been fixed
	// in Linux 1.4. So on Linux 1.3 need to put timeouts in on those sockets that can be separately closed while reading/accepting.
	static boolean LINUX_1_3 = "linux".equalsIgnoreCase(System.getProperty("os.name")) && System.getProperty("java.version","").startsWith("1.3"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	
	public REMCallbackServerThread(String name, REMProxyFactoryRegistry aFactory, Object started) {
		super("Callback Server Thread-"+name); //$NON-NLS-1$
	
		fFactory = aFactory;
		fNamePostfix = name;
		fStarted = started;
		
		try {
			fServer = new ServerSocket(0, 50, InetAddress.getByName("localhost"));	// Create using whatever port is available. //$NON-NLS-1$
		} catch (IOException e) {
			return;
		}	
		
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
	
	/**
	 * Return the server port this connection server is using.
	 * Answer -1 if the socket could not be established.
	 */
	public int getServerPort() {
		return fServer != null ? fServer.getLocalPort() : -1;
	}
	
	public void run() {
		try {
			if (LINUX_1_3)
				fServer.setSoTimeout(1000);	// Need to periodically timeout to see if socket closed.
			synchronized(fStarted) {
				fStarted.notify();	// Let the caller know the server socket is ready.
			}
			fStarted = null;	// So it can be GC'd.
			
			while(true) {
				Socket incoming = null;
				try {
					incoming = fServer.accept();
				} catch (java.io.InterruptedIOException e) {
					continue;	// Interrupted, try again.
				}
				Thread st = new REMCallbackThread(incoming, this, "Callback Thread-"+fNamePostfix, fFactory, fFactory.fNoTimeouts); //$NON-NLS-1$
				fThreads.add(st);
				st.start();
				// Null out locals so they can be GC'd since this is a long running loop.
				st = null;
				incoming = null;
			}
		} catch (Exception e) {
		}
		
		// We've had an exception, either something really bad, or we were closed,
		// so go through shutdowns.
		shutdown();					
	}
	
	/**
	 * Use this to request a shutdown. If the server hasn't even been
	 * created yet, this will return false.
	 */
	public boolean requestShutdown() {		
		if (fServer == null)
			return false;
		// Closing the server socket should cause a break.
		try {
			fServer.close();
		} catch (Exception e) {
		}
		return true;
	}
	
	/**
	 * Remove a thread from the list.
	 */
	public void removeCallbackThread(REMCallbackThread thread) {
		fThreads.remove(thread);
	}
	
	private void shutdown() {
		// At this point in time, the remote process has already been shutdown (because this
		// is called after the process has terminated), so all of the threads should already 
		// be gone. This will get rid of any outstanding ones and clean up.
		if (fServer != null)
			try {
				fServer.close();	// Close it so that no more requests can be made.
			} catch (Exception e) {
			}
		
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