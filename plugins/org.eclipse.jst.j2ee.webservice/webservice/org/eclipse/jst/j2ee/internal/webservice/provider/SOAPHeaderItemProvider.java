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
package org.eclipse.jst.j2ee.internal.webservice.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jst.j2ee.common.internal.provider.QNameItemProvider;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPHeader;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.wscommon.SOAPHeader}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class SOAPHeaderItemProvider extends QNameItemProvider{
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public SOAPHeaderItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}


	/**
	 * This returns SOAPHeader.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return getResourceLocator().getImage("full/obj16/SOAPHeader"); //$NON-NLS-1$
	}


	protected String toDisplayString(String s) {
		return (s != null) ? s : ""; //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	/*
	 * public String getText(Object object) { String label =
	 * ((SOAPHeader)object).getCombinedQName(); return label == null || label.length() == 0 ?
	 * getString("_UI_SOAPHeader_type") : getString("_UI_SOAPHeader_type") + " " + label; }
	 */

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @non-generated
	 */
	@Override
	public String getText(Object object) {
		SOAPHeader soapHeader = (SOAPHeader) object;
		String nsURI = soapHeader.getNamespaceURI();
		String localpart = soapHeader.getLocalPart();
		if (nsURI == null || nsURI.length() == 0 || localpart == null || localpart.length() == 0)
			return getString("%_UI_SOAPHeader_type"); //$NON-NLS-1$
		StringBuffer label = new StringBuffer();
		label.append(nsURI);
		label.append("#"); //$NON-NLS-1$
		label.append(localpart);
		return label.toString();
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


}