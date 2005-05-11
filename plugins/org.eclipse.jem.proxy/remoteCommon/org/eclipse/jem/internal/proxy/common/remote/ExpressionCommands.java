/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ExpressionCommands.java,v $
 *  $Revision: 1.4 $  $Date: 2005/05/11 19:01:12 $ 
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
		EXPRESSION_NOEXPRESSIONVALUE_EXCEPTION = Commands.MAX_ERROR_CODE+1;	// No expression value occurred.
	
	// These are the flag values sent in proxy resolution when doesn't resolve to proxy.
	public static final int
		EXPRESSIONPROXY_VOIDTYPE = 0,	// Expression proxy resolves to void type.
		EXPRESSIONPROXY_NOTRESOLVED = 1;	// Expression proxy not resolved.

	public static final String EXPRESSIONTRACE = "proxyvm.expressionTrace";	// The system property for turning on expression tracing.
	public static final String EXPRESSIONTRACE_TIMER_THRESHOLD = "proxyvm.expressionTraceTimerThreshold";	// The system property for timer threshold.
	
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
	 * 		3: ERROR command with code of EXPRESSION_NOEXPRESSIONVALUE_EXCEPTION, with value of String with message from exception.
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
	 * @param expressionID 
	 * @param os
	 * 
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public static void sendStartExpressionProcessingCommand(int expressionID, DataOutputStream os) throws IOException {
		os.writeByte(Commands.EXPRESSION_TREE_COMMAND);
		os.writeInt(expressionID);
		os.writeByte(START_EXPRESSION_TREE_PROCESSING);
	}
	
	/**
	 * Send the end expression processing command.
	 * @param expressionID 
	 * @param os
	 * 
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public static void sendEndExpressionProcessingCommand(int expressionID, DataOutputStream os) throws IOException {
		os.writeByte(Commands.EXPRESSION_TREE_COMMAND);
		os.writeInt(expressionID);
		os.writeByte(END_EXPRESSION_TREE_PROCESSING);
		os.flush();	// Flushing because we are done and want to make sure everything goes out.
	}
	
	/**
	 * Send an expression subcommand.
	 * @param expressionID 
	 * @param os
	 * @param subcommand
	 * 
	 * @throws IOException
	 * 
	 * @since 1.0.0
	 */
	public static void sendExpressionCommand(int expressionID, DataOutputStream os, byte subcommand) throws IOException {
		os.writeByte(Commands.EXPRESSION_TREE_COMMAND);
		os.writeInt(expressionID);
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
		Commands.sendStringData(os, aString);
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
	 * Send the pull value command. After processing the proxies, caller should call getFinalValue() to get the value.
	 * @param expressionID 
	 * @param os
	 * @param is
	 * @param proxyids if not <code>null</code>, then this is the list of expression proxy ids that need to be returned as rendered.
	 * @param sender sender used to process the resolved proxy ids, or <code>null</code> if no proxy ids.
	 * @throws CommandException
	 * 
	 * @since 1.0.0
	 */
	public static void sendPullValueCommand(int expressionID, DataOutputStream os, DataInputStream is, Commands.ValueObject proxyids, Commands.ValueSender sender) throws CommandException {
		try {
			os.writeByte(Commands.EXPRESSION_TREE_COMMAND);
			os.writeInt(expressionID);
			os.writeByte(PULL_VALUE_REQUEST);
			sendProxyIDs(os, proxyids);
			os.flush();
			if (proxyids != null) {
				Commands.readBackValue(is, proxyids, Commands.ARRAY_IDS);	// Read the array header.
				sender.initialize(proxyids);
				Commands.readArray(is, proxyids.anInt, sender, proxyids, true);	// Read the array.
			}
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (IOException e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}
	
	/**
	 * Send a sync command. This does a synchronize with the remote expression processor. It makes sure that the
	 * stream is caught and doesn't return until everything on the stream has been processed. Should then call
	 * getFinalValue() to verify the expression is valid.
	 * @param expressionID 
	 * @param os
	 * @param is
	 * @param proxyids if not <code>null</code>, then this is the list of expression proxy ids that need to be returned as rendered.
	 * @param sender the sender used for reading back the proxyid resolutions, or <code>null</code> if no proxy ids.
	 * 
	 * @throws CommandException
	 * 
	 * @since 1.0.0
	 */
	public static void sendSyncCommand(int expressionID, DataOutputStream os, DataInputStream is, Commands.ValueObject proxyids, Commands.ValueSender sender) throws CommandException {
		try {
			os.writeByte(Commands.EXPRESSION_TREE_COMMAND);
			os.writeInt(expressionID);
			os.writeByte(SYNC_REQUEST);
			sendProxyIDs(os, proxyids);
			os.flush();
			if (proxyids != null) {
				Commands.readBackValue(is, proxyids, Commands.ARRAY_IDS);	// Read the array header.
				sender.initialize(proxyids);
				Commands.readArray(is, proxyids.anInt, sender, proxyids, true);	// Read the array.
			}
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}	
	
	/**
	 * Send the proxyids (if not null) as part of a command. Used by sync and pullValue.
	 * @param os
	 * @param proxyids <code>null</code> if not requesting proxy ids.
	 * @throws IOException
	 * @throws CommandException
	 * 
	 * @since 1.1.0
	 */
	private static void sendProxyIDs(DataOutputStream os, Commands.ValueObject proxyids) throws IOException, CommandException {
		if (proxyids != null) {
			os.writeBoolean(true);	// Indicates proxy ids being sent.
			Commands.writeValue(os, proxyids, false, false);
		} else
			os.writeBoolean(false);	// No proxyids being sent.
	}

	private ExpressionCommands() {
		// Never intended to be instantiated.
	}
	
}
