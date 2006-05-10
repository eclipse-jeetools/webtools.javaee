/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.wst.common.frameworks.internal.DoNotUseMeThisWillBeDeletedPost15;


/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateProjectClasspath {
	
 
	public UpdateProjectClasspath(String sourceFolder, String componentName, IProject jProject){
		addSrcFolderToProject(sourceFolder, componentName, jProject);
	}
	
	private IClasspathEntry[] getClasspathEntries(String sourceFolder, String componentName,
			IProject jProject) {
	
		ArrayList list = new ArrayList();
		list.add(JavaCore.newSourceEntry(jProject.getFullPath().append(sourceFolder)));
		
		IClasspathEntry[] classpath = new IClasspathEntry[list.size()];


        IPath newOutputPath = null;
        for (int i = 0; i < classpath.length; i++) {
			classpath[i] = (IClasspathEntry) list.get(i);
            newOutputPath = Path.fromOSString(Path.SEPARATOR +jProject.getName() + "/bin/");
            ((ClasspathEntry)classpath[i]).specificOutputLocation = newOutputPath;
		}
		return classpath;		
	}	
	
	private void addSrcFolderToProject(String sourceFolder,String componentName,
			IProject jProject) {
			
		IJavaProject javaProject = JavaCore.create( jProject );
		try {
	
			IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
            List oldEntriesList,classpathList;
			IClasspathEntry[] newEntries = getClasspathEntries(sourceFolder, componentName, 
					jProject);
	         /**
	          * Warning clean-up 12/05/2005
	          */   
			//int oldSize = oldEntries.length;
			//int newSize = newEntries.length;
			
			classpathList = new ArrayList();
			oldEntriesList = Arrays.asList(oldEntries);
			classpathList.addAll(oldEntriesList);
			for( int j=0; j< newEntries.length; j++){
                if(!oldEntriesList.contains(newEntries[j])) {
                	classpathList.add(newEntries[j]);
                }
			}
			IClasspathEntry[] classpathEntries = (IClasspathEntry[]) classpathList.toArray(new IClasspathEntry[classpathList.size()]);
			javaProject.setRawClasspath(classpathEntries, null);
		}
		catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
	}	

	private static IClasspathEntry[] getProjectDependency(IProject clientProj){
		IClasspathEntry projectEntry = JavaCore.newProjectEntry(clientProj.getFullPath(), true);
			return new IClasspathEntry[]{projectEntry};	
	}
	
	private static boolean entryToChange(final IClasspathEntry entry, final IClasspathEntry[] entriesToChange) {
		for (int i = 0; i < entriesToChange.length; i++) {
			if (entriesToChange[i].equals(entry)) {
				return true;
			}
		}
		return false;
	}	

	/**
	 * {@link DoNotUseMeThisWillBeDeletedPost15}
	 * TODO this probably can be deleted
	 * @param projectName
	 * @param referencedProjectName
	 * @param add
	 */
	public static  void updateProjectDependency(final String projectName, final String referencedProjectName, final boolean add) {

		IProject proj = ProjectUtilities.getProject( projectName );
		IProject refproj = ProjectUtilities.getProject( referencedProjectName );
		updateProjectDependency( proj, refproj, add );
		
	}
	/**
	 * {@link DoNotUseMeThisWillBeDeletedPost15}
	 * TODO this probably can be deleted
	 * @param ejbProj
	 * @param clientProj
	 * @param add
	 */
	public static  void updateProjectDependency(final IProject ejbProj, final IProject clientProj, final boolean add) {
		final IJavaProject javaProject = JavaCore.create(ejbProj);
		try {
			final IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
			final IClasspathEntry[] entriesToChange = getProjectDependency(clientProj);
			final List classpathEntries = new ArrayList();
			for (int i = 0; i < oldEntries.length; i++) {
				if (!entryToChange(oldEntries[i], entriesToChange)) {
					classpathEntries.add(oldEntries[i]);
				}
			}
			if (add) {
				for (int j = 0; j < entriesToChange.length; j++) {
					boolean containsEntry = false;
					if (entriesToChange[j].getEntryKind() == IClasspathEntry.CPE_PROJECT) {
						for (int k = 0; k < classpathEntries.size(); k++) {
							String existingEntry = ((IClasspathEntry) classpathEntries.get(k)).getPath().segment(0);
							String newEntry = entriesToChange[j].getPath().segment(0);
							if (existingEntry.equals(newEntry)) {
								containsEntry = true;
								break;
							}
						}
					}
					if (!containsEntry)
						classpathEntries.add(entriesToChange[j]);
				}
			}
			javaProject.setRawClasspath((IClasspathEntry[]) classpathEntries.toArray(new IClasspathEntry[classpathEntries.size()]), null);
		} catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
	}
	
}
