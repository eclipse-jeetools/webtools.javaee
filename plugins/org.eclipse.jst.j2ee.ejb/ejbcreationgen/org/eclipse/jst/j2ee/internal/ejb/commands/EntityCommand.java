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



import java.util.Collections;
import java.util.List;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


/**
 * Insert the type's description here. Creation date: (8/22/2000 8:22:01 AM)
 * 
 * @author: Administrator
 */
public abstract class EntityCommand extends EnterpriseBeanCommand {
	protected static final String KEY_CLASS_SUFFIX = "Key";//$NON-NLS-1$
	private boolean isReentrant = false;
	private boolean isReentrantSet = false;


	private boolean createAdditionalCommand = true;

	/**
	 * EntityCommand constructor comment.
	 * 
	 * @param aBeanName
	 *            java.lang.String
	 */
	public EntityCommand(EnterpriseBean anEjb, EJBEditModel anEditModel) {
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
	public EntityCommand(String aName, EJBEditModel anEditModel) {
		super(aName, anEditModel);
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 10:01:03 AM)
	 */
	protected EnterpriseBeanCodegenCommand createCodegenCommand() {
		return new EntityCodegenCommand(getEntity());
	}

	/**
	 * createEJB method comment.
	 */
	protected EnterpriseBean createEJB() {
		EnterpriseBean bean = getEJBFactory().createEntity();
		bean.setName(getName());
		getEditModel().getEjbXmiResource().setID(bean, getName());
		return bean;
	}

	// Proxies need to be resolved because they may not be able to be
	// resolved at a later time (e.g., during a delete).
	protected void ensureEJBProxiesAreResolved() {
		super.ensureEJBProxiesAreResolved();
		Entity anEntity = getEntity();
		if (anEntity != null)
			anEntity.getPrimaryKey();
	}

	/**
	 * Override to perform the necessary operation to update the Metadata.
	 */
	protected void executeForMetadataGeneration() throws CommandExecutionFailure {
		super.executeForMetadataGeneration();
		if (isReentrantSet || isCreateCommand())
			getEntity().setReentrant(isReentrant());
	}

	public Entity getEntity() {
		return (Entity) getEjb();
	}

	protected String getPrimaryKeyQualifiedName() {
		String baseName;
		if (getEntity().getPrimaryKeyName() != null)
			return getEntity().getPrimaryKeyName();
		String beanName = getEntity().getEjbClassName();
		baseName = beanName.substring(0, beanName.length() - 4); //subtract Bean
		return baseName + KEY_CLASS_SUFFIX;
	}

	protected List getSubtypes() {
		EjbModuleExtensionHelper helper = IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
		if (helper == null)
			return Collections.EMPTY_LIST;
		return helper.getSubtypes(getEjb());
	}

	/**
	 * We need to create commands for subtypes if the key class has changed.
	 */
	protected void initializeAdditionalCommand() {
		if (!shouldCreateAdditionalCommand())
			return;
		if (!shouldPropagate)
			return;
		doInitializeAdditionalCommand();
	}

	/**
	 * Method shouldCreateAdditionalCommand.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateAdditionalCommand() {
		return createAdditionalCommand;
	}

	/**
	 * Subclasses should override this method to initialize the additional command because the
	 * 
	 * @rootCommands Map ensures that duplicate commands will not be created for the same EJB.
	 */
	protected void doInitializeAdditionalCommand() {
		initializeAdditionalCommandForInheritance();
	}

	/**
	 * We need to create commands for subtypes if the key class has changed.
	 */
	protected void initializeAdditionalCommandForInheritance() {
		if (needToRegenerateSubtypes()) {
			java.util.List subtypes = getSubtypes();
			if (subtypes.isEmpty())
				return;
			JavaClass pkClass = null;
			if (isKeyClassChanging())
				pkClass = getEntity().getPrimaryKey();
			Entity entity;
			IRootCommand command = null;
			for (int i = 0; i < subtypes.size(); i++) {
				entity = (Entity) subtypes.get(i);
				if (hasInitializedAdditionalCommand())
					command = getAdditionalCommand().getRootCommand(entity);
				if (command == null) {
					if (entity.isContainerManagedEntity())
						command = new UpdateContainerManagedEntityCommand(entity, getEditModel());
					else
						command = new UpdateEntityCommand(entity, getEditModel());
					command.getCodegenCommand(); //force the codegen command
					command.setGenerateJava(true);
					getAdditionalCommand().append(command);

				}
				if (pkClass != null)
					new CreatePrimaryKeyClassCommand(command, pkClass, false);
			}
		}
	}

	protected boolean isKeyChanging() {
		EntityHelper helper = (EntityHelper) getCodegenCommand().getHelper();
		return helper.isKeyChanging() || isKeyClassChanging();
	}

	protected boolean isKeyClassChanging() {
		Entity oldEjb = (Entity) getOldEjb();
		if ((oldEjb != null) && (oldEjb.getPrimaryKeyName() != null))
			return !oldEjb.getPrimaryKeyName().equals(getEntity().getPrimaryKeyName());
		return false;
	}

	/**
	 * Insert the method's description here. Creation date: (5/31/2001 2:01:36 PM)
	 * 
	 * @return boolean
	 */
	public boolean isReentrant() {
		return isReentrant;
	}

	/**
	 * Return true if the key shape is changing or the number of required roles is changing.
	 */
	protected boolean needToRegenerateSubtypes() {
		return isKeyChanging();
	}

	/**
	 * Insert the method's description here. Creation date: (5/31/2001 2:01:36 PM)
	 * 
	 * @param newReentrant
	 *            boolean
	 */
	public void setReentrant(boolean newReentrant) {
		isReentrant = newReentrant;
		isReentrantSet = true;
	}

	/**
	 * Override to perform the necessary operation to undo the Metadata update.
	 */
	protected void undoMetadataGeneration() {
		super.undoMetadataGeneration();
		Entity oldEntity = (Entity) getOldEjb();
		if (oldEntity != null)
			getEntity().setReentrant(oldEntity.isReentrant());
	}

	/**
	 * Sets the createAdditionalCommand.
	 * 
	 * @param createAdditionalCommand
	 *            The createAdditionalCommand to set
	 */
	public void setCreateAdditionalCommand(boolean createAdditionalCommand) {
		this.createAdditionalCommand = createAdditionalCommand;
	}

}