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
/*
 * Created on Oct 27, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.jca.operations;

import java.util.Map;

import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory;


/**
 * 
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * 
 * @deprecated
 * Use
 * <p>
 * 		ConnectorArtifactEdit
 * </p>
 */
public class ConnectorEditModelFactory extends EditModelFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory#createEditModelForRead(java.lang.String,
	 *      org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext)
	 */
	public EditModel createEditModelForRead(String editModelID, EMFWorkbenchContext context, Map params) {
		return new ConnectorEditModel(editModelID, context, true, isLoadKnownResourcesAsReadOnly());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory#createEditModelForWrite(java.lang.String,
	 *      org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext)
	 */
	public EditModel createEditModelForWrite(String editModelID, EMFWorkbenchContext context, Map params) {
		return new ConnectorEditModel(editModelID, context, false, isLoadKnownResourcesAsReadOnly());
	}



}