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
 *  $RCSfile: REMMasterServerThread.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.io.*;
import java.net.*;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jem.internal.proxy.core.ProxyPlugin;
import org.eclipse.jem.internal.proxy.common.remote.Commands;

/**
 * Master Server thread for the Remote VMs.
 * 
 * It responds to the remote vm requests to this IDE.
 * 
 * It is package-protected because only ProxyRemotePluguin should access it.
 * @author richkulp
 */
class REMMasterServerThread extends Thread {
	
	protected ServerSocket masterServerSocket;
	protected REMRegistryController registryController;
	protected boolean shuttingDown = false;
	
	// Kludge: Bug in Linux 1.3.xxx of JVM. Closing a socket while the socket is being read/accept will not interrupt the
	// wait. Need to timeout to the socket read/accept before the socket close will be noticed. This has been fixed
	// in Linux 1.4. So on Linux 1.3 need to put timeouts in on those sockets that can be separately closed while reading/accepting.
	static boolean LINUX_1_3 = "linux".equalsIgnoreCase(System.getProperty("os.name")) && System.getProperty("java.version","").startsWith("1.3");	 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	
	
	public REMMasterServerThread(REMRegistryController registryController) {
		super("Remote Proxy Master Server Thread");	//$NON-NLS-1$
		this.registryController = registryController;
		
		try {
			masterServerSocket = new ServerSocket(0, 50, InetAddress.getByName("localhost"));	// Any available port //$NON-NLS-1$
			if (LINUX_1_3)
				masterServerSocket.setSoTimeout(1000);	// Linux 1.3 bug, see comment on LINUX_1_3
		} catch (SocketException e) {
			ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e));	// This is bad. Should be no exceptions creating a socket.
		} catch (IOException e) {				
			ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e));	// This is bad. Should be no exceptions creating a socket.
		}			
	}

	public ServerSocket getMasterSocket() {
		return masterServerSocket;
	}
	
	/**
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			while (masterServerSocket != null) {
				Socket incoming = null;
				try {
					incoming = masterServerSocket.accept();
				} catch (InterruptedIOException e) {
					continue; // Timeout, try again
				} catch (NullPointerException e) {
					continue;	// In Linux 1.3 case masterServerSocket could of gone null between loop test and try block.
				}
				try {
					processRequest(incoming);
				} finally {
					try {
						incoming.close();
					} catch (IOException e) {
					}
				}
				incoming = null;
			}
		} catch (Exception e) {
			if (!shuttingDown)
				ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e));
		}
		
		shutdown();	// Either a bad exception or we were closed. Either way is shutdown.
	}
	
	/**
	 * Use this to request a shutdown.
	 */
	public void requestShutdown() {		
		if (masterServerSocket == null)
			return;
		// Closing the server socket should cause a break.
		try {
			shuttingDown = true;
			masterServerSocket.close();
		} catch (Exception e) {
		}
		return;
	}	
	
	protected void processRequest(Socket remote) throws IOException {
		DataInputStream	in = new DataInputStream(remote.getInputStream());
		DataOutputStream out = new DataOutputStream(remote.getOutputStream());
		
		try {
			byte cmd = in.readByte();
			switch (cmd) {
				case Commands.ALIVE:
					int registryID = in.readInt();
					out.writeBoolean(registryController.getRegistry(new Integer(registryID)) != null);	// Return if registry still alive
					out.flush();
					break;
					
				case Commands.REMOTE_STARTED:
					registryID = in.readInt();
					int remoteServerPort = in.readInt();
					REMProxyFactoryRegistry registry = registryController.getRegistry(new Integer(registryID));
					if (registry != null) {
						registry.setServerPort(remoteServerPort);
						out.writeBoolean(true);
					} else
						out.writeBoolean(false);	// No registry
					out.flush();
					break;
					
				case Commands.GET_CALLBACK_PORT:
					registryID = in.readInt();
					registry = registryController.getRegistry(new Integer(registryID));
					if (registry != null)
						out.writeInt(registry.getCallbackServerPort());
					else
						out.writeInt(-1);
					out.flush();
					break;
			}
		} catch (Exception e) {
			ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e));	
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
	}
	
	private void shutdown() {
		requestShutdown();
		masterServerSocket = null;		
	}		
	
}
