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
package org.eclipse.jst.j2ee.internal.webservice.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.internal.webservice.adapter.AdapterViewerItem;
import org.eclipse.jst.j2ee.webservice.plugin.WebServicePlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;

/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class PortComponentItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public PortComponentItemProvider(AdapterFactory adapterFactory) {
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

			addPortComponentNamePropertyDescriptor(object);
			addServiceEndpointInterfacePropertyDescriptor(object);
			addSmallIconPropertyDescriptor(object);
			addLargeIconPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addDisplayNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	/*
	 * pgm protected void addIdPropertyDescriptor(Object object) { itemPropertyDescriptors.add (new
	 * ItemPropertyDescriptor (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
	 * getString("_UI_PortComponent_id_feature"), getString("_UI_PropertyDescriptor_description",
	 * "_UI_PortComponent_id_feature", "_UI_PortComponent_type"),
	 * WsddPackage.eINSTANCE.getPortComponent_Id(), true,
	 * ItemPropertyDescriptor.GENERIC_VALUE_IMAGE)); }
	 */

	/**
	 * This adds a property descriptor for the Port Component Name feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPortComponentNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_PortComponent_portComponentName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_PortComponent_portComponentName_feature", "_UI_PortComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getPortComponent_PortComponentName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Service Endpoint Interface feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addServiceEndpointInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_PortComponent_serviceEndpointInterface_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_PortComponent_serviceEndpointInterface_feature", "_UI_PortComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getPortComponent_ServiceEndpointInterface(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Small Icon feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSmallIconPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_PortComponent_smallIcon_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_PortComponent_smallIcon_feature", "_UI_PortComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getPortComponent_SmallIcon(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Large Icon feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLargeIconPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_PortComponent_largeIcon_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_PortComponent_largeIcon_feature", "_UI_PortComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getPortComponent_LargeIcon(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_PortComponent_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_PortComponent_description_feature", "_UI_PortComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getPortComponent_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Display Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDisplayNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_PortComponent_displayName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_PortComponent_displayName_feature", "_UI_PortComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getPortComponent_DisplayName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(WsddPackage.eINSTANCE.getPortComponent_WsdlPort());
			childrenReferences.add(WsddPackage.eINSTANCE.getPortComponent_ServiceImplBean());
			childrenReferences.add(WsddPackage.eINSTANCE.getPortComponent_Handlers());
			childrenReferences.add(WsddPackage.eINSTANCE.getPortComponent_DescriptionType());
			childrenReferences.add(WsddPackage.eINSTANCE.getPortComponent_DisplayNameType());
			childrenReferences.add(WsddPackage.eINSTANCE.getPortComponent_IconType());
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
	 * This returns PortComponent.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	/*
	 * public Object getImage(Object object) { return
	 * getResourceLocator().getImage("full/obj16/PortComponent"); }
	 */
	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	/*
	 * public String getText(Object object) { String label =
	 * ((PortComponent)object).getPortComponentName(); return label == null || label.length() == 0 ?
	 * getString("_UI_PortComponent_type") : getString("_UI_PortComponent_type") + " " + label; }
	 */

	/**
	 * This returns PortComponent.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @non-generated
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("icons/obj16/portcomponent.gif"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @non-generated
	 */
	public String getText(Object object) {
		String label = ((PortComponent) object).getPortComponentName();
		return label == null || label.length() == 0 ? getString("%_UI_PortComponent_type") : label; //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(PortComponent.class)) {
			case WsddPackage.PORT_COMPONENT__PORT_COMPONENT_NAME :
			case WsddPackage.PORT_COMPONENT__SERVICE_ENDPOINT_INTERFACE :
			case WsddPackage.PORT_COMPONENT__SMALL_ICON :
			case WsddPackage.PORT_COMPONENT__LARGE_ICON :
			case WsddPackage.PORT_COMPONENT__DESCRIPTION :
			case WsddPackage.PORT_COMPONENT__DISPLAY_NAME :
			case WsddPackage.PORT_COMPONENT__WSDL_PORT :
			case WsddPackage.PORT_COMPONENT__SERVICE_IMPL_BEAN :
			case WsddPackage.PORT_COMPONENT__HANDLERS :
			case WsddPackage.PORT_COMPONENT__DESCRIPTION_TYPE :
			case WsddPackage.PORT_COMPONENT__DISPLAY_NAME_TYPE :
			case WsddPackage.PORT_COMPONENT__ICON_TYPE : {
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

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getPortComponent_WsdlPort(), WsddFactory.eINSTANCE.createWSDLPort()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getPortComponent_ServiceImplBean(), WsddFactory.eINSTANCE.createServiceImplBean()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getPortComponent_Handlers(), WsddFactory.eINSTANCE.createHandler()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getPortComponent_DescriptionType(), WscommonFactory.eINSTANCE.createDescriptionType()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getPortComponent_DisplayNameType(), WscommonFactory.eINSTANCE.createDisplayNameType()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getPortComponent_IconType(), CommonFactory.eINSTANCE.createIconType()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 */
	public ResourceLocator getResourceLocator() {
		return WebServicePlugin.getInstance();
	}

	/**
	 * This implements
	 * {@link ITreeItemContentProvider#getChildren ITreeItemContentProvider.getChildren}by calling
	 * {@link #getChildrenReferences getChildrenReferences}and using those to collect the children.
	 */
	public Collection getChildren(Object object) {
		if (object instanceof EList)
			return getChildrenFromEList((EList) object);
		else if (object instanceof AdapterViewerItem)
			return getChildrenFromAdapterViewerItem((AdapterViewerItem) object);
		else
			return super.getChildren(object);
	}

	protected Collection getChildrenFromEList(EList eList) {
		Collection result = new ArrayList();
		for (Iterator it = eList.iterator(); it.hasNext();)
			result.addAll(super.getChildren(it.next()));
		return result;
	}

	protected Collection getChildrenFromAdapterViewerItem(AdapterViewerItem adapterViewerItem) {
		Collection result = new ArrayList();
		EList eObjectList = adapterViewerItem.getEObjectList();
		EStructuralFeature feature = adapterViewerItem.getEStructuralFeature();
		for (Iterator it = eObjectList.iterator(); it.hasNext();) {
			EObject eObject = (EObject) it.next();
			Object object = eObject.eGet(feature);
			if (object != null) {
				if (object instanceof List)
					result.addAll((List) object);
				else
					result.add(object);
			}
		}
		return result;
	}
}