/*******************************************************************************
 * Copyright (c) 2010, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui.preferences;

import org.eclipse.jst.j2ee.internal.modulecore.util.DummyClasspathDependencyContainerVirtualComponent;
import org.eclipse.jst.j2ee.internal.wizard.AvailableJarsProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.IVirtualComponentLabelProvider;

public class ClasspathDependencyComponentLabelProvider implements
		IVirtualComponentLabelProvider {

	public ClasspathDependencyComponentLabelProvider() {
		// Do nothing
	}

	@Override
	public boolean canHandle(IVirtualComponent component) {
		if( component instanceof DummyClasspathDependencyContainerVirtualComponent)
			return true;
		return false;
	}

	@Override
	public String getSourceText(IVirtualComponent component) {
		return Messages.ChildClasspathDependencyDescription;
	}

	@Override
	public Image getSourceImage(IVirtualComponent component) {
		return AvailableJarsProvider.getClasspathDependencyImage();
	}
	@Override
	public void dispose() {
		//  Do nothing
	}
}
