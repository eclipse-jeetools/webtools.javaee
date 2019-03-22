/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.application.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.ConnectorModule;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;


/**
 * This is the item provider adapter for a Connector modules in ears.
 */
public class ConnectorModuleItemProvider extends ModuleItemProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * 
	 * @param AdapterFactory
	 *            adapterFactory - Current adapter
	 */
	public ConnectorModuleItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}// ConnectorModuleItemProvider

	/**
	 * This returns resource image
	 * 
	 * @param Object
	 *            object - resource
	 * @return Object
	 */
	@Override
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("full/obj16/connector_module"); //$NON-NLS-1$
	}// getImage

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ConnectorModule) object).getUri();
		return label == null || label.length() == 0 ? getString("_UI_ConnectorModule_type") : //$NON-NLS-1$
					getString("_UI_ConnectorModule_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return ApplicationProvidersResourceHandler.RESOURCE_LOCATOR;
	}

	/**
	 * This returns the parent of the ConnectorModule.
	 * 
	 * @param Object
	 *            object - resource
	 * @return Object
	 */
	@Override
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}// getParent

	/**
	 * This returns the property descriptors for the adapted class.
	 * 
	 * @param Object
	 *            object - resource
	 * @return List
	 */
	@Override
	public List getPropertyDescriptors(Object object) {

		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);
		}// if

		return itemPropertyDescriptors;
	}// getPropertyDescriptors

}// ConnectorModuleItemProvider
