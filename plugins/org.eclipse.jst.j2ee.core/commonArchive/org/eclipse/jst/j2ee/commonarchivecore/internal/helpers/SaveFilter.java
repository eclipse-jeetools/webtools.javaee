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



import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;

/**
 * Object used for saving an archive with only a subset of its files. By default all files are
 * saved. Clients can create a custom filter and set it on a save strategy, and call
 * {@link Archive#save(SaveStrategy)}
 */
public interface SaveFilter {
	/**
	 * Answer whether an element in the archive having the uri should be saved; the uri may be for a
	 * file, a nested archive, or a loaded mof resource
	 */
	public boolean shouldSave(String uri, Archive anArchive);
}
