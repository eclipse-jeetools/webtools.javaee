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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.RemoveCommand;

/**
 * Insert the type's description here. Creation date: (06/07/01 10:44:02 AM)
 * 
 * @author: Administrator
 */
public class J2EERemoveOverrideCommand extends AbstractOverrideCommand {
	private RemoveCommand bindingsRemoveCommand;
	private RemoveCommand extensionsRemoveCommand;
	private ResourceSet resourceSet;

	/**
	 * J2EERemoveOverrideCommand constructor comment.
	 */
	protected J2EERemoveOverrideCommand() {
		super();
	}

	public J2EERemoveOverrideCommand(RemoveCommand command) {
		super(command);
	}

	protected RemoveCommand createRemoveCommand(Collection elements) {
		return (RemoveCommand) RemoveCommand.create(getDomain(), elements);
	}

	/**
	 * This will perform the command activity required for the effect. The effect of calling execute
	 * when canExecute returns false, or when canExecute hasn't been called, is undefined.
	 */
	public void execute() {
		/*
		 * For each object being removed, check if it has a binding, and an extension Make
		 * collections of these, make a command parameter for each collection, an instantiate a
		 * remove command
		 */

		Collection objects = getRemoveCommand().getCollection();
		List bindings = new ArrayList(objects.size());
		List extensions = new ArrayList(objects.size());
		Iterator it = objects.iterator();
		while (it.hasNext()) {
			EObject o = (EObject) it.next();
			if (resourceSet == null)
				resourceSet = o.eResource().getResourceSet();
			// TODO make command adaptable
			//		EObject binding = BindingAndExtensionHelper.getBinding(o);
			//		if (binding != null) {
			//			bindings.add(binding);
			//			getJ2eeClipboard().addBinding(o, binding);
			//		}
			//		EObject extension = BindingAndExtensionHelper.getExtension(o);
			//		if (extension != null) {
			//			extensions.add(extension);
			//			getJ2eeClipboard().addExtension(o, extension);
			//		}
		}
		if (!bindings.isEmpty())
			setBindingsRemoveCommand(createRemoveCommand(bindings));

		if (!extensions.isEmpty())
			setExtensionsRemoveCommand(createRemoveCommand(extensions));

		executeAllChildren();
	}

	protected void executeAllChildren() {
		executeNested(bindingsRemoveCommand);
		executeNested(extensionsRemoveCommand);
		getOverridable().doExecute();
	}

	protected void executeNested(RemoveCommand cmd) {
		if (cmd != null && cmd.doCanExecute()) {
			cmd.doExecute();
			//		Collection result = cmd.getResult();
			//TODO
			//		BindingAndExtensionHelper.resolveAllProxies(result, resourceSet);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (06/07/01 1:32:44 PM)
	 * 
	 * @return RemoveCommand
	 */
	protected RemoveCommand getBindingsRemoveCommand() {
		return bindingsRemoveCommand;
	}

	/**
	 * Insert the method's description here. Creation date: (06/07/01 1:32:44 PM)
	 * 
	 * @return RemoveCommand
	 */
	protected RemoveCommand getExtensionsRemoveCommand() {
		return extensionsRemoveCommand;
	}

	public RemoveCommand getRemoveCommand() {
		return (RemoveCommand) getOverridable();
	}

	protected boolean prepare() {
		setJ2eeClipboard(new J2EEClipboard(getRemoveCommand().getCollection()));
		return true;
	}

	/**
	 * This will again perform the command activity required to redo the effect after undoing the
	 * effect. The effect, if any, of calling redo before undo is called is undefined. Note that if
	 * you implement redo to call execute then any derived class will be restricted to by that
	 * decision also.
	 */
	public void redo() {
		executeAllChildren();
	}

	/**
	 * Insert the method's description here. Creation date: (06/07/01 1:32:44 PM)
	 * 
	 * @param newBindingsRemoveCommand
	 *            RemoveCommand
	 */
	protected void setBindingsRemoveCommand(RemoveCommand newBindingsRemoveCommand) {
		bindingsRemoveCommand = newBindingsRemoveCommand;
	}

	/**
	 * Insert the method's description here. Creation date: (06/07/01 1:32:44 PM)
	 * 
	 * @param newExtensionsRemoveCommand
	 *            RemoveCommand
	 */
	protected void setExtensionsRemoveCommand(RemoveCommand newExtensionsRemoveCommand) {
		extensionsRemoveCommand = newExtensionsRemoveCommand;
	}

	public void undo() {
		getRemoveCommand().doUndo();
		undoNested(bindingsRemoveCommand);
		undoNested(extensionsRemoveCommand);
	}

	protected void undoNested(RemoveCommand cmd) {
		if (cmd != null && cmd.doCanUndo())
			cmd.doUndo();
	}
}