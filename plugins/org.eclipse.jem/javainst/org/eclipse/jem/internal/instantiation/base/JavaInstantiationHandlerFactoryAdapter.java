/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation.base;
/*
 *  $RCSfile: JavaInstantiationHandlerFactoryAdapter.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:20:25 $ 
 */

import org.eclipse.emf.common.notify.impl.AdapterImpl;

import org.eclipse.jem.java.impl.JavaFactoryImpl;
import org.eclipse.jem.internal.java.instantiation.IInstantiationHandler;
import org.eclipse.jem.internal.java.instantiation.IInstantiationHandlerFactoryAdapter;

/**
 * This adapter is attached to the resource set for a java model. The
 * JavaXMIFactory will ask for this adapter and ask it for the IInstantiationHandler.
 */
public class JavaInstantiationHandlerFactoryAdapter extends AdapterImpl implements IInstantiationHandlerFactoryAdapter {

	/**
	 * Constructor for JavaInstantiationHandlerFactoryAdapter.
	 */
	public JavaInstantiationHandlerFactoryAdapter() {
		super();
	}

	/**
	 * @see org.eclipse.jem.internal.instantiation.IInstantiationHandlerFactoryAdapter#getInstantiationHandler(JavaFactoryImpl)
	 */
	public IInstantiationHandler getInstantiationHandler(JavaFactoryImpl factory) {
		return JavaFactoryHandler.INSTANCE;
	}

	/**
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(Object)
	 */
	public boolean isAdapterForType(Object type) {
		return IInstantiationHandlerFactoryAdapter.ADAPTER_KEY == type;
	}

}
