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
 *  $RCSfile: IDEPointBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.ide.*;
import org.eclipse.jem.internal.proxy.ide.IDEBeanTypeProxy;

import java.awt.Point;

public class IDEPointBeanTypeProxy extends IDEBeanTypeProxy {

IDEPointBeanTypeProxy(ProxyFactoryRegistry aRegistry){
	this(aRegistry,Point.class);
}
private IDEPointBeanTypeProxy(ProxyFactoryRegistry aRegistry, Class type){
	super(aRegistry,type);
}
protected IIDEBeanProxy newBeanProxy(Object anObject){

	return new IDEPointBeanProxy(fProxyFactoryRegistry, anObject, this);

}
IDEPointBeanProxy createPointBeanProxy(int x, int y){
	return new IDEPointBeanProxy(fProxyFactoryRegistry,new Point(x,y),this);
}
	/*
	 * @see IDEBeanTypeProxy#newBeanTypeForClass(Class)
	 */
	public IDEBeanTypeProxy newBeanTypeForClass(Class type) {
		return new IDEPointBeanTypeProxy(fProxyFactoryRegistry, type);
	}

}