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
package org.eclipse.jst.j2ee.applicationclient.creation;

import java.util.Map;

import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory;



/**
 * @deprecated
 * Use
 * <p>
 * 		AppClientAritfactEdit
 * </p>
 */
public class AppClientEditModelFactory extends EditModelFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory#createEditModelForRead(java.lang.Object,
	 *      org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext)
	 */
	public EditModel createEditModelForRead(String editModelID, EMFWorkbenchContext context, Map params) {
		return new AppClientEditModel(editModelID, context, true, isLoadKnownResourcesAsReadOnly());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory#createEditModelForWrite(java.lang.Object,
	 *      org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext)
	 */
	public EditModel createEditModelForWrite(String editModelID, EMFWorkbenchContext context, Map params) {
		return new AppClientEditModel(editModelID, context, false, isLoadKnownResourcesAsReadOnly());
	}

}