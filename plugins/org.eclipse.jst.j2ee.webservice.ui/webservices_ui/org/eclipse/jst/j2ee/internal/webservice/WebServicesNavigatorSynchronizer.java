/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jst.j2ee.internal.webservice.helper.WebServicesManager;
import org.eclipse.jst.j2ee.webservice.wsclient.ComponentScopedRefs;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;

/**
 * @author jlanuti
 */
public class WebServicesNavigatorSynchronizer extends AdapterFactoryContentProvider implements Adapter {


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
	@Override
	public Notifier getTarget() {
		return target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
	 */
	@Override
	public boolean isAdapterForType(Object type) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	@Override
	public void setTarget(Notifier newTarget) {
		target = newTarget;
	}

	@Override
	public void notifyChanged(final Notification notification) {
		if (notification.isTouch()) {
			//There is nothing that is required since nothing changed.
			return;
		}
		
		EObject notifier = (EObject) notification.getNotifier();
		if (notifier instanceof WebServices) {
			//Do nothing.
		} else if (isServiceRefModification(notification)) {
			// Handle service ref edits
			contentProvider.getViewer().refresh(notifier);
		} else if (isServiceRefAddOrRemove(notification)) {
			//Handle service ref adds or removes
			contentProvider.getViewer().refresh();
		} else {
			// Handle web service changes by getting the WSDD parent
			while (!(notifier instanceof WebServiceDescription) && notifier != null) {
				notifier = notifier.eContainer();
			}
			// Refresh the associated wsdl service for the WSDD parent
			if (notifier instanceof WebServiceDescription) {
				EObject wsdl = WebServicesManager.getInstance().getWSDLServiceForWebService((WebServiceDescription)notifier);
				contentProvider.getViewer().refresh(wsdl);
			}
			super.notifyChanged(notification);
		}
	}
	
	private boolean isServiceRefAddOrRemove(Notification notification) {
		//Note this check is not handling ADD_MANY or REMOVE_MANY.
		Object value = null;
		switch (notification.getEventType()) {
			case Notification.ADD:
				value = notification.getNewValue();
				break;
			case Notification.REMOVE:
				value = notification.getOldValue();
				break;
		}
		return value != null && (value instanceof ServiceRef || value instanceof ComponentScopedRefs);
	}
	
	private boolean isServiceRefModification(Notification notification) {
		Object notifier = notification.getNotifier();
		return notifier instanceof ServiceRef || notifier instanceof Handler;
	}
}
