/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;



import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;


public abstract class AbstractActionWithDelegate extends org.eclipse.ui.actions.SelectionListenerAction {
	protected IActionDelegate delegate;

	/**
	 * EditModuleDependencyAction constructor comment.
	 * 
	 * @param text
	 *            java.lang.String
	 */
	public AbstractActionWithDelegate() {
		super("");//$NON-NLS-1$
		initLabel();
		initDelegate();
	}

	protected abstract IActionDelegate createDelegate();

	protected abstract String getLabel();

	protected void initDelegate() {
		delegate = createDelegate();
	}

	protected void initLabel() {
		setText(getLabel());
	}

	/**
	 * Implementation of method defined on <code>IAction</code>.
	 */
	public void run() {
		delegate.run(this);
	}

	/**
	 * Updates this action in response to the given selection.
	 * <p>
	 * The <code>SelectionListenerAction</code> implementation of this method returns
	 * <code>true</code>. Subclasses may extend to react to selection changes; however, if the
	 * super method returns <code>false</code>, the overriding method must also return
	 * <code>false</code>.
	 * </p>
	 * 
	 * @param selection
	 *            the new selection
	 * @return <code>true</code> if the action should be enabled for this selection, and
	 *         <code>false</code> otherwise
	 */
	protected boolean updateSelection(IStructuredSelection selection) {
		delegate.selectionChanged(this, selection);
		return this.isEnabled();
	}
}