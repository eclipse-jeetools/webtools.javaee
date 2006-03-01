/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
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
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProvider;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyOperation;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


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
						&& !project.equals(newProject) && !JemProjectUtilities.isBinaryProject(project)) {
						
						IDataModel model = DataModelFactory.createDataModel( new JARDependencyDataModelProvider());
						
						model.setIntProperty(JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE,
									JARDependencyDataModelProperties.JAR_MANIPULATION_INVERT);
						
						model.setProperty(JARDependencyDataModelProperties.PROJECT_NAME,
									project.getName());
						model.setProperty(JARDependencyDataModelProperties.OPPOSITE_PROJECT_NAME,
									newProject.getName());
						model.setProperty(JARDependencyDataModelProperties.EAR_PROJECT_NAME,
									earProjects[i].getName() );
						model.setProperty(JARDependencyDataModelProperties.REFERENCED_PROJECT_NAME,
									oldProject.getName());
						childOperations.add(new JARDependencyOperation(model));
					}					
				}
			}

		}
	}

	private void executeChildOperations() {
		monitor.beginTask("", childOperations.size()); //$NON-NLS-1$
		for (int i = 0; i < childOperations.size(); i++) {
			JARDependencyOperation op = (JARDependencyOperation) childOperations.get(i);
			try {
				op.execute(new SubProgressMonitor(monitor, 1), null);
			} catch (Exception e) {
				Logger.getLogger().logError(e);
			}
		}
	}


}