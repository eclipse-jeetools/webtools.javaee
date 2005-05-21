/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.rename;


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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class RenameModuleDependencyOperation extends WTPOperation {

	protected List earEditModels;
	protected Set projects;
	protected IProject renamedProject = null;
	protected Map renameInfos;
	protected IProgressMonitor monitor;
	protected static String MOD_DEP_RENAME_ERRORS = J2EECreationResourceHandler.getString("Errors_occurred_renaming_module_dependencies_UI_"); //$NON-NLS-1$

	public RenameModuleDependencyOperation(List earEditModels, Set projects, IProject renamedProject) {
		this.earEditModels = earEditModels;
		this.projects = projects;
		this.renamedProject = renamedProject;
	}

	protected Map getRenameInfos() {
		if (renameInfos == null)
			renameInfos = new HashMap();
		return renameInfos;
	}

	protected ClasspathRenameInfo getRenameInfo(IProject referencingProject) {
		ClasspathRenameInfo info = (ClasspathRenameInfo) getRenameInfos().get(referencingProject);
		if (info != null)
			return info;
		info = new ClasspathRenameInfo(referencingProject);
		renameInfos.put(referencingProject, info);
		return info;
	}

	/**
	 * @see HeadlessJ2EEOperation#execute(IProgressMonitor)
	 */
	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		this.monitor = aMonitor;
		gatherRenameInfoIfNecessary();
		executeRenameInfo();
	}

	/*
	 * Iterate the edit models; for each edit model, iterate the projects; for each project... find
	 * the module for the project find all the modules that reference the module for each of those,
	 * resolve back to a project and cache the runtime classpath entry and project name going away
	 */
	protected void gatherRenameInfo() {
		for (int i = 0; i < earEditModels.size(); i++) {
			EAREditModel model = (EAREditModel) earEditModels.get(i);
			gatherRenameInfo(model);
		}
	}

	protected void gatherRenameInfo(EAREditModel editModel) {
		EARNatureRuntime runtime = editModel.getEARNature();
		Map urisToNatures = runtime.getModuleProjects();
		for (Iterator iterator = projects.iterator(); iterator.hasNext();) {
			IProject project = (IProject) iterator.next();
			gatherRenameInfo(runtime, urisToNatures, project);
		}
	}

	/*
	 * If this project is referenced by the EAR iterate all the other projects referenced by the EAR
	 * for each project p if the p references the referenced project, add the reference to the
	 * rename info
	 */
	protected void gatherRenameInfo(EARNatureRuntime runtime, Map urisToNatures, IProject referencedProject) {
		Module m = runtime.getModule(referencedProject);
		if (m == null)
			return;

		Iterator it = urisToNatures.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String uri = (String) entry.getKey();
			J2EENature nature = (J2EENature) entry.getValue();
			if (nature == null || referencedProject.equals(nature.getProject()))
				continue;
			gatherRenameInfo(uri, nature, referencedProject, m.getUri());
		}
	}

	protected void gatherRenameInfo(String testUri, J2EENature testNature, IProject referencedProject, String moduleUri) {
		ArchiveManifest mf = J2EEProjectUtilities.readManifest(testNature.getProject());
		if (mf == null)
			return;
		String[] classpath = J2EEProjectUtilities.readManifest(testNature.getProject()).getClassPathTokenized();
		for (int i = 0; i < classpath.length; i++) {
			String resolved = ArchiveUtil.deriveEARRelativeURI(classpath[i], moduleUri);
			if (!moduleUri.equals(resolved))
				continue;
			ClasspathRenameInfo info = getRenameInfo(testNature.getProject());
			info.getManifestEntries().add(classpath[i]);
			info.getReferencedProjects().add(referencedProject);
		}
	}

	protected void executeRenameInfo() throws InvocationTargetException {
		if (renameInfos == null)
			return;
		Iterator it = renameInfos.values().iterator();
		while (it.hasNext()) {
			ClasspathRenameInfo info = (ClasspathRenameInfo) it.next();
			IProject p = info.getTargetProject();
			J2EEModuleNature nature = (J2EEModuleNature) J2EENature.getRegisteredRuntime(p);
			if (nature == null)
				continue;
			if (nature.isBinaryProject()) {
				String message = J2EECreationResourceHandler.getString("Can_not_rename_module_dependency_from_project_UI_", new Object[]{p.getName()}); //$NON-NLS-1$ 
				addErrorStatus(message);
				continue;
			}
			try {
				replaceManifest(nature, info);
				replaceBuildPath(nature, info);
			} catch (Exception ex) {
				throw new WFTWrappedException(ex);
			}
		}
	}

	protected void replaceManifest(J2EENature nature, ClasspathRenameInfo info) throws IOException {
		ArchiveManifest mf = J2EEProjectUtilities.readManifest(nature.getProject());
		if (mf == null)
			return;
		String[] entries = mf.getClassPathTokenized();
		StringBuffer sb = new StringBuffer();
		boolean empty = true;
		for (int i = 0; i < entries.length; i++) {
			String entry = entries[i];
			if (info.getManifestEntries().contains(entry)) {
				String suffix = entry.substring(entry.lastIndexOf('.'));
				entry = renamedProject.getName();
				entry = entry.concat(suffix);
			}
			if (!empty)
				sb.append(" "); //$NON-NLS-1$
			empty = false;
			sb.append(entry);
		}
		mf.setClassPath(sb.toString());
		J2EEProjectUtilities.writeManifest(nature.getProject(), mf);
	}

	protected void replaceBuildPath(J2EENature nature, ClasspathRenameInfo info) throws JavaModelException {
		IJavaProject javaP = JemProjectUtilities.getJavaProject(nature.getProject());
		if (javaP == null)
			return;
		List result = new ArrayList();
		IClasspathEntry[] entries = javaP.getRawClasspath();
		for (int i = 0; i < entries.length; i++) {
			IClasspathEntry entry = entries[i];
			if (shouldKeepEntry(entry, info))
				result.add(entry);
			else {
				IPath path = renamedProject.getFullPath();
				IClasspathEntry renamedEntry = JavaCore.newProjectEntry(path, true);
				result.add(renamedEntry);
			}
		}
		IClasspathEntry[] toArray = (IClasspathEntry[]) result.toArray(new IClasspathEntry[result.size()]);
		javaP.setRawClasspath(toArray, new SubProgressMonitor(monitor, 1));
	}

	protected boolean shouldKeepEntry(IClasspathEntry entry, ClasspathRenameInfo info) {
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
		gatherRenameInfoIfNecessary();
		if (renameInfos == null || renameInfos.isEmpty())
			return Collections.EMPTY_LIST;
		List result = new ArrayList();
		Iterator it = renameInfos.values().iterator();
		while (it.hasNext()) {
			ClasspathRenameInfo info = (ClasspathRenameInfo) it.next();
			IProject p = info.getTargetProject();
			IFile aFile = J2EEProjectUtilities.getManifestFile(p);
			if (aFile != null && aFile.exists()) {
				result.add(aFile);
				result.addAll(ProjectUtilities.getFilesAffectedByClasspathChange(p));
			}
		}
		return result;
	}

	/*
	 * Iterate the edit models; for each edit model, iterate the projects; for each project... find
	 * the module for the project find all the modules that reference the module for each of those,
	 * resolve back to a project and cache the runtime classpath entry and project name going away
	 */
	protected void gatherRenameInfoIfNecessary() {
		if (renameInfos == null || renameInfos.isEmpty()) {
			for (int i = 0; i < earEditModels.size(); i++) {
				EAREditModel model = (EAREditModel) earEditModels.get(i);
				gatherRenameInfo(model);
			}
		}
	}

	protected void addErrorStatus(String message) {
		IStatus newStatus = J2EEPlugin.newErrorStatus(message, null);
		addStatus(newStatus);
		//		if (status == null)
		//			status = newStatus;
		//		else if (status.isMultiStatus())
		//			((MultiStatus)status).add(newStatus);
		//		else
		//			status = new MultiStatus(J2EEPlugin.PLUGIN_ID, 0, new IStatus[]{status},
		// status.getMessage(), null);
	}
}