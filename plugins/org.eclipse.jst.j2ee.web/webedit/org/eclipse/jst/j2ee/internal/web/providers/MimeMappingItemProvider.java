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
package org.eclipse.jst.j2ee.internal.web.providers;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.webapplication.MimeMapping;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.MimeMapping}object.
 */
public class MimeMappingItemProvider extends WebapplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public MimeMappingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns mime_mapping.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("mime_mapping");//$NON-NLS-1$
	}

	/**
	 * This returns the webApp of the MimeMapping.
	 */
	public Object getParent(Object object) {
		return ((MimeMapping) object).getWebApp();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;

			// This is for the extension feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("Extension_UI_"), //$NON-NLS-1$ = "Extension"
						WebAppEditResourceHandler.getString("The_extension_property_UI_"), //$NON-NLS-1$ = "The extension property"
						pkg.getMimeMapping_Extension()));

			// This is for the mimeType feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("MimeType_UI_"), //$NON-NLS-1$ = "MimeType"
						WebAppEditResourceHandler.getString("The_mimeType_property_UI_"), //$NON-NLS-1$ = "The mimeType property"
						pkg.getMimeMapping_MimeType()));

			// This is for the webApp feature.
			//
			/*
			 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
			 * WebAppEditResourceHandler.getString("WebApp_UI_"), //$NON-NLS-1$ = "WebApp"
			 * WebAppEditResourceHandler.getString("The_webApp_property_UI_"), //$NON-NLS-1$ = "The
			 * webApp property" pkg.getMimeMapping_WebApp()));
			 */

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Extension feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addExtensionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MimeMapping_extension_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MimeMapping_extension_feature", "_UI_MimeMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getMimeMapping_Extension(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Mime Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMimeTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MimeMapping_mimeType_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MimeMapping_mimeType_feature", "_UI_MimeMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getMimeMapping_MimeType(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


	public String getText(Object object) {
		return WebAppEditResourceHandler.getString("22concat_UI_", (new Object[]{((MimeMapping) object).getExtension()})); //$NON-NLS-1$ = "MimeMapping {0}"
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(MimeMapping.class)) {
			case WebapplicationPackage.MIME_MAPPING__EXTENSION :
			case WebapplicationPackage.MIME_MAPPING__MIME_TYPE : {
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
	 * 
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return J2EEPlugin.getDefault();
	}

	/**
	 * This always returns false. The base class has already implemented
	 * {@link org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren ItemProviderAdapter.getChildren}
	 * to return the empty enumeration, and this to check that enumeration, but we know there can't
	 * be any children, so this is faster.
	 */
	public boolean hasChildren(Object object) {
		return false;
	}

}
