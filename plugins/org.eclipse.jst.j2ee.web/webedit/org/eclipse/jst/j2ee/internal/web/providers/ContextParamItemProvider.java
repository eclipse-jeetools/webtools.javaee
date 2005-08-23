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
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.ContextParam}
 * object.
 */
public class ContextParamItemProvider extends WebapplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public ContextParamItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns initializ_parameter_context.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("initializ_parameter_context");//$NON-NLS-1$
	}

	/**
	 * This returns the webApp of the ContextParam.
	 */
	public Object getParent(Object object) {
		return ((ContextParam) object).getWebApp();
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
						pkg.getContextParam_ParamName()));

			// This is for the paramValue feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("ParamValue_UI_"), //$NON-NLS-1$ = "ParamValue"
						WebAppEditResourceHandler.getString("The_paramValue_property_UI_"), //$NON-NLS-1$ = "The paramValue property"
						pkg.getContextParam_ParamValue()));

			// This is for the description feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("Description_UI__UI_"), //$NON-NLS-1$ = "Description"
						WebAppEditResourceHandler.getString("The_description_property_UI_"), //$NON-NLS-1$ = "The description property"
						pkg.getContextParam_Description()));

			// This is for the webApp feature.
			//
			/*
			 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
			 * WebAppEditResourceHandler.getString("WebApp_UI_"), //$NON-NLS-1$ = "WebApp"
			 * WebAppEditResourceHandler.getString("The_webApp_property_UI_"), //$NON-NLS-1$ = "The
			 * webApp property" pkg.getContextParam_WebApp()));
			 */

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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ContextParam_paramName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ContextParam_paramName_feature", "_UI_ContextParam_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getContextParam_ParamName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Param Value feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addParamValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ContextParam_paramValue_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ContextParam_paramValue_feature", "_UI_ContextParam_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getContextParam_ParamValue(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ContextParam_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ContextParam_description_feature", "_UI_ContextParam_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getContextParam_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


	public String getText(Object object) {
		return WebAppEditResourceHandler.getString("12concat_UI_", (new Object[]{((ContextParam) object).getParamName()})); //$NON-NLS-1$ = "ContextParam {0}"
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(ContextParam.class)) {
			case WebapplicationPackage.CONTEXT_PARAM__PARAM_NAME :
			case WebapplicationPackage.CONTEXT_PARAM__PARAM_VALUE :
			case WebapplicationPackage.CONTEXT_PARAM__DESCRIPTION : {
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

}