/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.java.instantiation;
/*
 *  $RCSfile: IInstantiationHandlerFactoryAdapter.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:17 $ 
 */
import org.eclipse.emf.common.notify.Adapter;

import org.eclipse.jem.java.impl.JavaFactoryImpl;

/**
 * This adapter is a factory for IInstantiationHandler's. It is attached as
 * an adapter on the ResourceSet that contains the java model. JavaXMIFactory
 * will use it to retrieve its IInstantiationHandler. If the adapter is not
 * found, then no instantiation handler exists.
 */
public interface IInstantiationHandlerFactoryAdapter extends Adapter {
	
	public final static Class ADAPTER_KEY = IInstantiationHandlerFactoryAdapter.class;
	
	/**
	 * Return an IInstantiationHandler.
	 */
	public IInstantiationHandler getInstantiationHandler(JavaFactoryImpl factory);

}
