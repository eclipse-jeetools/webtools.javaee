/*
 * Created on Sep 3, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jem.tests.instantiation;
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

import java.io.*;
import java.util.Collections;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoNature;
import org.eclipse.jem.internal.instantiation.InitStringAllocation;
import org.eclipse.jem.internal.instantiation.InstantiationFactory;
import org.eclipse.jem.internal.instantiation.base.IJavaDataTypeInstance;
import org.eclipse.jem.internal.instantiation.base.IJavaObjectInstance;
import org.eclipse.jem.java.*;
import org.eclipse.jem.tests.JavaProjectUtil;
import org.eclipse.jem.tests.beaninfo.AbstractBeanInfoTestCase;

/**
 * @author richkulp
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestStandard extends TestCase {

	/**
	 * 
	 */
	public TestStandard() {
		super();
	}

	/**
	 * @param name
	 */
	public TestStandard(String name) {
		super(name);
	}

	protected BeaninfoNature nature;
	protected ResourceSet rset;
	protected JavaHelpers boolType;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		IProject biProject = JavaProjectUtil.getProject(AbstractBeanInfoTestCase.TEST_BEANINFO_PROJECT);
		assertNotNull(biProject);
		nature = BeaninfoNature.getRuntime(biProject);
		assertNotNull(nature);
		rset = nature.getResourceSet();
		assertNotNull(rset);
		boolType = JavaRefFactory.eINSTANCE.reflectType("boolean", rset);
		assertNotNull(boolType);
	}

	protected JavaClass getTest1Class() {
		return (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jem.tests.beaninfo.Test1Class", rset); //$NON-NLS-1$
	}

	public void testInit() {
		JavaClass test1Class = getTest1Class();

		// Test instantiate an instance.
		IJavaObjectInstance ji1 = (IJavaObjectInstance) test1Class.getEPackage().getEFactoryInstance().create(test1Class);
		ji1.setAllocation(InstantiationFactory.eINSTANCE.createInitStringAllocation("new Test1Class()")); //$NON-NLS-1$
		assertInit(ji1);
	}

	private void assertInit(IJavaObjectInstance ji1) {
		// Here because two tests will do same test sequence.
		assertTrue(ji1.isSetAllocation());
		InitStringAllocation alloc = (InitStringAllocation) ji1.getAllocation();
		assertEquals("new Test1Class()", ((InitStringAllocation) alloc).getInitString()); //$NON-NLS-1$
	}

	public void testPropertyAccess() {
		JavaClass test1Class = getTest1Class();
		IJavaObjectInstance ji1 = (IJavaObjectInstance) test1Class.getEPackage().getEFactoryInstance().create(test1Class);
		ji1.setAllocation(InstantiationFactory.eINSTANCE.createInitStringAllocation("new Test1Class()")); //$NON-NLS-1$

		// Test property set/get of a dynamic property.
		EStructuralFeature setSF = test1Class.getEStructuralFeature("set");
		assertNotNull(setSF);
		assertSame(boolType, setSF.getEType());
		IJavaDataTypeInstance setting = (IJavaDataTypeInstance) setSF.getEType().getEPackage().getEFactoryInstance().create((EClass) setSF.getEType());
		setting.setAllocation(InstantiationFactory.eINSTANCE.createInitStringAllocation("true")); //$NON-NLS-1$
		ji1.eSet(
			setSF,
			setting);
		assertProperty(ji1);
	}

	private void assertProperty(IJavaObjectInstance ji1) {
		// Here because two tests will do same test sequence.
		EStructuralFeature setSF = ji1.eClass().getEStructuralFeature("set");
		Object v = ji1.eGet(setSF);
		assertNotNull(v);
		assertTrue(v instanceof IJavaDataTypeInstance);
		IJavaDataTypeInstance dtv = (IJavaDataTypeInstance) v;
		assertSame(boolType, dtv.getJavaType());
		assertTrue(dtv.isSetAllocation());
		InitStringAllocation alloc = (InitStringAllocation) dtv.getAllocation();
		assertEquals("true", ((InitStringAllocation) alloc).getInitString());
	}

	protected IJavaObjectInstance setupInstance() {
		// Setup the standard test instance without the assertions. For use in other tests.
		JavaClass test1Class = getTest1Class();
		IJavaObjectInstance ji1 = (IJavaObjectInstance) test1Class.getEPackage().getEFactoryInstance().create(test1Class);
		ji1.setAllocation(InstantiationFactory.eINSTANCE.createInitStringAllocation("new Test1Class()")); //$NON-NLS-1$

		// Test property set/get of a dynamic property.
		EStructuralFeature setSF = test1Class.getEStructuralFeature("set");
		IJavaDataTypeInstance setting = (IJavaDataTypeInstance) setSF.getEType().getEPackage().getEFactoryInstance().create((EClass) setSF.getEType());
		setting.setAllocation(InstantiationFactory.eINSTANCE.createInitStringAllocation("true")); //$NON-NLS-1$
		ji1.eSet(
			setSF,
			setting);
		return ji1;
	}

	protected String setupResource() throws IOException {
		IJavaObjectInstance ji1 = setupInstance();

		// Write it out, see if it is what it should be, then read it in and see if it loads correctly.
		ResourceSet rswork1 = nature.newResourceSet(); // Rsource set to write from

		Resource r = rswork1.createResource(URI.createURI("f.xmi")); //$NON-NLS-1$
		r.getContents().add(ji1);
		// Bit of a kludge, but all references in java model are shared, so to serialize we need to get the 
		// "set" setting and add to the resource so that it is contained somewhere.
		r.getContents().add(ji1.eGet(ji1.eClass().getEStructuralFeature("set")));

		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		r.save(bo, Collections.EMPTY_MAP);
		return bo.toString();
	}

	public void testSerialization() throws IOException {
		// Test the serialization that it produces correct output string.
		String out = setupResource();
		// Need to create a print stream so that we get the correct local newline chars in to match from the resource.
		StringWriter sw = new StringWriter(out.length() + 50);
		PrintWriter pw = new PrintWriter(sw);
		pw.println("<?xml version=\"1.0\" encoding=\"ASCII\"?>");
		pw.println(
			"<xmi:XMI xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:_-javaprim=\"java:/\" xmlns:org.eclipse.jem.internal.instantiation=\"http:///org/eclipse/jem/internal/instantiation.ecore\" xmlns:org.eclipse.jem.tests.beaninfo=\"java:/org.eclipse.jem.tests.beaninfo\">");
		pw.println("  <org.eclipse.jem.tests.beaninfo:Test1Class set=\"/1\">");
		pw.println("    <allocation xsi:type=\"org.eclipse.jem.internal.instantiation:InitStringAllocation\" initString=\"new Test1Class()\"/>");
		pw.println("  </org.eclipse.jem.tests.beaninfo:Test1Class>");
		pw.println("  <_-javaprim:boolean>");
		pw.println("    <allocation xsi:type=\"org.eclipse.jem.internal.instantiation:InitStringAllocation\" initString=\"true\"/>");
		pw.println("  </_-javaprim:boolean>");
		pw.println("</xmi:XMI>");
		pw.close();
		assertEquals(sw.toString(), out);
	}
		
	public void testReading() throws IOException {
		// Test reading serialization back in produces correct objects.
		// Rerun tests.
		String out = setupResource();

		ResourceSet rswork2 = nature.newResourceSet(); // Resource set to read into
		Resource rIn = rswork2.createResource(URI.createURI("f1.xmi")); //$NON-NLS-1$
		rIn.load(new ByteArrayInputStream(out.getBytes()), Collections.EMPTY_MAP);
		JavaClass test1Class = getTest1Class();
		IJavaObjectInstance ji1 = (IJavaObjectInstance) EcoreUtil.getObjectByType(rIn.getContents(), test1Class);
		assertNotNull(ji1);
		assertInit(ji1);
		assertProperty(ji1);
	}

}
