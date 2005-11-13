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

package org.eclipse.jst.j2ee.ejb.annotation.internal.preferences;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ModelPlugin;

public class AnnotationPreferenceStore extends AbstractPreferenceInitializer {

	public static final String ANNOTATIONPROVIDER = "ANNOTATIONPROVIDER";

	protected static String getPreferencePrefix() {
		return ModelPlugin.getDefault().getBundle().getSymbolicName();
	}

	private static Preferences getPreferenceStore() {
		Preferences store = ModelPlugin.getDefault().getPluginPreferences();
		return store;
	}

	public static String getPropertyRaw(String item) {
		Preferences store = getPreferenceStore();
		return store.getString(item);
	}

	public static void setProperty(String prefix, String item, String value) {
		getPreferenceStore().setValue(ModelPlugin.PLUGINID + "." + prefix + "." + item + ".value", value);
	}

	public static void setProperty(String item, String value) {
		String prefix = ModelPlugin.PLUGINID + ".";
		getPreferenceStore().setValue(prefix + item + ".value", value);
	}

	public static String getProperty(String item) {
		String prefix = ModelPlugin.PLUGINID + ".";
		return getPreferenceStore().getString(prefix + item + ".value");
	}

	public static String getProperty(String prefix, String item) {
		String pfix = ModelPlugin.PLUGINID + "." + prefix + "." + item + ".value";
		return getPreferenceStore().getString(pfix);
	}

	public static boolean isPropertyActive(String item) {
		String prefix = ModelPlugin.PLUGINID + ".";
		return getPreferenceStore().getBoolean(prefix + item);
	}

	public static void setPropertyActive(String item, boolean active) {
		String prefix = ModelPlugin.PLUGINID + ".";
		getPreferenceStore().setValue(prefix + item, active);
	}

	public static void initializeDefaultPreferences(Preferences store) {
		String prefix = ModelPlugin.PLUGINID + ".";
		store.setDefault(prefix + ANNOTATIONPROVIDER + ".value", "XDoclet");
	}

	public void initializeDefaultPreferences() {
		IEclipsePreferences node = new DefaultScope().getNode(getPreferencePrefix());
		String prefix = ModelPlugin.PLUGINID + ".";
		node.put(prefix + ANNOTATIONPROVIDER + ".value", "XDoclet");
	}

}
