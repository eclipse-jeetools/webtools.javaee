/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceInterfaceMapping;


/**
 * This is the item provider adpater for a
 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ServiceInterfaceMapping}object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ServiceInterfaceMappingItemProvider extends InterfaceMappingItemProvider{
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ServiceInterfaceMappingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addIdPropertyDescriptor(object);
			addServiceInterfacePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ServiceInterfaceMapping_id_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ServiceInterfaceMapping_id_feature", "_UI_ServiceInterfaceMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getServiceInterfaceMapping_Id(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Service Interface feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addServiceInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ServiceInterfaceMapping_serviceInterface_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ServiceInterfaceMapping_serviceInterface_feature", "_UI_ServiceInterfaceMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getServiceInterfaceMapping_ServiceInterface(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This specifies how to implement {@link #getChildren}and
	 * { @link org.eclipse.emf.edit.command.AddCommand}and
	 * { @link org.eclipse.emf.edit.command.RemoveCommand}support in {@link #createCommand}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Collection getChildrenReferences(Object object) {
		if (childrenReferences == null) {
			super.getChildrenReferences(object);
			childrenReferences.add(JaxrpcmapPackage.eINSTANCE.getServiceInterfaceMapping_WsdlServiceName());
			childrenReferences.add(JaxrpcmapPackage.eINSTANCE.getServiceInterfaceMapping_PortMappings());
		}
		return childrenReferences;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EReference getChildReference(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildReference(object, child);
	}


	/**
	 * This returns ServiceInterfaceMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return getResourceLocator().getImage("full/obj16/ServiceInterfaceMapping"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ServiceInterfaceMapping) object).getId();
		return label == null || label.length() == 0 ? getString("_UI_ServiceInterfaceMapping_type") : //$NON-NLS-1$
					getString("_UI_ServiceInterfaceMapping_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(ServiceInterfaceMapping.class)) {
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__ID :
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__SERVICE_INTERFACE :
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME :
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__PORT_MAPPINGS : {
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
	@Override
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(JaxrpcmapPackage.eINSTANCE.getServiceInterfaceMapping_WsdlServiceName(), JaxrpcmapFactory.eINSTANCE.createWSDLServiceName()));

		newChildDescriptors.add(createChildParameter(JaxrpcmapPackage.eINSTANCE.getServiceInterfaceMapping_PortMappings(), JaxrpcmapFactory.eINSTANCE.createPortMapping()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return Webservicej2eeEditPlugin.INSTANCE;
	}
}
