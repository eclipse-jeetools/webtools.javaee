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
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.util.CoreUtility;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.CPListElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;


/**
 * @author Sachin
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ClassesImportWizard extends Wizard implements IImportWizard {

	public WizardClassesImportMainPage mainPage = null;

	public WizardClassesImportPage1 page1 = null;

	private IWorkbench workbench;

	private IStructuredSelection selection;

	private IPath importedClassesPath;

	private IJavaProject javaProject = null;

	private IProject project = null;

	protected ArrayList fileNames = null;

	public ClassesImportWizard(IProject project) {
		super();
		this.project = project;

		setWindowTitle(J2EEUIMessages.getResourceString("Import_Class_Files_UI")); //$NON-NLS-1$
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor("import_class_file_wiz_ban")); //$NON-NLS-1$
	}

	public ClassesImportWizard(IProject project, String fileName, List otherFileNames) {
		this(project);
		this.fileNames = new ArrayList();
		this.fileNames.add(fileName);
		int i = fileName.lastIndexOf(java.io.File.separatorChar);
		String parentDir = fileName.substring(0, i);
		if (otherFileNames != null)
			for (int j = otherFileNames.size() - 1; j >= 0; j--) {
				if (otherFileNames.get(j) != null) {
					int k = ((String) otherFileNames.get(j)).lastIndexOf(java.io.File.separatorChar);
					if (k == i && parentDir.equals(((String) otherFileNames.get(j)).substring(0, k))) {
						fileNames.add(otherFileNames.remove(j));
					}
				}
			}

	}

	public void setFolderPath(IPath path) {
		importedClassesPath = path;
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {

		return page1.finish();

	}

	public void createImportedClassesFolder(IProject aProject) {
		//Create imported_classes folder selected project
		IContainer container = aProject;
		IFolder folder = container.getFolder(new Path("imported_classes")); //$NON-NLS-1$
		javaProject = getIJavaProject(aProject);

		IPath importedFoldersClass = folder.getFullPath();

		CPListElement entry = newCPLibraryElement(folder);
		IClasspathEntry newEntry = entry.getClasspathEntry();

		IResource res = entry.getResource();
		if ((res instanceof IFolder) && !res.exists()) {
			try {
				CoreUtility.createFolder((IFolder) res, true, true, null);

			} catch (CoreException e) {
			}
		}

		try {
			IClasspathEntry[] classpathEntries = javaProject.getRawClasspath();
			IClasspathEntry[] newClasspath = new IClasspathEntry[classpathEntries.length + 1];

			for (int i = 0; i < classpathEntries.length; i++) {
				newClasspath[i] = classpathEntries[i];
			}
			newClasspath[classpathEntries.length] = newEntry;

			javaProject.setRawClasspath(newClasspath, null);

		} catch (JavaModelException e) {
		}

		setFolderPath(importedFoldersClass);
	}

	private IJavaProject getIJavaProject(IProject projectHandle) {
		IJavaModel javaModel = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());
		return javaModel.getJavaProject(projectHandle.getName());
	}

	private CPListElement newCPLibraryElement(IResource res) {

		return new CPListElement(javaProject, IClasspathEntry.CPE_LIBRARY, res.getFullPath(), res);
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench aWorkbench, IStructuredSelection aSelection) {
		this.workbench = aWorkbench;
		this.selection = aSelection;
		if (project == null && !aSelection.isEmpty() && aSelection.getFirstElement() instanceof IProject)
			this.project = (IProject) aSelection.getFirstElement();

		if (project != null)
			createImportedClassesFolder(project);
	}

	public void addPages() {
		super.addPages();

		//mainPage = new WizardClassesImportMainPage("id"); //$NON-NLS-1$
		mainPage = new WizardClassesImportMainPage("id", fileNames); //$NON-NLS-1$
		page1 = new WizardClassesImportPage1(workbench, selection, importedClassesPath, fileNames);

		page1.setWizard(this);

		if (fileNames == null || fileNames.size() == 0)
			addPage(mainPage);
		addPage(page1);

	}

}