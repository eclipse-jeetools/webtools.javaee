/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.archive.operations;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EELoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorNatureRuntime;

import com.ibm.wtp.emf.workbench.WorkbenchURIConverter;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverterImpl;

/**
 * Class defines the loading mechanism for RAR projects.
 */
public class RARProjectLoadStrategyImpl extends J2EELoadStrategyImpl {

	//The module root of the RAR, usually a "connectorModule" folder
	private IContainer moduleRoot;
	private int moduleRootSegmentCount;
	private Map innerClasses;

	public static String[] knownLibExtensions = {".jar", //$NON-NLS-1$
				".zip", //$NON-NLS-1$
				".so", //$NON-NLS-1$
				".o", //$NON-NLS-1$
				".sl", //$NON-NLS-1$
				".dll", //$NON-NLS-1$
	}; //$NON-NLS-1$

	public RARProjectLoadStrategyImpl(IProject aProject) {
		super();
		project = aProject;
		filesList = new ArrayList();
		init();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.archive.operations.J2EELoadStrategyImpl#getFiles()
	 */
	public List getFiles() {
		List files = super.getFiles();
		files.addAll(getNestedJARsFromSourceFolders());
		return files;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.archive.operations.J2EELoadStrategyImpl#getFiles(List)
	 */
	protected ArrayList getFiles(List projectResources) throws Exception {
		return super.getFiles(projectResources);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.archive.operations.J2EELoadStrategyImpl#shouldInclude(IContainer)
	 */
	protected boolean shouldInclude(IContainer iContainer) {
		return moduleRoot.equals(iContainer) || !getSourceFolders().contains(iContainer);
	}

	private Collection getNestedJARsFromSourceFolders() {
		List localSourceFolders = getSourceFolders();
		List result = new ArrayList(localSourceFolders.size());
		for (int i = 0; i < localSourceFolders.size(); i++) {
			IFolder folder = (IFolder) localSourceFolders.get(i);
			File aFile = getNestedJARFromSourceFolder(folder);
			if (aFile != null)
				result.add(aFile);
		}
		return result;
	}

	private File getNestedJARFromSourceFolder(IFolder folder) {
		List sourceIFiles = new ArrayList();
		boolean isModuleRoot = folder.equals(moduleRoot);
		boolean foundJava = gatherFilesForJAR(sourceIFiles, folder, folder, isModuleRoot, false);
		if (!isModuleRoot || foundJava) {
			return createNestedArchive(sourceIFiles, folder);
		}
		return null;
	}

	private File createNestedArchive(List sourceIFiles, IFolder folder) {
		NestedJARLoadStrategyImpl loader = new NestedJARLoadStrategyImpl(this, sourceIFiles, folder);
		loader.setExportSource(isExportSource());
		ArchiveOptions options = ((Archive) getContainer()).getOptions().cloneWith(loader);
		String uri = computeUniqueArchiveURI(folder);
		try {
			return getArchiveFactory().primOpenArchive(options, uri);
		} catch (OpenFailureException ex) {
			throw new ArchiveRuntimeException(ex);
		}
	}

	private String computeUniqueArchiveURI(IFolder folder) {
		int increment = 0;
		String name = folder.getName();
		StringBuffer sb = null;
		do {
			sb = new StringBuffer(name.length() + 5);
			sb.append('_');
			sb.append(name);
			if (increment > 0)
				sb.append(increment);
			sb.append(".jar"); //$NON-NLS-1$
			increment++;
		} while (visitedURIs.contains(sb.toString()));
		return sb.toString();
	}

	private boolean gatherFilesForJAR(List iFiles, IFolder current, IFolder root, boolean isModuleRoot, boolean foundJava) {
		IResource[] members;
		try {
			members = current.members();
		} catch (CoreException core) {
			throw new ArchiveRuntimeException(core);
		}
		for (int i = 0; i < members.length; i++) {
			IResource res = members[i];
			if (res.getType() == IResource.FOLDER) {
				foundJava = gatherFilesForJAR(iFiles, (IFolder) res, root, isModuleRoot, foundJava) || foundJava;
			} else {//it must be a file
				IFile iFile = (IFile) res;
				checkInnerClass(iFile);
				if (belongsInNestedJAR(iFile, root, isModuleRoot)) {
					iFiles.add(iFile);
					if (isModuleRoot)
						foundJava = foundJava || isJava(iFile) || isClass(iFile);
				}
			}
		}
		return foundJava;
	}

	private void checkInnerClass(IFile iFile) {
		if (!isClass(iFile))
			return;
		String name = iFile.getName();
		if (name.indexOf('$') != -1) {
			if (innerClasses == null)
				innerClasses = new HashMap();
			IPath relPath = iFile.getProjectRelativePath().removeFirstSegments(moduleRootSegmentCount);
			String key = relPath.toString();
			key = key.substring(0, key.indexOf('$'));
			List inners = (List) innerClasses.get(key);
			if (inners == null) {
				inners = new ArrayList(1);
				innerClasses.put(key, inners);
			}
			inners.add(iFile);
		}
	}

	public List retrieveInnerClasses(String key) {
		if (innerClasses == null)
			return null;
		return (List) innerClasses.get(key);
	}


	public boolean isJava(IFile iFile) {
		return hasExtension(iFile, ArchiveUtil.DOT_JAVA);
	}

	public boolean isClass(IFile iFile) {
		return hasExtension(iFile, ArchiveUtil.DOT_CLASS);
	}

	private boolean hasExtension(IFile iFile, String ext) {
		String name = iFile.getName();
		return hasExtension(name, ext);
	}

	private boolean hasExtension(String name, String ext) {
		int offset = ext.length();
		return name.regionMatches(true, name.length() - offset, ext, 0, offset);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.archive.operations.J2EELoadStrategyImpl#shouldInclude(String)
	 */
	protected boolean shouldInclude(String uri) {
		return !hasExtension(uri, ArchiveUtil.DOT_CLASS) && !hasExtension(uri, ArchiveUtil.DOT_JAVA);
	}

	private boolean belongsInNestedJAR(IFile iFile, IFolder root, boolean isModuleRoot) {
		for (int i = 0; i < knownLibExtensions.length; i++) {
			if (hasExtension(iFile, knownLibExtensions[i]) || (isModuleRoot && (isDeploymentDescriptor(iFile, root) || isClassWithSource(iFile))))
				return false;
		}
		return true;
	}

	/**
	 * @param iFile
	 * @return
	 */
	private boolean isClassWithSource(IFile iFile) {
		return isClass(iFile) && hasSource(iFile);
	}


	/**
	 * @param iFile
	 * @return
	 */
	private boolean hasSource(IFile classFile) {
		IPath path = classFile.getProjectRelativePath().removeFirstSegments(moduleRootSegmentCount);
		String baseURI = ArchiveUtil.classUriToJavaUri(path.toString());
		return moduleRoot.getFile(new Path(baseURI)).exists();
	}

	private boolean isDeploymentDescriptor(IFile iFile, IFolder root) {
		IPath path = iFile.getProjectRelativePath().removeFirstSegments(moduleRootSegmentCount);
		return ArchiveConstants.RAR_DD_URI.equals(path.toString());
	}

	public IContainer getModuleContainer() {
		return moduleRoot;
	}

	public IContainer getModuleRoot() {
		return moduleRoot;
	}

	protected void init() {
		ConnectorNatureRuntime connRT = ConnectorNatureRuntime.getRuntime(getProject());
		moduleRoot = connRT.getModuleServerRoot();
		projectURIConverter = new WorkbenchURIConverterImpl(moduleRoot);
		moduleRootSegmentCount = moduleRoot.getProjectRelativePath().segmentCount();
	}

	public WorkbenchURIConverter getProjectURIConverter() {
		return projectURIConverter;
	}

	/**
	 * Returns the moduleRootSegmentCount.
	 * 
	 * @return int
	 */
	public int getModuleRootSegmentCount() {
		return moduleRootSegmentCount;
	}


}