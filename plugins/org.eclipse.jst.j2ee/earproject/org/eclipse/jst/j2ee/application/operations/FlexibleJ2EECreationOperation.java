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
 * Created on Oct 31, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;

public abstract class FlexibleJ2EECreationOperation extends WTPOperation {

	public FlexibleJ2EECreationOperation(FlexibleJ2EECreationDataModel dataModel) {
		super(dataModel);
	}

	public FlexibleJ2EECreationOperation() {
		super();
	}

	//protected abstract void createModule(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException;

}