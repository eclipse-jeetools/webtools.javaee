/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.SessionHomeInterface;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;


public class SessionLocalHomeInterface extends SessionHomeInterface {

	/**
	 * Constructor for SessionLocalHomeInterface.
	 */
	public SessionLocalHomeInterface() {
		super();
	}

	/*
	 * @see EnterpriseBeanHomeInterface#isRemote()
	 */
	public boolean isRemote() {
		return false;
	}

	/**
	 * Override to get required member generator names.
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		EJBClassReferenceHelper refHelper = getClassRefHelper();
		List names = new ArrayList();

		// Do them all on create.
		if ((refHelper != null) && (refHelper.isCreate())) {
			names.add(IEJBGenConstants.SESSION_LOCAL_HOME_CREATE);
		}
		return names;
	}

}