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
 * Created on Jan 19, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.webservices.WebServicesManager;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsdd.EJBLink;
import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.ServiceImplBean;
import org.eclipse.jst.j2ee.webservice.wsdd.ServletLink;
import org.eclipse.jst.j2ee.webservice.wsdd.WSDLPort;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.common.internal.emfworkbench.integration.DynamicAdapterFactory;
import org.eclipse.wst.wsdl.Service;
import org.eclipse.wst.wsdl.internal.util.WSDLResourceImpl;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServicesNavigatorLabelProvider extends AdapterFactoryLabelProvider implements ILabelProvider {

	private final static String VIEWER_ID = "org.eclipse.wst.navigator.ui.WTPCommonNavigator";//$NON-NLS-1$
	
	public WebServicesNavigatorLabelProvider() {
		super(createAdapterFactory());
	}
	
	/**
	 * Configure and return a composite adapter factory for our contents
	 */
	public static AdapterFactory createAdapterFactory() {
		return new DynamicAdapterFactory(VIEWER_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		if (element instanceof WebServiceNavigatorGroup)
			return J2EEUIPlugin.getDefault().getImage("webServicesFolder_obj"); //$NON-NLS-1$
		else if (element instanceof String)
			return J2EEUIPlugin.getDefault().getImage("wsdl"); //$NON-NLS-1$
		else if (element instanceof WebServiceNavigatorGroupType)
			return J2EEUIPlugin.getDefault().getImage("folder"); //$NON-NLS-1$
		else if (element instanceof Service) {
			if (WebServicesManager.getInstance().isServiceInternal((Service) element))
				return J2EEUIPlugin.getDefault().getImage("webServiceItemProvider_obj"); //$NON-NLS-1$
			return J2EEUIPlugin.getDefault().getImage("extwebserviceitemprovider_obj"); //$NON-NLS-1$
		} else if (element instanceof WSDLResourceImpl)
			return J2EEUIPlugin.getDefault().getImage("wsdl"); //$NON-NLS-1$
		else
			return super.getImage(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		String space = " "; //$NON-NLS-1$
		if (element instanceof WebServiceDescription)
			return getWebServiceDescriptionText((WebServiceDescription) element);
		else if (element instanceof PortComponent)
			return WebServiceUIResourceHandler.getString("PORT_UI_") + space + super.getText(element); //$NON-NLS-1$
		else if (element instanceof Handler)
			return WebServiceUIResourceHandler.getString("HANDLER_UI_") + space + super.getText(element); //$NON-NLS-1$
		else if (element instanceof WSDLPort)
			return WebServiceUIResourceHandler.getString("WSDL_PORT_UI_") + space + super.getText(element); //$NON-NLS-1$
		else if (element instanceof ServiceImplBean)
			return WebServiceUIResourceHandler.getString("SERVICE_CLASSES_UI_"); //$NON-NLS-1$
		else if (element instanceof EJBLink)
			return WebServiceUIResourceHandler.getString("SERVICE_IMPL_UI_") + space + super.getText(element); //$NON-NLS-1$
		else if (element instanceof ServletLink)
			return WebServiceUIResourceHandler.getString("SERVICE_IMPL_UI_") + space + super.getText(element); //$NON-NLS-1$
		else if (element instanceof Service)
			return ((Service) element).getQName().getLocalPart();
		else if (element instanceof WSDLResourceImpl) {
			String result = ""; //$NON-NLS-1$
			IFile file = WorkbenchResourceHelper.getFile((WSDLResourceImpl) element);
			if (file != null && file.exists())
				result = file.getFullPath().toString();
			else
				result = ((WSDLResourceImpl) element).getURI().toString();
			return WebServiceUIResourceHandler.getString("WSDL_UI_") + space + result; //$NON-NLS-1$
		} else if (element instanceof ServiceRef) {
			String beanName14 = ""; //$NON-NLS-1$
			if (WebServicesManager.getInstance().isJ2EE14((ServiceRef) element)) {
				if (((ServiceRef) element).eContainer() instanceof Session)
					beanName14 = ((Session) ((ServiceRef) element).eContainer()).getName() + ": "; //$NON-NLS-1$
			}

			return ProjectUtilities.getProject((ServiceRef) element).getName() + ": " + beanName14 //$NON-NLS-1$
						+ ((ServiceRef) element).getServiceRefName();
		} else
			return super.getText(element);
	}

	/**
	 * get text for web service
	 */
	private String getWebServiceDescriptionText(WebServiceDescription description) {
		Resource res = description.eResource();
		IProject project = null;
		String projString = ""; //$NON-NLS-1$
		if (res != null)
			project = WorkbenchResourceHelper.getProject(res);
		if (project != null)
			projString = project.getName() + ": "; //$NON-NLS-1$
		return projString + super.getText(description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		// TODO add any listeners?

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		// TODO handle dispose

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
	 *      java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		// TODO do something smart here?
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		// TODO how do we remove listeners?

	}

}