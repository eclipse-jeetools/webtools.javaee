/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.project;

import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.ISingleRootStatus;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;

public class SingleRootStatus extends Status implements ISingleRootStatus {

	ComponentResource resource;

	public SingleRootStatus(int type, int code, ComponentResource resource, String message, Throwable exception) {
		super(type, J2EEPlugin.PLUGIN_ID, code, message, exception);
		this.resource = resource;
	}
	
	public SingleRootStatus(int code, ComponentResource resource, String message) {
		this(getSeverity(code), code, resource, message, null);
	}
	
	public ComponentResource getComponentResource() {
		return resource;
	}

	protected static int getSeverity(int code) {
		return code == 0 ? 0 : 1 << (code / 33);
	}

}
