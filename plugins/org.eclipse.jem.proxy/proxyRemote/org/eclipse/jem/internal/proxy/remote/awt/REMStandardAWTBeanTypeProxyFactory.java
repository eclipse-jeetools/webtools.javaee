package org.eclipse.jem.internal.proxy.remote.awt;
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
 *  $RCSfile: REMStandardAWTBeanTypeProxyFactory.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.remote.*;
/**
 * BeanType factory standard AWT bean types.
 * This is package protected because it shouldn't be
 * referenced outside the package. It should only be accessed through
 * the interface.
 */
class REMStandardAWTBeanTypeProxyFactory implements IREMBeanTypeProxyFactory {

	static final String BEAN_TYPE_FACTORY_KEY = "java.awt"; //$NON-NLS-1$
	
	protected final REMProxyFactoryRegistry fFactoryRegistry;
	
	REMStandardAWTBeanTypeProxyFactory(REMProxyFactoryRegistry aRegistry) {
		fFactoryRegistry = aRegistry;	
		fFactoryRegistry.registerBeanTypeProxyFactory(BEAN_TYPE_FACTORY_KEY, this);
	}
	
	/**
	 * We don't pre-cache any types. We need to have the
	 * id from the server to create types.
	 */
	public IREMBeanTypeProxy getExtensionBeanTypeProxy(String className){
		return null;
	}
	
	/**
	 * Create the correct beantype from the class and id passed in.
	 */
	public IREMBeanTypeProxy getExtensionBeanTypeProxy(String className, Integer classID, IBeanTypeProxy superType){
		
		if ("java.awt.Dimension".equals(className)) //$NON-NLS-1$
			return new REMDimensionBeanTypeProxy(fFactoryRegistry, classID, className, superType);
		else if ("java.awt.Point".equals(className)) //$NON-NLS-1$
			return new REMPointBeanTypeProxy(fFactoryRegistry, classID, className, superType);			
		else if ("java.awt.Rectangle".equals(className)) //$NON-NLS-1$
			return new REMRectangleBeanTypeProxy(fFactoryRegistry, classID, className, superType);
		else 
			return null;
	}
	
	/**
	 * Terminate this factory. Since it doesn't hold onto anything other than the registry,
	 * and nothing will be holding onto this factory, nothing needs to be done. It will be GC'd.
	 */
	public void terminateFactory() {
	}	

}