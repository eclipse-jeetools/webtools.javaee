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


import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.wst.common.emf.utilities.ExtendedEcoreUtil;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Insert the type's description here. Creation date: (8/22/2000 8:30:03 AM)
 * 
 * @author: Administrator
 */
public abstract class EnterpriseBeanCommand extends AbstractEJBRootCommand implements IRootCommand {

	protected static final String CREATING_TASK_PATTERN = EJBCodeGenResourceHandler.getString("Creating_bean_named___{0}__UI_"); //$NON-NLS-1$ = "Creating bean named \"{0}\""
	protected static final String UPDATING_TASK_PATTERN = EJBCodeGenResourceHandler.getString("Updating_bean_named___{0}__UI_"); //$NON-NLS-1$ = "Updating bean named \"{0}\""
	protected static final String DELETING_TASK_PATTERN = EJBCodeGenResourceHandler.getString("Deleting_bean_named___{0}__UI_"); //$NON-NLS-1$ = "Deleting bean named \"{0}\""
	protected EnterpriseBean ejb;
	protected String name;
	protected EnterpriseBeanCodegenCommand codegenCommand;
	protected String defaultPackageFragmentRootName;
	protected boolean calculatedShouldGenerateJava = false;
	protected boolean shouldPropagate = true;
	//EJB model attributes
	private String description;
	protected boolean isDescriptionSet = false;
	private String displayName;
	protected boolean isDisplayNameSet = false;
	private String smallIcon;
	protected boolean isSmallIconSet = false;
	private String largeIcon;
	protected boolean isLargeIconSet = false;
	private String jndiName;
	protected boolean isJndiNameSet = false;
	protected boolean isVersion2_x = false;

	//True indicates that the delete should take place by default for a delete command.
	//This flag should only be used by delete commands.
	protected boolean deleteGeneratedClassesOverrideFlag = true;

	/**
	 * CreateCommand constructor comment.
	 */
	public EnterpriseBeanCommand(EnterpriseBean anEjb, EJBEditModel anEditModel) {
		super(anEjb.getName());
		setName(anEjb.getName());
		setEjb(anEjb);
		setEditModel(anEditModel);
	}

	/**
	 * Constructor method for an EnterpriseBeanCommand.
	 * 
	 * @param java.lang.String
	 *            aName
	 * @param java.lang.String
	 *            aFolderName
	 * 
	 * aName - The name and ID of the EJB. aProject - The project in which to create the folder.
	 */
	public EnterpriseBeanCommand(String aName, EJBEditModel anEditModel) {
		super(aName);
		setName(aName);
		setEditModel(anEditModel);
	}

	/**
	 * Insert the method's description here. Creation date: (3/23/2001 2:59:07 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * Insert the method's description here. Creation date: (3/23/2001 2:59:07 PM)
	 * 
	 * @param newDescription
	 *            java.lang.String
	 */
	public void setDescription(java.lang.String newDescription) {
		description = newDescription;
		isDescriptionSet = true;
	}

	/**
	 * Insert the method's description here. Creation date: (3/23/2001 2:59:07 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDisplayName() {
		return displayName;
	}

	/**
	 * Insert the method's description here. Creation date: (3/23/2001 2:59:07 PM)
	 * 
	 * @param newDisplayName
	 *            java.lang.String
	 */
	public void setDisplayName(java.lang.String newDisplayName) {
		displayName = newDisplayName;
		isDisplayNameSet = true;
	}

	/**
	 * Insert the method's description here. Creation date: (9/3/2000 10:09:57 AM)
	 * 
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getEjb() {
		return ejb;
	}

	/**
	 * Insert the method's description here. Creation date: (9/3/2000 10:09:57 AM)
	 * 
	 * @param newEjb
	 *            EnterpriseBean
	 */
	public void setEjb(EnterpriseBean newEjb) {
		ejb = newEjb;
	}

	/**
	 * Insert the method's description here. Creation date: (9/11/2000 12:54:36 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDefaultPackageFragmentRootName() {
		if (defaultPackageFragmentRootName == null && !isCreateCommand() && getEjb() != null) {
			JavaClass beanClass = getEjb().getEjbClass();
			if (beanClass != null) {
				IType type = EJBGenHelpers.getType(beanClass);
				if (type != null && !type.isBinary()) {
					IPackageFragment frag = type.getPackageFragment();
					if (frag != null) {
						IJavaElement root = frag.getParent();
						defaultPackageFragmentRootName = (root == null) ? null : root.getElementName();
					}
				}
			}
		}
		return defaultPackageFragmentRootName;
	}

	/**
	 * Insert the method's description here. Creation date: (9/11/2000 12:54:36 PM)
	 * 
	 * @param newDefaultPackageFragmentRootName
	 *            java.lang.String
	 */
	public void setDefaultPackageFragmentRootName(java.lang.String newDefaultPackageFragmentRootName) {
		defaultPackageFragmentRootName = newDefaultPackageFragmentRootName;
	}

