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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.webapplication.LoginConfig;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.LoginConfig}object.
 */
public class LoginConfigItemProvider extends WebapplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public LoginConfigItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This creates a new child for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public Object createChild(Object object) {
		// TODO: create some child object.
		return null;
	}

	/**
	 * This specifies how to implement {@link #getChildren}and {@link AddCommand}and
	 * {@link RemoveCommand}support in {@link #createCommand}.
	 */
	public Collection getChildrenReferences(Object object) {
		WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;
		Collection result = new ArrayList();
		result.add(pkg.getLoginConfig_FormLoginConfig());
		return result;
	}

	//TODO Removing UI dependency by commenting out method
	/**
	 * This returns the image for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	/*
	 * public ImageDescriptor getCreateChildImageDescriptor(Object object) { EObject refObject =
	 * (EObject)object; return
	 * WEBUIPlugin.getDefault().getImageDescriptor(refObject.eClass().getName() +
	 * "Create#CHILD_CLASS_NAME#");//$NON-NLS-1$ }
	 */
	/**
	 * This returns the label for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return WebAppEditResourceHandler.getString("Create_#CHILD_CLASS_NAME#_UI_"); //$NON-NLS-1$ = "Create #CHILD_CLASS_NAME#"
	}

	/**
	 * This returns the help text for
	 * {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return WebAppEditResourceHandler.getString("1concat", (new Object[]{refObject.eClass().getName()}));//$NON-NLS-1$ = "Create a child of type #CHILD_CLASS_NAME# for the selected {0}." 
	}

	/**
	 * This returns form_login_config.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("form_login_config");//$NON-NLS-1$
	}

	/**
	 * This returns the webApp of the LoginConfig.
	 */
	public Object getParent(Object object) {
		return ((LoginConfig) object).getWebApp();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;
			;

			// This is for the authMethod feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("AuthMethod_UI_"), //$NON-NLS-1$ = "AuthMethod"
						WebAppEditResourceHandler.getString("The_authMethod_property_UI_"), //$NON-NLS-1$ = "The authMethod property"
						pkg.getLoginConfig_AuthMethod()));

			// This is for the realmName feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("RealmName_UI_"), //$NON-NLS-1$ = "RealmName"
						WebAppEditResourceHandler.getString("The_realmName_property_UI_"), //$NON-NLS-1$ = "The realmName property"
						pkg.getLoginConfig_RealmName()));

			// This is for the webApp feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("WebApp_UI_"), //$NON-NLS-1$ = "WebApp"
						WebAppEditResourceHandler.getString("The_webApp_property_UI_"), //$NON-NLS-1$ = "The webApp property"
						pkg.getLoginConfig_WebApp()));

			// This is for the formLoginConfig feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("FormLoginConfig_UI_"), //$NON-NLS-1$ = "FormLoginConfig"
						WebAppEditResourceHandler.getString("The_formLoginConfig_proper_UI_"), //$NON-NLS-1$ = "The formLoginConfig property"
						pkg.getLoginConfig_FormLoginConfig()));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Auth Method feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addAuthMethodPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_LoginConfig_authMethod_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_LoginConfig_authMethod_feature", "_UI_LoginConfig_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getLoginConfig_AuthMethod(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Realm Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRealmNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_LoginConfig_realmName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_LoginConfig_realmName_feature", "_UI_LoginConfig_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getLoginConfig_RealmName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Authorization Method feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addAuthorizationMethodPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_LoginConfig_authorizationMethod_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_LoginConfig_authorizationMethod_feature", "_UI_LoginConfig_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getLoginConfig_AuthorizationMethod(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	public String getText(Object object) {
		return WebAppEditResourceHandler.getString("21concat_UI_", (new Object[]{((LoginConfig) object).getRealmName()})); //$NON-NLS-1$ = "LoginConfig {0}"
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(LoginConfig.class)) {
			case WebapplicationPackage.LOGIN_CONFIG__AUTH_METHOD :
			case WebapplicationPackage.LOGIN_CONFIG__REALM_NAME :
			case WebapplicationPackage.LOGIN_CONFIG__AUTHORIZATION_METHOD :
			case WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG : {
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

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getLoginConfig_FormLoginConfig(), WebapplicationFactory.eINSTANCE.createFormLoginConfig()));
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