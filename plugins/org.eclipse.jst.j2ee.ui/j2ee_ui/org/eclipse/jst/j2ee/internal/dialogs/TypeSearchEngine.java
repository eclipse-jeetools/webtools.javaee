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
package org.eclipse.jst.j2ee.internal.dialogs;

import java.util.HashSet;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;

public class TypeSearchEngine extends SearchEngine {

	/**
	 * Constructor for TypeSearchEngine.
	 */
	public TypeSearchEngine() {
		super();
	}

	/**
	 * Constructor for TypeSearchEngine.
	 * 
	 * @param workingCopies
	 */
	public TypeSearchEngine(ICompilationUnit[] workingCopies) {
		super(workingCopies);
	}

	/**
	 * Method createJavaSearchScopeForAProject. Given a project it returns the
	 * scope of the classes within the project's scope
	 * 
	 * @param project
	 * @param includeReferencedProjects
	 * @param includeExportedClassPathEntriesOnly
	 * @return IJavaSearchScope
	 */
	public static IJavaSearchScope createJavaSearchScopeForAProject(IJavaProject project, boolean includeReferencedProjects, boolean includeExportedClassPathEntriesOnly) {
		if (!includeExportedClassPathEntriesOnly) {
			IJavaElement javaElements[] = new IJavaElement[]{project};
			return SearchEngine.createJavaSearchScope(javaElements, includeReferencedProjects);
		}
		TypeJavaSearchScope scope = new TypeJavaSearchScope();
		scope.setIncludeExportedClassPathEntriesOnly(true);
		HashSet visitedProjects = new HashSet(2);
		try {
			scope.addProject(project, includeReferencedProjects, visitedProjects);
		} catch (JavaModelException e) {
			// ignore
		}
		return scope;
	}

}
