/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.locator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.internal.web.taglib.ITaglibInfo;
import org.eclipse.jst.j2ee.internal.web.taglib.TLDDigester;
import org.eclipse.jst.j2ee.internal.web.taglib.TaglibInfo;
import org.eclipse.jst.j2ee.web.modulecore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.web.internal.operation.ILibModule;

abstract public class AbstractWebTaglibLocator extends AbstractTaglibLocator {
	/**
	 * Constructor for AbstractWebProjectTaglibLocator.
	 * 
	 * @param project
	 */
	public AbstractWebTaglibLocator(IProject project) {
		super(project);
	}

	protected boolean isInLibFolder(IFile file) {
		IContainer libFolder = WebPropertiesUtil.getWebLibFolder(project);
		IPath libPath = libFolder.getProjectRelativePath();
		int numOfLibPathSegs = libPath.segmentCount();
		if (file.getProjectRelativePath().matchingFirstSegments(libPath) == numOfLibPathSegs) {
			return true;
		}
		return false;
	}

	protected boolean isTLDFile(IFile file) {
		if (getJSPVersion()<J2EEVersionConstants.JSP_1_2_ID)
			return hasTLDExtension(file.getProjectRelativePath());
		return isInWebInfFolder(file) && hasTLDExtension(file.getProjectRelativePath());
	}

	protected boolean isInWebInfFolder(IFile file) {
		IPath webInfPath = getWebDeploymentDescriptorPath();
		int numOfWebInfPathSegs = webInfPath.segmentCount();
		if (file.getProjectRelativePath().matchingFirstSegments(webInfPath) == numOfWebInfPathSegs)
			return true;
		return false;
	}

	protected boolean isTaglibJar(IFile file) {
		return isInLibFolder(file) && hasJarExtension(file.getProjectRelativePath());
	}

	protected boolean isTaglibTLD(String filename) {
		if (filename != null) {
			if (filename.equalsIgnoreCase(IWebNatureConstants.META_INFO_DIRECTORY + "/taglib.tld")) //$NON-NLS-1$
				return true;
		}
		return false;
	}

	protected ZipEntry[] findTLDEntriesInZip(ZipFile zFile) {
		Enumeration entries = zFile.entries();
		ArrayList results = new ArrayList();

		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			if (!entry.isDirectory()) {
				// Look for the first .tld file found in the META-INF directory.
				IPath entryPath = new Path(entry.getName());
				if (isValidTLDJarPath(entryPath))
					results.add(entry);
			}
		}

