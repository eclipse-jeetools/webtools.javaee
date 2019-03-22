/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.archive.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.ComponentLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.jca.modulecore.util.ConnectorArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualContainer;

public class ConnectorComponentLoadStrategyImpl extends ComponentLoadStrategyImpl {

	private int dotJavaLength = ArchiveUtil.DOT_JAVA.length();
	private List alreadyIndexed = new ArrayList();
	private Map classesMap;
	private IFile knownDD;

	public static String[] knownLibExtensions = {IJ2EEModuleConstants.JAR_EXT,
				".zip", //$NON-NLS-1$
				".so", //$NON-NLS-1$
				".o", //$NON-NLS-1$
				".sl", //$NON-NLS-1$
				".dll", //$NON-NLS-1$
	};

	public ConnectorComponentLoadStrategyImpl(IVirtualComponent vComponent) {
		this(vComponent, true);
	}
	
	public ConnectorComponentLoadStrategyImpl(IVirtualComponent vComponent, boolean includeClasspathComponents) {
		super(vComponent, includeClasspathComponents);		
		knownDD = vComponent.getRootFolder().getFile(J2EEConstants.RAR_DD_URI).getUnderlyingFile();
	}

	@Override
	public List getFiles() {
		addNestedJARsFromSourceRoots();
		aggregateSourceFiles();
		return filesHolder.getFiles();
	}

	private void addNestedJARsFromSourceRoots() {
		IPackageFragmentRoot[] sourceRoots = J2EEProjectUtilities.getSourceContainers(vComponent.getProject());
		for (int i = 0; i < sourceRoots.length; i++) {
			File aFile;
			try {
				aFile = getNestedJar(sourceRoots[i]);
				if (null != aFile) {
					filesHolder.addFile(aFile);
				}
			} catch (JavaModelException e) {
				Logger.getLogger().logError(e);
			}
		}
	}



