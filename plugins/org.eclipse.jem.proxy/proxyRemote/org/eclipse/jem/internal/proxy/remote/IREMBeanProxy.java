package org.eclipse.jem.internal.proxy.remote;
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
 *  $RCSfile: IREMBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * A Remote VM proxy. All IBeanProxy implementations
 * for the Remote VM must also implement this interface.
 * This is necessary for proper functioning.
 *
 * NOTE: This is an internal interface to the Remote VM Proxy.
 * It MUST not be used outside of the proxy factory family.
 */

public interface IREMBeanProxy extends IBeanProxy {
	/**
	 * Return the ID of this proxy
	 */
	public Integer getID();
	
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
	public void release();
	
	/**
	 * Render this bean for transport to the server into a Commands.ValueObject.
	 * For example, a string may put a string. An id type may put out the id.
	 */
	public void renderBean(Commands.ValueObject renderInto);
	
}