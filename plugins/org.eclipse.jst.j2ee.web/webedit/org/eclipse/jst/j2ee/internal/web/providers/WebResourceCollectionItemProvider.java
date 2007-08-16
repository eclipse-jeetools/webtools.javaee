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
package org.eclipse.jst.j2ee.internal.web.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.webapplication.URLPatternType;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;

/**
 * This is the item provider adpater for a
 * {@link org.eclipse.jst.j2ee.internal.internal.webapplication.WebResourceCollection}object.
 */
public class WebResourceCollectionItemProvider extends WebapplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public WebResourceCollectionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This creates a new child for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public Object createChild(Object object) {
		// TODO: check that this is what you want.
		URLPatternType child = WebapplicationFactory.eINSTANCE.createURLPatternType();

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
		result.add(pkg.getWebResourceCollection_UrlPattern());
		result.add(pkg.getWebResourceCollection_HttpMethod());
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
		return WebPlugin.getDefault().getImage(refObject.eClass().getName() + "CreateURLPatternType");//$NON-NLS-1$
	}

	/**
	 * This returns the label for {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return WebAppEditResourceHandler.getString("Create_URLPatternType_UI_"); //$NON-NLS-1$ = "Create URLPatternType"
	}

	/**
	 * This returns the help text for
	 * {@link org.eclipse.jst.j2ee.internal.internal.webapplication.commandCreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return WebAppEditResourceHandler.getString("37concat_UI_", (new Object[]{refObject.eClass().getName()})); //$NON-NLS-1$ = "Create a child of type URLPatternType for the selected {0}."
	}

	/**
	 * This returns web_resource_collection.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("web_resource_collection");//$NON-NLS-1$
	}

	/**
	 * This returns the secConstraint of the WebResourceCollection.
	 */
	public Object getParent(Object object) {
		return ((WebResourceCollection) object).getSecConstraint();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;

			// This is for the webResourceName feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("WebResourceName_UI_"), //$NON-NLS-1$ = "WebResourceName"
						WebAppEditResourceHandler.getString("The_webResourceName_proper_UI_"), //$NON-NLS-1$ = "The webResourceName property"
						pkg.getWebResourceCollection_WebResourceName()));

			// This is for the description feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("Description_UI__UI_"), //$NON-NLS-1$ = "Description"
						WebAppEditResourceHandler.getString("The_description_property_UI_"), //$NON-NLS-1$ = "The description property"
						pkg.getWebResourceCollection_Description()));

			// This is for the secConstraint feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("SecConstraint_UI_"), //$NON-NLS-1$ = "SecConstraint"
						WebAppEditResourceHandler.getString("The_secConstraint_property_UI_"), //$NON-NLS-1$ = "The secConstraint property"
						pkg.getWebResourceCollection_SecConstraint()));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Web Resource Name feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addWebResourceNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebResourceCollection_webResourceName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebResourceCollection_webResourceName_feature", "_UI_WebResourceCollection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getWebResourceCollection_WebResourceName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebResourceCollection_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebResourceCollection_description_feature", "_UI_WebResourceCollection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getWebResourceCollection_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Url Pattern feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addUrlPatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebResourceCollection_urlPattern_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebResourceCollection_urlPattern_feature", "_UI_WebResourceCollection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getWebResourceCollection_UrlPattern(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Http Method feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addHttpMethodPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_WebResourceCollection_httpMethod_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_WebResourceCollection_httpMethod_feature", "_UI_WebResourceCollection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getWebResourceCollection_HttpMethod(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	public String getText(Object object) {
		//	return WebAppEditResourceHandler.getString("38concat_UI_", (new Object[] {
		// ((WebResourceCollection)object).getWebResourceName() })); //$NON-NLS-1$ =
		// "WebResourceCollection {0}"
		return ((WebResourceCollection) object).getWebResourceName();
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(WebResourceCollection.class)) {
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME :
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTION :
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__URL_PATTERN :
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD :
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS :
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS :
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTT_PS : {
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

		newChildDescriptors.add(createChildParameter(((WebResourceCollection) WebapplicationPackage.eINSTANCE.getWebResourceCollection()).getURLs(), WebapplicationFactory.eINSTANCE.createURLPatternType()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getWebResourceCollection_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getWebResourceCollection_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));

		newChildDescriptors.add(createChildParameter(((WebResourceCollection) WebapplicationPackage.eINSTANCE.getWebResourceCollection()).getHttpMethod(), WebapplicationFactory.eINSTANCE.createHTTPMethodType()));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.IEditingDomainItemProvider#createCommand(java.lang.Object,
	 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Class,
	 *      org.eclipse.emf.edit.command.CommandParameter)
	 */
	public Command createCommand(Object object, EditingDomain editingDomain, Class commandClass, CommandParameter commandParameter) {
		EStructuralFeature sf = commandParameter.getEReference();
		if (sf == null) {
			sf = commandParameter.getEAttribute();
			if (sf != null && !sf.isMany())
				sf = null;
		}
		if (sf != null) {
			EObject owner = commandParameter.getEOwner();
			Collection collection = commandParameter.getCollection();
			if (commandClass == AddCommand.class) {
				return createAddCommand(editingDomain, owner, sf, collection, commandParameter.getIndex());
			} else if (commandClass == RemoveCommand.class) {
				return createRemoveCommand(editingDomain, owner, sf, collection);
			}
		}
		return super.createCommand(object, editingDomain, commandClass, commandParameter);
	}
}
