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
 * This an abstract type off which any implementers of the archive strategy can subclass.
 */
public abstract class ArchiveStrategyImpl implements ArchiveStrategy {
	protected Archive archive;

	/**
	 * ArchiveStrategy constructor comment.
	 */
	public ArchiveStrategyImpl() {
		super();
	}

	public Archive getArchive() {
		return archive;
	}

	public void setArchive(Archive newArchive) {
		archive = newArchive;
	}
}
