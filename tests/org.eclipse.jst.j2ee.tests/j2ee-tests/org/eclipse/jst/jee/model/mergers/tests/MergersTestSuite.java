/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.mergers.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Dimitar Giormov
 * 
 */
//@SuiteClasses({ EjbJarMergerTest.class, SessionMergerTest.class, MdbMergerTest.class, AssemblyDescriptorMergerTest.class, JndiRefsTest.class, WebAppMergerTest.class })
//@RunWith(Suite.class)
public class MergersTestSuite {

    public static Test suite() {
    	TestSuite suite = new TestSuite(MergersTestSuite.class.getName());
    	suite.addTestSuite(EjbJarMergerTest.class);
    	suite.addTestSuite(SessionMergerTest.class);
    	suite.addTestSuite(MdbMergerTest.class);
    	suite.addTestSuite(AssemblyDescriptorMergerTest.class);
    	suite.addTestSuite(JndiRefsTest.class);
    	suite.addTestSuite(WebAppMergerTest.class);
    	suite.addTestSuite(WebApp3MergerTest.class);
        return suite;
    }
}
