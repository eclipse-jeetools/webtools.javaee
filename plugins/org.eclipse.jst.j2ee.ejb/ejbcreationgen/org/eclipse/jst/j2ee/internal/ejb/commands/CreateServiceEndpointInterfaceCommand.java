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
 * Created on Mar 10, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;


/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class CreateServiceEndpointInterfaceCommand extends EJBClassReferenceCommand {
	/**
	 * @param parent
	 * @param aJavaClass
	 */
	public CreateServiceEndpointInterfaceCommand(IRootCommand parent, JavaClass aJavaClass) {
		super(parent, aJavaClass);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param aJavaClass
	 * @param shouldGenJava
	 */
	public CreateServiceEndpointInterfaceCommand(IRootCommand parent, JavaClass aJavaClass, boolean shouldGenJava) {
		super(parent, aJavaClass, shouldGenJava);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param aJavaClass
	 * @param shouldGenJava
	 * @param shouldGenMetadata
	 */
	public CreateServiceEndpointInterfaceCommand(IRootCommand parent, JavaClass aJavaClass, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aJavaClass, shouldGenJava, shouldGenMetadata);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param aJavaClassName
	 * @param aPackageName
	 */
	public CreateServiceEndpointInterfaceCommand(IRootCommand parent, String aJavaClassName, String aPackageName) {
		super(parent, aJavaClassName, aPackageName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param aJavaClassName
	 * @param aPackageName
	 * @param shouldGenJava
	 */
	public CreateServiceEndpointInterfaceCommand(IRootCommand parent, String aJavaClassName, String aPackageName, boolean shouldGenJava) {
		super(parent, aJavaClassName, aPackageName, shouldGenJava);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param aJavaClassName
	 * @param aPackageName
	 * @param shouldGenJava
	 * @param shouldGenMetadata
	 */
	public CreateServiceEndpointInterfaceCommand(IRootCommand parent, String aJavaClassName, String aPackageName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aJavaClassName, aPackageName, shouldGenJava, shouldGenMetadata);
		// TODO Auto-generated constructor stub
	}

	protected EJBGenerationHelper createCodegenHelper() {
		EJBClassReferenceHelper aHelper = new EJBClassReferenceHelper(getJavaClass());
		aHelper.setServiceEndpointHelper();
		return aHelper;
	}

	/**
	 * Update the remoteInterfaceClass attribute for the EJB.
	 */
	protected void executeForMetadataGeneration() throws CommandExecutionFailure {
		super.executeForMetadataGeneration();
		if (getEjb() == null) {
			failedSettingClass();
			return;
		}
		JavaClass endpoint = getJavaClass();
		if (endpoint == null) {
			((Session) getEjb()).setServiceEndpointName(getQualifiedName());
			setJavaClass(((Session) getEjb()).getServiceEndpoint());
		} else
			((Session) getEjb()).setServiceEndpoint(endpoint);
	}

	/**
	 * Find the original remoteInterface
	 */
	public EObject findOriginalSourceMetaType() {
		Session ejb = (Session) getOriginalEjb();
		if (ejb != null)
			return ejb.getServiceEndpoint();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.EJBCommand#createInverseCodegenHelper()
	 */
	protected EJBGenerationHelper createInverseCodegenHelper() {
		return null;
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		super.setupHelper();
		getHelper().setCreate();
	}
}