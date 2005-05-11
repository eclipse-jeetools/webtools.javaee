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
package org.eclipse.wtp.headless.tests.savestrategy;

import java.io.IOException;
import java.net.URL;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.etools.common.test.apitools.ProjectUnzipUtil;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.internal.archive.operations.FlexibleSaveStrategyImpl;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public abstract class SaveStrategyTest extends TestCase {
	public static String fileSep = System.getProperty("file.separator");

	protected String projectName;
	protected IProject project;

	protected IVirtualComponent[] vComps;

	public SaveStrategyTest() {
		super();
		projectName = getProjectName();
	}

	public void setUp() {
		try {
			ProjectUtility.deleteAllProjects();
			project = null;
			if (createProject()) {
				project = ProjectUtilities.getProject(projectName);
			}
			IFlexibleProject flexProject = ComponentCore.createFlexibleProject(project);
			vComps = flexProject.getComponents();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean createProject() {
		IPath localZipPath = getFlexProjectSeed();
		ProjectUnzipUtil util = new ProjectUnzipUtil(localZipPath, new String[]{projectName});
		return util.createProjects();
	}

	private IPath getFlexProjectSeed() {
		String file = "TestData" + fileSep + "SaveStrategyTests" + fileSep + getFlexProjectSeedName();
		IPath zipFilePath = new Path(file);

		URL url = HeadlessTestsPlugin.getDefault().find(zipFilePath);
		try {
			url = Platform.asLocalURL(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Path(url.getPath());
	}

	protected void importArchive(String archiveName) throws Exception {

		String uri = getUri(archiveName);
		Archive moduleFile = null;
		for (int i = 0; i < vComps.length; i++) {
			try {
				moduleFile = openModuleFile(getArchiveOptions(), uri);
				FlexibleSaveStrategyImpl aStrategy = createSaveStrategy(vComps[i]);
				aStrategy.setProgressMonitor(new NullProgressMonitor());
				moduleFile.save(aStrategy);
			} finally {
				if (null != moduleFile) {
					moduleFile.close();
				}
			}
		}
	}

	protected abstract FlexibleSaveStrategyImpl createSaveStrategy(IVirtualComponent component);

	protected abstract Archive openModuleFile(ArchiveOptions archiveOptions, String uri) throws OpenFailureException;

	protected String getUri(String warName) {
		String file = getRootArchiveFolderName() + fileSep + warName;
		String uri = getFullTestDataPath(file);
		return uri;
	}

	protected static String getFullTestDataPath(String dataPath) {
		try {
			return ProjectUtility.getFullFileName(HeadlessTestsPlugin.getDefault(), dataPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	protected final ArchiveOptions getArchiveOptions() {
		ArchiveOptions opts = new ArchiveOptions();
		opts.setIsReadOnly(true);
		return opts;
	}

	public abstract String getProjectName();

	public abstract String getFlexProjectSeedName();

	public abstract String getRootArchiveFolderName();

}
