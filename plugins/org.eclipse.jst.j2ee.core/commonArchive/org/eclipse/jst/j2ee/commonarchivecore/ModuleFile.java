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



import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.commonarchivecore.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.exception.ResourceLoadException;

/**
 * @generated
 */
public interface ModuleFile extends Archive {

	public Resource getDeploymentDescriptorResource() throws java.io.FileNotFoundException, ResourceLoadException;

	public String getDeploymentDescriptorUri();

	/**
	 * Return the container for this archive casted to EARFile; null if this file is not contained
	 * in an EARFile
	 */
	public EARFile getEARFile();

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return com.ibm.etools.archive.ExportStrategy
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.helpers.ExportStrategy getExportStrategy();

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return com.ibm.etools.archive.ImportStrategy
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.strategy.ImportStrategy getImportStrategy();

	/**
	 * Returns the specification version of the module file. For example, "2.0"
	 * 
	 * @deprecated, Use getDeploymentDescriptorResource().getModuleVersionID();
	 */
	public String getSpecVersion();

	/**
	 * Return the version ID of the module For example, "20"
	 * 
	 * @return int
	 */
	public int getSpecVersionID();

	EObject getStandardDeploymentDescriptor() throws DeploymentDescriptorLoadException;

	/**
	 * Answers whether the deployment descriptor is null; used for copy, to determine whether the
	 * import strategy needs to be copied or not
	 */
	public boolean isDeploymentDescriptorSet();

	public Resource makeDeploymentDescriptorResource();

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newExportStrategy
	 *            com.ibm.etools.archive.ExportStrategy
	 */
	public void setExportStrategy(org.eclipse.jst.j2ee.commonarchivecore.helpers.ExportStrategy newExportStrategy);

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newImportStrategy
	 *            com.ibm.etools.archive.ImportStrategy
	 */
	public void setImportStrategy(org.eclipse.jst.j2ee.commonarchivecore.strategy.ImportStrategy newImportStrategy);

	/**
	 * Sets the J2EE version for this archive
	 * 
	 * @see org.eclipse.jst.j2ee.J2EEVersionConstants
	 */
	public void setJ2EEVersion(int versionID);

}
