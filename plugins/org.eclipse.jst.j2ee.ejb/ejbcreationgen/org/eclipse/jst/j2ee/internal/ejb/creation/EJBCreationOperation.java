package org.eclipse.jst.j2ee.internal.ejb.creation;

/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddBeanClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddPersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddPrimaryKeyClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddRemoteInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateBeanClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateContainerManagedEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreatePersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreatePrimaryKeyClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateRemoteInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateSessionCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBClassReferenceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IPersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.SessionCommand;
import org.eclipse.jst.j2ee.internal.ejb.operations.CreateSourceFolderOperation;
import org.eclipse.jst.j2ee.internal.ejb.operations.EjbModificationOperation;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb20.commands.AddLocalHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.AddLocalInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateContainerManagedEntity20Command;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateLocalHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateLocalInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateMessageDrivenCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.MessageDrivenCommand;
import org.eclipse.wst.common.framework.operation.IOperationHandler;
import org.eclipse.wst.common.framework.operation.WTPOperation;


/**
 * @deprecated
 * @author jsholl
 */
public class EJBCreationOperation extends EjbModificationOperation implements IEJBCreationOperation {
	protected EJBCreationModel creationModel;
	private EnterpriseBeanCommand ejbCommand;

	/**
	 * Constructor
	 * 
	 * @param anEjbCreationInfoProvider
	 *            IEJBCreationInfoProvider
	 * @param anEditModel
	 *            org.eclipse.jst.j2ee.ejb.ejbproject.EJBEditModel
	 * @param anOperationHandler
	 *            org.eclipse.jst.j2ee.operations.IOperationHandler
	 */
	public EJBCreationOperation(EJBCreationModel anEjbCreationModel, EJBEditModel anEditModel, IOperationHandler anOperationHandler) {
		super(anEditModel, anOperationHandler);
		creationModel = anEjbCreationModel;

	}

	/**
	 * Return the EnterpriseBean that was newly created by this operation.
	 * 
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getEjb() {
		if (ejbCommand != null)
			return ejbCommand.getEjb();
		return null;
	}

	protected void execute(org.eclipse.core.runtime.IProgressMonitor monitor) throws java.lang.reflect.InvocationTargetException, InterruptedException, org.eclipse.core.runtime.CoreException {
		createSourceFolder(monitor);
		super.execute(monitor);
	}

	/**
	 * Run an operation that will ensure that a source folder is created and added to the Java
	 * project's classpath.
	 * 
	 * @param monitor
	 *            IProgressMonitor
	 */
	protected void createSourceFolder(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
		WTPOperation op = new CreateSourceFolderOperation(creationModel.getSourceFolderName(), getEditModel().getEJBNature());
		op.run(monitor);
	}

	/*
	 * @see EjbModificationOperation#createCommand()
	 */
	protected IEJBCommand createCommand() {
		ejbCommand = createRootCommand();
		if (ejbCommand != null) {
			initializeRootCommand();
			createDependentCommands();
		}
		return ejbCommand;
	}

	/**
	 * Create an IRootCommand that will create an EJB of the appropriate type based on the
	 * <code>infoProvider</code>.
	 * 
	 * @return EnterpriseBeanCommand
	 */
	protected EnterpriseBeanCommand createRootCommand() {
		int type = creationModel.getBeanType();
		String beanName = creationModel.getBeanName();
		EnterpriseBeanCommand rootCommand = null;
		switch (type) {
			case EJBCreationModel.SESSION :
				rootCommand = new CreateSessionCommand(beanName, getEditModel(), creationModel.getIsStatefull(), creationModel.getIsContainerManaged());
				break;
			case EJBCreationModel.BMP :
				rootCommand = new CreateEntityCommand(beanName, getEditModel());
				break;
			case EJBCreationModel.CMP : {
				if (creationModel.isEJB20())
					rootCommand = new CreateContainerManagedEntity20Command(beanName, getEditModel());
				else
					rootCommand = new CreateContainerManagedEntityCommand(beanName, getEditModel());
				break;
			}
			case EJBCreationModel.MDB :
				rootCommand = new CreateMessageDrivenCommand(beanName, getEditModel());
				break;

		}
		return rootCommand;
	}

	protected void initializeRootCommand() {
		ejbCommand.setOperationHandler(getOperationHandler());
		ejbCommand.setDefaultPackageFragmentRootName(creationModel.getSourceFolderName());
		ejbCommand.setVersion2_X(creationModel.isEJB20());
		String jndiName = creationModel.getJndiName();
		if (jndiName != null || !creationModel.useDefaultJNDIName())
			ejbCommand.setJndiName(jndiName);
		if (creationModel.getBeanType() == EJBCreationModel.SESSION)
			initializeSessionRootCommand((SessionCommand) ejbCommand);
		else if (creationModel.getBeanType() == EJBCreationModel.MDB)
			initializeMDBRootCommand((MessageDrivenCommand) ejbCommand);
	}

