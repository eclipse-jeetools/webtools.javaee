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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.common.UpdateProjectClasspath;
import org.eclipse.jst.j2ee.internal.project.ManifestFileCreationAction;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationOperation;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

public class JavaUtilityComponentCreationOperation extends ComponentCreationOperation {
	/**
	 * @param dataModel
	 */
	public JavaUtilityComponentCreationOperation(JavaComponentCreationDataModel dataModel) {
		super(dataModel);
	}


	 protected void execute(String componentType, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
	 	JavaComponentCreationDataModel dm = (JavaComponentCreationDataModel)operationDataModel;
	    super.execute(IModuleConstants.JST_UTILITY_MODULE, monitor);
		createManifest(monitor);
	    addSrcFolderToProject();
	 }
	 
	 protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
	    execute(IModuleConstants.JST_UTILITY_MODULE, monitor);
	 }
  
	protected void createComponent() {
			   	
		JavaComponentCreationDataModel dm = (JavaComponentCreationDataModel)operationDataModel;	   	
		IVirtualComponent component = ComponentCore.createComponent(dm.getProject(), dm.getComponentDeployName());
		try {
			component.create(0, null);
			//create and link javaSource Source Folder
			IVirtualFolder javaSourceFolder = component.getFolder(new Path("/")); //$NON-NLS-1$		
			javaSourceFolder.createLink(new Path("/" + dm.getJavaSourceFolder()), 0, null);
			
			//create and link META-INF folder
			IVirtualFolder metaInfFolder = component.getFolder(new Path("/" + J2EEConstants.META_INF)); //$NON-NLS-1$		
			metaInfFolder.createLink(new Path("/" + dm.getComponentName() + "/" + J2EEConstants.META_INF), 0, null);
		}
		catch (CoreException e) {
			Logger.getLogger().log(e);
		}
	}
	   
	protected void createManifest(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
	 	JavaComponentCreationDataModel dm = (JavaComponentCreationDataModel)operationDataModel;		
		String manifestFolder = dm.getStringProperty(J2EEComponentCreationDataModel.MANIFEST_FOLDER);
		IContainer container = dm.getProject().getFolder( manifestFolder );
	
		IFile file = container.getFile( new Path(J2EEConstants.MANIFEST_SHORT_NAME));
		
		try {
			ManifestFileCreationAction.createManifestFile(file, dm.getProject());
		}
		catch (CoreException e) {
			Logger.getLogger().log(e);
		}
		catch (IOException e) {
			Logger.getLogger().log(e);
		}

	}	
	    
	
	private void addSrcFolderToProject() {
	 	JavaComponentCreationDataModel dm = (JavaComponentCreationDataModel)operationDataModel;			
		UpdateProjectClasspath update = new UpdateProjectClasspath(dm.getJavaSourceFolder(), dm.getProject());
	}	
}