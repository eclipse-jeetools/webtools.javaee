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
 *  $RCSfile: REMRegistryController.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a controller for all of the registries.
 * It's main purpose is so that it can be separate from the
 * plugin structure so that eventually can run outside of the IDE.
 * @author richkulp
 */
public class REMRegistryController {
	
	private Map fActiveRegistries = new HashMap();
	
	// Thread to clean up GC'd proxies. Runs as a daemon at the lowest priority
	private boolean goingDown = false;
	private Thread processQueueThread = new Thread(new Runnable() {
		public void run() {
			do {			
				if (Thread.interrupted())
					continue; // Get to clean uninterrupted state.
				try {
					Thread.sleep(60000); // Sleep until interrupted or 60 seconds to process the queue again.
					REMProxyFactoryRegistry[] registries = null;
					synchronized (fActiveRegistries) {
						// This list may be updated by others, so we need to make a copy
						// or else we could get a failure.
						registries = 
							(REMProxyFactoryRegistry[]) fActiveRegistries.values().toArray(
								new REMProxyFactoryRegistry[fActiveRegistries.size()]);
					}
					for (int i = 0; i < registries.length; i++) {
						try {
							((REMStandardBeanProxyFactory) registries[i].getBeanProxyFactory()).processQueue();					
						} catch (RuntimeException e) {
							// When debugging, getBeanProxyFactory can throw exception because it hasn't been initialized
							// yet when the thread wakes up, though the registry has been registered. It has to do with it 
							// can take significant time for the user to start up the debugger, and during that time this
							// thread could kick in.
						}
					}
					
				} catch (InterruptedException e) {
				}
			} while (!goingDown);
		}

	}, "Remote VM Cleanup GC'd Proxies Thread"); //$NON-NLS-1$	
	
	public REMRegistryController() {
		masterThread = new REMMasterServerThread(this);
		masterThread.start();
		
	processQueueThread.setPriority(Thread.MIN_PRIORITY);
	processQueueThread.setDaemon(true);
	processQueueThread.start();
		
	}
	
	/*
	 * Add registry to list of active. Return a unique number to be the key.
	 * Package-protected so that only locals can access it.
	 */
	synchronized Integer registerRegistry(REMProxyFactoryRegistry registry) {
			
		Integer hashcode = new Integer(registry.hashCode());
		while (true) {
			REMProxyFactoryRegistry existing = (REMProxyFactoryRegistry) fActiveRegistries.get(hashcode);
			if (existing == null)
				break;	// Not yet registered, use use the hashcode
			else if (existing != registry)
				hashcode = new Integer(hashcode.intValue()+1);						
			else
				return hashcode;	// Same guy, use the hashcode.
		}
		
		fActiveRegistries.put(hashcode, registry);
		return hashcode;
	}
	
	/*
	 * deregister the registry.
	 */
	synchronized void deregisterRegistry(Integer key) {
		fActiveRegistries.remove(key);
	}
	
	/*
	 * Return the registry for the given key
	 */
	synchronized REMProxyFactoryRegistry getRegistry(Integer key) {
		return (REMProxyFactoryRegistry) fActiveRegistries.get(key);
	}
	
	/**
	 * Master server thread. Handles keep-alive requests and register remote server threads.
	 * It will be created when needed.
	 */
	protected REMMasterServerThread masterThread;

	/**
	 * Shuts down this plug-in and discards all plug-in state.
	 *
	 * In this case, terminate all of the active registries so that they can be shutdown.
	 * Don't want them hanging around after termination of the desktop.
	 *
	 * @exception CoreException if this method fails to shut down
	 *   this plug-in 
	 */
	public void shutdown() {
		
		goingDown = true;
		processQueueThread.interrupt();
			
		REMProxyFactoryRegistry[] registries = null;
		synchronized (fActiveRegistries) {
			// This list will be updated in the terminateRegistry, so we need to make a copy
			// or else we get a failure.
			registries = 
				(REMProxyFactoryRegistry[]) fActiveRegistries.values().toArray(
					new REMProxyFactoryRegistry[fActiveRegistries.size()]);
		}
		for (int i = 0; i < registries.length; i++)
			registries[i].terminateRegistry();
			
		if (masterThread != null) {
			try {
				masterThread.requestShutdown();
				masterThread.join(20000);	// Wait 20 seconds for everything to go down.
				masterThread = null;				
			} catch (InterruptedException e) {
			}
		}
		
		try {
			processQueueThread.join(5000);	// Wait 5 secs for it stop.
		} catch(InterruptedException e) {
		}
		
	}
	
	/**
	 * Return the master socket port number.
	 */
	public int getMasterSocketPort() {
		return masterThread != null ? masterThread.getMasterSocket().getLocalPort() : -1;
	}	

}
