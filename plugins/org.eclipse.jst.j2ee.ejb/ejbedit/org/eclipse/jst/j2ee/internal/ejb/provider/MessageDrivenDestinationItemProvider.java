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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.MessageDrivenDestination;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.ejb.MessageDrivenDestination}
 * object.
 */
public class MessageDrivenDestinationItemProvider extends EjbItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public MessageDrivenDestinationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns MessageDrivenDestination.gif.
	 */
	public Object getImage(Object object) {
		String imageString = null;
		//  	MessageDrivenDestination messageDrivenDestination = ((MessageDrivenDestination)object);
		//    if (messageDrivenDestination.getType().getValue() == 0)
		//this will change when the images are finished being designed
		//        imageString = "msgdrivendestination_obj"; //$NON-NLS-1$
		//    else
		//        imageString ="msgdrivendestination_obj"; //$NON-NLS-1$
		imageString = "msgdrivendestination_obj"; //$NON-NLS-1$
		return J2EEPlugin.getPlugin().getImage(imageString); //$NON-NLS-1$
	}

	/**
	 * This returns the parent of the MessageDrivenDestination.
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

			EjbPackage pkg = EjbPackage.eINSTANCE;
			;

			// This is for the type feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Type_2"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_type_property_of_the_message_driven_destination_3"), //$NON-NLS-1$
						pkg.getMessageDrivenDestination_Type(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));

			// This is for the subscriptionDurability feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Subscription_Durability_4"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_subscription_durability_property_of_the_message_driven_destination_5"), //$NON-NLS-1$
						pkg.getMessageDrivenDestination_SubscriptionDurability(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));

			// This is for the bean feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Bean_6"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_bean_of_the_message_driven_destination_7"), //$NON-NLS-1$
						pkg.getMessageDrivenDestination_Bean(), false));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MessageDrivenDestination_type_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MessageDrivenDestination_type_feature", "_UI_MessageDrivenDestination_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMessageDrivenDestination_Type(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Subscription Durability feature. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSubscriptionDurabilityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MessageDrivenDestination_subscriptionDurability_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MessageDrivenDestination_subscriptionDurability_feature", "_UI_MessageDrivenDestination_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMessageDrivenDestination_SubscriptionDurability(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


	public String getText(Object object) {
		MessageDrivenDestination messageDrivenDestination = ((MessageDrivenDestination) object);
		if (messageDrivenDestination.getType().getName() != null)
			return "Destination type: " + messageDrivenDestination.getType().getName(); //$NON-NLS-1$
		return "?>>?"; //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(MessageDrivenDestination.class)) {
			case EjbPackage.MESSAGE_DRIVEN_DESTINATION__TYPE :
			case EjbPackage.MESSAGE_DRIVEN_DESTINATION__SUBSCRIPTION_DURABILITY : {
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