	protected void initializeSessionRootCommand(SessionCommand command) {
		command.setIsContainerManaged(creationModel.getIsContainerManaged());
		command.setIsStateful(creationModel.getIsStatefull());
	}

	protected void initializeMDBRootCommand(MessageDrivenCommand command) {
		EJB20CreationModel model20 = (EJB20CreationModel) creationModel;
		command.setIsContainerManaged(creationModel.getIsContainerManaged());
		if (!creationModel.getIsContainerManaged())
			command.setAcknowledgeMode(model20.getAcknowledgeMode());
		command.setDestinationType(model20.getDestinationType());
		command.setSubscriptionDurability(model20.getDurability());
		command.setMessageSelector(model20.getMessageSelector());
		command.setListenerPortName(model20.getMDBListenerPortName());
	}

	/**
	 * Create any other commands that are dependent on the passed EnterpriseBeanCommand.
	 * 
	 * @param root
	 *            EnterpriseBeanCommand
	 */
	protected void createDependentCommands() {
		//Creating generalization is being removed from the the dependent commands
		//createGeneralizationCommand();
		createClassReferenceCommands();
		if (creationModel.getBeanType() == EJBCreationModel.CMP)
			createAttributeCommands();
	}



	protected void createClassReferenceCommands() {
		int type = creationModel.getBeanType();
		if (type != EJBCreationModel.MDB) {
			if (creationModel.isRemoteClient()) {
				createHomeCommand();
				createRemoteCommand();
			}
			if (creationModel.isEJB20() && ((EJB20CreationModel) creationModel).isLocalClient()) {
				createLocalHomeCommand();
				createLocalCommand();
			}
		}
		createEJBClassCommand();
		if (type == EJBCreationModel.BMP || type == EJBCreationModel.CMP)
			createPrimaryKeyClassCommand();
	}

	protected void createHomeCommand() {
		String homeName = creationModel.getHomeName();
		String homePackage = creationModel.getHomePackage();
		if (creationModel.shouldCreateHomeInterface())
			new CreateHomeInterfaceCommand(ejbCommand, homeName, homePackage, true);
		else
			new AddHomeInterfaceCommand(ejbCommand, homeName, homePackage);
	}

	protected void createLocalHomeCommand() {
		EJB20CreationModel model20 = (EJB20CreationModel) creationModel;
		String homeName = model20.getLocalHomeName();
		String homePackage = model20.getLocalHomeNamePackage();
		if (model20.shouldCreateLocalHomeInterface())
			new CreateLocalHomeInterfaceCommand(ejbCommand, homeName, homePackage, true);
		else
			new AddLocalHomeInterfaceCommand(ejbCommand, homeName, homePackage);
	}

	protected void createRemoteCommand() {
		String remoteName = creationModel.getRemoteName();
		String remotePackage = creationModel.getRemotePackage();
		if (creationModel.shouldCreateRemoteInterface()) {
			IEJBClassReferenceCommand remoteInterfaceCommand = new CreateRemoteInterfaceCommand(ejbCommand, remoteName, remotePackage, true);
			initializeCreateRemoteClientInterfaceCommand(remoteInterfaceCommand);
		} else
			new AddRemoteInterfaceCommand(ejbCommand, remoteName, remotePackage);
	}

	protected void createLocalCommand() {
		EJB20CreationModel model20 = (EJB20CreationModel) creationModel;
		String localName = model20.getLocalName();
		String localPackage = model20.getLocalNamePackage();
		if (model20.shouldCreateLocalInterface()) {
			IEJBClassReferenceCommand command = new CreateLocalInterfaceCommand(ejbCommand, localName, localPackage, true);
			initializeCreateLocalClientInterfaceCommand(command);
		} else
			new AddLocalInterfaceCommand(ejbCommand, localName, localPackage);
	}

	protected void initializeCreateRemoteClientInterfaceCommand(IEJBClassReferenceCommand interfaceCommand) {
		if (interfaceCommand == null)
			return;
		String[] interfaces = creationModel.getRemoteExtendsInterfaceNames();
		if (interfaces == null)
			return;
		for (int i = 0; i < interfaces.length; i++)
			interfaceCommand.addSuperInterfaceName(interfaces[i]);
	}


	protected void initializeCreateLocalClientInterfaceCommand(IEJBClassReferenceCommand interfaceCommand) {
		if (interfaceCommand == null)
			return;
		String[] interfaces = ((EJB20CreationModel) creationModel).getLocalExtendsInterfaceNames();
		if (interfaces == null)
			return;
		for (int i = 0; i < interfaces.length; i++)
			interfaceCommand.addSuperInterfaceName(interfaces[i]);
	}

