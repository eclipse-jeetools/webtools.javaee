/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.web.taglib;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IPath;

public interface ITaglibRegistry {
	/**
	 * Return the project that this taglib registry is over
	 * 
	 * @return IProject The project for this registry
	 */
	IProject getProject();

	/**
	 * Get the taglib that maps to the passed in URI. Return null if the URI is not present in any
	 * mapping.
	 * 
	 * @param uri
	 * @return ITaglibInfo The taglib that matches the uri or null
	 */
	ITaglibInfo getTaglib(String uri);

	/**
	 * Return all the taglibs that map to a given location
	 * 
	 * @param location
	 *            The project relative location
	 * @return ITaglibInfo[] All taglibs that map to the passed in location
	 */
	ITaglibInfo[] getTaglibs(IPath location);

	/**
	 * Return all the taglibs available in the project. This may include many mappings to a single
	 * taglib. A separate taglib is returned for each of mapping in this case.
	 * 
	 * @return ITaglibInfo[] All the taglibs for project
	 */
	ITaglibInfo[] getTaglibs();

	/**
	 * Returns the recommended taglibs for this project. Only one taglib is returned for each
	 * location- this is the recommended URI to use to map to this location. Note that this list
	 * filters the visible taglibs to return one per resource. Thus this list cannot be used to
	 * validate a given uri.
	 * 
	 * @return ITaglibInfo[]
	 */
	ITaglibInfo[] getRecommendedTaglibs();

	/**
	 * Returns the visible taglibs for this project. All taglib uri's that are visible are returned.
	 * This list can be used to display all the valid taglibs.
	 * 
	 * @return ITaglibInfo[]
	 */
	ITaglibInfo[] getVisibleTaglibs();


	/**
	 * Call with to allow the tablib registry a chance to update itself to correspond with the
	 * passed in resource delta. This method should not be called by clients. It is called by the
	 * ITaglibRegistryManager.
	 * 
	 * @param delta
	 */
	void processResourceChanged(IResourceDelta delta);

	/**
	 * Add a listener to the taglib registry. This listener will be notified whenever a taglib is
	 * added, removed, or changed from the project that this registry is associated with.
	 * 
	 * @param listener
	 *            The listener to notify
	 */
	void addTaglibRegistryListener(ITaglibRegistryListener listener);

	/**
	 * Remove a listener from the taglib registry.
	 * 
	 * @param listener
	 *            The listener to remove
	 */
	void removeTaglibRegistryListener(ITaglibRegistryListener listener);

	/**
	 * Refresh the registry to an up-to-date status
	 */
	public void refresh();
}