/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.classpathdep;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IClasspathEntry;

/**
 * 
 * This interface is used by extenders of the ClasspathDependencyExtension extension
 *
 */
public interface IClasspathDependencyExtension 
{
  /**
   * An extender can indicate if it handles the exporting for a particular entry in a project.
   * 
   * @param project the project containing the classpath entry
   * @param entry the classpath entry
   * @return returns true if this project will handle the exporting of the classpath entry.
   */
  public boolean projectHandlesExport( IProject project, IClasspathEntry entry );
  
  /**
   * 
   * @param project
   * @param entry
   * @return returns the value that the classpath attribute "org.eclipse.jst.component.dependency" should
   * contain.
   */
  public String getClasspathdependencyAttribute( IProject project, IClasspathEntry entry );
}