	protected void createEJBClassCommand() {
		String beanClassName = creationModel.getEJBClassName();
		String beanClassPackage = creationModel.getEJBClassPackage();
		if (creationModel.shouldCreateEJBClass()) {
			IEJBClassReferenceCommand beanClassCommand = new CreateBeanClassCommand(ejbCommand, beanClassName, beanClassPackage, true);
			initializeCreateBeanClassCommand(beanClassCommand);
		} else
			new AddBeanClassCommand(ejbCommand, beanClassName, beanClassPackage);
	}

	protected void initializeCreateBeanClassCommand(IEJBClassReferenceCommand beanClassCommand) {
		if (beanClassCommand == null)
			return;
		beanClassCommand.setSuperclassName(creationModel.getEJBClassSuperclassName());

		/*
		 * NSS: Import statement are not used anymore, so the following code is not needed String[]
		 * importStatements = creationModel.getEJBClassImportStatements(); if (importStatements ==
		 * null) return; for (int i = 0; i < importStatements.length; i++) { int index =
		 * importStatements[i].indexOf('*'); if (index != -1) { String importStatement =
		 * importStatements[i].substring(0, index - 1); if (importStatement.length() > 0)
		 * beanClassCommand.addPackageImportName(importStatement); } else
		 * beanClassCommand.addTypeImportName(importStatements[i]); }
		 */
	}

	protected void createPrimaryKeyClassCommand() {
		int type = creationModel.getBeanType();
		if (type == EJBCreationModel.CMP && creationModel.shouldUsePrimKeyField())
			return;
		String keyClassName = creationModel.getPrimaryKeyClassName();
		String keyPackage = creationModel.getPrimaryKeyClassPackage();
		boolean shouldCreateKeyClass = creationModel.shouldCreatePrimaryKeyClass();
		if (creationModel.getBeanSupertypeName() != null && creationModel.getBeanSupertypeName().length() > 0) {
			Entity entity = getEntity(creationModel.getBeanSupertypeName());
			if (entity != null) {
				if (type == EJBCreationModel.CMP && ((ContainerManagedEntity) entity).getPrimKeyField() != null)
					return;
				shouldCreateKeyClass = false;
				String qualifiedName = entity.getPrimaryKeyName();
				keyClassName = Signature.getSimpleName(qualifiedName);
				keyPackage = Signature.getQualifier(qualifiedName);
			}
		}
		if (shouldCreateKeyClass)
			new CreatePrimaryKeyClassCommand(ejbCommand, keyClassName, keyPackage);
		else
			new AddPrimaryKeyClassCommand(ejbCommand, keyClassName, keyPackage);
	}

	protected Entity getEntity(String beanName) {
		EJBJar jar = getEditModel().getEJBJar();
		if (jar != null)
			return (Entity) jar.getEnterpriseBeanNamed(beanName);
		return null;
	}

	protected void createAttributeCommands() {
		IPersistentAttributeCommand attributeCommand;
		List fields = creationModel.getCMPFields();
		if (fields == null)
			return;
		CMPField field;
		for (int i = 0; i < fields.size(); i++) {
			field = (CMPField) fields.get(i);
			if (creationModel.shouldCreateEJBClass() || (creationModel.shouldCreateRemoteInterface() && field.isPromoteGS()) || (creationModel.isEJB20() && ((EJB20CreationModel) creationModel).shouldCreateLocalInterface() && field.isPromoteLocalGS()))
				attributeCommand = new CreatePersistentAttributeCommand(ejbCommand, field.getName(), true);
			else
				attributeCommand = new AddPersistentAttributeCommand(ejbCommand, field.getName());
			initializePersistentAttributeCommand(attributeCommand, field);
		}
	}

	protected void initializePersistentAttributeCommand(IPersistentAttributeCommand attributeCommand, CMPField field) {
		if (attributeCommand == null)
			return;
		attributeCommand.setTypeName(field.getType());
		attributeCommand.setInitializer(field.getInitialValue());
		attributeCommand.setKey(field.isIsKey());
		attributeCommand.setGenerateAccessors(field.isAccessWithGS() || creationModel.isEJB20());
		if (field.isIsArray())
			attributeCommand.setArrayDimensions(field.getArrayDimension());
		if (creationModel.isEJB20()) {
			EJB20CreationModel model20 = (EJB20CreationModel) creationModel;
			if (model20.isRemoteClient())
				attributeCommand.setRemote(field.isPromoteGS());
			if (model20.isLocalClient())
				attributeCommand.setLocal(field.isPromoteLocalGS());
		} else {
			attributeCommand.setRemote(field.isPromoteGS());
			attributeCommand.setIsReadOnly(field.isGetterRO());
		}
	}

	/*
	 * @see EjbModificationOperation#errorMessage()
	 */
	protected String errorMessage() {
		return EJBCodeGenResourceHandler.getString("Failed_to_create_the_EnterpriseBean"); //$NON-NLS-1$
	}



	/**
	 * @see IEJBCreationOperation#getNewEnterpriseBean()
	 */
	public EnterpriseBean getNewEnterpriseBean() {
		return getEjb();
	}

}