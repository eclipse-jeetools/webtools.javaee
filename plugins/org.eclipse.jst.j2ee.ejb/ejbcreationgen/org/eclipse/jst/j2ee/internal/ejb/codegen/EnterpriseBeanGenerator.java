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
import java.util.List;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitGroupGenerator;


/**
 * Insert the type's description here. Creation date: (10/3/00 5:34:42 PM)
 * 
 * @author: Steve Wasleski
 */
public abstract class EnterpriseBeanGenerator extends JavaCompilationUnitGroupGenerator {
	/**
	 * CMPEntityBeanGenerator constructor comment.
	 */
	public EnterpriseBeanGenerator() {
		super();
	}

	protected boolean canModifySource(JavaClass aJavaClass) {
		return org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper.canModifySource(aJavaClass);
	}

	/**
	 * Subclasses must implement this method to return the logical compilation unit generator names.
	 */

	protected abstract List getCUGeneratorNames();

	/**
	 * Insert the method's description here. Creation date: (9/14/2001 6:48:17 PM)
	 */
	protected EnterpriseBean getEjb() {
		return (EnterpriseBean) getSourceElement();
	}

	/**
	 * This implementation gets the compilation unit generator names and instatiates them as
	 * children.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		Iterator cuGeneratorNames = getCUGeneratorNames().iterator();
		IBaseGenerator temp = null;
		while (cuGeneratorNames.hasNext()) {
			temp = getGenerator((String) cuGeneratorNames.next());
			temp.initialize(mofObject);
		}
	}

	protected EnterpriseBeanHelper getEnterpriseBeanHelper() {
		return (EnterpriseBeanHelper) getTopLevelHelper();
	}

	protected boolean shouldGenEjbClass() {
		return canModifySource(getEjb().getEjbClass());
	}

	protected boolean shouldGenRemote() {
		if (getEnterpriseBeanHelper().isCreate())
			return getEnterpriseBeanHelper().getRemoteHelper() != null;
		return (getEnterpriseBeanHelper().getRemoteHelper() != null || (getEjb().getRemoteInterface() != null && canModifySource(getEjb().getRemoteInterface())));
	}

	protected boolean shouldGenHome() {
		if (getEnterpriseBeanHelper().isCreate())
			return getEnterpriseBeanHelper().getHomeHelper() != null;
		return (getEnterpriseBeanHelper().getHomeHelper() != null || ((!getEnterpriseBeanHelper().isEJBRemoved() || getEnterpriseBeanHelper().isDeleteAll()) && getEjb().getHomeInterface() != null && canModifySource(getEjb().getHomeInterface())));

	}

	protected boolean shouldGenLocal() {
		if (getEnterpriseBeanHelper().isCreate())
			return getEnterpriseBeanHelper().getLocalHelper() != null;
		return (getEnterpriseBeanHelper().getLocalHelper() != null || (getEjb().getLocalInterface() != null && canModifySource(getEjb().getLocalInterface())));
	}

	protected boolean shouldGenLocalHome() {
		if (getEnterpriseBeanHelper().isCreate())
			return getEnterpriseBeanHelper().getLocalHomeHelper() != null;
		return (getEnterpriseBeanHelper().getLocalHomeHelper() != null || ((!getEnterpriseBeanHelper().isEJBRemoved() || getEnterpriseBeanHelper().isDeleteAll()) && getEjb().getLocalHomeInterface() != null && canModifySource(getEjb().getLocalHomeInterface())));
	}

}