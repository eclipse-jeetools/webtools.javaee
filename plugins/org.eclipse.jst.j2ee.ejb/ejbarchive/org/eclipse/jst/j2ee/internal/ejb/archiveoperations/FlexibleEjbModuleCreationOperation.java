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
 * Created on Nov 6, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class FlexibleEjbModuleCreationOperation extends FlexibleJ2EEModuleCreationOperation {
	public FlexibleEjbModuleCreationOperation(FlexibleEjbModuleCreationDataModel dataModel) {
		super(dataModel);
	}

	public FlexibleEjbModuleCreationOperation() {
		super();
	}

	
	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		super.execute( IModuleConstants.JST_EJB_MODULE, monitor );

	}

	protected  void addResources( WorkbenchComponent component ){
		addResource(component, getModuleRelativeFile(getWebContentSourcePath( getModuleName() ), getProject()), getWebContentDeployPath());
		addResource(component, getModuleRelativeFile(getJavaSourceSourcePath( getModuleName() ), getProject()), getJavaSourceDeployPath());		
	}
	
	/**
	 * @return
	 */
	public String getJavaSourceSourcePath(String moduleName) {
		return "/" + moduleName +"/JavaSource"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getJavaSourceDeployPath() {
		return "/WEB-INF/classes"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getWebContentSourcePath(String moduleName) {
		return "/" + moduleName + "/WebContent"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getWebContentDeployPath() {
		return "/"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation#createProjectStructure()
	 */
	protected void createProjectStructure() throws CoreException {
		// TODO Auto-generated method stub
		
	} 
	
}