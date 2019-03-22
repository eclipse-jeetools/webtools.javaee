/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;



/**
 * Helper class for manifest files
 * @deprecated
 */
public class ArchiveManifestImpl extends org.eclipse.jst.common.internal.modulecore.util.ArchiveManifestImpl 
								implements org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest {
	/**
	 * ArchiveManifest constructor comment.
	 * @deprecated
	 */
	public ArchiveManifestImpl() {
		super();
	}

	/**
	 * ArchiveManifest constructor comment.
	 * 
	 * @param is
	 *            java.io.InputStream
	 * @throws java.io.IOException
	 *             The exception description.
	 * @deprecated
	 */
	public ArchiveManifestImpl(java.io.InputStream is) throws java.io.IOException {
		super(is);
	}

	/**
	 * @deprecated
	 * @param man
	 */
	public ArchiveManifestImpl(java.util.jar.Manifest man) {
		super(man);
	}

}
