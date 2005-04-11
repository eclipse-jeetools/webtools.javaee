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
package org.eclipse.jst.j2ee.internal.web.locator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.internal.web.taglib.ITaglibInfo;
import org.eclipse.jst.j2ee.internal.web.taglib.TLDDigester;
import org.eclipse.jst.j2ee.internal.web.taglib.TaglibInfo;
import org.eclipse.jst.j2ee.internal.web.taglib.WebXMLTaglibInfo;
import org.eclipse.jst.j2ee.jsp.JSPConfig;
import org.eclipse.jst.j2ee.jsp.TagLibRefType;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.TagLibRef;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.web.internal.operation.ILibModule;

/**
 * @version 1.0
 * @author
 */
public class WebXMLTaglibLocator extends AbstractWebTaglibLocator {

	public WebXMLTaglibLocator(IProject project) {
		super(project);
	}

	protected ILibModule findLibModule(String jarFile) {
		String fileName = new Path(jarFile).lastSegment();
		ILibModule[] libModules = getLibModules();
		for (int i = 0; i < libModules.length; i++) {
			ILibModule iLibModule = libModules[i];
			if (iLibModule.getJarName().equals(fileName))
				return iLibModule;
		}
		return null;
	}

	protected IFile findWebAppRelativeFile(IPath path) {
		if (path != null) {
			IResource resource = getModuleServerRoot().findMember(path);
			if (resource != null && resource.getType() == IResource.FILE)
				return (IFile) resource;
		}
		return null;
	}

	protected IPath[] findTLDsInJar(IFile jarFile) {
		ZipFile zFile = null;
		IPath[] results = new IPath[0];

		try {
			zFile = new ZipFile(jarFile.getLocation().toFile());
			ZipEntry[] entries = findTLDEntriesInZip(zFile);
			results = new IPath[entries.length];
			for (int i = 0; i < entries.length; i++) {
				ZipEntry entry = entries[i];
				results[i] = new Path(entry.getName());
			}
		} catch (ZipException e) {
			//Do nothing
		} catch (IOException e) {
			//Do nothing
		} finally {
			if (zFile != null) {
				try {
					zFile.close();
				} catch (IOException e) {
					//Do nothing
				}
			}
		}
		return results;
	}

