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
package org.eclipse.jst.jee.archive;

/**
 * An {@link IArchiveHandler} handles a specific type of {@link IArchive}.
 * {@link IArchiveHandler}s are defined via extension points.
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

	/**
	 * Returns a new {@link IArchive} from the passed {@link IArchive}. The
	 * returned {@link IArchive} may the be cast to a more specific type as
	 * defined by the specifie {@link IArchiveHandler} performing the
	 * conversion. This method throws an {@link ArchiveOpenFailureException} if
	 * this {@link IArchiveHandler} can not handle the passed IArchive as
	 * specified by {@link #handlesArchive(IArchive)}.
	 * 
	 * @param archive
	 * @return
	 * @throws ArchiveOpenFailureException
	 */
	public IArchive convertToSpecificArchive(IArchive archive) throws ArchiveOpenFailureException;

}
