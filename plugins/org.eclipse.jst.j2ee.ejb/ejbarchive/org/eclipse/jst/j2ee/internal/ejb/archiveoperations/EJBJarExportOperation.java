/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.application.operations.J2EEExportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.internal.archive.operations.DependentJarExportMerger;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEExportOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;


public class EJBJarExportOperation extends J2EEExportOperation {
	//TODO - jsholl - why is this here --shouldn't this be in deploy plugins?
	protected DependentJarExportMerger merger;

	/**
	 * @param model
	 */
	public EJBJarExportOperation(J2EEExportDataModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	protected String archiveString() {
		return EJBArchiveOpsResourceHandler.getString("EJB_Jar_File_UI_"); //$NON-NLS-1$ = "EJB Jar File"
	}

	public void createModuleFile() throws SaveFailureException {

		try {
			CommonarchiveFactory caf = ((CommonarchivePackage) EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI)).getCommonarchiveFactory();
			EJBProjectLoadStrategyImpl ls = new EJBProjectLoadStrategyImpl(project);
			if (isExportSource())
				ls.setExportSource(true);

			moduleFile = caf.openEJBJarFile(ls, getDestinationPath().toOSString());
			// moduleFile.setSaveFilter(getFilter());
			worked(1);

		} catch (Exception e) {
			throw new SaveFailureException(EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_OpeningArchive"), e); //$NON-NLS-1$
		}

	}

	public void export() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException {
		startProgressMonitor();
		exportEJBProject();
	}

	public void exportEJBProject() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException {

		try {
			createModuleFile();
			worked(1);
			mergeDependentJars();
			worked(1);
			getEjbJarFile().saveAsNoReopen(getDestinationPath().toOSString());
			releaseMerger();
			worked(1);
		} catch (SaveFailureException ex) {
			throw ex;
		} catch (Exception e) {
			throw new SaveFailureException(EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_OpeningArchive"), e);//$NON-NLS-1$
		}

	}

	/**
	 * Creates a class loader to be used by the 1.0 dd generator for reflecting java fields;
	 * 
	 * @return an instance of URLClassLoader with the class path of the current project
	 */
	public ClassLoader getAlternateClassLoader() {
		IPath wbPath = J2EEPlugin.getInstallLocation();
		IJavaProject p = getJavaProject();
		IClasspathEntry[] cp = null;
		try {
			cp = p.getResolvedClasspath(true);
		} catch (Exception ex) {
			//Real hack for now; On the very first try, it bombs
			//On subsequent tries, it works
			try {
				cp = p.getResolvedClasspath(true);
			} catch (JavaModelException ex2) {
				// Okay; something is really wrong
				com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(ex);
			}
		}
		List paths = new ArrayList();
		try {
			paths.add(getJavaProject().getOutputLocation().addTrailingSeparator());
		} catch (JavaModelException ex) {
			com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(ex);
		}
		for (int i = 0; i < cp.length; i++) {
			if (cp[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				continue;
			}
			paths.add(cp[i].getPath());
		}
		java.net.URL[] pathUrls = new java.net.URL[paths.size()];
		for (int i = 0; i < paths.size(); i++) {
			IPath path = (IPath) paths.get(i);
			if (path.getDevice() == null) {
				path = wbPath.append(path);
			}
			try {
				pathUrls[i] = new java.net.URL("file:///" + path.toString());//$NON-NLS-1$
			} catch (java.net.MalformedURLException ex) {
				com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(ex);
			}
		}
		java.net.URLClassLoader parent = null; //
		// URL entries can be jar or directory.
		// this will use the system class loader as the parent
		// parent - null = bootstrap class loader.
		java.net.URLClassLoader cl = new java.net.URLClassLoader(pathUrls, parent);
		return cl;
	}

	public EJBJarFile getEjbJarFile() {
		return (EJBJarFile) moduleFile;
	}

	protected void startProgressMonitor() {
		if (getProgressMonitor() != null) {
			getProgressMonitor().beginTask(null, 5);
		}
	}

	/**
	 * Set the number of units worked with the ProgressMonitor.
	 */

	public void worked(int units) {
		if (getProgressMonitor() != null)
			getProgressMonitor().worked(units);
	}

	protected void mergeDependentJars() {
		merger = new DependentJarExportMerger(getModuleFile(), getProject(), isExportSource());
		merger.merge();
	}

	protected void releaseMerger() {
		if (merger != null)
			merger.release();
	}
}