/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration.actions;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.common.util.CommonUtil;
import org.eclipse.jst.j2ee.internal.actions.AbstractActionDelegate;
import org.eclipse.jst.j2ee.internal.migration.AbstractJ2EEMigrationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.wst.common.ui.WTPUIPlugin;

import com.ibm.wtp.common.logger.proxy.Logger;


public abstract class J2EEMigrationActionDelegate extends AbstractActionDelegate implements IJ2EEMigrationConstants {
	public static IStructuredSelection getCurrentSelection() {
		IWorkbenchWindow window = J2EEUIPlugin.getActiveWorkbenchWindow();
		if (window != null) {
			ISelection selection = window.getSelectionService().getSelection();
			if (selection instanceof IStructuredSelection) {
				return (IStructuredSelection) selection;
			}

		}
		return null;
	}

	/**
	 * Constructor for J2EEMigrationActionDelegate.
	 */
	public J2EEMigrationActionDelegate() {
		super();
		setAllowsMultiSelect(true);
	}

	private boolean checkNoEditorsOpen(Shell shell) {
		if (isEditorOpen()) {
			MessageDialog.openError(shell, OPEN_EDITORS_TITLE, INFORM_OPEN_EDITORS);
			return false;
		}
		return true;
	}

	protected boolean confirmProceed(Shell shell) {
		return MessageDialog.openConfirm(shell, IJ2EEMigrationConstants.CONFIRMATION_TITLE, getConfirmationMessage());
	}


	//	private void refreshJ2EEView(List projects) {
	//		if (projects.isEmpty() || !J2EERoot.hasInstance()) return;
	//		IProject project = null;
	//		for (int i = 0; i < projects.size(); i++) {
	//			project = (IProject) projects.get(i);
	//			refreshJ2EEView(project);
	//		}
	//	}

	//	/**
	//	 * Method refreshJ2EEView.
	//	 * @param project
	//	 */
	//	private void refreshJ2EEView(IProject project) {
	//		J2EERoot root = J2EERoot.instance();
	//		EObject obj = (EObject) root.getNodeFor(project);
	//		if (obj == null)
	//			return;
	//		AdapterFactory factory =
	// ((ComposedAdapterFactory)root.getAdapterFactory()).getFactoryForType(obj);
	//		if (factory != null) {
	//			Iterator adapters = obj.eAdapters().iterator();
	//			while (adapters.hasNext()) {
	//				Adapter adapter = (Adapter) adapters.next();
	//				if (adapter instanceof ItemProviderAdapter &&
	//					((ItemProviderAdapter)adapter).getAdapterFactory() == factory) {
	//					//Send a bogus fire to update the viewer.
	//					//check for update
	//					((ItemProviderAdapter)adapter).fireNotifyChanged(new
	// NotificationImpl(Notification.ADD,obj,null, -1));
	//				}
	//			}
	//		}
	//	}

	protected abstract AbstractJ2EEMigrationOperation createMigrationOperation(List projects);

	protected IRunnableWithProgress createRunnableWithProgress(AbstractJ2EEMigrationOperation op, List projects) {
		return WTPUIPlugin.getRunnableWithProgress(op);
	}

	/**
	 * The message to be displayed in a confirmation prompt to allow migration to proceed
	 */
	protected abstract String getConfirmationMessage();

	protected boolean isEditorOpen() {
		IWorkbench workbench = WTPUIPlugin.getDefault().getWorkbench();
		IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
		for (int i = 0; i < windows.length; i++) {
			IWorkbenchPage[] pages = windows[i].getPages();
			for (int x = 0; x < pages.length; x++) {
				IEditorReference refs[] = pages[x].getEditorReferences();
				if (refs != null && refs.length > 0)
					return true;
			}
		}
		return false;
	}

	/**
	 * @see org.eclipse.jst.j2ee.common.actions.AbstractActionDelegate#isSupportedAction(Object)
	 */
	protected boolean isSupportedAction(Object element) {
		return CommonUtil.isDeploymentDescriptorRoot(element, true);
	}

	/**
	 * @see org.eclipse.jst.j2ee.common.actions.AbstractActionDelegate#primRun(IProject, Shell)
	 */
	protected void primRun(Shell shell) {
		if (!checkNoEditorsOpen(shell))
			return;
		if (!confirmProceed(shell))
			return;
		// Replace this with the actual selected projects.
		List projects = getProjects();
		if (projects == null || projects.isEmpty())
			return;

		AbstractJ2EEMigrationOperation operation = createMigrationOperation(projects);
		IRunnableWithProgress runnable = createRunnableWithProgress(operation, projects);
		IRunnableContext ctx = new ProgressMonitorDialog(shell);
		try {
			ctx.run(false, false, runnable);
		} catch (InvocationTargetException e) {
			Logger.getLogger().logError(e);
			//Need UI to handle exception here
		} catch (InterruptedException e) {
		}
		//TODO - The J2EE Explorer refreshes on resource changes. If it does not then the refresh
		// needs to be fixed.
		//refreshJ2EEView(projects);
		MigrationStatusDialog.openDialog(shell, null, null, operation.getMigrationStatus());
	}


}

