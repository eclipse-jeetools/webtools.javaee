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
package org.eclipse.jst.j2ee.internal.ear.actions;


import org.eclipse.jst.j2ee.internal.wizard.TableObjects;

/**
 * Insert the type's description here. Creation date: (5/7/2001 11:39:11 AM)
 * 
 * @author: Administrator
 */
public class EARImportListContentProvider implements org.eclipse.jface.viewers.IStructuredContentProvider {
	/**
	 * EARImportListContentProvider constructor comment.
	 */
	public EARImportListContentProvider() {
		super();
	}

	/**
	 * Disposes of this content provider. This is called by the viewer when it is disposed.
	 */
	public void dispose() {
	}

	/**
	 * Returns the elements to display in the viewer when its input is set to the given element.
	 * These elements can be presented as rows in a table, items in a list, etc. The result is not
	 * modified by the viewer.
	 * 
	 * @param inputElement
	 *            the input element
	 * @return the array of elements to display in the viewer
	 */
	public java.lang.Object[] getElements(Object inputElement) {
		if (inputElement instanceof TableObjects)
			return ((TableObjects) inputElement).getTableObjects().toArray();
		return new Object[0]; //should throw exception instead
	}

	/**
	 * Notifies this content provider that the given viewer's input has been switched to a different
	 * element.
	 * <p>
	 * A typical use for this method is registering the content provider as a listener to changes on
	 * the new input (using model-specific means), and deregistering the viewer from the old input.
	 * In response to these change notifications, the content provider propagates the changes to the
	 * viewer.
	 * </p>
	 * 
	 * @param viewer
	 *            the viewer
	 * @param oldInput
	 *            the old input element, or <code>null</code> if the viewer did not previously
	 *            have an input
	 * @param newInput
	 *            the new input element, or <code>null</code> if the viewer does not have an input
	 */
	public void inputChanged(org.eclipse.jface.viewers.Viewer viewer, Object oldInput, Object newInput) {
	}
}