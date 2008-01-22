/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AbstractSupertypesValidator {
	
	private IDataModel dataModel;
	
	private String superclass;
	private List interfaces;
	private IJavaProject javaProject;
	
	public AbstractSupertypesValidator(IDataModel dataModel) {
		this.dataModel = dataModel;
	}
	
	protected String getSuperclass() {
		if (superclass == null) {
			superclass = dataModel.getStringProperty(INewServletClassDataModelProperties.SUPERCLASS);
		}
		return superclass;
	}
	
	protected List getInterfaces() {
		if (interfaces == null) {
			interfaces = (List) dataModel.getProperty(INewServletClassDataModelProperties.INTERFACES);
		}
		return interfaces;
	}
	
	protected IJavaProject getJavaProject() {
		if (javaProject == null) {
			javaProject = JavaCore.create((IProject) dataModel.getProperty(
					INewJavaClassDataModelProperties.PROJECT));
		}
		return javaProject;
	}
	
	protected boolean hasSuperclass(String typeName, String superTypeName) {
		try {
			IType type = getJavaProject().findType(typeName);
			if (type != null) {
				ITypeHierarchy typeHierarchy = type.newTypeHierarchy(new NullProgressMonitor());
				for (IType superType : typeHierarchy.getAllSuperclasses(type)) {
					if (superTypeName.equals(superType.getFullyQualifiedName()))
						return true;
				}
			}
		} catch (JavaModelException e) {
			WebPlugin.log(e);
		}
		
		return false;
	}
	
	protected boolean hasSuperInterface(String typeName, String superTypeName) {
		try {
			IType type = getJavaProject().findType(typeName);
			if (type != null) {
				ITypeHierarchy typeHierarchy = type.newTypeHierarchy(new NullProgressMonitor());
				for (IType superType : typeHierarchy.getAllSuperInterfaces(type)) {
					if (superTypeName.equals(superType.getFullyQualifiedName()))
						return true;
				}
			}
		} catch (JavaModelException e) {
			WebPlugin.log(e);
		}
		
		return false;
	}

}
