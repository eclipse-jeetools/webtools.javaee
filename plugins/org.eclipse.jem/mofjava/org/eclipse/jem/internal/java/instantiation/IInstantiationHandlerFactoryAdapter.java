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
package org.eclipse.jem.internal.java.instantiation;
/*
 *  $RCSfile: IInstantiationHandlerFactoryAdapter.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/15 22:37:02 $ 
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
