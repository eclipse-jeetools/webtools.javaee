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
package org.eclipse.jst.j2ee.internal.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;

/**
 * Insert the type's description here. Creation date: (06/13/01 10:27:16 AM)
 * 
 * @author: Administrator
 */
public class J2EECompoundCommand extends CompoundCommand {
	/**
	 * J2EECompoundCommand constructor comment.
	 */
	public J2EECompoundCommand() {
		super();
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param resultIndex
	 *            int
	 */
	public J2EECompoundCommand(int resultIndex) {
		super(resultIndex);
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param resultIndex
	 *            int
	 * @param label
	 *            java.lang.String
	 */
	public J2EECompoundCommand(int resultIndex, String label) {
		super(resultIndex, label);
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param resultIndex
	 *            int
	 * @param label
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 */
	public J2EECompoundCommand(int resultIndex, String label, String description) {
		super(resultIndex, label, description);
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param resultIndex
	 *            int
	 * @param label
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 * @param commandList
	 *            java.util.List
	 */
	public J2EECompoundCommand(int resultIndex, String label, String description, java.util.List commandList) {
		super(resultIndex, label, description, commandList);
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param resultIndex
	 *            int
	 * @param label
	 *            java.lang.String
	 * @param commandList
	 *            java.util.List
	 */
	public J2EECompoundCommand(int resultIndex, String label, java.util.List commandList) {
		super(resultIndex, label, commandList);
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param resultIndex
	 *            int
	 * @param commandList
	 *            java.util.List
	 */
	public J2EECompoundCommand(int resultIndex, java.util.List commandList) {
		super(resultIndex, commandList);
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 */
	public J2EECompoundCommand(String label) {
		super(label);
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 */
	public J2EECompoundCommand(String label, String description) {
		super(label, description);
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 * @param commandList
	 *            java.util.List
	 */
	public J2EECompoundCommand(String label, String description, java.util.List commandList) {
		super(label, description, commandList);
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param commandList
	 *            java.util.List
	 */
	public J2EECompoundCommand(String label, java.util.List commandList) {
		super(label, commandList);
	}

	/**
	 * J2EECompoundCommand constructor comment.
	 * 
	 * @param commandList
	 *            java.util.List
	 */
	public J2EECompoundCommand(java.util.List commandList) {
		super(commandList);
	}

	protected Collection getMergedAffectedObjectsCollection() {
		J2EEClipboard result = new J2EEClipboard(new ArrayList());

		for (Iterator commands = commandList.iterator(); commands.hasNext();) {
			Command command = (Command) commands.next();
			result.addAll(command.getAffectedObjects());
		}

		return result;
	}

	protected Collection getMergedResultCollection() {
		J2EEClipboard result = new J2EEClipboard(new ArrayList());

		for (Iterator commands = commandList.iterator(); commands.hasNext();) {
			Command command = (Command) commands.next();
			result.addAll(command.getResult());
		}

		return result;
	}
}