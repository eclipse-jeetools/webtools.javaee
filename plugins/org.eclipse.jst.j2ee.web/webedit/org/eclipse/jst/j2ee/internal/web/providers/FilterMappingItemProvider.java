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
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.Servlet;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.FilterMapping}
 * object.
 */
public class FilterMappingItemProvider extends WebapplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public FilterMappingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns filter_mapping.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("filter_mapping"); //$NON-NLS-1$
	}

	/**
	 * This returns the webApp of the FilterMapping.
	 */
	public Object getParent(Object object) {
		return WebapplicationPackage.eINSTANCE.getWebApp();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);
			WebapplicationPackage wap = WebapplicationPackage.eINSTANCE;

			// This is for the urlPattern feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("UrlPattern_UI_"), //$NON-NLS-1$ = "UrlPattern"
						WebAppEditResourceHandler.getString("The_urlPattern_property_UI_"), //$NON-NLS-1$ = "The urlPattern property"
						wap.getFilterMapping_UrlPattern()));

			// This is for the filter feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), "Filter", WebAppEditResourceHandler.getString("The_filter_property_2"), wap.getFilterMapping_Filter(), false)); //$NON-NLS-1$ //$NON-NLS-2$

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Url Pattern feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addUrlPatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_FilterMapping_urlPattern_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_FilterMapping_urlPattern_feature", "_UI_FilterMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getFilterMapping_UrlPattern(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Dispatcher Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDispatcherTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_FilterMapping_dispatcherType_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_FilterMapping_dispatcherType_feature", "_UI_FilterMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getFilterMapping_DispatcherType(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Filter feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFilterPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_FilterMapping_filter_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_FilterMapping_filter_feature", "_UI_FilterMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getFilterMapping_Filter(), true));
	}

	/**
	 * This adds a property descriptor for the Servlet feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addServletPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_FilterMapping_servlet_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_FilterMapping_servlet_feature", "_UI_FilterMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getFilterMapping_Servlet(), true));
	}


	public String getText(Object object) {
		FilterMapping filterMapping = (FilterMapping) object;
		Filter filter = filterMapping.getFilter();
		String filterStr = (filter == null) ? WebAppEditResourceHandler.getString("?_UI_") : filter.getName(); //$NON-NLS-1$ = "?"

		String mapText = ((FilterMapping) object).getUrlPattern();
		if (mapText == null) {
			Servlet servlet = filterMapping.getServlet();
			if (servlet != null)
				mapText = servlet.getServletName();
		}
		if (mapText == null)
			mapText = "?"; //$NON-NLS-1$
		return WebAppEditResourceHandler.getString("Filter_mapping_text", new Object[]{filterStr, mapText}); //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(FilterMapping.class)) {
			case WebapplicationPackage.FILTER_MAPPING__URL_PATTERN :
			case WebapplicationPackage.FILTER_MAPPING__FILTER :
			case WebapplicationPackage.FILTER_MAPPING__SERVLET :
			case WebapplicationPackage.FILTER_MAPPING__DISPATCHER_TYPE : {
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