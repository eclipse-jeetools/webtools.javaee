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
 *  $RCSfile: REMProxyFactoryRegistry.java,v $
 *  $Revision: 1.5 $  $Date: 2004/03/04 20:30:21 $ 
 */


import java.io.IOException;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Stack;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.*;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * This is the factory registry to use for Remote VM.
 * It adds to the standard registry, connection specific information.
 *
 * This will always hold onto one connection open until termination is requested.
 * That way while the IDE is up, the remove vm won't time out.
 */

public class REMProxyFactoryRegistry extends ProxyFactoryRegistry {
	protected int fServerPort = 0;	// The server port to use when making connections.
	protected REMCallbackRegistry fCallbackServer;	// The callback server thread for this remote vm.
	protected Stack fConnectionPool = new Stack();	// Stack of free connections.
	protected static int NUMBER_FREE_CONNECTIONS = 5;	// Number of free connections to keep open.
	protected IProcess fProcess;	// The process that is the server. If null and fServerPort is not zero, 
									// then this registry is in test mode
									// and the server is in same the process.
	protected String fName;
	protected int fCallbackServerPort;
	protected Integer fRegistryKey;
	protected REMRegistryController fRegistryController;
	
	// Package protected because only the ProxyVMStarter should set this flag. It would set it if
	// working with a debugger because we don't how long it will be to respond to requests when 
	// someone is working with a debugger.
	boolean fNoTimeouts = false;
	
	// This is set via the static setGlobalNoTimeouts() method. It is here so that
	// when debugging callbacks, but not debugging remote vm, that no timeouts for any registry will occur. 
	static boolean fGlobalNoTimeouts = false;
	
	/**
	 * Typicall set through the "expression" evaluation of the debugger.
	 * @param noTimeouts
	 * 
	 * @since 1.0.0
	 */
	public static void setGlobalNoTimeouts(boolean noTimeouts) {
		fGlobalNoTimeouts = noTimeouts;
	}
	
	// An internal thread that locks and waits for the remote vm to register itself. 
	private WaitForRegistrationThread waitRegistrationThread;
	
	private class WaitForRegistrationThread extends Thread {
		public WaitForRegistrationThread() {
			super("Wait for remote vm registration thread"); //$NON-NLS-1$
		}
		
