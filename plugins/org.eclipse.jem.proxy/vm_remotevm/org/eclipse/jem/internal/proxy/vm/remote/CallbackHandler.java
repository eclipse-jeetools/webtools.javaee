package org.eclipse.jem.internal.proxy.vm.remote;
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
 *  $RCSfile: CallbackHandler.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.net.Socket;
import java.io.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.common.remote.UnexpectedExceptionCommandException;
import org.eclipse.jem.internal.proxy.common.*;
/**
 * This is a handler for doing callbacks to the
 * client.
 *
 * This is package protected because no one should
 * use it from the outside. It is too critical that
 * callback's work only in this specified way.
 */
class CallbackHandler extends ConnectionHandler implements ICallbackHandler {
	public CallbackHandler(Socket sc, RemoteVMServerThread svr) {
		super(sc, svr, null);
	}		
	
	/**
	 * Initiate a stream callback request.
	 */
	public void initiateCallbackStream(int callbackID, int msgID) throws CommandException {
		try {
			Commands.sendCallbackStreamCommand(out, callbackID, msgID);
			out.flush();
		} catch (CommandException e) {
			throw e;
		} catch (Exception e) {
			throw new UnexpectedExceptionCommandException(false, e);
		}
		Object result = run();	// Now run and wait for return. If the result is null, then it signals to continue.
		if (result != null)
			throw new CommandException("Request refused.", result); //$NON-NLS-1$
	}
	
	/**
	 * Write bytes to the client. If bytesToWrite is -1, then this is end and
	 * no bytes are being written. Just write the -1 then.
	 */
	public void writeBytes(byte[] bytes, int bytesToWrite) throws CommandException {
		try {
			if (bytesToWrite != -1)
				Commands.writeBytes(out, bytes, bytesToWrite);
			else
				out.writeInt(-1);
		} catch (CommandException e) {
			throw e;
		} catch (Exception e) {
			throw new UnexpectedExceptionCommandException(false, e);
		}
	}

		
	/**
	 * Callback, but send the parm as an object, ie. it must
	 * be nothing but constants, e.g. String, Integer, or an
	 * array of constants. Constants should not be things like
	 * regular objects. This is because only standard java.lang
	 * type constants can be assured to be available on the other
	 * client. Also you don't want to send big objects, except
	 * maybe something like an array of bytes or strings. It must
	 * be constants that don't need to be sent back for any reason
	 * since their identity will be lost in the transfer.
	 *
	 * This should be used if there are no parms (i.e. it is null).
	 */
	public Object callbackAsConstants(int callbackID, int msgID, Object parm) throws CommandException {

		try {
			Commands.sendCallbackCommand(out, callbackID, msgID);
			Commands.ValueObject v = new Commands.ValueObject();
			sendObject(parm, SEND_AS_IS, v, out, false);
			out.flush();
		} catch (CommandException e) {
			throw e;
		} catch (Exception e) {
			throw new UnexpectedExceptionCommandException(false, e);
		}
		return run();	// Now run and wait for return.
	}
	
	
	// A retriever is what handles the array part.
	private class Retriever implements Commands.ValueRetrieve {
		int index=0;
		Object[] array;
		Commands.ValueObject worker = new Commands.ValueObject();
		
		
		public Retriever(Object[] anArray) {
			array = anArray;
		}
		
		public Commands.ValueObject nextValue() {
			if (index < array.length) {
				Object parm = array[index++];
				if (parm != null) {
					if (parm instanceof ICallbackHandler.TransmitableArray) {
						// It is another array, create a new retriever.
						worker.setArrayIDS(new Retriever(((ICallbackHandler.TransmitableArray) parm).getArray()), ((ICallbackHandler.TransmitableArray) parm).getArray().length, Commands.OBJECT_CLASS);
					} else {
						// They may add some new ID's and if there is an error, they
						// won't get released, but that is a slight chance we will have
						// to take because we don't know which ones were new and which
						// ones weren't.
						fillInValue(parm, NOT_A_PRIMITIVE, worker);
					}
				} else
					worker.set();
				return worker;
			} else
				return null;
		}
	};
		
	/**
	 * Callback, but send the parm as IDs so that proxies
	 * will be created for the objects and can be referenced.
	 *
	 * This should be used even if there is only one parm.
	 */
	public Object callbackWithParms(int callbackID, int msgID, Object[] parms) throws CommandException {
		try {
			if (parms == null)
				return callbackAsConstants(callbackID, msgID, null);
				
			Commands.ValueObject v = new Commands.ValueObject();
							
			v.setArrayIDS(new Retriever(parms), parms.length, Commands.OBJECT_CLASS);
			Commands.sendCallbackCommand(out, callbackID, msgID);
			Commands.writeValue(out, v, false);
			out.flush();
		} catch (CommandException e) {
			throw e;
		} catch (Exception e) {
			throw new UnexpectedExceptionCommandException(false, e);
		}
		return run();	// Now run and wait for return.
	}	
	
	/**
	 * A closeHandler has been requested. This is called when
	 * not waiting within a loop, so we need to do the cleanup ourselves.
	 */
	public void closeHandler() {
		if (isConnected()) {
			try {
				Commands.sendQuitCommand(out);
			} catch (IOException e) {
			} finally {
				if (in != null)
					try {
						in.close();
					} catch (Exception e) {
					}
				if (out != null)
					try {
						out.close();
					} catch (Exception e) {
					}
				close();
				in = null;
				out = null;
				socket = null;
			}
		}
	}
			
}