/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore;



import java.util.List;

import org.eclipse.jst.j2ee.commonarchivecore.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


/**
 * @generated
 */
public interface EJBJarFile extends ModuleFile {

	/**
	 * Used for tools performing selective import
	 */
	public List getAssociatedFiles(EnterpriseBean ejb);

	/**
	 * @throws DeploymentDescriptorLoadException -
	 *             is a runtime exception, because we can't override the signature of the generated
	 *             methods
	 */



	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The DeploymentDescriptor reference
	 */
	EJBJar getDeploymentDescriptor() throws DeploymentDescriptorLoadException;

	public boolean isImportedFrom10();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param l
	 *            The new value of the DeploymentDescriptor reference
	 */
	void setDeploymentDescriptor(EJBJar value);

}
