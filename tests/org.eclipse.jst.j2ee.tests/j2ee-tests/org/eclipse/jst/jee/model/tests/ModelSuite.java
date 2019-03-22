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
package org.eclipse.jst.jee.model.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Dimitar Giormov
 * 
 */
public class ModelSuite {

    public static Test suite() {
    	TestSuite suite = new TestSuite(ModelSuite.class.getName());
    	suite.addTestSuite(JEE5LegacyModelTest.class );
    	suite.addTestSuite(JEE5ModelTest.class );
    	suite.addTestSuite(JEE6ModelTest.class );
    	suite.addTestSuite(ManyToOneRelationTest.class );
    	suite.addTestSuite(ModelProviderTest.class );
    	suite.addTestSuite(RegisterMergedModelProviderTest.class );
        return suite;
    }
}
