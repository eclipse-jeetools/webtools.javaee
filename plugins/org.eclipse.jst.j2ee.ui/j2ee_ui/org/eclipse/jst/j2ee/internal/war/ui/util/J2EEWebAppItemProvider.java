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
 * Created on Mar 29, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.war.ui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jst.j2ee.internal.web.providers.WebAppItemProvider;
import org.eclipse.jst.j2ee.internal.webservices.WebServiceEditModel;
import org.eclipse.jst.j2ee.internal.webservices.WebServicesManager;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class J2EEWebAppItemProvider extends WebAppItemProvider {

	private List children = new ArrayList();
	private WebServletGroupItemProvider webServletGroup;
	private WebServletMappingGroupItemProvider webServletMappingGroup;
	private WebFiltersGroupItemProvider webFiltersGroup;
	private WebFilterMappingGroupItemProvider webFilterMappingGroup;
	private WebReferencesGroupItemProvider webRefsGroup;
	private WebSecurityGroupItemProvider webSecurityGroup;
	private J2EEWebServiceClientDDManager clientMgr;
	private WebListenerGroupItemProvider webListenerGroup;

	/**
	 * Listen and fire updates for 1.3 web service clients
	 */
	private class J2EEWebServiceClientDDManager extends AdapterImpl implements EditModelListener {
		private WebApp webApp;
		private WebServicesManager webServiceMgr = WebServicesManager.getInstance();
		WebServiceEditModel editModel;
		WebServicesClient client;

		public J2EEWebServiceClientDDManager(WebApp webApp) {
			this.webApp = webApp;
			init();
		}

		public void setWebApp(WebApp webApp) {
			this.webApp = webApp;
		}

		public void init() {
			editModel = webServiceMgr.getWSEditModel(ProjectUtilities.getProject(webApp));
			if (editModel != null) {
				editModel.addListener(this);
				if (editModel.get13WebServicesClientResource() != null) {
					client = editModel.get13WebServicesClientResource().getWebServicesClient();
					if (client != null)
						client.eAdapters().add(this);
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener#editModelChanged(org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent)
		 */
		public void editModelChanged(EditModelEvent anEvent) {
			if (editModel == null)
				init();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification notification) {
			if (notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.ADD_MANY || notification.getEventType() == Notification.REMOVE
					|| notification.getEventType() == Notification.REMOVE_MANY) {
				if (notification.getFeatureID(WebServicesClient.class) == Webservice_clientPackage.WEB_SERVICES_CLIENT__SERVICE_REFS) {
					NotificationWrapper notificationWrapper = new NotificationWrapper(webRefsGroup, notification);
					fireNotifyChanged(notificationWrapper);
				}
			}
			super.notifyChanged(notification);
		}

		public void dispose() {
			if (editModel != null) {
				editModel.removeListener(this);
				if (editModel.get13WebServicesClientResource() != null) {
					client = editModel.get13WebServicesClientResource().getWebServicesClient();
					if (client != null)
						client.eAdapters().remove(this);
				}
			}
		}
	}
	/**
	 * Default constructor
	 */
	public J2EEWebAppItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * initilaize list of children
	 */
	private void initChildren(WebApp webApp) {
		if (clientMgr == null)
			clientMgr = new J2EEWebServiceClientDDManager(webApp);
		children.add(webServletGroup = new WebServletGroupItemProvider(adapterFactory, webApp));
		children.add(webServletMappingGroup = new WebServletMappingGroupItemProvider(adapterFactory, webApp));
		children.add(webFiltersGroup = new WebFiltersGroupItemProvider(adapterFactory, webApp));
		children.add(webFilterMappingGroup = new WebFilterMappingGroupItemProvider(adapterFactory, webApp));
		children.add(webRefsGroup = new WebReferencesGroupItemProvider(adapterFactory, webApp));
		children.add(webSecurityGroup = new WebSecurityGroupItemProvider(adapterFactory, webApp));
		children.add(webListenerGroup = new WebListenerGroupItemProvider(adapterFactory, webApp));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		if (object instanceof WebApp && children.isEmpty()) {
			WebApp webApp = (WebApp) object;
			initChildren(webApp);
		}
		if (object instanceof WebApp)
			setWebAppOnChildren((WebApp) object);
		return children;
	}

	/**
	 * @param app
	 */
	private void setWebAppOnChildren(WebApp app) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) instanceof WebGroupItemProvider) {
				WebGroupItemProvider provider = (WebGroupItemProvider) children.get(i);
				provider.setWebApp(app);
			}
		}
		if (clientMgr != null)
			clientMgr.setWebApp(app);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		// We only care about adds and removes for the different item provider
		// groups
		if (notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.ADD_MANY || notification.getEventType() == Notification.REMOVE
				|| notification.getEventType() == Notification.REMOVE_MANY) {
			Object notifier = null;
			switch (notification.getFeatureID(WebApp.class)) {
				case WebapplicationPackage.WEB_APP__SERVLETS :
					notifier = webServletGroup;
					break;
				case WebapplicationPackage.WEB_APP__SERVLET_MAPPINGS :
					notifier = webServletMappingGroup;
					break;
				case WebapplicationPackage.WEB_APP__FILTERS :
					notifier = webFiltersGroup;
					break;
				case WebapplicationPackage.WEB_APP__FILTER_MAPPINGS :
					notifier = webFilterMappingGroup;
					break;
				case WebapplicationPackage.WEB_APP__EJB_LOCAL_REFS :
				case WebapplicationPackage.WEB_APP__EJB_REFS :
				case WebapplicationPackage.WEB_APP__MESSAGE_DESTINATION_REFS :
				case WebapplicationPackage.WEB_APP__RESOURCE_ENV_REFS :
				case WebapplicationPackage.WEB_APP__RESOURCE_REFS :
				case WebapplicationPackage.WEB_APP__SERVICE_REFS :
					notifier = webRefsGroup;
					break;
				case WebapplicationPackage.WEB_APP__SECURITY_ROLES :
				case WebapplicationPackage.WEB_APP__CONSTRAINTS :
					notifier = webSecurityGroup;
					break;
				case WebapplicationPackage.WEB_APP__LISTENERS :
					notifier = webListenerGroup;
					break;
			}
			if (notifier != null) {
				NotificationWrapper notificationWrapper = new NotificationWrapper(notifier, notification);
				fireNotifyChanged(notificationWrapper);
			}
		}
		super.notifyChanged(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
	 */
	public void dispose() {
		if (clientMgr != null)
			clientMgr.dispose();
		super.dispose();
	}
}