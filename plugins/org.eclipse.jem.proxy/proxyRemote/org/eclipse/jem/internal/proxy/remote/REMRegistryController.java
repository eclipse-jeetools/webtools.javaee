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
 *  $RCSfile: REMRegistryController.java,v $
 *  $Revision: 1.11 $  $Date: 2005/06/21 20:35:08 $ 
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
	
	protected boolean inShutdown;	// Are we in shutdown mode. Terminate registries runs differently in shutdown mode.
	
	// Thread to clean up GC'd proxies. Runs as a daemon at the lowest priority
	private Job processQueueJob= new Job(ProxyRemoteMessages.CleanupJob_title) { 
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
	
	/**
	 * Answer whether we are shutting down or not.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public boolean inShutDown() {
		return inShutdown;
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
		
		inShutdown = true;	// We are now in shutdown mode.
		// In shutdown mode the registries will not create the job that waits for the process
		// to terminate, and if it doesn't in 1.5 secs it does a force.
		// Instead what we will do is shutdown all of the registries. If they don't
		// shutdown on their own then they stay out there. But they do have an
		// internal timer that checks every five minutes to see if the host is 
		// still there, and if it isn't they will shut themselves down. They
		// would have to be really hosed if the suicide timer wasn't working.
		
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
		
		REMProxyFactoryRegistry.cancelAllTerminateJobs();
	}
	
	/**
	 * Return the master socket port number.
	 */
	public int getMasterSocketPort() {
		return masterThread != null ? masterThread.getMasterSocket().getLocalPort() : -1;
	}	

}