	/*
	 * @see ITaglibLocator#search(IResource)
	 */
	public ITaglibInfo[] searchFile(IFile file) {
		// This locator only looks at web.xml files.
		if (!getWebDeploymentDescriptorPath().equals(file.getFullPath()))
			return EMPTY_TAGLIBINFO_ARRAY;

		ArrayList results = new ArrayList();
		WebArtifactEdit webEdit = null;
		try {
			WebApp webApp = null;
			webEdit = (WebArtifactEdit) StructureEdit.getFirstArtifactEditForRead(project);
			if (webEdit != null)
				webApp = (WebApp) webEdit.getDeploymentDescriptorRoot();
		
			if (webApp == null)
				return EMPTY_TAGLIBINFO_ARRAY;
	
			List taglibs = new ArrayList();
			if (webApp.getVersionID() >= J2EEVersionConstants.WEB_2_4_ID) {
				JSPConfig config = webApp.getJspConfig();
				if (config != null)
					taglibs = config.getTagLibs();
			} else {
				taglibs = webApp.getTagLibs();
			}
	
			for (Iterator iter = taglibs.iterator(); iter.hasNext();) {
				TagLibRef taglibRef13;
				TagLibRefType taglibRef14;
				String uri;
				String taglibLocation;
				if (webApp.getVersionID() >= J2EEVersionConstants.WEB_2_4_ID) {
					taglibRef14 = (TagLibRefType) iter.next();
					uri = taglibRef14.getTaglibURI();
					taglibLocation = taglibRef14.getTaglibLocation();
				} else {
					taglibRef13 = (TagLibRef) iter.next();
					uri = taglibRef13.getTaglibURI();
					taglibLocation = taglibRef13.getTaglibLocation();
				}
	
				IPath projectRelativeLocation = new Path(taglibLocation);
				IPath webModuleRelativeLocation = getWebAppRelativePath(taglibLocation);
				if (webModuleRelativeLocation != null) {
					projectRelativeLocation = getServerRoot().append(webModuleRelativeLocation);
				}
				WebXMLTaglibInfo taglibInfo = null;
				IFile locationFile = findWebAppRelativeFile(webModuleRelativeLocation);
				boolean isLocationResolved = true;
				if (hasJarExtension(taglibLocation)) {
					if (locationFile == null) {
						// If the location file is null it means that the file could not
						// be found in this project, check to see if it is referencing
						// a TLD in a lib module
						IResource resource = findLibModuleRelativeFile(webModuleRelativeLocation);
						if (resource == null || !resource.exists()) {
							// Go ahead and create an entry which cannot be resolved
							// Only the /META-INF/taglib.tld file can be specified in web.xml
							isLocationResolved = false;
							taglibInfo = (WebXMLTaglibInfo) createTaglibForJar(uri, projectRelativeLocation, new Path("META-INF/taglib.tld")); //$NON-NLS-1$
						} else {
							locationFile = (IFile) resource;
							taglibInfo = (WebXMLTaglibInfo) createTaglibForLibModuleJar(uri, projectRelativeLocation, resource);
							setPrefix(taglibInfo, locationFile);
						}
					} else {
						isLocationResolved = true;
						// Only the /META-INF/taglib.tld file can be specified in web.xml
						taglibInfo = (WebXMLTaglibInfo) createTaglibForJar(uri, projectRelativeLocation, new Path("META-INF/taglib.tld")); //$NON-NLS-1$
						setPrefix(taglibInfo, locationFile);
					}
				} else {
					if (locationFile == null)
						isLocationResolved = false;
					taglibInfo = (WebXMLTaglibInfo) createTaglibForTLD(uri, projectRelativeLocation);
					setPrefix(taglibInfo, locationFile);
	
				}
				if (taglibInfo != null) {
					taglibInfo.setIsWebXMLEntry(true);
					taglibInfo.setWebXMLLocation(new Path(taglibLocation));
					taglibInfo.setIsLocationResolved(isLocationResolved);
					// If the location cannot be resolved, set the taglibInfo to be invalid
					if (!isLocationResolved)
						taglibInfo.setIsValid(false);
					results.add(taglibInfo);
				}
			}
		} finally {
			if (webEdit != null)
				webEdit.dispose();
		}
		return (ITaglibInfo[]) results.toArray(new ITaglibInfo[results.size()]);
	}

	/**
	 * This method retrieves the short-name from the tld file and adds it as a prefix to the
	 * ITaglibInfo entry
	 * 
	 * @param taglibInfo
	 * @param locationFile
	 */
	private void setPrefix(ITaglibInfo taglibInfo, IFile locationFile) {
		if (locationFile != null && locationFile.exists()) {
			TLDDigester digester = null;
			try {
				if (isTaglibJar(locationFile)) {
					digester = getTLDDigester(locationFile, new Path("META-INF/taglib.tld")); //$NON-NLS-1$
					((TaglibInfo) taglibInfo).setPrefix(digester);
				} else if (hasTLDExtension(locationFile.getFullPath())) {
					digester = getTLDDigester(locationFile);
					((TaglibInfo) taglibInfo).setPrefix(digester);
				}
			} finally {
				if (digester != null)
					digester.close();
			}
		}
	}

	protected ITaglibInfo createTaglibForTLD(String uri, IPath file) {
		return new WebXMLTaglibInfo(this.project, uri, file);
	}

	protected ITaglibInfo createTaglibForJar(String uri, IPath jarfile, IPath tldLocation) {
		WebXMLTaglibInfo taglibInfo = new WebXMLTaglibInfo(this.project, uri, jarfile, tldLocation);
		return taglibInfo;
	}


