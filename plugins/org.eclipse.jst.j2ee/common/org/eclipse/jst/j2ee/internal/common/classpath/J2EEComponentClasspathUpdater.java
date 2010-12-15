/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.classpath;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.jdt.internal.classpath.FlexibleProjectContainer;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.componentcore.util.EARVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEBinaryComponentHelper;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.builder.IDependencyGraphListener;
import org.eclipse.wst.common.componentcore.internal.builder.IDependencyGraphUpdateEvent;
import org.eclipse.wst.common.componentcore.internal.impl.ResourceTreeRootAdapter;
import org.eclipse.wst.common.componentcore.internal.impl.WTPModulesResourceFactory;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;

public class J2EEComponentClasspathUpdater implements IResourceChangeListener, IResourceDeltaVisitor, IDependencyGraphListener {

	private static J2EEComponentClasspathUpdater instance = null;

	private static boolean updateDependencyGraph = true;

	private int pauseCount = 0;
	
	public static IPath WEB_APP_LIBS_PATH = new Path("org.eclipse.jst.j2ee.internal.web.container"); //$NON-NLS-1$

	public static J2EEComponentClasspathUpdater getInstance() {
		if (instance == null) {
			init();
		}
		return instance;
	}

	private static void init() {
		if (instance == null) {
			instance = new J2EEComponentClasspathUpdater();
		}
	}

	/**
	 * Pauses updates; any caller of this method must ensure through a
	 * try/finally block that resumeUpdates is subsequently called.
	 */
	public void pauseUpdates() {
		synchronized (this) {
			pauseCount++;
		}
	}

	public void resumeUpdates() {
		resumeUpdates(true);
	}
	
	private void resumeUpdates(boolean scheduleJob){
		synchronized (this) {
			if (pauseCount > 0) {
				pauseCount--;
			}
			if (pauseCount > 0) {
				return;
			}
		}
		if(scheduleJob){
			moduleUpdateJob.schedule(MODULE_UPDATE_DELAY);
		}
	}
	
	public void forceUpdate(Collection projects){
		forceUpdate(projects, true);
	}
	
	/**
	 * Collection of type IProject
	 * @param projects
	 */
	public void forceUpdate(Collection projects, boolean runAsJob) {
		try {
			pauseUpdates();
			Iterator iterator = projects.iterator();
			while(iterator.hasNext()){
				queueUpdate((IProject)iterator.next());
			}
		} finally {
			forceUpdateOnNextRun = true;
			// the following code is in place of the normal call to
			// resume updates.  This restores the pauseCount and forces 
			// the job to be scheduled immediately 
			synchronized (this) {
				if (pauseCount > 0) {
					pauseCount--;
				}
			}
			if(runAsJob){
				moduleUpdateJob.schedule(0);
			} else {
				try
				{
					updateDependencyGraph = false;
					moduleUpdateJob.run(new NullProgressMonitor());
				}
				finally
				{
					updateDependencyGraph = true;
				}
			}
		}
	}
	
	private boolean forceUpdateOnNextRun = false;
	
	public void queueUpdate(IProject project) {
		if (JavaEEProjectUtilities.isEARProject(project)) {
			queueUpdateEAR(project);
		} else if (JavaEEProjectUtilities.isApplicationClientProject(project) || JavaEEProjectUtilities.isEJBProject(project) || JavaEEProjectUtilities.isDynamicWebProject(project)
				|| JavaEEProjectUtilities.isJCAProject(project) || JavaEEProjectUtilities.isUtilityProject(project)) {
			queueUpdateModule(project);
		}
	}

	public void queueUpdateModule(IProject project) {
		moduleUpdateJob.queueModule(project);
		synchronized (this) {
			if (pauseCount > 0) {
				return;
			}
		}
		moduleUpdateJob.schedule(MODULE_UPDATE_DELAY);
	}

	public void queueUpdateEAR(IProject earProject) {
		moduleUpdateJob.queueEAR(earProject);
		synchronized (this) {
			if (pauseCount > 0) {
				return;
			}
		}
		moduleUpdateJob.schedule(MODULE_UPDATE_DELAY);
	}
	
	public boolean projectsQueued() {
		return moduleUpdateJob.projectsQueued() || moduleUpdateJob.getState() != Job.NONE;
	}
	
	private static final int MODULE_UPDATE_DELAY = 30;
	public static final String MODULE_UPDATE_JOB_NAME = Messages.J2EEComponentClasspathUpdater_EAR_Libraries_Update_Jo_; 

	private final ModuleUpdateJob moduleUpdateJob = new ModuleUpdateJob();

	public class ModuleUpdateJob extends Job {

