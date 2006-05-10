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
package org.eclipse.jst.j2ee.internal.common.classpath;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IJavaProject;

public class J2EEComponentClasspathInitializer extends ClasspathContainerInitializer {

	public void initialize(IPath containerPath, IJavaProject javaProject) throws CoreException {
		J2EEComponentClasspathUpdater.init();
		(new J2EEComponentClasspathContainer(containerPath, javaProject)).install();
	}
}