	protected ITaglibInfo createTaglibForLibModuleJar(String uri, IPath jarfile, IResource tldFile) {
		WebXMLTaglibInfo taglibInfo = new WebXMLTaglibInfo(tldFile.getProject(), uri, jarfile, tldFile.getProjectRelativePath());
		taglibInfo.setIsLibModule(true);
		return taglibInfo;
	}


	/**
	 * Method findLibModuleRelativeFile.
	 * 
	 * @param location
	 * @return String
	 */
	private IResource findLibModuleRelativeFile(IPath location) {
		if (location != null) {
			ILibModule libModule = findLibModule(location.lastSegment());
			if (libModule != null) {
				IProject tProject = libModule.getProject();
				if (tProject.isOpen()) {
					try {
						IJavaProject javaProject = JavaCore.create(tProject);
						IPath outputLocation = javaProject.getOutputLocation();
						IPath searchPath = outputLocation.removeFirstSegments(1);
						searchPath = searchPath.append(J2EEConstants.META_INF);
						searchPath = searchPath.append("taglib.tld"); //$NON-NLS-1$
						IResource searchResource = tProject.findMember(searchPath);
						if (searchResource != null)
							return searchResource;
					} catch (JavaModelException e) {
						//Do nothing
					}
				}
			}
		}
		return null;
	}

	/**
	 * Method getWebAppRelativePath. This method resolves a location specified in a taglib uri to a
	 * project relative IPath
	 * 
	 * @return null if the relative path cannot be resolved to an actual resource or a web library
	 *         project
	 * @param location
	 * @return IPath
	 */
	private IPath getWebAppRelativePath(String location) {
		IPath resolvedPath = null;
		if (location != null && !location.trim().equals("")) { //$NON-NLS-1$
			IPath preResolvePath = new Path(location);
			IContainer webModuleFolder = getModuleServerRoot();
			IContainer webLibraryFolder = WebPropertiesUtil.getWebLibFolder(project);
			IContainer webInfFolder = webLibraryFolder.getParent();
			if (preResolvePath.getDevice() == null && !preResolvePath.isEmpty()) {
				IContainer searchContainer = webModuleFolder;
				if (!preResolvePath.isAbsolute()) {
					searchContainer = webInfFolder;
				}
				for (int i = 0; i < preResolvePath.segmentCount(); i++) {
					String pathSegment = preResolvePath.segment(i);
					if (pathSegment.equals(".")) //$NON-NLS-1$
						continue;
					else if (pathSegment.equals("..")) { //$NON-NLS-1$
						searchContainer = searchContainer.getParent();
						if (searchContainer.getFullPath().equals(webModuleFolder.getParent().getFullPath()))
							break; // path must be confined to webModuleFolder
					} else {
						IResource resourceFound = searchContainer.findMember(pathSegment);
						if (resourceFound instanceof IContainer) {
							searchContainer = (IContainer) resourceFound;
						} else if (i == preResolvePath.segmentCount() - 1) {
							if (resourceFound != null) {
								if (resourceFound.getType() == IResource.FILE) {
									resolvedPath = resourceFound.getFullPath().removeFirstSegments(webModuleFolder.getFullPath().segmentCount());
									break; // for readability
								}
							} else {
								// maybe its a web library project, so let us check if the current
								// container is the lib folder
								if (webLibraryFolder.getFullPath().equals(searchContainer.getFullPath())) {
									// the last segment could be the name of the jar for the web
									// library project
									if (findLibModule(pathSegment) != null) {
										resolvedPath = searchContainer.getFullPath().removeFirstSegments(webModuleFolder.getFullPath().segmentCount());
										resolvedPath = resolvedPath.append(pathSegment);
									}
								}
							}
						} else
							break;// not a container, not the last segment either so its an invalid
								  // path
					}
				}
			}
		}
		return resolvedPath;
	}

}