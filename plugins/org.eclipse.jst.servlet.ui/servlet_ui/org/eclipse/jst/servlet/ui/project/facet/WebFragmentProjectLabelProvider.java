/*******************************************************************************

 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.servlet.ui.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class WebFragmentProjectLabelProvider extends LabelProvider {
	private ILabelProvider workbenchLabelProvider = new WorkbenchLabelProvider();
	private ILabelDecorator decorator = PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
	
	@Override
	public Image getImage(Object o) {
		Image image = workbenchLabelProvider.getImage(o);
		
		if (o instanceof IProject) {
			Image decorated = decorator.decorateImage(image, o);
			
			if (decorated != null) {
				return decorated;
			}}
		return image;
	}
	
	@Override
	public String getText(Object o) {
		if (o instanceof IProject) {
			return ((IProject) o).getName();
		}
		return ""; //$NON-NLS-1$
	}
}