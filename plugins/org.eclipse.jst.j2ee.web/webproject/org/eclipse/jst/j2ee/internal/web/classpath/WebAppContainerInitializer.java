/******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.internal.web.classpath;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class WebAppContainerInitializer extends ClasspathContainerInitializer

{
    public void initialize( final IPath path,
                            final IJavaProject jproj ) throws CoreException

    {
        final IJavaProject[] projects = new IJavaProject[] { jproj };

        final WebAppContainer container
            = new WebAppContainer( path, jproj.getProject() );

        final IClasspathContainer[] containers
            = new IClasspathContainer[] { container };

        JavaCore.setClasspathContainer( path, projects, containers, null );
    }
	
	public boolean canUpdateClasspathContainer(IPath containerPath, IJavaProject project) {
		return true;
	}

	public void requestClasspathContainerUpdate(IPath containerPath, IJavaProject project, IClasspathContainer containerSuggestion) throws CoreException {
		System.out.println("Request to update Web Lib Container");
	}
}
