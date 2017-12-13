/*******************************************************************************
 * Copyright (c) 2011 Oracle and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Ludovic Champenois ludo@java.net
 *******************************************************************************/

package org.eclipse.jst.j2ee.ejb.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;

@SuppressWarnings("restriction")
public class AddEjbTimerClassOperation extends NewEnterpriseBeanClassOperation {

	/**
	 * folder location of the EJB Timer creation templates directory
	 */
	protected static final String TEMPLATE_FILE = "/templates/ejbtimer.javajet"; //$NON-NLS-1$

	public AddEjbTimerClassOperation(IDataModel dataModel) {
		super(dataModel);
	}

	@Override
	protected void generateUsingTemplates(IProgressMonitor monitor,
			IPackageFragment fragment) throws WFTWrappedException,
			CoreException {
		// Create the enterprise bean template model
		AddEjbTimerTemplateModel tempModel = new AddEjbTimerTemplateModel(model);
		// Using the WTPJetEmitter, generate the java source based on the bean
		// template model
		try {
			if (fragment != null) {
				// Create the EJB Timer java file
				EjbTimerTemplate tempImpl = EjbTimerTemplate.create(null);

				try {
					Method method = tempImpl.getClass().getMethod("generate", //$NON-NLS-1$
							new Class[] { Object.class });
					String source = (String) method.invoke(tempImpl, tempModel);
					String javaFileName = tempModel.getClassName() + ".java"; //$NON-NLS-1$
					createJavaFile(monitor, fragment, source, javaFileName);
				} catch (SecurityException e) {
					throw new JETException(e);
				} catch (NoSuchMethodException e) {
					throw new JETException(e);
				} catch (IllegalArgumentException e) {
					throw new JETException(e);
				} catch (IllegalAccessException e) {
					throw new JETException(e);
				} catch (InvocationTargetException e) {
					throw new JETException(e);
				}
			}
		} catch (Exception e) {
			throw new WFTWrappedException(e);
		}
	}
}