	/**
	 * Insert the method's description here. Creation date: (3/23/2001 2:59:07 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getLargeIcon() {
		return largeIcon;
	}

	/**
	 * Insert the method's description here. Creation date: (3/23/2001 2:59:07 PM)
	 * 
	 * @param newLargeIcon
	 *            java.lang.String
	 */
	public void setLargeIcon(java.lang.String newLargeIcon) {
		largeIcon = newLargeIcon;
		isLargeIconSet = true;
	}

	/**
	 * Insert the method's description here. Creation date: (3/23/2001 2:59:07 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSmallIcon() {
		return smallIcon;
	}

	/**
	 * Insert the method's description here. Creation date: (3/23/2001 2:59:07 PM)
	 * 
	 * @param newSmallIcon
	 *            java.lang.String
	 */
	public void setSmallIcon(java.lang.String newSmallIcon) {
		smallIcon = newSmallIcon;
		isSmallIconSet = true;
	}

	/**
	 * Insert the method's description here. Creation date: (10/11/2000 1:50:52 PM)
	 * 
	 * @return java.lang.String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Insert the method's description here. Creation date: (10/11/2000 1:50:52 PM)
	 * 
	 * @param newName
	 *            java.lang.String
	 */
	public void setName(java.lang.String newName) {
		name = newName;
	}

	/**
	 * Gets the jndiName.
	 * 
	 * @return Returns a String
	 */
	public String getJndiName() {
		return jndiName;
	}

