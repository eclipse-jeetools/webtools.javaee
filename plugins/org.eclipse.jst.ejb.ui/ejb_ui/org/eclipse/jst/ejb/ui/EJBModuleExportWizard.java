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
package org.eclipse.jst.ejb.ui;

import org.eclipse.jst.ejb.ui.internal.wizard.EJBExportPage;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBModuleExportOperation;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEArtifactExportWizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;

/**
 * <p>
 * Wizard used to export J2EE Enterprise Java Bean module structures 
 * from the Eclipse Workbench to a deployable Enterprise Java Bean 
 * Archive *.jar file.  
 * </p>
 */
public final class EJBModuleExportWizard extends J2EEArtifactExportWizard implements IExportWizard {

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, 
	 * no model instance, and no operation instance. The model and 
	 * operation will be created as needed.
	 * </p>
	 */
	public EJBModuleExportWizard() {
		super();
	}

	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls
	 * and interface with the operation.
	 * </p>
	 * @param model The model parameter is used to pre-populate wizard controls and interface with the operation
	 */
	public EJBModuleExportWizard(EJBModuleExportDataModel model) {
		super(model);
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * <p>
	 * Overridden to return an {@link EJBModuleExportDataModel}.
	 * </p>
	 *  
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		return new EJBModuleExportDataModel(); 
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * <p>
	 * Returns an {@link EJBModuleExportOperation} using the model either
	 * supplied in the constructor or created from {@see #createDefaultModel()}.
	 * </p>
	 * @return Returns the operation to be executed when the Wizard completes.
	 */
	protected WTPOperation createBaseOperation() {
		return new EJBModuleExportOperation(getSpecificModel());
	}

	/**
	 * <p>
	 * Adds the following pages:
	 * <ul>
	 * 	<li> {@link EJBExportPage} as the main wizard page ({@link #MAIN_PG}) 
	 * </ul>
	 * </p>
	 */
	public void doAddPages() {
		addPage(new EJBExportPage(getSpecificModel(), MAIN_PG, getSelection()));
	}

	/**
	 * {@inheritDoc}   
	 * 
	 * <p>
	 * Sets up the default wizard page image. 
	 * </p>
	 */
	protected void doInit() {
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EJB_EXPORT_WIZARD_BANNER));
	}
	
	private EJBModuleExportDataModel getSpecificModel() {
		return (EJBModuleExportDataModel) getModel();
	}

}