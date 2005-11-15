/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.ejb.refactor;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * Subclass of <code>UpdateEJBClientRefOp</code> that updates the ejb client ref 
 * in the ejb-jar.xml file of a dependent EJB module when the name of the ejb 
 * client project is deleted.
 */
public class UpdateEJBClientRefOnDeleteOp extends UpdateEJBClientRefOp {

	public UpdateEJBClientRefOnDeleteOp() {
		super();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#execute(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return updateEJBClientRef(monitor, true);
	}

}
