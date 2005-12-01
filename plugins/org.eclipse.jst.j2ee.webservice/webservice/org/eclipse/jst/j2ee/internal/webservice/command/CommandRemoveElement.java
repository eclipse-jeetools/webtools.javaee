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

package org.eclipse.jst.j2ee.internal.webservice.command;

import java.util.Collection;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class CommandRemoveElement extends AbstractCommand {
	private EObject parent_;
	private Object child_;
	private Object oldChild_;
	private boolean oldChildSet_;
	private EStructuralFeature childFeature_;
	private boolean nillable_;

	public CommandRemoveElement(String label, String description, EObject parent, EStructuralFeature childFeature) {
		this(label, description, parent, childFeature, true);
	}

	public CommandRemoveElement(String label, String description, EObject parent, EStructuralFeature childFeature, boolean nillable) {
		this(label, description, parent, null, childFeature, nillable);
	}

	public CommandRemoveElement(String label, String description, EObject parent, Object child, EStructuralFeature childFeature) {
		this(label, description, parent, child, childFeature, true);
	}

	public CommandRemoveElement(String label, String description, EObject parent, Object child, EStructuralFeature childFeature, boolean nillable) {
		super(label, description);
		parent_ = parent;
		child_ = child;
		oldChild_ = null;
		oldChildSet_ = true;
		childFeature_ = childFeature;
		nillable_ = nillable;
	}

	/**
	 * Called at most once in {@link #canExecute}to give the command an opportunity to ready itself
	 * for execution. The returned value is stored in {@link #canExecute}. In other words, you can
	 * override this method to initialize and to yield a cached value for the all subsequent calls
	 * to canExecute.
	 * 
	 * @return whether the command is executable.
	 */
	protected boolean prepare() {
		return true;
	}

	/**
	 * Returns whether the comamad is valid to <code>execute</code>. The
	 * {@link UnexecutableCommand#INSTANCE}.<code>canExecute()</code> always returns
	 * <code>false</code>. This <b>must </b> be called before calling <code>execute</code>.
	 * 
	 * @return whether the comamad is valid to <code>execute</code>.
	 */
	public boolean canExecute() {
		return super.canExecute();
	}

	/**
	 * Performs the command activity required for the effect. The effect of calling
	 * <code>execute</code> when <code>canExecute</code> returns <code>false</code>, or when
	 * <code>canExecute</code> hasn't been called, is undefined.
	 */
	public void execute() {
		if (childFeature_.isMany()) {
			if (child_ != null)
				((EList) parent_.eGet(childFeature_)).remove(child_);
		} else {
			oldChild_ = parent_.eGet(childFeature_);
			oldChildSet_ = parent_.eIsSet(childFeature_);
			if (nillable_)
				parent_.eUnset(childFeature_);
			else
				parent_.eSet(childFeature_, null);
		}
	}

	/**
	 * Returns <code>true</code> because most command should be undoable.
	 * 
	 * @return <code>true</code>.
	 */
	public boolean canUndo() {
		return true;
	}

	/**
	 * Performs the command activity required to <code>undo</code> the effects of a preceding
	 * <code>execute</code> (or <code>redo</code>). The effect, if any, of calling
	 * <code>undo</code> before <code>execute</code> or <code>redo</code> have been called, or
	 * when canUndo returns <code>false</code>, is undefined.
	 */
	public void undo() {
		if (childFeature_.isMany()) {
			if (child_ != null)
				((EList) parent_.eGet(childFeature_)).add(child_);
		} else {
			if (oldChildSet_)
				parent_.eSet(childFeature_, oldChild_);
			else
				parent_.eUnset(childFeature_);
		}
	}

	/**
	 * Performs the command activity required to <code>redo</code> the effect after undoing the
	 * effect. The effect, if any, of calling <code>redo</code> before <code>undo</code> is
	 * called is undefined. Note that if you implement <code>redo</code> to call
	 * <code>execute</code> then any derived class will be restricted by that decision also.
	 */
	public void redo() {
		execute();
	}

	/**
	 * Returns a collection of things which this command wishes to present as it's result. The
	 * result of calling this before an <code>execute</code> or <code>redo</code>, or after an
	 * <code>undo</code>, is undefined.
	 * 
	 * @return a collection of things which this command wishes to present as it's result.
	 */
	public Collection getResult() {
		return super.getResult();
	}

	/**
	 * Returns the collection of things which this command wishes to present as the objects affected
	 * by the command. Typically should could be used as the selection that should be highlighted to
	 * best illustrate the effect of the command. The result of calling this before an
	 * <code>execute</code>,<code>redo</code>, or <code>undo</code> is undefined. The
	 * result may be different after an <code>undo</code> than it is after an <code>execute</code>
	 * or <code>redo</code>, but the result should be the same (equivalent) after either an
	 * <code>execute</code> or <code>redo</code>.
	 * 
	 * @return the collection of things which this command wishes to present as the objects affected
	 *         by the command.
	 */
	public Collection getAffectedObjects() {
		return super.getAffectedObjects();
	}

	/**
	 * Called to indicate that the command will never be used again. Calling any other method after
	 * this one has undefined results.
	 */
	public void dispose() {
		//Do nothing
	}

	/**
	 * Returns a command that represents the composition of this command with the given command. The
	 * resulting command may just be this, if this command is capabable of composition. Otherwise,
	 * it will be a new command created to compose the two.
	 * <p>
	 * Instead of the following pattern of usage
	 * 
	 * <pre>
	 * Command result = x;
	 * if (condition)
	 * 	result = result.chain(y);
	 * </pre>
	 * 
	 * you should consider using a {@link org.eclipse.emf.common.command.CompoundCommand}and using
	 * {@link org.eclipse.emf.common.command.CompoundCommand#unwrap()}to optimize the result:
	 * 
	 * <pre>
	 * CompoundCommand subcommands = new CompoundCommand();
	 * subcommands.append(x);
	 * if (condition)
	 * 	subcommands.append(y);
	 * Command result = subcommands.unwrap();
	 * </pre>
	 * 
	 * This gives you more control over how the compound command composes it's result and affected
	 * objects.
	 * 
	 * @param command
	 *            the command to chain.
	 * @return a command that represents the composition of this command with the given command.
	 */
	public Command chain(Command command) {
		return super.chain(command);
	}
}
