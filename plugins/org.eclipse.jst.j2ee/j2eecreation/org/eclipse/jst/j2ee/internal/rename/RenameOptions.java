/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.rename;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RenameOptions {
	protected boolean isEARRename;
	protected boolean renameProjects;
	protected boolean renameModules;
	protected boolean renameModuleDependencies;
	protected List selectedProjects;
	/** Applicable for EAR rename */
	protected List selectedReferencedProjects;
	protected String newName = null;
	protected String newContextRoot = null;
	protected boolean bogus;

	/**
	 * Constructor for RenameOptions.
	 */
	public RenameOptions() {
		super();
	}


	public boolean shouldRenameModules() {
		return renameModules;
	}


	public void setRenameModules(boolean renameModulesFromAllEARs) {
		this.renameModules = renameModulesFromAllEARs;
	}


	public boolean shouldRenameProjects() {
		return renameProjects;
	}


	public void setRenameProjects(boolean renameProjects) {
		this.renameProjects = renameProjects;
	}


	public boolean shouldRenameModuleDependencies() {
		return renameModuleDependencies;
	}


	public void setRenameModuleDependencies(boolean renameReferencesFromModuleDependencies) {
		this.renameModuleDependencies = renameReferencesFromModuleDependencies;
	}

	public List getModuleAndUtilityProjects() {
		if (isEARRename)
			return getSelectedReferencedProjects();
		return getSelectedProjects();
	}

	public List getAllProjectsToRename() {
		List result = new ArrayList();
		result.addAll(getSelectedProjects());
		result.addAll(getSelectedReferencedProjects());
		return result;
	}

	/**
	 * Gets the selectedProjects.
	 * 
	 * @return Returns a List
	 */
	public List getSelectedProjects() {
		if (selectedProjects == null)
			return Collections.EMPTY_LIST;
		return selectedProjects;
	}

	/**
	 * Sets the selectedProjects.
	 * 
	 * @param selectedProjects
	 *            The selectedProjects to set
	 */
	public void setSelectedProjects(List selectedProjects) {
		this.selectedProjects = selectedProjects;
	}

	/**
	 * Gets the selectedReferencedProjects.
	 * 
	 * @return Returns a List
	 */
	public List getSelectedReferencedProjects() {
		if (selectedReferencedProjects == null)
			return Collections.EMPTY_LIST;
		return selectedReferencedProjects;
	}

	/**
	 * Sets the selectedReferencedProjects.
	 * 
	 * @param selectedReferencedProjects
	 *            The selectedReferencedProjects to set
	 */
	public void setSelectedReferencedProjects(List selectedReferencedProjects) {
		this.selectedReferencedProjects = selectedReferencedProjects;
	}

	public boolean isEARRename() {
		return isEARRename;
	}

	public void setIsEARRename(boolean isEARRename) {
		this.isEARRename = isEARRename;
	}

	/**
	 * Gets the newName.
	 * 
	 * @return Returns a String
	 */
	public String getNewName() {
		return newName;
	}

	/**
	 * Sets the newName.
	 * 
	 * @param newName
	 *            The newName to set
	 */
	public void setNewName(String newName) {
		this.newName = newName;
	}

	/**
	 * Gets the newContextRoot.
	 * 
	 * @return Returns a String
	 */
	public String getNewContextRoot() {
		return newContextRoot;
	}

	/**
	 * Sets the newContextRoot.
	 * 
	 * @param newContextRoot
	 *            The newContextRoot to set
	 */
	public void setNewContextRoot(String newContextRoot) {
		this.newContextRoot = newContextRoot;
	}
}