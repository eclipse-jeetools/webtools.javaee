/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.core;
/*


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
