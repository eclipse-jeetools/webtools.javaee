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
package org.eclipse.jst.j2ee.defect.tests;

import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

import junit.framework.TestCase;

public class DefectTests extends TestCase {

	public static String fileSep = System.getProperty("file.separator");

	public DefectTests() {
		super("Defect Tests");
	}

	public static String getFullTestDataPath(String dataPath) {
		try {
			return ProjectUtility.getFullFileName(HeadlessTestsPlugin.getDefault(), dataPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void test10041() {
		String jarName = "TestData" + fileSep + "DefectTests" + fileSep + "EJB10041NoClient.jar";
	}


}
