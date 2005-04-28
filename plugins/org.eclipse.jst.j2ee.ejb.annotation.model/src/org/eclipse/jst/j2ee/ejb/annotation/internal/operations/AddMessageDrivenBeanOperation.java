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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IType;
import org.eclipse.jst.j2ee.ejb.DestinationType;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.MessageDrivenDestination;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.BeanFactory;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EjbCommonDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.MessageDrivenBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ModelPlugin;
import org.eclipse.jst.j2ee.ejb.annotation.internal.utility.AnnotationUtilities;
import org.eclipse.jst.j2ee.ejb.annotations.internal.classgen.EjbBuilder;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.EjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.MessageDrivenEjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.model.IMessageDrivenBeanDelegate;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class AddMessageDrivenBeanOperation extends WTPOperation {
	/**
	 * @param dataModel
	 */
	public AddMessageDrivenBeanOperation(EjbCommonDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		createDefaultMessageDrivenBean(monitor);

	}

	private void createDefaultMessageDrivenBean(IProgressMonitor monitor)
			throws CoreException, InterruptedException {

		MessageDrivenBeanDataModel ejbModel = (MessageDrivenBeanDataModel) this
				.getOperationDataModel();
		NewJavaClassDataModel ejbClassModel = (NewJavaClassDataModel) ejbModel
				.getNestedModel("NewEJBJavaClassDataModel");

		MessageDriven mdBean = EjbFactory.eINSTANCE.createMessageDriven();
		mdBean.setName(ejbModel.getStringProperty(EjbCommonDataModel.EJB_NAME));
		mdBean.setDescription(ejbModel
				.getStringProperty(EjbCommonDataModel.DESCRIPTION));
		mdBean.setDisplayName(ejbModel
				.getStringProperty(EjbCommonDataModel.DISPLAY_NAME));
		mdBean.setEjbClassName(ejbClassModel.getQualifiedClassName());

		String destType = ejbModel
				.getStringProperty(MessageDrivenBeanDataModel.DESTINATIONTYPE);
		DestinationType dType = DestinationType.QUEUE_LITERAL;
		if (destType.equals(DestinationType.TOPIC_LITERAL.getName()))
			dType = DestinationType.TOPIC_LITERAL;

		MessageDrivenDestination destination = EjbFactory.eINSTANCE
				.createMessageDrivenDestination();
		destination.setType(dType);
		destination.setBean(mdBean);

		mdBean.setDestination(destination);
		mdBean.setMessageSelector(ejbModel
				.getStringProperty(MessageDrivenBeanDataModel.DESTINATIONNAME));

		String tType = ejbModel
				.getStringProperty(MessageDrivenBeanDataModel.TRANSACTIONTYPE);
		TransactionType transactionType = TransactionType.CONTAINER_LITERAL;
		if (tType.equals(TransactionType.BEAN_LITERAL.getName()))
			transactionType = TransactionType.BEAN_LITERAL;
		mdBean.setTransactionType(transactionType);

		IMessageDrivenBeanDelegate delegate = BeanFactory.getDelegate(mdBean,
				ejbModel);


		String comment = "";
		String stub = "";
		String method = "";
		String fields = "";
		String className = mdBean.getEjbClassName();
		IConfigurationElement preferredAnnotation = AnnotationUtilities
		.findAnnotationEmitterForProvider(ejbModel.getStringProperty(MessageDrivenBeanDataModel.ANNOTATIONPROVIDER));

		try {
			EjbEmitter ejbEmitter = new MessageDrivenEjbEmitter(
					preferredAnnotation);
			ejbEmitter.setMonitor(monitor);
			fields = ejbEmitter.emitFields(delegate);
			comment = ejbEmitter.emitTypeComment(delegate);
			stub = ejbEmitter.emitTypeStub(delegate);
			method = ejbEmitter.emitInterfaceMethods(delegate);
		} catch (CoreException e) {
			throw e;
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR,
					ModelPlugin.PLUGINID, 0,
					"MessageDriven EJB Emitters Failed", e));
		}

		EjbBuilder ejbBuilder = new EjbBuilder();
		ejbBuilder.setConfigurationElement(preferredAnnotation);
		ejbBuilder.setMonitor(monitor);
		ejbBuilder.setPackageFragmentRoot(ejbClassModel
				.getJavaPackageFragmentRoot());
		ejbBuilder.setEnterpriseBeanDelegate(delegate);
		ejbBuilder.setTypeName(ejbClassModel
				.getStringProperty(NewJavaClassDataModel.CLASS_NAME));
		ejbBuilder.setPackageName(ejbClassModel
				.getStringProperty(NewJavaClassDataModel.JAVA_PACKAGE));

		ejbBuilder.setTypeComment(comment);
		ejbBuilder.setTypeStub(stub);
		ejbBuilder.setMethodStub(method);
		ejbBuilder.setFields(fields);

		ejbBuilder.createType();
		IType bean = ejbBuilder.getCreatedType();
		IResource javaFile = bean.getCorrespondingResource();
		IProject project = ejbClassModel.getTargetProject();
		initializeBuilder(monitor, preferredAnnotation,javaFile, project);

	}

	protected void initializeBuilder(IProgressMonitor monitor, IConfigurationElement emitterConfiguration, IResource javaFile, IProject project) throws CoreException {
		AnnotationUtilities.addAnnotationBuilderToProject(
				emitterConfiguration, project);
	}

}