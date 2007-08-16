/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.provider;


import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

public class J2EEProviderUtility {

	private J2EEProviderUtility() {
		super();
	}

	public static String prependProjectName(EObject object, String label) {
		String projectName = null;
		if (object.eResource() != null) {
			IProject proj = WorkbenchResourceHelper.getProject(object.eResource());
			if (proj != null)
				projectName = proj.getName();
		}

		if (projectName == null || projectName.equals(label))
			return label;
		else if (label == null || label.length() == 0)
			return projectName;
		else
			return projectName + ": " + label; //$NON-NLS-1$
	}

}