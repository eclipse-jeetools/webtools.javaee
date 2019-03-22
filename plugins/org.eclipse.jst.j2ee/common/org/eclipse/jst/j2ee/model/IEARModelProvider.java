/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model;


import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * In progress...    Simple interface that serves to unify the various model access api's
 *
 */
public interface IEARModelProvider extends IModelProvider {

	/**
	 * This provides a way to get module URIs from the appropriate model context
	 */
	public String getModuleURI(IVirtualComponent moduleComp);

	/**
	 * This method will return the context root in this application for the passed in web project.
	 * 
	 * @param webProject
	 * @return contextRoot String
	 */
	public String getWebContextRoot(IProject webProject);

	/**
	 * This method will set the context root on the application for the passed in contextRoot.
	 * 
	 * @param webProject
	 * @param aContextRoot
	 */
	public void setWebContextRoot(IProject webProject, String aContextRoot);
}
