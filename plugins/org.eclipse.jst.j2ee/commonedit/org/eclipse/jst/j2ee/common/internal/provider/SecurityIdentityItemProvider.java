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
package org.eclipse.jst.j2ee.common.internal.provider;



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
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.internal.common.CommonEditResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;


;



/**
 * This is the item provider adpater for a {@link com.ibm.etools.common.SecurityIdentity}object.
 */
public class SecurityIdentityItemProvider extends CommonItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public SecurityIdentityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			CommonPackage pkg = CommonPackage.eINSTANCE;
			;

			// This is for the description feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), "Description", //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_description_property_of_the_security_identity_2"), //$NON-NLS-1$
						pkg.getSecurityIdentity_Description(), true, ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_SecurityIdentity_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_SecurityIdentity_description_feature", "_UI_SecurityIdentity_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					CommonPackage.eINSTANCE.getSecurityIdentity_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(CommonPackage.eINSTANCE.getSecurityIdentity_Descriptions());
		}
		return childrenReferences;
	}


	/**
	 * This returns the parent of the SecurityIdentity.
	 */
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}

	/**
	 * This returns SecurityIdentity.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("environment_entity");//$NON-NLS-1$
	}

	public String getText(Object object) {
		SecurityIdentity securityIdentity = ((SecurityIdentity) object);
		return "SecurityIdentity " + securityIdentity.getDescription(); //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(SecurityIdentity.class)) {
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTION :
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTIONS : {
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

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getSecurityIdentity_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getSecurityIdentity_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	public ResourceLocator getResourceLocator() {
		return J2EEPlugin.getDefault();
	}
}