		return (ZipEntry[]) results.toArray(new ZipEntry[results.size()]);
	}

	protected TLDDigester getTLDDigester(IFile jarFile, IPath jarRelativePath) {
		InputStream stream = null;
		TLDDigester digester = null;
		try {
			ZipFile zFile = new ZipFile(jarFile.getLocation().toFile());
			ZipEntry[] entries = findTLDEntriesInZip(zFile);
			if (entries != null) {
				for (int i = 0; i < entries.length; i++) {
					ZipEntry entry = entries[i];
					if (entry.getName().equalsIgnoreCase(jarRelativePath.toString())) {
						stream = zFile.getInputStream(entry);
						break;
					}
				}
				if (stream != null)
					digester = getTLDDigester(stream);
			}
		} catch (Exception e) {
			Logger.getLogger().log(e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					Logger.getLogger().log(e);
				}
			}
		}
		return digester;
	}

	protected boolean isValidTLD(TLDDigester digester) {
		if (digester == null || !super.isValidTLD(digester))
			return false;
		int JSPVersion = getJSPVersion();
		// JSP 1.2 TLDs are not allowed in JSP 1.1 projects
		if (JSPVersion==J2EEVersionConstants.JSP_1_1_ID && (digester.getJSPLevel() == null || !digester.getJSPLevel().equals(J2EEVersionConstants.VERSION_1_1_TEXT)))
			return false;
		// JSP 2.0 TLDs are not allowed in JSP 1.2 projects
		if (JSPVersion==J2EEVersionConstants.JSP_1_2_ID && (digester.getJSPLevel() == null || !digester.getJSPLevel().equals(J2EEVersionConstants.VERSION_1_2_TEXT) || !digester.getJSPLevel().equals(J2EEVersionConstants.VERSION_1_1_TEXT)))
			return false;
		// JSP 2.0 TLDs are allowed in JSP 2.0 projects only
		if (JSPVersion==J2EEVersionConstants.JSP_2_0_ID && (digester.getJSPLevel() == null || !digester.getJSPLevel().equals(J2EEVersionConstants.VERSION_2_0_TEXT) || !digester.getJSPLevel().equals(J2EEVersionConstants.VERSION_1_2_TEXT) || !digester.getJSPLevel().equals(J2EEVersionConstants.VERSION_1_1_TEXT)))
			return false;
		return true;
	}

	protected boolean isValidTLDJarPath(IPath path) {
		int JSPVersion = getJSPVersion();
		if (JSPVersion==J2EEVersionConstants.JSP_1_2_ID || JSPVersion==J2EEVersionConstants.JSP_2_0_ID) {
			if (!hasTLDExtension(path))
				return false;
			if (!(path.matchingFirstSegments(new Path("META-INF")) > 0)) //$NON-NLS-1$
				return false;
		} else {
			if (!path.equals(new Path("META-INF/taglib.tld"))) //$NON-NLS-1$
				return false;
		}
		return true;
	}

	protected ITaglibInfo[] searchJarFile(IFile jarFile) {
		return searchJarFile(null, jarFile.getLocation().toFile(), jarFile.getProjectRelativePath());
	}

	protected ITaglibInfo[] searchJarFile(File jarFile) {
		return searchJarFile(null, jarFile, new Path(jarFile.getAbsolutePath()));
	}

	protected ITaglibInfo[] searchJarFile(String uri, File jarFile, IPath projectRelativePath) {

		ArrayList results = new ArrayList();
		ZipFile zFile = null;
		try {
			// Need to check if the jar file has a .tld file anywhere under the
			// META-INF directory. If there is, add a taglib for the uri that
			// is inside the TLD file.
			zFile = new ZipFile(jarFile);
			ZipEntry[] entries = findTLDEntriesInZip(zFile);

			for (int i = 0; i < entries.length; i++) {
				ZipEntry entry = entries[i];
				if (entry != null) {
					// most entries can be skipped this way
					if (entry.getName() == null || !entry.getName().toLowerCase().endsWith("tld")) //$NON-NLS-1$
						continue;
					TLDDigester digester = getTLDDigester(zFile.getInputStream(entry));
					IPath entryPath = new Path(entry.getName());
					int JSPVersion = getJSPVersion();
					if (JSPVersion==J2EEVersionConstants.JSP_1_2_ID || JSPVersion==J2EEVersionConstants.JSP_2_0_ID) {
						String tURI = uri;
						if (uri == null) {
							tURI = digester.getURI();
						}

						// If a uri is found, add a taglib for the uri
						if (tURI != null) {
							ITaglibInfo taglib = createTaglibForJar(tURI, projectRelativePath, entryPath);
							((TaglibInfo) taglib).setIsURIFromTLD(true);
							((TaglibInfo) taglib).setPrefix(digester);
							results.add(taglib);
						}
					}

					if (isTaglibTLD(entryPath.toString())) {
						boolean canAddTaglibTLD = canAddTaglibTld(digester);
						if (canAddTaglibTLD) {
							ITaglibInfo taglib = createTaglibForJar(calculateURIForFile(projectRelativePath), projectRelativePath, entryPath);
							((TaglibInfo) taglib).setPrefix(digester);
							results.add(taglib);
						}
					}
					if (digester != null)
						digester.close();
				}
			}
		} catch (ZipException e) {
			Logger.getLogger().logError(e);
		} catch (IOException e) {
			Logger.getLogger().logError(e);
		} finally {
			if (zFile != null) {
				try {
					zFile.close();
				} catch (IOException e) {
					//Do nothing
				}
			}
		}
		return (ITaglibInfo[]) results.toArray(new ITaglibInfo[results.size()]);
	}

	/**
	 * @return
	 */
	protected boolean canAddTaglibTld(TLDDigester digester) {
		return isValidTLD(digester);
	}
	
	protected int getJSPVersion() {
		WebArtifactEdit webEdit = null;
		int JSPVersion = 0;
		try {
			webEdit = (WebArtifactEdit) StructureEdit.getFirstArtifactEditForRead(project);
			JSPVersion = webEdit.getJSPVersion();
		} finally {
			if (webEdit != null)
				webEdit.dispose();
		}
		return JSPVersion;
	}
	
	protected ILibModule[] getLibModules() {
		//TODO this will throw classcastexception, do we use ILibModule anymore?
		WebArtifactEdit webEdit = null;
		try {
			webEdit = (WebArtifactEdit) StructureEdit.getFirstArtifactEditForRead(project);
			if (webEdit != null)
				return (ILibModule[])webEdit.getLibModules();
		} finally {
			if (webEdit != null)
				webEdit.dispose();
		}
		return new ILibModule[] {};
	}
	
	/**
	 * @see AbstractTaglibLocator#getServerRoot()
	 */
	protected IPath getServerRoot() {
		return getModuleServerRoot().getProjectRelativePath();
	}
	
	protected IContainer getModuleServerRoot() {
		return WebPropertiesUtil.getModuleServerRoot(project);
	}
	
	protected IPath getWebDeploymentDescriptorPath() {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = (WebArtifactEdit) StructureEdit.getFirstArtifactEditForRead(project);
			return webEdit.getDeploymentDescriptorPath();
		} finally {
			if (webEdit != null)
				webEdit.dispose();
		}
	}
}