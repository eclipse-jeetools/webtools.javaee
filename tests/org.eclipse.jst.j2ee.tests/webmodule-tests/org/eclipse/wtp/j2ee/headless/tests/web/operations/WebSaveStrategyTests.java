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
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

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
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.internal.web.archive.operations.FlexibleJ2EEWebSaveStrategyImpl;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public class WebSaveStrategyTests extends TestCase {
	
	public static String fileSep = System.getProperty("file.separator");

	private String projectName = "WarImportFlexProject";
	private IProject project;

	public WebSaveStrategyTests(String name) {
		super(name);
	}

	public boolean createProject() {
		IPath localZipPath = getLocalPath();
		ProjectUnzipUtil util = new ProjectUnzipUtil(localZipPath, new String[]{projectName});
		return util.createProjects();
	}

	private IPath getLocalPath() {
		String file = "TestData" + fileSep + "WARImportTests" + fileSep + "WarImportFlexProject.zip";
		//fielString uri = getFullTestDataPath(file);
		IPath zipFilePath = new Path(file);
		
		URL url = HeadlessTestsPlugin.getDefault().find(zipFilePath);
		try {
			url = Platform.asLocalURL(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Path(url.getPath());
	}

	public void setUp() {
		try {
			ProjectUtility.deleteAllProjects();
			project = null;
			if (createProject()) {
				project = ProjectUtilities.getProject(projectName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected final ArchiveOptions getArchiveOptions() {
		ArchiveOptions opts = new ArchiveOptions();
		opts.setIsReadOnly(true);
		return opts;
	}

	// public void testImportExample1() throws Exception {
	// setup();
	// importWar("Example1.war");
	// }
	//
	// public void testImportTest12Web() throws Exception {
	// setup();
	// importWar("Test12Web.war");
	// }
	//
	// public void testImportTest13Web() throws Exception {
	// setup();
	// importWar("Test13Web.war");
	// }
	//
	// public void testImportTest14Web() throws Exception {
	// setup();
	// importWar("Test14Web.war");
	// }

	public void testSource() throws Exception {
		importWar("WebSource.war");
	}

	public void testSomeSource() throws Exception {
		importWar("WebSomeSource.war");
	}

	public void testNoSource() throws Exception {
		importWar("WebNoSource.war");
	}

//	public void testAuctionV60WebSource() throws Exception {
//		importWar("AuctionV60WebSource.war");
//	}
//
//	public void testAuctionV60WebNoSource() throws Exception {
//		importWar("AuctionV60WebNoSource.war");
//	}

	protected static String getFullTestDataPath(String dataPath) {
    	try {
    	  return ProjectUtility.getFullFileName(HeadlessTestsPlugin.getDefault(),dataPath);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    }
	 
	private String getUri(String warName) {
		String file = "TestData" + fileSep + "WARImportTests" + fileSep + warName;
		String uri = getFullTestDataPath(file);
		return uri;
	}
	private void importWar(String warName) throws Exception {
		    
		String uri = getUri(warName);
		Archive moduleFile = null;
		IFlexibleProject flexProject = ComponentCore.createFlexibleProject(project);
		IVirtualComponent[] vComps = flexProject.getComponents();
		for (int i = 0; i < vComps.length; i++) {
			try {
				moduleFile = CommonarchiveFactory.eINSTANCE.openWARFile(getArchiveOptions(), uri);
				FlexibleJ2EEWebSaveStrategyImpl aStrategy = new FlexibleJ2EEWebSaveStrategyImpl(vComps[i]);
				aStrategy.setProgressMonitor(new NullProgressMonitor());
				moduleFile.save(aStrategy);
			} finally {
				if (null != moduleFile) {
					moduleFile.close();
				}
			}
		}
	}

}
