/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 10, 2005
 */
package org.eclipse.jst.j2ee.internal.webservice;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.j2ee.internal.actions.OpenJ2EEResourceAction;
import org.eclipse.jst.j2ee.internal.webservice.helper.WebServicesManager;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceExtManager;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddResource;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

/**
 * @author jlanuti
 */
public class WebServicesNavigatorGroupOpenListener extends CommonActionProvider {

	private OpenExternalWSDLAction openExternalWSDLAction;
	private OpenJ2EEResourceAction openAction;
	
	/**
	 * @param text
	 */
	public WebServicesNavigatorGroupOpenListener() {
	}
	
	@Override
	public void init(ICommonActionExtensionSite aConfig) { 
		openExternalWSDLAction = new OpenExternalWSDLAction(WebServiceUIResourceHandler.WebServiceGroupContentExtension_UI_1);
		openAction = new OpenJ2EEResourceAction();
	}
	
	@Override
	public void setContext(ActionContext aContext) {
		if (aContext != null && aContext.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) aContext.getSelection();
			WSDLServiceHelper serviceHelper = WSDLServiceExtManager.getServiceHelper();
			if (selection == null || selection.getFirstElement()==null)
				return;
			Object selectedObject = selection.getFirstElement();
			if (serviceHelper==null)
				return;
			else if (serviceHelper.isService(selectedObject)) {
				WsddResource resource = WebServicesManager.getInstance().getWsddResource((EObject)selectedObject);
				if (resource == null) {
					openExternalWSDLAction.selectionChanged(selection);
					super.setContext(aContext);
					return;
				}
				List wsddSelection = new ArrayList();
				wsddSelection.add(resource);
				openAction.selectionChanged(new StructuredSelection(wsddSelection));
			}
			else if (serviceHelper.isWSDLResource(selectedObject)) {
				Resource wsdl = (Resource) selectedObject;
				IFile wsdlFile = WorkbenchResourceHelper.getFile(wsdl);
				if (wsdlFile == null || !wsdlFile.exists()) {
					openExternalWSDLAction.selectionChanged(selection);
					super.setContext(aContext);
					return;
				}
				openAction.selectionChanged(selection);
			}
			
			else {
				openAction.selectionChanged(selection);
			}	
		}
		super.setContext(aContext);
	}
	
	@Override
	public void fillActionBars(IActionBars theActionBars) {
		if (getContext()==null || getContext().getSelection().isEmpty())
			return;
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		Object first = selection.getFirstElement();
		WSDLServiceHelper helper =	WSDLServiceExtManager.getServiceHelper();
		if(helper != null && helper.isService(first)) {
			WsddResource resource = WebServicesManager.getInstance().getWsddResource((EObject)first);
			if (resource == null) {
				IFile wsdlFile = WorkbenchResourceHelper.getFile((EObject)first);
				if (wsdlFile == null || !wsdlFile.exists()) {
					openExternalWSDLAction.selectionChanged(selection);
					theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openExternalWSDLAction);
					return;
				} 
				openAction.selectionChanged(selection);
				theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
				return;
			}
			List wsddSelection = new ArrayList();
			wsddSelection.add(resource);
			openAction.selectionChanged(new StructuredSelection(wsddSelection));
			theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
			return;
			
		} else if (helper != null && helper.isWSDLResource(first)) {
			IFile wsdlFile = WorkbenchResourceHelper.getFile((Resource)first);
			if (wsdlFile == null || !wsdlFile.exists()) {
				openExternalWSDLAction.selectionChanged(selection);
				theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openExternalWSDLAction);
				return;
			}
		}
		
		openAction.selectionChanged(selection);
		if(openAction.isEnabled()) 
			theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
		return;
	}
	
	@Override
	public void fillContextMenu(IMenuManager menu) {
		if (getContext()==null || getContext().getSelection().isEmpty())
			return;
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		Object first = selection.getFirstElement();
		WSDLServiceHelper helper =	WSDLServiceExtManager.getServiceHelper();
		if (helper != null && helper.isService(first)) {
			IFile wsdlFile = WorkbenchResourceHelper.getFile((EObject)first);
			if (wsdlFile == null || !wsdlFile.exists()) {
				openExternalWSDLAction.selectionChanged(selection);
				menu.insertAfter(ICommonMenuConstants.GROUP_OPEN, openExternalWSDLAction);
				return;
			}
		}
		else if (helper != null && helper.isWSDLResource(first)) {
			IFile wsdlFile = WorkbenchResourceHelper.getFile((Resource)first);
			if (wsdlFile == null || !wsdlFile.exists()) {
				openExternalWSDLAction.selectionChanged(selection);
				menu.insertAfter(ICommonMenuConstants.GROUP_OPEN, openExternalWSDLAction);
				return;
			}
		}
		openAction.selectionChanged(selection);
		if (openAction.isEnabled())
			menu.insertAfter(ICommonMenuConstants.GROUP_OPEN, openAction);
	}
}
