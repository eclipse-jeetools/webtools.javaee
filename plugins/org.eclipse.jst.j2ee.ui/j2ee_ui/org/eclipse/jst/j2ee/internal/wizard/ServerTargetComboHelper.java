/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Aug 27, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code
 * and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.List;

import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;


public class ServerTargetComboHelper {
	private List targets;
	public String[] items;
	public static final String defaultId = "com.ibm.etools.websphere.serverTarget.base.v51"; //$NON-NLS-1$
	public static final String defaultExpressId = "com.ibm.etools.websphere.serverTarget.express.v51"; //$NON-NLS-1$

	ServerTargetComboHelper(List targets, String[] items) {
		this.targets = targets;
		this.items = items;
	}

	public IRuntime getSelectedTarget(int itemIndex) {
		return (IRuntime) targets.get(itemIndex);
	}

	/**
	 * @param target
	 * @return
	 */
	public boolean isCompatible(IRuntime target) {
		return targets.contains(target);
	}

	public int getDefaultServerTargetIndex() {
		IRuntime v51TargetServer = null;
		if (J2EEPlugin.isEJBSupportAvailable())
			v51TargetServer = ServerCore.getResourceManager().getRuntime(defaultId);
		else
			v51TargetServer = ServerCore.getResourceManager().getRuntime(defaultExpressId);
		if (v51TargetServer != null && targets.contains(v51TargetServer))
			return targets.indexOf(v51TargetServer) + 1;
		return -1;
	}

	public int getServerTargetIndexFromItems(IRuntime serverTarget) {
		if (items != null && items.length > 0) {
			String serverTargetLabel = serverTarget.getName() + " (" + serverTarget.getRuntimeType().getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
			for (int i = 0; i < items.length; i++) {
				String label = items[i];
				if (label.equals(serverTargetLabel)) {
					return i;
				}
			}
		}
		return -1;
	}

	public List getValidTargets() {
		return targets;
	}
};