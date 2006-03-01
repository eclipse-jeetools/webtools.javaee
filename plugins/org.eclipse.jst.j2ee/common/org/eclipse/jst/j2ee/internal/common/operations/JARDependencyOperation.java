/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Sep 2, 2003
 *  
 */
package org.eclipse.jst.j2ee.internal.common.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation;
import org.eclipse.jst.j2ee.internal.common.ClasspathModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;


public class JARDependencyOperation extends AbstractDataModelOperation implements JARDependencyDataModelProperties{
	public JARDependencyOperation(IDataModel dataModel) {
		super(dataModel);
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void saveModel(ClasspathModel model, IProgressMonitor monitor) throws InvocationTargetException, InterruptedException, CoreException {
		if (!model.isDirty())
			return;
		validateEdit(model);
		monitor.beginTask("", 2); //$NON-NLS-1$
		org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation mfOperation = createManifestOperation(model);
		IHeadlessRunnableWithProgress buildPathOperation = createBuildPathOperation(model);
		try {
			mfOperation.execute(new SubProgressMonitor(monitor, 1), null);
			buildPathOperation.run(new SubProgressMonitor(monitor, 1));
		} catch (Exception e) {
			Logger.getLogger().logError(e);
		}
	}

	/**
	 * @param model
	 */
	protected void validateEdit(ClasspathModel aModel) throws CoreException {
		Set affectedFiles = aModel.getAffectedFiles();
		IFile[] files = (IFile[]) affectedFiles.toArray(new IFile[affectedFiles.size()]);
		IStatus result = J2EEPlugin.getWorkspace().validateEdit(files, null);
		if (!result.isOK())
			throw new CoreException(result);
	}

	protected UpdateJavaBuildPathOperation createBuildPathOperation(ClasspathModel aModel) {
		IJavaProject javaProject = JemProjectUtilities.getJavaProject(aModel.getProject());
		return new UpdateJavaBuildPathOperation(javaProject, aModel.getClassPathSelection());
	}

	private UpdateManifestOperation createManifestOperation(ClasspathModel aModel) {
		IDataModel updateManifestDataModel = DataModelFactory.createDataModel(UpdateManifestDataModelProvider.class);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, aModel.getProject().getName());
		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, UpdateManifestDataModelProvider.convertClasspathStringToList(aModel.getClassPathSelection().toString()));
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, J2EEProjectUtilities.getManifestFile( aModel.getProject()));
		return new UpdateManifestOperation(updateManifestDataModel);
	}

	
	public final IStatus execute(IProgressMonitor monitor, IAdaptable adaptable) throws ExecutionException {
		
		IProject proj = ProjectUtilities.getProject(model.getStringProperty(JARDependencyDataModelProperties.PROJECT_NAME));
		IProject earProject = ProjectUtilities.getProject(model.getStringProperty(JARDependencyDataModelProperties.EAR_PROJECT_NAME)); 
		
		ClasspathModel clpModel = new ClasspathModel(null);
		IVirtualComponent earComponent = ComponentCore.createComponent( earProject );
		if( !earComponent.exists() )
			return OK_STATUS;
		
		clpModel.setSelectedEARComponent( earComponent );
		clpModel.setProject( proj );
		
		try {
			int jarManipulationType = model.getIntProperty(JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE);
			switch (jarManipulationType) {
				case JARDependencyDataModelProperties.JAR_MANIPULATION_ADD :
					{
						List jarList = (List) model.getNestedModel(NESTED_MODEL_UPDATE_MAINFEST).getProperty(UpdateManifestDataModelProperties.JAR_LIST);
						if (!jarList.isEmpty()) {
							for (int i = 0; i < jarList.size(); i++) {
								String jarName = (String) jarList.get(i);
								clpModel.selectDependencyIfNecessary(jarName);
							}
						} else {
							IProject refproj = ProjectUtilities.getProject(model.getStringProperty(JARDependencyDataModelProperties.REFERENCED_PROJECT_NAME));							
							clpModel.selectDependencyIfNecessary( refproj );
						}
					}
					break;
					
				case JARDependencyDataModelProperties.JAR_MANIPULATION_REMOVE : 
					{
						List jarList = (List) model.getNestedModel(NESTED_MODEL_UPDATE_MAINFEST).getProperty(UpdateManifestDataModelProperties.JAR_LIST);
						for (int i = 0; i < jarList.size(); i++) {
							String jarName = (String) jarList.get(i);
							clpModel.removeDependency(jarName);
						}
					}
					break;
					
				case JARDependencyDataModelProperties.JAR_MANIPULATION_INVERT :
					{
						IProject refproj = ProjectUtilities.getProject(model.getStringProperty(JARDependencyDataModelProperties.REFERENCED_PROJECT_NAME));
						IProject oppositeProject = ProjectUtilities.getProject(model.getStringProperty(JARDependencyDataModelProperties.OPPOSITE_PROJECT_NAME));
						clpModel.getClassPathSelection().invertClientJARSelection( refproj, oppositeProject );
					}
					break;

			}
			if (clpModel.isDirty())
				try {
					saveModel(clpModel, monitor);
				} catch (InvocationTargetException e) {
					Logger.getLogger().logError(e);
				} catch (InterruptedException e) {
					Logger.getLogger().logError(e);
				} catch (CoreException e) {
					Logger.getLogger().logError(e);
				}			
		} finally {
			if (clpModel != null)
				clpModel.dispose();			
			if (monitor != null)
				monitor.done();
		}
		return OK_STATUS;
	}	

}
