/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.jem.java.JavaClass;

/**
 * The entity element declares an entity bean. The declaration consists of: an optional description; optional display name; optional small icon file name; optional large icon file name; a unique name assigned to the enterprise bean in the deployment descriptor; the names of the entity bean's home and remote interfaces, if any; the names of the entity bean's local home and local interface, if any; the entity bean's implementation class; the entity bean's persistence management type; the entity bean's primary key class name; an indication of the entity bean's reentrancy; an optional specification of the entity bean's cmp-version; an optional specification of the entity bean's abstract schema name; an optional list of container-managed fields; an optional specification of the primary key field; an optional declaration of the bean's environment entries; an optional declaration of the bean's EJB references; an optional declaration of the bean's local EJB references; an optional declaration of the security role references; an optional declaration of the security identity to be used for the execution of the bean's methods; an optional declaration of the bean's resource manager connection factory references; an optional declaration of the bean's resource environment references; an optional set of query declarations for finder and select methods for an entity bean with cmp-version 2.x.
 * The optional abstract-schema-name element must be specified for an entity bean with container managed persistence and cmp-version 2.x. The optional primkey-field may be present in the descriptor if the entity's persistence-type is Container. The optional cmp-version element may be present in the descriptor if the entity's persistence-type is Container. If the persistence-type is Container and the cmp-version element is not specified, its value defaults to 2.x.
 * The optional home and remote elements must be specified if the entity bean cmp-version is 1.x.
 * The optional local-home and local elements must be specified if the entity bean has a local home and local interface.
 * The optional query elements must be present if the persistence-type is Container and the cmp-version is 2.x and query methods other than findByPrimaryKey have been defined for the entity bean.
 * The other elements that are optional are "optional" in the sense that they are omitted if the lists represented by them are empty. At least one cmp-field element must be present in the descriptor if
 * the entity's persistence-type is Container and the cmp-version is 1.x, and none must not be present if the entity's persistence-type is Bean.
 * @since 1.0 */
public interface Entity extends EnterpriseBean{

public String getPrimaryKeyName();
public boolean isContainerManagedEntity();

public void setPrimaryKeyName(String primaryKeyName);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsReentrant attribute
	 * The reentrant element specifies whether an entity bean is reentrant or not.

	 */
	boolean isReentrant();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.ejb.Entity#isReentrant <em>Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reentrant</em>' attribute.
	 * @see #isSetReentrant()
	 * @see #unsetReentrant()
	 * @see #isReentrant()
	 * @generated
	 */
	void setReentrant(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.ejb.Entity#isReentrant <em>Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isSetReentrant()
	 * @see #isReentrant()
	 * @see #setReentrant(boolean)
	 * @generated
	 */
  void unsetReentrant();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.ejb.Entity#isReentrant <em>Reentrant</em>}' attribute is set.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Reentrant</em>' attribute is set.
	 * @see #unsetReentrant()
	 * @see #isReentrant()
	 * @see #setReentrant(boolean)
	 * @generated
	 */
  boolean isSetReentrant();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The PrimaryKey reference
	 * The primkey-field element is used to specify the name of the primary key field
	 * for an entity with container-managed persistence.  The primkey-field must be
	 * one of the fields declared in the cmp-field
	 * element, and the type of the field must be the same as the primary key type. 
	 * The primkey-field element is not used if the primary key maps to multiple
	 * container-managed fields (i.e. the key is a compound key). In this case, the
	 * fields of the primary key class must be public, and their names must correspond 
	 * to the field names of the entity bean class that comprise the key.
	 * Examples:<prim-key-class>java.lang.String<//prim-key-class>
	 * 	<prim-key-class>com.wombat.empl.EmployeeID<//prim-key-class>
	 * 	<prim-key-class>java.lang.Object<//prim-key-class>

	 */
	JavaClass getPrimaryKey();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the PrimaryKey reference
	 */
	void setPrimaryKey(JavaClass value);

}






