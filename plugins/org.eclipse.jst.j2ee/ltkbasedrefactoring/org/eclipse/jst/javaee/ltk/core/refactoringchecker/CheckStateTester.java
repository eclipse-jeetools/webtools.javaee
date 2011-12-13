/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.javaee.ltk.core.refactoringchecker;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.javaee.ltk.core.nls.RefactoringResourceHandler;


public class CheckStateTester {
	
	protected static final String EXTENSION_POINT = "RefactoringChecker";//$NON-NLS-1$
	public static CheckStateTester INSTANCE = new CheckStateTester();
	private ArrayList<IRefactoringCheckStateTester> checkStateTesters = new ArrayList();
	private boolean registryIsRead = false;
	
	
	private void readRegistry(){
			registryIsRead = true;
			IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(J2EEPlugin.PLUGIN_ID, EXTENSION_POINT);
			if (point == null) {
				J2EEPlugin.logError(RefactoringResourceHandler.CheckStateTester_Could_not_find_Check_State_Tester_E_);
				return;
			}
			IConfigurationElement[] elements = point.getConfigurationElements();
			for(IConfigurationElement element: elements){
				Object o;
				try {
					o = element.createExecutableExtension("class"); //$NON-NLS-1$
					if(o instanceof IRefactoringCheckStateTester){
						checkStateTesters.add((IRefactoringCheckStateTester)o);
					}
				} catch (CoreException e) {
					org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
				}
			}
	}
	
	public ArrayList<IRefactoringCheckStateTester> getCheckStateTesters(){
		if(registryIsRead == false){
			readRegistry();
		}
		return checkStateTesters;
	}
}
