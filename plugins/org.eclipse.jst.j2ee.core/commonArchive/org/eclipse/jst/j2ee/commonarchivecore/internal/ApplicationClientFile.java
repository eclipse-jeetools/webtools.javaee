/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal;



import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DeploymentDescriptorLoadException;


/**
 * @generated
 */
public interface ApplicationClientFile extends ModuleFile {

	/**
	 * @throws DeploymentDescriptorLoadException -
	 *             is a runtime exception, because we can't override the signature of the generated
	 *             methods
	 */



	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The DeploymentDescriptor reference
	 */
	ApplicationClient getDeploymentDescriptor() throws DeploymentDescriptorLoadException;

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param l
	 *            The new value of the DeploymentDescriptor reference
	 */
	void setDeploymentDescriptor(ApplicationClient value);

}
