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
import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.project.ManifestFileCreationAction;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationOperation;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.common.modulecore.resources.IVirtualContainer;
import org.eclipse.wst.common.modulecore.resources.IVirtualFolder;

public class JavaUtilityComponentCreationOperation extends ComponentCreationOperation {
	/**
	 * @param dataModel
	 */
	public JavaUtilityComponentCreationOperation(JavaComponentCreationDataModel dataModel) {
		super(dataModel);
	}


	 protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
	 	JavaComponentCreationDataModel dm = (JavaComponentCreationDataModel)operationDataModel;
	    super.execute(IModuleConstants.JST_UTILITY_MODULE, monitor);
		createManifest(monitor);
	    addSrcFolderToProject();
	 }
  
	protected void createComponent() {
			   	
		JavaComponentCreationDataModel dm = (JavaComponentCreationDataModel)operationDataModel;	   	
		IVirtualContainer component = ModuleCore.create(dm.getProject(), dm.getComponentDeployName());
		try {
			component.commit();
			//create and link javaSource Source Folder
			IVirtualFolder javaSourceFolder = component.getFolder(new Path("/")); //$NON-NLS-1$		
			javaSourceFolder.createLink(new Path("/" + dm.getComponentName()), 0, null);	
				    	
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
	    
	private IClasspathEntry[] getClasspathEntries() {
		IClasspathEntry[] sourceEntries = null;
		sourceEntries = getSourceClasspathEntries();
		return sourceEntries;
	}	
		
	private IClasspathEntry[] getSourceClasspathEntries() {
		
	 	JavaComponentCreationDataModel dm = (JavaComponentCreationDataModel)operationDataModel;			
		String sourceFolder = dm.getJavaSourceFolder();
		ArrayList list = new ArrayList();
		list.add(JavaCore.newSourceEntry(dm.getProject().getFullPath().append(sourceFolder)));
		
		IClasspathEntry[] classpath = new IClasspathEntry[list.size()];
		for (int i = 0; i < classpath.length; i++) {
			classpath[i] = (IClasspathEntry) list.get(i);
		}
		return classpath;
	}
	
	private void addSrcFolderToProject() {
	 	JavaComponentCreationDataModel dm = (JavaComponentCreationDataModel)operationDataModel;			
		IJavaProject javaProject = JavaCore.create( dm.getProject());
		try {
	
			IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
			IClasspathEntry[] newEntries = getClasspathEntries();
			
			int oldSize = oldEntries.length;
			int newSize = newEntries.length;
			
			IClasspathEntry[] classpathEnties = new IClasspathEntry[oldSize + newSize];
			int k = 0;
			for (int i = 0; i < oldEntries.length; i++) {
				classpathEnties[i] = oldEntries[i];
				k++;
			}
			for( int j=0; j< newEntries.length; j++){
				classpathEnties[k] = newEntries[j];
				k++;
			}
			javaProject.setRawClasspath(classpathEnties, null);
		}
		catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
	}	
}