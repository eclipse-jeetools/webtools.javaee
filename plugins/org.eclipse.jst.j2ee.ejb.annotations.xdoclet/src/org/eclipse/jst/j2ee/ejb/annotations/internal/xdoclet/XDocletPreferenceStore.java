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

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public final class XDocletPreferenceStore {

	private static IPreferencesService preferencesService = null;

	private ScopedPreferenceStore projectSettings;

	private IProject project = null;

	public static final String XDOCLETBUILDERACTIVE = "XDOCLETBUILDERACTIVE";
	public static final String XDOCLETUSEGLOBAL = "XDOCLETUSEGLOBAL";
	
	public static final String XDOCLETGENERATELOCAL = "XDOCLETGENERATELOCAL";
	public static final String XDOCLETGENERATEREMOTE = "XDOCLETGENERATEREMOTE";
	public static final String XDOCLETGENERATEUTIL = "XDOCLETGENERATEUTIL";

	public static final String XDOCLETFORCE = "XDOCLETFORCE";
	public static final String XDOCLETHOME = "XDOCLETHOME";
	public static final String XDOCLETVERSION = "XDOCLETVERSION";

	public static final String EJB_JBOSS = "EJB_JBOSS";
	public static final String EJB_JONAS = "EJB_JONAS";
	public static final String EJB_WEBSPHERE = "EJB_WEBSPHERE";
	public static final String EJB_WEBLOGIC = "EJB_WEBLOGIC";
	public static final String EJB_ORACLE = "EJB_ORACLE";
	public static final String EJB_ORION = "EJB_ORION";
	public static final String EJB_JRUN = "EJB_JRUN";

	public static final String WEB_JBOSS = "WEB_JBOSS";
	public static final String WEB_JONAS = "WEB_JONAS";
	public static final String WEB_WEBSPHERE = "WEB_WEBSPHERE";
	public static final String WEB_WEBLOGIC = "WEB_WEBLOGIC";
	public static final String WEB_ORACLE = "WEB_ORACLE";
	public static final String WEB_ORION = "WEB_ORION";
	public static final String WEB_JRUN = "WEB_JRUN";

	public XDocletPreferenceStore(IProject project) {
		this.project = project;
		if (project != null) {
			projectSettings = new ScopedPreferenceStore(new ProjectScope(project), getPreferencePrefix());
		}

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

	private IScopeContext[] getLookupOrder() {
		String key = getPreferencePrefix() + "." + XDOCLETUSEGLOBAL;

		IScopeContext[] projectScopeOrder = null;
		if (project != null)
			projectScopeOrder = new IScopeContext[] { new ProjectScope(project), new InstanceScope(), new DefaultScope() };
		IScopeContext[] globalScopeOrder = new IScopeContext[] { new InstanceScope(), new DefaultScope() };

		if (projectScopeOrder == null)
			projectScopeOrder = globalScopeOrder;

		boolean useGlobal = this.getPreferencesService().getBoolean(getPreferencePrefix(), key, false, projectScopeOrder);

		if (useGlobal || project == null)
			return globalScopeOrder;
		else
			return projectScopeOrder;
	}

	public String getPropertyRaw(String item) {
		IScopeContext[] lOrder = this.getLookupOrder();
		// String defvalue = preferenceStore.getDefaultString(item);
		return this.getPreferencesService().getString(getPreferencePrefix(), item, null, lOrder);
	}

	public void setProperty(String item, String value) {
		String key = getPreferencePrefix() + "." + item + ".value";
		IPreferenceStore store = XDocletAnnotationPlugin.getDefault().getPreferenceStore();
		if (project != null)
			store = projectSettings;

		store.setValue(key, value);
	}

	public void save() {

		try {
			if (project != null && projectSettings != null)
				projectSettings.save();
		} catch (IOException e) {
			Logger.logException(e);
		}

	}

	public void setProperty(String item, boolean value) {
		String key = getPreferencePrefix() + "." + item + ".value";
		IPreferenceStore store = XDocletAnnotationPlugin.getDefault().getPreferenceStore();
		if (project != null)
			store = projectSettings;

		store.setValue(key, value);

	}

	public String getProperty(String item) {
		String key = getPreferencePrefix() + "." + item + ".value";

		IScopeContext[] lOrder = this.getLookupOrder();
		return this.getPreferencesService().getString(getPreferencePrefix(), key, null, lOrder);
	}

	public boolean isPropertyActive(String item) {
		String key = getPreferencePrefix() + "." + item;
		IScopeContext[] lOrder = this.getLookupOrder();
		return this.getPreferencesService().getBoolean(getPreferencePrefix(), key, false, lOrder);
	}

	public void setPropertyActive(String item, boolean active) {
		String key = getPreferencePrefix() + "." + item;
		IPreferenceStore store = XDocletAnnotationPlugin.getDefault().getPreferenceStore();
		if (project != null)
			store = projectSettings;
		store.setValue(key, active);
	}

	protected static void initializeDefaultPreferences(IPreferenceStore store) {

		store.setDefault(XDOCLETFORCE, true);
		store.setDefault(getPreferencePrefix() + "." + XDOCLETVERSION + ".value", "1.2.1");
		store.setDefault(getPreferencePrefix() + "." + XDOCLETHOME + ".value", "");

		store.setDefault(getPreferencePrefix() + "." + EJB_JBOSS + "_VERSION.value", "2.4");
		store.setDefault(getPreferencePrefix() + "." + EJB_JONAS + "_VERSION.value", "2.6");
		store.setDefault(getPreferencePrefix() + "." + EJB_WEBLOGIC + "_VERSION.value", "6.1");
		store.setDefault(getPreferencePrefix() + "." + EJB_WEBSPHERE + "_VERSION.value", "all");

		store.setDefault(getPreferencePrefix() + "." + WEB_JBOSS + "_VERSION.value", "2.4");
		store.setDefault(getPreferencePrefix() + "." + WEB_JONAS + "_VERSION.value", "2.6");
		store.setDefault(getPreferencePrefix() + "." + WEB_WEBLOGIC + "_VERSION.value", "6.1");
		store.setDefault(getPreferencePrefix() + "." + WEB_WEBSPHERE + "_VERSION.value", "all");

		store.setDefault(getPreferencePrefix() + "." + XDOCLETGENERATELOCAL, true);
		store.setDefault(getPreferencePrefix() + "." + XDOCLETGENERATEREMOTE, true);
		store.setDefault(getPreferencePrefix() + "." + XDOCLETGENERATEUTIL, true);
		
		store.setDefault(getPreferencePrefix() + "." + XDOCLETUSEGLOBAL, true);
		store.setDefault(getPreferencePrefix() + "." + XDOCLETUSEGLOBAL, true);
		store.setDefault(getPreferencePrefix() + "." + XDOCLETBUILDERACTIVE, true);

		store.setDefault(getPreferencePrefix() + "." + EJB_JBOSS, false);
		store.setDefault(getPreferencePrefix() + "." + EJB_JONAS, false);
		store.setDefault(getPreferencePrefix() + "." + EJB_WEBSPHERE, false);
		store.setDefault(getPreferencePrefix() + "." + EJB_WEBLOGIC, false);
		store.setDefault(getPreferencePrefix() + "." + EJB_ORACLE, false);
		store.setDefault(getPreferencePrefix() + "." + EJB_ORION, false);
		store.setDefault(getPreferencePrefix() + "." + EJB_JRUN, false);

		store.setDefault(getPreferencePrefix() + "." + WEB_JBOSS, false);
		store.setDefault(getPreferencePrefix() + "." + WEB_JONAS, false);
		store.setDefault(getPreferencePrefix() + "." + WEB_WEBSPHERE, false);
		store.setDefault(getPreferencePrefix() + "." + WEB_WEBLOGIC, false);
		store.setDefault(getPreferencePrefix() + "." + WEB_ORACLE, false);
		store.setDefault(getPreferencePrefix() + "." + WEB_ORION, false);
		store.setDefault(getPreferencePrefix() + "." + WEB_JRUN, false);
	}

	public static XDocletPreferenceStore forProject(IProject currentProject) {

		return new XDocletPreferenceStore(currentProject);
	}
}
