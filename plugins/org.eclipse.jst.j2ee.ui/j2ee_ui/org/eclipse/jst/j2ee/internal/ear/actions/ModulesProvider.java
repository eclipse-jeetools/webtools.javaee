/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ear.actions;


import org.eclipse.jst.j2ee.application.internal.operations.ClassPathSelection;

/**
 * Insert the type's description here. Creation date: (8/22/2001 2:27:24 PM)
 * 
 * @author: Administrator
 */
public class ModulesProvider implements org.eclipse.jface.viewers.ILabelProvider, org.eclipse.jface.viewers.IStructuredContentProvider {
	/**
	 * ModulesProvider constructor comment.
	 */
	public ModulesProvider() {
		super();
	}

	/**
	 * Adds a listener to this label provider. Has no effect if an identical listener is already
	 * registered.
	 * <p>
	 * Label provider listeners are informed about state changes that affect the rendering of the
	 * viewer that uses this label provider.
	 * </p>
	 * 
	 * @param listener
	 *            a label provider listener
	 */
	public void addListener(org.eclipse.jface.viewers.ILabelProviderListener listener) {
		//do nothing
	}

	/**
	 * Disposes of this content provider. This is called by the viewer when it is disposed.
	 */
	public void dispose() {
		//dispose
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
	public java.lang.Object[] getElements(java.lang.Object inputElement) {
		return ((java.util.List) inputElement).toArray();
	}

	/**
	 * Returns the image for the label of the given element. The image is owned by the label
	 * provider and must not be disposed directly. Instead, dispose the label provider when no
	 * longer needed.
	 * 
	 * @param element
	 *            the element for which to provide the label image
	 * @return the image used to label the element, or <code>null</code> if these is no image for
	 *         the given object
	 */
	public org.eclipse.swt.graphics.Image getImage(Object element) {
		return null;
	}

	/**
	 * Returns the text for the label of the given element.
	 * 
	 * @param element
	 *            the element for which to provide the label text
	 * @return the text string used to label the element, or <code>null</code> if these is no text
	 *         label for the given object
	 */
	public String getText(Object element) {
		return ((ClassPathSelection) element).getText();
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
		//do nothing
	}

	/**
	 * Returns whether the label would be affected by a change to the given property of the given
	 * element. This can be used to optimize a non-structural viewer update. If the property
	 * mentioned in the update does not affect the label, then the viewer need not update the label.
	 * 
	 * @param element
	 *            the element
	 * @param property
	 *            the property
	 * @return <code>true</code> if the label would be affected, and <code>false</code> if it
	 *         would be unaffected
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/**
	 * Removes a listener to this label provider. Has no affect if an identical listener is not
	 * registered.
	 * 
	 * @param listener
	 *            a label provider listener
	 */
	public void removeListener(org.eclipse.jface.viewers.ILabelProviderListener listener) {
		//do nothing
	}
}
