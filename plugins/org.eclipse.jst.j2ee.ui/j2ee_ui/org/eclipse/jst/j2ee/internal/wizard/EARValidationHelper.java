/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.validation.UIEarValidator;
import org.eclipse.wst.common.frameworks.internal.ui.RunnableWithProgressWrapper;
import org.eclipse.wst.validation.internal.operations.OneValidatorOperation;
import org.eclipse.wst.validation.internal.operations.ValidatorManager;

public class EARValidationHelper {

	/**
	 * Constructor for EARValidationHelper.
	 */
	private EARValidationHelper() {
		super();
	}

	/**
	 * Return a list of runnable validation operations for all EAR projects which have auto validate
	 * enabled, and are impacted by the list of projects; If the ear project itself is in the list,
	 * then it is skipped.
	 */
	public static IRunnableWithProgress[] getEARValidationOperations(List modifiedProjects) {
		List earProjects = EARNatureRuntime.getAllEARProjectsInWorkbench();
		List result = new ArrayList(earProjects.size());
		for (int i = 0; i < earProjects.size(); i++) {
			IProject earProj = (IProject) earProjects.get(i);
			if (willEARProjectNeedValidation(earProj, modifiedProjects)) {
				result.add(createValidationRunnable(earProj));
			}
		}
		return (IRunnableWithProgress[]) result.toArray(new IRunnableWithProgress[result.size()]);
	}

	/**
	 * Return a list of runnable validation operations for all EAR projects which have auto validate
	 * enabled, and are impacted by the j2ee project
	 */
	public static IRunnableWithProgress[] getEARValidationOperations(IProject modifiedJ2EEProject) {
		return getEARValidationOperations(Collections.singletonList(modifiedJ2EEProject));
	}

	public static boolean isEARValidationAutoEnabled(IProject earProj) {
		return ValidatorManager.getManager().isAutoValidate(earProj) && ValidatorManager.getManager().isEnabled(earProj, UIEarValidator.VALIDATOR_ID);
	}

	private static boolean willEARProjectNeedValidation(IProject earProj, List modifiedProjects) {
		if (modifiedProjects.contains(earProj) || !isEARValidationAutoEnabled(earProj))
			return false;
		Object accessorKey = new Object();
		EARNatureRuntime runtime = EARNatureRuntime.getRuntime(earProj);
		EAREditModel editModel = runtime.getEarEditModelForRead(accessorKey);
		try {
			for (int i = 0; i < modifiedProjects.size(); i++) {
				if (editModel.hasMappingToProject((IProject) modifiedProjects.get(i)))
					return true;
			}
		} finally {
			if (editModel != null)
				editModel.releaseAccess(accessorKey);
		}
		return false;
	}

	/**
	 * Creates a new IRunnableWithProgress which runs a one validator operation on the EAR project
	 */
	public static IRunnableWithProgress createValidationRunnable(IProject earProj) {
		OneValidatorOperation op = new OneValidatorOperation(earProj, UIEarValidator.VALIDATOR_ID, true, false);

		return new RunnableWithProgressWrapper(op);
	}

}