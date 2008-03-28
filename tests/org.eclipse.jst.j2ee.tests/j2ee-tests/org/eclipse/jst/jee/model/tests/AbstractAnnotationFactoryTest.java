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

import junit.framework.TestCase;

import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.jst.jee.model.internal.EjbAnnotationFactory;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class AbstractAnnotationFactoryTest extends TestCase{

	protected EjbAnnotationFactory fixture;

//	@Before
	@Override
	protected void setUp() throws Exception {
		fixture = EjbAnnotationFactory.createFactory();
	}

//	@After
	@Override
	protected void tearDown() {
	}

	protected ICompilationUnit createCompilationUnit(final String name, final String content) throws JavaModelException {
		WorkingCopyOwner owner = new WorkingCopyOwner() {
			@Override
			public IBuffer createBuffer(ICompilationUnit workingCopy) {
				IBuffer buffer = super.createBuffer(workingCopy);
				buffer.setContents(content);
				return buffer;
			}
		};
		return owner.newWorkingCopy(name, new IClasspathEntry[0], null);
	}

}
