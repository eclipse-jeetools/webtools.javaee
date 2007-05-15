/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebPackageImpl.java,v 1.2 2007/05/15 23:17:38 cbridgha Exp $
 */
package org.eclipse.jst.javaee.web.internal.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl;

import org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage;

import org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationclientPackageImpl;

import org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage;

import org.eclipse.jst.javaee.core.internal.impl.JavaeePackageImpl;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

import org.eclipse.jst.javaee.ejb.internal.impl.EjbPackageImpl;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

import org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl;

import org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage;

import org.eclipse.jst.javaee.web.AuthConstraint;
import org.eclipse.jst.javaee.web.DispatcherType;
import org.eclipse.jst.javaee.web.ErrorPage;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.FilterMapping;
import org.eclipse.jst.javaee.web.FormLoginConfig;
import org.eclipse.jst.javaee.web.LocaleEncodingMapping;
import org.eclipse.jst.javaee.web.LocaleEncodingMappingList;
import org.eclipse.jst.javaee.web.LoginConfig;
import org.eclipse.jst.javaee.web.MimeMapping;
import org.eclipse.jst.javaee.web.NullCharType;
import org.eclipse.jst.javaee.web.SecurityConstraint;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.javaee.web.SessionConfig;
import org.eclipse.jst.javaee.web.TransportGuaranteeType;
import org.eclipse.jst.javaee.web.UserDataConstraint;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor;
import org.eclipse.jst.javaee.web.WebAppVersionType;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.javaee.web.WebResourceCollection;
import org.eclipse.jst.javaee.web.WelcomeFileList;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

