/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
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
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.operations.NewFilterClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.operations.NewListenerClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModelProvider;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;

public class AddWebArtifactOperationTest extends OperationTestCase implements
		INewJavaClassDataModelProperties {
	
    public static final String WEB_PROJECT_NAME = "WebProject"; //$NON-NLS-1$
    
    public static final String PACKAGE = "test"; //$NON-NLS-1$
    
    public static final String SERVLET_NAME = "TestServlet"; //$NON-NLS-1$
    public static final String SERVLET_CLASS_NAME = PACKAGE + "." + SERVLET_NAME; //$NON-NLS-1$
    public static final String SERVLET_DEFAULT_MAPPING = "/" + SERVLET_NAME; //$NON-NLS-1$
    
    public static final String FILTER_NAME = "TestFilter"; //$NON-NLS-1$
    public static final String FILTER_CLASS_NAME = PACKAGE + "." + FILTER_NAME; //$NON-NLS-1$
    public static final String FILTER_DEFAULT_MAPPING = "/" + FILTER_NAME; //$NON-NLS-1$
    
    public static final String LISTENER_NAME = "TestListener"; //$NON-NLS-1$
    public static final String LISTENER_CLASS_NAME = PACKAGE + "." + LISTENER_NAME; //$NON-NLS-1$

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
        		assertNotNull(servlet);
        		assertEquals(webApp, servlet.getWebApp());
        		assertEquals(SERVLET_NAME, servlet.getServletName());
        		assertEquals(SERVLET_NAME, servlet.getDisplayName());
        		
        		List mappings = servlet.getMappings();
        		assertEquals(1, mappings.size());
        		ServletMapping mapping = (ServletMapping) mappings.get(0);
        		assertEquals(SERVLET_NAME, mapping.getName());
        		assertEquals(SERVLET_DEFAULT_MAPPING, mapping.getUrlPattern());
        		assertEquals(servlet, mapping.getServlet());
        	}
    	} finally {
    		if (webEdit != null)
    			webEdit.dispose();
    	}
    }

    public void testAddServlet_Web25_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_25);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	IModelProvider provider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) provider.getModelObject();
		
    	addServlet_Defaults();
    	
    	assertJavaFileExists(SERVLET_CLASS_NAME);
    	
    	List servlets = webApp.getServlets();
    	assertEquals(1, servlets.size());
    	org.eclipse.jst.javaee.web.Servlet servlet = (org.eclipse.jst.javaee.web.Servlet) servlets.get(0);
    	assertEquals(SERVLET_NAME, servlet.getServletName());
    	assertEquals(SERVLET_CLASS_NAME, servlet.getServletClass());
    	List displayNames = servlet.getDisplayNames();
    	assertEquals(1, displayNames.size());
    	DisplayName displayName = (DisplayName) displayNames.get(0);
    	assertEquals(SERVLET_NAME, displayName.getValue());
		
		List params = servlet.getInitParams();
		assertNotNull(params);
		assertEquals(0, params.size());
    	
    	List mappings = webApp.getServletMappings();
    	assertEquals(1, mappings.size());
    	org.eclipse.jst.javaee.web.ServletMapping mapping = (org.eclipse.jst.javaee.web.ServletMapping) mappings.get(0);
    	assertEquals(SERVLET_NAME, mapping.getServletName());
    	List urlPatterns = mapping.getUrlPatterns();
    	assertEquals(1, urlPatterns.size());
    	UrlPatternType urlPattern = (UrlPatternType) urlPatterns.get(0);
    	assertEquals(SERVLET_DEFAULT_MAPPING, urlPattern.getValue());
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
        		assertNotNull(filter);
        		assertEquals(FILTER_NAME, filter.getName());
        		assertEquals(FILTER_NAME, filter.getDisplayName());
        		assertEquals(FILTER_CLASS_NAME, filter.getFilterClassName());
        		
        		List params = filter.getInitParams();
        		assertNotNull(params);
        		assertEquals(0, params.size());
        		
        		List mappings = webApp.getFilterMappings();
        		assertEquals(1, mappings.size());
        		FilterMapping mapping = (FilterMapping) mappings.get(0);
        		assertEquals(filter, mapping.getFilter());
        		assertEquals(FILTER_DEFAULT_MAPPING, mapping.getUrlPattern());
        		assertEquals(null, mapping.getServlet());
        		assertEquals(null, mapping.getServletName());
        		List dispatchers = mapping.getDispatcherType();
        		assertEquals(0, dispatchers.size());
        	}
    	} finally {
    		if (webEdit != null)
    			webEdit.dispose();
    	}
    }
    
    public void testAddFilter_Web25_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_25);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	IModelProvider provider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) provider.getModelObject();
		
    	addFilter_Defaults();
    	
		assertJavaFileExists(FILTER_CLASS_NAME);
    	
    	List filters = webApp.getFilters();
    	assertEquals(1, filters.size());
    	org.eclipse.jst.javaee.web.Filter filter = (org.eclipse.jst.javaee.web.Filter) filters.get(0);
    	assertEquals(FILTER_NAME, filter.getFilterName());
    	assertEquals(FILTER_CLASS_NAME, filter.getFilterClass());
    	List displayNames = filter.getDisplayNames();
    	assertEquals(1, displayNames.size());
    	DisplayName displayName = (DisplayName) displayNames.get(0);
    	assertEquals(FILTER_NAME, displayName.getValue());
    	
    	List mappings = webApp.getFilterMappings();
    	assertEquals(1, mappings.size());
    	org.eclipse.jst.javaee.web.FilterMapping mapping = (org.eclipse.jst.javaee.web.FilterMapping) mappings.get(0);
    	assertEquals(FILTER_NAME, mapping.getFilterName());
    	List urlPatterns = mapping.getUrlPatterns();
    	assertEquals(1, urlPatterns.size());
    	UrlPatternType urlPattern = (UrlPatternType) urlPatterns.get(0);
    	assertEquals(FILTER_DEFAULT_MAPPING, urlPattern.getValue());
    	assertEquals(0, mapping.getServletNames().size());
    	assertEquals(0, mapping.getDispatchers().size());
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
        		assertEquals(1, listeners.size()); 
        		Listener listener = (Listener) listeners.get(0);
        		assertEquals(LISTENER_CLASS_NAME, listener.getListenerClassName());
        	}
    	} finally {
    		if (webEdit != null)
    			webEdit.dispose();
    	}
    }
    
    public void testAddListener_Web25_Defaults() throws Exception {
    	createWebProject(WEB_PROJECT_NAME, JavaEEFacetConstants.WEB_25);
    	IProject proj = ProjectUtilities.getProject(WEB_PROJECT_NAME);

    	IModelProvider provider = ModelProviderManager.getModelProvider(proj);
		org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) provider.getModelObject();
		
    	addListener_Defaults();

		assertJavaFileExists(LISTENER_CLASS_NAME);
    	
    	List listeners = webApp.getListeners();
    	assertEquals(1, listeners.size());
    	org.eclipse.jst.javaee.core.Listener listener = (org.eclipse.jst.javaee.core.Listener) listeners.get(0);
    	assertEquals(LISTENER_CLASS_NAME, listener.getListenerClass());
    }

    public void createWebProject(String projectName, IProjectFacetVersion version) throws Exception {
    	IDataModel dm = WebProjectCreationOperationTest.getWebDataModel(
				WEB_PROJECT_NAME, null, null, null, null, version, false);
    	runAndVerify(dm);
    }

    public void addServlet_Defaults() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewServletClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, SERVLET_NAME);
        runAndVerify(dm);
    }
    
    public void addFilter_Defaults() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewFilterClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, FILTER_NAME);
        runAndVerify(dm);
    }
    
    public void addListener_Defaults() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewListenerClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, WEB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, LISTENER_NAME);
    	List interfaces = new ArrayList();
    	interfaces.add(NewListenerClassDataModelProvider.LISTENER_INTERFACES[0]);
    	dm.setProperty(INTERFACES, interfaces);
        runAndVerify(dm);
    }
    
    private void assertJavaFileExists(String fullyQualifiedName) throws JavaModelException {
		IJavaProject javaProject = JavaCore.create(
				ResourcesPlugin.getWorkspace().getRoot())
				.getJavaModel().getJavaProject(WEB_PROJECT_NAME);
		assertNotNull(javaProject);
		IFile file = (IFile) javaProject.findType(fullyQualifiedName).getResource();
		assertNotNull(file);
		assertTrue(file.exists());
    }
	
}
