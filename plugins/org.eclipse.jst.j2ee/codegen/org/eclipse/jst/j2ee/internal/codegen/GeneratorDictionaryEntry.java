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
 * An entry in the generator dictionary. An entry maps the Java class name to the java class object.
 * Use of this class avoids repeated calls to loadClass.
 */
public class GeneratorDictionaryEntry {
	private String fGenClassName = null;
	private Class fGenClass = null;

	/**
	 * Constructed from the actual generator class name
	 * 
	 * @param logicalName
	 *            java.lang.String
	 */
	public GeneratorDictionaryEntry(String genClassName) {
		super();
		fGenClassName = genClassName;
	}

	/**
	 * Returns the real generator class.
	 * 
	 * @return java.lang.Class
	 */
	public Class getGenClass(ClassLoader cl) throws ClassNotFoundException {
		if (fGenClass == null) {
			fGenClass = cl.loadClass(fGenClassName);
		}
		return fGenClass;
	}
}