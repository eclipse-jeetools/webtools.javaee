/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.webapplication.ExceptionTypeErrorPage;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * This is the item provider adpater for a
 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.ExceptionTypeErrorPage}object.
 */
public class ExceptionTypeErrorPageItemProvider extends ErrorPageItemProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public ExceptionTypeErrorPageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns exception_type_errorpage.gif.
	 */
	@Override
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("exception_type_errorpage"); //$NON-NLS-1$
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	@Override
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;

			// This is for the exceptionType feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("ExceptionType_UI_"), //$NON-NLS-1$ = "ExceptionType"
						WebAppEditResourceHandler.getString("The_exceptionType_property_UI_"), //$NON-NLS-1$ = "The exceptionType property"
						pkg.getExceptionTypeErrorPage_ExceptionType()));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Exception Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addExceptionTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ExceptionTypeErrorPage_exceptionType_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ExceptionTypeErrorPage_exceptionType_feature", "_UI_ExceptionTypeErrorPage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getExceptionTypeErrorPage_ExceptionType(), true));
	}


	@Override
	public String getText(Object object) {
	    String typeName = ((ExceptionTypeErrorPage) object).getExceptionTypeName();
	    if (typeName != null) {
	        typeName = typeName.substring(typeName.lastIndexOf('.')+1);
	    }
	    return typeName + " -> " + ((ExceptionTypeErrorPage) object).getLocation();  //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return J2EEPlugin.getDefault();
	}
}
