/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EjbRefType;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MultiplicityKind;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.PersistentRoleCommand;


public abstract class Persistent20RoleCommand extends PersistentRoleCommand {
	protected boolean isMany = false;
	protected boolean isCascadeDelete = false;
	protected String cmrFieldName;
	protected String cmrFieldCollectionTypeName;

	/**
	 * Constructor for Persitent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 */
	public Persistent20RoleCommand(IRootCommand parent, String aName) {
		super(parent, aName);
	}

	/**
	 * Constructor for Persitent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 */
	public Persistent20RoleCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		super(parent, aName, shouldGenJava);
	}

	/**
	 * Constructor for Persitent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 * @param shouldGenMetadata
	 */
	public Persistent20RoleCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
	}

	protected EJBRelationshipRole getEJBRelationshipRole() {
		return (EJBRelationshipRole) getSourceMetaType();
	}

	/**
	 * Gets the isMany.
	 * 
	 * @return Returns a boolean
	 */
	public boolean isMany() {
		return isMany;
	}

	/**
	 * Sets the isMany.
	 * 
	 * @param isMany
	 *            The isMany to set
	 */
	public void setIsMany(boolean isMany) {
		this.isMany = isMany;
	}

	/**
	 * Gets the isCascadeDelete.
	 * 
	 * @return Returns a boolean
	 */
	public boolean isCascadeDelete() {
		return isCascadeDelete;
	}

	/**
	 * Sets the isCascadeDelete.
	 * 
	 * @param isCascadeDelete
	 *            The isCascadeDelete to set
	 */
	public void setIsCascadeDelete(boolean isCascadeDelete) {
		this.isCascadeDelete = isCascadeDelete;
	}

	/**
	 * Gets the cmrFieldCollectionTypeName.
	 * 
	 * @return Returns a String
	 */
	public String getCmrFieldCollectionTypeName() {
		return cmrFieldCollectionTypeName;
	}

	/**
	 * Sets the cmrFieldCollectionTypeName.
	 * 
	 * @param cmrFieldCollectionTypeName
	 *            The cmrFieldCollectionTypeName to set
	 */
	public void setCmrFieldCollectionTypeName(String cmrFieldCollectionTypeName) {
		this.cmrFieldCollectionTypeName = cmrFieldCollectionTypeName;
	}

	/**
	 * Gets the cmrFieldName.
	 * 
	 * @return Returns a String
	 */
	public String getCmrFieldName() {
		return cmrFieldName;
	}

	/**
	 * Sets the cmrFieldName.
	 * 
	 * @param cmrFieldName
	 *            The cmrFieldName to set
	 */
	public void setCmrFieldName(String cmrFieldName) {
		this.cmrFieldName = cmrFieldName;
	}

	protected void initializeFeature() {
		if (getEJBRelationshipRole() == null) {
			EJBJar jar = getContainerManagedEntity().getEjbJar();
			setCommonRole(jar.getRelationshipRole(getName(), getContainerManagedEntity()));
		}
	}

	protected void initializeRoleFromName() {
		if (getName() != null) {
			EJBJar jar = getEJBJar();
			if (jar != null)
				setCommonRole(jar.getRelationshipRole(getName(), getContainerManagedEntity()));
		}
	}

	protected EJBRelationshipRole createRelationshipRole() {
		return getEJBFactory().createEJBRelationshipRole();
	}

	public void setRole(EJBRelationshipRole aRole) {
		setCommonRole(aRole);

	}

	protected void setRoleMultiplicity(EJBRelationshipRole aRole) {
		if (aRole != null) {
			if (isMany())
				aRole.setMultiplicity(MultiplicityKind.MANY_LITERAL);
			else
				aRole.setMultiplicity(MultiplicityKind.ONE_LITERAL);
		}
	}

	/**
	 * @see PersistentRoleCommand#getDefaultJndiName(EnterpriseBean)
	 */
	protected String getDefaultJndiName(EnterpriseBean anEjb) {
		if (anEjb != null) {
			if (anEjb.hasLocalClient())
				return anEjb.getLocalHomeInterfaceName();
			return super.getDefaultJndiName(anEjb);
		}
		//TODO Important test
		return ""; //$NON-NLS-1$
	}

	public EjbRef createEJBReference() {
		EnterpriseBean linkedEJB = getCommonRole().getTypeEntity();
		EJBLocalRef ref = CommonFactory.eINSTANCE.createEJBLocalRef();
		ref.setName(org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers.getEJBRefName(linkedEJB));
		ref.setType(EjbRefType.ENTITY_LITERAL);
		ref.setLocalHome(linkedEJB.getLocalHomeInterfaceName());
		ref.setLocal(linkedEJB.getLocalInterfaceName());
		ref.setLink(linkedEJB.getName());
		getEjb().getEjbLocalRefs().add(ref);
		return ref;
	}

	protected EjbRef getLinkedEjbReference(ContainerManagedEntity cmp) {
		return getEjb().getLinkedEJBLocalReference(cmp);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.PersistentRoleCommand#isNavigable()
	 */
	public boolean isNavigable() {
		return getCmrFieldName() != null;
	}


	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.PersistentRoleCommand#createEJBReferenceIfNecessary()
	 */
	protected void createEJBReferenceIfNecessary() {
		if (isNavigable()) {
			super.createEJBReferenceIfNecessary();
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.PersistentRoleCommand#removeEjbReference(EjbRef)
	 */
	protected boolean removeEjbReference(EjbRef ref) {
		return getEjb().getEjbLocalRefs().remove(ref);
	}

	protected void setRoleAttributesDerived(boolean aBoolean) {
		if (getEJBRelationshipRole() == null)
			return;
		List attributes = ((CommonRelationshipRole) getEJBRelationshipRole()).getAttributes();
		CMPAttribute att;
		for (int i = 0; i < attributes.size(); i++) {
			att = (CMPAttribute) attributes.get(i);
			att.setDerived(aBoolean);
		}
		Notification msg = new ENotificationImpl((InternalEObject) getEjb(), ContainerManagedEntity.DERIVED_FLAG_CHANGE, (EStructuralFeature) null, null, null, -1);
		getEjb().eNotify(msg);
	}

	protected void setupHelper() {
		super.setupHelper();
		RoleHelper roleHelper = (RoleHelper) getHelper();
		roleHelper.setName(getCmrFieldName());
	}
}