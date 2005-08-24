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
package org.eclipse.jem.internal.proxy.common;
/*
 *  $RCSfile: ICallbackHandler.java,v $
 *  $Revision: 1.8 $  $Date: 2005/08/24 20:39:07 $ 
 */

/**
 * This is the interface for a callback handler.
 * Users will talk to this interface to perform 
 * callbacks.
 */
public interface ICallbackHandler {

	/**
	 * Callback, but send the parm as an object, ie. it must
	 * be nothing but constants, e.g. String, Integer, or an
	 * array of constants. Constants should not be things like
	 * regular objects. This is because only standard java.lang
	 * type constants can be assured to be available on the other
	 * client. Also you don't want to send big objects. It must
	 * be constants that don't need to be sent back for any reason
	 * since their identity will be lost in the transfer.
	 * <p>
	 * This should be used if there are no parms (i.e. it is null).
	 * <p>
	 * To send big objects, use the callback stream.
	 * 
	 * @param callbackID
	 * @param msgID
	 * @param parm
	 * @return
	 * @throws CommandException
	 * 
	 * @see IVMCallbackServer#requestStream(int, int)
	 * @since 1.0.0
	 */
	public Object callbackAsConstants(int callbackID, int msgID, Object parm) throws CommandException;

	/**
	 * Callback to registered client.
	 * <p>
	 *  The parms can be <code>null</code> if no parms, or it is an array of parms.
	 * The parms
	 *    will be turned into proxies on the client vm. So the callback
	 *    will recieve an array of proxies to the values in the parms.
	 *    If any of the entries in the array is itself an array, a
	 *    proxy to the array will be created and sent. The array itself
	 *    will not be sent.
	 * <p>
	 *    If an array entry should go across as an array of proxies and
	 *    not as one proxy to an array, then the entry needs to be an
	 *    instance of {@link ICallbackHandler.TransmitableArray transmitableArray}. This will flag
	 *    that it should transmit the entire entry as proxies. This should
	 *    be used sparingly, only if there isn't much data in the array and
	 *    all of the array would be used on the client. That way transmitting
	 *    the entire array will be faster than accessing individual components.
	 * <p>
	 * If the array contains only one entry or the parm itself is null, then {@link org.eclipse.jem.internal.proxy.core.ICallback#calledBack(int, IBeanProxy)}
	 * will be called. Else {@link org.eclipse.jem.internal.proxy.core.ICallback#calledBack(int, Object[])} will be called with all of the parms
	 * turned into proxies as mentioned in the previous paragraph.
	 *
	 * @param callbackID The id of the callback. This will be given
	 *    to the callback when it is created, and it must pass
	 *    on to the handler. That way it is know which callback
	 *    to call on the client.
	 * @param msgID The id of the message for the callback. This is an
	 *    agreed upon id of the developers of the callback on both
	 *    sides.
	 * @param parms Null if no parms, or an array of objects to send to the client vm. See the comments 
	 * above for a complete description of the parameter.
	 * 
	 * @see ICallbackHandler.TransmitableArray
	 */
	public Object callbackWithParms(int callbackID, int msgID, Object[] parms) throws CommandException;

	/**
	 * Array wrapper that turns an array for parameters into a transmittable array.
	 * 
	 * @see ICallbackHandler#callbackWithParms(int, int, Object[])
	 * @since 1.1.0
	 */
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
