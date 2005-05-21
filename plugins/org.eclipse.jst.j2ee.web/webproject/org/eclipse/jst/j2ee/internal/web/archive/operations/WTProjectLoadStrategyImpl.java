/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverter;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverterImpl;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.ProjectSupportResourceHandler;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.web.internal.operation.ILibModule;

public class WTProjectLoadStrategyImpl extends org.eclipse.jst.j2ee.internal.archive.operations.J2EELoadStrategyImpl {
	private final static String SOURCE_DIR = "source"; //$NON-NLS-1$
	private final static String CLASSES_DIR = "classes"; //$NON-NLS-1$

	public static final String WEBSETTINGS_FILE_URI = ".j2ee"; //$NON-NLS-1$

	/**
	 * flag which indicates whether or not to export compiled JSP files (compiled files exist in
	 * /WEB-INF/classes as .class and .java[for debug])
	 */
	private boolean excludeCompiledJspFiles = false;

	/**
	 * EJBProjectLoadStrategyImpl constructor comment.
	 */
	public WTProjectLoadStrategyImpl(IProject aProject) {
		super();
		project = aProject;
		filesList = new ArrayList();
	}

	/**
	 * For each loose lib JAR project in this ear project, add an Archive to the list of files in
	 * the EAR
	 */
	public void addLooseLibJARsToFiles() {
		ILibModule[] libModules = getLibModules();
		for (int i = 0; i < libModules.length; i++) {
			ILibModule iLibModule = libModules[i];
			String uri = new Path(iLibModule.getURI()).makeRelative().toString();
			String projectName = iLibModule.getProjectName();
			try {
				Archive utilJAR = J2EEProjectUtilities.asArchive(uri, projectName, isExportSource(), shouldIncludeProjectMetaFiles());
				if (utilJAR == null)
					continue;
				filesList.add(utilJAR);
			} catch (OpenFailureException oe) {
				String message = ProjectSupportResourceHandler.getString("UNABLE_TO_LOAD_MODULE_ERROR_", new Object[]{uri, getProject().getName(), oe.getConcatenatedMessages()}); //$NON-NLS-1$
				org.eclipse.jem.util.logger.proxy.Logger.getLogger().logTrace(message);
			}
		}
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public java.util.List getFiles() {
		filesList.clear();
		try {
			// Determine if loose libs exist and set flag for meta files
			if (areLooseLibJarsIncluded()) {
				setIncludeProjectMetaFiles(true);
			}
			// First go through all of the files under the webApplication
			// directory, omitting the ones that are imported classes jars.
			List webAppFiles = new ArrayList(Arrays.asList(getModuleContainer().members()));
			if (shouldIncludeProjectMetaFiles()) {
				webAppFiles.add(getProject().getFile(PROJECT_FILE_URI));
				webAppFiles.add(getProject().getFile(CLASSPATH_FILE_URI));
				webAppFiles.add(getProject().getFile(WEBSETTINGS_FILE_URI));
			}
			IContainer outputContainer = JemProjectUtilities.getJavaProjectOutputContainer(getProject());
			webAppFiles.addAll(Arrays.asList(outputContainer.members()));

			// if the user has chosen not to export compiled JSP files, then we need to make sure
			// that
			// the compiled class files are excluded from the /WEB-INF/classes directory
			if (isExcludeCompiledJspFiles()) {
				try {
					//					IBaseWebNature wnr = J2EEWebNatureRuntimeUtilities.getRuntime(project);
					IPath outputPath = outputContainer.getProjectRelativePath();
					//					if (wnr != null) {
					//						IPath modulePath = wnr.getWebModulePath();
					//						IPath outputPath =
					// J2EEWebNatureRuntimeUtilities.getWebOutputFolderPath(modulePath.toString());
					// remove the WEB-INF folder from the list... we will get files from that
					// directory
					// after all others, in order to calculate which output files may exist,
					// according
					// to the JSPs in all other directories
					//						IPath webInf = modulePath.append(IWebNatureConstants.INFO_DIRECTORY);
					//					IResource outputDirResource = null;
					//					int len = webAppFiles.size();
					//					boolean found = false;
					//					for (int i = 0; !found && i < len; i++) {
					//						IResource res = (IResource) webAppFiles.get(i);
					//						if (res.getFullPath().equals(outputPath)) {
					//							outputDirResource = (IResource) webAppFiles.remove(i);
					//							found = true;
					//						}
					//					}
					// collect all the files (excluding those in /WEB-INF) and mark all of the
					// JSPs' compiled files (.class,.java) to be excluded from the
					webAppFiles = getFilesExcludeCompiledJsps(webAppFiles, outputPath);
					// now collect the files from the /WEB-INF directory. All of the compiled
					// JSP
					// output files should not be picked up now, as they were placed into the
					// visitedURIs
					// list
					ArrayList webInfList = new ArrayList(1);
					webInfList.add(outputContainer);
					webInfList = getFiles(webInfList);
					webAppFiles.addAll(webInfList);
					//					} else {
					//						webAppFiles = getFiles(webAppFiles);
					//					}
				} catch (Exception e) {
					throw new ArchiveRuntimeException(e.getMessage(), e);
				}
			} else {
				webAppFiles = getFiles(webAppFiles);
			}

			HashSet addedURIs = new HashSet();

			Iterator iterator = webAppFiles.iterator();
			while (iterator.hasNext()) {
				File file = (File) iterator.next();
				if (!isImportedClassJar(file)) {
					filesList.add(file);
					addedURIs.add(file.getURI());
				}
			}

			// Now go through the imported classes jars and add any
			// files that were not already in the classes directory.
			IContainer libFolder = WebPropertiesUtil.getWebLibFolder(project);
			if (libFolder != null && libFolder.exists()) {
				List libFiles = Arrays.asList(libFolder.members());
				getVisitedURIs().clear();
				libFiles = getFiles(libFiles);

				iterator = libFiles.iterator();
				while (iterator.hasNext()) {
					File file = (File) iterator.next();
					if (isImportedClassJar(file)) {
						List archiveFiles = getFiles((Archive) file);
						Iterator i = archiveFiles.iterator();
						while (i.hasNext()) {
							File innerFile = (File) i.next();
							if (!addedURIs.contains(innerFile.getURI())) {
								filesList.add(innerFile);
								addedURIs.add(innerFile.getURI());
							}
						}
					}
				}
			}

			// If the user wants source in his WAR file, then add that in too.
			if (isExportSource()) {
				List asourceFolders = getSourceFolders();

				for (int i = 0; i < asourceFolders.size(); i++) {
					IContainer sourceFolder = (IContainer) asourceFolders.get(i);
					if (sourceFolder != null && sourceFolder.exists()) {
						List sourceFiles = Arrays.asList(sourceFolder.members());
						sourceFiles = getFiles(sourceFiles);

						Iterator iterator2 = sourceFiles.iterator();
						while (iterator2.hasNext()) {
							File sourceFile = (File) iterator2.next();
							if (!addedURIs.contains(sourceFile.getURI())) {
								filesList.add(sourceFile);
								addedURIs.add(sourceFile.getURI());
							}
						}
					}
				}
			}
			// Add any lib module jars in.
			addLooseLibJARsToFiles();
		} catch (Exception exc) {
			throw new ArchiveRuntimeException(exc.getMessage(), exc);
			//$NON-NLS-1$
		}
		return filesList;
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	private ArrayList getFiles(Archive archive) throws Exception {

		List archiveFiles = archive.getFiles();
		ArrayList retList = new ArrayList(archiveFiles.size());
		Iterator i = archiveFiles.iterator();
		while (i.hasNext()) {
			File file = (File) i.next();
			file.setURI(ArchiveConstants.WEBAPP_CLASSES_URI + file.getURI());
			retList.add(file);
		}
		return retList;
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	protected ArrayList getFiles(List projectResources) throws Exception {
		if (projectResources.isEmpty()) {
			return new ArrayList(0);
		}

		ArrayList retAry = new ArrayList();

		Iterator iterator = projectResources.iterator();
		while (iterator.hasNext()) {
			IResource res = (IResource) (iterator.next());
			if (res.getType() == IResource.FILE) {
				//We have to avoid duplicates between the source and output folders (non-java
				// resources)
				IPath projRelPath = res.getProjectRelativePath();
				IPath outputPath = getOutputPathForFile(projRelPath);
				String loadURI = projRelPath == null ? null : projRelPath.toString();
				if (loadURI == null || getVisitedURIs().contains(loadURI))
					continue;
				File cFile = createFile(loadURI);
				cFile.setURI(outputPath.toString());
				cFile.setLastModified(getLastModified(res));
				retAry.add(cFile);
				getVisitedURIs().add(loadURI);
			} else {
				List moreFiles = getFiles(Arrays.asList(((IContainer) res).members()));
				retAry.addAll(moreFiles);
			}
		}
		return retAry;
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	protected ArrayList getFilesExcludeCompiledJsps(List projectResources, IPath outputDir) throws Exception {
		if (projectResources.isEmpty()) {
			return new ArrayList(0);
		}

		ArrayList retAry = new ArrayList();

		Iterator iterator = projectResources.iterator();
		while (iterator.hasNext()) {
			IResource res = (IResource) (iterator.next());
			if (res.getType() == IResource.FILE) {
				//We have to avoid duplicates between the source and output folders (non-java
				// resources)
				IPath projRelPath = res.getProjectRelativePath();
				IPath outputPath = getOutputPathForFile(projRelPath);
				String loadURI = projRelPath == null ? null : projRelPath.toString();
				if (loadURI == null || getVisitedURIs().contains(loadURI))
					continue;
				File cFile = createFile(loadURI);
				cFile.setURI(outputPath.toString());
				cFile.setLastModified(getLastModified(res));
				retAry.add(cFile);
				getVisitedURIs().add(loadURI);

				// exclude compiled JSP files
				String ext = projRelPath.getFileExtension();
				if (ext != null && ext.equals("jsp")) {//$NON-NLS-1$
					addOutputFilesToList(outputDir, outputPath, getVisitedURIs());
				}
			} else {
				List moreFiles = getFilesExcludeCompiledJsps(Arrays.asList(((IContainer) res).members()), outputDir);
				retAry.addAll(moreFiles);
			}
		}
		return retAry;
	}

	/*
	 * mark the compiled JSP output files (.class,.java) to NOT be added to the list of files to be
	 * exported
	 */
	private void addOutputFilesToList(IPath outputDir, IPath relativeJspPath, Set visitedUris) {
		outputDir = outputDir.removeFirstSegments(1);

		relativeJspPath = relativeJspPath.removeFileExtension();
		String baseFileName = getCompiledJspManagledName(relativeJspPath.lastSegment());
		relativeJspPath = relativeJspPath.removeLastSegments(1);

		String jspClass = outputDir.append(relativeJspPath).append(baseFileName + ".class").toString(); //$NON-NLS-1$
		String jspJava = outputDir.append(relativeJspPath).append(baseFileName + ".java").toString();//$NON-NLS-1$

		visitedUris.add(jspClass);
		visitedUris.add(jspJava);
	}

	/**
	 * Mangle string to WAS-like specifications
	 * 
	 * Was mangles Tom&Jerry as: _Tom_26_Jerry; this takes in the mangled name and returns the
	 * original name.
	 * 
	 * Unmangles the qualified type name. If an underscore is found it is assumed to be a mangled
	 * representation of a non-alpha, non-digit character of the form _NN_, where NN are hex digits
	 * representing the encoded character. This routine converts it back to the original character.
	 */
	protected static String getCompiledJspManagledName(String name) {
		StringBuffer modifiedName = new StringBuffer();
		int length = name.length();

		modifiedName.append('_');

		// ensure rest of characters are valid
		for (int i = 0; i < length; i++) {
			char currentChar = name.charAt(i);
			if (Character.isJavaIdentifierPart(currentChar) == true) {
				modifiedName.append(currentChar);
			} else {
				modifiedName.append(mangleChar(currentChar));
			}
		}
		return modifiedName.toString();

	}

	/**
	 * take a character and return its hex equivalent
	 */
	protected static String mangleChar(char ch) {
		if (ch == java.io.File.separatorChar) {
			ch = '/';
		}

		if (Character.isLetterOrDigit(ch) == true) {
			return "" + ch; //$NON-NLS-1$
		}
		return "_" + Integer.toHexString(ch).toUpperCase() + "_"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public IContainer getModuleContainer() {
		return WebPropertiesUtil.getModuleServerRoot(project);
	}

	/**
	 * save method comment.
	 */
	public WorkbenchURIConverter getProjectURIConverter() {
		projectURIConverter = new WorkbenchURIConverterImpl(project);
		projectURIConverter.addInputContainer(getModuleContainer());
		return projectURIConverter;

	}

	/**
	 * save method comment.
	 */
	public IFolder getSourceFolder() {
		return (IFolder) WebPropertiesUtil.getJavaSourceFolder(project);
	}
	
	public ILibModule[] getLibModules() {
		//TODO this will throw classcast exception, do we still use ILibModule?
		WebArtifactEdit webArtifactEdit = null;
		try {
			//TODO migrate to flex projects
			//webArtifactEdit = (WebArtifactEdit)StructureEdit.getFirstArtifactEditForRead(project);
			if (webArtifactEdit!=null)
				return (ILibModule[]) webArtifactEdit.getLibModules();
		} finally {
			if (webArtifactEdit!=null)
				webArtifactEdit.dispose();
		}
		return null;

	}

	protected IPath getOutputPathForFile(IPath aPath) throws Exception {
		if (isProjectMetaFile(aPath.toString()))
			return aPath;

		String uri = aPath.toString();
		if (uri.startsWith(getModuleContainer().getName())) {
			return new Path(uri).removeFirstSegments(getModuleContainer().getProjectRelativePath().segmentCount());
		}
		// If this is a source folder, stick it 'source' dir under the classes directory
		List asourceFolders = getSourceFolders();
		for (Iterator iterator = asourceFolders.iterator(); iterator.hasNext();) {
			IFolder sourceFolder = (IFolder) iterator.next();
			if (uri.startsWith(sourceFolder.getProjectRelativePath().toString())) {
				IPath relPath = aPath.removeFirstSegments(sourceFolder.getProjectRelativePath().segmentCount());
				IPath retPath = new Path(J2EEConstants.WEB_INF);
				retPath = retPath.append(SOURCE_DIR);
				return retPath.append(relPath);
			}
		}

		// If this is in an output folder, stick it in 'WEB-INF/classes
		IPath outputPath = JemProjectUtilities.getJavaProjectOutputContainer(getProject()).getProjectRelativePath();
		if (aPath.segmentCount() > outputPath.segmentCount() && aPath.removeLastSegments(aPath.segmentCount() - outputPath.segmentCount()).equals(outputPath)) {
			IPath retPath = new Path(J2EEConstants.WEB_INF);
			retPath = retPath.append(CLASSES_DIR);
			return retPath.append(aPath.removeFirstSegments(outputPath.segmentCount()));
		}

		return aPath;
	}

	private boolean isImportedClassJar(File file) {
		String uri = file.getURI();
		return file.isArchive() && uri.startsWith(ArchiveConstants.WEBAPP_LIB_URI) && uri.endsWith(IWebNatureConstants.IMPORTED_CLASSES_SUFFIX);
	}

	private boolean areLooseLibJarsIncluded() {
		boolean exists = false;
		ILibModule[] libModules = getLibModules();
		if (libModules.length > 0)
			exists = true;
		return exists;
	}

	/**
	 * set flag which indicates whether or not to export compiled JSP files (compiled files exist in
	 * /WEB-INF/classes as .class and .java[for debug])
	 */
	public void setExcludeCompiledJspFiles(boolean export) {
		excludeCompiledJspFiles = export;
	}

	/**
	 * return whether or not to export compiled JSP files (compiled files exist in /WEB-INF/classes
	 * as .class and .java[for debug])
	 */
	public boolean isExcludeCompiledJspFiles() {
		return excludeCompiledJspFiles;
	}

	protected boolean isProjectMetaFile(String uri) {
		return super.isProjectMetaFile(uri) || WEBSETTINGS_FILE_URI.equals(uri);
	}
}