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
 * Created on Mar 8, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.migration;

import org.eclipse.wst.common.framework.operation.IOperationHandler;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AppClientMetadataMigrationOperation extends J2EEProjectMetadataMigrationOperation {
	/**
	 * @param config
	 * @param aTargetVersion
	 * @param handler
	 */
	public AppClientMetadataMigrationOperation(J2EEMigrationConfig config, String aTargetVersion, IOperationHandler handler) {
		super(config, aTargetVersion, handler);
		setCurrentProject(config.getTargetProject());
	}
}