	/**
	 * Sets the jndiName.
	 * 
	 * @param jndiName
	 *            The jndiName to set
	 */
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
		isJndiNameSet = true;
	}

	/**
	 * Return a boolean indicating whether this command is for a version 2.X bean or not. If this is
	 * a create command, it will return the value of the <code>isVersion2_x</code> field;
	 * otherwise, it will determine it from the EJBJar of the Enterprise Bean.
	 */
	public boolean isVersion2_XOrHigher() {
		if (isCreateCommand())
			return isVersion2_x;
		return getEjb().getVersionID() >= J2EEVersionConstants.EJB_2_0_ID;
	}

	public void setVersion2_X(boolean aBoolean) {
		isVersion2_x = aBoolean;
	}

	/**
	 * Add the newEjb instance directly to the EList of the Resource.
	 */

	protected void addEnterpriseBeanToDocument(EnterpriseBean newEjb) {
		getResource().getContents().add(newEjb);
	}

	protected void addEnterpriseBeanToJar(EnterpriseBean newEjb) {
		getEJBJar().getEnterpriseBeans().add(newEjb);
	}

	public void append(EJBGenerationHelper aHelper) {
		getCodegenCommand().append(aHelper);
	}

	public IRootCommand append(IRootCommand aCommand) {
		if (aCommand == null)
			return this;
		EJBCompoundRootCommand merge = new EJBCompoundRootCommand(getDescription());
		merge.append(this);
		merge.append(aCommand);
		//Initialize fields on the Compound based off of this command.
		merge.setEditModel(getEditModel());
		merge.setCopier(primGetCopier());
		merge.setOperationHandler(getOperationHandler());
		merge.setPropogateAllKeyChanges(shouldPropogateAllKeyChanges());
		return merge;
	}

	public void appendInverse(EJBGenerationHelper aHelper) {
		getCodegenCommand().appendInverse(aHelper);
	}

	/**
	 * Modify the totalWork to account for the possibility of codegen. This is just a guess. We are
	 * better off to guess high than low.
	 */
	public int calculateTotalWork() {
		int total = super.calculateTotalWork();
		if (shouldGenerateJava())
			total += getCodegenCommand().calculateTotalWork();
		return total;
	}

	/**
	 * Check if the ProgressMonitor has been cancelled. If so, null out the codegen command and
	 * throw a CommandExecutionFailure. Throwing the failure will ensure that the CommandStack will
	 * call a dispose() on the command.
	 */

	protected void checkCancelled() {
		if (!isDeleteCommand())
			super.checkCancelled();
	}

	/**
	 * createEJB method comment.
	 */
	protected void createAndSetEJB() {
		processNewEjb(createEJBIfNecessary());
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 10:01:03 AM)
	 */
	protected abstract EnterpriseBeanCodegenCommand createCodegenCommand();

	/**
	 * createEJB method comment.
	 */
	protected abstract EnterpriseBean createEJB();

	/**
	 * createEJB method comment.
	 */
	protected EnterpriseBean createEJBIfNecessary() {
		if (getEjb() == null)
			return createEJB();
		return getEjb();
	}

	protected boolean deleteAssemblyData() {
		if (getEJBJar() != null) {
			AssemblyDescriptor dd = getEJBJar().getAssemblyDescriptor();
			if (dd != null) {
				dd.removeData(getEjb());
				return true;
			}
		}
		return false;
	}

	protected boolean deleteEnterpriseBean() {
		ensureEJBProxiesAreResolved();
		if (getEJBJar() != null) {
			if (getEjb() != null && getEjb().eResource() != null)
				ExtendedEcoreUtil.becomeProxy(getEjb(), getEjb().eResource());
			return getEJBJar().getEnterpriseBeans().remove(getEjb());
		}
		return false;
	}

	protected boolean deleteEntireEnterpriseBean() {
		deleteAssemblyData();
		return deleteEnterpriseBean();
	}

	/**
	 * Do not execute if we have a parent.
	 */
	protected void deleteResource() throws CommandExecutionFailure {
		if (!hasParent())
			super.deleteResource();
	}

	// Proxies need to be resolved because they may not be able to be
	// resolved at a later time (e.g., during a delete).
	protected void ensureEJBProxiesAreResolved() {
		EnterpriseBean anEJB = getEjb();
		if (anEJB != null) {
			anEJB.getHomeInterface();
			anEJB.getRemoteInterface();
			anEJB.getEjbClass();
		}
	}

	/**
	 * execute method comment.
	 */
	protected void executeCodegenCommand() {
		EnterpriseBeanCodegenCommand command = primGetCodegenCommand();
		if (command != null && shouldGenerateJava()) {
			command.setDefaultPackageFragmentRootName(getDefaultPackageFragmentRootName());
			command.setEditModel(getEditModel());
			command.execute(getProgressMonitor());
		}
	}

	/**
	 * execute method comment.
	 */
	protected void executeCodegenCommandIfNecessary() {
		if (!hasParent())
			super.executeCodegenCommandIfNecessary();
	}

	/**
	 * Override to perform the necessary operation to update the Metadata.
	 */
	protected void executeForMetadataGeneration() throws CommandExecutionFailure {
		if (isDescriptionSet)
			getEjb().setDescription(getDescription());
		if (isDisplayNameSet)
			getEjb().setDisplayName(getDisplayName());
		if (isSmallIconSet)
			getEjb().setSmallIcon(getSmallIcon());
		if (isLargeIconSet)
			getEjb().setLargeIcon(getLargeIcon());
		if (isCreateCommand()) {
			//TODO Should set the JNDI name using the PostRelationshipOperation
		}
	}

	/**
	 * Create the default jndi name for the binding. This should probably be done by another object
	 * once this is tied into the preferences. For now we will use the qualified home interface name
	 * replacing "." with "/".
	 */
	protected String getDefaultJndiName() {
		String homeName = getEjb().getHomeInterfaceName();
		if (homeName == null || homeName.length() < 1)
			homeName = getEjb().getLocalHomeInterfaceName();
		if (homeName == null || homeName.length() < 1)
			return null;
		StringBuffer buf = new StringBuffer();
		buf.append("ejb"); //$NON-NLS-1$
		StringTokenizer tok = new StringTokenizer(homeName, "."); //$NON-NLS-1$
		while (tok.hasMoreTokens()) {
			buf.append("/"); //$NON-NLS-1$
			buf.append(tok.nextToken());
		}
		return buf.toString();
	}

	/**
	 * Lookup the EnterpriseBean in the existing Resource's EList by using the name as the ID.
	 */
	protected EnterpriseBean findExistingEnterpriseBean() {
		EJBJar ejbJar = getEJBJar();
		if (null != ejbJar && null != getName()) {
			List beans = ejbJar.getEnterpriseBeans();
			for (int i = 0; i < beans.size(); i++) {
				EnterpriseBean bean = (EnterpriseBean) beans.get(i);
				if (getName().equals(bean.getName())) {
					return bean;
				}
			}
		}
		return null;
	}

	/**
	 * This determines the affected objects by composing the affected objects of the commands in the
	 * list; this is affected by the setting of {@link #resultIndex}.
	 */
	public Collection getAffectedObjects() {
		Collection objects = super.getAffectedObjects();
		objects.add(getEjb());
		return objects;

	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 3:12:32 PM)
	 * 
	 * @return EnterpriseBeanCodegenCommand
	 */
	public EnterpriseBeanCodegenCommand getCodegenCommand() {
		if (codegenCommand == null) {
			codegenCommand = createCodegenCommand();
			codegenCommand.setParent(this);
			codegenCommand.setOperationHandler(getOperationHandler());
		}
		return codegenCommand;
	}

	/**
	 * Insert the method's description here. Creation date: (12/4/2000 1:01:24 PM)
	 * 
	 * @return java.lang.String
	 */
	protected java.lang.String getCreatingTaskName() {
		return java.text.MessageFormat.format(CREATING_TASK_PATTERN, new String[]{getName()});
	}

	/**
	 * Insert the method's description here. Creation date: (12/4/2000 1:01:24 PM)
	 * 
	 * @return java.lang.String
	 */
	protected java.lang.String getDeletingTaskName() {
		return java.text.MessageFormat.format(DELETING_TASK_PATTERN, new String[]{getName()});
	}



	protected IJavaProject getJavaProject() {
		return getEJBNature() == null ? null : ProjectUtilities.getJavaProject(getEJBNature().getProject());
	}

	/**
	 * Insert the method's description here. Creation date: (9/3/2000 10:09:57 AM)
	 * 
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getOldEjb() {
		return (EnterpriseBean) getCopy(getEjb());
	}


	/**
	 * Insert the method's description here. Creation date: (12/4/2000 1:01:24 PM)
	 * 
	 * @return java.lang.String
	 */
	protected java.lang.String getUpdatingTaskName() {
		return java.text.MessageFormat.format(UPDATING_TASK_PATTERN, new String[]{getName()});
	}

	/**
	 * The default at this level is to do nothing.
	 */
	protected void initializeAdditionalCommand() {
	}

	protected void initializeAdditionalCommandIfNecessary() {
		if (!hasParent())
			super.initializeAdditionalCommandIfNecessary();
	}

	/**
	 * Initialize the code gen helper.
	 */
	protected void initializeHelper(EnterpriseBeanHelper aHelper) {
		aHelper.setOldMetaObject(getOldEjb());
		aHelper.setOldSupertype(getSupertype(getOldEjb()));
	}

	protected EnterpriseBean getSupertype(EnterpriseBean aEjb) {
		EjbModuleExtensionHelper helper = getEjbModuleExtension();
		if (helper != null)
			return helper.getSuperType(aEjb);
		return null;
	}

	protected EjbModuleExtensionHelper getEjbModuleExtension() {
		return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(getEjb());
	}

	/**
	 * Initialize the code gen helper.
	 */
	protected void initializeHelperIfNecessary() {
		if (shouldGenerateJava())
			initializeHelper((EnterpriseBeanHelper) getCodegenCommand().getHelper());
	}

	/**
	 * This method is called prior to executing child commands. It will create the EJB and its
	 * resource if this is a create command; otherwise, it will create a copy of the EJB.
	 */
	protected void initializeRoot() {
		if (isCreateCommand())
			setupResourceAndEJB();
		else
			createCopy();
		initializeHelperIfNecessary();
	}

	/**
	 * Create a copy of the EJB and the EJB extension (if it exists).
	 */
	protected void createCopy() {
		if (getEjb() != null)
			getCopier().copy(getEjb());
	}

	/**
	 * Override to return true in each subclass command that performs a creation.
	 */
	public boolean isCreateCommand() {
		return false;
	}

	/**
	 * Override to return true in each subclass command that performs a delete.
	 */
	public boolean isDeleteCommand() {
		return false;
	}

	public boolean isEnterpriseBeanRootCommand() {
		return true;
	}

	/**
	 * Override if there is anything that is necessary to be done before the document is saved. All
	 * child commands are already executed so their results have already been applied to the EJB.
	 * This is called prior to code generation.
	 */

	protected void postExecuteChildren() {
		super.postExecuteChildren();
		if (shouldGenerateMetadata())
			executeForMetadataGeneration();
	}

	/**
	 * Do whatever is needed prior to saving resources during undo.
	 */
	protected void postUndoChildren() {
		super.postUndoChildren();
		if (shouldGenerateMetadata() && !isCreateCommand())
			undoMetadataGeneration();
	}

	/**
	 * Save resources.
	 */
	protected void postUndoCodegen() throws CommandExecutionFailure {
		if (isCreateCommand())
			simpleDeleteEJB();
		else
			super.preUndoCodegen();
	}

	/**
	 * This determines whether all the commands can execute so that {@link #isExecutable}can be
	 * cached. An empty command list causes false to be returned.
	 */
	protected boolean prepare() {
		if ((isDeleteCommand() || isAdditionalCommand()) && commandList.isEmpty())
			return true;
		if (commandList.isEmpty())
			return true;
		return super.prepare();
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 3:12:32 PM)
	 * 
	 * @return EnterpriseBeanCodegenCommand
	 */
	private EnterpriseBeanCodegenCommand primGetCodegenCommand() {
		return codegenCommand;
	}

	/**
	 * Insert the method's description here. Creation date: (9/3/2000 10:09:57 AM)
	 * 
	 * @param newEjb
	 *            EnterpriseBean
	 */
	public void processNewEjb(EnterpriseBean newEjb) {
		if (newEjb != null && getEjb() != newEjb) {
			if (USE_ONE_RESOURCE)
				addEnterpriseBeanToJar(newEjb);
			else
				addEnterpriseBeanToDocument(newEjb);
		}
		setEjb(newEjb);
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 3:12:32 PM)
	 * 
	 * @param newCodegenCommand
	 *            EnterpriseBeanCodegenCommand
	 */
	public void setCodegenCommand(EnterpriseBeanCodegenCommand newCodegenCommand) {
		codegenCommand = newCodegenCommand;
	}

	protected void setupCodegenCommand() {
		((EnterpriseBeanHelper) primGetCodegenCommand().getHelper()).setOldMetaObject(getOldEjb());
		primGetCodegenCommand().setDefaultPackageFragmentRootName(getDefaultPackageFragmentRootName());
		primGetCodegenCommand().setEditModel(getEditModel());
	}

	/**
	 * These steps must be performed when doing a create. They include creating the folder (as well
	 * as the project if necessary), creating the document resource, and creating the EJB itself.
	 */
	protected void setupResourceAndEJB() throws CommandExecutionFailure {
		createResourceIfNecessary();
		createAndSetEJB();
	}

	/**
	 * We should not generate Java if the flag is false or we do not have any child Commands that
	 * need to generate Java.
	 */
	public boolean shouldGenerateJavaForModify() {
		if (calculatedShouldGenerateJava)
			return super.shouldGenerateJava();
		calculatedShouldGenerateJava = true;
		if (super.shouldGenerateJava() && !isEmpty()) {
			Command childCommand;
			for (int i = 0; i < commandList.size(); i++) {
				childCommand = (Command) commandList.get(i);
				if (childCommand instanceof IEJBCommand) {
					if (((IEJBCommand) childCommand).shouldGenerateJava())
						return true;
				} else
					return true;
			}
		}
		setGenerateJava(false);
		return false;
	}

	/**
	 * Delete the EJB as a resource in the Workbench.
	 */
	protected void simpleDeleteEJB() throws CommandExecutionFailure {
		if (USE_ONE_RESOURCE) {
			deleteEnterpriseBean();
		} else
			deleteResource();
	}

	/**
	 * undoCodegenCommand method comment.
	 */
	protected void undoCodegenCommand() {
		if (!hasParent() && primGetCodegenCommand() != null)
			primGetCodegenCommand().undo(getProgressMonitor());

	}

	/**
	 * Override to perform the necessary operation to undo the Metadata update.
	 */
	protected void undoMetadataGeneration() {
		EnterpriseBean oldEJB = getOldEjb();
		if (isDescriptionSet)
			getEjb().setDescription(oldEJB.getDescription());
		if (isDisplayNameSet)
			getEjb().setDisplayName(oldEJB.getDisplayName());
		if (isSmallIconSet)
			getEjb().setSmallIcon(oldEJB.getSmallIcon());
		if (isLargeIconSet)
			getEjb().setLargeIcon(oldEJB.getLargeIcon());
	}

	/**
	 * This method should only be called when using a delete command but you do not want the
	 * generated classes to be deleted automatically. This would be the case if you need to delete
	 * the EJB but not the generated classes and the code generators still need to run to modify the
	 * generated classes.
	 */
	public void doNotDeleteGeneratedClasses() {
		if (isDeleteCommand())
			deleteGeneratedClassesOverrideFlag = false;
	}

}