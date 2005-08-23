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
package org.eclipse.jst.j2ee.internal.web.providers;

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
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.InitParam}object.
 */
public class InitParamItemProvider extends WebapplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public InitParamItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns initializ_parameter.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("initializ_parameter"); //$NON-NLS-1$
	}

	/**
	 * This returns the servlet of the InitParam.
	 */
	public Object getParent(Object object) {
		return ((InitParam) object).eContainer();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;

			// This is for the paramName feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("ParamName_UI_"), //$NON-NLS-1$ = "ParamName"
						WebAppEditResourceHandler.getString("The_paramName_property_UI_"), //$NON-NLS-1$ = "The paramName property"
						pkg.getInitParam_ParamName()));

			// This is for the paramValue feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("ParamValue_UI_"), //$NON-NLS-1$ = "ParamValue"
						WebAppEditResourceHandler.getString("The_paramValue_property_UI_"), //$NON-NLS-1$ = "The paramValue property"
						pkg.getInitParam_ParamValue()));

			// This is for the description feature.
			//
			WebToolingItemPropertyDescriptor propertyDescriptor = new WebToolingItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("Description_UI__UI_"), //$NON-NLS-1$ = "Description"
						WebAppEditResourceHandler.getString("Description_UI__UI_"), //$NON-NLS-1$ = "Description"
						pkg.getInitParam_Description(), WebToolingItemPropertyDescriptor.MULTILINE_TEXT_EDITOR);
			itemPropertyDescriptors.add(propertyDescriptor);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Param Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addParamNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_InitParam_paramName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_InitParam_paramName_feature", "_UI_InitParam_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getInitParam_ParamName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Param Value feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addParamValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_InitParam_paramValue_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_InitParam_paramValue_feature", "_UI_InitParam_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getInitParam_ParamValue(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_InitParam_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_InitParam_description_feature", "_UI_InitParam_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getInitParam_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


	/**
	 * This implements
	 * {@link IItemPropertySource#getPropertyValue IItemPropertySource.getPropertyValue}by
	 * delegating to the descriptor, which is assumed to support the IItemPropertyDescriptor
	 * interface
	 */
	public Object getPropertyValue(Object object, String property) {
		Object ret = null;
		if (getPropertyDescriptor(object, property) instanceof WebToolingItemPropertyDescriptor) {
			ret = ((WebToolingItemPropertyDescriptor) getPropertyDescriptor(object, property)).getPropertyValue(object);
		} else
			ret = super.getPropertyValue(object, property);
		return ret;
	}

	public String getText(Object object) {
		return WebAppEditResourceHandler.getString("18concat_UI_", (new Object[]{((InitParam) object).getParamName(), ((InitParam) object).getParamValue()})); //$NON-NLS-1$ = "{0} = {1}"
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(InitParam.class)) {
			case WebapplicationPackage.INIT_PARAM__PARAM_NAME :
			case WebapplicationPackage.INIT_PARAM__PARAM_VALUE :
			case WebapplicationPackage.INIT_PARAM__DESCRIPTION : {
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

	/**
	 * This always returns false. The base class has already implemented
	 * {@link org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren ItemProviderAdapter.getChildren}
	 * to return the empty enumeration, and this to check that enumeration, but we know there can't
	 * be any children, so this is faster.
	 */
	public boolean hasChildren(Object object) {
		return false;
	}

	/**
	 * This implements {@link IItemPropertySource#isPropertySet IItemPropertySource.isPropertySet}
	 * by delegating to the descriptor, which is assumed to support the IItemPropertyDescriptor
	 * interface
	 */
	public boolean isPropertySet(Object object, String property) {
		boolean ret = false;
		if (getPropertyDescriptor(object, property) instanceof WebToolingItemPropertyDescriptor) {
			ret = ((WebToolingItemPropertyDescriptor) getPropertyDescriptor(object, property)).isPropertySet(object);
		} else
			ret = super.isPropertySet(object, property);
		return ret;
	}

	/**
	 * This implements
	 * {@link IItemPropertySource#setPropertyValue IItemPropertySource.setPropertyValue}by
	 * delegating to the descriptor, which is assumed to support the IItemPropertyDescriptor
	 * interface
	 */
	public void setPropertyValue(Object object, String property, Object value) {

		if (getPropertyDescriptor(object, property) instanceof WebToolingItemPropertyDescriptor) {
			((WebToolingItemPropertyDescriptor) getPropertyDescriptor(object, property)).setPropertyValue(object, value);
		} else
			super.setPropertyValue(object, property, value);

	}
}