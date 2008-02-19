/*******************************************************************************
 * Copyright (c) 2003, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * Kiril Mitov, k.mitov@sap.com	- bug 204160
 * Kaloyan Raev, kaloyan.raev@sap.com
 *******************************************************************************/
/*
 * Created on Aug 6, 2004
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.Collection;
import java.util.List;

import org.eclipse.jst.j2ee.web.IServletConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author jlanuti
 */
public class CreateServletTemplateModel extends CreateWebClassTemplateModel
		implements INewServletClassDataModelProperties, IServletConstants {

	public static final int NAME = 0;
	public static final int VALUE = 1;
	public static final int DESCRIPTION = 2;
	
	public CreateServletTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}
	
	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();
		
		if (shouldGenInit()) {
			collection.add(QUALIFIED_SERVLET_CONFIG);
			collection.add(QUALIFIED_SERVLET_EXCEPTION);
		}
		
		if (shouldGenGetServletConfig()) {
			collection.add(QUALIFIED_SERVLET_CONFIG);
		}
		
		if (shouldGenService()) {
			if (isHttpServletSuperclass()) {
				collection.add(QUALIFIED_HTTP_SERVLET_REQUEST);
				collection.add(QUALIFIED_HTTP_SERVLET_RESPONSE);
			} else {
				collection.add(QUALIFIED_SERVLET_REQUEST);
				collection.add(QUALIFIED_SERVLET_RESPONSE);
			}
			
			collection.add(QUALIFIED_SERVLET_EXCEPTION);
			collection.add(QUALIFIED_IO_EXCEPTION);
		}
		
		if (shouldGenDoGet() || shouldGenDoPost() || shouldGenDoPut() || 
				shouldGenDoDelete() || shouldGenDoHead() || 
				shouldGenDoOptions() || shouldGenDoTrace()) {
			collection.add(QUALIFIED_HTTP_SERVLET_REQUEST);
			collection.add(QUALIFIED_HTTP_SERVLET_RESPONSE);
			collection.add(QUALIFIED_SERVLET_EXCEPTION);
			collection.add(QUALIFIED_IO_EXCEPTION);
		}
		
		return collection;
	}

	public String getServletName() {
		return super.getDisplayName();
	}
	
	/**
	 * @deprecated Use {@link #getClassName()} instead. Will be removed post WTP
	 *             3.0.
	 * 
	 * @see CreateWebClassTemplateModel#getClassName()
	 */
	public String getServletClassName() {
		return super.getClassName();
	}

	public boolean shouldGenInit() {
		return implementImplementedMethod(METHOD_INIT);
	}

	public boolean shouldGenDestroy() {
		return implementImplementedMethod(METHOD_DESTROY);
	}

	public boolean shouldGenGetServletConfig() {
		return implementImplementedMethod(METHOD_GET_SERVLET_CONFIG);
	}

	public boolean shouldGenGetServletInfo() {
		return implementImplementedMethod(METHOD_GET_SERVLET_INFO);
	}

	public boolean shouldGenService() {
		return implementImplementedMethod(METHOD_SERVICE);
	}

	public boolean shouldGenDoGet() {
		return implementImplementedMethod(METHOD_DO_GET);
	}

	public boolean shouldGenDoPost() {
		return implementImplementedMethod(METHOD_DO_POST);
	}

	public boolean shouldGenDoPut() {
		return implementImplementedMethod(METHOD_DO_PUT);
	}

	public boolean shouldGenDoDelete() {
		return implementImplementedMethod(METHOD_DO_DELETE);
	}

	public boolean shouldGenDoHead() {
		return implementImplementedMethod(METHOD_DO_HEAD);
	}

	public boolean shouldGenDoOptions() {
		return implementImplementedMethod(METHOD_DO_OPTIONS);
	}

	public boolean shouldGenDoTrace() {
		return implementImplementedMethod(METHOD_DO_TRACE);
	}
	
	public boolean shouldGenToString() {
		return implementImplementedMethod(METHOD_TO_STRING);
	}

	public boolean isGenericServletSuperclass() {
		return ServletSupertypesValidator.isGenericServletSuperclass(dataModel);
	}
	
	public boolean isHttpServletSuperclass() {
		return ServletSupertypesValidator.isHttpServletSuperclass(dataModel);
	}
	
	public List getInitParams() {
		return (List) dataModel.getProperty(INIT_PARAM);
	}

	public String getInitParam(int index, int type) {
		List params = getInitParams();
		if (index < params.size()) {
			String[] stringArray = (String[]) params.get(index);
			return stringArray[type];
		}
		return null;
	}

	public List getServletMappings() {
		return (List) dataModel.getProperty(URL_MAPPINGS);
	}

	public String getServletMapping(int index) {
		List mappings = getServletMappings();
		if (index < mappings.size()) {
			String[] map = (String[]) mappings.get(index);
			return map[0];
		}
		return null;
	}

	/**
	 * @deprecated Use {@link #getDescription()} instead. Will be removed post
	 *             WTP 3.0.
	 * 
	 * @see CreateWebClassTemplateModel#getDescription()
	 */
	public String getServletDescription() {
		return super.getDescription();
	}

	protected boolean implementImplementedMethod(String methodName) {
		if (dataModel.getBooleanProperty(ABSTRACT_METHODS)) {
			if (methodName.equals(METHOD_INIT))
				return dataModel.getBooleanProperty(INIT);
			else if (methodName.equals(METHOD_DESTROY))
				return dataModel.getBooleanProperty(DESTROY);
			else if (methodName.equals(METHOD_GET_SERVLET_CONFIG))
				return dataModel.getBooleanProperty(GET_SERVLET_CONFIG);
			else if (methodName.equals(METHOD_GET_SERVLET_INFO))
				return dataModel.getBooleanProperty(GET_SERVLET_INFO);
			else if (methodName.equals(METHOD_SERVICE))
				return dataModel.getBooleanProperty(SERVICE);
			else if (methodName.equals(METHOD_DO_GET))
				return dataModel.getBooleanProperty(DO_GET);
			else if (methodName.equals(METHOD_DO_POST))
				return dataModel.getBooleanProperty(DO_POST);
			else if (methodName.equals(METHOD_DO_PUT))
				return dataModel.getBooleanProperty(DO_PUT);
			else if (methodName.equals(METHOD_DO_DELETE))
				return dataModel.getBooleanProperty(DO_DELETE);
			else if (methodName.equals(METHOD_DO_HEAD))
				return dataModel.getBooleanProperty(DO_HEAD);
			else if (methodName.equals(METHOD_DO_OPTIONS))
				return dataModel.getBooleanProperty(DO_OPTIONS);
			else if (methodName.equals(METHOD_DO_TRACE))
				return dataModel.getBooleanProperty(DO_TRACE);
		}
		return false;
	}

}
