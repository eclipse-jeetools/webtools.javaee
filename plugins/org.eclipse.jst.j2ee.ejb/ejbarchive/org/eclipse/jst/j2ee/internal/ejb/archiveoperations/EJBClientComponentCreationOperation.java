/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.internal.operations.JavaUtilityComponentCreationOperation;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class EJBClientComponentCreationOperation extends JavaUtilityComponentCreationOperation {

	/**
	 * @param dataModel
	 */
	public EJBClientComponentCreationOperation(EJBClientComponentDataModel dataModel) {
		super(dataModel);
	}
	
	 protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
	    super.execute(IModuleConstants.JST_UTILITY_MODULE, monitor);
	 }	
}