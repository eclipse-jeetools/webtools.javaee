/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.provider;


import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jst.j2ee.internal.command.J2EEClipboard;
import org.eclipse.jst.j2ee.internal.command.J2EECopyToClipboardOverrideCommand;
import org.eclipse.jst.j2ee.internal.command.J2EEPasteFromClipboardOverrideCommand;
import org.eclipse.jst.j2ee.internal.command.J2EERemoveOverrideCommand;


public class J2EEUIEditingDomain extends J2EEEditingDomain {
	/**
	 * J2EEUIEditingDomain constructor comment.
	 * 
	 * @param adapterFactory
	 *            org.eclipse.emf.common.notify.AdapterFactory
	 * @param commandStack
	 *            CommandStack
	 */
	public J2EEUIEditingDomain(org.eclipse.emf.common.notify.AdapterFactory adapterFactory, CommandStack commandStack) {
		super(adapterFactory, commandStack);
		turnTraceOnIfDebugging();
	}

	protected Command createCopyToClipboardOverrideCommand(CopyToClipboardCommand copyToClipboardCommand) {

		if (copyToClipboardCommand instanceof J2EECopyToClipboardOverrideCommand)
			return null;
		return new J2EECopyToClipboardOverrideCommand(copyToClipboardCommand);
	}

	protected Command createPasteFromClipboardOverrideCommand(PasteFromClipboardCommand pasteFromClipboardCommand) {
		if (pasteFromClipboardCommand instanceof J2EEPasteFromClipboardOverrideCommand)
			return null;
		if (!(getClipboard() instanceof J2EEClipboard))
			return null;
		return new J2EEPasteFromClipboardOverrideCommand(pasteFromClipboardCommand);
	}

	protected Command createRemoveOverrideCommand(RemoveCommand removeCommand) {
		return new J2EERemoveOverrideCommand(removeCommand);
	}

	public J2EEClipboard getJ2EEClipboard() {
		return (J2EEClipboard) getClipboard();
	}

	public Object getParent(Object object) {
		Object parent = super.getParent(object);
		if (parent != null)
			return parent;
		if (object instanceof EObject)
			return ((EObject) object).eContainer();
		return null;
	}

	protected void turnTraceOnIfDebugging() {
		//AbstractCommand.Trace.enable();
	}
}