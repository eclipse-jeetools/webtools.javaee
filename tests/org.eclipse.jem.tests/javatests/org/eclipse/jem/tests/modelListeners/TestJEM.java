/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: TestJEM.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 23:00:16 $ 
 */
package org.eclipse.jem.tests.modelListeners;

import org.eclipse.jdt.core.JavaModelException;

import org.eclipse.jem.internal.adapters.jdom.JavaReflectionSynchronizer;
 

/**
 * This is for testing the JEM Reflection listeners
 * @since 1.0.0
 */
public class TestJEM extends TestListeners {
	
	JavaReflectionSynchronizer sync;
	TestJavaJDOMAdapterFactory factory;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		factory = new TestJavaJDOMAdapterFactory();	// Needs to be set first so that super.setUP() calls to special setups will have a factory.
		super.setUp();	// Called first so that any setup won't fire our synchronizer.
		
		// Basic for this is add our special listener. (Don't need full JEM model cluttering things up).		
		factory.setJavaProject(jp);
		sync = factory.getSynchronizer();
		setTester(factory);
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpClose()
	 */
	protected void setUpClose() throws JavaModelException {
		super.setUpClose();
		factory.setTestCases(new int[0], new Object[0]);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpOpen()
	 */
	protected void setUpOpen() {
		super.setUpOpen();
		factory.setTestCases(new int[0], new Object[0]);		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpAddMethodInWorkingCopy()
	 */
	protected void setUpAddMethodInWorkingCopy() throws JavaModelException {
		super.setUpAddMethodInWorkingCopy();
		factory.setTestCases(new int[0], new Object[0]);		
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpSaveFromWorkingCopy()
	 */
	protected void setUpSaveFromWorkingCopy() throws JavaModelException {
		super.setUpSaveFromWorkingCopy();
		factory.setTestCases(new int[] {TestJavaJDOMAdapterFactory.FLUSH_REFLECTION_PLUS_INNER_NO_NOTIFICATION}, new Object[] {"org.eclipse.jem.tests.beaninfo.Test1Class"}); 		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpRevert()
	 */
	protected void setUpRevert() throws JavaModelException {
		super.setUpRevert();
		factory.setTestCases(new int[0], new Object[0]);		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setupDeleteMethodNoWorkingCopy()
	 */
	protected void setupDeleteMethodNoWorkingCopy() throws JavaModelException {
		super.setupDeleteMethodNoWorkingCopy();
		factory.setTestCases(new int[] {TestJavaJDOMAdapterFactory.FLUSH_REFLECTION_PLUS_INNER_NO_NOTIFICATION}, new Object[] {"org.eclipse.jem.tests.beaninfo.Test1Class"});		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setupAddClass()
	 */
	protected void setupAddClass() {
		super.setupAddClass();
		factory.setTestCases(new int[0], new Object[0]);				
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setupDeleteClassWithWorkingCopy()
	 */
	protected void setupDeleteClassWithWorkingCopy() throws JavaModelException {
		super.setupDeleteClassWithWorkingCopy();
		factory.setTestCases(new int[] {TestJavaJDOMAdapterFactory.FLUSH_REFLECTION_PLUS_INNER_NO_NOTIFICATION, TestJavaJDOMAdapterFactory.DISASSOCIATE_SOURCE_PLUS_INNER_NOTIFY}, new Object[] {"org.eclipse.jem.tests.beaninfo.NewClass", new Object[] {"org.eclipse.jem.tests.beaninfo.NewClass", Boolean.FALSE}});		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpDeleteClassNoWorkingCopy()
	 */
	protected void setUpDeleteClassNoWorkingCopy() throws JavaModelException {
		super.setUpDeleteClassNoWorkingCopy();
		factory.setTestCases(new int[] {TestJavaJDOMAdapterFactory.DISASSOCIATE_SOURCE_PLUS_INNER_NOTIFY}, new Object[] {new Object[] {"org.eclipse.jem.tests.beaninfo.NewClass", Boolean.FALSE}});		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpAddPackage()
	 */
	protected void setUpAddPackage() throws JavaModelException {
		super.setUpAddPackage();
		factory.setTestCases(new int[] {TestJavaJDOMAdapterFactory.FLUSH_PACKAGE_NO_NOTIFICATION}, new Object[] {new Object[] {"test", Boolean.TRUE}});		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpDeletePackage()
	 */
	protected void setUpDeletePackage() throws JavaModelException {
		super.setUpDeletePackage();
		factory.setTestCases(new int[] {TestJavaJDOMAdapterFactory.FLUSH_PACKAGE}, new Object[] {new Object[] {"test", Boolean.FALSE}});
	}
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		sync.stopSynchronizer();
		super.tearDown();
	}
}