import org.eclipse.jst.javaee.web.internal.util.WebValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WebPackageImpl extends EPackageImpl implements WebPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass authConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorPageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filterMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formLoginConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass localeEncodingMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass localeEncodingMappingListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass loginConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mimeMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass servletEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass servletMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sessionConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userDataConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webAppEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webAppDeploymentDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webResourceCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass welcomeFileListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dispatcherTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum nullCharTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum transportGuaranteeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum webAppVersionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType authMethodTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType dispatcherTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType encodingTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType errorCodeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType filterNameTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType httpMethodTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType loadOnStartupTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType localeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType mimeTypeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType nonEmptyStringTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType nullCharTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType servletNameTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType transportGuaranteeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType warPathTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType webAppVersionTypeObjectEDataType = null;

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private WebPackageImpl() {
		super(eNS_URI, WebFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

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
	public static WebPackage init() {
		if (isInited) return (WebPackage)EPackage.Registry.INSTANCE.getEPackage(WebPackage.eNS_URI);

		// Obtain or create and register package
		WebPackageImpl theWebPackage = (WebPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof WebPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new WebPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLNamespacePackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		JavaeePackageImpl theJavaeePackage = (JavaeePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI) instanceof JavaeePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI) : JavaeePackage.eINSTANCE);
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) instanceof EjbPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) : EjbPackage.eINSTANCE);
		ApplicationclientPackageImpl theApplicationclientPackage = (ApplicationclientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationclientPackage.eNS_URI) instanceof ApplicationclientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationclientPackage.eNS_URI) : ApplicationclientPackage.eINSTANCE);
		JspPackageImpl theJspPackage = (JspPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) instanceof JspPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) : JspPackage.eINSTANCE);

		// Create package meta-data objects
		theWebPackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theJavaeePackage.createPackageContents();
		theEjbPackage.createPackageContents();
		theApplicationclientPackage.createPackageContents();
		theJspPackage.createPackageContents();

		// Initialize created meta-data
		theWebPackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theJavaeePackage.initializePackageContents();
		theEjbPackage.initializePackageContents();
		theApplicationclientPackage.initializePackageContents();
		theJspPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theWebPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return WebValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theWebPackage.freeze();

		return theWebPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAuthConstraint() {
		return authConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAuthConstraint_Descriptions() {
		return (EReference)authConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthConstraint_RoleNames() {
		return (EAttribute)authConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthConstraint_Id() {
		return (EAttribute)authConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getErrorPage() {
		return errorPageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getErrorPage_ErrorCode() {
		return (EAttribute)errorPageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getErrorPage_ExceptionType() {
		return (EAttribute)errorPageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getErrorPage_Location() {
		return (EAttribute)errorPageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getErrorPage_Id() {
		return (EAttribute)errorPageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilter() {
		return filterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilter_Descriptions() {
		return (EReference)filterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilter_DisplayNames() {
		return (EReference)filterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilter_Icons() {
		return (EReference)filterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilter_FilterName() {
		return (EAttribute)filterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilter_FilterClass() {
		return (EAttribute)filterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilter_InitParams() {
		return (EReference)filterEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilter_Id() {
		return (EAttribute)filterEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilterMapping() {
		return filterMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilterMapping_FilterName() {
		return (EAttribute)filterMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilterMapping_Group() {
		return (EAttribute)filterMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilterMapping_UrlPatterns() {
		return (EReference)filterMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilterMapping_ServletNames() {
		return (EAttribute)filterMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilterMapping_Dispatchers() {
		return (EAttribute)filterMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilterMapping_Id() {
		return (EAttribute)filterMappingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormLoginConfig() {
		return formLoginConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormLoginConfig_FormLoginPage() {
		return (EAttribute)formLoginConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormLoginConfig_FormErrorPage() {
		return (EAttribute)formLoginConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormLoginConfig_Id() {
		return (EAttribute)formLoginConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocaleEncodingMapping() {
		return localeEncodingMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocaleEncodingMapping_Locale() {
		return (EAttribute)localeEncodingMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocaleEncodingMapping_Encoding() {
		return (EAttribute)localeEncodingMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocaleEncodingMapping_Id() {
		return (EAttribute)localeEncodingMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocaleEncodingMappingList() {
		return localeEncodingMappingListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLocaleEncodingMappingList_LocalEncodingMappings() {
		return (EReference)localeEncodingMappingListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocaleEncodingMappingList_Id() {
		return (EAttribute)localeEncodingMappingListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLoginConfig() {
		return loginConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLoginConfig_AuthMethod() {
		return (EAttribute)loginConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLoginConfig_RealmName() {
		return (EAttribute)loginConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLoginConfig_FormLoginConfig() {
		return (EReference)loginConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLoginConfig_Id() {
		return (EAttribute)loginConfigEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMimeMapping() {
		return mimeMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMimeMapping_Extension() {
		return (EAttribute)mimeMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMimeMapping_MimeType() {
		return (EAttribute)mimeMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMimeMapping_Id() {
		return (EAttribute)mimeMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecurityConstraint() {
		return securityConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityConstraint_DisplayNames() {
		return (EReference)securityConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityConstraint_WebResourceCollections() {
		return (EReference)securityConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityConstraint_AuthConstraint() {
		return (EReference)securityConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityConstraint_UserDataConstraint() {
		return (EReference)securityConstraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityConstraint_Id() {
		return (EAttribute)securityConstraintEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServlet() {
		return servletEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServlet_Descriptions() {
		return (EReference)servletEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServlet_DisplayNames() {
		return (EReference)servletEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServlet_Icons() {
		return (EReference)servletEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServlet_ServletName() {
		return (EAttribute)servletEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServlet_ServletClass() {
		return (EAttribute)servletEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServlet_JspFile() {
		return (EAttribute)servletEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServlet_InitParams() {
		return (EReference)servletEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServlet_LoadOnStartup() {
		return (EAttribute)servletEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServlet_RunAs() {
		return (EReference)servletEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServlet_SecurityRoleRefs() {
		return (EReference)servletEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServlet_Id() {
		return (EAttribute)servletEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServletMapping() {
		return servletMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServletMapping_ServletName() {
		return (EAttribute)servletMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServletMapping_UrlPatterns() {
		return (EReference)servletMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServletMapping_Id() {
		return (EAttribute)servletMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSessionConfig() {
		return sessionConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionConfig_SessionTimeout() {
		return (EAttribute)sessionConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionConfig_Id() {
		return (EAttribute)sessionConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserDataConstraint() {
		return userDataConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserDataConstraint_Descriptions() {
		return (EReference)userDataConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserDataConstraint_TransportGuarantee() {
		return (EAttribute)userDataConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserDataConstraint_Id() {
		return (EAttribute)userDataConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWebApp() {
		return webAppEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebApp_Group() {
		return (EAttribute)webAppEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_Descriptions() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_DisplayNames() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_Icons() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_Distributables() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_ContextParams() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_Filters() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_FilterMappings() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_Listeners() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_Servlets() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_ServletMappings() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_SessionConfigs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_MimeMappings() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_WelcomeFileLists() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_ErrorPages() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_JspConfigs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_SecurityConstraints() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_LoginConfigs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_SecurityRoles() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_EnvEntries() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_EjbRefs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_EjbLocalRefs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_ServiceRefs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_ResourceRefs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_ResourceEnvRefs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_MessageDestinationRefs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_PersistenceContextRefs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_PersistenceUnitRefs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_PostConstructs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_PreDestroys() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_MessageDestinations() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_LocalEncodingMappingsLists() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebApp_Id() {
		return (EAttribute)webAppEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebApp_MetadataComplete() {
		return (EAttribute)webAppEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebApp_Version() {
		return (EAttribute)webAppEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWebAppDeploymentDescriptor() {
		return webAppDeploymentDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebAppDeploymentDescriptor_Mixed() {
		return (EAttribute)webAppDeploymentDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebAppDeploymentDescriptor_XMLNSPrefixMap() {
		return (EReference)webAppDeploymentDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebAppDeploymentDescriptor_XSISchemaLocation() {
		return (EReference)webAppDeploymentDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebAppDeploymentDescriptor_WebApp() {
		return (EReference)webAppDeploymentDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWebResourceCollection() {
		return webResourceCollectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebResourceCollection_WebResourceName() {
		return (EAttribute)webResourceCollectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebResourceCollection_Descriptions() {
		return (EReference)webResourceCollectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebResourceCollection_UrlPatterns() {
		return (EReference)webResourceCollectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebResourceCollection_HttpMethods() {
		return (EAttribute)webResourceCollectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebResourceCollection_Id() {
		return (EAttribute)webResourceCollectionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWelcomeFileList() {
		return welcomeFileListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWelcomeFileList_WelcomeFiles() {
		return (EAttribute)welcomeFileListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWelcomeFileList_Id() {
		return (EAttribute)welcomeFileListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDispatcherType() {
		return dispatcherTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getNullCharType() {
		return nullCharTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTransportGuaranteeType() {
		return transportGuaranteeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getWebAppVersionType() {
		return webAppVersionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getAuthMethodType() {
		return authMethodTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDispatcherTypeObject() {
		return dispatcherTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEncodingType() {
		return encodingTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getErrorCodeType() {
		return errorCodeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getFilterNameType() {
		return filterNameTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHttpMethodType() {
		return httpMethodTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLoadOnStartupType() {
		return loadOnStartupTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLocaleType() {
		return localeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getMimeTypeType() {
		return mimeTypeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNonEmptyStringType() {
		return nonEmptyStringTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNullCharTypeObject() {
		return nullCharTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getServletNameType() {
		return servletNameTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTransportGuaranteeTypeObject() {
		return transportGuaranteeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWarPathType() {
		return warPathTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWebAppVersionTypeObject() {
		return webAppVersionTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebFactory getWebFactory() {
		return (WebFactory)getEFactoryInstance();
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
		authConstraintEClass = createEClass(AUTH_CONSTRAINT);
		createEReference(authConstraintEClass, AUTH_CONSTRAINT__DESCRIPTIONS);
		createEAttribute(authConstraintEClass, AUTH_CONSTRAINT__ROLE_NAMES);
		createEAttribute(authConstraintEClass, AUTH_CONSTRAINT__ID);

		errorPageEClass = createEClass(ERROR_PAGE);
		createEAttribute(errorPageEClass, ERROR_PAGE__ERROR_CODE);
		createEAttribute(errorPageEClass, ERROR_PAGE__EXCEPTION_TYPE);
		createEAttribute(errorPageEClass, ERROR_PAGE__LOCATION);
		createEAttribute(errorPageEClass, ERROR_PAGE__ID);

		filterEClass = createEClass(FILTER);
		createEReference(filterEClass, FILTER__DESCRIPTIONS);
		createEReference(filterEClass, FILTER__DISPLAY_NAMES);
		createEReference(filterEClass, FILTER__ICONS);
		createEAttribute(filterEClass, FILTER__FILTER_NAME);
		createEAttribute(filterEClass, FILTER__FILTER_CLASS);
		createEReference(filterEClass, FILTER__INIT_PARAMS);
		createEAttribute(filterEClass, FILTER__ID);

		filterMappingEClass = createEClass(FILTER_MAPPING);
		createEAttribute(filterMappingEClass, FILTER_MAPPING__FILTER_NAME);
		createEAttribute(filterMappingEClass, FILTER_MAPPING__GROUP);
		createEReference(filterMappingEClass, FILTER_MAPPING__URL_PATTERNS);
		createEAttribute(filterMappingEClass, FILTER_MAPPING__SERVLET_NAMES);
		createEAttribute(filterMappingEClass, FILTER_MAPPING__DISPATCHERS);
		createEAttribute(filterMappingEClass, FILTER_MAPPING__ID);

		formLoginConfigEClass = createEClass(FORM_LOGIN_CONFIG);
		createEAttribute(formLoginConfigEClass, FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE);
		createEAttribute(formLoginConfigEClass, FORM_LOGIN_CONFIG__FORM_ERROR_PAGE);
		createEAttribute(formLoginConfigEClass, FORM_LOGIN_CONFIG__ID);

		localeEncodingMappingEClass = createEClass(LOCALE_ENCODING_MAPPING);
		createEAttribute(localeEncodingMappingEClass, LOCALE_ENCODING_MAPPING__LOCALE);
		createEAttribute(localeEncodingMappingEClass, LOCALE_ENCODING_MAPPING__ENCODING);
		createEAttribute(localeEncodingMappingEClass, LOCALE_ENCODING_MAPPING__ID);

		localeEncodingMappingListEClass = createEClass(LOCALE_ENCODING_MAPPING_LIST);
		createEReference(localeEncodingMappingListEClass, LOCALE_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS);
		createEAttribute(localeEncodingMappingListEClass, LOCALE_ENCODING_MAPPING_LIST__ID);

		loginConfigEClass = createEClass(LOGIN_CONFIG);
		createEAttribute(loginConfigEClass, LOGIN_CONFIG__AUTH_METHOD);
		createEAttribute(loginConfigEClass, LOGIN_CONFIG__REALM_NAME);
		createEReference(loginConfigEClass, LOGIN_CONFIG__FORM_LOGIN_CONFIG);
		createEAttribute(loginConfigEClass, LOGIN_CONFIG__ID);

		mimeMappingEClass = createEClass(MIME_MAPPING);
		createEAttribute(mimeMappingEClass, MIME_MAPPING__EXTENSION);
		createEAttribute(mimeMappingEClass, MIME_MAPPING__MIME_TYPE);
		createEAttribute(mimeMappingEClass, MIME_MAPPING__ID);

		securityConstraintEClass = createEClass(SECURITY_CONSTRAINT);
		createEReference(securityConstraintEClass, SECURITY_CONSTRAINT__DISPLAY_NAMES);
		createEReference(securityConstraintEClass, SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS);
		createEReference(securityConstraintEClass, SECURITY_CONSTRAINT__AUTH_CONSTRAINT);
		createEReference(securityConstraintEClass, SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT);
		createEAttribute(securityConstraintEClass, SECURITY_CONSTRAINT__ID);

		servletEClass = createEClass(SERVLET);
		createEReference(servletEClass, SERVLET__DESCRIPTIONS);
		createEReference(servletEClass, SERVLET__DISPLAY_NAMES);
		createEReference(servletEClass, SERVLET__ICONS);
		createEAttribute(servletEClass, SERVLET__SERVLET_NAME);
		createEAttribute(servletEClass, SERVLET__SERVLET_CLASS);
		createEAttribute(servletEClass, SERVLET__JSP_FILE);
		createEReference(servletEClass, SERVLET__INIT_PARAMS);
		createEAttribute(servletEClass, SERVLET__LOAD_ON_STARTUP);
		createEReference(servletEClass, SERVLET__RUN_AS);
		createEReference(servletEClass, SERVLET__SECURITY_ROLE_REFS);
		createEAttribute(servletEClass, SERVLET__ID);

		servletMappingEClass = createEClass(SERVLET_MAPPING);
		createEAttribute(servletMappingEClass, SERVLET_MAPPING__SERVLET_NAME);
		createEReference(servletMappingEClass, SERVLET_MAPPING__URL_PATTERNS);
		createEAttribute(servletMappingEClass, SERVLET_MAPPING__ID);

		sessionConfigEClass = createEClass(SESSION_CONFIG);
		createEAttribute(sessionConfigEClass, SESSION_CONFIG__SESSION_TIMEOUT);
		createEAttribute(sessionConfigEClass, SESSION_CONFIG__ID);

		userDataConstraintEClass = createEClass(USER_DATA_CONSTRAINT);
		createEReference(userDataConstraintEClass, USER_DATA_CONSTRAINT__DESCRIPTIONS);
		createEAttribute(userDataConstraintEClass, USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE);
		createEAttribute(userDataConstraintEClass, USER_DATA_CONSTRAINT__ID);

		webAppEClass = createEClass(WEB_APP);
		createEAttribute(webAppEClass, WEB_APP__GROUP);
		createEReference(webAppEClass, WEB_APP__DESCRIPTIONS);
		createEReference(webAppEClass, WEB_APP__DISPLAY_NAMES);
		createEReference(webAppEClass, WEB_APP__ICONS);
		createEReference(webAppEClass, WEB_APP__DISTRIBUTABLES);
		createEReference(webAppEClass, WEB_APP__CONTEXT_PARAMS);
		createEReference(webAppEClass, WEB_APP__FILTERS);
		createEReference(webAppEClass, WEB_APP__FILTER_MAPPINGS);
		createEReference(webAppEClass, WEB_APP__LISTENERS);
		createEReference(webAppEClass, WEB_APP__SERVLETS);
		createEReference(webAppEClass, WEB_APP__SERVLET_MAPPINGS);
		createEReference(webAppEClass, WEB_APP__SESSION_CONFIGS);
		createEReference(webAppEClass, WEB_APP__MIME_MAPPINGS);
		createEReference(webAppEClass, WEB_APP__WELCOME_FILE_LISTS);
		createEReference(webAppEClass, WEB_APP__ERROR_PAGES);
		createEReference(webAppEClass, WEB_APP__JSP_CONFIGS);
		createEReference(webAppEClass, WEB_APP__SECURITY_CONSTRAINTS);
		createEReference(webAppEClass, WEB_APP__LOGIN_CONFIGS);
		createEReference(webAppEClass, WEB_APP__SECURITY_ROLES);
		createEReference(webAppEClass, WEB_APP__ENV_ENTRIES);
		createEReference(webAppEClass, WEB_APP__EJB_REFS);
		createEReference(webAppEClass, WEB_APP__EJB_LOCAL_REFS);
		createEReference(webAppEClass, WEB_APP__SERVICE_REFS);
		createEReference(webAppEClass, WEB_APP__RESOURCE_REFS);
		createEReference(webAppEClass, WEB_APP__RESOURCE_ENV_REFS);
		createEReference(webAppEClass, WEB_APP__MESSAGE_DESTINATION_REFS);
		createEReference(webAppEClass, WEB_APP__PERSISTENCE_CONTEXT_REFS);
		createEReference(webAppEClass, WEB_APP__PERSISTENCE_UNIT_REFS);
		createEReference(webAppEClass, WEB_APP__POST_CONSTRUCTS);
		createEReference(webAppEClass, WEB_APP__PRE_DESTROYS);
		createEReference(webAppEClass, WEB_APP__MESSAGE_DESTINATIONS);
		createEReference(webAppEClass, WEB_APP__LOCAL_ENCODING_MAPPINGS_LISTS);
		createEAttribute(webAppEClass, WEB_APP__ID);
		createEAttribute(webAppEClass, WEB_APP__METADATA_COMPLETE);
		createEAttribute(webAppEClass, WEB_APP__VERSION);

		webAppDeploymentDescriptorEClass = createEClass(WEB_APP_DEPLOYMENT_DESCRIPTOR);
		createEAttribute(webAppDeploymentDescriptorEClass, WEB_APP_DEPLOYMENT_DESCRIPTOR__MIXED);
		createEReference(webAppDeploymentDescriptorEClass, WEB_APP_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP);
		createEReference(webAppDeploymentDescriptorEClass, WEB_APP_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION);
		createEReference(webAppDeploymentDescriptorEClass, WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_APP);

		webResourceCollectionEClass = createEClass(WEB_RESOURCE_COLLECTION);
		createEAttribute(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME);
		createEReference(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__DESCRIPTIONS);
		createEReference(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__URL_PATTERNS);
		createEAttribute(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__HTTP_METHODS);
		createEAttribute(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__ID);

		welcomeFileListEClass = createEClass(WELCOME_FILE_LIST);
		createEAttribute(welcomeFileListEClass, WELCOME_FILE_LIST__WELCOME_FILES);
		createEAttribute(welcomeFileListEClass, WELCOME_FILE_LIST__ID);

		// Create enums
		dispatcherTypeEEnum = createEEnum(DISPATCHER_TYPE);
		nullCharTypeEEnum = createEEnum(NULL_CHAR_TYPE);
		transportGuaranteeTypeEEnum = createEEnum(TRANSPORT_GUARANTEE_TYPE);
		webAppVersionTypeEEnum = createEEnum(WEB_APP_VERSION_TYPE);

		// Create data types
		authMethodTypeEDataType = createEDataType(AUTH_METHOD_TYPE);
		dispatcherTypeObjectEDataType = createEDataType(DISPATCHER_TYPE_OBJECT);
		encodingTypeEDataType = createEDataType(ENCODING_TYPE);
		errorCodeTypeEDataType = createEDataType(ERROR_CODE_TYPE);
		filterNameTypeEDataType = createEDataType(FILTER_NAME_TYPE);
		httpMethodTypeEDataType = createEDataType(HTTP_METHOD_TYPE);
		loadOnStartupTypeEDataType = createEDataType(LOAD_ON_STARTUP_TYPE);
		localeTypeEDataType = createEDataType(LOCALE_TYPE);
		mimeTypeTypeEDataType = createEDataType(MIME_TYPE_TYPE);
		nonEmptyStringTypeEDataType = createEDataType(NON_EMPTY_STRING_TYPE);
		nullCharTypeObjectEDataType = createEDataType(NULL_CHAR_TYPE_OBJECT);
		servletNameTypeEDataType = createEDataType(SERVLET_NAME_TYPE);
		transportGuaranteeTypeObjectEDataType = createEDataType(TRANSPORT_GUARANTEE_TYPE_OBJECT);
		warPathTypeEDataType = createEDataType(WAR_PATH_TYPE);
		webAppVersionTypeObjectEDataType = createEDataType(WEB_APP_VERSION_TYPE_OBJECT);
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
		JavaeePackage theJavaeePackage = (JavaeePackage)EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
		JspPackage theJspPackage = (JspPackage)EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI);

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(authConstraintEClass, AuthConstraint.class, "AuthConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getAuthConstraint_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, AuthConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAuthConstraint_RoleNames(), theJavaeePackage.getRoleName(), "roleNames", null, 0, -1, AuthConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAuthConstraint_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, AuthConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(errorPageEClass, ErrorPage.class, "ErrorPage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getErrorPage_ErrorCode(), this.getErrorCodeType(), "errorCode", null, 0, 1, ErrorPage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getErrorPage_ExceptionType(), theJavaeePackage.getFullyQualifiedClassType(), "exceptionType", null, 0, 1, ErrorPage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getErrorPage_Location(), this.getWarPathType(), "location", null, 1, 1, ErrorPage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getErrorPage_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ErrorPage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(filterEClass, Filter.class, "Filter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getFilter_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFilter_DisplayNames(), theJavaeePackage.getDisplayName(), null, "displayNames", null, 0, -1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFilter_Icons(), theJavaeePackage.getIcon(), null, "icons", null, 0, -1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFilter_FilterName(), this.getFilterNameType(), "filterName", null, 1, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFilter_FilterClass(), theJavaeePackage.getFullyQualifiedClassType(), "filterClass", null, 1, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFilter_InitParams(), theJavaeePackage.getParamValue(), null, "initParams", null, 0, -1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFilter_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(filterMappingEClass, FilterMapping.class, "FilterMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFilterMapping_FilterName(), this.getFilterNameType(), "filterName", null, 1, 1, FilterMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFilterMapping_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, FilterMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFilterMapping_UrlPatterns(), theJavaeePackage.getUrlPatternType(), null, "urlPatterns", null, 0, -1, FilterMapping.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFilterMapping_ServletNames(), this.getServletNameType(), "servletNames", null, 0, -1, FilterMapping.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFilterMapping_Dispatchers(), this.getDispatcherType(), "dispatchers", "FORWARD", 0, 4, FilterMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getFilterMapping_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FilterMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(formLoginConfigEClass, FormLoginConfig.class, "FormLoginConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFormLoginConfig_FormLoginPage(), this.getWarPathType(), "formLoginPage", null, 1, 1, FormLoginConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFormLoginConfig_FormErrorPage(), this.getWarPathType(), "formErrorPage", null, 1, 1, FormLoginConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFormLoginConfig_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FormLoginConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(localeEncodingMappingEClass, LocaleEncodingMapping.class, "LocaleEncodingMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLocaleEncodingMapping_Locale(), this.getLocaleType(), "locale", null, 1, 1, LocaleEncodingMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLocaleEncodingMapping_Encoding(), this.getEncodingType(), "encoding", null, 1, 1, LocaleEncodingMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLocaleEncodingMapping_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, LocaleEncodingMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(localeEncodingMappingListEClass, LocaleEncodingMappingList.class, "LocaleEncodingMappingList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLocaleEncodingMappingList_LocalEncodingMappings(), this.getLocaleEncodingMapping(), null, "localEncodingMappings", null, 1, -1, LocaleEncodingMappingList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLocaleEncodingMappingList_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, LocaleEncodingMappingList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(loginConfigEClass, LoginConfig.class, "LoginConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLoginConfig_AuthMethod(), this.getAuthMethodType(), "authMethod", null, 0, 1, LoginConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLoginConfig_RealmName(), theXMLTypePackage.getToken(), "realmName", null, 0, 1, LoginConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLoginConfig_FormLoginConfig(), this.getFormLoginConfig(), null, "formLoginConfig", null, 0, 1, LoginConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLoginConfig_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, LoginConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(mimeMappingEClass, MimeMapping.class, "MimeMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getMimeMapping_Extension(), theXMLTypePackage.getToken(), "extension", null, 1, 1, MimeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMimeMapping_MimeType(), this.getMimeTypeType(), "mimeType", null, 1, 1, MimeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMimeMapping_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MimeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(securityConstraintEClass, SecurityConstraint.class, "SecurityConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSecurityConstraint_DisplayNames(), theJavaeePackage.getDisplayName(), null, "displayNames", null, 0, -1, SecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSecurityConstraint_WebResourceCollections(), this.getWebResourceCollection(), null, "webResourceCollections", null, 1, -1, SecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSecurityConstraint_AuthConstraint(), this.getAuthConstraint(), null, "authConstraint", null, 0, 1, SecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSecurityConstraint_UserDataConstraint(), this.getUserDataConstraint(), null, "userDataConstraint", null, 0, 1, SecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSecurityConstraint_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(servletEClass, Servlet.class, "Servlet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getServlet_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getServlet_DisplayNames(), theJavaeePackage.getDisplayName(), null, "displayNames", null, 0, -1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getServlet_Icons(), theJavaeePackage.getIcon(), null, "icons", null, 0, -1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getServlet_ServletName(), this.getServletNameType(), "servletName", null, 1, 1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getServlet_ServletClass(), theJavaeePackage.getFullyQualifiedClassType(), "servletClass", null, 0, 1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getServlet_JspFile(), theJspPackage.getJspFileType(), "jspFile", null, 0, 1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getServlet_InitParams(), theJavaeePackage.getParamValue(), null, "initParams", null, 0, -1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getServlet_LoadOnStartup(), this.getLoadOnStartupType(), "loadOnStartup", null, 0, 1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getServlet_RunAs(), theJavaeePackage.getRunAs(), null, "runAs", null, 0, 1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getServlet_SecurityRoleRefs(), theJavaeePackage.getSecurityRoleRef(), null, "securityRoleRefs", null, 0, -1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getServlet_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Servlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(servletMappingEClass, ServletMapping.class, "ServletMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getServletMapping_ServletName(), this.getServletNameType(), "servletName", null, 1, 1, ServletMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getServletMapping_UrlPatterns(), theJavaeePackage.getUrlPatternType(), null, "urlPatterns", null, 1, -1, ServletMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getServletMapping_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ServletMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sessionConfigEClass, SessionConfig.class, "SessionConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSessionConfig_SessionTimeout(), theXMLTypePackage.getInteger(), "sessionTimeout", null, 0, 1, SessionConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionConfig_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SessionConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(userDataConstraintEClass, UserDataConstraint.class, "UserDataConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getUserDataConstraint_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, UserDataConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getUserDataConstraint_TransportGuarantee(), this.getTransportGuaranteeType(), "transportGuarantee", "NONE", 1, 1, UserDataConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getUserDataConstraint_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, UserDataConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(webAppEClass, WebApp.class, "WebApp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getWebApp_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, WebApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_DisplayNames(), theJavaeePackage.getDisplayName(), null, "displayNames", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_Icons(), theJavaeePackage.getIcon(), null, "icons", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_Distributables(), theJavaeePackage.getEmptyType(), null, "distributables", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_ContextParams(), theJavaeePackage.getParamValue(), null, "contextParams", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_Filters(), this.getFilter(), null, "filters", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_FilterMappings(), this.getFilterMapping(), null, "filterMappings", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_Listeners(), theJavaeePackage.getListener(), null, "listeners", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_Servlets(), this.getServlet(), null, "servlets", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_ServletMappings(), this.getServletMapping(), null, "servletMappings", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_SessionConfigs(), this.getSessionConfig(), null, "sessionConfigs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_MimeMappings(), this.getMimeMapping(), null, "mimeMappings", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_WelcomeFileLists(), this.getWelcomeFileList(), null, "welcomeFileLists", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_ErrorPages(), this.getErrorPage(), null, "errorPages", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_JspConfigs(), theJspPackage.getJspConfig(), null, "jspConfigs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_SecurityConstraints(), this.getSecurityConstraint(), null, "securityConstraints", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_LoginConfigs(), this.getLoginConfig(), null, "loginConfigs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_SecurityRoles(), theJavaeePackage.getSecurityRole(), null, "securityRoles", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_EnvEntries(), theJavaeePackage.getEnvEntry(), null, "envEntries", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_EjbRefs(), theJavaeePackage.getEjbRef(), null, "ejbRefs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_EjbLocalRefs(), theJavaeePackage.getEjbLocalRef(), null, "ejbLocalRefs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_ServiceRefs(), theJavaeePackage.getServiceRef(), null, "serviceRefs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_ResourceRefs(), theJavaeePackage.getResourceRef(), null, "resourceRefs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_ResourceEnvRefs(), theJavaeePackage.getResourceEnvRef(), null, "resourceEnvRefs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_MessageDestinationRefs(), theJavaeePackage.getMessageDestinationRef(), null, "messageDestinationRefs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_PersistenceContextRefs(), theJavaeePackage.getPersistenceContextRef(), null, "persistenceContextRefs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_PersistenceUnitRefs(), theJavaeePackage.getPersistenceUnitRef(), null, "persistenceUnitRefs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_PostConstructs(), theJavaeePackage.getLifecycleCallback(), null, "postConstructs", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_PreDestroys(), theJavaeePackage.getLifecycleCallback(), null, "preDestroys", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_MessageDestinations(), theJavaeePackage.getMessageDestination(), null, "messageDestinations", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebApp_LocalEncodingMappingsLists(), this.getLocaleEncodingMappingList(), null, "localEncodingMappingsLists", null, 0, -1, WebApp.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWebApp_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, WebApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWebApp_MetadataComplete(), theXMLTypePackage.getBoolean(), "metadataComplete", null, 0, 1, WebApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWebApp_Version(), this.getWebAppVersionType(), "version", "2.5", 1, 1, WebApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(webAppDeploymentDescriptorEClass, WebAppDeploymentDescriptor.class, "WebAppDeploymentDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getWebAppDeploymentDescriptor_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebAppDeploymentDescriptor_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebAppDeploymentDescriptor_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebAppDeploymentDescriptor_WebApp(), this.getWebApp(), null, "webApp", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(webResourceCollectionEClass, WebResourceCollection.class, "WebResourceCollection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getWebResourceCollection_WebResourceName(), theXMLTypePackage.getToken(), "webResourceName", null, 1, 1, WebResourceCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebResourceCollection_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, WebResourceCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWebResourceCollection_UrlPatterns(), theJavaeePackage.getUrlPatternType(), null, "urlPatterns", null, 1, -1, WebResourceCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWebResourceCollection_HttpMethods(), this.getHttpMethodType(), "httpMethods", null, 0, -1, WebResourceCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWebResourceCollection_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, WebResourceCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(welcomeFileListEClass, WelcomeFileList.class, "WelcomeFileList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getWelcomeFileList_WelcomeFiles(), theXMLTypePackage.getString(), "welcomeFiles", null, 1, -1, WelcomeFileList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWelcomeFileList_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, WelcomeFileList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(dispatcherTypeEEnum, DispatcherType.class, "DispatcherType"); //$NON-NLS-1$
		addEEnumLiteral(dispatcherTypeEEnum, DispatcherType.FORWARD_LITERAL);
		addEEnumLiteral(dispatcherTypeEEnum, DispatcherType.INCLUDE_LITERAL);
		addEEnumLiteral(dispatcherTypeEEnum, DispatcherType.REQUEST_LITERAL);
		addEEnumLiteral(dispatcherTypeEEnum, DispatcherType.ERROR_LITERAL);

		initEEnum(nullCharTypeEEnum, NullCharType.class, "NullCharType"); //$NON-NLS-1$
		addEEnumLiteral(nullCharTypeEEnum, NullCharType.__LITERAL);

		initEEnum(transportGuaranteeTypeEEnum, TransportGuaranteeType.class, "TransportGuaranteeType"); //$NON-NLS-1$
		addEEnumLiteral(transportGuaranteeTypeEEnum, TransportGuaranteeType.NONE_LITERAL);
		addEEnumLiteral(transportGuaranteeTypeEEnum, TransportGuaranteeType.INTEGRAL_LITERAL);
		addEEnumLiteral(transportGuaranteeTypeEEnum, TransportGuaranteeType.CONFIDENTIAL_LITERAL);

		initEEnum(webAppVersionTypeEEnum, WebAppVersionType.class, "WebAppVersionType"); //$NON-NLS-1$
		addEEnumLiteral(webAppVersionTypeEEnum, WebAppVersionType._25_LITERAL);

		// Initialize data types
		initEDataType(authMethodTypeEDataType, String.class, "AuthMethodType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(dispatcherTypeObjectEDataType, DispatcherType.class, "DispatcherTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(encodingTypeEDataType, String.class, "EncodingType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(errorCodeTypeEDataType, BigInteger.class, "ErrorCodeType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(filterNameTypeEDataType, String.class, "FilterNameType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(httpMethodTypeEDataType, String.class, "HttpMethodType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(loadOnStartupTypeEDataType, Object.class, "LoadOnStartupType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(localeTypeEDataType, String.class, "LocaleType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(mimeTypeTypeEDataType, String.class, "MimeTypeType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(nonEmptyStringTypeEDataType, String.class, "NonEmptyStringType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(nullCharTypeObjectEDataType, NullCharType.class, "NullCharTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(servletNameTypeEDataType, String.class, "ServletNameType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(transportGuaranteeTypeObjectEDataType, TransportGuaranteeType.class, "TransportGuaranteeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(warPathTypeEDataType, String.class, "WarPathType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(webAppVersionTypeObjectEDataType, WebAppVersionType.class, "WebAppVersionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
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
		  (authConstraintEClass, 
		   source, 
		   new String[] {
			 "name", "auth-constraintType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAuthConstraint_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAuthConstraint_RoleNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "role-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAuthConstraint_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (authMethodTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "auth-methodType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (dispatcherTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "dispatcherType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (dispatcherTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "dispatcherType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "dispatcherType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (encodingTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "encodingType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "[^\\s]+" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (errorCodeTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "error-codeType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#positiveInteger", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "\\d{3}" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (errorPageEClass, 
		   source, 
		   new String[] {
			 "name", "error-pageType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getErrorPage_ErrorCode(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "error-code", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getErrorPage_ExceptionType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "exception-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getErrorPage_Location(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "location", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getErrorPage_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (filterEClass, 
		   source, 
		   new String[] {
			 "name", "filterType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilter_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilter_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilter_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilter_FilterName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "filter-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getFilter_FilterClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "filter-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getFilter_InitParams(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "init-param", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilter_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (filterMappingEClass, 
		   source, 
		   new String[] {
			 "name", "filter-mappingType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilterMapping_FilterName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "filter-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilterMapping_Group(), 
		   source, 
		   new String[] {
			 "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "group:1" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilterMapping_UrlPatterns(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "url-pattern", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:1" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilterMapping_ServletNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "servlet-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:1" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilterMapping_Dispatchers(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "dispatcher", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFilterMapping_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (filterNameTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "filter-nameType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "nonEmptyStringType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (formLoginConfigEClass, 
		   source, 
		   new String[] {
			 "name", "form-login-configType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getFormLoginConfig_FormLoginPage(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "form-login-page", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getFormLoginConfig_FormErrorPage(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "form-error-page", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getFormLoginConfig_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (httpMethodTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "http-methodType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "[\\p{L}-[\\p{Cc}\\p{Z}]]+" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (loadOnStartupTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "load-on-startupType", //$NON-NLS-1$ //$NON-NLS-2$
			 "memberTypes", "null-charType http://www.eclipse.org/emf/2003/XMLType#integer" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (localeEncodingMappingEClass, 
		   source, 
		   new String[] {
			 "name", "locale-encoding-mappingType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLocaleEncodingMapping_Locale(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "locale", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLocaleEncodingMapping_Encoding(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "encoding", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLocaleEncodingMapping_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (localeEncodingMappingListEClass, 
		   source, 
		   new String[] {
			 "name", "locale-encoding-mapping-listType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLocaleEncodingMappingList_LocalEncodingMappings(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "locale-encoding-mapping", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLocaleEncodingMappingList_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (localeTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "localeType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "[a-z]{2}(_|-)?([\\p{L}\\-\\p{Nd}]{2})?" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (loginConfigEClass, 
		   source, 
		   new String[] {
			 "name", "login-configType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLoginConfig_AuthMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "auth-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getLoginConfig_RealmName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "realm-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLoginConfig_FormLoginConfig(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "form-login-config", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLoginConfig_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (mimeMappingEClass, 
		   source, 
		   new String[] {
			 "name", "mime-mappingType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMimeMapping_Extension(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "extension", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMimeMapping_MimeType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mime-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMimeMapping_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (mimeTypeTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "mime-typeType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "[^\\p{Cc}^\\s]+/[^\\p{Cc}^\\s]+" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (nonEmptyStringTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "nonEmptyStringType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			 "minLength", "1" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (nullCharTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "null-charType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (nullCharTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "null-charType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "null-charType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (securityConstraintEClass, 
		   source, 
		   new String[] {
			 "name", "security-constraintType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityConstraint_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityConstraint_WebResourceCollections(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "web-resource-collection", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityConstraint_AuthConstraint(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "auth-constraint", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityConstraint_UserDataConstraint(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "user-data-constraint", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityConstraint_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (servletEClass, 
		   source, 
		   new String[] {
			 "name", "servletType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServlet_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServlet_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServlet_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServlet_ServletName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "servlet-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServlet_ServletClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "servlet-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServlet_JspFile(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jsp-file", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServlet_InitParams(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "init-param", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServlet_LoadOnStartup(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "load-on-startup", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServlet_RunAs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "run-as", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServlet_SecurityRoleRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-role-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServlet_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (servletMappingEClass, 
		   source, 
		   new String[] {
			 "name", "servlet-mappingType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServletMapping_ServletName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "servlet-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServletMapping_UrlPatterns(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "url-pattern", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServletMapping_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (servletNameTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "servlet-nameType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "nonEmptyStringType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (sessionConfigEClass, 
		   source, 
		   new String[] {
			 "name", "session-configType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionConfig_SessionTimeout(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "session-timeout", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionConfig_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (transportGuaranteeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "transport-guaranteeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (transportGuaranteeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "transport-guaranteeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "transport-guaranteeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (userDataConstraintEClass, 
		   source, 
		   new String[] {
			 "name", "user-data-constraintType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getUserDataConstraint_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getUserDataConstraint_TransportGuarantee(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "transport-guarantee", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getUserDataConstraint_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (warPathTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "war-pathType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "/.*" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (webAppEClass, 
		   source, 
		   new String[] {
			 "name", "web-appType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_Group(), 
		   source, 
		   new String[] {
			 "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_Distributables(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "distributable", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getWebApp_ContextParams(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "context-param", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_Filters(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "filter", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_FilterMappings(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "filter-mapping", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_Listeners(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "listener", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_Servlets(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "servlet", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_ServletMappings(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "servlet-mapping", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_SessionConfigs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "session-config", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_MimeMappings(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mime-mapping", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_WelcomeFileLists(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "welcome-file-list", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_ErrorPages(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "error-page", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_JspConfigs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jsp-config", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_SecurityConstraints(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-constraint", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_LoginConfigs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "login-config", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_SecurityRoles(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-role", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_EnvEntries(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "env-entry", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_EjbRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_EjbLocalRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-local-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_ServiceRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_ResourceRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_ResourceEnvRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-env-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_MessageDestinationRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_PersistenceContextRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-context-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_PersistenceUnitRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-unit-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_PostConstructs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "post-construct", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_PreDestroys(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "pre-destroy", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_MessageDestinations(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_LocalEncodingMappingsLists(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "locale-encoding-mapping-list", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getWebApp_MetadataComplete(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "metadata-complete" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebApp_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "version" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (webAppDeploymentDescriptorEClass, 
		   source, 
		   new String[] {
			 "name", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebAppDeploymentDescriptor_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebAppDeploymentDescriptor_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebAppDeploymentDescriptor_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getWebAppDeploymentDescriptor_WebApp(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "web-app", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (webAppVersionTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "web-app-versionType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (webAppVersionTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "web-app-versionType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "web-app-versionType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (webResourceCollectionEClass, 
		   source, 
		   new String[] {
			 "name", "web-resource-collectionType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getWebResourceCollection_WebResourceName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "web-resource-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebResourceCollection_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebResourceCollection_UrlPatterns(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "url-pattern", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebResourceCollection_HttpMethods(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "http-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWebResourceCollection_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (welcomeFileListEClass, 
		   source, 
		   new String[] {
			 "name", "welcome-file-listType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getWelcomeFileList_WelcomeFiles(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "welcome-file", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getWelcomeFileList_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

} //WebPackageImpl
