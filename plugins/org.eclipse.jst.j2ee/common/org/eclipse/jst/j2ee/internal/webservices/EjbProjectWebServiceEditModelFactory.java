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
 * Created on Feb 3, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservices;

import java.util.Map;

import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;


/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class EjbProjectWebServiceEditModelFactory extends WebServiceEditModelFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory#createEditModelForRead(java.lang.Object,
	 *      org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext)
	 */
	public EditModel createEditModelForRead(String editModelID, EMFWorkbenchContext context, Map params) {
		return super.createEditModelForRead(editModelID, context, params, WebServiceEditModel.EJB_PROJECT_WEBSERVICE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory#createEditModelForWrite(java.lang.Object,
	 *      org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext)
	 */
	public EditModel createEditModelForWrite(String editModelID, EMFWorkbenchContext context, Map params) {
		return super.createEditModelForWrite(editModelID, context, params, WebServiceEditModel.EJB_PROJECT_WEBSERVICE);
	}
}