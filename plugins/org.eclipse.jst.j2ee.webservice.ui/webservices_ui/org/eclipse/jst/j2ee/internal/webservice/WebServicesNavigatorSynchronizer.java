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
package org.eclipse.jst.j2ee.internal.webservice;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.common.navigator.internal.providers.CommonAdapterFactoryContentProvider;
import org.eclipse.jst.j2ee.internal.webservices.WebServicesManager;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;

/**
 * @author jlanuti
 */
public class WebServicesNavigatorSynchronizer extends CommonAdapterFactoryContentProvider implements Adapter {


	private static WebServicesNavigatorSynchronizer INSTANCE = null;
	protected Notifier target = null;
	private WebServicesNavigatorContentProvider contentProvider = null;

	/**
	 * Constructor
	 */
	public WebServicesNavigatorSynchronizer(AdapterFactory adapterFactory, WebServicesNavigatorContentProvider provider) {
		super(adapterFactory);
		contentProvider = provider;
	}

	public static WebServicesNavigatorSynchronizer getInstance() {
		return INSTANCE;
	}

	public static WebServicesNavigatorSynchronizer createInstance(AdapterFactory adapterFactory, WebServicesNavigatorContentProvider provider) {
		INSTANCE = new WebServicesNavigatorSynchronizer(adapterFactory, provider);
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
		EObject notifier = (EObject) notification.getNotifier();

		if (notifier instanceof WebServices)
			return;

		if (notifier instanceof ServiceRef) {
			contentProvider.inputChanged(contentProvider.getViewer(),notification.getOldValue(),notification.getNewValue());
			return;
		}

		while (!(notifier instanceof WebServiceDescription) && notifier != null)
			notifier = notifier.eContainer();

		if (notifier == null) {
			//site.notifyElementReplaced(getContainingExtension(), null);
			return;
		}

		EObject wsdl = WebServicesManager.getInstance().getWSDLServiceForWebService((WebServiceDescription) notifier);
		EObject oldWsdl = WebServicesManager.getInstance().getWSDLServiceForWebService((WebServiceDescription) notification.getOldValue());
		contentProvider.inputChanged(contentProvider.getViewer(), oldWsdl,wsdl);

		super.notifyChanged(notification);
	}
}