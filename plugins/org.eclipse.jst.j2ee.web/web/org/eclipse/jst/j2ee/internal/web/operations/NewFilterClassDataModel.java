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
 * Created on Jun 18, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class NewFilterClassDataModel extends NewWebJavaClassDataModel {

	protected IStatus validateJavaClassName(String className) {
		IStatus status = super.validateJavaClassName(className);
		if (status.isOK()) {
			// do not allow the name "Filter"
			if (className.equals("Filter")) { //$NON-NLS-1$
				String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_CLASS_NAME_INVALID);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
			return WTPCommonPlugin.OK_STATUS;
		}
		return status;
	}
}