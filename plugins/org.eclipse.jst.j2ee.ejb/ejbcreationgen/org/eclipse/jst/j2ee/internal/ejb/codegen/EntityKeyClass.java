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
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.PrimaryKeyHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;


/**
 * Insert the type's description here. Creation date: (10/11/00 10:02:06 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityKeyClass extends EnterpriseBeanClass {
	private String fName = null;

	/**
	 * EntityKeyClass constructor comment.
	 */
	public EntityKeyClass() {
		super();
	}

	protected List getActualKeyAttributeHelpers() {
		if (getClassRefHelper() != null && getClassRefHelper().isKeyHelper() && (!((EntityHelper) getTopLevelHelper()).hasRoleHelpers() || getClassRefHelper().isCreate()))
			return ((PrimaryKeyHelper) getClassRefHelper()).getKeyAttributeHelpers();
		return ((EntityHelper) getTopLevelHelper()).getKeyAttributeHelpersForKeyClass();
	}

	/**
	 * Return either the actual role helpers or the key propagation helpers.
	 */
	protected List getActualRoleHelpers() {
		if (getClassRefHelper() != null && getClassRefHelper().isKeyHelper())
			return ((PrimaryKeyHelper) getClassRefHelper()).getRoleHelpers();
		return super.getActualRoleHelpers();
	}

	/**
	 * Returns the logical name for an attribute generator.
	 */
	public String getAttributeGeneratorName(AttributeHelper attrHelper) {
		String name = null;
		if (attrHelper.isUpdate())
			name = attrHelper.getUpdateGeneratorName();
		if (name == null)
			name = IEJBGenConstants.ENTITY_ATTRIBUTE;
		return name;
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Key class for Entity Bean: " + ((Entity) getSourceElement()).getName() + IEJBGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
	}

	/**
	 * getInheritedSuperInterfaceNames method comment.
	 */
	protected List getInheritedSuperInterfaceNames() {
		List names = new ArrayList();
		names.add(IEJBGenConstants.SERIALIZABLE_INTERFACE_NAME);
		return names;
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		if (fName == null)
			fName = Signature.getSimpleName(((Entity) getSourceElement()).getPrimaryKeyName());
		return fName;
	}

	/**
	 * Override to get required member generator names.
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		EJBClassReferenceHelper refHelper = getClassRefHelper();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		List names = new ArrayList();

		// Do them all on create.
		if ((refHelper != null) && (refHelper.isCreate())) {
			names.add(IEJBGenConstants.EJB_SUID_FIELD);
			names.add(IEJBGenConstants.ENTITY_KEY_DEFAULT_CTOR);
			Entity entity = topHelper.getEntity();
			if ((entity.isContainerManagedEntity()) && (((ContainerManagedEntity) entity).getKeyAttributes().size() > 0))
				names.add(IEJBGenConstants.ENTITY_KEY_FEATURE_CTOR);
			names.add(IEJBGenConstants.ENTITY_KEY_EQUALS);
			names.add(IEJBGenConstants.ENTITY_KEY_HASHCODE);
		} else {
			// If not delete and there are feature helpers, redo ejbCreate.
			if (((refHelper == null) || (!refHelper.isDelete())) && topHelper.isKeyShapeChanging()) {
				names.add(IEJBGenConstants.ENTITY_KEY_FEATURE_CTOR);
				names.add(IEJBGenConstants.ENTITY_KEY_EQUALS);
				names.add(IEJBGenConstants.ENTITY_KEY_HASHCODE);
			}
		}
		return names;
	}

	/**
	 * Return either the actual role helpers or the key propagation helpers.
	 */
	protected List getRoleHelpers() {
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		if (helper.isKeyClassChanging() && helper.getKeyHelper() != null && helper.getKeyHelper().isCreate())
			return ((PrimaryKeyHelper) helper.getKeyHelper()).getRoleHelpers();
		return super.getRoleHelpers();
	}

	/**
	 * This implementation sets the mofObject as the source element and checks for a key class
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		setClassRefHelper(((EntityHelper) getTopLevelHelper()).getKeyHelper());
		super.initialize(mofObject);
		Iterator attrHelpers = getActualKeyAttributeHelpers().iterator();
		AttributeHelper attrHelper = null;
		IBaseGenerator gen = null;
		while (attrHelpers.hasNext()) {
			attrHelper = (AttributeHelper) attrHelpers.next();
			gen = getGenerator(getAttributeGeneratorName(attrHelper));
			gen.initialize(attrHelper);
		}
		List roleHelpers = getRoleHelpers();
		RoleHelper roleHelper;
		for (int i = 0; i < roleHelpers.size(); i++) {
			roleHelper = (RoleHelper) roleHelpers.get(i);
			gen = getGenerator(IEJBGenConstants.CMP_ENTITY_KEY_ROLE_GROUP);
			gen.initialize(roleHelper);
		}
	}

	public boolean isKeyGenerator() {
		return true;
	}


	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.codegen.EnterpriseBeanClass#getJavaTypeClass(EnterpriseBean)
	 */
	protected JavaClass getJavaTypeClass(EnterpriseBean ejb) {
		return ((Entity) ejb).getPrimaryKey();
	}

}