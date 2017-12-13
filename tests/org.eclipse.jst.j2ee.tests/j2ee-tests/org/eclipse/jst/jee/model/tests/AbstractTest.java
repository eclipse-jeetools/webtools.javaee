/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import junit.framework.AssertionFailedError;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class AbstractTest {

	private static final String EJB_API_JAR_LOCATION = "/home/kiko/local/jboss-4.2.1.GA/server/default/lib/jboss-ejb3x.jar";

	public static IFacetedProject createEjbProject(String earName, String ejbProjectName, String clientName)
			throws Exception {
		IDataModel dm = DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, ejbProjectName);

		FacetDataModelMap facetMap = (FacetDataModelMap) dm
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel facetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.EJB);
		facetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, IJ2EEFacetConstants.EJB_30);

		if (earName != null) {
			dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
			dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, earName);

			// only create client if given a client name, and is added to EAR
			if (clientName != null) {
				facetModel.setBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT, true);
				facetModel.setStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME, clientName);
			}
		} else {
			dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, false);
		}

		// this option only exists if JEE5
		facetModel.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, true);

		IDataModel javaFacetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.JAVA);
		javaFacetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, JavaFacetUtils.JAVA_50);

		dm.getDefaultOperation().execute(null, null);
		IFacetedProject project = ProjectFacetsManager.create(ResourcesPlugin.getWorkspace().getRoot().getProject(
				ejbProjectName));
		addEjbApiToClassPath(project);
		return project;
	}

	public static IFacetedProject createEjbProject(String projectName) throws Exception {
		IDataModel dm = DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName); //$NON-NLS-1$
		FacetDataModelMap facetDataModelMap = (FacetDataModelMap) dm
				.getProperty("IFacetProjectCreationDataModelProperties.FACET_DM_MAP");
		IDataModel javaFacetDataModel = facetDataModelMap.getFacetDataModel(J2EEProjectUtilities.JAVA);
		javaFacetDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION, JavaFacetUtils.JAVA_50);
		IDataModel ejbFacetDataModel = facetDataModelMap.getFacetDataModel(IJ2EEFacetConstants.EJB);
		ejbFacetDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION, IJ2EEFacetConstants.EJB_30);
		dm.getDefaultOperation().execute(null, null);
		IFacetedProject project = ProjectFacetsManager.create(ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName));
		addEjbApiToClassPath(project);
		return project;
	}

	public static void deleteProject(final String projectName) throws InterruptedException {
		Job deleteJob = new WorkspaceJob("Delete project job...") {
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).delete(true, monitor);
				return Status.OK_STATUS;
			}
		};
		deleteJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
		deleteJob.schedule();
		deleteJob.join();
	}

	public static void closeProject(final String projectName) throws Exception {
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {

			public void run(IProgressMonitor monitor) throws CoreException {
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				project.close(monitor);
			}
		}, new NullProgressMonitor());
	}

	public static IFacetedProject.Action setupEjbInstallAction() {
		IDataModel ejbFacetInstallDataModel = DataModelFactory.createDataModel(new EjbFacetInstallDataModelProvider());
		ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER, "ejbModule");
		ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.GENERATE_DD, true);
		IProjectFacetVersion webfacetversion = ProjectFacetsManager.getProjectFacet("jst.ejb").getVersion("3.0");
		IFacetedProject.Action action = new IFacetedProject.Action(IFacetedProject.Action.Type.INSTALL,
				webfacetversion, ejbFacetInstallDataModel);
		return action;
	}

	public static IFacetedProject.Action setupJavaInstallAction() {
		IProjectFacetVersion javafacetversion = ProjectFacetsManager.getProjectFacet("jst.java").getVersion("5.0");
		IDataModel javaFacetInstallDataModel = DataModelFactory
				.createDataModel(new JavaFacetInstallDataModelProvider());
		javaFacetInstallDataModel.setProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME, "ejbModule");
		IFacetedProject.Action action = new IFacetedProject.Action(IFacetedProject.Action.Type.INSTALL,
				javafacetversion, javaFacetInstallDataModel);
		return action;
	}

	/**
	 * @param facetProj
	 * @param installLocation
	 * @throws JavaModelException
	 */
	private static void addEjbApiToClassPath(IFacetedProject facetProj) throws JavaModelException {
		IPath classPath = new Path(EJB_API_JAR_LOCATION);
		IJavaProject javaProject = JavaCore.create(facetProj.getProject());
		IClasspathEntry[] entries = new IClasspathEntry[javaProject.getRawClasspath().length + 1];
		System.arraycopy(javaProject.getRawClasspath(), 0, entries, 0, entries.length - 1);
		IClasspathEntry entry = JavaCore.newLibraryEntry(classPath, null, null);
		entries[entries.length - 1] = entry;
		javaProject.setRawClasspath(entries, true, new NullProgressMonitor());
	}

	public static void saveFile(IFile file, String content) throws Exception {
		JdtChangeListenerWithSemaphore listener = new JdtChangeListenerWithSemaphore(1);
		JavaCore.addElementChangedListener(listener);
		if (file.exists())
			setContents(content, file);
		else
			createFile(content, file);
		if (listener.waitForEvents() == false)
			throw new AssertionFailedError();
		JavaCore.removeElementChangedListener(listener);
	}

	private static void createFile(final String content, final IFile file) throws Exception {
		BlockProgressMonitor monitor = new BlockProgressMonitor();
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				try {
					stream.write(content.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
				file.create(new ByteArrayInputStream(stream.toByteArray()), true, monitor);
			}
		}, ResourcesPlugin.getWorkspace().getRuleFactory().createRule(file), IResource.FORCE, monitor);

		if (monitor.waitForEvent() == false) {
			throw new IllegalStateException("Monitor not finished...");
		}
	}

	private static void setContents(final String content, final IFile file) throws Exception {
		BlockProgressMonitor monitor = new BlockProgressMonitor();
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();
				try {
					manager.connect(file.getFullPath(), LocationKind.IFILE, monitor);
					ITextFileBuffer buffer = manager.getTextFileBuffer(file.getFullPath(), LocationKind.IFILE);
					IDocument document = buffer.getDocument();
					document.set(content);
					buffer.commit(monitor, true);
				} finally {
					manager.disconnect(file.getFullPath(), LocationKind.IFILE, monitor);
				}
			}
		}, ResourcesPlugin.getWorkspace().getRuleFactory().modifyRule(file), IResource.NONE, monitor);
		if (monitor.waitForEvent() == false)
			throw new IllegalStateException("Monitor not finished...");
	}

	public static void deleteFile(final IFile file) throws Exception {
		JdtChangeListenerWithSemaphore listener = new JdtChangeListenerWithSemaphore(1);
		JavaCore.addElementChangedListener(listener);
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				file.delete(false, monitor);
				ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, monitor);
			}
		}, new NullProgressMonitor());
		if (listener.waitForEvents() == false)
			throw new AssertionFailedError();
		JavaCore.removeElementChangedListener(listener);
	}

	private static class BlockProgressMonitor implements IProgressMonitor {

		private Semaphore semaphore;

		private boolean canceled;

		public BlockProgressMonitor() throws InterruptedException {
			semaphore = new Semaphore(2);
			semaphore.acquire();
		}

		public void beginTask(String name, int totalWork) {
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public boolean waitForEvent() throws InterruptedException {
			return semaphore.tryAcquire(5, TimeUnit.SECONDS);
		}

		public void done() {
			semaphore.release();
		}

		public void internalWorked(double work) {
		}

		public boolean isCanceled() {
			return canceled;
		}

		public void setCanceled(boolean value) {
			semaphore.release();
			canceled = value;
		}

		public void setTaskName(String name) {

		}

		public void subTask(String name) {

		}

		public void worked(int work) {
		}

	}

	public static IFacetedProject createWebProject(String projectName) throws Exception {
		return createWebProject(projectName, IJ2EEFacetConstants.DYNAMIC_WEB_25, JavaFacetUtils.JAVA_50);
	}
	
	public static IFacetedProject createWebProject(String projectName, Object web_version, IProjectFacetVersion java_version) throws Exception {
		IDataModel dm = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName); //$NON-NLS-1$
		FacetDataModelMap facetDataModelMap = (FacetDataModelMap) dm
				.getProperty("IFacetProjectCreationDataModelProperties.FACET_DM_MAP");
		IDataModel javaFacetDataModel = facetDataModelMap.getFacetDataModel(J2EEProjectUtilities.JAVA);
		javaFacetDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION, java_version);
		IDataModel ejbFacetDataModel = facetDataModelMap.getFacetDataModel(IJ2EEFacetConstants.DYNAMIC_WEB);
		ejbFacetDataModel.setProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, true);
		ejbFacetDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION, web_version);
		dm.getDefaultOperation().execute(null, null);
		IFacetedProject project = ProjectFacetsManager.create(ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName));
		addEjbApiToClassPath(project);
		return project;
	}

}
