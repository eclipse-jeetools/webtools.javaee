/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Sep 2, 2003
 *  
 */
package org.eclipse.jst.j2ee.internal.common.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.JavaProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.ClassPathSelection;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation;
import org.eclipse.jst.j2ee.internal.common.ClasspathModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;


public class JARDependencyOperation extends WTPOperation {
	public JARDependencyOperation(JARDependencyDataModel dataModel) {
		super(dataModel);
	}

	private void saveModel(ClasspathModel model, IProgressMonitor monitor) throws InvocationTargetException, InterruptedException, CoreException {
		if (!model.isDirty())
			return;
		validateEdit(model);
		monitor.beginTask("", 2); //$NON-NLS-1$
		org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation mfOperation = createManifestOperation(model);
		IHeadlessRunnableWithProgress buildPathOperation = createBuildPathOperation(model);
		mfOperation.run(new SubProgressMonitor(monitor, 1));
		buildPathOperation.run(new SubProgressMonitor(monitor, 1));
	}

	/**
	 * @param model
	 */
	protected void validateEdit(ClasspathModel model) throws CoreException {
		Set affectedFiles = model.getAffectedFiles();
		IFile[] files = (IFile[]) affectedFiles.toArray(new IFile[affectedFiles.size()]);
		IStatus result = J2EEPlugin.getWorkspace().validateEdit(files, null);
		if (!result.isOK())
			throw new CoreException(result);
	}

	protected UpdateJavaBuildPathOperation createBuildPathOperation(ClasspathModel model) {
		IJavaProject javaProject = JavaProjectUtilities.getJavaProject(model.getProject());
		return new UpdateJavaBuildPathOperation(javaProject, model.getClassPathSelection());
	}

	private org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation createManifestOperation(ClasspathModel model) {
		UpdateManifestDataModel updateManifestDataModel = new UpdateManifestDataModel();
		updateManifestDataModel.setProperty(UpdateManifestDataModel.PROJECT_NAME, model.getProject().getName());
		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModel.MERGE, false);
		updateManifestDataModel.setProperty(UpdateManifestDataModel.JAR_LIST, UpdateManifestDataModel.convertClasspathStringToList(model.getClassPathSelection().toString()));
		return new UpdateManifestOperation(updateManifestDataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.operations.HeadlessJ2EEOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
//	protected final void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
//		JARDependencyDataModel dataModel = (JARDependencyDataModel) operationDataModel;
//		ClasspathModel model = new ClasspathModel(null);
//		model.setProject(dataModel.getProject());
//		//model.setSelectedEARNature(EARNatureRuntime.getRuntime(dataModel.getEARProject()));
//		try {
//			int jarManipulationType = dataModel.getIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE);
//			switch (jarManipulationType) {
//				case JARDependencyDataModel.JAR_MANIPULATION_ADD : {
//					List jarList = (List) dataModel.getUpdateManifestDataModel().getProperty(UpdateManifestDataModel.JAR_LIST);
//					if (!jarList.isEmpty()) {
//						for (int i = 0; i < jarList.size(); i++) {
//							String jarName = (String) jarList.get(i);
//							model.selectDependencyIfNecessary(jarName);
//						}
//					} else {
//						model.selectDependencyIfNecessary(dataModel.getReferencedProject());
//					}
//				}
//					break;
//				case JARDependencyDataModel.JAR_MANIPULATION_REMOVE : {
//					List jarList = (List) dataModel.getUpdateManifestDataModel().getProperty(UpdateManifestDataModel.JAR_LIST);
//					for (int i = 0; i < jarList.size(); i++) {
//						String jarName = (String) jarList.get(i);
//						model.removeDependency(jarName);
//					}
//				}
//					break;
//				case JARDependencyDataModel.JAR_MANIPULATION_INVERT :
//					ClassPathSelection classPathSelection = model.getClassPathSelection();
//					if (classPathSelection != null)
//						classPathSelection.invertClientJARSelection(dataModel.getReferencedProject(), dataModel.getOppositeProject());
//					break;
//			}
//			if (model.isDirty())
//				saveModel(model, monitor);
//		} finally {
//			if (model != null)
//				model.dispose();
//			if (monitor != null)
//				monitor.done();
//		}
//	}
	
	protected final void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		JARDependencyDataModel dataModel = (JARDependencyDataModel) operationDataModel;
		
		IProject proj = dataModel.getProject();
		IProject refproj = dataModel.getReferencedProject();

		try {
			int jarManipulationType = dataModel.getIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE);
			switch (jarManipulationType) {
				case JARDependencyDataModel.JAR_MANIPULATION_ADD : {
					updateProjectDependency(proj, refproj);
				}
				
				break;
				case JARDependencyDataModel.JAR_MANIPULATION_REMOVE : 

					break;
				case JARDependencyDataModel.JAR_MANIPULATION_INVERT :

					break;
			}
		} finally {

			if (monitor != null)
				monitor.done();
		}
	}	
	
	private IClasspathEntry[] getProjectDependency(IProject clientProj){
		IClasspathEntry projectEntry = JavaCore.newProjectEntry(clientProj.getFullPath(), true);
			return new IClasspathEntry[]{projectEntry};	
	}	
	
	private void updateProjectDependency(IProject ejbProj, IProject clientProj){
		
		IJavaProject javaProject = JavaCore.create( ejbProj );
		try {
	
			IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
			IClasspathEntry[] newEntries = getProjectDependency( clientProj );
			
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