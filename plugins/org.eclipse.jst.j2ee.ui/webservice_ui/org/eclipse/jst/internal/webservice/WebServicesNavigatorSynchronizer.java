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
/*
 * Created on Jun 10, 2004
 */
package org.eclipse.jst.internal.webservice;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.jst.j2ee.webservices.WebServicesManager;
import org.eclipse.wst.common.navigator.internal.providers.CommonAdapterFactoryContentProvider;
import org.eclipse.wst.common.navigator.views.INavigatorContentExtension;
import org.eclipse.wst.common.navigator.views.INavigatorExtensionSite;
import org.eclipse.wst.wsdl.Service;

/**
 * @author jlanuti
 */
public class WebServicesNavigatorSynchronizer extends CommonAdapterFactoryContentProvider implements Adapter {


	private static WebServicesNavigatorSynchronizer INSTANCE = null;
	protected Notifier target = null;

	/**
	 * Constructor
	 */
	public WebServicesNavigatorSynchronizer(AdapterFactory adapterFactory, INavigatorContentExtension containingExtension) {
		super(adapterFactory, containingExtension);
	}

	public static WebServicesNavigatorSynchronizer getInstance() {
		return INSTANCE;
	}

	public static WebServicesNavigatorSynchronizer createInstance(AdapterFactory adapterFactory, INavigatorContentExtension containingExtension) {
		INSTANCE = new WebServicesNavigatorSynchronizer(adapterFactory, containingExtension);
		return INSTANCE;
	}

	public static void disposeInstance() {
		INSTANCE = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
	 */
	public Notifier getTarget() {
		return target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		target = newTarget;
	}

	public void notifyChanged(final Notification notification) {
		INavigatorExtensionSite site = getContainingExtension().getExtensionSite();
		EObject notifier = (EObject) notification.getNotifier();

		if (notifier instanceof WebServices)
			return;

		if (notifier instanceof ServiceRef) {
			site.notifyElementReplaced(getContainingExtension(), notifier);
			return;
		}

		while (!(notifier instanceof WebServiceDescription) && notifier != null)
			notifier = notifier.eContainer();

		if (notifier == null) {
			site.notifyElementReplaced(getContainingExtension(), null);
			return;
		}

		Service wsdl = WebServicesManager.getInstance().getWSDLServiceForWebService((WebServiceDescription) notifier);
		site.notifyElementReplaced(getContainingExtension(), wsdl);

		super.notifyChanged(notification);
	}
}