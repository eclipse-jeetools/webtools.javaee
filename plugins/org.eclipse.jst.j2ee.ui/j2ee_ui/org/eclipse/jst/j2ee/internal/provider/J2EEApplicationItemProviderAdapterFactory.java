/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.provider;


import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.jst.j2ee.internal.application.provider.ApplicationItemProviderAdapterFactory;


public class J2EEApplicationItemProviderAdapterFactory extends ApplicationItemProviderAdapterFactory {

	/**
	 * Constructor for J2EEApplicationAdapterFactory.
	 */
	public J2EEApplicationItemProviderAdapterFactory() {
		super();
	}

	/**
	 * @see ApplicationAdapterFactory#createApplicationAdapter()
	 */
	@Override
	public Adapter createApplicationAdapter() {
		return new J2EEApplicationItemProvider(this);
	}

}
