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

import java.util.List;

/**
 * @deprecated
 * @author jsholl
 */
public class EJBCreationModel extends Object {

	public static final int SESSION = 0;
	public static final int BMP = 1;
	public static final int CMP = 2;
	public static final int MDB = 3; //EJB 2.0

	public int beanType;
	public String[] classImportStatements;
	public String beanClassName;
	public String beanName;
	public String beanClassNamePackage;
	public String remoteName;
	public String remoteNamePackage;
	public String homeName;
	public String homeNamePackage;
	public String keyClassName;
	public String keyClassPackage;
	public String superTypeName;
	public String sourceFolderName;
	public String jndiName;
	public boolean useDefaultJNDIName = true;
	public boolean isContainerManaged;
	public boolean isStatefull;
	public List cmpFields;
	public String defaultPackage;
	public boolean usePrimKeyClass;
	public String superClassName;
	public List interfacesToImplement;
	public String[] remoteInterfacesToExtend;
	protected boolean mustCreateEJBClass = true;
	protected boolean mustCreateGeneralization = true;
	protected boolean mustCreatePrimaryKey = true;
	protected boolean mustCreateRemoteInterface;
	protected boolean mustCreateHomeInterface = true;
	protected boolean isEJB20 = false;

	/**
	 * Constructor for EJBCreationModel.
	 */
	public EJBCreationModel() {
		super();
	}

	/**
	 * Return the varialbe corresponding to the EJB that is to be created.
	 * 
	 * @return int
	 * 
	 * @see SESSION
	 * @see BMP
	 * @see CMP
	 * @see MDB
	 */
	public int getBeanType() {
		return beanType;
	}

	/**
	 * Return the name of the new EJB.
	 * 
	 * @return String
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * Return the name of the folder that will act as the folder for the generated source code.
	 * 
	 * @return String
	 */
	public String getSourceFolderName() {
		return sourceFolderName;
	}

	/**
	 * Return true if the EJB to be created has a supertype bean.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateGeneralization() {
		return mustCreateGeneralization;
	}

	public void setMustCreateGeneralization(boolean aBool) {
		mustCreateGeneralization = aBool;
	}

	/**
	 * Return the name of the EJB which is the supertype of the EJB that is being created.
	 * 
	 * @return String
	 */
	public String getBeanSupertypeName() {
		return superTypeName;
	}

	/**
	 * Return true if the EJB to be created should be remote that means we will create remote
	 * interface references This is an EJB 2.0 property
	 * 
	 * @since version 5.0
	 * @return boolean
	 */
	public boolean isRemoteClient() {
		return getRemoteName() != null && getHomeName() != null;
	}

	public void setMustCreateHomeInterface(boolean aFlag) {
		mustCreateHomeInterface = aFlag;
	}


	/**
	 * Return true if the current Home interface type being used does not exist.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateHomeInterface() {
		return mustCreateHomeInterface;
	}

	/**
	 * Return the simple name of the Home interface.
	 * 
	 * @return String
	 */
	public String getHomeName() {
		return homeName;
	}

	/**
	 * Return the name of the Home interface package.
	 * 
	 * @return String
	 */
	public String getHomePackage() {
		return homeNamePackage;
	}

	public void setMustCreateRemoteInterface(boolean aFlag) {
		mustCreateRemoteInterface = aFlag;
	}



	/**
	 * Return true if the current Remote interface type being used does not exist.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateRemoteInterface() {
		return mustCreateRemoteInterface;
	}



	/**
	 * Return the simple name of the Remote interface.
	 * 
	 * @return String
	 */
	public String getRemoteName() {
		return remoteName;
	}



	/**
	 * Return the name of the Remote interface package.
	 * 
	 * @return String
	 */
	public String getRemotePackage() {
		return remoteNamePackage;
	}

	/**
	 * Return the jndi lookup name for the bean.
	 * 
	 * @return String
	 */
	public String getJndiName() {
		return jndiName;
	}

	/**
	 * Return an array of Strings that contain the fully qualified names of each of the interfaces
	 * that should be added to the the extends list of the newly created Remote interface class.
	 * This may return null if none are needed.
	 * 
	 * @return String[]
	 */
	public String[] getRemoteExtendsInterfaceNames() {
		return remoteInterfacesToExtend;
	}

	public void setMustCreateEJBClass(boolean flag) {
		mustCreateEJBClass = flag;
	}

	/**
	 * Return true if the current EJB class type being used does not exist.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateEJBClass() {
		return mustCreateEJBClass;
	}

	public void setEJBClassName(String className) {
		beanClassName = className;
	}

	/**
	 * Return the simple name of the EJB class.
	 * 
	 * @return String
	 */
	public String getEJBClassName() {
		return beanClassName;
	}

	public void setEJBClassPackage(String packageName) {
		beanClassNamePackage = packageName;
	}

