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

package org.eclipse.jst.j2ee.tests.bvt;

import junit.framework.Test;
import junit.framework.TestSuite;

public class CoreInfrastructureBVT extends TestSuite {
	
	public CoreInfrastructureBVT(){
		super();
		addTest(org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.SourceFolderUpdateTest.suite());
		addTest(org.eclipse.jst.j2ee.tests.modulecore.AllTests.suite());
		addTest(org.eclipse.jst.j2ee.dependency.tests.AllTests.suite());
		addTest(org.eclipse.jst.j2ee.classpath.tests.AllTests.suite());
		addTest(org.eclipse.wtp.j2ee.headless.tests.web.container.WebAppLibrariesContainerTests.suite());
		addTest(org.eclipse.jst.j2ee.defect.tests.DefectVerificationTestsSuite.suite());
		addTest(org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.HeaderParserTests.suite());
	}
	
	public static Test suite(){
    	return new CoreInfrastructureBVT();
    }	
}
