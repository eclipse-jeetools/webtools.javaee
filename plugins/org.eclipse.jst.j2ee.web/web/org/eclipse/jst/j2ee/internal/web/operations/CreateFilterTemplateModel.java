/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.Collection;
import java.util.List;

import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateFilterTemplateModel extends CreateWebClassTemplateModel {
	
	public static final String QUALIFIED_IO_EXCEPTION = "java.io.IOException"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_EXCEPTION = "javax.servlet.ServletException"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_REQUEST = "javax.servlet.ServletRequest"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_RESPONSE = "javax.servlet.ServletResponse"; //$NON-NLS-1$
	public static final String QUALIFIED_FILTER_CONFIG = "javax.servlet.FilterConfig"; //$NON-NLS-1$
	public static final String QUALIFIED_FILTER_CHAIN = "javax.servlet.FilterChain"; //$NON-NLS-1$

	public static final String INIT = "init"; //$NON-NLS-1$
	public static final String TO_STRING = "toString"; //$NON-NLS-1$
	public static final String DO_FILTER = "doFilter"; //$NON-NLS-1$
	public static final String DESTROY = "destroy"; //$NON-NLS-1$
	
	public static final int NAME = 0;
	public static final int VALUE = 1;
	public static final int DESCRIPTION = 2;

	public CreateFilterTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}
	
	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();
		
		if (shouldGenInit()) {
			collection.add(QUALIFIED_FILTER_CONFIG);
			collection.add(QUALIFIED_SERVLET_EXCEPTION);
		}
		
		if (shouldGenDoFilter()) {
			collection.add(QUALIFIED_SERVLET_REQUEST);
			collection.add(QUALIFIED_SERVLET_RESPONSE);
			collection.add(QUALIFIED_FILTER_CHAIN);
			collection.add(QUALIFIED_IO_EXCEPTION);
			collection.add(QUALIFIED_SERVLET_EXCEPTION);
		}
		
		return collection;
	}

	public String getFilterName() {
		return getProperty(INewJavaClassDataModelProperties.CLASS_NAME).trim();
	}

	public boolean shouldGenInit() {
		return implementImplementedMethod(INIT);
	}

	public boolean shouldGenToString() {
		return implementImplementedMethod(TO_STRING);
	}

	public boolean shouldGenDoFilter() {
		return implementImplementedMethod(DO_FILTER);
	}

	public boolean shouldGenDestroy() {
		return implementImplementedMethod(DESTROY);
	}

	public List getInitParams() {
		return (List) dataModel.getProperty(INewFilterClassDataModelProperties.INIT_PARAM);
	}

	public String getInitParam(int index, int type) {
		List params = getInitParams();
		if (index < params.size()) {
			String[] stringArray = (String[]) params.get(index);
			return stringArray[type];
		}
		return null;
	}

	public List getFilterMappings() {
		return (List) dataModel.getProperty(INewFilterClassDataModelProperties.URL_MAPPINGS);
	}

	public String getFilterMapping(int index) {
		List mappings = getFilterMappings();
		if (index < mappings.size()) {
			String[] map = (String[]) mappings.get(index);
			return map[0];
		}
		return null;
	}

	public String getFilterDescription() {
		return dataModel.getStringProperty(INewFilterClassDataModelProperties.DESCRIPTION);
	}

	protected boolean implementImplementedMethod(String methodName) {
		if (dataModel.getBooleanProperty(INewJavaClassDataModelProperties.ABSTRACT_METHODS)) {
			if (methodName.equals(INIT))
				return dataModel.getBooleanProperty(INewFilterClassDataModelProperties.INIT);
			else if (methodName.equals(TO_STRING))
				return dataModel.getBooleanProperty(INewFilterClassDataModelProperties.TO_STRING);
			else if (methodName.equals(DO_FILTER))
				return dataModel.getBooleanProperty(INewFilterClassDataModelProperties.DO_FILTER);
			else if (methodName.equals(DESTROY))
				return dataModel.getBooleanProperty(INewFilterClassDataModelProperties.DESTROY);
		}		
		return false;
	}

}
