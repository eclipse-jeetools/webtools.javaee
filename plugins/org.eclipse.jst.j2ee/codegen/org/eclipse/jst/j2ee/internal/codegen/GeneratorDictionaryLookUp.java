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


public class GeneratorDictionaryLookUp extends GeneratorDictionary {

	GeneratorDictionary dictionaryExtension = null;
	String dictionaryExtensionID = null;
	HashMap dictionaryExtMap = null;
	boolean isMulti = false;

	public GeneratorDictionaryLookUp(String dictionaryName, ClassLoader loader) throws FileNotFoundException {
		super(dictionaryName, loader);
	}

	public void addDictionaryExtension(String extDictionaryName, ClassLoader loader, String groupID) throws FileNotFoundException {
		if (dictionaryExtension == null) {
			dictionaryExtension = new GeneratorDictionary(extDictionaryName, loader);
			dictionaryExtensionID = groupID;
		} else {
			isMulti = true;
			if (dictionaryExtMap == null) {
				dictionaryExtMap = new HashMap();
				dictionaryExtMap.put(dictionaryExtensionID, dictionaryExtension);
			}
			GeneratorDictionary dictHolder = new GeneratorDictionary(extDictionaryName, loader);
			dictionaryExtMap.put(groupID, dictHolder);
		}
	}

	public Generator get(String logicalName) throws GeneratorNotFoundException {
		//TODO: use the groupID as a filter depending if isMulti is true
		if (!isMulti) {
			try {
				Generator genHolder = dictionaryExtension.get(logicalName);
				if (genHolder != null)
					return genHolder;
			} catch (GeneratorNotFoundException ex) {
				//do nothing as the generator may be in subsequent extension dictionaries or the
				// base.
			}
		}
		return super.get(logicalName);
	}
}