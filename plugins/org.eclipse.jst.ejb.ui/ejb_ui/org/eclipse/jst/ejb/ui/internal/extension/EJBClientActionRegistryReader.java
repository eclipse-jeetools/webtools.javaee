/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.extension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jst.ejb.ui.internal.plugin.EJBUIPlugin;
import org.eclipse.wst.common.core.util.RegistryReader;

public class EJBClientActionRegistryReader extends RegistryReader {
	private static EJBClientActionRegistryReader instance;
	private List<EJBClientActionExtension> ejbClientCreationExtensions;

	public EJBClientActionRegistryReader() {
		super(EJBUIPlugin.PLUGIN_ID, EJBClientActionExtension.EJB_CLIENT_CREATION_EXTENSION);
	}

	@Override
	public boolean readElement(IConfigurationElement element) {
		if (EJBClientActionExtension.ACTION.equals(element.getName()))
		{
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
		getEJBClientActionExtensions().add(new EJBClientActionExtension(newExtension));
	}

	/**
	 * @return Returns the handlerExtensions.
	 */
	protected List<EJBClientActionExtension> getEJBClientActionExtensions()
	{
		if (ejbClientCreationExtensions == null)
		{
			ejbClientCreationExtensions = new ArrayList<EJBClientActionExtension>();
		}
		return ejbClientCreationExtensions;
	}

	public List<IEJBClientActionExtender> getEJBClientActionExtenders()
	{
		List<IEJBClientActionExtender> retVal = new ArrayList<IEJBClientActionExtender>();
		Iterator<EJBClientActionExtension> i = getEJBClientActionExtensions().iterator();
		IEJBClientActionExtender current = null;
		while (i.hasNext())
		{
			current = i.next().getInstance();
			if (current != null)
			{
				retVal.add(current);
			}
		}
		
		return retVal;
	}

	/**
	 * Gets the instance.
	 * 
	 * @return Returns an EJBClientCreationRegistryReader
	 */
	public static EJBClientActionRegistryReader getInstance()
	{
		if (instance == null)
		{
			instance = new EJBClientActionRegistryReader();
			instance.readRegistry();
		}
		return instance;
	}
}
