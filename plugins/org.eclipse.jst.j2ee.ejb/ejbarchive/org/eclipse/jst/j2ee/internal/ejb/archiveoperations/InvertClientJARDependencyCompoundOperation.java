/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;


public class InvertClientJARDependencyCompoundOperation extends AbstractDataModelOperation {

	protected IProject[] earProjects;
	protected IProject oldProject;
	protected IProject newProject;
	protected List childOperations = null;
	protected IProgressMonitor monitor;

	public InvertClientJARDependencyCompoundOperation(IProject[] referencingEARs,
				IProject anOldProject, IProject aNewProject) {
		
		earProjects = referencingEARs;
		oldProject = anOldProject;
		newProject = aNewProject;
	}


	@Override
	public IStatus execute(IProgressMonitor aMonitor, IAdaptable info) throws ExecutionException {
		monitor = aMonitor;
		createChildOperations();
		executeChildOperations();	
		return OK_STATUS;
	}


	private void createChildOperations() {

		childOperations = new ArrayList();
		for (int i = 0; i < earProjects.length; i++) {
			IVirtualComponent earComponent = ComponentCore.createComponent( earProjects[i]);
			IVirtualReference[] refs = earComponent.getReferences();
			
			for( int j=0; j< refs.length; j++ ){
				IVirtualReference ref = refs[j];
				IVirtualComponent comp = ref.getReferencedComponent();
				if ( comp != null ){
					IProject project = comp.getProject();
					if ( project != null && !project.equals(oldProject) 
						&& !project.equals(newProject) && !JemProjectUtilities.isBinaryProject(project) 
						&& !comp.isBinary()){
						
						UpdateManifestOperation invertOp = new EJBClientManifestUtility().getInvertOperation(
								project, earProjects[0], newProject, oldProject);
						childOperations.add(invertOp);
					}
				}
			}

		}
	}

	private void executeChildOperations() {
		monitor.beginTask("", childOperations.size()); //$NON-NLS-1$
		for (int i = 0; i < childOperations.size(); i++) {
			AbstractDataModelOperation op = (AbstractDataModelOperation) childOperations.get(i);
			try {
				op.execute(new SubProgressMonitor(monitor, 1), null);
			} catch (Exception e) {
				EjbPlugin.logError(e);
			}
		}
	}


}
