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
package org.eclipse.jst.j2ee.internal.wizard;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author Sachin
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PackageNameResolver extends ClassLoader {

	public PackageNameResolver() {
		super();
	}

	public String getClassName(final String classFile) {
		File file = new File(classFile);
		byte[] classbuf = new byte[(int) file.length()];
		try {
			FileInputStream instream = new FileInputStream(file);
			instream.read(classbuf);
			instream.close();
		} catch (Throwable e) {
			return null;
		}
		boolean badclassname = true;
		String classname = classFile.replace(java.io.File.separatorChar, '.');
		int endi;
		if (classname.endsWith(".class")) //$NON-NLS-1$
			endi = classname.lastIndexOf('.');
		else
			endi = classname.length();
		int i = classname.indexOf('.');
		while (i < endi && badclassname == true) {
			badclassname = false;
			try {
				defineClass(classname.substring(i + 1, endi), classbuf, 0, classbuf.length);
			} catch (java.lang.NoClassDefFoundError e) {
				String msg = e.getMessage();
				if (msg == null || msg.indexOf(' ') > 0) {
					badclassname = true;
				}
			} catch (Throwable e) {
				badclassname = true;
			}
			if (badclassname) {
				i = classname.indexOf('.', i + 1);
				if (i == -1)
					i = endi;
			}
		}
		if (badclassname)
			return null;
		return classname.substring(i + 1, endi);
	}

}