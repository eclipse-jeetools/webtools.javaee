/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import org.eclipse.jst.j2ee.flexible.project.fvtests.WebDeployTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author jsholl
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AllTests extends TestSuite {
		
    public static Test suite(){
        return new AllTests();
    }
    
    public AllTests(){
        super("Web Operation, Servlet, & Deploy Tests");
       addTest(WebImportOperationTest.suite());
       addTest(WebExportOperationTest.suite());
       addTest(WebProjectCreationOperationTest.suite());
       
       addTest(WebDeployTest.suite());
       addTest(StaticWebProjectCreationOperationTest.suite());
       addTest(AddWebArtifactOperationTest.suite());
       addTest(UrlPatternTest.suite());
       // addTest(WebComponentCreationTest.suite());
		//addTest(new SimpleTestSuite(WebSaveStrategyTests.class));
    }
}
