/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: TestBeanInfo.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:58:55 $ 
 */
package org.eclipse.jem.tests.modelListeners;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.JavaModelException;

import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoModelSynchronizer;
import org.eclipse.jem.internal.beaninfo.core.IBeaninfoSupplier;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;
 

/**
 * This is for testing the JEM Reflection listeners
 * @since 1.0.0
 */
public class TestBeanInfo extends TestListeners {
	
	BeaninfoModelSynchronizer sync;
	TestBeaninfoAdapterFactory factory;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		factory = new TestBeaninfoAdapterFactory(new IBeaninfoSupplier() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jem.internal.beaninfo.core.IBeaninfoSupplier#getRegistry()
			 */
			public ProxyFactoryRegistry getRegistry() {
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jem.internal.beaninfo.core.IBeaninfoSupplier#isRegistryCreated()
			 */
			public boolean isRegistryCreated() {
				return false;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jem.internal.beaninfo.core.IBeaninfoSupplier#closeRegistry()
			 */
			public void closeRegistry() {
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jem.internal.beaninfo.core.IBeaninfoSupplier#getProject()
			 */
			public IProject getProject() {
				return jp.getProject();
			}
		});	// Needs to be set first so that super.setUP() calls to special setups will have a factory.
		super.setUp();	// Called first so that any setup won't fire our synchronizer.
		
		// Basic for this is add our special listener. (Don't need full JEM model cluttering things up).		
		sync = new BeaninfoModelSynchronizer(factory, jp);
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
		factory.setTestCases(new int[] {TestBeaninfoAdapterFactory.MARK_STALE_INTROSPECTION_PLUS_INNER}, new Object[] {new Object[] {"org.eclipse.jem.tests.beaninfo.Test1Class", Boolean.FALSE}}); 		
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
		factory.setTestCases(new int[] {TestBeaninfoAdapterFactory.MARK_STALE_INTROSPECTION_PLUS_INNER}, new Object[] {new Object[] {"org.eclipse.jem.tests.beaninfo.Test1Class", Boolean.FALSE}});		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setupAddClass()
	 */
	protected void setupAddClass() {
		super.setupAddClass();
		factory.setTestCases(new int[] {TestBeaninfoAdapterFactory.MARK_STALE_INTROSPECTION_PLUS_INNER}, new Object[] {new Object[] {"org.eclipse.jem.tests.beaninfo.NewClass", Boolean.TRUE}});				
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setupDeleteClassWithWorkingCopy()
	 */
	protected void setupDeleteClassWithWorkingCopy() throws JavaModelException {
		super.setupDeleteClassWithWorkingCopy();
		factory.setTestCases(new int[] {TestBeaninfoAdapterFactory.MARK_STALE_INTROSPECTION_PLUS_INNER, TestBeaninfoAdapterFactory.MARK_STALE_INTROSPECTION_PLUS_INNER}, new Object[] {new Object[] {"org.eclipse.jem.tests.beaninfo.NewClass", Boolean.FALSE}, new Object[] {"org.eclipse.jem.tests.beaninfo.NewClass", Boolean.TRUE}});		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpDeleteClassNoWorkingCopy()
	 */
	protected void setUpDeleteClassNoWorkingCopy() throws JavaModelException {
		super.setUpDeleteClassNoWorkingCopy();
		factory.setTestCases(new int[] {TestBeaninfoAdapterFactory.MARK_STALE_INTROSPECTION_PLUS_INNER}, new Object[] {new Object[] {"org.eclipse.jem.tests.beaninfo.NewClass", Boolean.TRUE}});		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpAddPackage()
	 */
	protected void setUpAddPackage() throws JavaModelException {
		super.setUpAddPackage();
		factory.setTestCases(new int[0], new Object[0]);		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.modelListeners.TestListeners#setUpDeletePackage()
	 */
	protected void setUpDeletePackage() throws JavaModelException {
		super.setUpDeletePackage();
		factory.setTestCases(new int[] {TestBeaninfoAdapterFactory.MARK_ALL_STALE}, new Object[] {null});
	}
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		sync.stopSynchronizer(true);
		super.tearDown();
	}
}