		/**
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			// Wait for registration. Put it into a thread so this
			// can occur while other stuff goes on. It locks the fConnectionPool
			// until done so that the first request for a connection by anyone
			// else will wait until this thread is finished. 
			
			synchronized(fConnectionPool) {
				synchronized(REMProxyFactoryRegistry.this) {
					// Notify the main thread that we have the 
					// connection pool locked.
					REMProxyFactoryRegistry.this.notifyAll();
				}
				synchronized (this) {
					// sync on self so that it can be notified when finally receive the registration
					long stopTime = System.currentTimeMillis()+60000;
					while (waitRegistrationThread != null && (fNoTimeouts || System.currentTimeMillis() < stopTime)) {
						try {
							Thread.currentThread().wait(60000);
						} catch (InterruptedException e) {
						}
					}
				}
			}
			
			waitRegistrationThread = null;	// No longer exists.				
		}
	}

	
	public REMProxyFactoryRegistry(REMRegistryController registryController, String name) {
		fRegistryController = registryController;		
		fRegistryKey = fRegistryController.registerRegistry(this);	// Register the registry with the plugin.		
		fName = name;		
		
		// Get the waitRegistrationThread started before we actually launch remote vm so
		// that it is waiting when the callback comes in.
		synchronized (this) {
			waitRegistrationThread = new WaitForRegistrationThread();
			waitRegistrationThread.start();
			
			// Now we will wait until the connection pool has been locked. The thread will
			// signal us when that is done. This is so that we don't continue on and let
			// a work connection be requested before we even got a chance to start waiting
			// for the registration.
			while(true) {
				try {
					wait();
					break;
				} catch (InterruptedException e) {
				}
			};		
		}		
	}
	
	public Integer getRegistryKey() {
		return fRegistryKey;
	}
	
	public void initializeRegistry(IProcess process) {
		fProcess = process;
		processListener = new IDebugEventSetListener() {
			/**
			 * @see org.eclipse.debug.core.IDebugEventSetListener#handleDebugEvents(DebugEvent[])
			 */
			public void handleDebugEvents(DebugEvent[] events) {
				for (int i = 0; i < events.length; i++) {
					DebugEvent e = events[i];
					if (e.getSource() == fProcess && e.getKind() == DebugEvent.TERMINATE) {
						// We terminating too soon. Pop up a msg.
						IStreamsProxy stProxy = fProcess.getStreamsProxy();
						java.io.StringWriter s = new java.io.StringWriter();
						java.io.PrintWriter w = new java.io.PrintWriter(s);
		
						String msg = MessageFormat.format(ProxyRemoteMessages.getString("Proxy_Terminated_too_soon_ERROR_"), new Object[] {fName}); //$NON-NLS-1$
						w.println(msg);						
						w.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.VM_TERMINATED));
						w.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.VM_TERMINATED_LINE1));
						w.println(stProxy.getErrorStreamMonitor().getContents());
						w.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.VM_TERMINATED_LINE2));
						w.println(stProxy.getOutputStreamMonitor().getContents());
						w.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.VM_TERMINATED_LINE3));
						w.close();
		
						DebugModeHelper dh = new DebugModeHelper();
						dh.displayErrorMessage(ProxyRemoteMessages.getString("Proxy_Error_Title"), msg); //$NON-NLS-1$
						ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, s.toString(), null));
						processListener = null;
						DebugPlugin.getDefault().removeDebugEventListener(this);						
						terminateRegistry();
						break;
					}
				}
			}
		};
		
		DebugPlugin.getDefault().addDebugEventListener(processListener);
	}
	
	private IDebugEventSetListener processListener = null;
	
	/**
	 * Get the CallbackRegistry
	 */
	public ICallbackRegistry getCallbackRegistry() {
		if (fCallbackServer == null)
			fCallbackServer = new REMCallbackRegistry(fName, this);
		return fCallbackServer;
	}
	
	/**
	 * Terminate the server.
	 */
	protected void registryTerminated() {
		if (processListener != null) {
			// Remove listener cause we are now going to terminate process and don't want premature terminate notice.
			// Sometimes in shutdown we are called and the debug plugin may of already been shutdown. In that case the db
			// will be null and there is nothing remove listener from.
			DebugPlugin db = DebugPlugin.getDefault();
			if (db != null)
				db.removeDebugEventListener(processListener);
			processListener = null;
		}
		
		if (fServerPort != 0) {
			if (waitRegistrationThread != null) {
				synchronized (waitRegistrationThread) {
					// Still waiting. close it out.
					WaitForRegistrationThread wThread = waitRegistrationThread;
					waitRegistrationThread = null;
					wThread.notifyAll();
				}
			}
					
			IREMConnection closeCon = null;	// The connection we will use to close the remote vm.			
			synchronized(fConnectionPool) {
				// Now we walk through all of the free connections and close them properly.
				Iterator itr = fConnectionPool.iterator();
				if (itr.hasNext())
					closeCon = (IREMConnection) itr.next();
				while (itr.hasNext()) {
					IREMConnection con = (IREMConnection) itr.next();
					con.close();
				}
			}
				
			// Now we terminate the server.
			if (closeCon == null)
				try {
					closeCon = getFreeConnection();	// There weren't any free connections, so get a new one so that we can close it.
				} catch (IllegalStateException e) {
					// Do nothing, don't want to stop termination just because we can't get a connection.
				}
			if (closeCon != null)
				closeCon.terminateServer();	// We got a connection to terminate (process may of terminated early, so we would not have a conn then).
			fConnectionPool.clear();
			fServerPort = 0;
			if (fProcess != null) {
				try {
					// There is no join on a process available, so we will have to
					// busy wait. Give it 10 seconds in 1/10 second intervals.
					for (int i=0; !fProcess.isTerminated() && i<100; i++) {
						try {
							Thread.sleep(100);							
						} catch (InterruptedException e) {
						}
					}
					if (!fProcess.isTerminated())
						fProcess.terminate();
				} catch (DebugException e) {
				}
				fProcess = null;
			}
		}
	
		if (fCallbackServer != null) {	
			fCallbackServer.requestShutdown();
			fCallbackServer = null;				
		}
		
		fConnectionPool.clear();
		fRegistryController.deregisterRegistry(fRegistryKey);	// De-register this registry.
	}
			
	/**
	 * Return the server port number.
	 */
	public int getServerPort() {
		return fServerPort;
	}
	
	/*
	 * set the server port.
	 */
	void setServerPort(int serverport) {
		fServerPort = serverport;
		if (waitRegistrationThread != null) {
				synchronized (waitRegistrationThread) {
					// Close it out, we are now registered
					WaitForRegistrationThread wThread = waitRegistrationThread;
					waitRegistrationThread = null;
					wThread.notifyAll();
				}
		}
	}
	
	/**
	 * Get a free connection
	 * @return
	 * @throws IllegalStateException - Thrown if a connection cannot be created.
	 * 
	 * @since 1.0.0
	 */
	public IREMConnection getFreeConnection() throws IllegalStateException {
		Thread thread = Thread.currentThread();
		if (thread instanceof REMCallbackThread) {
			// The current thread is a call back thread, so just reuse the connection.
			// This way any calls out to the remote vm will be on same thread as callback caller
			// on remote vm because that thread is waiting on this connection for commands.
			IREMConnection c = ((REMCallbackThread) thread).getConnection();
			if (c.isConnected())
				return c;
			else
				throw new IllegalStateException("Callback connection is not working.");
		}
		synchronized(fConnectionPool) {
			if (!fConnectionPool.isEmpty())
				return (IREMConnection) fConnectionPool.pop();
			// else we need to allocate one.			
			return createConnection();
		}
	}
	

	/**
	 * Make a new connection.
	 * @return
	 * @throws IllegalStateException - Thrown if connection cannot be created.
	 * 
	 * @since 1.0.0
	 */
	protected IREMConnection createConnection() throws IllegalStateException {
		// If we have a server port, then the server is probably open. If we don't then there is no server.
		if (fServerPort != 0) {
			// We are putting it off into a thread because there are no timeout capabilities on getting a socket.
			// So we need to allow for that.
			final Socket[] scArray = new Socket[1];
			final boolean[] waiting = new boolean[] {true};
			Thread doIt = new Thread(new Runnable() {
				public void run() {
					try {
						Socket sc = new Socket("localhost", fServerPort); //$NON-NLS-1$
						synchronized (this) {
							if (waiting[0])
								scArray[0] = sc;
							else
								sc.close();	// We are no longer waiting on this thread so close the socket since no one will use it.
						}
					} catch (IOException e) {
						ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e));
					}
				}
			});
			
			doIt.start();
			while (true) {
				try {
					doIt.join(!fNoTimeouts ? 60000 : 0);
					synchronized (doIt) {
						waiting[0] = false;	// To let it know we are no longer waiting
					}
					break;
				} catch (InterruptedException e) {
				}
			}
			
			if (scArray[0] == null)  {
				// Log where we are at so we can know where it was we down.
				ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", new RuntimeException("Connection creation failed.")));	//$NON-NLS-1$
				throw new IllegalStateException("Could not create a socket connection to remote vm.");	// Couldn't get one, probably server is down.
			}

			REMConnection connection = new REMConnection(scArray[0], fNoTimeouts);
			if (connection.isConnected())
				return connection;
				
			// Failed, close the socket. 
			try {
				scArray[0].close();
			} catch (IOException e) {
			}
		} else
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.INFO, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "No Server to retrieve a connection.", null));	///$NON-NLS-1$
		
		throw new IllegalStateException("Could not create a socket connection to remote vm.");
	}
		 
	/**
	 * Free the connection
	 */
	public void returnConnection(IREMConnection connection) {
		if (connection.isConnected()) {
			Thread thread = Thread.currentThread();
			if (!(thread instanceof REMCallbackThread) || ((REMCallbackThread) thread).getConnection() != connection) {
				// We are not a callback thread, or we are but the connection is not for the thread, then the connection
				// can be returned.
				synchronized (fConnectionPool) {
					if (fConnectionPool.size() < NUMBER_FREE_CONNECTIONS)
						fConnectionPool.push(connection);
					else
						connection.close();	// We don't need to maintain more than five free connections.
				}
			}
		}
	}
	
	/**
	 * Release this connection. This means close it out.
	 */
	public void closeConnection(IREMConnection connection) {
		connection.close();
	}
	
	
	public int connectionCount() {
		synchronized (fConnectionPool) {
			return fConnectionPool.size();
		}
	}
}