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
package org.eclipse.jst.j2ee.internal.web.archive.operations;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverter;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverterImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIterator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EESaveStrategyImpl;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;

/**
 * @author: Kevin Bauer
 */
public class WTProjectSaveStrategyImpl extends J2EESaveStrategyImpl {
	HashMap filesToSave;

	//protected List libModules;
	// Need .websettings to determine which jars are actually WLP/utility jars
	//protected WebSettings webSettings;
	//protected HashMap createdProjectsMap;
	//protected boolean isNestedWAR = false;
	protected WebModuleImportDataModel dataModel;

	public static final String WEBSETTINGS_FILE_URI = ".websettings"; //$NON-NLS-1$

	/**
	 * WorkbenchSaveStrategyImpl constructor comment.
	 */
	public WTProjectSaveStrategyImpl(IProject project) {
		super(project);
	}

	/**
	 * Return the container that represents the root of this war file
	 */
	public IContainer getModuleServerRoot() {
		return WebPropertiesUtil.getModuleServerRoot(project);
	}

	/**
	 * Return an output stream for the input file.
	 */
	public OutputStream getOutputStream(String uri) throws Exception {
		return getURIConverter(uri).createOutputStream(URI.createURI(uri));
	}

	public IFile getSaveFile(String aURI) {
		String saveURI = (String) filesToSave.get(aURI);
		try {
			return getURIConverter(aURI).getFile(saveURI);
		} catch (Exception exc) {
			return null;
		}
	}

	public WorkbenchURIConverter getSourceURIConverter() {
		if (sourceURIConverter == null) {
			sourceURIConverter = new WorkbenchURIConverterImpl(getModuleServerRoot());
			sourceURIConverter.setForceSaveRelative(true);
		}
		return sourceURIConverter;
	}

	public WorkbenchURIConverter getURIConverter(String uri) throws Exception {
		return new WorkbenchURIConverterImpl(project);
	}

	/**
	 * Return the web nature for the war project.
	 */
//	public J2EEWebNatureRuntime getWebNature() {
//		return J2EEWebNatureRuntimeUtilities.getJ2EERuntime(project);
//	}

	protected void saveFiles(FileIterator iterator) throws SaveFailureException {
		while (iterator.hasNext()) {
			File aFile = iterator.next();
			if (shouldSave(aFile))
				save(aFile, iterator);
		}
	}

	//    private void setUpWLPs() {
	//        if (!(getLibModules().isEmpty())) {
	//            ILibModule[] libModArray = new ILibModule[libModules.size()];
	//            for (int i = 0; i < libModules.size(); i++) {
	//                libModArray[i] = (ILibModule) libModules.get(i);
	//            }
	//            try {
	//                getWebNature().setLibModules(libModArray);
	//            } catch (CoreException e) {
	//                Logger.getLogger().logError(e);
	//            }
	//        }
	//    }

	protected boolean shouldSave(String uri) {
		//if (uri.endsWith(WEBSETTINGS_FILE_URI)) {
		//	return false;
		//}
		if (isProjectMetaFile(uri))
			return includeProjectMetaFiles;
		boolean shouldSave = getFilter().shouldSave(uri, getArchive());
		if (shouldSave && overwriteHandler != null) {
			return (shouldOverwrite(uri));
		}
		return shouldSave;
	}

	protected boolean isProjectMetaFile(String uri) {
		return super.isProjectMetaFile(uri) || WEBSETTINGS_FILE_URI.equals(uri);
	}


	protected List getClassesFiles() {
		return ((WARFile) getArchive()).getClasses();
	}

	protected List getJarFiles() {
		return ((WARFile) getArchive()).getArchiveFiles();
	}

	protected String convertToImportedClassesURI(String uri) {
		IPath path = new Path(LibCopyBuilder.IMPORTED_CLASSES_PATH);
		path = path.append(WTProjectStrategyUtils.makeRelative(uri, ArchiveConstants.WEBAPP_CLASSES_URI));
		return path.toString();
	}

	protected String convertToSourceURI(String uri) {
		IFolder javaSource = (IFolder) WebPropertiesUtil.getJavaSourceFolder(project);
		IPath path = javaSource.getProjectRelativePath();
		path = path.append(uri);
		return path.toString();
	}

