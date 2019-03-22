/*******************************************************************************
 * Copyright (c) 2003, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

public class WebComponentArchiveSaveAdapter extends J2EEComponentArchiveSaveAdapter {

	private IPath IMPORTED_CLASSES_PATH = new Path("/" + ArchiveConstants.WEBAPP_CLASSES_URI); //$NON-NLS-1$
	
	public WebComponentArchiveSaveAdapter(IVirtualComponent vComponent) {
		super(vComponent);
	}

	@Override
	protected boolean shouldAddImportedClassesToClasspath() {
		return false; // never add to classpath because the web app container will pick this up.
	}

	@Override
	protected IPath getImportedClassesURI(IArchiveResource aFile){
		return aFile.getPath().makeRelative().removeFirstSegments(IMPORTED_CLASSES_PATH.segmentCount());
	}

	@Override
	protected IPath getImportedClassesRuntimePath() {
		return IMPORTED_CLASSES_PATH;
	}
	
	@Override
	protected IPath getProjectRelativePath(IArchiveResource aFile) {
		if (null != nonStandardSourceFiles && nonStandardSourceFiles.containsKey(aFile)) {
			IPath path = nonStandardSourceFiles.get(aFile);
			IVirtualFolder rootFolder = vComponent.getRootFolder();
			IVirtualFile vFile = rootFolder.getFile(path);
			IFile iFile = vFile.getUnderlyingFile();
			return iFile.getProjectRelativePath();
		}
		if (endsWithClassType(aFile.getPath().lastSegment())) {
			IPath relativePath = aFile.getPath().makeRelative();
			if (!IMPORTED_CLASSES_PATH.isPrefixOf(relativePath))
			{
				IVirtualFolder rootFolder = vComponent.getRootFolder();
				IVirtualFile vFile = rootFolder.getFile(relativePath);
				IFile iFile = vFile.getUnderlyingFile();
				return iFile.getProjectRelativePath();
			}
		}
		return super.getProjectRelativePath(aFile);
	}

	/**
	 * This map handles the case when a java source file is not in the same place as the .class
	 * file. For example if all the source files were contained in WEB-INF/source
	 */
	protected Map<IArchiveResource, IPath> nonStandardSourceFiles;

	@Override
	protected boolean isClassWithoutSource(IArchiveResource aFile) {
		String javaUri = ArchiveUtil.classUriToJavaUri(aFile.getPath().toString());
		if (javaUri == null)
			return true;
		IPath javaPath = new Path(javaUri);
		if (archive.containsArchiveResource(javaPath)) {
			return false;
		}
		// see if it is a JSP
		String jspUri = javaUri.substring(0, javaUri.indexOf(ArchiveUtil.DOT_JAVA));
		int lastSlash = jspUri.lastIndexOf('/');
		int _index = lastSlash == -1 ? ArchiveConstants.WEBAPP_CLASSES_URI.length() : lastSlash + 1;
		if (jspUri.length() > _index && jspUri.charAt(_index) == '_') {
			jspUri = jspUri.substring(ArchiveConstants.WEBAPP_CLASSES_URI.length(), _index) + jspUri.substring(_index + 1) + ArchiveUtil.DOT_JSP;
			IPath jspPath = new Path(jspUri);
			if (archive.containsArchiveResource(jspPath)) {
				return false;
			}
		}

		//This is to handle archives created by an earlier version
		//The format was to include the source files in a directory called source in WEB-INF
		//Example: class  is in WEB-INF/classes/test/Foo.class
		//         source is in WEB-INF/source/test/Foo.java
		if(javaPath.segmentCount() > 2 && javaPath.segment(0).equals("WEB-INF") && javaPath.segment(1).equals("classes")){ //$NON-NLS-1$ //$NON-NLS-2$
			String alternateJavaUri = javaUri.replaceFirst("classes", "source"); //$NON-NLS-1$ //$NON-NLS-2$
			IPath alternateJavaPath = new Path(alternateJavaUri);
			if (archive.containsArchiveResource(alternateJavaPath)){
				IArchiveResource sourceFile;
				try {
					sourceFile = archive.getArchiveResource(alternateJavaPath);
					if(sourceFile != null){
						if (nonStandardSourceFiles == null) {
							nonStandardSourceFiles = new HashMap<IArchiveResource, IPath>();
						}
						if (!nonStandardSourceFiles.containsKey(sourceFile)) {
							nonStandardSourceFiles.put(sourceFile, javaPath);
						}
						return false;
					}
				} catch (FileNotFoundException e) {
					J2EEPlugin.logError(e);
				}
			}
		}
		return true;
	}
	
	@Override
	protected List<IArchiveResource> getArchiveResourcesForSave() {
		List <IArchiveResource> sortedFiles = new ArrayList <IArchiveResource>();
		int classIndex = 0;
		for(IArchiveResource resource : super.getArchiveResourcesForSave()){
			if(resource.getPath().lastSegment().endsWith(DOT_CLASS)){
				sortedFiles.add(classIndex++, resource);
			}
			else {
				sortedFiles.add(resource);
			}
		}
		return sortedFiles;
	}
}
