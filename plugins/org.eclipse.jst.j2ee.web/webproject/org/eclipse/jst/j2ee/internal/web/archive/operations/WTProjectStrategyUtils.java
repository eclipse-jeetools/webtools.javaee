/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;



import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.commonarchivecore.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.File;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;


public class WTProjectStrategyUtils {
	public static File findSourceFile(Archive archive, String file) {
		if (isClassFile(file))
			return findSourceFileForClass(archive, file);

		// Look in the CLASSES directory
		try {
			if (!isOutputFile(file))
				return archive.getFile(file);
		} catch (java.io.FileNotFoundException ex) {
		}
		return null;
	}

	public static File findSourceFileForClass(Archive archive, String classFile) {
		// Look in the CLASSES directory
		IPath classFilePath = new Path(classFile);
		IPath sourceFilePath = classFilePath.removeFileExtension().addFileExtension("java");//$NON-NLS-1$
		try {
			File file = archive.getFile(sourceFilePath.toString());
			if (file != null)
				return file;
		} catch (java.io.FileNotFoundException ex) {
		}

		// Look in the LIB directory
		sourceFilePath = new Path(ArchiveConstants.WEBAPP_LIB_URI).append(sourceFilePath);
		try {
			File file = archive.getFile(sourceFilePath.toString());
			if (file != null)
				return file;
		} catch (java.io.FileNotFoundException ex) {
		}
		return null;

	}

	public static boolean isClassFile(String uri) {
		return uri.startsWith(ArchiveConstants.WEBAPP_CLASSES_URI) && uri.endsWith(".class");//$NON-NLS-1$
	}

	public static boolean isOutputFile(String uri) {
		return uri.startsWith(ArchiveConstants.WEBAPP_CLASSES_URI);
	}

	public static boolean isSourceFile(String uri) {
		// A file is considered a java source file if it is in the
		// WEB_INF directory and it does not end with .class.
		if (uri.startsWith(J2EEConstants.WEB_INF + "/") && //$NON-NLS-1$
					uri.endsWith(".java"))//$NON-NLS-1$
			return true;
		return false;

	}

	public static String makeRelative(String pathString, String relToPathString) {
		IPath relToPath = new Path(relToPathString);
		IPath path = new Path(pathString);

		if (path.matchingFirstSegments(relToPath) == relToPath.segmentCount())
			path = path.removeFirstSegments(relToPath.segmentCount());

		return path.toString();
	}
}