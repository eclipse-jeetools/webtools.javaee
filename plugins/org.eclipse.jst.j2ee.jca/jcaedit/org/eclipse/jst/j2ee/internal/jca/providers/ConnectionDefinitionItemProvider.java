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
package org.eclipse.jst.j2ee.internal.jca.providers;


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
import org.eclipse.jst.j2ee.jca.ConnectionDefinition;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.internal.plugin.JCAResourceHandler;
import org.eclipse.jst.j2ee.jca.internal.plugin.JcaPlugin;

/**
 * This is the item provider adpater for a {@link com.ibm.etools.jca.ConnectionDefinition}object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ConnectionDefinitionItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ConnectionDefinitionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ConnectionDefinition_managedConnectionFactoryClass_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ConnectionDefinition_managedConnectionFactoryClass_feature", "_UI_ConnectionDefinition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getConnectionDefinition_ManagedConnectionFactoryClass(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Connection Factory Interface feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addConnectionFactoryInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ConnectionDefinition_connectionFactoryInterface_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ConnectionDefinition_connectionFactoryInterface_feature", "_UI_ConnectionDefinition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getConnectionDefinition_ConnectionFactoryInterface(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Connection Factory Impl Class feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addConnectionFactoryImplClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ConnectionDefinition_connectionFactoryImplClass_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ConnectionDefinition_connectionFactoryImplClass_feature", "_UI_ConnectionDefinition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getConnectionDefinition_ConnectionFactoryImplClass(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Connection Interface feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addConnectionInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ConnectionDefinition_connectionInterface_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ConnectionDefinition_connectionInterface_feature", "_UI_ConnectionDefinition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getConnectionDefinition_ConnectionInterface(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Connection Impl Class feature. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addConnectionImplClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ConnectionDefinition_connectionImplClass_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ConnectionDefinition_connectionImplClass_feature", "_UI_ConnectionDefinition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getConnectionDefinition_ConnectionImplClass(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(JcaPackage.eINSTANCE.getConnectionDefinition_ConfigProperties());
		}
		return childrenReferences;
	}


	/**
	 * This returns ConnectionDefinition.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("full/obj16/ConnectionDefinition"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */


	public String getText(Object object) {
		String label = ((ConnectionDefinition) object).getManagedConnectionFactoryClass();
		return label == null || label.length() == 0 ? JCAResourceHandler._UI_ConnectionDefinition_type : 
					JCAResourceHandler._UI_ConnectionDefinition_type + " " + label; 
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(ConnectionDefinition.class)) {
			case JcaPackage.CONNECTION_DEFINITION__MANAGED_CONNECTION_FACTORY_CLASS :
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_INTERFACE :
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_IMPL_CLASS :
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_INTERFACE :
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS :
			case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTIES : {
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

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getConnectionDefinition_ConfigProperties(), JcaFactory.eINSTANCE.createConfigProperty()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		//return J2EEPlugin.getDefault();
		return JcaPlugin.getPlugin();
	}
}
