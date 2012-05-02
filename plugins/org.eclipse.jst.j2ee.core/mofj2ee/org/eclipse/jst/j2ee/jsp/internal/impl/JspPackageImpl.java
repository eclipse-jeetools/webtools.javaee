/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jsp.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEInit;
import org.eclipse.jst.j2ee.jsp.JSPConfig;
import org.eclipse.jst.j2ee.jsp.JSPPropertyGroup;
import org.eclipse.jst.j2ee.jsp.JspFactory;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.jsp.TagLibRefType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JspPackageImpl extends EPackageImpl implements JspPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jspConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jspPropertyGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagLibRefTypeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jst.j2ee.jsp.JspPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private JspPackageImpl() {
		super(eNS_URI, JspFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static JspPackage init() {
		if (isInited) return (JspPackage)EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI);

		// Obtain or create and register package
		JspPackageImpl theJspPackage = 
			(JspPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof
					JspPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new
							JspPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		JavaRefPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theJspPackage.createPackageContents();

		// Initialize created meta-data
		theJspPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJspPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(JspPackage.eNS_URI, theJspPackage);

		J2EEInit.initEMFModels();

		return theJspPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJSPConfig() {
		return jspConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJSPConfig_TagLibs() {
		return (EReference)jspConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJSPConfig_PropertyGroups() {
		return (EReference)jspConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJSPPropertyGroup() {
		return jspPropertyGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSPPropertyGroup_UrlPattern() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSPPropertyGroup_ElIgnored() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSPPropertyGroup_PageEncoding() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSPPropertyGroup_ScriptingInvalid() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSPPropertyGroup_IsXML() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSPPropertyGroup_IncludePreludes() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSPPropertyGroup_IncludeCodas() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTagLibRefType() {
		return tagLibRefTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagLibRefType_TaglibURI() {
		return (EAttribute)tagLibRefTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagLibRefType_TaglibLocation() {
		return (EAttribute)tagLibRefTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JspFactory getJspFactory() {
		return (JspFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		jspConfigEClass = createEClass(JSP_CONFIG);
		createEReference(jspConfigEClass, JSP_CONFIG__TAG_LIBS);
		createEReference(jspConfigEClass, JSP_CONFIG__PROPERTY_GROUPS);

		jspPropertyGroupEClass = createEClass(JSP_PROPERTY_GROUP);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__URL_PATTERN);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__EL_IGNORED);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__PAGE_ENCODING);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__SCRIPTING_INVALID);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__IS_XML);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__INCLUDE_PRELUDES);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__INCLUDE_CODAS);

		tagLibRefTypeEClass = createEClass(TAG_LIB_REF_TYPE);
		createEAttribute(tagLibRefTypeEClass, TAG_LIB_REF_TYPE__TAGLIB_URI);
		createEAttribute(tagLibRefTypeEClass, TAG_LIB_REF_TYPE__TAGLIB_LOCATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}		
		
		try{
			// Add supertypes to classes
			jspConfigEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			jspPropertyGroupEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());
			tagLibRefTypeEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
	
			// Initialize classes and features; add operations and parameters
			initEClass(jspConfigEClass, JSPConfig.class, "JSPConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getJSPConfig_TagLibs(), this.getTagLibRefType(), null, "tagLibs", null, 0, -1, JSPConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getJSPConfig_PropertyGroups(), this.getJSPPropertyGroup(), null, "propertyGroups", null, 0, -1, JSPConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(jspPropertyGroupEClass, JSPPropertyGroup.class, "JSPPropertyGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getJSPPropertyGroup_UrlPattern(), ecorePackage.getEString(), "urlPattern", null, 0, -1, JSPPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJSPPropertyGroup_ElIgnored(), ecorePackage.getEBoolean(), "elIgnored", "true", 0, 1, JSPPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
			initEAttribute(getJSPPropertyGroup_PageEncoding(), ecorePackage.getEString(), "pageEncoding", null, 0, 1, JSPPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJSPPropertyGroup_ScriptingInvalid(), ecorePackage.getEBoolean(), "scriptingInvalid", "true", 0, 1, JSPPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
			initEAttribute(getJSPPropertyGroup_IsXML(), ecorePackage.getEBoolean(), "isXML", "false", 0, 1, JSPPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
			initEAttribute(getJSPPropertyGroup_IncludePreludes(), ecorePackage.getEString(), "includePreludes", null, 0, -1, JSPPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJSPPropertyGroup_IncludeCodas(), ecorePackage.getEString(), "includeCodas", null, 0, -1, JSPPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(tagLibRefTypeEClass, TagLibRefType.class, "TagLibRefType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getTagLibRefType_TaglibURI(), ecorePackage.getEString(), "taglibURI", null, 0, 1, TagLibRefType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getTagLibRefType_TaglibLocation(), ecorePackage.getEString(), "taglibLocation", null, 0, 1, TagLibRefType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			// Create resource
			createResource(eNS_URI);
		}finally{
			if( hasLock )
				J2EEInit.releaseInitializePackageContentsLock();
		}
	}

	@Override
	public void freeze()
	{
		// since EClassImpl.freeze() does a clear() on all of the subClasses, we need to protect initializePackageContents() against it.
		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}
		finally {
			super.freeze();
			if( hasLock )
				J2EEInit.releaseInitializePackageContentsLock();
		}
	}

} //JspPackageImpl
