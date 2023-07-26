/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.jst.j2ee.internal.wizard.AvailableJarsProvider;
import org.eclipse.swt.graphics.Image;

public class AvailableWebLibProvider implements IStructuredContentProvider, ITableLabelProvider {

	public AvailableWebLibProvider() {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof ArchiveWrapper) {
			Object[] array = ((ArchiveWrapper) inputElement).getWebLibs().toArray();
			Arrays.sort(array, new Comparator() {
				@Override
				public int compare(Object o1, Object o2) {
					return getColumnText(o1, 0).compareTo(getColumnText(o2, 0));
				}

			});
			return array;
		}
		return new Object[0];
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return AvailableJarsProvider.getUtilImage();
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		ArchiveWrapper wrapper = (ArchiveWrapper)element;
		return wrapper.getName();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}
}
