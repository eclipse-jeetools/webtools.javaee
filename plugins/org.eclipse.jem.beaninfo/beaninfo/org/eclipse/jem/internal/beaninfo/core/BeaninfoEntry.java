/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.core;
/*
 *  $RCSfile: BeaninfoEntry.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:31 $ 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.*;
import org.w3c.dom.*;

import org.eclipse.jem.internal.beaninfo.adapters.*;

/**
 * Beaninfo entry. Location of the beaninfos. Much like a standard classpath entry.
 * The BeanInfos are either in a jar or another project. They can be supplied as
 * a local file in the project, or as an external jar, or as external jar through a
 * variable, or an external jar through a plugin.
 * <p>
 * An external jar through containers is not valid because container are attached to
 * projects. they aren't standalone.
 * 
 * @version 	1.0
 * @author
 */
public class BeaninfoEntry implements IBeaninfosDocEntry {

	final static String sBeaninfo = "beaninfo"; // Beaninfo entry, shared with BeaninfosDoc. //$NON-NLS-1$
	
	public static final int BIE_PLUGIN = 100;	// Beaninfo jar can be found in a plugin.

	static int kindFromString(String kindStr) {
		if (kindStr == null || kindStr.length() == 0)
			return BIE_PLUGIN;	// Default to plugin. If coming from beaninfoconfig, there should always be kind. But if coming from plugin.xml there shouldn't be one.
		if (kindStr.equalsIgnoreCase("con"))
			return IClasspathEntry.CPE_CONTAINER;
		if (kindStr.equalsIgnoreCase("var")) //$NON-NLS-1$
			return IClasspathEntry.CPE_VARIABLE;
		if (kindStr.equalsIgnoreCase("src")) //$NON-NLS-1$
			return IClasspathEntry.CPE_SOURCE;
		if (kindStr.equalsIgnoreCase("lib")) //$NON-NLS-1$
			return IClasspathEntry.CPE_LIBRARY;
		if (kindStr.equalsIgnoreCase("plugin")) //$NON-NLS-1$
			return BIE_PLUGIN;
		return -1;
	}

	static String kindToString(int kind) {

		switch (kind) {
			case IClasspathEntry.CPE_PROJECT :
				return "src"; // backward compatibility //$NON-NLS-1$
			case IClasspathEntry.CPE_SOURCE :
				return "src"; //$NON-NLS-1$
			case IClasspathEntry.CPE_LIBRARY :
				return "lib"; //$NON-NLS-1$
			case IClasspathEntry.CPE_VARIABLE :
				return "var"; //$NON-NLS-1$
			case IClasspathEntry.CPE_CONTAINER:
				return "con";
			case BIE_PLUGIN:
				return "plugin";	//$NON-NLS-1$
			default :
				return "unknown"; //$NON-NLS-1$
		}
	}

