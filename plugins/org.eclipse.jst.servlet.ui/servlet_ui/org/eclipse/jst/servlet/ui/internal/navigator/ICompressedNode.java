/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.navigator;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * Provides a simple interface that is used to manipulate the rendering of Java elements to a format
 * that is friendlier for Web Projects.
 * 
 */
public interface ICompressedNode   {
	/**
	 * 
	 * @return The image to display for this node.
	 */
	public Image getImage();

	/**
	 * 
	 * @return The text label to display for this node.
	 */
	public String getLabel();

	/**
	 * 
	 * @param delegateContentProvider
	 *            The content provider that should used for any children that I cannot provide
	 *            children for.
	 * @return The children either from me or the delegateContentProvider.
	 */
	public Object[] getChildren(ITreeContentProvider delegateContentProvider);

	/**
	 * 
	 * @return The Java element associated with this node, if any.
	 */
	public IJavaElement getJavaElement();
}
