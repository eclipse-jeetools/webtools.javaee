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


import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodHistoryDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityBeanEjbPostCreate extends EntityBeanEjbMethod {
	/**
	 * EntityBeanEjbPostCreate constructor comment.
	 */
	public EntityBeanEjbPostCreate() {
		super();
	}

	/**
	 * Throw the EJB specified exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		if (getDeclaringTypeGenerator().isInterface())
			return new String[]{IEJBGenConstants.CREATE_EXCEPTION_NAME, IEJBGenConstants.REMOTE_EXCEPTION_NAME};
		return new String[]{IEJBGenConstants.CREATE_EXCEPTION_NAME};
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "ejbPostCreate";//$NON-NLS-1$
	}

	/**
	 * The parms are each key attribute and a bean for a role in the key.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		Entity entity = (Entity) getSourceElement();
		return getParameterDescriptors(entity);
	}

	/**
	 * The parms are each key attribute and a bean for a role in the key.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors(Entity entity) throws GenerationException {
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		switch (entity.getVersionID()) {
			case J2EEVersionConstants.EJB_1_0_ID :
			case J2EEVersionConstants.EJB_1_1_ID :
				return EJBGenHelpers.getEntityRequiredFieldsAsBeanParms(entity, topHelper, getSourceContext().getNavigator());
			default :
				return EJBGenHelpers.getEntityKeyFieldsAsBeanParms(entity, topHelper, getSourceContext().getNavigator());
		}
	}

	/**
	 * This implementation sets the mofObject as the source element and checks old field info in the
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);

		initializeHistoryDescriptor();
	}

	/**
	 * Method initializeHistoryDescriptor.
	 */
	protected void initializeHistoryDescriptor() throws GenerationException {

		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		Entity oldBean = (Entity) topHelper.getOldMetaObject();
		if (oldBean != null) {
			JavaMethodHistoryDescriptor historyDesc = new JavaMethodHistoryDescriptor();
			historyDesc.setName(getName());

			JavaParameterDescriptor[] parmDescs = getParameterDescriptors(oldBean);

			if (parmDescs.length > 0) {
				String[] parmTypes = new String[parmDescs.length];
				for (int i = 0; i < parmTypes.length; i++)
					parmTypes[i] = parmDescs[i].getType();
				historyDesc.setParameterTypes(parmTypes);
			}

			setHistoryDescriptor(historyDesc);
		}
	}

}