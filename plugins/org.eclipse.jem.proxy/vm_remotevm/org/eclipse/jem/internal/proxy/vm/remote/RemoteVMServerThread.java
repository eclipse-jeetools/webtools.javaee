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
package org.eclipse.jem.internal.proxy.vm.remote;
/*
 *  $RCSfile: RemoteVMServerThread.java,v $
 *  $Revision: 1.13 $  $Date: 2005/12/14 21:23:46 $ 
 */


import java.util.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import org.eclipse.jem.internal.proxy.common.remote.*;
import org.eclipse.jem.internal.proxy.common.*;
/**
 * RemoteVM Server Thread. This thread is the one
 * that waits for connections and spins off 
 * server connection threads. It manages the
 * connection threads and handles shutting them
 * down.
 *
 * System Properties:
 *   proxyvm.port - Port number to use for the ServerSocket (default is 8888)
 *   proxyvm.bufsize - Buffer size to use for TCP/IP buffers (default is system default)
 */

public class RemoteVMServerThread extends Thread implements IVMServer, IVMCallbackServer {
	protected List threads = Collections.synchronizedList(new LinkedList());	// List of active threads.
	protected ServerSocket server;	// Server Socket for this application
	private int highestIdentityID = 0;	// Identity codes to identify objects between server and client.
	private Map objectToIDMap;
	private HashMap idToObjectMap = new HashMap(100);	// Map from identity id to object
	
	protected Stack fCallbackHandlerPool = new Stack();	// Stack of free callback handlers
	protected static int NUMBER_FREE_CALLBACKS = 5;	// Number of free callback handlers to keep open.
	
	public static int ID_NOT_FOUND = Commands.NOT_AN_ID;	// The id was not found in the table.
	
	protected int masterIDESocketPort = -1;	// Port of master server socket on IDE. Used for special global requests.
	protected int registryKey = -1;	// Key of registry on the IDE.
	
