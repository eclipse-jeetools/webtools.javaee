package org.eclipse.jem.tests.beaninfo;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: AbstractBeanInfoTestCase.java,v $
 *  $Revision: 1.5 $  $Date: 2004/03/24 15:07:48 $ 
 */
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jem.internal.beaninfo.FeatureDecorator;
import org.eclipse.jem.internal.beaninfo.PropertyDecorator;
import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoNature;
import org.eclipse.jem.internal.beaninfo.core.Utilities;
import org.eclipse.jem.tests.JavaProjectUtil;

import org.eclipse.jem.java.JavaClass;

/**
 * @author richkulp
 *
 */
public abstract class AbstractBeanInfoTestCase extends TestCase {

	public AbstractBeanInfoTestCase() {
		super();
	}

	public AbstractBeanInfoTestCase(String name) {
		super(name);
	}
	
	protected BeaninfoNature nature;
	protected ResourceSet rset;

	public static final String 
		TEST_BEANINFO_PROJECT = "Test BeanInfo",	//$NON-NLS-1$
		TEST_BEANINFO_BEANINFOS_PROJECT = "Test BeanInfo BeanInfos", //$NON-NLS-1$
		TEST_BEANINFO_PREREQ_PROJECT = "Test BeanInfo Prereq"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		IProject biProject = JavaProjectUtil.getProject(TEST_BEANINFO_PROJECT);
		assertNotNull(biProject); 
		nature = BeaninfoNature.getRuntime(biProject);
		assertNotNull(nature);
		rset = nature.getResourceSet();
		assertNotNull(rset);
	}
	
	protected int objFeatures, objNonProperties;	// Object features count and Object non-properties count. This is only initialized as needed.
	/**
	 * To initialize the objFeatures and objNonProperties counts when necessary. Not needed for all tests.
	 */
	protected void objFeaturesSetup() {
		// Get the number of features that java.lang.Object has:
		JavaClass objClass = (JavaClass) rset.getEObject(URI.createURI("java:/java.lang#Object"), true); //$NON-NLS-1$
		objFeatures = objClass.getProperties().size();
		// Find the number of always inherited properties.
		objNonProperties = 0;
		for (Iterator itr0 = objClass.getProperties().iterator(); itr0.hasNext();) {
			EStructuralFeature p = (EStructuralFeature) itr0.next();
			PropertyDecorator pd = Utilities.getPropertyDecorator(p);
			if ( pd == null || (pd.isImplicitlyCreated() == FeatureDecorator.NOT_IMPLICIT && !pd.isMergeIntrospection()))
				objNonProperties++;
		}
	}

}
