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

import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;



/**
 * Insert the type's description here. Creation date: (08/10/00 12:55:52 PM)
 * 
 * @author: Administrator
 */
public class CMPField implements java.io.Serializable {
	protected java.lang.String name;
	protected java.lang.String type;
	protected boolean isArray;
	protected int arrayDimension = 1;
	protected java.lang.String initialValue;
	protected boolean isKey;
	protected boolean accessWithGS; //  generate getter/setter
	protected boolean promoteGS; //  promote getter/setter
	protected boolean getterRO; //  define the getter as Read Only
	protected boolean promoteLocalGS; //  promote getter/setter
	protected boolean existing = false;

	/**
	 * CMPField constructor comment.
	 */
	public CMPField() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:41:24 PM)
	 * 
	 * @return int
	 */
	public int getArrayDimension() {
		return arrayDimension;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:42:17 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getInitialValue() {
		return initialValue;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:38:03 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:39:22 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * Insert the method's description here. Creation date: (08/16/00 2:22:35 PM)
	 * 
	 * @return boolean
	 */
	public boolean isAccessWithGS() {
		return accessWithGS;
	}

	/**
	 * Insert the method's description here. Creation date: (9/17/2001 5:01:14 PM)
	 * 
	 * @return boolean
	 */
	public boolean isGetterRO() {
		return getterRO;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:40:25 PM)
	 * 
	 * @return boolean
	 */
	public boolean isIsArray() {
		return isArray;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:42:48 PM)
	 * 
	 * @return boolean
	 */
	public boolean isIsKey() {
		return isKey;
	}

	/**
	 * Insert the method's description here. Creation date: (08/16/00 2:23:12 PM)
	 * 
	 * @return boolean
	 */
	public boolean isPromoteGS() {
		return promoteGS;
	}

	public boolean isValidForPrimitiveKeyField() {
		return !org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers.isPrimitive(getType());
	}

	/**
	 * Insert the method's description here. Creation date: (08/16/00 2:22:35 PM)
	 * 
	 * @param newAccessWithGS
	 *            boolean
	 */
	public void setAccessWithGS(boolean newAccessWithGS) {
		accessWithGS = newAccessWithGS;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:41:24 PM)
	 * 
	 * @param newArrayDimension
	 *            int
	 */
	public void setArrayDimension(int newArrayDimension) {
		arrayDimension = newArrayDimension;
	}

	/**
	 * Insert the method's description here. Creation date: (9/17/2001 5:01:14 PM)
	 * 
	 * @param newGetterRO
	 *            boolean
	 */
	public void setGetterRO(boolean newGetterRO) {
		getterRO = newGetterRO;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:42:17 PM)
	 * 
	 * @param newInitialValue
	 *            java.lang.String
	 */
	public void setInitialValue(java.lang.String newInitialValue) {
		initialValue = newInitialValue;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:40:25 PM)
	 * 
	 * @param newIsArray
	 *            boolean
	 */
	public void setIsArray(boolean newIsArray) {
		isArray = newIsArray;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:42:48 PM)
	 * 
	 * @param newIsKey
	 *            boolean
	 */
	public void setIsKey(boolean newIsKey) {
		isKey = newIsKey;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:38:03 PM)
	 * 
	 * @param newName
	 *            java.lang.String
	 */
	public void setName(java.lang.String newName) {
		name = newName;
	}

	/**
	 * Insert the method's description here. Creation date: (08/16/00 2:23:12 PM)
	 * 
	 * @param newPromoteGS
	 *            boolean
	 */
	public void setPromoteGS(boolean newPromoteGS) {
		promoteGS = newPromoteGS;
	}

	/**
	 * Insert the method's description here. Creation date: (08/10/00 4:39:22 PM)
	 * 
	 * @param newType
	 *            java.lang.String
	 */
	public void setType(java.lang.String newType) {
		type = newType;
	}

	/**
	 * Insert the method's description here. Creation date: (08/16/00 3:37:14 PM)
	 * 
	 * @return java.lang.String
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();
		String filler = " ";//$NON-NLS-1$

		String mType;
		if (type.length() > 20)
			mType = "..." + type.substring(type.length() - 18);//$NON-NLS-1$
		else
			mType = type;

		s.append(mType);
		if (isArray) {
			for (int i = 0; i < arrayDimension; i++)
				s.append("[]");//$NON-NLS-1$
		}
		s.append(filler);
		for (int i = s.length(); i <= 22; i++)
			s.append(filler);
		s.append(filler + name);
		for (int i = s.length(); i <= 40; i++)
			s.append(filler);
		if (isIsKey())
			s.append(" (Key)");//$NON-NLS-1$
		return s.toString();
	}

	/**
	 * Gets the promoteLocalGS.
	 * 
	 * @return Returns a boolean
	 */
	public boolean isPromoteLocalGS() {
		return promoteLocalGS;
	}

	/**
	 * Sets the promoteLocalGS.
	 * 
	 * @param promoteLocalGS
	 *            The promoteLocalGS to set
	 */
	public void setPromoteLocalGS(boolean promoteLocalGS) {
		this.promoteLocalGS = promoteLocalGS;
	}

	/**
	 * Method setExisting.
	 * 
	 * @param isExistingField
	 */
	public void setExisting(boolean isExistingField) {
		existing = isExistingField;
	}

	/**
	 * Returns the existing.
	 * 
	 * @return boolean
	 */
	public boolean isExisting() {
		return existing;
	}


	public String methodSuffix() {
		return EJBGenHelpers.firstAsUppercase(getName());
	}

	/**
	 * Takes into consideration array dimensions
	 * 
	 * @return
	 */
	public String getTypeSignature() {
		if (!isArray)
			return getType();
		StringBuffer sb = new StringBuffer(type.length() + arrayDimension * 2);
		sb.append(type);
		for (int i = 0; i < arrayDimension; i++) {
			sb.append("[]"); //$NON-NLS-1$
		}
		return sb.toString();
	}
}