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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jst.ejb.ui.internal.plugin.EJBUIPlugin;

public class EJBClientActionExtension {
	public static final String EJB_CLIENT_CREATION_EXTENSION = "ejbClientActionExtension"; //$NON-NLS-1$
	public static final String ATT_ID = "id"; //$NON-NLS-1$
	public static final String ATT_CLASS = "class"; //$NON-NLS-1$
	public static final String ACTION = "action"; //$NON-NLS-1$
	private String id = null;
	private IConfigurationElement element;
	private IEJBClientActionExtender instance;
	private boolean errorCondition = false;

	public EJBClientActionExtension(IConfigurationElement newExtension) {
		if (!ACTION.equals(newExtension.getName()))
		{
			throw new IllegalArgumentException("Extensions must be of the type \"" + ACTION + "\".");  //$NON-NLS-1$//$NON-NLS-2$
		}
		element = newExtension;
		init();
	}

	private void init() {
		id = element.getAttribute(ATT_ID);
	}

	public String getId()
	{
		return id;
	}

	public IEJBClientActionExtender getInstance()
	{
		try
		{
			if (instance == null && !errorCondition)
			{
				instance = (IEJBClientActionExtender) element.createExecutableExtension(ATT_CLASS);
			}
		}
		catch (Throwable e)
		{
			EJBUIPlugin.logError(e);
			errorCondition = true;
		}
		return instance;
	}
}
