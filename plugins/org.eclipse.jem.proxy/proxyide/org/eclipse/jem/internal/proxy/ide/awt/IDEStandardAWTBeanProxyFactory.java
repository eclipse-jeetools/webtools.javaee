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
 *  $RCSfile: IDEStandardAWTBeanProxyFactory.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.awt.*;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * Standard AWT Bean Proxy Factory.
 * Package protected because it should not referenced
 * outside of the package other than through the interface.
 */
class IDEStandardAWTBeanProxyFactory implements IStandardAwtBeanProxyFactory {

	final IDEStandardAWTBeanTypeProxyFactory fAWTBeanTypeFactory;
	
public IDEStandardAWTBeanProxyFactory(ProxyFactoryRegistry factory) {
	factory.registerBeanProxyFactory(IStandardAwtBeanProxyFactory.REGISTRY_KEY, this);
	fAWTBeanTypeFactory = (IDEStandardAWTBeanTypeProxyFactory)factory.getBeanTypeProxyFactoryExtension(IDEStandardAWTBeanTypeProxyFactory.BEAN_TYPE_FACTORY_KEY);
}
public IDimensionBeanProxy createDimensionBeanProxyWith(int width, int height){
	return fAWTBeanTypeFactory.dimensionType.createDimensionBeanProxy(width,height);
}

public IPointBeanProxy createPointBeanProxyWith(int x, int y){
	return fAWTBeanTypeFactory.pointType.createPointBeanProxy(x,y);
}
public IRectangleBeanProxy createBeanProxyWith(int x, int y, int width, int height){
	return fAWTBeanTypeFactory.rectangleType.createRectangleBeanProxy(x,y,width,height);
}
/**
 * Terminate this factory. Since it doesn't hold onto anything other than the beantype factory,
 * and nothing will be holding onto this factory, nothing needs to be done. It will be GC'd.
 */
public void terminateFactory() {
}
}

