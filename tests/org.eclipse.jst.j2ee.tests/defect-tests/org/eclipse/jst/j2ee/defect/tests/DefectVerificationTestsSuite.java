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

import org.eclipse.wst.common.tests.SimpleTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class DefectVerificationTestsSuite extends TestSuite {

	public static Test suite() {
		return new DefectVerificationTestsSuite();
	}

	public DefectVerificationTestsSuite() {
		super("Defect Verification Tests");
		addTest(new SimpleTestSuite(DefectVerificationTests.class));
	}

}
