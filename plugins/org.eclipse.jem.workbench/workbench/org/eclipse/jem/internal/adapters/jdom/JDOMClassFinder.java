package org.eclipse.jem.internal.adapters.jdom;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JDOMClassFinder.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:33:53 $ 
 */

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.*;
import org.eclipse.jem.internal.java.adapters.nls.ResourceHandler;
/**
 * Insert the type's description here.
 * Creation date: (8/16/2000 11:06:46 PM)
 * @author: Administrator
 */
public class JDOMClassFinder {
	
	private static JDOMClassFinder instance;
/**
 * JDOMClassFinder constructor comment.
 */
public JDOMClassFinder() {
	super();
}
protected IPath getBinaryPathFromQualifiedName(String qualifiedName) {
	return new Path(qualifiedName.replace('.', File.separatorChar) + ".class");//$NON-NLS-1$
}
public IType getBinaryType(String qualifiedName) {
	try {
		IJavaElement found = getJavaElement(qualifiedName);
		return ((IClassFile) found).getType();
	} catch (JavaModelException jme) {
		System.out.println(ResourceHandler.getString("Error_Looking_Up_Type_ERROR_", (new Object[] {qualifiedName, jme.getMessage()}))); //$NON-NLS-1$ = "Error looking up type: "
	}
	return null;
}
public IJavaElement getJavaElement(String qualifiedName) {
	try {
		if (getSourceProject() != null)
			return getSourceProject().findElement(getPathFromQualifiedName(qualifiedName));
	} catch (JavaModelException jme) {
		System.out.println(ResourceHandler.getString("Error_Looking_Up_Type_ERROR_", (new Object[] {qualifiedName, jme.getMessage()}))); //$NON-NLS-1$ = "Error looking up type: "
	}
	return null;
}
protected IPath getPathFromQualifiedName(String qualifiedName) {
	return new Path(qualifiedName.replace('.', File.separatorChar) + ".java");//$NON-NLS-1$
}
protected IJavaProject getSourceProject() {
	//return (IJavaProject) ((JavaRefPackage)EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI)).getJavaRefFactory().getJavaContext();
	return null;
}
public IType getType(String qualifiedName) {
	try {
		IJavaElement found = getJavaElement(qualifiedName);
		if (found != null)
			if (found instanceof IClassFile)
				return ((IClassFile) found).getType();
			else
				if (found instanceof ICompilationUnit) {
					ICompilationUnit foundCU = (ICompilationUnit) found;
					// strip the ".java", lifted from CompilationUnit.getMainTypeName()
					String cuMainTypeName = foundCU.getElementName();
					cuMainTypeName = cuMainTypeName.substring(0, cuMainTypeName.length() - 5);
					return foundCU.getType(cuMainTypeName);
				}
	} catch (JavaModelException jme) {
		System.out.println(ResourceHandler.getString("Error_Looking_Up_Type_ERROR_", (new Object[] {qualifiedName, jme.getMessage()}))); //$NON-NLS-1$ = "Error looking up type: "
	}
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (8/16/2000 11:19:48 PM)
 * @return com.ibm.etools.java.adapters.JDOMClassFinder
 */
public static JDOMClassFinder instance() {
	if (instance == null)
		instance = new JDOMClassFinder();
	return instance;
}
}
