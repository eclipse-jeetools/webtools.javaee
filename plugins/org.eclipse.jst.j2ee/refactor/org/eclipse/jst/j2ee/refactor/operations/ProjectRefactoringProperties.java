/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.refactor.operations;

public interface ProjectRefactoringProperties {

	/**
	 * Name of the property that holds an instance of ProjectRefactorMetadata for the
	 * refactored project.
	 */
	public static final String PROJECT_METADATA = "ProjectRefactoringProperties.PROJECT_METADATA"; //$NON-NLS-1$
	
	/**
	 * Name of the property that holds an instance of ProjectRefactorMetadata for the original version of
	 * the refactored project.
	 */
	public static final String ORIGINAL_PROJECT_METADATA = "ProjectRefactoringProperties.ORIGINAL_PROJECT_METADATA"; //$NON-NLS-1$
	
	/**
	 * Name of the property that holds an instance of ProjectRefactorMetadata for the
	 * dependent project.
	 */
	public static final String DEPENDENT_PROJECT_METADATA = "ProjectRefactoringProperties.DEPENDENT_PROJECT_METADATA"; //$NON-NLS-1$
		
}