		@Override
		public boolean belongsTo(Object family) {
			if(family == MODULE_UPDATE_JOB_NAME){
				return true;
			}
			return super.belongsTo(family);
		}
		
		// We use the listener list as a thread safe queue.
		private class Queue extends ListenerList  {
			@Override
			public synchronized Object[] getListeners() {
				Object[] data = super.getListeners();
				clear();
				return data;
			}
		}

		private Queue moduleQueue = new Queue();

		private Queue earQueue = new Queue();

		//a private queue for adding modules queued by the EAR
		private Queue earAddedModuleQueue = new Queue();
		
		public ModuleUpdateJob() {
			super(MODULE_UPDATE_JOB_NAME); 
			setRule(ResourcesPlugin.getWorkspace().getRoot());
			setSystem(true);
		}

		public void queueEAR(IProject ear) {
			earQueue.add(ear);
		}

		public void queueModule(IProject project) {
			moduleQueue.add(project);
		}
		
		public boolean projectsQueued() {
			return !earQueue.isEmpty() || !moduleQueue.isEmpty();
		}

		/**
		 * Add referenced EARs from the queued modules into the EARs queue
		 */
		private void queueReferencingEars(Object[] projects) {
			for (int p = 0; p < projects.length; p++) {
				IProject project = (IProject) projects[p];
				if (!isKnown(project)) {
					IProject[] earProjects = EarUtilities.getReferencingEARProjects(project);
					for (int i = 0; i < earProjects.length; i++) {
						queueEAR(earProjects[i]);
					}
				} 
			}
		}
		
		private void processEars() {
			Object[] earProjects = earQueue.getListeners();
			for (int i = 0; i < earProjects.length; i++) {
				IProject earProject = (IProject) earProjects[i];
				if (JavaEEProjectUtilities.isEARProject(earProject))
				{
					IVirtualComponent earComponent = ComponentCore.createComponent(earProject); 
					IVirtualReference[] refs = J2EEProjectUtilities.getComponentReferences(earComponent);
					IVirtualComponent comp = null;
					for (int j = 0; j < refs.length; j++) {
						comp = refs[j].getReferencedComponent();
						if (!comp.isBinary()) {
							earAddedModuleQueue.add(comp.getProject());
						}
					}
					if(null != earComponent){
						JavaEEBinaryComponentHelper.clearDisconnectedArchivesInEAR(earComponent);	
					}
				}
			}
		}
		
