/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;



import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMapping;


public class AddModuleMapCommand extends AbstractCommand {
	protected EAREditModel editModel;
	protected Module module;
	protected IProject project;
	protected ModuleMapping map;

	/**
	 * AddModuleMapCommand constructor comment.
	 */
	protected AddModuleMapCommand() {
		super();
	}

	/**
	 * AddModuleMapCommand constructor comment.
	 */
	public AddModuleMapCommand(EAREditModel earEditModel, Module aModule, IProject aProject) {
		this(EARCreationResourceHandler.getString("Set_project_UI_")); //$NON-NLS-1$
		editModel = earEditModel;
		module = aModule;
		project = aProject;
	}

	/**
	 * AddModuleMapCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 */
	protected AddModuleMapCommand(String label) {
		super(label);
	}

	/**
	 * AddModuleMapCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 */
	protected AddModuleMapCommand(String label, String description) {
		super(label, description);
	}

	public boolean canUndo() {
		return map != null;
	}

	/**
	 * This will perform the command activity required for the effect. The effect of calling execute
	 * when canExecute returns false, or when canExecute hasn't been called, is undefined.
	 */
	public void execute() {
		map = editModel.addModuleMapping(module, project);
	}

	protected boolean prepare() {
		return true;
	}

	/**
	 * This will again perform the command activity required to redo the effect after undoing the
	 * effect. The effect, if any, of calling redo before undo is called is undefined. Note that if
	 * you implement redo to call execute then any derived class will be restricted to by that
	 * decision also.
	 */
	public void redo() {
		execute();
	}

	public void undo() {
		editModel.getModuleMappings().remove(map);
	}
}