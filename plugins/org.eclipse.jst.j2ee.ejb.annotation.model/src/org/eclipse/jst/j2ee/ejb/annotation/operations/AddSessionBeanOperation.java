/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/


package org.eclipse.jst.j2ee.ejb.annotation.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.ejb.annotation.model.BeanFactory;
import org.eclipse.jst.j2ee.ejb.annotation.model.EjbCommonDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.model.MessageDrivenBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.model.NewEJBJavaClassDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.model.SessionBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotations.ISessionBeanDelegate;
import org.eclipse.jst.j2ee.ejb.annotations.classgen.EjbBuilder;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.EjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.SessionEjbEmitter;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;



public class AddSessionBeanOperation extends WTPOperation {
	/**
	 * @param dataModel
	 */
	public AddSessionBeanOperation(EjbCommonDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		createDefaultSessionBean(monitor);
		
	}

	private void createDefaultSessionBean(IProgressMonitor monitor) {
		
		SessionBeanDataModel ejbModel = (SessionBeanDataModel)this.getOperationDataModel();
		NewEJBJavaClassDataModel ejbClassModel = (NewEJBJavaClassDataModel)ejbModel.getNestedModel("NewEJBJavaClassDataModel");
		
		Session sessionBean = EjbFactory.eINSTANCE.createSession();
		sessionBean.setName(ejbModel.getStringProperty(EjbCommonDataModel.EJB_NAME));
		sessionBean.setDescription(ejbModel.getStringProperty(EjbCommonDataModel.DESCRIPTION));
		sessionBean.setDisplayName(ejbModel.getStringProperty(EjbCommonDataModel.DISPLAY_NAME));
		sessionBean.setEjbClassName(ejbClassModel.getQualifiedClassName());

		String stateType = ejbModel.getStringProperty(EjbCommonDataModel.STATELESS);
		SessionType sessionBeanType = SessionType.STATELESS_LITERAL;
		if( stateType.equals(SessionType.STATEFUL_LITERAL.getName()))
			sessionBeanType = SessionType.STATEFUL_LITERAL;
		sessionBean.setSessionType(sessionBeanType);

		String tType = ejbModel.getStringProperty(MessageDrivenBeanDataModel.TRANSACTIONTYPE);
		TransactionType transactionType =TransactionType.CONTAINER_LITERAL;
		if(tType.equals(TransactionType.BEAN_LITERAL.getName()))
			transactionType = TransactionType.BEAN_LITERAL;
		sessionBean.setTransactionType(transactionType);

		
		ISessionBeanDelegate delegate =BeanFactory.getDelegate(sessionBean, ejbModel);
		
		
		try {
			IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.jst.j2ee.ejb.annotations.emitter.template");
			String builderId = configurationElements[0].getAttribute("builderId");
			addToEndOfBuildSpec( ejbClassModel.getTargetProject(),  configurationElements[0].getNamespace() + "."+builderId);
			EjbEmitter ejbEmitter = new SessionEjbEmitter(configurationElements[0]);
			String comment = ejbEmitter.emitTypeComment(delegate);
			String stub = ejbEmitter.emitTypeStub(delegate);
			String method = ejbEmitter.emitInterfaceMethods(delegate);
			String className = sessionBean.getEjbClassName();

			
		
			EjbBuilder ejbBuilder = new EjbBuilder();
			ejbBuilder.setConfigurationElement(configurationElements[0]);
			ejbBuilder.setMonitor(monitor);
			ejbBuilder.setPackageFragmentRoot(ejbClassModel.getJavaPackageFragmentRoot());
			ejbBuilder.setEnterpriseBeanDelegate(delegate);
			ejbBuilder.setTypeName(ejbClassModel.getStringProperty(NewEJBJavaClassDataModel.CLASS_NAME));
			ejbBuilder.setPackageName(ejbClassModel.getStringProperty(NewEJBJavaClassDataModel.JAVA_PACKAGE));
				
			ejbBuilder.setTypeComment(comment);
			ejbBuilder.setTypeStub(stub);
			ejbBuilder.setMethodStub(method);
			ejbBuilder.setFields("");
				
			ejbBuilder.createType();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	/**
	 * Adds a builder to the build spec for the given project.
	 */
	protected void addToEndOfBuildSpec(IProject project, String builderID) throws CoreException {
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
	}

}