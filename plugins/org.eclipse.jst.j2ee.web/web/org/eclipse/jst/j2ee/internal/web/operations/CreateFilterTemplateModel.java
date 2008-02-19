/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.web.IServletConstants;
import org.eclipse.jst.j2ee.webapplication.DispatcherType;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateFilterTemplateModel extends CreateWebClassTemplateModel
		implements INewFilterClassDataModelProperties, IServletConstants {
	
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
		return getProperty(CLASS_NAME).trim();
	}

	public boolean shouldGenInit() {
		return implementImplementedMethod(METHOD_INIT);
	}

	public boolean shouldGenDoFilter() {
		return implementImplementedMethod(METHOD_DO_FILTER);
	}

	public boolean shouldGenDestroy() {
		return implementImplementedMethod(METHOD_DESTROY);
	}

	public List<String[]> getInitParams() {
		return (List) dataModel.getProperty(INIT_PARAM);
	}

	public String getInitParam(int index, int type) {
		List<String[]> params = getInitParams();
		if (index < params.size()) {
			String[] stringArray = params.get(index);
			return stringArray[type];
		}
		return null;
	}

	public List<IFilterMappingItem> getFilterMappings() {
		return (List<IFilterMappingItem>) dataModel.getProperty(FILTER_MAPPINGS);
	}

	public IFilterMappingItem getFilterMapping(int index) {
		List<IFilterMappingItem> mappings = getFilterMappings();
		if (index < mappings.size()) {
		    return mappings.get(index);
		}
		return null;
	}
	
	public String getDispatcherList(IFilterMappingItem mapping) {
		List<String> list = new ArrayList<String>();
		
		int dispatchers = mapping.getDispatchers();
		if ((dispatchers & IFilterMappingItem.REQUEST) > 0) {
            list.add(DispatcherType.REQUEST_LITERAL.getLiteral());
        }
        if ((dispatchers & IFilterMappingItem.FORWARD) > 0) {
            list.add(DispatcherType.FORWARD_LITERAL.getLiteral());
        }
        if ((dispatchers & IFilterMappingItem.INCLUDE) > 0) {
            list.add(DispatcherType.INCLUDE_LITERAL.getLiteral());
        }
        if ((dispatchers & IFilterMappingItem.ERROR) > 0) {
            list.add(DispatcherType.ERROR_LITERAL.getLiteral());
        }
        
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
        	builder.append(iterator.next());
        	if (iterator.hasNext()) {
        		builder.append(",");
        	}
        }
		
		return builder.toString();
	}

	protected boolean implementImplementedMethod(String methodName) {
		if (dataModel.getBooleanProperty(ABSTRACT_METHODS)) {
			if (methodName.equals(METHOD_INIT))
				return dataModel.getBooleanProperty(INIT);
			else if (methodName.equals(METHOD_DO_FILTER))
				return dataModel.getBooleanProperty(DO_FILTER);
			else if (methodName.equals(METHOD_DESTROY))
				return dataModel.getBooleanProperty(DESTROY);
		}		
		return false;
	}

}
