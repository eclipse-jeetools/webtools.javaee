/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.jca.internal.deployables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.deployables.J2EEDeployable;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.server.core.IConnectorModule;

public class ConnectorDeployable extends J2EEDeployable implements IConnectorModule {
    protected static final String SERVER_CONTAINER = "org.eclipse.jst.server.core.container"; //$NON-NLS-1$

    private IJavaModel javaModel;

    /**
     * Constructor for ConnectorDeployable.
     * 
     * @param aNature
     * @param aFactoryId
     */
    public ConnectorDeployable(J2EEModuleNature aNature, String aFactoryId) {
        super(aNature, aFactoryId);
    }

    /**
     * Returns the javaModel.
     * 
     * @return IJavaModel
     */
    public IJavaModel getJavaModel() {
        if (javaModel == null)
            javaModel = JavaCore.create(getWorkspaceRoot());
        return javaModel;
    }

    /**
     * @see com.ibm.etools.server.core.util.DeployableProject#getRootFolder()
     */
    public IPath getRootFolder() {
        J2EEModuleNature aNature = (J2EEModuleNature) getNature();
        if (aNature == null)
            return super.getRootFolder();
        else if (aNature.isBinaryProject())
            return null;
        else
            return aNature.getModuleServerRoot().getProjectRelativePath();
    }

    /**
     * Returns the classpath as a list of absolute IPaths.
     * 
     * @param java.util.List
     */
    public IPath[] getClasspath() {
        IProject proj = getProject();
        if (proj != null && proj.isAccessible()) {
            List classpath = new ArrayList();
            List visitedProjects = new ArrayList();
            collectClasspathEntries(getJavaProject(proj), false, visitedProjects, classpath);
            return convertClassPathList(classpath);
        }
        return new IPath[0];

    }

    public IPath[] convertClassPathList(List classPathList) {
        IPath[] classPath = new IPath[classPathList.size()];
        int i = 0;
        for (Iterator iter = classPathList.iterator(); iter.hasNext();) {
            IPath element = (IPath) iter.next();
            classPath[i++] = element;
        }
        return classPath;

    }

    /**
     * 
     */
    protected void collectClasspathEntries(IJavaProject javaProject, boolean checkExport, List visitedProjects, List classpath) {
        if (javaProject != null && javaProject.exists() && !visitedProjects.contains(javaProject)) {
            visitedProjects.add(javaProject);
            addOutputLocationPath(javaProject, classpath);
            traverseClasspathEntries(javaProject, checkExport, visitedProjects, classpath);
        }
    }

    protected void addOutputLocationPath(IJavaProject javaProject, List classpath) {
        IPath outputLoc = null;
        try {
            outputLoc = javaProject.getOutputLocation();
        } catch (JavaModelException e) {
        		// ignore
        }
        if (outputLoc != null) {
            IFolder outputFolder = getWorkspaceRoot().getFolder(outputLoc);
            if (outputFolder.isAccessible())
                classpath.add(outputFolder.getLocation());
        }
    }

    protected IWorkspaceRoot getWorkspaceRoot() {
        return ResourcesPlugin.getWorkspace().getRoot();
    }

    protected void traverseClasspathEntries(IJavaProject javaProject, boolean checkExport, List visitedProjects, List classpath) {
        IClasspathEntry[] entries = null;
        try {
            entries = javaProject.getRawClasspath();
        } catch (JavaModelException e) {
        		// ignore
        }
        if (entries == null)
            return;
        IClasspathEntry entry = null;
        for (int i = 0; i < entries.length; i++) {
            entry = entries[i];
            if (checkExport && !entry.isExported())
                continue;
            switch (entry.getEntryKind()) {
            case IClasspathEntry.CPE_LIBRARY:
                processLibraryEntry(entry, classpath);
                break;
            case IClasspathEntry.CPE_CONTAINER:
                processContainerEntry(entry, classpath);
                break;
            case IClasspathEntry.CPE_PROJECT:
                processProjectEntry(entry, visitedProjects, classpath);
                break;
            }
        }
    }

    protected void processLibraryEntry(IClasspathEntry entry, List classpath) {
        addClasspathEntryPath(entry, classpath);
    }

    protected void addClasspathEntryPath(IClasspathEntry entry, List classpath) {
        IPath entryPath = entry.getPath();
        if (entryPath.getDevice() == null)
            classpath.add(getWorkspaceRoot().getLocation().append(entryPath));
        else
            classpath.add(entryPath);
    }

    protected void processContainerEntry(IClasspathEntry entry, List classpath) {
        if (isValidContainerEntry(entry)) {
            IClasspathEntry resolvedEntry = JavaCore.getResolvedClasspathEntry(entry);
            addClasspathEntryPath(resolvedEntry, classpath);
        }
    }

    protected void processProjectEntry(IClasspathEntry entry, List visitedProjects, List classpath) {
        String projName = entry.getPath().segment(0);
        IJavaProject javaProj = getJavaProject(projName);
        collectClasspathEntries(javaProj, true, visitedProjects, classpath);
    }

    protected IJavaProject getJavaProject(IProject proj) {
        return getJavaProject(proj.getName());
    }

    protected IJavaProject getJavaProject(String projName) {
        return getJavaModel().getJavaProject(projName);
    }

    protected boolean isValidContainerEntry(IClasspathEntry entry) {
        return entry.getPath().segment(0).equals(SERVER_CONTAINER);
    }

    public String getType() {
        return "j2ee.connector"; //$NON-NLS-1$
    }

    public String getVersion() {
        return "1.2"; //$NON-NLS-1$
    }

   
}