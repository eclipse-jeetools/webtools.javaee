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



import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaFieldGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberHistoryDescriptor;


/**
 * Insert the type's description here. Creation date: (9/5/2001 3:34:06 PM)
 * 
 * @author: Administrator
 */
public abstract class EnterpriseBeanContextField extends JavaFieldGenerator {
	/**
	 * EnterpriseBeanContextField constructor comment.
	 */
	public EnterpriseBeanContextField() {
		super();
	}

	/**
	 * We need to delete any generator that is added if the EJB is becoming a root.
	 */
	protected JavaMemberHistoryDescriptor createHistoryDescriptor(JavaMemberDescriptor desc) throws GenerationException {
		JavaMemberHistoryDescriptor historyDesc = super.createHistoryDescriptor(desc);
		if (shouldDelete())
			historyDesc.setDeleteOnly(true);
		return historyDesc;
	}

	protected boolean shouldDelete() {
		EnterpriseBeanHelper helper = (EnterpriseBeanHelper) getTopLevelHelper();
		return !helper.isBecomingRootEJB();
	}
}