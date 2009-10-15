/**
 * <copyright>
 * </copyright>
 *
 * $Id: AsyncMethodTypeImpl.java,v 1.1 2009/10/15 18:52:06 canderson Exp $
 */
package org.eclipse.jst.javaee.ejb.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.ejb.AsyncMethodType;
import org.eclipse.jst.javaee.ejb.MethodInterfaceType;
import org.eclipse.jst.javaee.ejb.MethodParams;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Async Method Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AsyncMethodTypeImpl#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AsyncMethodTypeImpl#getMethodParams <em>Method Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AsyncMethodTypeImpl#getMethodIntf <em>Method Intf</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AsyncMethodTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AsyncMethodTypeImpl extends EObjectImpl implements AsyncMethodType {
	/**
	 * The default value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodName()
	 * @generated
	 * @ordered
	 */
	protected static final String METHOD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodName()
	 * @generated
	 * @ordered
	 */
	protected String methodName = METHOD_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMethodParams() <em>Method Params</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodParams()
	 * @generated
	 * @ordered
	 */
	protected MethodParams methodParams;

	/**
	 * The default value of the '{@link #getMethodIntf() <em>Method Intf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodIntf()
	 * @generated
	 * @ordered
	 */
	protected static final MethodInterfaceType METHOD_INTF_EDEFAULT = MethodInterfaceType.HOME_LITERAL;

	/**
	 * The cached value of the '{@link #getMethodIntf() <em>Method Intf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodIntf()
	 * @generated
	 * @ordered
	 */
	protected MethodInterfaceType methodIntf = METHOD_INTF_EDEFAULT;

	/**
	 * This is true if the Method Intf attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean methodIntfESet;

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
	protected AsyncMethodTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.ASYNC_METHOD_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodName(String newMethodName) {
		String oldMethodName = methodName;
		methodName = newMethodName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ASYNC_METHOD_TYPE__METHOD_NAME, oldMethodName, methodName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodParams getMethodParams() {
		return methodParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethodParams(MethodParams newMethodParams, NotificationChain msgs) {
		MethodParams oldMethodParams = methodParams;
		methodParams = newMethodParams;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.ASYNC_METHOD_TYPE__METHOD_PARAMS, oldMethodParams, newMethodParams);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodParams(MethodParams newMethodParams) {
		if (newMethodParams != methodParams) {
			NotificationChain msgs = null;
			if (methodParams != null)
				msgs = ((InternalEObject)methodParams).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.ASYNC_METHOD_TYPE__METHOD_PARAMS, null, msgs);
			if (newMethodParams != null)
				msgs = ((InternalEObject)newMethodParams).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.ASYNC_METHOD_TYPE__METHOD_PARAMS, null, msgs);
			msgs = basicSetMethodParams(newMethodParams, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ASYNC_METHOD_TYPE__METHOD_PARAMS, newMethodParams, newMethodParams));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodInterfaceType getMethodIntf() {
		return methodIntf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodIntf(MethodInterfaceType newMethodIntf) {
		MethodInterfaceType oldMethodIntf = methodIntf;
		methodIntf = newMethodIntf == null ? METHOD_INTF_EDEFAULT : newMethodIntf;
		boolean oldMethodIntfESet = methodIntfESet;
		methodIntfESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ASYNC_METHOD_TYPE__METHOD_INTF, oldMethodIntf, methodIntf, !oldMethodIntfESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMethodIntf() {
		MethodInterfaceType oldMethodIntf = methodIntf;
		boolean oldMethodIntfESet = methodIntfESet;
		methodIntf = METHOD_INTF_EDEFAULT;
		methodIntfESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.ASYNC_METHOD_TYPE__METHOD_INTF, oldMethodIntf, METHOD_INTF_EDEFAULT, oldMethodIntfESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMethodIntf() {
		return methodIntfESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ASYNC_METHOD_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_PARAMS:
				return basicSetMethodParams(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_NAME:
				return getMethodName();
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_PARAMS:
				return getMethodParams();
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_INTF:
				return getMethodIntf();
			case EjbPackage.ASYNC_METHOD_TYPE__ID:
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
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_NAME:
				setMethodName((String)newValue);
				return;
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_PARAMS:
				setMethodParams((MethodParams)newValue);
				return;
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_INTF:
				setMethodIntf((MethodInterfaceType)newValue);
				return;
			case EjbPackage.ASYNC_METHOD_TYPE__ID:
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
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_NAME:
				setMethodName(METHOD_NAME_EDEFAULT);
				return;
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_PARAMS:
				setMethodParams((MethodParams)null);
				return;
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_INTF:
				unsetMethodIntf();
				return;
			case EjbPackage.ASYNC_METHOD_TYPE__ID:
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
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_NAME:
				return METHOD_NAME_EDEFAULT == null ? methodName != null : !METHOD_NAME_EDEFAULT.equals(methodName);
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_PARAMS:
				return methodParams != null;
			case EjbPackage.ASYNC_METHOD_TYPE__METHOD_INTF:
				return isSetMethodIntf();
			case EjbPackage.ASYNC_METHOD_TYPE__ID:
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
		result.append(" (methodName: "); //$NON-NLS-1$
		result.append(methodName);
		result.append(", methodIntf: "); //$NON-NLS-1$
		if (methodIntfESet) result.append(methodIntf); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //AsyncMethodTypeImpl