	protected String convertToContentURI(String uri) {
		if (isProjectMetaFile(uri))
			return uri;
		IPath path = WebPropertiesUtil.getModuleServerRoot(project).getProjectRelativePath();
		path = path.append(uri);
		return path.toString();
	}

	protected void saveFiles() throws SaveFailureException {
		WARFile warFile = (WARFile) getArchive();
		// First go through the classes in the WEB-INF/classes directory and try
		// to find a source file for each one file there. If a source file is found, copy
		// it to the source directory, if not, copy the .class file to the
		// imported_classes directory.
		List classesFiles = getClassesFiles();
		Iterator classesIterator = classesFiles.iterator();
		filesToSave = new HashMap();
		HashMap libModuleFiles = new HashMap();
		boolean hasSource = false;
		boolean hasClasses = false;
		while (classesIterator.hasNext()) {
			File classFile = (File) classesIterator.next();
			if (WTProjectStrategyUtils.isClassFile(classFile.getURI())) {
				hasClasses = true;
				File copyFile = warFile.getSourceFile(classFile);
				String saveURI = null;
				if (copyFile == null) {
					copyFile = classFile;
					saveURI = convertToImportedClassesURI(copyFile.getURI());
				} else {
					IPath sourcePath = new Path(copyFile.getURI());
					IPath relClassPath = new Path(WTProjectStrategyUtils.makeRelative(classFile.getURI(), ArchiveConstants.WEBAPP_CLASSES_URI));
					String relSourceURI = sourcePath.removeFirstSegments(sourcePath.segmentCount() - relClassPath.segmentCount()).toString();
					saveURI = convertToSourceURI(relSourceURI);
					hasSource = true;
				}
				filesToSave.put(classFile.getURI(), null);
				filesToSave.put(copyFile.getURI(), saveURI);
			}
		}
		// If there were no class files, then put any other resource in the classes
		// directory to the source folder.
		if (!hasClasses)
			hasSource = true;
		// Next go through the classes directory again saving all of the files that were
		// not previously saved. This handles the resource files.
		classesIterator = classesFiles.iterator();
		while (classesIterator.hasNext()) {
			File classFile = (File) classesIterator.next();
			if (!filesToSave.containsKey(classFile.getURI())) {
				File copyFile = warFile.getSourceFile(classFile);
				// If its a java file, put it into the source directory
				if (!hasSource && WTProjectStrategyUtils.isSourceFile(classFile.getURI()))
					hasSource = true;
				String saveURI = null;
				if (!hasSource) {
					saveURI = convertToImportedClassesURI(classFile.getURI());
				} else {
					IPath sourcePath = new Path(classFile.getURI());
					IPath relClassPath = new Path(WTProjectStrategyUtils.makeRelative(classFile.getURI(), ArchiveConstants.WEBAPP_CLASSES_URI));
					String relSourceURI = sourcePath.removeFirstSegments(sourcePath.segmentCount() - relClassPath.segmentCount()).toString();
					saveURI = convertToSourceURI(relSourceURI);
				}
				filesToSave.put(classFile.getURI(), saveURI);
				if (copyFile != null)
					filesToSave.put(copyFile.getURI(), null);
			}
		}
		//        if (isNestedWAR()) {
		//            if (!includeProjectMetaFiles) {
		//                if (checkCreateProjectOption())
		//                    createSourceProjectsForSelectJars(libModuleFiles);
		//                setUpWLPs();
		//            } else
		//                createProjectsToIncludeProjectMetaData(libModuleFiles);
		//        }
		// Finally, make a pass through all of the files now, saving them to the appropriate place
		// if they have not yet been saved.
		List allFiles = getArchive().getFiles();
		for (Iterator iter = allFiles.iterator(); iter.hasNext();) {
			File file = (File) iter.next();
			if (!filesToSave.containsKey(file.getURI())) {
				if (!libModuleFiles.containsKey(file.getURI())) {
					String saveURI = convertToContentURI(file.getURI());
					filesToSave.put(file.getURI(), saveURI);
				}
			}
		}
		super.saveFiles();
		//updateProjectClasspaths();
	}

