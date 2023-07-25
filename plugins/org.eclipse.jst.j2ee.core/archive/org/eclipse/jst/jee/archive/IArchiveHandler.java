/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive;

/**
 * An {@link IArchiveHandler} handles a specific type of {@link IArchive}.
 * { @link IArchiveHandler}s are defined via extension points.
 * 
 * This interface is not intended to be implemented by or subclassed by clients.
 * Clients should instead subclass {@link AbstractArchiveHandler} or one if its
 * subclasses.
 */
public interface IArchiveHandler {

	/**
	 * Returns <code>true</code> if this {@link IArchiveHandler} can handle
	 * the specified {@link IArchive}. Various {@link IArchive} types may be
	 * determined by examining the contents of the {@link IArchive}. e.g. by
	 * the existence of certain {@link IArchiveResource}s or their contents.
	 * 
	 * @param archive
	 * @return
	 */
	public boolean handlesArchive(IArchive archive);

}
