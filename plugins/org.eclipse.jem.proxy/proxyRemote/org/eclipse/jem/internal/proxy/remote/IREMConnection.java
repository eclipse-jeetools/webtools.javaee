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
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: IREMConnection.java,v $
 *  $Revision: 1.7 $  $Date: 2005/08/24 20:39:07 $ 
 */


import org.eclipse.jem.internal.proxy.common.CommandException;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Interface for the Remote VM Connection object.
 */

public interface IREMConnection {
	
	/**
	 * Did this construct correctly.
	 * This is needed because an exception could be thrown in the ctor and
	 * that's not a good thing to do.
	 */
	public boolean isConnected();
	
	/**
	 * Terminate the server.
	 * WARNING: This is only here so that factory can to it. Termination should ONLY
	 * be done by the Factory not any one else. 
	 */
	public void terminateServer();
			
	/**
	 * Close the connection.
	 */
	public void close();
	
	/**
	 * Get the class information. Null return if not found.
	 */
	public Commands.GetClassReturn getClass(String className) throws CommandException;

	/**
	 * Get the class information from the id. 
	 */
	 public Commands.GetClassIDReturn getClassFromID(int classID) throws CommandException;

	

	/**
	 * Get object data from an id.
	 */	
	public void getObjectData(int objectID, Commands.ValueObject valueReturn) throws CommandException;
		
	/**
	 * Get a new instance using the initialization string.
	 */
	public void getNewInstance(int classID, String initString, Commands.ValueObject newInstance) throws CommandException;
		
	/**
	 * Invoke the get array contents command.
	 * @param arrayID
	 * @param returnValue
	 * @throws CommandException
	 * 
	 * @since 1.1.0
	 */
	public void getArrayContents(int arrayID, Commands.ValueObject returnValue) throws CommandException;
	
	/**
	 * Invoke the method call.
	 * The parms valueObject must represent an Object[] (either through ARRAY_IDS or OBJECT)
	 */	
	public void invokeMethod(int methodID, Commands.ValueObject invokeOn, Commands.ValueObject parms, Commands.ValueObject returnValue) throws CommandException;

	/**
	 * Invoke the method call with method passed.
	 * The parms valueObject must represent an Object[] (either through ARRAY_IDS or OBJECT)
	 */	
	public void invokeMethod(Commands.ValueObject classType, String methodName, Commands.ValueObject parmTypes, Commands.ValueObject invokeOn, Commands.ValueObject parms, Commands.ValueObject returnValue) throws CommandException;

	/**
	 * Release the id. It is no longer needed on the client.
	 */
	public void releaseID(int id);
	
	/**
	 * Read array values as BeanProxies into valueSender.
	 * @param returnValue
	 * @param valueSender
	 * @param allowFlag <code>true</code> to allow FLAG values, <code>false</code> if not allow flag values.
	 * 
	 * @throws CommandException
	 * 
	 * @since 1.1.0
	 */
	public void readProxyArrayValues(Commands.ValueObject returnValue, Commands.ValueSender valueSender, boolean allowFlag) throws CommandException;
	
}
