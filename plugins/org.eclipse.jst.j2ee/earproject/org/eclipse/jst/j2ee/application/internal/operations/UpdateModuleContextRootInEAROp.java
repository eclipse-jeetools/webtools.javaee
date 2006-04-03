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

import java.util.List;

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
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * <code>AbstractDataModelOperation</code> subclass that updates the context-root for a given
 * module within the application.xml files of all containing EARs.
 */
public class UpdateModuleContextRootInEAROp extends AbstractDataModelOperation implements IUpdateModuleContextRootProperties {
	
	public UpdateModuleContextRootInEAROp() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#execute(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus execute(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        if(monitor != null) {
            monitor.beginTask("", 1);
        }
        
        try {
        	final String newContextRoot = model.getStringProperty(CONTEXT_ROOT);
        	final IProject project = (IProject) model.getProperty(PROJECT);
        	final IDataModel nestedModel = model.getNestedModel(NESTED_MODEL_ID);
        	if (nestedModel != null) {
            	List earsToUpdate= (List) nestedModel.getProperty(EARS_TO_UPDATE);
            	if (newContextRoot != null && earsToUpdate != null && project != null) {
            		// update each specified EAR
            		for (int i = 0; i < earsToUpdate.size(); i++) {
            			updateEARContextRoot((IProject) earsToUpdate.get(i), project, newContextRoot, monitor);
            		}
            	}
        	}
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
	 * Updates the context-root element int the application.xml of the specified EAR for the specified
	 * module project to the new value. 
	 */
	private void updateEARContextRoot(final IProject ear, final IProject project, final String newContextRoot,
			final IProgressMonitor monitor) {
		EARArtifactEdit earEdit = null;
		try {
			earEdit = EARArtifactEdit.getEARArtifactEditForWrite(ear);
			if (earEdit != null) {
				final Application application = earEdit.getApplication();
				final IVirtualComponent comp = ComponentCore.createComponent(project);
				if (comp == null) {
					return;
				}
				final String moduleURI = AddComponentToEnterpriseApplicationDataModelProvider.getComponentURI(comp);
				final Module module = application.getFirstModule(moduleURI);
				if (module != null && module instanceof WebModule) {
					((WebModule) module).setContextRoot(newContextRoot);
				}
				earEdit.saveIfNecessary(monitor);
			}
		} finally {
			if (earEdit != null) {
				earEdit.dispose();
			}
		}
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
