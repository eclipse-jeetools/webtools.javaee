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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.webservice.wscommon.InitParam;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.wscommon.InitParam}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class InitParamItemProvider extends AbstractATKUIItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public InitParamItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			//pgm addIdPropertyDescriptor(object);
			addParamNamePropertyDescriptor(object);
			addParamValuePropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
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
	 * getString("_UI_InitParam_id_feature"), getString("_UI_PropertyDescriptor_description",
	 * "_UI_InitParam_id_feature", "_UI_InitParam_type"),
	 * WscommonPackage.eINSTANCE.getInitParam_Id(), true,
	 * ItemPropertyDescriptor.GENERIC_VALUE_IMAGE)); }
	 */

	/**
	 * This adds a property descriptor for the Param Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	protected void addParamNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("%_UI_InitParam_paramName_feature"), //$NON-NLS-1$
					getString("%_UI_InitParam_paramName_feature_desc"), //$NON-NLS-1$
					WscommonPackage.eINSTANCE.getInitParam_ParamName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Param Value feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	protected void addParamValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("%_UI_InitParam_paramValue_feature"), //$NON-NLS-1$
					getString("%_UI_InitParam_paramValue_feature_desc"), //$NON-NLS-1$
					WscommonPackage.eINSTANCE.getInitParam_ParamValue(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("%_UI_InitParam_description_feature"), //$NON-NLS-1$
					getString("%_UI_InitParam_description_feature_desc"), //$NON-NLS-1$ 
					WscommonPackage.eINSTANCE.getInitParam_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(WscommonPackage.eINSTANCE.getInitParam_DescriptionTypes());
		}
		return childrenReferences;
	}


	/**
	 * This returns InitParam.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("icons/full/obj16/initializ_parameter.gif"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	/*
	 * public String getText(Object object) { String label = ((InitParam)object).getParamName();
	 * return label == null || label.length() == 0 ? getString("_UI_InitParam_type") :
	 * getString("_UI_InitParam_type") + " " + label; } /*
	 * 
	 * /** This returns the label text for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc --> @non-generated
	 */
	public String getText(Object object) {
		InitParam initParam = (InitParam) object;
		String name = initParam.getParamName();
		String value = initParam.getParamValue();
		if (name == null || name.length() == 0 || value == null || value.length() == 0)
			return getString("%_UI_InitParam_type"); //$NON-NLS-1$
		StringBuffer label = new StringBuffer();
		label.append(name);
		label.append("="); //$NON-NLS-1$
		label.append(value);
		return label.toString();
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(InitParam.class)) {
			//pgm case WscommonPackage.INIT_PARAM__ID:
			case WscommonPackage.INIT_PARAM__PARAM_NAME :
			case WscommonPackage.INIT_PARAM__PARAM_VALUE :
			case WscommonPackage.INIT_PARAM__DESCRIPTION :
			case WscommonPackage.INIT_PARAM__DESCRIPTION_TYPES : {
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

		newChildDescriptors.add(createChildParameter(WscommonPackage.eINSTANCE.getInitParam_DescriptionTypes(), WscommonFactory.eINSTANCE.createDescriptionType()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	/*
	 * public ResourceLocator getResourceLocator() { return Webservicej2eeEditPlugin.INSTANCE; }
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		InitParam param = (InitParam) object;
		if (param.getParamName() != null)
			children.add(param.getParamName());
		if (param.getParamValue() != null)
			children.add(param.getParamValue());
		if (param.getDescription() != null)
			children.add(param.getDescription());
		return children;

	}
}
