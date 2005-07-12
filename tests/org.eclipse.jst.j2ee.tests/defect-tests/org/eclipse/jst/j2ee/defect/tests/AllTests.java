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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.wst.common.tests.SimpleTestSuite;

public class AllTests extends TestSuite {

	public static Test suite(){
        return new AllTests();
    }
    
    public AllTests(){
        super("J2EE Defect Tests");
        addTest(new SimpleTestSuite(DefectTests.class));
    }
}
