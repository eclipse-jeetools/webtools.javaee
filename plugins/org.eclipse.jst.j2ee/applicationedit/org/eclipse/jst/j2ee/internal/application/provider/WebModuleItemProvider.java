/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;


/**
 * This is the item provider adpater for a
 * {@link org.eclipse.jst.j2ee.internal.internal.application.WebModule}object.
 */
public class WebModuleItemProvider extends ModuleItemProvider implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public WebModuleItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns WebModule.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("module_web_obj"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((WebModule) object).getUri();
		return label == null || label.length() == 0 ? getString("_UI_WebModule_type") : //$NON-NLS-1$
					getString("_UI_WebModule_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(WebModule.class)) {
			case ApplicationPackage.WEB_MODULE__CONTEXT_ROOT : {
				fireNotifyChanged(notification);
				return;
			}
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 */
	public ResourceLocator getResourceLocator() {
		return ApplicationProvidersResourceHandler.RESOURCE_LOCATOR;
	}

	/**
	 * This returns the parent of the WebModule.
	 */
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			ApplicationPackage pkg = ApplicationPackage.eINSTANCE;

			// This is for the contextRoot feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), ApplicationProvidersResourceHandler.getString("ContextRoot_UI_"), //$NON-NLS-1$
						ApplicationProvidersResourceHandler.getString("The_contextRoot_property_UI_"), //$NON-NLS-1$
						pkg.getWebModule_ContextRoot(), false));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Context Root feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addContextRootPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebModule_contextRoot_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebModule_contextRoot_feature", "_UI_WebModule_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					ApplicationPackage.eINSTANCE.getWebModule_ContextRoot(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


}
