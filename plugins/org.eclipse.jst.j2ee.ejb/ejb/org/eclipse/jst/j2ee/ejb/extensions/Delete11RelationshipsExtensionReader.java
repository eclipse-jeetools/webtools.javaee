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
 * Created on Jun 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.ejb.extensions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jst.j2ee.ejb.plugin.EjbPlugin;

import com.ibm.wtp.common.RegistryReader;

/**
 * @author vijayb
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class Delete11RelationshipsExtensionReader extends RegistryReader {
	static Delete11RelationshipsExtensionReader instance = null;
	protected List delete11RelationshipsExtensions;

	/**
	 * @param registry
	 * @param pluginID
	 * @param extensionPoint
	 */
	public Delete11RelationshipsExtensionReader() {
		super(EjbPlugin.PLUGIN_ID, "Delete11Relationships"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.RegistryReader#readElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	public boolean readElement(IConfigurationElement element) {
		if (Delete11RelationshipsExtension.DELETE_11_RELATIONSHIPS_EXTENSION.equals(element.getName())) {
			addExtension(element);
			return true;
		}
		return false;
	}

	/**
	 * Sets the extension point.
	 * 
	 * @param extensions
	 *            The extensions to set
	 */
	protected void addExtension(IConfigurationElement newExtension) {
		getDelete11RelationshipsExtensions().add(new Delete11RelationshipsExtension(newExtension));
	}

	/**
	 * Sets the extension point.
	 * 
	 * @param extensions
	 *            The extensions to set
	 */
	protected void addExtensionPoint(Delete11RelationshipsExtensionReader newExtension) {
		if (delete11RelationshipsExtensions == null)
			delete11RelationshipsExtensions = new ArrayList();
		delete11RelationshipsExtensions.add(newExtension);
	}

	/**
	 * @return Returns the handlerExtensions.
	 */
	protected List getDelete11RelationshipsExtensions() {
		if (delete11RelationshipsExtensions == null)
			delete11RelationshipsExtensions = new ArrayList();
		return delete11RelationshipsExtensions;
	}

	/**
	 * Gets the instance.
	 * 
	 * @return Returns a Delete11RelationshipsExtensionReader
	 */
	public static Delete11RelationshipsExtensionReader getInstance() {
		if (instance == null) {
			instance = new Delete11RelationshipsExtensionReader();
			instance.readRegistry();
		}
		return instance;
	}

	/**
	 * @return the appropriate handler for the project based on priorities of those which are
	 *         available and enabled
	 */
	public Delete11Relationships getDeleteDeployCodeExt() {
		Delete11RelationshipsExtension del11RelExt;
		for (Iterator del11RelExtItr = getDelete11RelationshipsExtensions().iterator(); del11RelExtItr.hasNext();) {
			del11RelExt = (Delete11RelationshipsExtension) del11RelExtItr.next();
			return del11RelExt.getInstance();
		}
		return null;
	}

}