/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Nov 19, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code
 * and Comments
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.archive.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.common.UpdateProjectClasspath;
import org.eclipse.jst.j2ee.internal.project.ManifestFileCreationAction;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationOperationEx;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class JavaUtilityComponentCreationOperationEx extends ComponentCreationOperationEx implements IJavaComponentCreationDataModelProperties{
	/**
	 * @param dataModel
	 */
	public JavaUtilityComponentCreationOperationEx(IDataModel dataModel) {
		super(dataModel);
	}


	 protected void execute(String componentType, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

	    super.execute(IModuleConstants.JST_UTILITY_MODULE, monitor);
		createManifest(monitor);
	    addSrcFolderToProject();
	 }
	 
	 protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
	    execute(IModuleConstants.JST_UTILITY_MODULE, monitor);
	 }
  
	protected void createComponent() {
			   	
   	
		IVirtualComponent component = ComponentCore.createComponent(getProject(), getComponentDeployName());
		try {
			component.create(0, null);
			//create and link javaSource Source Folder
			IVirtualFolder javaSourceFolder = component.getFolder(new Path("/")); //$NON-NLS-1$		
			javaSourceFolder.createLink(new Path("/" + getJavaSourceFolder()), 0, null);
			
			//create and link META-INF folder
			IVirtualFolder metaInfFolder = component.getFolder(new Path("/" + J2EEConstants.META_INF)); //$NON-NLS-1$		
			metaInfFolder.createLink(new Path("/" + getComponentName() + "/" + J2EEConstants.META_INF), 0, null);
		}
		catch (CoreException e) {
			Logger.getLogger().log(e);
		}
	}
	   
	String getJavaSourceFolder(){
		return model.getStringProperty(JAVASOURCE_FOLDER);
	}
	
	String getManifestFolder(){
		return model.getStringProperty(MANIFEST_FOLDER);
	}
	
	protected void createManifest(IProgressMonitor monitor) {

		IContainer container = getProject().getFolder( getManifestFolder() );
	
		IFile file = container.getFile( new Path(J2EEConstants.MANIFEST_SHORT_NAME));
		
		try {
			ManifestFileCreationAction.createManifestFile(file, getProject());
		}
		catch (CoreException e) {
			Logger.getLogger().log(e);
		}
		catch (IOException e) {
			Logger.getLogger().log(e);
		}

	}	
	    
	
	private void addSrcFolderToProject() {
		UpdateProjectClasspath update = new UpdateProjectClasspath(getJavaSourceFolder(), getProject());
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationOperationEx#getVersion()
	 */
	protected String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationOperationEx#getProperties()
	 */
	protected List getProperties() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.AbstractOperation#execute(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) {
	    super.execute(IModuleConstants.JST_UTILITY_MODULE, monitor, info);
		createManifest(monitor);
	    addSrcFolderToProject();
		return OK_STATUS;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.AbstractOperation#redo(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.AbstractOperation#undo(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) {
		// TODO Auto-generated method stub
		return null;
	}	
}