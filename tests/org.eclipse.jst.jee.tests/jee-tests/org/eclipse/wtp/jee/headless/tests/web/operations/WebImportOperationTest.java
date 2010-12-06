/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/


package org.eclipse.wtp.jee.headless.tests.web.operations;

import junit.framework.Test;

import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebImportOperationBaseTest;

public class WebImportOperationTest extends WebImportOperationBaseTest {

	public WebImportOperationTest() {
		super("WebImportOperationTests");
	}
	
	public WebImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(WebImportOperationTest.class);
	}
	
	public void testWebImport25_Defaults() throws Exception {
		runImportTests_All("Web25_Defaults");
	}	
	
	public void testWebImport25_DiffContentDir() throws Exception {
		runImportTests_All("Web25_DiffContentDir");
	}
		
	public void testWebImport25_DiffSrcDir() throws Exception {
		runImportTests_All("Web25_DiffSrcDir");
	}

	public void testWebImport25_DiffContentDir_DiffSrcDir() throws Exception {
		runImportTests_All("Web25_DiffContentDir_DiffSrcDir");
	}
	
	public void testWebImport25_Defaults_WithEAR() throws Exception {
		runImportTests_All("Web25_Defaults_WithEAR");
	}
	
	public void testWebImport25_DiffContextRoot_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_WithEAR");
	}

	public void testWebImport25_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContentDir_WithEAR");
	}
	
	public void testWebImport25_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport25_DiffContextRoot_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_WithEAR");
	}
	
	public void testWebImport25_DiffContextRoot_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport25_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContentDir_DiffSrcDir_WithEAR");
	}

	public void testWebImport25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR");
	}
}
