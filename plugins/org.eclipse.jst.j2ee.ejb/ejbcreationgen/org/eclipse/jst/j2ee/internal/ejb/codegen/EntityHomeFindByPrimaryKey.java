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



import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodHistoryDescriptor;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityHomeFindByPrimaryKey extends EntityHomeMethod {
	/**
	 * EntityHomeCreate constructor comment.
	 */
	public EntityHomeFindByPrimaryKey() {
		super();
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Finds an instance using a key for Entity Bean: " + ((Entity) getSourceElement()).getName() + IEJBGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
	}

	/**
	 * Throw the EJB specified exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		return new String[]{IEJBGenConstants.FINDER_EXCEPTION_NAME, IEJBGenConstants.REMOTE_EXCEPTION_NAME};
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "findByPrimaryKey";//$NON-NLS-1$
	}

	/**
	 * Setup the history descriptor based on a key change.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		if (!helper.isDeleteAll() && helper.isKeyChanging()) {
			Entity oldEntity;
			oldEntity = (Entity) helper.getOldMetaObject();
			if (oldEntity != null) {
				JavaMethodHistoryDescriptor historyDesc = new JavaMethodHistoryDescriptor();
				historyDesc.setName(getName());

				String[] parmTypes = new String[]{oldEntity.getPrimaryKeyName()};
				historyDesc.setParameterTypes(parmTypes);

				setHistoryDescriptor(historyDesc);
			}
		}
	}
}