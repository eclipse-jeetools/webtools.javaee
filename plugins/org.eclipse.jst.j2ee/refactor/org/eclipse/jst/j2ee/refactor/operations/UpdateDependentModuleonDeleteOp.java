/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.refactor.operations;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProvider;
import org.eclipse.jst.j2ee.refactor.RefactorResourceHandler;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Subclass of <code>UpdateDependentProjectOp</code> that updates a dependent
 * module project when one of the referenced projects has been deleted.
 */
public class UpdateDependentModuleonDeleteOp extends UpdateDependentProjectOp {
	
	/**
	 * Creates a new refactoring operation for the specified data model.
	 * @param model The data model.
	 */
	public UpdateDependentModuleonDeleteOp(final IDataModel model) {
		super(model);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#execute(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		final ProjectRefactorMetadata dependentMetadata = (ProjectRefactorMetadata) model.getProperty(DEPENDENT_PROJECT_METADATA);
		final ProjectRefactorMetadata refactoredMetadata = (ProjectRefactorMetadata) model.getProperty(PROJECT_METADATA);
		removeModuleDependency(dependentMetadata, refactoredMetadata);
		return Status.OK_STATUS;
	}
	
	/**
	 * Updates the dependent module project to account for the deletion of the referenced project.
	 */
	protected static void removeModuleDependency(final ProjectRefactorMetadata dependentMetadata, 
			final ProjectRefactorMetadata refactoredMetadata) throws ExecutionException {
		
		// create IVirtualComponents for the dependent and the refactored project
		final IVirtualComponent dependentComp = dependentMetadata.getVirtualComponent();
		final IVirtualComponent refactoredComp = refactoredMetadata.getVirtualComponent();
		final IProgressMonitor monitor = new NullProgressMonitor();

		// remove the component reference on the deleted project
		if (refactoredComp != null) {
			final IDataModel model = DataModelFactory.createDataModel(new RemoveReferenceComponentsDataModelProvider());
			model.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, dependentComp);
			final List modHandlesList = (List) model.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			modHandlesList.add(refactoredComp);
			model.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modHandlesList);
			model.getDefaultOperation().execute(monitor, null);
		}
		
		// update the manifest
		updateManifestDependency(refactoredMetadata, dependentMetadata, true);
		
		// update the JAR dependency data
		IDataModel dataModel = DataModelFactory.createDataModel(new JARDependencyDataModelProvider());
		dataModel.setProperty(JARDependencyDataModelProperties.PROJECT_NAME, dependentMetadata.getProjectName());
		dataModel.setProperty(JARDependencyDataModelProperties.REFERENCED_PROJECT_NAME, refactoredMetadata.getProjectName());
		dataModel.setIntProperty(JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE, JARDependencyDataModelProperties.JAR_MANIPULATION_REMOVE);
		dataModel.getDefaultOperation().execute(monitor, null );
		
	}
	
	protected static void updateManifestDependency(final ProjectRefactorMetadata refactoredMetadata,
			final ProjectRefactorMetadata dependentMetadata, final boolean remove) throws ExecutionException {
		final IVirtualComponent dependentComp = dependentMetadata.getVirtualComponent();
		final String dependentProjName = dependentMetadata.getProjectName();
		final String refactoredProjName = refactoredMetadata.getProjectName();
		final IVirtualFile vf = dependentComp.getRootFolder().getFile(new Path(J2EEConstants.MANIFEST_URI) );
		final IFile manifestmf = vf.getUnderlyingFile();
		final IProgressMonitor monitor = new NullProgressMonitor();
		final IDataModel updateManifestDataModel = DataModelFactory.createDataModel(new UpdateManifestDataModelProvider());
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, dependentProjName);
		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, manifestmf);
		final ArchiveManifest manifest = getArchiveManifest(manifestmf);
		String[] cp = manifest.getClassPathTokenized();
		List cpList = new ArrayList();
		String newCp = refactoredProjName + ".jar";//$NON-NLS-1$
		for (int i = 0; i < cp.length; i++) {
			if (!cp[i].equals(newCp)) {
				cpList.add(cp[i]);
			}
		}
		if (!remove) {
			cpList.add(newCp);
		}
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, cpList);
		updateManifestDataModel.getDefaultOperation().execute(monitor, null );
	}
	
	private static ArchiveManifest getArchiveManifest(final IFile manifestFile) throws ExecutionException {
		InputStream in = null;
		try {
			in = manifestFile.getContents();
			ArchiveManifest mf = new ArchiveManifestImpl(new Manifest(in));
			return mf;
		} catch (CoreException ce) {
			throw new ExecutionException(RefactorResourceHandler.getString("error_reading_manifest"), ce);
		} catch (IOException io) {
			throw new ExecutionException(RefactorResourceHandler.getString("error_reading_manifest"), io);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException weTried) {
					//Ignore
				}
			}
		}
	}
	
}
