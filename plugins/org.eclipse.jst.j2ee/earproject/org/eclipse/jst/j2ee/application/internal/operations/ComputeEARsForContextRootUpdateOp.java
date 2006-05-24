/*******************************************************************************
 * Copyright (c) 2006 BEA Systems, Inc
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     rfrost@bea.com - initial API and impl
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * <code>AbstractDataModelOperation</code> subclass that computes the set of referencing EARs
 * in which the context-root for a given module will be updated in the application.xml file.
 */
public class ComputeEARsForContextRootUpdateOp extends AbstractDataModelOperation implements IUpdateModuleContextRootProperties { 
	
	public ComputeEARsForContextRootUpdateOp() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#execute(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus execute(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        if (monitor != null) {
            monitor.beginTask("", 1);
        }
        
        final IProject project = (IProject)model.getProperty(PROJECT);
        final List earsToUpdate = new ArrayList();
        try {
        	// get the set of all referencing EARs
        	final IProject[] ears = J2EEProjectUtilities.getReferencingEARProjects(project);
        	
        	// for each EAR, check if the current context-root value is equal to the old context-root setting
        	// if it is, update it to reflect the new value
        	for (int i = 0; i < ears.length; i++) {
        		// check if the current value of the context-root for the module
        		// is equal to the value stored in the associated EAR's
        		// application.xml
        		if (currentRootEqualsEARRoot(ears[i], project)) {
        			earsToUpdate.add(ears[i]);
        		}
        	}
        	// Save the list of EARs to update in a nested IDataModel
        	IDataModel nestedModel = DataModelFactory.createDataModel(new ComputeEARsForContextRootUpdateDataModelProvider());
        	nestedModel.setProperty(EARS_TO_UPDATE, earsToUpdate);
        	model.addNestedModel(NESTED_MODEL_ID, nestedModel);

        } catch (Exception e) {
			Logger.getLogger().logError(e);
        } finally {
            if (monitor != null) {
                monitor.done();
            }
        }
		return OK_STATUS;
	}
	
	/*
	 * Determines if the current module context-root is equal to the value in the application.xml.
	 */
	private boolean currentRootEqualsEARRoot(final IProject earProject, final IProject moduleProject) {
		EARArtifactEdit earEdit = null;
		try {
			earEdit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			if (earEdit == null) {
				return false;
			}
			final Application app = earEdit.getApplication();
			final IVirtualComponent comp = ComponentCore.createComponent(moduleProject);
			if (app == null || comp == null) {
				return false;
			}
			final String moduleURI = earEdit.getModuleURI(comp);
			final Module module = app.getFirstModule(moduleURI);
			if (module != null && module instanceof WebModule) {
				final Properties props = comp.getMetaProperties();
				if ((props != null) && (props.containsKey(J2EEConstants.CONTEXTROOT))) {
					return ((WebModule) module).getContextRoot().equals(props.getProperty(J2EEConstants.CONTEXTROOT));
				}
			}
		} finally {
			if (earEdit != null) {
				earEdit.dispose();
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#redo(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// no-op
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#undo(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// no-op
		return null;
	}

}
