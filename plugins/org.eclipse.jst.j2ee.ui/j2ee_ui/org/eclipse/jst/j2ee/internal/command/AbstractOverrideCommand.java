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


import java.util.Collection;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Insert the type's description here. Creation date: (06/07/01 10:56:08 AM)
 * 
 * @author: Administrator
 */
public abstract class AbstractOverrideCommand extends AbstractCommand {
	private AbstractOverrideableCommand overridable;
	private J2EEClipboard j2eeClipboard;

	/**
	 * AbstractOverrideCommand constructor comment.
	 */
	protected AbstractOverrideCommand() {
		super();
	}

	public AbstractOverrideCommand(AbstractOverrideableCommand command) {
		super(command.getLabel(), command.getDescription());
		setOverridable(command);
	}

	public boolean canExecute() {
		return super.canExecute() && overridable.doCanExecute();
	}

	public boolean canUndo() {
		return overridable.doCanUndo();
	}

	public Collection getAffectedObjects() {
		return overridable.doGetAffectedObjects();
	}

	public EditingDomain getDomain() {
		return getOverridable().getDomain();
	}

	/**
	 * Insert the method's description here. Creation date: (06/07/01 11:19:27 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.internal.command.J2EEClipboard
	 */
	public J2EEClipboard getJ2eeClipboard() {
		return j2eeClipboard;
	}

	/**
	 * Insert the method's description here. Creation date: (06/07/01 10:58:33 AM)
	 * 
	 * @return AbstractOverrideableCommand
	 */
	public AbstractOverrideableCommand getOverridable() {
		return overridable;
	}

	public Collection getResult() {
		return getJ2eeClipboard();
	}

	/**
	 * Insert the method's description here. Creation date: (06/07/01 11:19:27 AM)
	 * 
	 * @param newJ2eeClipboard
	 *            org.eclipse.jst.j2ee.internal.internal.internal.command.J2EEClipboard
	 */
	protected void setJ2eeClipboard(J2EEClipboard newJ2eeClipboard) {
		j2eeClipboard = newJ2eeClipboard;
	}

	/**
	 * Insert the method's description here. Creation date: (06/07/01 10:58:33 AM)
	 * 
	 * @param AbstractOverrideableCommand
	 */
	protected void setOverridable(AbstractOverrideableCommand newOverridable) {
		overridable = newOverridable;
	}
}