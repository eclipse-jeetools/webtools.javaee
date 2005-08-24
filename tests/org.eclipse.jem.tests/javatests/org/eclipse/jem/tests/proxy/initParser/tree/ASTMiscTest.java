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
 *  $RCSfile: ASTMiscTest.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:58:54 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.InstantiationFactory;
import org.eclipse.jem.internal.instantiation.PTCharacterLiteral;
import org.eclipse.jem.tests.proxy.initParser.AbstractInitParserTestCase;
 
/**
 * Some misc. tests.
 * @since 1.0.0
 */
public class ASTMiscTest extends AbstractInitParserTestCase {

	/**
	 * Construct with name.
	 * @param name
	 * 
	 * @since 1.0.0
	 */
	public ASTMiscTest(String name) {
		super(name);
	}
	
	public void testCharLiteral() {
		PTCharacterLiteral cl = InstantiationFactory.eINSTANCE.createPTCharacterLiteral();
		cl.setEscapedValue("\'a\'");
		assertEquals('a', cl.getCharValue());
		cl.setCharValue('b');
		assertEquals("\'b\'", cl.getEscapedValue());
		cl.setEscapedValue("\'\\n\'");
		assertEquals('\n', cl.getCharValue());
		cl.setCharValue('\b');
		assertEquals("\'\\b\'", cl.getEscapedValue());
		cl.setEscapedValue("\'\\u0300\'");
		assertEquals('\u0300', cl.getCharValue());
		cl.setCharValue('\u0400');
		assertEquals("\'\\u0400\'", cl.getEscapedValue());
	}

	public void testStringLiteral() {
		PTStringLiteral sl = InstantiationFactory.eINSTANCE.createPTStringLiteral();
		sl.setEscapedValue("\"a\"");
		assertEquals("a", sl.getLiteralValue());
		sl.setLiteralValue("\b");
		assertEquals("\"\\b\"", sl.getEscapedValue());
		sl.setEscapedValue("\"\\n\"");
		assertEquals("\n", sl.getLiteralValue());
		sl.setLiteralValue("\b");
		assertEquals("\"\\b\"", sl.getEscapedValue());
		sl.setEscapedValue("\"\\u0300\"");
		assertEquals("\u0300", sl.getLiteralValue());
		sl.setLiteralValue("\u0400");
		assertEquals("\"\\u0400\"", sl.getEscapedValue());
	}
	
}
