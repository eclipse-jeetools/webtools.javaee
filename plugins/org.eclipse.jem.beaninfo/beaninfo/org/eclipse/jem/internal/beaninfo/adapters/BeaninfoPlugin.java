package org.eclipse.jem.internal.beaninfo.adapters;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeaninfoPlugin.java,v $
 *  $Revision: 1.4 $  $Date: 2004/02/24 19:33:46 $ 
 */


import java.util.*;

import org.eclipse.core.runtime.*;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.logger.proxyrender.EclipseLogger;

import org.eclipse.jem.internal.proxy.core.ProxyPlugin;

/**
 * The plugin class for the org.eclipse.jem.internal.proxy.core plugin.
 */

public class BeaninfoPlugin extends Plugin {
	public static final String PI_BEANINFO = "org.eclipse.jem.beaninfo";	// Plugin ID, used for QualifiedName. //$NON-NLS-1$
	public static final String PI_BEANINFO_REGISTRATIONS = "registrations";	// ID of the registrations extension point. //$NON-NLS-1$
	public static final String PI_BEANINFO_OVERRIDES = "overrides";	// ID of the overrides extension point. //$NON-NLS-1$
	
	public static final String PI_VARIABLE = "variable";	// <variable> in extension point. //$NON-NLS-1$
	public static final String PI_PATH = "path";	// <path="..."> in extension point. //$NON-NLS-1$
	public static final String PI_CONTRIBUTOR = "contributor";	// <contributor ...> or contributor="..." in extension point //$NON-NLS-1$

	public static final String PI_PACKAGE = "package";	// <package="..." in extension point. //$NON-NLS-1$
	
	private static BeaninfoPlugin BEANINFO_PLUGIN = null;
		
	public BeaninfoPlugin(IPluginDescriptor pluginDescriptor) {	
		super(pluginDescriptor);
		BEANINFO_PLUGIN = this;
	}
	
	/**
	 * Accessor method to get the singleton plugin.
	 */
	public static BeaninfoPlugin getPlugin() {
		return BEANINFO_PLUGIN;
	}
	
	
	
	// Map of registered beaninfos, mapped key is path, value is BeaninfoRegistration[].
	// It is allowed to have more than one. They will be concatenated together when used.
	private Map beaninfos = null;
	private Map getBeaninfoRegistrations() {
		if (beaninfos == null) {
			beaninfos = new HashMap(30);
			processRegistrationExtensionPoint();
		}
		return beaninfos;
	}
	
	// Corresponding arrays, that map one to the other.
	// Fragments is the array of package fragments, as paths (per plugin), that are registered.
	// Paths are the corresponding array of file paths array (as strings). They are retrieved
	// by the same index as the matching fragment index and plugin index.
	private IPath[][] fragments;
	private String[][][] paths;
	
	public class OverridePathSearch {
		protected IPath packagePath;
		protected int nextPlugin = 0;
		protected int nextIndex = 0;
		protected int matchIndex = -1;
		
		protected OverridePathSearch(IPath packagePath) {
			this.packagePath = packagePath;
			if (fragments == null)
				processOverridesExtensionPoint();
		}
		
		/**
		 * Get the next array of paths that match.
		 * Returns null if there are no more.
		 */
		public String[] getNextPath() {
			while (nextPlugin < fragments.length) {
				while (nextIndex < fragments[nextPlugin].length) {
					matchIndex = nextIndex++;
					if (fragments[nextPlugin][matchIndex].isPrefixOf(packagePath))
						return paths[nextPlugin][matchIndex];
				}
				nextPlugin++;	// Step up to next plugin
				nextIndex = 0;
			}
			matchIndex = -1;	// Not found
			return null;
		}
		
		/**
		 * Return the unmatched portion of the package name
		 * for the current entry. For example, if the
		 * package was "java.awt" and the current match
		 * path was "java", then the unmatched portion
		 * would be returned as "awt". If it matched exactly,
		 * it would return "". And if this is being called
		 * when it shouldn't it will return null. It will
		 * return it in directory form. (i.e. "xyz/qxr").
		 */
		public String getUnmatchedPath() {
			if (matchIndex != -1) {
				IPath match = fragments[nextPlugin][matchIndex];
				return packagePath.removeFirstSegments(match.segmentCount()).toString();
			}
			
			return null;
		}		
	}
			

	/**
	 * Return the searcher for the given package name.
	 */
	public OverridePathSearch getOverrideSearch(String packageName) {
		return new OverridePathSearch(new Path(packageName.replace('.', '/')));
	}
	
