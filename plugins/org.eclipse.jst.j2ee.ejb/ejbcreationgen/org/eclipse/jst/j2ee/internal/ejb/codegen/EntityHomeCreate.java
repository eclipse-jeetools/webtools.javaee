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
public class EntityHomeCreate extends EntityHomeMethod {
	/**
	 * EntityHomeCreate constructor comment.
	 */
	public EntityHomeCreate() {
		super();
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Creates an instance from a key for Entity Bean: " + ((Entity) getSourceElement()).getName() + IEJBGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
	}

	/**
	 * Throw the EJB specified exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		return new String[]{IEJBGenConstants.CREATE_EXCEPTION_NAME, IEJBGenConstants.REMOTE_EXCEPTION_NAME};
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "create";//$NON-NLS-1$
	}

	/**
	 * The parms are each key attribute and a bean for a role in the key.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		Entity entity = (Entity) getSourceElement();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		if (shouldUseFlattenedParameters(entity))
			return EJBGenHelpers.getEntityKeyFieldsAsFlatParms(entity, topHelper, getSourceContext().getNavigator());
		switch (entity.getVersionID()) {
			case J2EEVersionConstants.EJB_1_0_ID :
			case J2EEVersionConstants.EJB_1_1_ID :
				return EJBGenHelpers.getEntityRequiredFieldsAsBeanParms(entity, topHelper, getSourceContext().getNavigator());
			default :
				return EJBGenHelpers.getEntityKeyFieldsAsBeanParms(entity, topHelper, getSourceContext().getNavigator());
		}
	}

	protected boolean shouldUseFlattenedParameters(Entity entity) {
		return entity.isContainerManagedEntity() && entity.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID;
	}

	/**
	 * This implementation sets the mofObject as the source element and checks old field info in the
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);

		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		Entity oldBean = (Entity) topHelper.getOldMetaObject();
		if (oldBean != null) {
			JavaMethodHistoryDescriptor historyDesc = new JavaMethodHistoryDescriptor();
			historyDesc.setName(getName());

			JavaParameterDescriptor[] parmDescs = getOldParameterDescriptors(topHelper, oldBean);

			if (parmDescs.length > 0) {
				String[] parmTypes = new String[parmDescs.length];
				for (int i = 0; i < parmTypes.length; i++)
					parmTypes[i] = parmDescs[i].getType();
				historyDesc.setParameterTypes(parmTypes);
			}

			setHistoryDescriptor(historyDesc);
		}
	}

	protected JavaParameterDescriptor[] getOldParameterDescriptors(EntityHelper topHelper, Entity oldBean) throws GenerationException {
		Entity entity = topHelper.getEntity();
		if (shouldUseFlattenedParameters(entity))
			return EJBGenHelpers.getEntityKeyFieldsAsFlatParms(oldBean, topHelper, getSourceContext().getNavigator());
		return EJBGenHelpers.getEntityRequiredFieldsAsBeanParms(oldBean, topHelper, getSourceContext().getNavigator());
	}
}