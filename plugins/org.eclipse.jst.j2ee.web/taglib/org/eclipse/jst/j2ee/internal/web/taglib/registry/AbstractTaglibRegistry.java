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
package org.eclipse.jst.j2ee.internal.web.taglib.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.web.locator.WebProjectTaglibLocator;
import org.eclipse.jst.j2ee.internal.web.locator.WebXMLTaglibLocator;
import org.eclipse.jst.j2ee.web.taglib.ITaglibInfo;
import org.eclipse.jst.j2ee.web.taglib.ITaglibLocator;
import org.eclipse.jst.j2ee.web.taglib.ITaglibRegistry;
import org.eclipse.jst.j2ee.web.taglib.ITaglibRegistryListener;


/**
 * @version 1.0
 * @author
 */
abstract public class AbstractTaglibRegistry implements ITaglibRegistry {
	private static final boolean debug = false;

	protected IProject project;
	protected Vector listeners;
	protected Vector taglibs; // of type ITaglibInfo
	protected ITaglibLocator[] locators;
	protected boolean needsRefresh = false;

	public AbstractTaglibRegistry(IProject project) {
		this.project = project;
		initialize();
		refresh();
	}

	protected void initialize() {
		this.locators = new ITaglibLocator[]{new WebProjectTaglibLocator(this.project), new WebXMLTaglibLocator(this.project)};
	}

	/*
	 * @see ITaglibRegistry#addTaglibRegistryListener(ITaglibRegistryListener)
	 */
	protected void addTaglib(ITaglibInfo taglib) {
		// Add web.xml entries at the top of the list so that they are
		// always returned first from the getTaglib(uri) method.
		if (taglib.isWebXMLEntry())
			this.taglibs.add(0, taglib);
		else
			this.taglibs.add(taglib);
	}

	public void addTaglibRegistryListener(ITaglibRegistryListener listener) {
		if (this.listeners == null)
			this.listeners = new Vector();
		this.listeners.add(listener);
	}

	/*
	 * @see ITaglibRegistry#getTaglib(IPath)
	 */
	public ITaglibInfo[] getTaglibs(IPath location) {
		Vector results = getTaglibsVector(location);
		return (ITaglibInfo[]) results.toArray(new ITaglibInfo[results.size()]);
	}

	protected Vector getTaglibsVector(IPath location) {
		Vector results = new Vector();
		Vector tTaglibs = primGetTaglibs();

		boolean isWebXML = isWebXMLFile(location);

		// Start at the beginning of the taglib list and return the first
		// one that matches the location passed in.
		for (Iterator iter = tTaglibs.iterator(); iter.hasNext();) {
			ITaglibInfo taglib = (ITaglibInfo) iter.next();
			if (isWebXML) {
				if (taglib.isWebXMLEntry())
					results.add(taglib);
			} else if (taglib.getLocation().equals(location))
				results.add(taglib);
		}
		return results;
	}

	protected Vector getExistingTaglibs(IPath location) {
		Vector results = new Vector();
		Vector tTaglibs = this.taglibs;

		boolean isWebXML = isWebXMLFile(location);

		// Start at the beginning of the taglib list and return the first
		// one that matches the location passed in.
		for (Iterator iter = tTaglibs.iterator(); iter.hasNext();) {
			ITaglibInfo taglib = (ITaglibInfo) iter.next();
			if (isWebXML) {
				if (taglib.isWebXMLEntry())
					results.add(taglib);
			} else if (taglib.getLocation().equals(location) && !taglib.isWebXMLEntry())
				results.add(taglib);
		}
		return results;
	}

	public IProject getProject() {
		return this.project;
	}

	/*
	 * @see ITaglibRegistry#getRecommendedTaglibs()
	 */
	public ITaglibInfo[] getRecommendedTaglibs() {
		// we pass false because we dont want to show all entries per resource,just one
		return getValidTaglibs(false);
	}

	/*
	 * @see ITaglibRegistry#getVisibleTaglibs()
	 */
	public ITaglibInfo[] getVisibleTaglibs() {
		// we pass true because we want to show all entries that are valid uri's for a resource
		return getValidTaglibs(true);
	}


