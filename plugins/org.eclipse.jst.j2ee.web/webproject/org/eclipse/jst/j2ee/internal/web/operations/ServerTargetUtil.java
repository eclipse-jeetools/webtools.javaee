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
 * Created on Sep 12, 2003
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.servertarget.IServerTargetConstants;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * A class with some useful methods that support server targetting for Web projects.
 * 
 * @author Pratik Shah
 */
public class ServerTargetUtil {

	private static final String defaultId = "com.ibm.etools.websphere.serverTarget.base.v51"; //$NON-NLS-1$
	private static final String defaultExpressId = "com.ibm.etools.websphere.serverTarget.express.v51"; //$NON-NLS-1$

	/**
	 * @param targets
	 *            A list of IServerTargets
	 * @return The index of the target server with the
	 *         {@link #getDefaultServerTargetId() default Id}; or 0, if it could not be found.
	 */
	public static int findDefaultServerTargetIndex(List targets) {
		int index = 0;
		for (int i = 0; i < targets.size(); i++) {
			IRuntime target = (IRuntime) targets.get(i);
			if (target.getId().equals(getDefaultServerTargetId())) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * @param target
	 *            The IServerTarget that has to be found in the given list; it can be
	 *            <code>null</code>
	 * @param list
	 *            The List from which the IServerTarget has to be found
	 * 
	 * @return The index of the given target in the given list; or 0, if the given target could not
	 *         be found in the given list. Two IServerTargets are considered to be equal if they
	 *         have the same ID.
	 */
	public static int findIndexOf(IRuntime target, List list) {
		int index = 0;
		if (target != null) {
			for (int i = 0; i < list.size(); i++) {
				IRuntime element = (IRuntime) list.get(i);
				if (element.equals(target)) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

	/**
	 * @return the Id of the target server that should be selected by default
	 */
	public static String getDefaultServerTargetId() {
		String id = defaultExpressId;
		if (J2EEPlugin.isEJBSupportAvailable()) {
			id = defaultId;
		}
		return id;
	}

	/**
	 * @param isJ2EE13
	 *            <code>true</code> if the constant for J2EE version 1.3 is desired
	 * @return IServerTargetConstants.J2EE_12 or IServerTargetConstants.J2EE_13
	 */
	public static String getJ2EEVersion(boolean isJ2EE13) {
		return isJ2EE13 ? IServerTargetConstants.J2EE_13 : IServerTargetConstants.J2EE_12;
	}

	/**
	 * A convenient method that takes in a list of IServerTargets and returns an array of labels of
	 * the IServerTargets in the given list.
	 * 
	 * @param serverTargets
	 *            The list of IServerTargets
	 * @return An array lof labels of the IServerTargets in the given list
	 */
	public static String[] getServerNames(List serverTargets) {
		String[] result = new String[serverTargets.size()];
		for (int i = 0; i < result.length; i++) {
			IRuntime runtime = (IRuntime) serverTargets.get(i);
			result[i] = runtime.getName() + " (" + runtime.getRuntimeType().getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return result;
	}

	/**
	 * @param prjName
	 *            name of the project whose ServerTarget has to be retrieved; it can be
	 *            <code>null</code>
	 * 
	 * @return the ServerTarget of the given project; <code>null</code> if the project does not
	 *         exist or its ServerTarget is not specified.
	 */
	public static IRuntime getServerTarget(String prjName) {
		if (prjName != null && !prjName.trim().equals("")) { //$NON-NLS-1$
			IProject prj = ResourcesPlugin.getWorkspace().getRoot().getProject(prjName);
			if (prj != null && prj.exists()) {
				return ServerCore.getProjectProperties(prj).getRuntimeTarget();
			}
		}
		return null;
	}

}