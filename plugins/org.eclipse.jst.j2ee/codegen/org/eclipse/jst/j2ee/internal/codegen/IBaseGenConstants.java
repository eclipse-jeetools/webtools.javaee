/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.codegen;


/**
 * Defines some basic code generation constants.
 */
public interface IBaseGenConstants {
	String DEFAULT_INDENT = "\t"; //$NON-NLS-1$
	char DEFAULT_FORMAT_PARM_MARKER = '%';
	String COMMA_SEPARATOR = ", "; //$NON-NLS-1$
	String SPACE = " "; //$NON-NLS-1$
	String LINE_SEPARATOR = "\n"; //$NON-NLS-1$
	String EMPTY_STRING = ""; //$NON-NLS-1$
}