/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.provider;


import org.eclipse.emf.common.notify.Adapter;

public class J2EEModulemapItemProviderAdapterFactory extends ModulemapItemProviderAdapterFactory {

	/**
	 * Constructor for J2EEModulemapItemProviderAdapterFactory.
	 */
	public J2EEModulemapItemProviderAdapterFactory() {
		super();
	}

	/**
	 * @see ModulemapAdapterFactory#createEARProjectMapAdapter()
	 */
	public Adapter createEARProjectMapAdapter() {
		return new J2EEUtilityJavaProjectsItemProvider(this, false);
	}

}