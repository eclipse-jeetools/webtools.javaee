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
package org.eclipse.jst.j2ee.ejb.internal.operations;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.common.jdt.internal.integration.WorkingCopyProvider;




/**
 * Insert the type's description here. Creation date: (8/29/2001 4:50:44 PM)
 * 
 * @author: Administrator
 */
public interface IExtendedEJBCommand extends Command {
	void setEditModel(org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel newEditModel);

	void setEjb(EnterpriseBean newEjb);

	void setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor newProgressMonitor);

	/**
	 * Set if you want to provide a WorkingCopyProvider other than the EJBEditModel.
	 */
	void setWorkingCopyProviderOverride(WorkingCopyProvider newWorkingCopyProviderOverride);
}