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
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityBeanEjbFindByPrimaryKey extends EntityBeanEjbMethod {
	/**
	 * EntityBeanEjbFindByPrimaryKey constructor comment.
	 */
	public EntityBeanEjbFindByPrimaryKey() {
		super();
	}

	/**
	 * Return null per the spec.
	 */
	protected String getBody() throws GenerationException {
		return "return null;\n";//$NON-NLS-1$
	}

	/**
	 * Throw the EJB specified exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		if (getDeclaringTypeGenerator().isInterface())
			return new String[]{IEJBGenConstants.FINDER_EXCEPTION_NAME, IEJBGenConstants.REMOTE_EXCEPTION_NAME};
		return new String[]{IEJBGenConstants.FINDER_EXCEPTION_NAME};
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "ejbFindByPrimaryKey";//$NON-NLS-1$
	}

	/**
	 * The new context is the parameter.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		JavaParameterDescriptor[] parms = new JavaParameterDescriptor[1];
		Entity entity = (Entity) getSourceElement();

		// First check for a key class type.
		String keyType = entity.getPrimaryKeyName();
		/*
		 * ####### NOT SUPPORTED YET -- WE WILL ADD A SEPARATE COMMAND AND HELPER FOR KEY CLASS TYPE
		 * CHANGE if (keyType == null) { // If none, it must be a single key attribute without a
		 * wrapper or no key. // Check for a single key fieldl Field keyField =
		 * entity.getPrimaryKeyField(); if (keyField != null) { // Got one, check for a key
		 * attribute helper and get the type from it. // If there is one, it means this is just
		 * being created or the field is changing. Vector keyAttrFeatures =
		 * ((EntityHelper)getTopLevelHelper()).getKeyAttributeHelpers(); // At this point any more
		 * than one feature is an error. if ((keyAttrFeatures.size() == 1) &&
		 * (((EntityHelper)getTopLevelHelper()).getRoleHelpers().size() == 0)) { keyType =
		 * ((AttributeHelper)keyAttrFeatures.elementAt(0)).getTypeName(); } else { throw new
		 * GenerationException("More than one key feature or only key feature is a role and no key
		 * class specified."); } // If not found yet, there is a key field but no helper. Get the
		 * reflected type. // If we can not reflect, there should have been a helper but there was
		 * not. if (keyType == null) { com.ibm.mof.mof13.Classifier type = keyField.getType(); if
		 * (type != null) keyType = type.getQualifiedName(); else throw new GenerationException("New
		 * key field does not have an attribute helper."); } } // If still none and no exception, it
		 * must be the no key case. if (keyType == null) keyType =
		 * IEJBGenConstants.OBJECT_CLASS_NAME; }
		 */
		parms[0] = new JavaParameterDescriptor();
		parms[0].setName("primaryKey");//$NON-NLS-1$
		parms[0].setType(keyType);
		return parms;
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
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		if (!helper.isDeleteAll() && helper.isKeyClassChanging()) {
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