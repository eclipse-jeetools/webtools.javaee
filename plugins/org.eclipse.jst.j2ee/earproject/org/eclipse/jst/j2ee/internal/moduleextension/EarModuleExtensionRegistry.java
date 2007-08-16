/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
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
package org.eclipse.jst.j2ee.internal.moduleextension;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.RegistryReader;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

/**
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EarModuleExtensionRegistry extends RegistryReader {

	static final String EXTENSION_NAME = "EARModuleExtension"; //$NON-NLS-1$
	static final String ELEMENT_EARMODULE_EXTENSION = "earModuleExtension"; //$NON-NLS-1$
	static final String MODULE_EXTENSION_CLASS = "extensionClass"; //$NON-NLS-1$
	private static EarModuleExtensionRegistry INSTANCE = null;

	public EarModuleExtensionRegistry() {
		super(J2EEPlugin.PLUGIN_ID, EXTENSION_NAME);
	}

	public static EarModuleExtensionRegistry getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new EarModuleExtensionRegistry();
			INSTANCE.readRegistry();
		}
		return INSTANCE;
	}

	/**
	 * readElement() - parse and deal w/ an extension like: <earModuleExtension extensionClass =
	 * "com.ibm.etools.web.plugin.WebModuleExtensionImpl"/>
	 */
	public boolean readElement(IConfigurationElement element) {
		if (!element.getName().equals(ELEMENT_EARMODULE_EXTENSION))
			return false;

		EarModuleExtension extension = null;
		try {
			extension = (EarModuleExtension) element.createExecutableExtension(MODULE_EXTENSION_CLASS);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (extension != null)
			addModuleExtension(extension);
		return true;
	}


	private static void addModuleExtension(EarModuleExtension ext) {
		EarModuleManager.registerModuleExtension(ext);
	}

}