	// Kludge: Bug in Linux 1.3.xxx of JVM. Closing a socket while the socket is being read/accept will not interrupt the
	// wait. Need to timeout to the socket read/accept before the socket close will be noticed. This has been fixed
	// in Linux 1.4. So on Linux 1.3 need to put timeouts in on those sockets that can be separately closed while reading/accepting.
	static boolean LINUX_1_3 = "linux".equalsIgnoreCase(System.getProperty("os.name")) && System.getProperty("java.version","").startsWith("1.3");	 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	
	// If version 1.3.x, we need to use our IdentidyMap, if 1.4 or greater then we can use Java's IdentidyHashMap, which is more efficient than ours.
	static Constructor IDENTIDYMAP_CLASS_CTOR;
	static {
		Class idClass;
		try {
			idClass = Class.forName("java.util.IdentityHashMap"); //$NON-NLS-1$
		} catch (ClassNotFoundException e) {
			idClass = IdentityMap.class;
		}
		try {
			IDENTIDYMAP_CLASS_CTOR = idClass.getConstructor(new Class[] {Integer.TYPE});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public RemoteVMServerThread(String name) {
		super(name);
		try {
			objectToIDMap = (Map) IDENTIDYMAP_CLASS_CTOR.newInstance(new Object[] {new Integer(100)});
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	// The purpose of this thread is to wait 5 minutes, then see if the IDE is still
	// up. If it isn't it will go down. This is safety mechanism
	// in case the client went down without cleaning up and telling the server to go down.
	// That way it won't hang around forever.
	private boolean goingDown = false;
	private Thread safeClean = new Thread(new Runnable() {
		public void run() {
			while (!goingDown) {
				if (Thread.interrupted())
					continue;	// Get to clean uninterrupted state.
				try {
					Thread.sleep(5 * 60 * 1000);	// Sleep five minutes
					// Test if IDE still up.
					if (!isAlive()) {
						System.err.println("No registry available to connect with after five minutes. Shutting down.");	//$NON-NLS-1$
						requestShutdown();
						break;
					}
				} catch (InterruptedException e) {
				}
			}
		}

		/*
		 * See if still alive
		 */		
		private boolean isAlive() {
			Socket socket = getSocket();
			if (socket != null) {
				try {
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					DataInputStream in = new DataInputStream(socket.getInputStream());
					
					try {
						out.writeByte(Commands.ALIVE);
						out.writeInt(registryKey);
						out.flush();
						return in.readBoolean();
						// Now get the result.
					} finally {
						try {
							in.close();
						} catch (IOException e) {
						}
						try {
							out.close();
						} catch (IOException e) {
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();	// They should be closing. If they aren't, then they accumulate and master server will start rejecting new ones.
					}
				}					
			}
			
			return false;
		}	
		
	}, "Timeout Termination Thread"); //$NON-NLS-1$
	
	
	public void run() {
		
		// Initialize the mapping table with certain pre-defined ids.
		synchronized(objectToIDMap) {
			objectToIDMap.put(Void.TYPE, new Integer(Commands.VOID_TYPE));
			idToObjectMap.put(new Integer(Commands.VOID_TYPE), Void.TYPE);
			
			objectToIDMap.put(Boolean.TYPE, new Integer(Commands.BOOLEAN_TYPE));
			idToObjectMap.put(new Integer(Commands.BOOLEAN_TYPE), Boolean.TYPE);
			objectToIDMap.put(Boolean.class, new Integer(Commands.BOOLEAN_CLASS));
			idToObjectMap.put(new Integer(Commands.BOOLEAN_CLASS), Boolean.class);
			
			objectToIDMap.put(Integer.TYPE, new Integer(Commands.INTEGER_TYPE));
			idToObjectMap.put(new Integer(Commands.INTEGER_TYPE), Integer.TYPE);
			objectToIDMap.put(Integer.class, new Integer(Commands.INTEGER_CLASS));
			idToObjectMap.put(new Integer(Commands.INTEGER_CLASS), Integer.class);
			
			objectToIDMap.put(Byte.TYPE, new Integer(Commands.BYTE_TYPE));
			idToObjectMap.put(new Integer(Commands.BYTE_TYPE), Byte.TYPE);
			objectToIDMap.put(Byte.class, new Integer(Commands.BYTE_CLASS));
			idToObjectMap.put(new Integer(Commands.BYTE_CLASS), Byte.class);
			
			objectToIDMap.put(Short.TYPE, new Integer(Commands.SHORT_TYPE));
			idToObjectMap.put(new Integer(Commands.SHORT_TYPE), Short.TYPE);
			objectToIDMap.put(Short.class, new Integer(Commands.SHORT_CLASS));
			idToObjectMap.put(new Integer(Commands.SHORT_CLASS), Short.class);	
			
			objectToIDMap.put(Long.TYPE, new Integer(Commands.LONG_TYPE));
			idToObjectMap.put(new Integer(Commands.LONG_TYPE), Long.TYPE);
			objectToIDMap.put(Long.class, new Integer(Commands.LONG_CLASS));
			idToObjectMap.put(new Integer(Commands.LONG_CLASS), Long.class);
			
			objectToIDMap.put(Character.TYPE, new Integer(Commands.CHARACTER_TYPE));
			idToObjectMap.put(new Integer(Commands.CHARACTER_TYPE), Character.TYPE);
			objectToIDMap.put(Character.class, new Integer(Commands.CHARACTER_CLASS));
			idToObjectMap.put(new Integer(Commands.CHARACTER_CLASS), Character.class);
			
			objectToIDMap.put(Double.TYPE, new Integer(Commands.DOUBLE_TYPE));
			idToObjectMap.put(new Integer(Commands.DOUBLE_TYPE), Double.TYPE);
			objectToIDMap.put(Double.class, new Integer(Commands.DOUBLE_CLASS));
			idToObjectMap.put(new Integer(Commands.DOUBLE_CLASS), Double.class);
			
			objectToIDMap.put(Float.TYPE, new Integer(Commands.FLOAT_TYPE));
			idToObjectMap.put(new Integer(Commands.FLOAT_TYPE), Float.TYPE);
			objectToIDMap.put(Float.class, new Integer(Commands.FLOAT_CLASS));
			idToObjectMap.put(new Integer(Commands.FLOAT_CLASS), Float.class);														
						
			objectToIDMap.put(String.class, new Integer(Commands.STRING_CLASS));
			idToObjectMap.put(new Integer(Commands.STRING_CLASS), String.class);
			
			objectToIDMap.put(java.math.BigDecimal.class, new Integer(Commands.BIG_DECIMAL_CLASS));
			idToObjectMap.put(new Integer(Commands.BIG_DECIMAL_CLASS), java.math.BigDecimal.class);
			
			objectToIDMap.put(java.math.BigInteger.class, new Integer(Commands.BIG_INTEGER_CLASS));
			idToObjectMap.put(new Integer(Commands.BIG_INTEGER_CLASS), java.math.BigInteger.class);
			
			objectToIDMap.put(Number.class, new Integer(Commands.NUMBER_CLASS));
			idToObjectMap.put(new Integer(Commands.NUMBER_CLASS), Number.class);				
			
			objectToIDMap.put(Throwable.class, new Integer(Commands.THROWABLE_CLASS));
			idToObjectMap.put(new Integer(Commands.THROWABLE_CLASS), Throwable.class);	
			
			
			objectToIDMap.put(Object.class, new Integer(Commands.OBJECT_CLASS));
			idToObjectMap.put(new Integer(Commands.OBJECT_CLASS), Object.class);	
						
			objectToIDMap.put(Class.class, new Integer(Commands.CLASS_CLASS));
			idToObjectMap.put(new Integer(Commands.CLASS_CLASS), Class.class);
			
			objectToIDMap.put(java.lang.reflect.AccessibleObject.class, new Integer(Commands.ACCESSIBLEOBJECT_CLASS));
			idToObjectMap.put(new Integer(Commands.ACCESSIBLEOBJECT_CLASS), java.lang.reflect.AccessibleObject.class);								

			objectToIDMap.put(java.lang.reflect.Method.class, new Integer(Commands.METHOD_CLASS));
			idToObjectMap.put(new Integer(Commands.METHOD_CLASS), java.lang.reflect.Method.class);
			
			objectToIDMap.put(java.lang.reflect.Constructor.class, new Integer(Commands.CONSTRUCTOR_CLASS));
			idToObjectMap.put(new Integer(Commands.CONSTRUCTOR_CLASS), java.lang.reflect.Constructor.class);				
			
			objectToIDMap.put(java.lang.reflect.Field.class, new Integer(Commands.FIELD_CLASS));
			idToObjectMap.put(new Integer(Commands.FIELD_CLASS), java.lang.reflect.Field.class);	

			objectToIDMap.put(IVMServer.class, new Integer(Commands.IVMSERVER_CLASS));
			idToObjectMap.put(new Integer(Commands.IVMSERVER_CLASS), IVMServer.class);	

			objectToIDMap.put(ICallback.class, new Integer(Commands.ICALLBACK_CLASS));
			idToObjectMap.put(new Integer(Commands.ICALLBACK_CLASS), ICallback.class);	
			
			objectToIDMap.put(this, new Integer(Commands.REMOTESERVER_ID));
			idToObjectMap.put(new Integer(Commands.REMOTESERVER_ID), this);	

			objectToIDMap.put(RemoteVMServerThread.class, new Integer(Commands.REMOTEVMSERVER_CLASS));
			idToObjectMap.put(new Integer(Commands.REMOTEVMSERVER_CLASS), RemoteVMServerThread.class);	

			objectToIDMap.put(Thread.class, new Integer(Commands.THREAD_CLASS));
			idToObjectMap.put(new Integer(Commands.THREAD_CLASS), Thread.class);	

			objectToIDMap.put(ExpressionProcesserController.class, new Integer(Commands.EXPRESSIONPROCESSERCONTROLLER_CLASS));
			idToObjectMap.put(new Integer(Commands.EXPRESSIONPROCESSERCONTROLLER_CLASS), ExpressionProcesserController.class);	

			try {
				java.lang.reflect.Method getMethod = Class.class.getMethod("getMethod", new Class[] {String.class, (new Class[0]).getClass()}); //$NON-NLS-1$
				objectToIDMap.put(getMethod, new Integer(Commands.GET_METHOD_ID));
				idToObjectMap.put(new Integer(Commands.GET_METHOD_ID), getMethod);	
				
				java.lang.reflect.Method initMethod = ICallback.class.getMethod("initializeCallback", new Class[] {IVMCallbackServer.class, Integer.TYPE}); //$NON-NLS-1$
				objectToIDMap.put(initMethod, new Integer(Commands.INITIALIZECALLBACK_METHOD_ID));
				idToObjectMap.put(new Integer(Commands.INITIALIZECALLBACK_METHOD_ID), initMethod);	
				
			} catch (NoSuchMethodException e) {
				// Shouldn't really ever occur.
			}				

			highestIdentityID = Commands.FIRST_FREE_ID;		
		}

		masterIDESocketPort = Integer.getInteger("proxyvm.masterPort", -1).intValue(); //$NON-NLS-1$
		if (masterIDESocketPort == -1) {
			// No ports specified, need to just shutdown.
			shutdown();
			return;
		}
		
		registryKey = Integer.getInteger("proxyvm.registryKey", -1).intValue(); //$NON-NLS-1$
		if (registryKey == -1) {
			// No registry specified, need to just shutdown.
			shutdown();
			return;
		}		
		
		safeClean.setPriority(Thread.MIN_PRIORITY);
		safeClean.start();	
		boolean trying = true;
		try {
			server = new ServerSocket(0, 50 , InetAddress.getByName("localhost")); //$NON-NLS-1$
			trying = false;
			if (LINUX_1_3)
				server.setSoTimeout(1000);	// Linux 1.3 bug, see comment on LINUX_1_3
			if (registerServer(server.getLocalPort())) {
				while(server != null) {
					Socket incoming = null;
					try {
						incoming = server.accept();
					} catch (InterruptedIOException e) {
						continue;	// Timeout, try again
					} catch (NullPointerException e) {
						continue;	// Server could of gone null after test in while, means shutting down. This probably would only happen Linux 1.3.
					}
					Thread st = new ConnectionThread(incoming, this, "Connection Thread"); //$NON-NLS-1$
					threads.add(st);
					safeClean.interrupt();	// Let safeClean know there is a change
					st.start();
					// Null out locals so they can be GC'd since this is a long running loop.
					st = null;
					incoming = null;
				}
			}
		} catch (SocketException e) {
			if (trying || server != null)
				e.printStackTrace();	// Exception and not shutdown request, so print stack trace.
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		// We've had an exception, either something really bad, or we were closed,
		// so go through shutdowns.
		shutdown();		
	}
	
	
	/**
	 * Get an identityID, return -1 if not found.
	 */
	public int getIdentityID(Object anObject) {
		synchronized(objectToIDMap) {
			Integer id = (Integer) objectToIDMap.get(anObject);
			return id != null ? id.intValue() : ID_NOT_FOUND;
		}
	}
	
	/**
	 * Get an identityID and add it if not found. Place the id in the
	 * ValueObject passed in and return whether it was added (true) or was already in table (false)
	 */
	public boolean getIdentityID(Object anObject, Commands.ValueObject intoValue ) {
		boolean added = false;
		synchronized(objectToIDMap) {
			Integer id = (Integer) objectToIDMap.get(anObject);
			if (id == null) {
				do {
					if (++highestIdentityID == Commands.NOT_AN_ID)
						++highestIdentityID;	// Don't let -1 be a valid id.
					id = new Integer(highestIdentityID);
				} while (idToObjectMap.containsKey(id)); // Make sure not in use, really shouldn't ever happen because we have over 4 billion before it wraps back
				objectToIDMap.put(anObject, id);
				idToObjectMap.put(id, anObject);
				added = true;
			} 
			intoValue.setObjectID(id.intValue());
		}
		return added;
	}
	
	/**
	 * Remove an identity object from the mapping.
	 */
	public void removeObject(Object anObject) {
		synchronized(objectToIDMap) {
			Integer id = (Integer) objectToIDMap.remove(anObject);
			idToObjectMap.remove(id);
		}
	}
	
	/**
	 * Remove an identity object from the mapping, given the id.
	 */
	public void removeObject(int id) {
		synchronized(objectToIDMap) {
			Object o = idToObjectMap.remove(new Integer(id));
			objectToIDMap.remove(o);
		}
	}
	
	/**
	 * Get the object for an identity id
	 */
	public Object getObject(int id) {
		synchronized(objectToIDMap) {
			return idToObjectMap.get(new Integer(id));
		}
	} 
	
	/**
	 * Remove a thread from the list.
	 */
	public void removeConnectionThread(Thread thread) {
		threads.remove(thread);
		safeClean.interrupt();	// Let safe clean know there is a change.
	}
	
	/**
	 * Use this to request a shutdown. If the server hasn't even been
	 * created yet, this will return false.
	 */
	public boolean requestShutdown() {		
		if (server == null)
			return false;
		// Closing the server socket should cause a break.
		try {
			ServerSocket srv = server;
			server = null;	// So that server knows it is being shutdown and not print exception msg.
			srv.close();
		} catch (Exception e) {
		}
		return true;
	}
	
	/**
	 * Request a callback stream to write to.
	 * When done, the stream should be closed to release the connection.
	 */
	public OutputStream requestStream(int callbackID, int msgID)  throws CommandException {
		CallbackHandler h = (CallbackHandler) getFreeCallbackHandler();
		if (h == null)
			throw new CommandException("No callback handler retrieved.", null);	//$NON-NLS-1$
		h.initiateCallbackStream(callbackID, msgID);
		return new CallbackOutputStream(h, this);
	}
	
	protected void shutdown() {
		goingDown = true;
		safeClean.interrupt();	// Let safeClean know to come down.
		
		if (server != null)
			try {
				server.close();	// Close it so that no more requests can be made.
			} catch (Exception e) {
			}
		
		// Go through each thread and ask it to close. Make a copy of the list so that we
		// won't get into deadlocks.
		ConnectionThread[] threadsArray = (ConnectionThread[]) threads.toArray(new ConnectionThread[0]);
		for (int i=0; i<threadsArray.length; i++) {
			// This is a harsh way to shut a connection down, but there's no
			// other way I know of to interrupt the read on a socket.
			threadsArray[i].close();
		}
			
		// Now that they've been told to close, wait on each one to finish.
		for (int i=0; i<threadsArray.length; i++)
			try {
				threadsArray[i].join(10000);	// Wait ten seconds, if longer, just go on to next one.
				if (threadsArray[i].isAlive())
					System.out.println("*** Connection "+i+" did not die."); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (InterruptedException e) {
			}
	
		if (safeClean.isAlive()) {
			try {	
				safeClean.join(10000);
				if (safeClean.isAlive())
					System.out.println("*** Cleanup thread did not die."); //$NON-NLS-1$
			} catch (InterruptedException e) {
			}
		}
		
		// Now close the callbacks.
		synchronized(fCallbackHandlerPool) {
			// Now we walk through all of the free handlers and close them properly.
			Iterator itr = fCallbackHandlerPool.iterator();
			while (itr.hasNext()) {
				((CallbackHandler) itr.next()).closeHandler();
			}
			
			fCallbackHandlerPool.clear();
		}	
		
		List runnables = null;
		synchronized (this) {
			runnables = shutdownRunnables;
			shutdownRunnables = null;
		}
		if (runnables != null) {
			for (Iterator itr = runnables.iterator(); itr.hasNext();) {
				try {
					((Runnable) itr.next()).run();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Return a free callback handler
	 */
	public ICallbackHandler getFreeCallbackHandler() {
		synchronized(fCallbackHandlerPool) {
			if (!fCallbackHandlerPool.isEmpty())
				return (ICallbackHandler) fCallbackHandlerPool.pop();
			// else we need to allocate one.			
			return createCallbackHandler();
		}
	}
	
	/**
	 * Make a new callback handler
	 */
	protected ICallbackHandler createCallbackHandler() {
		Socket callbackSocket = requestCallbackSocket();
		if (callbackSocket != null) {
			CallbackHandler handler = new CallbackHandler(callbackSocket, this);
			if (handler.isConnected())
				return handler;
				
			// Failed, close the socket.
			try {
				callbackSocket.close();
			} catch (IOException e) {
			}
		}
		return null;
	}	
	
	/**
	 * Free the handler
	 */
	public void returnCallbackHandler(ICallbackHandler aHandler) {
		CallbackHandler handler = (CallbackHandler) aHandler;
		if (handler.isConnected())
			synchronized (fCallbackHandlerPool) {
				if (fCallbackHandlerPool.size() < NUMBER_FREE_CALLBACKS)
					fCallbackHandlerPool.push(handler);
				else
					handler.closeHandler();	// We don't need to maintain more than five free connections.
			}
	}
	
	/**
	 * Process a callback.
	 */
	public Object doCallback(ICallbackRunnable run) throws CommandException {
		CallbackHandler handler = (CallbackHandler) getFreeCallbackHandler();
		if (handler != null) {
			try {
				try {
					return run.run(handler);
				} catch (CommandErrorException e) {
					// This is command error, connection still good, don't retry, just pass it on.
					// It means the other side said I processed it, but there is an error.
					throw e;
				} catch (CommandException e) {
					if (!e.isRecoverable()) {
						// Close this handler and try a new one, one more time.
						handler.closeHandler();
						handler = (CallbackHandler) getFreeCallbackHandler();
						try {
							return run.run(handler);											
						} catch (CommandException eAgain) {
							// It failed again, just close the connection and rethrow the exception.
							handler.closeHandler();
							throw eAgain;
						}
					} else
						throw e;	// Recoverable, rethrow exception.
				}
			} finally {
				returnCallbackHandler(handler);
			}
		} else
			throw new CommandException("No callback handler retrieved.", null);	//$NON-NLS-1$
		
	}
	
	/*
	 * Register the server. Return if ide still active
	 */
	private boolean registerServer(int vmserverPort) {
		Socket socket = getSocket();
		if (socket != null) {
			try {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				try {
					out.writeByte(Commands.REMOTE_STARTED);
					out.writeInt(registryKey);
					out.writeInt(vmserverPort);
					out.flush();
					return in.readBoolean();
				} finally {
					try {
						in.close();
					} catch (IOException e) {
					}
					try {
						out.close();
					} catch (IOException e) {
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}					

		}
		
		return false;
	}
	
	/*
	 * Request the callback socket. <code>null</code> if there isn't one.
	 */
	private Socket requestCallbackSocket() {
		Socket socket = getSocket();
		if (socket != null) {
			boolean closeSocket = true;
			try {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				try {
					out.writeByte(Commands.ATTACH_CALLBACK);
					out.writeInt(registryKey);
					out.flush();
					closeSocket = !in.readBoolean();
					return !closeSocket ? socket : null;
				} finally {
					if (closeSocket) {
						try {
							in.close();
						} catch (IOException e) {
						}
						try {
							out.close();
						} catch (IOException e) {
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (closeSocket) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}					
		}
		
		return null;
	}	
	


	protected Socket getSocket() {
		// We are putting it off into a thread because there are no timeout capabilities on getting a socket.
		// So we need to allow for that.		
		final Socket[] scArray = new Socket[1];
		final boolean[] waiting = new boolean[] {true};		
		Thread doIt = new Thread(new Runnable() {
			public void run() {
				try {
					Socket sc = new Socket("localhost", masterIDESocketPort); //$NON-NLS-1$
					synchronized (this) {
						if (waiting[0])
							scArray[0] = sc;
						else
							sc.close();	// We are no longer waiting on this thread so close the socket since no one will use it.
					}					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		doIt.start();
		while (true) {
			try {
				doIt.join(60000);
				synchronized (doIt) {
					waiting[0] = false;	// To let it know we are no longer waiting
				}
				break;
			} catch (InterruptedException e) {
			}
		}
		
		if (scArray[0] == null) {
			System.out.println("Couldn't retrieve a socket from master server in 60 seconds.");	//$NON-NLS-1$
			return null;	// Couldn't get one, probably server is down.
		}
			
		return scArray[0];
	}
	
	private List shutdownRunnables;
	
	public synchronized void addShutdownListener(Runnable runnable) {
		if (shutdownRunnables == null) {
			shutdownRunnables = new ArrayList();
		} else if (shutdownRunnables.contains(runnable))
			return;
		shutdownRunnables.add(runnable);
	}
	
	public synchronized void removeShutdownListener(Runnable runnable) {
		if (shutdownRunnables != null)
			shutdownRunnables.remove(runnable);
	}


	public IVMServer getIVMServer() {
		return this;
	}
}
