/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodegenHandlerExtensionReader;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.IEJBCodegenHandler;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.wst.common.emf.utilities.CopyGroup;


public class EJBCopyGroup extends CopyGroup {

	protected EnterpriseBean ejb;
	protected List ejbs;
	protected int originalEJBCopyCount;
	protected int ejbCopyAdditions;
	protected EJBEditModel editModel;
	protected EJBJar originalEjbJar;
	protected List copyExtensionGrps;
	boolean initilizeComplete = true;
	protected List copiedRelations;
	protected List copiedEJBs;
	protected boolean shouldAddCopyResources;

	/**
	 *  
	 */
	public EJBCopyGroup(EnterpriseBean ejb, EJBEditModel editModel) {
		super();
		this.ejb = ejb;
		this.editModel = editModel;
		initilizeFlags();
		initilize(ejb);
	}

	/**
	 *  
	 */
	public EJBCopyGroup(List ejbList, EJBEditModel editModel) {
		super();
		this.editModel = editModel;
		initilizeFlags();
		initilize(ejbList);
	}

	/**
	 *  
	 */
	private void initilizeFlags() {
		IEJBCodegenHandler handler = getCodegenHandler();
		if (handler != null)
			shouldAddCopyResources = handler.hasDefinedExtensions(editModel);
	}

	/**
	 * @param ejb
	 */
	private void initilize(EnterpriseBean aEjb) {
		add(aEjb);
		originalEJBCopyCount = 1;
		ejbCopyAdditions = 0;
		initilizeComplete = true;
	}

	private void initilize(List ejbList) {
		add(ejbList);
		originalEJBCopyCount = ejbList.size();
		ejbCopyAdditions = 0;
		initilizeComplete = true;
	}


	public void add(List aList) {
		for (int i = 0; i < aList.size(); i++) {
			add((EObject) aList.get(i));
		}
	}

	public void add(EnterpriseBean aEjb) {
		if (aEjb == null)
			return;
		if (super.add(aEjb)) {
			getEnterpriseBeans().add(aEjb);
			if ((aEjb.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID) && aEjb.isContainerManagedEntity())
				add20Relationship((ContainerManagedEntity) aEjb);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.emf.ecore.utilities.copy.CopyGroup#addCopied(org.eclipse.emf.ecore.EObject)
	 */
	public void addCopied(EObject aRefObject) {
		if (aRefObject.eClass().equals(EjbPackage.eINSTANCE.getEJBRelation())) {
			getCopiedRelations().add(aRefObject);
		}
		if (aRefObject instanceof EnterpriseBean) {
			addCopied((EnterpriseBean) aRefObject);
		}
		super.addCopied(aRefObject);
	}

	public void addCopied(EnterpriseBean bean) {
		getCopiedEnterpriseBeans().add(bean);
	}

	/**
	 * Method copy20Relationships.
	 * 
	 * @param containerManagedEntity
	 */
	protected void add20Relationship(ContainerManagedEntity cmp) {
		EJBJar jar = cmp.getEjbJar();
		if (jar == null)
			return;
		List rels = jar.getEJBRelationsForSource(cmp);
		if (rels.isEmpty())
			return;
		int size = rels.size();
		EJBRelation rel;
		for (int i = 0; i < size; i++) {
			rel = (EJBRelation) rels.get(i);
			add(rel);
			List roles = rel.getRelationshipRoles();
			EJBRelationshipRole role = null;
			for (int j = 0; j < roles.size(); j++) {
				role = (EJBRelationshipRole) roles.get(j);
				add(role.getSourceEntity());
			}
		}
	}

	protected IEJBCodegenHandler getCodegenHandler() {
		IProject project = (editModel != null) ? editModel.getProject() : null;
		return EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(project);
	}

	public List getEnterpriseBeans() {
		if (ejbs == null)
			ejbs = new ArrayList();
		return ejbs;
	}

	public List getCopiedEnterpriseBeans() {
		if (copiedEJBs == null)
			copiedEJBs = new ArrayList();
		return copiedEJBs;
	}

	public boolean shouldAddCopiedResources() {
		//return shouldAddCopyResources;
		//We always need to return true now to support the getVersionID method which
		//only works when you are in a resource.
		return true;
	}

	/**
	 * @return Returns the copiedRelations.
	 */
	public List getCopiedRelations() {
		if (copiedRelations == null)
			copiedRelations = new ArrayList();
		return copiedRelations;
	}
}