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



import java.util.Iterator;

import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:26:21 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityRemoteInterface extends EnterpriseBeanClientInterface {
	/**
	 * EntityRemoteInterface constructor comment.
	 */
	public EntityRemoteInterface() {
		super();
	}

	/**
	 * Returns the logical name for an attribute generator.
	 */
	public String getAttributeGeneratorName(AttributeHelper attrHelper) {
		String name = null;
		if (attrHelper.isUpdate())
			name = attrHelper.getUpdateGeneratorName();
		if (name == null)
			name = IEJBGenConstants.ENTITY_ATTRIBUTE;
		return name;
	}

	//
	//protected boolean hasInterfacesToRemove() {
	//	boolean result = super.hasInterfacesToRemove();
	//	if (!result && isRemote())
	//		return hasAccessBeanInterfacesToRemove();
	//	return result;
	//}
	/**
	 * This implementation sets the mofObject as the source element and checks for a remote
	 * interface helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		Iterator attrHelpers = ((EntityHelper) getTopLevelHelper()).getAttributeHelpers().iterator();
		AttributeHelper attrHelper = null;
		IBaseGenerator attrGen = null;
		while (attrHelpers.hasNext()) {
			attrHelper = (AttributeHelper) attrHelpers.next();
			attrGen = getGenerator(getAttributeGeneratorName(attrHelper));
			attrGen.initialize(attrHelper);
		}
	}
}