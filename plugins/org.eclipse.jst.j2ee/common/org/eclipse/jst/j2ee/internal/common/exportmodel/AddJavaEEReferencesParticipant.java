/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.exportmodel;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class AddJavaEEReferencesParticipant extends AbstractFlattenParticipant {
	
	public static IPath WEBLIB = new Path("/WEB-INF/lib"); //$NON-NLS-1$
	
	@Override
	public boolean shouldIgnoreReference(IVirtualComponent rootComponent, IVirtualReference referenced, FlatComponentTaskModel dataModel) {
		if (!referenced.getReferencedComponent().isBinary()) {
			String parentType = JavaEEProjectUtilities.getJ2EEComponentType(rootComponent);
			if (IJ2EEFacetConstants.ENTERPRISE_APPLICATION.equals(parentType)) {
				return false;
			}
			else if (IJ2EEFacetConstants.DYNAMIC_WEB.equals(parentType) && referenced.getRuntimePath().equals(WEBLIB)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isChildModule(IVirtualComponent rootComponent, IVirtualReference reference, FlatComponentTaskModel dataModel) {
		if (!reference.getReferencedComponent().isBinary()) {
			return true;
		}
		return false;
	}
	
}
