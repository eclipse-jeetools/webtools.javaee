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
package org.eclipse.jst.j2ee.navigator.internal.dnd;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.wst.common.framework.AdaptabilityUtility;
import org.eclipse.wst.common.navigator.internal.workbench.ResourceDropAction;
import org.eclipse.wst.common.navigator.views.NavigatorDropAdapter;

/**
 * @author mdelder
 *  
 */
public class AddExternalUtilityJarDropAction extends ResourceDropAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.workbench.navigator.dnd.ResourceDropAction#validateDrop(org.eclipse.wst.common.navigator.internal.views.navigator.dnd.NavigatorDropAdapter,
	 *      java.lang.Object, int, org.eclipse.swt.dnd.TransferData)
	 */
	public boolean validateDrop(NavigatorDropAdapter dropAdapter, Object target, int operation, TransferData transferType) {
		if (FileTransfer.getInstance().isSupportedType(transferType)) {
			IProject project = (IProject) AdaptabilityUtility.getAdapter(target, IProject.class);
			try {
				if (project.hasNature(IEARNatureConstants.NATURE_ID)) {
					String[] sourceNames = (String[]) FileTransfer.getInstance().nativeToJava(transferType);
					if (sourceNames == null)
						return true;

					boolean result = true;
					for (int i = 0; i < sourceNames.length; i++)
						if (!(result = sourceNames[0].endsWith(".jar")))break; //$NON-NLS-1$
					return result;
				}
				return false;
			} catch (CoreException e) {
				return false;
			}
		}
		return false;
	}
}
