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
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;



/**
 * Default filter which allows all elememts to save
 */
public class SaveFilterImpl implements org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter {
	/**
	 * SaveFilterImpl constructor comment.
	 */
	public SaveFilterImpl() {
		super();
	}

	/**
	 * @see com.ibm.etools.archive.SaveFilter
	 */
	@Override
	public boolean shouldSave(java.lang.String uri, Archive anArchive) {
		return true;
	}
}
