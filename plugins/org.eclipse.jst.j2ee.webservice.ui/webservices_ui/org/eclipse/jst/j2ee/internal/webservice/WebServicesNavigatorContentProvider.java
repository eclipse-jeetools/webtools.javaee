/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 19, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.webservice.helper.WebServicesManager;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceExtManager;
import org.eclipse.jst.j2ee.navigator.internal.IJ2EENavigatorConstants;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.DynamicAdapterFactory;
import org.eclipse.wst.project.facet.ProductManager;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServicesNavigatorContentProvider extends AdapterFactoryContentProvider {

	private static final Object[] NO_CHILDREN = new Object[0]; 

	private WebServicesManager webServicesManager = null; 
	private WebServiceNavigatorGroup webServiceNavigatorGroup;
	private WebServiceNavigatorGroupType SERVICES = null;
	private WebServiceNavigatorGroupType CLIENTS = null;
	private HashMap HANDLERS = new HashMap();
	private TreeViewer viewer2 = null;

	private WebServiceViewerSynchronization viewerSynchronization;
	NewProjectsListener projectListener;
	

	public WebServicesNavigatorContentProvider() {
		super(createAdapterFactory());
		if (ProductManager.shouldUseViewerSyncForWebservices()) {
			viewerSynchronization = new WebServiceViewerSynchronization(this);
			projectListener = new NewProjectsListener(viewerSynchronization);
		}

	} 
	
	@Override
	public void dispose() { 
		super.dispose();
		if (projectListener != null)
			projectListener.dispose();
	}

	/**
	 * Configure and return a composite adapter factory for our contents
	 */
	public static AdapterFactory createAdapterFactory() {
		return new DynamicAdapterFactory(IJ2EENavigatorConstants.VIEWER_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof IWorkspaceRoot) {
			// return new Object[]{ getWebServicesNavigatorGroup(parentElement) };
			if(WebServiceViewerSynchronization.isThereWebServicesPreferenceSet()){
				if(WebServiceViewerSynchronization.areThereWebServices()){
					if (viewerSynchronization != null)
						viewerSynchronization.setNavigatorGroupAdded(true);
					return new Object[]{getNavigatorGroup()};
				}
				return NO_CHILDREN;
			}
			// first time on this workspace, let the job set the WebServiceViewerSynchronization.ARE_THERE_WEBSERVICES
			if (viewerSynchronization != null && !viewerSynchronization.hasIndexJobBeenScheduled()) {
				viewerSynchronization.startIndexJob();
			}
			return NO_CHILDREN;
		} else if (parentElement instanceof WebServiceNavigatorGroup){
			if (viewerSynchronization != null && !viewerSynchronization.hasIndexJobBeenScheduled()) {
				viewerSynchronization.startIndexJob();
			} 
			return new Object[]{getServicesGroup(), getClientsGroup()};

		}else if (parentElement instanceof WebServiceNavigatorGroupType) {
			if (viewerSynchronization != null && !viewerSynchronization.hasIndexJobBeenScheduled()) {
				viewerSynchronization.startIndexJob();
			} 
			WebServiceNavigatorGroupType wsGroupType = (WebServiceNavigatorGroupType) parentElement;
			return wsGroupType.getChildren();
		
		} else if (WSDLServiceExtManager.getServiceHelper().isService(parentElement))
			return getServiceLevelNodes(parentElement).toArray();

		else if (parentElement instanceof ServiceRef)
			return new Object[]{getHandlersGroup(parentElement)};

		else if (parentElement instanceof Handler || parentElement instanceof org.eclipse.jst.j2ee.webservice.wsclient.Handler || WSDLServiceExtManager.getServiceHelper().isWSDLResource(parentElement))
			return NO_CHILDREN;

		else
			return super.getChildren(parentElement);
	}


	private List getServiceLevelNodes(Object parentElement) {
		List result = new ArrayList();
		// add service classes
		if (getWebServicesManager().isServiceInternal((EObject) parentElement) && getWebServicesManager().getServiceImplBean((EObject) parentElement) != null)
			result.add(getWebServicesManager().getServiceImplBean((EObject) parentElement));
		// Add handlers
		if (getWebServicesManager().isServiceInternal((EObject) parentElement))
			result.add(getHandlersGroup(parentElement));
		// add wsdl file
		Resource wsdl = getWebServicesManager().getWSDLResource((EObject) parentElement);
		if (wsdl != null)
			result.add(wsdl);
		return result;
	}

//	private List getHandlerChildren(Object parentElement) {
//		List result = new ArrayList();
//		WebServiceNavigatorGroupType handlersGroup = (WebServiceNavigatorGroupType) parentElement;
//		// handle web service handlers case
//		if (handlersGroup.getWsdlService() != null) {
//			PortComponent port = getWebServicesManager().getPortComponent(handlersGroup.getWsdlService());
//			if (port != null && port.getHandlers() != null && !port.getHandlers().isEmpty())
//				result.addAll(port.getHandlers());
//		}
//		// handle service ref case
//		else if (handlersGroup.getServiceRef() != null) {
//			result.addAll(handlersGroup.getServiceRef().getHandlers());
//		}
//		return result;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		if (element instanceof IWorkspaceRoot)
			return null;
		else if (element instanceof WebServiceNavigatorGroup)
			return ((WebServiceNavigatorGroup) element).getRoot();
		else
			return super.getParent(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer aViewer, Object oldInput, Object newInput) {
		// TODO handle change events
		if (aViewer != null && aViewer instanceof TreeViewer)
			viewer2 = (TreeViewer) aViewer;
		super.inputChanged(aViewer, oldInput, newInput);
	}
 

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/**
	 * @return Returns the navigatorGroup.
	 */
	public WebServiceNavigatorGroup getNavigatorGroup() {
		if (null == webServiceNavigatorGroup) {
			webServiceNavigatorGroup = new WebServiceNavigatorGroup(ResourcesPlugin.getWorkspace().getRoot());
		}
		return webServiceNavigatorGroup;
	}

	protected WebServicesManager getWebServicesManager() {
		if (webServicesManager == null)
			webServicesManager = WebServicesManager.getInstance();
		return webServicesManager;
	}
	
	private WebServiceNavigatorGroupType getServicesGroup() {
		if (SERVICES == null)
			SERVICES = new WebServiceNavigatorGroupType(WebServiceNavigatorGroupType.SERVICES, getAdapterFactory());
		return SERVICES;
	}

	private WebServiceNavigatorGroupType getClientsGroup() {
		if (CLIENTS == null)
			CLIENTS = new WebServiceNavigatorGroupType(WebServiceNavigatorGroupType.CLIENTS, getAdapterFactory());
		return CLIENTS;
	}

	private WebServiceNavigatorGroupType getHandlersGroup(Object key) {
		if (key == null)
			return null;
		WebServiceNavigatorGroupType handler = null;
		handler = (WebServiceNavigatorGroupType) HANDLERS.get(key);
		if (handler == null) {
			if (WSDLServiceExtManager.getServiceHelper().isService(key))
				handler = new WebServiceNavigatorGroupType(WebServiceNavigatorGroupType.HANDLERS, (EObject) key, getAdapterFactory());
			else if (key instanceof ServiceRef)
				handler = new WebServiceNavigatorGroupType(WebServiceNavigatorGroupType.HANDLERS, (ServiceRef) key, getAdapterFactory());

			if (handler != null)
				HANDLERS.put(key, handler);
		}
		return handler;
	}

	/**
	 * @return Returns the viewer.
	 */
	public TreeViewer getViewer() {
		return viewer2;
	}
	
	@Override
	public void notifyChanged(Notification notification) {
	
		if(notification.getNotifier() instanceof ServiceRef) {
			switch(notification.getFeatureID(ServiceRef.class)) {
				case Webservice_clientPackage.SERVICE_REF__HANDLERS:
					
					WebServiceNavigatorGroupType handlersGroup = getHandlersGroup(notification.getNotifier());

					if(handlersGroup != null) {
						switch(notification.getEventType()) {
							case Notification.ADD: 
								viewer2.add(handlersGroup, notification.getNewValue());						
								break;
							case Notification.ADD_MANY: 
								viewer2.add(handlersGroup, ((Collection)notification.getNewValue()).toArray());						
								break;
							case Notification.REMOVE: 						
								viewer2.remove(notification.getOldValue());
								break;
							case Notification.REMOVE_MANY:
								viewer2.remove(((Collection)notification.getOldValue()).toArray());
								break;
						}			
					}
					return;				
				default:			
			} 
		} else if(notification.getNotifier() instanceof PortComponent) {
			switch(notification.getFeatureID(PortComponent.class)) {
				case WsddPackage.PORT_COMPONENT__HANDLERS:
					
					PortComponent pComp = (PortComponent) notification.getNotifier();
					EObject service = getWebServicesManager().getService(pComp);
					if(service != null) {
						WebServiceNavigatorGroupType handlersGroup = getHandlersGroup(service);						
						if(handlersGroup != null) {
							switch(notification.getEventType()) {
								case Notification.ADD: 
									viewer2.add(handlersGroup, notification.getNewValue());						
									break;
								case Notification.ADD_MANY: 
									viewer2.add(handlersGroup, ((Collection)notification.getNewValue()).toArray());						
									break;
								case Notification.REMOVE: 						
									viewer2.remove(notification.getOldValue());
									break;
								case Notification.REMOVE_MANY:
									viewer2.remove(((Collection)notification.getOldValue()).toArray());
									break;
							}
						}
					}
					
					
					return;				
				default:			
			}
		}  
		super.notifyChanged(notification);	 
	}

}
