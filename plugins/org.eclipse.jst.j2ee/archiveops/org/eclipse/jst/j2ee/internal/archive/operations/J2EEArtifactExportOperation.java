/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION;
import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.COMPONENT;
import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES;
import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.OPTIMIZE_FOR_SPECIFIC_RUNTIME;
import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.RUNTIME_SPECIFIC_PARTICIPANTS;
import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.RUN_BUILD;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.IArchiveExportParticipantData;
import org.eclipse.jst.j2ee.internal.archive.ComponentArchiveLoadAdapter;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.jst.jee.archive.ArchiveException;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveFactory;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * @deprecated use org.eclipse.jst.j2ee.internal.archive.operations.JavaEEComponentExportOperation instead
 */
public abstract class J2EEArtifactExportOperation extends AbstractDataModelOperation {

	protected IProgressMonitor progressMonitor;
	private IVirtualComponent component;
	private IPath destinationPath;
	private boolean exportSource = false;

	public J2EEArtifactExportOperation() {
		super();
	}

	public J2EEArtifactExportOperation(IDataModel model) {
		super(model);
	}
	
	protected final int REFRESH_WORK = 100;
	protected final int JAVA_BUILDER_WORK = 100;
	protected final int LIB_BUILDER_WORK = 100;
	protected final int EXPORT_WORK = 1000;
	protected final int CLOSE_WORK = 10;
	protected final int SINGLE_PARTICIPANT_WORK = 200;
	
	protected int computeTotalWork() {
		int totalWork = REFRESH_WORK;
		if (model.getBooleanProperty(RUN_BUILD)) {
			totalWork += JAVA_BUILDER_WORK + LIB_BUILDER_WORK;
		}
		totalWork += EXPORT_WORK + CLOSE_WORK;
		
		final IDataModel dm = getDataModel();
		
		if( dm.getProperty( OPTIMIZE_FOR_SPECIFIC_RUNTIME ) == Boolean.TRUE )
		{
    		final List<IArchiveExportParticipantData> extensions
    		    = (List<IArchiveExportParticipantData>) dm.getProperty( RUNTIME_SPECIFIC_PARTICIPANTS );
    		    
    		totalWork += extensions.size() * SINGLE_PARTICIPANT_WORK;
		}
		    
		return totalWork;
	}
	
	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		setComponent((IVirtualComponent) model.getProperty(COMPONENT));
		setDestinationPath(new Path(model.getStringProperty(ARCHIVE_DESTINATION)));
		setExportSource(model.getBooleanProperty(EXPORT_SOURCE_FILES));
		
		try
		{
		    monitor.beginTask(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Exporting_archive, new Object [] { getDestinationPath().lastSegment() }), computeTotalWork());
            setProgressMonitor(monitor);

