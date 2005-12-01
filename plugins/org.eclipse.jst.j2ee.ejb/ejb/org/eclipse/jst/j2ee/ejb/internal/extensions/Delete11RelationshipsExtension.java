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
 * Created on Jun 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.ejb.internal.extensions;

import org.eclipse.core.internal.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * @author vijayb
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class Delete11RelationshipsExtension {
	private String id = null;
	private Delete11Relationships instance;
	private boolean errorCondition = false;
	private IConfigurationElement element;
	public static final String DELETE_11_RELATIONSHIPS_EXTENSION = "delete11RelationshipsExHandler"; //$NON-NLS-1$
	public static final String RUN = "run"; //$NON-NLS-1$
	public static final String ATT_ID = "id"; //$NON-NLS-1$
	public static final String ATT_CLASS = "class"; //$NON-NLS-1$

	/**
	 *  
	 */
	public Delete11RelationshipsExtension() {
		super();
	}

	public Delete11Relationships getInstance() {
		try {
			if (instance == null && !errorCondition)
				instance = (Delete11Relationships) element.createExecutableExtension("run"); //$NON-NLS-1$
		} catch (Throwable e) {
			Logger.getLogger().logError(e);
			errorCondition = true;
		}
		return instance;
	}

	public Delete11RelationshipsExtension(IConfigurationElement element) {
		Assert.isLegal(DELETE_11_RELATIONSHIPS_EXTENSION.equals(element.getName()), "Extensions must be of the type \"" + DELETE_11_RELATIONSHIPS_EXTENSION + "\"."); //$NON-NLS-1$ //$NON-NLS-2$
		this.element = element;
		init();
	}

	private void init() {
		this.id = this.element.getAttribute(ATT_ID);
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
}
