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
package org.eclipse.jst.j2ee.internal.servertarget;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class SyncEARServerTargetDataModel extends ServerTargetDataModel {

	/**
	 * default false, type Boolean. Set this to true if the operation is supposed to sync all
	 * dependent modules and projects in an ear if the passed project name is an ear project
	 */

	public static final String SYNC_SERVER_TARGET_MODULES = "ServerTargetDataModel.SYNC_SERVER_TARGET_MODULES"; //$NON-NLS-1$

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SYNC_SERVER_TARGET_MODULES);
	}
}