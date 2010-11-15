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

public class EnterpriseBVT extends TestSuite {

	public EnterpriseBVT(){
		super();
		addTest(org.eclipse.wtp.j2ee.headless.tests.jca.operations.AllTests.suite());
		addTest(org.eclipse.wtp.j2ee.headless.tests.ear.operations.AllTests.suite());
		addTest(org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AllTests.suite());
		addTest(org.eclipse.wtp.j2ee.headless.tests.utility.operations.AllTests.suite());
	}
	
    public static Test suite(){
    	return new EnterpriseBVT();
    }
}
