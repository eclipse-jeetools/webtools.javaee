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



import java.util.ArrayList;
import java.util.List;

/**
 * No-op implementer used for new archive instances, which have their contents added and copied from
 * other sources
 */
public class NullLoadStrategyImpl extends LoadStrategyImpl {
	protected static List emptyList = new ArrayList(0);

	/**
	 * NullLoadStrategy constructor comment.
	 */
	public NullLoadStrategyImpl() {
		super();
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl
	 */
	public boolean contains(String uri) {
		return false;
	}

	protected boolean primContains(String uri) {
		return false;
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl
	 */
	public java.util.List getFiles() {
		return emptyList;
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl
	 */
	public java.io.InputStream getInputStream(String uri) throws java.io.IOException, java.io.FileNotFoundException {
		throw new java.io.FileNotFoundException(uri);
	}
}
