/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.ui.preferences;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.wst.common.internal.emf.utilities.Assert;





public class JavaEEPreferencePageExtension {

	public static final String ATT_ID = "id"; //$NON-NLS-1$
	public static final String ATT_CLASS = "class"; //$NON-NLS-1$
	public static final String JAVAEE_PAGE_EXTENSION = "javaeepageextender"; //$NON-NLS-1$
	
	private String id = null;
	private IConfigurationElement element;
	private IJavaEEPreferencePageExtender instance;
	private boolean errorCondition = false;
	
	public JavaEEPreferencePageExtension(){
		super();
	}
	
	private void init() {
		id = element.getAttribute(ATT_ID);
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public JavaEEPreferencePageExtension(IConfigurationElement element) {
		Assert.isLegal(JAVAEE_PAGE_EXTENSION.equals(element.getName()), "Extensions must be of the type \"" + JAVAEE_PAGE_EXTENSION + "\"."); //$NON-NLS-1$ //$NON-NLS-2$
		this.element = element;
		init();
	}

	public IJavaEEPreferencePageExtender getInstance() {
		try {
			if (instance == null && !errorCondition)
				instance = (IJavaEEPreferencePageExtender) element.createExecutableExtension("className"); //$NON-NLS-1$
		} catch (Throwable e) {
			errorCondition = true;
		}
		return instance;
	}	
}