	private Logger logger;
	public Logger getLogger() {
		if (logger == null)
			logger = EclipseLogger.getEclipseLogger(this);
		return logger;
	}
	
	
	/**
	 * Register one registration for the path.
	 * The path must be a classpath variable for the first segment. It won't be looked for otherwise.
	 * If it is only one segment long, then it is for the variable itself, and it will be used
	 * for all paths that start with that variable. This allows several different jars within 
	 * the variable's path to share the same beaninfo registration information.
	 */
	public void registerBeaninfoRegistration(IPath path, BeaninfoRegistration registration) {
		BeaninfoRegistration[] registered = (BeaninfoRegistration[]) getBeaninfoRegistrations().get(path);
		if (registered == null)
			registered = new BeaninfoRegistration[] {registration};
		else {
			BeaninfoRegistration[] old = registered;
			registered = new BeaninfoRegistration[old.length+1];
			System.arraycopy(old, 0, registered, 0, old.length);
			registered[old.length] = registration;
		}
		
		getBeaninfoRegistrations().put(path, registered);
	}
	
	/**
	 * Register multiple registrations for the path.
	 * The path must be a classpath variable for the first segment. It won't be looked for otherwise.
	 * If it is only one segment long, then it is for the variable itself, and it will be used
	 * for all paths that start with that variable. This allows several different jars within 
	 * the variable's path to share the same beaninfo registration information.
	 */	
	public void registerBeaninfoRegistration(IPath path, BeaninfoRegistration[] registrations) {
		BeaninfoRegistration[] registered = (BeaninfoRegistration[]) getBeaninfoRegistrations().get(path);
		if (registered == null) {
			registered = new BeaninfoRegistration[registrations.length];
			System.arraycopy(registrations, 0, registered, 0, registrations.length);
		} else {
			BeaninfoRegistration[] old = registered;
			registered = new BeaninfoRegistration[old.length+registrations.length];
			System.arraycopy(old, 0, registered, 0, old.length);
			System.arraycopy(registrations, 0, registered, old.length, registrations.length);
		}
		
		getBeaninfoRegistrations().put(path, registered);
	}
	
	/**
	 * Return the registrations for a specified path. Return null if not registered.
	 */
	public BeaninfoRegistration[] getRegistrations(IPath path) {
		return (BeaninfoRegistration[]) getBeaninfoRegistrations().get(path);
	}

	protected void processRegistrationExtensionPoint() {
		// Read in the registration information from the extensions.
		// We'll first gather together in Lists, and then send as arrays at one time to register them.
		HashMap registrations = new HashMap();
		IExtension[] extensions = getDescriptor().getExtensionPoint(PI_BEANINFO_REGISTRATIONS).getExtensions();
		// Need to be in plugin order so that first ones processed have no dependencies on others.
		HashMap pluginDescriptorsToExtensions = new HashMap(extensions.length);
		for (int i = 0; i < extensions.length; i++) {
			IPluginDescriptor desc = extensions[i].getDeclaringPluginDescriptor();
			IExtension[] ext = (IExtension[]) pluginDescriptorsToExtensions.get(desc);
			if (ext == null)
				pluginDescriptorsToExtensions.put(desc, new IExtension[] {extensions[i]});
			else {
				// More than one extension defined in this plugin.
				IExtension[] newExt = new IExtension[ext.length + 1];
				System.arraycopy(ext, 0, newExt, 0, ext.length);
				newExt[newExt.length-1] = extensions[i];
				pluginDescriptorsToExtensions.put(desc, newExt);
			}
		}
		
		IPluginDescriptor[] ordered = ProxyPlugin.orderPlugins(pluginDescriptorsToExtensions.keySet());
		for (int i = 0; i < ordered.length; i++) {
			IExtension[] exts = (IExtension[]) pluginDescriptorsToExtensions.get(ordered[i]);
			for (int j = 0; j < exts.length; j++) {			
				IConfigurationElement[] configs = exts[j].getConfigurationElements();
				for (int k = 0; k < configs.length; k++) {
					IConfigurationElement iConfigurationElement = configs[k];
					if (PI_VARIABLE.equals(iConfigurationElement.getName())) {
						boolean hasContributor = iConfigurationElement.getAttributeAsIs(PI_CONTRIBUTOR) != null || iConfigurationElement.getChildren(PI_CONTRIBUTOR).length > 0;
						String varpathstr = iConfigurationElement.getAttributeAsIs(PI_PATH);
						if (varpathstr == null)
							continue;	// Not proper format.
						IPath varpath = new Path(varpathstr);
						List varentry = (List) registrations.get(varpath);
						if (varentry == null) {
							varentry = new ArrayList(1);
							registrations.put(varpath, varentry);
						}
						IConfigurationElement[] beaninfos = iConfigurationElement.getChildren(BeaninfoNature.sBeaninfos);
						for (int l = 0; l < beaninfos.length; l++) {
							IConfigurationElement root = beaninfos[l];
							BeaninfoRegistration reg = new BeaninfoRegistration(BeaninfosDoc.readEntry(new ConfigurationElementReader(), root, null));
							if (hasContributor) {
								reg.setVariableElement(iConfigurationElement);
								hasContributor = false;	// Only the first one for this variable needs it. The others would only be dups.
							}
							varentry.add(reg);
						}
					}
				}
			}
		}
		
		// Now we've processed all of the extensions.
		Iterator regItr = registrations.entrySet().iterator();
		while (regItr.hasNext()) {
			Map.Entry entry = (Map.Entry) regItr.next();
			List registrationsList = (List) entry.getValue();
			registerBeaninfoRegistration((IPath) entry.getKey(), (BeaninfoRegistration[]) registrationsList.toArray(new BeaninfoRegistration[registrationsList.size()]));			
		}
	}
	
