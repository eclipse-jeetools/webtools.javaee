package org.eclipse.jem.internal.plugin;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ProjectUtilities.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:33:53 $ 
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.internal.core.JavaModel;
import org.eclipse.jdt.internal.core.JavaModelManager;

/**
 * Insert the type's description here.
 * Creation date: (4/25/2001 11:53:37 AM)
 * @author: Administrator
 */
public class ProjectUtilities {
	
	public final static String DOT_PROJECT = ".project"; //$NON-NLS-1$
	public final static String DOT_CLASSPATH = ".classpath"; //$NON-NLS-1$
    public final static String DOT_WEBSETTINGS = ".websettings"; //$NON-NLS-1$

	/**
	 * ProjectUtilities constructor comment.
	 */
	public ProjectUtilities() {
		super();
	}
	public static boolean addToBuildSpec(String builderID, IProject project) throws CoreException {
		return com.ibm.etools.emf.workbench.ProjectUtilities.addToBuildSpec(builderID, project);
	}
	public static boolean removeFromBuildSpec(String builderID, IProject project) throws CoreException {
		return com.ibm.etools.emf.workbench.ProjectUtilities.removeFromBuildSpec(builderID, project);
	}
	/**
	 * Adds a nauture to a project, FIRST
	 */
	public static void addNatureToProject(IProject proj, String natureId) throws CoreException {
		com.ibm.etools.emf.workbench.ProjectUtilities.addNatureToProject(proj, natureId);
	}
	/**
	 * Adds a nature to a project, LAST
	 */
	public static void addNatureToProjectLast(IProject proj, String natureId) throws CoreException {
		com.ibm.etools.emf.workbench.ProjectUtilities.addNatureToProjectLast(proj, natureId);
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/25/2001 11:56:59 AM)
	 */
	public static void addReferenceProjects(IProject project, List toBeAddedProjectsList) throws CoreException {
		com.ibm.etools.emf.workbench.ProjectUtilities.addReferenceProjects(project, toBeAddedProjectsList);
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/25/2001 11:56:59 AM)
	 */
	public static void addReferenceProjects(IProject project, IProject projectToBeAdded) throws CoreException {
		com.ibm.etools.emf.workbench.ProjectUtilities.addReferenceProjects(project, projectToBeAdded);
	}
	/**
	 * Append a list of IClasspathEntry's to the build path of the passed project.
	 * Updated to remove existing occurrences of the passed entries before appending.
	 */
	public static void appendJavaClassPath(IProject p, List appendClasspathEntries) throws JavaModelException {
		IJavaProject javaProject = null;
		try {
			javaProject = (IJavaProject) p.getNature(JavaCore.NATURE_ID);
		} catch (CoreException ignore) {}
		if (javaProject != null) {
			IClasspathEntry[] classpath = javaProject.getRawClasspath();
			List newPathList = new ArrayList(classpath.length);
			for (int i = 0; i < classpath.length; i++) {
				IClasspathEntry entry = classpath[i];
				// Skip entries which are in the append list
				if (appendClasspathEntries.indexOf(entry) < 0)
					newPathList.add(entry);
			}
			newPathList.addAll(appendClasspathEntries);
			IClasspathEntry[] newClasspath = (IClasspathEntry[]) newPathList.toArray(new IClasspathEntry[newPathList.size()]);
			javaProject.setRawClasspath(newClasspath, new NullProgressMonitor());
		}
	}
	/**
	 * Append one IClasspathEntry to the build path of the passed project.
	 * If a classpath entry having the same path as the parameter already exists,
	 * then does nothing.
	 */
	public static void appendJavaClassPath(IProject p, IClasspathEntry newEntry) throws JavaModelException {

		IJavaProject javaProject = getJavaProject(p);
		if (javaProject == null)
			return;

		IClasspathEntry[] classpath = javaProject.getRawClasspath();
		List newPathList = new ArrayList(classpath.length);
		for (int i = 0; i < classpath.length; i++) {
			IClasspathEntry entry = classpath[i];
			// Skip the entry to be added if it already exists
			if (!entry.getPath().equals(newEntry.getPath()))
				newPathList.add(entry);
			else 
				return;
		}
		newPathList.add(newEntry);
		IClasspathEntry[] newClasspath = (IClasspathEntry[]) newPathList.toArray(new IClasspathEntry[newPathList.size()]);
		javaProject.setRawClasspath(newClasspath, new NullProgressMonitor());
	}