	//    private void createProjectsToIncludeProjectMetaData(HashMap libModuleFiles) throws
	// SaveFailureException {
	//        // Next go through the jar files to determine if there are wlp files to
	//        // expand into binary projects. Only if webSettings file has been saved
	//        ILibModule[] libModules = getLooseLibsModules();
	//        List jarFiles = getJarFiles();
	//        if (libModules != null && jarFiles != null) {
	//
	//            Iterator jarIterator = jarFiles.iterator();
	//            while (jarIterator.hasNext()) {
	//                File jarFile = (File) jarIterator.next();
	//                for (int i = 0; i < libModules.length; i++) {
	//                    if (jarFile.getName().equals(libModules[i].getJarName())) {
	//                        saveArchiveAsJavaProject((Archive) jarFile, libModules[i]);
	//                        libModuleFiles.put(jarFile.getURI(), null);
	//                    }
	//                }
	//            }
	//        }
	//    }

	/**
	 *  
	 */
	//    private void createSourceProjectsForSelectJars(HashMap libModuleFiles) throws
	// SaveFailureException {
	//        List jarFiles = getJarFiles();
	//        Iterator jarIterator = jarFiles.iterator();
	//        while (jarIterator.hasNext()) {
	//            File jarFile = (File) jarIterator.next();
	//            if (checkCreateProjectOptions(jarFile)) {
	//                saveArchiveAsJavaProject((Archive) jarFile);
	//                libModuleFiles.put(jarFile.getURI(), null);
	//            }
	//
	//        }
	//    }
	/**
	 *  
	 */
	//    private void updateProjectClasspaths() {
	//        try {
	//            if (createdProjectsMap == null)
	//                return;
	//            List libModules = getJarFiles();
	//            for (int i = 0; i < libModules.size(); i++) {
	//                Archive module = (Archive) libModules.get(i);
	//                Object object = createdProjectsMap.get(module.getURI());
	//                if (object != null) {
	//                    IProject proj = (IProject) object;
	//                    IPath path = proj.getFullPath();
	//                    IClasspathEntry newEntry = JavaCore.newProjectEntry(path, false);
	//                    ProjectUtilities.appendJavaClassPath(project, newEntry);
	//                }
	//            }
	//        } catch (JavaModelException ex) {
	//            Logger.getLogger().logError(ex);
	//        }
	//    }
	//    /**
	//     * @return
	//     */
	//    private boolean checkCreateProjectOption() {
	//        return createWLProjectOptions != null && !createWLProjectOptions.isEmpty();
	//    }
	//
	//    /**
	//     * @return
	//     */
	//    private boolean checkCreateProjectOptions(File jarFile) {
	//        return createWLProjectOptions != null &&
	// createWLProjectOptions.containsKey(jarFile.getURI());
	//    }
	/**
	 * @see com.ibm.etools.archive.SaveStrategy
	 */
	public void save(File aFile, FileIterator iterator) throws SaveFailureException {
		if (aFile.isArchive() && dataModel.handlesArchive((Archive) aFile)) {
			return;
		}

		if (aFile.isArchive() && shouldIterateOver((Archive) aFile)) {
			save((Archive) aFile);
		} else {
			InputStream in = null;
			if (!aFile.isDirectoryEntry()) {
				try {
					in = iterator.getInputStream(aFile);
				} catch (IOException ex) {
					throw new SaveFailureException(aFile.getURI(), ex);
				}
			}
			String saveURI = (String) filesToSave.get(aFile.getURI());
			if (saveURI != null)
				save(saveURI, in);
		}
	}

	/**
	 * save method comment.
	 */
	public void save(String outputURI, InputStream in) throws SaveFailureException {
		getProgressMonitor().subTask(outputURI);
		try {
			WorkbenchURIConverter conv = getProjectMetaURIConverter();
			IFile aFile = conv.getOutputFileWithMappingApplied(outputURI);
			validateEdit(aFile);
			OutputStream out = new WorkbenchByteArrayOutputStream(aFile);
			ArchiveUtil.copy(in, out);
		} catch (Exception e) {
			throw new SaveFailureException(e.getMessage(), e);
		}
		worked(1);
	}

	//    protected ILibModule[] getLooseLibsModules() {
	//        try {
	//            File webSettingsFile = (((WARFile) getArchive()).getFile(WEBSETTINGS_FILE_URI));
	//
	//            ILibModule[] wlpModules;
	//            if (webSettingsFile != null) {
	//                WebSettings webSettings = new WebSettings(project, webSettingsFile);
	//                if (webSettings != null) {
	//                    wlpModules = webSettings.getLibModules();
	//                    return wlpModules;
	//                }
	//            }
	//        } catch (FileNotFoundException ex) {
	//            return new ILibModule[0];
	//        }
	//        return new ILibModule[0];
	//    }

