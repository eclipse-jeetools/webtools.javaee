/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package org.eclipse.jst.validation.sample.workbenchimpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.logging.Level;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.sample.parser.APropertyFile;
import org.eclipse.jst.validation.sample.parser.MessageMetaData;
import org.eclipse.wst.validation.core.IMessage;

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
		Plugin plugin = getPlugin(pluginId);			
		if(bundleName.equals("plugin")) { //$NON-NLS-1$
			// Try loading it from the Plugin parent (a "plugin.properties")
			cl = getPluginPropertiesClassLoader(plugin);
		}
		else {
			cl = plugin.getDescriptor().getPluginClassLoader();
		}
		inS = getInputStream(cl, bundleName);
		
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
	
	private InputStream getInputStream(ClassLoader cl, String bundleName) {
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
			inS = getResourceAsStream(cl, bundleName, lang_country_variant);
			if(inS != null) {
				_langVariant = lang_country_variant;
				return inS;
			}
		}
		
		if(lang_country != null) {
			inS = getResourceAsStream(cl, bundleName, lang_country);
			if(inS != null) {
				_langVariant = lang_country;
				return inS;
			}
		}
		
		if(lang != null) {
			inS = getResourceAsStream(cl, bundleName, lang);
			if(inS != null) {
				_langVariant = lang;
				return inS;
			}
		}
		
		if(Locale.getDefault().equals(Locale.US)) {
			// Running the TVT plugin in en_US mode, so return the default .properties file.
			inS = getResourceAsStream(cl, bundleName, ""); //$NON-NLS-1$
			if(inS != null) {
				_langVariant = ""; //$NON-NLS-1$
				return inS;
			}
		}
		
		return null;
	}
	
	private final static InputStream getResourceAsStream(final ClassLoader cl, final String bundleName, final String language) {
		String resName = bundleName.replace('.', '/') + language + ".properties"; //$NON-NLS-1$
		return cl.getResourceAsStream(resName);
	}
	
	private static ClassLoader getPluginPropertiesClassLoader(Plugin p) {
		// Copied from PluginDescriptor.java's getResourceBundle method.
		URL[] cp = ((URLClassLoader)p.getDescriptor().getPluginClassLoader()).getURLs();
		URL[] newcp = new URL[cp.length+1];
		for (int i=0; i<cp.length; i++) newcp[i+1] = cp[i];
		try {
			newcp[0] = Platform.resolve(p.getDescriptor().getInstallURL()); // always try to resolve URLs used in loaders
		} catch(IOException e) {
			newcp[0] = p.getDescriptor().getInstallURL();;
		}
		ClassLoader resourceLoader = new URLClassLoader(newcp, null);
		return resourceLoader;
	}
	
	private static Plugin getPlugin(String pluginId) {
		if (pluginId == null) {
			return null;
		}

		IPluginRegistry registry = Platform.getPluginRegistry();
		IPluginDescriptor pluginDesc = registry.getPluginDescriptor(pluginId);
		if(pluginDesc == null) {
			return null;
		}
		
		try {
			return pluginDesc.getPlugin();
		}
		catch(CoreException exc) {
			Logger logger = PropertiesValidatorPlugin.getPlugin().getMsgLogger();;
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			return null;
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
		buffer.append("::");
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
