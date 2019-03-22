/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEComponentSaveStrategyImpl;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * @deprecated this will be deleted post WTP 3.2.  This code is no longer
 * called because the IArchive API is used to handle imports.
 * 
 * @author jsholl
 */
public class WebComponentSaveStrategyImpl extends J2EEComponentSaveStrategyImpl {

	public WebComponentSaveStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}

	/**
	 * DoNotUseMeThisWillBeDeletedPost15
	 * 
	 * @deprecated
	 * @param archive
	 * @return
	 */
	protected boolean operationHandlesNested(Archive archive) {
		return !shouldLinkAsComponentRef(archive);
	}

	@Override
	protected boolean shouldAddImportedClassesToClasspath() {
		return false; // never add to classpath because the web app container will pick this up.
	}

	@Override
	protected String getImportedClassesURI(File aFile) {
		String uri = aFile.getURI();
		return WTProjectStrategyUtils.makeRelative(uri, ArchiveConstants.WEBAPP_CLASSES_URI);
	}

	@Override
	protected IPath getImportedClassesRuntimePath() {
		return new Path("/" + ArchiveConstants.WEBAPP_CLASSES_URI); //$NON-NLS-1$
	}

	@Override
	protected IPath getOutputPathForFile(File aFile) {
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

	@Override
	protected boolean isClassWithoutSource(File aFile) {
		String javaUri = ArchiveUtil.classUriToJavaUri(aFile.getURI());
		if (javaUri == null)
			return false;
		if (archive.containsFile(javaUri)) {
			return false;
		}
		// see if it is a JSP
		String jspUri = javaUri.substring(0, javaUri.indexOf(ArchiveUtil.DOT_JAVA));
		int lastSlash = jspUri.lastIndexOf('/');
		int _index = lastSlash == -1 ? ArchiveConstants.WEBAPP_CLASSES_URI.length() : lastSlash + 1;
		if (jspUri.charAt(_index) == '_') {
			jspUri = jspUri.substring(ArchiveConstants.WEBAPP_CLASSES_URI.length(), _index) + jspUri.substring(_index + 1) + ArchiveUtil.DOT_JSP;
			if (archive.containsFile(jspUri)) {
				return false;
			}
		}

		// see if the source is in another directory
		File sourceFile = ((WARFile) archive).getSourceFile(aFile);
		if (null == sourceFile) {
			return true;
		}
		if (nonStandardSourceFiles == null) {
			nonStandardSourceFiles = new HashMap();
		}
		if (!nonStandardSourceFiles.containsKey(sourceFile)) {
			nonStandardSourceFiles.put(sourceFile, javaUri);
		}
		return false;
	}
}
