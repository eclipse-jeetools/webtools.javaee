/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jst.j2ee.internal.actions.J2EERenameAction;
import org.eclipse.jst.j2ee.internal.actions.OpenJ2EEResourceAction;
import org.eclipse.jst.j2ee.internal.provider.J2EEUtilityJarItemProvider;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.wst.common.frameworks.internal.ui.WTPGenericActionIds;
import org.eclipse.wst.common.frameworks.internal.ui.WTPOptionalOperationAction;
import org.eclipse.wst.common.navigator.internal.provisional.views.ICommonActionProvider;
import org.eclipse.wst.common.navigator.internal.provisional.views.ICommonMenuConstants;
import org.eclipse.wst.common.navigator.internal.provisional.views.NavigatorContentService;
import org.eclipse.wst.common.navigator.internal.views.actions.CommonActionProvider;

public class J2EEActionProvider extends CommonActionProvider implements ICommonActionProvider {

	private IViewPart viewPart;

	private OpenJ2EEResourceAction openAction;

	private J2EERenameAction renameAction;

	private WTPOptionalOperationAction genericDelete;

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
	public void init(IViewPart aViewPart, StructuredViewer aViewer, NavigatorContentService aContentService) {
		viewPart = aViewPart;

		openAction = new OpenJ2EEResourceAction();
		renameAction = new J2EERenameAction(viewPart.getViewSite(), viewPart.getViewSite().getShell());

		genericDelete = new WTPOptionalOperationAction(WTPGenericActionIds.DELETE, WorkbenchMessages.Workbench_delete); //$NON-NLS-1$
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		genericDelete.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		genericDelete.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		genericDelete.setWorkbenchSite(viewPart.getViewSite());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#setActionContext(org.eclipse.ui.actions.ActionContext)
	 */
	public void setActionContext(ActionContext aContext) {
		if (aContext.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) aContext.getSelection();

			openAction.selectionChanged(selection);
			renameAction.selectionChanged(selection);

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
			genericDelete.selectionChanged(genericDelete, selection);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#fillActionBars(org.eclipse.ui.IActionBars)
	 */
	public boolean fillActionBars(IActionBars theActionBars) {
		if (genericDelete.isEnabled())
			theActionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), genericDelete);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.actions.ICommonActionProvider#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public boolean fillContextMenu(IMenuManager aMenu) {
		boolean actionsAdded = false;
	/*	if (renameAction.isEnabled()) {
			aMenu.appendToGroup(ICommonMenuConstants.COMMON_MENU_EDIT_ACTIONS, renameAction);
			actionsAdded = true;
		}
		if (genericDelete.isEnabled()) {
			aMenu.appendToGroup(ICommonMenuConstants.COMMON_MENU_EDIT_ACTIONS, genericDelete);
			actionsAdded = true;
		}*/
		return actionsAdded;
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

}