	private File getNestedJar(IPackageFragmentRoot sourceRoot) throws JavaModelException {
		IPath outputPath = sourceRoot.getRawClasspathEntry().getOutputLocation();
		if (outputPath == null) {
			IProject project = vComponent.getProject();
			try {
				if (project.hasNature(JavaCore.NATURE_ID)) {
					IJavaProject javaProject = JavaCore.create(project);
					outputPath = javaProject.getOutputLocation();
				}
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
			if (outputPath == null) {
				return null;
			}
		}

		IFolder javaOutputFolder = ResourcesPlugin.getWorkspace().getRoot().getFolder(outputPath);
		indexClassesForOutputFolder(javaOutputFolder);
		IContainer sourceContainer = (IContainer) sourceRoot.getResource();

		int sourceContainerSegmentCount = sourceContainer.getProjectRelativePath().segmentCount();
		boolean isModuleRoot = knownDD.getProjectRelativePath().toString().startsWith(sourceContainer.getProjectRelativePath().toString());
		Set iFilesSet = new HashSet();
		boolean foundJava = gatherFilesForJAR(iFilesSet, sourceContainer, isModuleRoot, false, sourceContainerSegmentCount);
		if (!isModuleRoot || foundJava) {
			List iFilesList = Collections.list(Collections.enumeration(iFilesSet));
			for (int i = 0; i < iFilesList.size(); i++) {
				filesHolder.removeIFile((IFile) iFilesList.get(i));
			}
			File nestedArchive = createNestedArchive(iFilesList, sourceContainer, javaOutputFolder);
			return nestedArchive;
		}
		return null;
	}

	private boolean gatherFilesForJAR(Collection iFiles, IContainer current, boolean isModuleRoot, boolean foundJava, int sourceContainerSegmentCount) {
		IResource[] members;
		try {
			members = current.members();
		} catch (CoreException core) {
			throw new ArchiveRuntimeException(core);
		}
		
		IContainer ddFolder = vComponent.getRootFolder().getFolder(J2EEConstants.META_INF).getUnderlyingFolder();
		
		boolean localFoundJava = foundJava;
		for (int i = 0; i < members.length; i++) {
			IResource res = members[i];
			if (res.getType() == IResource.FOLDER) {
				if (!ddFolder.equals(res)) {//if it's not the dd folder
					localFoundJava = gatherFilesForJAR(iFiles, (IFolder) res, isModuleRoot, localFoundJava, sourceContainerSegmentCount) || localFoundJava;
				}
			} else {// it must be a file
				IFile srcFile = (IFile) res;
				if (belongsInNestedJAR(srcFile, isModuleRoot)) {
					if (isJava(srcFile)) {
						if (exportSource) {
							iFiles.add(srcFile); //don't need to check duplicates here
						}
						String className = srcFile.getProjectRelativePath().removeFirstSegments(sourceContainerSegmentCount).toString();
						className = className.substring(0, className.length() - dotJavaLength);
						List classes = retrieveClasses(className);
						if (null != classes) {
							Iterator iterator = classes.iterator();
							while (iterator.hasNext()) {
								IFile clazz = (IFile) iterator.next();
								if(!iFiles.contains(clazz)){
									//.class need to check for duplicates
								iFiles.add(clazz);
							}
						}
						}
					} else {
						if(!iFiles.contains(srcFile)){
							//if it's not src, then it could be .class and need to check for duplicates
						iFiles.add(srcFile);
					}
					}
					if (isModuleRoot)
						localFoundJava = localFoundJava || isJava(srcFile) || isClass(srcFile);
				}
			}
		}
		return localFoundJava;
	}

	private File createNestedArchive(List files, IContainer sourceContainer, IFolder javaOutputFolder) {
		ConnectorComponentNestedJARLoadStrategyImpl loader = new ConnectorComponentNestedJARLoadStrategyImpl(files, sourceContainer, javaOutputFolder);
		ArchiveOptions options = ((Archive) getContainer()).getOptions().cloneWith(loader);
		String uri = computeUniqueArchiveURI(sourceContainer);
		try {
			return getArchiveFactory().primOpenArchive(options, uri);
		} catch (OpenFailureException ex) {
			throw new ArchiveRuntimeException(ex);
		}
	}

	private String computeUniqueArchiveURI(IResource resource) {
		int increment = 0;
		String name = resource.getName();
		StringBuffer sb = null;
		do {
			sb = new StringBuffer(name.length() + 5);
			sb.append('_');
			sb.append(name);
			if (increment > 0)
				sb.append(increment);
			sb.append(IJ2EEModuleConstants.JAR_EXT);
			increment++;
		} while (filesHolder.contains(sb.toString()));
		return sb.toString();
	}

	private boolean belongsInNestedJAR(IFile iFile, boolean isModuleRoot) {
		if (isModuleRoot && isDeploymentDescriptor(iFile)) {
			return false;
		}
		for (int i = 0; i < knownLibExtensions.length; i++) {
			if (hasExtension(iFile, knownLibExtensions[i]))
				return false;
		}
		return true;
	}
	@Override
	protected synchronized ArtifactEdit getArtifactEditForRead() {
		if(artifactEdit == null){
			artifactEdit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(vComponent);
		}
		return artifactEdit;
	}

	@Override
	protected boolean shouldInclude(IVirtualContainer vContainer) {
		boolean isDDFolder = vComponent.getRootFolder().getFolder(J2EEConstants.META_INF).equals(vContainer);
		return isDDFolder || !inJavaSrc(vContainer);
	}
	
	@Override
	protected boolean shouldInclude(String uri) {
		return !hasExtension(uri, ArchiveUtil.DOT_CLASS) && !hasExtension(uri, ArchiveUtil.DOT_JAVA);
	}

	/**
	 * Find all the .class files and index them so inner classes can be located.
	 */
	private void indexClassesForOutputFolder(IFolder javaOutputFolder) {
		if (null == javaOutputFolder || alreadyIndexed.contains(javaOutputFolder)) {
			return;
		}
		alreadyIndexed.add(javaOutputFolder);
		int segmentCount = javaOutputFolder.getProjectRelativePath().segmentCount();
		indexClasses(javaOutputFolder, segmentCount);
	}

	private void indexClasses(IResource resource, int javaOutputSegmentCount) {
		switch (resource.getType()) {
			case IResource.FILE :
				indexClass((IFile) resource, javaOutputSegmentCount);
				break;
			case IResource.FOLDER :
				try {
					IResource[] members = ((IFolder) resource).members();
					for (int i = 0; i < members.length; i++) {
						indexClasses(members[i], javaOutputSegmentCount);
					}
				} catch (CoreException e) {
					Logger.getLogger().logError(e);
				}
				break;
		}
	}

	private void indexClass(IFile iFile, int javaOutputSegmentCount) {
		if (!isClass(iFile))
			return;
		if (classesMap == null)
			classesMap = new HashMap();
		String name = iFile.getName();
		IPath relPath = iFile.getProjectRelativePath().removeFirstSegments(javaOutputSegmentCount);
		String key = relPath.toString();
		if (name.indexOf('$') != -1) {
			key = key.substring(0, key.indexOf('$'));
		} else {
			key = key.substring(0, key.indexOf('.'));
		}
		List inners = (List) classesMap.get(key);
		if (inners == null) {
			inners = new ArrayList(1);
			classesMap.put(key, inners);
		}
		inners.add(iFile);
	}

	public List retrieveClasses(String key) {
		if (classesMap == null)
			return null;
		return (List) classesMap.get(key);
	}

	public static boolean isJava(IFile iFile) {
		return hasExtension(iFile, ArchiveUtil.DOT_JAVA);
	}

	public static boolean isClass(IFile iFile) {
		return hasExtension(iFile, ArchiveUtil.DOT_CLASS);
	}

	private static boolean hasExtension(IFile iFile, String ext) {
		String name = iFile.getName();
		return hasExtension(name, ext);
	}

	private static boolean hasExtension(String name, String ext) {
		int offset = ext.length();
		return name.regionMatches(true, name.length() - offset, ext, 0, offset);
	}

	private boolean isDeploymentDescriptor(IFile iFile) {
		return knownDD.equals(iFile);
	}
}
