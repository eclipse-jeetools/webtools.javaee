/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.delete;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteOptions {

	protected boolean isEARDelete;
	protected boolean deleteProjects;
	protected boolean deleteModules;
	protected boolean deleteModuleDependencies;
	protected List selectedProjects;
	/** Applicable for EAR delete */
	protected List selectedReferencedProjects;

	/**
	 * Constructor for DeleteOptions.
	 */
	public DeleteOptions() {
		super();
	}


	public boolean shouldDeleteModules() {
		return deleteModules;
	}


	public void setDeleteModules(boolean deleteModulesFromAllEARs) {
		this.deleteModules = deleteModulesFromAllEARs;
	}


	public boolean shouldDeleteProjects() {
		return deleteProjects;
	}


	public void setDeleteProjects(boolean deleteProjects) {
		this.deleteProjects = deleteProjects;
	}


	public boolean shouldDeleteModuleDependencies() {
		return deleteModuleDependencies;
	}


	public void setDeleteModuleDependencies(boolean deleteReferencesFromModuleDependencies) {
		this.deleteModuleDependencies = deleteReferencesFromModuleDependencies;
	}

	public List getModuleAndUtilityProjects() {
		if (isEARDelete)
			return getSelectedReferencedProjects();
		return getSelectedProjects();
	}

	public List getAllProjectsToDelete() {
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

	public boolean isEARDelete() {
		return isEARDelete;
	}

	public void setIsEARDelete(boolean isEARDelete) {
		this.isEARDelete = isEARDelete;
	}

}