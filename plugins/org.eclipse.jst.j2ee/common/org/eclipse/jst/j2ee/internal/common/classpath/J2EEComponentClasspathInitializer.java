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

import java.lang.reflect.Field;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.common.jdt.internal.classpath.ClasspathDecorations;
import org.eclipse.jst.common.jdt.internal.classpath.ClasspathDecorationsManager;

public class J2EEComponentClasspathInitializer extends ClasspathContainerInitializer {

	private static final ClasspathDecorationsManager decorations = J2EEComponentClasspathContainer.getDecorationsManager();
	
	public void initialize(IPath containerPath, IJavaProject javaProject) throws CoreException {
		J2EEComponentClasspathContainer.install(containerPath, javaProject);
	}

	public boolean canUpdateClasspathContainer(final IPath containerPath, final IJavaProject project) {
		return true;
	}

	public void requestClasspathContainerUpdate(final IPath containerPath, final IJavaProject javaProject, final IClasspathContainer sg) throws CoreException {
		final String key = containerPath.toString();
		final IClasspathEntry[] entries = sg.getClasspathEntries();
		
		for (int i = 0; i < entries.length; i++) {
			final IClasspathEntry entry = entries[i];

			final IPath srcpath = entry.getSourceAttachmentPath();
			final IPath srcrootpath = entry.getSourceAttachmentRootPath();
			final IClasspathAttribute[] attrs = entry.getExtraAttributes();

			if (srcpath != null || attrs.length > 0) {
				final String eid = entry.getPath().toString();
				final ClasspathDecorations dec = new ClasspathDecorations();

				dec.setSourceAttachmentPath(srcpath);
				dec.setSourceAttachmentRootPath(srcrootpath);
				dec.setExtraAttributes(attrs);

				decorations.setDecorations(key, eid, dec);
			}
		}

		decorations.save();

		final IClasspathContainer container = JavaCore.getClasspathContainer(containerPath, javaProject);

		// ( (FlexibleProjectContainer) container ).refresh();

		refresh(container);
	}
	
	// Workaround for bug 145784.
    // this same hack is also being used in FlexibleProjectContainerInitializer
    private static void refresh( final IClasspathContainer container )
    {
        if( container instanceof J2EEComponentClasspathContainer )
        {
            ( (J2EEComponentClasspathContainer) container ).refresh(true);
        }
        else
        {
            try
            {
                final Field field 
                    = container.getClass().getDeclaredField( "fOriginal" ); //$NON-NLS-1$
                
                field.setAccessible( true );
                
                refresh( (IClasspathContainer) field.get( container ) );
            }
            catch( NoSuchFieldException e )
            {
                // Should not happen.
                throw new RuntimeException( e );
            }
            catch( IllegalAccessException e )
            {
                // Should not happen.
                throw new RuntimeException( e );
            }
        }
    }
}
