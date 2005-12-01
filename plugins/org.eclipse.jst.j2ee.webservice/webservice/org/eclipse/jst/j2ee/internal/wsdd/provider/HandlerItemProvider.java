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
package org.eclipse.jst.j2ee.internal.wsdd.provider;


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
import org.eclipse.jst.j2ee.common.internal.provider.CompatibilityDescriptionGroupItemProvider;
import org.eclipse.jst.j2ee.internal.webservice.adapter.AdapterViewerItem;
import org.eclipse.jst.j2ee.internal.webservice.plugin.WebServicePlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;

/*
 * This class should be merged with HandlerItemProvider in org.eclipse.jst.j2ee.internal.internal.internal.webservice.provider
 * package and all references to this class need to be replaced with HandlerItemProvider in 
 * org.eclipse.jst.j2ee.internal.internal.internal.webservice.provider package.
 */

/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.wsdd.Handler}object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class HandlerItemProvider extends CompatibilityDescriptionGroupItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public HandlerItemProvider(AdapterFactory adapterFactory) {
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

			addHandlerNamePropertyDescriptor(object);
			addHandlerClassPropertyDescriptor(object);
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
	 * getString("_UI_Handler_id_feature"), getString("_UI_PropertyDescriptor_description",
	 * "_UI_Handler_id_feature", "_UI_Handler_type"), WsddPackage.eINSTANCE.getHandler_Id(), true,
	 * ItemPropertyDescriptor.GENERIC_VALUE_IMAGE)); }
	 */

	/**
	 * This adds a property descriptor for the Handler Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addHandlerNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Handler_handlerName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Handler_handlerName_feature", "_UI_Handler_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getHandler_HandlerName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Handler Class feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addHandlerClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Handler_handlerClass_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Handler_handlerClass_feature", "_UI_Handler_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WsddPackage.eINSTANCE.getHandler_HandlerClass(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(WsddPackage.eINSTANCE.getHandler_InitParams());
			childrenReferences.add(WsddPackage.eINSTANCE.getHandler_SoapHeaders());
			childrenReferences.add(WsddPackage.eINSTANCE.getHandler_SoapRoles());
			childrenReferences.add(WsddPackage.eINSTANCE.getHandler_HandlerClass());
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
	 * This returns Handler.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	/*
	 * public Object getImage(Object object) { return
	 * getResourceLocator().getImage("full/obj16/Handler"); }
	 */
	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	/*
	 * public String getText(Object object) { String label = ((Handler)object).getDisplayName();
	 * return label == null || label.length() == 0 ? getString("_UI_Handler_type") :
	 * getString("_UI_Handler_type") + " " + label; }
	 */
	/**
	 * This returns Handler.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @non-generated
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("icons/obj16/handler.gif"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @non-generated
	 */
	public String getText(Object object) {
		String label = ((Handler) object).getHandlerName();
		return label == null || label.length() == 0 ? getString("%_UI_Handler_type") : label; //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(Handler.class)) {
			case WsddPackage.HANDLER__HANDLER_NAME :
			case WsddPackage.HANDLER__HANDLER_CLASS :
			case WsddPackage.HANDLER__INIT_PARAMS :
			case WsddPackage.HANDLER__SOAP_HEADERS :
			case WsddPackage.HANDLER__SOAP_ROLES : {
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

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getHandler_InitParams(), WscommonFactory.eINSTANCE.createInitParam()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getHandler_SoapHeaders(), WscommonFactory.eINSTANCE.createSOAPHeader()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getHandler_SoapRoles(), WscommonFactory.eINSTANCE.createSOAPRole()));
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
