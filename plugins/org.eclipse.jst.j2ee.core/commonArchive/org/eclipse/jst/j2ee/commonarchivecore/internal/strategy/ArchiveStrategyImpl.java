/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;

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

	@Override
	public Archive getArchive() {
		return archive;
	}

	@Override
	public void setArchive(Archive newArchive) {
		archive = newArchive;
	}
}
