/*
 * Created on Mar 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.common;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * @author nagrawal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateProjectClasspath {
	
 
	public UpdateProjectClasspath(String sourceFolder, IProject jProject){
		addSrcFolderToProject( sourceFolder, jProject);
	}
	
	private IClasspathEntry[] getClasspathEntries(String sourceFolder, IProject jProject) {
	
		ArrayList list = new ArrayList();
		list.add(JavaCore.newSourceEntry(jProject.getFullPath().append(sourceFolder)));
		
		IClasspathEntry[] classpath = new IClasspathEntry[list.size()];
		for (int i = 0; i < classpath.length; i++) {
			classpath[i] = (IClasspathEntry) list.get(i);
		}
		return classpath;		
	}	
	
	private void addSrcFolderToProject(String sourceFolder, IProject jProject) {
			
		IJavaProject javaProject = JavaCore.create( jProject );
		try {
	
			IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
			IClasspathEntry[] newEntries = getClasspathEntries(sourceFolder, jProject);
			
			int oldSize = oldEntries.length;
			int newSize = newEntries.length;
			
			IClasspathEntry[] classpathEnties = new IClasspathEntry[oldSize + newSize];
			int k = 0;
			for (int i = 0; i < oldEntries.length; i++) {
				classpathEnties[i] = oldEntries[i];
				k++;
			}
			for( int j=0; j< newEntries.length; j++){
				classpathEnties[k] = newEntries[j];
				k++;
			}
			javaProject.setRawClasspath(classpathEnties, null);
		}
		catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
	}		

}
