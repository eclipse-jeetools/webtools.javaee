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
package org.eclipse.jst.j2ee.internal.ejb.codegen;



import java.util.List;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.GeneratorNotFoundException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclipse.jst.j2ee.internal.ejb20.codegen.IEJB20GenConstants;


/**
 * Insert the type's description here. Creation date: (5/7/2001 11:30:09 AM)
 * 
 * @author: Administrator
 */
public class CMPEntityRemoteInterface extends EntityRemoteInterface {
	/**
	 * CMPEntityRemoteInterface constructor comment.
	 */
	public CMPEntityRemoteInterface() {
		super();
	}

	/**
	 * This implementation sets the mofObject as the source element and creates role generators.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		List roleHelpers = ((EntityHelper) getTopLevelHelper()).getRoleOrKeyPropagationHelpers();
		RoleHelper roleHelper = null;
		IBaseGenerator gen = null;
		for (int i = 0; i < roleHelpers.size(); i++) {
			roleHelper = (RoleHelper) roleHelpers.get(i);
			gen = getRoleGenerator();
			if (gen != null)
				gen.initialize(roleHelper);
		}
	}

	protected IBaseGenerator getRoleGenerator() throws GeneratorNotFoundException {
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		switch (helper.getEjb().getVersionID()) {
			case J2EEVersionConstants.EJB_1_0_ID :
			case J2EEVersionConstants.EJB_1_1_ID :
				return getGenerator(IEJBGenConstants.CMP_ENTITY_BEAN_ROLE);
			case J2EEVersionConstants.EJB_2_0_ID :
			case J2EEVersionConstants.EJB_2_1_ID :
			default :
				return null;
		}
	}

	public String getAttributeGeneratorName(AttributeHelper attrHelper) {
		String name = null;
		if (attrHelper.isUpdate())
			name = attrHelper.getUpdateGeneratorName();
		if (name == null) {
			switch (((EntityHelper) getTopLevelHelper()).getEjb().getVersionID()) {
				case J2EEVersionConstants.EJB_1_0_ID :
				case J2EEVersionConstants.EJB_1_1_ID :
					name = IEJBGenConstants.ENTITY_ATTRIBUTE;
					break;
				case J2EEVersionConstants.EJB_2_0_ID :
				case J2EEVersionConstants.EJB_2_1_ID :
				default :
					name = IEJB20GenConstants.ENTITY20_ATTRIBUTE;
					break;
			}
		}
		return name;
	}
}