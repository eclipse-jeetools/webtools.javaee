/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_FILTER;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class FilterSupertypesValidator extends AbstractSupertypesValidator {
	
	public static boolean isFilterSuperclass(IDataModel dataModel) {
		if (hasSuperInterface(dataModel, getSuperclass(dataModel), QUALIFIED_FILTER))
			return true;
		
		for (Object iface : getInterfaces(dataModel)) {
			if (hasSuperInterface(dataModel, (String) iface, QUALIFIED_FILTER)) 
				return true;
		}
		
		return false;
	}

}
