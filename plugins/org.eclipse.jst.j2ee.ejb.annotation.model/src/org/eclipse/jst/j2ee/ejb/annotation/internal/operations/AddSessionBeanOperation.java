/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/


package org.eclipse.jst.j2ee.ejb.annotation.internal.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.BeanFactory;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EjbCommonDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.MessageDrivenBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ModelPlugin;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.utility.AnnotationUtilities;
import org.eclipse.jst.j2ee.ejb.annotations.internal.classgen.EjbBuilder;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.EjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.SessionEjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.model.ISessionBeanDelegate;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;



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

	private void createDefaultSessionBean(IProgressMonitor monitor) throws CoreException, InterruptedException {
		
		SessionBeanDataModel ejbModel = (SessionBeanDataModel)this.getOperationDataModel();
		NewJavaClassDataModel ejbClassModel = (NewJavaClassDataModel)ejbModel.getNestedModel("NewEJBJavaClassDataModel");
		
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
		
		
			IConfigurationElement preferredAnnotation = AnnotationUtilities.getPreferredAnnotationEmitter();
			String comment = "";
			String stub = "";
			String method="";
			String className = sessionBean.getEjbClassName();

			try {
				AnnotationUtilities.addAnnotationBuilderToProject(preferredAnnotation,ejbClassModel.getTargetProject());
				EjbEmitter ejbEmitter = new SessionEjbEmitter(preferredAnnotation);
				ejbEmitter.setMonitor(monitor);
				comment = ejbEmitter.emitTypeComment(delegate);
				stub = ejbEmitter.emitTypeStub(delegate);
				method = ejbEmitter.emitInterfaceMethods(delegate);
			}catch (CoreException e) {
				throw e;
			} catch (Exception e) {
				throw new CoreException(new Status(IStatus.ERROR,ModelPlugin.PLUGINID,0,"Session EJB Emitters Failed",e));
			}

			
		
			EjbBuilder ejbBuilder = new EjbBuilder();
			ejbBuilder.setConfigurationElement(preferredAnnotation);
			ejbBuilder.setMonitor(monitor);
			ejbBuilder.setPackageFragmentRoot(ejbClassModel.getJavaPackageFragmentRoot());
			ejbBuilder.setEnterpriseBeanDelegate(delegate);
			ejbBuilder.setTypeName(ejbClassModel.getStringProperty(NewJavaClassDataModel.CLASS_NAME));
			ejbBuilder.setPackageName(ejbClassModel.getStringProperty(NewJavaClassDataModel.JAVA_PACKAGE));
				
			ejbBuilder.setTypeComment(comment);
			ejbBuilder.setTypeStub(stub);
			ejbBuilder.setMethodStub(method);
			ejbBuilder.setFields("");
				
			ejbBuilder.createType();
			
		
	}



}