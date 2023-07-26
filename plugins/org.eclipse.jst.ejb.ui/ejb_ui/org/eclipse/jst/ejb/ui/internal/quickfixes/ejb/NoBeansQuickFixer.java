/*******************************************************************************
 * Copyright (c) 2010, 2023 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.quickfixes.ejb;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;

public class NoBeansQuickFixer implements IMarkerResolutionGenerator2  {

	@Override
	public IMarkerResolution[] getResolutions(IMarker marker) {
        return new IMarkerResolution[] {
        		new CreateBeanQuickFix(CreateBeanQuickFix.BEAN_TYPE.SESSION, (IProject)marker.getResource()),
        		new CreateBeanQuickFix(CreateBeanQuickFix.BEAN_TYPE.MESSAGE_DRIVEN, (IProject)marker.getResource())
        };
    }

	@Override
	public boolean hasResolutions(IMarker marker) {
		return true;
	}
	
}
