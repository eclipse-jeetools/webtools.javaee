/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * @author jsholl
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DeleteOnExitUtility {

	private static Set tempFilesToDelete;
	private static boolean initialized = false;
	private static int cleanupCount = 0;
	private static final int CLEANUP_THRESHOLD = 10000;

	public static void markForDeletion(File file) {
		if (!initialized) {
			initialized = true;
			try {
				Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
					public void run() {
						if (tempFilesToDelete != null) {
							synchronized (tempFilesToDelete) {
								Iterator iterator = tempFilesToDelete.iterator();
								File file = null;
								while (iterator.hasNext()) {
									try {
										file = (File) iterator.next();
										if (file.exists()) {
											file.delete();
										}
									} catch (SecurityException e) {
									}
								}
							}
						}
					}
				}));
				tempFilesToDelete = new HashSet();
			} catch (SecurityException e) {
			}
		}
		if (tempFilesToDelete != null) {
			synchronized (tempFilesToDelete) {
				cleanupCount++;
				tempFilesToDelete.add(file);
			}
			if (cleanupCount > CLEANUP_THRESHOLD) {
				runCleanup();
			}
		}
	}

	public static void fileHasBeenDeleted(File file) {
		if (tempFilesToDelete != null) {
			synchronized (tempFilesToDelete) {
				cleanupCount++;
				try {
					if (!file.exists()) {
						tempFilesToDelete.remove(file);
					}
				} catch (SecurityException e) {
				}
			}
			if (cleanupCount > CLEANUP_THRESHOLD) {
				runCleanup();
			}
		}
	}

	public static void runCleanup() {
		if (tempFilesToDelete != null) {
			synchronized (tempFilesToDelete) {
				cleanupCount = 0;
				List filesToRemove = null;
				Iterator iterator = tempFilesToDelete.iterator();
				File file = null;
				while (iterator.hasNext()) {
					try {
						file = (File) iterator.next();
						if (!file.exists()) {
							if (filesToRemove == null) {
								filesToRemove = new ArrayList();
							}
							filesToRemove.add(file);
						}
					} catch (SecurityException e) {
					}
				}
				if (filesToRemove != null) {
					tempFilesToDelete.removeAll(filesToRemove);
				}
			}
		}
	}


}