	protected ITaglibInfo[] getValidTaglibs(boolean showAllEntriesPerResource) {
		Vector allTaglibs = primGetTaglibs();
		Vector results = new Vector(allTaglibs.size());
		Set locationSet = new HashSet();
		Set uriSet = new HashSet();

		// first pass web.xml entries
		for (Iterator iter = allTaglibs.iterator(); iter.hasNext();) {
			ITaglibInfo iTaglib = (ITaglibInfo) iter.next();
			if (iTaglib.isWebXMLEntry()) {
				if (!uriSet.contains(iTaglib.getURI())) {
					// show all web.xml entries whether they are mapped to the same resource
					//	if(showAllEntriesPerResource ||
					// !locationSet.contains(iTaglib.getLocation())){
					if (!locationSet.contains(iTaglib.getLocation()))
						locationSet.add(iTaglib.getLocation());
					results.add(iTaglib);
					//					}
					uriSet.add(iTaglib.getURI());
				}
			}
		}

		// second pass uri in tld entries
		for (Iterator iter = allTaglibs.iterator(); iter.hasNext();) {
			ITaglibInfo iTaglib = (ITaglibInfo) iter.next();
			if (iTaglib.isURIFromTLD() && !iTaglib.isWebXMLEntry()) {
				if (!uriSet.contains(iTaglib.getURI())) {
					if (!iTaglib.isInJar()) {
						// i.e its a tld file in the web application
						if (showAllEntriesPerResource || !locationSet.contains(iTaglib.getLocation())) {
							if (!locationSet.contains(iTaglib.getLocation()))
								locationSet.add(iTaglib.getLocation());
							results.add(iTaglib);
						}
					} else { // i.e its a tld file in a JAR file in the web application
						String jarRelativePath = iTaglib.getTLDLocation().toString();
						if (jarRelativePath.equalsIgnoreCase(IWebNatureConstants.META_INFO_DIRECTORY + "/taglib.tld")) { //$NON-NLS-1$
							if (!iTaglib.isServerContribution() // dont add META-INF/taglib.tld that
																// comes from a server
										&& (showAllEntriesPerResource || !locationSet.contains(iTaglib.getLocation()))) {
								if (!locationSet.contains(iTaglib.getLocation()))
									locationSet.add(iTaglib.getLocation());
								results.add(iTaglib);
							}
						} else { // if not metainf/taglib.tld
							// we want to include all the non meta-inf entries in the results
							results.add(iTaglib);
						}
					}
					uriSet.add(iTaglib.getURI());
				} // if !uriSet.contains
			} // if .isURIFromTLD
		}

		// third pass for entries whose uri's are resource paths
		for (Iterator iter = allTaglibs.iterator(); iter.hasNext();) {
			ITaglibInfo iTaglib = (ITaglibInfo) iter.next();
			if (!iTaglib.isURIFromTLD() && !iTaglib.isWebXMLEntry() && !iTaglib.isDirectoryEntry()) {
				if (!uriSet.contains(iTaglib.getURI())) {
					if (showAllEntriesPerResource || !locationSet.contains(iTaglib.getLocation())) {
						results.add(iTaglib);
						locationSet.add(iTaglib.getLocation());
					}
				}
			}
		}

		// fourth pass for entries whose uri's are resource paths & directory entries
		for (Iterator iter = allTaglibs.iterator(); iter.hasNext();) {
			ITaglibInfo iTaglib = (ITaglibInfo) iter.next();
			if (iTaglib.isDirectoryEntry()) {
				IPath directoryLoc = iTaglib.getLocation();
				if (!uriSet.contains(iTaglib.getURI())) {
					boolean canAdd = true;
					for (Iterator iterator = locationSet.iterator(); iterator.hasNext();) {
						IPath locationPath = (IPath) iterator.next();

						if (directoryLoc.matchingFirstSegments(locationPath) == locationPath.segmentCount() - 1) {
							canAdd = false;
							break;
						}
					}
					if (canAdd) {// don't add directories that have tld's in them
						results.add(iTaglib);
						locationSet.add(iTaglib.getLocation());
					}
				}
			}
		}


		return (ITaglibInfo[]) results.toArray(new ITaglibInfo[results.size()]);
	}

	protected Vector primGetTaglibs() {
		if (this.needsRefresh)
			refresh();
		return this.taglibs;
	}

	/*
	 * @see ITaglibRegistry#getTaglibs()
	 */
	public ITaglibInfo[] getTaglibs() {
		Vector tTaglibs = primGetTaglibs();
		return (ITaglibInfo[]) tTaglibs.toArray(new ITaglibInfo[tTaglibs.size()]);
	}

	/*
	 * @see ITaglibRegistry#getTaglib(String)
	 */
	public ITaglibInfo getTaglib(String uri) {
		List tTaglibs = Arrays.asList(getVisibleTaglibs());
		//commented out because we dont want to return the taglib for resources that are not
		// visible
		// or are not visible because of precedence //primGetTaglibs();
		for (Iterator iter = tTaglibs.iterator(); iter.hasNext();) {
			ITaglibInfo taglib = (ITaglibInfo) iter.next();
			if (taglib.getURI().equals(uri))
				return taglib;
		}
		return null;
	}

