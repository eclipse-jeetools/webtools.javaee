package org.eclipse.jem.internal.proxy.remote.awt;
/*******************************************************************************
 * Copyright (c)  2001, 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: REMStandardAWTBeanProxyFactory.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:56:10 $ 
 */

import org.eclipse.jem.internal.proxy.awt.*;
import org.eclipse.jem.internal.proxy.core.*;

/**
 * Standard AWT Bean Proxy Factory.
 * Package protected because it should not referenced
 * outside of the package other than through the interface.
 */
class REMStandardAWTBeanProxyFactory implements IStandardAwtBeanProxyFactory {

	final IStandardBeanTypeProxyFactory fBeanTypeFactory;
	
	public REMStandardAWTBeanProxyFactory(ProxyFactoryRegistry factory) {
		factory.registerBeanProxyFactory(IStandardAwtBeanProxyFactory.REGISTRY_KEY, this);
		fBeanTypeFactory = factory.getBeanTypeProxyFactory();
	}
	
	public IDimensionBeanProxy createDimensionBeanProxyWith(int width, int height){
		try {
			return (IDimensionBeanProxy) fBeanTypeFactory.getBeanTypeProxy("java.awt.Dimension").newInstance("new java.awt.Dimension("+width+","+height+")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		} catch (ThrowableProxy e) {
			return null;
		} catch (InstantiationException e) {
			return null;	// Shouldn't occur
		}
	}
	
	public IPointBeanProxy createPointBeanProxyWith(int x, int y){
		try {
			return (IPointBeanProxy) fBeanTypeFactory.getBeanTypeProxy("java.awt.Point").newInstance("new java.awt.Point("+x+","+y+")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		} catch (ThrowableProxy e) {
			return null;
		} catch (InstantiationException e) {
			return null;	// Shouldn't occur
		}

	}
	
	public IRectangleBeanProxy createBeanProxyWith(int x, int y, int width, int height){
		try {
			return (IRectangleBeanProxy) fBeanTypeFactory.getBeanTypeProxy("java.awt.Rectangle").newInstance("new java.awt.Rectangle("+x+","+y+","+width+","+height+")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		} catch (ThrowableProxy e) {
			return null;
		} catch (InstantiationException e) {
			return null;	// Shouldn't occur
		}

	}
	
	/*
	 * Terminate this factory. Since it doesn't hold onto anything other than the beantype factory,
	 * and nothing will be holding onto this factory, nothing needs to be done. It will be GC'd.
	 */
	public void terminateFactory(boolean wait) {
	}

}