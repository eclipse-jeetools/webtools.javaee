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
package org.eclipse.jst.j2ee.commonarchivecore.strategy;



import org.eclipse.jst.j2ee.commonarchivecore.Archive;

/**
 * ImportStrategy knows how to interpret the information in an archive, usually in deployment
 * descriptors, to build up a model of the contained objects.
 */
public interface ImportStrategy extends ArchiveStrategy {
	/**
	 * Helper method used for copying archives; create a new instance of this kind of strategy
	 */
	public ImportStrategy createImportStrategy(Archive old, Archive newArchive);

	/**
	 * Do whatever is necessary to bring in metadata from the archive and set the deployment
	 * descriptors to the standard form that the apis can recognize
	 */
	public void importMetaData() throws Exception;

	public boolean isEJB10();
}