	public static void updateClasspath(IJavaProject javaProject) throws JavaModelException {
		if (javaProject != null)
			javaProject.setRawClasspath(javaProject.getRawClasspath(), new NullProgressMonitor());
	}

	public static IJavaProject getJavaProject(IProject p) {
		try {
			return (IJavaProject) p.getNature(JavaCore.NATURE_ID);
		} catch (CoreException ignore) {
			return null;
		}
	}
	protected static IPath createPath(IProject p, String defaultSourceName) {
		IPath path = new Path(p.getName());
		path = path.append(defaultSourceName);
		path = path.makeAbsolute();
		return path;
	}
	/**
	 * Return the source folder matching the parameter; if the parameter is null,
	 * or if the source folder is not on the classpath, 
	 * return the first source folder on the classpath
	 */
	public static IContainer getSourceFolderOrFirst(IProject p, String defaultSourceName) {
		try {
			IPath sourcePath = getSourcePathOrFirst(p, defaultSourceName);
			if (sourcePath == null)
				return null;
			else if (sourcePath.isEmpty())
				return p;
			else
				return p.getFolder(sourcePath);
		} catch (IllegalArgumentException ex) {
			return null;
		}
	}

	public static IPath getSourcePathOrFirst(IProject p, String defaultSourceName) {
		IJavaProject javaProj = getJavaProject(p);
		if (javaProj == null)
			return null;
		IClasspathEntry[] cp = null;
		try {
			cp = javaProj.getRawClasspath();
		} catch (JavaModelException ex) {
			JavaPlugin.getDefault().getMsgLogger().log(ex);
			return null;
		}
		IClasspathEntry firstSource = null;
		IPath defaultSourcePath = null;
		if (defaultSourceName != null)
			defaultSourcePath = createPath(p, defaultSourceName);
		for (int i = 0; i < cp.length; i++) {
			if (cp[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				if (firstSource == null) {
					firstSource = cp[i];
					if (defaultSourcePath == null)
						break;
				}
				if (cp[i].getPath().equals(defaultSourcePath))
					return defaultSourcePath.removeFirstSegments(1);
			}
		}
		if (firstSource == null)
			return null;

		if (firstSource.getPath().segment(0).equals(p.getName()))
			return firstSource.getPath().removeFirstSegments(1);

		return null;
	}
	/**
	 * Returns a list of IFolder that represents each source folder in 
	 * a java project
	 * 
	 * @deprecated Use {@link #getSourceContainers(IProject)} because the project
	 * itself might be a source container
	 */
	public static List getSourceFolders(IProject p) {
		try {
			List sourceFolders = new ArrayList();
			List sourcePaths = getSourcePaths(p);
			if (sourcePaths != null && !sourcePaths.isEmpty()) {
				for (int i = 0; i < sourcePaths.size(); i++) {
					IPath path = (IPath) sourcePaths.get(i);
					if (!path.isEmpty())
						sourceFolders.add(p.getFolder(path));
				}
			}
			return sourceFolders;
		} catch (IllegalArgumentException ex) {
			return Collections.EMPTY_LIST;
		}
	}
	
	public static List getSourceContainers(IProject p) {
		try {
			List sourceContainers = new ArrayList();
			List sourcePaths = getSourcePaths(p);
			if (sourcePaths != null && !sourcePaths.isEmpty()) {
				for (int i = 0; i < sourcePaths.size(); i++) {
					IPath path = (IPath) sourcePaths.get(i);
					if (path.isEmpty())
						sourceContainers.add(p);
					else 
						sourceContainers.add(p.getFolder(path));
				}
			}
			return sourceContainers;
		} catch (IllegalArgumentException ex) {
			return Collections.EMPTY_LIST;
		}
	}
	protected static List getSourcePaths(IProject p) {
		IJavaProject javaProj = getJavaProject(p);
		if (javaProj == null)
			return null;
		IClasspathEntry[] cp = null;
		try {
			cp = javaProj.getRawClasspath();
		} catch (JavaModelException ex) {
			JavaPlugin.getDefault().getMsgLogger().log(ex);
			return null;
		}
		List sourcePaths = new ArrayList();
		for (int i = 0; i < cp.length; i++) {
			if (cp[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				sourcePaths.add(cp[i].getPath().removeFirstSegments(1));
			}
		}
		return sourcePaths;
	}

	/**
	* Return the location of the binary files for the JavaProject.
	*/
	public static IPath getJavaProjectOutputLocation(IProject p) {
		try {
			IJavaProject javaProj = getJavaProject(p);
			if (javaProj == null)
				return null;
			if (!javaProj.isOpen())
				javaProj.open(null);
			return javaProj.getOutputLocation();
		} catch (JavaModelException e) {
			return null;
		}
	}
	
	public static IContainer getJavaProjectOutputContainer(IProject p) {
		IPath path = getJavaProjectOutputLocation(p);
		if (path == null)
			return null;
		if (path.segmentCount() == 1)
			return p;
		return p.getFolder(path.removeFirstSegments(1));
	}	

	public static IPath getJavaProjectOutputAbsoluteLocation(IProject p) {
		IContainer container = getJavaProjectOutputContainer(p);
		if (container != null)	
			return container.getLocation();
		return null;
	}
	
	/**
	 * Typically a Java project is considered binary if it does not have a source entry in the classpath.
	 */
	public static boolean isBinaryProject(IProject aProject) {

		IJavaProject javaProj = getJavaProject(aProject);
		if (javaProj == null)
			return false;
		IClasspathEntry[] entries = null;
		try {
			entries = javaProj.getRawClasspath();
		} catch (JavaModelException jme) {
			return false;
		}
		for (int i = 0; i < entries.length; i++) {
			IClasspathEntry entry = entries[i];
			if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE)
				return false;
		}
		return true;
	}

	/**
	 *	Import the appropriate resources from the specified archive file
	 */
	public static void forceAutoBuild(IProject project, IProgressMonitor progressMonitor) {
		com.ibm.etools.emf.workbench.ProjectUtilities.forceAutoBuild(project, progressMonitor);
	}
	/**
	 * Hack to force a reload of the .classpath file
	 */
	public static void forceClasspathReload(IProject project) throws JavaModelException {
		IJavaProject javaProj = ProjectUtilities.getJavaProject(project);	
		if (javaProj != null) {
			javaProj.close();
			//Hack provided by eclipse team, as this broke in 2.1.1
			JavaModelManager.PerProjectInfo perProjectInfo = JavaModelManager.getJavaModelManager().getPerProjectInfo(project, true/*create if missing*/);
			perProjectInfo.classpath = null;
			perProjectInfo.lastResolvedClasspath = null;
		}
	}
	/**
	 *	Import the appropriate resources from the specified archive file
	 */
	public static boolean getCurrentAutoBuildSetting() {
		return com.ibm.etools.emf.workbench.ProjectUtilities.getCurrentAutoBuildSetting();
	}
	/**
	 *	Return the global Eclipse Java Model
	 */
	public static JavaModel getJavaModel() {
		return (JavaModel) JavaModelManager.getJavaModelManager().getJavaModel();
	}
	
	public static List getSourcePackageFragmentRoots(IJavaProject javaProj) throws JavaModelException {
		List result = new ArrayList();
		IPackageFragmentRoot[] roots = javaProj.getPackageFragmentRoots();
		for (int i = 0; i < roots.length; i++) {
			IPackageFragmentRoot root = roots[i];
			if (root.getKind() == IPackageFragmentRoot.K_SOURCE) 
				result.add(result);
		}
		return result;
	}

	/**
	 * Returns a list of existing files which will be modified if the classpath changes for 
	 * the given proeject.
	 */
	public static List getFilesAffectedByClasspathChange(IProject p) {
		List result = new ArrayList(2);
		addFileIfExists(p, result, DOT_CLASSPATH);
		addFileIfExists(p, result, DOT_PROJECT);
		return result;
	}
	protected static void addFileIfExists(IProject p, List aList, String filename) {
		IFile aFile = p.getFile(filename);
		if (aFile != null && aFile.exists())
			aList.add(aFile);
	}
		
	/**
	 * Return an IProject for a given @aRefObject if it is
	 * part of a J2EENature.
	 */
	public static IProject getProject(EObject aRefObject) {
		return com.ibm.etools.emf.workbench.ProjectUtilities.getProject(aRefObject);
	}
	public static String[] getProjectNamesWithoutForwardSlash(String[] projecNames) {
		String[] projNames = new String[projecNames.length];
		List temp = java.util.Arrays.asList(projecNames);

		for (int i = 0; i < temp.size(); i++) {
			String name = (String) (temp.get(i));
			if (name.startsWith("/")) { //$NON-NLS-1$
				projNames[i] = name.substring(1, name.length());
			} else {
				projNames[i] = name;
			}
		}
		return projNames;
	}

	/**
	 * The parameter should be a java projecct
	 * @return A list of IPath, where each entry is a project relative path to a JAR contained
	 * in the project
	 */
	public static List getLocalJARPathsFromClasspath(IProject proj) {
		IJavaProject javaProj = getJavaProject(proj);
		if (javaProj == null)
			return null;

		IPath projectPath = proj.getFullPath();
		List result = new ArrayList();
		try {
			IClasspathEntry[] entries = javaProj.getRawClasspath();
			for (int i = 0; i < entries.length; i++) {
				IClasspathEntry entry = entries[i];
				if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
					IPath path = entry.getPath();
					int segments = path.matchingFirstSegments(projectPath);
					if (segments > 0)
						result.add(path.removeFirstSegments(segments));
				}
			}
		} catch (JavaModelException e) {
			JavaPlugin.getDefault().getMsgLogger().log(e);
		}
		return result;
	}

