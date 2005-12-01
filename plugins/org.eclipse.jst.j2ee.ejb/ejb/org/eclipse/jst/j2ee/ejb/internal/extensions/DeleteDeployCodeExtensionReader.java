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
 * Created on Jun 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.ejb.internal.extensions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.RegistryReader;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;


/**
 * @author vijayb
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DeleteDeployCodeExtensionReader extends RegistryReader {
	static DeleteDeployCodeExtensionReader instance = null;
	protected List deleteDeployCodeExtensions;

	/**
	 * @param registry
	 * @param pluginID
	 * @param extensionPoint
	 */
	public DeleteDeployCodeExtensionReader() {
		super(EjbPlugin.PLUGIN_ID, "DeleteDeployCode"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.RegistryReader#readElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	public boolean readElement(IConfigurationElement element) {
		if (DeleteDeployCodeExtension.DELETE_DEPLOY_CODE_EXTENSION.equals(element.getName())) {
			addExtension(element);
			return true;
		}
		return false;
	}

	/**
	 * Gets the instance.
	 * 
	 * @return Returns a DeleteDeployCodeExtensionReader
	 */
	public static DeleteDeployCodeExtensionReader getInstance() {
		if (instance == null) {
			instance = new DeleteDeployCodeExtensionReader();
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
		getDeleteDeployCodeExtensions().add(new DeleteDeployCodeExtension(newExtension));
	}

	/**
	 * Sets the extension point.
	 * 
	 * @param extensions
	 *            The extensions to set
	 */
	protected void addExtensionPoint(DeleteDeployCodeExtensionReader newExtension) {
		if (deleteDeployCodeExtensions == null)
			deleteDeployCodeExtensions = new ArrayList();
		deleteDeployCodeExtensions.add(newExtension);
	}

	/**
	 * @return Returns the handlerExtensions.
	 */
	protected List getDeleteDeployCodeExtensions() {
		if (deleteDeployCodeExtensions == null)
			deleteDeployCodeExtensions = new ArrayList();
		return deleteDeployCodeExtensions;
	}

	/**
	 * @return the appropriate handler for the project based on priorities of those which are
	 *         available and enabled
	 */
	public DeleteDeployCode getDeleteDeployCodeExt() {
		DeleteDeployCodeExtension delDepCodeExt;
		for (Iterator delDepCodeExtItr = getDeleteDeployCodeExtensions().iterator(); delDepCodeExtItr.hasNext();) {
			delDepCodeExt = (DeleteDeployCodeExtension) delDepCodeExtItr.next();
			return delDepCodeExt.getInstance();
		}
		return null;
	}

}
