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
package org.eclipse.jst.j2ee.ui;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.ui.IImportWizard;
import org.eclipse.wst.common.frameworks.internal.AdaptabilityUtility;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.validation.internal.operations.ValidatorSubsetOperation;

/**
 * <p>
 * Serves as a base class for Wizards which import J2EE module structures into Eclipse projects.
 * </p>
 * <p> 
 * Import wizards must define the following methods:
 * <ul>
 * 	<li>{@link #getImportOperation()}
 *  <li>{@link #getModuleValidatorStrings()}
 * </ul>
 * </p> 
 * <p>
 * And optionally, they may override the following methods from 
 * {@see org.eclipse.jst.j2ee.internal.wizard.J2EEArtifactImportWizard}:
 * <ul>
 * 	<li>{@link #getFinalPerspectiveID()}
 * 	<li>{@link #doInit()()}
 * 	<li>{@link #doDispose()()}
 * </ul>
 */
public abstract class J2EEModuleImportWizard extends J2EEArtifactImportWizard implements IImportWizard, IExecutableExtension {
	
	private static final String[] VALIDATOR_STRINGS = new String[]{"org.eclipse.jst.j2ee.internal.validation.UIEarValidator"}; //$NON-NLS-1$

	private static final Class IPROJECT_CLASS = IProject.class;

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, 
	 * no model instance, and no operation instance. The model and 
	 * operation will be created as needed.
	 * </p>
	 */
	public J2EEModuleImportWizard() {
		super();
		setWindowTitle(J2EEUIMessages.getResourceString("38")); //$NON-NLS-1$
	}
	
	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls
	 * and interface with the operation.
	 * </p>
	 * @param model The model parameter is used to pre-populate wizard controls and interface with the operation
	 */
	public J2EEModuleImportWizard(J2EEModuleImportDataModel model) {
		super(model);
		setWindowTitle(J2EEUIMessages.getResourceString("38"));//$NON-NLS-1$ 
	}	
	

	
	/**
	 * <p>
	 * Creates an Import Operation using {@link #getImportOperation()} and wraps it 
	 * to run validation once the module has been imported.
	 * </p> 
	 * @return Returns a wrapper operation around the result of getImportOperation() that also runs validation as defined in getModuleValidatorStrings()
	 * @see #getImportOperation()
	 * @see #getModuleValidatorStrings()
	 */
	protected final WTPOperation createOperation() { 
		return new WTPOperation() {
			protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
				WTPOperation importOp = getImportOperation();
				if(importOp == null)
					return;
				importOp.run(monitor);
				
				J2EEModuleImportDataModel importModel = (J2EEModuleImportDataModel) getImportOperation().getOperationDataModel();
				IProject moduleProject = importModel.getProject();
				ValidatorSubsetOperation moduleValidator = new ValidatorSubsetOperation(moduleProject, true, false);
				moduleValidator.setValidators(getModuleValidatorStrings());
				moduleValidator.run(monitor);
				
				if (importModel.getBooleanProperty(J2EEModuleImportDataModel.ADD_TO_EAR)) {
					IProject earProject = importModel.getJ2EEModuleCreationDataModel().getApplicationCreationDataModel().getTargetProject();
					ValidatorSubsetOperation earValidator = new ValidatorSubsetOperation(earProject, true, false);
					earValidator.setValidators(getEarValidatorStrings());
					earValidator.run(monitor);
				}
			}
		};
	}

	/**
	 * 
	 * @return The appropriate import operation for the specific J2EE model type
	 */
	protected abstract WTPOperation getImportOperation();

	/**
	 * <p>
	 * The Import Wizards can run arbitrary validators once the module has 
	 * been created. These validators ensure that the structure created 
	 * by the Import operation and the contents of that structure are valid. 
	 * Any errors will be announced to the Problems view in Eclipse.
	 * </p>
	 * @return An array of validator IDs that should be used for this module type
	 */
	protected abstract String[] getModuleValidatorStrings(); 
	
	/**
	 * <p>
	 * Uses the selection supplied from the {@link #init(IWorkbench, IStructuredSelection)} 
	 * method to set the associated EAR Module if an EAR artifact was selected.
	 * </p>
	 * @param importModel The Module Import data model to have its J2EEModuleImportDataModel.EAR_PROJECT field set.
	 */
	protected final void updateEARToModelFromSelection(J2EEModuleImportDataModel importModel) {
		/* Set the default ear selected if a ear is selected */
		try {			
			if (getSelection() != null && !getSelection().isEmpty()) {
				IProject targetEARProject = (IProject) AdaptabilityUtility.getAdapter(getSelection().getFirstElement(), IPROJECT_CLASS);
				if (targetEARProject != null && targetEARProject.hasNature(IEARNatureConstants.NATURE_ID))
					importModel.setProperty(J2EEModuleImportDataModel.EAR_PROJECT, targetEARProject.getName());
			}
		} catch (Exception e) {
		}
	}  

	private String[] getEarValidatorStrings() {
		return VALIDATOR_STRINGS;
	} 
 
}