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
 * Created on Feb 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.servertarget;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Plugin;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class TargetRuntimeExtension {
	String groupID;
	String className;
	IConfigurationElement configElement;
	Plugin plugin;

	/**
	 *  
	 */
	public TargetRuntimeExtension() {
		super();
	}

	public TargetRuntimeExtension(Plugin aPlugin, IConfigurationElement element, String groupId, String handlerClass) {
		super();
		plugin = aPlugin;
		configElement = element;
		groupID = groupId;
		className = handlerClass;
	}

	/**
	 * @return
	 */
	public String getGroupId() {
		return groupID;
	}

	/**
	 * @param string
	 */
	public void setGroupId(String string) {
		groupID = string;
	}
}