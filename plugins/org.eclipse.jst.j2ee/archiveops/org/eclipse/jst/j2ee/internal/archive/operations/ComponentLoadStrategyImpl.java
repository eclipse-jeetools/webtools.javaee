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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIterator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIteratorImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.ContainerImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.ArchiveMessages;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyEnablement;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyManifestUtil;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyVirtualComponent;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.DependencyType;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualContainer;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public abstract class ComponentLoadStrategyImpl extends LoadStrategyImpl {

	protected static final String DOT_PROJECT = ".project"; //$NON-NLS-1$
	protected static final String DOT_CLASSPATH = ".classpath"; //$NON-NLS-1$
	protected static final String DOT_SETTINGS = ".settings"; //$NON-NLS-1$
	protected static final String DOT_CVS_IGORE = ".cvsignore"; //$NON-NLS-1$

	protected IVirtualComponent vComponent;
	protected boolean exportSource;
	protected ArtifactEdit artifactEdit;
	private List zipFiles = new ArrayList();
	private List javaClasspathURIs = new ArrayList();
	protected boolean includeClasspathComponents = true;

	protected class FilesHolder {

		private Map urisToFiles = new HashMap();
		private Map urisToResources = new HashMap();
		private Map resourcesToURI = new HashMap();
		private Map urisToDiskFiles;
		private Map urisToZipEntry = new HashMap();

		public void removeIFile(IFile file) {
			String uri = (String) resourcesToURI.get(file);
			remove(uri);
		}

		public void remove(String uri) {
			urisToFiles.remove(uri);
			Object resource = urisToResources.remove(uri);
			if (resource != null) {
				resourcesToURI.remove(resource);
			}
			if (urisToDiskFiles != null) {
				urisToDiskFiles.remove(uri);
			}
		}

		public void addDirectory(File directory) {
			String uri = directory.getURI();
			urisToFiles.put(uri, directory);
		}

		public void addFile(File file) {
			String uri = file.getURI();
			urisToFiles.put(uri, file);
		}

		public void addFile(File file, java.io.File externalDiskFile) {
			String uri = file.getURI();
			urisToFiles.put(uri, file);
			if (null == urisToDiskFiles) {
				urisToDiskFiles = new HashMap();
			}
			urisToDiskFiles.put(uri, externalDiskFile);
		}

		public void addFile(File file, IResource resource) {
			String uri = file.getURI();
			urisToFiles.put(uri, file);
			urisToResources.put(uri, resource);
		}

		public InputStream getInputStream(String uri) throws IOException, FileNotFoundException {
			java.io.File diskFile = null;

			if (urisToDiskFiles != null && urisToDiskFiles.containsKey(uri)) {
				diskFile = (java.io.File) urisToDiskFiles.get(uri);
			} else if (urisToResources != null && urisToResources.containsKey(uri)) {
				IResource resource = (IResource) urisToResources.get(uri);
				diskFile = new java.io.File(resource.getLocation().toOSString());
			} else {
				Map fileURIMap = (Map) urisToZipEntry.get(uri);
				Iterator it = fileURIMap.keySet().iterator();

				String sourceFileUri = ""; //$NON-NLS-1$
				ZipFile zipFile = null;

				// there is only one key, pair
				while (it.hasNext()) {
					sourceFileUri = (String) it.next();
					zipFile = (ZipFile) fileURIMap.get(sourceFileUri);
				}
				if(zipFile == null){
					throw new FileNotFoundException(uri);
				}
				ZipEntry entry = zipFile.getEntry(sourceFileUri);
				InputStream in = zipFile.getInputStream(entry);
				return in;
			}
			return new FileInputStream(diskFile);
		}

		public List getFiles() {
			return new ArrayList(urisToFiles.values());
		}

		public boolean contains(String uri) {
			return urisToFiles.containsKey(uri);
		}

		public void addEntry(ZipEntry entry, ZipFile zipFile, IPath runtimePath) {
			String uri = runtimePath == null ? null : runtimePath.toString();
			String fileURI = ""; //$NON-NLS-1$
			if (uri != null) {
				if (!uri.equals("/")) //$NON-NLS-1$
					fileURI = uri + entry.getName();
				else
					fileURI = entry.getName();
			} else {
				fileURI = entry.getName();
			}

			File file = createFile(fileURI);

			Map fileURIMap = new HashMap();
			fileURIMap.put(entry.getName(), zipFile);

			urisToZipEntry.put(file.getURI(), fileURIMap);
			urisToFiles.put(file.getURI(), file);
		}
	}

	protected FilesHolder filesHolder;

	private Exception exception; //TEMP TODO REMOVE
	
	private IVirtualFile manifestFile = null;
	 
	public ComponentLoadStrategyImpl(IVirtualComponent vComponent) {
		this(vComponent, true);
	}
	
	public ComponentLoadStrategyImpl(IVirtualComponent vComponent, boolean includeClasspathComponents) {
		this.vComponent = vComponent;
		filesHolder = new FilesHolder();
		exception = new Exception();
		this.includeClasspathComponents = includeClasspathComponents;
		if (includeClasspathComponents && ClasspathDependencyEnablement.isAllowClasspathComponentDependency()) {
			this.manifestFile = vComponent.getRootFolder().getFile(new Path(J2EEConstants.MANIFEST_URI));
			saveJavaClasspathReferences();
		}
	}

	@Override
	public boolean contains(String uri) {
		IVirtualFolder rootFolder = vComponent.getRootFolder();
		return rootFolder.getFile(new Path(uri)).exists();
	}

	@Override
	protected void initializeResourceSet() {
		resourceSet = WorkbenchResourceHelperBase.getResourceSet(vComponent.getProject());
	}

	@Override
	protected boolean primContains(String uri) {
		return false;
	}

	@Override
	public List getFiles() {
		aggregateSourceFiles();
		aggregateClassFiles();
		addUtilities();
		return filesHolder.getFiles();
	}
	
	/**
	 * Adds library cp entries that point to class folders and have been tagged with the publish/export attribute.
	 */
	protected void addMappedClassFolders(final IPath targetRuntimePath) {
		// retrieve all mapped class folders
		if (vComponent instanceof J2EEModuleVirtualComponent) {
			try {
				final J2EEModuleVirtualComponent j2eeComponent = (J2EEModuleVirtualComponent) vComponent;
				final IVirtualReference[] cpRefs = j2eeComponent.getJavaClasspathReferences();
				for (int j = 0; j < cpRefs.length; j++) {
					final IVirtualReference ref = cpRefs[j];
					final IPath runtimePath = ref.getRuntimePath();
					// only process mappings with the specified runtime path
					if (ref.getReferencedComponent() instanceof ClasspathDependencyVirtualComponent
							&& runtimePath.equals(targetRuntimePath)) {
						final ClasspathDependencyVirtualComponent comp = (ClasspathDependencyVirtualComponent) ref.getReferencedComponent();
						if (comp.isClassFolder()) {
							final IContainer classFolder = comp.getClassFolder();
							if (classFolder != null && classFolder.exists()) {
								aggregateOutputFiles(new IResource[]{classFolder}, runtimePath.makeRelative(), classFolder.getProjectRelativePath().segmentCount());
							}
						}
					}
				}
			} catch (CoreException e) {
				J2EEPlugin.logError(e);
			}
		}
	}
	
    protected void saveJavaClasspathReferences() {
        if (vComponent instanceof J2EEModuleVirtualComponent) {
        	final J2EEModuleVirtualComponent j2eeComp = (J2EEModuleVirtualComponent) vComponent;
        	final IVirtualReference[] refs = j2eeComp.getJavaClasspathReferences();
            if (refs == null) {
                return;
            }
            for (int i = 0; i < refs.length; i++) {
            	if (refs[i].getRuntimePath().equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
            		javaClasspathURIs.add(refs[i].getArchiveName());
            	}
            }
        }
    }

	protected void addUtilities() {
		IVirtualReference[] components = vComponent.getReferences();
		for (int i = 0; i < components.length; i++) {
			IVirtualReference reference = components[i];
			IVirtualComponent referencedComponent = reference.getReferencedComponent();
			if (referencedComponent.isBinary() && reference.getDependencyType() == DependencyType.CONSUMES) {
				java.io.File diskFile = referencedComponent.getAdapter(java.io.File.class);
				ZipFile zipFile;
				IPath path = reference.getRuntimePath();
				try {
					zipFile = org.eclipse.jst.jee.archive.internal.ArchiveUtil.newZipFile(diskFile);
					zipFiles.add(zipFile);
					Enumeration enumeration = zipFile.entries();
					while (enumeration.hasMoreElements()) {
						ZipEntry entry = (ZipEntry) enumeration.nextElement();
						filesHolder.addEntry(entry, zipFile, path);
					}
				} catch (ZipException e) {
					J2EEPlugin.logError(e);
				} catch (IOException e) {
					J2EEPlugin.logError(e);
				}
			}
		}
	}

	/**
	 *	This is a cache of the IResource roots for all java source folders and is used by
	 *  {@link #inJavaSrc(IVirtualResource)}.
	 */
	private IResource[] sourceRoots = null;
	
	protected void aggregateSourceFiles() {
		try {
			IVirtualFolder rootFolder = vComponent.getRootFolder();
			IVirtualResource[] members = rootFolder.members();
			IPackageFragmentRoot[] srcPkgs = J2EEProjectUtilities.getSourceContainers(vComponent.getProject());
			sourceRoots = new IResource[srcPkgs.length];
			for (int i = 0; i < srcPkgs.length; i++) {
				sourceRoots[i] = srcPkgs[i].getCorrespondingResource();
			}
			inJavaSrc = false;
			aggregateFiles(members); 
		} catch (CoreException e) {
			J2EEPlugin.logError(e);
		}
	}

	protected void aggregateClassFiles() {
		StructureEdit se = null;
		try {
			IPackageFragmentRoot[] sourceRoots = J2EEProjectUtilities.getSourceContainers(vComponent.getProject());
			se = StructureEdit.getStructureEditForRead(vComponent.getProject());
			for (int i = 0; i < sourceRoots.length; i++) {
				IPath outputPath = sourceRoots[i].getRawClasspathEntry().getOutputLocation();
				if (outputPath == null) {
					IProject project = vComponent.getProject();
					if (project.hasNature(JavaCore.NATURE_ID)) {
						IJavaProject javaProject = JavaCore.create(project);
						outputPath = javaProject.getOutputLocation();
					}
				}
				if (outputPath != null) {
					IContainer javaOutputContainer = outputPath.segmentCount() > 1 ? (IContainer) ResourcesPlugin.getWorkspace().getRoot().getFolder(outputPath) : (IContainer) ResourcesPlugin.getWorkspace().getRoot().getProject(outputPath.lastSegment());
					IPath runtimePath = null;
					try {
						ComponentResource[] componentResources = se.findResourcesBySourcePath(sourceRoots[i].getResource().getProjectRelativePath());
						if (componentResources.length > 0) {
							IPath tmpRuntimePath = componentResources[0].getRuntimePath();
							IPath tmpSourcePath = componentResources[0].getSourcePath();
							if (!tmpRuntimePath.equals(tmpSourcePath)) {
								while (tmpSourcePath.segmentCount() > 0 && tmpRuntimePath.segmentCount() > 0 && tmpRuntimePath.lastSegment().equals(tmpSourcePath.lastSegment())) {
									tmpRuntimePath = tmpRuntimePath.removeLastSegments(1);
									tmpSourcePath = tmpSourcePath.removeLastSegments(1);
								}
								if (tmpRuntimePath.segmentCount() != 0) {
									runtimePath = tmpRuntimePath.makeRelative();
								}
							}
						}
					} catch (UnresolveableURIException e) {
						J2EEPlugin.logError(e);
					}
					if (null == runtimePath) {
						runtimePath = new Path(""); //$NON-NLS-1$
					}
					aggregateOutputFiles(new IResource[]{javaOutputContainer}, runtimePath, javaOutputContainer.getProjectRelativePath().segmentCount());
				}
			}				
		} catch (CoreException e) {
			J2EEPlugin.logError(e);
		} finally {
			if (se != null) {
				se.dispose();
			}
		}
	}
	
	protected boolean aggregateOutputFiles(IResource[] resources, final IPath runtimePathPrefix, int outputFolderSegmentCount) throws CoreException {
		boolean fileAdded = false;
		for (int i = 0; i < resources.length; i++) {
			File cFile = null;
			if (!resources[i].exists()) {
				continue;
			}
			// We have to avoid duplicates between the source and output folders (non-java
			// resources)
			IPath runtimePath = runtimePathPrefix.append(resources[i].getProjectRelativePath().removeFirstSegments(outputFolderSegmentCount));
			String uri = runtimePath == null ? null : runtimePath.toString();
			if (uri == null)
				continue;
			if (resources[i].getType() == IResource.FILE) {
				if (!shouldInclude(uri))
					continue;
				cFile = createFile(uri);
				cFile.setLastModified(getLastModified(resources[i]));
				filesHolder.addFile(cFile, resources[i]);
				fileAdded = true;
			} else if (shouldInclude((IContainer) resources[i])) {
				IResource[] nestedResources = ((IContainer) resources[i]).members();
				aggregateOutputFiles(nestedResources, runtimePathPrefix, outputFolderSegmentCount);
				if(!filesHolder.contains(uri)){
					if (!shouldInclude(uri))
						continue;
					cFile = createDirectory(uri);
					cFile.setLastModified(getLastModified(resources[i]));
					filesHolder.addDirectory(cFile);
					fileAdded = true;
				}
			}
		}
		return fileAdded;
	}

	/**
	 * This is used to track whether {@link #aggregateFiles(IVirtualResource[])} is currently within a Java Source folder.
	 */
	private boolean inJavaSrc = false;
	
	protected boolean aggregateFiles(IVirtualResource[] virtualResources) throws CoreException {
		boolean fileAdded = false;
		for (int i = 0; i < virtualResources.length; i++) {
			File cFile = null;
			if (!virtualResources[i].exists()) {
				continue;
			}
			// We have to avoid duplicates between the source and output folders (non-java
			// resources)
			IPath runtimePath = virtualResources[i].getRuntimePath();
			String uri = runtimePath == null ? null : runtimePath.toString();
			if (uri == null)
				continue;
			if (uri.charAt(0) == IPath.SEPARATOR) {
				uri = uri.substring(1);
			}
			if (filesHolder.contains(uri))
				continue;
			
			if (virtualResources[i].getType() == IVirtualResource.FILE) {
				if (!shouldInclude(uri))
					continue;
				IResource resource = virtualResources[i].getUnderlyingResource();
				// want to ignore derived resources nested within Java src directories; this covers the case where
				// a user has nested a Java output directory within a Java src directory (note: should ideally be 
				// respecting Java src path exclusion filters)
				if (inJavaSrc && resource.isDerived()) {
					continue;
				}
				cFile = createFile(uri);
				cFile.setLastModified(getLastModified(resource));
				filesHolder.addFile(cFile, resource);
				fileAdded = true;
			} else if (shouldInclude((IVirtualContainer) virtualResources[i])) {
				boolean inJavaSrcAtThisLevel = inJavaSrc;
				try {
					if (!inJavaSrc) {
						// if not already inside a Java src dir, check again
						inJavaSrc = inJavaSrc(virtualResources[i]);
					}
					IVirtualResource[] nestedVirtualResources = ((IVirtualContainer) virtualResources[i]).members();
					aggregateFiles(nestedVirtualResources);
					if(!filesHolder.contains(uri)){
						if (!shouldInclude(uri))
							continue;
						IResource resource = virtualResources[i].getUnderlyingResource();
						if (inJavaSrc && resource.isDerived()) {
							continue;
						}
						cFile = createDirectory(uri);
						cFile.setLastModified(getLastModified(resource));
						filesHolder.addDirectory(cFile);
						fileAdded = true;
					}
				} finally {
					inJavaSrc = inJavaSrcAtThisLevel;
				}
			}
		}
		return fileAdded;
	}
	
	/**
	 * Determines if the specified IVirtualResource maps to a IResource that is contained within a Java src root.
	 * @param virtualResource IVirtualResource to check.
	 * @param sourceRoots Current Java src roots.
	 * @return True if contained in a Java src root, false otherwise.
	 */
	protected boolean inJavaSrc(final IVirtualResource virtualResource) {
		if (sourceRoots.length == 0) {
			return false;
		}
		// all mapped resources must be associated with Java src for the resource to be considered in Java src
		final IResource[] resources = virtualResource.getUnderlyingResources();
		boolean inJavaSrc = false;
		for (int i = 0; i < resources.length; i++) {
			inJavaSrc = false;
			for (int j = 0; j < sourceRoots.length; j++) {
				if (sourceRoots[j].getFullPath().isPrefixOf(resources[i].getFullPath())) {
					inJavaSrc = true;
					break;
				}
			}
			// if this one was not in Java src, can break
			if (!inJavaSrc) {
				break;
			}
		}
		
		return inJavaSrc;
	}

	protected long getLastModified(IResource aResource) {
		return aResource.getLocation().toFile().lastModified();
	}

	public void setExportSource(boolean newExportSource) {
		exportSource = newExportSource;
	}

	public boolean isExportSource() {
		return exportSource;
	}

	protected boolean shouldInclude(IContainer aContainer) {
		return true;
	}

	protected boolean shouldInclude(IVirtualContainer vContainer) {
		IContainer iContainer = (IContainer) vContainer.getUnderlyingResource();
		return shouldInclude(iContainer);
	}

	protected boolean shouldInclude(String uri) {
		if (uri.length() == 0 || DOT_PROJECT.equals(uri) || DOT_CLASSPATH.equals(uri) || uri.endsWith(DOT_CVS_IGORE) || uri.startsWith(DOT_SETTINGS)){
			return false;
		}
		return isExportSource() || !isSource(uri);
	}

	protected boolean isSource(String uri) {
		if (uri == null)
			return false;
		return uri.endsWith(ArchiveUtil.DOT_JAVA) || uri.endsWith(ArchiveUtil.DOT_SQLJ);
	}

	protected void addExternalFile(String uri, java.io.File externalDiskFile) {
		File aFile = createFile(uri);
		filesHolder.addFile(aFile, externalDiskFile);
	}
	
	@Override
	public InputStream getInputStream(String uri) throws IOException, FileNotFoundException {
		// If the MANIFEST.MF of a module component is being requested and that module component references
        // Java build path-based components, need to dynamically update the manifest classpath to reflect the resolved
        // contributions from the build path
		if (includeClasspathComponents && 
			uri.equals(J2EEConstants.MANIFEST_URI) && !javaClasspathURIs.isEmpty() &&
			manifestFile != null && manifestFile.getUnderlyingFile() != null && 
			manifestFile.getUnderlyingFile().exists()) {
			//update the manifest classpath for the component
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ClasspathDependencyManifestUtil.updateManifestClasspath(manifestFile.getUnderlyingFile(), javaClasspathURIs, baos);
	        return new ByteArrayInputStream(baos.toByteArray());
		}
		
		if (filesHolder.contains(uri)) {
			return filesHolder.getInputStream(uri);
		}
		IVirtualFolder rootFolder = vComponent.getRootFolder();
		IVirtualResource vResource = rootFolder.findMember(uri);
		String filePath = null;
		if (null != vResource && vResource.exists()) {
			filePath = vResource.getUnderlyingResource().getLocation().toOSString();
			java.io.File file = new java.io.File(filePath);
			return new FileInputStream(file);
		}
		String eString = EARArchiveOpsResourceHandler.ARCHIVE_OPERATION_FileNotFound;
		throw new FileNotFoundException(eString);
	}

	@Override
	public Collection getLoadedMofResources() {
		Collection resources = super.getLoadedMofResources();
		Collection resourcesToRemove = new ArrayList();
		Iterator iterator = resources.iterator();
		while (iterator.hasNext()) {
			Resource res = (Resource) iterator.next();
			//only remove component files that are in the settings directory
			if (res.getURI().toString().endsWith(IModuleConstants.COMPONENT_FILE_NAME) && res.getURI().segmentsList().contains(IModuleConstants.DOT_SETTINGS))
				resourcesToRemove.add(res);
		}
		resources.removeAll(resourcesToRemove);
		return resources;
	}

	protected synchronized ArtifactEdit getArtifactEditForRead() {
		if(artifactEdit == null){
			artifactEdit = ArtifactEdit.getArtifactEditForRead(vComponent);
		}
		return artifactEdit;
	}
	
	@Override
	public Resource getMofResource(String uri) throws FileNotFoundException, ResourceLoadException {
		return getArtifactEditForRead().getResource(URI.createURI(uri));
	}

	@Override
	public boolean isClassLoaderNeeded() {
		return false;
	}

	public IVirtualComponent getComponent() {
		return vComponent;
	}

	protected IProgressMonitor monitor = null;
	
	public void setProgressMonitor(IProgressMonitor monitor){
		this.monitor = monitor;
	}
	
	protected final int FILE_SAVE_WORK = 100;
	
	@Override
	public FileIterator getFileIterator() throws IOException {
		return new FileIteratorImpl(getContainer().getFiles()){
			protected SubProgressMonitor lastSubMon = null;
			boolean firstVisit = true;
			
			@Override
			public File next() {
				if(firstVisit){
					firstVisit = false;
					if(monitor != null){
						monitor.beginTask(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Exporting_archive, new Object [] { getContainer().getURI() }), files.size() * FILE_SAVE_WORK);
					}
				}
				if(lastSubMon != null){
					lastSubMon.done();
					lastSubMon = null;
				} else if(monitor != null){
					monitor.worked(FILE_SAVE_WORK);
				}
				File file = super.next();
				if(monitor != null){
					if(file.isContainer() && ComponentLoadStrategyImpl.class.isInstance(((ContainerImpl)file).getLoadStrategy())){
						ComponentLoadStrategyImpl ls = (ComponentLoadStrategyImpl)((ContainerImpl)file).getLoadStrategy();
						lastSubMon = new SubProgressMonitor(monitor, FILE_SAVE_WORK, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
						ls.setProgressMonitor(lastSubMon);
					} else {
						monitor.subTask(file.getURI());
					}
				}
				return file;
			}
		};
		
	}
	
	@Override
	public void close() {
		if(Thread.currentThread().toString().toLowerCase().indexOf("finalizer") != -1){ //$NON-NLS-1$
			J2EEPlugin.logError(ArchiveMessages.ComponentLoadStrategyImpl_Opener_of_Archive_did_not_close_it_, exception);
		}
		
		try{
			Iterator it = zipFiles.iterator();
			while (it.hasNext()) {
				ZipFile file = (ZipFile) it.next();
				try {
					file.close();
				} catch (IOException e) {
					J2EEPlugin.logError(e);
				}
			}
		} finally{
			try{
				synchronized(this) {
					if(artifactEdit != null){
						ArtifactEditModel editModel = (ArtifactEditModel)artifactEdit.getAdapter(ArtifactEditModel.class);
						if(editModel != null && !editModel.isDisposed()){
							artifactEdit.dispose();
						}
						artifactEdit = null;
					}
				}
			} finally {
				super.close();
			}
		}
		
	}
}