    		try {
    			// defect 240999
    			component.getProject().refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(monitor, REFRESH_WORK));
    			if (model.getBooleanProperty(RUN_BUILD)) {
    				runNecessaryBuilders(component, new SubProgressMonitor(monitor, JAVA_BUILDER_WORK + LIB_BUILDER_WORK));
    			}
    			export();
    		} catch (Exception e) {
    			throw new ExecutionException(EJBArchiveOpsResourceHandler.Error_exporting__UI_ + getDestinationPath(), e);

    		}
    		
            final IDataModel dm = getDataModel();
            
            if( dm.getProperty( OPTIMIZE_FOR_SPECIFIC_RUNTIME ) == Boolean.TRUE )
            {
                for( IArchiveExportParticipantData extData 
                     : (List<IArchiveExportParticipantData>) dm.getProperty( RUNTIME_SPECIFIC_PARTICIPANTS ) )
                {
                    final IDataModelOperation op 
                        = extData.getParticipant().createOperation( extData.getDataModel() );
                    
                    op.execute( null, null );
                    monitor.worked( SINGLE_PARTICIPANT_WORK );
                }
            }
		}
		finally
		{
		    monitor.done();
		}
		
		return OK_STATUS;
	}

	public void export() throws ArchiveException, CoreException, InvocationTargetException, InterruptedException {
		IProgressMonitor subMonitor = new SubProgressMonitor(progressMonitor, EXPORT_WORK);
		IArchive archiveFromComponent = null;
		try {
			archiveFromComponent = JavaEEArchiveUtilities.INSTANCE.openArchive(getComponent());
			saveArchive(archiveFromComponent, getDestinationPath().toOSString(), subMonitor);
		} catch (Exception e) {
			throw new ArchiveException(AppClientArchiveOpsResourceHandler.ARCHIVE_OPERATION_OpeningArchive, e);
		} finally {
			if (archiveFromComponent != null){
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archiveFromComponent);
			}
			subMonitor.done();
		}
	}

	protected void setProgressMonitor(IProgressMonitor newProgressMonitor) {
		progressMonitor = newProgressMonitor;
	}

	protected IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}

	private void setComponent(IVirtualComponent newComponent) {
		component = newComponent;
	}

	protected IVirtualComponent getComponent() {
		if (component == null)
			component = (IVirtualComponent) model.getProperty(COMPONENT);
		return component;
	}

	protected IPath getDestinationPath() {
		return destinationPath;
	}

	protected void setDestinationPath(IPath newDestinationPath) {
		destinationPath = newDestinationPath;
	}

	protected boolean isExportSource() {
		return exportSource;
	}

	protected void setExportSource(boolean newExportSource) {
		exportSource = newExportSource;
	}

	protected void runNecessaryBuilders(IVirtualComponent component, IProgressMonitor monitor) throws CoreException {
		try{
			monitor.beginTask(null, JAVA_BUILDER_WORK + LIB_BUILDER_WORK);
			if(!component.isBinary()){
				IProject project = component.getProject();
				IProjectDescription description = project.getDescription();
				ICommand javaBuilder = getJavaCommand(description);
				if (javaBuilder != null) {
					project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, JavaCore.BUILDER_ID, javaBuilder.getArguments(), new SubProgressMonitor(monitor, JAVA_BUILDER_WORK));
				}
				ICommand libCopyBuilder = getLibCopyBuilder(description);
				if (null != libCopyBuilder) {
					project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, LibCopyBuilder.BUILDER_ID, libCopyBuilder.getArguments(), new SubProgressMonitor(monitor, LIB_BUILDER_WORK));
				}
			}
		} finally {
			monitor.done();
		}
	}

	/**
	 * Find the specific Java command amongst the build spec of a given description
	 */
	protected ICommand getJavaCommand(IProjectDescription description) throws CoreException {
		if (description == null) {
			return null;
		}

		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(JavaCore.BUILDER_ID)) {
				return commands[i];
			}
		}
		return null;
	}

	protected ICommand getLibCopyBuilder(IProjectDescription description) throws CoreException {
		if (description == null) {
			return null;
		}

		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(LibCopyBuilder.BUILDER_ID)) {
				return commands[i];
			}
		}
		return null;

	}

	@Override
	public ISchedulingRule getSchedulingRule() {
		Set projs = gatherDependentProjects(getComponent(), new HashSet());
		ISchedulingRule combinedRule = null;
		IResourceRuleFactory ruleFactory = ResourcesPlugin.getWorkspace().getRuleFactory();
		for (Iterator iter = projs.iterator(); iter.hasNext();) {
			IProject proj = (IProject) iter.next();
			ISchedulingRule rule = ruleFactory.createRule(proj);
			combinedRule = MultiRule.combine(rule, combinedRule);
		}
		combinedRule = MultiRule.combine(ruleFactory.buildRule(), combinedRule);

		return combinedRule;
	}

	private Set gatherDependentProjects(IVirtualComponent comp, Set projs) {
		if (!projs.contains(comp.getProject())) {
			projs.add(comp.getProject());
			IVirtualReference[] refs = comp.getReferences();
			for (int i = 0; i < refs.length; i++) {
				IVirtualReference refComp = refs[i];
				projs.addAll(gatherDependentProjects(refComp.getReferencedComponent(), projs));
			}
		}
		return projs;
	}
	
	protected void saveArchive(IArchive archiveToSave, String fileName, IProgressMonitor monitor) throws ArchiveSaveFailureException  {
		String writeFileName = fileName;
		File writeFile = new File(writeFileName);
		if (writeFile.exists()) {
			writeFile.delete();
		}
		IPath outputPath = new Path(writeFileName);
		IArchiveLoadAdapter loadAdapter = archiveToSave.getLoadAdapter();
		if(loadAdapter instanceof ComponentArchiveLoadAdapter){
			ComponentArchiveLoadAdapter cLoadAdapter = (ComponentArchiveLoadAdapter)loadAdapter;
			cLoadAdapter.setExportSource(getDataModel().getBooleanProperty(EXPORT_SOURCE_FILES));
			
		}
		IArchiveFactory.INSTANCE.saveArchive(archiveToSave, outputPath, monitor);
	}


}
