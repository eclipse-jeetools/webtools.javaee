/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Kaloyan Raev, kaloyan.raev@sap.com - bug 218496
 *******************************************************************************/
/*
 * Created on May 13, 2004
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.operations.NewFilterClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.operations.NewListenerClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModelProvider;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.WebUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.jee.model.tests.SynchronousModelChangedListener;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;

public class AddWebArtifactOperationTest extends OperationTestCase implements
		INewJavaClassDataModelProperties {
	
    public static final String WEB_PROJECT_NAME = "WebProject"; //$NON-NLS-1$
    public static final String EAR_PROJECT_NAME = "EarProject"; //$NON-NLS-1$
    
    public static final String WEB_CONTEXT_ROOT = "WebRoot"; //$NON-NLS-1$
    
    public static final String PACKAGE = "test"; //$NON-NLS-1$
    
    public static final String SERVLET_NAME = "TestServlet"; //$NON-NLS-1$
    public static final String SERVLET_CLASS_NAME = PACKAGE + "." + SERVLET_NAME; //$NON-NLS-1$
    public static final String SERVLET_DEFAULT_MAPPING = "/" + SERVLET_NAME; //$NON-NLS-1$
    
    public static final String FILTER_NAME = "TestFilter"; //$NON-NLS-1$
    public static final String FILTER_CLASS_NAME = PACKAGE + "." + FILTER_NAME; //$NON-NLS-1$
    public static final String FILTER_DEFAULT_MAPPING = "/" + FILTER_NAME; //$NON-NLS-1$
    
    public static final String LISTENER_NAME = "TestListener"; //$NON-NLS-1$
    public static final String LISTENER_CLASS_NAME = PACKAGE + "." + LISTENER_NAME; //$NON-NLS-1$
	private IModelProvider mergedModelProvider;
	
    /**
	 * @param name
	 */
	public AddWebArtifactOperationTest(String name) {
		super(name);
	}

	/**
	 * Default constructor
	 */
	public AddWebArtifactOperationTest() {
		super();
	}

	public static Test suite() {
        return new TestSuite(AddWebArtifactOperationTest.class);
    }
	
	public void testAddServlet_Web24_Defaults_NoJETEmitter() throws Exception {
		disableJETEmitter();
		testAddServlet_Web24_Defaults();
		enableJETEmitter();
	}

    public void testAddServlet_Web24_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_24);
    	WebArtifactEdit webEdit = null;
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);
    	try {
    		webEdit = new WebArtifactEdit(proj, false, true);
    		WebApp webApp = webEdit.getWebApp();
        	addServlet_Defaults();
        	if (webApp != null) {
        		assertJavaFileExists(SERVLET_CLASS_NAME);
        		
        		Servlet servlet = webApp.getServletNamed(SERVLET_NAME);
        		assertNotNull("Servlet " + SERVLET_NAME + " not found in the model", servlet);
        		assertEquals("Servlet points to an unexpected deployment descriptor object", 
        				webApp, servlet.getWebApp());
        		assertEquals("Servlet name is expected to be " + SERVLET_NAME + ", but it is " + servlet.getServletName(), 
        				SERVLET_NAME, servlet.getServletName());
        		assertEquals("Display name is expected to be " + SERVLET_NAME + ", but it is " + servlet.getDisplayName(), 
        				SERVLET_NAME, servlet.getDisplayName());
        		
        		List params = servlet.getInitParams();
        		assertNotNull("List of initialization parameters cannot be retrieved", params);
        		assertEquals("None initialization parameter is expected, but " + params.size() + " are found", 
        				0, params.size());
        		
        		List mappings = servlet.getMappings();
        		assertEquals(1, mappings.size());
        		ServletMapping mapping = (ServletMapping) mappings.get(0);
        		assertEquals("Servlet name of the mapping is expected to be " + SERVLET_NAME + ", but it is " + mapping.getName(), 
        				SERVLET_NAME, mapping.getName());
        		assertEquals("Servlet mapping URL pattern value is expected to be " + SERVLET_DEFAULT_MAPPING + ", but it is " + mapping.getUrlPattern(),
        				SERVLET_DEFAULT_MAPPING, mapping.getUrlPattern());
        		assertEquals("Servlet mapping is expected to point to servlet " + servlet.getServletName() + ", but it points to servlet " + mapping.getServlet().getServletName(), 
        				servlet, mapping.getServlet());
        	}
    	} finally {
    		if (webEdit != null)
    			webEdit.dispose();
    	}
    }
	
	public void testAddServlet_Web25_Defaults_NoJETEmitter() throws Exception {
		disableJETEmitter();
		testAddServlet_Web25_Defaults();
		enableJETEmitter();
	}

    public void testAddServlet_Web25_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_25);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	IModelProvider provider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) provider.getModelObject();
		
    	addServlet_Defaults();
    	
    	assertJavaFileExists(SERVLET_CLASS_NAME);
    	
    	List servlets = webApp.getServlets();
    	assertEquals("Exactly one filter is expected in the model, but " + servlets.size() + " are found", 
    			1, servlets.size());
    	org.eclipse.jst.javaee.web.Servlet servlet = (org.eclipse.jst.javaee.web.Servlet) servlets.get(0);
    	assertEquals("Servlet name is expected to be " + SERVLET_NAME + ", but it is " + servlet.getServletName(), 
    			SERVLET_NAME, servlet.getServletName());
    	assertEquals("Servlet class name is expected to be " + SERVLET_CLASS_NAME + ", but it is " + servlet.getServletClass(), 
    			SERVLET_CLASS_NAME, servlet.getServletClass());
    	List displayNames = servlet.getDisplayNames();
    	assertEquals("Exactly one display name is expected, but " + displayNames.size() + " are found", 
    			1, displayNames.size());
    	DisplayName displayName = (DisplayName) displayNames.get(0);
    	assertEquals("Display name is expected to be " + SERVLET_NAME + ", but it is " + displayName.getValue(), 
    			SERVLET_NAME, displayName.getValue());
		
		List params = servlet.getInitParams();
		assertNotNull("List of initialization parameters cannot be retrieved", params);
		assertEquals("None initialization parameter is expected, but " + params.size() + " are found", 
				0, params.size());
    	
    	List mappings = webApp.getServletMappings();
    	assertEquals("Exactly one servlet mapping is expected, but " + mappings.size() + " are found", 
    			1, mappings.size());
    	org.eclipse.jst.javaee.web.ServletMapping mapping = (org.eclipse.jst.javaee.web.ServletMapping) mappings.get(0);
    	assertEquals("Servlet name of the mapping is expected to be " + SERVLET_NAME + ", but it is " + mapping.getServletName(), 
    			SERVLET_NAME, mapping.getServletName());
    	List urlPatterns = mapping.getUrlPatterns();
    	assertEquals("Exactly one URL pattern is expected in the mapping, but " + urlPatterns.size() + " are found", 
    			1, urlPatterns.size());
    	UrlPatternType urlPattern = (UrlPatternType) urlPatterns.get(0);
    	assertEquals("Servlet mapping URL pattern value is expected to be " + SERVLET_DEFAULT_MAPPING + ", but it is " + urlPattern.getValue(), 
    			SERVLET_DEFAULT_MAPPING, urlPattern.getValue());
    }
    
    
    public void testAddServlet_Web30_Defaults() throws Exception{
       	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_30);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	mergedModelProvider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mergedModelProvider.getModelObject();
		
    	addServlet_Defaults_Annotations();
    	
    	assertJavaFileExists(SERVLET_CLASS_NAME);  	
    	
       	List servlets = webApp.getServlets();
    	assertEquals("Exactly one servlet is expected in the model, but " + servlets.size() + " are found", 
    			1, servlets.size());
    	org.eclipse.jst.javaee.web.Servlet servlet = (org.eclipse.jst.javaee.web.Servlet) servlets.get(0);
    	assertEquals("Servlet name is expected to be " + SERVLET_NAME + ", but it is " + servlet.getServletName(), 
    			SERVLET_NAME, servlet.getServletName());
    	assertEquals("Servlet class name is expected to be " + SERVLET_CLASS_NAME + ", but it is " + servlet.getServletClass(), 
    			SERVLET_CLASS_NAME, servlet.getServletClass());
		
		List params = servlet.getInitParams();
		assertNotNull("List of initialization parameters cannot be retrieved", params);
		assertEquals("None initialization parameter is expected, but " + params.size() + " are found", 
				0, params.size());
    	
    	List mappings = webApp.getServletMappings();
    	assertEquals("Exactly one servlet mapping is expected, but " + mappings.size() + " are found", 
    			1, mappings.size());
    	org.eclipse.jst.javaee.web.ServletMapping mapping = (org.eclipse.jst.javaee.web.ServletMapping) mappings.get(0);
    	assertEquals("Servlet name of the mapping is expected to be " + SERVLET_NAME + ", but it is " + mapping.getServletName(), 
    			SERVLET_NAME, mapping.getServletName());
    	List urlPatterns = mapping.getUrlPatterns();
    	assertEquals("Exactly one URL pattern is expected in the mapping, but " + urlPatterns.size() + " are found", 
    			1, urlPatterns.size());
    	UrlPatternType urlPattern = (UrlPatternType) urlPatterns.get(0);
    	assertEquals("Servlet mapping URL pattern value is expected to be " + SERVLET_DEFAULT_MAPPING + ", but it is " + urlPattern.getValue(), 
    			SERVLET_DEFAULT_MAPPING, urlPattern.getValue());    	   	
    }
    
    
    public void testAddServlet_Web30_Async() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_30);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	mergedModelProvider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mergedModelProvider.getModelObject();
		
    	addServlet_Async();
    	
    	assertJavaFileExists(SERVLET_CLASS_NAME);  	
    	
       	List servlets = webApp.getServlets();
    	assertEquals("Exactly one servlet is expected in the model, but " + servlets.size() + " are found", 
    			1, servlets.size());
    	org.eclipse.jst.javaee.web.Servlet servlet = (org.eclipse.jst.javaee.web.Servlet) servlets.get(0);
    	assertEquals("Servlet name is expected to be " + SERVLET_NAME + ", but it is " + servlet.getServletName(), 
    			SERVLET_NAME, servlet.getServletName());
    	assertEquals("Servlet class name is expected to be " + SERVLET_CLASS_NAME + ", but it is " + servlet.getServletClass(), 
    			SERVLET_CLASS_NAME, servlet.getServletClass());
		
		List params = servlet.getInitParams();
		assertNotNull("List of initialization parameters cannot be retrieved", params);
		assertEquals("None initialization parameter is expected, but " + params.size() + " are found", 
				0, params.size());
    	
    	List mappings = webApp.getServletMappings();
    	assertEquals("Exactly one servlet mapping is expected, but " + mappings.size() + " are found", 
    			1, mappings.size());
    	org.eclipse.jst.javaee.web.ServletMapping mapping = (org.eclipse.jst.javaee.web.ServletMapping) mappings.get(0);
    	assertEquals("Servlet name of the mapping is expected to be " + SERVLET_NAME + ", but it is " + mapping.getServletName(), 
    			SERVLET_NAME, mapping.getServletName());
    	List urlPatterns = mapping.getUrlPatterns();
    	assertEquals("Exactly one URL pattern is expected in the mapping, but " + urlPatterns.size() + " are found", 
    			1, urlPatterns.size());
    	UrlPatternType urlPattern = (UrlPatternType) urlPatterns.get(0);
    	assertEquals("Servlet mapping URL pattern value is expected to be " + SERVLET_DEFAULT_MAPPING + ", but it is " + urlPattern.getValue(), 
    			SERVLET_DEFAULT_MAPPING, urlPattern.getValue());    	
    	
    	
    	assertTrue(servlet.isAsyncSupported());
    }
    
    public void testAddServlet_Web31_Async() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_31);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	mergedModelProvider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mergedModelProvider.getModelObject();
		
    	addServlet_Async();
    	
    	assertJavaFileExists(SERVLET_CLASS_NAME);  	
    	
       	List servlets = webApp.getServlets();
    	assertEquals("Exactly one servlet is expected in the model, but " + servlets.size() + " are found", 
    			1, servlets.size());
    	org.eclipse.jst.javaee.web.Servlet servlet = (org.eclipse.jst.javaee.web.Servlet) servlets.get(0);
    	assertEquals("Servlet name is expected to be " + SERVLET_NAME + ", but it is " + servlet.getServletName(), 
    			SERVLET_NAME, servlet.getServletName());
    	assertEquals("Servlet class name is expected to be " + SERVLET_CLASS_NAME + ", but it is " + servlet.getServletClass(), 
    			SERVLET_CLASS_NAME, servlet.getServletClass());
		
		List params = servlet.getInitParams();
		assertNotNull("List of initialization parameters cannot be retrieved", params);
		assertEquals("None initialization parameter is expected, but " + params.size() + " are found", 
				0, params.size());
    	
    	List mappings = webApp.getServletMappings();
    	assertEquals("Exactly one servlet mapping is expected, but " + mappings.size() + " are found", 
    			1, mappings.size());
    	org.eclipse.jst.javaee.web.ServletMapping mapping = (org.eclipse.jst.javaee.web.ServletMapping) mappings.get(0);
    	assertEquals("Servlet name of the mapping is expected to be " + SERVLET_NAME + ", but it is " + mapping.getServletName(), 
    			SERVLET_NAME, mapping.getServletName());
    	List urlPatterns = mapping.getUrlPatterns();
    	assertEquals("Exactly one URL pattern is expected in the mapping, but " + urlPatterns.size() + " are found", 
    			1, urlPatterns.size());
    	UrlPatternType urlPattern = (UrlPatternType) urlPatterns.get(0);
    	assertEquals("Servlet mapping URL pattern value is expected to be " + SERVLET_DEFAULT_MAPPING + ", but it is " + urlPattern.getValue(), 
    			SERVLET_DEFAULT_MAPPING, urlPattern.getValue());    	
    	
    	
    	assertTrue(servlet.isAsyncSupported());
    }    
    
    
    public void testAddFilter_Web24_Defaults_NoJETEmitter() throws Exception {
		disableJETEmitter();
		testAddFilter_Web24_Defaults();
		enableJETEmitter();
	}
    
    public void testAddFilter_Web24_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_24);
    	WebArtifactEdit webEdit = null;
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);
    	try {
    		webEdit = new WebArtifactEdit(proj, false, true);
    		WebApp webApp = webEdit.getWebApp();
        	addFilter_Defaults();
        	if (webApp != null) {
        		assertJavaFileExists(FILTER_CLASS_NAME);
        		
        		Filter filter = webApp.getFilterNamed(FILTER_NAME);
        		assertNotNull("Filter " + FILTER_NAME + " not found in the model", filter);
        		assertEquals("Filter name is expected to be " + FILTER_NAME + ", but it is " + filter.getName(), 
        				FILTER_NAME, filter.getName());
        		assertEquals("Display name is expected to be " + FILTER_NAME + ", but it is " + filter.getDisplayName(), 
        				FILTER_NAME, filter.getDisplayName());
        		assertEquals("Filter class name is expected to be " + FILTER_CLASS_NAME + ", but it is " + filter.getFilterClassName(), 
        				FILTER_CLASS_NAME, filter.getFilterClassName());
        		
        		List params = filter.getInitParams();
        		assertNotNull("List of initialization parameters cannot be retrieved", params);
        		assertEquals("None initialization parameter is expected, but " + params.size() + " are found", 
        				0, params.size());
        		
        		List mappings = webApp.getFilterMappings();
        		assertEquals("Exactly one filter mapping is expected, but " + mappings.size() + " are found", 
        				1, mappings.size());
        		FilterMapping mapping = (FilterMapping) mappings.get(0);
        		assertEquals("Filter of the mapping is expected to be " + filter.getName() + ", but it is " + mapping.getFilter().getName(), 
        				filter, mapping.getFilter());
        		assertEquals("Filter mapping URL pattern value is expected to be " + FILTER_DEFAULT_MAPPING + ", but it is " + mapping.getUrlPattern(), 
        				FILTER_DEFAULT_MAPPING, mapping.getUrlPattern());
        		assertEquals("The filter mapping is not expected to point to a servlet, but it points to servlet " + mapping.getServlet(), 
        				null, mapping.getServlet());
        		assertEquals("The filter mapping is not expected to point to a servlet, but it points to servlet " + mapping.getServlet(),
        				null, mapping.getServletName());
        		List dispatchers = mapping.getDispatcherType();
        		assertEquals("None dispatcher is expected in the filter mapping, but " + dispatchers.size() + " are found", 
        				0, dispatchers.size());
        	}
    	} finally {
    		if (webEdit != null)
    			webEdit.dispose();
    	}
    }
    
    public void testAddFilter_Web25_Defaults_NoJETEmitter() throws Exception {
		disableJETEmitter();
		testAddFilter_Web25_Defaults();
		enableJETEmitter();
	}
    
    public void testAddFilter_Web25_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_25);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	IModelProvider provider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) provider.getModelObject();
		
    	addFilter_Defaults();
    	
		assertJavaFileExists(FILTER_CLASS_NAME);
    	
    	List filters = webApp.getFilters();
    	assertEquals("Exactly one filter is expected in the model, but " + filters.size() + " are found", 
    			1, filters.size());
    	org.eclipse.jst.javaee.web.Filter filter = (org.eclipse.jst.javaee.web.Filter) filters.get(0);
    	assertEquals("Filter name is expected to be " + FILTER_NAME + ", but it is " + filter.getFilterName(), 
    			FILTER_NAME, filter.getFilterName());
    	assertEquals("Filter class name is expected to be " + FILTER_CLASS_NAME + ", but it is " + filter.getFilterClass(), 
    			FILTER_CLASS_NAME, filter.getFilterClass());
    	List displayNames = filter.getDisplayNames();
    	assertEquals("Exactly one display name is expected, but " + displayNames.size() + " are found", 
    			1, displayNames.size());
    	DisplayName displayName = (DisplayName) displayNames.get(0);
    	assertEquals("Display name is expected to be " + FILTER_NAME + ", but it is " + displayName.getValue(), 
    			FILTER_NAME, displayName.getValue());
    	
    	List params = filter.getInitParams();
		assertNotNull("List of initialization parameters cannot be retrieved", params);
		assertEquals("None initialization parameter is expected, but " + params.size() + " are found", 
				0, params.size());
    	
    	List mappings = webApp.getFilterMappings();
    	assertEquals("Exactly one filter mapping is expected, but " + mappings.size() + " are found", 
    			1, mappings.size());
    	org.eclipse.jst.javaee.web.FilterMapping mapping = (org.eclipse.jst.javaee.web.FilterMapping) mappings.get(0);
    	assertEquals("Filter name of the mapping is expected to be " + FILTER_NAME + ", but it is " + mapping.getFilterName(), 
    			FILTER_NAME, mapping.getFilterName());
    	List urlPatterns = mapping.getUrlPatterns();
    	assertEquals("Exactly one URL pattern is expected in the mapping, but " + urlPatterns.size() + " are found", 
    			1, urlPatterns.size());
    	UrlPatternType urlPattern = (UrlPatternType) urlPatterns.get(0);
    	assertEquals("Filter mapping URL pattern value is expected to be " + FILTER_DEFAULT_MAPPING + ", but it is " + urlPattern.getValue(), 
    			FILTER_DEFAULT_MAPPING, urlPattern.getValue());
    	assertEquals("None servlet name is expected in the filter mapping, but " + mapping.getServletNames().size() + " are found", 
    			0, mapping.getServletNames().size());
    	assertEquals("None dispatcher is expected in the filter mapping, but " + mapping.getDispatchers().size() + " are found", 
    			0, mapping.getDispatchers().size());
    }
    
    public void testAddFilter_Web30_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_30);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	mergedModelProvider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mergedModelProvider.getModelObject();
		
    	addFilter_Defaults_Annotations();
    	
		assertJavaFileExists(FILTER_CLASS_NAME);
    	
    	List filters = webApp.getFilters();
    	assertEquals("Exactly one filter is expected in the model, but " + filters.size() + " are found", 
    			1, filters.size());
    	org.eclipse.jst.javaee.web.Filter filter = (org.eclipse.jst.javaee.web.Filter) filters.get(0);
    	assertEquals("Filter name is expected to be " + FILTER_NAME + ", but it is " + filter.getFilterName(), 
    			FILTER_NAME, filter.getFilterName());
    	assertEquals("Filter class name is expected to be " + FILTER_CLASS_NAME + ", but it is " + filter.getFilterClass(), 
    			FILTER_CLASS_NAME, filter.getFilterClass());
    	
    	List params = filter.getInitParams();
		assertNotNull("List of initialization parameters cannot be retrieved", params);
		assertEquals("None initialization parameter is expected, but " + params.size() + " are found", 
				0, params.size());
    	
    	List mappings = webApp.getFilterMappings();
    	assertEquals("Exactly one filter mapping is expected, but " + mappings.size() + " are found", 
    			1, mappings.size());
    	org.eclipse.jst.javaee.web.FilterMapping mapping = (org.eclipse.jst.javaee.web.FilterMapping) mappings.get(0);
    	assertEquals("Filter name of the mapping is expected to be " + FILTER_NAME + ", but it is " + mapping.getFilterName(), 
    			FILTER_NAME, mapping.getFilterName());
    	List urlPatterns = mapping.getUrlPatterns();
    	assertEquals("Exactly one URL pattern is expected in the mapping, but " + urlPatterns.size() + " are found", 
    			1, urlPatterns.size());
    	UrlPatternType urlPattern = (UrlPatternType) urlPatterns.get(0);
    	assertEquals("Filter mapping URL pattern value is expected to be " + FILTER_DEFAULT_MAPPING + ", but it is " + urlPattern.getValue(), 
    			FILTER_DEFAULT_MAPPING, urlPattern.getValue());
    	assertEquals("None servlet name is expected in the filter mapping, but " + mapping.getServletNames().size() + " are found", 
    			0, mapping.getServletNames().size());
    	assertEquals("None dispatcher is expected in the filter mapping, but " + mapping.getDispatchers().size() + " are found", 
    			0, mapping.getDispatchers().size());    	
    }
    
    public void testAddFilter_Web30_Async() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_30);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	mergedModelProvider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mergedModelProvider.getModelObject();
		
    	addFilter_Async();
    	
		assertJavaFileExists(FILTER_CLASS_NAME);
    	
    	List filters = webApp.getFilters();
    	assertEquals("Exactly one filter is expected in the model, but " + filters.size() + " are found", 
    			1, filters.size());
    	org.eclipse.jst.javaee.web.Filter filter = (org.eclipse.jst.javaee.web.Filter) filters.get(0);
    	assertEquals("Filter name is expected to be " + FILTER_NAME + ", but it is " + filter.getFilterName(), 
    			FILTER_NAME, filter.getFilterName());
    	assertEquals("Filter class name is expected to be " + FILTER_CLASS_NAME + ", but it is " + filter.getFilterClass(), 
    			FILTER_CLASS_NAME, filter.getFilterClass());
    	
    	List params = filter.getInitParams();
		assertNotNull("List of initialization parameters cannot be retrieved", params);
		assertEquals("None initialization parameter is expected, but " + params.size() + " are found", 
				0, params.size());
    	
    	List mappings = webApp.getFilterMappings();
    	assertEquals("Exactly one filter mapping is expected, but " + mappings.size() + " are found", 
    			1, mappings.size());
    	org.eclipse.jst.javaee.web.FilterMapping mapping = (org.eclipse.jst.javaee.web.FilterMapping) mappings.get(0);
    	assertEquals("Filter name of the mapping is expected to be " + FILTER_NAME + ", but it is " + mapping.getFilterName(), 
    			FILTER_NAME, mapping.getFilterName());
    	List urlPatterns = mapping.getUrlPatterns();
    	assertEquals("Exactly one URL pattern is expected in the mapping, but " + urlPatterns.size() + " are found", 
    			1, urlPatterns.size());
    	UrlPatternType urlPattern = (UrlPatternType) urlPatterns.get(0);
    	assertEquals("Filter mapping URL pattern value is expected to be " + FILTER_DEFAULT_MAPPING + ", but it is " + urlPattern.getValue(), 
    			FILTER_DEFAULT_MAPPING, urlPattern.getValue());
    	assertEquals("None servlet name is expected in the filter mapping, but " + mapping.getServletNames().size() + " are found", 
    			0, mapping.getServletNames().size());
    	assertEquals("None dispatcher is expected in the filter mapping, but " + mapping.getDispatchers().size() + " are found", 
    			0, mapping.getDispatchers().size());    	
    }
    
    
    public void testAddFilter_Web31_Async() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_31);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	mergedModelProvider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mergedModelProvider.getModelObject();
		
    	addFilter_Async();
    	
		assertJavaFileExists(FILTER_CLASS_NAME);
    	
    	List filters = webApp.getFilters();
    	assertEquals("Exactly one filter is expected in the model, but " + filters.size() + " are found", 
    			1, filters.size());
    	org.eclipse.jst.javaee.web.Filter filter = (org.eclipse.jst.javaee.web.Filter) filters.get(0);
    	assertEquals("Filter name is expected to be " + FILTER_NAME + ", but it is " + filter.getFilterName(), 
    			FILTER_NAME, filter.getFilterName());
    	assertEquals("Filter class name is expected to be " + FILTER_CLASS_NAME + ", but it is " + filter.getFilterClass(), 
    			FILTER_CLASS_NAME, filter.getFilterClass());
    	
    	List params = filter.getInitParams();
		assertNotNull("List of initialization parameters cannot be retrieved", params);
		assertEquals("None initialization parameter is expected, but " + params.size() + " are found", 
				0, params.size());
    	
    	List mappings = webApp.getFilterMappings();
    	assertEquals("Exactly one filter mapping is expected, but " + mappings.size() + " are found", 
    			1, mappings.size());
    	org.eclipse.jst.javaee.web.FilterMapping mapping = (org.eclipse.jst.javaee.web.FilterMapping) mappings.get(0);
    	assertEquals("Filter name of the mapping is expected to be " + FILTER_NAME + ", but it is " + mapping.getFilterName(), 
    			FILTER_NAME, mapping.getFilterName());
    	List urlPatterns = mapping.getUrlPatterns();
    	assertEquals("Exactly one URL pattern is expected in the mapping, but " + urlPatterns.size() + " are found", 
    			1, urlPatterns.size());
    	UrlPatternType urlPattern = (UrlPatternType) urlPatterns.get(0);
    	assertEquals("Filter mapping URL pattern value is expected to be " + FILTER_DEFAULT_MAPPING + ", but it is " + urlPattern.getValue(), 
    			FILTER_DEFAULT_MAPPING, urlPattern.getValue());
    	assertEquals("None servlet name is expected in the filter mapping, but " + mapping.getServletNames().size() + " are found", 
    			0, mapping.getServletNames().size());
    	assertEquals("None dispatcher is expected in the filter mapping, but " + mapping.getDispatchers().size() + " are found", 
    			0, mapping.getDispatchers().size());    	
    }
    
    public void testAddListener_Web24_Defaults_NoJETEmitter() throws Exception {
		disableJETEmitter();
		testAddListener_Web24_Defaults();
		enableJETEmitter();
	}
    
    public void testAddListener_Web24_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_24);
    	WebArtifactEdit webEdit = null;
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);
    	try {
    		webEdit = new WebArtifactEdit(proj, false, true);
    		WebApp webApp = webEdit.getWebApp();
        	addListener_Defaults();
        	if (webApp != null) {
        		assertJavaFileExists(LISTENER_CLASS_NAME);
        		
        		List listeners = webApp.getListeners();
        		assertEquals("Exactly one listener is expected in the model, but " + listeners.size() + " are found", 
        				1, listeners.size());
        		Listener listener = (Listener) listeners.get(0);
        		assertEquals("Listener " + LISTENER_CLASS_NAME + " is expected in the model, but " + listener.getListenerClassName() + " is found", 
            			LISTENER_CLASS_NAME, listener.getListenerClassName());
        	}
    	} finally {
    		if (webEdit != null)
    			webEdit.dispose();
    	}
    }
    
    public void testAddListener_Web25_Defaults_NoJETEmitter() throws Exception {
		disableJETEmitter();
		testAddListener_Web25_Defaults();
		enableJETEmitter();
	}
    
    public void testAddListener_Web25_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_25);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	IModelProvider provider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) provider.getModelObject();
		
    	addListener_Defaults();

		assertJavaFileExists(LISTENER_CLASS_NAME);
    	
    	List listeners = webApp.getListeners();
    	assertEquals("Exactly one listener is expected in the model, but " + listeners.size() + " are found", 
    			1, listeners.size());
    	org.eclipse.jst.javaee.core.Listener listener = (org.eclipse.jst.javaee.core.Listener) listeners.get(0);
    	assertEquals("Listener " + LISTENER_CLASS_NAME + " is expected in the model, but " + listener.getListenerClass() + " is found", 
    			LISTENER_CLASS_NAME, listener.getListenerClass());
    }

    
    public void testAddListener_Web30_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_30);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	mergedModelProvider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mergedModelProvider.getModelObject();
		
    	addListener_Defaults_Annotations();

		assertJavaFileExists(LISTENER_CLASS_NAME);
    	
    	List listeners = webApp.getListeners();
    	assertEquals("Exactly one listener is expected in the model, but " + listeners.size() + " are found", 
    			1, listeners.size());
    	org.eclipse.jst.javaee.core.Listener listener = (org.eclipse.jst.javaee.core.Listener) listeners.get(0);
    	assertEquals("Listener " + LISTENER_CLASS_NAME + " is expected in the model, but " + listener.getListenerClass() + " is found", 
    			LISTENER_CLASS_NAME, listener.getListenerClass());
    }    
    
    
    
    public void testAddListener_Web31_AsyncListener() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_31);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	mergedModelProvider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mergedModelProvider.getModelObject();
		
		addListener_AsyncListener();
		assertJavaFileExists(LISTENER_CLASS_NAME);
    	
    	List listeners = webApp.getListeners();
    	assertEquals("Exactly one listener is expected in the model, but " + listeners.size() + " are found", 
    			1, listeners.size());
    	org.eclipse.jst.javaee.core.Listener listener = (org.eclipse.jst.javaee.core.Listener) listeners.get(0);
    	assertEquals("Listener " + LISTENER_CLASS_NAME + " is expected in the model, but " + listener.getListenerClass() + " is found", 
    			LISTENER_CLASS_NAME, listener.getListenerClass());
    	
    	
    }   
    
    
    public void testAddListener_Web31_HttpSessionIdListener() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_31);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	mergedModelProvider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mergedModelProvider.getModelObject();
		
		addListener_HttpSessionIdListener();
		assertJavaFileExists(LISTENER_CLASS_NAME);
    	
    	List listeners = webApp.getListeners();
    	assertEquals("Exactly one listener is expected in the model, but " + listeners.size() + " are found", 
    			1, listeners.size());
    	org.eclipse.jst.javaee.core.Listener listener = (org.eclipse.jst.javaee.core.Listener) listeners.get(0);
    	assertEquals("Listener " + LISTENER_CLASS_NAME + " is expected in the model, but " + listener.getListenerClass() + " is found", 
    			LISTENER_CLASS_NAME, listener.getListenerClass());
    	
    	
    }        
    
    public void testAddListener_Web31_MultiListeners() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_31);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	mergedModelProvider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mergedModelProvider.getModelObject();
		
		addListener_MultiInterfaces();
		assertJavaFileExists(LISTENER_CLASS_NAME);
    	
    	List listeners = webApp.getListeners();
    	assertEquals("Exactly one listener is expected in the model, but " + listeners.size() + " are found", 
    			1, listeners.size());
    	org.eclipse.jst.javaee.core.Listener listener = (org.eclipse.jst.javaee.core.Listener) listeners.get(0);
    	assertEquals("Listener " + LISTENER_CLASS_NAME + " is expected in the model, but " + listener.getListenerClass() + " is found", 
    			LISTENER_CLASS_NAME, listener.getListenerClass());
    	
    	
    }        
    
	@Override
	protected void tearDown() throws Exception {
		// uncomment the below line if you want to dump a check whether the
		// .JETEmitters projects is created as a result of the executed
		// operation
//		System.out.println(".JETEmitters exists : "
//				+ ResourcesPlugin.getWorkspace().getRoot().getProject(
//						WTPJETEmitter.PROJECT_NAME).exists());
		super.tearDown();
	}

    private void enableJETEmitter() {
    	J2EEPreferences preferences = J2EEPlugin.getDefault().getJ2EEPreferences();
		preferences.setValue(J2EEPlugin.DYNAMIC_TRANSLATION_OF_JET_TEMPLATES_PREF_KEY, true);
	}

	private void disableJETEmitter() {
		J2EEPreferences preferences = J2EEPlugin.getDefault().getJ2EEPreferences();
		preferences.setValue(J2EEPlugin.DYNAMIC_TRANSLATION_OF_JET_TEMPLATES_PREF_KEY, false);
	}

    public void createWebProject(String projectName, IProjectFacetVersion version) throws Exception {
    	IDataModel dm = WebProjectCreationOperationTest.getWebDataModel(
				projectName, null, null, null, null, version, false);
     	runAndVerify(dm);
    }

    private void addServlet_Defaults() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewServletClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, SERVLET_NAME);
        runAndVerify(dm);
    }
    
    private void addServlet_Defaults_Annotations() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewServletClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, SERVLET_NAME);
    	executeAndWait(dm.getDefaultOperation());
    }
    
    
    private void addServlet_Async() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewServletClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, SERVLET_NAME);
    	dm.setBooleanProperty(INewServletClassDataModelProperties.ASYNC_SUPPORT, true);
    	executeAndWait(dm.getDefaultOperation());
    }
    
	private void executeAndWait(IDataModelOperation dataModelOperation) throws InterruptedException, ExecutionException {
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		mergedModelProvider.addListener(listener);
		dataModelOperation.execute(null, null);
		listener.waitForEvents();
		mergedModelProvider.removeListener(listener);
	}
    
    private void addFilter_Defaults() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewFilterClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, FILTER_NAME);
        runAndVerify(dm);
    }
    
    private void addFilter_Defaults_Annotations() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewFilterClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, FILTER_NAME);
      	executeAndWait(dm.getDefaultOperation());
    }   
    
    private void addFilter_Async() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewFilterClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, FILTER_NAME);
    	dm.setBooleanProperty(INewFilterClassDataModelProperties.ASYNC_SUPPORT, true);
       	executeAndWait(dm.getDefaultOperation());
    }    
    
    private void addListener_Defaults() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewListenerClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, LISTENER_NAME);
    	List interfaces = new ArrayList();
    	interfaces.add(NewListenerClassDataModelProvider.LISTENER_INTERFACES[0]);
    	dm.setProperty(INTERFACES, interfaces);
        runAndVerify(dm);
    }
    
    
    private void addListener_Defaults_Annotations() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewListenerClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, LISTENER_NAME);
    	List interfaces = new ArrayList();
    	interfaces.add(NewListenerClassDataModelProvider.LISTENER_INTERFACES[0]);
    	dm.setProperty(INTERFACES, interfaces);
    	executeAndWait(dm.getDefaultOperation());
    }
    
    
    private void addListener_AsyncListener() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewListenerClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, LISTENER_NAME);
    	List interfaces = new ArrayList();
    	interfaces.add(NewListenerClassDataModelProvider.LISTENER_INTERFACES[9]);
    	dm.setProperty(INTERFACES, interfaces);
    	executeAndWait(dm.getDefaultOperation());
    }
    
    private void addListener_HttpSessionIdListener() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewListenerClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, LISTENER_NAME);
    	List interfaces = new ArrayList();
    	interfaces.add(NewListenerClassDataModelProvider.LISTENER_INTERFACES[6]);
    	dm.setProperty(INTERFACES, interfaces);
    	executeAndWait(dm.getDefaultOperation());
    }
    
    private void addListener_MultiInterfaces() throws Exception{
    	//add sessionid and async listeners
    	IDataModel dm = DataModelFactory.createDataModel(NewListenerClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, LISTENER_NAME);
    	List interfaces = new ArrayList();
      	interfaces.add(NewListenerClassDataModelProvider.LISTENER_INTERFACES[9]);
    	interfaces.add(NewListenerClassDataModelProvider.LISTENER_INTERFACES[6]);
    	dm.setProperty(INTERFACES, interfaces);
    	executeAndWait(dm.getDefaultOperation());   	
    }
    
    private void assertJavaFileExists(String fullyQualifiedName) throws JavaModelException {
		IJavaProject javaProject = JavaCore.create(
				ResourcesPlugin.getWorkspace().getRoot())
				.getJavaModel().getJavaProject(WEB_PROJECT_NAME);
		assertNotNull("Java project " + WEB_PROJECT_NAME + " not found", javaProject);
		IType type = javaProject.findType(fullyQualifiedName);
		assertNotNull("Java type " + fullyQualifiedName + " not found", type);
		IFile file = (IFile) type.getResource();
		assertNotNull("Source file for Java type " + fullyQualifiedName + " not found", file);
		assertTrue(file.exists());
    }

	public void testDefaultWebUtilityContextRoot() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel(
				WEB_PROJECT_NAME, EAR_PROJECT_NAME, null, null, null, JavaEEFacetConstants.WEB_25, true);
    	runAndVerify(dm);
		
		IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);
		IProject earproj = ProjectUtilities.getProject(EAR_PROJECT_NAME);
	
		assertEquals(WebUtilities.getServerContextRoot(proj, earproj),WEB_PROJECT_NAME);
	}
	public void testWebUtilityContextRoot() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel(
				WEB_PROJECT_NAME, EAR_PROJECT_NAME, WEB_CONTEXT_ROOT, null, null, JavaEEFacetConstants.WEB_25, true);
    	runAndVerify(dm);
		
		IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);
		IProject earproj = ProjectUtilities.getProject(EAR_PROJECT_NAME);
	
		assertEquals(WebUtilities.getServerContextRoot(proj, earproj),WEB_CONTEXT_ROOT);
	}
	
}
