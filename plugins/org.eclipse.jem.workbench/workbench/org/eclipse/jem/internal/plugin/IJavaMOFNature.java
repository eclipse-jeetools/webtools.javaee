package org.eclipse.jem.internal.plugin;
/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import org.eclipse.emf.ecore.resource.ResourceSet;

public interface IJavaMOFNature {
	String NATURE_ID = "org.eclipse.jem.workbench.JavaEMFNature"; //$NON-NLS-1$
	String SOURCE_PATH = "sourcepath"; //$NON-NLS-1$
	String TRUE  = "true"; //$NON-NLS-1$
	String FALSE = "false"; //$NON-NLS-1$
	
ResourceSet getContext() ;
IFolder getJavaOutputFolder();
/**
 * Return the root location for loading mof resources; defaults to the source folder, subclasses may override
 */
IContainer getMofRoot();
IFolder getSourceFolder();
List getSourceFolders();
IProject getProject();

/**
*Shuts down the MOF Nature
*/
void shutdown();
}
