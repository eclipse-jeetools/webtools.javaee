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

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.pde.core.plugin.*;

import org.eclipse.jem.internal.proxy.core.ProxyPlugin.FoundIDs;
 

/*
 * Used for PDE Processing for the Proxy Plugin class. It will be optionally loaded
 * if PDE plugin is installed. This allows usage in an installation that doesn't have
 * PDE installed.
 * 
 * @since 1.0.2
 */
class PDEProcessForPlugin implements ProxyPlugin.IPDEProcessForPlugin {

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.ProxyPlugin.IPDEProcessForPlugin#processPlugin(org.eclipse.jdt.core.IJavaProject, java.util.Map, boolean, boolean)
	 */
	public void findPlugins(IJavaProject project, FoundIDs foundIds, boolean visible, boolean first) {
		IPluginModelBase m = PluginRegistry.findModel(project.getProject());
		if (m instanceof IPluginModel) {
			// it is a plugin, process it.
			IPlugin plugin = ((IPluginModel) m).getPlugin();
			if (foundIds.pluginIds.containsKey(plugin.getId()))
				return;	// already processed it
			foundIds.pluginIds.put(plugin.getId(), first || visible ? Boolean.TRUE : Boolean.FALSE);			
			expandPlugin(plugin, foundIds, visible, first);
		}
		return;
	}
	
	private void expandPlugin(IPluginBase plugin, FoundIDs foundIds, boolean visible, boolean first) {
		IPluginImport[] imports = plugin.getImports();
		for (int i = 0; i < imports.length; i++) {
			IPluginImport pi = imports[i];
			Boolean piValue = (Boolean) foundIds.pluginIds.get(pi.getId());
			boolean importVisible = first || (visible && pi.isReexported());
			if (piValue != null && (!importVisible || piValue.booleanValue()))
				continue;	// we already processed it, this time not visible, or this time visible and was previously visible.
			// Now either first time, or it was there before, but now visible, but this time it is visible.
			// We want it to become visible in that case. 
			foundIds.pluginIds.put(pi.getId(), importVisible ? Boolean.TRUE : Boolean.FALSE);			

			// Note: this search does not take into account the IPluginImport's version or match level
			// but neither did the previous version
			IPluginModelBase model = PluginRegistry.findModel(pi.getId());
			if (model != null && model.isEnabled()) {
				IPluginBase foundPlugin = model.getPluginBase();
				if (foundPlugin != null) {
					expandPlugin(foundPlugin, foundIds, importVisible, false);
				}
			}
		}
	}
}
