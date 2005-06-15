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
package org.eclipse.jst.servlet.ui.internal.wizard;

import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.internal.wizard.AvailableJarsProvider;
import org.eclipse.swt.graphics.Image;

public class AvailableWebLibProvider implements IStructuredContentProvider, ITableLabelProvider {

	public AvailableWebLibProvider() {
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof WARFile) {
			Object[] array = ((WARFile) inputElement).getLibArchives().toArray();
			Arrays.sort(array, new Comparator() {
				public int compare(Object o1, Object o2) {
					return getColumnText(o1, 0).compareTo(getColumnText(o2, 0));
				}

			});
			return array;
		}
		return new Object[0];
	}

	public Image getColumnImage(Object element, int columnIndex) {
		return AvailableJarsProvider.getUtilImage();
	}

	public String getColumnText(Object element, int columnIndex) {
		File file = (File) element;
		return file.getName();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void addListener(ILabelProviderListener listener) {
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
	}
}