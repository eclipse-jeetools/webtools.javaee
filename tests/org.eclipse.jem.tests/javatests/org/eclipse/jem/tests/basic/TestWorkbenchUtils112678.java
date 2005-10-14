/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: TestWorkbenchUtils112678.java,v $
 *  $Revision: 1.1 $  $Date: 2005/10/14 20:57:30 $ 
 */
package org.eclipse.jem.tests.basic;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
 

/**
 * Test bug:  https://bugs.eclipse.org/bugs/show_bug.cgi?id=112678
 * @since 1.2.0
 */
public class TestWorkbenchUtils112678 extends TestCase {

	/**
	 * 
	 * 
	 * @since 1.2.0
	 */
	public TestWorkbenchUtils112678() {
		super();
	}

	/**
	 * @param name
	 * 
	 * @since 1.2.0
	 */
	public TestWorkbenchUtils112678(String name) {
		super(name);
	}
	
	public static final String FILE_RESOURCE_PATH = "Test Basic/workbenchUtil/FileResource.xmi";
	public static final String NOT_FOUND_FILE_RESOURCE_PATH = "Test Basic/workbenchUtil/NoFileResource.xmi";
	
	/**
	 * Test {@link WorkbenchResourceHelperBase#getIFile(URI)} with platform resource uri.
	 * 
	 * 
	 * @since 1.2.0
	 */
	public void testGetIFilePlatformResource() {
		URI uri = URI.createPlatformResourceURI(FILE_RESOURCE_PATH);
		IFile file = WorkbenchResourceHelperBase.getIFile(uri);
		assertNotNull(file);
		assertTrue(file.exists());

		uri = URI.createPlatformResourceURI(NOT_FOUND_FILE_RESOURCE_PATH);
		file = WorkbenchResourceHelperBase.getIFile(uri);
		assertNotNull(file);
		assertFalse(file.exists());
		
		// Test a relative form that is not a valid project.
		uri = URI.createPlatformResourceURI("NotProject/file.xmi"); 	
		file = WorkbenchResourceHelperBase.getIFile(uri);
		assertNull(file);		
	}
	
	/**
	 * Test {@link WorkbenchResourceHelperBase#getIFile(URI)} with a relative uri (e.g. "project/file").
	 * 
	 * 
	 * @since 1.2.0
	 */
	public void testGetIFileProjectRelativeResource() {
		URI uri = URI.createURI(FILE_RESOURCE_PATH);
		IFile file = WorkbenchResourceHelperBase.getIFile(uri);
		assertNotNull(file);
		assertTrue(file.exists());

		uri = URI.createURI(NOT_FOUND_FILE_RESOURCE_PATH);
		file = WorkbenchResourceHelperBase.getIFile(uri);
		assertNotNull(file);
		assertFalse(file.exists());
		
		// Test a relative form that is not a valid project.
		uri = URI.createURI("NotProject/file.xmi"); 	
		file = WorkbenchResourceHelperBase.getIFile(uri);
		assertNull(file);
		
	}
	
	/**
	 * Test {@link WorkbenchResourceHelperBase#getIFile(URI)} with a non-platform resource uri.
	 * 
	 * 
	 * @since 1.2.0
	 */
	public void testGetIFileNotPlatformResource() {
		URI uri = URI.createURI("platform:/plugin/xyz/abc");
		IFile file = WorkbenchResourceHelperBase.getIFile(uri);
		assertNull(file);
	}
	
	/**
	 * Test {@link WorkbenchResourceHelperBase#getResource(URI)} with a platform resource uri.
	 * 
	 * 
	 * @since 1.2.0
	 */
	public void testGetResourcePlatformResource() {
		URI uri = URI.createPlatformResourceURI(FILE_RESOURCE_PATH);
		Resource res = WorkbenchResourceHelperBase.getResource(uri);
		assertNotNull(res);
		assertTrue(res.isLoaded());
		
		uri = URI.createPlatformResourceURI(NOT_FOUND_FILE_RESOURCE_PATH);
		boolean good = false;
		try {
			res = WorkbenchResourceHelperBase.getResource(uri);
		} catch (WrappedException e) {
			good = true;
		}
		assertTrue(good);

		// Test a relative form that is not a valid project.
		uri = URI.createPlatformResourceURI("NotProject/file.xmi"); 	
		res = WorkbenchResourceHelperBase.getResource(uri);
		assertNull(res);
	}
	
	/**
	 * Test {@link WorkbenchResourceHelperBase#getResource(URI)} with a relative uri (e.g. "project/file").
	 * 
	 * 
	 * @since 1.2.0
	 */
	public void testGetResourceProjectRelativeResource() {
		URI uri = URI.createURI(FILE_RESOURCE_PATH);
		Resource res = WorkbenchResourceHelperBase.getResource(uri);
		assertNotNull(res);
		assertTrue(res.isLoaded());
		
		boolean good = false;
		uri = URI.createURI(NOT_FOUND_FILE_RESOURCE_PATH);
		try {
			res = WorkbenchResourceHelperBase.getResource(uri);
		} catch (WrappedException e) {
			good = true;
		}
		assertTrue(good);

		// Test a relative form that is not a valid project.
		uri = URI.createURI("NotProject/file.xmi"); 	
		res = WorkbenchResourceHelperBase.getResource(uri);
		assertNull(res);
	}	

}
