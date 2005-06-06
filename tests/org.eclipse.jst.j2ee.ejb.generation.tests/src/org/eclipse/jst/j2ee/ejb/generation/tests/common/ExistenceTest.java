/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.generation.tests.common;

import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ModelPlugin;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.EjbAnnotationsUiPlugin;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.EjbEmitterPlugin;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletAnnotationPlugin;

import junit.framework.TestCase;

public class ExistenceTest extends TestCase {
	public void testModelPluginExists() {
		assertNotNull(ModelPlugin.getDefault());
	}
	public void testEmitterPluginExists() {
		assertNotNull(EjbEmitterPlugin.getDefault());
	}
	public void testEjbUIPluginExists() {
		assertNotNull(EjbAnnotationsUiPlugin.getDefault());
	}
	public void testXDocletPluginExists() {
		assertNotNull(XDocletAnnotationPlugin.getDefault());
	}
}