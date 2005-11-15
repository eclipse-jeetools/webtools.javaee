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

package org.eclipse.jst.j2ee.internal.ejb.refactor;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.internal.impl.EJBJarImpl;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata;
import org.eclipse.jst.j2ee.refactor.operations.ProjectRefactoringProperties;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.Property;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;

/**
 * Abstract based class of operations that update the ejb client ref in the ejb-jar.xml file 
 * of a dependent EJB module when the name of the ejb client project is refactored.
 */
public abstract class UpdateEJBClientRefOp extends AbstractDataModelOperation 
	implements ProjectRefactoringProperties {

	public UpdateEJBClientRefOp() {
		super();
	}

	protected IStatus updateEJBClientRef(final IProgressMonitor monitor, final boolean remove) throws ExecutionException {
		
		final ProjectRefactorMetadata dependentMetadata = (ProjectRefactorMetadata) model.getProperty(DEPENDENT_PROJECT_METADATA);
		final ProjectRefactorMetadata refactoredMetadata = (ProjectRefactorMetadata) model.getProperty(PROJECT_METADATA);
		ProjectRefactorMetadata originalMetadata = refactoredMetadata;
		if (!remove) {
			originalMetadata = (ProjectRefactorMetadata) model.getProperty(ORIGINAL_PROJECT_METADATA);
		}

		// the dependent project must be an EJB project and the refactored project must be a utility project
		if (!(dependentMetadata.isEJB() && refactoredMetadata.isUtility())) {
			return Status.OK_STATUS;
		}

		// Update the ejb client jar prop in the ejb-jar.xml
		final String originalProjectName = originalMetadata.getProjectName();
		EJBArtifactEdit ejbEdit = null;
		try{
			ejbEdit = EJBArtifactEdit.getEJBArtifactEditForWrite(dependentMetadata.getProject());
			if(ejbEdit != null) {
				final EJBJarImpl ejbres = (EJBJarImpl) ejbEdit.getDeploymentDescriptorRoot();
				final String ejbClientJar = ejbres.getEjbClientJar();
				if (ejbClientJar != null && ejbClientJar.equals(originalProjectName + ".jar")) { //$NON-NLS-1$
					if (remove) {
						ejbres.setEjbClientJar(null);
					} else {
						final String newProjectName = refactoredMetadata.getProjectName();
						ejbres.setEjbClientJar(newProjectName + ".jar");//$NON-NLS-1$
					}
					ejbEdit.saveIfNecessary(monitor);
				}
			}
		} finally {
			if(ejbEdit != null) {
				ejbEdit.dispose();
			}
		}  
		
		// update the client jar props in the .component file
		StructureEdit core = null;
		try {
			core = StructureEdit.getStructureEditForWrite(dependentMetadata.getProject());
			WorkbenchComponent component = core.getComponent();
			// if the deploy-name had been set to the old project name, update it to 
			// the new project name
			// if there is a context-root property that had been set to the old project name, update it to 
			// the new project name
			List propList = component.getProperties();
            for (int i = 0; i < propList.size(); i++) {
            	final Property prop = (Property) propList.get(i);
            	if (prop.getName().equals(CreationConstants.EJB_CLIENT_NAME) && prop.getValue().equals(originalMetadata.getProjectName())) {
            		propList.remove(i);
            		if (!remove) {
            			final Property newProp = ComponentcoreFactory.eINSTANCE.createProperty();
            			newProp.setName(CreationConstants.EJB_CLIENT_NAME);
            			newProp.setValue(refactoredMetadata.getProjectName());
            			propList.add(0, newProp);
            		} else {
            			i--;
            		}
            	} else if (prop.getName().equals(CreationConstants.CLIENT_JAR_URI) && prop.getValue().equals(originalMetadata.getProjectName() + ".jar")) { //$NON-NLS-1$
            		propList.remove(i);
            		if (!remove) {
            			final Property newProp = ComponentcoreFactory.eINSTANCE.createProperty();
            			newProp.setName(CreationConstants.CLIENT_JAR_URI);
            			newProp.setValue(refactoredMetadata.getProjectName() + ".jar");
            			propList.add(0, newProp);
            		} else {
            			i--;
            		}
            	}
            }
		} finally {
			if(core != null) {
				core.saveIfNecessary(null);
				core.dispose();
			}
		}	
	
		return Status.OK_STATUS;
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
