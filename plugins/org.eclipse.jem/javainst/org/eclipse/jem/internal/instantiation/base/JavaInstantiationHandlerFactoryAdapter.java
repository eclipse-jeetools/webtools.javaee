/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation.base;
/*


 */

import org.eclipse.emf.common.notify.impl.AdapterImpl;

import org.eclipse.jem.java.internal.impl.JavaFactoryImpl;
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
