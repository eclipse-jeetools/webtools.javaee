/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     rfrost@bea.com - derived from WebLibDependencyPropertiesPage for projects referenced just from a dynamic web project.
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

/**
 * Supports UI manipulation of the published/exported classpath entries for projects referenced by dynamic web projects.
 * @deprecated wtp 3.2
 */
public class WebRefDependencyPropertiesPage extends JARDependencyPropertiesPage {

	public WebRefDependencyPropertiesPage(final IProject project, final J2EEDependenciesPage page) {
		super(project, page);
	}

	@Override
	public Composite createContents(Composite parent) {
		initialize();
		Composite composite = createBasicComposite(parent);
		if (model.getComponent() != null) {
			if (!isValidComponent()) {
				return composite;
			}
			J2EEDependenciesPage.createDescriptionComposite(composite, ManifestUIResourceHandler.Web_Ref_Desc);
			createListGroup(composite);
			tableManager.refresh();
			setEnablement();
		}
	    Dialog.applyDialogFont(parent);
	    postCreateContents();
		return composite;
	}

	@Override
	protected void createTableComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridData gData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gData);
		tableManager = new ClasspathTableManager(this, model, validateEditListener);
		tableManager.setReadOnly(isReadOnly());
		tableManager.fillWebRefComposite(composite);
	}

	@Override
	protected boolean isValidComponent() {
		// must have the Java nature and cannot be a dynamic web project
		boolean isJavaProject = false;
		try {
			isJavaProject = project.hasNature(JavaCore.NATURE_ID);
		} catch (CoreException ce) {}
		if (JavaEEProjectUtilities.isDynamicWebProject(project) || !isJavaProject) {
			propPage.setErrorMessage(ManifestUIResourceHandler.Dynamic_Web_Error); 
			return false;
		} else if (J2EEProjectUtilities.getReferencingWebProjects(project).length == 0) {
			propPage.setErrorMessage(ManifestUIResourceHandler.No_Web_Reference_Error);
			return false;
		}
		return true;
	}
	
	protected void setEnablement() {
		if (tableManager.availableJARsViewer.getTable().getItems().length == 0) {
			tableManager.selectAllButton.setEnabled(false);
			tableManager.deselectAllButton.setEnabled(false);
		} else {
			tableManager.selectAllButton.setEnabled(true);
			tableManager.deselectAllButton.setEnabled(true);
		}
	}
	
	@Override
	protected void handleClasspathChange() {
		super.handleClasspathChange();
		setEnablement();
	}

	@Override
	public boolean performOk() {
		if (model.getComponent() == null || !isValidComponent()) {
			return true;
		}
		if (!isDirty) {
			return true;
		}
		return super.performOk();
	}

}
