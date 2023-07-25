/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Time Unit Type Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The time-unit-typeType represents a time duration at a given
 *         unit of granularity.  
 *         
 *         The time unit type must be one of the following :
 *         
 *         Days
 *         Hours
 *         Minutes
 *         Seconds
 *         Milliseconds
 *         Microseconds
 *         Nanoseconds
 *         
 *         @since Java EE 6, EJB 3.1
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimeUnitTypeType()
 * @generated
 */
public enum TimeUnitTypeType implements Enumerator {
	/**
	 * The '<em><b>Days</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DAYS_VALUE
	 * @generated
	 * @ordered
	 */
	DAYS(0, "Days", "Days"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Hours</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HOURS_VALUE
	 * @generated
	 * @ordered
	 */
	HOURS(1, "Hours", "Hours"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Minutes</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUTES_VALUE
	 * @generated
	 * @ordered
	 */
	MINUTES(2, "Minutes", "Minutes"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Seconds</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECONDS_VALUE
	 * @generated
	 * @ordered
	 */
	SECONDS(3, "Seconds", "Seconds"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Milliseconds</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MILLISECONDS_VALUE
	 * @generated
	 * @ordered
	 */
	MILLISECONDS(4, "Milliseconds", "Milliseconds"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Microseconds</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MICROSECONDS_VALUE
	 * @generated
	 * @ordered
	 */
	MICROSECONDS(5, "Microseconds", "Microseconds"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Nanoseconds</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NANOSECONDS_VALUE
	 * @generated
	 * @ordered
	 */
	NANOSECONDS(6, "Nanoseconds", "Nanoseconds"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Days</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Days</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DAYS
	 * @generated
	 * @ordered
	 */
	public static final int DAYS_VALUE = 0;

	/**
	 * The '<em><b>Hours</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Hours</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HOURS
	 * @generated
	 * @ordered
	 */
	public static final int HOURS_VALUE = 1;

	/**
	 * The '<em><b>Minutes</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Minutes</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MINUTES
	 * @generated
	 * @ordered
	 */
	public static final int MINUTES_VALUE = 2;

	/**
	 * The '<em><b>Seconds</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Seconds</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SECONDS
	 * @generated
	 * @ordered
	 */
	public static final int SECONDS_VALUE = 3;

	/**
	 * The '<em><b>Milliseconds</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Milliseconds</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MILLISECONDS
	 * @generated
	 * @ordered
	 */
	public static final int MILLISECONDS_VALUE = 4;

	/**
	 * The '<em><b>Microseconds</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Microseconds</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MICROSECONDS
	 * @generated
	 * @ordered
	 */
	public static final int MICROSECONDS_VALUE = 5;

	/**
	 * The '<em><b>Nanoseconds</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Nanoseconds</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NANOSECONDS
	 * @generated
	 * @ordered
	 */
	public static final int NANOSECONDS_VALUE = 6;

	/**
	 * An array of all the '<em><b>Time Unit Type Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TimeUnitTypeType[] VALUES_ARRAY =
		new TimeUnitTypeType[] {
			DAYS,
			HOURS,
			MINUTES,
			SECONDS,
			MILLISECONDS,
			MICROSECONDS,
			NANOSECONDS,
		};

	/**
	 * A public read-only list of all the '<em><b>Time Unit Type Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TimeUnitTypeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Time Unit Type Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TimeUnitTypeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TimeUnitTypeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Time Unit Type Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TimeUnitTypeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TimeUnitTypeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Time Unit Type Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TimeUnitTypeType get(int value) {
		switch (value) {
			case DAYS_VALUE: return DAYS;
			case HOURS_VALUE: return HOURS;
			case MINUTES_VALUE: return MINUTES;
			case SECONDS_VALUE: return SECONDS;
			case MILLISECONDS_VALUE: return MILLISECONDS;
			case MICROSECONDS_VALUE: return MICROSECONDS;
			case NANOSECONDS_VALUE: return NANOSECONDS;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TimeUnitTypeType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //TimeUnitTypeType
