/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;


import org.eclipse.emf.common.notify.Adapter;

;
/**
 * Insert the type's description here. Creation date: (6/20/2001 7:20:07 PM)
 * 
 * @author: Administrator
 */
public class J2EEEjbItemProviderAdapterFactory extends org.eclipse.jst.j2ee.internal.ejb.provider.EjbItemProviderAdapterFactory {
	/**
	 * J2EEEjbItemProviderAdapterFactory constructor comment.
	 */
	public J2EEEjbItemProviderAdapterFactory() {
		super();
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.ejb.ContainerManagedEntity}.
	 */
	public Adapter createContainerManagedEntityAdapter() {
		if (containerManagedEntityItemProvider == null) {
			containerManagedEntityItemProvider = new J2EEContainerManagedEntityItemProvider(this);
		}

		return containerManagedEntityItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.ejb.EJBJar}.
	 */
	public Adapter createEJBJarAdapter() {
		if (eJBJarItemProvider == null) {
			eJBJarItemProvider = new GroupedEJBJarItemProvider(this, true);
		}

		return eJBJarItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.ejb.Entity}.
	 */
	public Adapter createEntityAdapter() {
		if (entityItemProvider == null) {
			entityItemProvider = new J2EEEntityItemProvider(this);
		}

		return entityItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.ejb.Session}.
	 */
	public Adapter createSessionAdapter() {
		if (sessionItemProvider == null) {
			sessionItemProvider = new J2EESessionItemProvider(this);
		}

		return sessionItemProvider;
	}

	public Adapter createMessageDrivenAdapter() {
		if (messageDrivenItemProvider == null) {
			messageDrivenItemProvider = new J2EEMessageDrivenItemProvider(this);
		}
		return messageDrivenItemProvider;
	}
}