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
 * Created on Oct 29, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.codegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;

import com.ibm.wtp.common.RegistryReader;

/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DictionaryExtensionReader extends RegistryReader {
	private static HashMap dictionaryExtMap = null;
	static final String ELEMENT_DICT_EXTENSION = "dictionaryExtension"; //$NON-NLS-1$ 
	static final String BASE_DICTIONARY = "baseDictionary"; //$NON-NLS-1$
	static final String EXTENSION_DICTIONARY = "extensionDictionary"; //$NON-NLS-1$

	public DictionaryExtensionReader() {
		super(J2EEPlugin.PLUGIN_ID, "GeneratorDictionaryExtension"); //$NON-NLS-1$
	}

	/**
	 * readElement() - parse and deal w/ an extension like: <dictionaryExtension groupID = "IBM"
	 * baseDictionary = "com.ibm.etools.foo.BaseDictionary.xml" extensionDictionary =
	 * "com.ibm.etools.foo.ExtendingDictionary.xml"/>
	 */
	public boolean readElement(IConfigurationElement element) {
		if (!element.getName().equals(ELEMENT_DICT_EXTENSION))
			return false;
		if (element.getAttribute(BASE_DICTIONARY) == null || element.getAttribute(EXTENSION_DICTIONARY) == null) {
			logMissingAttribute(element, "Incomplete dictionary extension specification."); //$NON-NLS-1$
			return false;
		}
		addDictionaryExtension(element);
		return true;
	}

	/**
	 * @param op
	 * @param baseDictionary
	 * @param extDictionary
	 */
	private static void addDictionaryExtension(IConfigurationElement element) {
		if (dictionaryExtMap == null)
			dictionaryExtMap = new HashMap();
		String baseDictionary = element.getAttribute(BASE_DICTIONARY);
		List configElements = (List) dictionaryExtMap.get(baseDictionary);
		if (configElements != null) {
			dictionaryExtMap.remove(baseDictionary);
		} else { //first time
			configElements = new ArrayList();
		}
		configElements.add(element);
		dictionaryExtMap.put(baseDictionary, configElements);
	}

	protected static HashMap getDictionaryExtensions() {
		return dictionaryExtMap;
	}

}