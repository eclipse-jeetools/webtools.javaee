/******************************************************************************
 * Copyright (c) 2005-2019 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.internal.web.classpath;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jst.common.jdt.internal.classpath.FlexibleProjectContainerInitializer;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class WebAppLibrariesContainerInitializer

    extends FlexibleProjectContainerInitializer

{
    @Override
	public void initialize( final IPath path, 
                            final IJavaProject jproj )

	    throws CoreException

	{
	    ( new WebAppLibrariesContainer( path, jproj ) ).install();
	}

}
