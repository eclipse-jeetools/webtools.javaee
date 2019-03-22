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
package org.eclipse.jst.jee.model.ejb.tests;


import junit.framework.Test;
import junit.framework.TestSuite;

public class EJBModelSuite extends TestSuite {
	
    public static Test suite(){
        return new EJBModelSuite();
    }
    
    public EJBModelSuite(){
        super("EJB Model Tests");

        //addTestSuite(CreateModelFromXmlTest.class );
        addTestSuite(DeleteProjectTest.class );
        addTestSuite(EJB3MergedModelProviderFactoryTest.class );
        //addTestSuite(EJB3MergedModelProviderTest.class );
        addTestSuite(Ejb3ModelProviderTest.class );
        addTestSuite(EjbAnnotationFactoryTest.class );
        //addTestSuite(EjbAnnotationReaderTest.class );
        addTestSuite(EJBAnnotationReaderWithClientTest.class );
        //addTestSuite(EjbReferenceTest.class );
        addTestSuite(GenerateDDTest.class );
        //addTestSuite(LifecycleAnnotationsTest.class );
        addTestSuite(NotifyCloseProjectTest.class );
        //addTestSuite(ResourceReferenceTest.class );
        addTestSuite(SecurityRolesTest.class );
    }
}
