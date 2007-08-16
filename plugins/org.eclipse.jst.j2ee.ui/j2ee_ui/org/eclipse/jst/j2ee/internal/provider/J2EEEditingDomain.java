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
package org.eclipse.jst.j2ee.internal.provider;


import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.command.CreateCopyCommand;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.OverrideableCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

/**
 * Custom editing domain which provides hooks for creating override commands; should not be used
 * directly but can be subclassed to override commands as necessary. All the override methods by
 * default return null, so a subclass may override only a subset of the commands.
 */
public class J2EEEditingDomain extends AdapterFactoryEditingDomain {
	/**
	 * J2EEEditingDomain constructor comment.
	 * 
	 * @param adapterFactory
	 *            org.eclipse.emf.common.notify.AdapterFactory
	 * @param commandStack
	 *            CommandStack
	 */
	public J2EEEditingDomain(org.eclipse.emf.common.notify.AdapterFactory adapterFactory, CommandStack commandStack) {
		super(adapterFactory, commandStack);
	}

	/**
	 * The default is not to override this command; subclasses can override if necessary
	 */
	protected Command createAddOverrideCommand(AddCommand addCommand) {
		return null;
	}

	/**
	 * The default is not to override this command; subclasses can override if necessary
	 */
	protected Command createCopyToClipboardOverrideCommand(CopyToClipboardCommand copyToClipboardCommand) {
		return null;
	}

	/**
	 * The default is not to override this command; subclasses can override if necessary
	 */
	protected Command createCreateCopyOverrideCommand(CreateCopyCommand createCopyCommand) {
		return null;
	}

	/**
	 * The default is not to override this command; subclasses can override if necessary
	 */
	protected Command createDragAndDropOverrideCommand(DragAndDropCommand dragAndDropCommand) {
		return null;
	}

	/**
	 * The default is not to override this command; subclasses can override if necessary
	 */
	protected Command createInitializeCopyOverrideCommand(InitializeCopyCommand initializeCopyCommand) {
		return null;
	}

	/**
	 * The default is not to override this command; subclasses can override if necessary
	 */
	protected Command createMoveOverrideCommand(MoveCommand moveCommand) {
		return null;
	}

	public Command createOverrideCommand(OverrideableCommand command) {
		if (command instanceof AddCommand) {
			AddCommand addCommand = (AddCommand) command;
			return createAddOverrideCommand(addCommand);
		} else if (command instanceof RemoveCommand) {
			RemoveCommand removeCommand = (RemoveCommand) command;
			return createRemoveOverrideCommand(removeCommand);
		} else if (command instanceof SetCommand) {
			SetCommand setCommand = (SetCommand) command;
			return createSetOverrideCommand(setCommand);
		} else if (command instanceof ReplaceCommand) {
			ReplaceCommand replaceCommand = (ReplaceCommand) command;
			return createReplaceOverrideCommand(replaceCommand);
		} else if (command instanceof MoveCommand) {
			MoveCommand moveCommand = (MoveCommand) command;
			return createMoveOverrideCommand(moveCommand);
		} else if (command instanceof CreateCopyCommand) {
			CreateCopyCommand createCopyCommand = (CreateCopyCommand) command;
			return createCreateCopyOverrideCommand(createCopyCommand);
		} else if (command instanceof InitializeCopyCommand) {
			InitializeCopyCommand initializeCopyCommand = (InitializeCopyCommand) command;
			return createInitializeCopyOverrideCommand(initializeCopyCommand);
		} else if (command instanceof CopyToClipboardCommand) {
			CopyToClipboardCommand copyToClipboardCommand = (CopyToClipboardCommand) command;
			return createCopyToClipboardOverrideCommand(copyToClipboardCommand);
		} else if (command instanceof PasteFromClipboardCommand) {
			PasteFromClipboardCommand pasteFromClipboardCommand = (PasteFromClipboardCommand) command;
			return createPasteFromClipboardOverrideCommand(pasteFromClipboardCommand);
		} else if (command instanceof DragAndDropCommand) {
			DragAndDropCommand dragAndDropCommand = (DragAndDropCommand) command;
			return createDragAndDropOverrideCommand(dragAndDropCommand);
		} else {
			return null;
		}
	}

	/**
	 * The default is not to override this command; subclasses can override if necessary
	 */
	protected Command createPasteFromClipboardOverrideCommand(PasteFromClipboardCommand pasteFromClipboardCommand) {
		return null;
	}

	/**
	 * The default is not to override this command; subclasses can override if necessary
	 */
	protected Command createRemoveOverrideCommand(RemoveCommand removeCommand) {
		return null;
	}

	/**
	 * The default is not to override this command; subclasses can override if necessary
	 */
	protected Command createReplaceOverrideCommand(ReplaceCommand replaceCommand) {
		return null;
	}

	/**
	 * The default is not to override this command; subclasses can override if necessary
	 */
	protected Command createSetOverrideCommand(SetCommand setCommand) {
		return null;
	}
}