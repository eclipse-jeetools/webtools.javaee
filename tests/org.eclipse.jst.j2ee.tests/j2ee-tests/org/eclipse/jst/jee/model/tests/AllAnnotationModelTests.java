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
package org.eclipse.jst.jee.model.tests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.eclipse.jst.jee.model.ejb.tests.DeleteProjectTest;
import org.eclipse.jst.jee.model.ejb.tests.EJBModelWithClientProjectTest;
import org.eclipse.jst.jee.model.ejb.tests.EjbAnnotationFactoryTest;
import org.eclipse.jst.jee.model.ejb.tests.EjbAnnotationReaderTest;
import org.eclipse.jst.jee.model.ejb.tests.EjbReferenceTest;
import org.eclipse.jst.jee.model.ejb.tests.LifecycleAnnotationsTest;
import org.eclipse.jst.jee.model.ejb.tests.NotifyCloseProjectTest;
import org.eclipse.jst.jee.model.ejb.tests.ResourceReferenceTest;
import org.eclipse.jst.jee.model.ejb.tests.SecurityRolesTest;
import org.eclipse.jst.jee.model.web.tests.DeleteWebProjectTest;
import org.eclipse.jst.jee.model.web.tests.Web25MergedModelProviderTest;
import org.eclipse.jst.jee.model.web.tests.WebAnnotationReaderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
@SuiteClasses(value = { EjbAnnotationReaderTest.class, EJBModelWithClientProjectTest.class, EjbReferenceTest.class,
		LifecycleAnnotationsTest.class, ResourceReferenceTest.class, SecurityRolesTest.class, DeleteProjectTest.class,
		ManyToOneRelationTest.class, RegisterMergedModelProviderTest.class, EjbAnnotationFactoryTest.class,
		WebAnnotationReaderTest.class, DeleteWebProjectTest.class, NotifyCloseProjectTest.class,
		Web25MergedModelProviderTest.class })
@RunWith(Suite.class)
public class AllAnnotationModelTests {

	public static Test suite() {
		return new JUnit4TestAdapter(AllAnnotationModelTests.class);
	}
}
