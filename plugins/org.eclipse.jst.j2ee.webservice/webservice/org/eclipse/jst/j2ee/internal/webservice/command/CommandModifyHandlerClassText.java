/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.command;

import java.util.Collection;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;


public class CommandModifyHandlerClassText extends AbstractCommand {
	private Handler eObject_;
	private EStructuralFeature feature_;
	private String newValue_;
	private JavaClass oldValue_;
	private boolean nillable_;
	private boolean oldIsSet_;

	public CommandModifyHandlerClassText(String label, String description, Handler eObject, String newValue) {
		super(label, description);
		eObject_ = eObject;
		feature_ = Webservice_clientPackage.eINSTANCE.getHandler_HandlerClass();
		if (newValue == null)
			newValue_ = ""; //$NON-NLS-1$
		else
			newValue_ = newValue;
		nillable_ = false;
		oldValue_ = null;
		oldIsSet_ = true;
	}

	/**
	 * Called at most once in {@link #canExecute}to give the command an opportunity to ready itself
	 * for execution. The returned value is stored in {@link #canExecute}. In other words, you can
	 * override this method to initialize and to yield a cached value for the all subsequent calls
	 * to canExecute.
	 * 
	 * @return whether the command is executable.
	 */
	@Override
	protected boolean prepare() {
		return true;
	}

	/**
	 * Returns whether the comamad is valid to <code>execute</code>. The
	 * { @link UnexecutableCommand#INSTANCE}.<code>canExecute()</code> always returns
	 * <code>false</code>. This <b>must </b> be called before calling <code>execute</code>.
	 * 
	 * @return whether the comamad is valid to <code>execute</code>.
	 */
	@Override
	public boolean canExecute() {
		return super.canExecute();
	}

	/**
	 * Performs the command activity required for the effect. The effect of calling
	 * <code>execute</code> when <code>canExecute</code> returns <code>false</code>, or when
	 * <code>canExecute</code> hasn't been called, is undefined.
	 */
	@Override
	public void execute() {
		oldIsSet_ = eObject_.eIsSet(feature_);
		oldValue_ = eObject_.getHandlerClass();
		eObject_.setHandlerClass(JavaRefFactory.eINSTANCE.createClassRef(newValue_));
		if (nillable_ && newValue_ == null)
			eObject_.eUnset(feature_);
	}

	/**
	 * Returns <code>true</code> because most command should be undoable.
	 * 
	 * @return <code>true</code>.
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/**
	 * Performs the command activity required to <code>undo</code> the effects of a preceding
	 * <code>execute</code> (or <code>redo</code>). The effect, if any, of calling
	 * <code>undo</code> before <code>execute</code> or <code>redo</code> have been called, or
	 * when canUndo returns <code>false</code>, is undefined.
	 */
	@Override
	public void undo() {
		if (oldIsSet_)
			eObject_.setHandlerClass(oldValue_);
		else
			eObject_.eUnset(feature_);
	}

	/**
	 * Performs the command activity required to <code>redo</code> the effect after undoing the
	 * effect. The effect, if any, of calling <code>redo</code> before <code>undo</code> is
	 * called is undefined. Note that if you implement <code>redo</code> to call
	 * <code>execute</code> then any derived class will be restricted by that decision also.
	 */
	@Override
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
	@Override
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
	@Override
	public Collection getAffectedObjects() {
		return super.getAffectedObjects();
	}

	/**
	 * Called to indicate that the command will never be used again. Calling any other method after
	 * this one has undefined results.
	 */
	@Override
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
	 * { @link org.eclipse.emf.common.command.CompoundCommand#unwrap()}to optimize the result:
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
	@Override
	public Command chain(Command command) {
		return super.chain(command);
	}
}
