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
package org.eclipse.jst.j2ee.internal.archive.operations;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.internal.resources.ProjectDescription;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.jst.j2ee.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverter;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverterImpl;

/**
 * Insert the type's description here. Creation date: (5/10/2001 3:54:51 PM)
 * 
 * @author: Administrator
 */
public abstract class J2EELoadStrategyImpl extends LoadStrategyImpl implements IJ2EEImportExportConstants {
	protected IProject project;
	protected ArrayList filesList;
	//This one is used for the dot files in the project
	protected WorkbenchURIConverter projectMetaURIConverter;
	protected WorkbenchURIConverter projectURIConverter;
	protected Set visitedURIs;
	private boolean exportSource = false;
	private boolean includeProjectMetaFiles = false;
	protected List sourceFolders;

	/**
	 * J2EELoadStrategyImpl constructor comment.
	 */
	public J2EELoadStrategyImpl() {
		super();
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public boolean contains(String uri) {

		if (projectURIConverter == null)
			getProjectURIConverter();
		return projectURIConverter.getFile(uri).exists();

	}

	/* should never get called outside of runtime */
	protected boolean primContains(String uri) {
		return false;
	}

	protected long getLastModified(IResource aResource) {
		return aResource.getLocation().toFile().lastModified();
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public java.util.List getFiles() {

		filesList.clear();
		try {

			List projMembers = Arrays.asList(project.members());
			List exopProjMembers = new ArrayList();
			Iterator iterator = projMembers.iterator();
			while (iterator.hasNext()) {
				exopProjMembers.add(iterator.next());
			}

			filesList = getFiles(exopProjMembers);

		} catch (Exception exc) {
			throw new ArchiveRuntimeException(EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_FilesFromProject"), exc); //$NON-NLS-1$
			//$NON-NLS-1$
		}
		return filesList;
	}

	protected java.util.ArrayList getFiles(List projectResources) throws Exception {
		if (projectResources.isEmpty()) {
			return filesList;
		}

		IContainer outputFolder = ProjectUtilities.getJavaProjectOutputContainer(getProject());

		Iterator iterator = projectResources.iterator();
		while (iterator.hasNext()) {
			File cFile = null;
			IResource res = (IResource) (iterator.next());
			if ((res.getType() == IResource.FILE)) {

				//We have to avoid duplicates between the source and output folders (non-java
				// resources)
				IPath path = getOutputPathForFile(res.getProjectRelativePath());
				String uri = path == null ? null : path.toString();

				//Avoid adding files in source folder which are not in output folder.
				//unless they are .java or .sqlj (source)
				if (outputFolder != null && !outputFolder.exists(path)) {
					if (uri != null && !isSource(uri) && !uri.equals(ProjectDescription.DESCRIPTION_FILE_NAME) && !uri.equals(".classpath")) //$NON-NLS-1$
						continue;
				}

				if (uri == null)
					continue;
				if (!shouldInclude(uri)) //$NON-NLS-1$
					continue;
				if (getVisitedURIs().contains(uri))
					continue;
				cFile = createFile(uri);
				cFile.setLastModified(getLastModified(res));
				getVisitedURIs().add(uri);
				filesList.add(cFile);
			} else {
				if (shouldInclude((IContainer) res))
					getFiles(Arrays.asList(((IContainer) res).members()));
			}
		}
		return filesList;
	}

	protected boolean isSource(String uri) {
		if (uri == null)
			return false;
		return uri.endsWith(ArchiveUtil.DOT_JAVA) || uri.endsWith(ArchiveUtil.DOT_SQLJ);// ||
		// uri.equals(ProjectDescription.DESCRIPTION_FILE_NAME)
		// ||
		// uri.equals(".classpath");
	}

	protected boolean shouldInclude(IContainer iContainer) {
		return true;
	}

	protected boolean shouldInclude(String uri) {
		return isExportSource() || !isSource(uri);
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public java.io.InputStream getInputStream(String uri) throws IOException, FileNotFoundException {

		try {
			if (projectURIConverter == null)
				getProjectURIConverter();
			if (isProjectMetaFile(uri))
				return getProjectMetaURIConverter().createInputStream(URI.createURI(uri));
			return projectURIConverter.createInputStream(URI.createURI(uri));
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw new ArchiveRuntimeException(e);
		}
	}

	protected boolean isProjectMetaFile(String uri) {
		return PROJECT_FILE_URI.equals(uri) || CLASSPATH_FILE_URI.equals(uri);
	}

	public abstract IContainer getModuleContainer();

	protected IPath getOutputPathForFile(IPath aPath) throws Exception {
		if (isProjectMetaFile(aPath.toString())) {
			if (includeProjectMetaFiles)
				return aPath;
			return null;
		}
		IContainer moduleContainer = getModuleContainer();
		IPath moduleContainerPath = moduleContainer.getProjectRelativePath();
		//check if the file is an output folder file
		if (aPath.segmentCount() > moduleContainerPath.segmentCount() && aPath.removeLastSegments(aPath.segmentCount() - moduleContainerPath.segmentCount()).equals(moduleContainerPath)) {
			IPath tempPath = aPath.removeFirstSegments(moduleContainerPath.segmentCount());
			if (moduleContainer.exists(tempPath)) {
				return tempPath;
			}
		}
		//if this file is in a source folder, find the source folder
		//and remove the source folder portion from the path
		List lSourceFolders = getSourceFolders();
		for (int i = 0; i < lSourceFolders.size(); i++) {
			IPath folderPath = ((IFolder) lSourceFolders.get(i)).getProjectRelativePath();
			if (aPath.segmentCount() > folderPath.segmentCount() && aPath.removeLastSegments(aPath.segmentCount() - folderPath.segmentCount()).equals(folderPath)) {
				return aPath.removeFirstSegments(folderPath.segmentCount());
			}
		}
		return null;
	}

	//		int charIndex = 0;

	//		ArrayList tokens = new ArrayList();
	//		String pathString = aPath.toString();
	//
	//		for (int i = 0; i < pathString.length(); i++) {
	//			if (pathString.charAt(i) == '/') {
	//				tokens.add(pathString.substring(charIndex, i));
	//				charIndex = ++i;
	//			}
	//		}

	//		int indexOfModule = -1;
	//		IContainer outputFolder = ProjectUtilities.getJavaProjectOutputContainer(getProject());
	// check if file is an output folder file

	//		if (tokens.contains(getModuleFolderName())) {
	//			indexOfModule = tokens.indexOf(getModuleFolderName());
	//			// make sure the path exists, if not, we should check source folders
	//			if (!outputFolder.exists(aPath.removeFirstSegments(indexOfModule + 1)))
	//				indexOfModule = -1;
	//		}
	// check if file is a source folder file
	//		if (indexOfModule == -1) {
	//			List localSourceFolders = getSourceFoldersNames();
	//			if (localSourceFolders != null) {
	//				for (int j = 0; j < localSourceFolders.size(); j++) {
	//					if (!tokens.isEmpty()) {
	//						if (((String) tokens.get(0)).equals(localSourceFolders.get(j))) {
	//							indexOfModule = tokens.indexOf(localSourceFolders.get(j));
	//							break;
	//						}
	//						continue;
	//					}
	//				}
	//			}
	//		}
	//		if (indexOfModule > -1)
	//			return aPath.removeFirstSegments(indexOfModule + 1);
	//		return null;
	//	}
	/**
	 * Insert the method's description here. Creation date: (7/19/2001 3:58:31 PM)
	 * 
	 * @return org.eclipse.core.resources.IProject
	 */
	public org.eclipse.core.resources.IProject getProject() {
		return project;
	}

	public abstract WorkbenchURIConverter getProjectURIConverter();

	public String getSourceFolderName() throws Exception {
		try {
			return ProjectUtilities.getSourceFolderOrFirst(project, null).getName();
		} catch (Exception e) {
			throw new SaveFailureException(EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_ProjectNature"), e);//$NON-NLS-1$
		}

	}

	public List getSourceFoldersNames() throws Exception {
		try {
			List sourceFolderNames = new ArrayList();
			List localSourceFolders = getSourceFolders();
			for (int i = 0; i < localSourceFolders.size(); i++) {
				sourceFolderNames.add(((org.eclipse.core.resources.IFolder) localSourceFolders.get(i)).getName());
			}
			return sourceFolderNames;

		} catch (Exception e) {
			throw new SaveFailureException(EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_ProjectNature"), e); //$NON-NLS-1$
		}
	}


	protected List getSourceFolders() {
		if (sourceFolders == null) {
			sourceFolders = ProjectUtilities.getSourceContainers(project);
		}
		return sourceFolders;
	}

	public Set getVisitedURIs() {
		if (visitedURIs == null)
			visitedURIs = new HashSet();
		return visitedURIs;
	}

	public boolean isClassLoaderNeeded() {
		return false;
	}

	/**
	 * Insert the method's description here. Creation date: (4/19/2001 6:07:54 PM)
	 * 
	 * @return boolean
	 */
	public boolean isExportSource() {
		return exportSource;
	}

	/**
	 * Insert the method's description here. Creation date: (4/19/2001 6:07:54 PM)
	 * 
	 * @param newExportSource
	 *            boolean
	 */
	public void setExportSource(boolean newExportSource) {
		exportSource = newExportSource;
	}

	/**
	 * Returns the includeProjectMetaFiles.
	 * 
	 * @return boolean
	 */
	public boolean shouldIncludeProjectMetaFiles() {
		return includeProjectMetaFiles;
	}


	/**
	 * Sets the includeProjectMetaFiles.
	 * 
	 * @param includeProjectMetaFiles
	 *            The includeProjectMetaFiles to set
	 */
	public void setIncludeProjectMetaFiles(boolean includeProjectMetaFiles) {
		this.includeProjectMetaFiles = includeProjectMetaFiles;
	}

	protected URIConverter getProjectMetaURIConverter() {
		if (projectMetaURIConverter == null)
			projectMetaURIConverter = new WorkbenchURIConverterImpl(getProject());
		return projectMetaURIConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl#requiresIterationOnSave()
	 */
	public boolean requiresIterationOnSave() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.commonarchivecore.strategy.LoadStrategyImpl#initializeResourceSet()
	 */
	protected void initializeResourceSet() {
		resourceSet = WorkbenchResourceHelper.getResourceSet(project);
	}

	/*
	 * Overrode from super to bypass SAX
	 */
	protected Registry createResourceFactoryRegistry() {
		return new J2EEResourceFactoryRegistry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.commonarchivecore.strategy.LoadStrategyImpl#getAbsolutePath()
	 */
	public String getAbsolutePath() throws FileNotFoundException {
		return project.getLocation().toOSString();
	}
}