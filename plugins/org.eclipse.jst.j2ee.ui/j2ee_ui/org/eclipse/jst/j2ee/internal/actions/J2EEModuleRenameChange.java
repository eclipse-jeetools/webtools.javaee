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
/*
 * Created on Sep 26, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.internal.dialogs.J2EERenameUIConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.rename.RenameModuleOperation;
import org.eclipse.jst.j2ee.internal.rename.RenameOptions;
import org.eclipse.jst.j2ee.internal.rename.RenameUtilityJarMetadataOperation;
import org.eclipse.jst.j2ee.internal.rename.UpdateWebContextRootMetadataOperation;
import org.eclipse.jst.j2ee.internal.web.operations.WebNatureRuntimeUtilities;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.wst.web.internal.operation.IBaseWebNature;


/**
 * @author mdelder
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class J2EEModuleRenameChange extends Change {

	private String newName;
	private IProject target;
	private boolean renameDependencies;

	public J2EEModuleRenameChange(IProject target, String newName, boolean renameDependencies) {
		this.target = target;
		this.newName = newName;
		this.renameDependencies = renameDependencies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	public String getName() {
		return J2EERenameUIConstants.RENAME_MODULES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.Change#initializeValidationData(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void initializeValidationData(IProgressMonitor pm) {
		//Do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.Change#isValid(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		if (target != null)
			return RefactoringStatus.create(Status.OK_STATUS);
		return RefactoringStatus.create(new Status(IStatus.ERROR, J2EEUIPlugin.PLUGIN_ID, 0, "", null)); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Change perform(IProgressMonitor pm) throws CoreException {
		try {

			RenameModuleOperation renameOp = new RenameModuleOperation(getRenameOptions());
			renameOp.run(pm);

			IProject newTarget = ResourcesPlugin.getWorkspace().getRoot().getProject(this.newName);
			if (newTarget.isAccessible()) {
				IBaseWebNature webNature = WebNatureRuntimeUtilities.getRuntime(newTarget);
				if (webNature != null) {
					new UpdateWebContextRootMetadataOperation(newTarget, webNature.getContextRoot()).run(pm);
				} else if (J2EENature.getRegisteredRuntime(newTarget) == null)
					new RenameUtilityJarMetadataOperation(this.target, newTarget).run(pm);
			}

		} catch (InvocationTargetException e) {
			//Ignore
		} catch (InterruptedException e) {
			//Ignore
		}
		return null;
	}

	/**
	 * @return
	 */
	private RenameOptions getRenameOptions() {
		RenameOptions options = new RenameOptions();
		options.setNewName(this.newName);
		options.setSelectedProjects(Collections.singletonList(this.target));
		options.setIsEARRename(EARNatureRuntime.getRuntime(this.target) != null);
		options.setRenameModuleDependencies(this.renameDependencies);
		options.setRenameModules(true);
		options.setRenameProjects(false);
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.Change#getModifiedElement()
	 */
	public Object getModifiedElement() {
		return null;
	}

}