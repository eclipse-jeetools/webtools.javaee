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
package org.eclipse.jem.internal.proxy.ide;
/*
 *  $RCSfile: IDEThrowableProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/05/11 19:01:12 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

public class IDEThrowableProxy extends ThrowableProxy implements IIDEBeanProxy {

	protected IBeanTypeProxy fBeanTypeProxy;

	protected IDEThrowableProxy(Throwable exc, IBeanTypeProxy aBeanTypeProxy) {
		super(exc);
		fBeanTypeProxy = aBeanTypeProxy;
	}

	public boolean equals(Object obj) {
		if (super.equals(obj))
			return true;
		if (obj instanceof IIDEBeanProxy) {
			return getCause().equals(((IIDEBeanProxy) obj).getBean());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public boolean sameAs(IBeanProxy aBeanProxy) {
		if (this == aBeanProxy)
			return true;
		if (aBeanProxy instanceof IIDEBeanProxy)
			return getCause() == ((IIDEBeanProxy) aBeanProxy).getBean();
		return false;
	}

	public String getProxyLocalizedMessage() {
		return getCause().getLocalizedMessage();
	}
	public String getProxyMessage() {
		return getCause().getMessage();
	}
	public void printProxyStackTrace(java.io.PrintWriter writer) {
		getCause().printStackTrace(writer);
	}
	public void printProxyStackTrace(java.io.PrintStream stream) {
		getCause().printStackTrace(stream);
	}
	public void printProxyStackTrace() {
		getCause().printStackTrace();
	}
	public IBeanTypeProxy getTypeProxy() {
		return fBeanTypeProxy;
	}
	public ProxyFactoryRegistry getProxyFactoryRegistry() {
		return fBeanTypeProxy.getProxyFactoryRegistry();
	}
	public String toBeanString() {
		return getCause().toString();
	}
	public boolean isValid() {
		return true;
	}
	/**
	 * Return the exception which is the live bean
	 */
	public Object getBean() {
		return getCause();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxy#isBeanProxy()
	 */
	public final boolean isBeanProxy() {
		return true;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxy#isExpressionProxy()
	 */
	public final boolean isExpressionProxy() {
		return false;
	}
}
