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
 * Created on Apr 1, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsController;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerManager;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassOperation;
import org.eclipse.jst.j2ee.internal.project.WTPJETEmitter;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class NewServletClassOperation extends NewJavaClassOperation {
	protected static final String TO_STRING = "toString"; //$NON-NLS-1$
	protected EditModel editModel = null;
	public static final String TEMPLATE_EMITTER = "org.eclipse.jst.j2ee.ejb.annotations.emitter.template"; //$NON-NLS-1$
	public static final String BUILDER_ID = "builderId"; //$NON-NLS-1$
	
	/**
	 * @param dataModel
	 */
	public NewServletClassOperation(WTPOperationDataModel dataModel) {
		super(dataModel);
		//EditModel editModel =
		// J2EEWebNatureRuntime.getEditModelForProject(dataModel.getTargetProject(),this);
		//EditModel editModel = model.getEditModelForRead(this);
		//setEditModel(editModel);
	}

	protected boolean implementImplementedMethod(IMethod method) {
		NewServletClassDataModel model = (NewServletClassDataModel) this.operationDataModel;
		String methodName = method.getElementName();
		if (methodName.equals("init")) { //$NON-NLS-1$
			return model.getBooleanProperty(NewServletClassDataModel.INIT);
		} else if (methodName.equals("toString")) { //$NON-NLS-1$
			return model.getBooleanProperty(NewServletClassDataModel.TO_STRING);
		} else if (methodName.equals("getServletInfo")) { //$NON-NLS-1$
			return model.getBooleanProperty(NewServletClassDataModel.GET_SERVLET_INFO);
		} else if (methodName.equals("doPost")) { //$NON-NLS-1$
			return model.getBooleanProperty(NewServletClassDataModel.DO_POST);
		} else if (methodName.equals("doPut")) { //$NON-NLS-1$
			return model.getBooleanProperty(NewServletClassDataModel.DO_PUT);
		} else if (methodName.equals("doDelete")) { //$NON-NLS-1$
			return model.getBooleanProperty(NewServletClassDataModel.DO_DELETE);
		} else if (methodName.equals("destroy")) { //$NON-NLS-1$
			return model.getBooleanProperty(NewServletClassDataModel.DESTROY);
		} else if (methodName.equals("doGet")) { //$NON-NLS-1$
			return model.getBooleanProperty(NewServletClassDataModel.DO_GET);
		}
		return false;
	}

	protected String getUserDefinedMethodStubs(IType superClassType) {
		// toString method is not found in the getMethods of the
		// IType and hence extra handling is needed to generate toString()
		return generateToString(superClassType);
	}

	private String generateToString(IType superClassType) {
		StringBuffer sb = new StringBuffer();
		NewServletClassDataModel model = (NewServletClassDataModel) this.operationDataModel;
		if (model.getBooleanProperty(NewServletClassDataModel.TO_STRING)) {
			try {
				IMethod[] methods = superClassType.getMethods();
				// check whether toString is already generated
				for (int j = 0; j < methods.length; j++) {
					IMethod method = methods[j];
					if (method.getElementName().equals(TO_STRING))
						return EMPTY_STRING;
				}
				// generate stub for toString method
				String name = TO_STRING;
				// Java doc
				sb.append("\t/* (non-Java-doc)"); //$NON-NLS-1$
				sb.append(lineSeparator);
				sb.append("\t * @see "); //$NON-NLS-1$
				sb.append(JAVA_LANG_OBJECT + POUND + name + OPEN_PAR);
				sb.append(CLOSE_PAR);
				sb.append(lineSeparator);
				sb.append("\t */"); //$NON-NLS-1$
				sb.append(lineSeparator);
				// access
				sb.append(TAB);
				sb.append(PUBLIC);

				// return type
				String returnType = "String"; //$NON-NLS-1$
				sb.append(returnType);
				sb.append(SPACE);
				// name
				sb.append(name);
				// Parameters
				sb.append(OPEN_PAR + CLOSE_PAR);
				sb.append(SPACE + OPEN_BRA);
				sb.append(lineSeparator);
				// method body
				sb.append(TODO_COMMENT);
				sb.append(lineSeparator);
				sb.append(RETURN_NULL);
				sb.append(lineSeparator);
				// method body end
				sb.append(TAB + CLOSE_BRA);
				sb.append(lineSeparator);
				sb.append(lineSeparator);

			} catch (JavaModelException e) {
				Logger.getLogger().log(e);
			}
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		IFolder sourceFolder = createJavaSourceFolder();
		IPackageFragment pack = createJavaPackage();
		// if using annotations
		if (((NewServletClassDataModel) getOperationDataModel()).isAnnotated())
			generateUsingTemplates(monitor, pack);
		else
			createJavaFile(sourceFolder, pack);
	}

	/**
	 * @param monitor
	 */
	private void generateUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment) throws WFTWrappedException, CoreException {
		CreateServletTemplateModel tempModel = createTemplateModel();
		String source;
		try {
			source = generateTemplateSource(tempModel, monitor);
		} catch (JETException e) {
			throw new WFTWrappedException(e);
		}
		if (fragment != null) {
			String javaFileName = tempModel.getServletClassName() + ".java"; //$NON-NLS-1$
			ICompilationUnit cu = fragment.getCompilationUnit(javaFileName);
			if (cu == null || !cu.exists())
				cu = fragment.createCompilationUnit(javaFileName, source, true, monitor);
			IFile aFile = (IFile) cu.getResource();
			AnnotationsController controller = AnnotationsControllerManager.INSTANCE.getAnnotationsController(this.editModel.getProject());
			if (controller != null)
				controller.process(aFile);
			((J2EEEditModel)this.editModel).getWorkingCopy(cu, true); //Track CU.
		}
		addAnnotationsBuilder();
	}
	
	private void addAnnotationsBuilder() {
		try {
			NewServletClassDataModel dataModel = (NewServletClassDataModel) operationDataModel;
			IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(TEMPLATE_EMITTER);
			String builderID = configurationElements[0].getNamespace() + "."+ configurationElements[0].getAttribute(BUILDER_ID); //$NON-NLS-1$
			IProject project = dataModel.getTargetProject(); 
			IProjectDescription description = project.getDescription();
			ICommand[] commands = description.getBuildSpec();
			boolean found = false;
			for (int i = 0; i < commands.length; ++i) {
				if (commands[i].getBuilderName().equals(builderID)) {
					found = true;
					break;
				}
			}
			if (!found) {
				ICommand command = description.newCommand();
				command.setBuilderName(builderID);
				ICommand[] newCommands = new ICommand[commands.length + 1];
				System.arraycopy(commands, 0, newCommands, 0, commands.length);
				newCommands[commands.length] = command;
				IProjectDescription desc = project.getDescription();
				desc.setBuildSpec(newCommands);
				project.setDescription(desc, null);
			}
		} catch (Exception e) {
			//Ignore
		}
	}

	private String generateTemplateSource(CreateServletTemplateModel tempModel, IProgressMonitor monitor) throws JETException {
		String templateURI = "platform:/plugin/" + WebPlugin.PLUGIN_ID + "/templates/" + getTemplateFileName(); //$NON-NLS-1$ //$NON-NLS-2$
		WTPJETEmitter emitter = new WTPJETEmitter(templateURI, this.getClass().getClassLoader());
		emitter.setIntelligentLinkingEnabled(true);
		emitter.addVariable("WEB_PLUGIN", WebPlugin.PLUGIN_ID); //$NON-NLS-1$
		return emitter.generate(monitor, new Object[]{tempModel});
	}

	protected CreateServletTemplateModel createTemplateModel() {
		CreateServletTemplateModel model = new CreateServletTemplateModel((NewServletClassDataModel) getOperationDataModel());
		return model;
	}

	protected String getTemplateFileName() {
		return "servletXDoclet.javajet"; //$NON-NLS-1$
	}

	/**
	 * @param editModel
	 *            The editModel to set.
	 */
	public void setEditModel(EditModel editModel) {
		this.editModel = editModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#dispose(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void dispose(IProgressMonitor pm) {
		//if (editModel != null)
		//    editModel.releaseAccess(this);
		super.dispose(pm);
	}
}