/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaEEArtifactClassOperation;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * The NewMessageDrivenBeanClassOperation is an IDataModelOperation following the
 * IDataModel wizard and operation framework.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * 
 * It extends ArtifactEditProviderOperation to provide enterprise bean specific java
 * class generation.
 * @see org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation
 * 
 * This operation is used by the AddMessageDrivenBeanOperation to generate an
 * non annotated java class for an added enterprise bean. It shares the
 * NewMessageDrivenBeanClassDataModelProvider with the AddMessageDrivenBeanOperation to store the
 * appropriate properties required to generate the new message-driven bean.
 * @see org.eclipse.jst.j2ee.ejb.internal.operations.AddMessageDrivenBeanOperation
 * @see org.eclipse.jst.j2ee.ejb.internal.operations.NewMessageDrivenBeanClassDataModelProvider
 * 
 * A WTPJetEmitter bean template is used to create the class with the bean template. 
 * @see org.eclipse.jst.j2ee.internal.project.WTPJETEmitter
 * @see org.eclipse.jst.j2ee.ejb.internal.operations.CreateMessageDrivenBeanTemplateModel
 * 
 * Subclasses may extend this operation to provide their own specific bean
 * java class generation. The execute method may be extended to do so. Also,
 * generateUsingTemplates is exposed.
 * 
 */
public abstract class NewEnterpriseBeanClassOperation extends NewJavaEEArtifactClassOperation {

	/**
	 * Constructor taking an IDataModel
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * @see NewEnterpriseBeanClassOperation
	 * 
	 * @param dataModel
	 * @return NewEnterpriseBeanClassOperation
	 */
	public NewEnterpriseBeanClassOperation(IDataModel dataModel) {
		super(dataModel);
	}

	protected IFile createJavaFile(IProgressMonitor monitor, IPackageFragment fragment, String source, String localBeanName) throws JavaModelException {
		if (fragment != null) {
			ICompilationUnit cu = fragment.getCompilationUnit(localBeanName);
			// Add the compilation unit to the java file
			if (cu == null || !cu.exists())
				cu = fragment.createCompilationUnit(localBeanName, source,
						true, monitor);
			return (IFile) cu.getResource();
		}
		return null;
	}
	
}
