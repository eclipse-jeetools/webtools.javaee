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

import org.eclipse.core.runtime.Path;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Abstract base class for operations that update dependent projects when a 
 * referenced project is refactored.
 */
public abstract class UpdateDependentProjectOp extends AbstractDataModelOperation 
	implements ProjectRefactoringProperties {

	/**
	 * Creates a new refactoring operation for the specified data model.
	 * @param model The data model.
	 */
	public UpdateDependentProjectOp(final IDataModel model) {
		super(model);
	}
	
	/**
	 * Does the dependent project have a .component reference on the refactored project?
	 * @return IVirtualReference or null if one didn't exist.
	 */
	protected static IVirtualReference hadReference(final ProjectRefactorMetadata dependentMetadata,
			final ProjectRefactorMetadata refactoredMetadata) {
		final IVirtualComponent refactoredComp = refactoredMetadata.getVirtualComponent();
		if (refactoredComp == null) {
			return null;
		}
		final IVirtualReference[] refs = dependentMetadata.getVirtualComponent().getReferences();
		IVirtualReference ref = null;
		for (int i = 0; i < refs.length; i++) {
			if (refs[i].getReferencedComponent().equals(refactoredComp)) {
				ref = refs[i];
				break;
			}
		}
		return ref;
	}
	
	/**
	 * Returns true if the dependency was a web library dependency. 
	 * @param ref
	 * @return
	 */
	protected static boolean hasWebLibDependency(final IVirtualReference ref) {
		if (ref == null) {
			return false;
		}
		return ref.getRuntimePath().equals(new Path("/WEB-INF/lib")); //$NON-NLS-1$
	}
	
	/**
	 * Override to disable redo support
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#canRedo()
	 */
	public boolean canRedo() {
		return false;
	}

	/**
	 * Override to disable undo support.
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#canUndo()
	 */
	public boolean canUndo() {
		return false;
	}
	
}