	/**
	 * Return the appropriate kind of entry when we know it is a classpath entry.
	 */
	public static IClasspathEntry createEntry(int kind, IPath path, IProject project, boolean isExported) {
		switch (kind) {

			case IClasspathEntry.CPE_LIBRARY :
				if (path.isAbsolute())
					return JavaCore.newLibraryEntry(path, null, null, isExported);
				break;

			case IClasspathEntry.CPE_SOURCE :
				if (path.isAbsolute()) {
					// must be an entry in this project or specify another project
					String projSegment = path.segment(0);
					if (project != null && projSegment != null && projSegment.equals(project.getName())) {
						// this project
						return JavaCore.newSourceEntry(path);
					} else {
						// another project
						return JavaCore.newProjectEntry(path, isExported);
					}
				}
				break;

			case IClasspathEntry.CPE_VARIABLE :
				return JavaCore.newVariableEntry(path, null, null, isExported);
			
			case IClasspathEntry.CPE_CONTAINER:
				return JavaCore.newContainerEntry(path, isExported);

		}

		return null;
	}
	/**
	 * Read the entry in from the element.
	 */
	public static BeaninfoEntry readEntry(IReader reader, Object element, IProject project) {
		String elementKind = reader.getAttribute(element, BeaninfosDoc.sKind);
		String pathStr = reader.getAttribute(element, BeaninfosDoc.sPath);
		// ensure path is absolute
		IPath path = new Path(pathStr);
		int kind = kindFromString(elementKind);
		if (kind != IClasspathEntry.CPE_VARIABLE && kind != IClasspathEntry.CPE_CONTAINER && kind != BIE_PLUGIN && !path.isAbsolute()) {
			path = project != null ? project.getFullPath().append(path) : path.makeAbsolute(); // Some folder/jar within this project
		}

		// exported flag
		String exportedString = reader.getAttribute(element, BeaninfosDoc.sExported);
		boolean isExported = "true".equalsIgnoreCase(exportedString); //$NON-NLS-1$
		//$NON-NLS-1$

		// recreate the entry
		IClasspathEntry cpEntry = null;
		IPath pluginPath = null;
		if (kind != BIE_PLUGIN) {
			cpEntry = createEntry(kind, path, project, isExported);
		} else {
			if (path.isAbsolute())
				pluginPath = path;
			else {
				// Kludge This should only be a plugin type if from configuration element. So we will cast to that
				// and get the plugin id to create an absolute plugin path.
				if (element instanceof IConfigurationElement) {
					pluginPath = new Path('/'+((IConfigurationElement) element).getDeclaringExtension().getNamespace()).append(path);
				} else
					return null;	// Not valid because can't have plugin from .beaninfoconfig file.
			}
		}

		ArrayList searchpaths = new ArrayList();
		Object children = reader.getChildren(element);
		int childrenLength = reader.getLength(children);
		for (int i = 0; i < childrenLength; i++) {
			Object child = reader.getItem(children, i);
			if (reader.isNodeTypeElement(child)) {
				Object entry = null;
				if (reader.getNodeName(child).equalsIgnoreCase(SearchpathEntry.sSearchpath)) {
					entry = SearchpathEntry.readEntry(reader, child, project, true);
				}
				if (entry != null)
					searchpaths.add(entry);
			}
		}

		if (cpEntry != null)
			return new BeaninfoEntry(
				cpEntry,
				(SearchpathEntry[]) searchpaths.toArray(new SearchpathEntry[searchpaths.size()]),
				isExported);
		else return new BeaninfoEntry(
				pluginPath,
				(SearchpathEntry[]) searchpaths.toArray(new SearchpathEntry[searchpaths.size()]),
				isExported);
	}

	protected IClasspathEntry entry; // Store it as a classpath entry for convienence. It is the RAW classpath entry. This is only used when pointing to something other than a plugin.
	protected IPath pluginPath;	// When stored in a plugin, this will be set instead.
	protected boolean isExported;
	protected SearchpathEntry[] searchpaths;
	
	/**
	 * Used when the beaninfo jar is within a plugin. In that case, the first segment
	 * of the path is the plugin descriptor, and the rest is the path from the plugin
	 * directory to the jar.
	 */
	public BeaninfoEntry(IPath pluginPath, SearchpathEntry[] searchpaths, boolean isExported) {
		this(searchpaths, isExported);
		this.pluginPath = pluginPath;
	}

	/**
	 * Used when the beaninfo jar/folder is either an external jar/folder or is somewhere else
	 * in the workspace. In that case the entry is the RAW classpath entry to that code.
	 */
	public BeaninfoEntry(IClasspathEntry entry, SearchpathEntry[] searchpaths, boolean isExported) {
		this(searchpaths, isExported);
		this.entry = entry;
	}
	
	protected BeaninfoEntry(SearchpathEntry[] searchpaths, boolean isExported) {
		this.isExported = isExported;
		this.searchpaths = searchpaths != null ? searchpaths : new SearchpathEntry[0];
	}
	
	public SearchpathEntry[] getSearchPaths() {
		return searchpaths;
	}
	
	public void setSearchPaths(SearchpathEntry[] searchpaths) {
		this.searchpaths = searchpaths;
	}
	
	public boolean isExported() {
		return isExported;
	}
	
	public void setIsExported(boolean isExported) {
		this.isExported = isExported;
	}

	public Node writeEntry(Document doc, IProject project) {

		Element element = doc.createElement(sBeaninfo);
		IPath path = null;
		if (entry != null) {
			element.setAttribute(BeaninfosDoc.sKind, kindToString(entry.getEntryKind()));
			path = entry.getPath();
			if (entry.getEntryKind() != IClasspathEntry.CPE_VARIABLE && entry.getEntryKind() != IClasspathEntry.CPE_CONTAINER) {
				// translate to project relative from absolute (unless a device path)
				if (path.isAbsolute()) {
					if (path.segment(0).equals(project.getFullPath().segment(0))) {
						path = path.removeFirstSegments(1);
						path = path.makeRelative();
					} else {
						path = path.makeAbsolute();
					}
				}
			}
		} else {
			element.setAttribute(BeaninfosDoc.sKind, kindToString(BIE_PLUGIN));
			path = pluginPath;
		}

		element.setAttribute(BeaninfosDoc.sPath, path.toString()); //$NON-NLS-1$
		if (isExported()) {
			element.setAttribute(BeaninfosDoc.sExported, "true"); //$NON-NLS-1$
		}

		for (int i = 0; i < searchpaths.length; i++) {
			SearchpathEntry spe = searchpaths[i];
			element.appendChild(spe.writeEntry(doc, project));
		}

		return element;
	}
	
