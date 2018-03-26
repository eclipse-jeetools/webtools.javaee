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
package org.eclipse.jst.j2ee.flexible.project.fvtests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class FVTestSuite extends TestSuite {

	public static Test suite() {
		return new FVTestSuite();
	}

	public FVTestSuite() {
		super("All FV Tests");
		addTestSuite(ArchiveComponentCreationTest.class );
		//addTestSuite(EJBArtifactEditFvTest.class ); // empty class
		addTestSuite(EJBDeployTest.class );
		addTestSuite(J2EEFlexProjDeployablePerfTest.class );
		addTestSuite(PortedComponentCreationTest.class );
		addTestSuite(WebDeployTest.class );
	}

}
