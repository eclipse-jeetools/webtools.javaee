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
package org.eclipse.jst.j2ee.commonarchivecore.internal;



import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.jca.Connector;


/**
 * @generated
 */
public interface RARFile extends ModuleFile {

	/**
	 * Returns the value of the '<em><b>Deployment Descriptor</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployment Descriptor</em>' reference list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Deployment Descriptor</em>' reference.
	 * @see #setDeploymentDescriptor(Connector)
	 * @see org.eclipse.jst.j2ee.internal.commonarchivecore.CommonarchivePackage#getRARFile_DeploymentDescriptor()
	 * @model required="true"
	 * @generated
	 */
	Connector getDeploymentDescriptor();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.jst.j2ee.internal.commonarchivecore.RARFile#getDeploymentDescriptor <em>Deployment Descriptor</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Deployment Descriptor</em>' reference.
	 * @see #getDeploymentDescriptor()
	 * @generated
	 */
	void setDeploymentDescriptor(Connector value);

	/**
	 * Extracts the RAR file to the specified directory. This method should be used for expanding
	 * the RAR file if it is a standalone RAR. If the RAR file is packaged as part of an EAR, this
	 * method should not be used. Instead, the expandTo() of the EAR should be used expand the
	 * contents of the ear and the nested RAR relative to the EAR's directory (similar to WAR's).
	 * Creation date: (2/6/2001 7:44:41 PM)
	 * 
	 * @param dir
	 *            java.lang.String
	 * @param expandFlags
	 *            int
	 */
	void extractToConnectorDirectory(String dir, int expandFlags) throws SaveFailureException;
}
