/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*


 */
package org.eclipse.jem.internal.proxy.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.pde.core.plugin.*;

/**
 * Contribute to classpath PDE entries that are needed for a launch.
 * <p>
 * This is a special class that should not be referenced directly from code. Reference it through the interface that it implements.
 * 
 * @see org.eclipse.jem.internal.proxy.core.IPDEContributeClasspath
 * @since 1.0.2
 */
class PDEContributeClasspath implements IPDEContributeClasspath {

	public void getPDEContributions(IConfigurationContributionController controller, IConfigurationContributionInfo info) throws CoreException {
		if (!info.getPluginIds().isEmpty()) {
			Collection pluginIds = info.getPluginIds().keySet();

			IPluginModelBase[] allModels = PluginRegistry.getAllModels();
			List fragments = new ArrayList();
			for (int i = 0; i < allModels.length; i++) {
				if (allModels[i].isFragmentModel()) {
					fragments.add(allModels[i]);
				}
			}

			for (Iterator itr = fragments.iterator(); itr.hasNext();) {
				IFragmentModel fragmentModel = (IFragmentModel) itr.next();
				IFragment fragment = fragmentModel.getFragment();
				if (pluginIds.contains(fragment.getPluginId())) {
					// We'll do a cheat for now and assume fragment is for same version of plugin. PDECore actually
					// checks the version of the fragment against the version of the plugin to see they are for each
					// other, but we'll just assume they are for now. Change this later if we actually do run into this case.
					IResource resource = fragment.getModel().getUnderlyingResource();
					if (resource != null) {
						IProject fragProject = resource.getProject();
						if (fragProject.hasNature(JavaCore.NATURE_ID)) {
							controller.contributeProject(fragProject);
						}
						continue;
					}

					IPluginLibrary[] libraries = fragment.getLibraries();
					for (int j = 0; j < libraries.length; j++) {
						IPluginLibrary library = libraries[j];
						String name = library.getName();
						String expandedName = expandLibraryName(name);

						IPluginModelBase model = library.getPluginModel();
						URL url = getPath(model, expandedName);
						if (url != null)
							controller.contributeClasspath(url, IConfigurationContributionController.APPEND_USER_CLASSPATH);
					}
				}
			}
		}

		return;
	}

	private URL getPath(IPluginModelBase model, String libraryName) {
		try {
			IResource resource = model.getUnderlyingResource();
			if (resource != null) {
				IResource jarFile = resource.getProject().findMember(libraryName);
				if (jarFile != null)
					return jarFile.getFullPath().toFile().toURL();
			} else {
				File file = new File(model.getInstallLocation(), libraryName);
				if (file.exists())
					return file.toURL();
			}
		} catch (MalformedURLException e) {
			ProxyPlugin.getPlugin().getLogger().log(e, Level.WARNING);
		}
		return null;
	}

	/**
	 * This utility method was originally copied from method of same name, in org.eclipse.pde.internal.core.ClasspathUtilCore. It was not API, and
	 * seems safe enough to copy here. But, see https://bugs.eclipse.org/bugs/show_bug.cgi?id=182555 
	 * in case there is ever an API method provided to codify these Eclipse rules. 
	 */
	private static String expandLibraryName(String source) {
		if (source == null || source.length() == 0)
			return ""; //$NON-NLS-1$
		if (source.indexOf("$ws$") != -1) //$NON-NLS-1$
			source = source.replaceAll("\\$ws\\$", //$NON-NLS-1$
					"ws" + IPath.SEPARATOR + TargetPlatform.getWS()); //$NON-NLS-1$
		if (source.indexOf("$os$") != -1) //$NON-NLS-1$
			source = source.replaceAll("\\$os\\$", //$NON-NLS-1$
					"os" + IPath.SEPARATOR + TargetPlatform.getOS()); //$NON-NLS-1$
		if (source.indexOf("$nl$") != -1) //$NON-NLS-1$
			source = source.replaceAll("\\$nl\\$", //$NON-NLS-1$
					"nl" + IPath.SEPARATOR + TargetPlatform.getNL()); //$NON-NLS-1$
		if (source.indexOf("$arch$") != -1) //$NON-NLS-1$
			source = source.replaceAll("\\$arch\\$", //$NON-NLS-1$
					"arch" + IPath.SEPARATOR + TargetPlatform.getOSArch()); //$NON-NLS-1$
		return source;
	}
}
