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
package org.eclipse.jst.j2ee.internal.ejb.commands;



import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.impl.RequiredRelationshipRoleFilter;
import org.eclipse.jst.j2ee.ejb.util.CMPKeySynchronizationAdapter;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb20.commands.ContainerManagedEntity20CodegenCommand;


/**
 * Insert the type's description here. Creation date: (9/12/2000 12:13:36 PM)
 * 
 * @author: Administrator
 */
public abstract class ContainerManagedEntityCommand extends EntityCommand {
	/**
	 * ContainerManagedEntityCommand constructor comment.
	 * 
	 * @param aName
	 *            java.lang.String
	 * @param aFolder
	 *            com.ibm.itp.core.api.resources.IFolder
	 */
	public ContainerManagedEntityCommand(EnterpriseBean anEjb, EJBEditModel anEditModel) {
		super(anEjb, anEditModel);
	}

	/**
	 * This command will update the EJBModel.ejbxmi resource held by aProject.
	 * 
	 * @param java.lang.String
	 *            aName
	 * @param java.lang.String
	 *            aFolderName
	 * 
	 * aName - The name and ID of the EJB. aProject - The project in which to create the folder.
	 */
	public ContainerManagedEntityCommand(String aName, EJBEditModel anEditModel) {
		super(aName, anEditModel);
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 10:01:03 AM)
	 */
	protected EnterpriseBeanCodegenCommand createCodegenCommand() {
		if (isVersion2_XOrHigher())
			return new ContainerManagedEntity20CodegenCommand(getContainerManagedEntity());
		return new ContainerManagedEntityCodegenCommand(getContainerManagedEntity());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		CMPKeySynchronizationAdapter syncAdapter = null;
		try {
			if (getContainerManagedEntity() != null) {
				syncAdapter = ((CMPKeySynchronizationAdapter) EcoreUtil.getExistingAdapter(getContainerManagedEntity(), CMPKeySynchronizationAdapter.ADAPTER_TYPE));
				if (syncAdapter != null)
					syncAdapter.setEnabled(false);
			}
			super.execute();
		} finally {
			if (syncAdapter != null)
				syncAdapter.setEnabled(true);
		}

	}

	/**
	 * createEJB method comment.
	 */
	protected EnterpriseBean createEJB() {
		EnterpriseBean bean = getEJBFactory().createContainerManagedEntity();
		bean.setName(getName());
		getEditModel().getEjbXmiResource().setID(bean, getName());
		return bean;
	}

	protected ContainerManagedEntity getContainerManagedEntity() {
		return (ContainerManagedEntity) getEjb();
	}

	protected String getPrimaryKeyQualifiedName() {
		if (getContainerManagedEntity().getPrimKeyField() != null)
			return null;
		return super.getPrimaryKeyQualifiedName();
	}

	protected List getRequiredRoles(ContainerManagedEntity cmp) {
		if (cmp != null) {
			return cmp.getFilteredFeatures(RequiredRelationshipRoleFilter.singleton());
		}
		return null;
	}

	/**
	 * Return true if the key shape is changing or the number of required roles is changing.
	 */
	protected boolean needToRegenerateSubtypes() {
		boolean result = super.needToRegenerateSubtypes();
		if (!result) {
			ContainerManagedEntity oldCmp = (ContainerManagedEntity) getOldEjb();
			List reqRoles, oldReqRoles;
			reqRoles = getRequiredRoles(getContainerManagedEntity());
			oldReqRoles = getRequiredRoles(oldCmp);
			if (reqRoles == null && oldReqRoles == null)
				result = false;
			else {
				result = (reqRoles != null && oldReqRoles == null) || (oldReqRoles != null && reqRoles == null) || reqRoles.size() != oldReqRoles.size();
			}
		}
		return result;
	}

	/**
	 * The primary key class needs to be updated. This needs to be done here because we need to wait
	 * until the bean class has been set with the EJB.
	 */

	protected void postExecuteChildren() {
		super.postExecuteChildren();
		updatePrimaryKey();
	}

	/**
	 * Do whatever is needed prior to saving resources during undo.
	 */
	protected void postUndoChildren() {
		super.postUndoChildren();
		updatePrimaryKey();
	}

	/**
	 * If there is not a primaryKey and there is only one keyAttribute, set the primaryKey to be the
	 * type of the keyAttribute.
	 */
	protected void setPrimaryKeyFor(CMPAttribute keyAttribute) {
		JavaHelpers primKeyType = keyAttribute.getType();
		if (primKeyType != null)
			if (!primKeyType.isPrimitive())
				getContainerManagedEntity().setPrimaryKey((JavaClass) primKeyType);
			else
				setPrimaryKeyFor((PersistentAttributeCommand) getChildCommand(keyAttribute));
	}

	/**
	 * Set the primaryKey based on the type from
	 * 
	 * @anAttributeCommand.
	 */
	protected void setPrimaryKeyFor(PersistentAttributeCommand anAttributeCommand) {
		if (anAttributeCommand != null) {
			String primKeyTypeName = anAttributeCommand.getTypeName();
			if (primKeyTypeName != null && !EJBGenHelpers.isPrimitive(primKeyTypeName))
				getContainerManagedEntity().setPrimaryKeyName(primKeyTypeName);
		}
	}

	protected void setShouldPropagate(boolean aBoolean) {
		shouldPropagate = aBoolean;
	}

	/**
	 * The primary key class needs to be updated. This needs to be done here because we need to wait
	 * until the bean class has been set with the EJB.
	 */

	protected void updatePrimaryKey() {
		if (shouldGenerateMetadata())
			updatePrimaryKeyMetadata();
	}

	/**
	 * If there is not a primaryKey and there is only one keyAttribute, set the primaryKey to be the
	 * type of the keyAttribute.
	 */
	protected void updatePrimaryKeyMetadata() {
		if (getContainerManagedEntity() != null) {
			java.util.List keyAttributes = getContainerManagedEntity().getKeyAttributes();
			if (keyAttributes.size() == 1) {
				JavaClass keyClass = getContainerManagedEntity().getPrimaryKey();
				CMPAttribute primKey = (CMPAttribute) keyAttributes.get(0);
				if (keyClass == null) {
					setPrimaryKeyFor(primKey);
					keyClass = getContainerManagedEntity().getPrimaryKey();
				}
				if (keyClass != null && primKey.getType().equals(keyClass))
					getContainerManagedEntity().setPrimKeyField(primKey);
			}
		}
	}
}