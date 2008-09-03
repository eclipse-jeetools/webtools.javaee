/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.OPEN_IN_EDITOR;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.PROJECT;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.QUALIFIED_CLASS_NAME;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.ejb.ui.internal.plugin.EJBUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizard;

public abstract class NewEnterpriseBeanWizard extends DataModelWizard implements INewWizard {

	/**
	 * @param model
	 */
	public NewEnterpriseBeanWizard(IDataModel model) {
		super(model);
	}
	
	/**
	 * Default constructor
	 */
	public NewEnterpriseBeanWizard() {
		super();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		getDataModel();
	}
	
	protected IStructuredSelection getCurrentSelection() {
		IWorkbenchWindow window = J2EEUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();
		if (window != null) {
			ISelection selection = window.getSelectionService().getSelection();
			if (selection instanceof IStructuredSelection) {
				return (IStructuredSelection) selection;
			}
		}
		return null;
	}

	@Override
	protected void postPerformFinish() throws InvocationTargetException {
		String className = getDataModel().getStringProperty(QUALIFIED_CLASS_NAME);
		IProject p = (IProject) getDataModel().getProperty(PROJECT);
		IJavaProject javaProject = J2EEEditorUtility.getJavaProject(p);
		IFile file;
		try {
			file = (IFile) javaProject.findType(className).getResource();
			openEditor(file);
		} catch (JavaModelException e) {
			EJBUIPlugin.logError(e);
		}
	}

	private void openEditor(final IFile file) {
		if (getDataModel().getBooleanProperty(OPEN_IN_EDITOR)) {
			if (file != null) {
				getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						try {
							IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
							IDE.openEditor(page, file, true);
						} catch (PartInitException e) {
							EJBUIPlugin.logError(e);
						}
					}
				});
			}
		}
	}

	@Override
	public boolean canFinish() {
		return getDataModel().isValid();
	}

}
