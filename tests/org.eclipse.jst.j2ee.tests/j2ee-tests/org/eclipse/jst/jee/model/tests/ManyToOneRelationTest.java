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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.jee.model.internal.common.ManyToOneRelation;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class ManyToOneRelationTest extends TestCase {

	private ManyToOneRelation<String, Integer> fixture;

	private ManyToOneRelation<String, Integer> getFixture() {
		return fixture;
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(ManyToOneRelationTest.class);
		return suite;
	}

	// @Before
	public void setUp() throws Exception {
		super.setUp();
		fixture = new ManyToOneRelation<String, Integer>();
	}

	
	// @Test
	public void testGetTarget() {
		getFixture().connect("str1", new Integer(1));
		assertEquals(new Integer(1), getFixture().getTarget("str1"));
	}

	public void testConnectToNewTarget() {
		getFixture().connect("str1", new Integer(1));
		getFixture().connect("str1", new Integer(2));
		assertFalse(getFixture().containsTarget(new Integer(1)));
		assertTrue(getFixture().containsTarget(new Integer(2)));
	}

	// @Test
	public void testGetSources() {
		getFixture().connect("str1", new Integer(1));
		getFixture().connect("str2", new Integer(1));
		assertTrue(getFixture().getSources(new Integer(1)).contains("str1"));
		assertTrue(getFixture().getSources(new Integer(1)).contains("str2"));
	}

	// @Test
	public void testGetFilesManyFiles() {
		getFixture().connect("str1", new Integer(1));
		getFixture().connect("str2", new Integer(2));
		assertTrue(getFixture().getSources(new Integer(1)).contains("str1"));
		assertTrue(getFixture().getSources(new Integer(2)).contains("str2"));
	}

	// @Test
	public void testGetModelObjectManyFiles() {

		getFixture().connect("str1", new Integer(1));
		getFixture().connect("str2", new Integer(1));
		assertEquals(new Integer(1), getFixture().getTarget("str1"));
		assertEquals(new Integer(1), getFixture().getTarget("str2"));
	}

	// @Test
	public void testContainsFile() {

		getFixture().connect("str1", new Integer(1));
		getFixture().connect("str2", new Integer(2));

		assertTrue(getFixture().containsSource("str1"));
		assertTrue(getFixture().containsSource("str2"));
		assertFalse(getFixture().containsSource(null));
	}

	// @Test
	public void testTwoFilesTwoObjects() {
		getFixture().connect("str1", new Integer(1));
		getFixture().connect("str2", new Integer(2));
		assertEquals(new Integer(1), getFixture().getTarget("str1"));
		assertEquals(new Integer(2), getFixture().getTarget("str2"));
		assertTrue(getFixture().containsTarget(new Integer(1)));
		assertTrue(getFixture().containsTarget(new Integer(2)));
	}

	// @Test
	public void testDisconnectFile() {
		getFixture().connect("str1", new Integer(1));
		getFixture().connect("str2", new Integer(1));
		assertEquals(new Integer(1), getFixture().getTarget("str1"));
		assertEquals(new Integer(1), getFixture().getTarget("str2"));

		getFixture().disconnectSource("str1");
		assertFalse(getFixture().containsSource("str1"));
		assertNull(getFixture().getTarget("str1"));
		assertEquals(new Integer(1), new Integer(getFixture().getSources(new Integer(1)).size()));
		assertTrue(getFixture().getSources(new Integer(1)).contains("str2"));
	}

	// @Test
	public void testDisconnectModelObject() {
		getFixture().connect("str1", new Integer(1));
		getFixture().connect("str11", new Integer(1));
		getFixture().connect("str2", new Integer(2));
		assertEquals(new Integer(1), new Integer(getFixture().getSources(new Integer(2)).size()));
		assertEquals(new Integer(2), new Integer(getFixture().getSources(new Integer(1)).size()));

		getFixture().disconnect(new Integer(1));
		assertFalse(getFixture().containsTarget(new Integer(1)));
		assertFalse(getFixture().containsSource("str1"));
		assertFalse(getFixture().containsSource("str11"));
		assertTrue(getFixture().containsSource("str2"));
		assertTrue(getFixture().containsTarget(new Integer(2)));
	}

}
