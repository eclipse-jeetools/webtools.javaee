/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IREMExpressionConnection.java,v $
 *  $Revision: 1.1 $  $Date: 2004/02/03 23:18:36 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import java.io.IOException;

import org.eclipse.jem.internal.proxy.common.CommandException;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
 
/**
 * Additional function on a connection for expression tree processing.
 * Connections returned (IREMConnections) will always also implement IREMExpressionConnection,
 * but they are separated so that expression functions don't pollute the regular
 * connection interface with added commands that shouldn't be called except
 * when processing an expression. If they were called out of order, big problems
 * can occur.
 * <p>
 * To use, simply cast the IREMConnection to be an IREMExpressionConnection.
 * 
 * @since 1.0.0
 */
public interface IREMExpressionConnection extends IREMConnection {
	
	/**
	 * Start expression processing.
	 * 
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public void startExpressionProcessing() throws IOException;
	
	/**
	 * Push an expression command. This is the common portion of the
	 * subcommand. The actual data of the command will be separately done.
	 * 
	 * @param subcommand The subcommand being sent. From IInternalExpressionConstants.
	 * @throws IOException
	 * 
	 * @see org.eclipse.jem.internal.proxy.initParser.tree.IInternalExpressionConstants#PUSH_TO_PROXY_EXPRESSION
	 * @since 1.0.0
	 */
	public void pushExpressionCommand(byte subcommand) throws IOException;
	
	/**
	 * Push the value object to the remote side.
	 * 
	 * @param valueObject
	 * @throws CommandException
	 * 
	 * @since 1.0.0
	 */
	public void pushValueObject(Commands.ValueObject valueObject) throws CommandException;

	/**
	 * Push just the single byte to the remote side.
	 * @param abyte
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public void pushByte(byte abyte) throws IOException;
	
	/**
	 * Push just the single int to the remote side.
	 * 
	 * @param anInt
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public void pushInt(int anInt) throws IOException;
	
	
	/**
	 * Push just the singe string to the remote side.
	 * 
	 * @param aString
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public void pushString(String aString) throws IOException;
	
	/**
	 * Push just the bool to the remote side.
	 * @param aBool
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public void pushBoolean(boolean aBool) throws IOException;
	
	/**
	 * Pull the return value and put into the parameter value object. If an error
	 * occurs, command exception is thrown. The value codes are either <code>ExpressionNoExpressionValueException</code> or
	 * <code>ThrowableSent</code>
	 * 
	 * @param returnValue
	 * @throws CommandException
	 * 
	 * @since 1.0.0
	 */
	public void pullValue(Commands.ValueObject returnValue) throws CommandException;
	
	/**
	 * Send the sync command and put the return value into the parameter value object. If an error
	 * occurs, command exception is thrown. The value codes are either <code>ExpressionNoExpressionValueException</code> or
	 * <code>ThrowableSent</code>
	 * 
	 * @param returnValue
	 * @throws CommandException
	 * 
	 * @since 1.0.0
	 */
	public void sync(Commands.ValueObject returnValue) throws CommandException;	
	
	/**
	 * Stop expression processing.
	 * 
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public void stopExpressionProcessing() throws IOException;
}
