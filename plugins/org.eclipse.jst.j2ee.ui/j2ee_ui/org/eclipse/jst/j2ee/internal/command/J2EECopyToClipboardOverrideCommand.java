/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.command;


import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.wst.common.internal.emf.utilities.EtoolsCopyUtility;


public class J2EECopyToClipboardOverrideCommand extends CopyToClipboardCommand {
	//The collection of source objects, with bindings and extensions, if any exist
	protected J2EEClipboard extendedSourceObjects;
	protected boolean onlyRefObjects = true;

	public J2EECopyToClipboardOverrideCommand(CopyToClipboardCommand cmd) {
		super(cmd.getDomain(), cmd.getSourceObjects());
	}

	protected Command createCopyCommand() {
		CompoundCommand cmd = new J2EECompoundCommand(CompoundCommand.MERGE_COMMAND_ALL);
		Iterator it = extendedSourceObjects.iterator();
		EtoolsCopyUtility copyUtil = new EtoolsCopyUtility();
		while (it.hasNext()) {
			Object o = it.next();
			if (!(o instanceof EObject)) {
				cmd.append(UnexecutableCommand.INSTANCE);
			} else {
				EObject r = (EObject) o;
				cmd.append(new J2EECopyCommand(r, extendedSourceObjects.getBinding(r), extendedSourceObjects.getExtension(r), copyUtil));
			}
		}
		return cmd.unwrap();
	}

	protected boolean prepare() {
		prepareSourceObjects();
		if (!onlyRefObjects) {
			copyCommand = UnexecutableCommand.INSTANCE;
			return copyCommand.canExecute();
		}

		if (!extendedSourceObjects.hasBindings() && !extendedSourceObjects.hasExtensions())
			return super.prepare();

		copyCommand = createCopyCommand();
		return copyCommand.canExecute();
	}

	protected void prepareSourceObjects() {
		extendedSourceObjects = new J2EEClipboard(getSourceObjects());
		Iterator it = getSourceObjects().iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (o instanceof EObject) {
				//			EObject r = (EObject) o;
				// TODO switch to adaptable commands
				//			EObject bnd = BindingAndExtensionHelper.getBinding(r);
				//			EObject ext = BindingAndExtensionHelper.getExtension(r);
				//			if (bnd != null)
				//				extendedSourceObjects.addBinding(r, bnd);
				//			if (ext != null)
				//				extendedSourceObjects.addExtension(r, ext);
			} else {
				//Right now we can only handle ref objects in the tree
				onlyRefObjects = false;
				return;
			}
		}
	}
}
