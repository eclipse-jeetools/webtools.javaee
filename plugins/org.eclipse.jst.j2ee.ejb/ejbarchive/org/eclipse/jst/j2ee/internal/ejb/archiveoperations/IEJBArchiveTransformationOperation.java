/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;



import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.wst.common.frameworks.operations.IHeadlessRunnableWithProgress;

/**
 * Insert the type's description here. Creation date: (7/17/2001 5:31:45 PM)
 * 
 * @author: Administrator
 */
public interface IEJBArchiveTransformationOperation extends IHeadlessRunnableWithProgress {
	void setArchive(Archive anArchive);

	void setBatch(boolean isBatch);

	void setPerformSplit(boolean shouldSplit);

	void setProject(org.eclipse.core.resources.IProject newProject);

	void setIsImport(boolean newIsImport);
}