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
import org.eclipse.jst.j2ee.common.internal.provider.CompatibilityDescriptionGroupItemProvider;
import org.eclipse.jst.j2ee.internal.jsp.JspPackage;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.jsp.JSPPropertyGroup;

/**
 * This is the item provider adpater for a {@link com.ibm.etools.jsp.JSPPropertyGroup}object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class JSPPropertyGroupItemProvider extends CompatibilityDescriptionGroupItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public JSPPropertyGroupItemProvider(AdapterFactory adapterFactory) {
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

			addUrlPatternPropertyDescriptor(object);
			addElIgnoredPropertyDescriptor(object);
			addPageEncodingPropertyDescriptor(object);
			addScriptingInvalidPropertyDescriptor(object);
			addIsXMLPropertyDescriptor(object);
			addIncludePreludesPropertyDescriptor(object);
			addIncludeCodasPropertyDescriptor(object);
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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_JSPPropertyGroup_urlPattern_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_JSPPropertyGroup_urlPattern_feature", "_UI_JSPPropertyGroup_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JspPackage.eINSTANCE.getJSPPropertyGroup_UrlPattern(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the El Ignored feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addElIgnoredPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_JSPPropertyGroup_elIgnored_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_JSPPropertyGroup_elIgnored_feature", "_UI_JSPPropertyGroup_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JspPackage.eINSTANCE.getJSPPropertyGroup_ElIgnored(), true, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Page Encoding feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPageEncodingPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_JSPPropertyGroup_pageEncoding_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_JSPPropertyGroup_pageEncoding_feature", "_UI_JSPPropertyGroup_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JspPackage.eINSTANCE.getJSPPropertyGroup_PageEncoding(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Scripting Invalid feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addScriptingInvalidPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_JSPPropertyGroup_scriptingInvalid_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_JSPPropertyGroup_scriptingInvalid_feature", "_UI_JSPPropertyGroup_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JspPackage.eINSTANCE.getJSPPropertyGroup_ScriptingInvalid(), true, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Is XML feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addIsXMLPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_JSPPropertyGroup_isXML_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_JSPPropertyGroup_isXML_feature", "_UI_JSPPropertyGroup_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JspPackage.eINSTANCE.getJSPPropertyGroup_IsXML(), true, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Include Preludes feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addIncludePreludesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_JSPPropertyGroup_includePreludes_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_JSPPropertyGroup_includePreludes_feature", "_UI_JSPPropertyGroup_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JspPackage.eINSTANCE.getJSPPropertyGroup_IncludePreludes(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Include Codas feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addIncludeCodasPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_JSPPropertyGroup_includeCodas_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_JSPPropertyGroup_includeCodas_feature", "_UI_JSPPropertyGroup_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JspPackage.eINSTANCE.getJSPPropertyGroup_IncludeCodas(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


	/**
	 * This returns JSPPropertyGroup.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("full/obj16/JSPPropertyGroup"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((JSPPropertyGroup) object).getDisplayName();
		return label == null || label.length() == 0 ? getString("_UI_JSPPropertyGroup_type") : //$NON-NLS-1$
					getString("_UI_JSPPropertyGroup_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(JSPPropertyGroup.class)) {
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERN :
			case JspPackage.JSP_PROPERTY_GROUP__EL_IGNORED :
			case JspPackage.JSP_PROPERTY_GROUP__PAGE_ENCODING :
			case JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID :
			case JspPackage.JSP_PROPERTY_GROUP__IS_XML :
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES :
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS : {
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
	 */
	public ResourceLocator getResourceLocator() {
		//		return J2EEPlugin.getDefault();
		return WebAppEditResourceHandler.RESOURCE_LOCATOR;
	}
}