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
 * Created on Jul 26, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.ejb.internal.extensions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;

import com.ibm.wtp.common.RegistryReader;

/**
 * @author vijayb
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class BackendMigrationExtensionReader extends RegistryReader {
	static BackendMigrationExtensionReader instance = null;
	protected List backendMigrationExtensions;

	/**
	 * @param registry
	 * @param pluginID
	 * @param extensionPoint
	 */
	public BackendMigrationExtensionReader() {
		super(EjbPlugin.PLUGIN_ID, "BackendMigration"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.RegistryReader#readElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	public boolean readElement(IConfigurationElement element) {
		if (BackendMigrationExtension.BACKEND_MIGRATION_EXTENSION.equals(element.getName())) {
			addExtension(element);
			return true;
		}
		return false;
	}

	/**
	 * Gets the instance.
	 * 
	 * @return Returns a BackendMigrationExtensionReader
	 */
	public static BackendMigrationExtensionReader getInstance() {
		if (instance == null) {
			instance = new BackendMigrationExtensionReader();
			instance.readRegistry();
		}
		return instance;
	}

	/**
	 * Sets the extension point.
	 * 
	 * @param extensions
	 *            The extensions to set
	 */
	protected void addExtension(IConfigurationElement newExtension) {
		getBackendMigrationExtensions().add(new BackendMigrationExtension(newExtension));
	}

	/**
	 * Sets the extension point.
	 * 
	 * @param extensions
	 *            The extensions to set
	 */
	protected void addExtensionPoint(BackendMigrationExtensionReader newExtension) {
		if (backendMigrationExtensions == null)
			backendMigrationExtensions = new ArrayList();
		backendMigrationExtensions.add(newExtension);
	}

	/**
	 * @return Returns the handlerExtensions.
	 */
	protected List getBackendMigrationExtensions() {
		if (backendMigrationExtensions == null)
			backendMigrationExtensions = new ArrayList();
		return backendMigrationExtensions;
	}

	/**
	 * @return the appropriate handler for the project based on priorities of those which are
	 *         available and enabled
	 */
	public BackendMigration getBackendMigrationExt() {
		BackendMigrationExtension backendMigrationExt;
		for (Iterator backendMigExtItr = getBackendMigrationExtensions().iterator(); backendMigExtItr.hasNext();) {
			backendMigrationExt = (BackendMigrationExtension) backendMigExtItr.next();
			return backendMigrationExt.getInstance();
		}
		return null;
	}

}