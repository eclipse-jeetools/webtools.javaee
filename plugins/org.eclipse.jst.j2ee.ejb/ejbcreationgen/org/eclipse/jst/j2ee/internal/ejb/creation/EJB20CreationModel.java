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


import org.eclipse.emf.ecore.EEnumLiteral;

/**
 * @deprecated
 * @version 1.0
 * @author
 */
public class EJB20CreationModel extends EJBCreationModel {
	public String localName;
	public String localNamePackage;
	public String localHomeName;
	public String localHomeNamePackage;
	protected boolean mustCreateLocalInterface = true;
	protected boolean mustCreateLocalHomeInterface = true;
	public String[] localInterfacesToExtend;

	//MessageDriven attributes
	public String messageSelector;
	public EEnumLiteral acknowledgeMode;
	public EEnumLiteral destinationType;
	public EEnumLiteral durability;
	public String listenerPortName;

	/**
	 * Constructor for EJB20CreationModel.
	 */
	public EJB20CreationModel() {
		super();
		setIsEJB20(true);
	}

	/**
	 * Return the simple name of the Local interface.
	 * 
	 * @return String
	 */
	public String getLocalName() {
		return localName;
	}

	/**
	 * Return the simple name of the Local interface.
	 * 
	 * @return String
	 */
	public String getLocalNamePackage() {
		return localNamePackage;
	}

	/**
	 * Return the simple name of the Local interface.
	 * 
	 * @return String
	 */
	public String getLocalHomeName() {
		return localHomeName;
	}

	/**
	 * Return the simple name of the Local interface.
	 * 
	 * @return String
	 */
	public String getLocalHomeNamePackage() {
		return localHomeNamePackage;
	}

	/**
	 * Return true if the EJB to be created should be local that means we will create local
	 * interface references interface references This is an EJB 2.0 property
	 * 
	 * @since version 5.0
	 * @return boolean
	 */
	public boolean isLocalClient() {
		return getLocalHomeName() != null && getLocalName() != null;
	}

	public void setMustCreateLocalInterface(boolean aFlag) {
		mustCreateLocalInterface = aFlag;
	}

	public void setLocalInterfaceClassName(String className) {
		localName = className;
	}

	public void setLocalHomeInterfaceClassName(String className) {
		localHomeName = className;
	}

	public void setLocalHomeInterfacePackageName(String packageName) {
		localHomeNamePackage = packageName;
	}

	/**
	 * Return true if the current Local interface type being used does not exist.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateLocalInterface() {
		return mustCreateLocalInterface;
	}

	public void setMustCreateLocalHomeInterface(boolean aFlag) {
		mustCreateLocalHomeInterface = aFlag;
	}

	public void setLocalInterfacePackageName(String aPackageName) {
		localNamePackage = aPackageName;
	}

	public void setLocalInterfacesToExtend(String[] interfaces) {
		localInterfacesToExtend = interfaces;
	}

	/**
	 * Return an array of Strings that contain the fully qualified names of each of the interfaces
	 * that should be added to the the extends list of the newly created Local interface class. This
	 * may return null if none are needed.
	 * 
	 * @return String[]
	 */
	public String[] getLocalExtendsInterfaceNames() {
		return localInterfacesToExtend;
	}

	/**
	 * Return true if the current Home interface type being used does not exist.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateLocalHomeInterface() {
		return mustCreateLocalHomeInterface;
	}

	/**
	 * Gets the acknowledgeMode.
	 * 
	 * @return Returns a boolean
	 */
	public EEnumLiteral getAcknowledgeMode() {
		return acknowledgeMode;
	}

	/**
	 * Sets the acknowledgeMode.
	 * 
	 * @param acknowledgeMode
	 *            The acknowledgeMode to set
	 */
	public void setAcknowledgeMode(EEnumLiteral acknowledgeMode) {
		this.acknowledgeMode = acknowledgeMode;
	}

	/**
	 * Gets the destinationType.
	 * 
	 * @return Returns a boolean
	 */
	public EEnumLiteral getDestinationType() {
		return destinationType;
	}

	/**
	 * Sets the destinationType.
	 * 
	 * @param destinationType
	 *            The destinationType to set
	 */
	public void setDestinationType(EEnumLiteral destinationType) {
		this.destinationType = destinationType;
	}

	/**
	 * Gets the durability.
	 * 
	 * @return Returns a boolean
	 */
	public EEnumLiteral getDurability() {
		return durability;
	}

	/**
	 * Sets the durability.
	 * 
	 * @param durability
	 *            The durability to set
	 */
	public void setDurability(EEnumLiteral durability) {
		this.durability = durability;
	}

	/**
	 * Gets the messageSelector.
	 * 
	 * @return Returns a String
	 */
	public String getMessageSelector() {
		return messageSelector;
	}

	/**
	 * Sets the messageSelector.
	 * 
	 * @param messageSelector
	 *            The messageSelector to set
	 */
	public void setMessageSelector(String messageSelector) {
		this.messageSelector = messageSelector;
	}

	/**
	 * Gets the listenerPortName.
	 * 
	 * @return Returns a String
	 */
	public String getMDBListenerPortName() {
		return listenerPortName;
	}

	/**
	 * Sets the listenerPortName.
	 * 
	 * @param listenerPortName
	 *            The listenerPortName to set
	 */
	public void setMDBListenerPortName(String aListenerPortName) {
		listenerPortName = aListenerPortName;
	}

}