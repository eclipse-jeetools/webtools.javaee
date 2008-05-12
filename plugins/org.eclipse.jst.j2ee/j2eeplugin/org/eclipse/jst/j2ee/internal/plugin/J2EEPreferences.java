/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.jst.common.project.facet.core.FacetCorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;


/**
 * @author mdelder
 */
public class J2EEPreferences {

	public interface Keys {
		
		
		/**
		 * @deprecated, use CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER or use
		 * getJavaSourceFolderName()
		 * do not call J2EEPlugin.getJ2EEPreferences.getString(JAVA_SOURCE)
		 * @since 2.0
		 */
		static final String JAVA_SOURCE = "org.eclipse.jst.j2ee.preference.javaSourceName"; //$NON-NLS-1$

		static final String SERVER_TARGET_SUPPORT = "org.eclipse.jst.j2ee.preference.servertargetsupport"; //$NON-NLS-1$
		static final String CREATE_EJB_CLIENT_JAR = "org.eclipse.jst.j2ee.preference.createClientJar"; //$NON-NLS-1$
		static final String J2EE_VERSION = "org.eclipse.jst.j2ee.ui.preference.j2eeVersion"; //$NON-NLS-1$
		static final String EJB_CLIENT_JAR_CP_COMPATIBILITY = "org.eclipse.jst.j2ee.preference.clientjar.cp.compatibility"; //$NON-NLS-1$
		static final String INCREMENTAL_DEPLOYMENT_SUPPORT = "org.eclipse.jst.j2ee.ui.preference.incrementalDeployment"; //$NON-NLS-1$
		
		final static String USE_EAR_LIBRARIES = "org.eclipse.jst.j2ee.preferences.useEARLibraries";//$NON-NLS-1$
		final static String USE_WEB_APP_LIBRARIES = "org.eclipse.jst.j2ee.preferences.useWebAppLibraries";//$NON-NLS-1$
		final static String USE_EAR_LIBRARIES_JDT_EXPORT = "org.eclipse.jst.j2ee.preferences.useEARLibrariesJDTExport";//$NON-NLS-1$
		
		/**
		 * @deprecated, 
		 * but should it be deprecated ? is j2ee_web_content a better name than web_content_folder ?
		 * @since 2.0
		 */
		static final String J2EE_WEB_CONTENT = "org.eclipse.jst.j2ee.preference.j2eeWebContentName"; //$NON-NLS-1$
		/**
		 * @deprecated, static web content is got from ProductManager
		 * @since 2.0
		 */
		static final String STATIC_WEB_CONTENT = "org.eclipse.jst.j2ee.preference.staticWebContentName"; //$NON-NLS-1$
		/**
		 * @since 2.0
		 */
		static final String APPLICATION_CONTENT_FOLDER = IProductConstants.APPLICATION_CONTENT_FOLDER;
		/**
		 * @since 2.0
		 */
		static final String WEB_CONTENT_FOLDER = IProductConstants.WEB_CONTENT_FOLDER;
		/**
		 * @since 2.0
		 */
		static final String EJB_CONTENT_FOLDER = IProductConstants.EJB_CONTENT_FOLDER;
		/**
		 * @since 2.0
		 */
		static final String APP_CLIENT_CONTENT_FOLDER = IProductConstants.APP_CLIENT_CONTENT_FOLDER;
		/**
		 * @since 2.0
		 */
		static final String JCA_CONTENT_FOLDER = IProductConstants.JCA_CONTENT_FOLDER;
		
