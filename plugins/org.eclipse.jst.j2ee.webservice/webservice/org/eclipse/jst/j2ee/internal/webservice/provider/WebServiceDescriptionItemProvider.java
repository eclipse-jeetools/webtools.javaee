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
import org.eclipse.jst.j2ee.internal.webservice.plugin.WebServicePlugin;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.WsddPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddFactory;

/**
 * This is the item provider adpater for a
 * {@link org.eclipse.jst.j2ee.internal.internal.webservice.wsdd.WebServiceDescription}object. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class WebServiceDescriptionItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public WebServiceDescriptionItemProvider(AdapterFactory adapterFactory) {
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

			addJaxrpcMappingFilePropertyDescriptor(object);
			addWebServiceDescriptionNamePropertyDescriptor(object);
			addWsdlFilePropertyDescriptor(object);
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
	 * getString("_UI_WebServiceDescription_id_feature"),
	 * getString("_UI_PropertyDescriptor_description", "_UI_WebServiceDescription_id_feature",
	 * "_UI_WebServiceDescription_type"), WsddPackage.eINSTANCE.getWebServiceDescription_Id(), true,
	 * ItemPropertyDescriptor.GENERIC_VALUE_IMAGE)); }
	 */

	/**
	 * This adds a property descriptor for the Jaxrpc Mapping File feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addJaxrpcMappingFilePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebServiceDescription_jaxrpcMappingFile_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebServiceDescription_jaxrpcMappingFile_feature", "_UI_WebServiceDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getWebServiceDescription_JaxrpcMappingFile(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Web Service Description Name feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addWebServiceDescriptionNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebServiceDescription_webServiceDescriptionName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebServiceDescription_webServiceDescriptionName_feature", "_UI_WebServiceDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getWebServiceDescription_WebServiceDescriptionName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Wsdl File feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addWsdlFilePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebServiceDescription_wsdlFile_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebServiceDescription_wsdlFile_feature", "_UI_WebServiceDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getWebServiceDescription_WsdlFile(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Small Icon feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSmallIconPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebServiceDescription_smallIcon_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebServiceDescription_smallIcon_feature", "_UI_WebServiceDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getWebServiceDescription_SmallIcon(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Large Icon feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLargeIconPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebServiceDescription_largeIcon_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebServiceDescription_largeIcon_feature", "_UI_WebServiceDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getWebServiceDescription_LargeIcon(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebServiceDescription_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebServiceDescription_description_feature", "_UI_WebServiceDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getWebServiceDescription_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Display Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDisplayNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebServiceDescription_displayName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebServiceDescription_displayName_feature", "_UI_WebServiceDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getWebServiceDescription_DisplayName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(WsddPackage.eINSTANCE.getWebServiceDescription_PortComponents());
			childrenReferences.add(WsddPackage.eINSTANCE.getWebServiceDescription_DescriptionType());
			childrenReferences.add(WsddPackage.eINSTANCE.getWebServiceDescription_DisplayNameType());
			childrenReferences.add(WsddPackage.eINSTANCE.getWebServiceDescription_IconType());
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
	 * This returns WebServiceDescription.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	/*
	 * public Object getImage(Object object) { return
	 * getResourceLocator().getImage("full/obj16/WebServiceDescription"); }
	 */

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	/*
	 * public String getText(Object object) { String label =
	 * ((WebServiceDescription)object).getWebServiceDescriptionName(); return label == null ||
	 * label.length() == 0 ? getString("_UI_WebServiceDescription_type") :
	 * getString("_UI_WebServiceDescription_type") + " " + label; }
	 */

	/**
	 * This returns WebServiceDescription.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *  
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("icons/obj16/webservicedesc.gif"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @non-generated
	 */
	public String getText(Object object) {
		String label = ((WebServiceDescription) object).getWebServiceDescriptionName();
		return label == null || label.length() == 0 ? getString("%_UI_WebServiceDescription_type") : label; //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(WebServiceDescription.class)) {
			case WsddPackage.WEB_SERVICE_DESCRIPTION__JAXRPC_MAPPING_FILE :
			case WsddPackage.WEB_SERVICE_DESCRIPTION__WEB_SERVICE_DESCRIPTION_NAME :
			case WsddPackage.WEB_SERVICE_DESCRIPTION__WSDL_FILE :
			case WsddPackage.WEB_SERVICE_DESCRIPTION__SMALL_ICON :
			case WsddPackage.WEB_SERVICE_DESCRIPTION__LARGE_ICON :
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION :
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME :
			case WsddPackage.WEB_SERVICE_DESCRIPTION__PORT_COMPONENTS :
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE :
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE :
			case WsddPackage.WEB_SERVICE_DESCRIPTION__ICON_TYPE : {
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

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getWebServiceDescription_PortComponents(), WsddFactory.eINSTANCE.createPortComponent()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getWebServiceDescription_DescriptionType(), WscommonFactory.eINSTANCE.createDescriptionType()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getWebServiceDescription_DisplayNameType(), WscommonFactory.eINSTANCE.createDisplayNameType()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getWebServiceDescription_IconType(), CommonFactory.eINSTANCE.createIconType()));
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