	/**
	 * If this is not a plugin info, then return the classpath entry.
	 */
	public IClasspathEntry getClasspathEntry() {
		return entry;
	}

	/**
	 * Return the resolved classpaths. Each entry in the array will be either:
	 * 1) IProject - If it is a project type entry. Want the whole project
	 * 2) String - an absolute external path to a jar
	 * 3) IPath - a path to a plugin jar. The first segment is the plugin id, the rest is the path relative to that plugin.
	 *            
	 * 
	 * @param javaProject
	 * @return The array of paths, or <code>null</code> if no paths.
	 * 
	 * @since 1.0.0
	 */
	public Object[] getClasspath(IJavaProject javaProject) {
		if (entry != null) {
			// It is a standard CPE Entry.
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			List paths = new ArrayList(1);
			IClasspathEntry resolvedEntry = JavaCore.getResolvedClasspathEntry(entry);
			resolveEntry(root, paths, resolvedEntry, javaProject);
			return paths.toArray();
		} else if (pluginPath != null) 
			return new Object[] {pluginPath};
		
		return null;

	}
	
	private void resolveEntry(IWorkspaceRoot root, List paths, IClasspathEntry entry, IJavaProject javaProject) {
		switch (entry.getEntryKind()) {
			case IClasspathEntry.CPE_PROJECT :
				IProject reqProject = (IProject) root.findMember(entry.getPath().lastSegment());
				// Project entries only have one segment.
				if (reqProject != null && reqProject.isOpen())
					paths.add(reqProject);
				break;

			case IClasspathEntry.CPE_SOURCE :
				reqProject = (IProject) root.findMember(entry.getPath().segment(0));
				// Find project from the first segment.
				IJavaProject jProject = JavaCore.create(reqProject);
				if (jProject != null) {
					try {
						IPath outputLocation = jProject.getOutputLocation();
						IResource resource = root.findMember(outputLocation);
						if (resource != null) {
							paths.add(resource.getLocation().toString());
						}
					} catch(JavaModelException e) {
					}
				}
				break;

			case IClasspathEntry.CPE_LIBRARY :
				IResource library = root.findMember(entry.getPath());
				// can be external or in workspace
				paths.add((library != null) ? library.getLocation().toString() : entry.getPath().toString());
				break;
				
			case IClasspathEntry.CPE_CONTAINER:
				try {
					IClasspathContainer container = JavaCore.getClasspathContainer(entry.getPath(), javaProject);
					if (container != null) {
						IClasspathEntry[] entries = container.getClasspathEntries();
						for (int i = 0; i < entries.length; i++) {
							resolveEntry(root, paths, entries[i], javaProject);
						}
					}
				} catch (JavaModelException e) {
					BeaninfoPlugin.getPlugin().getLogger().log(e, Level.WARNING);
				}			
		}
	}

	public int getKind() {
		return entry != null ? entry.getEntryKind() : BIE_PLUGIN;
	}
	
	public IPath getPath() {
		return entry != null ? entry.getPath() : pluginPath;
	}
	
	public boolean equals(Object other) {
		if (this == other)
			return true;
			
		if (!(other instanceof BeaninfoEntry))
			return false;

		// Return equal if the classpath entry is the same classpath entry or plugin path entry.
		// The search path doesn't have any affect on the semantic equality.
		BeaninfoEntry otherEntry = (BeaninfoEntry) other;
		if (isExported != otherEntry.isExported)
			return false;	
		if (entry != null)
			return entry.equals(otherEntry.entry);
		
		return pluginPath.equals(otherEntry.pluginPath);
	}
	
	public int hashCode() {
		if (entry != null)
			return entry.hashCode() ^ (isExported ? Boolean.TRUE : Boolean.FALSE).hashCode();
		else
			return pluginPath.hashCode() ^ (isExported ? Boolean.TRUE : Boolean.FALSE).hashCode();
	}

}
