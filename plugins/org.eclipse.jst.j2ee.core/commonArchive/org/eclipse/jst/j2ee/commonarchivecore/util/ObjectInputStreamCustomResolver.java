/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.util;



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamClass;
import java.io.StreamCorruptedException;
import java.lang.reflect.Array;

public class ObjectInputStreamCustomResolver extends java.io.ObjectInputStream {
	private ClassLoader cl;

	/**
	 * Constructor
	 */

	public ObjectInputStreamCustomResolver(InputStream is, ClassLoader loader) throws IOException, StreamCorruptedException {

		super(is);

		if (loader != null)
			cl = loader;

	}

	/**
	 * Gets the java type class given a signature letter.
	 */

	private Class getType(char type) {

		switch (type) {
			case 'B' :
				return byte.class;
			case 'C' :
				return char.class;
			case 'D' :
				return double.class;
			case 'F' :
				return float.class;
			case 'I' :
				return int.class;
			case 'J' :
				return long.class;
			case 'S' :
				return short.class;
			case 'Z' :
				return boolean.class;
			default :
				return null;
		}

	}

	/**
	 * This overrides the default resolveClass() method to use a custom class loader.
	 */

	protected Class resolveClass(ObjectStreamClass classDesc) throws IOException, ClassNotFoundException {

		String className = classDesc.getName();

		//UtilsTrace.trace(" ObjectInputStreamCustomResolver: Resolving " + className);

		// Handle arrays of classes differently

		if (className.startsWith("[")) {//$NON-NLS-1$

			Class component;
			int dimNum;

			for (dimNum = 1; className.charAt(dimNum) == '['; dimNum++);

			if (className.charAt(dimNum) == 'L') {
				component = cl.loadClass(className.substring(dimNum + 1, className.length() - 1));
			} else {
				if (className.length() != dimNum + 1)
					throw new ClassNotFoundException(className); // malformed
				component = getType(className.charAt(dimNum));
			}

			int dimensions[] = new int[dimNum];
			for (int i = 0; i < dimNum; i++)
				dimensions[i] = 0;

			return Array.newInstance(component, dimensions).getClass();

		}
		return cl.loadClass(className);
	}
}
