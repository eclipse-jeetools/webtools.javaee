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
package org.eclipse.jst.j2ee.internal.ejb.operations;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;

/**
 * Insert the type's description here. Creation date: (12/12/2000 4:08:21 PM)
 * 
 * @author: Administrator
 */
public interface IEJBProgressCommand extends Command {
	/**
	 * Insert the method's description here. Creation date: (12/12/2000 4:10:56 PM)
	 * 
	 * @param monitor
	 *            com.ibm.itp.common.IProgressMonitor
	 */
	IProgressMonitor getProgressMonitor();

	/**
	 * Insert the method's description here. Creation date: (12/12/2000 4:10:56 PM)
	 * 
	 * @param monitor
	 *            com.ibm.itp.common.IProgressMonitor
	 */
	void setProgressMonitor(IProgressMonitor monitor);
}