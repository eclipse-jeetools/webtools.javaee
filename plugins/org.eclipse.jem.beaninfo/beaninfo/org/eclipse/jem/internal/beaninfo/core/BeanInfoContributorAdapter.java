/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeanInfoContributorAdapter.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/22 23:49:10 $ 
 */
package org.eclipse.jem.internal.beaninfo.core;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.jem.internal.beaninfo.core.BeaninfoPlugin.IContributorOverrideRunnable;
import org.eclipse.jem.internal.proxy.core.IConfigurationContributionInfo;
 
/**
 * A default implementation of IBeanInfoContributor for users to subclass. Default does nothing.
 * 
 * @since 1.0.0
 */
public class BeanInfoContributorAdapter implements IBeanInfoContributor {
	
	/**
	 * An empty array of BeaninfoEntry. Available for subclasses to return if they
	 * decide there is nothing to return.
	 */
	public static final BeaninfoEntry[] EMPTY_BEANINFO_ENTRIES = new BeaninfoEntry[0];
	
	/**
	 * Return true if the given fragment is part of the package. Used by subclasses to determine
	 * if a override associated with a given fragment should be used for the package.
	 * 
	 * @param fragment
	 * @param packagePath
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected boolean isFragment(IPath fragment, IPath packagePath) {
		return fragment.isPrefixOf(packagePath);
	}
	
	/**
	 * Return the unmatched portion of the package path from a fragment. Used by subclasses
	 * to get the part of the package path that is after the fragment. This is then used to
	 * append to the path from the override to get the full path to override files for this
	 * package.
	 * 
	 * @param fragment
	 * @param packagePath
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected String getUnmatchedPath(IPath fragment, IPath packagePath) {
		return fragment.removeFirstSegments(packagePath.segmentCount()).toString();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.beaninfo.core.IBeanInfoContributor#getBeanInfoEntryContributions(org.eclipse.jem.internal.proxy.core.IConfigurationContributionInfo)
	 */
	public BeaninfoEntry[] getBeanInfoEntryContributions(IConfigurationContributionInfo info) {
		return EMPTY_BEANINFO_ENTRIES;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.beaninfo.core.IBeanInfoContributor#runOverrides(org.eclipse.core.runtime.IPath, String, org.eclipse.emf.ecore.resource.ResourceSet, org.eclipse.jem.internal.beaninfo.core.BeaninfoPlugin.IContributorOverrideRunnable)
	 */
	public void runOverrides(IPath packagePath, String className, ResourceSet rset, IContributorOverrideRunnable runnable) {
		// Default is do nothing
	}
	
	/**
	 * Subclasses can use this helper method to get the override resource from the given plugin. 
	 * 
	 * @param plugin the plugin to use.
	 * @param relativePath path of file relative to the plugin.
	 * @param resource set to load into.
	 * @param runnable the runnable that is being used for the override. It is used to determine if resource already used once.
	 * @return the resource or <code>null</code> if couldn't be loaded or if it was already used once.
	 * 
	 * @since 1.0.0
	 */
	protected Resource loadOverrideResource(IPluginDescriptor plugin, String relativePath, ResourceSet rset, BeaninfoPlugin.IContributorOverrideRunnable runnable) {
		URI uri = URI.createURI(plugin.getInstallURL().toString()+relativePath);
		if (runnable.resourceContributed(uri))
			return null;	// Already contributed once.
		Resource result = null;
		try {
			result = rset.getResource(uri, true);
		} catch (WrappedException e) {
			// FileNotFoundException is ok
			if (!(e.exception() instanceof FileNotFoundException)) {
				if (e.exception() instanceof IOException && e.getMessage() == null)
					;	// TODO remove this when bugzilla fixed so that throws FileNotFound again. https://bugs.eclipse.org/bugs/show_bug.cgi?id=51649
				else if (e.exception() instanceof CoreException
					&& ((CoreException) e.exception()).getStatus().getCode() == IResourceStatus.RESOURCE_NOT_FOUND) {
					// This is ok. Means uri_mapping not set so couldn't find in Workspace, also ok.
				} else {
					BeaninfoPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, BeaninfoPlugin.PI_BEANINFO_PLUGINID, 0, "Error loading file\"" + uri + "\"", e.exception())); //$NON-NLS-1$ //$NON-NLS-2$						
				}
			}
			// In case it happened after creating resource but during load. Need to get rid of it in the finally.
			result = null;
			Resource res = rset.getResource(uri, false);
			if (res != null)
				rset.getResources().remove(res);
		} catch (Exception e) {
			// Couldn't load it for some reason.
			BeaninfoPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, BeaninfoPlugin.PI_BEANINFO_PLUGINID, 0, "Error loading file\"" + uri + "\"", e)); //$NON-NLS-1$ //$NON-NLS-2$
			// In case it happened after creating resource but during load. Need to get rid of it in the finally.
			result = null;
			Resource res = rset.getResource(uri, false);
			if (res != null)
				rset.getResources().remove(res);
		};
		return result;
	}
	
}
