/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
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
 *  $RCSfile: REMAbstractBeanProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.*;
/**
 * An abstract base class of IREMBeanProxy that doesn't
 * have the bean type stored in it. The subclasses will
 * supply it. It does however have an ID because it does
 * exist over on the remote VM.
 */
public abstract class REMAbstractBeanProxy implements IREMBeanProxy {
	protected final REMProxyFactoryRegistry fFactory;
	private Integer fID;
	
	/**
	 * Set the bean we are proxying.  We are a proxy for a bean running on the remote VM. 
	 */
	protected REMAbstractBeanProxy(REMProxyFactoryRegistry aRegistry, Integer anID){
		fFactory = aRegistry;
		fID = anID;
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
		
	public ProxyFactoryRegistry getProxyFactoryRegistry() {
		return fFactory;
	}
	
	/**
	 * Get ID. An internal method to be used only within the ProxyFactory family.
	 */
	public Integer getID() {
		return fID;
	}
	
	/**
	 * isValid.
	 */
	public boolean isValid() {
		return fID != null;
	}
	
	/**
	 * Proxy is about to be released, MUST mark it invalid.
	 * This is required. Resources can also be cleaned up
	 * if they are being held and need to be cleaned up.
	 *
	 * NOTE: This method will only be called when the proxy
	 * is explicitly released. If it is simply garbage collected,
	 * then this method will not be called. Simple garbage collection
	 * means that no one is referencing this proxy. The proxy factory
	 * will know what id this proxy was referencing and will release it
	 * on the server at GC time.
	 *
	 * If there are resources
	 * that absolutely must be released, then the finalize method
	 * should be implemented to do the clean up. However, try not
	 * to have this be the case. Finalize methods add overhead that is
	 * usually better not to have. The better way is for any users
	 * that get this kind of proxy object know to call release on
	 * ProxyFactoryRegistry to release it before garbage collection.	 
	 */
	public void release() {
		fID = null;
	}
			
	/**
	 * Return the toString of the actual bean on the remote side.
	 */
	public String toBeanString() {
		IStringBeanProxy string = (IStringBeanProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getObjectToString().invokeCatchThrowableExceptions(this);
		return (string != null) ? string.stringValue() : null;
	}
	
	/**
	 * Render the bean proxy into the value field.
	 */
	public void renderBean(Commands.ValueObject value) {
		value.setObjectID(isValid() ? getID().intValue() : Commands.VOID);	// No longer exists, so send over null.
	}

}
