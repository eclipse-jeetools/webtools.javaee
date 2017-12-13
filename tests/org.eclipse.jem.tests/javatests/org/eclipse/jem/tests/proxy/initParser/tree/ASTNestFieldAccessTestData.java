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
package org.eclipse.jem.tests.proxy.initParser.tree;
/*
 *  $RCSfile: ASTNestFieldAccessTestData.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:58:54 $ 
 */

import java.awt.Color;
import java.awt.Rectangle;

/**
 * This class is used in the testing of nested field access for Parse testing.
 * This is when you access a field, and then access a field of that.
 * In this case  <code>ASTNestFieldAccessTestData.acolor.red</code>
 * @since 1.0.0
 */
public class ASTNestFieldAccessTestData {
	public static Color acolor = Color.green;
	public Rectangle arect = new Rectangle(10,20,30,40); 
}
