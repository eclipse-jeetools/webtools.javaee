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
package org.eclipse.jst.j2ee.ejb.internal.operations;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBCommandHelper;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBCompoundRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;

/**
 * Insert the type's description here. Creation date: (7/11/2001 10:21:18 AM)
 * 
 * @author: Administrator
 */
public class DeleteEjbRelationshipOperation extends org.eclipse.jst.j2ee.internal.ejb.creation.EjbModificationOperation {
	private List relationships;
	private boolean genJava = true;

	/**
	 * Insert the method's description here. Creation date: (6/18/2001 9:57:35 AM)
	 */
	public DeleteEjbRelationshipOperation(EJBEditModel anEditModel, CommonRelationship aRelationship, IOperationHandler anOperationHandler) {
		super(anEditModel, anOperationHandler);
		List list = new ArrayList(1);
		list.add(aRelationship);
		setRelationships(list);
	}

	/**
	 * Insert the method's description here. Creation date: (6/18/2001 9:57:35 AM)
	 */
	public DeleteEjbRelationshipOperation(EJBEditModel anEditModel, List someRelationships, IOperationHandler anOperationHandler) {
		super(anEditModel, anOperationHandler);
		setRelationships(someRelationships);
	}

	/**
	 * createCommand method comment.
	 */
	protected IEJBCommand createCommand() {
		List list = getRelationships();
		CommonRelationship rel;
		IEJBCommand command;
		IRootCommand merged = null;
		for (int i = 0; i < list.size(); i++) {
			rel = (CommonRelationship) list.get(i);
			if (genJava)
				command = EJBCommandHelper.createDeleteEjbRelationshipCommand(rel, getEditModel());
			else
				command = EJBCommandHelper.createRemoveEjbRelationshipCommand(rel, getEditModel());
			if (merged == null) {
				if (command.isRootCommand())
					merged = (IRootCommand) command;
				else {
					EJBCompoundRootCommand compound = new EJBCompoundRootCommand(null);
					compound.setEditModel(getEditModel());
					compound.append(command);
					merged = compound;
				}
			} else {
				if (command.isRootCommand())
					merged.append((IRootCommand) command);
				else
					merged.append(command);
			}
		}
		return merged;
	}

	/**
	 * Return a String for error message if the operation fails.
	 */
	protected String errorMessage() {
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (7/11/2001 10:28:42 AM)
	 * 
	 * @return java.util.List
	 */
	protected java.util.List getRelationships() {
		return relationships;
	}

	/**
	 * Insert the method's description here. Creation date: (7/11/2001 10:28:42 AM)
	 * 
	 * @param newRelationships
	 *            java.util.List
	 */
	protected void setRelationships(java.util.List newRelationships) {
		relationships = newRelationships;
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