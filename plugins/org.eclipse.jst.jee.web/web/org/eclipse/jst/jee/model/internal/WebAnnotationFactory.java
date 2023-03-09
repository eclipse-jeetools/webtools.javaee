/***********************************************************************
 * Copyright (c) 2008, 2014 by SAP AG, Walldorf and others. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.Listener;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.FilterMapping;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.jee.model.internal.common.AbstractAnnotationFactory;
import org.eclipse.jst.jee.model.internal.common.Result;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class WebAnnotationFactory extends AbstractAnnotationFactory {

	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String FILTER_NAME = "filterName"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String EJB = "EJB"; //$NON-NLS-1$
	private static final String RUN_AS = "RunAs"; //$NON-NLS-1$
	private static final String RESOURCE = "Resource"; //$NON-NLS-1$
	private static final String RESOURCES = "Resources"; //$NON-NLS-1$
	private static final String DECLARE_ROLES = "DeclareRoles"; //$NON-NLS-1$
	private static final String ASYNC_SUPPORTED = "asyncSupported"; //$NON-NLS-1$
	
//	private static final String INIT_PARAM = "InitParam"; //$NON-NLS-1$
//	private static final String INIT_PARAM_FQ = "javax.servlet.annotation.InitParam"; //$NON-NLS-1$
//	
//	private static final String INIT_PARAM_NAME = "initParams"; //$NON-NLS-1$
	private static final String URL_PATTERNS_NAME = "urlPatterns"; //$NON-NLS-1$
	
	
	private static final String WEB_SERVLET = "WebServlet"; //$NON-NLS-1$
	private static final String WEB_SERVLET_FQ = "javax.servlet.annotation.WebServlet"; //$NON-NLS-1$
	private static final String WEB_SERVLET_FQ_JAKARTA = "jakarta.servlet.annotation.WebServlet"; //$NON-NLS-1$
	
	private static final String WEB_LISTENER = "WebListener"; //$NON-NLS-1$
	private static final String WEB_LISTENER_FQ = "javax.servlet.annotation.WebListener"; //$NON-NLS-1$
	private static final String WEB_LISTENER_FQ_JAKARTA = "jakarta.servlet.annotation.WebListener"; //$NON-NLS-1$

	private static final String WEB_FILTER = "WebFilter"; //$NON-NLS-1$
	private static final String WEB_FILTER_FQ = "javax.servlet.annotation.WebFilter"; //$NON-NLS-1$
	private static final String WEB_FILTER_FQ_JAKARTA = "jakarta.servlet.annotation.WebFilter"; //$NON-NLS-1$

	
	
	
	
	

	private WebAnnotationFactory() {
		super();
	}

	public static WebAnnotationFactory createFactory() {
		return new WebAnnotationFactory();
	}

	public Result createServlet(IType rootType, String servletName) throws JavaModelException {
		Result result = new Result();
		Servlet servlet = WebFactory.eINSTANCE.createServlet();
		servlet.setServletName(servletName);
		servlet.setServletClass(rootType.getFullyQualifiedName());
		result.setMainObject(servlet);
		processCommonAnnotation(result, servlet, rootType);
		processTypeAnnotations(result, rootType);
		processMethodAnnotations(result, rootType);
		processFieldAnnotations(result, rootType);
		return result;
	}

	public Result createListener(IType rootType) throws JavaModelException {
		Result result = new Result();
		Listener listener = JavaeeFactory.eINSTANCE.createListener();
		listener.setListenerClass(rootType.getFullyQualifiedName());
		result.setMainObject(listener);
		processCommonAnnotation(result, listener, rootType);
		processTypeAnnotations(result, rootType);
		processMethodAnnotations(result, rootType);
		processFieldAnnotations(result, rootType);
		return result;
	}

	public Result createFilter(IType rootType, String filterName) throws JavaModelException {
		Result result = new Result();
		Filter filter = WebFactory.eINSTANCE.createFilter();
		filter.setFilterName(filterName);
		filter.setFilterClass(rootType.getFullyQualifiedName());
		result.setMainObject(filter);
		processCommonAnnotation(result, filter, rootType);
		processTypeAnnotations(result, rootType);
		processMethodAnnotations(result, rootType);
		processFieldAnnotations(result, rootType);
		return result;
	}

	private void processCommonAnnotation(Result result, JavaEEObject artifact, IType rootType) throws JavaModelException {
		for (IAnnotation annotation : rootType.getAnnotations()) {
			String annotationName = annotation.getElementName();
			IMemberValuePair[] memberValuePairs = annotation.getMemberValuePairs();
			String name = (String) getAnnotatedValue(NAME, memberValuePairs);
			String filterName = (String) getAnnotatedValue(FILTER_NAME, memberValuePairs);
			String descr = (String) getAnnotatedValue(DESCRIPTION, memberValuePairs);
			Boolean async = (Boolean) getAnnotatedValue(ASYNC_SUPPORTED, memberValuePairs);
			if(WEB_SERVLET.equals(annotationName) || WEB_SERVLET_FQ.equals(annotationName) ||  WEB_SERVLET_FQ_JAKARTA.equals(annotationName)){
				Servlet servlet = (Servlet)artifact;
				if (name != null) {
					servlet.setServletName(name);
				} else if(servlet.getServletName() == null){
					servlet.setServletName(rootType.getElementName());
				}
				if (descr != null) {
					Description description = JavaeeFactory.eINSTANCE.createDescription();
					description.setValue(descr);
					servlet.getDescriptions().add(description);
				}
				if (async != null){
					servlet.setAsyncSupported(async.booleanValue());
				}
				
				processUrlMapping(result, annotation, servlet);
			} else if(WEB_FILTER.equals(annotationName) || WEB_FILTER_FQ.equals(annotationName) || WEB_FILTER_FQ_JAKARTA.equals(annotationName)){
				Filter filter = (Filter)artifact;
				if (filterName != null) {
					filter.setFilterName(filterName);
				} else if(filter.getFilterName() == null){
					filter.setFilterName(rootType.getElementName());
				}
				if (descr != null) {
					Description description = JavaeeFactory.eINSTANCE.createDescription();
					description.setValue(descr);
					filter.getDescriptions().add(description);
				}
				if (async != null){
					filter.setAsyncSupported(async.booleanValue());
				}				
				processUrlMapping(result, annotation, filter);
				
			} else if(WEB_LISTENER.equals(annotationName) || WEB_LISTENER_FQ.equals(annotationName)  || WEB_LISTENER_FQ_JAKARTA.equals(annotationName)){
				Listener listener = (Listener)artifact;
				Description description = JavaeeFactory.eINSTANCE.createDescription();
				if(listener.getListenerClass() == null){
					listener.setListenerClass(rootType.getFullyQualifiedName());
				}
				if (descr != null) {
					description.setValue(descr);
					listener.getDescriptions().add(description);
				}
				
			}
		}
		
	}

	private void processTypeAnnotations(Result result, IType type) throws JavaModelException {
		JavaEEObject mainObject = result.getMainObject();
		boolean isServlet = Servlet.class.isInstance(mainObject);
		for (IAnnotation annotation : type.getAnnotations()) {
			String annotationName = annotation.getElementName();
			if (isServlet && DECLARE_ROLES.equals(annotationName)) {
				List<SecurityRoleRef> refs = new ArrayList<SecurityRoleRef>();
				processDeclareRoles(result, refs, annotation, type);
				((Servlet) mainObject).getSecurityRoleRefs().addAll(refs);
			} else if (RESOURCES.equals(annotationName)) {
				List<ResourceRef> resourceRefs = new ArrayList<ResourceRef>(2);
				processResourcesAnnotation(annotation, resourceRefs, type, result.getDependedTypes());
				result.getAdditional().addAll(resourceRefs);
			} else if (RESOURCE.equals(annotationName)) {
				List<ResourceRef> resourceRefs = new ArrayList<ResourceRef>(2);
				processResourceRefAnnotation(annotation, resourceRefs, type, result.getDependedTypes());
				result.getAdditional().addAll(resourceRefs);
			} else if (isServlet && RUN_AS.equals(annotationName)) {
				RunAs runAs = JavaeeFactory.eINSTANCE.createRunAs();
				processRunAs(annotation, runAs);
				((Servlet) mainObject).setRunAs(runAs);
			}
		}
	}
	
	private void processFieldAnnotations(Result result, IType type) throws JavaModelException {
		for (IField field : type.getFields()) {
			for (IAnnotation annotation : field.getAnnotations()) {
				processMemberAnnotations(result, field, annotation);
			}
		}
	}

	private void processMethodAnnotations(Result result, IType type) throws JavaModelException {
		for (IMethod method : type.getMethods()) {
			for (IAnnotation annotation : method.getAnnotations()) {
				processMemberAnnotations(result, method, annotation);
			}
		}
	}

	private void processMemberAnnotations(Result result, IMember member, IAnnotation annotation)
			throws JavaModelException {
		String annotationName = annotation.getElementName();
		if (EJB.equals(annotationName)) {
			List<EjbLocalRef> refs = new ArrayList<EjbLocalRef>(1);
			processEjbAnnotation(annotation, refs, member, result.getDependedTypes());
			result.getAdditional().addAll(refs);
		} else if (RESOURCE.equals(annotationName)) {
			List<ResourceRef> refs = new ArrayList<ResourceRef>(1);
			processResourceRefAnnotation(annotation, refs, member, result.getDependedTypes());
			result.getAdditional().addAll(refs);
		}
	}
	
	private void processUrlMapping(Result result, IAnnotation annotation, JavaEEObject artifact) throws JavaModelException {
		IMemberValuePair[] pairs = annotation.getMemberValuePairs();
		Object values = getAnnotatedValue(URL_PATTERNS_NAME, pairs);
		if (values == null){
			values = getAnnotatedValue("value", pairs); //$NON-NLS-1$			
		}
		if (values == null){
			return;
		}
		if(Servlet.class.isInstance(artifact)){
			String servletName = ((Servlet)artifact).getServletName();
			ServletMapping mapping = WebFactory.eINSTANCE.createServletMapping();
			mapping.setServletName(servletName);
			if (!isArrayOfObject(values)){
				transformToMapping(mapping, values);
			} else {
				for (Object urlPattern : (Object[]) values) {
					transformToMapping(mapping, urlPattern);	
				}
			}
			result.getAdditional().add(mapping);
		} else if(Filter.class.isInstance(artifact)){
			String filterName = ((Filter)artifact).getFilterName();
			FilterMapping mapping = WebFactory.eINSTANCE.createFilterMapping();
			mapping.setFilterName(filterName);
			if (!isArrayOfObject(values)){
				UrlPatternType urlPatternType = JavaeeFactory.eINSTANCE.createUrlPatternType();
				urlPatternType.setValue((String) values);
				mapping.getUrlPatterns().add(urlPatternType);
			} else {
				for (Object urlPattern : (Object[]) values) {
					UrlPatternType urlPatternType = JavaeeFactory.eINSTANCE.createUrlPatternType();
					urlPatternType.setValue((String) urlPattern);
					mapping.getUrlPatterns().add(urlPatternType);
				}
			}
			
			result.getAdditional().add(mapping);
		}
			
	}

	private void transformToMapping(ServletMapping mapping, Object urlPattern) {
		UrlPatternType urlPatternType = JavaeeFactory.eINSTANCE.createUrlPatternType();
		urlPatternType.setValue((String) urlPattern);
		mapping.getUrlPatterns().add(urlPatternType);
	}

}
