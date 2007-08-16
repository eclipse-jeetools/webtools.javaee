/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jun 30, 2004
 */
package org.eclipse.jst.j2ee.internal.plugin;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.ILaunchable;
import org.eclipse.emf.ecore.EObject;

/**
 * @author jlanuti
 */
public class J2EEUIAdapterFactory implements IAdapterFactory {

	protected static final Class ILAUNCHABLE_CLASS = ILaunchable.class;

	/**
	 * Default Constructor
	 */
	public J2EEUIAdapterFactory() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof EObject) {
			if (adapterType == ILAUNCHABLE_CLASS)
				return adaptableObject;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		return new Class[]{ILAUNCHABLE_CLASS};
	}

}