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
 *  $Revision: 1.1 $  $Date: 2004/03/22 23:49:10 $ 
 */
package org.eclipse.jem.internal.beaninfo.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.jem.internal.proxy.core.IConfigurationContributionInfo;
 
/**
 * Interface for BeanInfo contributors. Supplied either as additional interface on a IClasspathContainer, or as
 * an explicit contributor for BeanInfos from the manifest files.
 * 
 * @since 1.0.0
 */
public interface IBeanInfoContributor {
	
	/**
	 * Return the BeanInfoEntry contributions that are needed.
	 * @param info
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public BeaninfoEntry[] getBeanInfoEntryContributions(IConfigurationContributionInfo info);
	
	/**
	 * For the given package path run the override contributions through the runnable.
	 * 
	 * @param packagePath
	 * @param className the className of the class that the overrides is for. This can be used in very special cases to provide exact override files instead of letting the path be searched. An example would be for providing a file that isn't the same name as the class.
	 * @param resource set that contributors can use to temporarily load dynamic override files.
	 * @param runnable
	 * 
	 * @since 1.0.0
	 */
	public void runOverrides(IPath packagePath, String className, ResourceSet rset, BeaninfoPlugin.IContributorOverrideRunnable runnable);
}
