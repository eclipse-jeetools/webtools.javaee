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
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntimeUtilities;
import org.eclipse.jst.j2ee.internal.web.taglib.TLDDigester;
import org.eclipse.jst.j2ee.internal.web.taglib.TaglibInfo;
import org.eclipse.jst.j2ee.web.taglib.ITaglibInfo;

import com.ibm.wtp.common.logger.proxy.Logger;

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
		J2EEWebNatureRuntime nature = getWebNature();
		IContainer libFolder = nature.getLibraryFolder();
		IPath libPath = libFolder.getProjectRelativePath();
		int numOfLibPathSegs = libPath.segmentCount();
		if (file.getProjectRelativePath().matchingFirstSegments(libPath) == numOfLibPathSegs) {
			return true;
		}
		return false;
	}

	protected boolean isTLDFile(IFile file) {
		J2EEWebNatureRuntime nature = getWebNature();

		// defect CMVC 214409
		if (nature.getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_1_2)) {
			return isInWebInfFolder(file) && hasTLDExtension(file.getProjectRelativePath());
		} else if (nature.getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_2_0)) {
			return isInWebInfFolder(file) && hasTLDExtension(file.getProjectRelativePath());
		}

		return /* isInWebInfFolder(file) && */hasTLDExtension(file.getProjectRelativePath());
	}

	protected boolean isInWebInfFolder(IFile file) {
		J2EEWebNatureRuntime nature = getWebNature();
		IPath webInfPath = nature.getWEBINFPath();
		int numOfWebInfPathSegs = webInfPath.segmentCount();
		if (file.getProjectRelativePath().matchingFirstSegments(webInfPath) == numOfWebInfPathSegs) {
			return true;
		}
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

	/**
	 * @see AbstractTaglibLocator#getServerRoot()
	 */
	protected IPath getServerRoot() {
		return getWebNature().getModuleServerRoot().getProjectRelativePath();
	}

	protected J2EEWebNatureRuntime getWebNature() {
		return (J2EEWebNatureRuntime) J2EEWebNatureRuntimeUtilities.getRuntime(this.project);
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

		// JSP 1.2 TLDs are not allowed in JSP 1.1 projects
		if (getWebNature().getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_1_1) && (digester.getJSPLevel() == null || !digester.getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_1_1)))
			return false;
		// JSP 2.0 TLDs are not allowed in JSP 1.2 projects
		if (getWebNature().getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_1_2) && (digester.getJSPLevel() == null || !digester.getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_1_2) || !digester.getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_1_1)))
			return false;

		// JSP 2.0 TLDs are allowed in JSP 2.0 projects only
		if (getWebNature().getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_2_0) && (digester.getJSPLevel() == null || !digester.getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_2_0) || !digester.getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_1_2) || !digester.getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_1_1)))
			return false;


		return true;
	}

	protected boolean isValidTLDJarPath(IPath path) {
		boolean isJSP12 = getWebNature().getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_1_2);
		boolean isJSP20 = getWebNature().getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_2_0);

		if (isJSP12 || isJSP20) {
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
					if (getWebNature().getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_1_2) || getWebNature().getJSPLevel().equals(J2EEWebNatureRuntime.JSPLEVEL_2_0)) {
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

}