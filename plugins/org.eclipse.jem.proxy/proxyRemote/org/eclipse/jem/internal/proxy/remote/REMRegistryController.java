/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMRegistryController.java,v $
 *  $Revision: 1.6 $  $Date: 2004/08/27 15:35:20 $ 
 */

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

import org.eclipse.jem.internal.proxy.core.ProxyPlugin;

/**
 * This class is a controller for all of the registries.
 * @author richkulp
 */
public class REMRegistryController {
	
	private Map fActiveRegistries = new HashMap();	// Access to this must be sync(REMRegistryController)
	private static final long CLEANUP_INTERVAL = 60000l;	// The interval between clean up job execution.
	
	// Thread to clean up GC'd proxies. Runs as a daemon at the lowest priority
	private Job processQueueJob= new Job(ProxyRemoteMessages.getString("CleanupJob.title")) { //$NON-NLS-1$
		public IStatus run(IProgressMonitor m) {
				REMProxyFactoryRegistry[] registries = null;
				synchronized (REMRegistryController.this) {
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
			synchronized(this) {
				if (!m.isCanceled())
					this.schedule(CLEANUP_INTERVAL);	// Schedule to start again in one minute.
			}
			return Status.OK_STATUS;
		}

	}; //$NON-NLS-1$	
	
	public REMRegistryController() {

		ProxyPlugin.getPlugin().addProxyShutdownListener(new ProxyPlugin.IProxyPluginShutdownListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jem.internal.proxy.core.ProxyPlugin.IProxyPluginShutdownListener#shutdown()
			 */
			public void shutdown() {
				REMRegistryController.this.shutdown();
			}
		});

		masterThread = new REMMasterServerThread(this);
		masterThread.start();

		processQueueJob.setSystem(true);	// So that it won't show processing in process view. Not of interest to general users.
		processQueueJob.setPriority(Job.SHORT);
		processQueueJob.schedule(CLEANUP_INTERVAL);

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

	/*
	 * Shuts down this plug-in and discards all plug-in state.
	 *
	 * In this case, terminate all of the active registries so that they can be shutdown.
	 * Don't want them hanging around after termination of the desktop.
	 */
	void shutdown() {
		
		synchronized(processQueueJob) {
			processQueueJob.cancel();
		}
			
		REMProxyFactoryRegistry[] registries = null;
		synchronized (this) {
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
			processQueueJob.join();
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
