/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.commands;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;


public class DeleteClassReferenceCommand extends EJBClassReferenceCommand {
	protected static final EjbPackage EJBPACK = EjbFactoryImpl.getPackage();
	public static final EStructuralFeature EJB_CLASS = EJBPACK.getEnterpriseBean_EjbClass();
	public static final EStructuralFeature HOME = EJBPACK.getEnterpriseBean_HomeInterface();
	public static final EStructuralFeature LOCAL_HOME = EJBPACK.getEnterpriseBean_LocalHomeInterface();
	public static final EStructuralFeature REMOTE = EJBPACK.getEnterpriseBean_RemoteInterface();
	public static final EStructuralFeature LOCAL = EJBPACK.getEnterpriseBean_LocalInterface();
	public static final EStructuralFeature SERVICE_ENDPOINT = EJBPACK.getSession_ServiceEndpoint();

	protected EStructuralFeature feature;

	/**
	 * Constructor for DeleteClassReferenceCommand.
	 * 
	 * @param parent
	 * @param aJavaClass
	 */
	public DeleteClassReferenceCommand(IRootCommand parent, JavaClass aJavaClass, EStructuralFeature aType) {
		super(parent, aJavaClass);
		feature = aType;
	}


	/**
	 * Constructor for DeleteClassReferenceCommand.
	 * 
	 * @param parent
	 * @param aJavaClass
	 * @param shouldGenJava
	 * @param shouldGenMetadata
	 */
	public DeleteClassReferenceCommand(IRootCommand parent, JavaClass aJavaClass, EStructuralFeature aType, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aJavaClass, shouldGenJava, shouldGenMetadata);
		feature = aType;
	}

	/*
	 * @see EJBCommand#createCodegenHelper()
	 */
	protected EJBGenerationHelper createCodegenHelper() {
		EJBClassReferenceHelper aHelper = createClassReferenceHelper();
		aHelper.setDelete();
		return aHelper;
	}

	/*
	 * @see EJBCommand#createInverseCodegenHelper()
	 */
	protected EJBGenerationHelper createInverseCodegenHelper() {
		EJBClassReferenceHelper aHelper = createClassReferenceHelper();
		aHelper.setCreate();
		return aHelper;
	}

	protected EJBClassReferenceHelper createClassReferenceHelper() {
		EJBClassReferenceHelper aHelper = new EJBClassReferenceHelper(getJavaClass());
		setHelperType(aHelper);
		return aHelper;
	}

	/*
	 * @see EJBCommand#executeForMetadataGeneration()
	 */
	protected void executeForMetadataGeneration() throws CommandExecutionFailure {
		super.executeForMetadataGeneration();
		if (getEjb() != null && feature != null)
			getEjb().eUnset(feature);
	}

	/*
	 * @see EJBCommand#undoMetadataGeneration()
	 */
	protected void undoMetadataGeneration() {
		super.undoMetadataGeneration();
		if (getEjb() != null && feature != null && getJavaClass() != null)
			getEjb().eSet(feature, getJavaClass());
	}

	/*
	 * @see EJBCommand#setupHelper()
	 */
	protected void setupHelper() {
		super.setupHelper();
		getHelper().setDelete();
	}

	protected void setHelperType(EJBClassReferenceHelper aHelper) {
		if (feature == HOME)
			aHelper.setHomeHelper();
		else if (feature == LOCAL_HOME)
			aHelper.setLocalHomeHelper();
		else if (feature == REMOTE)
			aHelper.setRemoteHelper();
		else if (feature == LOCAL)
			aHelper.setLocalHelper();
		else if (feature == EJB_CLASS)
			aHelper.setBeanHelper();
		else if (feature == SERVICE_ENDPOINT)
			aHelper.setServiceEndpointHelper();
	}

	/*
	 * @see EJBCommand#setupInverseHelper()
	 */
	protected void setupInverseHelper() {
		super.setupInverseHelper();
		getHelper().setCreate();
	}

}