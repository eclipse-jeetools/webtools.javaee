/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.delete;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class DeleteModuleDependencyOperation extends WTPOperation {

	protected List earNatures;
	protected Set projects;
	protected Map deleteInfos;
	protected IProgressMonitor monitor;
	protected static String MOD_DEP_DELETE_ERRORS = J2EECreationResourceHandler.getString("Errors_occurred_deleting_module_dependencies_UI_"); //$NON-NLS-1$
	protected List affectedFiles = null;

	public DeleteModuleDependencyOperation(List earNatures, Set projects) {
		this.earNatures = earNatures;
		this.projects = projects;
	}

	protected Map getDeleteInfos() {
		if (deleteInfos == null)
			deleteInfos = new HashMap();
		return deleteInfos;
	}

	protected ClasspathDeleteInfo getDeleteInfo(IProject referencingProject) {
		ClasspathDeleteInfo info = (ClasspathDeleteInfo) getDeleteInfos().get(referencingProject);
		if (info != null)
			return info;
		info = new ClasspathDeleteInfo(referencingProject);
		deleteInfos.put(referencingProject, info);
		return info;
	}

	/**
	 * @see HeadlessJ2EEOperation#execute(IProgressMonitor)
	 */
	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		this.monitor = aMonitor;
		gatherDeleteInfoIfNecessary();
		executeDeleteInfo();
	}

	/*
	 * Iterate the edit models; for each edit model, iterate the projects; for each project... find
	 * the module for the project find all the modules that reference the module for each of those,
	 * resolve back to a project and cache the runtime classpath entry and project name going away
	 */
	protected void gatherDeleteInfoIfNecessary() {
		if (deleteInfos == null || deleteInfos.isEmpty()) {
			for (int i = 0; i < earNatures.size(); i++) {
				EARNatureRuntime nature = (EARNatureRuntime) earNatures.get(i);
				gatherDeleteInfo(nature);
			}
		}
	}

	protected void gatherDeleteInfo(EARNatureRuntime runtime) {
		Map urisToProjects = runtime.getAllMappedProjects();
		for (Iterator iterator = projects.iterator(); iterator.hasNext();) {
			IProject project = (IProject) iterator.next();
			gatherDeleteInfo(runtime, urisToProjects, project);
		}
	}

	/*
	 * If this project is referenced by the EAR iterate all the other projects referenced by the EAR
	 * for each project p if the p references the referenced project, add the reference to the
	 * delete info
	 */
	protected void gatherDeleteInfo(EARNatureRuntime runtime, Map urisToProjects, IProject referencedProject) {
		String referencedProjectURI = runtime.getJARUri(referencedProject);
		if (referencedProjectURI == null)
			return;
		Iterator it = urisToProjects.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String uri = (String) entry.getKey();
			IProject project = (IProject) entry.getValue();
			if (referencedProject.equals(project) || projects.contains(project))
				continue;
			gatherDeleteInfo(uri, project, referencedProject, referencedProjectURI);
		}
	}

	protected void gatherDeleteInfo(String testUri, IProject testProject, IProject referencedProject, String moduleUri) {
		ArchiveManifest mf = J2EEProjectUtilities.readManifest(testProject);
		if (mf == null)
			return;
		String[] classpath = mf.getClassPathTokenized();
		for (int i = 0; i < classpath.length; i++) {
			String resolved = ArchiveUtil.deriveEARRelativeURI(classpath[i], testUri);
			if (!moduleUri.equals(resolved))
				continue;
			ClasspathDeleteInfo info = getDeleteInfo(testProject);
			info.getManifestEntries().add(classpath[i]);
			info.getReferencedProjects().add(referencedProject);
		}
	}

	protected void executeDeleteInfo() throws InvocationTargetException {
		if (deleteInfos == null)
			return;
		Iterator it = deleteInfos.values().iterator();
		while (it.hasNext()) {
			ClasspathDeleteInfo info = (ClasspathDeleteInfo) it.next();
			IProject p = info.getTargetProject();
			if (J2EEProjectUtilities.isBinaryProject(p)) {
				String message = J2EECreationResourceHandler.getString("Can_not_remove_module_dependency_from_project_UI_", new Object[]{p.getName()}); //$NON-NLS-1$ 
				addErrorStatus(message);
				continue;
			}
			try {
				replaceManifest(p, info);
				replaceBuildPath(p, info);
			} catch (Exception ex) {
				throw new WFTWrappedException(ex);
			}
		}
	}

	protected void replaceManifest(IProject project, ClasspathDeleteInfo info) throws IOException {
		ArchiveManifest mf = J2EEProjectUtilities.readManifest(project);
		if (mf == null)
			return;
		String[] entries = mf.getClassPathTokenized();
		StringBuffer sb = new StringBuffer();
		boolean empty = true;
		for (int i = 0; i < entries.length; i++) {
			String entry = entries[i];
			if (info.getManifestEntries().contains(entry))
				continue;
			if (!empty)
				sb.append(" "); //$NON-NLS-1$
			empty = false;
			sb.append(entries[i]);
		}
		mf.setClassPath(sb.toString());
		J2EEProjectUtilities.writeManifest(project, mf);
	}

	protected void replaceBuildPath(IProject project, ClasspathDeleteInfo info) throws JavaModelException {
		IJavaProject javaP = ProjectUtilities.getJavaProject(project);
		if (javaP == null)
			return;
		List result = new ArrayList();
		IClasspathEntry[] entries = javaP.getRawClasspath();
		for (int i = 0; i < entries.length; i++) {
			IClasspathEntry entry = entries[i];
			if (shouldKeepEntry(entry, info))
				result.add(entry);
		}
		IClasspathEntry[] toArray = (IClasspathEntry[]) result.toArray(new IClasspathEntry[result.size()]);
		javaP.setRawClasspath(toArray, new SubProgressMonitor(monitor, 1));
	}

	protected boolean shouldKeepEntry(IClasspathEntry entry, ClasspathDeleteInfo info) {
		if (entry.getEntryKind() != IClasspathEntry.CPE_PROJECT)
			return true;

		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(entry.getPath().toString());
		return !info.getReferencedProjects().contains(project);
	}

	/**
	 * Return a list of all the files that will get modified as a result of running this operation;
	 * used for validateEdit
	 */
	protected List getAffectedFiles() {
		if (affectedFiles == null)
			affectedFiles = gatherAffectedFiles();
		return affectedFiles;
	}

	protected List gatherAffectedFiles() {
		gatherDeleteInfoIfNecessary();
		if (deleteInfos == null || deleteInfos.isEmpty())
			return Collections.EMPTY_LIST;
		List result = new ArrayList();
		Iterator it = deleteInfos.values().iterator();
		while (it.hasNext()) {
			ClasspathDeleteInfo info = (ClasspathDeleteInfo) it.next();
			IProject p = info.getTargetProject();
			IFile aFile = J2EEProjectUtilities.getManifestFile(p);
			if (aFile != null && aFile.exists()) {
				result.add(aFile);
				result.addAll(ProjectUtilities.getFilesAffectedByClasspathChange(p));
			}
		}
		return result;
	}

	protected void addErrorStatus(String message) {
		IStatus newStatus = J2EEPlugin.newErrorStatus(message, null);
		addStatus(newStatus);
		//		if (status.isOK())
		//			status = newStatus;
		//		else if (status.isMultiStatus())
		//			((MultiStatus)status).add(newStatus);
		//		else {
		//			IStatus[] children = new IStatus[] {status, newStatus};
		//			status = new MultiStatus(J2EEPlugin.PLUGIN_ID, 0, children, MOD_DEP_DELETE_ERRORS, null);
		//		}
	}
}