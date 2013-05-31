/*******************************************************************************
 * Copyright (c) 2009, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.plugin;


import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.jst.common.project.facet.core.internal.FacetCorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;

public class JavaEEPreferencesInitializer extends AbstractPreferenceInitializer {

	public interface Keys {
		static final String JAVA_SOURCE = "org.eclipse.jst.j2ee.preference.javaSourceName"; //$NON-NLS-1$

		static final String SERVER_TARGET_SUPPORT = "org.eclipse.jst.j2ee.preference.servertargetsupport"; //$NON-NLS-1$
		static final String CREATE_EJB_CLIENT_JAR = "org.eclipse.jst.j2ee.preference.createClientJar"; //$NON-NLS-1$
		static final String J2EE_VERSION = "org.eclipse.jst.j2ee.ui.preference.j2eeVersion"; //$NON-NLS-1$
		static final String EJB_CLIENT_JAR_CP_COMPATIBILITY = "org.eclipse.jst.j2ee.preference.clientjar.cp.compatibility"; //$NON-NLS-1$
		static final String INCREMENTAL_DEPLOYMENT_SUPPORT = "org.eclipse.jst.j2ee.ui.preference.incrementalDeployment"; //$NON-NLS-1$
		
		final static String USE_EAR_LIBRARIES = "org.eclipse.jst.j2ee.preferences.useEARLibraries";//$NON-NLS-1$
		final static String USE_WEB_APP_LIBRARIES = "org.eclipse.jst.j2ee.preferences.useWebAppLibraries";//$NON-NLS-1$
		final static String USE_EAR_LIBRARIES_JDT_EXPORT = "org.eclipse.jst.j2ee.preferences.useEARLibrariesJDTExport";//$NON-NLS-1$
		/**
		 * @deprecated Do not use. The ALLOW_CLASSPATH_DEP preference has been deprecated and its ability to disable dynamic manifest updates will soon be removed.
		 */
		final static String ALLOW_CLASSPATH_DEP = IProductConstants.ALLOW_CLASSPATH_DEP;
		final static String VALIDATE_DUPLICATE_CLASSPATH_COMPONENT_URI= IProductConstants.VALIDATE_DUPLICATE_CLASSPATH_COMPONENT_URI;

		static final String J2EE_WEB_CONTENT = "org.eclipse.jst.j2ee.preference.j2eeWebContentName"; //$NON-NLS-1$
		static final String STATIC_WEB_CONTENT = "org.eclipse.jst.j2ee.preference.staticWebContentName"; //$NON-NLS-1$
		static final String APPLICATION_CONTENT_FOLDER = IProductConstants.APPLICATION_CONTENT_FOLDER;
		/**
		 * @since 2.0
		 */
		static final String WEB_CONTENT_FOLDER = IProductConstants.WEB_CONTENT_FOLDER;
		/**
		 * @since 2.0
		 */
		static final String EJB_CONTENT_FOLDER = IProductConstants.EJB_CONTENT_FOLDER;
		/**
		 * @since 2.0
		 */
		static final String APP_CLIENT_CONTENT_FOLDER = IProductConstants.APP_CLIENT_CONTENT_FOLDER;
		/**
		 * @since 2.0
		 */
		static final String JCA_CONTENT_FOLDER = IProductConstants.JCA_CONTENT_FOLDER;
		
		/**
		 * @since 2.0
		 */
		static final String ADD_TO_EAR_BY_DEFAULT = IProductConstants.ADD_TO_EAR_BY_DEFAULT;
		/**
		 * @since 2.0
		 */
		static final String APPLICATION_GENERATE_DD = "application_generate_dd"; //$NON-NLS-1$
		/**
		 * @since 2.0
		 */
		static final String DYNAMIC_WEB_GENERATE_DD = IProductConstants.DYNAMIC_WEB_GENERATE_DD;
		/**
		 * @since 3.2
		 */
		static final String EE6_DYNAMIC_WEB_GENERATE_DD = "ee6_dynamic_web_generate_dd"; //$NON-NLS-1$
		/**
		 * @since 3.5
		 */
		static final String EE7_DYNAMIC_WEB_GENERATE_DD = "ee7_dynamic_web_generate_dd"; //$NON-NLS-1$
		/**
		 * @since 2.0
		 */
		static final String EJB_GENERATE_DD = "ejb_generate_dd"; //$NON-NLS-1$
		/**
		 * @since 2.0
		 */
		static final String APP_CLIENT_GENERATE_DD = "app_client_generate_dd"; //$NON-NLS-1$
		/**
		 * @since 3.0
		 */
		static String ID_PERSPECTIVE_HIERARCHY_VIEW = "perspective_hierarchy_view_id"; //$NON-NLS-1$
		/**
		 * @since 3.1
		 */
		static String SHOW_JAVA_EE_MODULE_DEPENDENCY_PAGE = "showJavaEEModuleDependencyPage"; //$NON-NLS-1$
		
		
		/**
		 * 
		 */
		static final String DYN_WEB_SRC_FOLDER = "dynWebSource"; //$NON-NLS-1$
		/**
		 * 
		 */
		
		static final String DYN_WEB_OUTPUT_FOLDER = IProductConstants.DYN_WEB_OUTPUT_FOLDER;
		/**
		 * 
		 */
		static final String EJB_OUTPUT_FOLDER = IProductConstants.EJB_OUTPUT_FOLDER;
		
		/**
		 * 
		 */
	    static final String APP_CLIENT_OUTPUT_FOLDER = IProductConstants.APP_CLIENT_OUTPUT_FOLDER;
	    
		/**
		 * 
		 */
	    static final String JCA_OUTPUT_FOLDER = IProductConstants.JCA_OUTPUT_FOLDER;
	    
	    /**
	     * @since 3.2
	     */
		static final String EE6_CONNECTOR_GENERATE_DD = "ee6_connector_generate_dd"; //$NON-NLS-1$
		
		/**
	     * @since 3.5
	     */
		static final String EE7_CONNECTOR_GENERATE_DD = "ee7_connector_generate_dd"; //$NON-NLS-1$
		
		/**
		 * Used to determine if the business interface annotations should be added to the bean class during EJB creation. 
		 */
		static final String EJB_BUSINESS_INTERFACE_ANNOTATION_IN_BEAN = IProductConstants.EJB_BUSINESS_INTERFACE_ANNOTATION_IN_BEAN;
		/**
		 * Used to determine if the business interface annotations should be to the business interfaces during EJB creation. 
		 */
		static final String EJB_BUSINESS_INTERFACE_ANNOTATION_IN_INTERFACE = IProductConstants.EJB_BUSINESS_INTERFACE_ANNOTATION_IN_INTERFACE;
		
		/**
		 * Indicates a suffix that should be used for the package of all the interfaces when creating session beans.
		 * For example, if the qualified bean class name is com.test.Bean1, and the value of #EJB_INTERFACE_PACKAGE_SUFFIX is 
		 * test2, then the default local business interface name will be com.test.test2.Bean1Local 
		 * Default value is an empty string.  
		 */
		static final String EJB_INTERFACE_PACKAGE_SUFFIX = IProductConstants.EJB_INTERFACE_PACKAGE_SUFFIX;

		

	}

	public interface Values {
		final static String J2EE_VERSION_1_2 = "J2EE_1_2"; //$NON-NLS-1$
		final static String J2EE_VERSION_1_3 = "J2EE_1_3"; //$NON-NLS-1$
		final static String J2EE_VERSION_1_4 = "J2EE_1_4"; //$NON-NLS-1$

		/**
		 * @deprecated, see initializeDefaultPreferences() it uses ProductManager
		 */
		final static String J2EE_WEB_CONTENT = ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER);
		/**
		 * @deprecated, see initializeDefaultPreferences() it uses ProductManager
		 */
		final static String STATIC_WEB_CONTENT = ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER);
		/**
		 * @deprecated, use CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER
		 */
		final static String JAVA_SOURCE = CommonFrameworksPlugin.getDefault().getPluginPreferences().getString(CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER);
	}

	public interface Defaults {

		/**
		 * @deprecated, see initializeDefaultPreferences() it uses ProductManager
		 */
		final static String J2EE_WEB_CONTENT = Values.J2EE_WEB_CONTENT;
		/**
		 * @deprecated, see initializeDefaultPreferences() it uses ProductManager
		 */
		final static String STATIC_WEB_CONTENT = Values.STATIC_WEB_CONTENT;
		/**
		 * @deprecated, see DEFAULT_SOURCE_FOLDER
		 */
		final static String JAVA_SOURCE = Values.JAVA_SOURCE;
		final static String J2EE_VERSION = Values.J2EE_VERSION_1_4;
		final static int J2EE_VERSION_ID = J2EEVersionConstants.J2EE_1_4_ID;
		final static boolean CREATE_EJB_CLIENT_JAR = false;
		final static boolean EJB_CLIENT_JAR_CP_COMPATIBILITY = true;
		final static boolean INCREMENTAL_DEPLOYMENT_SUPPORT = true;
		final static boolean USE_EAR_LIBRARIES_JDT_EXPORT = false;
		final static String ID_PERSPECTIVE_HIERARCHY_VIEW = "org.eclipse.ui.navigator.ProjectExplorer"; //$NON-NLS-1$
		/**
		 * @deprecated Do not use. The ALLOW_CLASSPATH_DEP preference has been deprecated and its ability to disable dynamic manifest updates will soon be removed.
		 */
		final static boolean ALLOW_CLASSPATH_DEP = true;
		final static boolean VALIDATE_DUPLICATE_CLASSPATH_COMPONENT_URI = true;
		final static boolean SHOW_JAVA_EE_MODULE_DEPENDENCY_PAGE = true;
		public static final String STRING_DEFAULT_DEFAULT = ""; //$NON-NLS-1$
		public static final boolean BOOLEAN_DEFAULT_DEFAULT = false;
		final static boolean DYNAMIC_WEB_GENERATE_DD = false;
		final static boolean EE6_CONNECTOR_GENERATE_DD = false;
		final static boolean EE7_CONNECTOR_GENERATE_DD = false;
		final static boolean EJB_BUSINESS_INTERFACE_ANNOTATION_IN_BEAN = false;
		final static boolean EJB_BUSINESS_INTERFACE_ANNOTATION_IN_INTERFACE = true;
	}
	
	@Override
	public void initializeDefaultPreferences() {
		
		IEclipsePreferences node = new DefaultScope().getNode(J2EEPlugin.PLUGIN_ID);
		
		node.put(Keys.J2EE_VERSION, Defaults.J2EE_VERSION);
		node.putBoolean(Keys.CREATE_EJB_CLIENT_JAR, Defaults.CREATE_EJB_CLIENT_JAR);
		node.putBoolean(Keys.EJB_CLIENT_JAR_CP_COMPATIBILITY, Defaults.EJB_CLIENT_JAR_CP_COMPATIBILITY);
		node.putBoolean(Keys.INCREMENTAL_DEPLOYMENT_SUPPORT, Defaults.INCREMENTAL_DEPLOYMENT_SUPPORT);
		
		// since 2.0
		node.put(Keys.J2EE_WEB_CONTENT, ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER));
		node.put(Keys.STATIC_WEB_CONTENT, ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER));
		// since 2.0
		node.put(Keys.JAVA_SOURCE, FacetCorePlugin.getJavaSrcFolder());
		// done in CommonFrameworksPref..Initializer
		//node.put(Keys.DEFAULT_SOURCE_FOLDER, ProductManager.getProperty(IProductConstants.DEFAULT_SOURCE_FOLDER));
		node.put(Keys.APPLICATION_CONTENT_FOLDER, ProductManager.getProperty(IProductConstants.APPLICATION_CONTENT_FOLDER));
		node.put(Keys.WEB_CONTENT_FOLDER, ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER));
		node.put(Keys.APP_CLIENT_CONTENT_FOLDER, ProductManager.getProperty(IProductConstants.APP_CLIENT_CONTENT_FOLDER));
		node.put(Keys.EJB_CONTENT_FOLDER, ProductManager.getProperty(IProductConstants.EJB_CONTENT_FOLDER));
		node.put(Keys.JCA_CONTENT_FOLDER, ProductManager.getProperty(IProductConstants.JCA_CONTENT_FOLDER));
		node.put(Keys.ADD_TO_EAR_BY_DEFAULT, ProductManager.getProperty(IProductConstants.ADD_TO_EAR_BY_DEFAULT));
		// done in CommonFrameworksPref..Initializer
		//node.put(Keys.OUTPUT_FOLDER, ProductManager.getProperty(IProductConstants.OUTPUT_FOLDER));
		
		// since 2.0, for java ee projects
		node.putBoolean(Keys.APPLICATION_GENERATE_DD, false);
		// for ee5 jee web projects default it to true so that we can create servlets, otherwise false
		String generateDynamicWebDD = ProductManager.getProperty(IProductConstants.DYNAMIC_WEB_GENERATE_DD);
		boolean generateDynamicWebDDDefault = (generateDynamicWebDD != null) ? Boolean.parseBoolean(generateDynamicWebDD) : Defaults.DYNAMIC_WEB_GENERATE_DD;
		node.putBoolean(Keys.DYNAMIC_WEB_GENERATE_DD, generateDynamicWebDDDefault);
		node.putBoolean(Keys.EE6_DYNAMIC_WEB_GENERATE_DD, false);
		node.putBoolean(Keys.EJB_GENERATE_DD, false);
		node.putBoolean(Keys.APP_CLIENT_GENERATE_DD, false);	
		String ee6ConnectorGenerateDD = ProductManager.getProperty(IProductConstants.EE6_CONNECTOR_GENERATE_DD);
		boolean ee6ConnectorGenerateDDDefault = (ee6ConnectorGenerateDD != null) ? Boolean.parseBoolean(ee6ConnectorGenerateDD) : Defaults.EE6_CONNECTOR_GENERATE_DD;
		node.putBoolean(Keys.EE6_CONNECTOR_GENERATE_DD, ee6ConnectorGenerateDDDefault);
		String ee7ConnectorGenerateDD = ProductManager.getProperty(IProductConstants.EE7_CONNECTOR_GENERATE_DD);
		boolean ee7ConnectorGenerateDDDefault = (ee7ConnectorGenerateDD != null) ? Boolean.parseBoolean(ee7ConnectorGenerateDD) : Defaults.EE7_CONNECTOR_GENERATE_DD;
		node.putBoolean(Keys.EE7_CONNECTOR_GENERATE_DD, ee7ConnectorGenerateDDDefault);
		
		node.putBoolean(Keys.USE_EAR_LIBRARIES, true);
		node.putBoolean(Keys.USE_WEB_APP_LIBRARIES, true);
		node.putBoolean(Keys.USE_EAR_LIBRARIES_JDT_EXPORT, Defaults.USE_EAR_LIBRARIES_JDT_EXPORT);
		String perspectiveID = ProductManager.getProperty(IProductConstants.ID_PERSPECTIVE_HIERARCHY_VIEW);
		node.put(Keys.ID_PERSPECTIVE_HIERARCHY_VIEW, (perspectiveID != null) ? perspectiveID : Defaults.ID_PERSPECTIVE_HIERARCHY_VIEW);
		String allowClasspathDep = ProductManager.getProperty(IProductConstants.ALLOW_CLASSPATH_DEP);
		boolean allowClasspathDepDefault = (allowClasspathDep != null) ? Boolean.parseBoolean(allowClasspathDep) : Defaults.ALLOW_CLASSPATH_DEP;
		node.putBoolean(Keys.ALLOW_CLASSPATH_DEP, allowClasspathDepDefault);
		String validateDupClasspathCompURIs = ProductManager.getProperty(IProductConstants.VALIDATE_DUPLICATE_CLASSPATH_COMPONENT_URI);
		boolean validateDupClasspathCompURIsDefault = (validateDupClasspathCompURIs != null) ? Boolean.parseBoolean(validateDupClasspathCompURIs) : Defaults.VALIDATE_DUPLICATE_CLASSPATH_COMPONENT_URI;
		node.putBoolean(Keys.VALIDATE_DUPLICATE_CLASSPATH_COMPONENT_URI, validateDupClasspathCompURIsDefault);
		String showJavaEEModuleDependencyPage = ProductManager.getProperty(IProductConstants.SHOW_JAVA_EE_MODULE_DEPENDENCY_PAGE);
		boolean showJavaEEModuleDependencyPageDefault = (showJavaEEModuleDependencyPage != null) ? Boolean.parseBoolean(showJavaEEModuleDependencyPage) : Defaults.SHOW_JAVA_EE_MODULE_DEPENDENCY_PAGE;
		node.putBoolean(Keys.SHOW_JAVA_EE_MODULE_DEPENDENCY_PAGE, showJavaEEModuleDependencyPageDefault);
		
		node.put(Keys.DYN_WEB_SRC_FOLDER, getDynamicWebDefaultSourceFolder());
		node.put(Keys.DYN_WEB_OUTPUT_FOLDER, getDynamicWebDefaultOuputFolderName());
		node.put(Keys.APP_CLIENT_OUTPUT_FOLDER,  getAppClientDefaultOutputFolderName() );
		node.put(Keys.EJB_OUTPUT_FOLDER, getEJBDefaultOutputFolderName() );
		node.put(Keys.JCA_OUTPUT_FOLDER, getJCADefaultOutputFolderName() );
		
		String ejbBusinessInterfaceAnnotationInBean = ProductManager.getProperty(IProductConstants.EJB_BUSINESS_INTERFACE_ANNOTATION_IN_BEAN);
		boolean  ejbBusinessInterfaceAnnotationInBeanDefault = (ejbBusinessInterfaceAnnotationInBean != null )? Boolean.parseBoolean(ejbBusinessInterfaceAnnotationInBean) : Defaults.EJB_BUSINESS_INTERFACE_ANNOTATION_IN_BEAN;
		node.putBoolean(Keys.EJB_BUSINESS_INTERFACE_ANNOTATION_IN_BEAN, ejbBusinessInterfaceAnnotationInBeanDefault);
		
		String ejbBusinessInterfaceAnnotationInInterface = ProductManager.getProperty(IProductConstants.EJB_BUSINESS_INTERFACE_ANNOTATION_IN_INTERFACE);
		boolean  ejbBusinessInterfaceAnnotationInInterfaceDefault = (ejbBusinessInterfaceAnnotationInInterface != null )? Boolean.parseBoolean(ejbBusinessInterfaceAnnotationInInterface) : Defaults.EJB_BUSINESS_INTERFACE_ANNOTATION_IN_INTERFACE;
		node.putBoolean(Keys.EJB_BUSINESS_INTERFACE_ANNOTATION_IN_INTERFACE, ejbBusinessInterfaceAnnotationInInterfaceDefault);
		
		node.put(Keys.EJB_INTERFACE_PACKAGE_SUFFIX, ProductManager.getProperty(IProductConstants.EJB_INTERFACE_PACKAGE_SUFFIX));
		}

	
	/**
	 * This method should not intended to be used anywhere outside this class. 
	 * This will be made private once the deprecated J2EEPreferences class is deleted 
	 */
	static String getDynamicWebDefaultSourceFolder(){
		return getDefaultJavaSrcFolder();
	}
	

	/**
	 * This method should not intended to be used anywhere outside this class. 
	 * This will be made private once the deprecated J2EEPreferences class is deleted 
	 */
	static String getDefaultJavaSrcFolder(){
		String srcFolder = FacetCorePlugin.getDefault().getPluginPreferences().getDefaultString(FacetCorePlugin.PROD_PROP_SOURCE_FOLDER_LEGACY);
		if( srcFolder == null || srcFolder.equals("") ){ //$NON-NLS-1$
			if( Platform.getProduct() != null ){
				srcFolder = Platform.getProduct().getProperty( "defaultJavaSourceFolder" ); //$NON-NLS-1$
			    if( srcFolder == null || srcFolder.equals("")){ //$NON-NLS-1$
			    	srcFolder = Platform.getProduct().getProperty( FacetCorePlugin.PROD_PROP_SOURCE_FOLDER_LEGACY );
			    }      			
			}
	    	if( srcFolder == null || srcFolder.equals("") ){ //$NON-NLS-1$
	    		srcFolder = FacetCorePlugin.DEFAULT_SOURCE_FOLDER;
	    	}
	
		}
	    return srcFolder;
	}
	
	
	/**
	 * This method should not intended to be used anywhere outside this class. 
	 * This will be made private once the deprecated J2EEPreferences class is deleted 
	 */
	static String getDynamicWebDefaultOuputFolderName(){
		if ( ProductManager.shouldUseSingleRootStructure() ){
			return ProductManager.getProperty(IProductConstants.WEB_CONTENT_FOLDER) + "/"+ J2EEConstants.WEB_INF_CLASSES; //$NON-NLS-1$
		}
		return getDefaultOutputFolderName();
	}
	
	/**
	 * This method should not intended to be used anywhere outside this class. 
	 * This will be made private once the deprecated J2EEPreferences class is deleted 
	 */
	static String getAppClientDefaultOutputFolderName(){
		return getDefaultOutputFolderName();
	}
	
	/**
	 * This method should not intended to be used anywhere outside this class. 
	 * This will be made private once the deprecated J2EEPreferences class is deleted 
	 */
	static String getEJBDefaultOutputFolderName(){
		return getDefaultOutputFolderName();
	}
	
	/**
	 * This method should not intended to be used anywhere outside this class. 
	 * This will be made private once the deprecated J2EEPreferences class is deleted 
	 */
	static String getJCADefaultOutputFolderName(){
		return getDefaultOutputFolderName();
	}
	
	/**
	 * This method should not intended to be used anywhere outside this class. 
	 * This will be made private once the deprecated J2EEPreferences class is deleted 
	 */
	static String getString(String name) {
		IPreferencesService preferencesService = Platform.getPreferencesService();
		IScopeContext[] lookupOrder = new IScopeContext[]{new InstanceScope(), new DefaultScope()};
		return preferencesService.getString(J2EEPlugin.PLUGIN_ID, name, Defaults.STRING_DEFAULT_DEFAULT, lookupOrder);
		}

	/**
	 * This method should not intended to be used anywhere outside this class. 
	 * This will be made private once the deprecated J2EEPreferences class is deleted 
	 */
	static String getDefaultOutputFolderName(){
	    String outputFolder = getProductProperty( "defaultJavaOutputFolder" ); //$NON-NLS-1$
	    if( outputFolder == null ){
	        outputFolder = getProductProperty( "outputFolder" ); //$NON-NLS-1$
	    }
	    
	    if( outputFolder == null )
	   {
	        outputFolder = FacetCorePlugin.DEFUALT_OUTPUT_FOLDER;
	    }
	    return outputFolder;
	}
	
	/**
	 * This method should not intended to be used anywhere outside this class. 
	 * This will be made private once the deprecated J2EEPreferences class is deleted 
	 */
	private static String getProductProperty( final String propName ){
	    String value = null;
	    if( Platform.getProduct() != null ){
	        value = Platform.getProduct().getProperty( propName );
	    }
	    return value;
	}
	

	/**
	 * Returns the default value for the boolean-valued property
	 * with the given name.
	 * Returns the default-default value (<code>false</code>) if there
	 * is no default property with the given name, or if the default 
	 * value cannot be treated as a boolean.
	 * The given name must not be <code>null</code>.
	 *
	 * @param name the name of the property
	 * @return the default value of the named property. If default value 
	 * is not set, a false will be returned
	 */
	public static boolean getDefaultBoolean(String name) {
		return new DefaultScope().getNode(J2EEPlugin.PLUGIN_ID).getBoolean(name, Defaults.BOOLEAN_DEFAULT_DEFAULT);
	}
	
	/**
	 * Returns the default value for the string-valued property
	 * with the given name.
	 * Returns the default-default value (the empty string <code>""</code>) 
	 * is no default property with the given name, or if the default 
	 * value cannot be treated as a string.
	 * The given name must not be <code>null</code>.
	 *
	 * @param name the name of the property
	 * @return the default value of the named property
	 */
	public static String getDefaultString(String name) {
		return new DefaultScope().getNode(J2EEPlugin.PLUGIN_ID).get(name, Defaults.STRING_DEFAULT_DEFAULT);
	}
}
