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
package org.eclipse.jst.j2ee.internal.jca.providers;



import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.ResourceAdapter;
import org.eclipse.jst.j2ee.jca.internal.plugin.JCAResourceHandler;
import org.eclipse.jst.j2ee.jca.internal.plugin.JcaPlugin;

/**
 * This is the item provider adpater for a ResourceAdapter object.
 */
public class ResourceAdapterItemProvider extends JcaItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * 
	 * @param AdapterFactory
	 *            adapterFactory - The adapt factory
	 */
	public ResourceAdapterItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}// ResourceAdapterItemProvider

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addManagedConnectionFactoryClassPropertyDescriptor(object);
			addConnectionFactoryInterfacePropertyDescriptor(object);
			addConnectionFactoryImplClassPropertyDescriptor(object);
			addConnectionInterfacePropertyDescriptor(object);
			addConnectionImplClassPropertyDescriptor(object);
			addTransactionSupportPropertyDescriptor(object);
			addReauthenticationSupportPropertyDescriptor(object);
			addResourceAdapterClassPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Managed Connection Factory Class feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addManagedConnectionFactoryClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceAdapter_managedConnectionFactoryClass_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceAdapter_managedConnectionFactoryClass_feature", "_UI_ResourceAdapter_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getResourceAdapter_ManagedConnectionFactoryClass(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Connection Factory Interface feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addConnectionFactoryInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceAdapter_connectionFactoryInterface_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceAdapter_connectionFactoryInterface_feature", "_UI_ResourceAdapter_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getResourceAdapter_ConnectionFactoryInterface(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Connection Factory Impl Class feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addConnectionFactoryImplClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceAdapter_connectionFactoryImplClass_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceAdapter_connectionFactoryImplClass_feature", "_UI_ResourceAdapter_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getResourceAdapter_ConnectionFactoryImplClass(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Connection Interface feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addConnectionInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceAdapter_connectionInterface_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceAdapter_connectionInterface_feature", "_UI_ResourceAdapter_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getResourceAdapter_ConnectionInterface(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Connection Impl Class feature. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addConnectionImplClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceAdapter_connectionImplClass_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceAdapter_connectionImplClass_feature", "_UI_ResourceAdapter_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getResourceAdapter_ConnectionImplClass(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Transaction Support feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTransactionSupportPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceAdapter_transactionSupport_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceAdapter_transactionSupport_feature", "_UI_ResourceAdapter_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getResourceAdapter_TransactionSupport(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Reauthentication Support feature. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addReauthenticationSupportPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceAdapter_reauthenticationSupport_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceAdapter_reauthenticationSupport_feature", "_UI_ResourceAdapter_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getResourceAdapter_ReauthenticationSupport(), true, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Resource Adapter Class feature. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addResourceAdapterClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceAdapter_resourceAdapterClass_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceAdapter_resourceAdapterClass_feature", "_UI_ResourceAdapter_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getResourceAdapter_ResourceAdapterClass(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * 
	 * @param Object
	 *            object - The adapted class.
	 * @return List
	 */
	/*
	 * public List getPropertyDescriptors(Object object) { if (itemPropertyDescriptors == null) {
	 * super.getPropertyDescriptors(object);
	 * 
	 * ResourceAdapter f_resourceAdapter = ((ResourceAdapter)object); JcaPackage pkg =
	 * JcaPackage.eINSTANCE;; // This is for the managedConnectionFactoryClass feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Managed Connection
	 * Factory Class", "The managed connection factory class property of the resource adapter",
	 * pkg.getResourceAdapter_ManagedConnectionFactoryClass(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the connectionFactoryInterface
	 * feature. // itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Connection Factory
	 * Interface", "The connection factory interface property of the resource adapter",
	 * pkg.getResourceAdapter_ConnectionFactoryInterface(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the connectionFactoryImplClass
	 * feature. // itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Connection Factory
	 * Impl Class", "The connection factory impl class property of the resource adapter",
	 * pkg.getResourceAdapter_ConnectionFactoryImplClass(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the connectionInterface feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Connection Interface",
	 * "The connection interface property of the resource adapter",
	 * pkg.getResourceAdapter_ConnectionInterface(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the connectionImplClass feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Connection Impl
	 * Class", "The connection impl class property of the resource adapter",
	 * pkg.getResourceAdapter_ConnectionImplClass(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the transactionSupport feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Transaction Support",
	 * "The transaction support property of the resource adapter",
	 * pkg.getResourceAdapter_TransactionSupport(), true,
	 * ItemPropertyDescriptor.GENERIC_VALUE_IMAGE)); // This is for the reauthenticationSupport
	 * feature. // itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Reauthentication
	 * Support", "The reauthentication support property of the resource adapter",
	 * pkg.getResourceAdapter_ReauthenticationSupport(), true,
	 * ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	 * 
	 * }// if return itemPropertyDescriptors; }// getPropertyDescriptors
	 */
	/**
	 * This specifies how to implement AddCommand and RemoveCommand support in createCommand.
	 * 
	 * @param Object
	 *            object - The adapt class.
	 * @return Collection
	 */
	public Collection getChildrenReferences(Object object) {
		if (childrenReferences == null) {
			super.getChildrenReferences(object);
			int version = J2EEVersionConstants.JCA_1_0_ID;
			if (object instanceof ResourceAdapter)
				version = ((Connector) ((EObject) object).eContainer()).getVersionID();

			switch (version) {
				case J2EEVersionConstants.JCA_1_0_ID :
					childrenReferences.add(JcaPackage.eINSTANCE.getResourceAdapter_OutboundResourceAdapter());
					childrenReferences.add(JcaPackage.eINSTANCE.getResourceAdapter_SecurityPermissions());
					childrenReferences.add(JcaPackage.eINSTANCE.getResourceAdapter_AuthenticationMechanisms());
					childrenReferences.add(JcaPackage.eINSTANCE.getResourceAdapter_ConfigProperties());
					break;
				case J2EEVersionConstants.JCA_1_5_ID :
					childrenReferences.add(JcaPackage.eINSTANCE.getResourceAdapter_OutboundResourceAdapter());
					childrenReferences.add(JcaPackage.eINSTANCE.getResourceAdapter_InboundResourceAdapter());
					childrenReferences.add(JcaPackage.eINSTANCE.getResourceAdapter_AdminObjects());
					childrenReferences.add(JcaPackage.eINSTANCE.getResourceAdapter_SecurityPermissions());
					childrenReferences.add(JcaPackage.eINSTANCE.getResourceAdapter_AuthenticationMechanisms());
					childrenReferences.add(JcaPackage.eINSTANCE.getResourceAdapter_ConfigProperties());
					break;
			}

		}// if
		return childrenReferences;
	}// getChildrenReferences

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
	 * This returns the parent of the ResourceAdapter.
	 * 
	 * @param Object
	 *            object - The adapt class.
	 * @return Object
	 */
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}// getParent

	/**
	 * This returns ResourceAdapter.gif.
	 * 
	 * @param Object
	 *            object - The adapt class.
	 * @return Object
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("full/obj16/resource_adapter_obj"); //$NON-NLS-1$
	}// getImage

	/**
	 * Get the display name.
	 * 
	 * @param Object
	 *            object - The object to get the name for.
	 * @return String
	 */
	public String getText(Object object) {
		//		ResourceAdapter resourceAdapter = ((ResourceAdapter) object);
		//return "ResourceAdapter: " + resourceAdapter.getManagedConnectionFactoryClass();
		// //$NON-NLS-1$
		return JCAResourceHandler.getString("_UI_ResourceAdapter_type"); //$NON-NLS-1$

	}// getText

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(ResourceAdapter.class)) {
			case JcaPackage.RESOURCE_ADAPTER__MANAGED_CONNECTION_FACTORY_CLASS :
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_INTERFACE :
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_IMPL_CLASS :
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_INTERFACE :
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_IMPL_CLASS :
			case JcaPackage.RESOURCE_ADAPTER__TRANSACTION_SUPPORT :
			case JcaPackage.RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT :
			case JcaPackage.RESOURCE_ADAPTER__RESOURCE_ADAPTER_CLASS :
			case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSIONS :
			case JcaPackage.RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS :
			case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTIES :
			case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER :
			case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER :
			case JcaPackage.RESOURCE_ADAPTER__ADMIN_OBJECTS : {
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

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getResourceAdapter_SecurityPermissions(), JcaFactory.eINSTANCE.createSecurityPermission()));

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getResourceAdapter_AuthenticationMechanisms(), JcaFactory.eINSTANCE.createAuthenticationMechanism()));

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getResourceAdapter_ConfigProperties(), JcaFactory.eINSTANCE.createConfigProperty()));

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getResourceAdapter_OutboundResourceAdapter(), JcaFactory.eINSTANCE.createOutboundResourceAdapter()));

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getResourceAdapter_InboundResourceAdapter(), JcaFactory.eINSTANCE.createInboundResourceAdapter()));

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getResourceAdapter_AdminObjects(), JcaFactory.eINSTANCE.createAdminObject()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return JcaPlugin.getDefault();
	}

	/**
	 * This handles notification.
	 * 
	 * @param Notifier
	 *            notifier - Notifies of change.
	 * @param int
	 *            eventType - The event type.
	 * @param EObject
	 *            feature - The reference object.
	 * @param Object
	 *            oldValue - The old object reference.
	 * @param Object
	 *            newValue - The new object reference.
	 * @param int
	 *            index - The current of the object.
	 */
	public void notifyChanged(Notifier notifier, int eventType, EObject feature, Object oldValue, Object newValue, int index) {
		JcaPackage pkg = JcaPackage.eINSTANCE;
		Notification msg = new ENotificationImpl((InternalEObject) notifier, eventType, feature.eContainmentFeature(), oldValue, newValue, index);
		if (feature == pkg.getResourceAdapter_ManagedConnectionFactoryClass() || feature == pkg.getResourceAdapter_ConnectionFactoryInterface() || feature == pkg.getResourceAdapter_ConnectionFactoryImplClass() || feature == pkg.getResourceAdapter_ConnectionInterface() || feature == pkg.getResourceAdapter_ConnectionImplClass() || feature == pkg.getResourceAdapter_TransactionSupport() || feature == pkg.getResourceAdapter_ReauthenticationSupport() || feature == pkg.getResourceAdapter_SecurityPermissions() || feature == pkg.getResourceAdapter_AuthenticationMechanisms() || feature == pkg.getResourceAdapter_ConfigProperties()) {
			fireNotifyChanged(msg);
			return;
		}// if
		super.notifyChanged(msg);
	}// notifyChanged

}// ResourceAdapterItemProvider
