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

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.internal.archive.operations.ComponentSaveStrategyImpl;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentSaveStrategyImpl;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;


public class WebSaveStrategyTests extends SaveStrategyTest {


	public WebSaveStrategyTests() {
		super();
	}

	public void testSource() throws Exception {
		importArchive("WebSource.war");
	}

	public void testSomeSource() throws Exception {
		importArchive("WebSomeSource.war");
	}

	public void testNoSource() throws Exception {
		importArchive("WebNoSource.war");
	}

	public String getRootArchiveFolderName() {
		return "TestData" + fileSep + "WARImportTests";
	}

	public String getFlexProjectSeedName() {
		return "WarImportFlexProject.zip";
	}

	public String getProjectName() {
		return "WarImportFlexProject";
	}

	protected ComponentSaveStrategyImpl createSaveStrategy(IVirtualComponent component) {
		return new WebComponentSaveStrategyImpl(component);
	}

	protected Archive openModuleFile(ArchiveOptions archiveOptions, String uri) throws OpenFailureException {
		return CommonarchiveFactory.eINSTANCE.openWARFile(archiveOptions, uri);
	}


}
