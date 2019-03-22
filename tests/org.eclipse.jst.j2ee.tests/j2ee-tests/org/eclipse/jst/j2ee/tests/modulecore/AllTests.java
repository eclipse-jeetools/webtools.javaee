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
package org.eclipse.jst.j2ee.tests.modulecore;


import org.eclipse.jst.j2ee.project.facet.tests.EjbProjectFacetCreationTest;
import org.eclipse.jst.j2ee.project.facet.tests.ProjectFacetCreationTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {

    public static Test suite(){
        return new AllTests();
    }
    
    public AllTests(){
        super("ModuleCore Tests");
        addTest(ProjectFacetCreationTest.suite());
        addTest(EjbProjectFacetCreationTest.suite());
        //addTest(FlexibleProjectBuilderTest.suite());
        //addTest(ModuleStructuralModelTest.suite());
    }
}
