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
/*
 * Created on Jan 20, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodegenHandlerExtensionReader;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.IEJBCodegenHandler;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddBeanClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateBeanClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBClassReferenceCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.WTPJETEmitter;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.internal.annotations.controller.AnnotationsController;
import org.eclipse.wst.common.internal.annotations.controller.AnnotationsControllerManager;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;
import org.eclipse.wst.common.jdt.internal.integration.WorkingCopyManager;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class CreateEnterpriseBeanOperation extends EditModelOperation {


	/**
	 * @param dataModel
	 */
	public CreateEnterpriseBeanOperation(CreateEnterpriseBeanDataModel dataModel) {
		super(dataModel);
	}

	/**
	 *  
	 */
	public CreateEnterpriseBeanOperation() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		createSourceFolder(monitor);
		createEnterpriseBean(monitor);
	}

	/**
	 *  
	 */
	private final void createSourceFolder(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(editModel.getProject());
		String folderName = (String) operationDataModel.getProperty(CreateEnterpriseBeanDataModel.SOURCE_FOLDER_NAME);
		if (folderName != null && folderName.length() > 0) {
			WTPOperation op = new CreateSourceFolderOperation(folderName, nature);
			op.run(monitor);
		}
	}

	/**
	 *  
	 */
	private final void createEnterpriseBean(IProgressMonitor monitor) throws WFTWrappedException, CoreException {
		if (useAnnotations())
			generateUsingTemplates(monitor);
		else {
			Command command = createCommand(monitor);
			if (command != null)
				editModel.getCommandStack().execute(command);
		}
	}

	/**
	 * @param monitor
	 */
	private void generateUsingTemplates(IProgressMonitor monitor) throws WFTWrappedException, CoreException {
		CreateEnterpriseBeanTemplateModel tempModel = createTemplateModel();
		String source;
		IFile annotatedFile = null;

		try {
			source = generateTemplateSource(tempModel, monitor);
		} catch (JETException e) {
			throw new WFTWrappedException(e);
		}
		IPackageFragment fragment = getOrCreatePackageFragment(tempModel.getBeanClassPackageName(), monitor);
		if (fragment != null) {
			String javaFileName = tempModel.getSimpleBeanClassName() + ".java"; //$NON-NLS-1$
			ICompilationUnit cu = fragment.getCompilationUnit(javaFileName);
			if (cu == null || !cu.exists())
				cu = fragment.createCompilationUnit(javaFileName, source, true, monitor);
			annotatedFile = (IFile) cu.getResource();
			editModel.getWorkingCopy(cu, true); //Track CU.
		}
		WorkingCopyManager mgr = editModel.getWorkingCopyManager();
		if (mgr != null && annotatedFile != null) {
			mgr.saveOnlyNewCompilationUnits(new SubProgressMonitor(monitor, 1));
			AnnotationsController controller = AnnotationsControllerManager.INSTANCE.getAnnotationsController(editModel.getProject());
			if (controller != null)
				controller.process(annotatedFile);
		}
	}

	/**
	 * @param monitor
	 * @return
	 */
	private IPackageFragment getOrCreatePackageFragment(String packageName, IProgressMonitor monitor) throws JavaModelException {
		IFolder source = (IFolder) operationDataModel.getProperty(CreateEnterpriseBeanDataModel.SOURCE_FOLDER);
		IPackageFragmentRoot root = (IPackageFragmentRoot) JavaCore.create(source);
		IPackageFragment fragment = root.getPackageFragment(packageName);
		if (!fragment.exists())
			fragment = root.createPackageFragment(packageName, false, monitor);
		return fragment;
	}

	private String generateTemplateSource(CreateEnterpriseBeanTemplateModel tempModel, IProgressMonitor monitor) throws JETException {
		String templateURI = "platform:/plugin/" + EjbPlugin.PLUGIN_ID + "/templates/" + getTemplateFileName(); //$NON-NLS-1$ //$NON-NLS-2$
		WTPJETEmitter emitter = new WTPJETEmitter(templateURI, this.getClass().getClassLoader());
		emitter.setIntelligentLinkingEnabled(true);
		emitter.addVariable("EJB_PLUGIN", EjbPlugin.PLUGIN_ID); //$NON-NLS-1$
		return emitter.generate(monitor, new Object[]{tempModel});
	}

	/**
	 * @return
	 */
	protected abstract CreateEnterpriseBeanTemplateModel createTemplateModel();

	/**
	 * @return
	 */
	protected abstract String getTemplateFileName();

	/**
	 * @return
	 */
	private boolean useAnnotations() {
		return operationDataModel.getBooleanProperty(CreateEnterpriseBeanDataModel.USE_ANNOTATIONS);
	}

	/**
	 * @param monitor
	 * @return
	 */
	private Command createCommand(IProgressMonitor monitor) {
		String beanName = (String) operationDataModel.getProperty(CreateEnterpriseBeanDataModel.BEAN_NAME);
		EnterpriseBeanCommand ejbCommand = createRootCommand(beanName);
		if (ejbCommand != null) {
			initializeRootCommand(ejbCommand);
			createDependentCommands(ejbCommand);
		}
		return ejbCommand;
	}

	/**
	 * Add all dependent EJB commands to the root ejbCommand.
	 * 
	 * @param ejbCommand
	 */
	protected void createDependentCommands(EnterpriseBeanCommand ejbCommand) {
		createEJBClassCommand(ejbCommand);
		createEJBInheritanceCommand(ejbCommand);
	}

	/**
	 * @param ejbCommand
	 */
	private void createEJBInheritanceCommand(EnterpriseBeanCommand ejbCommand) {
		EnterpriseBean superEJB = (EnterpriseBean) operationDataModel.getProperty(CreateEnterpriseBeanDataModel.BEAN_SUPEREJB);
		if (superEJB != null) {
			IEJBCodegenHandler handler = getCodegenHandler();
			handler.createEJBInheritanceCommand(superEJB, ejbCommand);
		}
	}

	/**
	 * @param ejbCommand
	 */
	private void createEJBClassCommand(EnterpriseBeanCommand ejbCommand) {
		String beanClassName = operationDataModel.getStringProperty(CreateEnterpriseBeanDataModel.BEAN_CLASS_NAME);
		JavaClass beanClass = reflectJavaClass(beanClassName);
		if (shouldGenerateClass(CreateEnterpriseBeanDataModel.BEAN_CLASS_NAME)) {
			IEJBClassReferenceCommand beanClassCommand = new CreateBeanClassCommand(ejbCommand, beanClass);
			beanClassCommand.setSuperclassName((String) operationDataModel.getProperty(CreateEnterpriseBeanDataModel.BEAN_CLASS_SUPERCLASS));
		} else {
			new AddBeanClassCommand(ejbCommand, beanClass);
		}
	}

	protected JavaClass reflectJavaClass(String qualifiedName) {
		return (JavaClass) JavaRefFactory.eINSTANCE.reflectType(qualifiedName, editModel.getResourceSet());
	}

	protected boolean shouldGenerateClass(String classPropertyName) {
		CreateEnterpriseBeanDataModel ejbModel = (CreateEnterpriseBeanDataModel) operationDataModel;
		return !ejbModel.typeExists(classPropertyName);
	}

	/**
	 * @param ejbCommand
	 */
	protected void initializeRootCommand(EnterpriseBeanCommand ejbCommand) {
		ejbCommand.setVersion2_X(((CreateEnterpriseBeanDataModel) operationDataModel).isVersion2xOrGreater());
		ejbCommand.setOperationHandler((IOperationHandler) operationDataModel.getProperty(CreateEnterpriseBeanDataModel.OPERATION_HANDLER));
		ejbCommand.setDefaultPackageFragmentRootName(operationDataModel.getStringProperty(CreateEnterpriseBeanDataModel.SOURCE_FOLDER_NAME));
	}

	/**
	 * Return a new EnterpriseBeanCommand for the specific type of bean that is being created.
	 * 
	 * @return
	 */
	protected abstract EnterpriseBeanCommand createRootCommand(String beanName);

	private IEJBCodegenHandler getCodegenHandler() {
		return EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(operationDataModel.getTargetProject());
	}

}