	protected void fireTaglibAdded(ITaglibInfo addedTaglib) {
		if (debug)
			System.out.println("TaglibRegistry: taglib added: " + addedTaglib); //$NON-NLS-1$

		if (this.listeners == null)
			return;
		for (Iterator iter = this.listeners.iterator(); iter.hasNext();) {
			ITaglibRegistryListener listener = (ITaglibRegistryListener) iter.next();
			listener.taglibAdded(addedTaglib);
		}
	}

	protected void fireTaglibChanged(ITaglibInfo changedTaglib) {
		if (debug)
			System.out.println("TaglibRegistry: taglib changed: " + changedTaglib); //$NON-NLS-1$

		if (this.listeners == null)
			return;
		for (Iterator iter = this.listeners.iterator(); iter.hasNext();) {
			ITaglibRegistryListener listener = (ITaglibRegistryListener) iter.next();
			listener.taglibChanged(changedTaglib);
		}
	}

	protected void fireTaglibRemoved(ITaglibInfo removedTaglib) {
		if (debug)
			System.out.println("TaglibRegistry: taglib removed: " + removedTaglib); //$NON-NLS-1$

		if (this.listeners == null)
			return;
		for (Iterator iter = this.listeners.iterator(); iter.hasNext();) {
			ITaglibRegistryListener listener = (ITaglibRegistryListener) iter.next();
			listener.taglibRemoved(removedTaglib);
		}
	}

	abstract protected boolean isWebXMLFile(IPath filePath);

	protected boolean isTaglibTLD(IPath tldPath) {
		return tldPath.lastSegment().equalsIgnoreCase("taglib.tld"); //$NON-NLS-1$
	}

	public void processResourceChanged(IResourceDelta delta) {
		int kind = delta.getKind();
		IResource resource = delta.getResource();
		int flags = delta.getFlags();
		if (delta.getKind() == IResourceDelta.CHANGED && flags == IResourceDelta.NO_CHANGE)
			return;

		// If the websettings file changed, refresh everything in case the context root
		// was modified or the J2EE level was changed.
		if (requiresFullUpdate(delta)) {
			updateAll();
			return;
		}
		// Make sure resource is not null
		if (resource == null)
			return;

		// Only process resource changes that are under the refreshRoot.
		IPath resourcePath = resource.getProjectRelativePath();

		IResource refreshRoot = getRefreshRoot();
		// RATLC00974251 for a java project before build is done, output location does not exist
		if (refreshRoot == null)
			return;

		IPath refreshPath = refreshRoot.getProjectRelativePath();
		if (resourcePath.matchingFirstSegments(refreshPath) != refreshPath.segmentCount())
			return;

		if (debug)
			System.out.println("TaglibRegistry(" + this.project.getName() + ").resourceChanged: " + delta); //$NON-NLS-1$ //$NON-NLS-2$

		if (kind == IResourceDelta.CHANGED || kind == IResourceDelta.REPLACED) {
			handleResourceModified(resource);
		} else if (kind == IResourceDelta.REMOVED) {
			handleResourceRemoved(resource);
		} else if (kind == IResourceDelta.ADDED) {
			handleResourceAdded(resource);
		} else {
			if (debug)
				System.out.println("TaglibRegistry.resourceChanged:  UNHANDLED DELTA TYPE"); //$NON-NLS-1$
		}
	}

	protected void handleResourceAdded(IResource resource) {
		handleResourceModified(resource);
	}

	protected void handleResourceModified(IResource resource) {
		try {
			resource.accept(new IResourceVisitor() {
				public boolean visit(IResource aresource) throws CoreException {
					if (aresource.getType() != IResource.ROOT && AbstractTaglibRegistry.this.project != aresource.getProject())
						return false;

					if (aresource.getType() == IResource.FILE) {
						processFile(aresource);
						return false;
					}
					if (shouldProcessDirectory(aresource)) {
						processDirectory(aresource);
					}
					return (canVisitResource(aresource));

				}


			});
		} catch (CoreException e) {
			Logger.getLogger().log(e);
		}
	}

	/**
	 * Return true if the resource and its children should be visited
	 * 
	 * @param resource
	 * @return
	 */
	protected boolean canVisitResource(IResource resource) {
		if (resource.getType() != IResource.ROOT && this.project != resource.getProject())
			return false;
		if (resource.getType() != IResource.FILE)
			return true;
		return true;
	}

	protected void handleResourceRemoved(IResource resource) {
		IPath location = resource.getProjectRelativePath();
		ITaglibInfo[] removedTaglibs = removeTaglibs(location);
		if (removedTaglibs != null) {
			for (int i = 0; i < removedTaglibs.length; i++) {
				ITaglibInfo iTaglibInfo = removedTaglibs[i];
				if (debug)
					System.out.println("TaglibRegistry.handleResourceRemoved taglib removed: " + iTaglibInfo); //$NON-NLS-1$
				fireTaglibRemoved(iTaglibInfo);
			}
		}
	}

