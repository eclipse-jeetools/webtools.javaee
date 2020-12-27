/*******************************************************************************
 * Copyright (c) 2003, 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 26, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.plugin;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jst.common.project.facet.core.internal.FacetCorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.osgi.service.prefs.BackingStoreException;


/**
 * @author mdelder
 * @deprecated
 */

public class J2EEPreferences {

	
	public interface Keys extends JavaEEPreferencesInitializer.Keys{}

	public interface Values extends JavaEEPreferencesInitializer.Values {}

	public interface Defaults extends JavaEEPreferencesInitializer.Defaults {}

	private boolean persistOnChange = false;

	public J2EEPreferences(Plugin owner) {
	}
/**
 * @deprecated
 * See JavaEEPreferencesInitializer.initializeDefaultPreferences
 * which will get called lazily. No need to explicitly call this method 
 * for initialization anymore.
 */
	protected void initializeDefaultPreferences() {}

	
	public String getSetting(String key){
		return getString(key);
	}
	
	public void setSetting(String key, String value){
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).put(key, value);
		firePreferenceChanged();
	}
	
	public boolean getUseEARLibraries() {
		return getBoolean(Keys.USE_EAR_LIBRARIES);
	}
	
	public void setUseEARLibraries(boolean value) {
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).putBoolean(Keys.USE_EAR_LIBRARIES, value);
		firePreferenceChanged();
	}
	
	public boolean getUseEARLibrariesJDTExport() {
		return getBoolean(Keys.USE_EAR_LIBRARIES_JDT_EXPORT);
	}
	
	public void setUseEARLibrariesJDTExport(boolean value) {
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).putBoolean(Keys.USE_EAR_LIBRARIES_JDT_EXPORT, value);
		firePreferenceChanged();
	}
	
	public boolean getUseWebLibaries() {
		return getBoolean(Keys.USE_WEB_APP_LIBRARIES);
	}
	
	public void setUseWebLibraries(boolean value) {
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).putBoolean(Keys.USE_WEB_APP_LIBRARIES, value);
		firePreferenceChanged();
	}
	
	/**
	 * @return
	 * @deprecated Do not use. The ALLOW_CLASSPATH_DEP preference has been deprecated and its ability to disable dynamic manifest updates will soon be removed.
	 */
	public boolean getAllowClasspathDep() {
		return getBoolean(Keys.ALLOW_CLASSPATH_DEP);
	}
	
	/**
	 * @param value
	 * @deprecated Do not use. The ALLOW_CLASSPATH_DEP preference has been deprecated and its ability to disable dynamic manifest updates will soon be removed.
	 */
	public void setAllowClasspathDep(boolean value) {
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).putBoolean(Keys.ALLOW_CLASSPATH_DEP, value);
		firePreferenceChanged();
	}
	
	public IEclipsePreferences getInstancePreferenceNode(String qualifier){
		return new InstanceScope().getNode(J2EEPlugin.PLUGIN_ID); 
	}
	
	public String getJ2EEWebContentFolderName() {
		return getString(Keys.WEB_CONTENT_FOLDER);
	}

	/**
	 * @return
	 */
	public String getStaticWebContentFolderName() {
		return getString(Keys.STATIC_WEB_CONTENT);
	}

	public String getJavaSourceFolderName() {
		//return getPreferences().getString(Keys.JAVA_SOURCE);
		// TODO is JAVA_SOURCE a better name or is DEFAULT_SOURCE...
        return FacetCorePlugin.getJavaSrcFolder();
	}

	public String getHighestJ2EEVersionSetting() {
		return getString(Keys.J2EE_VERSION);
	}

	public boolean isServerTargetingEnabled() {
		return getBoolean(Keys.SERVER_TARGET_SUPPORT);
	}

	///
	public void setJ2EEWebContentFolderName(String value) {
		//getPreferences().setValue(Keys.J2EE_WEB_CONTENT, value);
		// TODO but should it be deprecated ? is j2ee_web_content a better name than web_content_folder ?
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).put(Keys.WEB_CONTENT_FOLDER, value);
		firePreferenceChanged();
	}

	/**
	 * @param value
	 */
	public void setStaticWebContentFolderName(String value) {
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).put(Keys.STATIC_WEB_CONTENT, value);
		firePreferenceChanged();
	}

	public void setJavaSourceFolderName(String value) {
		//getPreferences().setValue(Keys.JAVA_SOURCE, value);
		// TODO is JAVA_SOURCE a better name or is DEFAULT_SOURCE...
        FacetCorePlugin.getDefault().getPluginPreferences().setValue(FacetCorePlugin.PROD_PROP_SOURCE_FOLDER_LEGACY, value);
        FacetCorePlugin.getDefault().savePluginPreferences();		
		firePreferenceChanged();
	}

	public void setHighestJ2EEVersionSetting(String value) {
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).put(Keys.J2EE_VERSION, value);
		firePreferenceChanged();
	}

	public void setServerTargetingEnabled(boolean value) {
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).putBoolean(Keys.SERVER_TARGET_SUPPORT, value);
		firePreferenceChanged();
	}

	public void setIncrementalDeploymentEnabled(boolean value) {
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).putBoolean(Keys.INCREMENTAL_DEPLOYMENT_SUPPORT, value);
		firePreferenceChanged();
	}

	public boolean isIncrementalDeploymentEnabled() {
		return getBoolean(Keys.INCREMENTAL_DEPLOYMENT_SUPPORT);
	}

	/**
	 * @return one of J2EEVersionConstants.J2EE_VERSION_X_X (@see J2EEVersionConstants)
	 */
	public int getHighestJ2EEVersionID() {
		String versionPreference = getHighestJ2EEVersionSetting();
		if (Values.J2EE_VERSION_1_2.equals(versionPreference))
			return J2EEVersionConstants.J2EE_1_2_ID;
		else if (Values.J2EE_VERSION_1_3.equals(versionPreference))
			return J2EEVersionConstants.J2EE_1_3_ID;
		else
			return J2EEVersionConstants.J2EE_1_4_ID;
	}

	public void firePreferenceChanged() {
		if (isPersistOnChange())
			persist();
	}

	public void persist() {
		try {
			getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).flush();
		} catch (BackingStoreException e) {
			org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
		}
	}

	/**
	 * @return Returns the persistOnChange.
	 */
	public boolean isPersistOnChange() {
		return this.persistOnChange;
	}

	/**
	 * @param persistOnChange
	 *            The persistOnChange to set.
	 */
	public void setPersistOnChange(boolean persistOnChange) {
		this.persistOnChange = persistOnChange;
	}


	

	/**
	 * Returns the default value for the boolean-valued property
	 * with the given name.
	 * Returns the default-default value (<code>false</code>) if there
	 * is no default property with the given name, or if the default 
	 * value cannot be treated as a boolean.
	 * The given name must not be <code>null</code>.
	 *
	 * @param name the name of the property
	 * @return the default value of the named property. If default value 
	 * is not set, a false will be returned
	 */
	public boolean getDefaultBoolean(String name) {
		return new DefaultScope().getNode(J2EEPlugin.PLUGIN_ID).getBoolean(name, Defaults.BOOLEAN_DEFAULT_DEFAULT);
	}
	/**
	 * Returns the current value of the boolean-valued property with the
	 * given name.
	 * The given name must not be <code>null</code>.
	 *
	 * @param name the name of the property
	 * @return the boolean-valued property. If property value is not set, a false is returned.
	 */
	public boolean getBoolean(String name) {
		IPreferencesService preferencesService = Platform.getPreferencesService();
		IScopeContext[] lookupOrder = new IScopeContext[]{new InstanceScope(), new DefaultScope()};
		return preferencesService.getBoolean(J2EEPlugin.PLUGIN_ID, name, Defaults.BOOLEAN_DEFAULT_DEFAULT, lookupOrder);
	}

	/**
	 * Sets the current value of the boolean-valued property with the
	 * given name. The given name must not be <code>null</code>.
	 * @param name the name of the property
	 * @param value the new current value of the property
	 */
	public void setValue(String name, boolean value) {
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).putBoolean(name, value);
		firePreferenceChanged();
	}

	/**
	 * Returns the default value for the string-valued property
	 * with the given name.
	 * Returns the default-default value (the empty string <code>""</code>) 
	 * is no default property with the given name, or if the default 
	 * value cannot be treated as a string.
	 * The given name must not be <code>null</code>.
	 *
	 * @param name the name of the property
	 * @return the default value of the named property
	 */
	public String getDefaultString(String name) {
		return new DefaultScope().getNode(J2EEPlugin.PLUGIN_ID).get(name, Defaults.STRING_DEFAULT_DEFAULT);
	}	
	
	/**
	 * Returns the current value of the string-valued property with the
	 * given name.
	 * Returns the default-default value (the empty string <code>""</code>)
	 * if there is no property with the given name.
	 * The given name must not be <code>null</code>.
	 *
	 * @param name the name of the property
	 * @return the string-valued property
	 */
	public String getString(String name) {
		return JavaEEPreferencesInitializer.getString(name);
	}

	/**
	 * Sets the current value of the string-valued property with the
	 * given name. The given name must not be <code>null</code>.
	 * @param name the name of the property
	 * @param value the new current value of the property
	 */
	public void setValue(String name, String value) {
		getInstancePreferenceNode(J2EEPlugin.PLUGIN_ID).put(name, value);
		firePreferenceChanged();
	}

		
		
	public String getDynamicWebDefaultSourceFolder(){
		return JavaEEPreferencesInitializer.getDynamicWebDefaultSourceFolder();
	}
	
	public String getDynamicWebDefaultOuputFolderName(){
		return JavaEEPreferencesInitializer.getDynamicWebDefaultOuputFolderName(); 
		}
	
	public String getAppClientDefaultOutputFolderName(){
		return JavaEEPreferencesInitializer.getAppClientDefaultOutputFolderName();
	}
	
	public String getEJBDefaultOutputFolderName(){
		return JavaEEPreferencesInitializer.getEJBDefaultOutputFolderName();
	}
	
	public String getJCADefaultOutputFolderName(){
		return JavaEEPreferencesInitializer.getJCADefaultOutputFolderName();
	}
	
	public String getDefaultOutputFolderName(){
		return JavaEEPreferencesInitializer.getDefaultOutputFolderName();
	}
	    
	public String getDefaultJavaSrcFolder(){
		return JavaEEPreferencesInitializer.getDefaultJavaSrcFolder();
	}
	
	
	public String getUtilityOutputFolderName(){
		
		String outputFolder = FacetCorePlugin.getDefault().getPluginPreferences().getString(FacetCorePlugin.OUTPUT_FOLDER);
		if( outputFolder == null || outputFolder.equals("") ){ //$NON-NLS-1$
			return getDefaultOutputFolderName();
		}
	    return outputFolder;
	}
}
