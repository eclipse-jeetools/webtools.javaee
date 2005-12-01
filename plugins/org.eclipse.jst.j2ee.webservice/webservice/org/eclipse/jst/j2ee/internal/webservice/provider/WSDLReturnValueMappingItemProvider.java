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
package org.eclipse.jst.j2ee.internal.webservice.provider;


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
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping;


/**
 * This is the item provider adpater for a
 * {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLReturnValueMapping}object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class WSDLReturnValueMappingItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public WSDLReturnValueMappingItemProvider(AdapterFactory adapterFactory) {
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

			addIdPropertyDescriptor(object);
			addMethodReturnValuePropertyDescriptor(object);
			addWsdlMessagePartNamePropertyDescriptor(object);
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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WSDLReturnValueMapping_id_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WSDLReturnValueMapping_id_feature", "_UI_WSDLReturnValueMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getWSDLReturnValueMapping_Id(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Method Return Value feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMethodReturnValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WSDLReturnValueMapping_methodReturnValue_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WSDLReturnValueMapping_methodReturnValue_feature", "_UI_WSDLReturnValueMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getWSDLReturnValueMapping_MethodReturnValue(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Wsdl Message Part Name feature. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addWsdlMessagePartNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WSDLReturnValueMapping_wsdlMessagePartName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WSDLReturnValueMapping_wsdlMessagePartName_feature", "_UI_WSDLReturnValueMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getWSDLReturnValueMapping_WsdlMessagePartName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(JaxrpcmapPackage.eINSTANCE.getWSDLReturnValueMapping_WsdlMessage());
		}
		return childrenReferences;
	}


	/**
	 * This returns WSDLReturnValueMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("full/obj16/WSDLReturnValueMapping"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((WSDLReturnValueMapping) object).getWsdlMessagePartName();
		return label == null || label.length() == 0 ? getString("_UI_WSDLReturnValueMapping_type") : //$NON-NLS-1$
					getString("_UI_WSDLReturnValueMapping_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(WSDLReturnValueMapping.class)) {
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__ID :
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__METHOD_RETURN_VALUE :
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE_PART_NAME :
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE : {
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

		newChildDescriptors.add(createChildParameter(JaxrpcmapPackage.eINSTANCE.getWSDLReturnValueMapping_WsdlMessage(), JaxrpcmapFactory.eINSTANCE.createWSDLMessage()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return Webservicej2eeEditPlugin.INSTANCE;
	}
}
