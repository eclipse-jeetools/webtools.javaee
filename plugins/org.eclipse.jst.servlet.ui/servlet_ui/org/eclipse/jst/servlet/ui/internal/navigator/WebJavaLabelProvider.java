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
package org.eclipse.jst.servlet.ui.internal.navigator;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class WebJavaLabelProvider implements ILabelProvider {

	public Image getImage(Object element) {
		if(element instanceof CompressedJavaProject)
			return ((CompressedJavaProject)element).getImage();
		if(element instanceof CompressedJavaLibraries)
			return ((CompressedJavaLibraries)element).getImage();
		return null;
	}

	public String getText(Object element) {
		if(element instanceof CompressedJavaProject)
			return ((CompressedJavaProject)element).getLabel();
		if(element instanceof CompressedJavaLibraries)
			return ((CompressedJavaLibraries)element).getLabel();
		return null;
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
