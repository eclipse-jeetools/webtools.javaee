/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;

public class SingleRootStatus extends Status implements ISingleRootStatus {

	IPath path;
	IContainer container;

	public SingleRootStatus(int type, int code, IPath resourcePath, IContainer containerPath, String message, Throwable exception) {
		super(type, CommonFrameworksPlugin.PLUGIN_ID, code, message, exception);
		path = resourcePath;
		container = containerPath;
	}
	
	public SingleRootStatus(int code, IPath resourcePath, IContainer containerPath) {
		this(getSeverity(code), code, resourcePath, containerPath, null, null);
	}
	
	public IPath getPath() {
		return path;
	}

	public IContainer getSingleRoot() {
		return container;
	}
	
	protected static int getSeverity(int code) {
		if( code < 33 ) return IStatus.INFO;
		if( code < 128 ) return IStatus.WARNING;
		return IStatus.ERROR;
	}

}
