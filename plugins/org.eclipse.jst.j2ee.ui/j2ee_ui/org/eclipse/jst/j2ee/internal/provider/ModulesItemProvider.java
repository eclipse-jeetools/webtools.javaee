/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.provider;


import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class ModulesItemProvider extends J2EEItemProvider {
	public static final String MODULES = J2EEUIMessages.getResourceString("Modules_UI_"); //$NON-NLS-1$

	/**
	 * Constructor for ModulesItemProvider.
	 */
	public ModulesItemProvider() {
		super();
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param children
	 */
	public ModulesItemProvider(Collection children) {
		super(children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 */
	public ModulesItemProvider(String text) {
		super(text);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 * @param children
	 */
	public ModulesItemProvider(String text, Collection children) {
		super(text, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 * @param image
	 */
	public ModulesItemProvider(String text, Object image) {
		super(text, image);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 * @param image
	 * @param children
	 */
	public ModulesItemProvider(String text, Object image, Collection children) {
		super(text, image, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 * @param image
	 * @param parent
	 */
	public ModulesItemProvider(String text, Object image, Object parent) {
		super(text, image, parent);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 * @param image
	 * @param parent
	 * @param children
	 */
	public ModulesItemProvider(String text, Object image, Object parent, Collection children) {
		super(text, image, parent, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text) {
		super(adapterFactory, text);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text, Object image) {
		super(adapterFactory, text, image);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 * @param parent
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text, Object image, Object parent) {
		super(adapterFactory, text, image, parent);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param children
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, Collection children) {
		super(adapterFactory, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param children
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text, Collection children) {
		super(adapterFactory, text, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 * @param children
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text, Object image, Collection children) {
		super(adapterFactory, text, image, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 * @param parent
	 * @param children
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text, Object image, Object parent, Collection children) {
		super(adapterFactory, text, image, parent, children);
	}


	/**
	 * @see ItemProviderAdapter#getImage(Object)
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("folder"); //$NON-NLS-1$
	}

	public Application getParentApplication() {
		return (Application) getParent();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.provider.J2EEItemProvider#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		if (adapter == IRESOURCE_CLASS || adapter == IPROJECT_CLASS)
			return ProjectUtilities.getProject(getParentApplication());
		return super.getAdapter(adapter);
	}


	/**
	 * @see IItemLabelProvider#getText(Object)
	 */
	public String getText(Object object) {
		return MODULES;
	}

}