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
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.internal.webservice;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.actions.OpenJ2EEResourceAction;
import org.eclipse.jst.j2ee.webservice.wsdd.BeanLink;
import org.eclipse.jst.j2ee.webservice.wsdd.ServiceImplBean;
import org.eclipse.jst.j2ee.webservices.WebServicesManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.ActivityManagerEvent;
import org.eclipse.ui.activities.IActivityManagerListener;
import org.eclipse.wst.common.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.common.emfworkbench.integration.DynamicAdapterFactory;
import org.eclipse.wst.common.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.emfworkbench.integration.EditModelListener;
import org.eclipse.wst.common.navigator.internal.views.NavigatorActivityHelper;
import org.eclipse.wst.common.navigator.views.DefaultNavigatorContentExtension;
import org.eclipse.wst.common.navigator.views.INavigatorContentProvider;
import org.eclipse.wst.common.navigator.views.actions.CommonEditActionGroup;
import org.eclipse.wst.common.navigator.views.actions.ICommonMenuConstants;

import com.ibm.etools.wsdl.util.WSDLResourceImpl;

/**
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServiceGroupContentExtension extends DefaultNavigatorContentExtension implements EditModelListener, IActivityManagerListener {
	private final static String VIEWER_ID = "org.eclipse.wst.common.navigator.internal.views.navigator.navigatorViewer"; //$NON-NLS-1$
	private INavigatorContentProvider contentProvider = null;
	private ILabelProvider labelProvider = null;
	private OpenJ2EEResourceAction openAction = new OpenJ2EEResourceAction();
	private OpenExternalWSDLAction openExternalWSDLAction = new OpenExternalWSDLAction(WebServiceUIResourceHandler.getString("WebServiceGroupContentExtension_UI_1")); //$NON-NLS-1$

	public WebServiceGroupContentExtension() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.DefaultNavigatorContentExtension#doInit()
	 */
	public void doInit() {
		WebServicesManager.getInstance().addListener(this);
		PlatformUI.getWorkbench().getActivitySupport().getActivityManager().addActivityManagerListener(this);

		// create the default synchronizer for any web service editor to use with view due
		// to the usage of seperate edit models.
		WebServicesNavigatorSynchronizer.createInstance(createAdapterFactory(), this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentExtension#getDescription(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public String getDescription(IStructuredSelection selection) {
		return ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentExtension#getContentProvider()
	 */
	public INavigatorContentProvider getContentProvider() {
		if (contentProvider == null)
			contentProvider = new WebServicesNavigatorContentProvider(createAdapterFactory(), this);
		return contentProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentExtension#getLabelProvider()
	 */
	public ILabelProvider getLabelProvider() {
		if (labelProvider == null) {
			labelProvider = new WebServicesNavigatorLabelProvider(createAdapterFactory());
		}
		return labelProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.emfworkbench.integration.EditModelListener#editModelChanged(org.eclipse.wst.common.emfworkbench.integration.EditModelEvent)
	 */
	public void editModelChanged(EditModelEvent anEvent) {
		this.getExtensionSite().notifyElementReplaced(this, ((WebServicesNavigatorContentProvider) getContentProvider()).getNavigatorGroup());
	}

	/**
	 * Configure and return a composite adapter factory for our contents
	 */
	public AdapterFactory createAdapterFactory() {
		return new DynamicAdapterFactory(VIEWER_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentExtension#dispose()
	 */
	public void dispose() {
		super.dispose();
		WebServicesManager.getInstance().removeListener(this);
		PlatformUI.getWorkbench().getActivitySupport().getActivityManager().removeActivityManagerListener(this);

		// dispose current instance of web service editor/explorer synchronizer
		WebServicesNavigatorSynchronizer.disposeInstance();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentExtension#getEditActionGroup()
	 */
	public CommonEditActionGroup getEditActionGroup() {
		return new CommonEditActionGroup(getExtensionSite()) {
			public void fillOpenWithMenu(IMenuManager menu) {
				if ((getExtensionSite().getSelection().getFirstElement() instanceof BeanLink)) {
					openAction.selectionChanged(getExtensionSite().getSelection());
					menu.insertAfter(ICommonMenuConstants.COMMON_MENU_TOP, openAction);
				} else if ((getExtensionSite().getSelection().getFirstElement() instanceof WSDLResourceImpl)) {
					WSDLResourceImpl wsdl = (WSDLResourceImpl) getExtensionSite().getSelection().getFirstElement();
					IFile wsdlFile = WorkbenchResourceHelper.getFile(wsdl);
					if (wsdlFile == null || !wsdlFile.exists()) {
						openExternalWSDLAction.selectionChanged(getExtensionSite().getSelection());
						menu.insertAfter(ICommonMenuConstants.COMMON_MENU_TOP, openExternalWSDLAction);
					}
				}
			}

			public void runDefaultAction(IStructuredSelection element) {
				if (element.getFirstElement() instanceof ServiceImplBean)
					return;
				if ((getExtensionSite().getSelection().getFirstElement() instanceof BeanLink)) {
					openAction.selectionChanged(element);
					openAction.run();
					return;
				} else if ((getExtensionSite().getSelection().getFirstElement() instanceof WSDLResourceImpl)) {
					WSDLResourceImpl wsdl = (WSDLResourceImpl) getExtensionSite().getSelection().getFirstElement();
					IFile wsdlFile = WorkbenchResourceHelper.getFile(wsdl);
					if (wsdlFile == null || !wsdlFile.exists()) {
						openExternalWSDLAction.selectionChanged(element);
						openExternalWSDLAction.run();
						return;
					}
				}

				openAction.selectionChanged(element);
				openAction.run();

			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.activities.IActivityManagerListener#activityManagerChanged(org.eclipse.ui.activities.ActivityManagerEvent)
	 */
	public void activityManagerChanged(ActivityManagerEvent activityManagerEvent) {
		WebServicesNavigatorContentProvider provider = (WebServicesNavigatorContentProvider) getContentProvider();
		boolean webServicesEnabled = NavigatorActivityHelper.isActivityEnabled(this);
		Object[] obj = new Object[]{provider.getNavigatorGroup()};
		if (webServicesEnabled) {
			Object root = ResourcesPlugin.getWorkspace().getRoot();
			getExtensionSite().notifyElementsAdded(this, root, obj);
		} else {
			getExtensionSite().notifyElementsRemoved(this, obj);
		}
	}
}