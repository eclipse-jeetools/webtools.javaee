package org.eclipse.jem.java.impl;

/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: MethodImpl.java,v $
 *  $Revision: 1.5 $  $Date: 2004/08/17 22:02:45 $ 
 */

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EOperationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.java.Block;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.JavaVisibilityKind;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;

/**
 * @generated
 */
public class MethodImpl extends EOperationImpl implements Method {

	protected String signature;

	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #isNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NATIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNative()
	 * @generated
	 * @ordered
	 */
	protected boolean native_ = NATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynchronized()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYNCHRONIZED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynchronized()
	 * @generated
	 * @ordered
	 */
	protected boolean synchronized_ = SYNCHRONIZED_EDEFAULT;

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
	 * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected boolean final_ = FINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #isConstructor() <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstructor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONSTRUCTOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConstructor() <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstructor()
	 * @generated
	 * @ordered
	 */
	protected boolean constructor = CONSTRUCTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected boolean static_ = STATIC_EDEFAULT;

	/**
	 * The default value of the '{@link #getJavaVisibility() <em>Java Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final JavaVisibilityKind JAVA_VISIBILITY_EDEFAULT = JavaVisibilityKind.PUBLIC_LITERAL;

	private transient boolean isGenerated = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaVisibilityKind javaVisibility = JAVA_VISIBILITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList parameters = null;

	/**
	 * The cached value of the '{@link #getJavaExceptions() <em>Java Exceptions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaExceptions()
	 * @generated
	 * @ordered
	 */
	protected EList javaExceptions = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Block source = null;

