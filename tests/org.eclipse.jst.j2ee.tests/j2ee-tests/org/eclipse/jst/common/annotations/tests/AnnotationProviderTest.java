/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.annotations.tests;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;

import org.eclipse.jst.common.internal.annotations.core.AnnotationsProviderManager;
import org.eclipse.jst.common.internal.annotations.core.IAnnotationsProvider;
import org.eclipse.wst.common.tests.SimpleTestSuite;

public class AnnotationProviderTest extends TestCase {

	public AnnotationProviderTest() {
		super();
	}

	public AnnotationProviderTest(String name) {
		super(name);
	}
	
	public static Test suite() {
	    return new SimpleTestSuite(AnnotationProviderTest.class);
	}
	
	public void testAnnotationProviderFramework() throws Exception {
		List providers = AnnotationsProviderManager.INSTANCE.getAnnotationsProviders();
		boolean success = false;
		for (int i=0; i<providers.size(); i++) {
			IAnnotationsProvider provider = (IAnnotationsProvider) providers.get(i);
			provider.isAnnotated(null);
			provider.getPrimaryAnnotatedCompilationUnit(null);
			provider.getPrimaryTagset(null);
			success = true;
		}
		assertTrue(success);
	}

}
