/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 17, 2005
 */
package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;

public class EMFModelManagerFactory {
	public static EMFModelManager createEMFModelManager(IProject project, EMFRootObjectProvider provider) {
		boolean flexible =  (ModuleCoreNature.getModuleCoreNature(project) != null);
		EMFModelManager modelManager = null;
		if (flexible)
			modelManager = new FlexibleEMFModelManager(project,provider);
		else
			modelManager = new NonFlexibleEMFModelManager(project,provider);
		return modelManager;
	}
}
