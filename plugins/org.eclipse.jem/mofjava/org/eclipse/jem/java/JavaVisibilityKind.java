/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java;
/*
 *  $RCSfile: JavaVisibilityKind.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/15 22:37:02 $ 
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Java Visibility Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * PACKAGE=1
 * PUBLIC=2
 * PRIVATE=3
 * PROTECTED=4
 * <!-- end-model-doc -->
 * @see org.eclipse.jem.java.JavaRefPackage#getJavaVisibilityKind()
 * @model
 * @generated
 */
public final class JavaVisibilityKind extends AbstractEnumerator
{
	/**
	 * The '<em><b>PUBLIC</b></em>' literal value.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #PUBLIC_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
  public static final int PUBLIC = 0;

	/**
	 * The '<em><b>PRIVATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #PRIVATE_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
  public static final int PRIVATE = 1;

	/**
	 * The '<em><b>PROTECTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #PROTECTED_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
  public static final int PROTECTED = 2;

	/**
	 * The '<em><b>PACKAGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #PACKAGE_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
  public static final int PACKAGE = 3;

	/**
	 * The '<em><b>PUBLIC</b></em>' literal object.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>PUBLIC</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @see #PUBLIC
	 * @generated
	 * @ordered
	 */
  public static final JavaVisibilityKind PUBLIC_LITERAL = new JavaVisibilityKind(PUBLIC, "PUBLIC");

	/**
	 * The '<em><b>PRIVATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>PRIVATE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @see #PRIVATE
	 * @generated
	 * @ordered
	 */
  public static final JavaVisibilityKind PRIVATE_LITERAL = new JavaVisibilityKind(PRIVATE, "PRIVATE");

	/**
	 * The '<em><b>PROTECTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>PROTECTED</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @see #PROTECTED
	 * @generated
	 * @ordered
	 */
  public static final JavaVisibilityKind PROTECTED_LITERAL = new JavaVisibilityKind(PROTECTED, "PROTECTED");

	/**
	 * The '<em><b>PACKAGE</b></em>' literal object.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>PACKAGE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @see #PACKAGE
	 * @generated
	 * @ordered
	 */
  public static final JavaVisibilityKind PACKAGE_LITERAL = new JavaVisibilityKind(PACKAGE, "PACKAGE");

	/**
	 * An array of all the '<em><b>Java Visibility Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private static final JavaVisibilityKind[] VALUES_ARRAY =
		new JavaVisibilityKind[] {
			PUBLIC_LITERAL,
			PRIVATE_LITERAL,
			PROTECTED_LITERAL,
			PACKAGE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Java Visibility Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Java Visibility Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public static JavaVisibilityKind get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JavaVisibilityKind result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Java Visibility Kind</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public static JavaVisibilityKind get(int value) {
		switch (value) {
			case PUBLIC: return PUBLIC_LITERAL;
			case PRIVATE: return PRIVATE_LITERAL;
			case PROTECTED: return PROTECTED_LITERAL;
			case PACKAGE: return PACKAGE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private JavaVisibilityKind(int value, String name) {
		super(value, name);
	}

} //JavaVisibilityKind
