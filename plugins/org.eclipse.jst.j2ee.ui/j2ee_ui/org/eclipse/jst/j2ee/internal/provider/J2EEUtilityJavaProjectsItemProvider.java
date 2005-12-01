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
package org.eclipse.jst.j2ee.internal.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;

public class J2EEUtilityJavaProjectsItemProvider extends EARProjectMapItemProvider {

	public final static String UTILITY_JAVA_PROJECTS = J2EEUIMessages.getResourceString("Utility_Java_Projects_UI_"); //$NON-NLS-1$

	/**
	 * Constructor for UtilityJARsItemProvider.
	 * 
	 * @param adapterFactory
	 */
	public J2EEUtilityJavaProjectsItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * Constructor for UtilityJARsItemProvider.
	 * 
	 * @param adapterFactory
	 * @param includeModules
	 */
	public J2EEUtilityJavaProjectsItemProvider(AdapterFactory adapterFactory, boolean includeModules) {
		super(adapterFactory, includeModules);
	}

	/**
	 * @see ItemProviderAdapter#getImage(Object)
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("folder"); //$NON-NLS-1$
	}

	/**
	 * @see ItemProviderAdapter#getText(Object)
	 */
	public String getText(Object object) {
		return UTILITY_JAVA_PROJECTS;
	}

}
