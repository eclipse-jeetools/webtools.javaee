/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.application.internal.operations.EARComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.ui.IExportWizard;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/**
 * <p>
 * Wizard used to export J2EE Enterprise Application structures from the Eclipse Workbench to a
 * deployable Enterprise Application Archive *.ear file.
 * </p>
 */
public final class EARComponentExportWizard extends J2EEArtifactExportWizard implements IExportWizard {

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public EARComponentExportWizard() {
		super();
	}

	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls and interface with the operation.
	 * </p>
	 * 
	 * @param model
	 *            The model parameter is used to pre-populate wizard controls and interface with the
	 *            operation
	 */
	public EARComponentExportWizard(IDataModel model) {
		super(model);
	}

    protected IDataModelProvider getDefaultProvider() {
        return new EARComponentExportDataModelProvider();
    }

	/**
	 * <p>
	 * Adds the following pages:
	 * <ul>
	 * <li>{@link EARComponentExportPage}as the main wizard page ({@link #MAIN_PG})
	 * </ul>
	 * </p>
	 */
	public void doAddPages() {
		addPage(new EARComponentExportPage(getDataModel(), MAIN_PG, getSelection()));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Sets up the default wizard page image.
	 * </p>
	 */
	protected void doInit() {
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_EXPORT_WIZARD_BANNER));
	}

}
