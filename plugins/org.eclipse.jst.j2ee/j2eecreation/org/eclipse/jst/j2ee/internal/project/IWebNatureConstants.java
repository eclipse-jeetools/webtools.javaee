package org.eclipse.jst.j2ee.internal.project;

/*
 * Licensed Material - Property of IBM (C) Copyright IBM Corp. 2002 - All Rights Reserved. US
 * Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP
 * Schedule Contract with IBM Corp.
 */

/*
 * Licensed Material - Property of IBM (C) Copyright IBM Corp. 2001 - All Rights Reserved. US
 * Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP
 * Schedule Contract with IBM Corp.
 */
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;


/*
 * The Web Nature constants are the constants for the web nature interface to the plugins
 */

public interface IWebNatureConstants {

	public static final String WEB_XML_TEMPLATE_SERVLET_2_2 = "web22.xml"; //$NON-NLS-1$
	public static final String WEB_XML_TEMPLATE_SERVLET_2_3 = "web23.xml"; //$NON-NLS-1$
	public static final String JAVA_SOURCE = "JavaSource"; //$NON-NLS-1$

	String J2EE_NATURE_ID = "org.eclipse.jst.j2ee.web.WebNature"; //$NON-NLS-1$
	String PORTLET_NATURE_ID = "com.ibm.etools.portal.tools.PortletProjectNature"; //$NON-NLS-1$
	String STRUTS_NATURE_ID = "com.ibm.etools.struts.StrutsNature";//$NON-NLS-1$
	String JSF_NATURE_ID = "com.ibm.etools.jsf.JSFNature";//$NON-NLS-1$
	public static final String EDIT_MODEL_ID = "com.ibm.wtp.web.editModel"; //$NON-NLS-1$
	String WEB_SERVICE_EDIT_MODEL_ID = "com.ibm.wtp.webservice.web.editModel"; //$NON-NLS-1$
	String DEFAULT_AUTO_INVOKER_PATH = "servlet"; //$NON-NLS-1$
	String DEFAULT_FILE_PATH = ""; //$NON-NLS-1$

	String EXCEPTION_CLASS_NAME = "java.lang.Exception"; //$NON-NLS-1$

	String FILE_ENABLER_NAME = "File"; //$NON-NLS-1$
	String FILE_ENABLER_DISPLAY_NAME = ProjectSupportResourceHandler.getString("File_Serving_Enabler_7"); //$NON-NLS-1$
	String FILE_ENABLER_CLASS_NAME = "com.ibm.servlet.engine.webapp.SimpleFileServlet"; //$NON-NLS-1$
	String FILE_ENABLER_DESCRIPTION = ProjectSupportResourceHandler.getString("Auto_Generated_-_File_Enabler_9"); //$NON-NLS-1$
	String FILE_ENABLER_MAPPING = "/"; //$NON-NLS-1$

	String DEPLOYMENT_DESCRIPTOR_FILE_NAME = "web.xml"; //$NON-NLS-1$
	String BINDINGS_FILE_NAME = "ibm-web-bnd.xmi"; //$NON-NLS-1$
	String EXTENSIONS_FILE_NAME = "ibm-web-ext.xmi"; //$NON-NLS-1$
	String WEBSETTINGS_FILE_NAME = ".websettings"; //$NON-NLS-1$

	String INFO_DIRECTORY = "WEB-INF"; //$NON-NLS-1$
	String META_INFO_DIRECTORY = "META-INF"; //$NON-NLS-1$
	String CLASSES_DIRECTORY = "classes"; //$NON-NLS-1$
	String LIBRARY_DIRECTORY = "lib"; //$NON-NLS-1$
	String IMPORTED_CLASSES_SUFFIX = "_classes.jar"; //$NON-NLS-1$
	String DATABASES_DIRECTORY = "databases"; //$NON-NLS-1$

	String CSS_DIRECTORY = "theme"; //$NON-NLS-1$
	public static final String DEFAULT_CSS_FILE_NAME = "Master.css"; //$NON-NLS-1$

	// These values must be accessed through IBaseWebNature or IJ2EEWebNature
	String WEB_MODULE_DIRECTORY_V4 = "webApplication";//$NON-NLS-1$
	String WEB_MODULE_DIRECTORY_ = "Web Content";//$NON-NLS-1$
	IPath WEB_MODULE_PATH_V4 = new Path(WEB_MODULE_DIRECTORY_V4);
	IPath WEB_MODULE_PATH_ = new Path(WEB_MODULE_DIRECTORY_);
	IPath WEBINF_PATH_V4 = WEB_MODULE_PATH_V4.append(INFO_DIRECTORY);
	IPath WEBINF_PATH_ = WEB_MODULE_PATH_.append(INFO_DIRECTORY);

	String DEFAULT_DOCUMENT_ROOT = ""; //$NON-NLS-1$

	String GENERATED_WEB_APP_DESCRIPTION = ProjectSupportResourceHandler.getString("Generated_by_Web_Tooling_23"); //$NON-NLS-1$

	// keys for the persistent store
	String SOURCE_FOLDER_KEY = "java-source-folder"; //$NON-NLS-1$
	String CLASS_FOLDER_KEY = "class-folder"; //$NON-NLS-1$
	String META_PATH_KEY = "meta-path"; //$NON-NLS-1$

	String SERVLET_JAR = "servlet.jar"; //$NON-NLS-1$
	String WEBAS_JAR = "ibmwebas.jar"; //$NON-NLS-1$

	//Messages used by the web nature classes
	String NO_NATURE_MESSAGE = ProjectSupportResourceHandler.getString("Not_a_web_project_29"); //$NON-NLS-1$
	String NOT_OPEN_MESSAGE = ProjectSupportResourceHandler.getString("A_web_project_must_be_open_and_must_exist_for_properties_to_be_edited_30"); //$NON-NLS-1$

	String WEBTOOLS_PLUGINDIR_VARIABLE = "WEBTOOLS_PLUGINDIR"; //$NON-NLS-1$

	int J2EE_WEB_PROJECT = 1;
	int PORTLET_WEB_PROJECT = 2;

	java.lang.String STATIC_CONTEXT_ROOT = "/"; //$NON-NLS-1$
	String STATIC_NATURE_ID = "org.eclipse.jst.j2ee.web" + ".StaticWebNature"; //$NON-NLS-1$ //$NON-NLS-2$
	int STATIC_WEB_PROJECT = 0;

}