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
package org.eclipse.jst.javaee.ejb.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.ejb.TimerScheduleType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Timer Schedule Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.TimerScheduleTypeImpl#getSecond <em>Second</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.TimerScheduleTypeImpl#getMinute <em>Minute</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.TimerScheduleTypeImpl#getHour <em>Hour</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.TimerScheduleTypeImpl#getDayOfMonth <em>Day Of Month</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.TimerScheduleTypeImpl#getMonth <em>Month</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.TimerScheduleTypeImpl#getDayOfWeek <em>Day Of Week</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.TimerScheduleTypeImpl#getYear <em>Year</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.TimerScheduleTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimerScheduleTypeImpl extends EObjectImpl implements TimerScheduleType {
	/**
	 * The default value of the '{@link #getSecond() <em>Second</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecond()
	 * @generated
	 * @ordered
	 */
	protected static final String SECOND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecond() <em>Second</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecond()
	 * @generated
	 * @ordered
	 */
	protected String second = SECOND_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinute() <em>Minute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinute()
	 * @generated
	 * @ordered
	 */
	protected static final String MINUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinute() <em>Minute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinute()
	 * @generated
	 * @ordered
	 */
	protected String minute = MINUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHour() <em>Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHour()
	 * @generated
	 * @ordered
	 */
	protected static final String HOUR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHour() <em>Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHour()
	 * @generated
	 * @ordered
	 */
	protected String hour = HOUR_EDEFAULT;

	/**
	 * The default value of the '{@link #getDayOfMonth() <em>Day Of Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDayOfMonth()
	 * @generated
	 * @ordered
	 */
	protected static final String DAY_OF_MONTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDayOfMonth() <em>Day Of Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDayOfMonth()
	 * @generated
	 * @ordered
	 */
	protected String dayOfMonth = DAY_OF_MONTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMonth() <em>Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonth()
	 * @generated
	 * @ordered
	 */
	protected static final String MONTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMonth() <em>Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonth()
	 * @generated
	 * @ordered
	 */
	protected String month = MONTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getDayOfWeek() <em>Day Of Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDayOfWeek()
	 * @generated
	 * @ordered
	 */
	protected static final String DAY_OF_WEEK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDayOfWeek() <em>Day Of Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDayOfWeek()
	 * @generated
	 * @ordered
	 */
	protected String dayOfWeek = DAY_OF_WEEK_EDEFAULT;

	/**
	 * The default value of the '{@link #getYear() <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYear()
	 * @generated
	 * @ordered
	 */
	protected static final String YEAR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getYear() <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYear()
	 * @generated
	 * @ordered
	 */
	protected String year = YEAR_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimerScheduleTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.TIMER_SCHEDULE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSecond() {
		return second;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSecond(String newSecond) {
		String oldSecond = second;
		second = newSecond;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.TIMER_SCHEDULE_TYPE__SECOND, oldSecond, second));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMinute() {
		return minute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMinute(String newMinute) {
		String oldMinute = minute;
		minute = newMinute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.TIMER_SCHEDULE_TYPE__MINUTE, oldMinute, minute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHour() {
		return hour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHour(String newHour) {
		String oldHour = hour;
		hour = newHour;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.TIMER_SCHEDULE_TYPE__HOUR, oldHour, hour));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDayOfMonth() {
		return dayOfMonth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDayOfMonth(String newDayOfMonth) {
		String oldDayOfMonth = dayOfMonth;
		dayOfMonth = newDayOfMonth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.TIMER_SCHEDULE_TYPE__DAY_OF_MONTH, oldDayOfMonth, dayOfMonth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMonth() {
		return month;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMonth(String newMonth) {
		String oldMonth = month;
		month = newMonth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.TIMER_SCHEDULE_TYPE__MONTH, oldMonth, month));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDayOfWeek(String newDayOfWeek) {
		String oldDayOfWeek = dayOfWeek;
		dayOfWeek = newDayOfWeek;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.TIMER_SCHEDULE_TYPE__DAY_OF_WEEK, oldDayOfWeek, dayOfWeek));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getYear() {
		return year;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setYear(String newYear) {
		String oldYear = year;
		year = newYear;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.TIMER_SCHEDULE_TYPE__YEAR, oldYear, year));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.TIMER_SCHEDULE_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.TIMER_SCHEDULE_TYPE__SECOND:
				return getSecond();
			case EjbPackage.TIMER_SCHEDULE_TYPE__MINUTE:
				return getMinute();
			case EjbPackage.TIMER_SCHEDULE_TYPE__HOUR:
				return getHour();
			case EjbPackage.TIMER_SCHEDULE_TYPE__DAY_OF_MONTH:
				return getDayOfMonth();
			case EjbPackage.TIMER_SCHEDULE_TYPE__MONTH:
				return getMonth();
			case EjbPackage.TIMER_SCHEDULE_TYPE__DAY_OF_WEEK:
				return getDayOfWeek();
			case EjbPackage.TIMER_SCHEDULE_TYPE__YEAR:
				return getYear();
			case EjbPackage.TIMER_SCHEDULE_TYPE__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EjbPackage.TIMER_SCHEDULE_TYPE__SECOND:
				setSecond((String)newValue);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__MINUTE:
				setMinute((String)newValue);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__HOUR:
				setHour((String)newValue);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__DAY_OF_MONTH:
				setDayOfMonth((String)newValue);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__MONTH:
				setMonth((String)newValue);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__DAY_OF_WEEK:
				setDayOfWeek((String)newValue);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__YEAR:
				setYear((String)newValue);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__ID:
				setId((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EjbPackage.TIMER_SCHEDULE_TYPE__SECOND:
				setSecond(SECOND_EDEFAULT);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__MINUTE:
				setMinute(MINUTE_EDEFAULT);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__HOUR:
				setHour(HOUR_EDEFAULT);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__DAY_OF_MONTH:
				setDayOfMonth(DAY_OF_MONTH_EDEFAULT);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__MONTH:
				setMonth(MONTH_EDEFAULT);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__DAY_OF_WEEK:
				setDayOfWeek(DAY_OF_WEEK_EDEFAULT);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__YEAR:
				setYear(YEAR_EDEFAULT);
				return;
			case EjbPackage.TIMER_SCHEDULE_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EjbPackage.TIMER_SCHEDULE_TYPE__SECOND:
				return SECOND_EDEFAULT == null ? second != null : !SECOND_EDEFAULT.equals(second);
			case EjbPackage.TIMER_SCHEDULE_TYPE__MINUTE:
				return MINUTE_EDEFAULT == null ? minute != null : !MINUTE_EDEFAULT.equals(minute);
			case EjbPackage.TIMER_SCHEDULE_TYPE__HOUR:
				return HOUR_EDEFAULT == null ? hour != null : !HOUR_EDEFAULT.equals(hour);
			case EjbPackage.TIMER_SCHEDULE_TYPE__DAY_OF_MONTH:
				return DAY_OF_MONTH_EDEFAULT == null ? dayOfMonth != null : !DAY_OF_MONTH_EDEFAULT.equals(dayOfMonth);
			case EjbPackage.TIMER_SCHEDULE_TYPE__MONTH:
				return MONTH_EDEFAULT == null ? month != null : !MONTH_EDEFAULT.equals(month);
			case EjbPackage.TIMER_SCHEDULE_TYPE__DAY_OF_WEEK:
				return DAY_OF_WEEK_EDEFAULT == null ? dayOfWeek != null : !DAY_OF_WEEK_EDEFAULT.equals(dayOfWeek);
			case EjbPackage.TIMER_SCHEDULE_TYPE__YEAR:
				return YEAR_EDEFAULT == null ? year != null : !YEAR_EDEFAULT.equals(year);
			case EjbPackage.TIMER_SCHEDULE_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (second: "); //$NON-NLS-1$
		result.append(second);
		result.append(", minute: "); //$NON-NLS-1$
		result.append(minute);
		result.append(", hour: "); //$NON-NLS-1$
		result.append(hour);
		result.append(", dayOfMonth: "); //$NON-NLS-1$
		result.append(dayOfMonth);
		result.append(", month: "); //$NON-NLS-1$
		result.append(month);
		result.append(", dayOfWeek: "); //$NON-NLS-1$
		result.append(dayOfWeek);
		result.append(", year: "); //$NON-NLS-1$
		result.append(year);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //TimerScheduleTypeImpl
