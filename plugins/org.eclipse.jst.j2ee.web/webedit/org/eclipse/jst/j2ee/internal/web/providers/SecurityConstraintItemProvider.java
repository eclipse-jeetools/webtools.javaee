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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;

/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.SecurityConstraint}
 * object.
 */
public class SecurityConstraintItemProvider extends WebapplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public SecurityConstraintItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This creates a new child for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public Object createChild(Object object) {
		// TODO: check that this is what you want.
		WebResourceCollection child = WebapplicationFactory.eINSTANCE.createWebResourceCollection();

		// TODO: initialize child here...

		return child;
	}

	/**
	 * This specifies how to implement {@link #getChildren}and {@link AddCommand}and
	 * {@link RemoveCommand}support in {@link #createCommand}.
	 */
	public Collection getChildrenReferences(Object object) {
		WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;
		Collection result = new ArrayList();
		result.add(pkg.getSecurityConstraint_WebResourceCollections());
		result.add(pkg.getSecurityConstraint_AuthConstraint());
		result.add(pkg.getSecurityConstraint_UserDataConstraint());
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EReference getChildReference(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildReference(object, child);
	}


	/**
	 * This returns the image for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public Object getCreateChildImage(Object object) {
		EObject refObject = (EObject) object;
		return WebPlugin.getDefault().getImage(refObject.eClass().getName() + "CreateWebResourceCollection"); //$NON-NLS-1$
	}

	/**
	 * This returns the label for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return WebAppEditResourceHandler.getString("Create_WebResourceCollecti_UI_"); //$NON-NLS-1$ = "Create WebResourceCollection"
	}

	/**
	 * This returns the help text for
	 * {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return WebAppEditResourceHandler.getString("24concat", (new Object[]{refObject.eClass().getName()})); //$NON-NLS-1$ = "Create a child of type WebResourceCollection for the selected {0}."
	}

	/**
	 * This returns security_constraint.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("security_constraint"); //$NON-NLS-1$
	}

	/**
	 * This returns the webApp of the SecurityConstraint.
	 */
	public Object getParent(Object object) {
		return ((SecurityConstraint) object).getWebApp();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;

			// This is for the webApp feature.
			//
			/*
			 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
			 * WebAppEditResourceHandler.getString("WebApp_UI_"), //$NON-NLS-1$ = "WebApp"
			 * WebAppEditResourceHandler.getString("The_webApp_property_UI_"), //$NON-NLS-1$ = "The
			 * webApp property" pkg.getSecurityConstraint_WebApp()));
			 */

			// This is for the displayName feature.
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("DisplayName_UI_"), //$NON-NLS-1$ = "DisplayName"
						WebAppEditResourceHandler.getString("The_displayName_property_UI_"), //$NON-NLS-1$ = "The displayName property"
						pkg.getSecurityConstraint_DisplayName()));

			// This is for the authConstraint feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("AuthConstraint_UI_"), //$NON-NLS-1$ = "AuthConstraint"
						WebAppEditResourceHandler.getString("The_authConstraint_propert_UI_"), //$NON-NLS-1$ = "The authConstraint property"
						pkg.getSecurityConstraint_AuthConstraint()));

			// This is for the userDataConstraint feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("UserDataConstraint_UI_"), //$NON-NLS-1$ = "UserDataConstraint"
						WebAppEditResourceHandler.getString("The_userDataConstraint_pro_UI_"), //$NON-NLS-1$ = "The userDataConstraint property"
						pkg.getSecurityConstraint_UserDataConstraint()));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Display Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDisplayNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_SecurityConstraint_displayName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_SecurityConstraint_displayName_feature", "_UI_SecurityConstraint_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getSecurityConstraint_DisplayName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	public String getText(Object object) {
		String displayName = ((SecurityConstraint) object).getDisplayName();
		if (displayName == null) {
			List displayNameList = ((SecurityConstraint) object).getDisplayNames();
			if (displayNameList != null && displayNameList.size() > 0)
				displayName = ((DisplayName) displayNameList.get(0)).getValue();
		}
		if (displayName == null)
			displayName = ""; //$NON-NLS-1$
		return " " + displayName; //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(SecurityConstraint.class)) {
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAME :
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS :
			case WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT :
			case WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT :
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES : {
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

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getSecurityConstraint_WebResourceCollections(), WebapplicationFactory.eINSTANCE.createWebResourceCollection()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getSecurityConstraint_AuthConstraint(), WebapplicationFactory.eINSTANCE.createAuthConstraint()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getSecurityConstraint_UserDataConstraint(), WebapplicationFactory.eINSTANCE.createUserDataConstraint()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getSecurityConstraint_DisplayNames(), CommonFactory.eINSTANCE.createDisplayName()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getSecurityConstraint_DisplayNames(), WscommonFactory.eINSTANCE.createDisplayNameType()));
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