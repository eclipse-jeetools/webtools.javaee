/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

public class WebComponentArchiveSaveAdapter extends J2EEComponentArchiveSaveAdapter {

	public WebComponentArchiveSaveAdapter(IVirtualComponent vComponent) {
		super(vComponent);
	}

	protected boolean shouldAddImportedClassesToClasspath() {
		return false; // never add to classpath because the web app container will pick this up.
	}

	protected String getImportedClassesURI(IArchive aFile){
		return aFile.getPath().lastSegment().toString();
		
	}

	protected IPath getImportedClassesRuntimePath() {
		return new Path("/" + ArchiveConstants.WEBAPP_CLASSES_URI);
	}

	protected IPath getOutputPathForFile(IArchive aFile) {
		if (null != nonStandardSourceFiles && nonStandardSourceFiles.containsKey(aFile)) {
			IVirtualFolder rootFolder = vComponent.getRootFolder();
			IVirtualFile vFile = rootFolder.getFile((String) nonStandardSourceFiles.get(aFile));
			IFile iFile = vFile.getUnderlyingFile();
			return iFile.getProjectRelativePath();
		}
		return super.getOutputPathForFile(aFile);
	}

	/**
	 * This map handles the case when a java source file is not in the same place as the .class
	 * file. For example if all the source files were contained in WEB-INF/source
	 */
	protected Map nonStandardSourceFiles;

	protected boolean isClassWithoutSource(IArchive aFile) {
		String javaUri = ArchiveUtil.classUriToJavaUri(aFile.getPath().toString());
		if (javaUri == null)
			return false;
		if (archive.containsArchiveResource(aFile.getPath())) {
			return false;
		}
		// see if it is a JSP
		String jspUri = javaUri.substring(0, javaUri.indexOf(ArchiveUtil.DOT_JAVA));
		int lastSlash = jspUri.lastIndexOf('/');
		int _index = lastSlash == -1 ? ArchiveConstants.WEBAPP_CLASSES_URI.length() : lastSlash + 1;
		if (jspUri.charAt(_index) == '_') {
			jspUri = jspUri.substring(ArchiveConstants.WEBAPP_CLASSES_URI.length(), _index) + jspUri.substring(_index + 1) + ArchiveUtil.DOT_JSP;
			if (archive.containsArchiveResource(aFile.getPath())) {
				return false;
			}
		}

		// see if the source is in another directory
		/*File sourceFile = ((WARFile) archive).getSourceFile(aFile);
		if (null == sourceFile) {
			return true;
		}*/
		/*if (nonStandardSourceFiles == null) {
			nonStandardSourceFiles = new HashMap();
		}
		if (!nonStandardSourceFiles.containsKey(sourceFile)) {
			nonStandardSourceFiles.put(sourceFile, javaUri);
		}*/
		return false;
	}
}
