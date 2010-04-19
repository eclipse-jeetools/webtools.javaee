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
		mergeServletMappings(getBaseWebApp().getServletMappings(), getToMergeWebApp().getServletMappings());
		super.mergeServlets(warnings);
	}
	
	private void mergeServlet(Servlet servlet, Servlet toMergeArtifact) {
		mergeInitParams(servlet.getInitParams(), toMergeArtifact.getInitParams());
	}
	
	private void mergeServletMappings(List<ServletMapping> servletMappings,
			List<ServletMapping> servletMappings2) {
		if (servletMappings != null && servletMappings2 != null && servletMappings.size() > 0 && servletMappings2.size() >0){
			for(ServletMapping mapping : servletMappings){
				ServletMapping artifactFromList = (ServletMapping) getArtifactFromList(mapping, servletMappings2);
				if (artifactFromList != null && artifactFromList.getServletName().equals(mapping.getServletName())){
					copyMissingContentInBase(artifactFromList.getUrlPatterns(), mapping.getUrlPatterns());
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
		mergeFilterMappings(getBaseWebApp().getFilterMappings(), getToMergeWebApp().getFilterMappings());
		super.mergeFilters(warnings);
	}

	private void mergeFilterMappings(List<FilterMapping> filterMappings,
			List<FilterMapping> filterMappings2) {
		if (filterMappings != null && filterMappings2 != null && filterMappings.size() > 0 && filterMappings2.size() >0){
			for(FilterMapping mapping : filterMappings){
				FilterMapping artifactFromList = (FilterMapping) getArtifactFromList(mapping, filterMappings2);
				if (artifactFromList != null && artifactFromList.getFilterName().equals(mapping.getFilterName())){
					copyMissingContentInBase(artifactFromList.getUrlPatterns(), mapping.getUrlPatterns());
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
