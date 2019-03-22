/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 18, 2005
 */
package org.eclipse.jst.jee.model.mergers.tests;


import junit.framework.Test;
import junit.framework.TestSuite;

public class ModelMergersSuite extends TestSuite {
    public static Test suite(){
        return new ModelMergersSuite();
    }
    
    public ModelMergersSuite(){
        super("Model Mergers Tests");
        addTestSuite(AssemblyDescriptorMergerTest.class );
        addTestSuite(EjbJarMergerTest.class );
        addTestSuite(JndiRefsTest.class );
        addTestSuite(MdbMergerTest.class );
        addTestSuite(SessionMergerTest.class );
        addTestSuite(WebApp3MergerTest.class );
        addTestSuite(WebAppMergerTest.class );

    }
}
