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
 *  $RCSfile: IDERectangleBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:36 $ 
 */

import java.awt.Rectangle;

import org.eclipse.jem.internal.proxy.ide.*;

public class IDERectangleBeanTypeProxy extends IDEBeanTypeProxy {

IDERectangleBeanTypeProxy(IDEProxyFactoryRegistry aRegistry){
	this(aRegistry,Rectangle.class);
}

private IDERectangleBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class type){
	super(aRegistry,type);
}
protected IIDEBeanProxy newBeanProxy(Object anObject){

	return new IDERectangleBeanProxy(fProxyFactoryRegistry, anObject, this);

}
IDERectangleBeanProxy createRectangleBeanProxy(int x, int y, int width, int height){
	return new IDERectangleBeanProxy(fProxyFactoryRegistry,new Rectangle(x,y,width,height),this);
}
	/*
	 * @see IDEBeanTypeProxy#newBeanTypeForClass(Class)
	 */
	public IDEBeanTypeProxy newBeanTypeForClass(Class type) {
		return new IDERectangleBeanTypeProxy(fProxyFactoryRegistry, type);
	}

}