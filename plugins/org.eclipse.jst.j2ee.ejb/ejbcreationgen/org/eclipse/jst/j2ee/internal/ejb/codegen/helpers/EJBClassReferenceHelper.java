package org.eclipse.jst.j2ee.internal.ejb.codegen.helpers;

/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;

/**
 * Insert the type's description here. Creation date: (08/11/00 11:17:08 AM)
 * 
 * @author: Administrator
 */
public class EJBClassReferenceHelper extends EJBGenerationHelper {
	private List fImportNames = null;
	private List fSuperInterfaceNames = null;
	private String fSuperclassName = null;
	private static final int REMOTE_INTERFACE = 0;
	private static final int HOME_INTERFACE = 1;
	private static final int BEAN_CLASS = 2;
	private static final int KEY_CLASS = 3;
	private static final int CONCRETE_BEAN_CLASS = 4;
	private static final int LOCAL_HOME_INTERFACE = 5;
	private static final int LOCAL_INTERFACE = 6;
	private static final int SERVICE_ENDPOINT_INTERFACE = 7;
	private int fRefType = REMOTE_INTERFACE;
	private static final String PACKAGE_IMPORT_SUFFIX = ".*"; //$NON-NLS-1$

	//added for client view
	private List methodCollection;

	/**
	 * JavaClassInfo constructor comment.
	 */
	public EJBClassReferenceHelper(EObject aMetaObject) {
		super(aMetaObject);
	}

	/**
	 * Adds a package import.
	 */
	public void addPackageImportName(String packageImportName) {
		String pkgName = packageImportName;
		if (!(pkgName.charAt(pkgName.length() - 1) == '*'))
			pkgName = pkgName + PACKAGE_IMPORT_SUFFIX;
		getImportNames().add(pkgName);
	}

	/**
	 * Adds a super interface.
	 */
	public void addSuperInterfaceName(String superInterfaceName) {
		getSuperInterfaceNames().add(superInterfaceName);
	}

	/**
	 * Adds a type import.
	 */
	public void addTypeImportName(String typeImportName) {
		getImportNames().add(typeImportName);
	}

	/**
	 * Returns the import names for the type.
	 */
	public List getImportNames() {
		if (fImportNames == null)
			fImportNames = new ArrayList();
		return fImportNames;
	}

	public JavaClass getJavaClass() {
		return (JavaClass) getMetaObject();
	}

	/**
	 * Insert the method's description here. Creation date: (10/5/00 6:06:08 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSuperclassName() {
		return fSuperclassName;
	}

	/**
	 * Returns the super interface names for the type.
	 */
	public List getSuperInterfaceNames() {
		if (fSuperInterfaceNames == null)
			fSuperInterfaceNames = new ArrayList();
		return fSuperInterfaceNames;
	}

	public boolean isBeanHelper() {
		return fRefType == BEAN_CLASS;
	}

	public boolean isClassReferenceHelper() {
		return true;
	}

	public boolean isConcreteBeanHelper() {
		return fRefType == CONCRETE_BEAN_CLASS;
	}

	public boolean isHomeHelper() {
		return fRefType == HOME_INTERFACE;
	}

	public boolean isKeyHelper() {
		return fRefType == KEY_CLASS;
	}

	public boolean isRemoteHelper() {
		return fRefType == REMOTE_INTERFACE;
	}

	public boolean isLocalHelper() {
		return fRefType == LOCAL_INTERFACE;
	}

	public boolean isLocalHomeHelper() {
		return fRefType == LOCAL_HOME_INTERFACE;
	}

	public boolean isServiceEndpointHelper() {
		return fRefType == SERVICE_ENDPOINT_INTERFACE;
	}

	/**
	 * Checks if any of the interfaces are local.
	 * 
	 * @return boolean
	 */
	public boolean hasLocalInterface() {
		return (this.isLocalHelper() || this.isLocalHomeHelper());
	}// hasLocalInterface

	/**
	 * Checks if any of the interfaces are remote.
	 * 
	 * @return boolean
	 */
	public boolean hasRemoteInterface() {
		return (this.isRemoteHelper() || this.isHomeHelper());
	}// hasRemoteInterface


	public void setBeanHelper() {
		fRefType = BEAN_CLASS;
	}

	public void setConcreteBeanHelper() {
		fRefType = CONCRETE_BEAN_CLASS;
	}

	public void setHomeHelper() {
		fRefType = HOME_INTERFACE;
	}

	public void setKeyHelper() {
		fRefType = KEY_CLASS;
	}

	public void setRemoteHelper() {
		fRefType = REMOTE_INTERFACE;
	}

	public void setLocalHomeHelper() {
		fRefType = LOCAL_HOME_INTERFACE;
	}

	public void setLocalHelper() {
		fRefType = LOCAL_INTERFACE;
	}

	public void setServiceEndpointHelper() {
		fRefType = SERVICE_ENDPOINT_INTERFACE;
	}

	/**
	 * Insert the method's description here. Creation date: (10/5/00 6:06:08 PM)
	 * 
	 * @param newSuperclassName
	 *            java.lang.String
	 */
	public void setSuperclassName(java.lang.String newSuperclassName) {
		fSuperclassName = newSuperclassName;
	}

	//Mutator added for client view functionaliy

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
	 * @see EJBGenerationHelper#getOldName()
	 */
	public String getOldName() {
		if (isDelete()) {
			if (getJavaClass() != null)
				return getJavaClass().getName();
			return null;
		}
		return super.getOldName();
	}

}// EJBClassReferenceHelper
