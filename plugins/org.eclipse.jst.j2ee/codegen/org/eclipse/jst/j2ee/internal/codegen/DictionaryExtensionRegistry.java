/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.codegen;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Plugin;

public class DictionaryExtensionRegistry {
	static final String GROUP_ID = "groupID"; //$NON-NLS-1$
	static final String BASE_DICTIONARY = "baseDictionary"; //$NON-NLS-1$
	static final String EXTENSION_DICTIONARY = "extensionDictionary"; //$NON-NLS-1$
	private static DictionaryExtensionReader reader = null;

	private static HashMap dictionaryExtMap = null;

	private static boolean extPointHasRead = false;

	public DictionaryExtensionRegistry() {
		super();
	}

	public static GeneratorDictionary generateDictionary(String xmlDictionaryName, ClassLoader loader) throws FileNotFoundException {
		dictionaryExtMap = getDictionaryExtensions();
		if (dictionaryExtMap == null)
			return new GeneratorDictionary(xmlDictionaryName, loader);
		List extensionsList = (List) dictionaryExtMap.get(xmlDictionaryName);
		if (extensionsList == null)
			return new GeneratorDictionary(xmlDictionaryName, loader);

		GeneratorDictionaryLookUp lookUp = new GeneratorDictionaryLookUp(xmlDictionaryName, loader);
		for (int i = 0; i < extensionsList.size(); i++) {
			IConfigurationElement configElement = (IConfigurationElement) extensionsList.get(i);
			String groupID = configElement.getAttribute(GROUP_ID);
			String extDictionary = configElement.getAttribute(EXTENSION_DICTIONARY);
			try {
				Plugin plugin = configElement.getDeclaringExtension().getDeclaringPluginDescriptor().getPlugin();
				ClassLoader extLoader = plugin.getClass().getClassLoader();
				lookUp.addDictionaryExtension(extDictionary, extLoader, groupID);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lookUp;
	}

	private static HashMap getDictionaryExtensions() {
		if (!extPointHasRead) {
			reader = new DictionaryExtensionReader();
			reader.readRegistry();
			extPointHasRead = true;
		}
		if (reader != null)
			return DictionaryExtensionReader.getDictionaryExtensions();
		return null;
	}
}