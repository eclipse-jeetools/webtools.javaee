/*******************************************************************************
 * Copyright (c) 2007, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.PROJECT;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.SUPERCLASS;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AbstractSupertypesValidator {
	
	private static ITypeHierarchy typeHierarchyCache = null;
	
	protected static String getSuperclass(IDataModel dataModel) {
		return dataModel.getStringProperty(SUPERCLASS);
	}
	
	protected static List getInterfaces(IDataModel dataModel) {
		return (List) dataModel.getProperty(INTERFACES);
	}
	
	protected static IJavaProject getJavaProject(IDataModel dataModel) {
		return JavaCore.create((IProject) dataModel.getProperty(PROJECT));
	}
	
	protected static boolean hasSuperclass(IDataModel dataModel,
			String typeName, String superTypeName) {
		try {
			IType type = getJavaProject(dataModel).findType(typeName);
			if (type != null) {
				ITypeHierarchy typeHierarchy = getTypeHierarchy(type);
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
	
	protected static boolean hasSuperInterface(IDataModel dataModel,
			String typeName, String superTypeName) {
		try {
			IType type = getJavaProject(dataModel).findType(typeName);
			if (type != null) {
				ITypeHierarchy typeHierarchy = getTypeHierarchy(type);
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
	
	private static ITypeHierarchy getTypeHierarchy(IType type) throws JavaModelException {
		if (typeHierarchyCache == null || !type.equals(typeHierarchyCache.getType())) {
			typeHierarchyCache = type.newTypeHierarchy(null);
		}
		return typeHierarchyCache;
	}

}
