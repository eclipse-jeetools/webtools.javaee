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
 * Created on Jun 21, 2004 
 * @author jsholl
 */
package org.eclipse.jst.j2ee.application.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;

import com.ibm.wtp.common.RegistryReader;
import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author jsholl
 */
public class ExtendedImportRegistry extends RegistryReader {

	private static final String PLUGIN_ID = J2EEPlugin.PLUGIN_ID;

	private static final String EXTENSION_ID = "ExtendedModuleImport"; //$NON-NLS-1$

	private static final String FACTORY_CLASS = "factoryClass"; //$NON-NLS-1$

	private static final String MODULE_TYPE = "moduleType"; //$NON-NLS-1$

	public static final String EJB_TYPE = "EJB"; //$NON-NLS-1$

	private List ejbConfigurationElements = null;

	private List ejbFactories = null;

	private static ExtendedImportRegistry instance = null;

	public static ExtendedImportRegistry getInstance() {
		//if (null == instance) {
		instance = new ExtendedImportRegistry();
		//}
		return instance;
	}

	private ExtendedImportRegistry() {
		super(PLUGIN_ID, EXTENSION_ID);
		readRegistry();
	}

	/**
	 * returns a List of ExtendedFactories;
	 * 
	 * @param type
	 * @return
	 */
	public List getFactories(String type) {
		if (type.equals(EJB_TYPE)) {
			if (ejbFactories == null) {
				ejbFactories = new ArrayList();
				for (int i = 0; null != ejbConfigurationElements && i < ejbConfigurationElements.size(); i++) {
					try {
						ejbFactories.add(((IConfigurationElement) ejbConfigurationElements.get(i)).createExecutableExtension(FACTORY_CLASS));
					} catch (CoreException e) {
						Logger.getLogger().logError(e);
					}
				}
			}
			return ejbFactories;
		}
		return null;
	}

	public boolean readElement(IConfigurationElement element) {
		String moduleType = element.getAttribute(MODULE_TYPE);
		if (null != moduleType) {
			if (moduleType.equals(EJB_TYPE)) {
				if (ejbConfigurationElements == null) {
					ejbConfigurationElements = new ArrayList();
				}
				ejbConfigurationElements.add(element);
				return true;
			}
		}
		return false;
	}
}