/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    BEA Systems, Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.dependency.tests.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.Manifest;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.RemoveComponentFromEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Test utility class that programmatically creates J2EE dependencies. 
 */
public class DependencyCreationUtil {
	
	public static void createEARDependency(final IProject earProject, final IProject childProject) throws ExecutionException {
		createEARDependency(earProject, childProject, false);
	}
	
	public static void createEARDependency(final IProject earProject, final IProject childProject, final boolean inLibDir) throws ExecutionException {
		final IDataModel dm = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
		IVirtualComponent earComp = ComponentCore.createComponent(earProject);
		IVirtualComponent childComp = ComponentCore.createComponent(childProject);
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp); 
		final List depList = new ArrayList();
		depList.add(childComp);
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, depList);
		if (inLibDir) {
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, J2EEConstants.EAR_DEFAULT_LIB_DIR); //$NON-NLS-1$
		} else {
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, J2EEConstants.EAR_ROOT_DIR); //$NON-NLS-1$
			Map modDeployPathMap = (Map) dm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH_MAP);
			modDeployPathMap.put(childComp, J2EEConstants.EAR_ROOT_DIR);
		}
		dm.getDefaultOperation().execute(null, null);
        ProjectUtil.waitForClasspathUpdate();
	}
	
	public static void removeEARDependency(final IProject earProject, final IProject childProject) throws ExecutionException {
		final IDataModel dm = DataModelFactory.createDataModel(new RemoveComponentFromEnterpriseApplicationDataModelProvider());
		IVirtualComponent earComp = ComponentCore.createComponent(earProject);
		IVirtualComponent childComp = ComponentCore.createComponent(childProject);
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp); 
		final List depList = new ArrayList();
		depList.add(childComp);
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, depList);
		dm.getDefaultOperation().execute(null, null);
        ProjectUtil.waitForClasspathUpdate();
	}

	public static void createModuleDependency(final IProject source, final IProject target) throws ExecutionException, CoreException, IOException {
		createProjectDependency(source, target, false);
	}
	
	public static void createWebLibDependency(final IProject source, final IProject target) throws ExecutionException, CoreException, IOException {
		createProjectDependency(source, target, true);
	}
	
	private static void createProjectDependency(final IProject source, final IProject target, final boolean webLibDep) throws ExecutionException, CoreException, IOException {
		IVirtualComponent sourceComp = ComponentCore.createComponent(source);
		IVirtualComponent targetComp = ComponentCore.createComponent(target);
		if (webLibDep) {
			// add component and project refs
			final IDataModel refdm = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
			final List targetCompList = (List) refdm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			targetCompList.add(targetComp);
			refdm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, sourceComp);
			refdm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, targetCompList);
			refdm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH,"/WEB-INF/lib"); //$NON-NLS-1$
			refdm.getDefaultOperation().execute(null, null);
		} else {
			// just add a manifest ref
			final IVirtualComponent dependentComp = sourceComp;
			final String dependentProjName = source.getName();
			final String refactoredProjName = target.getName();
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
			cpList.add(newCp);
			updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, cpList);
			updateManifestDataModel.getDefaultOperation().execute(monitor, null );
		}
        ProjectUtil.waitForClasspathUpdate();
        if (webLibDep)
        	DependencyUtil.waitForComponentRefactoringJobs();
	}
	
	public static ArchiveManifest getArchiveManifest(final IFile manifestFile) throws CoreException, IOException {
		InputStream in = null;
		try {
			in = manifestFile.getContents();
			ArchiveManifest mf = new ArchiveManifestImpl(new Manifest(in));
			return mf;
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
