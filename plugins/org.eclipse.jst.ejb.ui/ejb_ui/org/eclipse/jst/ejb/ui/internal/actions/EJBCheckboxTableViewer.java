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
package org.eclipse.jst.ejb.ui.internal.actions;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

/**
 * Insert the type's description here. Creation date: (3/22/2001 4:44:53 PM)
 * 
 * @author: Administrator
 */
public class EJBCheckboxTableViewer extends org.eclipse.jface.viewers.CheckboxTableViewer {
	/**
	 * EJBCheckboxTableViewer constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.swt.widgets.Composite
	 * @param style
	 *            int
	 */
	public EJBCheckboxTableViewer(org.eclipse.swt.widgets.Composite parent, int style) {
		super(createTable(parent, style));
	}

	/**
	 * EJBCheckboxTableViewer constructor comment.
	 * 
	 * @param table
	 *            org.eclipse.swt.widgets.Table
	 */
	public EJBCheckboxTableViewer(org.eclipse.swt.widgets.Table table) {
		super(table);
	}

	/**
	 * Returns a list of elements corresponding to checked table items in this viewer.
	 * <p>
	 * This method is typically used when preserving the interesting state of a viewer;
	 * <code>setCheckedElements</code> is used during the restore.
	 * </p>
	 * <p>
	 * [Issue: Should return Object[] rather than List.]
	 * </p>
	 * 
	 * @return the list of checked nodes (element type: <code>Object</code>)
	 * @see #setCheckedElements
	 */
	public List getAllElements() {
		TableItem[] children = getTable().getItems();
		List v = new ArrayList(children.length);
		for (int i = 0; i < children.length; i++) {
			TableItem item = children[i];
			v.add(item.getData());
		}

		return v;
	}

	/**
	 * Returns a list of elements corresponding to checked table items in this viewer.
	 * <p>
	 * This method is typically used when preserving the interesting state of a viewer;
	 * <code>setCheckedElements</code> is used during the restore.
	 * </p>
	 * <p>
	 * [Issue: Should return Object[] rather than List.]
	 * </p>
	 * 
	 * @return the list of checked nodes (element type: <code>Object</code>)
	 * @see #setCheckedElements
	 */
	public List getUnCheckedElements() {
		TableItem[] children = getTable().getItems();
		List v = new ArrayList(children.length);
		for (int i = 0; i < children.length; i++) {
			TableItem item = children[i];
			if (!(item.getChecked()))
				v.add(item.getData());
		}
		return v;
	}

	/**
	 * Sets which nodes are checked in this viewer. The given list contains the elements that are to
	 * be checked; all other nodes are to be unchecked.
	 * <p>
	 * This method is typically used when restoring the interesting state of a viewer captured by an
	 * earlier call to <code>getCheckedElements</code>.
	 * </p>
	 * <p>
	 * [Issue: Should accept Object[] rather than List.]
	 * </p>
	 * 
	 * @param elements
	 *            the list of checked elements (element type: <code>Object</code>)
	 * @see #getCheckedElements
	 */
	public void setUnCheckedElements(List elements) {
		for (int i = 0; i < elements.size(); i++) {
			Widget widget = findItem(elements.get(i));
			if (widget instanceof TableItem)
				((TableItem) widget).setChecked(false);
		}

	}

	public void setCheckedElements(List elements) {
		for (int i = 0; i < elements.size(); i++) {
			Widget widget = findItem(elements.get(i));
			if (widget instanceof TableItem)
				((TableItem) widget).setChecked(true);
		}

	}
}