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
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIterator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEComponentSaveStrategyImpl;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentImportDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class WebComponentSaveStrategyImpl extends J2EEComponentSaveStrategyImpl {

	public WebComponentSaveStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}

	public void save(File aFile, FileIterator iterator) throws SaveFailureException {
		if (aFile.isArchive() && operationHandlesNested((Archive) aFile)) {
			return;
		}
		super.save(aFile, iterator);
	}

	protected boolean operationHandlesNested(Archive archive) {
		if (null != dataModel) {
			List list = (List) dataModel.getProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED);
			return list.contains(archive);
		}
		return false;
	}
	
	protected boolean shouldAddImportedClassesToClasspath() {
		return false; //never add to classpath because the web app container will pick this up.
	}

	protected String getImportedClassesURI(File aFile) {
		String uri = aFile.getURI();
		return WTProjectStrategyUtils.makeRelative(uri, ArchiveConstants.WEBAPP_CLASSES_URI);
	}

	protected IPath getImportedClassesRuntimePath() {
		return new Path("/" + ArchiveConstants.WEBAPP_CLASSES_URI);
	}

	protected IPath getOutputPathForFile(File aFile) {
		if (null != nonStandardSourceFiles && nonStandardSourceFiles.containsKey(aFile)) {
			return new Path((String) nonStandardSourceFiles.get(aFile));
		}
		return super.getOutputPathForFile(aFile);
	}

	/**
	 * This map handles the case when a java source file is not in the same place as the .class
	 * file. For example if all the source files were contained in WEB-INF/source
	 */
	protected Map nonStandardSourceFiles;

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
		if (!nonStandardSourceFiles.containsKey(nonStandardSourceFiles)) {
			nonStandardSourceFiles.put(sourceFile, javaUri);
		}
		return false;
	}
}
