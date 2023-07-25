/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.Entity;

/**
 * The entity element declares an entity bean. The declaration consists of: an optional description; optional display name; optional small icon file name; optional large icon file name; a unique name assigned to the enterprise bean in the deployment descriptor; the names of the entity bean's home and remote interfaces, if any; the names of the entity bean's local home and local interface, if any; the entity bean's implementation class; the entity bean's persistence management type; the entity bean's primary key class name; an indication of the entity bean's reentrancy; an optional specification of the entity bean's cmp-version; an optional specification of the entity bean's abstract schema name; an optional list of container-managed fields; an optional specification of the primary key field; an optional declaration of the bean's environment entries; an optional declaration of the bean's EJB references; an optional declaration of the bean's local EJB references; an optional declaration of the security role references; an optional declaration of the security identity to be used for the execution of the bean's methods; an optional declaration of the bean's resource manager connection factory references; an optional declaration of the bean's resource environment references; an optional set of query declarations for finder and select methods for an entity bean with cmp-version 2.x.
 * The optional abstract-schema-name element must be specified for an entity bean with container managed persistence and cmp-version 2.x. The optional primkey-field may be present in the descriptor if the entity's persistence-type is Container. The optional cmp-version element may be present in the descriptor if the entity's persistence-type is Container. If the persistence-type is Container and the cmp-version element is not specified, its value defaults to 2.x.
 * The optional home and remote elements must be specified if the entity bean cmp-version is 1.x.
 * The optional local-home and local elements must be specified if the entity bean has a local home and local interface.
 * The optional query elements must be present if the persistence-type is Container and the cmp-version is 2.x and query methods other than findByPrimaryKey have been defined for the entity bean.
 * The other elements that are optional are "optional" in the sense that they are omitted if the lists represented by them are empty. At least one cmp-field element must be present in the descriptor if
 * the entity's persistence-type is Container and the cmp-version is 1.x, and none must not be present if the entity's persistence-type is Bean.
 */
public class EntityImpl extends EnterpriseBeanImpl implements Entity {

	/**
	 * The default value of the '{@link #isReentrant() <em>Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReentrant()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REENTRANT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReentrant() <em>Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReentrant()
	 * @generated
	 * @ordered
	 */
	protected boolean reentrant = REENTRANT_EDEFAULT;

	/**
	 * This is true if the Reentrant attribute has been set.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  protected boolean reentrantESet = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass primaryKey = null;
	public EntityImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.ENTITY;
	}

@Override
public String getPrimaryKeyName() {
	getPrimaryKey();
	return primaryKey == null ? null : primaryKey.getQualifiedName();
}
/**
 * Return true if @aJavaClass as equal to the
 * home, remote, bean class, or key class.
 */
@Override
public boolean hasJavaReference(JavaClass aJavaClass) {
	boolean result = super.hasJavaReference(aJavaClass);
	if (!result && aJavaClass != null)
		result = aJavaClass.equals(getPrimaryKey());
	return result;
}
//Return false by default
@Override
public boolean isContainerManagedEntity() {
	return false;
}
@Override
public boolean isEntity() {
	return true;
}
protected boolean primGetIsReentrant() {
   return isReentrant();
}
protected JavaClass primGetPrimaryKey() {
	return getPrimaryKey();
}

@Override
public java.lang.Object primRefValue(EObject feature) {
	switch (eDerivedStructuralFeatureID(((EStructuralFeature)feature).getFeatureID(), ((EStructuralFeature)feature).getContainerClass())) {
		case EjbPackage.ENTITY__REENTRANT: return new Boolean(isReentrant());
		case EjbPackage.ENTITY__PRIMARY_KEY: return getPrimaryKey();
	}
	return super.primRefValue(feature);
}
@Override
public void setPrimaryKeyName(String primaryKeyName) {
	eSet(EjbPackage.eINSTANCE.getEntity_PrimaryKey(), createClassRef(primaryKeyName));
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The reentrant element specifies whether an entity bean is reentrant or not.

	 */
	@Override
	public boolean isReentrant() {
		return reentrant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReentrant(boolean newReentrant) {
		boolean oldReentrant = reentrant;
		reentrant = newReentrant;
		boolean oldReentrantESet = reentrantESet;
		reentrantESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTITY__REENTRANT, oldReentrant, reentrant, !oldReentrantESet));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void unsetReentrant() {
		boolean oldReentrant = reentrant;
		boolean oldReentrantESet = reentrantESet;
		reentrant = REENTRANT_EDEFAULT;
		reentrantESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.ENTITY__REENTRANT, oldReentrant, REENTRANT_EDEFAULT, oldReentrantESet));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public boolean isSetReentrant() {
		return reentrantESet;
	}

    /**
     * Handwritten version of getPrimaryKey():
     */
 
 
    @Override
	public JavaClass getPrimaryKey()  { 
       if (primaryKey != null && primaryKey.eIsProxy()) { 
          JavaClass oldPrimaryKey = primaryKey; 
          JavaClass resolved = (JavaClass)EcoreUtil.resolve(primaryKey, this); 
          resolved(this.primaryKey, resolved);
          this.primaryKey = resolved;
          if (primaryKey != oldPrimaryKey) { 
             if (eNotificationRequired()) 
                eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ENTERPRISE_BEAN__EJB_CLASS, oldPrimaryKey, primaryKey)); 
          } 
       } 
       return primaryKey; 
    } 
 
         

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setPrimaryKey(JavaClass newPrimaryKey) {
		JavaClass oldPrimaryKey = primaryKey;
		primaryKey = newPrimaryKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTITY__PRIMARY_KEY, oldPrimaryKey, primaryKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.ENTITY__REENTRANT:
				return isReentrant() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.ENTITY__PRIMARY_KEY:
				if (resolve) return getPrimaryKey();
				return basicGetPrimaryKey();
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
			case EjbPackage.ENTITY__REENTRANT:
				setReentrant(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.ENTITY__PRIMARY_KEY:
				setPrimaryKey((JavaClass)newValue);
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
			case EjbPackage.ENTITY__REENTRANT:
				unsetReentrant();
				return;
			case EjbPackage.ENTITY__PRIMARY_KEY:
				setPrimaryKey((JavaClass)null);
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
			case EjbPackage.ENTITY__REENTRANT:
				return isSetReentrant();
			case EjbPackage.ENTITY__PRIMARY_KEY:
				return primaryKey != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (reentrant: "); //$NON-NLS-1$
		if (reentrantESet) result.append(reentrant); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
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
	public JavaClass getPrimaryKeyGen() {
		if (primaryKey != null && primaryKey.eIsProxy()) {
			InternalEObject oldPrimaryKey = (InternalEObject)primaryKey;
			primaryKey = (JavaClass)eResolveProxy(oldPrimaryKey);
			if (primaryKey != oldPrimaryKey) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ENTITY__PRIMARY_KEY, oldPrimaryKey, primaryKey));
			}
		}
		return primaryKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetPrimaryKey() {
		return primaryKey;
	}

}







