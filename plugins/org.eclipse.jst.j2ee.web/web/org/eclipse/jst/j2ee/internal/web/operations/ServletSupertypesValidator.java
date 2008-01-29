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

import org.eclipse.jst.j2ee.web.IServletConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ServletSupertypesValidator extends AbstractSupertypesValidator
		implements IServletConstants {
	
	public ServletSupertypesValidator(IDataModel dataModel) {
		super(dataModel);
	}
	
	public boolean isHttpServletSuperclass() {
		if (QUALIFIED_HTTP_SERVLET.equals(getSuperclass()))
			return true;
		
		if (hasSuperclass(getSuperclass(), QUALIFIED_HTTP_SERVLET))
			return true;
		
		return false;
	}

	public boolean isGenericServletSuperclass() {
		if (QUALIFIED_HTTP_SERVLET.equals(getSuperclass()))
			return true;
		
		if (QUALIFIED_GENERIC_SERVLET.equals(getSuperclass()))
			return true;
		
		if (hasSuperclass(getSuperclass(), QUALIFIED_GENERIC_SERVLET))
			return true;
		
		return false;
	}
	
	public boolean isServletSuperclass() {
		if (QUALIFIED_HTTP_SERVLET.equals(getSuperclass()))
			return true;
		
		if (QUALIFIED_GENERIC_SERVLET.equals(getSuperclass()))
			return true;
		
		if (getInterfaces().contains(QUALIFIED_SERVLET))
			return true;
		
		if (hasSuperInterface(getSuperclass(), QUALIFIED_SERVLET))
			return true;
		
		for (Object iface : getInterfaces()) {
			if (hasSuperInterface((String) iface, QUALIFIED_SERVLET)) 
				return true;
		}
		
		return false;
	}

}
