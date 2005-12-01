/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 18, 2003
 */
package org.eclipse.jst.j2ee.internal.model.translator.webapplication;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.model.translator.common.BooleanTranslator;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.model.translator.common.EnvEntryTranslator;
import org.eclipse.jst.j2ee.internal.model.translator.common.JavaClassTranslator;
import org.eclipse.jst.j2ee.internal.xml.WarDeploymentDescriptorXmlMapperI;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emf.resource.ConstantAttributeTranslator;
import org.eclipse.wst.common.internal.emf.resource.GenericTranslator;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.RootTranslator;
import org.eclipse.wst.common.internal.emf.resource.SourceLinkTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorPath;


/**
 * @author schacher
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class WebAppTranslator extends RootTranslator implements WarDeploymentDescriptorXmlMapperI, J2EEConstants {
	private static WebapplicationPackage WEBAPP_PKG = WebapplicationPackage.eINSTANCE;
	private static CommonPackage COMMON_PKG = CommonPackage.eINSTANCE;
	private static JspPackage JSP_PKG = JspPackage.eINSTANCE;
	public static WebAppTranslator INSTANCE = new WebAppTranslator();
	private static Translator[] children13;
	private static Translator[] children14;
	
	private static final Translator SERVLET_MAPPING_TRANSLATOR = createServletMappingTranslator();
	private static final Translator SESSION_CONFIG_TRANSLATOR = createSessionConfigTranslator();
	private static final Translator WELCOMEFILE_TRANSLATOR = createFileListTranslator();
	private static final Translator MIME_MAPPING_TRANSLATOR = createMimeMappingTranslator();
	private static final Translator ERROR_PAGE_TRANSLATOR = new ErrorPageTranslator();
	private static final Translator LOGIN_CONFIG_TRANSLATOR = createLoginConfigTranslator();
	private static final Translator DISTRIBUTABLE_TRANSLATOR = new Translator(DISTRIBUTABLE, WEBAPP_PKG.getWebApp_Distributable(), EMPTY_TAG);
	
	private static final Translator WEB_RESOURCE_NAME_TRANSLATOR = new Translator(WEB_RESOURCE_NAME, WEBAPP_PKG.getWebResourceCollection_WebResourceName()); 
	private static final Translator URL_PATTERN_TRANSLATOR = new Translator(URL_PATTERN, WEBAPP_PKG.getWebResourceCollection_UrlPattern());
	private static final Translator HTTP_METHOD_TRANSLATOR = new Translator(HTTP_METHOD, WEBAPP_PKG.getWebResourceCollection_HttpMethod());
	
	private static final Translator ROLE_NAME_TRANSLATOR = new Translator(ROLE_NAME, WEBAPP_PKG.getAuthConstraint_Roles());
	private static final Translator TRANSPORT_GUARANTEE_TRANSLATOR = new Translator(TRANSPORT_GUARANTEE, WEBAPP_PKG.getUserDataConstraint_TransportGuarantee());

	
	protected WebAppTranslator() {
		super(WEB_APP, WEBAPP_PKG.getWebApp());
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		switch (versionID) {
			case (J2EE_1_2_ID) :
			case (J2EE_1_3_ID) :
				if (children13 == null)
					children13 = create13Children();
				return children13;	
			default :
				if (children14 == null)
					children14 = create14Children();
				return children14; 
		}
	}


	protected Translator[] create13Children() {
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(SMALL_ICON_PATH, COMMON_PKG.getCompatibilityDescriptionGroup_SmallIcon()), 
			new Translator(LARGE_ICON_PATH, COMMON_PKG.getCompatibilityDescriptionGroup_LargeIcon()), 
			new Translator(DISPLAY_NAME, COMMON_PKG.getCompatibilityDescriptionGroup_DisplayName()), 
			new Translator(DESCRIPTION, COMMON_PKG.getCompatibilityDescriptionGroup_Description()), 
			DISTRIBUTABLE_TRANSLATOR,
			createContextParam13Translator(),	 
			createFilter13Translator(),	 
			createFilterMapping13Translator(),	 
			createListener13Translator(), 
			createServlet13Translator(), 
			SERVLET_MAPPING_TRANSLATOR,	 
			SESSION_CONFIG_TRANSLATOR,	 
			MIME_MAPPING_TRANSLATOR,	 
			WELCOMEFILE_TRANSLATOR, 
			ERROR_PAGE_TRANSLATOR, 
			createTagLibTranslator13(WEBAPP_PKG.getWebApp_TagLibs()), 
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_ENV_REF_1_3, 
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_REF_1_3,
			createSecurityConstraintTranslator13(),
			LOGIN_CONFIG_TRANSLATOR, 
			CommonTranslators.createSecurityRoleTranslator13(WEBAPP_PKG.getWebApp_SecurityRoles()), 
			new EnvEntryTranslator(COMMON_PKG.getJNDIEnvRefsGroup_EnvironmentProperties(), false, false), 
			CommonTranslators.JNDI_REF_GROUP_EJB_REF_1_3, 
			CommonTranslators.JNDI_REF_GROUP_EJB_LOCAL_REF_1_3 
		};
	}
	

	protected Translator[] create14Children() {
		Translator[] begin =  new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(VERSION, WEBAPP_PKG.getWebApp_Version(), DOM_ATTRIBUTE),
			new ConstantAttributeTranslator(XML_NS, J2EE_NS_URL),
			new ConstantAttributeTranslator(XML_NS_XSI, XSI_NS_URL),
			new ConstantAttributeTranslator(XSI_SCHEMA_LOCATION, J2EE_NS_URL+' '+WEB_APP_SCHEMA_LOC_2_4),
			CommonTranslators.DESCRIPTIONS_TRANSLATOR,
			CommonTranslators.DISPLAYNAMES_TRANSLATOR,
			CommonTranslators.ICONS_TRANSLATOR,
			DISTRIBUTABLE_TRANSLATOR,
			CommonTranslators.createParamValueType(CONTEXT_PARAM, WEBAPP_PKG.getWebApp_ContextParams()),
			createFilter14Translator(),
			createFilterMapping14Translator(),
			createListener14Translator(),
			createServlet14Translator(),
			SERVLET_MAPPING_TRANSLATOR,	 
			SESSION_CONFIG_TRANSLATOR,	 
			MIME_MAPPING_TRANSLATOR,	 
			WELCOMEFILE_TRANSLATOR, 
			ERROR_PAGE_TRANSLATOR,
			createJSPConfigTranslators(),
			createSecurityConstraintTranslators14(),
			LOGIN_CONFIG_TRANSLATOR,
			CommonTranslators.createSecurityRoleTranslator14(WEBAPP_PKG.getWebApp_SecurityRoles())
		};
		Translator[] end = new Translator[] {
			CommonTranslators.createMessageDestinationTranslator(WEBAPP_PKG.getWebApp_MessageDestinations()),
			createLocaleEncodingMappingListTranslator(),
		//	CommonTranslators.createDeploymentExtensionTranslator(WEBAPP_PKG.getWebApp_Extensions())
		};
		
		
		return (Translator[])concat(begin,concat(createJNDIEnvironmentRefsGroupTranslator(), end));
	}
	
	public static Translator createSecurityConstraintTranslators14() {
		GenericTranslator result = new GenericTranslator(SECURITY_CONSTRAINT, WEBAPP_PKG.getWebApp_Constraints());
		result.setChildren(new Translator[] {
			CommonTranslators.createDisplayNameTranslator(WEBAPP_PKG.getSecurityConstraint_DisplayNames()),
			createWebResourceCollectionTranslator14(),
			createAuthConstraintTranslator14(),
			createUserDataConstraintTranslator14(),
		});
		return result;
	}
	
	private static Translator createWebResourceCollectionTranslator14() {
		GenericTranslator result = new GenericTranslator(WEB_RESOURCE_COLLECTION, WEBAPP_PKG.getSecurityConstraint_WebResourceCollections());
		result.setChildren(new Translator[] {
			WEB_RESOURCE_NAME_TRANSLATOR, 
			CommonTranslators.createDescriptionTranslator(WEBAPP_PKG.getWebResourceCollection_Descriptions()), 
			URL_PATTERN_TRANSLATOR,
			HTTP_METHOD_TRANSLATOR
		});
		return result;
	}
	
	private static Translator createAuthConstraintTranslator14() { 
		GenericTranslator result = new GenericTranslator(AUTH_CONSTRAINT, WEBAPP_PKG.getSecurityConstraint_AuthConstraint());
		result.setChildren(new Translator[] {
			CommonTranslators.createDescriptionTranslator(WEBAPP_PKG.getAuthConstraint_Descriptions()), 
			ROLE_NAME_TRANSLATOR
		});
		return result;
	}
	
	private static Translator createUserDataConstraintTranslator14() {
		GenericTranslator result = new GenericTranslator(USER_DATA_CONSTRAINT, WEBAPP_PKG.getSecurityConstraint_UserDataConstraint());
		result.setChildren(new Translator[] {
			CommonTranslators.createDescriptionTranslator(WEBAPP_PKG.getUserDataConstraint_Descriptions()), 
			TRANSPORT_GUARANTEE_TRANSLATOR 
		});
		return result;
	}


	private Translator createJSPConfigTranslators() {
		GenericTranslator result = new GenericTranslator(JSP_CONFIG, WEBAPP_PKG.getWebApp_JspConfig());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			createTagLibTranslator14(JSP_PKG.getJSPConfig_TagLibs()),
			createJSPPropertyGroupTranslator()
		});
		
		return result;
	}


	/**
	 * @return
	 */
	private Translator createJSPPropertyGroupTranslator() {
		GenericTranslator result = new GenericTranslator(JSP_PROPERTY_GROUP, JSP_PKG.getJSPConfig_PropertyGroups());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			CommonTranslators.DESCRIPTIONS_TRANSLATOR,
			CommonTranslators.DISPLAYNAMES_TRANSLATOR,
			CommonTranslators.ICONS_TRANSLATOR,
			new Translator(URL_PATTERN, JSP_PKG.getJSPPropertyGroup_UrlPattern()),
			new BooleanTranslator(EL_IGNORED, JSP_PKG.getJSPPropertyGroup_ElIgnored()),
			new Translator(PAGE_ENCODING, JSP_PKG.getJSPPropertyGroup_PageEncoding()),
			new BooleanTranslator(SCRIPTING_INVALID, JSP_PKG.getJSPPropertyGroup_ScriptingInvalid()),
			new BooleanTranslator(IS_XML,JSP_PKG.getJSPPropertyGroup_IsXML()),
			new Translator(INCLUDE_PRELUDE, JSP_PKG.getJSPPropertyGroup_IncludePreludes()),
			new Translator(INCLUDE_CODA, JSP_PKG.getJSPPropertyGroup_IncludeCodas())
		});		
		return result;
	}


	private Translator createLocaleEncodingMappingListTranslator() {
		GenericTranslator result = new GenericTranslator(LOCALE_ENCODING_MAPPING_LIST, WEBAPP_PKG.getWebApp_LocalEncodingMappingList());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			createLocaleEncodingMapping()
		});
		return result;
	
	}


	/**
	 * @return
	 */
	private Translator createLocaleEncodingMapping() {
		GenericTranslator result = new GenericTranslator(LOCALE_ENCODING_MAPPING, WEBAPP_PKG.getLocalEncodingMappingList_LocalEncodingMappings());
		result.setChildren(new Translator[] {
			new Translator(LOCALE, WEBAPP_PKG.getLocalEncodingMapping_Locale()), //$NON-NLS-1$
			new Translator(ENCODING, WEBAPP_PKG.getLocalEncodingMapping_Encoding()) //$NON-NLS-1$
		});
		return result;

	}


	/**
	 * @return
	 */
	private Translator[] createJNDIEnvironmentRefsGroupTranslator() {
		return new Translator[] {
			CommonTranslators.JNDI_REF_GROUP_ENV_ENTRY_1_4,
			CommonTranslators.JNDI_REF_GROUP_EJB_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_EJB_LOCAL_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_SERVICE_REF_1_4,	
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_ENV_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_MESSAGE_DEST_REF_1_4
		};
	}


	/**
	 * @return
	 */
	private Translator createFilterMapping14Translator() {
		Translator dispatcher = new Translator(DISPATCHER, WEBAPP_PKG.getFilterMapping_DispatcherType());
		return GenericTranslator.appendChild((GenericTranslator)createFilterMapping13Translator(),dispatcher);
	}
	
	public static Translator createContextParam13Translator() {
		GenericTranslator result = new GenericTranslator(CONTEXT_PARAM, WEBAPP_PKG.getWebApp_Contexts());
		result.setChildren(new Translator[] {
			new Translator(PARAM_NAME, WEBAPP_PKG.getContextParam_ParamName()), 
			new Translator(PARAM_VALUE, WEBAPP_PKG.getContextParam_ParamValue()), 
			new Translator(DESCRIPTION, WEBAPP_PKG.getContextParam_Description()), 
		});
		return result;
	}
	
	public static Translator createFilter13Translator() {
		GenericTranslator result = new GenericTranslator(FILTER, WEBAPP_PKG.getWebApp_Filters());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(SMALL_ICON_PATH, COMMON_PKG.getCompatibilityDescriptionGroup_SmallIcon()), 
			new Translator(LARGE_ICON_PATH, COMMON_PKG.getCompatibilityDescriptionGroup_LargeIcon()), 
			new Translator(FILTER_NAME, WEBAPP_PKG.getFilter_Name(), 
						new TranslatorPath(new Translator[] {
							new Translator(WEB_APP, Translator.CONTAINER_FEATURE), 
							new Translator(FILTER_MAPPING, WEBAPP_PKG.getWebApp_FilterMappings()), 
						})),
			new Translator(DISPLAY_NAME, COMMON_PKG.getCompatibilityDescriptionGroup_DisplayName()), 
			new Translator(DESCRIPTION, COMMON_PKG.getCompatibilityDescriptionGroup_Description()), 
			new JavaClassTranslator(FILTER_CLASS, WEBAPP_PKG.getFilter_FilterClass()), 
			createInitParamTranslator(WEBAPP_PKG.getFilter_InitParams())
		});
		return result;
	}
	
	
	public static Translator createFilter14Translator() {
		GenericTranslator result = new GenericTranslator(FILTER, WEBAPP_PKG.getWebApp_Filters());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			CommonTranslators.DESCRIPTIONS_TRANSLATOR,
			CommonTranslators.DISPLAYNAMES_TRANSLATOR,
			CommonTranslators.ICONS_TRANSLATOR,
			new Translator(FILTER_NAME, WEBAPP_PKG.getFilter_Name(), 
						//TODO: Make sure the path is still valid
						new TranslatorPath(new Translator[] {
							new Translator(WEB_APP, Translator.CONTAINER_FEATURE), 
							new Translator(FILTER_MAPPING, WEBAPP_PKG.getWebApp_FilterMappings()), 
						})),
			new JavaClassTranslator(FILTER_CLASS, WEBAPP_PKG.getFilter_FilterClass()), 
			CommonTranslators.createParamValueType(INIT_PARAM,WEBAPP_PKG.getFilter_InitParamValues())
		});
		return result;
	}
	
	public static Translator createFilterMapping13Translator() {
		GenericTranslator result = new GenericTranslator(FILTER_MAPPING, WEBAPP_PKG.getWebApp_FilterMappings());
		result.setChildren(new Translator[] {
			new SourceLinkTranslator(FILTER_NAME, WEBAPP_PKG.getFilterMapping_Filter(), 
						new TranslatorPath(new Translator[] {
							new Translator(WEB_APP, Translator.CONTAINER_FEATURE), 
							new Translator(FILTER, WEBAPP_PKG.getWebApp_Filters()), 
							new Translator(FILTER_MAPPING, WEBAPP_PKG.getFilter_Name())
						})),
			new Translator(URL_PATTERN, WEBAPP_PKG.getFilterMapping_UrlPattern()), 
			new SourceLinkTranslator(SERVLET_NAME, WEBAPP_PKG.getFilterMapping_Servlet(), 
						new TranslatorPath(new Translator[] {
							new Translator(WEB_APP, Translator.CONTAINER_FEATURE), 
							new Translator(SERVLET, WEBAPP_PKG.getWebApp_Servlets()), 
							new Translator(SERVLET_NAME, WEBAPP_PKG.getServlet_ServletName()) 
						}), Translator.STRING_RESULT_OK),
		});
		return result;
	}
	
	public static Translator createInitParamTranslator(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(INIT_PARAM, feature);
		result.setChildren(new Translator[] {
			new Translator(PARAM_NAME, WEBAPP_PKG.getInitParam_ParamName()), 
			new Translator(PARAM_VALUE, WEBAPP_PKG.getInitParam_ParamValue()), 
			new Translator(DESCRIPTION, WEBAPP_PKG.getInitParam_Description()), 
		});
		return result;
	}
	
	public static Translator createListener13Translator() {
		GenericTranslator result = new GenericTranslator(LISTENER, WEBAPP_PKG.getWebApp_Listeners());
		result.setChildren(new Translator[] {
			CommonTranslators.LISTENER_CLASS_TRANSLATOR
		});
		return result;
	}
	
	public static Translator createListener14Translator() {
			GenericTranslator result = new GenericTranslator(LISTENER, WEBAPP_PKG.getWebApp_Listeners());
			result.setChildren(new Translator[] {
				CommonTranslators.DESCRIPTIONS_TRANSLATOR,
				CommonTranslators.DISPLAYNAMES_TRANSLATOR,
				CommonTranslators.ICONS_TRANSLATOR,
				CommonTranslators.LISTENER_CLASS_TRANSLATOR
			});
			return result;
	}
	
	public static Translator createServlet13Translator() {
		GenericTranslator result = new GenericTranslator(SERVLET, WEBAPP_PKG.getWebApp_Servlets());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(SMALL_ICON_PATH, COMMON_PKG.getCompatibilityDescriptionGroup_SmallIcon()), 
			new Translator(LARGE_ICON_PATH, COMMON_PKG.getCompatibilityDescriptionGroup_LargeIcon()), 
			new Translator(SERVLET_NAME, WEBAPP_PKG.getServlet_ServletName(), 
				new TranslatorPath[] {
					new TranslatorPath(new Translator[] {
						new Translator(WEB_APP, WEBAPP_PKG.getServlet_WebApp()), 
						new Translator(SERVLET_MAPPING, WEBAPP_PKG.getWebApp_ServletMappings()), 
					}), 
					new TranslatorPath(new Translator[] {
						new Translator(WEB_APP, WEBAPP_PKG.getServlet_WebApp()), 
						new Translator(FILTER_MAPPING, WEBAPP_PKG.getWebApp_FilterMappings()), 
					}), 
				}),
			new Translator(DISPLAY_NAME, COMMON_PKG.getCompatibilityDescriptionGroup_DisplayName()), 
			new Translator(DESCRIPTION, COMMON_PKG.getCompatibilityDescriptionGroup_Description()), 
			new WebTypeTranslator(), //servlet-class or jsp-file  
			createInitParamTranslator(WEBAPP_PKG.getServlet_Params()), 
			new Translator(LOAD_ON_STARTUP, WEBAPP_PKG.getServlet_LoadOnStartup()),
			CommonTranslators.createRunAsTranslator13(WEBAPP_PKG.getServlet_RunAs()),
			CommonTranslators.createSecurityRoleRefTranslator13(WEBAPP_PKG.getServlet_SecurityRoleRefs()) 
		});
		return result;	
	}
	
	public static Translator createServlet14Translator() {
		GenericTranslator result = new GenericTranslator(SERVLET, WEBAPP_PKG.getWebApp_Servlets());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			CommonTranslators.DESCRIPTIONS_TRANSLATOR,
			CommonTranslators.DISPLAYNAMES_TRANSLATOR,
			CommonTranslators.ICONS_TRANSLATOR,
			new Translator(SERVLET_NAME, WEBAPP_PKG.getServlet_ServletName(), 
				new TranslatorPath[] {
					new TranslatorPath(new Translator[] {
						new Translator(WEB_APP, WEBAPP_PKG.getServlet_WebApp()), 
						new Translator(SERVLET_MAPPING, WEBAPP_PKG.getWebApp_ServletMappings()), 
					}), 
					new TranslatorPath(new Translator[] {
						new Translator(WEB_APP, WEBAPP_PKG.getServlet_WebApp()), 
						new Translator(FILTER_MAPPING, WEBAPP_PKG.getWebApp_FilterMappings()), 
					}), 
				}),
			new WebTypeTranslator(), //servlet-class or jsp-file  
			CommonTranslators.createParamValueType(INIT_PARAM,WEBAPP_PKG.getServlet_InitParams()), 
			new Translator(LOAD_ON_STARTUP, WEBAPP_PKG.getServlet_LoadOnStartup()),
			CommonTranslators.createRunAsTranslator14(WEBAPP_PKG.getServlet_RunAs()),
			CommonTranslators.createSecurityRoleRefTranslator14(WEBAPP_PKG.getServlet_SecurityRoleRefs()),
		//	CommonTranslators.createDeploymentExtensionTranslator(WEBAPP_PKG.getServlet_Extensions())
		});
		return result;	
	}

	public static Translator createServletMappingTranslator() {
		GenericTranslator result = new GenericTranslator(SERVLET_MAPPING, WEBAPP_PKG.getWebApp_ServletMappings());
		result.setChildren(new Translator[] {
			new SourceLinkTranslator(SERVLET_NAME, WEBAPP_PKG.getServletMapping_Servlet(), 
				new TranslatorPath(new Translator[] {
					new Translator(WEB_APP, CONTAINER_FEATURE), 
					new Translator(SERVLET, WEBAPP_PKG.getWebApp_Servlets()), 
					new Translator(SERVLET_NAME, WEBAPP_PKG.getServlet_ServletName()) 
				})),
			new Translator(URL_PATTERN, WEBAPP_PKG.getServletMapping_UrlPattern()) 
		});
		return result;
	}
	
	public static Translator createSessionConfigTranslator() {
		GenericTranslator result = new GenericTranslator(SESSION_CONFIG, WEBAPP_PKG.getWebApp_SessionConfig());
		result.setChildren(new Translator[] {
			new Translator(SESSION_TIMEOUT, WebapplicationPackage.eINSTANCE.getSessionConfig_SessionTimeout()), 
		});
		return result;
	}
	
	public static Translator createMimeMappingTranslator() {
		GenericTranslator result = new GenericTranslator(MIME_MAPPING, WEBAPP_PKG.getWebApp_MimeMappings());
		result.setChildren(new Translator[] {
			new Translator(EXTENSION, WEBAPP_PKG.getMimeMapping_Extension()), 
			new Translator(MIME_TYPE, WEBAPP_PKG.getMimeMapping_MimeType()), 
			
		});
		return result;
	}
	
	public static Translator createFileListTranslator() {
		GenericTranslator result = new GenericTranslator(WELCOME_FILE_LIST, WEBAPP_PKG.getWebApp_FileList());
		result.setChildren(new Translator[] {
			createParentAndTextAttributeTranslator(WELCOME_FILE, WEBAPP_PKG.getWelcomeFileList_File(), WEBAPP_PKG.getWelcomeFile_WelcomeFile())
		});
		return result;
	}
	
	public static Translator createTagLibTranslator14(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(TAGLIB, feature);
		result.setChildren(new Translator[] {
			new Translator(TAGLIB_URI, JSP_PKG.getTagLibRefType_TaglibURI()), 
			new Translator(TAGLIB_LOCATION, JSP_PKG.getTagLibRefType_TaglibLocation()) 
		});
		return result;
	}
	public static Translator createTagLibTranslator13(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(TAGLIB, feature);
		result.setChildren(new Translator[] {
			new Translator(TAGLIB_URI, WEBAPP_PKG.getTagLibRef_TaglibURI()), 
			new Translator(TAGLIB_LOCATION, WEBAPP_PKG.getTagLibRef_TaglibLocation()) 
		});
		return result;
	}
	public static Translator createSecurityConstraintTranslator13() {
		GenericTranslator result = new GenericTranslator(SECURITY_CONSTRAINT, WEBAPP_PKG.getWebApp_Constraints());
		result.setChildren(new Translator[] {
			new Translator(DISPLAY_NAME, WEBAPP_PKG.getSecurityConstraint_DisplayName()),
			createWebResourceCollectionTranslator13(),
			createAuthConstraintTranslator13(),
			createUserDataConstraintTranslator13(),
		});
		return result;
	}
	
	private static Translator createWebResourceCollectionTranslator13() {
		GenericTranslator result = new GenericTranslator(WEB_RESOURCE_COLLECTION, WEBAPP_PKG.getSecurityConstraint_WebResourceCollections());
		result.setChildren(new Translator[] {
			WEB_RESOURCE_NAME_TRANSLATOR, 
			new Translator(DESCRIPTION,WEBAPP_PKG.getWebResourceCollection_Description()), 
			URL_PATTERN_TRANSLATOR,
			HTTP_METHOD_TRANSLATOR
		});
		return result;
	}
	
	private static Translator createAuthConstraintTranslator13() { 
		GenericTranslator result = new GenericTranslator(AUTH_CONSTRAINT, WEBAPP_PKG.getSecurityConstraint_AuthConstraint());
		result.setChildren(new Translator[] {
			new Translator(DESCRIPTION, WEBAPP_PKG.getAuthConstraint_Description()), 
			ROLE_NAME_TRANSLATOR
		});
		return result;
	}
	
	private static Translator createUserDataConstraintTranslator13() {
		GenericTranslator result = new GenericTranslator(USER_DATA_CONSTRAINT, WEBAPP_PKG.getSecurityConstraint_UserDataConstraint());
		result.setChildren(new Translator[] {
			new Translator(DESCRIPTION, WEBAPP_PKG.getUserDataConstraint_Description()), 
			TRANSPORT_GUARANTEE_TRANSLATOR 
		});
		return result;
	}
	
	public static Translator createLoginConfigTranslator() {
		GenericTranslator result = new GenericTranslator(LOGIN_CONFIG, WEBAPP_PKG.getWebApp_LoginConfig());
		result.setChildren(new Translator[] {
			new Translator(AUTH_METHOD, WEBAPP_PKG.getLoginConfig_AuthMethod(), Translator.ENUM_FEATURE_WITH_HYPHENS), 
			new Translator(REALM_NAME, WEBAPP_PKG.getLoginConfig_RealmName()), 
			createFormLoginConfigTranslator()
		});
		return result;
	}
	
	public static Translator createFormLoginConfigTranslator() {
		GenericTranslator result = new GenericTranslator(FORM_LOGIN_CONFIG,  WEBAPP_PKG.getLoginConfig_FormLoginConfig());
		result.setChildren(new Translator[] {
			new Translator(FORM_LOGIN_PAGE, WEBAPP_PKG.getFormLoginConfig_FormLoginPage()), 
			new Translator(FORM_ERROR_PAGE, WEBAPP_PKG.getFormLoginConfig_FormErrorPage()), 
		});
		return result;
	}
		
}
