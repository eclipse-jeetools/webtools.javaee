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
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebType;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

public class ServletItemProvider extends CompatibilityDescriptionGroupItemProvider implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {


	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public ServletItemProvider(AdapterFactory adapterFactory) {
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
		//result.add(pkg.getServlet_WebType());
		WebApp webApp = (WebApp) ((Servlet) object).eContainer();
		if (webApp == null)
			return result;
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID)
			result.add(pkg.getServlet_InitParams());
		else
			result.add(pkg.getServlet_Params());
		result.add(pkg.getServlet_SecurityRoleRefs());
		result.add(pkg.getServlet_RunAs());
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
		return WebAppEditResourceHandler.getString("Create_InitParam_UI_"); //$NON-NLS-1$ = "Create InitParam"
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
	 * This returns Servlet.gif.
	 */
	public Object getImage(Object object) {
		WebType wt = ((Servlet) object).getWebType();
		if (wt != null && wt.isJspType())
			return WebPlugin.getDefault().getImage("jsp_type"); //$NON-NLS-1$

		return WebPlugin.getDefault().getImage("servlet"); //$NON-NLS-1$
	}

	/**
	 * This returns the webApp of the Servlet.
	 */
	public Object getParent(Object object) {
		return ((Servlet) object).getWebApp();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;

			// This is for the servletName feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("ServletName_UI_"), //$NON-NLS-1$
						//$NON-NLS-1$ = "ServletName"
						WebAppEditResourceHandler.getString("The_servletName_property_UI_"), //$NON-NLS-1$ = "The servletName property"
						pkg.getServlet_ServletName()));


			// This is for the loadOnStartup feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("LoadOnStartup_UI_"), //$NON-NLS-1$
						//$NON-NLS-1$ = "LoadOnStartup"
						WebAppEditResourceHandler.getString("The_loadOnStartup_property_UI_"), //$NON-NLS-1$ = "The loadOnStartup property"
						pkg.getServlet_LoadOnStartup(), false));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Servlet Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addServletNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Servlet_servletName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Servlet_servletName_feature", "_UI_Servlet_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getServlet_ServletName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Load On Startup feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLoadOnStartupPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Servlet_loadOnStartup_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Servlet_loadOnStartup_feature", "_UI_Servlet_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getServlet_LoadOnStartup(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
		Servlet servlet = (Servlet) object;
		String name = servlet.getServletName();
		if (name == null)
			name = WebAppEditResourceHandler.getString("<servlet>_UI_"); //$NON-NLS-1$ = "<servlet>"
		return name;
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(Servlet.class)) {
			case WebapplicationPackage.SERVLET__SERVLET_NAME :
			case WebapplicationPackage.SERVLET__LOAD_ON_STARTUP :
			case WebapplicationPackage.SERVLET__WEB_TYPE :
			case WebapplicationPackage.SERVLET__PARAMS :
			case WebapplicationPackage.SERVLET__SECURITY_ROLE_REFS :
			case WebapplicationPackage.SERVLET__RUN_AS :
			case WebapplicationPackage.SERVLET__INIT_PARAMS : {
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

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getServlet_WebType(), WebapplicationFactory.eINSTANCE.createServletType()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getServlet_WebType(), WebapplicationFactory.eINSTANCE.createJSPType()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getServlet_Params(), WebapplicationFactory.eINSTANCE.createInitParam()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getServlet_SecurityRoleRefs(), CommonFactory.eINSTANCE.createSecurityRoleRef()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getServlet_RunAs(), CommonFactory.eINSTANCE.createRunAsSpecifiedIdentity()));

		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getServlet_InitParams(), CommonFactory.eINSTANCE.createParamValue()));
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
