/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeanProxyValueSender.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.common.CommandException;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.core.ProxyPlugin;
import org.eclipse.jem.internal.proxy.core.ThrowableProxy;
 
/**
 * Useful in REM to get the array of BeanProxyValues.
 * 
 * @since 1.1.0
 */
public class BeanProxyValueSender implements Commands.ValueSender {
	private int index = 0;
	private Object[] array;
	private Exception exception;
	private final REMStandardBeanProxyFactory factory;
	
	/**
	 * See if there was an exception thrown during reading.
	 * 
	 * @return an exception or <code>null</code> if no exception.
	 * 
	 * @since 1.1.0
	 */
	public Exception getException() {
		return exception;
	}
		
	public BeanProxyValueSender(REMStandardBeanProxyFactory factory) {
		this.factory = factory;
	}
	public BeanProxyValueSender(REMStandardBeanProxyFactory factory, Commands.ValueObject arrayHeader) {
		this(factory);
		initialize(arrayHeader);
	}
	
	public void initialize(Commands.ValueObject arrayHeader) {
		index = 0;
		// The array type doesn't matter, it will be an array of objects.
		// The values will either be IBeanProxies or an array, or constants.
		array = new Object[arrayHeader.anInt];
	}
	
	public void clear() {
		array = null;
		index = 0;
	}
	
	public Object getArray() {
		return array;
	}
				
	// A new value is being sent to the array
	// NOTE: It is important that this has been called within a transaction.
	public void sendValue(Commands.ValueObject value) {
		try {
			array[index++] = factory.getBeanProxy(value);	// Add it to the array
		} catch (ThrowableProxy e) {
			// We can't stop it right away because we can't send exception on, however,
			// we can log it and save the exception.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
			exception = e;			
		} catch (CommandException e) {
			// We can't stop it right away because we can't send exception on, however,
			// we can log it and save the exception.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
			exception = e;
		}
	}
				
	// The next entry is an array too!
	public Commands.ValueSender nestedArray(Commands.ValueObject arrayHeader) {
		BeanProxyValueSender sender = new BeanProxyValueSender(factory, arrayHeader);
		// Take the newly created array and put it into the current array.
		array[index++] = sender.getArray();
		return sender;
	}
}