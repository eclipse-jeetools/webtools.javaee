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

import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb20.codegen.IEJB20GenConstants;


/**
 * Insert the type's description here. Creation date: (10/3/00 5:34:42 PM)
 * 
 * @author: Steve Wasleski
 */
public class CMPEntityGenerator extends EntityGenerator {
	private List fGeneratorNames = null;

	/**
	 * CMPEntityBeanGenerator constructor comment.
	 */
	public CMPEntityGenerator() {
		super();
	}

	/**
	 * Returns the list of generator names.
	 */
	protected List getCUGeneratorNames() {
		if (fGeneratorNames == null) {
			fGeneratorNames = new ArrayList();
			EntityHelper helper = (EntityHelper) getEnterpriseBeanHelper();
			if (!helper.isMigrationCleanup()) {
				if (shouldGenRemote())
					fGeneratorNames.add(IEJBGenConstants.CMP_ENTITY_REMOTE_INTERFACE_CU);
				if (shouldGenHome())
					fGeneratorNames.add(IEJBGenConstants.CMP_ENTITY_HOME_INTERFACE_CU);
				if (shouldGenLocal())
					fGeneratorNames.add(IEJB20GenConstants.CMP20_ENTITY_LOCAL_INTERFACE_CU);
				if (shouldGenLocalHome())
					fGeneratorNames.add(IEJB20GenConstants.CMP20_ENTITY_LOCAL_HOME_INTERFACE_CU);
			}
			if (shouldGenEjbClass())
				fGeneratorNames.add(IEJBGenConstants.CMP_ENTITY_BEAN_CLASS_CU);
			if (!helper.isMigrationCleanup() && shouldAddPrimaryKeyGenerator())
				fGeneratorNames.add(IEJBGenConstants.CMP_ENTITY_KEY_CLASS_CU);
		}
		return fGeneratorNames;
	}

	/**
	 * This implementation gets the compilation unit generator names and instatiates them as
	 * children.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
	}

	/**
	 * Return true if we should have a primary key generator.
	 */
	protected boolean shouldAddPrimaryKeyGeneratorForDelete() {
		return getOldSourceSupertype() == null && !((EntityHelper) getTopLevelHelper()).hasPrimaryKeyAttribute() && ((ContainerManagedEntity) getSourceElement()).getPrimaryKey() != null;
	}

	/**
	 * Return true if we should have a primary key generator.
	 */
	protected boolean shouldAddPrimaryKeyGeneratorForUpdate() {
		boolean result = true;
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		if (getSourceSupertype() != null) {
			//only the root should update the key
			result = false;
			//unless the following is true
			if (helper.isChangingInheritance()) {
				//check if we are deleting the key
				result = helper.getKeyHelper() != null;
			}
		}
		//Should we check for key shape changing at this point to avoid adding
		//the generator at all?

		return result && !getEnterpriseBeanHelper().isEJBRemoved() && !helper.hasPrimaryKeyAttribute() && ((ContainerManagedEntity) getSourceElement()).getPrimaryKey() != null;
	}
}