/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive;

/**
 * An {@link IArchiveAdapter} provides a bridge so any {@link Object} (or
 * collection of {@link Object}s) may be represented as an {@link IArchive}.
 * Each {@link IArchiveAdapter} should only be used by one {@link IArchive} at a
 * time.
 * 
 * This interface is not intended to be implemented by or subclassed by clients.
 * Clients should instead subclass {@link AbstractArchiveAdapter} or one if its
 * subclasses.
 */
public interface IArchiveAdapter {

	/**
	 * Returns the {@link IArchive} associated with this adapter. Every
	 * { @link IArchiveAdapter} is associated with exactly one {@link IArchive}.
	 * 
	 * @return
	 */
	public IArchive getArchive();

	/**
	 * Sets the {@link IArchive} for this {@link IArchiveAdapter}.
	 * 
	 * @param archive
	 */
	public void setArchive(IArchive archive);

}
