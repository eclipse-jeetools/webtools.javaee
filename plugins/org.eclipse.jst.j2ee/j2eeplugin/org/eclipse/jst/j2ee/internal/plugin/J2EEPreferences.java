/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
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
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;


/**
 * @author mdelder
 */
public class J2EEPreferences {

	public interface Keys {
		final static String J2EE_WEB_CONTENT = "com.ibm.wtp.j2ee.preference.j2eeWebContentName"; //$NON-NLS-1$
		final static String STATIC_WEB_CONTENT = "com.ibm.wtp.j2ee.preference.staticWebContentName"; //$NON-NLS-1$
		final static String JAVA_SOURCE = "com.ibm.wtp.j2ee.preference.javaSourceName"; //$NON-NLS-1$
		final static String SERVER_TARGET_SUPPORT = "com.ibm.wtp.j2ee.preference.servertargetsupport"; //$NON-NLS-1$
		final static String CREATE_EJB_CLIENT_JAR = "com.ibm.wtp.j2ee.preference.createClientJar"; //$NON-NLS-1$
		final static String J2EE_VERSION = "com.ibm.wtp.j2ee.ui.preference.j2eeVersion"; //$NON-NLS-1$
		final static String EJB_CLIENT_JAR_CP_COMPATIBILITY = "com.ibm.wtp.j2ee.preference.clientjar.cp.compatibility"; //$NON-NLS-1$
		final static String INCREMENTAL_DEPLOYMENT_SUPPORT = "com.ibm.wtp.j2ee.ui.preference.incrementalDeployment"; //$NON-NLS-1$


	}

	public interface Values {
		final static String J2EE_VERSION_1_2 = "J2EE_1_2"; //$NON-NLS-1$
		final static String J2EE_VERSION_1_3 = "J2EE_1_3"; //$NON-NLS-1$
		final static String J2EE_VERSION_1_4 = "J2EE_1_4"; //$NON-NLS-1$

		final static String J2EE_WEB_CONTENT = "WebContent"; //$NON-NLS-1$
		final static String STATIC_WEB_CONTENT = "WebContent"; //$NON-NLS-1$
		final static String JAVA_SOURCE = "JavaSource"; //$NON-NLS-1$
	}

	public interface Defaults {

		final static String J2EE_WEB_CONTENT = Values.J2EE_WEB_CONTENT;
		final static String STATIC_WEB_CONTENT = Values.STATIC_WEB_CONTENT;
		final static String JAVA_SOURCE = Values.JAVA_SOURCE;
		final static String J2EE_VERSION = Values.J2EE_VERSION_1_4;
		final static int J2EE_VERSION_ID = J2EEVersionConstants.J2EE_1_4_ID;
		final static boolean CREATE_EJB_CLIENT_JAR = false;
		final static boolean EJB_CLIENT_JAR_CP_COMPATIBILITY = true;
		final static boolean INCREMENTAL_DEPLOYMENT_SUPPORT = false;
	}

	private Plugin owner = null;
	private Preferences preferences = null;
	private boolean persistOnChange = false;

	public J2EEPreferences(Plugin owner) {
		this.owner = owner;
	}

	protected void initializeDefaultPreferences() {
		getPreferences().setDefault(Keys.J2EE_WEB_CONTENT, Defaults.J2EE_WEB_CONTENT);
		getPreferences().setDefault(Keys.STATIC_WEB_CONTENT, Defaults.STATIC_WEB_CONTENT);
		getPreferences().setDefault(Keys.JAVA_SOURCE, Defaults.JAVA_SOURCE);

		getPreferences().setDefault(Keys.J2EE_VERSION, Defaults.J2EE_VERSION);
		getPreferences().setDefault(Keys.CREATE_EJB_CLIENT_JAR, Defaults.CREATE_EJB_CLIENT_JAR);
		getPreferences().setDefault(Keys.EJB_CLIENT_JAR_CP_COMPATIBILITY, Defaults.EJB_CLIENT_JAR_CP_COMPATIBILITY);
		getPreferences().setDefault(Keys.INCREMENTAL_DEPLOYMENT_SUPPORT, Defaults.INCREMENTAL_DEPLOYMENT_SUPPORT);

	}

	public String getJ2EEWebContentFolderName() {
		return getPreferences().getString(Keys.J2EE_WEB_CONTENT);
	}

	public String getStaticWebContentFolderName() {
		return getPreferences().getString(Keys.STATIC_WEB_CONTENT);
	}

	public String getJavaSourceFolderName() {
		return getPreferences().getString(Keys.JAVA_SOURCE);
	}

	public String getHighestJ2EEVersionSetting() {
		return getPreferences().getString(Keys.J2EE_VERSION);
	}

	public boolean isServerTargetingEnabled() {
		return getPreferences().getBoolean(Keys.SERVER_TARGET_SUPPORT);
	}

	///
	public void setJ2EEWebContentFolderName(String value) {
		getPreferences().setValue(Keys.J2EE_WEB_CONTENT, value);
		firePreferenceChanged();
	}

	public void setStaticWebContentFolderName(String value) {
		getPreferences().setValue(Keys.STATIC_WEB_CONTENT, value);
		firePreferenceChanged();
	}

	public void setJavaSourceFolderName(String value) {
		getPreferences().setValue(Keys.JAVA_SOURCE, value);
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

}