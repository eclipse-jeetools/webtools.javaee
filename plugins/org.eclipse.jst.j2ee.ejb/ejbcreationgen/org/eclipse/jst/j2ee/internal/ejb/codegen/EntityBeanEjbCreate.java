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
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodHistoryDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityBeanEjbCreate extends EntityBeanEjbMethod {
	static final String RETURN_LINE = "return null;\n";//$NON-NLS-1$

	/**
	 * EntityBeanEjbCreate constructor comment.
	 */
	public EntityBeanEjbCreate() {
		super();
	}

	/**
	 * Do key initializations and return null per the spec.
	 */
	protected void getBody(IGenerationBuffer bodyBuf) throws GenerationException {
		bodyBuf.indent();
		runDependents(bodyBuf);
		bodyBuf.appendWithMargin(RETURN_LINE);
		bodyBuf.unindent();
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
		return "ejbCreate";//$NON-NLS-1$
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
	 * getReturnType method comment.
	 */
	protected String getReturnType() throws GenerationException {
		return ((Entity) getSourceElement()).getPrimaryKeyName();
	}

	/**
	 * This implementation sets the mofObject as the source element and checks old field info in the
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);

		initializeHistoryDescriptor();

		createDependentGenerator(getAttributeMBDependentGeneratorName());
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

	protected String getAttributeMBDependentGeneratorName() {
		return IEJBGenConstants.ENTITY_BEAN_EJBCREATE_MB;
	}
}