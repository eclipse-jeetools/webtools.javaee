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
package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.provider.J2EEBinaryModulesItemProvider;

public class ApplicationViewerSorter extends J2EEViewerSorter {

	public ApplicationViewerSorter() {
		super();
	}
	
	public int compare(Viewer viewer, Object e1, Object e2) {
		if(e1 instanceof J2EEBinaryModulesItemProvider){
			return 1;
		} else if(e2 instanceof J2EEBinaryModulesItemProvider){
			return -1;
		}
		return super.compare(viewer, e1, e2);
	}
	
}
