/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java.internal.impl;

/*


 */
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EParameterImpl;
import org.eclipse.emf.ecore.util.*;

import org.eclipse.jem.internal.java.adapters.IJavaMethodAdapter;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.java.*;

/**
 * @generated
 */
public class JavaParameterImpl extends EParameterImpl implements JavaParameter {

	/**
	 * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINAL_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected static final int FINAL_EFLAG = 1 << 10;

	/**
	 * The default value of the '{@link #getParameterKind() <em>Parameter Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterKind()
	 * @generated
	 * @ordered
	 */
	protected static final JavaParameterKind PARAMETER_KIND_EDEFAULT = JavaParameterKind.IN_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaParameterKind parameterKind = PARAMETER_KIND_EDEFAULT;
	protected JavaParameterImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaRefPackage.Literals.JAVA_PARAMETER;
	}

	public JavaHelpers getJavaType() {
		return (JavaHelpers)getEType();
	}
  public String getQualifiedName() {
    return (eContainer() instanceof Method) ? ((Method)eContainer()).getName() + "." + this.getName() : this.getName();
  }
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaRefPackage.JAVA_PARAMETER__FINAL:
				return isFinal() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_PARAMETER__PARAMETER_KIND:
				return getParameterKind();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaRefPackage.JAVA_PARAMETER__FINAL:
				setFinal(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.JAVA_PARAMETER__PARAMETER_KIND:
				setParameterKind((JavaParameterKind)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case JavaRefPackage.JAVA_PARAMETER__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_PARAMETER__PARAMETER_KIND:
				setParameterKind(PARAMETER_KIND_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaRefPackage.JAVA_PARAMETER__FINAL:
				return ((eFlags & FINAL_EFLAG) != 0) != FINAL_EDEFAULT;
			case JavaRefPackage.JAVA_PARAMETER__PARAMETER_KIND:
				return parameterKind != PARAMETER_KIND_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * Is this parameter type an array type.
	 */
	public boolean isArray() {
		return getJavaType().isArray();
	}
	/**
	 * Is this a return parameter.
	 */
	public boolean isReturn() {
		return JavaParameterKind.RETURN == getParameterKind().getValue();
	}
	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public boolean isFinal() {
		return (eFlags & FINAL_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(boolean newFinal) {
		boolean oldFinal = (eFlags & FINAL_EFLAG) != 0;
		if (newFinal) eFlags |= FINAL_EFLAG; else eFlags &= ~FINAL_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.JAVA_PARAMETER__FINAL, oldFinal, newFinal));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaParameterKind getParameterKind() {
		return parameterKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterKind(JavaParameterKind newParameterKind) {
		JavaParameterKind oldParameterKind = parameterKind;
		parameterKind = newParameterKind == null ? PARAMETER_KIND_EDEFAULT : newParameterKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.JAVA_PARAMETER__PARAMETER_KIND, oldParameterKind, parameterKind));
	}

	/*
	 * This is not meant to be used outside of the reflection adapters.
	 */
	public synchronized ReadAdaptor getReadAdapter() {
		return (ReadAdaptor) EcoreUtil.getRegisteredAdapter(eContainer(), ReadAdaptor.TYPE_KEY);
	}
	
	private static final int  REFLECTED_BASE = 0x1, REFLECTED_PARAM_NAME = 0x2;

	protected int reflectionStatus = REFLECTED_BASE;  // At this time base reflection
													  // is performed at creation.

	protected void reflectParamName() {
		// We only want the testing of the hasReflected and get readadapter to be sync(this) so that
		// it is short and no deadlock possibility (this is because the the method reflection adapter may go
		// back to the containing java class to get its reflection adapter, which would lock on itself. So
		// we need to keep the sections that are sync(this) to not be deadlockable by not doing significant work
		// during the sync.
		ReadAdaptor readAdaptor = null;
		synchronized (this) {
			if ((reflectionStatus & REFLECTED_PARAM_NAME) == 0) {
				readAdaptor = getReadAdapter();
			}
		}
		if (readAdaptor != null) {
			boolean setReflected = ((IJavaMethodAdapter) readAdaptor).reflectParamNamesIfNecessary();
			synchronized (this) {
				// Don't want to set it false. That is job of reflection adapter. Otherwise we could have a race.
				// Normally we wouldn't need to set this because it would be set during the reflectParamNamesIfNecessary, but
				// in case there was a problem we mark it still reflected so we don't try again.
				if (setReflected)
					reflectionStatus |= (REFLECTED_BASE | REFLECTED_PARAM_NAME); // We can be certain base will be done by reflect generated if not already
																			  // done.
			}
		}
	}
	
	public String getName() {
		reflectParamName();
		return super.getName();
	}
	
	public void setName(String name) {
		super.setName(name);
		synchronized (this) {
		   reflectionStatus |= (REFLECTED_PARAM_NAME);
		}
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (final: ");
		result.append((eFlags & FINAL_EFLAG) != 0);
		result.append(", parameterKind: ");
		result.append(parameterKind);
		result.append(')');
		return result.toString();
	}
	@Override
	  public EList<EObject> eContents()
	  {
	    return EContentsEList.createEContentsEList(this);
	  }

	  @Override
	  public EList<EObject> eCrossReferences()
	  {
	    return ECrossReferenceEList.createECrossReferenceEList(this);
	  }

}





