/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IBeanInfoContributor.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/04 12:58:28 $ 
 */
package org.eclipse.jem.internal.beaninfo.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.jem.internal.proxy.core.IConfigurationContributionInfo;
import org.eclipse.jem.java.JavaClass;
 
/**
 * Interface for BeanInfo contributors. Supplied either as additional interface on a IClasspathContainer, or as
 * an explicit contributor for BeanInfos from the manifest files.
 * <p>
 * Implementations of this interface allows a dynamic supply of BeanInfo contributions (beaninfo jars and search paths)
 * and override files. This is used when the default plugin extension mechanism is too static for the requirements. 
 * <p>
 * There is a very useful default implementation (<code>BeanInfoContributorAdapter</code>) that can be subclassed. The
 * default doesn't do anything on its own. But it does have a very useful utility method for loading resources.
 * 
 * @see org.eclipse.jem.internal.beaninfo.core.BeanInfoContributorAdapter
 * @since 1.0.0
 */
public interface IBeanInfoContributor {
	
	/**
	 * Return the BeanInfoEntry contributions that are needed.
	 * @param info
	 * @return BeanInfo contributions or <code>null</code> if none to contribute.
	 * 
	 * @since 1.0.0
	 */
	public BeaninfoEntry[] getBeanInfoEntryContributions(IConfigurationContributionInfo info);
	
	/**
	 * For the given package path run the override contributions through the runnable.
	 * <p>
	 * Implementations must call the runnable for each override file that is to be applied to the incoming class.
	 * You can either call the method that passes in a string pointing to a directory where your override file is
	 * located (i.e. <code>overrideFileName.override</code> where "overrideFileName" is the name passed.
	 * 
	 * @param packagePath
	 * @param overrideFileName the overrideFileName that the overrides is for. This can be used in very special cases to provide 
	 * exact override files instead of letting the path be searched. An example would be for providing a file that isn't the same name as the class.
	 * A very special case is <code>ROOT</code>. This is used when asking to apply root overrides to classes that have no 
	 * super type. 
	 * @param javaClass the java class that the overrides will be applied to. Note: Do not call any property, features, events, or operations calls
	 * or any property, events, or operations Utilities against this class. That is because this class is being introspected at this time
	 * and those calls will return invalid values if called at this point since introspection is not yet complete. 
	 * @param resource set that contributors can use to temporarily load dynamic override files.
	 * @param runnable the runnable that you call to actually apply the override files.
	 * 
	 * @see BeaninfoPlugin#ROOT
	 * @see BeaninfoPlugin#OVERRIDE_EXTENSION
	 * @since 1.0.0
	 */
	public void runOverrides(IPath packagePath, String overrideFileName, JavaClass javaClass, ResourceSet rset, BeaninfoPlugin.IContributorOverrideRunnable runnable);
}
