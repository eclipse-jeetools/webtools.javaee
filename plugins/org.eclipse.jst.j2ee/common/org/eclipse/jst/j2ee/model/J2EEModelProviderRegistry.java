/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2005 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */
package org.eclipse.jst.j2ee.model;

import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.jem.util.RegistryReader;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class J2EEModelProviderRegistry extends RegistryReader {

	static final String EXTENSION_NAME = "J2EEModelProvider"; //$NON-NLS-1$
	static final String ELEMENT_providers = "provider"; //$NON-NLS-1$
	static final String MODEL_PROVIDER = "modelProvider"; //$NON-NLS-1$
	static final String PROVIDER_FACTORY_CLASS = "factoryClass"; //$NON-NLS-1$
	static final String PROVIDER_PRIORITY = "priority"; //$NON-NLS-1$
	static final String PROVIDER_FACET = "facet"; //$NON-NLS-1$
	static final String PROVIDER_FACET_ID = "id"; //$NON-NLS-1$
	static final String PROVIDER_FACET_VERSION = "versions"; //$NON-NLS-1$
	private static J2EEModelProviderRegistry INSTANCE = null;

	public J2EEModelProviderRegistry() {
		super(J2EEPlugin.PLUGIN_ID, EXTENSION_NAME);
	}

	public static J2EEModelProviderRegistry getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new J2EEModelProviderRegistry();
			INSTANCE.readRegistry();
		}
		return INSTANCE;
	}

	
	@Override
	public boolean readElement(IConfigurationElement element) {
		try{
			String priority = null;
			if (!element.getName().equals(ELEMENT_providers))
				return false;
			
			IModelProviderFactory factory = null;
			IConfigurationElement[] mp = element.getChildren(MODEL_PROVIDER);
			try {
				factory = (IModelProviderFactory) mp[0].createExecutableExtension(PROVIDER_FACTORY_CLASS);
			} catch (CoreException e) {
				logError(element, e.getMessage());
				J2EEPlugin.logError(e);
			}
			if (factory != null) {  //Optionally read priority if to override providers for type
				priority = mp[0].getAttribute(PROVIDER_PRIORITY);
			}
			if (factory != null) {
				IConfigurationElement[] facet = element.getChildren(PROVIDER_FACET);
				String facetId = facet[0].getAttribute(PROVIDER_FACET_ID);
				IProjectFacet pv = null;
				try{
					pv = ProjectFacetsManager.getProjectFacet(facetId);
				} catch (IllegalArgumentException e){
					logError(element, "Unrecognized facet: " + facetId); //$NON-NLS-1$
					J2EEPlugin.logError(e);
				}
				if (pv == null) {
					logError(element, "Unrecognized facet: " + facetId); //$NON-NLS-1$
					return true;  // Unrecognized facet
				}
				String facetVersions = facet[0].getAttribute(PROVIDER_FACET_VERSION);
				StringTokenizer tokens = new StringTokenizer(facetVersions,","); //$NON-NLS-1$
				while (tokens.hasMoreElements()) {
					String facetversion = (String) tokens.nextElement();
					IProjectFacetVersion fv = pv.getVersion(facetversion);
					if (fv != null)
						addModelProvider(factory, fv, priority);
				}
			}
			return true;
		} catch (Exception e) {
			logError(element, e.getMessage());
			J2EEPlugin.logError(e);
		}
		return true;
	}

	@Override
	protected void logError(IConfigurationElement element, String text) {
		IExtension extension = element.getDeclaringExtension();
		StringBuffer buf = new StringBuffer();
		buf.append("Plugin " + extension.getContributor().getName() + ", extension " + extension.getExtensionPointUniqueIdentifier()); //$NON-NLS-1$ //$NON-NLS-2$
		buf.append("\n" + text); //$NON-NLS-1$
		J2EEPlugin.logError(buf.toString());
	}

	private static void addModelProvider(IModelProviderFactory provider, IProjectFacetVersion version, String priority) {
		// Check priority of existing provider for override
		ModelProviderManager.registerProvider(provider,version,priority);
	}

}
