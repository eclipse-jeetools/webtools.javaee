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
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import java.util.HashSet;
import java.util.Set;

import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ExportStrategy;


/**
 * Abstract implementer off which other export strategies can subclass
 * 
 * @see ExportStrategy
 */
public abstract class ExportStrategyImpl extends ArchiveStrategyImpl implements ExportStrategy {
	/** The list of files already saved by this strategy during invokation of preSave() */
	protected Set savedFiles;

	public ExportStrategyImpl() {
		super();
	}

	public java.util.Set getSavedFiles() {
		if (savedFiles == null)
			savedFiles = new HashSet();
		return savedFiles;
	}

	public boolean hasSaved(String uri) {
		return savedFiles != null && getSavedFiles().contains(uri);
	}

	/**
	 * The default is to do nothing; subclasses may wish to override; typical operations would be to
	 * add extra elements to the outgoing archive, using SaveStrategy#save(File,
	 * java.io.InputStream)
	 */
	public void preSave(SaveStrategy aSaveStrategy) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException {
		//Default
	}

	/**
	 * The file with the given uri has just been saved; add this to the list of saved files
	 */
	protected void saved(String uri) {
		getSavedFiles().add(uri);
	}

	public void setSavedFiles(java.util.Set newSavedFiles) {
		savedFiles = newSavedFiles;
	}
}
