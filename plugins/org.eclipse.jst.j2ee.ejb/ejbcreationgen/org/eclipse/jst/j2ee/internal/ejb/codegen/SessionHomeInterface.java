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


/**
 * Insert the type's description here. Creation date: (10/11/00 6:11:34 PM)
 * 
 * @author: Steve Wasleski
 */
public class SessionHomeInterface extends EnterpriseBeanHomeInterface {
	/**
	 * SessionHomeInterface constructor comment.
	 */
	public SessionHomeInterface() {
		super();
	}

	/**
	 * Override to get required member generator names.
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		EJBClassReferenceHelper refHelper = getClassRefHelper();
		List names = new ArrayList();

		// Do them all on create.
		if ((refHelper != null) && (refHelper.isCreate())) {
			names.add(IEJBGenConstants.SESSION_HOME_CREATE);
		}
		return names;
	}
}