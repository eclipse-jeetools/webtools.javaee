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
    public boolean shouldRefactorDeletedProject(final ProjectRefactorMetadata metadata) {
        return true;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.refactor.operations.IOptionalRefactorHandler#shouldRefactorRenamedProject(org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata)
     */
    public boolean shouldRefactorRenamedProject(final ProjectRefactorMetadata metadata) {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.refactor.operations.IOptionalRefactorHandler#shouldRefactorDependentProjectOnDelete(org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata, org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata)
     */
    public boolean shouldRefactorDependentProjectOnDelete(final ProjectRefactorMetadata deletedMetadata, ProjectRefactorMetadata dependentMetadata) {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.refactor.operations.IOptionalRefactorHandler#shouldRefactorDependentProjectOnRename(org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata, org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata)
     */
    public boolean shouldRefactorDependentProjectOnRename(final ProjectRefactorMetadata renamedMetadata, ProjectRefactorMetadata dependentMetadata) {
        return true;
    }
}
