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
package org.eclipse.jst.j2ee.internal.migration;


import org.eclipse.jst.j2ee.ejb.EnterpriseBean;

/**
 * @author dfholttp
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class EJBClientViewMigrationConfig {
	protected EnterpriseBean ejb;
	protected boolean isSelected;
	protected EJBJarMigrationConfig parentConfig;

	/**
	 * Constructor for EJBMigrationConfig.
	 */
	public EJBClientViewMigrationConfig(EnterpriseBean anEjb) {
		super();
		ejb = anEjb;
		setDefaults();
	}

	public EJBClientViewMigrationConfig(EnterpriseBean anEjb, EJBJarMigrationConfig parent) {
		this(anEjb);
		parentConfig = parent;
	}

	/**
	 * Returns the ejb.
	 * 
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getEjb() {
		return ejb;
	}


	/**
	 * Returns the parentConfig.
	 * 
	 * @return EJBJarMigrationConfig
	 */
	public EJBJarMigrationConfig getParentConfig() {
		return parentConfig;
	}

	public boolean is11CMP() {
		return ejb.isContainerManagedEntity() && J2EEMigrationHelper.isEJB1_X(ejb);
	}

	public boolean isEntity() {
		return ejb.isEntity();
	}


	/**
	 * Returns the isSelected.
	 * 
	 * @return boolean
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * Method setDefaults.
	 */
	private void setDefaults() {
		isSelected = ejb.isEntity();
	}

	/**
	 * Sets the isSelected.
	 * 
	 * @param isSelected
	 *            The isSelected to set
	 */
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	/**
	 * Sets the parentConfig.
	 * 
	 * @param parentConfig
	 *            The parentConfig to set
	 */
	public void setParentConfig(EJBJarMigrationConfig parentConfig) {
		this.parentConfig = parentConfig;
	}

	public String toString() {
		return getClass().getName() + '(' + ejb.getName() + ')';
	}

	/**
	 * @return
	 */
	public boolean is20CMP() {
		return ejb.isContainerManagedEntity() && J2EEMigrationHelper.isEJB2_X(ejb);
	}

}