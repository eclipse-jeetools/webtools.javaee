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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EjbRefType;
import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.MetadataHistory;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHistory;
import org.eclipse.wst.common.emf.utilities.ExtendedEcoreUtil;


/**
 * Insert the type's description here. Creation date: (9/1/2000 2:13:20 PM)
 * 
 * @author: Administrator
 */
public abstract class PersistentRoleCommand extends PersistentFeatureCommand {
	protected String typeName;

	protected String relationshipName;

	protected boolean navigable = false;

	protected boolean forward = false;

	/**
	 * PersistentRoleCommand constructor comment.
	 */
	public PersistentRoleCommand(IRootCommand parent, String aName) {
		this(parent, aName, true);
	}

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public PersistentRoleCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		this(parent, aName, shouldGenJava, true);
	}

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public PersistentRoleCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
	}

	protected void addRoleToKey() {
		CommonRelationshipRole role = getCommonRole();
		if (role != null) {
			ContainerManagedEntity cmp = getContainerManagedEntity();
			if (cmp != null) {
				// remove prim key if it exists
				cmp.setPrimKeyField(null);

				List atts = role.getAttributes();
				if (atts.isEmpty())
					role.setKey(true);
				else {
					EList persistAtts = cmp.getPersistentAttributes();
					try {
						cmp.getKeyAttributes().addAll(atts);
						persistAtts.removeAll(atts);

					} finally {
						persistAtts.addAll(atts);
					}
				}

			}
		}
	}

	protected void addRoleToKeyIfNecessary() {
		if (isKey())
			addRoleToKey();
	}

	/**
	 * primCreateCodegenHelper method comment.
	 */
	protected EJBGenerationHelper createCodegenHelper() {
		return new RoleHelper(getCommonRole());
	}

	public EjbRef createEJBReference() {
		EnterpriseBean linkedEJB = getCommonRole().getTypeEntity();
		EjbRef ref = CommonFactory.eINSTANCE.createEjbRef();
		ref.setName(org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers.getEJBRefName(linkedEJB));
		ref.setType(EjbRefType.ENTITY_LITERAL);
		ref.setHome(linkedEJB.getHomeInterfaceName());
		ref.setRemote(linkedEJB.getRemoteInterfaceName());
		ref.setLink(linkedEJB.getName());
		getEjb().getEjbRefs().add(ref);
		return ref;
	}

	protected void createEJBReferenceIfNecessary() {
		if (getEjb() != null) {
			CommonRelationshipRole role = getCommonRole();
			if (role != null) {
				ContainerManagedEntity cmp = role.getTypeEntity();
				if (cmp != null && getLinkedEjbReference(cmp) == null)
					createEJBReference();
			}
		}
	}

	protected EjbRef getLinkedEjbReference(ContainerManagedEntity cmp) {
		return getEjb().getLinkedEJBReference(cmp);
	}

	/**
	 * primCreateCodegenHelper method comment.
	 */
	protected EJBGenerationHelper createInverseCodegenHelper() {
		//Need to create a codegen helper for roles.
		return null;
	}

	public void deleteEJBReference() {
		if (getEjb() != null) {
			CommonRelationshipRole role = getCommonRole();
			if (role != null) {
				ContainerManagedEntity cmp = role.getTypeEntity();
				if (cmp != null) {
					EjbRef ref = getLinkedEjbReference(cmp);
					if (ref != null) {
						removeEjbReference(ref);
					}
				}
			}
		}
	}

	protected boolean removeEjbReference(EjbRef ref) {
		ExtendedEcoreUtil.becomeProxy(ref, ref.eResource());
		return getEjb().getEjbRefs().remove(ref);
	}

	protected String getDefaultJndiName(EnterpriseBean anEjb) {
		return anEjb.getHomeInterface().getName();
	}

	public CommonRelationshipRole getOriginalCommonRole() {
		return (CommonRelationshipRole) getMetadataCopy();
	}

	protected CommonRelationship getCommonRelationship() {
		CommonRelationship relationship = null;
		if (getCommonRole() != null)
			relationship = getCommonRole().getCommonRelationship();
		return relationship;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 4:42:37 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getRelationshipName() {
		return relationshipName;
	}

	protected Entity getTypeFromName() {
		if (getTypeName() != null)
			return (Entity) getEJBJar().getEnterpriseBeanNamed(getTypeName());
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (11/13/2000 5:00:01 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTypeName() {
		return typeName;
	}

	protected void initializeKey() {
		if (!isKey()) {
			CommonRelationshipRole role = getCommonRole();
			if (role != null)
				setKey(role.isKey());
		}
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 3:19:17 PM)
	 * 
	 * @return boolean
	 */
	public boolean isForward() {
		return forward;
	}

	/**
	 * Insert the method's description here. Creation date: (9/11/2000 3:14:37 PM)
	 * 
	 * @return boolean
	 */
	public boolean isNavigable() {
		return navigable;
	}

	/**
	 * Override to return the proper MetadataHistory.
	 */
	protected MetadataHistory primCreateMetadataHistory() {
		return new RoleHistory();
	}

	protected void removeRoleFromKey() {
		CommonRelationshipRole commonRole = getCommonRole();
		if (commonRole != null) {
			ContainerManagedEntity cmp = commonRole.getSourceEntity();
			if (cmp != null)
				cmp.getKeyAttributes().removeAll(commonRole.getAttributes());
		}
	}

	protected void removeRoleFromKeyIfNecessary() {
		if (isKey())
			removeRoleFromKey();
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 3:19:17 PM)
	 * 
	 * @param newForward
	 *            boolean
	 */
	public void setForward(boolean newForward) {
		forward = newForward;
	}

	/**
	 * Insert the method's description here. Creation date: (9/11/2000 3:14:37 PM)
	 * 
	 * @param newNavigable
	 *            boolean
	 */
	public void setNavigable(boolean newNavigable) {
		navigable = newNavigable;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 4:42:37 PM)
	 * 
	 * @param newRelationshipName
	 *            java.lang.String
	 */
	public void setRelationshipName(java.lang.String newRelationshipName) {
		relationshipName = newRelationshipName;
	}

	protected void setCommonRole(CommonRelationshipRole aRole) {
		setSourceMetaType(aRole);
	}

	/**
	 * Insert the method's description here. Creation date: (11/13/2000 5:00:01 PM)
	 * 
	 * @param newTypeName
	 *            java.lang.String
	 */
	public void setTypeName(java.lang.String newTypeName) {
		typeName = newTypeName;
	}

	public CommonRelationshipRole getCommonRole() {
		return (CommonRelationshipRole) getSourceMetaType();
	}

	protected void addRoleToRelationshipIfNecessary() {
		if (getCommonRelationship() != null)
			getCommonRelationship().getCommonRoles().add(getCommonRole());
	}

	public boolean isDeleteCommand() {
		return false;
	}
}