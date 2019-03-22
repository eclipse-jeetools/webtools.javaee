/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.web.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Dimitar Giormov
 * 
 */
public class WebModelSuite {

    public static Test suite() {
    	TestSuite suite = new TestSuite(WebModelSuite.class.getName());
    	suite.addTestSuite(DeleteWebProjectTest.class );
    	suite.addTestSuite(Web25MergedModelProviderTest.class );
    	suite.addTestSuite(Web3AnnotationReaderTest.class );
    	suite.addTestSuite(WebAnnotationReaderTest.class );
    	//suite.addTestSuite(TestWebXmlModelAfterUpdate.class);
        return suite;
    }
}
