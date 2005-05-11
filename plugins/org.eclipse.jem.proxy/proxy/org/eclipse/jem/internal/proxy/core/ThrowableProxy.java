package org.eclipse.jem.internal.proxy.core;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ThrowableProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/05/11 19:01:12 $ 
 */


/**
 * This is a proxy wrapper for an exception being thrown on the
 * remove VM. The standard exception methods refer to this proxy.
 * To refer to the proxied exception itself, the methods defined
 * in here would be used.
 */
public abstract class ThrowableProxy extends java.lang.Exception implements IBeanProxy {
   /**
    * Constructs an <code>Exception</code> with no specified detail message. 
    */
   public ThrowableProxy() {
		super();
   }

	/**
	 * @param message
	 * @param cause
	 * 
	 * @since 1.1.0
	 */
	protected ThrowableProxy(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * @param cause
	 * 
	 * @since 1.1.0
	 */
	protected ThrowableProxy(Throwable cause) {
		super(cause);
	}
   /**
    * Constructs an <code>Exception</code> with the specified detail message. 
    *
    * @param   s   the detail message.
    */
   public ThrowableProxy(String s) {
		super(s);
   }
	
	
	public abstract String getProxyLocalizedMessage();
	public abstract String getProxyMessage();
	public abstract void printProxyStackTrace(java.io.PrintWriter writer);
	public abstract void printProxyStackTrace(java.io.PrintStream stream);
	public abstract void printProxyStackTrace();

	public String toString() {
		return super.toString() + "Proxy: " + toBeanString();	//$NON-NLS1$ - toString's aren't nls'ed. //$NON-NLS-1$
	}
}