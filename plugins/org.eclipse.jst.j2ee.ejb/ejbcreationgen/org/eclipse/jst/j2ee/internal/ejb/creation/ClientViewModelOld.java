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
package org.eclipse.jst.j2ee.internal.ejb.creation;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


/**
 * Defines the EditModel for the client view.
 */
public class ClientViewModelOld {

	private EnterpriseBean ejbBean;

	private List homeMethodCollection = new ArrayList();
	private List methodCollection = new ArrayList();

	private final static String LOCAL = "Local"; //$NON-NLS-1$
	private final static String HOME = "Home"; //$NON-NLS-1$


	private boolean shouldDelete = false;
	private boolean shouldDeleteRemote = false;
	private boolean shouldDeleteLocal = false;
	private boolean isRemoteSelected = false;
	private boolean isLocalSelected = false;
	private boolean shouldCreateRemoteCommand = true;
	private boolean shouldCreateLocalCommand = true;

	private JavaClass homeInterfaceExisting = null;
	private JavaClass remoteInterfaceExisting = null;
	private JavaClass localInterfaceExisting = null;
	private JavaClass localHomeInterfaceExisting = null;

	private String homeExistingName = null;
	private String remoteExistingName = null;
	private String localHomeExistingName = null;
	private String localExistingName = null;
	private String packageName = null;

	private String localSuffix;

	/**
	 * Constructor for ClientViewModel.
	 */
	public ClientViewModelOld() {
		super();
		methodCollection = new ArrayList();
	}// ClientViewModel

	/**
	 * Constructor for ClientViewModel.
	 */
	public ClientViewModelOld(EnterpriseBean ejbBean) {
		super();
		this.setEjbBean(ejbBean);
	}// ClientViewModel

	/**
	 * Sets if remote interfaces have been selected.
	 * 
	 * @param boolean
	 *            selection - The current selection.
	 */
	public void setRemoteSelected(boolean selection) {
		isRemoteSelected = selection;
	}// setRemoteSelected

	/**
	 * Sets if local interfaces have been selected.
	 * 
	 * @param boolean
	 *            selection - The current selection.
	 */
	public void setLocalSelected(boolean selection) {
		isLocalSelected = selection;
	}// setLocalSelected

	/**
	 * Says if remote interfaces have been selected.
	 * 
	 * @return boolean
	 */
	public boolean isRemoteSelected() {
		return isRemoteSelected;
	}// isRemoteSelected

	/**
	 * Says if local interfaces have been selected.
	 * 
	 * @return boolean
	 */
	public boolean isLocalSelected() {
		return isLocalSelected;
	}// isLocalSelected

	/**
	 * Has remote interfaces.
	 * 
	 * @return boolean
	 */
	public boolean hasRemoteInterfaces() {
		return (this.getEjbBean().getRemoteInterface() != null || this.getEjbBean().getHomeInterface() != null);
	}// hasRemoteInterfaces

	/**
	 * Has local interfaces.
	 * 
	 * @return boolean
	 */
	public boolean hasLocalInterfaces() {
		return (this.getEjbBean().getLocalInterface() != null || this.getEjbBean().getLocalHomeInterface() != null);
	}// hasRemoteInterfaces

	/**
	 * Gets the ejbBean.
	 * 
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getEjbBean() {
		return ejbBean;
	}// getEjbBean

	/**
	 * Sets the ejbBean .
	 * 
	 * @param EnterpriseBean -
	 *            The ejbBean to set
	 */
	public void setEjbBean(EnterpriseBean ejbBean) {
		this.ejbBean = ejbBean;
	}// setEjbBean

	/**
	 * Gets the homeExistingName.
	 * 
	 * @return String
	 */
	public String getHomeExistingName() {
		if (this.homeExistingName == null) {
			StringBuffer b = new StringBuffer();
			b.append(getPackageName()).append(".").append(getEjbBean().getName()).append(HOME); //$NON-NLS-1$
			this.homeExistingName = b.toString();
		}// if
		return homeExistingName;
	}// getHomeExistingName

	/**
	 * Sets the homeExistingName.
	 * 
	 * @param String
	 *            homeExistingName - The homeExistingName to set
	 */
	public void setHomeExistingName(String homeExistingName) {
		this.homeExistingName = homeExistingName;
	}// setHomeExistingName

	/**
	 * Gets the remoteExistingName.
	 * 
	 * @return String
	 */
	public String getRemoteExistingName() {
		if (this.remoteExistingName == null) {
			StringBuffer b = new StringBuffer();
			b.append(getPackageName()).append(".").append(getEjbBean().getName()); //$NON-NLS-1$
			this.remoteExistingName = b.toString(); //$NON-NLS-1$
		}// if
		return remoteExistingName;
	}// getRemoteExistingName

