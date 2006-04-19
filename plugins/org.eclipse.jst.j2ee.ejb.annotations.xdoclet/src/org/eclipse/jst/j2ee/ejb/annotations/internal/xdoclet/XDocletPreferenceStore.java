/*******************************************************************************
 * Copyright (c) 2002, 2003,2004 Eteration Bilisim A.S.
 * Naci Dai and others.
 * 
 * Parts developed under contract ref:FT/R&D/MAPS/AMS/2004-09-09/AL are 
 * Copyright France Telecom, 2004.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eteration Bilisim A.S. - initial API and implementation
 *     Naci Dai
 * For more information on eteration, please see
 * <http://www.eteration.com/>.
 ***************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.service.prefs.BackingStoreException;

public final class XDocletPreferenceStore {

	private static IPreferencesService preferencesService = null;

	private IProject project = null;

	private ProjectScope projectScope;

	private InstanceScope instanceScope;

	private boolean useGlobal;

	private IScopeContext[] lookupOrder;

	private IScopeContext[] projectLookupOrder;

	private IScopeContext[] instanceLookupOrder;

	private IEclipsePreferences instanceNode;

	private IEclipsePreferences projectNode;

	public static final String XDOCLETBUILDERACTIVE = "XDOCLETBUILDERACTIVE";

	public static final String XDOCLETUSEGLOBAL = "XDOCLETUSEGLOBAL";

	public static final String XDOCLETFORCE = "XDOCLETFORCE";

	public static final String XDOCLETHOME = "XDOCLETHOME";

	public static final String XDOCLETVERSION = "XDOCLETVERSION";

	public XDocletPreferenceStore(IProject project) {
		this.project = project;
		init();

	}

	private void init() {
		if (instanceScope == null)
			instanceScope = new InstanceScope();
		instanceNode = instanceScope.getNode(getPreferencePrefix());

		if (project != null) {
			if (projectScope == null)
				projectScope = new ProjectScope(project);
			projectNode = projectScope.getNode(getPreferencePrefix());
			useGlobal = projectNode.getBoolean(XDOCLETUSEGLOBAL, false);
		}

		instanceLookupOrder = new IScopeContext[] { instanceScope, new DefaultScope() };
		if (project != null)
			projectLookupOrder = new IScopeContext[] { projectScope, instanceScope, new DefaultScope() };
		if (useGlobal)
			lookupOrder = instanceLookupOrder;
		else
			lookupOrder = projectLookupOrder;

	}

	protected static String getPreferencePrefix() {
		return XDocletAnnotationPlugin.getDefault().getBundle().getSymbolicName();
	}

	private IPreferencesService getPreferencesService() {

		if (preferencesService == null) {
			preferencesService = Platform.getPreferencesService();

		}
		return preferencesService;
	}

	public void setProperty(String item, boolean value) {
		IEclipsePreferences node = getPreferenceNode();
		node.putBoolean(item, value);

	}

	public void setProperty(String item, String value) {
		IEclipsePreferences node = getPreferenceNode();
		node.put(item, value);
	}

	public String getProperty(String item) {
		init();
		return this.getPreferencesService().getString(getPreferencePrefix(), item, "", lookupOrder);
	}

	public String getPropertyNoGlobal(String item) {
		init();
		if (project != null)
			lookupOrder = projectLookupOrder;
		return this.getPreferencesService().getString(getPreferencePrefix(), item, "", lookupOrder);
	}

	public boolean getBooleanProperty(String item) {
		init();
		return this.getPreferencesService().getBoolean(getPreferencePrefix(), item, false, lookupOrder);
	}

	public boolean getBooleanPropertyNoGlobal(String item) {
		init();
		if (project != null)
			lookupOrder = projectLookupOrder;
		return this.getPreferencesService().getBoolean(getPreferencePrefix(), item, false, lookupOrder);
	}

	private IEclipsePreferences getPreferenceNode() {
		init();
		IEclipsePreferences node = projectNode;
		if (node == null)
			node = instanceNode;
		return node;
	}

	public void save() {
		try {
			if (projectNode != null)
				projectNode.flush();
			if (instanceNode != null)
				instanceNode.flush();
		} catch (BackingStoreException e) {
			Logger.logException(e);
		}
	}

	protected static void initializeDefaultPreferences(IPreferenceStore store) {

		store.setDefault(XDOCLETFORCE, true);
		store.setDefault(XDOCLETVERSION, "1.2.1");
		store.setDefault(XDOCLETHOME, "");
		store.setDefault(XDOCLETUSEGLOBAL, true);
		store.setDefault(XDOCLETBUILDERACTIVE, true);

		initDoclet(store, "org.eclipse.jst.j2ee.ejb.annotations.xdoclet.ejbDocletTaskProvider");
		initDoclet(store, "org.eclipse.jst.j2ee.ejb.annotations.xdoclet.webdocletTaskProvider");
	}

	private static void initDoclet(IPreferenceStore store, String extensionID) {
		IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(extensionID).getExtensions();
		for (int i = 0; extensions != null && i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] elements = extension.getConfigurationElements();
			if (elements == null)
				continue;

			String id = elements[0].getAttribute("id");
			boolean selected = Boolean.valueOf(elements[0].getAttribute("defaultSelection")).booleanValue();
			store.setDefault(id + ".defaultSelection", selected);
			for (int j = 1; j < elements.length; j++) {
				IConfigurationElement param = elements[j];
				if ("TaskProperty".equals(param.getName())) {
					String paramId = param.getAttribute("id");
					String paramValue = param.getAttribute("default");
					boolean include = Boolean.valueOf(param.getAttribute("include")).booleanValue();
					store.setDefault(paramId, paramValue);
					store.setDefault(paramId + ".include", include);
				}
			}
		}
	}

	public static XDocletPreferenceStore forProject(IProject currentProject) {
		return new XDocletPreferenceStore(currentProject);
	}
}
