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
 *  $RCSfile: ExpressionCommands.java,v $
 *  $Revision: 1.1 $  $Date: 2004/02/03 23:18:36 $ 
 */
package org.eclipse.jem.internal.proxy.common.remote;

import java.io.*;
import java.io.DataOutputStream;
import java.io.IOException;

import org.eclipse.jem.internal.proxy.common.CommandException;

 
/**
 * 
 * @since 1.0.0
 */
public class ExpressionCommands {

	// These are the sub-commands under EXPRESSION_TREE_COMMANDS that can be send.
	public static final byte
		START_EXPRESSION_TREE_PROCESSING = 0,
		PUSH_EXPRESSION = 1,
		END_EXPRESSION_TREE_PROCESSING = 2,
		SYNC_REQUEST = 3,
		PULL_VALUE_REQUEST = 4;
	
	// These are the expression specific error codes (it can also send back general ones. See SYNC_REQUEST docs lower down).
	public static final int
		ExpressionNoExpressionValueException = Commands.MAX_ERROR_CODE+1;	// No expression value occurred.
	
	/*
	 * The format of the commands are:
	 * 		Note: Most of the commands will not being doing a os.flush() at the end. We are just going to
	 * 		be streaming the data over the line. At the end we will flush and then catch up. That way
	 * 		we aren't waiting for the other side as we send the data.
	 * 
	 * START_EXPRESSION_TREE_PROCESSING: 0b
	 * 	Start processing.
	 * 
	 * PUSH_EXPRESSION: 1b, b
	 * 	Push an expression. Where "b" is the expression type from IInternalExpressionConstants. 
	 * 	The actual data that follows is expression type dependent and will be
	 * 	sent in a following call to ExpressionCommands as it builds up the actual command.
	 * 	See REMExpression and each type of push call method within it to see the actual
	 * 	sent data.
	 * 
	 * END_EXPRESSION_TREE_PROCESSING: 2b
	 * 	End the processing and clean up.
	 * 
	 * SYNC_REQUEST: 3b
	 * 	This will return the current status. The reason it is called sync is because the
	 * 	IDE will wait for it to complete and read back the value. It will send back:
	 * 		1: VALUE command with boolean true as the value.
	 * 		2: ERROR command with code of ExpressionClassNotFound, with value of String with message from exception.
	 * 		3: ERROR command with code of ExpressionNoExpressionValueException, with value of String with message from exception.
	 * 		4: THROWABLE command with the actual exception that occurred.
	 * 
	 *
	 * PULL_VALUE_REQUEST: 4b
	 * 	This will do a sync up and return the value from the expression.
	 * 	IDE will wait for it to compleate and read back the value. It will send back:
	 * 		1: VALUE command with the result as the value.
	 * 		2: ERROR or EXCEPTION if there were errors, see SYNC_REQUEST with the format they are sent back.
	 * 
	 * @see org.eclipse.jem.internal.proxy.initParser.tree.IInternalExpressionConstants
	 * @see org.eclipse.jem.internal.proxy.remote.REMExpression
	 * 
	 */
	
	/**
	 * Send the start expression processing command.
	 * 
	 * @param os
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public static void sendStartExpressionProcessingCommand(DataOutputStream os) throws IOException {
		os.writeByte(Commands.EXPRESSION_TREE_COMMAND);
		os.writeByte(START_EXPRESSION_TREE_PROCESSING);
	}
	
	/**
	 * Send the end expression processing command.
	 * 
	 * @param os
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public static void sendEndExpressionProcessingCommand(DataOutputStream os) throws IOException {
		os.writeByte(Commands.EXPRESSION_TREE_COMMAND);
		os.writeByte(END_EXPRESSION_TREE_PROCESSING);
		os.flush();	// Flushing because we are done and want to make sure everything goes out.
	}
	
	/**
	 * Send an expression subcommand.
	 * 
	 * @param os
	 * @param subcommand
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public static void sendExpressionCommand(DataOutputStream os, byte subcommand) throws IOException {
		os.writeByte(Commands.EXPRESSION_TREE_COMMAND);
		os.writeByte(PUSH_EXPRESSION);
		os.writeByte(subcommand);
	}
	
	/**
	 * Send just a byte.
	 * 
	 * @param os
	 * @param aByte
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public static void sendByte(DataOutputStream os, byte aByte) throws IOException {
		os.writeByte(aByte);
	}
	
	/**
	 * Send just an int.
	 * 
	 * @param os
	 * @param anInt
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public static void sendInt(DataOutputStream os, int anInt) throws IOException {
		os.writeInt(anInt);
	}
	
	/**
	 * Send just a string.
	 * 
	 * @param os
	 * @param aString
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public static void sendString(DataOutputStream os, String aString) throws IOException {
		os.writeUTF(aString);
	}
	
	/**
	 * Send just a boolean.
	 * 
	 * @param os
	 * @param aBool
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public static void sendBoolean(DataOutputStream os, boolean aBool) throws IOException {
		os.writeBoolean(aBool);
	}
	
	/**
	 * Send the pull value command. Return either the value or an error (NoValueExpressionException or a Throwable)>
	 * @param os
	 * @param is
	 * @param valueReturn
	 * @throws CommandException
	 * 
	 * @since 1.0.0
	 */
	public static void sendPullValueCommand(DataOutputStream os, DataInputStream is, Commands.ValueObject valueReturn) throws CommandException {
		try {
			os.writeByte(Commands.EXPRESSION_TREE_COMMAND);
			os.writeByte(PULL_VALUE_REQUEST);
			os.flush();
			Commands.readBackValue(is, valueReturn, Commands.NO_TYPE_CHECK);
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}
	
	/**
	 * Send a sync command. This does a synchronize with the remote expression processor. It makes sure that the
	 * stream is caught and doesn't return until everything on the stream has been processed. It will then return a
	 * value. The value should <code>true</code> if everything is OK, it could be an error return,
	 * 
	 * @param os
	 * @param is
	 * @param returnValue
	 * @throws CommandException
	 * 
	 * @since 1.0.0
	 */
	public static void sendSyncCommand(DataOutputStream os, DataInputStream is, Commands.ValueObject returnValue) throws CommandException {
		try {
			os.writeByte(Commands.EXPRESSION_TREE_COMMAND);
			os.writeByte(SYNC_REQUEST);
			os.flush();
			Commands.readBackValue(is, returnValue, Commands.NO_TYPE_CHECK);
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}	
	
	private ExpressionCommands() {
		// Never intended to be instantiated.
	}
	
}
