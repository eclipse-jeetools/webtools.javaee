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
package org.eclipse.jst.j2ee.application;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.SecurityRole;

/**
 * The application element is the root element of a J2EE application deployment descriptor.

 */
public interface Application extends CompatibilityDescriptionGroup{


public boolean containsSecurityRole(String name);
/**
 * Returns the first module matching the specified uri and altDD
 */
public Module getModule(String uri, String altDD);

/**
 * Returns the first module matching the specified uri
 * @deprecated preferably use {@link #getModule(String, String)} or {@link #getFirstModule(String)}
 * There may be multiple modules with the same uri but different alt-dds
 **/
public Module getModule(String uri);
/**
 *This returns the j2ee version id. Compare with J2EEVersionConstants to determine j2ee level
 */
public int getJ2EEVersionID() throws IllegalStateException ;
/**
 * This returns the module version id.  Compare with J2EEVersionConstants to determine module level
 */
public int getVersionID() throws IllegalStateException ;

/**
 * Returns the first module matching the specified uri
 */
public Module getFirstModule(String uri);
/**
 * Returns the first module where the alt dd matches the specified uri
 */
public Module getModuleHavingAltDD(String uri);
public SecurityRole getSecurityRoleNamed(String name);
/**
 * Return boolean indicating if this Application was populated from an Applcation1.2 compliant descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
public boolean isVersion1_2Descriptor();
/**
 * Return boolean indicating if this Application was populated from an Application1.3 compliant descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
public boolean isVersion1_3Descriptor();
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4 
	 * The required value for the version is 1.4.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.jst.j2ee.application.ApplicationPackage#getApplication_Version()
	 * @model 
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.application.Application#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of SecurityRoles references
	 * Contains the definitions of security roles which are
	 * global to the application. 
	 */
	EList getSecurityRoles();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Modules references
	 */
	EList getModules();

}






