/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 31, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.migration;

import java.util.List;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.jsp.JSPConfig;
import org.eclipse.jst.j2ee.jsp.JspFactory;
import org.eclipse.jst.j2ee.jsp.TagLibRefType;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.TagLibRef;
import org.eclipse.jst.j2ee.webapplication.WebApp;


/**
 * @author vijayb
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class War14SpecificationMigrator {

	/**
	 * @param app
	 */
	public J2EEMigrationStatus migrateWebAppTo14(WebApp app) {
		migrateTabLibs(app);
		migrateContextParam(app);
		migrateInitParams(app);
		return new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK,(new J2EEMigrationStatus()).format(J2EESpecificationMigrationConstants.DEFAULT_COMPLETED_STATUS_MSG,app.getDisplayName()));
	}
	/**
	 * @param app
	 */
	private void migrateInitParams(WebApp app) {
	    migrateServletInitParams(app);
		migrateFilterInitParams(app);
	}
	/**
     * @param app
     */
    private void migrateFilterInitParams(WebApp app) {
        List filters = app.getFilters();
		if(filters != null &&  !filters.isEmpty()) {
			for(int i = 0; i < filters.size(); i++) {
				Filter filter = (Filter)filters.get(i);
				List initParam = filter.getInitParams();
				for(int j = 0; j < initParam.size(); j++) {
					InitParam param = (InitParam)initParam.get(j);
					if(param != null) {
					    ParamValue cParam = createParamValue(param);
					    List contextParams = filter.getInitParamValues();
					    if(!paramValueExist(contextParams,cParam))
					        filter.getInitParamValues().add(cParam);
					}
				}
			}
		}
    }
    /**
     * @param app
     */
    private void migrateServletInitParams(WebApp app) {
        List servlets = app.getServlets();
	    if(servlets != null &&  !servlets.isEmpty()) {
			for(int i = 0; i < servlets.size(); i++) {
			    Servlet servlet = (Servlet)servlets.get(i);
				List initParam = servlet.getParams();
				for(int j = 0; j < initParam.size(); j++) {
					InitParam param = (InitParam)initParam.get(j);
					if(param != null) {
					    ParamValue cParam = createParamValue(param);
					    List contextParams = servlet.getInitParams();
					    if(!paramValueExist(contextParams,cParam)) 
					        servlet.getInitParams().add(cParam);
				}
			}
		  }
		}
    }
    /**
     * @param param
     * @return
     */
    private ParamValue createParamValue(InitParam param) {
        ParamValue cParam = CommonFactory.eINSTANCE.createParamValue();
        cParam.setName(param.getParamName());
        cParam.setValue(param.getParamValue());
        String desc = param.getDescription();
        if(desc != null && desc.length() > 0)
            cParam.getDescriptions().add(createDescription(desc));
        return cParam;
    }
    /**
     * @param desc
     * @return
     */
    private Object createDescription(String desc) {
       Description description = CommonFactory.eINSTANCE.createDescription();
       description.setValue(desc);
       return description;
    }
    /**
     * @param contextParam
     * @param param
     * @return
     */
    private boolean paramValueExist(List contextParams, ParamValue param) {
        boolean exists = false;
        for(int i = 0; i < contextParams.size(); i++) {
            ParamValue paramValue = (ParamValue)contextParams.get(i);
            if(paramValue.getName().equals(param.getName()) && paramValue.getValue().equals(param.getValue())) {
                exists = true;
                break;
            }
        }
        return exists;
    }
    
    /**
     * @param contextParams
     * @param param
     * @return
     */
    private boolean tagLibURITypeExists(List tagLibsURITypes, TagLibRefType tlut) {
        boolean exists = false;
        for(int i = 0; i < tagLibsURITypes.size(); i++) {
            TagLibRefType tempTlut = (TagLibRefType)tagLibsURITypes.get(i);
            if(tempTlut.getTaglibLocation().equals(tlut.getTaglibLocation()) && tempTlut.getTaglibURI().equals(tempTlut.getTaglibURI())) {
                exists = true;
                break;
            }
        }
        return exists;
    }
    /**
	 * @param app
	 */
	private void migrateContextParam(WebApp app) {
		List contexts = app.getContexts();
		if(contexts != null && !contexts.isEmpty()) {
				for(int j = 0; j < contexts.size(); j++) {
					ContextParam param = (ContextParam)contexts.get(j);
					if(param != null) {
					    ParamValue cParam = CommonFactory.eINSTANCE.createParamValue();
					    cParam.setName(param.getParamName());
					    cParam.setValue(param.getParamValue());
					    cParam.setDescription(param.getDescription());
					    List contextParams = app.getContextParams();
					    if(!paramValueExist(contextParams,cParam))
					        app.getContextParams().add(cParam);
					}
				}
			}
	}
		
	
	/**
	 * @param app
	 */
	private void migrateTabLibs(WebApp app) {
		List tagLibs = app.getTagLibs();
		JSPConfig config = app.getJspConfig();
		if(tagLibs != null && !tagLibs.isEmpty()) {
		    if(config == null) {
		        config = JspFactory.eINSTANCE.createJSPConfig();
		        app.setJspConfig(config);
		        config = app.getJspConfig();
		    }
		    for(int i = 0; i < tagLibs.size(); i++) {
		        TagLibRef tlr = (TagLibRef)tagLibs.get(i);
		        TagLibRefType tlrt = JspFactory.eINSTANCE.createTagLibRefType();
		        tlrt.setTaglibLocation(tlr.getTaglibLocation());
		        tlrt.setTaglibURI(tlr.getTaglibURI());
		        List tagLibRefTypes = config.getTagLibs();
		        if(!tagLibURITypeExists(tagLibRefTypes,tlrt))
		            config.getTagLibs().add(tlrt);
			}
		
	  }
	}
}
