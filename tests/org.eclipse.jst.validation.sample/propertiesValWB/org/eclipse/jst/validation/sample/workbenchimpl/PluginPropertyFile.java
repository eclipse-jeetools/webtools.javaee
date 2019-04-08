/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.validation.sample.workbenchimpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Locale;
import java.util.logging.Level;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.sample.parser.APropertyFile;
import org.eclipse.jst.validation.sample.parser.MessageMetaData;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.osgi.framework.Bundle;

/**
 * A PluginPropertyFile represents a resource bundle that is exported by a plugin.
 */
public class PluginPropertyFile extends APropertyFile {
	private String _bundleName = null;
	private String _pluginId = null;
	private String _langVariant = null; // e.g., en_US vs en
	private ClassLoader _classLoader = null; // the ClassLoader used to load the bundle
	
	
	/**
	 * Both the pluginId and the bundleName must not be null, must
	 * refer to an existing plugin, and must refer to an existing bundle 
	 * exported by that plugin.
	 */
	public PluginPropertyFile(String pluginId, String bundleName) {
		_pluginId = pluginId;
		_bundleName = bundleName;
		
		ClassLoader cl = null;
		InputStream inS = null;
		Bundle bundle = Platform.getBundle(pluginId);
		inS = getInputStream(bundle, bundleName);
		
		InputStreamReader inR = new InputStreamReader(inS);
		LineNumberReader lineR = new LineNumberReader(inR);
		parseFile(lineR); // populate this property file
		try {
			lineR.close();
			inR.close();
		}
		catch(IOException exc) {
			Logger logger = PropertiesValidatorPlugin.getPlugin().getMsgLogger();;
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
		
		try {
			if(inS != null) {
				inS.close();
			}
		}
		catch(IOException exc) {
			Logger logger = PropertiesValidatorPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
		
		_classLoader = cl; // store the ClassLoader which was used to load the bundle
	}
	
	private InputStream getInputStream(Bundle bundle, String bundleName) {
		Locale l = Locale.getDefault();
		String language = l.getLanguage();
		String country = l.getCountry();
		String variant = l.getVariant();
		
		String lang = null;
		String lang_country = null;
		String lang_country_variant = null;
		if((language != null) && !(language.equals(""))) { //$NON-NLS-1$
			lang = "_" + language; //$NON-NLS-1$
		}

		if((country != null) && !(country.equals(""))) { //$NON-NLS-1$
			lang_country = lang + "_" + country; //$NON-NLS-1$
		}

		if((variant != null) && !(variant.equals(""))) { //$NON-NLS-1$
			lang_country_variant = lang_country + "_" + variant; //$NON-NLS-1$
		}
		
		InputStream inS = null;
		if(lang_country_variant != null) {
			inS = getResourceAsStream(bundle, bundleName, lang_country_variant);
			if(inS != null) {
				_langVariant = lang_country_variant;
				return inS;
			}
		}
		
		if(lang_country != null) {
			inS = getResourceAsStream(bundle, bundleName, lang_country);
			if(inS != null) {
				_langVariant = lang_country;
				return inS;
			}
		}
		
		if(lang != null) {
			inS = getResourceAsStream(bundle, bundleName, lang);
			if(inS != null) {
				_langVariant = lang;
				return inS;
			}
		}
		
		if(Locale.getDefault().equals(Locale.US)) {
			// Running the TVT plugin in en_US mode, so return the default .properties file.
			inS = getResourceAsStream(bundle, bundleName, ""); //$NON-NLS-1$
			if(inS != null) {
				_langVariant = ""; //$NON-NLS-1$
				return inS;
			}
		}
		
		return null;
	}
	
	private final static InputStream getResourceAsStream(final Bundle bundle, final String bundleName, final String language) {
		String resName = bundleName.replace('.', '/') + language + ".properties"; //$NON-NLS-1$
		try {
			return bundle.getEntry(resName).openStream();
		}
		catch(IOException e) {
			Platform.getLog(bundle).log(new Status(IStatus.ERROR, bundle.getSymbolicName(), IStatus.ERROR, "Nothing found at " + resName + " in " + bundle.getSymbolicName(), e));
			return new ByteArrayInputStream(new byte[0]);
		}
	}
	
	/*
	 * @see APropertyFile#report(String)
	 */
	public void report(String str) {
		addParseWarning(new MessageMetaData(str));
	}
	
	public void report(IMessage message) {
		MessageMetaData mmd = new MessageMetaData(message.getBundleName(), message.getSeverity(), message.getId(), message.getParams(), message.getTargetObject(), message.getLineNumber(), message.getOffset(), message.getLength());
		addParseWarning(mmd);
	}
	
	public void report(MessageMetaData mmd) {
		addParseWarning(mmd);
	}
	
	public void addParseWarning(IMessage message) {
		MessageMetaData mmd = new MessageMetaData(message.getBundleName(), message.getSeverity(), message.getId(), message.getParams(), message.getTargetObject(), message.getLineNumber(), message.getOffset(), message.getLength());
		addParseWarning(mmd);
	}
	
	public String getPluginId() {
		return _pluginId;
	}

	public String getBundleName() {
		return _bundleName;
	}
	
	public String getQualifiedFileName() {
		StringBuffer buffer = new StringBuffer(getPluginId());
		buffer.append("::"); //$NON-NLS-1$
		buffer.append(getBundleName());
		buffer.append(_langVariant);
		return buffer.toString();
	}

	public String getLangVariant() {
		return _langVariant;
	}
	
	public ClassLoader getClassLoader() {
		return _classLoader;
	}	
}
