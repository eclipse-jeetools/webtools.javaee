package org.eclipse.jem.internal.proxy.common;
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
 *  $RCSfile: ICallbackHandler.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

/**
 * This is the interface for a callback handler.
 * Users will talk to this interface to perform 
 * callbacks.
 */
public interface ICallbackHandler {

	/**
	 * Callback to registered client.
	 *
	 * callbackID - The id of the callback. This will be given
	 *    to the callback when it is created, and it must pass
	 *    on to the handler. That way it is know which callback
	 *    to call on the client.
	 * msgID - The id of the message for the callback. This is an
	 *    agreed upon id of the developers of the callback on both
	 *    sides.
	 * parms - Null if no parms, or an array of objects to send to the client vm. They
	 *    will be turned into proxies on the client vm. So the callback
	 *    will recieve an array of proxies to the values in the parms.
	 *    If any of the entries in the array is itself an array, a
	 *    proxy to the array will be created and sent. The array itself
	 *    will not be sent.
	 *
	 *    If an array entry should go across as an array of proxies and
	 *    not as one proxy to an array, then the entry needs to be an
	 *    instance of ICallbackHandler.TransmitableArray. This will flag
	 *    that it should transmit the entire entry as proxies. This should
	 *    be used sparingly, only if there isn't much data in the array and
	 *    all of the array would be used on the client. That way transmitting
	 *    the entire array will be faster than accessing individual components.
	 * 
	 */
	public Object callbackWithParms(int callbackID, int msgID, Object[] parms) throws CommandException;

	public static class TransmitableArray {
		protected final Object[] fArray;
		public TransmitableArray(Object[] array) {
			fArray = array;
		}
		public Object[] getArray() {
			return fArray;
		}
	}
}