	/**
	 * Sets the remoteExistingName.
	 * 
	 * @param String
	 *            remoteExistingName - The remoteExistingName to set
	 */
	public void setRemoteExistingName(String remoteExistingName) {
		this.remoteExistingName = remoteExistingName;
	}// setRemoteExistingName

	/**
	 * Gets the localHomeExistingName.
	 * 
	 * @return Returns a String
	 */
	public String getLocalHomeExistingName() {
		if (this.localHomeExistingName == null) {
			StringBuffer b = new StringBuffer();
			b.append(getPackageName()).append(".").append(getEjbBean().getName()).append(getLocalSuffix()).append(HOME); //$NON-NLS-1$
			this.localHomeExistingName = b.toString();
		}// if
		return localHomeExistingName;
	}// getLocalHomeExistingName

	/**
	 * Sets the localHomeExistingName.
	 * 
	 * @param String
	 *            localHomeExistingName - The localHomeExistingName to set
	 */
	public void setLocalHomeExistingName(String localHomeExistingName) {
		this.localHomeExistingName = localHomeExistingName;
	}// setLocalHomeExistingName

	/**
	 * Gets the localExistingName.
	 * 
	 * @return String
	 */
	public String getLocalExistingName() {
		if (this.localExistingName == null) {
			StringBuffer b = new StringBuffer();
			b.append(getPackageName()).append(".").append(getEjbBean().getName()).append(getLocalSuffix()); //$NON-NLS-1$
			this.localExistingName = b.toString();
		}// if
		return localExistingName;
	}// getLocalExistingName

	/**
	 * Sets the localExistingName.
	 * 
	 * @param String
	 *            localExistingName - The localExistingName to set
	 */
	public void setLocalExistingName(String localExistingName) {
		this.localExistingName = localExistingName;
	}// setLocalExistingName

	/**
	 * Gets the methodCollection.
	 * 
	 * @return List
	 */
	public List getMethodCollection() {
		return methodCollection;
	}// getMethodCollection

	/**
	 * Sets the methodCollection.
	 * 
	 * @param List
	 *            methodCollection - The methodCollection to set
	 */
	public void setMethodCollection(List methodCollection) {
		this.methodCollection = methodCollection;
	}// setMethodCollection

	/**
	 * Sets the package name.
	 * 
	 * @param String
	 *            packageName - The name of package.
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}// setPackageName

	/**
	 * Gets the package name.
	 * 
	 * @return String
	 */
	public String getPackageName() {
		if (this.packageName == null) {
			return Signature.getQualifier(this.getEjbBean().getEjbClassName());
		}
		return this.packageName;
	}// getPackageName

	/**
	 * Gets the homeMethodCollection.
	 * 
	 * @return List
	 */
	public List getHomeMethodCollection() {
		return homeMethodCollection;
	}// getHomeMethodColleaction

	/**
	 * Sets the homeMethodCollection.
	 * 
	 * @param List
	 *            homeMethodCollection - The homeMethodCollection to set
	 */
	public void setHomeMethodCollection(List homeMethodCollection) {
		this.homeMethodCollection = homeMethodCollection;
	}// setHomeMethodColleaction

	/**
	 * Gets the shouldDelete.
	 * 
	 * @return boolean
	 */
	public boolean shouldDelete() {
		return shouldDelete;
	}// shouldDelete

	/**
	 * Sets the shouldDelete.
	 * 
	 * @param boolean
	 *            shouldDelete - The shouldDelete to set
	 */
	public void setShouldDelete(boolean shouldDelete) {
		this.shouldDelete = shouldDelete;
	}// setShouldDelete

	/**
	 * Gets the shouldDeleteRemote.
	 * 
	 * @return boolean
	 */
	public boolean shouldDeleteRemote() {
		return shouldDeleteRemote;
	}// shouldDeleteRemote

	/**
	 * Sets the shouldDeleteRemote.
	 * 
	 * @param boolean
	 *            shouldDeleteRemote - The shouldDeleteRemote to set
	 */
	public void setShouldDeleteRemote(boolean shouldDeleteRemote) {
		this.shouldDeleteRemote = shouldDeleteRemote;
	}// setShouldDeleteRemote

	/**
	 * Gets the shouldDeleteLocal.
	 * 
	 * @return boolean
	 */
	public boolean shouldDeleteLocal() {
		return shouldDeleteLocal;
	}// shouldDeleteLocal