	protected void processOverridesExtensionPoint() {
		// We are processing this once because it is accessed often (once per introspected class per project).
		// This can add up so we get it together once here.
		// Read in the overrides information from the extensions.
		// Read in the registration information from the extensions.
		IExtension[] extensions = getDescriptor().getExtensionPoint(PI_BEANINFO_OVERRIDES).getExtensions();
		// Need to be in plugin order so that first ones processed have no dependencies on others.
		HashMap pluginDescriptorsToExtensions = new HashMap(extensions.length);
		for (int i = 0; i < extensions.length; i++) {
			IPluginDescriptor desc = extensions[i].getDeclaringPluginDescriptor();
			IExtension[] ext = (IExtension[]) pluginDescriptorsToExtensions.get(desc);
			if (ext == null)
				pluginDescriptorsToExtensions.put(desc, new IExtension[] {extensions[i]});
			else {
				// More than one extension defined in this plugin.
				IExtension[] newExt = new IExtension[ext.length + 1];
				System.arraycopy(ext, 0, newExt, 0, ext.length);
				newExt[newExt.length-1] = extensions[i];
				pluginDescriptorsToExtensions.put(desc, newExt);
			}
		}
		
		// Now order them so we process in required order.
		HashMap overrideMap = new HashMap();	// Working override map per plugin
		IPluginDescriptor[] ordered = ProxyPlugin.orderPlugins(pluginDescriptorsToExtensions.keySet());
		fragments = new IPath[ordered.length][];
		paths = new String[ordered.length][][];
		for (int i = 0; i < ordered.length; i++) {
			IExtension[] exts = (IExtension[]) pluginDescriptorsToExtensions.get(ordered[i]);
			overrideMap.clear();
			for (int j = 0; j < exts.length; j++) {			
				IConfigurationElement[] configs = exts[j].getConfigurationElements();
				for (int k = 0; k < configs.length; k++) {
					IConfigurationElement iConfigurationElement = configs[k];
					// Don't care about the element name, we show <overrides...> in the example, but really don't care. It just needs path and package.
					String packageName = iConfigurationElement.getAttributeAsIs(PI_PACKAGE);
					String path = iConfigurationElement.getAttributeAsIs(PI_PATH);			
					if (packageName != null && packageName.length() > 0 && path != null && path.length() > 0) {
						IPath packPath = new Path(packageName.replace('.', '/'));
						if (path.charAt(path.length()-1) != '/' && path.charAt(path.length()-1) != '\\')
							path += '/';
						String[] sofar = (String[]) overrideMap.get(packPath);
						if (sofar == null)
							sofar = new String[] {path};
						else {
							String[] old = sofar;
							sofar = new String[old.length+1];
							System.arraycopy(old, 0, sofar, 0, old.length);
							sofar[old.length] = path;
						}
						overrideMap.put(packPath, sofar);
					}
				}
			}
			
			// Now construct the arrays for this plugin
			int size = overrideMap.size();
			fragments[i] = new IPath[size];
			paths[i] = new String[size][];
			Iterator itr = overrideMap.entrySet().iterator();
			int ii=-1;
			while (itr.hasNext()) {
				Map.Entry entry = (Map.Entry) itr.next();
				fragments[i][++ii] = (IPath) entry.getKey();
				paths[i][ii] = (String[]) entry.getValue();
			}
		}
	}
}


