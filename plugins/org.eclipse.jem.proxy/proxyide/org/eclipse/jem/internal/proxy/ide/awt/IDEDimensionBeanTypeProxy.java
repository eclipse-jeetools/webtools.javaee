package org.eclipse.jem.internal.proxy.ide.awt;
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
 *  $RCSfile: IDEDimensionBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.ide.*;
import org.eclipse.jem.internal.proxy.ide.IDEBeanTypeProxy;

import java.awt.Dimension;

public class IDEDimensionBeanTypeProxy extends IDEBeanTypeProxy {

IDEDimensionBeanTypeProxy(ProxyFactoryRegistry aRegistry){
	this(aRegistry,Dimension.class);
}

private IDEDimensionBeanTypeProxy(ProxyFactoryRegistry aRegistry, Class type){
	super(aRegistry, type);
}

protected IIDEBeanProxy newBeanProxy(Object anObject){

	return new IDEDimensionBeanProxy(fProxyFactoryRegistry, anObject, this);

}
IDEDimensionBeanProxy createDimensionBeanProxy(int width, int height){
	return new IDEDimensionBeanProxy(fProxyFactoryRegistry,new Dimension(width,height),this);
}
	/*
	 * @see IDEBeanTypeProxy#newBeanTypeForClass(Class)
	 */
	public IDEBeanTypeProxy newBeanTypeForClass(Class type) {
		return new IDEDimensionBeanTypeProxy(fProxyFactoryRegistry, type);
	}

}