	/**
	 * Adds a nauture to a project
	 */
	public static void makeJ2EENatureFirst(IProject proj) {
		String natureID = AbstractJavaMOFNatureRuntime.getRegisteredRuntimeID(proj);
		if (natureID != null) {
			try {
				IProjectDescription description = proj.getDescription();
				String[] prevNatures = description.getNatureIds();
				String[] newNatures = new String[prevNatures.length];
				newNatures[0] = natureID;
				int next = 1;
				for (int i = 0; i < prevNatures.length; i++) {
					if (!prevNatures[i].equals(natureID))
						newNatures[next++] = prevNatures[i];
				}
				description.setNatureIds(newNatures);
				proj.setDescription(description, null);
			} catch (CoreException e) {
				JavaPlugin.getDefault().getMsgLogger().log(e);
			}
		}
	}
	
	public static void removeFromJavaClassPath(IProject p, IResource res) throws JavaModelException {
		IClasspathEntry entry = JavaCore.newLibraryEntry(res.getFullPath(), null, null);
		removeFromJavaClassPath(p, entry);
	}
	public static void removeFromJavaClassPath(IProject p, IPath path) throws JavaModelException {
		org.eclipse.core.resources.IFile f = p.getFile(path);
		removeFromJavaClassPath(p, f);
	}
	public static void removeFromJavaClassPath(IProject p, IClasspathEntry entry) throws JavaModelException {

		IJavaProject javaProject = null;
		try {
			javaProject = (IJavaProject) p.getNature(JavaCore.NATURE_ID);
		} catch (CoreException ignore) {}

		if (javaProject != null) {
			IClasspathEntry[] classpath = javaProject.getRawClasspath();
			javaProject.setRawClasspath(primRemoveFromJavaClassPath(classpath, entry), new NullProgressMonitor());
		}
	}
	public static void removeFromJavaClassPath(IProject p, List entries) throws JavaModelException {

		IJavaProject javaProject = null;
		try {
			javaProject = (IJavaProject) p.getNature(JavaCore.NATURE_ID);
		} catch (CoreException ignore) {}

		if (javaProject != null) {
			IClasspathEntry[] classpath = javaProject.getRawClasspath();
			javaProject.setRawClasspath(primRemoveFromJavaClassPath(classpath, entries), new NullProgressMonitor());
		}
	}
	
