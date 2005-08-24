/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.core;
/*
 *  $RCSfile: IBeaninfosDocEntry.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:31:28 $ 
 */

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Interface for DOM Doc writable entries.
 * 
 * @version 	1.0
 * @author
 */
public interface IBeaninfosDocEntry {
	public Node writeEntry(Document doc, IProject project);
	
	public int getKind();
	public IPath getPath();
}