	//    protected void saveArchiveAsJavaProject(Archive jarFile, ILibModule libModule) throws
	// SaveFailureException {
	//        IProject nestedProject = null;
	//        if (createdProjectsMap == null)
	//            createdProjectsMap = new HashMap();
	//        // Do overwrite protection
	//        if (shouldSaveWLProject(libModule)) {
	//            String projectName = createWLProjectOptions == null ? null : (String)
	// createWLProjectOptions.get(jarFile.getURI());
	//            if (projectName == null)
	//                projectName = libModule.getProjectName();
	//
	//            WebProjectCreationDataModel model = new WebProjectCreationDataModel();
	//            model.setProperty(WebProjectCreationDataModel.CREATE_DEFAULT_FILES, Boolean.FALSE);
	//            model.setProperty(WebProjectCreationDataModel.PROJECT_NAME, projectName);
	//            IHeadlessRunnableWithProgress projOp = new WebProjectCreationOperation(model);
	//            executeOperation(projOp);
	//            nestedProject = model.getProject();
	//            createdProjectsMap.put(jarFile.getURI(), nestedProject);
	//
	//            if (isBinary() || includeProjectMetaFiles) {
	//                saveBinaryProject(nestedProject, jarFile);
	//            } else {
	//                JavaProjectSaveStrategyImpl strat = new JavaProjectSaveStrategyImpl(nestedProject);
	//                strat.setIncludeProjectMetaFiles(includeProjectMetaFiles);
	//                strat.setShouldIncludeImportedClasses(true);
	//                strat.setProgressMonitor(new SubProgressMonitor(progressMonitor, 1));
	//                jarFile.save(strat);
	//
	//                if (includeProjectMetaFiles) {
	//                    try {
	//                        ProjectUtilities.forceClasspathReload(nestedProject);
	//                    } catch (JavaModelException ex) {
	//                        Logger.getLogger().logError(ex);
	//                    }
	//                }
	//            }
	//            // Keep list of Library modules to readd to websettings
	//            if (!includeProjectMetaFiles)
	//                getLibModules().add(libModule);
	//        }
	//
	//    }

	//    /**
	//     * @param object
	//     */
	//    private void executeOperation(IHeadlessRunnableWithProgress op) {
	//        try {
	//            op.run(new NullProgressMonitor());
	//        } catch (java.lang.reflect.InvocationTargetException e) {
	//            Logger.getLogger().logError(e);
	//        } catch (InterruptedException ex) {
	//            Logger.getLogger().logError(ex);
	//        }
	//    }

	//    protected IProject saveArchiveAsJavaProject(Archive jarFile) throws SaveFailureException {
	//        IProject nestedProject = null;
	//        if (createdProjectsMap == null)
	//            createdProjectsMap = new HashMap();
	//        // Do overwrite protection
	//        J2EEJavaProjectInfo javaProjectInfo = new J2EEJavaProjectInfo();
	//        String projectName = (String) createWLProjectOptions.get(jarFile.getURI());
	//        J2EEProjectCreationDataModel model = new J2EEProjectCreationDataModel();
	//        model.setProperty(J2EEProjectCreationDataModel.PROJECT_NAME, projectName);
	//        model.setProperty(J2EEProjectCreationDataModel.CREATE_DEFAULT_FILES, Boolean.FALSE);
	//        IHeadlessRunnableWithProgress op = new JavaUtilityJARProjectCreationOperation(model);
	//        nestedProject = model.getProject();
	//        createdProjectsMap.put(jarFile.getURI(), nestedProject);
	//        if (isBinary()) {
	//            saveBinaryProject(nestedProject, jarFile);
	//        } else {
	//            JavaProjectSaveStrategyImpl strat = new JavaProjectSaveStrategyImpl(nestedProject);
	//            strat.setIncludeProjectMetaFiles(includeProjectMetaFiles);
	//            strat.setShouldIncludeImportedClasses(true);
	//            strat.setProgressMonitor(new SubProgressMonitor(progressMonitor, 1));
	//            jarFile.save(strat);
	//            if (includeProjectMetaFiles) {
	//                try {
	//                    ProjectUtilities.forceClasspathReload(nestedProject);
	//                } catch (JavaModelException ex) {
	//                    Logger.getLogger().logError(ex);
	//                }
	//            }
	//        }
	//        return nestedProject;
	//    }
	//    protected void saveBinaryProject(IProject aProject, Archive nested) throws
	// SaveFailureException {
	//        saveJARInBinaryProject(aProject, nested);
	//        if (includeProjectMetaFiles) {
	//            try {
	//                ProjectUtilities.forceClasspathReload(aProject);
	//            } catch (JavaModelException ex) {
	//                Logger.getLogger().logError(ex);
	//            }
	//            // ensureBinary(nested, aProject);
	//        }
	//
	//        // try {
	//        // IPath path = aFile.getFullPath();
	//        // IClasspathEntry newEntry = JavaCore.newLibraryEntry(path, path, null, true);
	//        // ProjectUtilities.appendJavaClassPath(aProject, newEntry);
	//        // } catch (JavaModelException ex) {
	//        // Logger.getLogger().logError(ex);
	//        // }
	//
	//        J2EEModuleNature nature = (J2EEModuleNature) J2EENature.getRegisteredRuntime(aProject);
	//        if (nature != null)
	//            nature.recomputeBinaryProject();
	//    }

