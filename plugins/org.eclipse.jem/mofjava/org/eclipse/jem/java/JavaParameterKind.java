/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java;

/*
 *  $RCSfile: JavaParameterKind.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:17 $ 
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Java Parameter Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * IN=0
 * OUT=1
 * INOUT=2
 * RETURN=3
 * <!-- end-model-doc -->
 * @see org.eclipse.jem.java.JavaRefPackage#getJavaParameterKind()
 * @model
 * @generated
 */
public final class JavaParameterKind extends AbstractEnumerator
{
	/**
	 * The '<em><b>IN</b></em>' literal value.
	 * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
	 * @see #IN_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int IN = 0;
	/**
	 * The '<em><b>OUT</b></em>' literal value.
	 * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
	 * @see #OUT_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int OUT = 1;
	/**
	 * The '<em><b>INOUT</b></em>' literal value.
	 * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
	 * @see #INOUT_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int INOUT = 2;
	/**
	 * The '<em><b>RETURN</b></em>' literal value.
	 * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
	 * @see #RETURN_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int RETURN = 3;
	/**
	 * The '<em><b>IN</b></em>' literal object.
	 * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>IN</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
	 * @see #IN
	 * @generated
	 * @ordered
	 */
  public static final JavaParameterKind IN_LITERAL = new JavaParameterKind(IN, "IN");

	/**
	 * The '<em><b>OUT</b></em>' literal object.
	 * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>OUT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
	 * @see #OUT
	 * @generated
	 * @ordered
	 */
  public static final JavaParameterKind OUT_LITERAL = new JavaParameterKind(OUT, "OUT");

	/**
	 * The '<em><b>INOUT</b></em>' literal object.
	 * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>INOUT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
	 * @see #INOUT
	 * @generated
	 * @ordered
	 */
  public static final JavaParameterKind INOUT_LITERAL = new JavaParameterKind(INOUT, "INOUT");

	/**
	 * The '<em><b>RETURN</b></em>' literal object.
	 * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>RETURN</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
	 * @see #RETURN
	 * @generated
	 * @ordered
	 */
  public static final JavaParameterKind RETURN_LITERAL = new JavaParameterKind(RETURN, "RETURN");

	/**
	 * An array of all the '<em><b>Java Parameter Kind</b></em>' enumerators.
	 * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
	 * @generated
	 */
  private static final JavaParameterKind[] VALUES_ARRAY =
		new JavaParameterKind[] {
			IN_LITERAL,
			OUT_LITERAL,
			INOUT_LITERAL,
			RETURN_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Java Parameter Kind</b></em>' enumerators.
	 * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
	 * @generated
	 */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Java Parameter Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
	 * @generated
	 */
  public static JavaParameterKind get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JavaParameterKind result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Java Parameter Kind</b></em>' literal with the specified value.
	 * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
	 * @generated
	 */
  public static JavaParameterKind get(int value) {
		switch (value) {
			case IN: return IN_LITERAL;
			case OUT: return OUT_LITERAL;
			case INOUT: return INOUT_LITERAL;
			case RETURN: return RETURN_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
	 * @generated
	 */
  private JavaParameterKind(int value, String name) {
		super(value, name);
	}

} //JavaParameterKind


