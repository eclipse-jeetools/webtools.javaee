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
 * Abstract implementation of IOptionalRefactorHandler.
 */
public abstract class AbstractOptionalRefactorHandler implements IOptionalRefactorHandler{
	
	public AbstractOptionalRefactorHandler(){
		
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.refactor.operations.IOptionalRefactorHandler#shouldRefactorDeletedProject(org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata)
     */
    @Override
	public boolean shouldRefactorDeletedProject(final ProjectRefactorMetadata metadata) {
        return true;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.refactor.operations.IOptionalRefactorHandler#shouldRefactorRenamedProject(org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata)
     */
    @Override
	public boolean shouldRefactorRenamedProject(final ProjectRefactorMetadata metadata) {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.refactor.operations.IOptionalRefactorHandler#shouldRefactorDependentProjectOnDelete(org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata, org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata)
     */
    @Override
	public boolean shouldRefactorDependentProjectOnDelete(final ProjectRefactorMetadata deletedMetadata, ProjectRefactorMetadata dependentMetadata) {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.refactor.operations.IOptionalRefactorHandler#shouldRefactorDependentProjectOnRename(org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata, org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata)
     */
    @Override
	public boolean shouldRefactorDependentProjectOnRename(final ProjectRefactorMetadata renamedMetadata, ProjectRefactorMetadata dependentMetadata) {
        return true;
    }
}