	protected MethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaRefPackage.eINSTANCE.getMethod();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstractGen() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.METHOD__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNativeGen() {
		return native_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNative(boolean newNative) {
		boolean oldNative = native_;
		native_ = newNative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.METHOD__NATIVE, oldNative, native_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSynchronizedGen() {
		return synchronized_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynchronized(boolean newSynchronized) {
		boolean oldSynchronized = synchronized_;
		synchronized_ = newSynchronized;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.METHOD__SYNCHRONIZED, oldSynchronized, synchronized_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinalGen() {
		return final_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(boolean newFinal) {
		boolean oldFinal = final_;
		final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.METHOD__FINAL, oldFinal, final_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConstructorGen() {
		return constructor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructor(boolean newConstructor) {
		boolean oldConstructor = constructor;
		constructor = newConstructor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.METHOD__CONSTRUCTOR, oldConstructor, constructor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStaticGen() {
		return static_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(boolean newStatic) {
		boolean oldStatic = static_;
		static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.METHOD__STATIC, oldStatic, static_));
	}

	/**
	 * Return the java class that this method is defined in.
	 */
	public JavaClass getContainingJavaClass() {
		return this.getJavaClass();
	}

	/**
	 * Overrides to ensure reflection is done.
	 */
	public boolean isAbstract() {
		reflectValues();
		return isAbstractGen();
	}

	public boolean isConstructor() {
		reflectValues();
		return isConstructorGen();
	}

	public boolean isFinal() {
		reflectValues();
		return isFinalGen();
	}

	public boolean isNative() {
		reflectValues();
		return isNativeGen();
	}

	public boolean isStatic() {
		reflectValues();
		return isStaticGen();
	}

	public boolean isSynchronized() {
		reflectValues();
		return isSynchronizedGen();
	}

	public EList getJavaExceptions() {
		reflectValues();
		return getJavaExceptionsGen();
	}

	public JavaVisibilityKind getJavaVisibility() {
		reflectValues();
		return getJavaVisibilityGen();
	}

	public EList getParameters() {
		reflectValues();
		return getParametersGen();
	}

	/**
	 * @see org.eclipse.emf.ecore.ETypedElement#getEType()
	 */
	public EClassifier getEType() {
		reflectValues();
		return super.getEType();
	}

	/**
	 * Return a String with the the method name and its parameters. e.g. <code> setFirstName(java.lang.String) <//code> .
	 *
	 */
	public String getMethodElementSignature() {
		StringBuffer sb = new StringBuffer(75);
		sb.append(getName());
		sb.append("(");
		List params = getParameters();
		JavaParameter param;
		int parmSize = params.size();
		int commaTest = 0;
		for (int j = 0; j < parmSize; j++) {
			if (j > commaTest) {
				sb.append(",");
			}
			param = (JavaParameter) params.get(j);
			//FB if (param.isReturn()) {
			//FB commaTest ++;
			//FB continue;
			//FB }
			sb.append(((JavaHelpers) param.getEType()).getQualifiedName());
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Return a Parameter with the passed name, or null.
	 */
	public JavaParameter getParameter(String parameterName) {
		List parms = getParameters();
		JavaParameter parm;
		int parmSize = parms.size();
		for (int j = 0; j < parmSize; j++) {
			parm = (JavaParameter) parms.get(j);
			if (parm.getName().equals(parameterName))
				return parm;
		}
		return null;
	}

	protected boolean hasReflected = false;

	protected void reflectValues() {
		// We only want the testing of the hasReflected and get readadapter to be sync(this) so that
		// it is short and no deadlock possibility (this is because the the method reflection adapter may go
		// back to the containing java class to get its reflection adapter, which would lock on itself. So
		// we need to keep the sections that are sync(this) to not be deadlockable by not doing significant work
		// during the sync.
		ReadAdaptor readAdaptor = null;
		synchronized (this) {
			if (!hasReflected) {
				readAdaptor = getReadAdapter();
			}
		}
		if (readAdaptor != null) {
			boolean setReflected = readAdaptor.reflectValuesIfNecessary();
			synchronized (this) {
				// Don't want to set it false. That is job of reflection adapter. Otherwise we could have a race.
				if (setReflected)
					hasReflected = setReflected;
			}
		}
	}

	/*
	 * This is not meant to be used outside of the reflection adapters.
	 */
	public synchronized ReadAdaptor getReadAdapter() {
		return (ReadAdaptor) EcoreUtil.getRegisteredAdapter(this, ReadAdaptor.TYPE_KEY);
	}

	/*
	 * Used by reflection adapter to clear the reflection. This not intended to be used by others.
	 */
	public synchronized void setReflected(boolean reflected) {
		hasReflected = reflected;
	}

	/**
	 * Get the return type.
	 */
	public JavaHelpers getReturnType() {
		return (JavaHelpers) getEType();
	}

	public String getSignature() {
		if (signature == null)
			signature = doGetSignature();
		return signature;
	}

	/**
	 * Replicate the functionality of java.lang.reflect.Method.toString().
	 * 
	 * Returns a string describing this Method. The string is formatted as the method access modifiers, if any, followed by the method return type,
	 * followed by a space, followed by the class declaring the method, followed by a period, followed by the method name, followed by a
	 * parenthesized, comma-separated list of the method's formal parameter types. If the method throws checked exceptions, the parameter list is
	 * followed by a space, followed by the word throws followed by a comma-separated list of the thrown exception types.
	 * 
	 * For example:
	 * 
	 * public boolean java.lang.Object.equals(java.lang.Object)
	 * 
	 * The access modifiers are placed in canonical order as specified by "The Java Language Specification". This is public,
	 * <tt>protected<//tt> or <tt>private<//tt> first, and then other modifiers in the following order: <tt>abstract<//tt>, <tt>static<//tt>, <tt>final<//tt>, <tt>synchronized<//tt> <tt>native<//tt>.

	 */
	protected String doGetSignature() {
		StringBuffer sb = new StringBuffer();
		switch (getJavaVisibility().getValue()) {
			case JavaVisibilityKind.PUBLIC:
				sb.append("Public ");
				break;
			case JavaVisibilityKind.PROTECTED:
				sb.append("Protected ");
				break;
			case JavaVisibilityKind.PRIVATE:
				sb.append("Private ");
				break;
			case JavaVisibilityKind.PACKAGE:
				sb.append("Package ");
				break;
		}
		if (isAbstract())
			sb.append("abstract ");
		if (isStatic())
			sb.append("static ");
		if (isFinal())
			sb.append("final ");
		if (isSynchronized())
			sb.append("synchronized ");
		if (isNative())
			sb.append("native ");
		if (isVoid())
			sb.append("void ");
		else
			sb.append(((JavaHelpers) getReturnType()).getQualifiedName() + " ");
		sb.append(getContainingJavaClass().getJavaName() + ".");
		sb.append(getName() + "(");
		List params = getParameters();
		JavaParameter param;
		int parmSize = params.size();
		for (int j = 0; j < parmSize; j++) {
			param = (JavaParameter) params.get(j);
			//FB if (param.isReturn())
			//FB continue; // listParameters() includes return type in array
			sb.append(((JavaHelpers) param.getEType()).getQualifiedName());
			if (j < (params.size() - 1)) {
				sb.append(",");
			}
		}
		sb.append(")");
		List exceptions = getJavaExceptions();
		JavaClass exception;
		if (exceptions.size() > 0) {
			sb.append(" throws ");
			for (int k = 0; k < exceptions.size(); k++) {
				exception = (JavaClass) exceptions.get(k);
				sb.append(exception.getJavaName());
				if (k < (exceptions.size() - 1)) {
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Returns true if the method is system generated. This is usually determined by the "generated" tag in the comment.
	 */
	public boolean isGenerated() {
		reflectValues();
		return isGeneratedGen();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isGeneratedGen() {
		return isGenerated;
	}	

	/**
	 * Is this a void return type method.
	 */
	public boolean isVoid() {
		//FB return (getReturnParameter() == null || "void".equals(getReturnType().getName()));
		return (getReturnType() == null || "void".equals(getReturnType().getName()));
	}

	public JavaParameter[] listParametersWithoutReturn() {
		Collection v = getParameters();
		JavaParameter[] result = new JavaParameter[v.size()];
		v.toArray(result);
		return result;
	}

	public EList eContents() {
		EList results = new BasicEList();
		results.addAll(getParametersGen()); //FB
		return results;
	}

	/**
	 * Set the isGenerated flag.
	 */
	public void setIsGenerated(boolean generated) {
		isGenerated = generated;
	}

	/**
	 * Set the return type
	 */
	public void setReturnType(JavaHelpers type) {
		this.setEType(type);
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaVisibilityKind getJavaVisibilityGen() {
		return javaVisibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaVisibility(JavaVisibilityKind newJavaVisibility) {
		JavaVisibilityKind oldJavaVisibility = javaVisibility;
		javaVisibility = newJavaVisibility == null ? JAVA_VISIBILITY_EDEFAULT : newJavaVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.METHOD__JAVA_VISIBILITY, oldJavaVisibility, javaVisibility));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaClass getJavaClass() {
		if (eContainerFeatureID != JavaRefPackage.METHOD__JAVA_CLASS)
			return null;
		return (JavaClass) eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaClass(JavaClass newJavaClass) {
		if (newJavaClass != eContainer || (eContainerFeatureID != JavaRefPackage.METHOD__JAVA_CLASS && newJavaClass != null)) {
			if (EcoreUtil.isAncestor(this, newJavaClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newJavaClass != null)
				msgs = ((InternalEObject) newJavaClass).eInverseAdd(this, JavaRefPackage.JAVA_CLASS__METHODS, JavaClass.class, msgs);
			msgs = eBasicSetContainer((InternalEObject) newJavaClass, JavaRefPackage.METHOD__JAVA_CLASS, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.METHOD__JAVA_CLASS, newJavaClass, newJavaClass));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public Block getSource() {
		if (source != null && source.eIsProxy()) {
			Block oldSource = source;
			source = (Block) eResolveProxy((InternalEObject) source);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaRefPackage.METHOD__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Block newSource) {
		Block oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.METHOD__SOURCE, oldSource, source));
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.METHOD__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case JavaRefPackage.METHOD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaRefPackage.METHOD__ORDERED:
				return ordered != ORDERED_EDEFAULT;
			case JavaRefPackage.METHOD__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case JavaRefPackage.METHOD__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case JavaRefPackage.METHOD__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case JavaRefPackage.METHOD__MANY:
				return isMany() != false;
			case JavaRefPackage.METHOD__REQUIRED:
				return isRequired() != false;
			case JavaRefPackage.METHOD__ETYPE:
				return eType != null;
			case JavaRefPackage.METHOD__ECONTAINING_CLASS:
				return getEContainingClass() != null;
			case JavaRefPackage.METHOD__EPARAMETERS:
				return eParameters != null && !eParameters.isEmpty();
			case JavaRefPackage.METHOD__EEXCEPTIONS:
				return eExceptions != null && !eExceptions.isEmpty();
			case JavaRefPackage.METHOD__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case JavaRefPackage.METHOD__NATIVE:
				return native_ != NATIVE_EDEFAULT;
			case JavaRefPackage.METHOD__SYNCHRONIZED:
				return synchronized_ != SYNCHRONIZED_EDEFAULT;
			case JavaRefPackage.METHOD__FINAL:
				return final_ != FINAL_EDEFAULT;
			case JavaRefPackage.METHOD__CONSTRUCTOR:
				return constructor != CONSTRUCTOR_EDEFAULT;
			case JavaRefPackage.METHOD__STATIC:
				return static_ != STATIC_EDEFAULT;
			case JavaRefPackage.METHOD__JAVA_VISIBILITY:
				return javaVisibility != JAVA_VISIBILITY_EDEFAULT;
			case JavaRefPackage.METHOD__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case JavaRefPackage.METHOD__JAVA_EXCEPTIONS:
				return javaExceptions != null && !javaExceptions.isEmpty();
			case JavaRefPackage.METHOD__JAVA_CLASS:
				return getJavaClass() != null;
			case JavaRefPackage.METHOD__SOURCE:
				return source != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.METHOD__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection) newValue);
				return;
			case JavaRefPackage.METHOD__NAME:
				setName((String) newValue);
				return;
			case JavaRefPackage.METHOD__ORDERED:
				setOrdered(((Boolean) newValue).booleanValue());
				return;
			case JavaRefPackage.METHOD__UNIQUE:
				setUnique(((Boolean) newValue).booleanValue());
				return;
			case JavaRefPackage.METHOD__LOWER_BOUND:
				setLowerBound(((Integer) newValue).intValue());
				return;
			case JavaRefPackage.METHOD__UPPER_BOUND:
				setUpperBound(((Integer) newValue).intValue());
				return;
			case JavaRefPackage.METHOD__ETYPE:
				setEType((EClassifier) newValue);
				return;
			case JavaRefPackage.METHOD__EPARAMETERS:
				getEParameters().clear();
				getEParameters().addAll((Collection) newValue);
				return;
			case JavaRefPackage.METHOD__EEXCEPTIONS:
				getEExceptions().clear();
				getEExceptions().addAll((Collection) newValue);
				return;
			case JavaRefPackage.METHOD__ABSTRACT:
				setAbstract(((Boolean) newValue).booleanValue());
				return;
			case JavaRefPackage.METHOD__NATIVE:
				setNative(((Boolean) newValue).booleanValue());
				return;
			case JavaRefPackage.METHOD__SYNCHRONIZED:
				setSynchronized(((Boolean) newValue).booleanValue());
				return;
			case JavaRefPackage.METHOD__FINAL:
				setFinal(((Boolean) newValue).booleanValue());
				return;
			case JavaRefPackage.METHOD__CONSTRUCTOR:
				setConstructor(((Boolean) newValue).booleanValue());
				return;
			case JavaRefPackage.METHOD__STATIC:
				setStatic(((Boolean) newValue).booleanValue());
				return;
			case JavaRefPackage.METHOD__JAVA_VISIBILITY:
				setJavaVisibility((JavaVisibilityKind) newValue);
				return;
			case JavaRefPackage.METHOD__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection) newValue);
				return;
			case JavaRefPackage.METHOD__JAVA_EXCEPTIONS:
				getJavaExceptions().clear();
				getJavaExceptions().addAll((Collection) newValue);
				return;
			case JavaRefPackage.METHOD__JAVA_CLASS:
				setJavaClass((JavaClass) newValue);
				return;
			case JavaRefPackage.METHOD__SOURCE:
				setSource((Block) newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.METHOD__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case JavaRefPackage.METHOD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__ETYPE:
				setEType((EClassifier) null);
				return;
			case JavaRefPackage.METHOD__EPARAMETERS:
				getEParameters().clear();
				return;
			case JavaRefPackage.METHOD__EEXCEPTIONS:
				getEExceptions().clear();
				return;
			case JavaRefPackage.METHOD__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__NATIVE:
				setNative(NATIVE_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__SYNCHRONIZED:
				setSynchronized(SYNCHRONIZED_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__CONSTRUCTOR:
				setConstructor(CONSTRUCTOR_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__STATIC:
				setStatic(STATIC_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__JAVA_VISIBILITY:
				setJavaVisibility(JAVA_VISIBILITY_EDEFAULT);
				return;
			case JavaRefPackage.METHOD__PARAMETERS:
				getParameters().clear();
				return;
			case JavaRefPackage.METHOD__JAVA_EXCEPTIONS:
				getJavaExceptions().clear();
				return;
			case JavaRefPackage.METHOD__JAVA_CLASS:
				setJavaClass((JavaClass) null);
				return;
			case JavaRefPackage.METHOD__SOURCE:
				setSource((Block) null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (abstract: ");
		result.append(abstract_);
		result.append(", native: ");
		result.append(native_);
		result.append(", synchronized: ");
		result.append(synchronized_);
		result.append(", final: ");
		result.append(final_);
		result.append(", constructor: ");
		result.append(constructor);
		result.append(", static: ");
		result.append(static_);
		result.append(", javaVisibility: ");
		result.append(javaVisibility);
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getParametersGen() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList(JavaParameter.class, this, JavaRefPackage.METHOD__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getJavaExceptionsGen() {
		if (javaExceptions == null) {
			javaExceptions = new EObjectResolvingEList(JavaClass.class, this, JavaRefPackage.METHOD__JAVA_EXCEPTIONS);
		}
		return javaExceptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.METHOD__EANNOTATIONS:
					return ((InternalEList) getEAnnotations()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.METHOD__ECONTAINING_CLASS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, JavaRefPackage.METHOD__ECONTAINING_CLASS, msgs);
				case JavaRefPackage.METHOD__EPARAMETERS:
					return ((InternalEList) getEParameters()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.METHOD__JAVA_CLASS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, JavaRefPackage.METHOD__JAVA_CLASS, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.METHOD__EANNOTATIONS:
					return ((InternalEList) getEAnnotations()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.METHOD__ECONTAINING_CLASS:
					return eBasicSetContainer(null, JavaRefPackage.METHOD__ECONTAINING_CLASS, msgs);
				case JavaRefPackage.METHOD__EPARAMETERS:
					return ((InternalEList) getEParameters()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.METHOD__PARAMETERS:
					return ((InternalEList) getParameters()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.METHOD__JAVA_CLASS:
					return eBasicSetContainer(null, JavaRefPackage.METHOD__JAVA_CLASS, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case JavaRefPackage.METHOD__ECONTAINING_CLASS:
					return ((InternalEObject) eContainer).eInverseRemove(this, EcorePackage.ECLASS__EOPERATIONS, EClass.class, msgs);
				case JavaRefPackage.METHOD__JAVA_CLASS:
					return ((InternalEObject) eContainer).eInverseRemove(this, JavaRefPackage.JAVA_CLASS__METHODS, JavaClass.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject) eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.METHOD__EANNOTATIONS:
				return getEAnnotations();
			case JavaRefPackage.METHOD__NAME:
				return getName();
			case JavaRefPackage.METHOD__ORDERED:
				return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.METHOD__UNIQUE:
				return isUnique() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.METHOD__LOWER_BOUND:
				return new Integer(getLowerBound());
			case JavaRefPackage.METHOD__UPPER_BOUND:
				return new Integer(getUpperBound());
			case JavaRefPackage.METHOD__MANY:
				return isMany() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.METHOD__REQUIRED:
				return isRequired() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.METHOD__ETYPE:
				if (resolve)
					return getEType();
				return basicGetEType();
			case JavaRefPackage.METHOD__ECONTAINING_CLASS:
				return getEContainingClass();
			case JavaRefPackage.METHOD__EPARAMETERS:
				return getEParameters();
			case JavaRefPackage.METHOD__EEXCEPTIONS:
				return getEExceptions();
			case JavaRefPackage.METHOD__ABSTRACT:
				return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.METHOD__NATIVE:
				return isNative() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.METHOD__SYNCHRONIZED:
				return isSynchronized() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.METHOD__FINAL:
				return isFinal() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.METHOD__CONSTRUCTOR:
				return isConstructor() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.METHOD__STATIC:
				return isStatic() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.METHOD__JAVA_VISIBILITY:
				return getJavaVisibility();
			case JavaRefPackage.METHOD__PARAMETERS:
				return getParameters();
			case JavaRefPackage.METHOD__JAVA_EXCEPTIONS:
				return getJavaExceptions();
			case JavaRefPackage.METHOD__JAVA_CLASS:
				return getJavaClass();
			case JavaRefPackage.METHOD__SOURCE:
				if (resolve)
					return getSource();
				return basicGetSource();
		}
		return eDynamicGet(eFeature, resolve);
	}

}

