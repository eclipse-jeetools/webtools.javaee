/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.command;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient;


public class CommandMoveServiceRefs extends AbstractCommand {
	private EObject source_;
	private EObject target_;
	private EStructuralFeature childFeature_;
	private Object[] children_;
	private boolean isCopy_;
	private Object[] clonedChildren_;
	private EList serviceRefs;

	public CommandMoveServiceRefs(String label, String description, EObject source, EObject target, EStructuralFeature childFeature, Object[] children, boolean isCopy, EList serviceRefs) {
		super(label, description);
		source_ = source;
		target_ = target;
		childFeature_ = childFeature;
		children_ = children;
		isCopy_ = isCopy;
		clonedChildren_ = null;
		this.serviceRefs = serviceRefs;
	}

	private Object cloneObject(Object object) {
		if (object instanceof EList) {
			EList clone = new BasicEList();
			for (Iterator it = ((EList) object).iterator(); it.hasNext();) {
				clone.add(cloneObject(it.next()));
			}
			return clone;
		}
		if (object instanceof EObject) {
			EObject source = (EObject) object;
			EClass eClass = ((EObject) object).eClass();
			EPackage ePackage = eClass.getEPackage();
			EFactory eFactory = ePackage.getEFactoryInstance();
			EObject clone = eFactory.create(eClass);
			EList features = eClass.getEAllStructuralFeatures();
			for (Iterator it = features.iterator(); it.hasNext();) {
				EStructuralFeature feature = (EStructuralFeature) it.next();
				if (source.eIsSet(feature)) {
					Object clonedFeature = cloneObject(source.eGet(feature));
					if (feature.isMany())
						((List) clone.eGet(feature)).addAll((List) clonedFeature);
					else
						clone.eSet(feature, clonedFeature);
				} else
					clone.eUnset(feature);
			}
			return clone;
		}
		if (object != null)
			return new String(object.toString());
		return null;
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
		if (childFeature_.isMany())
			executeMany();
		else
			executeSingle();
	}

	private void executeSingle() {
		Object child = source_.eGet(childFeature_);
		if (isCopy_)
			target_.eSet(childFeature_, cloneObject(child));
		else {
			source_.eUnset(childFeature_);
			target_.eSet(childFeature_, child);
		}
	}

	private void executeMany() {
		EList sourceList;
		if (source_ instanceof WebServicesClient)
			sourceList = serviceRefs;
		else
			sourceList = (EList) source_.eGet(childFeature_);
		EList targetList;
		if (target_ instanceof WebServicesClient)
			targetList = serviceRefs;
		else
			targetList = (EList) target_.eGet(childFeature_);
		if (isCopy_)
			clonedChildren_ = new Object[children_.length];
		for (int i = 0; i < children_.length; i++) {
			int index = sourceList.indexOf(children_[i]);
			if (index != -1) {
				if (isCopy_) {
					clonedChildren_[i] = cloneObject(children_[i]);
					targetList.add(clonedChildren_[i]);
				} else {
					sourceList.remove(index);
					targetList.add(children_[i]);
				}
			}
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
		if (childFeature_.isMany())
			undoSingle();
		else
			undoMany();
	}

	private void undoSingle() {
		Object child = target_.eGet(childFeature_);
		target_.eUnset(childFeature_);
		if (!isCopy_)
			source_.eSet(childFeature_, child);
	}

	private void undoMany() {
		EList sourceList;
		if (source_ instanceof WebServicesClient)
			sourceList = serviceRefs;
		else
			sourceList = (EList) source_.eGet(childFeature_);
		EList targetList;
		if (target_ instanceof WebServicesClient)
			targetList = serviceRefs;
		else
			targetList = (EList) target_.eGet(childFeature_);
		if (isCopy_) {
			if (clonedChildren_ != null) {
				for (int i = 0; i < clonedChildren_.length; i++) {
					targetList.remove(clonedChildren_[i]);
				}
			}
		} else {
			for (int i = 0; i < children_.length; i++) {
				int index = targetList.indexOf(children_[i]);
				if (index != -1) {
					targetList.remove(index);
					sourceList.add(children_[i]);
				}
			}
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