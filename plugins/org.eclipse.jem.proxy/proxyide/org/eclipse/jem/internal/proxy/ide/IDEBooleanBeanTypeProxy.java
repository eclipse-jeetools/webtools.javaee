/*
 * Created on Sep 15, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jem.internal.proxy.ide;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;

/**
 * @author richkulp
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class IDEBooleanBeanTypeProxy extends IDEBooleanBeanProxy {

	/**
	 * @param aRegistry
	 * @param aBean
	 * @param aBeanTypeProxy
	 */
	public IDEBooleanBeanTypeProxy(ProxyFactoryRegistry aRegistry, Object aBean, IBeanTypeProxy aBeanTypeProxy) {
		super(aRegistry, aBean, aBeanTypeProxy);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public boolean sameAs(IBeanProxy aBeanProxy) {
		return (aBeanProxy instanceof IDEBooleanBeanTypeProxy) ? getBooleanValue() == ((IDEBooleanBeanTypeProxy) aBeanProxy).getBooleanValue() : false;
	}

}