		/**
		 * @since 2.0
		 */
		static final String ADD_TO_EAR_BY_DEFAULT = IProductConstants.ADD_TO_EAR_BY_DEFAULT;
		/**
		 * @since 2.0
		 */
		static final String APPLICATION_GENERATE_DD = "application_generate_dd"; //$NON-NLS-1$
		/**
		 * @since 2.0
		 */
		static final String DYNAMIC_WEB_GENERATE_DD = "dynamic_web_generate_dd"; //$NON-NLS-1$
		/**
		 * @since 2.0
		 */
		static final String EJB_GENERATE_DD = "ejb_generate_dd"; //$NON-NLS-1$
		/**
		 * @since 2.0
		 */
		static final String APP_CLIENT_GENERATE_DD = "app_client_generate_dd"; //$NON-NLS-1$
		/**
		 * @since 3.0
		 */
		static String ID_PERSPECTIVE_HIERARCHY_VIEW = "perspective_hierarchy_view_id"; //$NON-NLS-1$

	}

	public interface Values {
		final static String J2EE_VERSION_1_2 = "J2EE_1_2"; //$NON-NLS-1$
		final static String J2EE_VERSION_1_3 = "J2EE_1_3"; //$NON-NLS-1$
		final static String J2EE_VERSION_1_4 = "J2EE_1_4"; //$NON-NLS-1$

