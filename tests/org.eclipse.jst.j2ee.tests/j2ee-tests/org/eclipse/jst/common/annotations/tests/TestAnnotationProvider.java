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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jst.common.internal.annotations.core.IAnnotationsProvider;

public class TestAnnotationProvider implements IAnnotationsProvider {

	public TestAnnotationProvider() {
		super();
	}

	public boolean isAnnotated(EObject eObject) {
		return false;
	}

	public ICompilationUnit getPrimaryAnnotatedCompilationUnit(EObject eObject) {
		return null;
	}

	public String getPrimaryTagset(EObject eObject) {
		return null;
	}

}
