/*******************************************************************************
 * Copyright (c) 2010, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.model.internal.mergers;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.FilterMapping;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;

public class WebApp3Merger extends WebAppMerger {

	public WebApp3Merger(JavaEEObject _base, JavaEEObject merge, int kind) {
		super(_base, merge, kind);
	}
	
	@Override
	protected void mergeServlets(List warnings) {
		List<Servlet> baseservlets = getBaseWebApp().getServlets();
		List<Servlet> toMergeServlets = getToMergeWebApp().getServlets();
		if (baseservlets != null && toMergeServlets != null && baseservlets.size() > 0 && toMergeServlets.size() > 0){
			for (Servlet servlet : baseservlets) {
				Servlet toMergeArtifact = (Servlet) getArtifactFromList(servlet, toMergeServlets);
				if (artifactIsValid(servlet) && toMergeArtifact != null){
					mergeServlet(servlet, toMergeArtifact);
				}
			}
		}
		if (getToMergeWebApp().getServlets() != null){
			copyMissingContentInBase(getToMergeWebApp().getServlets(), getBaseWebApp().getServlets());
		}
		if (getToMergeWebApp().getFilterMappings() != null){
			copyMissingContentInBase(getToMergeWebApp().getServletMappings(), getBaseWebApp().getServletMappings());
		}
		mergeServletMappings(getBaseWebApp().getServletMappings(), getToMergeWebApp().getServletMappings());
	}
	
	private void mergeServlet(Servlet servlet, Servlet toMergeArtifact) {
		mergeInitParams(servlet.getInitParams(), toMergeArtifact.getInitParams());
	}
	
	private void mergeServletMappings(List<ServletMapping> servletMappingsBase,
			List<ServletMapping> servletMappingsToMerge) {
		
		if (servletMappingsBase != null && servletMappingsToMerge != null && servletMappingsToMerge.size() >0){
			for(ServletMapping toMergeMapping : servletMappingsToMerge){
				ServletMapping baseMapping = (ServletMapping) getArtifactFromList(toMergeMapping, servletMappingsBase);
				if (baseMapping != null && baseMapping.getServletName().equals(toMergeMapping.getServletName())){
					copyMissingContentInBase(toMergeMapping.getUrlPatterns(), baseMapping.getUrlPatterns());
				}
			}
		}
	}

	@Override
	protected void mergeFilters(List warnings) {
		List<Filter> basefilters = getBaseWebApp().getFilters();
		List<Filter> toMergeFilters = getToMergeWebApp().getFilters();
		if (basefilters != null && toMergeFilters != null && basefilters.size() > 0 && toMergeFilters.size() > 0){
			for (Filter filter : basefilters) {
				Filter toMergeArtifact = (Filter) getArtifactFromList(filter, toMergeFilters);
				if (artifactIsValid(filter) && toMergeArtifact != null){
					mergeFilter(filter, toMergeArtifact);
				}
			}
		}
		if (getToMergeWebApp().getFilters() != null){
		   copyMissingContentInBase(getToMergeWebApp().getFilters(), getBaseWebApp().getFilters());
		}
		if (getToMergeWebApp().getFilterMappings() != null){
	    	copyMissingContentInBase(getToMergeWebApp().getFilterMappings(), getBaseWebApp().getFilterMappings());
	    }
		mergeFilterMappings(getBaseWebApp().getFilterMappings(), getToMergeWebApp().getFilterMappings());
	}
	
	private void mergeFilterMappings(List<FilterMapping> filterMappingsBase,
			List<FilterMapping> filterMappingsToMerge) {
		if (filterMappingsBase != null && filterMappingsToMerge != null && filterMappingsToMerge.size() >0){
			for(FilterMapping toMergeMapping : filterMappingsToMerge){
				FilterMapping baseMapping = (FilterMapping) getArtifactFromList(toMergeMapping, filterMappingsBase);
				if (baseMapping != null && baseMapping.getFilterName().equals(toMergeMapping.getFilterName())){
					copyMissingContentInBase(toMergeMapping.getUrlPatterns(), baseMapping.getUrlPatterns());
				}
			}
		}
	}

	private void mergeFilter(Filter filter, Filter toMergeArtifact) {
		mergeInitParams(filter.getInitParams(), toMergeArtifact.getInitParams());
	}

	private void mergeInitParams(List<ParamValue> initParams,
			List<ParamValue> initParams2) {
		if (initParams != null && initParams2 != null && initParams.size() > 0 && initParams2.size() >0){
			for(ParamValue value : initParams){
				ParamValue artifactFromList = (ParamValue) getArtifactFromList(value, initParams2);
				if(artifactFromList != null && value.getParamName().equals(artifactFromList.getParamName())){
					value.setParamValue(artifactFromList.getParamValue());
				}
			}
		}
		copyMissingContentInBase(initParams2, initParams);
	}
}
