/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timer Schedule Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *         @since Java EE 6, EJB 3.1
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getSecond <em>Second</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getMinute <em>Minute</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getHour <em>Hour</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getDayOfMonth <em>Day Of Month</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getMonth <em>Month</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getDayOfWeek <em>Day Of Week</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getYear <em>Year</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerScheduleType()
 * @extends JavaEEObject
 * @generated
 */
public interface TimerScheduleType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Second</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Second</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Second</em>' attribute.
	 * @see #setSecond(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerScheduleType_Second()
	 * @generated
	 */
	String getSecond();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getSecond <em>Second</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Second</em>' attribute.
	 * @see #getSecond()
	 * @generated
	 */
	void setSecond(String value);

	/**
	 * Returns the value of the '<em><b>Minute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minute</em>' attribute.
	 * @see #setMinute(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerScheduleType_Minute()
	 * @generated
	 */
	String getMinute();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getMinute <em>Minute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minute</em>' attribute.
	 * @see #getMinute()
	 * @generated
	 */
	void setMinute(String value);

	/**
	 * Returns the value of the '<em><b>Hour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hour</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hour</em>' attribute.
	 * @see #setHour(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerScheduleType_Hour()
	 * @generated
	 */
	String getHour();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getHour <em>Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hour</em>' attribute.
	 * @see #getHour()
	 * @generated
	 */
	void setHour(String value);

	/**
	 * Returns the value of the '<em><b>Day Of Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Day Of Month</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Day Of Month</em>' attribute.
	 * @see #setDayOfMonth(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerScheduleType_DayOfMonth()
	 * @generated
	 */
	String getDayOfMonth();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getDayOfMonth <em>Day Of Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Day Of Month</em>' attribute.
	 * @see #getDayOfMonth()
	 * @generated
	 */
	void setDayOfMonth(String value);

	/**
	 * Returns the value of the '<em><b>Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Month</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Month</em>' attribute.
	 * @see #setMonth(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerScheduleType_Month()
	 * @generated
	 */
	String getMonth();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getMonth <em>Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Month</em>' attribute.
	 * @see #getMonth()
	 * @generated
	 */
	void setMonth(String value);

	/**
	 * Returns the value of the '<em><b>Day Of Week</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Day Of Week</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Day Of Week</em>' attribute.
	 * @see #setDayOfWeek(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerScheduleType_DayOfWeek()
	 * @generated
	 */
	String getDayOfWeek();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getDayOfWeek <em>Day Of Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Day Of Week</em>' attribute.
	 * @see #getDayOfWeek()
	 * @generated
	 */
	void setDayOfWeek(String value);

	/**
	 * Returns the value of the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Year</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Year</em>' attribute.
	 * @see #setYear(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerScheduleType_Year()
	 * @generated
	 */
	String getYear();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getYear <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Year</em>' attribute.
	 * @see #getYear()
	 * @generated
	 */
	void setYear(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerScheduleType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerScheduleType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // TimerScheduleType