	protected IResource getRefreshRoot() {
		return getProject();
	}

	public void refresh() {
		this.taglibs = new Vector();
		IResource refreshRoot = getRefreshRoot();

		// RATLC00974251 for a java project before build is done, output location does not exist
		if (refreshRoot != null)
			handleResourceAdded(refreshRoot);

		this.needsRefresh = false;
	}

	protected void updateAll() {
		try {
			IResource refreshRoot = getRefreshRoot();

			// RATLC00974251 for a java project before build is done, output location does not exist
			if (refreshRoot == null)
				return;
			refreshRoot.accept(new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					handleResourceModified(resource);
					return true;
				}
			});
		} catch (CoreException e) {
			//Do nothing
		}
	}

	/**
	 * Remove all the taglibs associated with the location.
	 * 
	 * @param location
	 * @return ITaglibInfo[] The taglibs that are removed. Null if none are removed.
	 */
	protected ITaglibInfo[] removeTaglibs(IPath location) {
		List results = new ArrayList();

		boolean isWebXML = isWebXMLFile(location);
		for (int i = 0; i < this.taglibs.size(); i++) {
			ITaglibInfo taglib = (ITaglibInfo) this.taglibs.get(i);
			if (isWebXML) {
				if (taglib.isWebXMLEntry()) {
					removeTaglib(i);
					results.add(taglib);
					i--;
				}
			} else if (taglib.getLocation().equals(location)) {
				removeTaglib(i);
				results.add(taglib);
				i--;
			}
		}
		return results.isEmpty() ? null : (ITaglibInfo[]) results.toArray(new ITaglibInfo[results.size()]);
	}

	abstract protected boolean requiresFullUpdate(IResourceDelta delta);

	protected void removeTaglib(int i) {
		this.taglibs.remove(i);
	}

	protected ITaglibInfo[] searchForTaglibs(IResource resource) {
		ArrayList results = new ArrayList();
		for (int i = 0; i < this.locators.length; i++) {
			ITaglibLocator tLocator = this.locators[i];
			ITaglibInfo[] tTaglibs = tLocator.search(resource);
			if (tTaglibs != null) {
				for (int j = 0; j < tTaglibs.length; j++) {
					results.add(tTaglibs[j]);
				}
			}
		}
		return (ITaglibInfo[]) results.toArray(new ITaglibInfo[results.size()]);
	}

	/*
	 * @see ITaglibRegistry#removeTaglibRegistryListener(ITaglibRegistryListener)
	 */
	public void removeTaglibRegistryListener(ITaglibRegistryListener listener) {
		if (this.listeners == null)
			return;
		this.listeners.remove(listener);
	}

	/*
	 * @see Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("TaglibRegistry: \n"); //$NON-NLS-1$
		for (Iterator iter = this.taglibs.iterator(); iter.hasNext();) {
			ITaglibInfo taglib = (ITaglibInfo) iter.next();
			sb.append("\t"); //$NON-NLS-1$
			sb.append(taglib);
			sb.append("\n"); //$NON-NLS-1$
		}
		return sb.toString();
	}

	protected void processDirectory(IResource resource) {
		// do nothing
	}

	protected boolean shouldProcessDirectory(IResource resource) {
		return true;
	}

	/**
	 * @param resource
	 */
	protected void processFile(IResource resource) {
		Vector existingTaglibs = getExistingTaglibs(resource.getProjectRelativePath());
		ITaglibInfo[] newTaglibs = searchForTaglibs(resource);

		for (int i = 0; i < newTaglibs.length; i++) {
			ITaglibInfo newTaglib = newTaglibs[i];
			int existingIndex = existingTaglibs.indexOf(newTaglib);
			if (existingIndex >= 0) {
				// If its an exact match, leave it in alone the taglibs registry
				// but fire a taglibChangedEvent.
				if (!newTaglib.isWebXMLEntry())
					fireTaglibChanged(newTaglib);
				existingTaglibs.remove(existingIndex);
			} else {
				// The taglib did not exist previously in the list, add it and
				// fire a taglibAdded event.
				addTaglib(newTaglib);
				fireTaglibAdded(newTaglib);
			}
		}

		for (Iterator iter = existingTaglibs.iterator(); iter.hasNext();) {
			ITaglibInfo remainingTaglib = (ITaglibInfo) iter.next();
			this.taglibs.remove(remainingTaglib);
			fireTaglibRemoved(remainingTaglib);
		}
	}

}