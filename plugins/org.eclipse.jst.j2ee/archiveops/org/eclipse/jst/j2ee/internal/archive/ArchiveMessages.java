/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.archive;

import org.eclipse.osgi.util.NLS;

public class ArchiveMessages extends NLS {

	public static final String BUNDLE_NAME = "org.eclipse.jst.j2ee.internal.archive.messages"; //$NON-NLS-1$
	public static String ComponentArchiveSaveAdapter_Importing_0_;
	public static String ConnectorComponentNestedJARArchiveLoadAdapter_Unable_to_get_contents_from_0_mes_;
	public static String ComponentLoadStrategyImpl_Opener_of_Archive_did_not_close_it_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ArchiveMessages.class);
	}

}
