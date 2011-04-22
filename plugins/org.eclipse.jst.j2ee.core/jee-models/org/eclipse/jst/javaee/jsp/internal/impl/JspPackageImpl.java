/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jsp.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEInit;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;
import org.eclipse.jst.javaee.jsp.JspConfig;
import org.eclipse.jst.javaee.jsp.JspFactory;
import org.eclipse.jst.javaee.jsp.JspPropertyGroup;
import org.eclipse.jst.javaee.jsp.TagLib;

import org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage;

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
	private EClass tagLibEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType jspFileTypeEDataType = null;

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
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#eNS_URI
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
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link JspPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
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
		JspPackageImpl theJspPackage = (JspPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof JspPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new JspPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLNamespacePackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

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
	public EClass getJspConfig() {
		return jspConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJspConfig_TagLibs() {
		return (EReference)jspConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJspConfig_JspPropertyGroups() {
		return (EReference)jspConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspConfig_Id() {
		return (EAttribute)jspConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJspPropertyGroup() {
		return jspPropertyGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJspPropertyGroup_Descriptions() {
		return (EReference)jspPropertyGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJspPropertyGroup_DisplayNames() {
		return (EReference)jspPropertyGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJspPropertyGroup_Icons() {
		return (EReference)jspPropertyGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJspPropertyGroup_UrlPatterns() {
		return (EReference)jspPropertyGroupEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_ElIgnored() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_PageEncoding() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_ScriptingInvalid() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_IsXml() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_IncludePreludes() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_IncludeCodas() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_DeferredSyntaxAllowedAsLiteral() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_TrimDirectiveWhitespaces() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_DefaultContentType() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_Buffer() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_ErrorOnUndeclaredNamespace() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJspPropertyGroup_Id() {
		return (EAttribute)jspPropertyGroupEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTagLib() {
		return tagLibEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagLib_TaglibUri() {
		return (EAttribute)tagLibEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagLib_TaglibLocation() {
		return (EAttribute)tagLibEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagLib_Id() {
		return (EAttribute)tagLibEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getJspFileType() {
		return jspFileTypeEDataType;
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
	private boolean isCreated = false;

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
		createEReference(jspConfigEClass, JSP_CONFIG__JSP_PROPERTY_GROUPS);
		createEAttribute(jspConfigEClass, JSP_CONFIG__ID);

		jspPropertyGroupEClass = createEClass(JSP_PROPERTY_GROUP);
		createEReference(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__DESCRIPTIONS);
		createEReference(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__DISPLAY_NAMES);
		createEReference(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__ICONS);
		createEReference(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__URL_PATTERNS);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__EL_IGNORED);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__PAGE_ENCODING);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__SCRIPTING_INVALID);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__IS_XML);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__INCLUDE_PRELUDES);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__INCLUDE_CODAS);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__DEFAULT_CONTENT_TYPE);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__BUFFER);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__ERROR_ON_UNDECLARED_NAMESPACE);
		createEAttribute(jspPropertyGroupEClass, JSP_PROPERTY_GROUP__ID);

		tagLibEClass = createEClass(TAG_LIB);
		createEAttribute(tagLibEClass, TAG_LIB__TAGLIB_URI);
		createEAttribute(tagLibEClass, TAG_LIB__TAGLIB_LOCATION);
		createEAttribute(tagLibEClass, TAG_LIB__ID);

		// Create data types
		jspFileTypeEDataType = createEDataType(JSP_FILE_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

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
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
		JavaeePackage theJavaeePackage = (JavaeePackage)EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}		
		
		try{
			// Add supertypes to classes
	
			// Initialize classes and features; add operations and parameters
			initEClass(jspConfigEClass, JspConfig.class, "JspConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getJspConfig_TagLibs(), this.getTagLib(), null, "tagLibs", null, 0, -1, JspConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getJspConfig_JspPropertyGroups(), this.getJspPropertyGroup(), null, "jspPropertyGroups", null, 0, -1, JspConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspConfig_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, JspConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(jspPropertyGroupEClass, JspPropertyGroup.class, "JspPropertyGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getJspPropertyGroup_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getJspPropertyGroup_DisplayNames(), theJavaeePackage.getDisplayName(), null, "displayNames", null, 0, -1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getJspPropertyGroup_Icons(), theJavaeePackage.getIcon(), null, "icons", null, 0, -1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getJspPropertyGroup_UrlPatterns(), theJavaeePackage.getUrlPatternType(), null, "urlPatterns", null, 1, -1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_ElIgnored(), theJavaeePackage.getTrueFalseType(), "elIgnored", null, 0, 1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_PageEncoding(), theXMLTypePackage.getToken(), "pageEncoding", null, 0, 1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_ScriptingInvalid(), theJavaeePackage.getTrueFalseType(), "scriptingInvalid", null, 0, 1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_IsXml(), theJavaeePackage.getTrueFalseType(), "isXml", null, 0, 1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_IncludePreludes(), theJavaeePackage.getPathType(), "includePreludes", null, 0, -1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_IncludeCodas(), theJavaeePackage.getPathType(), "includeCodas", null, 0, -1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_DeferredSyntaxAllowedAsLiteral(), theJavaeePackage.getTrueFalseType(), "deferredSyntaxAllowedAsLiteral", null, 0, 1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_TrimDirectiveWhitespaces(), theJavaeePackage.getTrueFalseType(), "trimDirectiveWhitespaces", null, 0, 1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_DefaultContentType(), theXMLTypePackage.getToken(), "defaultContentType", null, 0, 1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_Buffer(), theXMLTypePackage.getToken(), "buffer", null, 0, 1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_ErrorOnUndeclaredNamespace(), theJavaeePackage.getTrueFalseType(), "errorOnUndeclaredNamespace", null, 0, 1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getJspPropertyGroup_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, JspPropertyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(tagLibEClass, TagLib.class, "TagLib", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getTagLib_TaglibUri(), theXMLTypePackage.getToken(), "taglibUri", null, 1, 1, TagLib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getTagLib_TaglibLocation(), theJavaeePackage.getPathType(), "taglibLocation", null, 1, 1, TagLib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getTagLib_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, TagLib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			// Initialize data types
			initEDataType(jspFileTypeEDataType, String.class, "JspFileType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
	
			// Create resource
			createResource(eNS_URI);
	
			// Create annotations
			// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
			createExtendedMetaDataAnnotations();
		}finally{
			if( hasLock )
				J2EEInit.releaseInitializePackageContentsLock();
		}
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$				
		addAnnotation
		  (jspConfigEClass, 
		   source, 
		   new String[] {
			 "name", "jsp-configType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getJspConfig_TagLibs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "taglib", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getJspConfig_JspPropertyGroups(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jsp-property-group", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getJspConfig_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (jspFileTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "jsp-fileType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://java.sun.com/xml/ns/javaee#pathType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (jspPropertyGroupEClass, 
		   source, 
		   new String[] {
			 "name", "jsp-property-groupType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getJspPropertyGroup_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getJspPropertyGroup_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getJspPropertyGroup_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getJspPropertyGroup_UrlPatterns(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "url-pattern", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_ElIgnored(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "el-ignored", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_PageEncoding(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "page-encoding", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_ScriptingInvalid(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "scripting-invalid", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_IsXml(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "is-xml", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_IncludePreludes(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "include-prelude", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_IncludeCodas(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "include-coda", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_DeferredSyntaxAllowedAsLiteral(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "deferred-syntax-allowed-as-literal", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_TrimDirectiveWhitespaces(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "trim-directive-whitespaces", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_DefaultContentType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "default-content-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_Buffer(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "buffer", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getJspPropertyGroup_ErrorOnUndeclaredNamespace(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "error-on-undeclared-namespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getJspPropertyGroup_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (tagLibEClass, 
		   source, 
		   new String[] {
			 "name", "taglibType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getTagLib_TaglibUri(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "taglib-uri", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getTagLib_TaglibLocation(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "taglib-location", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTagLib_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

} //JspPackageImpl
