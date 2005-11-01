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
 * Created on Jun 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.jfaces.extension;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * @author vijayb
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class FileURLExtension {
	private String id = null;
	private FileURL instance;
	private boolean errorCondition = false;
	private IConfigurationElement element;
	public static final String FILE_URL_EXTENSION = "fileURL"; //$NON-NLS-1$
	public static final String RUN = "run"; //$NON-NLS-1$
	public static final String ATT_ID = "id"; //$NON-NLS-1$
	public static final String ATT_CLASS = "class"; //$NON-NLS-1$


	/**
	 *  
	 */
	public FileURLExtension() {
		super();
	}

	public FileURL getInstance() {
		try {
			if (this.instance == null && !this.errorCondition)
				this.instance = (FileURL) this.element.createExecutableExtension("run"); //$NON-NLS-1$
		} catch (Throwable e) {
			Logger.getLogger().logError(e);
			this.errorCondition = true;
		}
		return this.instance;
	}

	public FileURLExtension(IConfigurationElement element) {
		if (!FILE_URL_EXTENSION.equals(element.getName()))
			throw new IllegalArgumentException("Extensions must be of the type \"" + FILE_URL_EXTENSION + "\"."); //$NON-NLS-1$ //$NON-NLS-2$
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
		return this.id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
}