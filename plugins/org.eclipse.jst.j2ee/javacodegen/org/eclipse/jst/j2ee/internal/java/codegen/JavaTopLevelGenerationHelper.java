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
package org.eclipse.jst.j2ee.internal.java.codegen;



import org.eclipse.core.resources.IWorkspace;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.TopLevelGenerationHelper;
import org.eclipse.wst.common.jdt.internal.integration.WorkingCopyProvider;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Java generation specific top level helper. This may be extended to add metadata level helper
 * information.
 */
public class JavaTopLevelGenerationHelper extends TopLevelGenerationHelper {
	private String fDefaultPkgFragRootName = null;
	private IJavaModel fJavaModel = null;
	private IJavaProject fJavaProject = null;
	private IPackageFragmentRoot fDefaultPackageFragmentRoot = null;
	private WorkingCopyProvider workingCopyProvider;

	/**
	 * JavaTopLevelGenerationHelper default constructor.
	 */
	public JavaTopLevelGenerationHelper() {
		super();
	}

	/**
	 * Subclasses can override to create a proper merge strategy. The default is an instance of
	 * {@link JavaTagBatchMergeStrategy}. Overriding this merge strategy creation here in the top
	 * level helper has the effect of setting the merge strategy for all compilation units affected
	 * by this generation.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.IJavaMergeStrategy
	 * @see JavaCompilationUnitGenerator#createMergeStrategy()
	 */
	public IJavaMergeStrategy createMergeStrategy() {
		return new org.eclipse.jst.j2ee.internal.java.codegen.JavaTagBatchMergeStrategy();
	}

	/**
	 * Returns the default PackageFragmentRoot for this generation. If the setter has not been
	 * called, it will try to return the first source root in the Java project. If this can not be
	 * resolved, an exception is thrown.
	 * 
	 * @return org.eclipse.jdt.core.api.IPackageFragmentRoot
	 * @exception GenerationException
	 *                if the project does not exist or no source root can be found
	 */
	public IPackageFragmentRoot getDefaultPackageFragmentRoot() throws GenerationException {
		if (fDefaultPackageFragmentRoot == null) {
			String pkgFragRootName = getDefaultPackageFragmentRootName();
			if ((pkgFragRootName == null) || (pkgFragRootName.length() == 0)) {
				try {
					IPackageFragmentRoot[] roots = getJavaProject().getPackageFragmentRoots();
					for (int i = 0; (i < roots.length) && (fDefaultPackageFragmentRoot == null); i++) {
						if (roots[i].getKind() == IPackageFragmentRoot.K_SOURCE)
							fDefaultPackageFragmentRoot = roots[i];
					}
				} catch (JavaModelException exc) {
					throw new GenerationException(exc);
				}
				if (fDefaultPackageFragmentRoot == null)
					throw new GenerationException(JavaCodeGenResourceHandler.getString("No_source_package_fragment_EXC_")); //$NON-NLS-1$ = "No source package fragment root could be found."
			} else {
				try {
					fDefaultPackageFragmentRoot = getJavaProject().findPackageFragmentRoot(getJavaProject().getProject().getFullPath().append(pkgFragRootName));
					if (fDefaultPackageFragmentRoot.getKind() != IPackageFragmentRoot.K_SOURCE) {
						fDefaultPackageFragmentRoot = null;
						throw new GenerationException(JavaCodeGenResourceHandler.getString("Specified_root_is_not_a_so_EXC_")); //$NON-NLS-1$ = "Specified root is not a source root."
					}
				} catch (JavaModelException exc) {
					throw new GenerationException(exc);
				}
			}
		}
		return fDefaultPackageFragmentRoot;
	}

	/**
	 * Returns the project relative name of the default package fragment root.
	 */
	public String getDefaultPackageFragmentRootName() {
		return fDefaultPkgFragRootName;
	}

	/**
	 * Returns the JavaModel for this generation.
	 * 
	 * @return org.eclipse.jdt.core.api.IJavaModel
	 */
	public IJavaModel getJavaModel() {
		if (fJavaModel == null)
			fJavaModel = ProjectUtilities.getJavaModel();
		return fJavaModel;
	}

	/**
	 * Returns the JavaProject for this generation.
	 * 
	 * @return org.eclipse.jdt.core.api.IJavaProject
	 * @exception org.eclipse.jst.j2ee.internal.internal.internal.codegen.GenerationException
	 *                if the project name has not been set.
	 */
	public IJavaProject getJavaProject() throws GenerationException {
		if (fJavaProject == null) {
			String projName = getProjectName();
			if ((projName == null) || (projName.length() == 0))
				throw new GenerationException(JavaCodeGenResourceHandler.getString("Project_name_not_specified_EXC_")); //$NON-NLS-1$ = "Project name not specified."
			fJavaProject = getJavaModel().getJavaProject(projName);
		}
		return fJavaProject;
	}

	/**
	 * Returns the compilation unit working copy provider for this generation. The working copy
	 * provider must be set before starting generation. Otherwise, this call will return null and
	 * null pointer exceptions are sure to result.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.WorkingCopyProvider
	 */
	public WorkingCopyProvider getWorkingCopyProvider() {
		return workingCopyProvider;
	}

	/**
	 * Returns the target workspace.
	 */
	public IWorkspace getWorkspace() {
		return org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.getWorkspace();
	}

	/**
	 * Sets the project relative name of the default package fragment root.
	 */
	public void setDefaultPackageFragmentRootName(String rootName) {
		fDefaultPkgFragRootName = rootName;
	}

	/**
	 * Sets the working copy provider for this generation. This must be set before starting the
	 * generation.
	 * 
	 * @param newWorkingCopyProvider
	 *            org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.WorkingCopyProvider
	 */
	public void setWorkingCopyProvider(WorkingCopyProvider newWorkingCopyProvider) {
		workingCopyProvider = newWorkingCopyProvider;
	}
}