	//    protected IFile saveJARInBinaryProject(IProject project, Archive nested) throws
	// SaveFailureException {
	//        IFile savedArchive = null;
	//        try {
	//            // savedArchive = saveFile(nested, project);
	//            // Put directly in the root directory, remove the WEB-INF/lib segments
	//            IPath path = new Path(nested.getURI());
	//            path = path.removeFirstSegments(2);
	//            IFile iFile = project.getFile(path.toString());
	//            WorkbenchByteArrayOutputStream out = new WorkbenchByteArrayOutputStream(iFile);
	//            ArchiveUtil.copy(nested.getInputStream(), out);
	//            savedArchive = iFile;
	//            if (includeProjectMetaFiles) {
	//                saveEnclosedFile(nested, project, CLASSPATH_FILE_URI);
	//                saveEnclosedFile(nested, project, PROJECT_FILE_URI);
	//            }
	//
	//            return savedArchive;
	//        } catch (IOException io) {
	//            throw new SaveFailureException(archive.getURI(), io);
	//        }
	//    }
	protected void saveEnclosedFile(Archive anArchive, IProject p, String uri) throws IOException {
		try {
			File aFile = anArchive.getFile(uri);
			saveFile(aFile, p);
		} catch (FileNotFoundException ignore) {
			//Ignore
		}
	}

	protected IFile saveFile(File aFile, IProject p) throws IOException {
		IFile iFile = p.getFile(aFile.getURI());
		WorkbenchByteArrayOutputStream out = new WorkbenchByteArrayOutputStream(iFile);
		ArchiveUtil.copy(aFile.getInputStream(), out);
		return iFile;
	}

	//    protected boolean shouldSaveWLProject(ILibModule libModule) {
	//        IProject wlProject = libModule.getProject();
	//        // Do overwrite protection
	//        if (wlProject != null && wlProject.exists()) {
	//            if (overwriteHandler != null) {
	//                return (overwriteHandler.shouldOverwriteWLProject(wlProject, libModule.getProjectName()));
	//            }
	//        }
	//        return true;
	//    }

	//    protected void initializeLibModules() {
	//        // Get the existing lib modules from the web nature
	//        ILibModule[] libModulesAry = getWebNature().getLibModules();
	//        libModules = new Vector(libModulesAry.length);
	//        for (int i = 0; i < libModulesAry.length; i++) {
	//            libModules.add(libModulesAry[i]);
	//        }
	//    }

	//    protected List getLibModules() {
	//        if (libModules == null)
	//            initializeLibModules();
	//        return libModules;
	//    }

	//    public void setNestedWAR(boolean isNested) {
	//        this.isNestedWAR = isNested;
	//    }
	//    public boolean isNestedWAR() {
	//        return isNestedWAR;
	//    }

	public void setDataModel(WebModuleImportDataModel model) {
		this.dataModel = model;
	}

}