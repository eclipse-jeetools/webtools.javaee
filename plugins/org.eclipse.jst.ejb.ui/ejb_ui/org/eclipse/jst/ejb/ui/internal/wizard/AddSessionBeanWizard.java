/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.ejb.ui.internal.plugin.EJBUIPlugin;
import org.eclipse.jst.j2ee.ejb.internal.operations.NewSessionBeanClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

public class AddSessionBeanWizard extends NewEnterpriseBeansWizard {


	private static final String PAGE_ONE = "pageOne"; //$NON-NLS-1$
	private static final String PAGE_TWO = "pageTwo"; //$NON-NLS-1$
	
	private AddSessionBeanWizardPage page2;
	private NewSessionBeanClassWizardPage page1;

	public AddSessionBeanWizard(IDataModel model) {
		super(model);
		setWindowTitle(IEjbWizardConstants.ADD_SESSION_BEANS_WIZARD_PAGE_TITLE);
		setDefaultPageImageDescriptor(getWizBan());
	}

	private ImageDescriptor getWizBan() {
		URL url = (URL) J2EEPlugin.getDefault().getImage("session_bean_wiz"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}

	public AddSessionBeanWizard() {
		this(null);
	}

	@Override
	protected void doAddPages() {
		page1 = new NewSessionBeanClassWizardPage(
				getDataModel(),
				PAGE_ONE,
				IEjbWizardConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IEjbWizardConstants.ADD_SESSION_BEANS_WIZARD_PAGE_TITLE, 
				J2EEProjectUtilities.EJB);
		addPage(page1);
		page2 = new AddSessionBeanWizardPage(getDataModel(), PAGE_TWO);
		addPage(page2);
	}

	@Override
	protected IDataModelProvider getDefaultProvider() {
		return (IDataModelProvider) new NewSessionBeanClassDataModelProvider();
	}

	@Override
	public boolean canFinish() {
		return getDataModel().isValid();
	}

	@Override
	protected void postPerformFinish() throws InvocationTargetException {
		String className = getDataModel().getStringProperty(INewJavaClassDataModelProperties.QUALIFIED_CLASS_NAME);
		IProject p = (IProject) getDataModel().getProperty(INewJavaClassDataModelProperties.PROJECT);
		IJavaProject javaProject = J2EEEditorUtility.getJavaProject(p);
		IFile file;
		try {
			file = (IFile) javaProject.findType(className).getResource();
			openEditor(file);
		} catch (JavaModelException e) {
			Logger.getLogger().log(e);
		}
	}

	private void openEditor(final IFile file) {
		if (file != null) {
			getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					try {
						IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
						IDE.openEditor(page, file, true);
					} catch (PartInitException e) {
						Logger.getLogger().log(e);
					}
				}
			});
		}
	}

}