		/**
		 * @deprecated, see initializeDefaultPreferences() it uses ProductManager
		 */
		final static String J2EE_WEB_CONTENT = ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER);
		/**
		 * @deprecated, see initializeDefaultPreferences() it uses ProductManager
		 */
		final static String STATIC_WEB_CONTENT = ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER);
		/**
		 * @deprecated, use CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER
		 */
		final static String JAVA_SOURCE = CommonFrameworksPlugin.getDefault().getPluginPreferences().getString(CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER);
	}

	public interface Defaults {

		/**
		 * @deprecated, see initializeDefaultPreferences() it uses ProductManager
		 */
		final static String J2EE_WEB_CONTENT = Values.J2EE_WEB_CONTENT;
		/**
		 * @deprecated, see initializeDefaultPreferences() it uses ProductManager
		 */
		final static String STATIC_WEB_CONTENT = Values.STATIC_WEB_CONTENT;
		/**
		 * @deprecated, see DEFAULT_SOURCE_FOLDER
		 */
		final static String JAVA_SOURCE = Values.JAVA_SOURCE;
		final static String J2EE_VERSION = Values.J2EE_VERSION_1_4;
		final static int J2EE_VERSION_ID = J2EEVersionConstants.J2EE_1_4_ID;
		final static boolean CREATE_EJB_CLIENT_JAR = false;
		final static boolean EJB_CLIENT_JAR_CP_COMPATIBILITY = true;
		final static boolean INCREMENTAL_DEPLOYMENT_SUPPORT = true;
		final static boolean USE_EAR_LIBRARIES_JDT_EXPORT = false;
		final static String ID_PERSPECTIVE_HIERARCHY_VIEW = "org.eclipse.ui.navigator.ProjectExplorer"; //$NON-NLS-1$
	}

	private Plugin owner = null;
	private Preferences preferences = null;
	private boolean persistOnChange = false;

	public J2EEPreferences(Plugin owner) {
		this.owner = owner;
	}

	protected void initializeDefaultPreferences() {

		getPreferences().setDefault(Keys.J2EE_VERSION, Defaults.J2EE_VERSION);
		getPreferences().setDefault(Keys.CREATE_EJB_CLIENT_JAR, Defaults.CREATE_EJB_CLIENT_JAR);
		getPreferences().setDefault(Keys.EJB_CLIENT_JAR_CP_COMPATIBILITY, Defaults.EJB_CLIENT_JAR_CP_COMPATIBILITY);
		getPreferences().setDefault(Keys.INCREMENTAL_DEPLOYMENT_SUPPORT, Defaults.INCREMENTAL_DEPLOYMENT_SUPPORT);
		
		// since 2.0
		getPreferences().setDefault(Keys.J2EE_WEB_CONTENT, ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER));
		getPreferences().setDefault(Keys.STATIC_WEB_CONTENT, ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER));
		// since 2.0
		getPreferences().setDefault(Keys.JAVA_SOURCE, FacetCorePlugin.getJavaSrcFolder());
		// done in CommonFrameworksPref..Initializer
		//getPreferences().setDefault(Keys.DEFAULT_SOURCE_FOLDER, ProductManager.getProperty(IProductConstants.DEFAULT_SOURCE_FOLDER));
		getPreferences().setDefault(Keys.APPLICATION_CONTENT_FOLDER, ProductManager.getProperty(IProductConstants.APPLICATION_CONTENT_FOLDER));
		getPreferences().setDefault(Keys.WEB_CONTENT_FOLDER, ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER));
		getPreferences().setDefault(Keys.APP_CLIENT_CONTENT_FOLDER, ProductManager.getProperty(IProductConstants.APP_CLIENT_CONTENT_FOLDER));
		getPreferences().setDefault(Keys.EJB_CONTENT_FOLDER, ProductManager.getProperty(IProductConstants.EJB_CONTENT_FOLDER));
		getPreferences().setDefault(Keys.JCA_CONTENT_FOLDER, ProductManager.getProperty(IProductConstants.JCA_CONTENT_FOLDER));
		getPreferences().setDefault(Keys.ADD_TO_EAR_BY_DEFAULT, ProductManager.getProperty(IProductConstants.ADD_TO_EAR_BY_DEFAULT));
		// done in CommonFrameworksPref..Initializer
		//getPreferences().setDefault(Keys.OUTPUT_FOLDER, ProductManager.getProperty(IProductConstants.OUTPUT_FOLDER));
		
		// since 2.0, for java ee projects
		getPreferences().setDefault(Keys.APPLICATION_GENERATE_DD, false);
		// for ee5 jee web projects default it to true so that we can create servlets, otherwise false
		getPreferences().setDefault(Keys.DYNAMIC_WEB_GENERATE_DD, true);
		getPreferences().setDefault(Keys.EJB_GENERATE_DD, false);
		getPreferences().setDefault(Keys.APP_CLIENT_GENERATE_DD, false);	
		
		getPreferences().setDefault(Keys.USE_EAR_LIBRARIES, true);
		getPreferences().setDefault(Keys.USE_WEB_APP_LIBRARIES, true);
		getPreferences().setDefault(Keys.USE_EAR_LIBRARIES_JDT_EXPORT, Defaults.USE_EAR_LIBRARIES_JDT_EXPORT);
		String perspectiveID = ProductManager.getProperty(IProductConstants.ID_PERSPECTIVE_HIERARCHY_VIEW);
		getPreferences().setDefault(Keys.ID_PERSPECTIVE_HIERARCHY_VIEW, (perspectiveID != null) ? perspectiveID : Defaults.ID_PERSPECTIVE_HIERARCHY_VIEW);
	}

	
	public String getSetting(String key){
		return getPreferences().getString(key);
	}
	
	public void setSetting(String key, String value){
		getPreferences().setValue(key, value);
		firePreferenceChanged();
	}
	
	public boolean getUseEARLibraries() {
		return getPreferences().getBoolean(Keys.USE_EAR_LIBRARIES);
	}
	
	public void setUseEARLibraries(boolean value) {
		getPreferences().setValue(Keys.USE_EAR_LIBRARIES, value);
		firePreferenceChanged();
	}
	
	public boolean getUseEARLibrariesJDTExport() {
		return getPreferences().getBoolean(Keys.USE_EAR_LIBRARIES_JDT_EXPORT);
	}
	
	public void setUseEARLibrariesJDTExport(boolean value) {
		getPreferences().setValue(Keys.USE_EAR_LIBRARIES_JDT_EXPORT, value);
		firePreferenceChanged();
	}
	
	public boolean getUseWebLibaries() {
		return getPreferences().getBoolean(Keys.USE_WEB_APP_LIBRARIES);
	}
	
	public void setUseWebLibraries(boolean value) {
		getPreferences().setValue(Keys.USE_WEB_APP_LIBRARIES, value);
		firePreferenceChanged();
	}
			
	
	public String getJ2EEWebContentFolderName() {
		//return getPreferences().getString(Keys.J2EE_WEB_CONTENT);
		//but should it be deprecated ? is j2ee_web_content a better name than web_content_folder ?
		return getPreferences().getString(Keys.WEB_CONTENT_FOLDER);
		
	}

	/**
	 * @return
	 * @deprecated 
	 */
	public String getStaticWebContentFolderName() {
		return getPreferences().getString(Keys.STATIC_WEB_CONTENT);
	}

	public String getJavaSourceFolderName() {
		//return getPreferences().getString(Keys.JAVA_SOURCE);
		// TODO is JAVA_SOURCE a better name or is DEFAULT_SOURCE...
        return FacetCorePlugin.getJavaSrcFolder();
	}

	public String getHighestJ2EEVersionSetting() {
		return getPreferences().getString(Keys.J2EE_VERSION);
	}

	public boolean isServerTargetingEnabled() {
		return getPreferences().getBoolean(Keys.SERVER_TARGET_SUPPORT);
	}

	///
	public void setJ2EEWebContentFolderName(String value) {
		//getPreferences().setValue(Keys.J2EE_WEB_CONTENT, value);
		// TODO but should it be deprecated ? is j2ee_web_content a better name than web_content_folder ?
		getPreferences().setValue(Keys.WEB_CONTENT_FOLDER, value);
		firePreferenceChanged();
	}

	/**
	 * @param value
	 * @deprecated
	 */
	public void setStaticWebContentFolderName(String value) {
		getPreferences().setValue(Keys.STATIC_WEB_CONTENT, value);
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
		getPreferences().setValue(Keys.J2EE_VERSION, value);
		firePreferenceChanged();
	}

	public void setServerTargetingEnabled(boolean value) {
		getPreferences().setValue(Keys.SERVER_TARGET_SUPPORT, value);
		firePreferenceChanged();
	}

	public void setIncrementalDeploymentEnabled(boolean value) {
		getPreferences().setValue(Keys.INCREMENTAL_DEPLOYMENT_SUPPORT, value);
		firePreferenceChanged();
	}

	public boolean isIncrementalDeploymentEnabled() {
		return getPreferences().getBoolean(Keys.INCREMENTAL_DEPLOYMENT_SUPPORT);
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
		getOwner().savePluginPreferences();
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

	private Preferences getPreferences() {
		if (this.preferences == null)
			this.preferences = getOwner().getPluginPreferences();
		return this.preferences;
	}

	/**
	 * @return Returns the owner.
	 */
	private Plugin getOwner() {
		return this.owner;
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
	 * @return the default value of the named property
	 */
	public boolean getDefaultBoolean(String name) {
		return getPreferences().getDefaultBoolean(name);
	}
	/**
	 * Returns the current value of the boolean-valued property with the
	 * given name.
	 * The given name must not be <code>null</code>.
	 *
	 * @param name the name of the property
	 * @return the boolean-valued property
	 */
	public boolean getBoolean(String name) {
		return getPreferences().getBoolean(name);
	}

	/**
	 * Sets the current value of the boolean-valued property with the
	 * given name. The given name must not be <code>null</code>.
	 * @param name the name of the property
	 * @param value the new current value of the property
	 */
	public void setValue(String name, boolean value) {
		getPreferences().setValue(name, value);
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
		return getPreferences().getDefaultString(name);
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
		return getPreferences().getString(name);
	}

	/**
	 * Sets the current value of the string-valued property with the
	 * given name. The given name must not be <code>null</code>.
	 * @param name the name of the property
	 * @param value the new current value of the property
	 */
	public void setValue(String name, String value) {
		getPreferences().setValue(name, value);
		firePreferenceChanged();
	}

}
