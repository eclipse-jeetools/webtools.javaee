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
import org.eclipse.jst.j2ee.webapplication.FormLoginConfig;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.FormLoginConfig}
 * object.
 */
public class FormLoginConfigItemProvider extends WebapplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public FormLoginConfigItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns form_login_config.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("form_login_config");//$NON-NLS-1$
	}

	/**
	 * This returns form_login_config.gif.
	 */
	//TODO Removing the UI dependency by commenting out this method
	/*
	 * public Image getLabelImage(Object object) { return (Image)
	 * WEBUIPlugin.getDefault().getImageFromRegistry("form_login_config");//$NON-NLS-1$ }
	 */
	public String getLabelText(Object object) {
		return WebAppEditResourceHandler.getString("52concat_UI_", (new Object[]{((FormLoginConfig) object).getFormLoginPage()})); //$NON-NLS-1$ = "FormLoginConfig {0}"
	}

	/**
	 * This returns the loginConfig of the FormLoginConfig.
	 */
	public Object getParent(Object object) {
		return ((FormLoginConfig) object).getLoginConfig();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;
			;

			// This is for the formLoginPage feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("FormLoginPage_UI_"), //$NON-NLS-1$ = "FormLoginPage"
						WebAppEditResourceHandler.getString("The_formLoginPage_property_UI_"), //$NON-NLS-1$ = "The formLoginPage property"
						pkg.getFormLoginConfig_FormLoginPage()));

			// This is for the formErrorPage feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("FormErrorPage_UI_"), //$NON-NLS-1$ = "FormErrorPage"
						WebAppEditResourceHandler.getString("The_formErrorPage_property_UI_"), //$NON-NLS-1$ = "The formErrorPage property"
						pkg.getFormLoginConfig_FormErrorPage()));

			// This is for the loginConfig feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("LoginConfig_UI_"), //$NON-NLS-1$ = "LoginConfig"
						WebAppEditResourceHandler.getString("The_loginConfig_property_UI_"), //$NON-NLS-1$ = "The loginConfig property"
						pkg.getFormLoginConfig_LoginConfig()));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Form Login Page feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFormLoginPagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_FormLoginConfig_formLoginPage_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_FormLoginConfig_formLoginPage_feature", "_UI_FormLoginConfig_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getFormLoginConfig_FormLoginPage(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Form Error Page feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFormErrorPagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_FormLoginConfig_formErrorPage_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_FormLoginConfig_formErrorPage_feature", "_UI_FormLoginConfig_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getFormLoginConfig_FormErrorPage(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


	public String getText(Object object) {
		return WebAppEditResourceHandler.getString("16concat_UI_", (new Object[]{((FormLoginConfig) object).getFormLoginPage()})); //$NON-NLS-1$ = "FormLoginConfig {0}"
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(FormLoginConfig.class)) {
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE :
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE : {
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