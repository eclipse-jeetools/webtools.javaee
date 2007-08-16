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


import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.jst.j2ee.common.internal.util.IDUtility;
import org.eclipse.jst.j2ee.internal.provider.J2EEUIEditingDomain;


public class J2EEPasteFromClipboardOverrideCommand extends PasteFromClipboardCommand {
	private J2EECopyFromClipboardCommand copyCommand;
	private Command addBindingsCommand;
	private Command addExtensionsCommand;

	public J2EEPasteFromClipboardOverrideCommand(PasteFromClipboardCommand p) {
		super(p.getDomain(), p.getOwner(), p.getFeature(), p.getIndex(), false);
	}

	public void doExecute() {
		super.doExecute();
		executeAddBindings();
		executeAddExtensions();
		J2EEClipboard result = (J2EEClipboard) doGetResult();
		for (int i = 0; i < result.size(); i++) {
			EObject o = (EObject) result.get(i);
			if (result.getBinding(o) != null || result.getExtension(o) != null)
				IDUtility.setDefaultID(o, true);
		}
	}

	public Collection doGetAffectedObjects() {
		return copyCommand.getAffectedObjects();
	}

	public Collection doGetResult() {
		return copyCommand.getResult();
	}

	public void doRedo() {
		super.doRedo();
		if (addBindingsCommand != null)
			addBindingsCommand.redo();
		if (addExtensionsCommand != null)
			addExtensionsCommand.redo();
	}

	public void doUndo() {
		super.doUndo();
		if (addBindingsCommand != null)
			addBindingsCommand.undo();
		if (addExtensionsCommand != null)
			addExtensionsCommand.undo();
	}

	protected void executeAddBindings() {
		if (addBindingsCommand != null && addBindingsCommand.canExecute())
			addBindingsCommand.execute();
	}

	protected void executeAddExtensions() {
		if (addExtensionsCommand != null && addExtensionsCommand.canExecute())
			addExtensionsCommand.execute();
	}

	public J2EEClipboard getCopiedClipoard() {
		return (J2EEClipboard) copyCommand.getResult();
	}

	protected J2EEClipboard getJ2EEClipboard() {
		return (J2EEClipboard) domain.getClipboard();
	}

	protected boolean prepare() {
		if (getJ2EEClipboard() == null)
			return false;
		command = new StrictCompoundCommand();

		copyCommand = new J2EECopyFromClipboardCommand((J2EEUIEditingDomain) domain);
		command.append(copyCommand);

		command.append(new CommandWrapper() {
			protected Command createCommand() {
				Command addCommand = AddCommand.create(getDomain(), getOwner(), getFeature(), copyCommand.getResult(), getIndex());
				return addCommand;
			}
		});
		prepareBindingCommand(copyCommand);
		prepareExtensionCommand(copyCommand);

		boolean result;
		if (optimize) {
			// This will determine canExecute as efficiently as possible.
			//
			result = optimizedCanExecute();
		} else {
			// This will actually execute the copy command in order to check if the add can execute.
			//
			result = command.canExecute();
		}

		return result;
	}

	protected void prepareBindingCommand(final J2EECopyFromClipboardCommand cmd) {
		if (!getJ2EEClipboard().hasBindings())
			return;
		//TODO make adaptable command
		//	addBindingsCommand = new CommandWrapper() {
		//		protected Command createCommand() {
		//			Object bindingOwner = BindingAndExtensionHelper.getBindingAddOwner((EObject)getOwner());
		//			Collection bindingsCopies = getCopiedClipoard().getBindings().values();
		//			Command addCommand = AddCommand.create(getDomain(), bindingOwner, null, bindingsCopies,
		// CommandParameter.NO_INDEX);
		//			return addCommand;
		//		}
		//	};
	}

	protected void prepareExtensionCommand(final J2EECopyFromClipboardCommand cmd) {
		if (!getJ2EEClipboard().hasExtensions())
			return;
		// TODO make adaptable command
		//	addExtensionsCommand = new CommandWrapper() {
		//		protected Command createCommand() {
		//			Object extensionOwner =
		// BindingAndExtensionHelper.getExtensionAddOwner((EObject)getOwner());
		//			Collection extensionsCopies = getCopiedClipoard().getExtensions().values();
		//			Command addCommand = AddCommand.create(getDomain(), extensionOwner, null,
		// extensionsCopies, CommandParameter.NO_INDEX);
		//			return addCommand;
		//		}
		//	};
	}
}
