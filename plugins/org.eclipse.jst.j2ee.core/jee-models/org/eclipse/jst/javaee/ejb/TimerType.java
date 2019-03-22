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

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timer Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The timerType specifies an enterprise bean timer.  Each
 *         timer is automatically created by the container upon
 *         deployment.  Timer callbacks occur based on the 
 *         schedule attributes.  All callbacks are made to the
 *         timeout-method associated with the timer.  
 *         
 *         A timer can have an optional start and/or end date. If
 *         a start date is specified, it takes precedence over the
 *         associated timer schedule such that any matching
 *         expirations prior to the start time will not occur.
 *         Likewise, no matching expirations will occur after any
 *         end date.   Start/End dates are specified using the
 *         XML Schema dateTime type, which follows the ISO-8601
 *         standard for date(and optional time-within-the-day) 
 *         representation.
 *         
 *         An optional flag can be used to control whether
 *         this timer has persistent(true) delivery semantics or
 *         non-persistent(false) delivery semantics.  If not specified,
 *         the value defaults to persistent(true).
 *         
 *         A time zone can optionally be associated with a timer.
 *         If specified, the timer's schedule is evaluated in the context
 *         of that time zone, regardless of the default time zone in which
 *         the container is executing.   Time zones are specified as an
 *         ID string.  The set of required time zone IDs is defined by
 *         the Zone Name(TZ) column of the public domain zoneinfo database.
 *         
 *         An optional info string can be assigned to the timer and 
 *         retrieved at runtime through the Timer.getInfo() method.
 *         
 *         The timerType can only be specified on stateless session
 *         beans, singleton session beans, and message-driven beans.
 *         
 *         @since Java EE 6, EJB 3.1
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerType#getSchedule <em>Schedule</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerType#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerType#getEnd <em>End</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerType#getTimeoutMethod <em>Timeout Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerType#isPersistent <em>Persistent</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerType#getTimezone <em>Timezone</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerType#getInfo <em>Info</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.TimerType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerType()
 * @extends JavaEEObject
 * @generated
 */
public interface TimerType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerType_Description()
	 * @generated
	 */
	List<Description> getDescription();

	/**
	 * Returns the value of the '<em><b>Schedule</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schedule</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schedule</em>' containment reference.
	 * @see #setSchedule(TimerScheduleType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerType_Schedule()
	 * @generated
	 */
	TimerScheduleType getSchedule();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerType#getSchedule <em>Schedule</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schedule</em>' containment reference.
	 * @see #getSchedule()
	 * @generated
	 */
	void setSchedule(TimerScheduleType value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(XMLGregorianCalendar)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerType_Start()
	 * @generated
	 */
	XMLGregorianCalendar getStart();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerType#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(XMLGregorianCalendar value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(XMLGregorianCalendar)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerType_End()
	 * @generated
	 */
	XMLGregorianCalendar getEnd();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerType#getEnd <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' attribute.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(XMLGregorianCalendar value);

	/**
	 * Returns the value of the '<em><b>Timeout Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout Method</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout Method</em>' containment reference.
	 * @see #setTimeoutMethod(NamedMethodType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerType_TimeoutMethod()
	 * @generated
	 */
	NamedMethodType getTimeoutMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerType#getTimeoutMethod <em>Timeout Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout Method</em>' containment reference.
	 * @see #getTimeoutMethod()
	 * @generated
	 */
	void setTimeoutMethod(NamedMethodType value);

	/**
	 * Returns the value of the '<em><b>Persistent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persistent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persistent</em>' attribute.
	 * @see #isSetPersistent()
	 * @see #unsetPersistent()
	 * @see #setPersistent(boolean)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerType_Persistent()
	 * @generated
	 */
	boolean isPersistent();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerType#isPersistent <em>Persistent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persistent</em>' attribute.
	 * @see #isSetPersistent()
	 * @see #unsetPersistent()
	 * @see #isPersistent()
	 * @generated
	 */
	void setPersistent(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerType#isPersistent <em>Persistent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPersistent()
	 * @see #isPersistent()
	 * @see #setPersistent(boolean)
	 * @generated
	 */
	void unsetPersistent();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.TimerType#isPersistent <em>Persistent</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Persistent</em>' attribute is set.
	 * @see #unsetPersistent()
	 * @see #isPersistent()
	 * @see #setPersistent(boolean)
	 * @generated
	 */
	boolean isSetPersistent();

	/**
	 * Returns the value of the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timezone</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timezone</em>' attribute.
	 * @see #setTimezone(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerType_Timezone()
	 * @generated
	 */
	String getTimezone();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerType#getTimezone <em>Timezone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timezone</em>' attribute.
	 * @see #getTimezone()
	 * @generated
	 */
	void setTimezone(String value);

	/**
	 * Returns the value of the '<em><b>Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Info</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Info</em>' attribute.
	 * @see #setInfo(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerType_Info()
	 * @generated
	 */
	String getInfo();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerType#getInfo <em>Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Info</em>' attribute.
	 * @see #getInfo()
	 * @generated
	 */
	void setInfo(String value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTimerType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.TimerType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // TimerType