	/**
	 * Sets the shouldDeleteLocal.
	 * 
	 * @param boolean
	 *            shouldDeleteLocal - The shouldDeleteLocal to set
	 */
	public void setShouldDeleteLocal(boolean shouldDeleteLocal) {
		this.shouldDeleteLocal = shouldDeleteLocal;
	}// setShouldDeleteLocal

	/**
	 * Gets the homeInterfaceExisting.
	 * 
	 * @return JavaClass
	 */
	public JavaClass getHomeInterfaceExisting() {
		return homeInterfaceExisting;
	}// getHomeInterfaceExisting

	/**
	 * Sets the homeInterfaceExisting.
	 * 
	 * @param JavaClass
	 *            homeInterfaceExisting - The homeInterfaceExisting to set
	 */
	public void setHomeInterfaceExisting(JavaClass homeInterfaceExisting) {
		this.homeInterfaceExisting = homeInterfaceExisting;
	}// setHomeInterfaceExisting

	/**
	 * Gets the remoteInterfaceExisiting.
	 * 
	 * @return JavaClass
	 */
	public JavaClass getRemoteInterfaceExisting() {
		return remoteInterfaceExisting;
	}// getRemoteInterfaceExisting

	/**
	 * Sets the remoteInterfaceExisiting.
	 * 
	 * @param JavaClass
	 *            remoteInterfaceExisiting - The remoteInterfaceExisiting to set
	 */
	public void setRemoteInterfaceExisting(JavaClass remoteInterfaceExisting) {
		this.remoteInterfaceExisting = remoteInterfaceExisting;
	}// setRemoteInterfaceExisting

	/**
	 * Gets the localInterfaceExisting.
	 * 
	 * @return JavaClass
	 */
	public JavaClass getLocalInterfaceExisting() {
		return localInterfaceExisting;
	}// getLocalInterfaceExisting

	/**
	 * Sets the localInterfaceExisting.
	 * 
	 * @param JavaClass
	 *            localInterfaceExisting - The localInterfaceExisting to set
	 */
	public void setLocalInterfaceExisting(JavaClass localInterfaceExisting) {
		this.localInterfaceExisting = localInterfaceExisting;
	}// setLocalInterfaceExisting

	/**
	 * Gets the localHomeInterfaceExisting.
	 * 
	 * @return JavaClass
	 */
	public JavaClass getLocalHomeInterfaceExisting() {
		return localHomeInterfaceExisting;
	}// getLocalHomeInterfaceExisting

	/**
	 * Sets the localHomeInterfaceExisting.
	 * 
	 * @param JavaClass
	 *            localHomeInterfaceExisting - The localHomeInterfaceExisting to set
	 */
	public void setLocalHomeInterfaceExisting(JavaClass localHomeInterfaceExisting) {
		this.localHomeInterfaceExisting = localHomeInterfaceExisting;
	}// setLocalHomeInterfaceExisting

	/**
	 * Returns the shouldCreateLocalCommand.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateLocalCommand() {
		return shouldCreateLocalCommand;
	}

	/**
	 * Sets the shouldCreateLocalCommand.
	 * 
	 * @param shouldCreateLocalCommand
	 *            The shouldCreateLocalCommand to set
	 */
	public void setShouldCreateLocalCommand(boolean shouldCreateLocalCommand) {
		this.shouldCreateLocalCommand = shouldCreateLocalCommand;
	}

	/**
	 * Returns the shouldCreateRemoteCommand.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateRemoteCommand() {
		return shouldCreateRemoteCommand;
	}

	/**
	 * Sets the shouldCreateRemoteCommand.
	 * 
	 * @param shouldCreateRemoteCommand
	 *            The shouldCreateRemoteCommand to set
	 */
	public void setShouldCreateRemoteCommand(boolean shouldCreateRemoteCommand) {
		this.shouldCreateRemoteCommand = shouldCreateRemoteCommand;
	}

	/**
	 * Returns the localSuffix.
	 * 
	 * @return String
	 */
	public String getLocalSuffix() {
		if (localSuffix == null)
			localSuffix = LOCAL;
		return localSuffix;
	}

	/**
	 * Sets the localSuffix.
	 * 
	 * @param localSuffix
	 *            The localSuffix to set
	 */
	public void setLocalSuffix(String localSuffix) {
		this.localSuffix = localSuffix;
	}

	public void resetDerivedAttributes() {
		homeExistingName = null;
		remoteExistingName = null;
		localHomeExistingName = null;
		localExistingName = null;
		packageName = null;
	}

}// ClientViewModel
