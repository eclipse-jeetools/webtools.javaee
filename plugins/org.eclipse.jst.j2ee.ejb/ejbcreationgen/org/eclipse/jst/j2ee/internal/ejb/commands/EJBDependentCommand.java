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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodegenHandlerExtensionReader;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.IEJBCodegenHandler;


/**
 * These commands cannot be executed without a parent command. The parent command will be an
 * EnterpriseBeanCommand. Creation date: (9/1/2000 2:11:49 PM)
 * 
 * @author: Administrator
 */
public abstract class EJBDependentCommand extends EJBCommand {
	protected List adaptors = null;
	protected IEJBCodegenHandler codegenHandler;
	protected boolean isCreate = false;
	protected boolean isDelete = false;

	/**
	 * EJBDependentCommand constructor comment.
	 */
	public EJBDependentCommand(IRootCommand parent) {
		this(parent, true);
	}

	/**
	 * EJBDependentCommand constructor comment.
	 */
	public EJBDependentCommand(IRootCommand parent, boolean shouldGenJava) {
		this(parent, shouldGenJava, true);
	}

	/**
	 * EJBDependentCommand constructor comment.
	 */
	public EJBDependentCommand(IRootCommand parent, boolean shouldGenJava, boolean shouldGenMetadata) {
		setGenerateJava(shouldGenJava);
		setGenerateMetadata(shouldGenMetadata);
		parent.append(this);
		createAndAddAdaptorsIfNecessary();
	}

	/**
	 * Do nothing since the copying is done by the parent command.
	 */
	protected void createCopyIfNecessary() {
	}

	protected void createAndAddAdaptorsIfNecessary() {
		if (adaptors == null) {
			initializeEJBCodegenHandler();
			if (codegenHandler != null) {
				adaptors = new ArrayList();
				DependentCommandAdaptor adaptor = (DependentCommandAdaptor) codegenHandler.getDependentCommandAdaptor();
				if (adaptor != null)
					adaptors.add(adaptor);
			}
		}
	}

	protected void postExecuteDependentCommand() {
		if (adaptors != null && !adaptors.isEmpty()) {
			for (int i = 0; i < adaptors.size(); i++) {
				DependentCommandAdaptor depAdaptor = (DependentCommandAdaptor) adaptors.get(i);
				depAdaptor.setCreate(isCreate());
				depAdaptor.setDelete(isDelete());
				depAdaptor.postExecute(this);
			}
		}
	}

	protected void postUndoDependentCommand() {
		if (adaptors != null && !adaptors.isEmpty()) {
			for (int i = 0; i < adaptors.size(); i++) {
				DependentCommandAdaptor depAdaptor = (DependentCommandAdaptor) adaptors.get(i);
				depAdaptor.setDelete(isDelete());
				depAdaptor.setCreate(isCreate());
				depAdaptor.postUndo(this);
			}
		}
	}

	protected void initializeEJBCodegenHandler() {
		if (codegenHandler == null) {
			IProject project = ((IRootCommand) getParent()).getProject();
			codegenHandler = EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(project);
		}
	}

	/**
	 * Initialize the EJB dependent fields.
	 */
	protected void executeForMetadataGeneration() {
		initializeEjbDependentFields();
		super.executeForMetadataGeneration();
		postExecuteDependentCommand();
	}

	/**
	 * Undo the the addition of the persistent attribute as well as removal from the key features if
	 * necessary.
	 */
	protected void undoMetadataGeneration() {
		super.undoMetadataGeneration();
		postUndoDependentCommand();
	}

	/**
	 * Insert the method's description here. Creation date: (8/22/2000 8:56:00 AM)
	 */
	public ResourceSet getContext() {
		if (getParent() != null) {
			EnterpriseBeanCommand rootCommand = (EnterpriseBeanCommand) getParent();
			return rootCommand.getEditModel().getEJBNature().getResourceSet();
		}
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (8/22/2000 4:09:08 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.EnterpriseBean
	 */
	public EnterpriseBean getEjb() {
		return getParent().getEjb();
	}

	public EjbFactory getEJBFactory() {
		return ((EnterpriseBeanCommand) getParent()).getEJBFactory();
	}

	/**
	 * Insert the method's description here. Creation date: (8/22/2000 4:09:08 PM)
	 * 
	 * @return EJBJar
	 */
	public EJBJar getEJBJar() {
		return (EJBJar) getEjb().eContainer();
	}

	/**
	 * Return the original EJB before any commands have been executed. Creation date: (8/22/2000
	 * 4:09:08 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.EnterpriseBean
	 */
	protected EnterpriseBean getOriginalEjb() {
		return (EnterpriseBean) getCopy(getEjb());
	}

	/**
	 * Initialize the fields that are dependent upon the EJB
	 */
	protected void initializeEjbDependentFields() {
		if (getEjb() != null) {
			initializeSourceMetaType();
			initializeOldMetadata(); //try to initialize the old metadata
			// again
		}
	}

	/**
	 * The default is to do nothing.
	 */
	protected void initializeSourceMetaType() {
	}

	public boolean isDependent() {
		return true;
	}

	/**
	 * @return Returns the isCreate.
	 */
	public boolean isCreate() {
		return isCreate;
	}

	/**
	 * @param isCreate
	 *            The isCreate to set.
	 */
	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	/**
	 * @return Returns the isDelete.
	 */
	public boolean isDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete
	 *            The isDelete to set.
	 */
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}