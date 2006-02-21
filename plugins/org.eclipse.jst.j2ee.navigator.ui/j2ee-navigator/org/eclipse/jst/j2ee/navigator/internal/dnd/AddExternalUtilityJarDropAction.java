/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal.dnd;

import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.internal.navigator.resources.workbench.ResourceDropAction;
import org.eclipse.ui.navigator.CommonDropAdapter;

/**
 * @author mdelder
 *  
 */
public class AddExternalUtilityJarDropAction extends ResourceDropAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.workbench.navigator.dnd.ResourceDropAction#validateDrop(org.eclipse.wst.common.navigator.internal.views.navigator.dnd.CommonNavigatorDropAdapter,
	 *      java.lang.Object, int, org.eclipse.swt.dnd.TransferData)
	 */
	public boolean validateDrop(CommonDropAdapter dropAdapter, Object target, int operation, TransferData transferType) {
		//TODO redo with components
//		if (FileTransfer.getInstance().isSupportedType(transferType)) {
//			IProject project = (IProject) AdaptabilityUtility.getAdapter(target, IProject.class);
//			try {
//				if (project.hasNature(IEARNatureConstants.NATURE_ID)) {
//					String[] sourceNames = (String[]) FileTransfer.getInstance().nativeToJava(transferType);
//					if (sourceNames == null)
//						return true;
//
//					boolean result = true;
//					for (int i = 0; i < sourceNames.length; i++)
//						if (!(result = sourceNames[0].endsWith(".jar")))break; //$NON-NLS-1$
//					return result;
//				}
//				return false;
//			} catch (CoreException e) {
//				return false;
//			}
//		}
		return false;
	}
}
