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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.internal.webservice.adapter.AdapterViewerItem;
import org.eclipse.jst.j2ee.internal.webservice.plugin.WebServicePlugin;
import org.eclipse.jst.j2ee.webservice.wsdd.ServiceImplBean;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.wsdd.ServiceImplBean}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ServiceImplBeanItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ServiceImplBeanItemProvider(AdapterFactory adapterFactory) {
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
	 * getString("_UI_ServiceImplBean_id_feature"), getString("_UI_PropertyDescriptor_description",
	 * "_UI_ServiceImplBean_id_feature", "_UI_ServiceImplBean_type"),
	 * WsddPackage.eINSTANCE.getServiceImplBean_Id(), true,
	 * ItemPropertyDescriptor.GENERIC_VALUE_IMAGE)); }
	 */

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
			childrenReferences.add(WsddPackage.eINSTANCE.getServiceImplBean_EEJBLink());
			childrenReferences.add(WsddPackage.eINSTANCE.getServiceImplBean_EServletLink());
			childrenReferences.add(WsddPackage.eINSTANCE.getServiceImplBean_BeanLink());
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
	 * This returns ServiceImplBean.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("icons/full/obj16/srvce_elem_obj.gif"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	/*
	 * public String getText(Object object) { String label = ((ServiceImplBean)object).getId();
	 * return label == null || label.length() == 0 ? getString("_UI_ServiceImplBean_type") :
	 * getString("_UI_ServiceImplBean_type") + " " + label; }
	 */

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @non-generated
	 */
	public String getText(Object object) {
		ServiceImplBean serviceImplBean = (ServiceImplBean) object;
		String ejbLink = serviceImplBean.getEEJBLink().getEjbLink();
		if (ejbLink != null && ejbLink.length() > 0)
			return ejbLink;
		String beanLink = serviceImplBean.getBeanLink().toString();
		if (beanLink != null && beanLink.length() > 0)
			return beanLink;
		String servletLink = serviceImplBean.getEServletLink().getServletLink();
		if (servletLink != null && servletLink.length() > 0)
			return servletLink;
		return getString("%_UI_ServiceImplBean_type"); //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(ServiceImplBean.class)) {
			case WsddPackage.SERVICE_IMPL_BEAN__EEJB_LINK :
			case WsddPackage.SERVICE_IMPL_BEAN__ESERVLET_LINK :
			case WsddPackage.SERVICE_IMPL_BEAN__BEAN_LINK : {
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

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getServiceImplBean_EEJBLink(), WsddFactory.eINSTANCE.createEJBLink()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getServiceImplBean_EServletLink(), WsddFactory.eINSTANCE.createServletLink()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getServiceImplBean_BeanLink(), WsddFactory.eINSTANCE.createBeanLink()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getServiceImplBean_BeanLink(), WsddFactory.eINSTANCE.createServletLink()));

		newChildDescriptors.add(createChildParameter(WsddPackage.eINSTANCE.getServiceImplBean_BeanLink(), WsddFactory.eINSTANCE.createEJBLink()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getCreateChildText(Object owner, Object feature, Object child, Collection selection) {
		boolean qualify = feature == WsddPackage.eINSTANCE.getServiceImplBean_EServletLink() || feature == WsddPackage.eINSTANCE.getServiceImplBean_BeanLink() || feature == WsddPackage.eINSTANCE.getServiceImplBean_EEJBLink();
		return getString(qualify ? "_UI_CreateChild_text2" : "_UI_CreateChild_text", //$NON-NLS-1$ //$NON-NLS-2$
					new Object[]{getTypeText(child), getFeatureText(feature), getTypeText(owner)});
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
