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
 * Created on Jun 7, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.ejb.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBCommandHelper;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBCompoundRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;


/**
 * @author dfholt
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class RemoveRelationshipDataModelOperation extends EditModelOperation {
	private boolean genJava = true;

	/**
	 * @param dataModel
	 */
	public RemoveRelationshipDataModelOperation(RemoveRelationshipDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		List list = ((List) operationDataModel.getProperty(RemoveRelationshipDataModel.COMMON_RELATIONSHIP_LIST));
		//the reason we are creating a tempList here is because the original list changes where the
		// relationships are
		//are deleted and cannot be used for iteration.
		List tempList = new ArrayList();
		tempList.addAll(list);
		if (!tempList.isEmpty()) {
			CommonRelationship rel = null;
			IEJBCommand command = null;
			IRootCommand merged = null;
			for (int i = 0; i < tempList.size(); i++) {
				rel = (CommonRelationship) tempList.get(i);
				if (genJava)
					command = EJBCommandHelper.createDeleteEjbRelationshipCommand(rel, (EJBEditModel) editModel);
				else
					command = EJBCommandHelper.createRemoveEjbRelationshipCommand(rel, (EJBEditModel) editModel);
				if (merged == null) {
					if (command != null) {
						if (command.isRootCommand())
							merged = (IRootCommand) command;
						else {
							EJBCompoundRootCommand compound = new EJBCompoundRootCommand(null);
							compound.setEditModel((EJBEditModel) editModel);
							compound.append(command);
							merged = compound;
						}
					}
				} else {
					if (command != null) {
						if (command.isRootCommand())
							merged.append((IRootCommand) command);
						else
							merged.append(command);
					}
				}
			}
			if (merged != null) {
				if (merged.isRootCommand())
					merged.setProgressMonitor(monitor);
				try {
					getCommandStack().execute(merged);
				} catch (Exception e) {
					throw new CoreException(J2EEPlugin.newErrorStatus(errorMessage(), e));
				} finally {
					monitor.done();
				}
			}
		}
	}

	/**
	 * Return a String for error message if the operation fails.
	 */
	protected String errorMessage() {
		return null;
	}

	/**
	 * Sets the genJava.
	 * 
	 * @param genJava
	 *            The genJava to set
	 */
	public void setGenJava(boolean genJava) {
		this.genJava = genJava;
	}

}