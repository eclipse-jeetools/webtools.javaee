package org.eclipse.jem.internal.proxy.ide.awt;
/*******************************************************************************
 * Copyright (c)  2001, 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IDEStandardAWTBeanTypeProxyFactory.java,v $
 *  $Revision: 1.2 $  $Date: 2004/10/12 20:20:14 $ 
 */

import org.eclipse.jem.internal.proxy.ide.*;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * BeanType factory standard AWT bean types.
 * This is package protected because it shouldn't be
 * referenced outside the package. It should only be accessed through
 * the interface.
 */
class IDEStandardAWTBeanTypeProxyFactory implements IDEExtensionBeanTypeProxyFactory {

	static final String BEAN_TYPE_FACTORY_KEY = "java.awt"; //$NON-NLS-1$
	
	protected final IDEProxyFactoryRegistry fFactoryRegistry;
	protected final IDEDimensionBeanTypeProxy dimensionType;
	protected final IDEPointBeanTypeProxy pointType;
	protected final IDERectangleBeanTypeProxy rectangleType;
	
IDEStandardAWTBeanTypeProxyFactory(IDEProxyFactoryRegistry aRegistry) {
	fFactoryRegistry = aRegistry;	
	fFactoryRegistry.registerBeanTypeProxyFactory(BEAN_TYPE_FACTORY_KEY, this);
	dimensionType = new IDEDimensionBeanTypeProxy(fFactoryRegistry);
	pointType = new IDEPointBeanTypeProxy(fFactoryRegistry);
	rectangleType = new IDERectangleBeanTypeProxy(fFactoryRegistry);
}
/**
 * Create the correct beantype from the class and id passed in.
 */
public IDEBeanTypeProxy getExtensionBeanTypeProxy(String className){
	
	if ("java.awt.Dimension".equals(className)) //$NON-NLS-1$
		return dimensionType;
	else if ("java.awt.Point".equals(className)) //$NON-NLS-1$
		return pointType;
	else if ("java.awt.Rectangle".equals(className)) //$NON-NLS-1$
		return rectangleType;
	else 
		return null;
}

/**
 * Create the correct beantype from the class and id passed in.
 */
public IDEBeanTypeProxy getExtensionBeanTypeProxy(String className, IBeanTypeProxy beanTypeProxy){

	return getExtensionBeanTypeProxy(className);
}

public void terminateFactory(boolean wait){
}
}
