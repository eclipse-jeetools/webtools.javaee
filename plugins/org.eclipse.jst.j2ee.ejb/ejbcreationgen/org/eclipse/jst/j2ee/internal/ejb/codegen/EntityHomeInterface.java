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



import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;


/**
 * Insert the type's description here. Creation date: (10/11/00 6:11:34 PM)
 * 
 * @author: Steve Wasleski
 */
public class EntityHomeInterface extends EnterpriseBeanHomeInterface {
	/**
	 * EntityHomeInterface constructor comment.
	 */
	public EntityHomeInterface() {
		super();
	}

	/**
	 * Override to get required member generator names.
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		EJBClassReferenceHelper refHelper = getClassRefHelper();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		List names = new ArrayList();

		// Do them all on create.
		if ((refHelper != null) && (refHelper.isCreate()) || topHelper.isKeyChanging()) {
			names.add(IEJBGenConstants.ENTITY_HOME_CREATE);
			names.add(IEJBGenConstants.ENTITY_HOME_FIND_BY_PRIMARY_KEY);
		} else {
			// If not delete and there are feature helpers, redo create.
			if (((refHelper == null) || (!refHelper.isDelete())) && (topHelper.isKeyShapeChanging())) {
				names.add(IEJBGenConstants.ENTITY_HOME_CREATE);
			}
		}
		return names;
	}
}