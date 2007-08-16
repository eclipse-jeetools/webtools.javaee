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

import org.eclipse.jdt.ui.JavaElementSorter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class CompressedJavaSorter extends ViewerSorter {
	
	private JavaElementSorter sorter = new JavaElementSorter();
	
	public int category(Object element) { 
		if(element instanceof CompressedJavaLibraries)
			return 1;
		else return 0;
	}
	
	public int compare(Viewer viewer, Object e1, Object e2) {
		if(e1 instanceof CompressedJavaLibraries)
			return 1;
		else if(e2 instanceof CompressedJavaLibraries)
			return -1;
		
		return sorter.compare(viewer, e1, e2);
	}

}
