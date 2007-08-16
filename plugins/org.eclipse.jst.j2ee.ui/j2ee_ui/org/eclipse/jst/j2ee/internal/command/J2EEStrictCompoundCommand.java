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

import org.eclipse.emf.common.command.StrictCompoundCommand;



/**
 * Overridden to provide an accessor to the pessimistic field. This is needed for the case of
 * undo/redo paste. We need the nested copy commmand in the paste command to redo itself when the
 * paste command is copied, otherwise we run into sed exception.
 */
public class J2EEStrictCompoundCommand extends StrictCompoundCommand {
	/**
	 * J2EEStrictCompoundCommand constructor comment.
	 */
	public J2EEStrictCompoundCommand() {
		super();
	}

	/**
	 * J2EEStrictCompoundCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 */
	public J2EEStrictCompoundCommand(String label) {
		super(label);
	}

	/**
	 * J2EEStrictCompoundCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 */
	public J2EEStrictCompoundCommand(String label, String description) {
		super(label, description);
	}

	/**
	 * J2EEStrictCompoundCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 * @param commandList
	 *            java.util.List
	 */
	public J2EEStrictCompoundCommand(String label, String description, java.util.List commandList) {
		super(label, description, commandList);
	}

	/**
	 * J2EEStrictCompoundCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param commandList
	 *            java.util.List
	 */
	public J2EEStrictCompoundCommand(String label, java.util.List commandList) {
		super(label, commandList);
	}

	/**
	 * J2EEStrictCompoundCommand constructor comment.
	 * 
	 * @param commandList
	 *            java.util.List
	 */
	public J2EEStrictCompoundCommand(java.util.List commandList) {
		super(commandList);
	}

	/**
	 * J2EEStrictCompoundCommand constructor comment.
	 */
	public J2EEStrictCompoundCommand(boolean pessimistic) {
		super();
		setIsPessismistic(pessimistic);
	}

	public void setIsPessismistic(boolean aBool) {
		isPessimistic = aBool;
	}
}