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
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.jem.java.JavaClass;
/**
 * Describes the bean provider's view of a relationship. It consists of an optional description, and the name and the class type of a field in the source of a role of a relationship. The CMRField::name element corresponds to the name used for the get and set accessor methods for the relationship. The CMRField::type element is used only for collection-valued CMRFields. It specifies the type of the collection that is used (a java class name).
 * 

 * @since 1.0 */
public interface CMRField extends CMPAttribute{

  public  String getCollectionTypeName();  
public void setCollectionTypeName(String typeName);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Role reference
	 */
	EJBRelationshipRole getRole();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Role reference
	 */
	void setRole(EJBRelationshipRole value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The CollectionType reference
	 */
	JavaClass getCollectionType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the CollectionType reference
	 */
	void setCollectionType(JavaClass value);

}





