/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * This is the item provider adpater for a
 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.LocalEncodingMapping}object. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class LocalEncodingMappingItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public LocalEncodingMappingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addLocalePropertyDescriptor(object);
			addEncodingPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Locale feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLocalePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_LocalEncodingMapping_locale_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_LocalEncodingMapping_locale_feature", "_UI_LocalEncodingMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getLocalEncodingMapping_Locale(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Encoding feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addEncodingPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_LocalEncodingMapping_encoding_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_LocalEncodingMapping_encoding_feature", "_UI_LocalEncodingMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getLocalEncodingMapping_Encoding(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


	/**
	 * This returns LocalEncodingMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public Object getImage(Object object) {
		//		return getResourceLocator().getImage("full/obj16/LocalEncodingMapping");
		return WebPlugin.getDefault().getImage("local_encoding_mapping");//$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 */
	@Override
	public String getText(Object object) {
		String lem = WebAppEditResourceHandler.getString("local_encoding_mapping_UI_"); //$NON-NLS-1$
		String label = ((LocalEncodingMapping) object).getLocale();
		return label == null || label.length() == 0 ? lem : lem + " " + label; //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(LocalEncodingMapping.class)) {
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING__LOCALE :
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING__ENCODING : {
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
		return J2EEPlugin.getDefault();
	}
}
