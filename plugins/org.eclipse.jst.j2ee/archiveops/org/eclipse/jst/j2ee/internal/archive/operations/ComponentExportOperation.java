/*******************************************************************************
 * Copyright (c) 2010, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION;
import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.COMPONENT;
import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES;
import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.OPTIMIZE_FOR_SPECIFIC_RUNTIME;
import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.RUNTIME_SPECIFIC_PARTICIPANTS;
import static org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.RUN_BUILD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.IArchiveExportParticipantData;
import org.eclipse.jst.j2ee.internal.archive.operations.FlatComponentArchiver.UnderlyingFileNotFoundException;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.internal.ArchiveUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class ComponentExportOperation extends AbstractDataModelOperation {

	protected IProgressMonitor progressMonitor;
	private IVirtualComponent component;
	private IPath destinationPath;
	private boolean exportSource = false;
	
	public static String[] DOT_FILE_NAMES = new String[] {
		".project", 	//$NON-NLS-1$
		".classpath", 	//$NON-NLS-1$
		".cvsignore", 	//$NON-NLS-1$
	};

	public static String[] DOT_SOURCE_FILES = new String[] {
		".java",		//$NON-NLS-1$
		".sqlj",		//$NON-NLS-1$
	};


	public ComponentExportOperation() {
		super();
	}

	public ComponentExportOperation(IDataModel model) {
		super(model);
	}
	
	protected final int REFRESH_WORK = 100;
	protected final int JAVA_BUILDER_WORK = 100;
	protected final int EXPORT_WORK = 1000;
	protected final int CLOSE_WORK = 10;
	protected final int SINGLE_PARTICIPANT_WORK = 200;
	
	protected int computeTotalWork() {
		int totalWork = REFRESH_WORK;
		if (model.getBooleanProperty(RUN_BUILD)) {
			totalWork += JAVA_BUILDER_WORK;
		}
		totalWork += EXPORT_WORK + CLOSE_WORK;
		
		final IDataModel dm = getDataModel();
		
		if (dm.getProperty(OPTIMIZE_FOR_SPECIFIC_RUNTIME) == Boolean.TRUE) {
    		final List<IArchiveExportParticipantData> extensions
    		    = (List<IArchiveExportParticipantData>) dm.getProperty(RUNTIME_SPECIFIC_PARTICIPANTS);
    		    
    		totalWork += extensions.size() * SINGLE_PARTICIPANT_WORK;
		}  
		return totalWork;
	}
	
	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		setComponent((IVirtualComponent) model.getProperty(COMPONENT));
		setDestinationPath(new Path(model.getStringProperty(ARCHIVE_DESTINATION)));
		setExportSource(model.getBooleanProperty(EXPORT_SOURCE_FILES));
		
		try {
		    monitor.beginTask(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Exporting_archive, new Object [] { getDestinationPath().lastSegment() }), computeTotalWork());
            setProgressMonitor(monitor);
    		try {
    			component.getProject().refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(monitor, REFRESH_WORK));
    			if (model.getBooleanProperty(RUN_BUILD)) {
    				IVirtualReference[] refs = component.getReferences();
    				int work = (JAVA_BUILDER_WORK) / (refs.length + 1);
    				runNecessaryBuilders(component, new SubProgressMonitor(monitor, work));
    				for (int i = 0; i < refs.length; i++) {
    					IVirtualComponent refComp = refs[i].getReferencedComponent();
    					runNecessaryBuilders(refComp, new SubProgressMonitor(monitor, work));
    				}
    			}
    			export();
    		} catch (Exception e) {
    			if (e.getCause() != null && e.getCause().getCause() instanceof UnderlyingFileNotFoundException){
					// Try to refresh the child modules and do the archive operation again
    				J2EEPlugin.logWarning("Refreshing referenced projects before trying to export again the project " + component.getDeployedName()); //$NON-NLS-1$
    				try{
						refreshChildModules(component);
						export();
    				}
    				catch (Exception exceptionWhileRetry){
    					throw new ExecutionException(EJBArchiveOpsResourceHandler.Error_exporting__UI_ + getDestinationPath(), exceptionWhileRetry);
    				}
				}
    			else{
    				throw new ExecutionException(EJBArchiveOpsResourceHandler.Error_exporting__UI_ + getDestinationPath(), e);
    			}
    		}
    		
            final IDataModel dm = getDataModel();
            
            if (dm.getProperty(OPTIMIZE_FOR_SPECIFIC_RUNTIME) == Boolean.TRUE) {
                for (IArchiveExportParticipantData extData : (List<IArchiveExportParticipantData>) dm.getProperty( RUNTIME_SPECIFIC_PARTICIPANTS)) {
                    final IDataModelOperation op = extData.getParticipant().createOperation( extData.getDataModel() );
                    
                    op.execute(null, null);
                    monitor.worked(SINGLE_PARTICIPANT_WORK);
                }
            }
		}
		finally {
		    monitor.done();
		}
		return OK_STATUS;
	}

	public void export() throws ArchiveSaveFailureException {
		IProgressMonitor subMonitor = new SubProgressMonitor(progressMonitor, EXPORT_WORK);
		final int SAVE_TICKS = 198;
		final int CLEANUP_TICKS = 2;
		final int TOTAL_TICKS = SAVE_TICKS + CLEANUP_TICKS;
		try {
			File writeFile = getDestinationPath().toFile();
			if (writeFile.exists()) {
				writeFile.delete();
			}
			java.io.File aFile = getDestinationPath().toFile();
			ArchiveUtil.checkWriteable(aFile);
			boolean fileExisted = aFile.exists();
			FlatComponentArchiver archiver = null;
			try {
				java.io.File destinationFile = fileExisted 
				? ArchiveUtil.createTempFile(getDestinationPath().toOSString(), aFile.getCanonicalFile().getParentFile()) 
						: aFile;
				
				java.io.OutputStream out = createOutputStream(destinationFile);
				archiver = createFlatComponentArchiver(out);
				subMonitor.beginTask(NLS.bind(CommonArchiveResourceHandler.ArchiveFactoryImpl_Saving_archive_to_0_, getDestinationPath().toOSString()), TOTAL_TICKS);
				archiver.saveArchive();
				subMonitor.worked(SAVE_TICKS);
				archiver.close();
			
				if (fileExisted) {
					ArchiveUtil.cleanupAfterTempSave(getDestinationPath().toOSString(), aFile, destinationFile);
				}
				subMonitor.worked(CLEANUP_TICKS);
			} catch (java.io.IOException e) {
				throw new ArchiveSaveFailureException(e);
			} catch (ArchiveSaveFailureException failure) {
				try {
					archiver.close();
				} catch (IOException weTried) {
					// Ignore
				}
				if (!fileExisted)
					aFile.delete();
				throw failure;
			} 
		} finally {
			subMonitor.done();
		}
	}

	protected java.io.OutputStream createOutputStream(java.io.File destinationFile) throws IOException, FileNotFoundException {
		if (destinationFile.exists() && destinationFile.isDirectory()) {
			throw new IOException(NLS.bind(CommonArchiveResourceHandler.ArchiveFactoryImpl_The_specified_file_0_exists_and_, destinationFile.getAbsolutePath()));
		}
		java.io.File parent = destinationFile.getParentFile();
		if (parent != null)
			parent.mkdirs();
		java.io.OutputStream out = new java.io.FileOutputStream(destinationFile);
		return out;
	}

	protected FlatComponentArchiver createFlatComponentArchiver(OutputStream out) {
		return new FlatComponentArchiver(getComponent(), out, getParticipants());
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
		try {
			monitor.beginTask(null, JAVA_BUILDER_WORK);
			if (!component.isBinary()) {
				IProject project = component.getProject();
				IProjectDescription description = project.getDescription();
				ICommand javaBuilder = getJavaCommand(description);
				if (javaBuilder != null) {
					project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, JavaCore.BUILDER_ID, javaBuilder.getArguments(), new SubProgressMonitor(monitor, JAVA_BUILDER_WORK));
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
	
	/**
	 * Subclasses can provide a list of participants who may
	 * be involved in forming the export model
	 * 
	 * A deployable with no participant should still properly
	 * consume consumed references and traverse the model appropriately
	 * 
	 * @return
	 */
	protected List<IFlattenParticipant> getParticipants() {
		return Collections.EMPTY_LIST;
	}
	
	protected String[] getFilteredExtensions() {
		ArrayList<String> excludeList = new ArrayList<String>();
		excludeList.addAll(Arrays.asList(DOT_FILE_NAMES));
		if (!isExportSource()) {
			excludeList.addAll(Arrays.asList(DOT_SOURCE_FILES));
		}
		return excludeList.toArray(new String[excludeList.size()]);
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
	
	private void refreshProject(IVirtualComponent refComp) throws CoreException {
		if (!component.isBinary()) {
			IProject project = refComp.getProject();
			project.refreshLocal(IResource.DEPTH_INFINITE,null);
		}			
	}
	
	private void refreshChildModules(IVirtualComponent component) throws CoreException {
		IVirtualReference[] refs = component.getReferences();
		for (int i = 0; i < refs.length; i++) {
			IVirtualComponent refComp = refs[i].getReferencedComponent();
			refreshProject(refComp);
		}	
	}
	
}
