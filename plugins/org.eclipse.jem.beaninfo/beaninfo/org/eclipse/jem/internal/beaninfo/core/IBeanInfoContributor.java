/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IBeanInfoContributor.java,v $
 *  $Revision: 1.6 $  $Date: 2005/08/24 20:31:28 $ 
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
	 * <p>
	 * This will be called by BeanInfo when it determines that this contributor is applicable 
	 * for the current project being processed. BeaninfoEntry's define the BeanInfo jars and search paths to use.
	 * 
	 * @param info
	 * @return BeanInfo contributions or <code>null</code> if none to contribute.
	 * 
	 * @since 1.0.0
	 */
	public BeaninfoEntry[] getBeanInfoEntryContributions(IConfigurationContributionInfo info);
	
	/**
	 * For the given package path run the override contributions through the runnable.
	 * <p>
	 * This will be called by BeanInfo when it determines that this
	 * contributor is applicable for the current project being processed.
	 * <p>
	 * Implementations must call the runnable (the argument <code>runnable</code>) for each override file that is to be applied to the incoming class.
	 * You can either call the run method that passes in a string pointing to a directory where your override file is
	 * located (i.e. <code>overrideFileName.override</code> where "overrideFileName" is the name passed. Or you can call the run method that 
	 * takes a Resource that is already loaded (and maybe modified or created locally).
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
