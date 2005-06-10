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

package org.eclipse.jst.common.jdt.internal.integration;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ComponentType;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


public  class JavaProjectMigrationOperation extends AbstractDataModelOperation implements IJavaProjectMigrationDataModelProperties{

	private static String WTP_MODULE_FILE_NAME = ".wtpmodules"; //$NON-NLS-1$
	
    public JavaProjectMigrationOperation(IDataModel model) {
        super(model);
    }

    public IStatus execute(IProgressMonitor monitor, IAdaptable info) {
	
		String projectName = model.getStringProperty(PROJECT_NAME);
		IProject project = ProjectUtilities.getProject(projectName);
		
		IFlexibleProject  fProject = ComponentCore.createFlexibleProject( project );
		if ( !fProject.isFlexible() ){
			if( project.isAccessible() && project.exists()){
				if( shouldMigrate(project)){
					try {
						if( !project.hasNature( IModuleConstants.MODULE_NATURE_ID ) ){
							addModuleCoreNature( project );
						}
						migrate( project );
					} catch (CoreException e) {
						Logger.getLogger().log( e );
					}
				}
			}
		}
        return OK_STATUS;
    }
		
	protected boolean shouldMigrate(IProject project){
		try {
			if( project.hasNature(JavaCore.NATURE_ID) )
				return true;
		} catch (CoreException e) {
			Logger.getLogger().log( e );
		}
		return false;
	}
	
	private void addModuleCoreNature(IProject project) {
		IProjectDescription description = null;
		try {
			description = project.getDescription();
		} catch (CoreException e) {
			Logger.getLogger().log( e );
		}
		String[] currentNatureIds = description.getNatureIds();
		String[] newNatureIds = new String[currentNatureIds.length + 1];
		System.arraycopy(currentNatureIds, 0, newNatureIds, 0, currentNatureIds.length);
		newNatureIds[currentNatureIds.length] = IModuleConstants.MODULE_NATURE_ID;
		description.setNatureIds(newNatureIds);
		try {
			project.setDescription(description, null);
		} catch (CoreException e1) {
			Logger.getLogger().log( e1 );
		}
	}		
	
	
	protected boolean wtpModuleFileExist(IProject project) {
		IFile file = project.getFile(WTP_MODULE_FILE_NAME);
		return file.exists();
	}

	
    protected void createComponent(String aComponentName, IProject aProject) throws CoreException {
        IVirtualComponent component = ComponentCore.createComponent(aProject, aComponentName);
        component.create(0, null);
        IVirtualFolder rootFolder = component.getFolder(new Path("/")); //$NON-NLS-1$  
	
		IJavaProject javaProject = JavaCore.create( aProject );
		try {
			IClasspathEntry[] entries = javaProject.getRawClasspath();
			for( int i=0; i< entries.length; i++){
				if( entries[i].getEntryKind() ==  IClasspathEntry.CPE_SOURCE){
					IPath path = entries[i].getPath().removeFirstSegments(1);
					if( path.isEmpty() ){
						path = new Path("/");
					}
					IPath out = entries[i].getOutputLocation();
					IVirtualFolder javaSourceFolder = component.getFolder( out );
					javaSourceFolder.createLink( path, 0, null);
				}
			}
		}catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
		
		setupComponentType(aComponentName, aProject, IModuleConstants.JST_UTILITY_MODULE);
    }
	
    protected void setupComponentType(String aComponentName, IProject aProject, String typeID) {
        IVirtualComponent component = ComponentCore.createComponent(aProject, aComponentName);
        ComponentType componentType = ComponentcoreFactory.eINSTANCE.createComponentType();
        componentType.setComponentTypeId(typeID);
        StructureEdit.setComponentType(component, componentType);
    }
	
	protected boolean migrate(IProject project) {
		IProject currentProject = project;
		if (wtpModuleFileExist(project))
			return false;
		
		try {
			createComponent(project.getName(), project);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}		

}