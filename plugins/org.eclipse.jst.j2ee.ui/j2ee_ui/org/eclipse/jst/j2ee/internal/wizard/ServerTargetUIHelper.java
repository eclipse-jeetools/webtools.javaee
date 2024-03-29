/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Aug 17, 2003
 * 
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerUtil;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class ServerTargetUIHelper {
	/**
	 *  
	 */
	public ServerTargetUIHelper() {
		super();
	}

	public static String getSelectedServerTargetString(Combo serverTargetCombo) {
		if (serverTargetCombo.getSelectionIndex() != -1)
			return serverTargetCombo.getItem(serverTargetCombo.getSelectionIndex());
		return null;
	}

	/**
	 * @return
	 */
	public static int getSelectedServerTargetStringIndex(Combo serverTargetCombo) {
		return serverTargetCombo.getSelectionIndex();
	}

//	private static void setServerTargetForProject(Shell shell, IProject project, IRuntime runtime) {
//		//ServerTargetHelper.cleanUpNonServerTargetClasspath(project);
//		setServerTarget(shell, project, runtime, null);
//	}

	/**
	 * @param earProject
	 * @param moduleProject
	 * @return
	 */
	public static boolean setModuleServerTargetIfNecessary(IProject earProject, IProject moduleProject, Shell shell) {
		return true;
	}

	//	private static String getEARJ2EELevel(IProject earProject) {
	//		EARNatureRuntime nature = EARNatureRuntime.getRuntime(earProject);
	//		String j2eeLevel = null;
	//		int natureID = nature.getJ2EEVersion();
	//		switch (natureID) {
	//			case (J2EEVersionConstants.J2EE_1_2_ID) :
	//				j2eeLevel = J2EEVersionConstants.VERSION_1_2_TEXT;
	//				break;
	//			case (J2EEVersionConstants.J2EE_1_3_ID) :
	//				j2eeLevel = J2EEVersionConstants.VERSION_1_3_TEXT;
	//				break;
	//			case (J2EEVersionConstants.J2EE_1_4_ID) :
	//				j2eeLevel = J2EEVersionConstants.VERSION_1_4_TEXT;
	//				break;
	//			default :
	//				j2eeLevel = J2EEVersionConstants.VERSION_1_4_TEXT;
	//				break;
	//		}
	//		return j2eeLevel;
	//	}

	public static void setServerTarget(Shell shell, IProject project, IRuntime runtime, IProgressMonitor monitor) {
//		try {
//			ServerCore.getProjectProperties(project).setRuntimeTarget(runtime, monitor);
//		} catch (CoreException e) {
//			Logger.getLogger().logError(e);
//		}
	}


	public static ServerTargetComboHelper getValidServerTargetComboItems(String j2eeType, String selectedVersion) {
		IRuntime[] validServerTargets = ServerUtil.getRuntimes(j2eeType, selectedVersion);
		String[] serverTargetList = null;
		if (validServerTargets.length>0) {
			int serverTargetListSize = validServerTargets.length;
			serverTargetList = new String[serverTargetListSize];
			for (int i = 0; i < validServerTargets.length; i++) {
				IRuntime runtime = validServerTargets[i];
				serverTargetList[i] = runtime.getName() + " (" + runtime.getRuntimeType().getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return new ServerTargetComboHelper(Arrays.asList(validServerTargets), serverTargetList);
	}

	/**
	 * @param project
	 */
	public static void runEarValidation(IProject project) {
		try {
			IRunnableWithProgress runnable = EARValidationHelper.createValidationRunnable(project);
			runnable.run(null);
		} catch (InterruptedException ie) {
			Logger.getLogger().logError(ie);
		} catch (InvocationTargetException ite) {
			Logger.getLogger().logError(ite);
		}
	}
}
