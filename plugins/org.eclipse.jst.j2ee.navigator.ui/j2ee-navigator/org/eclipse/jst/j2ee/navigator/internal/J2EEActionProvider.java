/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.j2ee.internal.actions.OpenJ2EEResourceAction;
import org.eclipse.jst.j2ee.internal.provider.J2EEUtilityJarItemProvider;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.internal.navigator.resources.plugin.WorkbenchNavigatorMessages;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.navigator.WizardActionGroup;

public class J2EEActionProvider extends CommonActionProvider implements IDoubleClickListener {

	private OpenJ2EEResourceAction openAction;
	private WizardActionGroup newWizardActionGroup;

//	private J2EERenameAction renameAction;

	//private WTPOptionalOperationAction genericDelete;
	//TODO add back generic delete
	/**
	 * 
	 */
	public J2EEActionProvider() {
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#init(org.eclipse.ui.IViewPart,
	 *      org.eclipse.jface.viewers.StructuredViewer,
	 *      org.eclipse.wst.common.navigator.internal.views.extensions.NavigatorContentService)
	 */
	public void init(ICommonActionExtensionSite aConfig) {
		if (aConfig.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			IWorkbenchWindow window = ((ICommonViewerWorkbenchSite) aConfig.getViewSite()).getWorkbenchWindow();
			newWizardActionGroup = new WizardActionGroup(window, PlatformUI.getWorkbench().getNewWizardRegistry(),WizardActionGroup.TYPE_NEW);
		}
		openAction = new OpenJ2EEResourceAction(); 
		aConfig.getStructuredViewer().addDoubleClickListener(this);
//		renameAction = new J2EERenameAction(aConfig.getViewSite(), aConfig.getViewSite().getShell());
//
//		genericDelete = new WTPOptionalOperationAction(WTPGenericActionIds.DELETE, WorkbenchMessages.Workbench_delete); //$NON-NLS-1$
//		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
//		genericDelete.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
//		genericDelete.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
//		genericDelete.setWorkbenchSite(viewPart.getViewSite());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#setActionContext(org.eclipse.ui.actions.ActionContext)
	 */
	public void setContext(ActionContext aContext) {
		
		if (aContext != null && aContext.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) aContext.getSelection();

			openAction.selectionChanged(selection);
//			renameAction.selectionChanged(selection);

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
			//genericDelete.selectionChanged(genericDelete, selection);
		}
		super.setContext(aContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#fillActionBars(org.eclipse.ui.IActionBars)
	 */
	public void fillActionBars(IActionBars theActionBars) {
//		if (genericDelete.isEnabled())
//			theActionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), genericDelete);
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager aMenu) {
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		if (selection.getFirstElement() instanceof ItemProvider) {
			
		IMenuManager submenu = null;
		if (submenu == null)
			submenu = new MenuManager(WorkbenchNavigatorMessages.NewActionProvider_NewMenu_label, ICommonMenuConstants.GROUP_NEW);
		
		// fill the menu from the commonWizard contributions
		newWizardActionGroup.setContext(getContext());
		newWizardActionGroup.fillContextMenu(submenu);

		submenu.add(new Separator(ICommonMenuConstants.GROUP_ADDITIONS));

		// append the submenu after the GROUP_NEW group.
		aMenu.insertAfter(ICommonMenuConstants.GROUP_NEW, submenu);
		}
		else {
			if (openAction.isEnabled())
				aMenu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, openAction);
		}
	/*	if (renameAction.isEnabled()) {
			aMenu.appendToGroup(ICommonMenuConstants.COMMON_MENU_EDIT_ACTIONS, renameAction);
			actionsAdded = true;
		}
		if (genericDelete.isEnabled()) {
			aMenu.appendToGroup(ICommonMenuConstants.COMMON_MENU_EDIT_ACTIONS, genericDelete);
			actionsAdded = true;
		}*/
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
	public void updateActionBars() {
		// TODO Auto-generated method stub
		super.updateActionBars();
	}
	public void doubleClick(DoubleClickEvent event) {
		openAction.run();
		
	}

}
