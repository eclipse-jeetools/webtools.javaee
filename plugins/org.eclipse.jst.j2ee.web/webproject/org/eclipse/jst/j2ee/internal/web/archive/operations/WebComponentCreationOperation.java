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
/*
 * Created on Nov 6, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class WebComponentCreationOperation extends J2EEComponentCreationOperation {
	public WebComponentCreationOperation(WebComponentCreationDataModel dataModel) {
		super(dataModel);
	}

	public WebComponentCreationOperation() {
		super();
	}


	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		
		String moduleName = (String)operationDataModel.getProperty(WebComponentCreationDataModel.MODULE_NAME);

		
		IFolder moduleFolder = getProject().getFolder( moduleName );

		if (!moduleFolder.exists()) {
			moduleFolder.create(true, true, null);
		}

		IFolder javaSourceFolder = moduleFolder.getFolder( "JavaSource" );
		if (!javaSourceFolder.exists()) {
			javaSourceFolder.create(true, true, null);
		}
		
		IFolder webContentFolder = moduleFolder.getFolder( "WebContent" );
		if (!webContentFolder.exists()) {
			webContentFolder.create(true, true, null); 
		}
		
		IFolder metainf = webContentFolder.getFolder(J2EEConstants.META_INF);
		if (!metainf.exists()) {
			IFolder parent = metainf.getParent().getFolder(null);
			if (!parent.exists()) {
				parent.create(true, true, null);
			}
			metainf.create(true, true, null);
		}
		
		
		IFolder webinf = webContentFolder.getFolder(J2EEConstants.WEB_INF);
		if (!webinf.exists()) {
			webinf.create(true, true, null);
		}
		
		IFolder lib = webinf.getFolder("lib"); //$NON-NLS-1$
		if (!lib.exists()) {
			lib.create(true, true, null);
		}

		
		//should cache wbmodule when created instead of  searching ?
        ModuleCore moduleCore = null;
        WorkbenchComponent wbmodule = null;
        try {
            moduleCore = ModuleCore.getModuleCoreForRead(getProject());
            wbmodule = moduleCore.findWorkbenchModuleByDeployName(operationDataModel.getStringProperty(WebComponentCreationDataModel.MODULE_DEPLOY_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }		


        WebArtifactEdit webEdit = null;
       	try{

       		webEdit = WebArtifactEdit.getWebArtifactEditForWrite( wbmodule );
       		String projPath = getProject().getLocation().toOSString();
       		//projPath += this.getDeploymentDescriptorFolder() + IPath.SEPARATOR + J2EEConstants.WEBAPP_DD_SHORT_NAME;       		

       		
       		projPath += operationDataModel.getProperty( WebComponentCreationDataModel.DD_FOLDER );
       		projPath +=IPath.SEPARATOR + J2EEConstants.WEBAPP_DD_SHORT_NAME;
//       		projPath += IPath.SEPARATOR + moduleName + IPath.SEPARATOR + "WebContent" + IPath.SEPARATOR + J2EEConstants.WEB_INF + IPath.SEPARATOR + J2EEConstants.WEBAPP_DD_SHORT_NAME;

       		

       		
       		IPath webxmlPath = new Path(projPath);
       		boolean b = webxmlPath.isValidPath(webxmlPath.toString());
       		if(webEdit != null) {
       			int moduleVersion = operationDataModel.getIntProperty(WebComponentCreationDataModel.J2EE_MODULE_VERSION);
  			
           		webEdit.createModelRoot( getProject(), webxmlPath, moduleVersion );
       		}
       	}
       	catch(Exception e){
            e.printStackTrace();
       	} finally {
       		if(webEdit != null)
       			webEdit.dispose();
       	}					
	
	
	
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		super.execute( IModuleConstants.JST_WEB_MODULE, monitor );
		addSrcFolderToProject();
	}
    
	public IClasspathEntry[] getClasspathEntries() {

		IClasspathEntry[] sourceEntries = null;
		
		sourceEntries = getSourceClasspathEntries();
		return sourceEntries;
	}
	
	private IClasspathEntry[] getSourceClasspathEntries() {
		String sourceFolder = getJavaSourceSourcePath(getModuleName());
		ArrayList list = new ArrayList();

		list.add(JavaCore.newSourceEntry(getProject().getFullPath().append(sourceFolder)));
		
		IClasspathEntry[] classpath = new IClasspathEntry[list.size()];
		for (int i = 0; i < classpath.length; i++) {
			classpath[i] = (IClasspathEntry) list.get(i);
		}
		return classpath;
	}
	
	private void addSrcFolderToProject() {
		IJavaProject javaProject = JavaCore.create( this.getProject());
		try {
			//javaProject.setOutputLocation(model.getOutputPath(), monitor);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	
	protected  void addResources( WorkbenchComponent component ){
		addResource(component, getModuleRelativeFile(getWebContentSourcePath( getModuleName() ), getProject()), getWebContentDeployPath());
		addResource(component, getModuleRelativeFile(getJavaSourceSourcePath( getModuleName() ), getProject()), getJavaSourceDeployPath());		
	}
	
	/**
	 * @return
	 */
	public String getJavaSourceSourcePath(String moduleName) {
		return "/" + moduleName +"/JavaSource"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getJavaSourceDeployPath() {
		return "/WEB-INF/classes"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getWebContentSourcePath(String moduleName) {
		return "/" + moduleName + "/WebContent"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getWebContentDeployPath() {
		return "/"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation#createProjectStructure()
	 */
	protected void createProjectStructure() throws CoreException {
		// TODO Auto-generated method stub
		
	}
}