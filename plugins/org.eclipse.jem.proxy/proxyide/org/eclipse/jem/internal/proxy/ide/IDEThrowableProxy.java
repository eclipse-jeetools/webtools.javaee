package org.eclipse.jem.internal.proxy.ide;
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
 *  $RCSfile: IDEThrowableProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:36 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

public class IDEThrowableProxy extends ThrowableProxy implements IIDEBeanProxy {

	protected Throwable fExc;
	protected IBeanTypeProxy fBeanTypeProxy;

	protected IDEThrowableProxy(Throwable exc, IBeanTypeProxy aBeanTypeProxy) {
		fExc = exc;
		fBeanTypeProxy = aBeanTypeProxy;
	}

	public boolean equals(Object obj) {
		if (super.equals(obj))
			return true;
		if (obj instanceof IIDEBeanProxy) {
			return fExc.equals(((IIDEBeanProxy) obj).getBean());
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
			return fExc == ((IIDEBeanProxy) aBeanProxy).getBean();
		return false;
	}

	public String getProxyLocalizedMessage() {
		return fExc.getLocalizedMessage();
	}
	public String getProxyMessage() {
		return fExc.getMessage();
	}
	public void printProxyStackTrace(java.io.PrintWriter writer) {
		fExc.printStackTrace(writer);
	}
	public void printProxyStackTrace(java.io.PrintStream stream) {
		fExc.printStackTrace(stream);
	}
	public void printProxyStackTrace() {
		fExc.printStackTrace();
	}
	public IBeanTypeProxy getTypeProxy() {
		return fBeanTypeProxy;
	}
	public ProxyFactoryRegistry getProxyFactoryRegistry() {
		return fBeanTypeProxy.getProxyFactoryRegistry();
	}
	public String toBeanString() {
		return fExc.toString();
	}
	public boolean isValid() {
		return true;
	}
	/**
	 * Return the exception which is the live bean
	 */
	public Object getBean() {
		return fExc;
	}

}