	protected static IClasspathEntry[] primRemoveFromJavaClassPath(IClasspathEntry[] classpath, IClasspathEntry entry) throws JavaModelException {
		List result = new ArrayList();
		boolean didRemove = false;
		for (int i = 0; i < classpath.length; i++) {
			IClasspathEntry cpEntry = classpath[i];
			if (!entry.getPath().equals(classpath[i].getPath()))
				result.add(cpEntry);
			else
				didRemove = true;
		}
		if (!didRemove)
			return classpath;
		return (IClasspathEntry[]) result.toArray(new IClasspathEntry[result.size()]);
	}
	
	protected static IClasspathEntry[] primRemoveFromJavaClassPath(IClasspathEntry[] classpath, List entries) throws JavaModelException {
		List arrayList = Arrays.asList(classpath);
		List removeable = new ArrayList(arrayList);
		IClasspathEntry entry;
		boolean didRemove = false;
		int size = entries.size();
		for (int i = 0; i < size; i++) {
			entry = (IClasspathEntry) entries.get(i);
			for (int j = 0; j < classpath.length; j++) {
				IClasspathEntry cpEntry = classpath[j];
				if (entry.getPath().equals(classpath[j].getPath())) {
					if (removeable.remove(cpEntry))
						didRemove = true;
				}
			}
		}
		if (!didRemove)
			return classpath;
		return (IClasspathEntry[]) removeable.toArray(new IClasspathEntry[removeable.size()]);
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/25/2001 11:56:59 AM)
	 */
	public static void removeReferenceProjects(IProject project, List toBeRemovedProjectList) throws org.eclipse.core.runtime.CoreException {
		com.ibm.etools.emf.workbench.ProjectUtilities.removeReferenceProjects(project, toBeRemovedProjectList);
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/25/2001 11:56:59 AM)
	 */
	public static void removeReferenceProjects(IProject project, IProject toBeRemovedProject) throws org.eclipse.core.runtime.CoreException {
		com.ibm.etools.emf.workbench.ProjectUtilities.removeReferenceProjects(project, toBeRemovedProject);
	}
	/**
	 *	Import the appropriate resources from the specified archive file
	 */
	public static void turnAutoBuildOff() {
		com.ibm.etools.emf.workbench.ProjectUtilities.turnAutoBuildOff();
	}
	/**
	 *	Import the appropriate resources from the specified archive file
	 */
	public static void turnAutoBuildOn() {
		com.ibm.etools.emf.workbench.ProjectUtilities.turnAutoBuildOn();
	}
	/**
	 *	Import the appropriate resources from the specified archive file
	 */
	public static void turnAutoBuildOn(boolean aBoolean) {
		com.ibm.etools.emf.workbench.ProjectUtilities.turnAutoBuildOn(aBoolean);
	}
	/**
	 * remove a nature from the project
	 */
	public static void removeNatureFromProject(IProject project, String natureId) throws CoreException {
		IProjectDescription description = project.getDescription();
		String[] prevNatures = description.getNatureIds();
		int size = prevNatures.length;
		int newsize = 0;
		String[] newNatures = new String[size];
		boolean matchfound = false;

		for (int i=0; i<size; i++) {
			if (prevNatures[i].equals(natureId)) {
				matchfound = true;
				continue;
			} else
				newNatures[newsize++] = prevNatures[i];
		}
				
		if (!matchfound)
			throw new CoreException(new Status(IStatus.ERROR, "com.ibm.etools.java", 0, "The nature id " + natureId + " does not exist on the project " + project.getName(), null)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		else {
			String[] temp = newNatures;
			newNatures = new String[newsize];
			System.arraycopy(temp, 0, newNatures, 0, newsize);
			description.setNatureIds(newNatures);
			project.setDescription(description, null);
		}
	}	

	public static URL[] getClasspathAsURLArray(IJavaProject javaProject) {
		if (javaProject == null) return null;
		Set visited = new HashSet();
		List urls = new ArrayList(20);
		collectClasspathURLs(javaProject, urls, visited, true);
		URL[] result = new URL[urls.size()];
		urls.toArray(result);
		return result;
	}

	private static void collectClasspathURLs(IJavaProject javaProject, List urls, Set visited, boolean isFirstProject) {
		if (visited.contains(javaProject)) return;
		visited.add(javaProject);
		IPath outPath = getJavaProjectOutputAbsoluteLocation(javaProject.getProject());
		outPath = outPath.addTrailingSeparator();
		URL out = createFileURL(outPath); 
		urls.add(out);
		IClasspathEntry[] entries = null;
		try {
			entries = javaProject.getResolvedClasspath(true);
		} catch (JavaModelException e) {
			return;
		}
		IClasspathEntry entry;
		for (int i = 0; i < entries.length; i++) {
			entry = entries[i];
			switch (entry.getEntryKind()) {
				case IClasspathEntry.CPE_LIBRARY :
				case IClasspathEntry.CPE_CONTAINER : 
				case IClasspathEntry.CPE_VARIABLE :
					collectClasspathEntryURL(entry, urls);
					break;				
				case IClasspathEntry.CPE_PROJECT : {
					if (isFirstProject || entry.isExported())
						collectClasspathURLs(getJavaProject(entry), urls, visited, false);						
					break;
				}
			}
		}
	}

	private static URL createFileURL(IPath path) {
		URL url = null;
		try {
			url = new URL("file://" + path.toOSString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}


	private static void collectClasspathEntryURL(IClasspathEntry entry, List urls) {
		URL url = createFileURL(entry.getPath());
		if (url != null)
			urls.add(url);
	}

	private static IJavaProject getJavaProject(IClasspathEntry entry) {
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(entry.getPath().segment(0));
		if (proj != null)
			return getJavaProject(proj);
		return null;
	}
	
	/**
	 * return list of path that contain classes
	 */	
	public static List getLibaryContainers(IProject p) {
		try {
			List libaryContainers = new ArrayList();
			List libaryPaths = getLibaryPaths(p);
			if (libaryPaths != null && !libaryPaths.isEmpty()) {
				for (int i = 0; i < libaryPaths.size(); i++) {
					IPath path = (IPath) libaryPaths.get(i);
					if (path.isEmpty())
						libaryContainers.add(p);
					else 
						libaryContainers.add(p.getFolder(path));
				}
			}
			return libaryContainers;
		} catch (IllegalArgumentException ex) {
			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * return list of path that may contain classes
	 */	
	protected static List getLibaryPaths(IProject p) {
		IJavaProject javaProj = getJavaProject(p);
		if (javaProj == null)
			return null;
		IClasspathEntry[] cp = null;
		try {
			cp = javaProj.getRawClasspath();
		} catch (JavaModelException ex) {
			JavaPlugin.getDefault().getMsgLogger().log(ex);
			return null;
		}
		List libaryPaths = new ArrayList();
		for (int i = 0; i < cp.length; i++) {
			if (cp[i].getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
				libaryPaths.add(cp[i].getPath().removeFirstSegments(1));
			}
		}
		return libaryPaths;
	}
	
}