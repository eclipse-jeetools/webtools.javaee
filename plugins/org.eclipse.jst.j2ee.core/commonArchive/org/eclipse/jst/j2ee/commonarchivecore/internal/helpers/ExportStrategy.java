/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;

import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ArchiveStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;



/**
 * ExportStrategy knows what to do just before a dump of an archive is about to occur, typically
 * adding items such as deployment descriptors and such. This provides a delegation model for
 * archive/version/platform specific rules about which resources etc need to be exported. Clients
 * can implement this interface, and "plug in" to an instance of an archive.
 */
public interface ExportStrategy extends ArchiveStrategy {
	/**
	 * Returns whether this strategy has already saved a file entry having a uri named by the
	 * parameter
	 */
	public boolean hasSaved(String uri);

	/**
	 * The archive is saving itself, and giving the export strategy the opportunity to do whatever
	 * it needs to do. The export strategy may write entries directly to the SaveStrategy, but if it
	 * does, it should remember the names of these entries so the archive does not attempt to
	 * duplicate by saving an entry with the same name
	 */
	public void preSave(SaveStrategy aSaveStrategy) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
}