	/**
	 * Return the name of the EJB Class package.
	 * 
	 * @return String
	 */
	public String getEJBClassPackage() {
		return beanClassNamePackage;
	}

	/**
	 * Return the fully qualified name of the superclass for the EJB class.
	 * 
	 * @return String
	 */
	public String getEJBClassSuperclassName() {
		return superClassName;
	}

	/**
	 * Return an array of Strings that contains the import statements that should be added to the
	 * newly created EJB class. This may return null if no import statements are to be added.
	 * 
	 * @deprecated The class import statements are not used anymore.
	 */
	public String[] getEJBClassImportStatements() {
		return classImportStatements;
	}

	/**
	 * Return the simple name of the PrimaryKey class.
	 * 
	 * @return String
	 */
	public String getPrimaryKeyClassName() {
		return keyClassName;
	}

	/**
	 * Return the name of the PrimaryKey Class package.
	 * 
	 * @return String
	 */
	public String getPrimaryKeyClassPackage() {
		return keyClassPackage;
	}

	/**
	 * Return a List of the CMPFields that are defined for the CMP. This may return null if no
	 * fields are being defined.
	 * 
	 * @return List
	 */
	public List getCMPFields() {
		return cmpFields;
	}

	/**
	 * Return true if the bean is a EJB 2.0 bean and false if the bean is a EJB 1.1 bean return
	 * boolean
	 */
	public boolean isEJB20() {
		return isEJB20;
	}

	/**
	 * Return true if the new CMP should use a prim-key-field.
	 * 
	 * @return boolean
	 */
	public boolean shouldUsePrimKeyField() {
		return usePrimKeyClass;
	}

	public void setBeanType(int aType) {
		beanType = aType;
	}

	public void setBeanName(String aName) {
		beanName = aName;
	}

	public void setDefaultPackageName(String packageName) {

		defaultPackage = packageName;
	}

	public void setJndiName(String aName) {
		jndiName = aName;
	}

	public void setSourceFolderName(String folderName) {
		sourceFolderName = folderName;
	}

	public void setSuperTypeName(String superType) {
		superTypeName = superType;
	}

	public void setRemoteIterfaceClassName(String aName) {
		remoteName = aName;
	}

	public void setRemoteInterfacePackageName(String aPackageName) {
		remoteNamePackage = aPackageName;
	}



	public void setRemoteInterfaceClassName(String className) {
		remoteName = className;
	}


	public void setHomeInterfaceClassName(String className) {
		homeName = className;
	}


	public void setHomeInterfacePackageName(String packageName) {
		homeNamePackage = packageName;
	}


	public void setKeyClassName(String className) {
		keyClassName = className;
	}

	public void setKeyClassPackageName(String packageName) {
		keyClassPackage = packageName;
	}

	public void setIsStatefull(boolean aBool) {
		isStatefull = aBool;
	}

	public boolean getIsStatefull() {
		return isStatefull;
	}

	public void setIsContainerManaged(boolean flag) {
		isContainerManaged = flag;
	}

	public boolean getIsContainerManaged() {
		return isContainerManaged;
	}

	public void setCMPFields(List aList) {
		cmpFields = aList;
	}

	public void setUsePrimKeyClass(boolean flag) {
		usePrimKeyClass = flag;
	}

	public void setSuperClassName(String className) {
		superClassName = className;
	}

	/**
	 * @deprecated The class import statements are not used anymore.
	 */

	public void setClassImportStatements(String[] importStatements) {
		classImportStatements = importStatements;
	}

	public void setRemoteInterfacesToExtend(String[] interfaces) {
		remoteInterfacesToExtend = interfaces;
	}

	/**
	 * Return true if the current Primary Key class type being used does not exist. This method does
	 * not need to worry about whether the bean if CMP or BMP because the consumer of this type will
	 * have already taken care of this test. This method should only be called when it determines
	 * that a Primary Key class needs to exist and it just needs to determine whether to create the
	 * class or just add an existing reference.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreatePrimaryKeyClass() {
		return mustCreatePrimaryKey;
	}

	public void setMustCreatePrimaryKeyClass(boolean aFlag) {
		mustCreatePrimaryKey = aFlag;
	}



	/**
	 * Sets the isEJB20.
	 * 
	 * @param isEJB20
	 *            The isEJB20 to set
	 */
	public void setIsEJB20(boolean isEJB20) {
		this.isEJB20 = isEJB20;
	}

	/**
	 * Returns the useDefaultJNDIName.
	 * 
	 * @return boolean
	 */
	public boolean useDefaultJNDIName() {
		return useDefaultJNDIName;
	}

	/**
	 * Sets the useDefaultJNDIName.
	 * 
	 * @param useDefaultJNDIName
	 *            The useDefaultJNDIName to set
	 */
	public void setUseDefaultJNDIName(boolean useDefaultJNDIName) {
		this.useDefaultJNDIName = useDefaultJNDIName;
	}

}