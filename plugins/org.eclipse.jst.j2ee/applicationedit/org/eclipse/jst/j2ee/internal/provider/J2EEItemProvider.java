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


import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;

public class J2EEItemProvider extends ItemProvider implements IAdaptable {

	protected static final Class IRESOURCE_CLASS = IResource.class;
	protected static final Class IPROJECT_CLASS = IProject.class;


	/**
	 * Constructor for J2EEItemProvider.
	 */
	public J2EEItemProvider() {
		super();
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param children
	 */
	public J2EEItemProvider(Collection children) {
		super(children);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param text
	 */
	public J2EEItemProvider(String text) {
		super(text);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param text
	 * @param children
	 */
	public J2EEItemProvider(String text, Collection children) {
		super(text, children);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param text
	 * @param image
	 */
	public J2EEItemProvider(String text, Object image) {
		super(text, image);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param text
	 * @param image
	 * @param children
	 */
	public J2EEItemProvider(String text, Object image, Collection children) {
		super(text, image, children);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param text
	 * @param image
	 * @param parent
	 */
	public J2EEItemProvider(String text, Object image, Object parent) {
		super(text, image, parent);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param text
	 * @param image
	 * @param parent
	 * @param children
	 */
	public J2EEItemProvider(String text, Object image, Object parent, Collection children) {
		super(text, image, parent, children);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param adapterFactory
	 */
	public J2EEItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 */
	public J2EEItemProvider(AdapterFactory adapterFactory, String text) {
		super(adapterFactory, text);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 */
	public J2EEItemProvider(AdapterFactory adapterFactory, String text, Object image) {
		super(adapterFactory, text, image);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 * @param parent
	 */
	public J2EEItemProvider(AdapterFactory adapterFactory, String text, Object image, Object parent) {
		super(adapterFactory, text, image, parent);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param adapterFactory
	 * @param children
	 */
	public J2EEItemProvider(AdapterFactory adapterFactory, Collection children) {
		super(adapterFactory, children);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param children
	 */
	public J2EEItemProvider(AdapterFactory adapterFactory, String text, Collection children) {
		super(adapterFactory, text, children);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 * @param children
	 */
	public J2EEItemProvider(AdapterFactory adapterFactory, String text, Object image, Collection children) {
		super(adapterFactory, text, image, children);
	}

	/**
	 * Constructor for J2EEItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 * @param parent
	 * @param children
	 */
	public J2EEItemProvider(AdapterFactory adapterFactory, String text, Object image, Object parent, Collection children) {
		super(adapterFactory, text, image, parent, children);
	}

	protected boolean notificationOn = true;

	protected void disableNotification() {
		notificationOn = false;
	}

	protected void enableNotification() {
		notificationOn = true;
	}


	public void fireNotifyChanged(Notification notification) {
		if (notificationOn)
			super.fireNotifyChanged(notification);
	}

	public Collection getChildren(Object object) {
		if (children.isEmpty()) {
			try {
				disableNotification();
				initializeChildren();
			} finally {
				enableNotification();
			}
		}
		return children;
	}

	protected void initializeChildren() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		if (adapter == IRESOURCE_CLASS || adapter == IPROJECT_CLASS)
			return getProjectFromParent();
		return null;
	}

	/**
	 * @return
	 */
	private IProject getProjectFromParent() {
		return ProjectUtilities.getProject(getParent());
	}

}
