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

package org.eclipse.jst.j2ee.ejb.annotations.xdoclet;

import org.eclipse.jface.preference.IPreferenceStore;




public class XDocletPreferenceStore {
	
	
	
	private static IPreferenceStore preferenceStore = null;
	public static final String XDOCLETBUILDERACTIVE = "XDOCLETBUILDERACTIVE";
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


	private static IPreferenceStore getPreferenceStore() {
		if (preferenceStore == null) {
			preferenceStore = XDocletAnnotationPlugin.getDefault().getPreferenceStore();
		}
		return preferenceStore;
    }

	public static String getPropertyRaw(String item) {
		return getPreferenceStore().getString(item);
	}
	

	public static void setProperty(String prefix,String item, String value) {
		getPreferenceStore().setValue(XDocletAnnotationPlugin.PLUGINID+"."+prefix+"."+item+".value",value);
	}
	public static void setProperty(String item, String value) {
		String prefix = XDocletAnnotationPlugin.PLUGINID+".";
		getPreferenceStore().setValue(prefix+item+".value",value);
	}
	
	public static String getProperty(String item) {
		String prefix = XDocletAnnotationPlugin.PLUGINID+".";
		return getPreferenceStore().getString(prefix+item+".value");
	}
	public static String getProperty(String prefix,String item) {
		String pfix = XDocletAnnotationPlugin.PLUGINID+"."+prefix+"."+item+".value";
		return getPreferenceStore().getString(pfix);
	}
	

	
	public static boolean isPropertyActive(String item) {
		String prefix = XDocletAnnotationPlugin.PLUGINID+".";
		return getPreferenceStore().getBoolean(prefix+item);
	}
	
	public static void setPropertyActive(String item, boolean active) {
		String prefix = XDocletAnnotationPlugin.PLUGINID+".";
		getPreferenceStore().setValue(prefix+item, active);
	}
	
	
	public static void initializeDefaultPreferences(IPreferenceStore store)
	{
		String prefix = XDocletAnnotationPlugin.PLUGINID+".";
		store.setDefault(prefix+ XDOCLETFORCE + ".value", "true");
		store.setDefault(prefix+ XDOCLETVERSION + ".value", "1.2.2");
		XDocletPreferenceStore.setPropertyActive(EJB_JBOSS , true);
		store.setDefault(prefix+ EJB_JBOSS + "_VERSION.value", "2.4");
		store.setDefault(prefix+ EJB_JONAS + "_VERSION.value", "2.6");
		store.setDefault(prefix+ EJB_WEBLOGIC + "_VERSION.value", "6.1");
		store.setDefault(prefix+ EJB_WEBSPHERE + "_VERSION.value", "all");

		XDocletPreferenceStore.setPropertyActive(XDOCLETBUILDERACTIVE , true);
		XDocletPreferenceStore.setPropertyActive(EJB_JONAS , true);
		XDocletPreferenceStore.setPropertyActive(EJB_WEBSPHERE , true);
		XDocletPreferenceStore.setPropertyActive(EJB_WEBLOGIC , true);
		XDocletPreferenceStore.setPropertyActive(EJB_ORACLE , true);
		XDocletPreferenceStore.setPropertyActive(EJB_ORION , true);
		XDocletPreferenceStore.setPropertyActive(EJB_JRUN , true);
	}

}
