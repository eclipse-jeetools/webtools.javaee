/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.util;

import java.io.File;


/**
 * @author jsholl
 */
public class DeleteOnExitUtility {

	public static void markForDeletion(File file) {
		org.eclipse.jst.jee.archive.internal.DeleteOnExitUtility.markForDeletion(file);
	}

	public static void fileHasBeenDeleted(File file) {
		org.eclipse.jst.jee.archive.internal.DeleteOnExitUtility.fileHasBeenDeleted(file);
	}

	public static void runCleanup() {
		org.eclipse.jst.jee.archive.internal.DeleteOnExitUtility.runCleanup();
	}


}
