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
 * Created on Jun 8, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.internal.registry.ConfigurationElement;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.plugin.CommonEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.internal.common.frameworks.ui.WTPWizard;
import org.eclipse.wst.validation.internal.operations.ValidatorSubsetOperation;

/**
 * @author jsholl
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public abstract class J2EEModuleImportWizard extends WTPWizard implements IExecutableExtension, IImportWizard {

	public J2EEModuleImportWizard() {
		super();
		setWindowTitle(J2EEUIMessages.getResourceString("38")); //$NON-NLS-1$
	}

	public J2EEModuleImportWizard(J2EEModuleImportDataModel dm) {
		super(dm);
		setWindowTitle(J2EEUIMessages.getResourceString("38"));//$NON-NLS-1$ 
	}

	protected WTPOperation createOperation() {
		final WTPOperation importOp = getImportOperation();
		return new WTPOperation() {
			protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
				importOp.run(monitor);
				IProject moduleProject = getModuleImportDataModel().getProject();
				ValidatorSubsetOperation moduleValidator = new ValidatorSubsetOperation(moduleProject, true, false);
				moduleValidator.setValidators(getModuleValidatorStrings());
				moduleValidator.run(monitor);
				if (getModuleImportDataModel().getBooleanProperty(J2EEModuleImportDataModel.ADD_TO_EAR)) {
					IProject earProject = getModuleImportDataModel().getJ2EEModuleCreationDataModel().getEarProjectCreationDataModel().getTargetProject();
					ValidatorSubsetOperation earValidator = new ValidatorSubsetOperation(earProject, true, false);
					earValidator.setValidators(getEarValidatorStrings());
					earValidator.run(monitor);
				}
			}
		};
	}

	protected abstract WTPOperation getImportOperation();

	protected abstract String[] getModuleValidatorStrings();

	protected String[] getEarValidatorStrings() {
		return new String[]{"org.eclipse.jst.j2ee.internal.validation.UIEarValidator"}; //$NON-NLS-1$ 
	}

	protected J2EEModuleImportDataModel getModuleImportDataModel() {
		return (J2EEModuleImportDataModel) model;
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
	}

	protected String getFinalPerspectiveID() {
		return "org.eclipse.jst.j2ee.J2EEPerspective"; //$NON-NLS-1$
	}

	protected boolean prePerformFinish() {
		if (!CommonEditorUtility.promptToSaveAllDirtyEditors()) {
			return false;
		}
		if (CommonEditorUtility.getDirtyEditors().length != 0) { // all checkboxes were not selected
			return false;
		}
		return super.prePerformFinish();
	}

	protected void postPerformFinish() throws InvocationTargetException {
		super.postPerformFinish();
		IConfigurationElement element = new ConfigurationElement() {
			public String getAttribute(String aName) {
				if (aName.equals("finalPerspective")) { //$NON-NLS-1$
					return getFinalPerspectiveID();
				}
				return super.getAttribute(aName);
			}

		};
		BasicNewProjectResourceWizard.updatePerspective(element);
	}

}