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
package org.eclipse.jst.j2ee.navigator.internal.dnd;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;
import org.eclipse.wst.common.frameworks.internal.AdaptabilityUtility;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.navigator.internal.views.NavigatorPlugin;
import org.eclipse.wst.common.navigator.internal.views.dnd.IDropValidator;
import org.eclipse.wst.common.navigator.internal.views.dnd.NavigatorDropActionDelegate;
import org.eclipse.wst.common.navigator.internal.views.dnd.CommonNavigatorDropAdapter;

/**
 * @author jsholl
 *  
 */
public abstract class AddProjectToEarDropAction extends NavigatorDropActionDelegate implements IDropValidator {

	public AddProjectToEarDropAction() {
		super();
	}

	public boolean validateDrop(CommonNavigatorDropAdapter dropAdapter, Object target, int operation, TransferData transferType) {
		if (LocalSelectionTransfer.getInstance().isSupportedType(transferType) || PluginTransfer.getInstance().isSupportedType(transferType)) {
			IProject earProject = (IProject) AdaptabilityUtility.getAdapter(target, IProject.class);
			if (null == earProject || !earProject.isAccessible()) {
				return false;
			}
			EARNatureRuntime earNature = (EARNatureRuntime) J2EENature.getRegisteredRuntime(earProject);
			if (null == earNature) {
				return false;
			}
			int earVersion = earNature.getApplication().getJ2EEVersionID();

			ISelection selection = LocalSelectionTransfer.getInstance().getSelection();
			if (selection == null || !(selection instanceof IStructuredSelection)) {
				return false;
			}
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.isEmpty()) {
				return false;
			}
			Object key = new Object();
			EAREditModel editModel = null;
			try {
				editModel = earNature.getEarEditModelForRead(key);
				Iterator iterator = structuredSelection.iterator();
				while (iterator.hasNext()) {
					Object next = iterator.next();
					IProject projectToAdd = (IProject) AdaptabilityUtility.getAdapter(next, IProject.class);
					if (null == projectToAdd || !projectToAdd.isAccessible()) {
						return false;
					}

					if (!validateProjectToAdd(projectToAdd, earVersion)) {
						return false;
					}

					if (editModel.hasMappingToProject(projectToAdd)) {
						return false;
					}
				}
			} finally {
				if (null != editModel) {
					editModel.releaseAccess(key);
				}
			}
			return true;
		}
		return false;
	}

	protected abstract boolean validateProjectToAdd(IProject projectToAdd, int earVersion);

	protected abstract WTPOperationDataModel getDataModel(IProject earProject, IProject projectToAdd);

	protected void doInit() {
	}

	public boolean run(Object source, Object target) {
		if (source instanceof IStructuredSelection)
			return run((IStructuredSelection) source, (IProject) AdaptabilityUtility.getAdapter(target, IProject.class));
		return false;
	}

	public boolean run(CommonNavigatorDropAdapter dropAdapter, Object source, Object target) {
		return run(source, target);
	}

	protected boolean run(IStructuredSelection selection, IProject earProject) {
		if (selection == null || selection.isEmpty() || earProject == null || !earProject.isAccessible())
			return false;

		for (Iterator selectionIterator = selection.iterator(); selectionIterator.hasNext();) {
			Object sourceObject = selectionIterator.next();

			IProject projectToAdd = (IProject) AdaptabilityUtility.getAdapter(sourceObject, IProject.class);
			if (projectToAdd != null) {
				try {
					WTPOperationDataModel dataModel = getDataModel(earProject, projectToAdd);
					dataModel.getDefaultOperation().run(null);
				} catch (InvocationTargetException e) {
					NavigatorPlugin.log(e.toString());
				} catch (InterruptedException e) {
					NavigatorPlugin.log(e.toString());
				}
			}
		}
		return true;
	}

}
