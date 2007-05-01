/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive.internal;

import java.util.List;

import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveHandler;


public class ArchiveHanderFactory {

	private static ArchiveHanderFactory instance = null;

	public static ArchiveHanderFactory getInstance() {
		if (instance == null) {
			instance = new ArchiveHanderFactory();
		}
		return instance;
	}

	private ArchiveHanderFactory() {
	}

	public IArchiveHandler getArchiveHandler(IArchive archive) {
		return null;
	}

	public List getArchiveHandlers() {
		return null;
	}

}