		private void processModules(Object[] projects) {
			for (int i = 0; i < projects.length; i++) {
				IProject project = (IProject) projects[i];
				// this block is for Web app Libraries
				if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
					IClasspathContainer webAppLibrariesContainer = J2EEComponentClasspathContainerUtils.getInstalledWebAppLibrariesContainer(project);
					// If the container is present, refresh it
					if (webAppLibrariesContainer != null) {
						((FlexibleProjectContainer) webAppLibrariesContainer).refresh(forceUpdateOnNextRun);
					}
				}

				// ******************** The following is for EAR Libraries
				IClasspathContainer earLibrariesContainer = J2EEComponentClasspathContainerUtils.getInstalledEARLibrariesContainer(project);
				// If the container is present, refresh it
				if (earLibrariesContainer != null) {
					((J2EEComponentClasspathContainer) earLibrariesContainer).refresh(forceUpdateOnNextRun);
				}
			}
			// [202820]
			updateDependencyGraph = true;
		}
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {

			SafeRunner.run(new ISafeRunnable() {
				public void handleException(Throwable e) {
					J2EEPlugin.logError(e);
				}

				public void run() throws Exception {
					try {
						Object[] moduleProjects = moduleQueue.getListeners();
						queueReferencingEars(moduleProjects);
						processEars();
						Object [] earQueuedModuleProjects = earAddedModuleQueue.getListeners();
						Set modulesSet = new HashSet();
						modulesSet.addAll(Arrays.asList(moduleProjects));
						modulesSet.addAll(Arrays.asList(earQueuedModuleProjects));
						Object [] modulesArray = modulesSet.toArray();
						processModules(modulesArray);
					} finally {
						forceUpdateOnNextRun = false;
					}
					
				}
			});

			return Status.OK_STATUS;
		}
	}

	public IClasspathContainer getWebAppLibrariesContainer(IProject webProject, boolean create) {
		IJavaProject jproj = JavaCore.create(webProject);
		IClasspathContainer container = null;
		IClasspathEntry entry = create ? null : getExistingContainer(jproj, WEB_APP_LIBS_PATH);
		if (entry != null || create) {
		try {
				container = JavaCore.getClasspathContainer(WEB_APP_LIBS_PATH, jproj);
		} catch (JavaModelException e) {
				J2EEPlugin.logError(e);
		}
	}
		return container;
	}
	
	/**
	 * Returns the existing classpath container if it is already on the classpath. This will not
	 * create a new container.
	 * 
	 * @param jproj
	 * @param classpathContainerID
	 * @return
	 */
	public IClasspathEntry getExistingContainer(IJavaProject jproj, IPath classpathContainerPath) {
		return J2EEComponentClasspathContainerUtils.getInstalledContainerEntry(jproj, classpathContainerPath);
	}

	private Set knownProjects = new HashSet();
	
	private boolean isKnown(IProject project){
		return !knownProjects.add(project.getName());
	}
	
	private void forgetProject(IProject project){
		knownProjects.remove(project.getName());
	}
	
	public void dependencyGraphUpdate(IDependencyGraphUpdateEvent event) {
		if((event.getType() & IDependencyGraphUpdateEvent.ADDED) == IDependencyGraphUpdateEvent.ADDED){
			Map<IProject, Set<IProject>> addedReferences = event.getAddedReferences();
			for(IProject referencedProject : addedReferences.keySet()){
				queueUpdate(referencedProject);
				for(IProject referencingProject : addedReferences.get(referencedProject)){
					queueUpdate(referencingProject);
				}
			}
		}
	}
	
	public void resourceChanged(IResourceChangeEvent event) {
		boolean scheduleJob = false;
		try {
			pauseUpdates();
			switch (event.getType()){
				case IResourceChangeEvent.PRE_CLOSE:
				case IResourceChangeEvent.PRE_DELETE:
					IResource resource = event.getResource();
					if(resource.getType() == IResource.PROJECT){
						if(ModuleCoreNature.isFlexibleProject((IProject) resource)){
							if(JavaEEProjectUtilities.isEARProject((IProject)resource)){
								IProject earProject = (IProject) resource;
								
								IVirtualReference[] refs = EarUtilities.getComponentReferences(ComponentCore.createComponent(earProject));
								IVirtualComponent comp = null;
								for (int j = 0; j < refs.length; j++) {
									comp = refs[j].getReferencedComponent();
									if (!comp.isBinary()) {
										queueUpdateModule(comp.getProject());
									}
								}
									
							} else {
								IProject[] earProjects = EarUtilities.getReferencingEARProjects((IProject)resource);
								for(int i=0; i<earProjects.length; i++){
									queueUpdateEAR(earProjects[i]);
								}
							}
							forgetProject((IProject)resource);
						}
						JavaEEBinaryComponentHelper.clearAllArchivesInProject((IProject)resource);
					}
					break;
				case IResourceChangeEvent.POST_CHANGE:
					scheduleJob = true;
					event.getDelta().accept(this);
					IResourceDelta[] d = event.getDelta().getAffectedChildren();
					findNode(d);
			
					break;
			}
		} catch (CoreException e) {
			J2EEPlugin.logError(e);
		} 
		finally {
			resumeUpdates(scheduleJob);
		}
	}
	

	public static void clearResourceTreeRootCache(WorkbenchComponent aModule) {

		ResourceTreeRootAdapter resourceTreeAdapter = (ResourceTreeRootAdapter) ExtendedEcoreUtil
				.getAdapter(aModule, aModule.eAdapters(),
						ResourceTreeRootAdapter.DEPLOY_ADAPTER_TYPE);
		if(null != resourceTreeAdapter) {
			resourceTreeAdapter.setResourceTreeRoot(null);
		}
		resourceTreeAdapter = (ResourceTreeRootAdapter) ExtendedEcoreUtil
				.getAdapter(aModule, aModule.eAdapters(),
						ResourceTreeRootAdapter.SOURCE_ADAPTER_TYPE);
		if(null != resourceTreeAdapter){
			resourceTreeAdapter.setResourceTreeRoot(null);
		}
	}


	/*
	 * Needs to notice changes to MANIFEST.MF in any J2EE projects, changes to
	 * .component in any J2EE Projects, and any archive changes in EAR projects
	 */
	
	public boolean findNode(IResourceDelta[] delta) {

		for (int i = 0; i < delta.length; i++) {
			if (delta[i].toString().indexOf(IJ2EEModuleConstants.COMPONENT_FILE_NAME) != -1) {
				StructureEdit core = StructureEdit
						.getStructureEditForRead(delta[i].getResource()
								.getProject());
				if(null != core){
					WorkbenchComponent component = core.getComponent();
					if(component != null){
						clearResourceTreeRootCache(component);
					}
				}
			} else {
				findNode(delta[i].getAffectedChildren());
			}
		}

		return true;
	}
	
	public boolean visit(IResourceDelta delta) {
		// If it is only a marker change, ignore the change
		if(delta.getFlags() == IResourceDelta.MARKERS) {
			return false;
		}
		
		IResource resource = delta.getResource();
		switch (resource.getType()) {
		case IResource.ROOT:
			return true;
		case IResource.PROJECT:
			return ModuleCoreNature.isFlexibleProject((IProject) resource);
		case IResource.FOLDER: {
			if (resource.getName().equals(IJ2EEModuleConstants.DOT_SETTINGS)) {
				return true;
			}
			IVirtualComponent comp = ComponentCore.createComponent(resource.getProject());

			if (comp instanceof J2EEModuleVirtualComponent || comp instanceof EARVirtualComponent) {
				IVirtualFolder rootFolder = comp.getRootFolder();
				if (comp instanceof EARVirtualComponent) {
					return isRootAncester(resource, rootFolder) || isEARLibraryDirectory(resource, comp);
				}//else J2EEModuleVirtualComponent
				return isRootAncester(resource, rootFolder) || isFolder(resource, rootFolder.getFolder(J2EEConstants.META_INF));
			}
			return false;
		}
		case IResource.FILE: {
			String name = resource.getName();
			if (name.equals(WTPModulesResourceFactory.WTP_MODULES_SHORT_NAME)) {
				queueUpdate(resource.getProject());
			} else if (name.equals(J2EEConstants.MANIFEST_SHORT_NAME)) { // MANIFEST.MF must be all caps per spec
				IFile manifestFile = J2EEProjectUtilities.getManifestFile(resource.getProject(), false);
				if (null == manifestFile || resource.equals(manifestFile)) {
					queueUpdateModule(resource.getProject());
				}
			} else if (endsWithIgnoreCase(name, IJ2EEModuleConstants.JAR_EXT) || endsWithIgnoreCase(name, ".zip")) { //$NON-NLS-1$
				try {
					if (FacetedProjectFramework.hasProjectFacet(resource.getProject(), J2EEProjectUtilities.ENTERPRISE_APPLICATION)) {
						IVirtualComponent comp = ComponentCore.createComponent(resource.getProject());
						if(isFolder(resource.getParent(), comp.getRootFolder()) || isEARLibraryDirectory(resource, comp)){
							queueUpdateEAR(resource.getProject());
						}
					}
				} catch (CoreException e) {
					J2EEPlugin.logError(e);
				}
			}
		}
			
		}
		return false;
	}
	
	private boolean isEARLibraryDirectory(IResource resource, IVirtualComponent earComponent) {
		// check if the EAR component's version is 5 or greater
		IProject project = earComponent.getProject();
		if (!JavaEEProjectUtilities.isJEEComponent(earComponent, JavaEEProjectUtilities.DD_VERSION)
				|| !JavaEEProjectUtilities.isJEEComponent(earComponent, JavaEEProjectUtilities.FACET_VERSION)) {
			return false;
		}
		
		// retrieve the model provider
		IModelProvider modelProvider = ModelProviderManager.getModelProvider(project);
		if (modelProvider == null) return false;
		
		// retrieve the EAR's model object
		Application app = (Application) modelProvider.getModelObject();
		if (app == null) return false;
		
		// retrieve the library directory from the model
		String libDir = app.getLibraryDirectory();
		if (libDir == null) {
			// the library directory is not set - use the default one
			libDir = J2EEConstants.EAR_DEFAULT_LIB_DIR;
		}
		
		IVirtualFolder libFolder = earComponent.getRootFolder().getFolder(libDir); 
		if(resource.getType() == IResource.FILE){
			return isRootAncester(resource.getParent(), libFolder);
		}
		return isRootAncester(resource, libFolder);
	}
	
	public static boolean endsWithIgnoreCase(String str, String sfx) {
		return str.regionMatches(true, str.length() - sfx.length(), sfx, 0, sfx.length());
	}

	public static boolean isFolder(IResource resource, IVirtualFolder folder) {
		IContainer[] realFolders = folder.getUnderlyingFolders();
		for (int i = 0; i < realFolders.length; i++) {
			if (realFolders[i].equals(resource)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isRootAncester(IResource resource, IVirtualFolder rootFolder) {
		IContainer[] realRoots = rootFolder.getUnderlyingFolders();
		IPath currentResourcePath = resource.getFullPath();
		for (int i = 0; i < realRoots.length; i++) {
			if (currentResourcePath.isPrefixOf(realRoots[i].getFullPath()))
				return true;
		}
		return false;
	}

	public static boolean shouldUpdateDependencyGraph()
	{
		return updateDependencyGraph;
	}
	
	// [202820]
	public static void setUpdateDependencyGraph(boolean value)
	{
		updateDependencyGraph = value;
	}
}
