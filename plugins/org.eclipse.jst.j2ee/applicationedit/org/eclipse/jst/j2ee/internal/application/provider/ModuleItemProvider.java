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
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.application.Module}object.
 */
public class ModuleItemProvider extends ApplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public ModuleItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns Module.gif.
	 */
	public Object getImage(Object object) {
		return org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.getPlugin().getImage("Module"); //$NON-NLS-1$
	}

	/**
	 * This returns the parent of the Module.
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
			;

			// This is for the uri feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), ApplicationProvidersResourceHandler.getString("Uri_UI_"), //$NON-NLS-1$
						ApplicationProvidersResourceHandler.getString("The_uri_property_UI_"), //$NON-NLS-1$
						pkg.getModule_Uri()));

			// This is for the altDD feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), ApplicationProvidersResourceHandler.getString("AltDD_UI_"), //$NON-NLS-1$
						ApplicationProvidersResourceHandler.getString("The_altDD_property_UI_"), //$NON-NLS-1$
						pkg.getModule_AltDD(), false));

			// This is for the application feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), ApplicationProvidersResourceHandler.getString("Application_UI_"), //$NON-NLS-1$
						ApplicationProvidersResourceHandler.getString("The_application_property_UI_"), //$NON-NLS-1$
						pkg.getModule_Application(), false));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Uri feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addUriPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Module_uri_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Module_uri_feature", "_UI_Module_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					ApplicationPackage.eINSTANCE.getModule_Uri(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Alt DD feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addAltDDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Module_altDD_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Module_altDD_feature", "_UI_Module_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					ApplicationPackage.eINSTANCE.getModule_AltDD(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


	public String getText(Object object) {
		return getURIIfPossible((Module) object);
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(Module.class)) {
			case ApplicationPackage.MODULE__URI :
			case ApplicationPackage.MODULE__ALT_DD : {
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

	public static String getURIIfPossible(Module m) {
		return m.getUri() == null ? ApplicationProvidersResourceHandler.getString("<<URI_not_set>>_ERROR_") : m.getUri(); //$NON-NLS-1$
	}
	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
	 */
	/*
	 * public void notifyChanged(Notifier notifier, int eventType, EObject feature, Object oldValue,
	 * Object newValue, int index) { ApplicationPackage pkg = ApplicationPackage.eINSTANCE;; if
	 * (feature == pkg.getModule_Uri() || feature == pkg.getModule_AltDD() || feature ==
	 * pkg.getModule_Application()) { fireNotifyChanged(notifier, eventType, feature, oldValue,
	 * newValue, index); // ModuleExtension does not update automatically. // The following code is
	 * to explicitly notify ApplicationExtension if (feature == pkg.getModule_Uri() || feature ==
	 * pkg.getModule_AltDD() ) { ApplicationExtension applicationExtension =
	 * ApplicationExtensionsHelper.getApplicationExtension(((Module)notifier).getApplication()); if
	 * (applicationExtension == null) return; EObject newFeature =
	 * ApplicationextPackage.eINSTANCE.getApplicationExtension_ModuleExtensions(); Object
	 * moduleExtension = applicationExtension.getModuleExtension((Module)notifier); Notification
	 * notification = new NotificationImpl(applicationExtension, Notification.SET, newFeature,
	 * moduleExtension, moduleExtension); applicationExtension.eNotify(notification); } // code ends
	 * return; } super.notifyChanged(notifier, eventType, feature, oldValue, newValue, index); }
	 */
}