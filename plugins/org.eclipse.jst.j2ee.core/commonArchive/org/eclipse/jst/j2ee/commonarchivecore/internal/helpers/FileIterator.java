/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;



import java.io.InputStream;

import org.eclipse.jst.j2ee.commonarchivecore.internal.File;


/**
 * Insert the type's description here. Creation date: (05/02/01 5:20:00 PM)
 * 
 * @author: Administrator
 */
public interface FileIterator {
	public InputStream getInputStream(File aFile) throws java.io.IOException, java.io.FileNotFoundException;

	public boolean hasNext();

	public File next();
}
