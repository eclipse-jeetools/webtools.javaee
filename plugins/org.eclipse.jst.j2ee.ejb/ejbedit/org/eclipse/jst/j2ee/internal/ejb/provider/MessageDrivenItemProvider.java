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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;



/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.MessageDriven}object.
 */
public class MessageDrivenItemProvider extends EnterpriseBeanItemProvider implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public MessageDrivenItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This creates a new child for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public Object createChild(Object object) {
		// TODO: check that this is what you want.
		CMPAttribute child = EjbFactory.eINSTANCE.createCMPAttribute();

		// TODO: initialize child here...

		return child;
	}

	/**
	 * This specifies how to implement {@link #getChildren}and {@link AddCommand}and
	 * {@link RemoveCommand}support in {@link #createCommand}.
	 */
	public Collection getChildrenReferences(Object object) {
		if (childrenReferences == null) {
			childrenReferences = (List) super.getChildrenReferences(object);
			childrenReferences.add(EjbPackage.eINSTANCE.getMessageDriven_Destination());
			childrenReferences.add(EjbPackage.eINSTANCE.getMessageDriven_ActivationConfig());
			childrenReferences.add(EjbPackage.eINSTANCE.getActivationConfig_ConfigProperties());

		}
		return childrenReferences;
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
	 * This returns the image for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public Object getCreateChildImage(Object object) {
		EObject refObject = (EObject) object;
		return J2EEPlugin.getPlugin().getImage(refObject.eClass().getName() + "CreateCMPAttribute"); //$NON-NLS-1$
	}

	/**
	 * This returns the label for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return EJBProviderLibrariesResourceHandler.getString("Create_CMPAttribute_2"); //$NON-NLS-1$
	}

	/**
	 * This returns the help text for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return EJBProviderLibrariesResourceHandler.getString("Create_a_child_of_type_CMPAttribute_for_the_selected__3") + refObject.eClass().getName() + EJBProviderLibrariesResourceHandler.getString("_4"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This returns MessageDriven.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("message_bean_obj"); //$NON-NLS-1$
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			EjbPackage pkg = EjbPackage.eINSTANCE;

			// This is for the transactionType feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Transaction_Type_6"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_transaction_type_property_of_the_message_driven_7"), //$NON-NLS-1$
						pkg.getMessageDriven_TransactionType(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));

			// This is for the messageSelector feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Message_Selector_8"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_message_selector_property_of_the_message_driven_9"), //$NON-NLS-1$
						pkg.getMessageDriven_MessageSelector(), true, ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

			// This is for the acknowledgeMode feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Acknowledge_Mode_10"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_acknowledge_mode_property_of_the_message_driven_11"), //$NON-NLS-1$
						pkg.getMessageDriven_AcknowledgeMode(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));

			// This is for the destination feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Destination_12"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_destination_of_the_message_driven_13"), //$NON-NLS-1$
						pkg.getMessageDriven_Destination(), false));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Transaction Type feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTransactionTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MessageDriven_transactionType_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MessageDriven_transactionType_feature", "_UI_MessageDriven_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMessageDriven_TransactionType(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Message Selector feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMessageSelectorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MessageDriven_messageSelector_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MessageDriven_messageSelector_feature", "_UI_MessageDriven_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMessageDriven_MessageSelector(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Acknowledge Mode feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addAcknowledgeModePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MessageDriven_acknowledgeMode_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MessageDriven_acknowledgeMode_feature", "_UI_MessageDriven_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMessageDriven_AcknowledgeMode(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Message Destination feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMessageDestinationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MessageDriven_messageDestination_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MessageDriven_messageDestination_feature", "_UI_MessageDriven_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMessageDriven_MessageDestination(), true));
	}

	/**
	 * This adds a property descriptor for the Link feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLinkPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MessageDriven_link_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MessageDriven_link_feature", "_UI_MessageDriven_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMessageDriven_Link(), true));
	}

	/**
	 * This adds a property descriptor for the Messaging Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMessagingTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MessageDriven_messagingType_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MessageDriven_messagingType_feature", "_UI_MessageDriven_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMessageDriven_MessagingType(), true));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> Don't remove this
	 * method or it will be regenerated. <!-- end-user-doc -->
	 *  
	 */
	public String getText(Object object) {
		return super.getText(object);
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(MessageDriven.class)) {
			case EjbPackage.MESSAGE_DRIVEN__TRANSACTION_TYPE :
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_SELECTOR :
			case EjbPackage.MESSAGE_DRIVEN__ACKNOWLEDGE_MODE :
			case EjbPackage.MESSAGE_DRIVEN__DESTINATION :
			case EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG : {
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

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getMessageDriven_Destination(), EjbFactory.eINSTANCE.createMessageDrivenDestination()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getMessageDriven_ActivationConfig(), EjbFactory.eINSTANCE.createActivationConfig()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getCreateChildText(Object owner, Object feature, Object child, Collection selection) {
		boolean qualify = feature == CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbRefs() || feature == CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbLocalRefs();
		return getString(qualify ? "_UI_CreateChild_text2" : "_UI_CreateChild_text", //$NON-NLS-1$ //$NON-NLS-2$
					new Object[]{getTypeText(child), getFeatureText(feature), getTypeText(owner)});
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