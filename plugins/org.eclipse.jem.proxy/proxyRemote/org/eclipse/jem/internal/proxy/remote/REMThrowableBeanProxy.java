/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMThrowableBeanProxy.java,v $
 *  $Revision: 1.3.2.1 $  $Date: 2004/06/24 18:19:03 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.*;
/**
 * Exception Proxy for exception on Remote.
 * NOTE: This one is unusual in that it doesn't inherit from
 *       IDEBeanProxy because it needs to be an exception, and
 *       exceptions are not an interface.
 */
public class REMThrowableBeanProxy extends ThrowableProxy implements IREMBeanProxy {

	private Integer fID;
	protected final REMProxyFactoryRegistry fFactory;
	private IBeanTypeProxy fTypeProxy;

	/**
	 * Set the bean we are proxying.  We are a proxy for a bean running on the remote VM. 
	 */
	protected REMThrowableBeanProxy(REMProxyFactoryRegistry aRegistry, Integer anID, IBeanTypeProxy aType){
		fFactory = aRegistry;
		fID = anID;
		fTypeProxy = aType;
	}
	
	/**
	 * equals: Equal if:
	 *         1) This proxy == (identity) to the other object
	 *         2) Else if other is an IBeanProxy and not a constant one, then if
	 *            equals on the server.
	 *         3) If this is a constant proxy and the other is too or is a constant
	 *            value (e.g. IStringBeanProxy.equals(String), then true if values are equals.
	 */
	public boolean equals(Object anObject) {
		if (super.equals(anObject))
			return true;	// Identity
		if (anObject instanceof IBeanProxy && !(anObject instanceof IREMConstantBeanProxy) && fFactory.isValid() && ((IBeanProxy) anObject).getProxyFactoryRegistry() == fFactory)
			try {
				// The other is a bean proxy and is not a constant one, let the server do the check.
				return ((IBooleanBeanProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getObjectEquals().invoke(this, (IBeanProxy) anObject)).booleanValue();
			} catch (ThrowableProxy e) {
			}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public boolean sameAs(IBeanProxy aBeanProxy) {
		return this == aBeanProxy;	// We can be assured in Remote Proxy that identity of proxy and identity of object are the same.
	}
	
	
	/**
	 * Get ID. An internal method to be used only within the ProxyFactory family.
	 */
	public Integer getID() {
		return fID;
	}
	
	/**
	 * isValid - has this bean been released.
	 */
	public boolean isValid() {
		return fID != null;
	}
	
	/**
	 * release - The bean is being released.
	 */
	public void release() {
		fID = null;
		fTypeProxy = null;
	} 
		
	/**
	 * Return the registry this proxy was created with.
	 */
	public ProxyFactoryRegistry getProxyFactoryRegistry() {
		return fFactory;
	}
	
	/**
	 * Return the proxied class of the bean
	 * Creation date: (12/3/99 11:37:01 AM)
	 * @author: Joe Winchester
	 */
	public IBeanTypeProxy getTypeProxy() {
		return fTypeProxy;
	}
	
	/**
	 * Return the toString of the actual bean on the remote side.
	 */
	public String toBeanString() {
		IStringBeanProxy string = (IStringBeanProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getObjectToString().invokeCatchThrowableExceptions(this);
		return (string != null) ? string.stringValue() : null;
	}
	
	public String getProxyLocalizedMessage() {
		IStringBeanProxy msg = (IStringBeanProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getThrowableLocalizedMessage().invokeCatchThrowableExceptions(this);
		return msg != null ? msg.stringValue() : null;
	}
	
	public String getProxyMessage() {
		IStringBeanProxy msg = (IStringBeanProxy)  REMStandardBeanProxyConstants.getConstants(fFactory).getThrowableMessage().invokeCatchThrowableExceptions(this);
		return msg != null ? msg.stringValue() : null;
	}
	
	/**
	 * Temporary way to get the string value of the stacktrace.
	 */
	private String getProxyStackTrace() {
		IMethodProxy tempGetStackTrace = fFactory.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.StackTraceUtility", "printStackTrace", new String[] {"java.lang.Throwable"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		IStringBeanProxy trace = (IStringBeanProxy) tempGetStackTrace.invokeCatchThrowableExceptions(null, this);
		return trace != null ? trace.stringValue() : ""; //$NON-NLS-1$
	}
		
	public void printProxyStackTrace(java.io.PrintWriter writer) {
		String trace = getProxyStackTrace();
		synchronized(writer) {
			writer.print(trace);
		}
	}
	
	public void printProxyStackTrace(java.io.PrintStream stream) {
		String trace = getProxyStackTrace();
		synchronized(stream) {
			stream.print(trace);
		}

	}
	
	public void printProxyStackTrace() {
		String trace = getProxyStackTrace();
		synchronized(System.err) {
			System.err.print(trace);
		}
	}


	/**
	 * Below are the actual printStackTrace. They are set up to first call
	 * the proxy trace so that it can show where it occurred on the server,
	 * and then it will do a super to show where it occurred on this side.
	 */
	public void printStackTrace() { 
		synchronized (System.err) {
			System.err.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.REMOTE_VM_TRACE_HEADER));
			printProxyStackTrace();
			System.err.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.REMOVE_VM_LOCAL_TRACE_HEADER));
			super.printStackTrace();
		}
	}

	public void printStackTrace(java.io.PrintWriter s) { 
		synchronized (s) {
			s.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.REMOTE_VM_TRACE_HEADER));
			printProxyStackTrace(s);
			s.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.REMOVE_VM_LOCAL_TRACE_HEADER));
			super.printStackTrace(s);
		}
	}

	public void printStackTrace(java.io.PrintStream s) { 
		synchronized (s) {
			s.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.REMOTE_VM_TRACE_HEADER));
			printProxyStackTrace(s);
			s.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.REMOVE_VM_LOCAL_TRACE_HEADER));
			super.printStackTrace(s);
		}
	}

	/**
	 * Render the bean into value object.
	 */
	public void renderBean(Commands.ValueObject value) {
		value.setObjectID(getID().intValue());
	}
	
}
