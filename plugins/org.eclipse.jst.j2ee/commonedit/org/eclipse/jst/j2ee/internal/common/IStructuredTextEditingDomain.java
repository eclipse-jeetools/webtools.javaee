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
package org.eclipse.jst.j2ee.internal.common;



import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.wst.sse.core.undo.IStructuredTextUndoManager;

/**
 * This is a specialized editing domain that can be used by editors that have one or more design
 * pages that view a MOF model and a source page that contains an XML Model.
 */
public interface IStructuredTextEditingDomain extends EditingDomain {
	/**
	 * Execute a command within the editing domain.
	 */
	public void execute(Command command);

	/**
	 * Execute a command within the editing domain
	 */
	public void execute(String label, Command command);

	/**
	 * This returns the adapter factory used by this domain.
	 */
	public AdapterFactory getAdapterFactory();

	public IStructuredTextUndoManager getUndoManager();

	public void setUndoManager(IStructuredTextUndoManager newUndoManager);
}