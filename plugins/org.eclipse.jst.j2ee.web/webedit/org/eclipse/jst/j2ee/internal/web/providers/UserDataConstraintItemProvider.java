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
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.webapplication.UserDataConstraint;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;

/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.UserDataConstraint}
 * object.
 */
public class UserDataConstraintItemProvider extends WebapplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public UserDataConstraintItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns user_data_constraint.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("user_data_constraint"); //$NON-NLS-1$
	}

	/**
	 * This returns the secConstraint of the UserDataConstraint.
	 */
	public Object getParent(Object object) {
		return ((UserDataConstraint) object).getSecConstraint();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;

			// This is for the description feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("Description_UI__UI_"), //$NON-NLS-1$ = "Description"
						WebAppEditResourceHandler.getString("The_description_property_UI_"), //$NON-NLS-1$ = "The description property"
						pkg.getUserDataConstraint_Description()));

			// This is for the transportGuarantee feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("TransportGuarantee_UI_"), //$NON-NLS-1$ = "TransportGuarantee"
						WebAppEditResourceHandler.getString("The_transportGuarantee_pro_UI_"), //$NON-NLS-1$ = "The transportGuarantee property"
						pkg.getUserDataConstraint_TransportGuarantee()));

			// This is for the secConstraint feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("SecConstraint_UI_"), //$NON-NLS-1$ = "SecConstraint"
						WebAppEditResourceHandler.getString("The_secConstraint_property_UI_"), //$NON-NLS-1$ = "The secConstraint property"
						pkg.getUserDataConstraint_SecConstraint()));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_UserDataConstraint_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_UserDataConstraint_description_feature", "_UI_UserDataConstraint_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getUserDataConstraint_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Transport Guarantee feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTransportGuaranteePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_UserDataConstraint_transportGuarantee_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_UserDataConstraint_transportGuarantee_feature", "_UI_UserDataConstraint_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getUserDataConstraint_TransportGuarantee(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This specifies how to implement {@link #getChildren}and
	 * {@link org.eclipse.emf.edit.command.AddCommand}and
	 * {@link org.eclipse.emf.edit.command.RemoveCommand}support in {@link #createCommand}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Collection getChildrenReferences(Object object) {
		if (childrenReferences == null) {
			super.getChildrenReferences(object);
			childrenReferences.add(WebapplicationPackage.eINSTANCE.getUserDataConstraint_Descriptions());
		}
		return childrenReferences;
	}


	public String getText(Object object) {
		return WebAppEditResourceHandler.getString("34concat_UI_", (new Object[]{((UserDataConstraint) object).getTransportGuarantee().getName()})); //$NON-NLS-1$ = "UserDataConstraint {0}"
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(UserDataConstraint.class)) {
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTION :
			case WebapplicationPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE :
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS : {
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

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getUserDataConstraint_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getUserDataConstraint_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
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
