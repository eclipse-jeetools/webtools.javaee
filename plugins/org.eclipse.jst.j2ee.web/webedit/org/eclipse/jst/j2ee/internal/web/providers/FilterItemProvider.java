/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.providers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.internal.provider.CompatibilityDescriptionGroupItemProvider;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

public class FilterItemProvider extends CompatibilityDescriptionGroupItemProvider implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public FilterItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This creates a new child for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public Object createChild(Object object) {
		// TODO: check that this is what you want.
		InitParam child = WebapplicationFactory.eINSTANCE.createInitParam();

		// TODO: initialize child here...

		return child;
	}

	/**
	 * This specifies how to implement {@link #getChildren}and {@link AddCommand}and
	 * {@link RemoveCommand}support in {@link #createCommand}.
	 */
	public Collection getChildrenReferences(Object object) {
		WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;
		Collection result = new ArrayList();
		WebApp webApp = (WebApp) ((Filter) object).eContainer();
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID)
			result.add(pkg.getFilter_InitParamValues());
		else
			result.add(pkg.getFilter_InitParams());
		return result;
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
	 * This returns the image for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public Object getCreateChildImage(Object object) {
		EObject refObject = (EObject) object;
		return WebPlugin.getDefault().getImage(refObject.eClass().getName() + "CreateInitParam"); //$NON-NLS-1$
	}

	/**
	 * This returns the label for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return "Create InitParam"; //$NON-NLS-1$
	}

	/**
	 * This returns the help text for
	 * {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return WebAppEditResourceHandler.getString("26concat_UI_", (new Object[]{refObject.eClass().getName()})); //$NON-NLS-1$ = "Create a child of type InitParam for the selected {0}."
	}

	/**
	 * This returns Filter.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("filter"); //$NON-NLS-1$
	}

	/**
	 * This returns the webApp of the Filter.
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

			// This is for the filterName feature.

			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("FilterItemProvider_UI_0"), //$NON-NLS-1$
						WebAppEditResourceHandler.getString("The_filterName_property_3"), wap.getFilter_Name())); //$NON-NLS-1$ //$NON-NLS-2$

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Filter_name_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Filter_name_feature", "_UI_Filter_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getFilter_Name(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Filter Class feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFilterClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Filter_filterClass_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Filter_filterClass_feature", "_UI_Filter_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getFilter_FilterClass(), true));
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
		Filter filter = (Filter) object;
		String name = filter.getName();
		if (name == null)
			name = "<filter>"; //$NON-NLS-1$
		return name;
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(Filter.class)) {
			case WebapplicationPackage.FILTER__NAME :
			case WebapplicationPackage.FILTER__INIT_PARAMS :
			case WebapplicationPackage.FILTER__INIT_PARAM_VALUES : {
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

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getFilter_InitParams(), WebapplicationFactory.eINSTANCE.createInitParam()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getFilter_InitParamValues(), CommonFactory.eINSTANCE.createParamValue()));
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
