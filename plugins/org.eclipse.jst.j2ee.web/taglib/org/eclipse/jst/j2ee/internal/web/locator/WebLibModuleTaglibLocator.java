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
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.internal.web.taglib.ITaglibInfo;
import org.eclipse.jst.j2ee.internal.web.taglib.ITaglibRegistry;
import org.eclipse.jst.j2ee.internal.web.taglib.TLDDigester;
import org.eclipse.jst.j2ee.internal.web.taglib.TaglibInfo;
import org.eclipse.wst.web.internal.operation.ILibModule;

/**
 * @version 1.0
 * @author
 */
public class WebLibModuleTaglibLocator extends AbstractWebTaglibLocator {

	public WebLibModuleTaglibLocator(IProject project) {
		super(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.web.taglib.AbstractTaglibLocator#search(org.eclipse.core.resources.IResource)
	 */
	public ITaglibInfo[] search(IResource resource) {
		List libModuleTaglibs = calculateLibModuleTaglibs();
		return (ITaglibInfo[]) libModuleTaglibs.toArray(new ITaglibInfo[libModuleTaglibs.size()]);
	}

	protected List calculateLibModuleTaglibs() {
		// Check to see if there are any lib modules, if so, we must check to see
		// if there are any taglibs in these and concat the results.

		ILibModule[] libModules = getLibModules();
		Vector results = new Vector();

		// Go through all of the library modules and add any taglibs
		// from the java projects they map to.
		boolean addedJar = false;

		for (int i = 0; i < libModules.length; i++) {
			ILibModule iLibModule = libModules[i];
			IProject prj = iLibModule.getProject();
			ITaglibRegistry registry = WebPlugin.getDefault().getTaglibRegistryManager().getTaglibRegistry(prj);
			if (registry != null) { // CMVC defect 221661, Web library project being closed
				ITaglibInfo[] javaTaglibs = registry.getTaglibs();

				// Need to go through the java taglibs and change them to the appropriate
				for (int j = 0; j < javaTaglibs.length; j++) {
					ITaglibInfo iTaglibInfo = javaTaglibs[j];
					if (iTaglibInfo.isInJar())
						break;
					// defect 212671
					IPath webProjectRelativePath = WebPropertiesUtil.getWebLibFolder(project).getProjectRelativePath();
					IPath jarLocationPath = webProjectRelativePath.append(iLibModule.getJarName());
					IPath jarName = jarLocationPath.removeFirstSegments(getServerRoot().segmentCount());
					// defect 212671 This is important because jarName may not have a leading /
					IPath jarURI = new Path("/"); //$NON-NLS-1$
					jarURI = jarURI.append(jarName);
					int JSPVersion = getJSPVersion();
					if (iTaglibInfo.isURIFromTLD()) {
						if (JSPVersion==J2EEVersionConstants.JSP_1_2_ID || JSPVersion==J2EEVersionConstants.JSP_2_0_ID) {
							TaglibInfo newTaglib = new TaglibInfo(prj, iTaglibInfo.getURI(), jarLocationPath, iTaglibInfo.getTLDLocation());
							newTaglib.setIsLibModule(true);
							newTaglib.setIsURIFromTLD(true);
							results.add(newTaglib);
						}
					}
					// Add a taglib entry for the jar file if it contains a taglib.tld file.
					if (!addedJar && isTaglibTLD(iTaglibInfo.getTLDLocation())) {
						// Direct references can be made no matter what the JSP Level
						TaglibInfo newTaglib = new TaglibInfo(prj, jarURI.toString(), jarLocationPath, iTaglibInfo.getTLDLocation());
						newTaglib.setIsLibModule(true);
						// defect 212671
						newTaglib.setIsURIFromTLD(false);
						boolean canAddTaglibTLD = true;
						if (JSPVersion==J2EEVersionConstants.JSP_1_1_ID) { // this clause is for performance, get digester only for jsp 1.1
							try {
								TLDDigester digester = new TLDDigester(newTaglib.getTLDStream());
								if (digester.getJSPLevel() == null || !digester.getJSPLevel().equals(J2EEVersionConstants.VERSION_1_1_TEXT))
									// If JSP1.1 then tld better be 1.1 defect CMVC 217548
									canAddTaglibTLD = false;
							} catch (ZipException e) {
								// Do nothing
							} catch (IOException e) {
								// Do nothing
							} catch (CoreException e) {
								// Do nothing
							}
						} else if (JSPVersion==J2EEVersionConstants.JSP_1_2_ID) { // this clause is for performance, get digester only for jsp 1.1
							try {
								TLDDigester digester = new TLDDigester(newTaglib.getTLDStream());
								String digesterJSPLevel = digester.getJSPLevel();

								if (digesterJSPLevel == null || !digesterJSPLevel.equals(J2EEVersionConstants.VERSION_1_2_TEXT) || !digesterJSPLevel.equals(J2EEVersionConstants.VERSION_1_1_TEXT))// If JSP2.0 then tld better be 1.1 or 1.2 and not 2.0 defect CMVC 217548
									canAddTaglibTLD = false;
							} catch (ZipException e) {
								//do nothing
							} catch (IOException e) {
								//Do nothing
							} catch (CoreException e) {
								//Do nothing
							}
						} else if (JSPVersion==J2EEVersionConstants.JSP_2_0_ID) { // this clause is for performance, get digester only for jsp 1.1
							try {
								TLDDigester digester = new TLDDigester(newTaglib.getTLDStream());
								String digesterJSPLevel = digester.getJSPLevel();
								if (digesterJSPLevel == null || !digesterJSPLevel.equals(J2EEVersionConstants.VERSION_2_0_TEXT) || !digesterJSPLevel.equals(J2EEVersionConstants.VERSION_1_2_TEXT)
										|| !digesterJSPLevel.equals(J2EEVersionConstants.VERSION_1_1_TEXT))// If JSP2.0 then tld better be 1.1 or 1.2 and not 2.0 defect CMVC 217548
									canAddTaglibTLD = false;
							} catch (ZipException e) {
								//Do nothing
							} catch (IOException e) {
								//Do nothing
							} catch (CoreException e) {
								//Do nothing
							}
						}
						if (canAddTaglibTLD)
							results.add(newTaglib);
						addedJar = true; // no matter whether we added the entry or not we cannot add any other entry either.
					}
				}
			}
		}
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.web.taglib.AbstractTaglibLocator#searchFile(org.eclipse.core.resources.IFile)
	 */
	protected ITaglibInfo[] searchFile(IFile file) {
		// do nothing
		return new ITaglibInfo[0];
	}

	protected boolean isTaglibTLD(IPath tldPath) {
		return tldPath.lastSegment().equalsIgnoreCase("taglib.tld"); //$NON-NLS-1$
	}

}