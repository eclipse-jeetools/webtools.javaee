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
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.ejb.ActivationConfigProperty;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.ActivationConfigProperty}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ActivationConfigPropertyItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ActivationConfigPropertyItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			//addValuePropertyDescriptor(object);
			//addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Value feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ActivationConfigProperty_value_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ActivationConfigProperty_value_feature", "_UI_ActivationConfigProperty_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getActivationConfigProperty_Value(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ActivationConfigProperty_name_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ActivationConfigProperty_name_feature", "_UI_ActivationConfigProperty_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getActivationConfigProperty_Name(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


	/**
	 * This returns ActivationConfigProperty.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("full/obj16/ActivationConfigProperty"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *  
	 */
	public String getText(Object object) {
		String label = ((ActivationConfigProperty) object).getName();
		return label == null || label.length() == 0 ? EJBProviderLibrariesResourceHandler.getString("_UI_ActivationConfigProperty_type") : //$NON-NLS-1$
					EJBProviderLibrariesResourceHandler.getString("_UI_ActivationConfigProperty_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(ActivationConfigProperty.class)) {
			case EjbPackage.ACTIVATION_CONFIG_PROPERTY__VALUE :
			case EjbPackage.ACTIVATION_CONFIG_PROPERTY__NAME : {
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
}