/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on May 6, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class J2EEReferenceProviderHelper {

	public static Collection getReferences(EnterpriseBean bean) {
		List result = new ArrayList();
		if (!bean.getEjbRefs().isEmpty())
			result.addAll(bean.getEjbRefs());
		if (!bean.getEjbLocalRefs().isEmpty())
			result.addAll(bean.getEjbLocalRefs());
		if (!bean.getResourceRefs().isEmpty())
			result.addAll(bean.getResourceRefs());
		if (!bean.getSecurityRoleRefs().isEmpty())
			result.addAll(bean.getSecurityRoleRefs());
		if (!bean.getResourceEnvRefs().isEmpty())
			result.addAll(bean.getResourceEnvRefs());
		return result;
	}

}