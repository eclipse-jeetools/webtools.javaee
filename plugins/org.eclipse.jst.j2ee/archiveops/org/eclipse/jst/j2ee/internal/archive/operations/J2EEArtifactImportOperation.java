/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.jst.j2ee.internal.archive.ComponentArchiveSaveAdapter;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveFactory;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;

public abstract class J2EEArtifactImportOperation extends AbstractDataModelOperation {

	protected ArchiveWrapper archiveWrapper;
	protected IVirtualComponent virtualComponent;
	protected IAdaptable info;
	protected final int PROJECT_CREATION_WORK = 30;
	
	private static IArchiveFactory archiveFactory = IArchiveFactory.INSTANCE;

	public J2EEArtifactImportOperation(IDataModel model) {
		super(model);
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable anInfo) throws ExecutionException {
		try {
			J2EEComponentClasspathUpdater.getInstance().pauseUpdates();
			this.info = anInfo;
			archiveWrapper = (ArchiveWrapper)model.getProperty(IJ2EEComponentImportDataModelProperties.ARCHIVE_WRAPPER);
			monitor.beginTask(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Importing_archive, new Object [] { archiveWrapper.getPath() }), computeTotalWork());
			doExecute(monitor);
			return OK_STATUS;
		} finally {
			try {
				if (virtualComponent != null) {
					J2EEComponentClasspathUpdater.getInstance().queueUpdate(virtualComponent.getProject());
				}
			} finally {
				J2EEComponentClasspathUpdater.getInstance().resumeUpdates();
				model.dispose();
				monitor.done();
			}
		}
	}

	protected int computeTotalWork() {
		return PROJECT_CREATION_WORK + archiveWrapper.getSize();
	}
	
	/**
	 * Subclasses overriding this method should also override {@link #computeTotalWork()}
	 * @param monitor
	 * @throws ExecutionException
	 */
	protected void doExecute(IProgressMonitor monitor) throws ExecutionException {
		IDataModel nestedModel = model.getNestedModel(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION);
		IProgressMonitor subMonitor = new SubProgressMonitor(monitor, PROJECT_CREATION_WORK);
		virtualComponent = createVirtualComponent(nestedModel, subMonitor);

		try {
			importModuleFile(new SubProgressMonitor(monitor, archiveWrapper.getSize()));
		} catch (InvocationTargetException e) {
			throw new ExecutionException(e.getMessage(), e);
		} catch (InterruptedException e) {
			throw new ExecutionException(e.getMessage(), e);
		}
	}

	protected IVirtualComponent createVirtualComponent(IDataModel aModel, IProgressMonitor monitor) throws ExecutionException {
		try {
			aModel.getDefaultOperation().execute(monitor, info);
			String projectName = aModel.getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME);
			IProject project = ProjectUtilities.getProject(projectName);
			return ComponentCore.createComponent(project);
		} finally {
			monitor.done();
		}
	}

	protected ComponentArchiveSaveAdapter getArchiveSaveAdapter(IVirtualComponent virtualComponent){
		return new ComponentArchiveSaveAdapter(virtualComponent);
	}

	/**
	 * perform the archive import operation
	 * 
	 * @throws java.lang.reflect.InvocationTargetException
	 * @throws java.lang.InterruptedException
	 */
	protected void importModuleFile(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		try {
			monitor.beginTask(null, archiveWrapper.getSize());
			IArchive archive = archiveWrapper.getIArchive();
			ComponentArchiveSaveAdapter adap = getArchiveSaveAdapter(virtualComponent);
			ArchiveOptions saveOptions = new ArchiveOptions();
			saveOptions.setOption(ArchiveOptions.SAVE_ADAPTER, adap);
			archiveFactory.saveArchive(archive, saveOptions,monitor);
		} catch (OverwriteHandlerException oe) {
			throw new InterruptedException();
		} catch (Exception ex) {
			throw new WFTWrappedException(ex, EJBArchiveOpsResourceHandler.ERROR_IMPORTING_MODULE_FILE);
		} finally {
			monitor.done();
		}
	}

}
