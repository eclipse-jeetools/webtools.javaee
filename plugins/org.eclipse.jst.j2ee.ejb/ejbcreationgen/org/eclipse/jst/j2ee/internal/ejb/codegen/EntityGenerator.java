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

import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;


/**
 * Insert the type's description here. Creation date: (10/3/00 5:34:42 PM)
 * 
 * @author: Steve Wasleski
 */
public class EntityGenerator extends EnterpriseBeanGenerator {
	private List fGeneratorNames = null;

	/**
	 * CMPEntityBeanGenerator constructor comment.
	 */
	public EntityGenerator() {
		super();
	}

	/**
	 * Subclasses must implement this method to return the logical compilation unit generator names.
	 */
	protected List getCUGeneratorNames() {
		if (fGeneratorNames == null) {
			fGeneratorNames = new ArrayList();
			if (shouldGenRemote())
				fGeneratorNames.add(IEJBGenConstants.ENTITY_REMOTE_INTERFACE_CU);
			if (shouldGenHome())
				fGeneratorNames.add(IEJBGenConstants.ENTITY_HOME_INTERFACE_CU);
			if (shouldGenLocal())
				fGeneratorNames.add(IEJBGenConstants.ENTITY_LOCAL_INTERFACE_CU);
			if (shouldGenLocalHome())
				fGeneratorNames.add(IEJBGenConstants.ENTITY_LOCAL_HOME_INTERFACE_CU);
			if (shouldGenEjbClass())
				fGeneratorNames.add(IEJBGenConstants.ENTITY_BEAN_CLASS_CU);
			if (shouldAddPrimaryKeyGenerator())
				fGeneratorNames.add(IEJBGenConstants.ENTITY_KEY_CLASS_CU);
		}
		return fGeneratorNames;
	}

	/**
	 * Return the old supertype EnterpriseBean for the old sourceElement.
	 * 
	 * @return EnterpriseBean
	 */
	protected EnterpriseBean getOldSourceSupertype() {
		EnterpriseBeanHelper helper = (EnterpriseBeanHelper) getTopLevelHelper();
		return helper.getOldSupertype();

	}

	/**
	 * Return the supertype EnterpriseBean for the sourceElement.
	 * 
	 * @return EnterpriseBean
	 */
	protected EnterpriseBean getSourceSupertype() {
		EnterpriseBean entity = (EnterpriseBean) getSourceElement();
		EjbModuleExtensionHelper helper = getEjbModuleExtension();
		if (helper != null)
			return helper.getSuperType(entity);
		return null;
	}

	protected EjbModuleExtensionHelper getEjbModuleExtension() {
		return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
	}

	/**
	 * Return true if we should have a primary key generator.
	 */
	protected boolean shouldAddPrimaryKeyGenerator() {
		EntityHelper helper = (EntityHelper) getEnterpriseBeanHelper();
		//if (getEnterpriseBeanHelper().isCreate())
		if (helper.getKeyHelper() != null && helper.getKeyHelper().isCreate())
			return helper.getKeyHelper() != null;
		if (!getTopLevelHelper().isDeleteAll() && helper.getKeyHelper() != null && helper.getKeyHelper().isDelete())
			return true;
		if (canModifySource(((Entity) getEjb()).getPrimaryKey())) {
			if (getTopLevelHelper().isDeleteAll())
				return shouldAddPrimaryKeyGeneratorForDelete();
			return shouldAddPrimaryKeyGeneratorForUpdate();
		}

		return false;
	}

	/**
	 * Return true if we should have a primary key generator.
	 */
	protected boolean shouldAddPrimaryKeyGeneratorForDelete() {
		return getOldSourceSupertype() == null && ((Entity) getSourceElement()).getPrimaryKey() != null;
	}

	/**
	 * Return true if we should have a primary key generator.
	 */
	protected boolean shouldAddPrimaryKeyGeneratorForUpdate() {
		return !getEnterpriseBeanHelper().isEJBRemoved() && getSourceSupertype() == null && ((Entity) getSourceElement()).getPrimaryKey() != null;
	}
}