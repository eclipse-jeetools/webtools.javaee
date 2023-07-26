/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.j2ee.internal.provider.J2EEUtilityJarItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.appclient.GroupAppClientProvider;
import org.eclipse.jst.jee.ui.internal.navigator.ear.GroupEARProvider;
import org.eclipse.jst.jee.ui.internal.navigator.ejb.BeanInterfaceNode;
import org.eclipse.jst.jee.ui.internal.navigator.ejb.BeanNode;
import org.eclipse.jst.jee.ui.internal.navigator.ejb.GroupEJBProvider;
import org.eclipse.jst.jee.ui.internal.navigator.ra.RaGroupContentProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebAppProvider;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.OpenWithMenu;
import org.eclipse.ui.internal.navigator.resources.plugin.WorkbenchNavigatorMessages;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

/**
 * Standard operations Java EE Action Provider.
 * Introduces standard operations for Deployment descriptor tree nodes. (like open) 
 * 
 * @author Dimitar Giormov
 *
 */
public class JEEActionProvider extends CommonActionProvider {

private static final Class IRESOURCE_CLASS = IResource.class;
	
	private OpenJEEResourceAction openAction; 
	
	/**
	 * 
	 */
	public JEEActionProvider() {
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#init(org.eclipse.ui.IViewPart,
	 *      org.eclipse.jface.viewers.StructuredViewer,
	 *      org.eclipse.wst.common.navigator.internal.views.extensions.NavigatorContentService)
	 */
	@Override
	public void init(ICommonActionExtensionSite aConfig) {	  
		openAction = new OpenJEEResourceAction(); 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#setActionContext(org.eclipse.ui.actions.ActionContext)
	 */
	@Override
	public void setContext(ActionContext aContext) {
		
		if (aContext != null && aContext.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) aContext.getSelection();

			openAction.selectionChanged(selection);

			Object[] array = selection.toArray();
			if (isUtilityJars(array)) {
				J2EEUtilityJarItemProvider utilityJarItemProvider = null;
				List newSelection = new ArrayList();
				for (int i = 0; i < array.length; i++) {
					utilityJarItemProvider = (J2EEUtilityJarItemProvider) array[i];
					newSelection.addAll(utilityJarItemProvider.getChildren(null));
				}
				selection = new StructuredSelection(newSelection);
			}
		}
		super.setContext(aContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#fillActionBars(org.eclipse.ui.IActionBars)
	 */
	@Override
	public void fillActionBars(IActionBars theActionBars) {
		if(openAction.isEnabled()) 
			theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu(IMenuManager aMenu) {		
		if (getContext()==null || getContext().getSelection().isEmpty())
			return;
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		if(!adaptsToResource(selection.toArray())) {
			openAction.selectionChanged(selection);
//			createAction.selectionChanged(selection);
			if (openAction.isEnabled())
				aMenu.insertAfter(ICommonMenuConstants.GROUP_OPEN, openAction);
//			if (createAction.isEnabled())
//				aMenu.insertAfter(ICommonMenuConstants.GROUP_OPEN, createAction);
			addOpenWithMenu(aMenu);
			
		}
	}

	private boolean adaptsToResource(Object[] objects) {
		for (int i = 0; i < objects.length; i++) {
			if(objects[i] instanceof IResource) {
				return true;
			} else if (objects[i] instanceof IAdaptable && (((IAdaptable)objects[i]).getAdapter(IRESOURCE_CLASS) != null)) {
				return true;
			}			
		}
		return false;
	}
	private boolean isUtilityJars(Object[] items) {
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (items[i] == null || items[i].getClass() != J2EEUtilityJarItemProvider.class)
					return false;
			}
			return true;
		}
		return false;
	} 
	 
	private void addOpenWithMenu(IMenuManager menu) {
		ISelection selection= getContext().getSelection();
		if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
			return;
		IStructuredSelection ss= (IStructuredSelection)selection;
		if (ss.size() != 1)
			return;

		Object srcObject = ss.getFirstElement();
		Object resource = null;
		
		if (srcObject instanceof EObject) {
			resource = openEObject((EObject) srcObject);
		} else if (srcObject instanceof BeanInterfaceNode) {
			resource = ((BeanInterfaceNode) srcObject).get_fqn();
		} else if (srcObject instanceof BeanNode) {
			resource = ((BeanNode) srcObject).getEjbClassQualifiedName();

		} else if (srcObject instanceof WebAppProvider) {
			IFile file = ((WebAppProvider) srcObject).getDDFile();
			if (file.isAccessible()){				
				resource = file;
			}
		} else if (srcObject instanceof RaGroupContentProvider) {
			IFile file = WorkbenchResourceHelper.getFile((EObject) ((AbstractGroupProvider)srcObject).getJavaEEObject());
			if (file.isAccessible()){				
				resource = file;
			}
		} else if (srcObject instanceof GroupAppClientProvider) {
			IFile file = ((GroupAppClientProvider) srcObject).getDDFile();
			if (file.isAccessible()){				
				resource = file;
			}
		} else if (srcObject instanceof GroupEJBProvider) {
			IFile file = WorkbenchResourceHelper.getFile((EObject)((GroupEJBProvider)srcObject).getEjbJar());
			if (file.isAccessible()){
				resource = file;
			}
		} else if (srcObject instanceof GroupEARProvider) {
			IFile file = ((GroupEARProvider) srcObject).getDDFile();
			if (file.isAccessible()){
				resource = file;
			}
		}
		
		if (!(resource instanceof IFile))
			return; 

		// Create a menu.
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		.getActivePage();
		IMenuManager submenu= new MenuManager(WorkbenchNavigatorMessages.OpenActionProvider_OpenWithMenu_label, 
				ICommonMenuConstants.GROUP_OPEN_WITH); 
		submenu.add(new OpenWithMenu(page, (IFile) resource));

		// Add the submenu.
		menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, submenu);
	}
	
	private IResource openEObject(EObject _srcObject) {
//		EObject ro = _srcObject;
//		IResource resource = WorkbenchResourceHelper
//		.getFile(_srcObject);
//		if (resource != null) {
//			return resource;
//		}
//		ModuleFile moduleFile = ArchiveUtil.getModuleFile(ro);
//		if (moduleFile != null) {
//			ArchiveOptions options = moduleFile.getOptions();
//			if (options instanceof ComponentArchiveOptions) {
//				IVirtualComponent component = ((ComponentArchiveOptions) options)
//				.getComponent();
////					openAppropriateEditor(component);
//			}
//		} else {
//			IArchive archive = JavaEEArchiveUtilities.findArchive(ro);
//			if(archive != null){
//				IVirtualComponent component = JavaEEArchiveUtilities.findComponent(archive);
//				if(component != null){
////						openAppropriateEditor(component);
//				}
//			}
//		}
		
		return null;
	}
}
