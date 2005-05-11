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
import org.eclipse.jst.j2ee.internal.archive.operations.FlexibleJ2EEAppClientSaveStrategyImpl;
import org.eclipse.jst.j2ee.internal.archive.operations.FlexibleSaveStrategyImpl;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class AppClientSaveStrategyTests extends SaveStrategyTest {

	protected FlexibleSaveStrategyImpl createSaveStrategy(IVirtualComponent component) {
		return new FlexibleJ2EEAppClientSaveStrategyImpl(component);
	}

	protected Archive openModuleFile(ArchiveOptions archiveOptions, String uri) throws OpenFailureException {
		return CommonarchiveFactory.eINSTANCE.openApplicationClientFile(archiveOptions, uri);
	}

	public String getFlexProjectSeedName() {
		return "AppClient.zip";
	}

	public String getProjectName() {
		return "AppClient";
	}

	public String getRootArchiveFolderName() {
		return "TestData" + fileSep + "AppClientTests";
	}


	public void testSource() throws Exception {
		importArchive("AppClientSource.jar");
	}

	public void testSomeSource() throws Exception {
		importArchive("AppClientSomeSource.jar");
	}

	public void testNoSource() throws Exception {
		importArchive("AppClientNoSource.jar");
	}


}
