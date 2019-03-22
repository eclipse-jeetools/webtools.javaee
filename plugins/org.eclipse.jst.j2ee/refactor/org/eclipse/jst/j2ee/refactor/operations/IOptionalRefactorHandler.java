/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.refactor.operations;

/**
 * Interface for classes that compute whether the automatically executed J2EE
 * refactoring logic should be executed when a project is deleted or renamed.
 */
public interface IOptionalRefactorHandler {

    /**
     * Called to determine if automatic refactoring logic should be executed
     * for a given deleted project. The project will already have been deleted.  
     * this method is called.
     * 
     * @param metadata Metadata for the deleted project.
     *  
     * @return True to refactor, false to skip refactoring for all dependent projects.
     */
	public boolean shouldRefactorDeletedProject(ProjectRefactorMetadata metadata);
    
    /**
     * Called to determine if automatic refactoring logic should be executed
     * for a given renamed project. The project will already have been be renamed when
     * this method is called.
     * 
     * @param metadata Metadata for the renamed project (pre-rename).
     *  
     * @return True to refactor, false to skip refactoring for all dependent projects.
     */
    public boolean shouldRefactorRenamedProject(ProjectRefactorMetadata metadata);

    /**
     * Called to determine if automatic refactoring logic should be executed
     * on the specified dependent project when a referenced project has been deleted.
     * The referenced project will already have been deleted when this method is called.
     * 
     * @param deletedMetadata Metadata for the deleted project.
     * @param dependentMetadata Metadata for the dependent project.
     * 
     * @return True to refactor the dependent project.
     */
    public boolean shouldRefactorDependentProjectOnDelete(ProjectRefactorMetadata deletedMetadata, ProjectRefactorMetadata dependentMetadata);

    /**
     * Called to determine if automatic refactoring logic should be executed
     * on the specified dependent project when a referenced project has been renamed.
     * The referenced project will already have been renamed when this method is called.
     * 
     * @param renamedMetadata Metadata for the renamed project.
     * @param dependentMetadata Metadata for the dependent project.
     * 
     * @return True to refactor the dependent project.
     */
    public boolean shouldRefactorDependentProjectOnRename(ProjectRefactorMetadata renamedMetadata, ProjectRefactorMetadata dependentMetadata);
}
